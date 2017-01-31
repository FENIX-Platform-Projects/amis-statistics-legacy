package org.fao.fenix.web.modules.core.client.utils.highcharts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


import org.adapters.highcharts.codegen.sections.options.OptionPath;
import org.adapters.highcharts.codegen.sections.options.types.RawStringType;
import org.adapters.highcharts.codegen.types.HighChartJS;
import org.adapters.highcharts.codegen.types.SeriesType;
import org.adapters.highcharts.codegen.types.SeriesType.SeriesDataEntry;
import org.adapters.highcharts.codegen.types.SeriesType.SeriesDataEntryOpt;
import org.adapters.highcharts.gxt.widgets.HighChart;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartValueVO;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartsTypes;




public class HighChartUtils {
	
	/** TODO: check every label for the single apex ' and call the clean function cleanLabel(String label) 
	 * 		  there is a bug in the system that doesn't handle the apexes in the labels
	 * **/
	
	public static HighChart buildChart(String chartTitle, String chartType) {
		HighChart hc = new HighChart(null, chartType);
		
		if ( chartTitle != null )
			try {
				
			
				//hc.setOption(new OptionPath("/subtitle/text"),"<b>To Zoom</b>: Click & drag in the plot area <br/><b>To Hide/Show a Series</b>: Click on the corresponding legend item");
				
				hc.setOption(new OptionPath("/title/style/fontSize"),"12px");
				hc.setOption(new OptionPath("/title/text"), "<br/>    ");
				//hc.setOption(new OptionPath("/title/text"), "<b>" + chartTitle + "</b>");
				//hc.setOption(new OptionPath("/title/margin"), 25);
//				hc.setOption(new OptionPath("/title/text"), null);
				
				// zoom
				buildZoomType(hc, "xy");
				

				///buttons
//				hc.setOption(new OptionPath("/exporting/buttons/exportButton/symbolSize"), "9px");
//				hc.setOption(new OptionPath("/exporting/buttons/enabled"), false);
				


				

//				hc.setOption(new OptionPath("/exporting/buttons/exportButton/symbolSize "), "9px");

				// legend
//				hc.setOption(new OptionPath("/legend/style/fontSize"), "9px");
//				hc.setOption(new OptionPath("/legend/layout"), "vertical");
//				hc.setOption(new OptionPath("/legend/verticalAlign"), "middle");
//				hc.setOption(new OptionPath("/legend/align"), "right");
//				hc.setOption(new OptionPath("/legend/align"), "right");
//				hc.setOption(new OptionPath("/legend/itemWidth"), "35");
//				
//				hc.setOption(new OptionPath("/legend/style/fontSize"), "9px");
//
//				hc.setOption(new OptionPath("/legend/itemStyle/fontSize"), "9px");
//				hc.setOption(new OptionPath("/legend/lineHeight"), 10);
//				hc.setOption(new OptionPath("/legend/symbolWidth"), 20);
//				hc.setOption(new OptionPath("/legend/itemStyle/fontSize"), "9px");
//				hc.setOption(new OptionPath("/labels/style/fontSize"), "7px");
				
//				hc.setOption(new OptionPath("/legend/backgroundColor/fontSize"), "Highcharts.theme.legendBackgroundColor || #000000");
//				hc.setOption(new OptionPath("/legend/x"), 100);
//				hc.setOption(new OptionPath("/legend/y"), 50);
//				hc.setOption(new OptionPath("/legend/floating"), true);
//				hc.setOption(new OptionPath("/legend/floating"), true);
				
				
				hc.setOption(new OptionPath("/legend/lineHeight"), 3);
				hc.setOption(new OptionPath("/legend/symbolPadding"), 3);
				hc.setOption(new OptionPath("/legend/symbolWidth"), 10);
				
		
			    
				
				//margin : Number Since 2.1
				//If the plot area sized is calculated automatically and the legend is not floating, the legend margin is the space between the legend and the axis labels or plot area. Defaults to 15.
//				hc.setOption(new OptionPath("/legend/margin"), 4);
//				
//				hc.setOption(new OptionPath("/legend/itemStyle/fontSize"), "5px");
//				hc.setOption(new OptionPath("/legend/itemHoverStyle/fontSize"), "5px");
//				hc.setOption(new OptionPath("/legend/itemHiddenStyle/fontSize"), "5px");
//
//				hc.setOption(new OptionPath("/legend/style/spacing"), "1px");
//				hc.setOption(new OptionPath("/legend/style/spacingLeft"), "1px");
//				hc.setOption(new OptionPath("/legend/style/fontSize"), "5px");
//				
//				hc.setOption(new OptionPath("/chart/spacingLeft"), 27);
				
	


				
				



//				hc.setOption(new OptionPath("/legend/x"), 15);
//				hc.setOption(new OptionPath("/legend/y"), 0);
//				hc.setOption(new OptionPath("/legend/floating"), true);
//				hc.setOption(new OptionPath("/legend/verticalAlign"), "top");
//				hc.setOption(new OptionPath("/legend/align"), "left");
//				hc.setOption(new OptionPath("/legend/vertical"), "vertical");



//				hc.setOption(new OptionPath("/plotOptions/column/cursor"), "pointer");
//				 plotOptions: {
//			         pie: {
//			            allowPointSelect: true,
//			            cursor: 'pointer',
//			            dataLabels: {
//			               enabled: false
//			            },
//			            showInLegend: true
//			         }
//			      }


//				legend: {
//			         layout: 'vertical',
//			         align: 'left',
//			         x: 120,
//			         verticalAlign: 'top',
//			         y: 80,
//			         floating: true,
//			         backgroundColor: Highcharts.theme.legendBackgroundColor || '#FFFFFF'
//			      },

				
				
				

	
				
//				hc.setOption(new OptionPath("/exporting/enabled"), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return hc;
	}
	
	/** TODO: change it **/
	public static void buildChartXAxis(HighChart hc, String chartType, String measurementUnit, LinkedHashMap<String, Map<String, Double>> series, String xTitle, Boolean showXvalues, Boolean isStacked, Boolean isTimeserie, String source) {
//		System.out.println("SERIES: " + series);
		
		HighChartsTypes c = HighChartsTypes.valueOf(chartType);

//		System.out.println("HighChartsTypes: " + c.toString());
		
		
		switch (c) {
		case pie: buildChartXAxisPie(hc, series, xTitle, showXvalues);
				  buildPlotOptionsPie(hc);
				  buildToolTipOptionsPie(hc, measurementUnit);
		break;
		
//		case column: 
//				 buildStackedChart(hc, measurementUnit, series, xTitle, showXvalues);
//		break;

		default: buildChartXAxisDefault(hc, measurementUnit, series, xTitle, showXvalues, isStacked, isTimeserie, source);	break;
		}

	}
	
	public static void buildChartXAxisPie(HighChart hc, LinkedHashMap<String, Map<String, Double>> series, String xTitle, Boolean showXvalues) {
		
		System.out.println("PIE");
		
//		System.out.println("series: "+ series);
	    
		// getting the values
		SeriesType seriestype = new SeriesType(""); 
		for(String serie : series.keySet()) {
			for(String xValue : series.get(serie).keySet()) {
				 seriestype.addEntry(new SeriesType.SeriesDataEntry(cleanLabel(serie), series.get(serie).get(xValue)));
			}
		}
		hc.addSeries(seriestype);
	}
	
	public static void buildChartXAxisDefault(HighChart hc, String measurementUnit, LinkedHashMap<String, Map<String, Double>> series, String xTitle, Boolean showXvalues, Boolean isStacked, Boolean isTimeserie, String source) {
				
		LinkedHashMap<String, String> xValuesHashMap = new LinkedHashMap<String, String>();
		for(String key : series.keySet()) {
			for(String value : series.get(key).keySet()) {
				xValuesHashMap.put(value, value);
			}
		}
		
		// SORT TODO: Do the sort if needed...
//		LinkedHashMap<String, String>  sortedMap = sortByValues(xValuesHashMap);
		LinkedHashMap<String, String>  sortedMap = xValuesHashMap;
		

		List<String> xValues = new ArrayList<String>();
		for(String value : sortedMap.keySet()) {
			//xValues.add(value);
			xValues.add(cleanLabel(value));
		}

		

		
		System.out.println("xValues: " + xValues);
	  	try {
			hc.setOption(new OptionPath("/xAxis/categories"), xValues);
			hc.setOption(new OptionPath("/xAxis/labels/staggerLines"), 2);
//			hc.setOption(new OptionPath("/xAxis/labels/rotation"), -45);
			hc.setOption(new OptionPath("/xAxis/labels/style/fontSize"), "10px");
//			hc.setOption(new OptionPath("/xAxis/labels/style/padding"), 5);
			
			/** TODO: workaround for "1" serie...boolean **/
			if ( xValues.size() == 1) {
				hc.setOption(new OptionPath("/xAxis/labels/enabled"), false);
				buildToolSingleColumnOptions(hc, measurementUnit);
			}

//			hc.setOption(new OptionPath("/xAxis/labels/step"), 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
		
		System.out.println("GETTING VALUES...");
		
		Double maxValue = new Double(0);
		
		Double minValue = new Double(0);
		
		// getting the values
		for(String serie : series.keySet()) {
			SeriesType seriestype = new SeriesType(cleanLabel(serie)); 
			for(String xValue : sortedMap.keySet()) {
//				SeriesDataEntryOpt opt = new SeriesDataEntryOpt("yAxis", 1);
				
				SeriesDataEntry s = new SeriesType.SeriesDataEntry(series.get(serie).get(xValue));
				
				if ( series.get(serie).get(xValue) != null)
					if ( minValue > series.get(serie).get(xValue) ) {
						minValue = series.get(serie).get(xValue);
					}
					else if ( minValue == 0 ) {
						minValue = series.get(serie).get(xValue);
					}
//				if ( maxValue < series.get(serie).get(xValue)){
//					maxValue = series.get(serie).get(xValue);
//				}
		
//				SeriesDataEntry s = new SeriesType.SeriesDataEntry(series.get(serie).get(xValue));
		
			
				seriestype.addEntry(s);
		
			}
			hc.addSeries(seriestype);

		}
		
//		System.out.println("MAX VALUE: " + maxValue);
		System.out.println("MIN VALUE: " + minValue);
		

		
//		setAnimationDuration(hc, 1500);
		
	
		maxValueOnYAxis(hc);

		// tooltips
		buildToolTipOptions(hc, measurementUnit);
		
		if ( isTimeserie) {
			try {
				hc.setOption(new OptionPath("/xAxis/labels/staggerLines"), 1);
				hc.setOption(new OptionPath("/xAxis/labels/rotation"), -45);
				hc.setOption(new OptionPath("/xAxis/labels/align"), "right");
				if(source!=null)
					hc.setOption(new OptionPath("/xAxis/title/text"), "Source: "+source);
				hc.setOption(new OptionPath("/xAxis/title/align"), "high");
				hc.setOption(new OptionPath("/xAxis/title/margin"), 10);
				
				/** TODO: this is has to be calculated dinamically, not always works...depends
				 *  on the dimension of the chart
				 */
				hc.setOption(new OptionPath("/xAxis/labels/y"), 20);
				
				hc.setOption(new OptionPath("/plotOptions/series/marker/enabled"), false);
				
				hc.setOption(new OptionPath("/plotOptions/series/marker/states/hover/enabled"), true);

				
				hc.setOption(new OptionPath("/yAxis/min"), minValue);
				
				hc.setOption(new OptionPath("/yAxis/endOnTick"), true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// stacked 
		if ( isStacked ) {
			buildStack(hc);
		}
		
		
		// AREA
		
		
		
		
		
		// TODO: EXAMPLE FOR DOUBLE AXIS
//		for(String serie : series.keySet()) {
//			SeriesType seriestype = new SeriesType(cleanLabel(serie)); 
////			SeriesDataEntryOpt opt = new SeriesDataEntryOpt("yAxis", 1);
//			try {
//				seriestype.setSubOption("yAxis", 1);
//			} catch (Exception e) {
//			}
//			
//			for(String xValue : xValues) {
//				SeriesDataEntry s = new SeriesType.SeriesDataEntry(series.get(serie).get(xValue), new SeriesDataEntryOpt("color", "#3c3c3c"));
//			
//				seriestype.addEntry(s);
//		
//			}
//			hc.addSeries(seriestype);
//
//		}
		
		 
		 

		
				 
	}
	
	public static void buildChartXAxis(HighChart hc, List<String> xValues, List<HighChartValueVO> series, String xTitle, Boolean showXvalues) {
	
		
		System.out.println("buildChartXAxis");
		
		 SeriesType seriestype = null;
		 for(HighChartValueVO serie : series) {

			 seriestype = new SeriesType(cleanLabel(serie.getName())); 
			 for(Double value : serie.getValues()) {
				 System.out.println("NAME: " + serie.getName() +  " | " + value);
				 seriestype.addEntry(new SeriesType.SeriesDataEntry(value));
			 }
			 hc.addSeries(seriestype);

	
		 }
	}
	
	public static void buildChartYAxis(HighChart hc, String yTitle) {
		   try {	
			   
			   // TODO: EXAMPLE FOR DOUBLE AXIS
//			   List<Map<String, Object>> yAxises = new ArrayList<Map<String,Object>>();
//				 HashMap<String, Object> yAxis1 = new HashMap<String, Object>();
//				 HashMap<String, Object> yAxisTitle1 = new HashMap<String, Object>();
//				 yAxisTitle1.put("text", "asdoij");
//				 yAxis1.put("title", yAxisTitle1);
//				 
//				 HashMap<String, Object> yAxis2 = new HashMap<String, Object>();
//				 HashMap<String, Object> yAxisTitle2 = new HashMap<String, Object>();
//				 yAxisTitle2.put("text", "asdoij2");
//				 yAxis2.put("title", yAxisTitle2);
//				 yAxis2.put("opposite", true);
//
//	
//				 yAxises.add(yAxis1);
//				 yAxises.add(yAxis2);
//				 hc.setOption(new OptionPath("/yAxis"), yAxises);
		
			   
			   
			hc.setOption(new OptionPath("/yAxis/labels/style/fontSize"), "9px");
			hc.setOption(new OptionPath("/yAxis/title/text"), cleanLabel(yTitle));
		

			hc.setOption(new OptionPath("/yAxis/title/style/fontSize"), "11px");
//			hc.setOption(new OptionPath("/yAxis/title/margin"), "20");
//			hc.setOption(new OptionPath("/yAxis/title/enabled"), "null");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void buildPlotOptionsPie(HighChart hc) {
		try {
			hc.setOption(new OptionPath("/plotOptions/pie/cursor"), "pointer");
			hc.setOption(new OptionPath("/plotOptions/pie/allowPointSelect"), true);
			hc.setOption(new OptionPath("/plotOptions/pie/dataLabels/enabled"), true);
//			hc.setOption(new OptionPath("/plotOptions/pie/dataLabels/enabled"), true);
//			hc.setOption(new OptionPath("/plotOptions/pie/dataLabels/color"), "#FFFFFF");

//			hc.setOption(new OptionPath("/plotOptions/pie/showInLegend"), true);


		} catch (Exception e) {
		}
	}
	
	private static void buildToolSingleColumnOptions(HighChart hc, String measurementUnit) {
		try {
			
		    hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
		    hc.setOption(new OptionPath("/tooltip/shared"), true);

//			hc.setOption(new OptionPath("/tooltip/formatter"), 
//						  new RawStringType("function() { " +
//			      					" 			return '<b>'+ this.series.name +'</b>: ' + this.y +' " + cleanLabel(measurementUnit) + "' " + 
//					      					" }"));
		} catch (Exception e) {
		}
	}
	
	private static void buildToolTipOptionsPie(HighChart hc, String measurementUnit) {
		try {


			
//			System.out.println("MU: " + mu);
//           return ''+
//           this.x +': '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+
//           Highcharts.numberFormat(this.y, 0, ',') +' millions)';
			hc.setOption(new OptionPath("/tooltip/formatter"), 	
					      new RawStringType("function() { " +
					      					" 			return '<b>'+ this.point.name +'</b>: ' + this.y +' " + cleanLabel(measurementUnit) + "<br> ' + this.percentage +' %'" +  
					      					" }"));
		} catch (Exception e) {
		}
	}
	
	private static void buildStack(HighChart hc) { 
		
	    try {
		    hc.setOption(new OptionPath("/plotOptions/column/stacking"), "normal");
		} catch (Exception e) {
			e.printStackTrace();
		}

//		hc.setOption(new OptionPath("/plotOptions/column/stacking", "normal"));

//			 plotOptions: {
//	        column: {
//	            stacking: 'normal'
//	        }
//	    },
		
	} 
	
	private static void buildToolTipOptions(HighChart hc, String measurementUnit) {
		try {
		    hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
		    hc.setOption(new OptionPath("/tooltip/shared"), true);

		} catch (Exception e) {
		}
	}
	
	private static void buildZoomType(HighChart hc, String zoomType) {
		try {
			
			//zoomtype
			hc.setOption(new OptionPath("/chart/zoomType"), zoomType);
//			hc.setOption(new OptionPath("/chart/zoomType"), "xy");
//			hc.setOption(new OptionPath("/chart/zoomType"), "x");
//			hc.setOption(new OptionPath("/chart/zoomType"), "y");

		} catch (Exception e) {
		}
	}
	
	public static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	private static void maxValueOnYAxis(HighChart hc) {
	
		try {
			hc.setOption(new OptionPath("/yAxis/endOnTick"), false);

//			hc.setOption(new OptionPath("/xAxis/minPadding"), 0.25);
//			hc.setOption(new OptionPath("/xAxis/startOnTick"), true);

//			hc.setOption(new OptionPath("/yAxis/max"), maxValue);
		} catch (Exception e) {
			System.out.println("ERROR: maxValueOnYAxis");
			e.printStackTrace();
		}

	} 
	
	private static void setAnimationDuration(HighChart hc, Integer value) {
		
		try {
			hc.setOption(new OptionPath("/plotOptions/series/animation/duration"), 1500);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	/** Workaround for the labels **/ 
	private static String cleanLabel(String label) {
		
		
		String cleanLabel = label.replace("'", "\\'");
		
		System.out.println("cleanLabel: "+cleanLabel);
		return cleanLabel;
	}
	
	
}