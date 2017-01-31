package org.fao.fenix.web.modules.birt.server.utils.fsatmisTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
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
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
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
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.core.domain.udtable.UDTable;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;

public class CreateFsatmisChartForReport {

	SessionHandle session = BirtUtil.getDesignSessionHandle();
	String rep;
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	List<UDTable> data = null;
	
	
	public CreateFsatmisChartForReport(List<UDTable> data){
		this.rep = BirtUtil.randomNameFile();
		this.data = data;
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

			for (int i=0; i<data.size(); i++) {
				ExtendedItemHandle chart = getChart(data.get(i), i);
				designHandle.getBody().add(chart);
			}
				
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.close();
			//Platform.shutdown();

			System.out.println("Finished Successfull created FS-ATMIS charts");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rep.substring(0, (rep.length() - 10));
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}
	
	private void createDataSets() throws SemanticException {

		for (int i = 0; i < data.size() ; i++) {
			ScriptDataSetHandle dataSetHandle;
			dataSetHandle = designHandle.getElementFactory().newScriptDataSet("Chart"+i);

			dataSetHandle.setDataSource("srcScripted");

			PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

			ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

			// x axis
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(0);
			resultColumn.setColumnName("value0");
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);

			
			int numOfSeries = (data.get(i).getHeaders().size() -1);
			
			for (int j=0; j<numOfSeries; j++){
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition((j+1));
				resultColumn.setColumnName("value" + (j+1));
				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}
			
			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(i);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode(i);
			dataSetHandle.setFetch(fetchCode.toString());

			designHandle.getDataSets().add(dataSetHandle);
		}	
			
	}
	

	private StringBuffer generateFetchCode(int index) {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		for (int i=0; i<data.get(index).getRows().get(0).getCells().size(); i++){
			fetchCode.append("row[\"value" + i + "\"] = sourcedata[count][" + i + "];").append("\n\t");
		}
		
		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;

	}

	private StringBuffer generateOpenCode(int index) {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";

		int lenghtArray = data.get(index).getHeaders().size();
		int numElements = data.get(index).getRows().size();
		for (int i = 0; i < numElements; i++) {
			if ((i + 1) == numElements) {
				tmp += "new Array(" + lenghtArray + "));";
			} else {
				tmp += "new Array(" + lenghtArray + "),";
			}
		}

		openCode.append(tmp).append("\n\n");

		for (int i = 0; i < data.get(index).getRows().size(); i++) {
			for (int j=0; j < data.get(index).getRows().get(i).getCells().size(); j++){
				openCode.append("sourcedata[" + i + "][" + j + "]=\"" + data.get(index).getRows().get(i).getCells().get(j).getValue() + "\";").append("\n");
			}
		}

		return openCode;
	}
	
	
	private ExtendedItemHandle getChart(UDTable udTable, int count) throws ExtendedElementException, SemanticException {

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
		cwaBar.getTitle().getLabel().getCaption().setValue("");
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
		xAxisPrimary.getTitle().getCaption().setValue(udTable.getHeaders().get(0).getHeader());
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
		yAxisPrimary.getTitle().getCaption().setValue("");
		yAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 6, false, false, false, false, false, 90, null));
		yAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		yAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//yAxisPrimary.getLabel().setVisible(dataGWT.isYAxisShowLabels());
		yAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"value0\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		
		// Y-Series BAR
		//List<List<String>> labels = MatchValue.getYLabelBarLine(dataGWT);
		//List<String> labelBar = labels.get(0);
		List<String> series = new ArrayList<String>();
		for (int i = 1; i < udTable.getHeaders().size(); i++) {
				BarSeries bs = (BarSeries) BarSeriesImpl.create();

				bs.getLabel().setVisible(false);
				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
				bs.getLabel().getCaption().getFont().setSize(10);
				
				
				bs.setSeriesIdentifier(udTable.getHeaders().get(i).getHeader());
				Query query1 = QueryImpl.create("row[\"value" + i + "\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value"+i);
				bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
				int red = (int) Math.round((Math.random() * 255));
				int green = (int) Math.round((Math.random() * 255));
				int blue = (int) Math.round((Math.random() * 255));
				final Fill[] fiaBase = {
						ColorDefinitionImpl.create(red, green, blue),
						
				};
				sdY1.getSeriesPalette().getEntries().clear();
				for (int j = 0; j < fiaBase.length; j++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[j]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
		}	
		
		
		BoundColumns.setBoundColumns(chartHandle, "value0", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		chartHandle.setName("Chart" + count);
		
		return chartHandle;

	}
	
	public void addChartToReport(String container, int row, int col, int chartNum, String templateName, ReportDesignHandle designTemplate) throws Exception{
		
		GridHandle reportGridHandle = (GridHandle) designTemplate.findElement(container);
		RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(row);
		CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(col);
				
		DesignElementHandle chart=designHandle.findElement("Chart"+chartNum);
		DataSetHandle dset=designHandle.findDataSet("Chart"+chartNum);
			
		dset.setName("Chart"+chartNum);
		designTemplate.getDataSets().add(dset);
		chart.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset.getName());
									
		gridCellHandle.getContent().add(chart);
		
		designTemplate.saveAs(templateName);
	}
		
}
