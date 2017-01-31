package org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.MarkerType;
import org.eclipse.birt.chart.model.attribute.NumberFormatSpecifier;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.NumberFormatSpecifierImpl;
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
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.core.framework.Platform;
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
import org.fao.fenix.core.persistence.isfp.util.ChartFoodPricesInfo;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;

public class ISFPChart {

	/**
	 *  list[x] body label-value
	 */
	List<List<String>> dataChart1 = null;
	List<List<String>> dataChart2 = null;
	String market;
	String width;
	String height;
	List<ChartFoodPricesInfo> chartFoodPricesInfo;
	
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	String nameReport = null;
	
	public ISFPChart(List<ChartFoodPricesInfo> data, String market, String width, String height){
		if (data.get(0) != null) this.dataChart1 = data.get(0).getData();
		if (data.get(1) != null) this.dataChart2 = data.get(1).getData();
		this.market = market;
		this.width = width;
		this.height = height;
		this.chartFoodPricesInfo = data;
		
		try {
			
			tempChart();
								
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}
	
	
	private void createDataSets(int chartNum) throws SemanticException {
		// Data Set
		ScriptDataSetHandle dataSetHandle = designHandle.getElementFactory().newScriptDataSet("Chart" + chartNum);
		dataSetHandle.setDataSource("srcScripted");

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

		// Since this is a Scripted Data Source you need to tell the
		// DataSet what the columns are. For this example, we will just
		// hard code the known resultColumn values

		ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();
		resultColumn.setPosition(0);
		resultColumn.setColumnName("label");
		resultColumn.setDataType("string");
		computedSet.addItem(resultColumn);
		
		
		for (int i=1; i< chartFoodPricesInfo.get((chartNum-1)).getData().get(0).size() ; i++){
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(1);
			resultColumn.setColumnName("value" + i);
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);
		}
		
		
		// Set open( ) in code
		StringBuffer openCode = generateOpenCode((chartNum-1));
		dataSetHandle.setOpen(openCode.toString());

		// Set fetch( ) in code
		StringBuffer fetchCode = generateFetchCode((chartNum-1));
		dataSetHandle.setFetch(fetchCode.toString());
		
		designHandle.getDataSets().add(dataSetHandle);
	}
	
	private StringBuffer generateOpenCode(int chartNum) {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";
		
		int numCol = (chartFoodPricesInfo.get(chartNum).getData().get(0).size()-1);

		for (int i = 0; i < chartFoodPricesInfo.get(chartNum).getData().size(); i++) {
			if ((i + 1) == chartFoodPricesInfo.get(chartNum).getData().size()) {
				tmp += "new Array(" + numCol + "));";
			} else {
				tmp += "new Array(" + numCol + "),";
			}

		}

		openCode.append(tmp).append("\n\n");

		for (int i = 0; i < chartFoodPricesInfo.get(chartNum).getData().size(); i++) {
			for (int j = 0; j < chartFoodPricesInfo.get(chartNum).getData().get(i).size(); j++) {
				if (j == 0){
					openCode.append("sourcedata[" + i + "][" + j + "]=\"" + chartFoodPricesInfo.get(chartNum).getData().get(i).get(j) + "\";").append("\n");
				} else {
					openCode.append("sourcedata[" + i + "][" + j + "]=" + chartFoodPricesInfo.get(chartNum).getData().get(i).get(j) + ";").append("\n");
				}
				
			}
		}
	
		return openCode;
	}
	
	private StringBuffer generateFetchCode(int chartNum) {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		fetchCode.append("row[\"label\"] = sourcedata[count][0];").append("\n\t");
		
		for (int i=1; i < chartFoodPricesInfo.get(chartNum).getData().get(0).size(); i++){
			fetchCode.append("row[\"value" + i + "\"] = sourcedata[count][" + i + "];").append("\n\t");
		}
		

		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;
	}
	
	
	public void tempChart(){
	
		SessionHandle session = BirtUtil.getDesignSessionHandle();
		

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			DesignElementHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			designHandle.getMasterPages().add(simpleMasterPage);

			createDataSources();
						
			if (dataChart1 != null){
				createDataSets(1);
				createChart(1);
			}
			
			if (dataChart2 != null){
				createDataSets(2);
				createChart(2);
			}
			
			nameReport = BirtUtil.randomNameFile();
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + nameReport);

			designHandle.close();
			Platform.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createChart(int chartNum) throws SemanticException {
		
		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

			chartHandle.setHeight(this.height);
			chartHandle.setWidth(this.width);
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP, ("Chart" + chartNum));

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		
		ChartWithAxes lineChart = ChartWithAxesImpl.create();
		lineChart.setType("Line");

		// Plot
		lineChart.getBlock().setBackground(ColorDefinitionImpl.WHITE());
		lineChart.getBlock().getOutline().setColor(ColorDefinitionImpl.create(219, 95, 46));
		lineChart.getBlock().getInsets().setBottom(0);
		lineChart.getBlock().getInsets().setTop(0);
		lineChart.getBlock().getInsets().setLeft(0);
		lineChart.getBlock().getInsets().setRight(0);
		lineChart.getBlock().getOutline().setVisible(true);
		lineChart.getBlock().setBounds(BoundsImpl.create(0, 0, Double.valueOf(this.width)-58, Double.valueOf(this.height)-90));

		Plot p = lineChart.getPlot();
		p.getOutline().setVisible(false);
		//p.getOutline().setColor(ColorDefinitionImpl.create(219, 95, 46));
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(ColorDefinitionImpl.create(252, 240, 230));
		
		
		
