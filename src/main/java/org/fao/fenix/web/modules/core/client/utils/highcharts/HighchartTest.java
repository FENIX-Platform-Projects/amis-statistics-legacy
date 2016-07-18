package org.fao.fenix.web.modules.core.client.utils.highcharts;

import org.adapters.highcharts.gxt.widgets.HighChart;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartsTypes;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class HighchartTest {
	
	 public static final String CONTAINER_DIV = "MyUniqueDIV";
	 final static String types[] = new String[] {
	  // "spline",
	 //  "column",
	  // "areaspline",
	 //  "area",
	   "line"};

	public static ContentPanel createChart(final ContentPanel panel, final DWFAOSTATResultVO rvo, final String width, final String height, final String browser, final String version) {

//		System.out.println("TYPE OF OUTPUT: " + rvo.getTypeOfOutput());
		
		HighChart hc = HighChartUtilsOLD.buildChart(rvo.getTitle(), HighChartConstants.chartType.get(rvo.getTypeOfOutput()), browser, version);
//		HighChart hc = HighChartUtils.buildChart(rvo.getTitle(), HighChartConstants.chartType.get(rvo.getTypeOfOutput()));

		  panel.add(buildChart(hc, rvo));
		  
		  panel.layout();

		  
		  // workaroud to resize and repaint the chart
		  hc.setSize(width, height);
		  hc.doResize();
		  
		  return panel;
		 }
	
	
	private static HighChart buildChart(HighChart hc, final DWFAOSTATResultVO rvo) {
		
		Boolean isStacked = false;
		if ( DataViewerBoxContent.valueOf(rvo.getTypeOfOutput()).equals(DataViewerBoxContent.STACKED)) {
			isStacked = true;
		}
		
		Boolean isTimeserie = false;
		if ( DataViewerBoxContent.valueOf(rvo.getTypeOfOutput()).equals(DataViewerBoxContent.TIMESERIE)) {
			isTimeserie = true;
		}
		  
		HighChartUtilsOLD.buildChartXAxis(hc, HighChartConstants.chartType.get(rvo.getTypeOfOutput()), rvo.getMeasurementUnit(), rvo.getChartValues(), rvo.getSerieMeasurementUnits(), rvo.getMeasurementUnits(),"", true, isStacked, isTimeserie, rvo.getPalette(), false, null, true, true);

	
		// TODO: workaround for the pie chart
		HighChartsTypes c = HighChartsTypes.valueOf(HighChartConstants.chartType.get(rvo.getTypeOfOutput()));
		if ( !c.equals(HighChartsTypes.pie)) {
			HighChartUtilsOLD.buildChartYAxis(hc, rvo.getMeasurementUnits());
		}

		return hc;
	}
	
}
