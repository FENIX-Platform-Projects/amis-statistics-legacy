package org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.MarkerType;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.FontDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.BarSeries;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.core.domain.rainfall.RainfallBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;
import org.fao.fenix.web.modules.rainfall.server.utils.AfricanGaulCode;

public class RainfallCharts {

	SessionHandle session = BirtUtil.getDesignSessionHandle();
	String rep;
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	List<RainfallBean> rainfallList = null;
	
	public RainfallCharts(List<RainfallBean> rainfallBean) {
		this.rainfallList = rainfallBean;
		this.rep = BirtUtil.randomNameFile(); 
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}
	
	private void createDataSets() throws SemanticException {

		for (int i = 0; i < rainfallList.size() ; i++) {
			ScriptDataSetHandle dataSetHandle;
			dataSetHandle = designHandle.getElementFactory().newScriptDataSet("Chart"+i);

			dataSetHandle.setDataSource("srcScripted");

			PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

			ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

			// date
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(0);
			resultColumn.setColumnName("date");
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);

			// first bar chart
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(1);
			resultColumn.setColumnName("value0");
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);

			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(2);
			resultColumn.setColumnName("value1");
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);
			
			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(i);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode();
			dataSetHandle.setFetch(fetchCode.toString());

			designHandle.getDataSets().add(dataSetHandle);
		}	
			
	}
	

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"date\"] = sourcedata[count][0];").append("\n\t");

		fetchCode.append("row[\"value0\"] = sourcedata[count][1];").append("\n\t");
		fetchCode.append("row[\"value1\"] = sourcedata[count][2];").append("\n\t");
		
		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;

	}

	private StringBuffer generateOpenCode(int rainfallProvince) {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";

		int numElements = rainfallList.get(rainfallProvince).getDates().size();
		for (int i = 0; i < numElements; i++) {
			if ((i + 1) == numElements) {
				tmp += "new Array(2));";
			} else {
				tmp += "new Array(2),";
			}
		}

		openCode.append(tmp).append("\n\n");

		for (int i = 0; i < rainfallList.get(rainfallProvince).getDates().size(); i++) {
			// dates
			openCode.append("sourcedata[" + i + "][0]=\"" + rainfallList.get(rainfallProvince).getDates().get(i) + "\";").append("\n");

			// first bar chart
			openCode.append("sourcedata[" + i + "][1]=" + rainfallList.get(rainfallProvince).getFirstIstogramValues().get(i) + ";").append("\n");

			// line chart
			openCode.append("sourcedata[" + i + "][2]=" + rainfallList.get(rainfallProvince).getLineValues().get(i) + ";").append("\n");
		}

		return openCode;
	}
		
	private ExtendedItemHandle getChart(RainfallBean rainfallBean, int count) throws ExtendedElementException, SemanticException {

		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"Chart" + count);

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		//cwaBar.setType("Bar/Line");
		
		// Plot
		cwaBar.getBlock().getOutline().setVisible(true);
		cwaBar.getBlock().getOutline().setColor(ColorDefinitionImpl.BLUE());

		cwaBar.getBlock().setBounds(BoundsImpl.create(0, 0, 236, 217));

		Plot p = cwaBar.getPlot();
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(
				ColorDefinitionImpl.create(255, 255, 255));

				
		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue(rainfallBean.getTitle());
		cwaBar.getTitle().getLabel().getCaption().getFont().setSize(8);
		cwaBar.getTitle().getLabel().getCaption().getFont().setBold(true);
		cwaBar.getTitle().getLabel().getCaption().getFont().setName("Arial");
		//cwaBar.getTitle().getLabel().getOutline().setVisible(true);
		//cwaBar.getTitle().getLabel().getOutline().setColor(ColorDefinitionImpl.BLUE());
		
		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(6);
		lg.getText().getFont().setName("Arial");
		lia.setStyle(LineStyle.SOLID_LITERAL);
		lg.setVisible(true);
		lg.setPosition(Position.BELOW_LITERAL);
						
		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 20));

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getTitle().setVisible(true);
		xAxisPrimary.getTitle().getCaption().setValue(rainfallBean.getXLabel());
		xAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 6, false, false, false, false, false, 0, null));
		xAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		xAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//xAxisPrimary.getLabel().setVisible(dataGWT.isXAxisShowLabels());
		xAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(rainfallBean.getYLabel());
		yAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 6, false, false, false, false, false, 90, null));
		yAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		yAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//yAxisPrimary.getLabel().setVisible(dataGWT.isYAxisShowLabels());
		yAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"date\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		List<String> series = new ArrayList<String>();
		// Y-Series BAR
		//List<List<String>> labels = MatchValue.getYLabelBarLine(dataGWT);
		//List<String> labelBar = labels.get(0);
				// draw a bar
				BarSeries bs = (BarSeries) BarSeriesImpl.create();

				bs.getLabel().setVisible(false);
				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
				bs.getLabel().getCaption().getFont().setSize(10);
				
				if (rainfallBean.getYearTwo() == null) bs.setSeriesIdentifier(rainfallBean.getYearOne());
				else bs.setSeriesIdentifier(rainfallBean.getYearOne() + "/" + rainfallBean.getYearTwo());
				Query query1 = QueryImpl.create("row[\"value0\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value0");
				bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
