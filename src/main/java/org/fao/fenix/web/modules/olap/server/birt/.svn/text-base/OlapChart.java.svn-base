package org.fao.fenix.web.modules.olap.server.birt;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import org.eclipse.birt.chart.model.component.Scale;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.BarSeries;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.fao.fenix.core.domain.olap.OLAPChartBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.olap.common.vo.OLAPChartResultVo;

public class OlapChart {
	
	private ReportDesignHandle designHandle = null;
	private ElementFactory designFactory = null;
	private List<OLAPChartBean> olapChartList;
	GridHandle dataGridHandle;
	
	private static final Logger LOGGER = Logger.getLogger(OlapChart.class);
	
	public OlapChart(List<OLAPChartBean> olapChartList){
		this.olapChartList = olapChartList;
		
//		LOGGER.info(olapChartList.size() + " OLAPChartBeanVO's");
//		for (OLAPChartBean vo : olapChartList) {
//			LOGGER.info(vo.getTitle());
//			Map<String, List<String>> map = vo.getYSeries();
//			for (String key : map.keySet())
//				LOGGER.info("\t" + key + ": " + map.get(key));
//		}
	}
	
	public List<OLAPChartResultVo> createOlapCharts(){
		
		List<OLAPChartResultVo> olapChartResultVoList = new ArrayList<OLAPChartResultVo>();
		
		for (int i = 0; i < olapChartList.size() ; i++) {
			
			SessionHandle session = BirtUtil.getDesignSessionHandle();
			String rep = BirtUtil.randomNameFile();
			OLAPChartResultVo olapChartResultVo = new OLAPChartResultVo();
			
			try {
			
				designHandle = session.createDesign();
				designFactory = designHandle.getElementFactory();

//				ImageHandle imageHandle = designHandle.getElementFactory().newImage("Header");
//				imageHandle.setWidth("17.75cm");
//				imageHandle.setHeight("1.5cm");
//				imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/rainfall_report_banner.tif\"");

				SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");
				simpleMasterPage.setPageType("a4");
				simpleMasterPage.setOrientation("portrait");
				simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "1cm");
				simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "1cm");
				simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.45cm");
				simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.45cm");
				
				//simpleMasterPage.getPageHeader().add(imageHandle);

				// add footer
				//simpleMasterPage.getPageFooter().add(createFooter());

				designHandle.getMasterPages().add(simpleMasterPage);

				createDataSources();
				createDataSets(i);

				dataGridHandle = designFactory.newGridItem("dataGrid", 1, 2);
				designHandle.getBody().add(dataGridHandle);
				
				createChart(i);
				
				createParameters(i);

				designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

				designHandle.close();
				Platform.shutdown();

				olapChartResultVo.setReportName(rep);
				olapChartResultVo.setIFrame("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
						+ "&servletType=preview' width='100%' height='100%' />");
				olapChartResultVo.setChartType(olapChartList.get(i).getChartType());
				
				olapChartResultVoList.add(olapChartResultVo);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return olapChartResultVoList;
		
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}
	
	private StringBuffer generateFetchCode(int chartInfoIndex) {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"xlabel\"] = sourcedata[count][0];").append("\n\t");

