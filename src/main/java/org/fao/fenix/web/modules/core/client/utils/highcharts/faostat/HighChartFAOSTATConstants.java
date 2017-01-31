package org.fao.fenix.web.modules.core.client.utils.highcharts.faostat;

import java.util.HashMap;


import org.adapters.highcharts.codegen.sections.options.OptionPath;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartParametersConstants;
import org.moxieapps.gwt.highcharts.client.Chart.ZoomType;


public class HighChartFAOSTATConstants {
	
	
	public static HashMap<HighChartParametersConstants, String> getDefaultChart() {
		HashMap<HighChartParametersConstants, String> parameters = new HashMap<HighChartParametersConstants, String>();
		
		addStandardParamenters(parameters);
		
		addDefaultParameters(parameters);
		
		return parameters;
	}
	
	public static HashMap<HighChartParametersConstants, String> getTimeSerieChart() {
		HashMap<HighChartParametersConstants, String> parameters = new HashMap<HighChartParametersConstants, String>();
		
		addStandardParamenters(parameters);
		
		addTimeSeriesParameters(parameters);
		
		return parameters;
		
	}
	
	public static HashMap<HighChartParametersConstants, String> getPieChart() {
		HashMap<HighChartParametersConstants, String> parameters = new HashMap<HighChartParametersConstants, String>();
		
		addStandardParamenters(parameters);
		
		addPieParameters(parameters);
		
		return parameters;
		
	}
	
	private static void addDefaultParameters(HashMap<HighChartParametersConstants, String> parameters) {
		
		// X_AXIS
		parameters.put(HighChartParametersConstants.X_STAGGERLINES, "2");
		
		// Y_AXIS
		parameters.put(HighChartParametersConstants.Y_END_OF_TICK, "false");
		
		// LEGEND
		parameters.put(HighChartParametersConstants.LEGEND_LINEHEIGHT, "3");
		parameters.put(HighChartParametersConstants.LEGEND_SYMBOLPADDING, "3");
		parameters.put(HighChartParametersConstants.LEGEND_SYMBOLWIDTH, "10");
		
	}
	
	private static void addTimeSeriesParameters(HashMap<HighChartParametersConstants, String> parameters) {
		
		// X_AXIS
		parameters.put(HighChartParametersConstants.X_STAGGERLINES, "1");
		parameters.put(HighChartParametersConstants.X_ROTATION, "-45");
		parameters.put(HighChartParametersConstants.X_Y_DISTANCE, "20");
		
		// Y_AXIS
		parameters.put(HighChartParametersConstants.Y_END_OF_TICK, "true");
		
		// LEGEND
		parameters.put(HighChartParametersConstants.LEGEND_LINEHEIGHT, "3");
		parameters.put(HighChartParametersConstants.LEGEND_SYMBOLPADDING, "3");
		parameters.put(HighChartParametersConstants.LEGEND_SYMBOLWIDTH, "10");
		

		// PLOT OPTIONS
		parameters.put(HighChartParametersConstants.SERIE_MARKER_ENABLED, "false");
		parameters.put(HighChartParametersConstants.SERIE_MARKER_STATES_HOVER_ENABLED, "true");

		
//		hc.setOption(new OptionPath("/yAxis/min"), minValue);

	}
	
	
	private static void addPieParameters(HashMap<HighChartParametersConstants, String> parameters) {
		
		
		// PLOT OPTIONS
		parameters.put(HighChartParametersConstants.CURSOR, "pointer");
		
//		hc.setOption(new OptionPath("/plotOptions/pie/cursor"), "pointer");
//		hc.setOption(new OptionPath("/plotOptions/pie/allowPointSelect"), true);
//		hc.setOption(new OptionPath("/plotOptions/pie/dataLabels/enabled"), true);
	}
	 
	
	private static void addStandardParamenters(HashMap<HighChartParametersConstants, String> parameters) {
		
		// Settings
		parameters.put(HighChartParametersConstants.ZOOM_TYPE, "X_AND_Y");
		
		// ToolTip
		parameters.put(HighChartParametersConstants.CROSSHAIRS, "true");
		parameters.put(HighChartParametersConstants.SHARED, "true");
		
		// X-Axis
		parameters.put(HighChartParametersConstants.X_FONTSIZE, "10px");
		
		// Y-Axis
		parameters.put(HighChartParametersConstants.Y_FONTSIZE, "9px");
		
		

	}
	
	
	
}
