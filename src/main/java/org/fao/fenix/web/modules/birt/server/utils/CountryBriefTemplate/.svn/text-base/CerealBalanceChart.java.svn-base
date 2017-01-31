package org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
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
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.cb.CerealBalanceChartBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;

public class CerealBalanceChart {
	
	SessionHandle session = BirtUtil.getDesignSessionHandle();
	String rep = BirtUtil.randomNameFile();
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	CerealBalanceChartBean cerealBalanceChartBean = null;
	Map<String,List<Integer>> colorSeries;
	Map<String, Integer> signValues;
	Map<String, String> labels;
	
	CodecDao codecDao;
	
	public CerealBalanceChart(CerealBalanceChartBean cerealBalanceChartBean, CodecDao codecDao){
		this.cerealBalanceChartBean = cerealBalanceChartBean;
		this.codecDao = codecDao;
		signValues = new HashMap<String, Integer>();
		signValues.put("B4", 1);
		signValues.put("C4", -1);
		signValues.put("D4", -1);
		signValues.put("E4", -1);
		signValues.put("F4", -1);
		signValues.put("H4", 1);
		signValues.put("I4", 1);
		signValues.put("A4", 1);
		signValues.put("G4", -1);
		
		labels = new HashMap<String, String>();
		labels.put("B4", "Production");
		labels.put("C4", "Food Use");
		labels.put("D4", "Feed Use");
		labels.put("E4", "Other Use");
		labels.put("F4", "Exports");
		labels.put("H4", "Comm Imports");
		labels.put("I4", "Food Aid");
		labels.put("A4", "Stock Buildup");
		labels.put("G4", "Stock Drawdown");
		
		
		colorSeries = new HashMap<String, List<Integer>>();
		List<Integer> rgb;
		rgb = new ArrayList<Integer>();
		rgb.add(0);
		rgb.add(128);
		rgb.add(128);
		colorSeries.put("B4", rgb);
		
		rgb = new ArrayList<Integer>();
		rgb.add(255);
		rgb.add(102);
		rgb.add(0);
		colorSeries.put("C4", rgb);
		
		rgb = new ArrayList<Integer>();
		rgb.add(255);
		rgb.add(204);
		rgb.add(0);
		colorSeries.put("D4", rgb);
		
		rgb = new ArrayList<Integer>();
		rgb.add(153);
		rgb.add(51);
		rgb.add(0);
		colorSeries.put("E4", rgb);
				
		rgb = new ArrayList<Integer>();
		rgb.add(153);
		rgb.add(204);
		rgb.add(0);
		colorSeries.put("F4", rgb);
				
		rgb = new ArrayList<Integer>();
		rgb.add(51);
		rgb.add(204);
		rgb.add(204);
		colorSeries.put("H4", rgb);
				
		rgb = new ArrayList<Integer>();
		rgb.add(0);
		rgb.add(0);
		rgb.add(0);
		colorSeries.put("I4", rgb);
				
		rgb = new ArrayList<Integer>();
		rgb.add(255);
		rgb.add(204);
		rgb.add(153);
		colorSeries.put("A4", rgb);
				
		rgb = new ArrayList<Integer>();
		rgb.add(128);
		rgb.add(0);
		rgb.add(128);
		colorSeries.put("G4", rgb);
		
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}
	
	private void createDataSets() throws SemanticException {

		ScriptDataSetHandle dataSetHandle;
		dataSetHandle = designHandle.getElementFactory().newScriptDataSet("Chart100");

		dataSetHandle.setDataSource("srcScripted");

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

		ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

		// date
		resultColumn = StructureFactory.createResultSetColumn();
		resultColumn.setPosition(0);
		resultColumn.setColumnName("date");
		resultColumn.setDataType("string");
		computedSet.addItem(resultColumn);

		
		for (int i=0; i<cerealBalanceChartBean.getSeries().size(); i++){
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition((i+1));
			resultColumn.setColumnName("value"+i);
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);
		}
		
		// Set open( ) in code
		StringBuffer openCode = generateOpenCode();
		dataSetHandle.setOpen(openCode.toString());

		// Set fetch( ) in code
		StringBuffer fetchCode = generateFetchCode();
		dataSetHandle.setFetch(fetchCode.toString());

