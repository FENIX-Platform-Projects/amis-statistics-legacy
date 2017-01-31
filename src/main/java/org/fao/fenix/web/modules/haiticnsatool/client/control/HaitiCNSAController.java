package org.fao.fenix.web.modules.haiticnsatool.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.bangladesh.client.view.BangladeshWindow;
import org.fao.fenix.web.modules.bangladesh.common.services.BangladeshServiceEntry;
import org.fao.fenix.web.modules.haiti.client.view.HaitiOLPanel;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAChartPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSACommandPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSALegendPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAMapPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
import org.fao.fenix.web.modules.haiticnsatool.common.services.HaitiCNSAToolServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever;
import org.fao.fenix.web.modules.map.client.view.form.ExportAsPDFForm;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.ofcchart.common.services.OfcChartServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.rednels.ofcgwt.client.ChartWidget;


public class HaitiCNSAController {
	
	private static Timer timer;

	
	public static void initializeCNSATool(final String gaul0code, final HaitiCNSAPanel haitiCNSAPanel, final String language) {
		initializeCNSADates(gaul0code, haitiCNSAPanel);
	}
	
	public static void initializeCNSAMapOtherDekads(final String gaul0code, final String date, final HaitiCNSACommandPanel haitiCNSACommandPanel, final HaitiCNSAMapPanel haitiCNSAMapPanel, final String language) {
		final HaitiOLPanel haitiOLPanel = haitiCNSAMapPanel.getHaitiOLPanel();
		final ClientMapView mapView = haitiOLPanel.getMapView();
		final MapPanel mapPanel = haitiOLPanel.getMapPanel();
		
		
		haitiCNSAMapPanel.getToolBase().setHeading("Vegetation Index - " + date);
		
		System.out.println("initializeCNSAMapOtherDekads: " + mapPanel.getMapDivId() + " | " + date);
		HaitiCNSAToolServiceEntry.getInstance().retrieveCNSALayers(gaul0code, date, new AsyncCallback<List<ClientGeoView>>() {
			
			public void onSuccess(List<ClientGeoView> cgvlist) {
				
				Map<String, Map<String, LayerVO>> layersMap = haitiCNSACommandPanel.getLayersCategoryMap();

				ClientBBox bbox = null;
				List<LayerVO> layerVOs = new ArrayList<LayerVO>();
				int j=0;
				for (ClientGeoView clientGeoView : cgvlist) { 
					// this is used for the first creation of the map
					if ( !mapPanel.getIsMapCreated()) 
						mapView.addLayer(clientGeoView);		
					// this is used when the map is already been created (and the j is used to skip the carribean (base) layer)
					else if ( mapPanel.getIsMapCreated())
						mapPanel.addLayer(clientGeoView);

					if (bbox == null)
						bbox = clientGeoView.getBBox();
					else
						bbox = bbox.union(clientGeoView.getBBox());
					LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
					vo.setWmsURL(clientGeoView.getGetMapUrl());
					vo.setStyleName(clientGeoView.getStyleName());
					vo.setFenixCode(clientGeoView.getFenixCode());
					vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
					vo.setSource(clientGeoView.getSource());
					vo.setSourceContact(clientGeoView.getSourceContact());
					vo.setProvider(clientGeoView.getProvider());
					vo.setProviderContact(clientGeoView.getProviderContact());
					vo.setIsHidden(clientGeoView.isHidden());
					vo.setClientBBox(clientGeoView.getBBox());
					layerVOs.add(vo);
					j++;
				}
				System.out.println("\n");
				for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
					System.out.println("adding layer: " +mapPanel.getMapDivId() + " | " + layerVOs.get(i).getFenixCode());
					if ( i == 0 && !mapPanel.getIsMapCreated()) {
						System.out.println("   ADDED BASE layer: " + layerVOs.get(i).getFenixCode());
						layerVOs.get(i).setIsBaseLayer(true);
//						layersMap.put(layerVOs.get(i).getFenixCode(), layerVOs.get(i));
						putLayerToHashMap(layersMap, layerVOs.get(i).getFenixCode(), layerVOs.get(i), mapPanel.getMapDivId());
					}
					else if (  i != 0 ) {
						putLayerToHashMap(layersMap, layerVOs.get(i).getFenixCode(), layerVOs.get(i), mapPanel.getMapDivId());
						System.out.println("   ADDED layer: " + layerVOs.get(i).getFenixCode());

					}
					
				}

			
			
				
				mapView.setBbox(bbox);
				if ( !mapPanel.getIsMapCreated()) {
					mapPanel.createMapHaiti(mapView, language);
				}
				
				
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
			
		});

		
	}
	
