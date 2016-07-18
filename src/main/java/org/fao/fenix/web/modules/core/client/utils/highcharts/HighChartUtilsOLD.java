package org.fao.fenix.web.modules.core.client.utils.highcharts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


import org.adapters.highcharts.codegen.sections.options.OptionPath;
import org.adapters.highcharts.codegen.sections.options.types.RawStringType;
import org.adapters.highcharts.codegen.types.SeriesType;
import org.adapters.highcharts.codegen.types.SeriesType.SeriesDataEntry;
import org.adapters.highcharts.gxt.widgets.HighChart;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartValueVO;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartsTypes;




public class HighChartUtilsOLD {
	
	/** TODO: check every label for the single apex ' and call the clean function cleanLabel(String label) 
	 * 		  there is a bug in the system that doesn't handle the apexes in the labels
	 * **/
	
	public static HighChart buildChart(String chartTitle, String chartType, String browser, String version) {
		HighChart hc = new HighChart(null, chartType);
		
		if ( chartTitle != null ) 
			try {
				//Set title to empty string to avoid default "Chart title" displaying in later versions of HighCharts
				hc.setOption(new OptionPath("/title/text"), " ");
				
				//Hide the HighCharts credit in later versions of HighCharts
				hc.setOption(new OptionPath("/credits/enabled"), false);
				
				// zoom
				buildZoomType(hc, "xy");

				hc.setOption(new OptionPath("/legend/lineHeight"), 3);
				hc.setOption(new OptionPath("/legend/symbolPadding"), 3);
				hc.setOption(new OptionPath("/legend/symbolWidth"), 10);
				
				hc.setOption(new OptionPath("/chart/spacingTop"), 15);
				hc.setOption(new OptionPath("/chart/spacingBottom"), 8);

				
				if ( browser != null ) {
//					System.out.println("----------------->BROWSER: " + browser);
					if ( browser.toLowerCase().contains("msie")) {
						if ( !version.contains("9.0"))
							hc.setOption(new OptionPath("/chart/animation"), false);
					}
					else if (browser.toLowerCase().contains("microsoft internet explorer")) {
						if ( !version.contains("9.0"))
							hc.setOption(new OptionPath("/chart/animation"), false);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return hc;
	}
	
	/** TODO: change it **/
	public static void buildChartXAxis(HighChart hc, String chartType, String measurementUnit, LinkedHashMap<String, Map<String, Double>> series, LinkedHashMap<String, String> serieMeasurementUnits, LinkedHashMap<String, String> measurementUnits, String xTitle, Boolean showXvalues, Boolean isStacked, Boolean isTimeserie, String palette, Boolean isXAxisLabelAligned, String source, Boolean isChartTooltipShared, Boolean isFAOSTAt) {

		
		HighChartsTypes c = HighChartsTypes.valueOf(chartType);
		
		System.out.println(measurementUnit);
		System.out.println(serieMeasurementUnits);
		System.out.println(series);

		switch (c) {
		case pie: buildChartXAxisPie(hc, series, xTitle, showXvalues);
				  buildPlotOptionsPie(hc);
				  // TODO: workaround
				  String mu = "";
				  for(String key : serieMeasurementUnits.keySet()) {
					  mu = serieMeasurementUnits.get(key);
					  break;
				  }
				  buildToolTipOptionsPie(hc, mu);
		break;
		
		default: buildChartXAxisDefault(hc, measurementUnit, series, serieMeasurementUnits, measurementUnits, xTitle, showXvalues, isStacked, isTimeserie, palette, isXAxisLabelAligned, source, isChartTooltipShared, isFAOSTAt);	break;
		}

	}
	
	public static void buildChartXAxisPie(HighChart hc, LinkedHashMap<String, Map<String, Double>> series, String xTitle, Boolean showXvalues) {
	    
		// getting the values
		SeriesType seriestype = new SeriesType(""); 
		for(String serie : series.keySet()) {
			for(String xValue : series.get(serie).keySet()) {
				try {
				 seriestype.addEntry(new SeriesType.SeriesDataEntry(cleanLabel(xValue), series.get(serie).get(xValue)));
				}
				catch (Exception e) {
					System.out.println("errore: " + e.getStackTrace());
				}
			}
		}
		hc.addSeries(seriestype);
	}
	

	public static void buildChartXAxisDefault(HighChart hc, String measurementUnit, LinkedHashMap<String, Map<String, Double>> series, LinkedHashMap<String, String> serieMeasurementUnits, LinkedHashMap<String, String> measurementUnits,String xTitle, Boolean showXvalues, Boolean isStacked, Boolean isTimeserie, String palette, Boolean isXAxisLabelAligned, String source, Boolean isChartTooltipShared, Boolean isFAOSTAT) {
				
		LinkedHashMap<String, String> xValuesHashMap = new LinkedHashMap<String, String>();
		for(String key : series.keySet()) {
			for(String value : series.get(key).keySet()) {
				xValuesHashMap.put(value, value);
			}
		}
		
		// SORT TODO: Do the sort if needed...
		LinkedHashMap<String, String>  sortedMap = xValuesHashMap;
		
		if ( isFAOSTAT && isTimeserie ) {
			
			// TODO: should take the years but it's a quick workaround...
			// the xAxis should be passed directly by the server
			sortedMap = sortByValuesTimeserie(xValuesHashMap);
			
		}
		
		List<String> xValues = new ArrayList<String>();
		for(String value : sortedMap.keySet()) {
			xValues.add(cleanLabel(value));
		}
		
	

		

		
//		System.out.println("xValues: " + xValues);
	  	try {
			hc.setOption(new OptionPath("/xAxis/categories"), xValues);
			hc.setOption(new OptionPath("/xAxis/labels/staggerLines"), 2);
			//hc.setOption(new OptionPath("/xAxis/labels/rotation"), -45);
			hc.setOption(new OptionPath("/xAxis/labels/style/fontSize"), "10px");
//			hc.setOption(new OptionPath("/xAxis/labels/style/padding"), 5);
						
			/** TODO: workaround for "1" serie...boolean **/
			/** TODO: change it...**/
			if ( xValues.size() == 1) {
				hc.setOption(new OptionPath("/xAxis/labels/enabled"), false);
				buildToolSingleColumnOptions(hc, measurementUnit);
			}

//			hc.setOption(new OptionPath("/xAxis/labels/step"), 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
		
//		System.out.println("GETTING VALUES...");
		
		Double maxValue = new Double(0);
		
		Double minValue = new Double(0);
		

		// getting the values
		int i=0;
		for(String serie : series.keySet()) {
			
//			System.out.println("SERIE: " + serie);
		
			SeriesType seriestype = new SeriesType(cleanLabel(serie)); 

			for(String xValue : sortedMap.keySet()) {
	
//				System.out.println("-----------------");
//				System.out.println("serie: " + serie);
//				System.out.println("xvalue: " + xValue);
//				System.out.println("series.get(serie).get(xValue): " + series.get(serie).get(xValue));
//				System.out.println("///////");
		
				SeriesDataEntry s = new SeriesType.SeriesDataEntry(series.get(serie).get(xValue));
				
//				System.out.println("SERIE v: " + series.get(serie).get(xValue));
				
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

				
				try {
					seriestype.setSubOption("name", cleanLabel(serie));

//					System.out.println("serieMeasurementUnits all " + serieMeasurementUnits);
//					System.out.println("serieMeasurementUnits: " + serieMeasurementUnits.get(serie));
//					System.out.println("mu: " + measurementUnits.get(serieMeasurementUnits.get(serie)));
//					seriestype.setSubOption("yAxis", Integer.valueOf(measurementUnits.get(serieMeasurementUnits.get(serie))));
					try {
						seriestype.setSubOption("yAxis", Integer.valueOf(measurementUnits.get(serieMeasurementUnits.get(serie))));
					}catch (Exception e) {
							// TODO: handle exception
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				seriestype.addEntry(s);
				
			}
			try {
				/** TODO: specify palette **/	
//				System.out.println("PALETTE: " + palette);
				
				
				if ( palette != null ) {
					seriestype.setSubOption("color", HighChartPalettes.palettes.get(palette.toUpperCase()).get(i));
				} else {
					seriestype.setSubOption("color", HighChartPalettes.secondPalette.get(i));
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			i++;
			hc.addSeries(seriestype);

		}
		
//		System.out.println("MAX VALUE: " + maxValue);
//		System.out.println("MIN VALUE: " + minValue);
		

		
//		setAnimationDuration(hc, 1500);
		
	
		maxValueOnYAxis(hc);

		// tooltips 
		if(isChartTooltipShared)
		   buildToolTipOptions(hc, measurementUnit);
		else
			buildNotSharedToolTipFormatter(hc, serieMeasurementUnits);
		
		//New tooltip formatter includes measurement unit alongside each point value
//		if ( serieMeasurementUnits != null && !serieMeasurementUnits.isEmpty() )
////			buildSharedToolTipFormatter(hc, serieMeasurementUnits);
//			buildNotSharedToolTipFormatter(hc, serieMeasurementUnits);
//		else
//			buildToolTipOptions(hc, measurementUnit);
		
		if ( isTimeserie) {
			try {
				hc.setOption(new OptionPath("/xAxis/labels/staggerLines"), 1);
				hc.setOption(new OptionPath("/xAxis/labels/rotation"), -45);			
				if(isXAxisLabelAligned)
					hc.setOption(new OptionPath("/xAxis/labels/align"), "right");
				if(source!=null) {
					hc.setOption(new OptionPath("/xAxis/title/text"), "Source: "+source);
					hc.setOption(new OptionPath("/xAxis/title/align"), "high");
					hc.setOption(new OptionPath("/xAxis/title/margin"), 10);
				}
				/** TODO: this is has to be calculated dinamically, not always works...depends
				 *  on the dimension of the chart
				 */
				hc.setOption(new OptionPath("/xAxis/labels/y"), 20);
				
				hc.setOption(new OptionPath("/plotOptions/series/marker/enabled"), false);
				
				hc.setOption(new OptionPath("/plotOptions/series/marker/states/hover/enabled"), true);

				
				hc.setOption(new OptionPath("/yAxis/min"), minValue);
				
				hc.setOption(new OptionPath("/yAxis/endOnTick"), true);
				
				// if one year
				if ( xValues.size() == 1) {
					// switch to bar chart
//					System.out.println("HERE COLUMN!!!!1");
					hc.setOption(new OptionPath("/chart/type"), "column");
//					hc.setOption(new OptionPath("/xAxis/labels/enabled"), false);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// stacked 
		if ( isStacked ) {
			buildStack(hc);
		}
		
		
//		System.out.println("hc.setOption(new OptionPath(/yAxis/labels/formatter");
		try {
			
			hc.setOption(new OptionPath("/yAxis/labels/style/fontSize"), "16px");
//			hc.setOption(new OptionPath("/yAxis/labels/formatter"), 	
//				      new RawStringType("function() { " +
//				      					" 	return this.y" +  
//				      					" }"));
			
//			hc.setOption(new OptionPath("/yAxis/labels"), 	
//		      new RawStringType("formatter: function() { " +
//		      					" 	return this.value" +  
//		      					" }"));
			
			
//			System.out.println("--> " + hc.getOption(new OptionPath("/yAxis/labels")).toString());
			
//			hc.setOption(new OptionPath("/yAxis/labels/formatter"), "function() { " +
//				      					" 			return this.value" +  
//				      				" }");
			
//			hc.setOption(new OptionPath("/yAxis/labels/formatter"), 
//						     new RawStringType("function() { " +
//						      					" 			return this.value" +  
//						      				" }"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				 
	}
	
	private static void buildToolTipOptions(HighChart hc, String measurementUnit) {
		try {
			
//			System.out.println("----------------------->");
		    hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
		    hc.setOption(new OptionPath("/tooltip/shared"), true);
		    
//			hc.setOption(new OptionPath("/tooltip/formatter"), 	
//				      new RawStringType("function() { " +
//								" 			return '<b>'+ this.y +'</b>:'" +  
//				      					" }"));

//			hc.setOption(new OptionPath("/tooltip/formatter"), 	
//		      new RawStringType("function() { " +
//    					" 			   var s = '<b>asdasdasd</b>'; " +
//						            
//						           " $.each(this.points, function(i, point) {"+
////						           "     s += '<br/>: "+
////						           "         m';"+
//						           " });"+
//						            
//						          "  return s;" +  
//		      					" }"));

		} catch (Exception e) {
		}
	}
	
	/* buildNotSharedToolTipFormatter:
	 * OptionPath("/tooltip/shared") = false
	 */
	private static void buildNotSharedToolTipFormatter(HighChart hc, LinkedHashMap<String, String> seriesUnits) {
		try {

//			System.out.println("----------------------->");
			hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
			hc.setOption(new OptionPath("/tooltip/shared"), false);

			StringBuffer function = new StringBuffer(); // shared = false
			function.append("function() { var unit = { ");	

			int i = 0;
			for(String serie: seriesUnits.keySet()){
				function.append(" '"+serie+"': '"+seriesUnits.get(serie)+"' ");		

				if (i < seriesUnits.size() - 1)
					function.append(", ");
				i++;
			}

			function.append(" }[this.series.name]; ");	
			function.append(" return '<span style=\"color:'+this.series.color+';font-weight:bold;\">'+this.series.name +'</span><br/>' + ");	
			function.append("   '<b>'+this.x +'</b>: '+ this.y +' '+ unit; }");	

			/** Function Output Example:
			 *    formatter: function() {
		            var unit = {
		               'Rainfall': 'mm',
		               'Temperature': '°C',
		               'Sea-Level Pressure': 'mb'
		            }[this.series.name];

		            return ''+
		               this.x +': '+ this.y +' '+ unit;
		         }
			 */

			//System.out.println("function.toString() ######## "+function.toString());

			hc.setOption(new OptionPath("/tooltip/formatter"), 	
					new RawStringType(function.toString()));

		} catch (Exception e) {
		}
	}

/* buildSharedToolTipFormatter:
	 * OptionPath("/tooltip/shared") = true
	 */
	private static void buildSharedToolTipFormatter(HighChart hc, LinkedHashMap<String, String> seriesUnits) {
		try {

//			System.out.println("----------------------->");
			hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
			hc.setOption(new OptionPath("/tooltip/shared"), true);

			StringBuffer function = new StringBuffer(); // shared = true
			function.append("function() { var s = '<b>'+ this.x  +'</b><br/>'; ");	
			int j = 0;
				for(String serie: seriesUnits.keySet()){
					
					
					function.append("if ( this.points["+j+"].series.visible && this.points["+j+"].series.tooltipPoints.length )  { ");
						function.append(" s += '<span style=\"color:'+this.points["+j+"].series.color+';font-weight:bold;\">'+this.points["+j+"].series.name+'</span>: '+ this.points["+j+"].y +' '+'"+seriesUnits.get(serie)+"'");	
	
						if (j < seriesUnits.size() - 1)
							function.append(" + '<br/>' ");
						j++;
						function.append("; ");
					
					function.append("} ");
					
				}
				
				// refresh the tooltip
				// refresh the tooltip if necessary
				function.append("if (this.points.length && (this.points[0].plotX !== hoverX)) {");
				function.append("this.tooltip.refresh(points);");
				function.append("this.hoverX = this.points[0].plotX;");
				function.append("}");
				
				
				function.append("return s; }");
				
				
//				function.append("if (this.points["+j+"].series.visible && this.points["+j+"].series.tooltipPoints.length) {");
//					function.append(" s += '<span style=\"color:'+this.points["+j+"].series.color+';font-weight:bold;\">'+this.points["+j+"].series.name+'</span>: '+ this.points["+j+"].y +' '+'"+seriesUnits.get(serie)+"'");	
//
//					if (j < seriesUnits.size() - 1)
//						function.append(" + '<br/>' + ");
//				function.append("}");
//				j++;
//			}
//
//			function.append(";  return s; }");
			


//			System.out.println("function.toString() ######## "+function.toString());
			hc.setOption(new OptionPath("/tooltip/formatter"), 	
					new RawStringType(function.toString()));

		} catch (Exception e) {
			System.out.println("e:" + e.getStackTrace());
		}
	}
	
	/* buildSharedToolTipFormatter:
	 * OptionPath("/tooltip/shared") = true
	 */
	private static void buildSharedToolTipFormatterOriginal(HighChart hc, LinkedHashMap<String, String> seriesUnits) {
		try {

//			System.out.println("----------------------->");
			hc.setOption(new OptionPath("/tooltip/crosshairs"), true);
			hc.setOption(new OptionPath("/tooltip/shared"), true);

			StringBuffer function = new StringBuffer(); // shared = true
			function.append("function() { var s = '<b>'+ this.x  +'</b><br/>' +");	
			int j = 0;
			for(String serie: seriesUnits.keySet()){
				function.append(" '<span style=\"color:'+this.points["+j+"].series.color+';font-weight:bold;\">'+this.points["+j+"].series.name+'</span>: '+ this.points["+j+"].y +' '+'"+seriesUnits.get(serie)+"'");	

				if (j < seriesUnits.size() - 1)
					function.append(" + '<br/>' + ");
				j++;
			}

			function.append(";  return s; }");

			//System.out.println("function.toString() ######## "+function.toString());
			hc.setOption(new OptionPath("/tooltip/formatter"), 	
					new RawStringType(function.toString()));

		} catch (Exception e) {
		}
	}
	
		
	public static void buildChartXAxis(HighChart hc, List<String> xValues, List<HighChartValueVO> series, String xTitle, Boolean showXvalues) {
	
		
//		System.out.println("buildChartXAxis");
		
		 SeriesType seriestype = null;
		 for(HighChartValueVO serie : series) {

			 seriestype = new SeriesType(cleanLabel(serie.getName())); 
			 for(Double value : serie.getValues()) {
//				 System.out.println("NAME: " + serie.getName() +  " | " + value);
				 seriestype.addEntry(new SeriesType.SeriesDataEntry(value));
			 }
			 hc.addSeries(seriestype);

	
		 }
	}
	
	public static void buildChartYAxis(HighChart hc, String yTitle) {
		   try {	
			hc.setOption(new OptionPath("/yAxis/labels/style/fontSize"), "9px");
			hc.setOption(new OptionPath("/yAxis/title/text"), cleanLabel(yTitle));
			hc.setOption(new OptionPath("/yAxis/title/style/fontSize"), "11px");

//			hc.setOption(new OptionPath("/yAxis/title/margin"), "20");
//			hc.setOption(new OptionPath("/yAxis/title/enabled"), "null");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buildChartYAxis(HighChart hc, LinkedHashMap<String, String> measurementUnits) {
		   try {

//			   System.out.println("------------> MUS: " + measurementUnits);
			   
//			   for(String key : mus.keySet()) {
//				   
//			   }
			      
			   // TODO: EXAMPLE FOR DOUBLE AXIS
			   List<Map<String, Object>> yAxises = new ArrayList<Map<String,Object>>();
			      
			   int i=0;
			   for(String key : measurementUnits.keySet()) {	
				   HashMap<String, Object> yAxis = new HashMap<String, Object>();
				   HashMap<String, Object> yAxisTitle = new HashMap<String, Object>(); 
				   HashMap<String, HashMap<String, Object>> yAxisLabels = new HashMap<String, HashMap<String, Object>>(); 
				   HashMap<String, Object> yAxisLabelsColor = new HashMap<String, Object>(); 
				  
				   if(hc.getSeriesList().size()==measurementUnits.size())
					   yAxisLabelsColor.put("color", hc.getSeriesList().get(i).getSubOption("color"));		
				   else
					   yAxisLabelsColor.put("color", "#0C5C86");	 
					 			
				   //Set Measurement Unit title and colour
				   yAxisTitle.put("text", key);
				   yAxisTitle.put("style", yAxisLabelsColor);
				   yAxis.put("title", yAxisTitle);
				  
				   //Set YAxis labels colour
				   yAxisLabels.put("style", yAxisLabelsColor);
				   yAxis.put("labels", yAxisLabels);
					    
				   if ( i > 0 ) {
					   yAxis.put("opposite", true);
				   }
				   
		     	   /** TODO: this hadcodes the raw value without formatting it ( i.e. 1k = 1000 on yAxis)
				   RawStringType s = new RawStringType("{formatter: function() { return this.value;}}");
				   yAxis.put("labels", s);
					**/
 
				   
				   yAxises.add(yAxis);
		  
				   i++;
			   }
			   hc.setOption(new OptionPath("/yAxis"), yAxises);
			   
			   
			   
			   
//				hc.setOption(new OptionPath("/yAxislabels/style/fontSize"), "36px");
//				hc.setOption(new OptionPath("/yAxis/labels/formatter"), 	
//					      new RawStringType("function() { " +
//					      					" 	return this.y" +  
//					      					" }"));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private static void buildPlotOptionsPie(HighChart hc) {
		try {
			hc.setOption(new OptionPath("/plotOptions/pie/cursor"), "pointer");
			hc.setOption(new OptionPath("/plotOptions/pie/allowPointSelect"), true);

		   /** Percentage **/
		   RawStringType s = new RawStringType("{pie: { dataLabels: { formatter: function() " +
				"{ return '<b>'+ this.point.name +'</b><br> ' + $wnd.Highcharts.numberFormat(this.percentage, 2) + '%'}}}}");


		   hc.setOption(new OptionPath("/plotOptions"), s);

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

			
			hc.setOption(new OptionPath("/tooltip/formatter"), 	
		      new RawStringType("function() { " +
		      					" 			return '<b>'+ this.point.name +'</b>: ' + this.y +' " + cleanLabel(measurementUnit) + "<br> ' + $wnd.Highcharts.numberFormat(this.percentage, 2) +'% '" +  
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
		for (int i=0; i<size; i++) {
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		}
		return out;
	}
	
	// this one get the first and the latest year and make the serie complete (for the xAxis)
	public static LinkedHashMap<String, String> sortByValuesTimeserie(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		
		String startYear = sortedArray[0].toString();
		String endYear = sortedArray[size-1].toString();
//		System.out.println("YEARS: " + startYear + " | " + endYear );
		
		Integer start = Integer.valueOf(startYear);
		Integer end = Integer.valueOf(endYear);
		for(int i = start; i <= end; i++) {
			out.put(String.valueOf(i),String.valueOf(i));
		}
//		System.out.println("YEARS: " + out);
		
		return out;
	}
	
	private static void maxValueOnYAxis(HighChart hc) {
	
		try {
			hc.setOption(new OptionPath("/yAxis/endOnTick"), false);

//			hc.setOption(new OptionPath("/xAxis/minPadding"), 0.25);
//			hc.setOption(new OptionPath("/xAxis/startOnTick"), true);

//			hc.setOption(new OptionPath("/yAxis/max"), maxValue);
		} catch (Exception e) {
//			System.out.println("ERROR: maxValueOnYAxis");
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
		
//		String cleanLabel = label.replace("'", " ");
		cleanLabel = cleanLabel.replace("-", "\\-");
		
		
		return cleanLabel;
	}

}
