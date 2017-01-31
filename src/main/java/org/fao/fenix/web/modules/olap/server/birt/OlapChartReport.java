package org.fao.fenix.web.modules.olap.server.birt;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
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
import org.fao.fenix.core.domain.olap.OLAPChartBean;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class OlapChartReport {
	
	private ReportDesignHandle design = null;
	private ElementFactory designFactory = null;
	private List<OLAPChartBean > olapChartList;
	GridHandle dataGridHandle;
	String rptDesign;
	
	public OlapChartReport(List<OLAPChartBean> olapChartList, String rptDesign){
		this.olapChartList = olapChartList;
		this.rptDesign = rptDesign;
	}
	
	public String addOlapChartsToReport(){
		
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {
			design = session.openDesign(name);
			designFactory = design.getElementFactory();
		
			createDataSources();
			createDataSets();
			
			dataGridHandle = designFactory.newGridItem("dataGrid", 1, olapChartList.size());
			design.getBody().add(dataGridHandle);
			
			createChart();
			
			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());

		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign
				+ "&servletType=preview' width='100%' height='100%' />";
	}
		
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = design.getElementFactory().newScriptDataSource("srcScripted");
		design.getDataSources().add(dataSourceHandle);
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
	
	private void createDataSets() throws SemanticException {

		ScriptDataSetHandle dataSetHandle;
		
		for (int i = 0; i < olapChartList.size() ; i++) {
		
			dataSetHandle = designFactory.newScriptDataSet("setScripted"+i);

			dataSetHandle.setDataSource("srcScripted");

			PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

			ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

			// x dimension
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(0);
			resultColumn.setColumnName("xlabel");
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);
				
			for (int j=0; j < olapChartList.get(i).getYSeries().size(); j++){
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition((j+1));
				resultColumn.setColumnName("value"+j);
				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}
				
			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(i);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode(i);
			dataSetHandle.setFetch(fetchCode.toString());

			design.getDataSets().add(dataSetHandle);
		
		}	
		
		
		
	}
	
	public void createChart() throws ExtendedElementException, SemanticException {

		for (int i = 0; i < olapChartList.size() ; i++) {
			
			GridHandle gridForChart = designFactory.newGridItem("dataGrid", 1, 2);
			
			ExtendedItemHandle chartHandle = design.getElementFactory().newExtendedItem(null, "Chart"+i);

			try {

				chartHandle.setProperty("outputFormat", "PNG");
				chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
						"setScripted"+i);

			} catch (SemanticException e) {
				e.printStackTrace();
			}

			
			ChartWithAxes cwaBar = ChartWithAxesImpl.create();
			cwaBar.setType(olapChartList.get(i).getChartType());
			
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
			cwaBar.getTitle().getLabel().getCaption().setValue(olapChartList.get(i).getTitle());
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
			xAxisPrimary.getTitle().getCaption().setValue(olapChartList.get(i).getXLabel());
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
			yAxisPrimary.getTitle().getCaption().setValue(olapChartList.get(i).getYLabel());
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

			Iterator<Map.Entry<String, List<String>>> keyValuePairs = olapChartList.get(i).getYSeries().entrySet().iterator();
			List<String> series = new ArrayList<String>();
			for (int j = 0; j < olapChartList.get(i).getYSeries().size(); j++) {
				
					Series bs=null;
				
					if (olapChartList.get(i).getChartType().equals("Bar")) bs = (BarSeries) BarSeriesImpl.create();
					else if (olapChartList.get(i).getChartType().equals("Line")) bs = (LineSeries) LineSeriesImpl.create();
					
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
					for (int z = 0; z < fiaBase.length; z++) {
						sdY1.getSeriesPalette().getEntries().add(fiaBase[z]);
					}

					yAxisPrimary.getSeriesDefinitions().add(sdY1);
					sdY1.getSeries().add(bs);
				}
			
				
			cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
			
			BoundColumns.setBoundColumns(chartHandle, "xlabel", series);
			
			chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
			
			RowHandle row = (RowHandle) gridForChart.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(chartHandle);
			
			if (!olapChartList.get(i).getXParameter().equals("") || !olapChartList.get(i).getYParameter().equals("")){
				TextItemHandle parameter = design.getElementFactory().newTextItem("Par");
				parameter.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				String textOtherDim = "<div style='font-weight:bold; font-size: 12px; color:#FF0000;'>Parameters: </div>";
				textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + olapChartList.get(i).getXParameter() + "</div>";
				textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + olapChartList.get(i).getYParameter() + "</div>";
				parameter.setContent(textOtherDim);

				row = (RowHandle) gridForChart.getRows().get(1);
				gridCellHandle = (CellHandle) row.getCells().get(0);
				
				gridCellHandle.getContent().add(parameter);
			}
			
					
			//add chart to report
			RowHandle rowMain = (RowHandle) gridForChart.getRows().get(i);
			CellHandle gridCellHandleMain = (CellHandle) rowMain.getCells().get(0);
			gridCellHandleMain.getContent().add(gridForChart);
			
					
		}
	
	}
		
}