		// Title
		lineChart.getTitle().getLabel().getCaption().setValue(chartFoodPricesInfo.get((chartNum-1)).getTitle());
		lineChart.getTitle().getLabel().getCaption().getFont().setSize(10);
		lineChart.getTitle().getLabel().setBackground(ColorDefinitionImpl.create(237, 162, 122));
		lineChart.getTitle().getLabel().getCaption().setColor(ColorDefinitionImpl.create(255, 255, 255));
		lineChart.getTitle().setBackground(ColorDefinitionImpl.create(237, 162, 122));
		

		// Trasposed axis
		// cwaBar.setTransposed(dataGWT.isFlip());

		// Legend
		Legend lg = lineChart.getLegend();
		lg.setVisible(true);
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(8);
		lia.setStyle(LineStyle.SOLID_LITERAL);
		lineChart.getLegend().setPosition(Position.BELOW_LITERAL);
		
		

		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 30));

		// X-Axis
		Axis xAxisPrimary = lineChart.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getMajorGrid().getLineAttributes().setVisible(true);
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);
		//xAxisPrimary.getTitle().setVisible(true);
		//xAxisPrimary.getTitle().getCaption().setValue(xRow);
		xAxisPrimary.getTitle().getCaption().getFont().setSize(10);
		xAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		
		// Y-Axis
		Axis yAxisPrimary = lineChart.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getMajorGrid().getLineAttributes().setVisible(true);
		yAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		yAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(chartFoodPricesInfo.get((chartNum-1)).getMeasurementUnit());
		NumberFormatSpecifier format = NumberFormatSpecifierImpl.create();
		format.setFractionDigits(1);
		yAxisPrimary.setFormatSpecifier(format);
		
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"label\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		List<String> series = new ArrayList<String>();
		// Y-Series
		for (int j=1; j < chartFoodPricesInfo.get((chartNum-1)).getData().get(0).size(); j++){
		
			LineSeries bs = (LineSeries) LineSeriesImpl.create();

			bs.setSeriesIdentifier(chartFoodPricesInfo.get((chartNum-1)).getSeries().get((j-1)));
			Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
			bs.getDataDefinition().add(query1);

			series.add("value"+j);
			
			// bs.setRiserOutline(null);
			bs.setConnectMissingValue(false);
			
			bs.getLabel().setVisible(false);

			((Marker) bs.getMarker()).setVisible(true);
			((Marker) bs.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
			((Marker) bs.getMarker()).setSize(1);

			// smooth line
			// bs.setCurve(true);
			bs.setLabelPosition(Position.LEFT_LITERAL);
			bs.getLabel().getCaption().getFont().setSize(8);

			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
			

			if (j==1) bs.getLineAttributes().setColor(ColorDefinitionImpl.BLACK());
			else if (j==2) bs.getLineAttributes().setColor(ColorDefinitionImpl.RED());
			else if (j==3) bs.getLineAttributes().setColor(ColorDefinitionImpl.BLUE());
			else if (j==4) bs.getLineAttributes().setColor(ColorDefinitionImpl.GREEN());
			else {
				int red = (int) Math.round((Math.random() * 255));
				int green = (int) Math.round((Math.random() * 255));
				int blue = (int) Math.round((Math.random() * 255));
				bs.getLineAttributes().setColor(ColorDefinitionImpl.create(red, green, blue));
			}
			
			bs.getLineAttributes().setThickness(2);

			yAxisPrimary.getSeriesDefinitions().add(sdY1);
			sdY1.getSeries().add(bs);
			
		}
		
		BoundColumns.setBoundColumns(chartHandle, "label", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", lineChart);
		chartHandle.setName("Chart" + chartNum);
		
		designHandle.getBody().add(chartHandle);
				

	}
	
	public void addChartToReport(String container, String templateName, ReportDesignHandle designTemplate){
		
		//================= take the chart element with datasource and dataset ====
		DesignElementHandle chart1=null;
		DataSetHandle dset1=null;
		if (dataChart1 != null){
			chart1= designHandle.findElement("Chart1");
			dset1=designHandle.findDataSet("Chart1");
		}
		
		DesignElementHandle chart2=null;
		DataSetHandle dset2=null;
		if (dataChart2 != null){
			chart2= designHandle.findElement("Chart2");
			dset2=designHandle.findDataSet("Chart2");
		}
				
		//=================== add chart to template ============================
			
		try {
			
			if (dataChart1 != null){
				dset1.setName("chartF");
				designTemplate.getDataSets().add(dset1);
							
				chart1.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset1.getName());
								
				GridHandle reportGridHandle = (GridHandle) designTemplate.findElement(container);
				RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				chart1.setName("chartF");
				gridCellHandle.getContent().add(chart1);
			}
			
			
			if (dataChart2 != null){
				dset2.setName("chartS");
				designTemplate.getDataSets().add(dset2);
							
				chart2.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset2.getName());
				GridHandle reportGridHandle = (GridHandle) designTemplate.findElement(container);				
				RowHandle birtRowTwo = (RowHandle) reportGridHandle.getRows().get(1);
				CellHandle gridCellHandleTwo = (CellHandle) birtRowTwo.getCells().get(1);
				chart2.setName("chartS");
				gridCellHandleTwo.getContent().add(chart2);
			}
			
						
			designTemplate.saveAs(templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
				
		
	}
	
	
}