//				int red = (int) Math.round((Math.random() * 255));
//				int green = (int) Math.round((Math.random() * 255));
//				int blue = (int) Math.round((Math.random() * 255));
				Fill[] fiaBase=new Fill[1];
				fiaBase[0] = ColorDefinitionImpl.create(29, 68, 137);
								
				sdY1.getSeriesPalette().getEntries().clear();
				for (int i = 0; i < fiaBase.length; i++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[i]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
			
		
			// Y-Series LINE
		
			LineSeries bsAvg = (LineSeries) LineSeriesImpl.create();
			
			bsAvg.setSeriesIdentifier("Avg " + rainfallBean.getAverageRange());//$NON-NLS-1$
			
			query1 = QueryImpl.create("row[\"value1\"]");
			bsAvg.getDataDefinition().add(query1);
			
			series.add("value1");
			// bs.setRiserOutline(null);

			bsAvg.getLabel().setVisible(false);
			
			((Marker) bsAvg.getMarker()).setVisible(true);
			((Marker) bsAvg.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
			((Marker) bsAvg.getMarker()).setSize(1);
			
			//smooth line
			//bs.setCurve(true);
			bsAvg.setConnectMissingValue(true);
			bsAvg.setLabelPosition(Position.LEFT_LITERAL);

			sdY1 = SeriesDefinitionImpl.create();
//			int red = (int) Math.round((Math.random() * 255));
//			int green = (int) Math.round((Math.random() * 255));
//			int blue = (int) Math.round((Math.random() * 255));
			
			bsAvg.getLineAttributes().setColor(ColorDefinitionImpl.create(202, 22, 22));
			bsAvg.getLineAttributes().setThickness(2);

			yAxisPrimary.getSeriesDefinitions().add(sdY1);
			sdY1.getSeries().add(bsAvg);
			
		

		cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		
		BoundColumns.setBoundColumns(chartHandle, "date", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		chartHandle.setName("Chart" + count);
		
		return chartHandle;

	}
	
	public String createCharts() {
		
		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			DesignElementHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			designHandle.getMasterPages().add(simpleMasterPage);

			createDataSources();

			createDataSets();

			for (int i=0; i<rainfallList.size(); i++) {
				ExtendedItemHandle chart = getChart(rainfallList.get(i), i);
				designHandle.getBody().add(chart);
			}
				
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.close();
			//Platform.shutdown();

			System.out.println("Finished Successfull");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rep.substring(0, (rep.length() - 10));
	}
	
	public void addChartToReport(String container, String templateName, ReportDesignHandle designTemplate, String countryCode) throws Exception{
		
		GridHandle reportGridHandle = (GridHandle) designTemplate.findElement(container);
		RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(2);
		CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
		
//		TextItemHandle space;
//		space = designTemplate.getElementFactory().newTextItem("text");
//		space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//		space.setContent("<br><br>");
//		gridCellHandle.getContent().add(space);
		
		int chartNum = designHandle.getBody().getCount();
		
		int count=0;
		GridHandle newGrid = designTemplate.getElementFactory().newGridItem("dataGrid0", 2, 1);
		int nameGrid = 1;
		for (int i = 0; i < chartNum; i++) {
			DesignElementHandle chart=designHandle.findElement("Chart"+i);
			DataSetHandle dset=designHandle.findDataSet("Chart"+i);
			
			dset.setName("Chart"+i);
			designTemplate.getDataSets().add(dset);
			chart.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset.getName());
									
			if (count==2){
				gridCellHandle.getContent().add(newGrid);
				newGrid = designTemplate.getElementFactory().newGridItem("dataGrid"+nameGrid, 2, 1);
				nameGrid++;
				count = 0;
			} 
				
			RowHandle row1 = (RowHandle) newGrid.getRows().get(0);
			CellHandle cell = (CellHandle) row1.getCells().get(count);
			cell.getContent().add(chart);
			
			//add source
			TextItemHandle source;
			source = designTemplate.getElementFactory().newTextItem("text");
			source.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (AfricanGaulCode.africaGaulCodes.contains(countryCode)) source.setContent("<div style='font-size:9px; font-style:italic;'>Source: FAO GIEWS (derived from NOAA satellite images, resolution 5 Km)</div>"); 
			else source.setContent("<div style='font-size:9px; font-style:italic;'>Source: FAO GIEWS (derived from JRC satellite images, resolution 0.5 Degree)</div>");
			cell.getContent().add(source);
			
			count++;
			
		}
		
		//save last grid
		gridCellHandle.getContent().add(newGrid);
		
		designTemplate.saveAs(templateName);
	}
	
}