	private static void putLayerToHashMap(Map<String, Map<String, LayerVO>> layersMap, String category, LayerVO layerVO, String mapViewID) {
		
		if ( layersMap.containsKey(category) ) 
			layersMap.get(category).put(mapViewID, layerVO);
		else {
			Map<String, LayerVO> hashMap = new HashMap<String, LayerVO>();
			hashMap.put(mapViewID, layerVO);
			layersMap.put(category, hashMap);
		}
		
		System.out.println("Vegetation Index - " + layersMap);
	}
	
		
	public static void initializeCNSAMap(final String gaul0code, final ComboBox<ListItem> combo, final HaitiCNSACommandPanel haitiCNSACommandPanel, final HaitiCNSAMapPanel haitiCNSAMapPanel, final String language, final List<HaitiCNSAMapPanel> otherMapPanels) {
		final HaitiOLPanel haitiOLPanel = haitiCNSAMapPanel.getHaitiOLPanel();
		final ClientMapView mapView = haitiOLPanel.getMapView();
		final MapPanel mapPanel = haitiOLPanel.getMapPanel();
		final ListItem value = combo.getValue();
		
		System.out.println("initializeCNSAMap: " + mapPanel.getMapDivId() + " | " + value.getValue());
		
	
		haitiCNSAMapPanel.getToolBase().setHeading("Vegetation Index - " + value.getValue());
		
		HaitiCNSAToolServiceEntry.getInstance().retrieveCNSALayers(gaul0code, value.getValue(), new AsyncCallback<List<ClientGeoView>>() {
			
			public void onSuccess(List<ClientGeoView> cgvlist) {
				
				Map<String, Map<String, LayerVO>> layersMap = haitiCNSACommandPanel.getLayersCategoryMap();

				ClientBBox bbox = null;
				List<LayerVO> layerVOs = new ArrayList<LayerVO>();
				int j=0;
				for (ClientGeoView clientGeoView : cgvlist) { 
					// this is used for the first creation of the map
					if ( !mapPanel.getIsMapCreated()) 
						mapView.addLayer(clientGeoView);		
					// this is used when the map is already been created (and the j is used to skip the carribean (base) layer)
					else if ( mapPanel.getIsMapCreated())
						mapPanel.addLayer(clientGeoView);

					if (bbox == null)
						bbox = clientGeoView.getBBox();
					else
						bbox = bbox.union(clientGeoView.getBBox());
					LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
					vo.setWmsURL(clientGeoView.getGetMapUrl());
					vo.setStyleName(clientGeoView.getStyleName());
					vo.setFenixCode(clientGeoView.getFenixCode());
					vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
					vo.setSource(clientGeoView.getSource());
					vo.setSourceContact(clientGeoView.getSourceContact());
					vo.setProvider(clientGeoView.getProvider());
					vo.setProviderContact(clientGeoView.getProviderContact());
					vo.setIsHidden(clientGeoView.isHidden());
					vo.setClientBBox(clientGeoView.getBBox());
					layerVOs.add(vo);
					j++;
				}
				for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
					System.out.println("adding layer: " + mapPanel.getMapDivId() + " | " + layerVOs.get(i).getFenixCode());
					if ( i == 0 && !mapPanel.getIsMapCreated()) {
						System.out.println("   ADDED BASE layer: " + layerVOs.get(i).getFenixCode());
						layerVOs.get(i).setIsBaseLayer(true);
						putLayerToHashMap(layersMap, layerVOs.get(i).getFenixCode(), layerVOs.get(i), mapPanel.getMapDivId());
					}
					else if (  i != 0 ) {
						putLayerToHashMap(layersMap, layerVOs.get(i).getFenixCode(), layerVOs.get(i), mapPanel.getMapDivId());
						System.out.println("   ADDED layer: " + layerVOs.get(i).getFenixCode());
					}
					
				}
				
//				for(String key : layersMap.keySet()) 
//					System.out.println("2haitiCNSACommandPanel.getLayersMap(): " + key + " | " + layersMap.get(key).getLayerName());
				

			
			
				
				mapView.setBbox(bbox);
				if ( !mapPanel.getIsMapCreated()) {
					mapPanel.createMapHaiti(mapView, language);
				}
				
				List<MapPanel> otherMP = new ArrayList<MapPanel>();
				for(HaitiCNSAMapPanel otherMapPanel : otherMapPanels) {
					otherMP.add(otherMapPanel.getHaitiOLPanel().getMapPanel());
				}
				
				
				ListStore<ListItem> dates = combo.getStore();
				int baseIndex = 0;
				for(int i=0; i < dates.getCount(); i++) {
					System.out.println("dates: " + i + "| " + dates.getAt(i).getValue());
					if ( value.getValue().equals(dates.getAt(i).getValue())) {
						System.out.println("same date, fill index " + i);
						baseIndex = i;
						break;
					}
				}
				
				
				int k=1;
				System.out.println("otherMapPanels size: " + otherMapPanels.size());
				for (HaitiCNSAMapPanel otherMapPanel : otherMapPanels) {
					System.out.println("baseIndex dates: " + (baseIndex + k) + "| " + dates.getAt(baseIndex + k).getValue());
					initializeCNSAMapOtherDekads(gaul0code, dates.getAt(baseIndex + k).getValue(), haitiCNSACommandPanel, otherMapPanel, language);
					k++;
				}
				
				getCurrentBoundingBox(mapPanel, otherMP);
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
			
		});

		
	}
	
