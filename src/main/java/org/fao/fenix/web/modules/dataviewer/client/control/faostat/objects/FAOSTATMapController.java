package org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATEmptyValuesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATMapDisclaimerPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuMap;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class FAOSTATMapController {
		
	static Timer timer;
	
	public static void addMap(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final String width, final String height, final boolean customColors) {
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));
		panel.setWidth(width);
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				public void onSuccess(DWFAOSTATResultVO rvo) {	
					qvo.setRvo(rvo);
					createMap(panel, qvo, rvo, width, height, customColors);
				}
				public void onFailure(Throwable arg0) {
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createMap(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, final String width, final String height, final boolean customColors) {
		panel.removeAll();
		panel.setWidth(width);
		ContentPanel mapPanel = new ContentPanel();
		mapPanel.setHeaderVisible(false);
		mapPanel.setBodyBorder(false);
		mapPanel.addStyleName("content_box");

		
		
//		String[] urlParts = rvo.getUrl().split("\\?");
//		if (urlParts.length > 1) {
//		    String query = urlParts[1];
//		    for (String param : query.split("&")) {
//		        String[] pair = param.split("=");
//		        
//		        String key = URL.decode(pair[0]);
//				String value = URL.decode(pair[1]);
//		        System.out.println("KEY: " + key);
//				System.out.println("VALUES: " + value);
//		    }
//		}

		try {
			if ( rvo.getMapsVO() != null)
				for(String key : rvo.getMapsVO().getValues().keySet()) {
					rvo.setUrl(rvo.getUrl() + "&" + key + "=" + rvo.getMapsVO().getValues().get(key));
				}
		}catch (Exception e) {
		}
		
		// this is used to enable the zoom in the map 
		if ( qvo.isEnableZoomTo() ) {
			if ( !qvo.getZoomto().isEmpty() ) {
				String zoomto = "";
				for(String key : qvo.getZoomto().keySet() ) {
					zoomto = key;
				}
				rvo.setUrl(rvo.getUrl() + "&zoomto=FAOSTAT(" + zoomto +")");
			}
		}
		System.out.println("MAP URL: " + rvo.getUrl());
		panel.add(FAOSTATBoxMenuMap.buildMenu(qvo, rvo, width, width, rvo.getUrl(), true, mapPanel));
		
		// make it more dynamic
		mapPanel.setUrl(rvo.getUrl());
		mapPanel.setWidth(width);
		mapPanel.setHeight(height);
		panel.add(mapPanel);
			
		panel.layout();
	}
	
	public static void addGoogleMap(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final String width, final String height, final boolean customColors) {
		// this is called to get the cached RVO (if it exist and doesn't have to be launched an async call 
		if ( qvo.getRvo() != null && !qvo.getRunAsyncCall()) {

			// this is a workaround for google maps.
			timer = new Timer() {
				public void run() {
					createGoogleMap(panel, qvo, qvo.getRvo(), width, height, customColors);
					timer.cancel();
				}
			};
			timer.scheduleRepeating(10);
			
		}
		// otherwise call the asyncall
		else {
			
			panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));
			panel.setWidth(width);
			try {
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					public void onSuccess(DWFAOSTATResultVO rvo) {	
						qvo.setRvo(rvo);
						createGoogleMap(panel, qvo, rvo, width, height, customColors);
					}
					public void onFailure(Throwable arg0) {
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void createGoogleMap(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, final String width, final String height, final boolean customColors) {
		panel.removeAll();
		panel.setWidth(width);
		panel.add(FAOSTATBoxMenuMap.buildMenu(qvo, rvo, width, width, null, false, null));
		
		if ( rvo.getChartValues().isEmpty() ) {
			/** TODO: set autoheight true, it doesn't work, test why.. **/
//			panel.setAutoHeight(true);
			panel.setHeight(FAOSTATConstants.calculateHeightWithBoxMenu(height));
			panel.add(FAOSTATEmptyValuesPanel.build(FAOSTATLanguage.print().noDataAvailable(), width, null));
			panel.layout();
		}
		else {
			Double random = Math.random();
			String id = "map_google_" + random.toString();
			// make it more dynamic
			Html map = new Html("<div id='" + id + "'></div>");
			map.addStyleName("content_box");
			map.setWidth(width);
			map.setHeight(height);

			panel.add(map);
			
			panel.add(FAOSTATMapDisclaimerPanel.build());
			
			panel.layout();
			String regioncode = null;
	
			String widthMap = String.valueOf(Integer.parseInt(width) - 2);
			String heightMap = String.valueOf(Integer.parseInt(height) - 2);
			
			Map<String, Map<String, Double>> values = rvo.getChartValues();
			drawMap(values, widthMap, heightMap, id, regioncode, rvo.getMeasurementUnit(), customColors);
		}
	}

	
	private static void drawMap(Map<String, Map<String, Double>> map, String width, String height, String position, String regioncode, String measurementUnit, boolean customColors) {
		JSONArray countriesCode = new JSONArray();
		JSONArray values = new JSONArray();
		JSONArray countriesLabel = new JSONArray();
		JSONArray legendColors = new JSONArray();
		
		int i=0;
		
		for(String countryCode : map.keySet()) {
			for(String countryLabel : map.get(countryCode).keySet()) {
				countriesCode.set(i, new JSONString(countryCode));
				values.set(i, new JSONNumber(map.get(countryCode).get(countryLabel)));
				countriesLabel.set(i, new JSONString(countryLabel));
			}
			i++;
		}	
		String[] colors = new String[]{"0xFFFFCC", "0xFFEDA0", "0xFED976", "0xFEB24C", "0xFD8D3C", "0xFC4E2A", "0xE31A1C", "0xBD0026", "0x800026"};
		String[] defaultColors = new String[]{"0xE0FFD4", "0xA5EF63", "0x50AA00", "0x267114"};
		if (customColors) {
			for (int z = 0 ; z < colors.length ; z++)
				legendColors.set(z, new JSONString(colors[z]));
		} else {
			for (int z = 0 ; z < defaultColors.length ; z++)
				legendColors.set(z, new JSONString(defaultColors[z]));
		}
		
		drawMapJS(countriesCode, values, countriesLabel, width, height, position, regioncode, measurementUnit, legendColors);
	}

	public static native void drawMapJS(JSONArray countriesCode, JSONArray quantity, JSONArray countriesLabel, String width, String height, String position, String regioncode, String measurementUnit, JSONArray legendColors)/*-{
		///////////////////// JSONArray
		var size =      countriesCode.@com.google.gwt.json.client.JSONArray::size()();	
	//	alert("size: " + size);
		
		var countries = new Array();
	 	var values = new Array();
	 	var countriesL = new Array();
	 	var colors = new Array();

		
		for(var index = 0; index < size; index++) {
			var c = countriesCode.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var v = quantity.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var l = countriesLabel.@com.google.gwt.json.client.JSONArray::jsArray[index];
			
//			alert("value: " + c + " | " + v + " | " + l );
			countries[index] = c;
			values[index] = v;
			countriesL[index] = l;
		}
		
		var colorsSize = legendColors.@com.google.gwt.json.client.JSONArray::size()();
		for(var index = 0; index < colorsSize; index++) {
			var col = legendColors.@com.google.gwt.json.client.JSONArray::jsArray[index];
			colors[index] = col;
			//alert("Color: " + col);
		}
		
		$wnd.countries = countries;
		$wnd.values = values;
		$wnd.countriesLabel = countriesL;
		$wnd.measurementUnit = measurementUnit;
		$wnd.map_colors = colors;
	
	
		
//		alert("here2: "); 
		$wnd.map_width = width;
	    $wnd.map_height = height;
	    $wnd.map_position = position;



	    $wnd.map_region = regioncode;

		$wnd.drawMap();
		
		
	
	    // alert("$wnd.map_region:" + $wnd.map_region);
	
	 
	}-*/;

	
	// hardcoded to FAOSTAT codes
	// TODO: change with a aggregation region filter...
	private static String getRegion(DWFAOSTATQueryVO qvo) {
		String regioncode = "world";
		
		System.out.println("qvo.getAggregationsFilter(): " + qvo.getAggregationsFilter());
		
		for(String key : qvo.getAggregationsFilter().keySet()) {
			
			// EUROPE, SUBEUROPE (EUROPEAN UNION)
			if ( key.contains("540") || key.equals("5706")) {
				regioncode = "150";
			}
			
			// ASIA
//			5301,Central Asia
//			5302,Eastern Asia
//			5303,Southern Asia
//			5304,South-Eastern Asia
//			5305,Western Asia
//			else if ( key.contains("530")) {
//				// doesn't exist
//				//regioncode = "142";
//			}
			else if ( key.equals("5301") ) {
				regioncode = "143";
			}
			else if ( key.equals("5302") ) {
				regioncode = "030";
			}
			else if ( key.equals("5203") ) {
				regioncode = "034";
			}
			else if ( key.equals("5304") ) {
				//regioncode = "034";
			}
			else if ( key.equals("5305") ) {
				//regioncode = "";
			}

			
			
			
			// OCEANIA
			else if ( key.contains("550")) {
				regioncode = "009";
			}
			
			// AMERICAS
			else if ( key.contains("520")) {
				//regioncode = "019";
			}
			
			// AFRICA
			else if ( key.contains("510")) {
				regioncode = "002";
			}
			
		}
		
		// mapping
		
		
		return regioncode;
	} 
			
	//	world - (Whole world) - 5000
	//	us_metro - (United States, metro areas)
	//	005 - (South America) 
	//	013 - (Central America)
	//	021 - (North America)
	//	002 - (All of Africa)
	//	017 - (Central Africa)
	//	015 - (Northern Africa)
	//	018 - (Southern Africa)
	//	030 - (Eastern Asia)
	//	034 - (Southern Asia)
	//	035 - (Asia/Pacific region)
	//	143 - (Central Asia)
	//	145 - (Middle East)
	//	151 - (Northern Asia)
	//	154 - (Northern Europe)
	//	155 - (Western Europe)
	//	039 - (Southern Europe)
	//	053 Australia and New Zealand 
	//	054 Melanesia 
	//	057 Micronesia 
	//	061 Polynesia 
	//	029 Caribbean 
	//	011 Western Africa 
	//	014 Eastern Africa 
	
	
	
	
	
	
//	AreaGroupCode,AreaGroupNameE
//	5000,World
//	5100,Africa
//	5101,Eastern Africa
//	5102,Middle Africa
//	5103,Northern Africa
//	5104,Southern Africa
//	5105,Western Africa
//	5200,Americas
//	5203,Northern America
//	5204,Central America
//	5206,Caribbean
//	5207,South America
//	5300,Asia
//	5301,Central Asia
//	5302,Eastern Asia
//	5303,Southern Asia
//	5304,South-Eastern Asia
//	5305,Western Asia
//	5400,Europe
//	5401,Eastern Europe
//	5402,Northern Europe
//	5403,Southern Europe
//	5404,Western Europe
//	5500,Oceania
//	5501,Australia & New Zealand
//	5502,Melanesia
//	5503,Micronesia
//	5504,Polynesia
//	5706,European Union
//	5801,Least Developed Countries
//	5802,Land Locked Developing Countries
//	5803,Small Island Developing States
//	5815,Low Income Food Deficit Countries
//	5817,Net Food Importing Developing Count
}