		designHandle.getDataSets().add(dataSetHandle);
	
			
	}
	

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"date\"] = sourcedata[count][0];").append("\n\t");

		for (int i=0; i<cerealBalanceChartBean.getSeries().size(); i++)
			fetchCode.append("row[\"value" + i + "\"] = sourcedata[count][" + (i+1)+ "];").append("\n\t");
		
		
		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;

	}

	private StringBuffer generateOpenCode() {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";

		int numElements = cerealBalanceChartBean.getData().size();
		for (int i = 0; i < numElements; i++) {
			if ((i + 1) == numElements) {
				tmp += "new Array(9));";
			} else {
				tmp += "new Array(9),";
			}
		}

		openCode.append(tmp).append("\n\n");

		for (int i=0; i<cerealBalanceChartBean.getData().size(); i++) {
			
			for (int j=0; j<cerealBalanceChartBean.getData().get(i).size(); j++){
				if (j>0){
					float v = (signValues.get(cerealBalanceChartBean.getSeries().get((j-1))) * Float.valueOf(cerealBalanceChartBean.getData().get(i).get(j)));
					openCode.append("sourcedata[" + i + "][" + j + "]=\"" + v + "\";").append("\n");
				} else openCode.append("sourcedata[" + i + "][" + j + "]=\"" + cerealBalanceChartBean.getData().get(i).get(j) + "\";").append("\n");
			}
			
		}

		return openCode;
	}
	
		
	private ExtendedItemHandle getChart() throws ExtendedElementException, SemanticException {

		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"Chart100");

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		//cwaBar.setType("Bar/Line");
		
		// Plot
		cwaBar.getBlock().getOutline().setVisible(true);
		cwaBar.getBlock().getOutline().setColor(ColorDefinitionImpl.BLUE());

		cwaBar.getBlock().setBounds(BoundsImpl.create(0, 0, 500, 170));

		Plot p = cwaBar.getPlot();
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(
				ColorDefinitionImpl.create(255, 255, 255));

		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue("Total Cereal Balance");
		cwaBar.getTitle().getLabel().getCaption().getFont().setSize(8);
		cwaBar.getTitle().getLabel().getCaption().getFont().setBold(true);
		cwaBar.getTitle().getLabel().getCaption().getFont().setName("Arial");
		//cwaBar.getTitle().getLabel().getOutline().setVisible(true);
		//cwaBar.getTitle().getLabel().getOutline().setColor(ColorDefinitionImpl.BLUE());
		
		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(7);
		lg.getText().getFont().setName("Arial");
		lia.setStyle(LineStyle.SOLID_LITERAL);
		lg.setVisible(true);
		lg.setPosition(Position.RIGHT_LITERAL);
						
		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 20));

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);
		xAxisPrimary.getTitle().setVisible(true);
		xAxisPrimary.getTitle().getCaption().setValue("");
		xAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 6, false, false, false, false, false, 0, null));
		xAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		xAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//xAxisPrimary.getLabel().setVisible(dataGWT.isXAxisShowLabels());
		xAxisPrimary.getLabel().getCaption().getFont().setSize(6);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(cerealBalanceChartBean.getMeasurementUnit());
		yAxisPrimary.getMajorGrid().getLineAttributes().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 6, false, false, false, false, false, 90, null));
		yAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		yAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//yAxisPrimary.getLabel().setVisible(dataGWT.isYAxisShowLabels());
		yAxisPrimary.getLabel().getCaption().getFont().setSize(6);
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"date\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		
		// Y-Series
		//List<List<String>> labels = MatchValue.getYLabelBarLine(dataGWT);
		//List<String> labelBar = labels.get(0);
		List<String> series = new ArrayList<String>();
		// draw a bar
		for (int i=0; i<cerealBalanceChartBean.getSeries().size(); i++){
				BarSeries bs = (BarSeries) BarSeriesImpl.create();

				bs.getLabel().setVisible(false);
				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
				bs.getLabel().getCaption().getFont().setSize(10);
				
				bs.setSeriesIdentifier(labels.get(cerealBalanceChartBean.getSeries().get(i)));
				Query query1 = QueryImpl.create("row[\"value" + i + "\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value"+i);
				
				bs.setStacked(true);
				bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
//				int red = (int) Math.round((Math.random() * 255));
//				int green = (int) Math.round((Math.random() * 255));
//				int blue = (int) Math.round((Math.random() * 255));
				Fill[] fiaBase=new Fill[1];
				List<Integer> serCol = colorSeries.get(cerealBalanceChartBean.getSeries().get(i));
				fiaBase[0] = ColorDefinitionImpl.create(serCol.get(0), serCol.get(1), serCol.get(2));
								
				sdY1.getSeriesPalette().getEntries().clear();
				for (int j = 0; j < fiaBase.length; j++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[j]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
		}
			

		cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		
		BoundColumns.setBoundColumns(chartHandle, "date", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		chartHandle.setName("Chart100");
		
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

			ExtendedItemHandle chart = getChart();
			designHandle.getBody().add(chart);
							
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
				
		DesignElementHandle chart=designHandle.findElement("Chart100");
		DataSetHandle dset=designHandle.findDataSet("Chart100");
			
		dset.setName("Chart100");
		designTemplate.getDataSets().add(dset);
		chart.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset.getName());
									
		gridCellHandle.getContent().add(chart);
		
		designTemplate.saveAs(templateName);
	}

}
