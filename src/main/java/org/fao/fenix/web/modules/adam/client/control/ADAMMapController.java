package org.fao.fenix.web.modules.adam.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMEmptyValuesPanel;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMMapController extends ADAMController {
	
	public static void addGoogleMapsADAMView(final ADAMQueryVO q, final String width, final String height, final String color, final String position, final Boolean isSmall, final Boolean agricultureFilter, final long objID) {
//		ADAMQueryVO q = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + "("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, agricultureFilter);
		
		addGoogleMaps(q, width, height, color, position, isSmall, objID);
	}	
	
	public static void addGoogleMapsFAOView(String title, final HashMap<String, List<String>> filters, final String width, final String height, final String color, final String position, final Boolean isSmall, String filterFramework, HashMap<String, String> priorities, final long objID, boolean isSO, boolean isOR) {
						
	    ADAMQueryVO q = ADAMQueryVOBuilderFAO.googleMapsQueryFAOView(title + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, filterFramework, priorities);
	    q.setIsSO(isSO);
	    q.setIsOR(isOR);
		       
		addGoogleMaps(q, width, height, color, position, isSmall,  objID);
	}	

	public static ADAMQueryVO getGoogleMapsFAOViewVO(String title, final HashMap<String, List<String>> filters, final String width, final String height, final String color, final String position, final Boolean isSmall, String filterFramework, HashMap<String, String> priorities, final long objID, boolean isSO, boolean isOR) {
		
	    ADAMQueryVO q = ADAMQueryVOBuilderFAO.googleMapsQueryFAOView(title + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, filterFramework, priorities);
	    q.setIsSO(isSO);
	    q.setIsOR(isOR);
		       
		return q;
	}	
	
	static void addGoogleMaps(final ADAMQueryVO q, final String width, final String height, final String color, final String position, final Boolean isSmall, final long objID) {	
	//	System.out.println("changing the view based on priorities");

		//System.out.println("AGGREGATIONCOMLUM: " +  crs_aggregationColumn.getGaulCode());
		//System.out.println("type: " +  crs_aggregationType);
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		
		
		/**TODO: Obj check **/
		RootPanel.get(position).setStyleName("small-box content");

		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));

				RootPanel.get(position).setStyleName("small-box content");
				//System.out.println("result: " + vo.getChartValues());
				Map<String, Map<String, Double>> values = vo.getChartValues();
				
				if ( !q.getSwitchResources().isEmpty() ) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				VerticalPanel panel = new VerticalPanel();
				panel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT_MAP);
				
				if ( isSmall ) {
					panel.add(ADAMBoxMaker.buildGoogleMapsMenu(q, vo, ADAMConstants.SMALL_MENU_GAP, position, color, ADAMMapController.addGoogleMapsFullView(q,vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT, color, "CENTER", false, objID), isSmall));
				}
				

				if ( values.isEmpty() ) {
					panel.add(new ADAMEmptyValuesPanel().build("No data available for the current selection", width, height));		
					RootPanel.get(position).add(panel);		
				}
				else {
					// make it more dynamic
					panel.add(new Html("<div id='map_google'></div>"));
					RootPanel.get(position).add(panel);
					drawMap(values, width, height, "map_google");
				}
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	};
	
	
	public static SelectionListener<ButtonEvent> addGoogleMapsFullView(final ADAMQueryVO qvo, final ADAMResultVO vo, final String width, final String height, final String color, final String position, final Boolean isSmall, final long objID) {	
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
		
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));

				RootPanel.get(position).setStyleName("big-box content");
				//System.out.println("result: " + vo.getChartValues());
				Map<String, Map<String, Double>> values = vo.getChartValues();
				
				
				VerticalPanel panel = new VerticalPanel();
				panel.setHeight(ADAMConstants.BIG_BOX_HEIGHT_MAP);
				//panel.setWidth(ADAMConstants.BIG_BOX_WIDTH);
				
				
				panel.add(ADAMBoxMaker.buildGoogleMapsMenu(qvo, vo, ADAMConstants.BIG_MENU_GAP, position, color, removeCenterMap(), isSmall));
				
				//panel.add(ADAMBoxMaker.buildGoogleMapsMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, removeCenterMap(), isSmall));
				if ( values.isEmpty() ) {
					panel.add(new ADAMEmptyValuesPanel().build("No data available for the current selection", width, height));		
					RootPanel.get(position).add(panel);		
				}
				else {
					// make it more dynamic
					panel.add(new Html("<div id='map_google_big'></div>"));
					RootPanel.get(position).add(panel);
					drawMap(values, width, height, "map_google_big");
				}
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> removeCenterMap() {	
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeCenter();
			}
		};
	}



	
	private static void drawMap(Map<String, Map<String, Double>> map, String width, String height, String position) {
		JSONArray countriesCode = new JSONArray();
		JSONArray values = new JSONArray();
		JSONArray countriesLabel = new JSONArray();
		
		int i=0;
		
		for(String countryCode : map.keySet()) {
			for(String countryLabel : map.get(countryCode).keySet()) {
//				System.out.println(countryLabel +  " | " + countryCode + " | " + map.get(countryCode).get(countryLabel));
				countriesCode.set(i, new JSONString(countryCode));
				values.set(i, new JSONNumber(map.get(countryCode).get(countryLabel)));
				countriesLabel.set(i, new JSONString(countryLabel));
			}
			i++;
		}

		
//		System.out.println("countriesCode: " + countriesCode );
//		System.out.println("values: " + values );
//		System.out.println("countriesLabel: " + countriesLabel );
		drawMapJS(countriesCode, values, countriesLabel, width, height, position, ADAMController.baseUnit);
	}
	
	
	public static native void drawMapJS(JSONArray countriesCode, JSONArray quantity, JSONArray countriesLabel, String width, String height, String position, String measurementUnit)/*-{
		///////////////////// JSONArray
		var size =      countriesCode.@com.google.gwt.json.client.JSONArray::size()();	
	//	alert("size: " + size);
		
		var countries = new Array();
	 	var values = new Array();
	 	var countriesL = new Array();
		
	//	var k = countriesCode.@com.google.gwt.json.client.JSONArray::jsArray[0];
	//	alert("keyset: " + k);
		
		for(var index = 0; index < size; index++) {
			var c = countriesCode.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var v = quantity.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var l = countriesLabel.@com.google.gwt.json.client.JSONArray::jsArray[index];
			
	//		alert("value: " + c + " | " + v + " | " + l );
			countries[index] = c;
			values[index] = v;
			countriesL[index] = l;
		}
	//	var i = 0;
	//	var keyset = args.@com.google.gwt.json.client.JSONArray::get(Ljava/lang/Integer;)(i);	
		
	//	alert("keyset.length: " + keyset);
	
		////////////////////////////
		
		
	
		
	//	alert(countries.length);
	//	alert(values.length);
		
		$wnd.countries = countries;
		$wnd.values = values;
		$wnd.countriesLabel = countriesL;
		$wnd.unit = measurementUnit;
	
	
		
		//	alert("here2: "); 
		$wnd.map_width = width;
	    $wnd.map_height = height;
	    $wnd.map_position = position;
		
		$wnd.drawMap();
		
		
	
	     //alert("matix:" + matrix);
	
	 
	}-*/;
	
	
}