	public static void addRemoveLegends(final HaitiCNSAPanel haitiCNSAPanel, final String language) {
		HaitiCNSACommandPanel haitiCNSACommandPanel = haitiCNSAPanel.getCommandPanel();
		HaitiCNSALegendPanel haitiCNSALegendPanel = haitiCNSAPanel.getLegendPanel();
		
		for(int i=0 ; i < haitiCNSACommandPanel.getMenu().getItemCount(); i++) {
			CheckMenuItem item = (CheckMenuItem) haitiCNSACommandPanel.getMenu().getItem(i);
			System.out.println(item.getItemId() + " | " + item.isChecked() );
			if ( item.isChecked()) {
				if ( item.getItemId().equals("ndvi"))
					haitiCNSALegendPanel.getNdvi().show();				
				else if ( item.getItemId().equals("ndvi_va"))
					haitiCNSALegendPanel.getNdvi_va().show();
				else if ( item.getItemId().equals("agric_land"))
					haitiCNSALegendPanel.getAgro_land().show();
			}
			else {
				if ( item.getItemId().equals("ndvi"))
					haitiCNSALegendPanel.getNdvi().hide();				
				else if ( item.getItemId().equals("ndvi_va"))
					haitiCNSALegendPanel.getNdvi_va().hide();
				else if ( item.getItemId().equals("agric_land"))
					haitiCNSALegendPanel.getAgro_land().hide();
			}
		}
	}
	
