package org.fao.fenix.web.modules.core.client.utils.highcharts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.adapters.highcharts.codegen.sections.options.OptionPath;
import org.bouncycastle.mail.smime.handlers.x_pkcs7_mime;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartParametersConstants;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartVO;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.Style;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.YAxis;
import org.moxieapps.gwt.highcharts.client.Chart.ZoomType;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.XAxisLabels;
import org.moxieapps.gwt.highcharts.client.labels.YAxisLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.ColumnPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions.Stacking;

import com.extjs.gxt.ui.client.widget.ContentPanel;


public class HighChart {
	
	
	public static void createChart(ContentPanel panel, HighChartVO vo, Integer width, Integer height) {
		
		System.out.println("HighChart createChart: " + width + " | " + height);
		System.out.println("PARAMTERS: " + vo.getParameters());
		
		Chart chart = new Chart();
		
		String type = HighChartConstants.chartType.get(vo.getType());
		
		System.out.println("type: " + type);
		
		chart.setType(Series.Type.valueOf(type.toUpperCase()));
		
		addSettings(chart, type, vo.getParameters());
		
		// creating title and type
//		addSettings(chart, vo.getType(), vo.getTitle());
		
		// building X-AXIS
//		addXAxis(chart, vo);
		
		// building SERIES
		addSeries(chart, vo);
		
		
		
		
		
//		Chart chart = new Chart().setType(Series.Type.SPLINE).setChartTitleText("Lawn Tunnels");
//		Series series = chart.createSeries().setName("Moles per Yard").setPoints(new Number[] { 163, 203, 276, 408, 547, 729, 628 });
//		chart.addSeries(series);
		
		
		
		
		panel.add(chart);
		
		panel.layout();
		
		chart.setSize(width, height, true);
		chart.redraw();
	}
	
