package org.fao.fenix.web.modules.core.client.utils.highcharts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HighChartPalettes {
	
	
	public static LinkedHashMap<String, List<String>> palettes = new LinkedHashMap<String, List<String>>();
	public static List<String> secondPalette;
	
	static {
		secondPalette = new ArrayList<String>();
		
		secondPalette.add("#4572A7"); // blue
		secondPalette.add("#AA4643"); // red 
		secondPalette.add("#89A54E"); // green 
		secondPalette.add("#80699B"); // purple
		secondPalette.add("#3D96AE"); // light blue  
		secondPalette.add("#DB843D"); // orange/yellow
		
		secondPalette.add("#A47D7C"); // light purple 
		secondPalette.add("#B5CA92"); // light green  

		
		// REPEATED
		secondPalette.add("#4572A7"); // blue
		secondPalette.add("#AA4643"); // red 
		secondPalette.add("#89A54E"); // green 
		secondPalette.add("#80699B"); // purple
		secondPalette.add("#3D96AE"); // light blue  
		secondPalette.add("#DB843D"); // orange/yellow
		
		secondPalette.add("#A47D7C"); // light purple 
		secondPalette.add("#B5CA92"); // light green  
		
		
		// REPEATED
		secondPalette.add("#4572A7"); // blue
		secondPalette.add("#AA4643"); // red 
		secondPalette.add("#89A54E"); // green 
		secondPalette.add("#80699B"); // purple
		secondPalette.add("#3D96AE"); // light blue  
		secondPalette.add("#DB843D"); // orange/yellow
		
		secondPalette.add("#A47D7C"); // light purple 
		secondPalette.add("#B5CA92"); // light green  
		
		
		
	}
	
	
	static {
		palettes = new LinkedHashMap<String, List<String>>();
		
		palettes.put("SECONDPALETTE", secondPalette);
	}
	
	
	
	

}
