package org.fao.fenix.web.modules.amis.client.view.utils;

import org.adapters.highcharts.gxt.widgets.HighChart;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.core.client.utils.highcharts.HighChartConstants;
import org.fao.fenix.web.modules.core.client.utils.highcharts.HighChartUtilsOLD;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;


import com.extjs.gxt.ui.client.widget.ContentPanel;

public class AMISHighchart {

	 public static final String CONTAINER_DIV = "MyUniqueDIV";
	 final static String types[] = new String[] {
	  // "spline",
	 //  "column",
	  // "areaspline",
	 //  "area",
	   "line"};
	 
	 

	public static ContentPanel createChart(final ContentPanel panel, final AMISResultVO rvo, final String width, final String height) {

		System.out.println("createMultiScaleChart: START ");
		System.out.println("createMultiScaleChart:  "+ rvo.getMeasurementUnits());
		     HighChart hc = HighChartUtilsOLD.buildChart(rvo.getTitle(), HighChartConstants.chartType.get(rvo.getTypeOfOutput()), null, null);
			  panel.add(buildChart(hc, rvo));
			  panel.layout();

			  
			  // workaroud to resize and repaint the chart
			  hc.setSize(width, height);
			  hc.doResize();
			  
			  return panel;
		}

	/*
	 * Handles multiscale
	 */
	private static HighChart buildChart(final HighChart hc, final AMISResultVO rvo) {
		
		//set the x-axis label aligned
		rvo.setIsXAxisLabelAligned(true);
		
		Boolean isStacked = false;
		if ( AMISConstants.valueOf(rvo.getTypeOfOutput()).equals(AMISConstants.STACKED)) {
			isStacked = true;
		}
		
		Boolean isTimeserie = false;
		if ( AMISConstants.valueOf(rvo.getTypeOfOutput()).equals(AMISConstants.TIMESERIES)) {
			isTimeserie = true;
		}
		
//		Boolean isTimeserie = false;
//		if ( DataViewerBoxContent.valueOf(rvo.getTypeOfOutput()).equals(DataViewerBoxContent.TIMESERIE)) {
//			isTimeserie = true;
//		}
		
		HighChartUtilsOLD.buildChartXAxis(hc, HighChartConstants.chartType.get(rvo.getTypeOfOutput()), rvo.getMeasurementUnit(), rvo.getChartValues(), rvo.getSerieMeasurementUnits(), rvo.getMeasurementUnits(),"", true, isStacked, isTimeserie, rvo.getPalette(), rvo.getIsXAxisLabelAligned(), rvo.getSource(), rvo.isChartTooltipShared(), false);
		
		HighChartUtilsOLD.buildChartYAxis(hc, rvo.getMeasurementUnits());
		
		return hc;
	}

	
}