		for (int i = 0; i < olapChartList.get(chartInfoIndex).getYSeries().size(); i++) {
			fetchCode.append("row[\"value" + i + "\"] = sourcedata[count][" + (i+1) + "];").append("\n\t");
		}
		
		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;

	}

	private StringBuffer generateOpenCode(int chartInfoIndex) {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";

		int seriesNumber = olapChartList.get(chartInfoIndex).getYSeries().size();
		int numElements = olapChartList.get(chartInfoIndex).getXValues().size();
		for (int i = 0; i < numElements; i++) {
			if ((i + 1) == numElements) {
				tmp += "new Array(" + seriesNumber + "));";
			} else {
				tmp += "new Array(" + seriesNumber + "),";
			}
		}

		openCode.append(tmp).append("\n\n");

		Iterator<Map.Entry<String, List<String>>> keyValuePairs = olapChartList.get(chartInfoIndex).getYSeries().entrySet().iterator();
		List<String> seriesName = new ArrayList<String>();
		for (int i = 0; i < olapChartList.get(chartInfoIndex).getYSeries().size(); i++) {
			Map.Entry<String, List<String>> entry = keyValuePairs.next();
			seriesName.add(entry.getKey());
		}
		
		int countSeriesValue = -1;
		for (int i = 0; i < olapChartList.get(chartInfoIndex).getXValues().size(); i++) {
			
			countSeriesValue++;
			
			// x dimension
			openCode.append("sourcedata[" + i + "][0]=\"" + olapChartList.get(chartInfoIndex).getXValues().get(i) + "\";").append("\n");

			// y dimension
			for (int z = 0; z < olapChartList.get(chartInfoIndex).getYSeries().size(); z++) {
				openCode.append("sourcedata[" + i + "][" + (z+1) + "]=" + olapChartList.get(chartInfoIndex).getYSeries().get(seriesName.get(z)).get(countSeriesValue) + ";").append("\n");
				//datasetIdList.add(entry.getKey());
			}
			
		}

		return openCode;
	}
	
	private void createParameters(int chartInfoIndex) throws SemanticException {
		
		if (!olapChartList.get(chartInfoIndex).getXParameter().equals("") || !olapChartList.get(chartInfoIndex).getYParameter().equals("")){
			TextItemHandle parameter = designHandle.getElementFactory().newTextItem("Par");
			parameter.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			String textOtherDim = "<div style='font-weight:bold; font-size: 12px; color:#FF0000;'>Parameters: </div>";
			textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + olapChartList.get(chartInfoIndex).getXParameter() + "</div>";
			textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + olapChartList.get(chartInfoIndex).getYParameter() + "</div>";
			parameter.setContent(textOtherDim);

			RowHandle row2 = (RowHandle) dataGridHandle.getRows().get(1);
			CellHandle gridCellHandle2 = (CellHandle) row2.getCells().get(0);
			
			gridCellHandle2.getContent().add(parameter);
		}
		
		
		
	}
	
	private void createDataSets(int chartInfoIndex) throws SemanticException {

		ScriptDataSetHandle dataSetHandle;
		dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted");

		dataSetHandle.setDataSource("srcScripted");

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

		ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

		// x dimension
		resultColumn = StructureFactory.createResultSetColumn();
		resultColumn.setPosition(0);
		resultColumn.setColumnName("xlabel");
		resultColumn.setDataType("string");
		computedSet.addItem(resultColumn);
			
		for (int j=0; j < olapChartList.get(chartInfoIndex).getYSeries().size(); j++){
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition((j+1));
			resultColumn.setColumnName("value"+j);
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);
		}
			
		// Set open( ) in code
		StringBuffer openCode = generateOpenCode(chartInfoIndex);
		dataSetHandle.setOpen(openCode.toString());

		// Set fetch( ) in code
		StringBuffer fetchCode = generateFetchCode(chartInfoIndex);
		dataSetHandle.setFetch(fetchCode.toString());

		designHandle.getDataSets().add(dataSetHandle);
		
	}
	
	public void createChart(int chartInfoIndex) throws ExtendedElementException, SemanticException {

		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"setScripted");

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		cwaBar.setType(olapChartList.get(chartInfoIndex).getChartType());
		
		// Plot
		cwaBar.getBlock().getOutline().setVisible(false);
		cwaBar.getBlock().getOutline().setColor(ColorDefinitionImpl.create(208, 221, 237));

		cwaBar.getBlock().setBounds(BoundsImpl.create(0, 0, 500, 230));

		Plot p = cwaBar.getPlot();
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(ColorDefinitionImpl.create(255, 255, 255));
		cwaBar.getBlock().setBackground(ColorDefinitionImpl.create(255, 255, 255));
		
				
		cwaBar.setUnitSpacing(50);
		cwaBar.setTransposed(false);
		
		//scale
		Scale scale = cwaBar.getPrimaryOrthogonalAxis(((ChartWithAxes) cwaBar).getPrimaryBaseAxes()[0]).getScale();
		scale.unsetStepNumber();
		scale.setMin(NumberDataElementImpl.create(0));
		scale.setMax(null);
		
		cwaBar.getPrimaryOrthogonalAxis(((ChartWithAxes) cwaBar).getPrimaryBaseAxes()[0]).setFormatSpecifier(null);
		
		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue(olapChartList.get(chartInfoIndex).getTitle());
		cwaBar.getTitle().getLabel().getCaption().getFont().setSize(15);
		cwaBar.getTitle().getLabel().getCaption().getFont().setBold(true);
		cwaBar.getTitle().getLabel().getCaption().getFont().setName("Arial");
		cwaBar.getTitle().getLabel().getCaption().setColor(ColorDefinitionImpl.BLACK());
		cwaBar.getTitle().getLabel().getOutline().setVisible(false);
				
		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(10);
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
		xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getTitle().setVisible(true);
		xAxisPrimary.getTitle().getCaption().setValue(olapChartList.get(chartInfoIndex).getXLabel());
		xAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 8, false, false, false, false, false, 0, null));
		xAxisPrimary.getTitle().getCaption().getFont().setSize(10);
		xAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		xAxisPrimary.getTitle().getCaption().setColor(ColorDefinitionImpl.BLACK());
		//xAxisPrimary.getLabel().setVisible(dataGWT.isXAxisShowLabels());
		xAxisPrimary.getLabel().getCaption().getFont().setSize(10);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		xAxisPrimary.getLabel().getCaption().setColor(ColorDefinitionImpl.BLACK());
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(olapChartList.get(chartInfoIndex).getYLabel());
		yAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 8, false, false, false, false, false, 90, null));
		yAxisPrimary.getTitle().getCaption().getFont().setSize(10);
		yAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		yAxisPrimary.getTitle().getCaption().setColor(ColorDefinitionImpl.BLACK());
		//yAxisPrimary.getLabel().setVisible(dataGWT.isYAxisShowLabels());
		yAxisPrimary.getLabel().getCaption().getFont().setSize(10);
		yAxisPrimary.getLabel().getCaption().setColor(ColorDefinitionImpl.BLACK());
		
		// set a fix scale?
		//yAxisPrimary.getScale().setMax(NumberDataElementImpl.create(maxScale));
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"xlabel\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		Iterator<Map.Entry<String, List<String>>> keyValuePairs = olapChartList.get(chartInfoIndex).getYSeries().entrySet().iterator();
		List<String> series = new ArrayList<String>();
		for (int j = 0; j < olapChartList.get(chartInfoIndex).getYSeries().size(); j++) {
			
				Series bs=null;
			
				if ((olapChartList.get(chartInfoIndex).getChartType() != null) && olapChartList.get(chartInfoIndex).getChartType().equals("Line")) 
					bs = (LineSeries) LineSeriesImpl.create();
				else  
					bs = (BarSeries) BarSeriesImpl.create();
				
				bs.getLabel().setVisible(false);
				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
				bs.getLabel().getCaption().getFont().setSize(10);
				
				Map.Entry<String, List<String>> entry = keyValuePairs.next();
				
				bs.setSeriesIdentifier(entry.getKey());
				Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value"+j);
				//bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
				int red = (int) Math.round((Math.random() * 255));
				int green = (int) Math.round((Math.random() * 255));
				int blue = (int) Math.round((Math.random() * 255));
				final Fill[] fiaBase = {
						ColorDefinitionImpl.create(red, green, blue),
						
				};
				
				sdY1.getSeriesPalette().getEntries().clear();
				for (int i = 0; i < fiaBase.length; i++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[i]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
			}
		
			
//		if (rainfallBean.getLineValues().size()>0){	
//			LineSeries bs = (LineSeries) LineSeriesImpl.create();
//			
//			bs.setSeriesIdentifier("Avg " + rainfallBean.getAverageRange());//$NON-NLS-1$
//			// bs.setSeriesIdentifier("Average");
//			Query query1=null;
//			if (rainfallBean.getSecondIstogramValues().size() > 0)
//				query1 = QueryImpl.create("row[\"value2\"]");
//			else query1 = QueryImpl.create("row[\"value1\"]");
//			bs.getDataDefinition().add(query1);
//
//			// bs.setRiserOutline(null);
//
//			bs.getLabel().setVisible(false);
//			
//			((Marker) bs.getMarker()).setVisible(true);
//			((Marker) bs.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
//			((Marker) bs.getMarker()).setSize(1);
//			
//			//smooth line
//			//bs.setCurve(true);
//			bs.setLabelPosition(Position.LEFT_LITERAL);
//
//			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
////			int red = (int) Math.round((Math.random() * 255));
////			int green = (int) Math.round((Math.random() * 255));
////			int blue = (int) Math.round((Math.random() * 255));
//			
//			bs.getLineAttributes().setColor(ColorDefinitionImpl.create(202, 22, 22));
//			bs.getLineAttributes().setThickness(2);
//
//			yAxisPrimary.getSeriesDefinitions().add(sdY1);
//			sdY1.getSeries().add(bs);
//		}
		
		cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		
		//cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL);
		
		BoundColumns.setBoundColumns(chartHandle, "xlabel", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		
		RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row1.getCells().get(0);

		gridCellHandle.getContent().add(chartHandle);
		
	}

}