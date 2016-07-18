/*
 */

package org.fao.fenix.web.modules.map.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.core.client.utils.FormatValues;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.haiti.client.view.HaitiHotLinkWindow;
import org.fao.fenix.web.modules.haiti.common.services.HaitiServiceEntry;
import org.fao.fenix.web.modules.haiti.common.vo.FeatureInfoModelData;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This class is used from inside native JS.
 * 
 * @author Vortex
 */
public class GetFeatureInfoWindow {

	private static Grid<FeatureInfoModelData> grid;

	private static ListStore<FeatureInfoModelData> store;

	private static Window staticWindow = null;

	private HaitiHotLinkWindow haitiHotLinkWindow;
	
	private static Float lon;
	
	private static Float lat;
	
	private static String mapID;
	
	private static LoadingWindow loadingWindow = null;
	

	
	public static void showADAMpopup(String html) {

		ADAMViewMenuBuilder.hideWaitingPanel();
		System.out.println("HTML: " + html);
		try {

			HaitiServiceEntry.getInstance().parseFeatureInfoHTML(html, null, new AsyncCallback<Map<Integer, List<String>>>() {

				public void onSuccess(Map<Integer, List<String>> map) {
					
					System.out.println("TABLE: " + map.size());
					for(Integer key : map.keySet()) {
						System.out.println("key: " + key + " | " + map.get(key));
					}
					
					Integer quantityIndex = 0;
					Integer countryIndex = 0;
					StringBuffer htmlBuffer = new StringBuffer();
					
					List<String> headers = map.get(0);
					for (int i = 0; i < headers.size(); i++) {
						if (headers.get(i).contains("ADM0_NAME")) {
							countryIndex = new Integer(i);
						}
						if (headers.get(i).contains("quantity")) {
							quantityIndex = new Integer(i);
						}
					}
					
					List<String> content = map.get(1);
					System.out.println("CONTENT");
					//TODO change it
					
				
					
//					for (int i = 0; i < content.size(); i++) {
//						System.out.println(content.get(i));
//						
//					}
					
					System.out.println("quantityIndex: " + quantityIndex);
					System.out.println("countryIndex: " + countryIndex);
					
					if ( quantityIndex == 0 && countryIndex == 0) {
						// DO NOTHING
						FenixAlert.info("Attention", "No additional info on the selected polygon");
					}
					else {
						htmlBuffer.append("<div class='small-content'>");
						htmlBuffer.append("<b>" + content.get(countryIndex) +" <b>");
						htmlBuffer.append("</div>");
						htmlBuffer.append("<div class='small-content'>");
						htmlBuffer.append(FormatValues.formatValue(content.get(quantityIndex), 2) + "(mil $)");
						htmlBuffer.append("</div>");
						NativeOLCalls.addPopup(mapID, lon, lat, htmlBuffer.toString());
					}
					
				}

				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}

			});

		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	
	/** POPUP ADAM **/
	public static void prepareADAMpopup(Long mwId, String mapid, String lonlat) {
		ADAMViewMenuBuilder.showWaitingPanel("Getting polygon Info");
		MapWindow mw = MapWindow.getMapWindow(mwId);
		mapID = mapid;
		System.out.println("PREPARE ADAM: " + mapID);
		
		setLonLat(lonlat);
		if (mw == null)
			return;
		LayerItem li = mw.getSelectedLayer();
		if (li == null)
			return;
	}
	
	public static void setLonLat(String lonLat) {
		System.out.println("setLonLat: " + lonLat);
		List<String> lonLatList = getLonLat(lonLat);
		Float lonf = Float.valueOf(lonLatList.get(0));
		Float latf = Float.valueOf(lonLatList.get(1));
		lon = lonf;
		lat = latf;
		System.out.println("lon: " + lon);
		System.out.println("lat: " + lat);
	}
	
	private static List<String> getLonLat(String lonLat) {
		List<String> result = new ArrayList<String>();
//		System.out.println("1) " + lonLat.indexOf("lon=") + 4);
		String lon = lonLat.substring(lonLat.indexOf("lon=") + 4, lonLat.indexOf(","));
//		System.out.println("2) " + lonLat.indexOf(",lat=") + 5 );
		String lat = lonLat.substring(lonLat.indexOf(",lat=") + 5 );
		System.out.println("LON: " + lon);
		System.out.println("LAT: " + lat);
		result.add(lon);
		result.add(lat);
		return result;			
	}
	

	public static void prepare(Long mwId) {
		Window window = getWindow();
		MapWindow mw = MapWindow.getMapWindow(mwId);
		if (mw == null)
			return;
		LayerItem li = mw.getSelectedLayer();
		if (li == null)
			return;
		window.setHeading(li.getLayerTitle() + " @ " + mw.getMapViewTitle());
		window.removeAll();
		Html h = new Html("Loading... please wait...");
		window.add(h);
		window.layout();
		
		loadingWindow = new LoadingWindow("Quering layer", li.getLayerTitle() + " @ " + mw.getMapViewTitle(), "loading");
		loadingWindow.showLoadingBox();
//		window.show();
	}

	public static void prepareHaiti(String layerName) {
		Window window = getWindow();
		window.setHeading(layerName);
		window.removeAll();
		Html h = new Html("Loading... please wait...");
		window.add(h);
		window.layout();
	}

	public static void show(String html) {
	
		loadingWindow.destroyLoadingBox();
		
		
		MapServiceEntry.getInstance().parseFeatureInfoHTML(html, "", new AsyncCallback<List<List<String>>>() {

			public void onSuccess(List<List<String>> result) {
				Window w = getWindow();

				
				if ( result.isEmpty() ) {
					Html h = new Html("These is any valid selection");
					Window window = new Window();
					window.setHeading(w.getHeading());
					w.close();
					window.add(h);
					window.setWidth(675);
					window.setHeight(430);
					window.layout();
					window.show();
				}
				else {
					// TODO: remove with a different headers
					
					Integer size = result.get(0).size();
					List<String> headers = new ArrayList<String>();
					headers.add("Dimensions");
					for(int i = 1; i < size; i++) {
						headers.add("Values");
					}
					
					Window window = new Window();
					window.setHeading(w.getHeading());
					w.close();
					window.add(FeatureInfoGrid.buildGrid(headers, result, "650", "400"));
	//				window.setScrollMode(Scroll.ALWAYS);
					window.setWidth(680);
					window.setHeight(435);
					window.layout();
					window.show();
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				
			}

			
		
		});

		
	
	}

	
	public static void showHaiti(String args) {

		int separatotIdx = args.indexOf("~");
		final String html = args.substring(0, separatotIdx);
		final String language = args.substring(1 + separatotIdx, 3 + separatotIdx);
		final String fenixCode = args.substring(4 + separatotIdx);

		try {

			HaitiServiceEntry.getInstance().parseFeatureInfoHTML(html, fenixCode, new AsyncCallback<Map<Integer, List<String>>>() {

				public void onSuccess(Map<Integer, List<String>> map) {

					// headers
					List<String> headers = map.get(0);
					resetHeaders(headers);
					

					// hot-link
					Integer gaulCodeIndex = null;
					for (int i = 0; i < headers.size(); i++) {
						if (headers.get(i).contains("GAUL")) {
							gaulCodeIndex = new Integer(i);
							break;
						}
					}

					// contents
					List<String> contents = map.get(1);
					FeatureInfoModelData md = new FeatureInfoModelData();
					for (int i = 0; i < contents.size(); i++)
						md.addColumnValue(i, contents.get(i));
					store.removeAll();
					store.add(md);

					// hot link
					if (gaulCodeIndex != null) {
						new HaitiHotLinkWindow().build(contents.get(gaulCodeIndex), language);
					}
				}

				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}

			});

		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}

	}

	public static void showHaiti(String html, ListStore<FeatureInfoModelData> store) {
		Window window = getWindow();
		window.removeAll();
		Html h = new Html(html);
		window.add(h);
		window.layout();
	}

	private static Window getWindow() {
		if (staticWindow != null) {
			return staticWindow;
		}
		staticWindow = new Window();
		staticWindow.setWidth(1000);
		staticWindow.setHeight(150);
		staticWindow.setScrollMode(Scroll.AUTO);
		staticWindow.setResizable(true);
		staticWindow.setTitle("GetFeatureInfo");
		staticWindow.setStyleAttribute("bottom", "10px");
		return staticWindow;
	}
	

	private static void resetHeaders() {
		for (int i = 0; i < grid.getColumnModel().getColumnCount(); i++)
			grid.getColumnModel().setColumnHeader(i, "Column " + i);
	}

	private static void resetHeaders(List<String> columnHeaders) {
		for (int i = 0; i < grid.getColumnModel().getColumnCount(); i++) {
			if (i < columnHeaders.size()) {
				grid.getColumnModel().setHidden(i, false);
				grid.getColumnModel().setColumnHeader(i, columnHeaders.get(i));
			}
			else {
				grid.getColumnModel().setHidden(i, true);
				grid.getColumnModel().setColumnHeader(i, "");
			}
		}
	}

	public HaitiHotLinkWindow getHaitiHotLinkWindow() {
		return haitiHotLinkWindow;
	}

	public static ListStore<FeatureInfoModelData> getStore() {
		return store;
	}

	public static void setStore(ListStore<FeatureInfoModelData> store) {
		GetFeatureInfoWindow.store = store;
	}

	public static Grid<FeatureInfoModelData> getGrid() {
		return grid;
	}

	public static void setGrid(Grid<FeatureInfoModelData> grid) {
		GetFeatureInfoWindow.grid = grid;
	}

	public static Float getLon() {
		return lon;
	}

	public static Float getLat() {
		return lat;
	}

	public static String getMapID() {
		return mapID;
	}
	
	
	
	
}