	public static void initializeCNSAChart(final String gaul0code, final String date, final HaitiCNSAChartPanel haitiCNSAPanel, final String language) {
		haitiCNSAPanel.getTitle().setHtml(HaitiLangEntry.getInstance(language).loadingChartData() + "...");
		haitiCNSAPanel.getTitle().show();
		haitiCNSAPanel.getChartPanel().removeAll();
		HaitiCNSAToolServiceEntry.getInstance().retrieveCNSAChart(gaul0code, date, "", computeHeightRatio(haitiCNSAPanel.getWidth()) + "px", haitiCNSAPanel.getPanelChartHeight(), HaitiLangEntry.getInstance(language).chartTitle() + " - " + date, HaitiLangEntry.getInstance(language).yAxisMeasurementUnit(), haitiCNSAPanel.getLanguage() ,new AsyncCallback<String>() {
			public void onSuccess(String result) {
				haitiCNSAPanel.getTitle().hide();
//				haitiCNSAPanel.getTitle().setHtml(HaitiLangEntry.getInstance(language).chartTitle() + " - " + date);
				haitiCNSAPanel.getChartPanel().add(new HTML(result));
				haitiCNSAPanel.getChartPanel().layout();
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	public static void initializeCNSADates(final String gaul0code, final HaitiCNSAPanel haitiCNSAPanel) {
		HaitiCNSAToolServiceEntry.getInstance().retrieveCNSADates(gaul0code, new AsyncCallback<HashMap<String,String>>() {
			public void onSuccess(HashMap<String, String> result) {
				
				ListStore<ListItem> store = haitiCNSAPanel.getCommandPanel().getStore();
				ComboBox<ListItem> combo = haitiCNSAPanel.getCommandPanel().getCombo();
				LinkedHashMap<String, String> out = sortByValues(result);

				List<ListItem> values = new ArrayList<ListItem>();
				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
				}
				store.removeAll();
				store.add(values);
				

				if (!values.isEmpty())
					combo.setValue(values.get(0));
				
				store.clearFilters();
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
		
	
	private static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size -1; i >= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	public static SelectionListener<MenuEvent> showHideLayer(final HaitiCNSAPanel haitiCNSAPanel) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				CheckMenuItem menuItem = (CheckMenuItem) ce.getItem();
				
				showHideLayer(menuItem, haitiCNSAPanel.getMapPanel().getHaitiOLPanel().getMapPanel(), haitiCNSAPanel);
				addRemoveLegends(haitiCNSAPanel, haitiCNSAPanel.getLanguage());
				
				for(String key : haitiCNSAPanel.getMapPanelDekadsHM().keySet()) 
					showHideLayer(menuItem, haitiCNSAPanel.getMapPanelDekadsHM().get(key).getHaitiOLPanel().getMapPanel(), haitiCNSAPanel);
				
				
//				String itemId = menuItem.getItemId();
//				MapPanel mapPanel = haitiCNSAPanel.getMapPanel().getHaitiOLPanel().getMapPanel();
//				LayerVO layerVo = haitiCNSAPanel.getCommandPanel().getLayersMap().get(itemId);
//				try{
//					mapPanel.setLayerVisibility(layerVo.getOlID(), menuItem.isChecked());
//				} catch (Exception e) {
//					
//				}
//				
//				addRemoveLegends(haitiCNSAPanel, haitiCNSAPanel.getLanguage());
			}
		};
	}
	
	



	private static void showHideLayer(final CheckMenuItem menuItem,final  MapPanel mapPanel,final   HaitiCNSAPanel haitiCNSAPanel) {
		System.out.println("----> showHideLayer");
		String itemId = menuItem.getItemId();
		LayerVO layerVo = haitiCNSAPanel.getCommandPanel().getLayersCategoryMap().get(itemId).get(mapPanel.getMapDivId());

		try{
			mapPanel.setLayerVisibility(layerVo.getOlID(), menuItem.isChecked());
		} catch (Exception e) {
			
		}
		
		
	}
	
	public static SelectionListener<ButtonEvent> exportReport(final HaitiCNSAPanel haitiCNSAPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String language = haitiCNSAPanel.getLanguage();
				String countryCode = haitiCNSAPanel.getGaul0code();
				String date = haitiCNSAPanel.getCommandPanel().getCombo().getValue().getValue();
				

				
				
				ClientMapView cmv = createClientMapView(haitiCNSAPanel.getCommandPanel(), haitiCNSAPanel.getMapPanel());
				final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
				loadingWindow.showLoadingBox();
				
				
				HaitiCNSAToolServiceEntry.getInstance().exportReport(cmv, new ArrayList<ClientGeoView>(), "PDF", countryCode, date, "", "500px", "400px", HaitiLangEntry.getInstance(language).chartTitle(), HaitiLangEntry.getInstance(language).yAxisMeasurementUnit(), language,  new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loadingWindow.destroyLoadingBox();
						Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
////						com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
//						window.add(new Html(result));
//						window.setSize(800, 600);
//						window.show();
					}
					public void onFailure(Throwable e) {
						loadingWindow.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
					}
				});
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> exportChart(HaitiCNSAPanel haitiCNSAPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				
			}
		};
	}
	
	public static SelectionChangedListener<ListItem> reinitializeCNSATool(final ComboBox<ListItem> combo, final HaitiCNSAPanel haitiCNSAPanel) {
		return new SelectionChangedListener<ListItem>() {
			public void selectionChanged(SelectionChangedEvent<ListItem> se) {
				ListItem value = combo.getValue();
				
				
				// reinitialize everything
				// menu layers
				haitiCNSAPanel.getCommandPanel().refreshMenu();
				
				// remove all the layers from open layer
				for(String key : haitiCNSAPanel.getCommandPanel().getLayersCategoryMap().keySet()) {
					removeAllLayers(haitiCNSAPanel.getCommandPanel().getLayersCategoryMap().get(key), haitiCNSAPanel);
				}


				// chart
				initializeCNSAChart(haitiCNSAPanel.getGaul0code(), value.getValue(), haitiCNSAPanel.getChartPanel(), haitiCNSAPanel.getLanguage());
				
				// map
				List<HaitiCNSAMapPanel> otherMapPanels = new ArrayList<HaitiCNSAMapPanel>();
				for(String key : haitiCNSAPanel.getMapPanelDekadsHM().keySet()) {
					otherMapPanels.add(haitiCNSAPanel.getMapPanelDekadsHM().get(key));
				}
				
				initializeCNSAMap(haitiCNSAPanel.getGaul0code(), combo, haitiCNSAPanel.getCommandPanel(), haitiCNSAPanel.getMapPanel(),  haitiCNSAPanel.getLanguage(), otherMapPanels);

//				initializeCNSAMap(haitiCNSAPanel.getGaul0code(), value.getValue(), haitiCNSAPanel.getCommandPanel(), haitiCNSAPanel.getMapPanel(),  haitiCNSAPanel.getLanguage(), otherMapPanels);
		
				// legend
				addRemoveLegends(haitiCNSAPanel, haitiCNSAPanel.getLanguage());
			}
		};
	}

	
	private static void removeAllLayers(Map<String, LayerVO> layersMap, HaitiCNSAPanel haitiCNSAPanel) {
		System.out.println("removeAllLayers: ");
		HashMap<String, LayerVO> baseLayers = new HashMap<String, LayerVO>();

		
		for(String key : layersMap.keySet()) {
			System.out.println("KEY: " + key + " | " + layersMap.get(key).getOlID()+ " | " + layersMap.get(key).isBaseLayer());
			if (!layersMap.get(key).isBaseLayer()) {
				if ( key.equals("ol_map0")) {
					System.out.println("IS MAP_PANEL: " + layersMap.get(key).getOlID()); 
					haitiCNSAPanel.getMapPanel().getHaitiOLPanel().getMapPanel().removeLayer(layersMap.get(key).getOlID());
				}
				else {
					System.out.println("IS NOT MAPPANEL: " + layersMap.get(key).getOlID()); 
					haitiCNSAPanel.getMapPanelDekadsHM().get(key).getHaitiOLPanel().getMapPanel().removeLayer(layersMap.get(key).getOlID());
				}
				
				
			}
			else {
				System.out.println("is base layer " + layersMap.get(key).getOlID());
				baseLayers.put(key, layersMap.get(key));
				
			}
		}
		System.out.println("----baseLayers : " +baseLayers);
		
		layersMap.clear();
		
		if ( !baseLayers.isEmpty() )
			layersMap.putAll(baseLayers);
		
		System.out.println("----layersMap : " +layersMap);
		
//		k = key;
//			baseLayer = layersMap.get(key);
//		}
//	}
//	
//	layersMap.clear();
//	
//	if ( k !=null )
//		layersMap.put(k, baseLayer);
		
		
//		MapPanel mapPanel = haitiCNSAPanel.getMapPanelDekadsHM().get(key)
//		String k = null;
//		LayerVO baseLayer =null;
//		for(String key : layersMap.keySet()) {
//			if (!layersMap.get(key).isBaseLayer()){
////				System.out.println("REMOVING: " + key + " | " + layersMap.get(key).getOlID());
//				mapPanel.removeLayer(layersMap.get(key).getOlID());
//			}
//			else {
//				k = key;
//				baseLayer = layersMap.get(key);
//			}
//		}
//
//		layersMap.clear();
//		
//		if ( k !=null )
//			layersMap.put(k, baseLayer);
	}
	
	
	public static SelectionListener<IconButtonEvent> exportAsImage(final HaitiCNSAPanel haitiCNSAPanel, final String language) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ClientMapView cmv = createClientMapView(haitiCNSAPanel.getCommandPanel(), haitiCNSAPanel.getMapPanel());
				final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
				loadingWindow.showLoadingBox();
				
				MapServiceEntry.getInstance().exportAsZip(cmv, true, 1024, 768, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loadingWindow.destroyLoadingBox();
						Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
					}
					public void onFailure(Throwable e) {
						loadingWindow.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
					}
				});
			}
		};
	}
	
	private static ClientMapView createClientMapView(HaitiCNSACommandPanel haitiCNSACommandPanel, HaitiCNSAMapPanel haitiCNSAMapPanel) {
		String mapDivId = haitiCNSAMapPanel.getHaitiOLPanel().getMapPanel().getMapDivId();
		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
		ClientMapView cmv = new ClientMapView();
		cmv.setBbox(bbox);
		List<LayerVO> vos = new ArrayList<LayerVO>();
		
//		System.out.println("HASHMAP: " + haitiCNSACommandPanel.getLayersMap());
		
//		for(String key : haitiCNSACommandPanel.getLayersMap().keySet()) 
//				System.out.println("2haitiCNSACommandPanel.getLayersMap(): " + key + " | " +  haitiCNSACommandPanel.getLayersMap().get(key).getLayerName());
		
		
		
	
		for(int i=0 ; i < haitiCNSACommandPanel.getMenu().getItemCount(); i++) {
			CheckMenuItem item = (CheckMenuItem) haitiCNSACommandPanel.getMenu().getItem(i);
			if ( item.isChecked() || i == haitiCNSACommandPanel.getMenu().getItemCount() -1) {
//				vos.add(haitiCNSACommandPanel.getLayersMap().get(item.getItemId()));
//				System.out.println("haitiCNSACommandPanel.get: " +item.getItemId() + " | " + haitiCNSACommandPanel.getLayersMap().get(item.getItemId()) + " | " );
			}
		}
		
		System.out.println("vos: " + vos.size() + " | " + bbox.getMaxLat() + " | " );
		int i=0;
		for (LayerVO vo : vos) {
			try {
			System.out.println("i: " + i);
			System.out.println("vo: " + vo.getLayerName());
			int opacity = 100;
			ClientGeoView cgv = new ClientGeoView();
			cgv.setBBox(bbox);
			cgv.setGetMapUrl(vo.getWmsURL());
			cgv.setLayerName(vo.getLayerName());
			cgv.setStyleName(vo.getStyleName());
			cgv.setTitle(vo.getLayerTitle());
			cgv.setOpacity(opacity);
			cgv.setFenixCode(vo.getFenixCode());
			if ( vo.getLayerID() != null)
				cgv.setLayerId(vo.getLayerID());
			cmv.addLayer(cgv);
			}
			catch (Exception e) {
			
			}
			i++;
			
		}
		return cmv;
	}
	
	public static SelectionListener<IconButtonEvent> exportAsPDF(final HaitiCNSAPanel haitiCNSAPanel, final String language) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ClientMapView cmv = createClientMapView(haitiCNSAPanel.getCommandPanel(), haitiCNSAPanel.getMapPanel());
				new ExportAsPDFForm(cmv).run();
			}
		};
	}
	

	
	private static String computeHeightRatio(String width) {
		Integer w = Integer.parseInt(width.substring(0, width.length() -2));
		w = w- 200;
		System.out.println("W-panel: " + w);
		
	
		return Integer.toString(w);
	}
	
	
	
	public static void getCurrentBoundingBox(final MapPanel mapPanel, final List<MapPanel> otherMapPanels) {
		timer = new Timer() {
			public void run() {
				ClientBBox bbox = mapPanel.getFinalBB(mapPanel.getMapDivId());
				
				ClientBBox previousBBox = new ClientBBox("4326", bbox.getMinLat(), bbox.getMaxLat(), bbox.getMinLon(),bbox.getMaxLon());
//				System.out.println("previous");
//				System.out.println(previousBBox.getMinLat());
//				System.out.println(previousBBox.getMinLon());
//				System.out.println(previousBBox.getMaxLat());
//				System.out.println(previousBBox.getMaxLon());
//			
				ClientBBox currentBoundingBox2 = mapPanel.getCurrentOpenLayerBoundingBox(mapPanel.getMapDivId());		
//				System.out.println("-> " + currentBoundingBox2.getMinLat());
//				System.out.println("-> " + currentBoundingBox2.getMinLon());
//				System.out.println("-> " + currentBoundingBox2.getMaxLat());
//				System.out.println("-> " + currentBoundingBox2.getMaxLon());
				
				
				ClientBBox currentBoundingBox = mapPanel.getFinalBB(mapPanel.getMapDivId());
//				System.out.println("current");
				
//				ClientBBox currentBoundingBox = mapPanel.getCurrentBoundingBox(mapPanel.getMapDivId());
//				System.out.println("-> " + currentBoundingBox.getMinLat());
//				System.out.println("-> " + currentBoundingBox.getMinLon());
//				System.out.println("-> " + currentBoundingBox.getMaxLat());
//				System.out.println("-> " + currentBoundingBox.getMaxLon());

				//				// check
				if ( previousBBox.getMinLat() != currentBoundingBox.getMinLat() || previousBBox.getMinLon() != currentBoundingBox.getMinLon() || 
						previousBBox.getMaxLat() != currentBoundingBox.getMaxLat() || previousBBox.getMaxLon() != currentBoundingBox.getMaxLon()) {
					
					for(MapPanel otherMapPanel : otherMapPanels) {
						otherMapPanel.zoomToBBox(currentBoundingBox);
					}
				}
				
			}
		};
		timer.scheduleRepeating(1000);

	
	}
	
	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}
//	}
}