	private static void addSettings(Chart chart, String type, HashMap<HighChartParametersConstants, String> parameters) {
		// Tooltip
		ToolTip toolTip = new ToolTip();
		
		// X-Axis
		XAxisLabels xAxisLabels = new XAxisLabels();
		Style xAxisLabelsStyle = new Style(); 

		// Legend
		Legend legend = new Legend();
		
		// Y-Axis
		
		
		
		for(HighChartParametersConstants key : parameters.keySet()) {
			
			String value = parameters.get(key);
			
			System.out.println("KEY: " + key + " | " + value);
			switch (key) {

				// STANDARD SETTINGS
				case ZOOM_TYPE: chart.setZoomType(ZoomType.valueOf(value)); break;
				
				// TOOLTIP
				case SHARED: toolTip.setShared(Boolean.valueOf(value)); break;
				case CROSSHAIRS: toolTip.setCrosshairs(Boolean.valueOf(value)); break;
				
				// X-Axis
				case X_FONTSIZE: xAxisLabelsStyle.setFontSize(value); break;
				case X_ROTATION: xAxisLabels.setRotation(Integer.valueOf(value)); break;
				case X_STAGGERLINES: xAxisLabels.setStaggerLines(Integer.valueOf(value)); break;
				case X_Y_DISTANCE: xAxisLabels.setY(Integer.valueOf(value)); break;
				
				// Y-Axis			
				case Y_END_OF_TICK:	chart.getYAxis().setEndOnTick(Boolean.valueOf(value));break;
				case Y_FONTSIZE: chart.setOption("/yAxis/labels/style/fontSize", value);break;

				// LEGEND
				case LEGEND_LINEHEIGHT: chart.setOption("/legend/lineHeight", Integer.valueOf(value)); break;
				case LEGEND_SYMBOLPADDING: legend.setSymbolPadding(Integer.valueOf(value)); break;
				case LEGEND_SYMBOLWIDTH: legend.setSymbolWidth(Integer.valueOf(value)); break;
				
				
				// PLOT OPTIONS
				case SERIE_MARKER_ENABLED: chart.setOption("/plotOptions/series/marker/enabled", Boolean.valueOf(value)); break;
				case SERIE_MARKER_STATES_HOVER_ENABLED: chart.setOption("/plotOptions/series/marker/states/hover/enabled", Boolean.valueOf(value)); break;

				case CURSOR: 
					String a = "/plotOptions/"+type+"/cursor";
					System.out.println("---> " + a + " | " + value);
//					hc.setOption(new OptionPath("/plotOptions/pie/cursor"), "pointer");
//					chart.setOption(a, value);
					
					break;

				
							
				
				// PLOTOPTIONS
//				chart.setPi

			default:
				break;
			}
		}
		
//		System.out.println("end of tick: " + chart.getYAxis().get);
		
		// Set the various settings
		chart.setToolTip(toolTip);
		
		// X-Axis
		xAxisLabels.setStyle(xAxisLabelsStyle);	
		chart.getXAxis().setLabels(xAxisLabels);
		
		// Legend
		chart.setLegend(legend);
		
	
		
	}
	
//	private static void addSettings(Chart chart, String chartType, String title) {
//		System.out.println("chart type: " + chartType);
//		
//		String type = HighChartConstants.chartType.get(chartType);
//		
//		System.out.println("type: " + type);
//		
//		chart.setType(Series.Type.valueOf(type.toUpperCase()));
//		
//		/** TODO: Dynamic **/
//		chart.setZoomType(ZoomType.X_AND_Y);
//		
//		
//		
////		chart.setColumnPlotOptions(new ColumnPlotOptions().setStacking(Stacking.PERCENT));
//		
////		hc.setOption(new OptionPath("/chart/zoomType"), zoomType);
//		
////		chart.setChartTitleText(title);
//		
//		
//		// Tooltip
//		addTootip(chart);
//		
//	}
//	
//	private static void addTootip(Chart chart) {
//		
//		/** TODO: call tooltip **/
//		chart.setToolTip(new ToolTip().setCrosshairs(true).setShared(true));
//	}
//	
//	
//	private static void addXAxis(Chart chart, HighChartVO vo) {
//		
//		System.out.println("addXAxis");
//		
//		LinkedHashMap<String, String> xValuesHashMap = new LinkedHashMap<String, String>();
//		for(String key : vo.getChartValues().keySet()) {
//			for(String value : vo.getChartValues().get(key).keySet()) {
//				xValuesHashMap.put(value, value);
//			}
//		}
//		
//		// SORT TODO: Do the sort if needed...
////		LinkedHashMap<String, String>  sortedMap = sortByValues(xValuesHashMap);
//		LinkedHashMap<String, String>  sortedMap = xValuesHashMap;
//		
//
//		String[] xValues = new String[sortedMap.size()];
//
//		int i=0;
//		for(String value : sortedMap.keySet()) {
//			xValues[i] = cleanLabel(value);
//			i++;
//		}
//		
//		chart.getXAxis().setCategories(xValues);
//		
//	}
	
	
	private static void addSeries(Chart chart, HighChartVO vo) {
		
		System.out.println("addXAxis");
		
		LinkedHashMap<String, String> xValuesHashMap = new LinkedHashMap<String, String>();
		for(String key : vo.getChartValues().keySet()) {
			for(String value : vo.getChartValues().get(key).keySet()) {
				xValuesHashMap.put(value, value);
			}
		}
		
		// SORT TODO: Do the sort if needed...
//		LinkedHashMap<String, String>  sortedMap = sortByValues(xValuesHashMap);
		LinkedHashMap<String, String>  sortedMap = xValuesHashMap;
		

		String[] xValues = new String[sortedMap.size()];

		int i=0;
		for(String value : sortedMap.keySet()) {
			xValues[i] = cleanLabel(value);
			i++;
		}
		
		chart.getXAxis().setCategories(xValues);
	
		
		

		
		System.out.println("addSeries");
		
		LinkedHashMap<String, Map<String, Double>> seriesValues = vo.getChartValues();
		
		for(String serieValue : seriesValues.keySet()) {
			Series serie = chart.createSeries();
			serie.setName(cleanLabel(serieValue));
			
//			SeriesType seriestype = new SeriesType(cleanLabel(serie)); 
			for(String xValue : xValues) {
	
//				System.out.println("-----------------");
//				System.out.println("serie: " + serieValue);
//				System.out.println("xvalue: " + xValue);
//				System.out.println("series.get(serie).get(xValue): " + seriesValues.get(serieValue).get(xValue));
//				System.out.println("///////");

				serie.addPoint(seriesValues.get(serieValue).get(xValue));

			}
			i++;
			chart.addSeries(serie);

		}
		
	}

	
	public static void createChartExample(ContentPanel panel, Integer width, Integer height) {
		
		System.out.println("createChartExample: " + width + " | " + height);
		
		Chart chart = new Chart().setType(Series.Type.PIE).setChartTitleText("Lawn Tunnels");
		
		Series series = chart.createSeries().setName("Moles per Yard").setPoints(new Number[] { 163, 203, 276, 408, 547, 729, 628 });
		chart.addSeries(series);
		
		panel.add(chart);
		
		panel.layout();
		
		chart.setSize(width, height, true);
		chart.redraw();
	}
	
	
	/** Workaround for the labels **/ 
	private static String cleanLabel(String label) {
		
		String cleanLabel = label.replace("'", "\\'");
		return cleanLabel;
	}
	
}
