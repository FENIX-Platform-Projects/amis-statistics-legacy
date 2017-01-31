package org.fao.fenix.web.modules.haiti.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.haiti.client.view.HaitiCropCalendarPanel;
import org.fao.fenix.web.modules.haiti.client.view.HaitiHotLinkWindow;
import org.fao.fenix.web.modules.haiti.client.view.HaitiLayerMetadataWindow;
import org.fao.fenix.web.modules.haiti.client.view.HaitiLayersPanel;
import org.fao.fenix.web.modules.haiti.client.view.HaitiMapTabItem;
import org.fao.fenix.web.modules.haiti.client.view.HaitiMapsGalleryPanel;
import org.fao.fenix.web.modules.haiti.client.view.HaitiOLPanel;
import org.fao.fenix.web.modules.haiti.client.view.HaitiRSSPanel;
import org.fao.fenix.web.modules.haiti.common.services.HaitiServiceEntry;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiti.common.vo.WMSModelData;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever;
import org.fao.fenix.web.modules.map.client.view.form.ExportAsPDFForm;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class HaitiController {

	public static SelectionListener<MenuEvent> sizeAndLanguage(final String height, final String width, final String language) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				Window.open("http://fenix.fao.org:8080/fenix-web/org.fao.fenix.web.modules.haiti.Haiti/Haiti.html" +
							"?country=108" +
							"&width=" + width + 
							"&height=" + height + 
							"&language=" + language, 
							"_blank", 
							"resizable=yes,scrollbars=yes");
			}
		};
	}
	
	public static void fillDatasetScrollerMenu(final String gaulCode, final String language, final Menu datasetScrollMenu) {
		HaitiServiceEntry.getInstance().findAllCountryProvinces(gaulCode, language, new AsyncCallback<Map<String, String>>() {
			public void onSuccess(Map<String, String> result) {
				TreeMap<String, String> out = sortByKeys(result);
				for (String code : out.keySet()) {
					MenuItem mi = new MenuItem(result.get(code));
					mi.setIconStyle("marker");
					mi.addSelectionListener(callHotlinkWindow(code, language));
					datasetScrollMenu.add(mi);
				}
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	private static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
	private static SelectionListener<MenuEvent> callHotlinkWindow(final String gaulCode, final String language) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				HaitiHotLinkWindow w = new HaitiHotLinkWindow();
				w.build(gaulCode, language);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> showMetadataLayer(final LayerVO layerVO, final String language) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				new HaitiLayerMetadataWindow(layerVO, language);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> exportAsPDF(final HaitiMapTabItem haitiMapTabItem, final String language) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ClientMapView cmv = createClientMapView(haitiMapTabItem);
				new ExportAsPDFForm(cmv).run();
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> exportAsImage(final HaitiMapTabItem haitiMapTabItem, final String language) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ClientMapView cmv = createClientMapView(haitiMapTabItem);
				final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
				loadingWindow.showLoadingBox();
//				MapServiceEntry.getInstance().exportAsImage(cmv, new AsyncCallback<String>() {
//					public void onSuccess(String result) {
//						loadingWindow.destroyLoadingBox();
//						Window.open("/fenix-web/export/map/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//					}
//					public void onFailure(Throwable e) {
//						loadingWindow.destroyLoadingBox();
//						FenixAlert.error("ERROR", e.getMessage());
//					}
//				});
				
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
	
	private static ClientMapView createClientMapView(HaitiMapTabItem haitiMapTabItem) {
		String mapDivId = haitiMapTabItem.getHaitiOLPanel().getMapPanel().getMapDivId();
		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
		ClientMapView cmv = new ClientMapView();
		cmv.setBbox(bbox);
		List<LayerVO> vos = getSortedLayers(haitiMapTabItem);
		for (LayerVO vo : vos) {
			int opacity = getOpacity(haitiMapTabItem.getHaitiLayersPanel(), vo.getOlID());
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
		return cmv;
	}
	
	private static Integer getOpacity(HaitiLayersPanel haitiLayersPanel, Long id) {
		Map<Long, HorizontalPanel> layerPanelMap = haitiLayersPanel.getLayerPanelsMap();
		HorizontalPanel layerPanel = layerPanelMap.get(id);
		return ((Slider)layerPanel.getData("slider")).getValue();
	}
	
	private static List<LayerVO> getSortedLayers(HaitiMapTabItem haitiMapTabItem) {
		List<LayerVO> vos = new ArrayList<LayerVO>();
		ContentPanel layersPanel = haitiMapTabItem.getHaitiLayersPanel().getLayersPanel();
		
		Integer legendIndex = findExistingLegendIndex(haitiMapTabItem);
		if (legendIndex != null) {
			haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().remove(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getWidget(legendIndex));
		}
		for (int i = layersPanel.getItemCount() - 1 ; i >= 0 ; i--) {
			HorizontalPanel layerPanel = (HorizontalPanel)layersPanel.getWidget(i);
			boolean isOn = ((CheckBox)layerPanel.getData("checkbox")).getValue();
			if (isOn) {
				String layerID = layerPanel.getData("layerID");
				LayerVO vo = haitiMapTabItem.getHaitiLayersPanel().getLayersMap().get(Long.parseLong(layerID));
				vos.add(vo);
			}
		}
		return vos;
	}
	
	public static void fillNewsPanel(final HaitiRSSPanel haitiRSSPanel, List<String> urls) {
		HaitiServiceEntry.getInstance().getLatestNews(urls, "", 25, new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> news) {
				haitiRSSPanel.addNews(news);
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	public static void createNewsTabPanel(final HaitiRSSPanel haitiRSSPanel, Map<String, String> sourceUrlMap, final String width, final String height) {
		HaitiServiceEntry.getInstance().getLatestNews(sourceUrlMap, "", 50, new AsyncCallback<Map<String, List<String>>>() {
			public void onSuccess(Map<String, List<String>> sourceNewsMap) {
				haitiRSSPanel.buildTabPanel(sourceNewsMap, width, height);
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	public static SelectionListener<ButtonEvent> refreshNews(final HaitiRSSPanel haitiRSSPanel, final Map<String, String> sourceUrlMap, final String width, final String height) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createNewsTabPanel(haitiRSSPanel, sourceUrlMap, width, height);
			}
		};
	}
	
	public static Listener<BaseEvent> hotLinkMetadata(final VerticalPanel metadataPanel, final ListView<ResourceVO> view, final String language) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				ResourceVO vo = view.getSelectionModel().getSelectedItem();
				hotLinkMetadataAgent(metadataPanel, vo, language);
			}
		};
	}
	
	public static void hotLinkMetadataAgent(VerticalPanel metadataPanel, ResourceVO vo, String language) {
		metadataPanel.removeAll();
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).title() + "</b>"));
		metadataPanel.add(new HTML(vo.getTitle()));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).resourceType() + "</b>"));
		metadataPanel.add(new HTML(vo.getResourceType()));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).abstractAbstract() + "</b>"));
		metadataPanel.add(new HTML(vo.getAbstractAbstract()));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).dateLastUpdate() + "</b>"));
		metadataPanel.add(new HTML(String.valueOf(vo.getDateLastUpdate())));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).source() + "</b>"));
		metadataPanel.add(new HTML(vo.getSource()));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).provider() + "</b>"));
		metadataPanel.add(new HTML(vo.getProvider()));
		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).keywords() + "</b>"));
		metadataPanel.add(new HTML(vo.getKeywords()));
//		metadataPanel.add(new HTML("<b>" + HaitiLangEntry.getInstance(language).category() + "</b>"));
//		metadataPanel.add(new HTML(vo.getCategories()));
		metadataPanel.getLayout().layout();
	}
	
	public static SelectionListener<ButtonEvent> openResource(final ListView<ResourceVO> view) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ResourceVO vo = view.getSelectionModel().getSelectedItem();
				if (vo.getResourceType().equalsIgnoreCase("TextView"))
					ResourceOpener.openTextViewer(vo.getResourceId(), false);
				else if (vo.getResourceType().equalsIgnoreCase("Dataset"))
					new TableWindow().build(vo.getResourceId(), false, false, vo.getTitle());
				else if (vo.getResourceType().equalsIgnoreCase("ChartView"))
					new ChartViewer(String.valueOf(vo.getResourceId()));
			}
		};
	}
	
	public static void fillMapStore(String gaul0code, final ComboBox<LayerVO> mapList, final ListStore<LayerVO> mapStore) { 
		try {
			HaitiServiceEntry.getInstance().loadPredifinedMapList(gaul0code, new AsyncCallback<List<ClientGeoView>>() {
				public void onSuccess(List<ClientGeoView> result) {
					for (ClientGeoView cgv : result) {
						LayerVO vo = new LayerVO(cgv.getTitle(), cgv.getClientId(), cgv.getLayerId(), cgv.getOpacity(), cgv.getLayerType().name(), cgv.getLayerName());
						vo.setWmsURL(cgv.getGetMapUrl());
						vo.setStyleName(cgv.getStyleName());
						vo.setFenixCode(cgv.getFenixCode());
						mapStore.add(vo);
					}
					
					for (int i = 0 ; i < mapStore.getCount() ; i++) 
						if (mapStore.getAt(i).getLayerTitle().equalsIgnoreCase("Landuse")) 
							mapList.setValue(mapStore.getAt(i));
					
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error("ERROR", e.getMessage());
		}
	}
	
	public static SelectionChangedListener<LayerVO> showPredifinedMap(final String language, final HaitiMapTabItem haitiMapTabItem) {
		
		return new SelectionChangedListener<LayerVO>() {
			
			public void selectionChanged(SelectionChangedEvent<LayerVO> se) {
				
				// remove legend if any
				Integer legendIndex = findExistingLegendIndex(haitiMapTabItem);
				if (legendIndex != null) {
					haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().remove(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getWidget(legendIndex));
				}
				
				final HaitiLayersPanel haitiLayersPanel = haitiMapTabItem.getHaitiLayersPanel();
				final String gaul0code = haitiLayersPanel.getGaulList().getSelection().get(0).getGaulCode();
				final HaitiOLPanel haitiOLPanel = haitiMapTabItem.getHaitiOLPanel(); 
				final MapPanel mapPanel = haitiOLPanel.getMapPanel();
				final String predefinedMapName = haitiLayersPanel.getMapList().getSelection().get(0).getLayerTitle();
				
//				try {
				
//				System.out.println("predefinedMapName: "+   predefinedMapName);
					
					HaitiServiceEntry.getInstance().getHaitiLayers("HaitiFeatureTypes", predefinedMapName, new AsyncCallback<List<ClientGeoView>>() {
						
						public void onSuccess(List<ClientGeoView> cgvlist) {
							
							removeAllAgent(mapPanel, haitiLayersPanel);
							final ClientMapView mapView = haitiOLPanel.getMapView();
							ClientBBox bbox = null;
							List<LayerVO> layerVOs = new ArrayList<LayerVO>();
							for (ClientGeoView clientGeoView : cgvlist) { 
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
								mapPanel.addLayer(clientGeoView);
//								if ( vo.getFenixCode().equals("HAITI_GAUL0") || vo.getFenixCode().equals("HAITI_GAUL1") || vo.getFenixCode().equals("HAITI_GAUL2") || vo.getFenixCode().equals("HAITI_GAUL3") || vo.getFenixCode().equals("HAITI_GAUL4")) {
//									ClientGeoView hotlinkGV = new ClientGeoView();
//									hotlinkGV = clientGeoView.copy();
//									hotlinkGV.setTitle(clientGeoView.getTitle() +  " ("+HaitiLangEntry.getInstance(language).hotlink() + ")");
//									hotlinkGV.setStyleName(vo.getFenixCode() + "_hotlink");
//									hotlinkGV.setHidden(true);
//									hotlinkGV.setFenixCode(clientGeoView.getFenixCode());
//									hotlinkGV.setAbstractAbstract(clientGeoView.getAbstractAbstract());
//									hotlinkGV.setSource(clientGeoView.getSource());						
//									hotlinkGV.setSourceContact(clientGeoView.getSourceContact());
//									hotlinkGV.setProvider(clientGeoView.getProvider());
//									hotlinkGV.setProviderContact(clientGeoView.getProviderContact());
//									
//									LayerVO hotlinkVO = new LayerVO(hotlinkGV.getTitle(), hotlinkGV.getClientId(), hotlinkGV.getLayerId(), hotlinkGV.getOpacity(), hotlinkGV.getLayerType().name(), hotlinkGV.getLayerName());
//									hotlinkVO.setIsHidden(true);
//									hotlinkVO.setWmsURL(hotlinkGV.getGetMapUrl());
//									hotlinkVO.setStyleName(hotlinkGV.getStyleName());
//									hotlinkVO.setFenixCode(hotlinkGV.getFenixCode());
//									hotlinkVO.setAbstractAbstract(hotlinkGV.getAbstractAbstract());
//									hotlinkVO.setSource(hotlinkGV.getSource());
//									hotlinkVO.setSourceContact(hotlinkGV.getSourceContact());
//									hotlinkVO.setProvider(hotlinkGV.getProvider());
//									hotlinkVO.setProviderContact(hotlinkGV.getProviderContact());
//									hotlinkVO.setClientBBox(hotlinkGV.getBBox());									
//									layerVOs.add(hotlinkVO);
//									mapPanel.addLayer(hotlinkGV);
//								}
								
								
							}

							for (int i = 0; i < layerVOs.size(); i++) {
								haitiLayersPanel.addLayerPanelOnTop(layerVOs.get(i), haitiMapTabItem, language);
							}
//							mapView.setBbox(bbox);
//							mapPanel.createMapHaiti(mapView, language);
							
							zoom(gaul0code, mapPanel);
							
							//set the Map title
							haitiMapTabItem.getCenter().setHeading(HaitiLangEntry.getInstance().currentMapView() + ": " + predefinedMapName);
						}
						
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
						
					});
					
//				} catch (FenixGWTException e) {
//					FenixAlert.error("ERROR", e.getMessage());
//				}
			}
		};
	}
	
	public static void fillWMSStore(final ListStore<WMSModelData> wmsStore) {
		HaitiServiceEntry.getInstance().findAllWMSServers(new AsyncCallback<List<WMSModelData>>() {
			public void onSuccess(List<WMSModelData> result) {
				for (WMSModelData m : result)
					wmsStore.add(m);
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	public static void fillWMSStore(final ComboBox<WMSModelData> wmsList, final ListStore<WMSModelData> wmsStore) {
		HaitiServiceEntry.getInstance().findAllWMSServers(new AsyncCallback<List<WMSModelData>>() {
			public void onSuccess(List<WMSModelData> result) {
				for (WMSModelData m : result) 
					wmsStore.add(m);
				wmsList.setValue(wmsStore.getAt(0));
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	public static SelectionListener<IconButtonEvent> moveLayerUp(final MapPanel mapPanel, final long gvid, final HaitiMapTabItem haitiMapTabItem) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				
				// remove legend if any
				Integer legendIndex = findExistingLegendIndex(haitiMapTabItem);
				if (legendIndex != null) {
					haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().remove(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getWidget(legendIndex));
				}
				
				mapPanel.moveLayerDown(gvid);
				ContentPanel layersPanel = haitiMapTabItem.getHaitiLayersPanel().getLayersPanel();
				for (int i = layersPanel.getItemCount() - 2 ; i > 0  ; i--) {
					HorizontalPanel layerPanel = (HorizontalPanel)layersPanel.getWidget(i);
					long layerPanelID = Long.parseLong((String)(layerPanel.getData("layerID")));
					if (layerPanelID == gvid) {
						layersPanel.insert(layerPanel, i-1);
						layersPanel.getLayout().layout();
						break;
					}
				}
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> moveLayerDown(final MapPanel mapPanel, final long gvid, final HaitiMapTabItem haitiMapTabItem) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				
				// remove legend if any
				Integer legendIndex = findExistingLegendIndex(haitiMapTabItem);
				if (legendIndex != null) {
					haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().remove(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getWidget(legendIndex));
				}
				
				mapPanel.moveLayerUp(gvid);
				ContentPanel layersPanel = haitiMapTabItem.getHaitiLayersPanel().getLayersPanel();
				for (int i = 0 ; i < layersPanel.getItemCount() - 2 ; i++) {
					HorizontalPanel layerPanel = (HorizontalPanel)layersPanel.getWidget(i);
					long layerPanelID = Long.parseLong((String)(layerPanel.getData("layerID")));
					if (layerPanelID == gvid) {
						layerPanel = (HorizontalPanel)layersPanel.getWidget(i+1);
						layersPanel.insert(layerPanel, i);
						layersPanel.getLayout().layout();
						break;
					}
				}
			}
		};
	}
	
	public static Listener<BaseEvent> changeTrasparency(final MapPanel mapPanel, final long gvid, final Slider slider) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				double opacityValue = (double)slider.getValue();
				double newOpacity = (opacityValue - 10) / 100;
				if (newOpacity >= 0 && newOpacity <= 100) 
					mapPanel.setLayerOpacity(gvid, (float)newOpacity);
			};
		};
	}
	
	public static SelectionListener<IconButtonEvent> refreshLayer(final MapPanel mapPanel, final long gvid) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				mapPanel.redrawLayer(gvid);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> removeAll(final MapPanel mapPanel, final HaitiLayersPanel haitiLayersPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeAllAgent(mapPanel, haitiLayersPanel);
			}
		};
	}
	
	private static void removeAllAgent(final MapPanel mapPanel, final HaitiLayersPanel haitiLayersPanel) {
		List<Long> gvids = new ArrayList<Long>();
		for (Long gvid : haitiLayersPanel.getLayersMap().keySet()) {
			LayerVO layerVo = haitiLayersPanel.getLayersMap().get(gvid);
			if ( !layerVo.isBaseLayer()) 
				gvids.add(gvid);
				
		}
		
		for(Long gvid : gvids) {
			mapPanel.removeLayer(gvid);
			HorizontalPanel layerPanel = haitiLayersPanel.getLayerPanelsMap().get(gvid);
			Component component = layerPanel;
			haitiLayersPanel.getLayersPanel().remove(component);
			haitiLayersPanel.getLayerPanelsMap().remove(gvid);
			haitiLayersPanel.getLayersMap().remove(gvid);
			haitiLayersPanel.getLayersDBIDMap().remove(gvid);		
		}
//		haitiLayersPanel.getLayersMap().clear();
//		haitiLayersPanel.getLayersMap().put(new Long(101), layerVo);
		
		
//		haitiLayersPanel.getLayerPanelsMap().clear();	
//		haitiLayersPanel.getLayersMap().clear();
//		haitiLayersPanel.getLayersDBIDMap().clear();
	}
	
	public static SelectionListener<IconButtonEvent> removeLayer(final MapPanel mapPanel, final long gvid, final HaitiLayersPanel haitiLayersPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				HorizontalPanel layerPanel = haitiLayersPanel.getLayerPanelsMap().get(gvid);
				mapPanel.removeLayer(gvid);
				Component component = layerPanel;
				haitiLayersPanel.getLayersPanel().remove(component);
				haitiLayersPanel.getLayerPanelsMap().remove(gvid);
				haitiLayersPanel.getLayersMap().remove(gvid);
				haitiLayersPanel.getLayersDBIDMap().remove(gvid);
			}
		};
	}
	
	public static ClickHandler showLayer(final MapPanel mapPanel, final long gvid, final boolean visible) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				mapPanel.setLayerVisibility(gvid, visible);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showAllLayers(final MapPanel mapPanel, final HaitiLayersPanel haitiLayersPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (Long gvid : haitiLayersPanel.getLayersMap().keySet()) {
					mapPanel.setLayerVisibility(gvid, true);
					HorizontalPanel layerPanel = haitiLayersPanel.getLayerPanelsMap().get(gvid);
					CheckBox checkbox = (CheckBox)layerPanel.getData("checkbox");
					checkbox.setValue(true);
//					checkbox.setText("on");
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> hideAllLayers(final MapPanel mapPanel, final HaitiLayersPanel haitiLayersPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (Long gvid : haitiLayersPanel.getLayerPanelsMap().keySet()) {
					mapPanel.setLayerVisibility(gvid, false);
					HorizontalPanel layerPanel = haitiLayersPanel.getLayerPanelsMap().get(gvid);
					CheckBox checkbox = (CheckBox)layerPanel.getData("checkbox");
					checkbox.setValue(false);
//					checkbox.setText("off");
				}
			}
		};
	}
	
	public static ClickHandler showHideLayer(final MapPanel mapPanel, final long gvid, final CheckBox checkBox) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				mapPanel.setLayerVisibility(gvid, checkBox.getValue());
//				if (checkBox.getValue())
//					checkBox.setText("on");
//				else
//					checkBox.setText("off");
			}
		};
	}
	
	public static void hideLayer(final MapPanel mapPanel, final long gvid, final CheckBox checkBox) {
		checkBox.setValue(false);
	}
	
	public static ClickHandler selectLayer(final HaitiMapTabItem haitiMapTabItem, final LayerVO selectedLayer, final HorizontalPanel selectedLayerPanel) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				MapPanel mapPanel = haitiMapTabItem.getHaitiOLPanel().getMapPanel();
				mapPanel.setSelectedLayer(selectedLayer);
				showLegend(haitiMapTabItem, selectedLayer);
				highlightSelectedLayer(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel(), selectedLayer.getOlID());
			}
		};
	}
	
	private static void highlightSelectedLayer(ContentPanel layersPanel, Long layerID) {
		for (int i = 0 ; i < layersPanel.getItemCount() ; i++) {
			try {
				HorizontalPanel panel = (HorizontalPanel)layersPanel.getWidget(i);
				Hyperlink label = (Hyperlink)panel.getData("hyperlink");
				Long actualLayerID = Long.parseLong(((String)panel.getData("layerID")));
				if (actualLayerID.longValue() == layerID.longValue())
					highlightHyperlink(label);
				else
					downHyperlink(label);
			} catch (ClassCastException e) {
				
			}
		}
	}
	
	private static void highlightHyperlink(Hyperlink label) {
		String style = label.getStyleName();
		if (style.equalsIgnoreCase("gwt-Hyperlink") || style.equalsIgnoreCase("gwt-Hyperlink gwt-Hyperlink-PLAIN")) {
			label.removeStyleDependentName("PLAIN");
			label.addStyleName("gwt-Hyperlink-BOLD");
			label.addStyleDependentName("BOLD");
		}
	}
	
	// downlight???
	private static void downHyperlink(Hyperlink label) {
		String style = label.getStyleName();
		if (style.equalsIgnoreCase("gwt-Hyperlink gwt-Hyperlink-BOLD")) {
			label.removeStyleDependentName("BOLD");
			label.addStyleName("gwt-Hyperlink-PLAIN");
			label.addStyleDependentName("PLAIN");
		}
	}
	
	public static SelectionListener<IconButtonEvent> addLegendPanel(final HaitiMapTabItem haitiMapTabItem, final LayerVO vo, final HorizontalPanel selectedLayerPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addLegendPanelAgent(haitiMapTabItem, vo, selectedLayerPanel);
			}
		};
	}
	
	private static void addLegendPanelAgent(HaitiMapTabItem haitiMapTabItem, LayerVO vo, HorizontalPanel selectedLayerPanel) {
		Integer legendIndex = findExistingLegendIndex(haitiMapTabItem);
		if (legendIndex == null) {
			String imageHTML = getImageHTML(vo);
			ContentPanel layersPanel = haitiMapTabItem.getHaitiLayersPanel().getLayersPanel();
			for (int i = 0 ; i < layersPanel.getItemCount() ; i++) {
				HorizontalPanel hz = (HorizontalPanel)layersPanel.getWidget(i);
				Long layerID = Long.parseLong(((String)hz.getData("layerID")));
				if (layerID.longValue() == vo.getOlID().longValue()) {
					haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().insert(buildLegendPanel(vo, imageHTML, (1 + i)), (1 + i));
					haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getLayout().layout();
					break;
				}
			}
		} else {
			haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().remove(haitiMapTabItem.getHaitiLayersPanel().getLayersPanel().getWidget(legendIndex));
//			addLegendPanelAgent(haitiMapTabItem, vo, selectedLayerPanel);
		}
	}
	
	private static Integer findExistingLegendIndex(HaitiMapTabItem haitiMapTabItem) {
		ContentPanel layersPanel = haitiMapTabItem.getHaitiLayersPanel().getLayersPanel();
		for (int i = 0 ; i < layersPanel.getItemCount() ; i++) {
			try {
				VerticalPanel vp = (VerticalPanel)layersPanel.getWidget(i);
				int legendIndex = Integer.parseInt(((String)vp.getData("legendIndex")));
				return legendIndex;
			} catch (ClassCastException e) {
				
			}
		}
		return null;
	}
	
	private static VerticalPanel buildLegendPanel(LayerVO vo, String imageHTML, int contentPanelIndex) {
		VerticalPanel vp = new VerticalPanel();
		vp.setData("legendIndex", String.valueOf(contentPanelIndex));
		vp.setSpacing(10);
		vp.setBorders(true);
		vp.add(new HTML("<div style='font-size: 8pt; '><i>Legend of " + vo.getLayerTitle() + ": </i></div>"));
		vp.add(new HTML("<img src='" + imageHTML + "'/>"));
		return vp;
	}
	
	private static String getImageHTML(LayerVO vo) {
		String geoserverURL = (vo.getWmsURL().indexOf("wms") > 0 ? vo.getWmsURL().substring(0, vo.getWmsURL().indexOf("wms")) : vo.getWmsURL());
		String layerName = vo.getLayerName();
		String styleName = vo.getStyleName();
		String url = "";
		if ( vo.getLegendUrl() != null ) {
			url = vo.getLegendUrl();
		} else{
			url = geoserverURL + 
		     	  "wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER=" + 
		     	  layerName + 
		     	  "&STYLE=" + 
		     	  styleName + 
		     	  "&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP=" + 
		     	  0;
		}
		return url;
	}
	
	private static void showLegend(HaitiMapTabItem haitiMapTabItem, LayerVO vo) {
		String geoserverURL = (vo.getWmsURL().indexOf("wms") > 0 ? vo.getWmsURL().substring(0, vo.getWmsURL().indexOf("wms")) : vo.getWmsURL());
		String layerName = vo.getLayerName();
		String layerTitle = vo.getLayerTitle();
		String styleName = vo.getStyleName();
		String url = new String();
		if ( vo.getLegendUrl() != null ) {		
			url = vo.getLegendUrl();
		}
		else{
			url = geoserverURL + 
					     "wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER=" + 
					     layerName + 
					     "&STYLE=" + 
					     styleName + 
					     "&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP=" + 
					     0;
		}
	}
	
	public static SelectionChangedListener<GaulModelData> addLayers(final ComboBox<GaulModelData> gaulList, final HaitiMapTabItem haitiMapTabItem, final String language) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				addLayersAgent(gaulList, haitiMapTabItem, language);
			}
		};
	}
	
	public static void addLayersAgent(ComboBox<GaulModelData> gaulList, final HaitiMapTabItem haitiMapTabItem, final String language) {
		
		final HaitiOLPanel haitiOLPanel = haitiMapTabItem.getHaitiOLPanel();
		final ClientMapView mapView = haitiOLPanel.getMapView();
		final MapPanel mapPanel = haitiOLPanel.getMapPanel();
		final HaitiLayersPanel haitiLayersPanel = haitiMapTabItem.getHaitiLayersPanel();
		
		final String gaul0code = getGaulCode(gaulList);
		final String gaul0label = getGaulLabel(gaulList);
		
		MapServiceEntry.getInstance().findAllCountryLayers(gaul0code, new AsyncCallback<List<Long>>() {
			
			public void onSuccess(List<Long> layerIds) {
				
				MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {

					public void onSuccess(List<ClientGeoView> cgvlist) {
						mapView.setTitle(gaul0label + " Map View");
						ClientBBox bbox = null;
						List<LayerVO> layerVOs = new ArrayList<LayerVO>();
						for (ClientGeoView clientGeoView : cgvlist) { 
							mapView.addLayer(clientGeoView);
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
						}
						for (int i = layerVOs.size() - 1 ; i >= 0 ; i--) {
							if ( i == 0) {
								layerVOs.get(i).setIsBaseLayer(true);
								haitiLayersPanel.addLayerPanel(layerVOs.get(i), haitiMapTabItem, language, true);
							}
							else
								haitiLayersPanel.addLayerPanel(layerVOs.get(i), haitiMapTabItem, language, false);
						}
						mapView.setBbox(bbox);
						mapPanel.createMapHaiti(mapView, language);
						
						// zoom to layer
						zoom(gaul0code, mapPanel);
					}

					public void onFailure(Throwable e) {
						FenixAlert.error("ERROR", e.getMessage());
					}
					
				});
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
			
		});
		
	}
	
	public static void addLayersAgent(final String gaul0code, final HaitiMapTabItem haitiMapTabItem, final String language) {
		
		final HaitiOLPanel haitiOLPanel = haitiMapTabItem.getHaitiOLPanel();
		final ClientMapView mapView = haitiOLPanel.getMapView();
		final MapPanel mapPanel = haitiOLPanel.getMapPanel();
		final HaitiLayersPanel haitiLayersPanel = haitiMapTabItem.getHaitiLayersPanel();
		
//		List<String> featureTypeTitles = new ArrayList<String>();
//		featureTypeTitles.add("Haiti Rivers");
//		featureTypeTitles.add("World GAUL0");
		
		
		HaitiServiceEntry.getInstance().getHaitiLayers("HaitiFeatureTypes", "START", new AsyncCallback<List<ClientGeoView>>() {
			
			public void onSuccess(List<ClientGeoView> cgvlist) {
				mapView.setTitle("Area of Interest");
				ClientBBox bbox = null;
				List<LayerVO> layerVOs = new ArrayList<LayerVO>();
				for (ClientGeoView clientGeoView : cgvlist) { 
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
					mapView.addLayer(clientGeoView);
//					if ( vo.getFenixCode().equals("HAITI_GAUL0") || vo.getFenixCode().equals("HAITI_GAUL1") || vo.getFenixCode().equals("HAITI_GAUL2") || vo.getFenixCode().equals("HAITI_GAUL3") || vo.getFenixCode().equals("HAITI_GAUL4")) {
//						ClientGeoView hotlinkGV = new ClientGeoView();
//						hotlinkGV = clientGeoView.copy();
//						hotlinkGV.setTitle(clientGeoView.getTitle() + " ("+ HaitiLangEntry.getInstance(language).hotlink()+ ")");
//						hotlinkGV.setStyleName(vo.getFenixCode() + "_hotlink");
//						hotlinkGV.setHidden(true);	
//						hotlinkGV.setFenixCode(clientGeoView.getFenixCode());
//						hotlinkGV.setAbstractAbstract(clientGeoView.getAbstractAbstract());
//						hotlinkGV.setSource(clientGeoView.getSource());						
//						hotlinkGV.setSourceContact(clientGeoView.getSourceContact());
//						hotlinkGV.setProvider(clientGeoView.getProvider());
//						hotlinkGV.setProviderContact(clientGeoView.getProviderContact());
//						
//
//						LayerVO hotlinkVO = new LayerVO(hotlinkGV.getTitle(), hotlinkGV.getClientId(), hotlinkGV.getLayerId(), hotlinkGV.getOpacity(), hotlinkGV.getLayerType().name(), hotlinkGV.getLayerName());
//						hotlinkVO.setIsHidden(true);
//						hotlinkVO.setWmsURL(hotlinkGV.getGetMapUrl());
//						hotlinkVO.setStyleName(hotlinkGV.getStyleName());
//						hotlinkVO.setFenixCode(hotlinkGV.getFenixCode());
//						hotlinkVO.setAbstractAbstract(hotlinkGV.getAbstractAbstract());
//						hotlinkVO.setSource(hotlinkGV.getSource());
//						hotlinkVO.setSourceContact(hotlinkGV.getSourceContact());
//						hotlinkVO.setProvider(hotlinkGV.getProvider());
//						hotlinkVO.setProviderContact(hotlinkGV.getProviderContact());
//						hotlinkVO.setClientBBox(hotlinkGV.getBBox());
//						layerVOs.add(hotlinkVO);
//						mapView.addLayer(hotlinkGV);
//						
//					}
				}
				for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
					if ( i == 0) {
						layerVOs.get(i).setIsBaseLayer(true);
						haitiLayersPanel.addLayerPanel(layerVOs.get(i), haitiMapTabItem, language, true);
					}
					else
						haitiLayersPanel.addLayerPanel(layerVOs.get(i), haitiMapTabItem, language, false);
					
				}
				
				mapView.setBbox(bbox);
				mapPanel.createMapHaiti(mapView, language);
				zoom(gaul0code, mapPanel);
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
			
		});
		
//		MapServiceEntry.getInstance().findAllCountryLayers(gaul0code, new AsyncCallback<List<Long>>() {
//			
//			public void onSuccess(List<Long> layerIds) {
//				
//				MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {
//
//					public void onSuccess(List<ClientGeoView> cgvlist) {
//						mapView.setTitle("Area of Interest");
//						ClientBBox bbox = null;
//						List<LayerVO> layerVOs = new ArrayList<LayerVO>();
//						for (ClientGeoView clientGeoView : cgvlist) { 
//							mapView.addLayer(clientGeoView);
//							if (bbox == null)
//								bbox = clientGeoView.getBBox();
//							else
//								bbox = bbox.union(clientGeoView.getBBox());
//							LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
//							vo.setWmsURL(clientGeoView.getGetMapUrl());
//							vo.setStyleName(clientGeoView.getStyleName());
//							vo.setFenixCode(clientGeoView.getFenixCode());
//							layerVOs.add(vo);
//						}
//						for (int i = layerVOs.size() - 1 ; i >= 0 ; i--)
//							haitiLayersPanel.addLayerPanel(layerVOs.get(i), haitiMapTabItem, language);
//						mapView.setBbox(bbox);
//						mapPanel.createMapHaiti(mapView, language);
//						
//						// zoom to layer
//						zoom(gaul0code, mapPanel);
//					}
//
//					public void onFailure(Throwable e) {
//						FenixAlert.error("ERROR", e.getMessage());
//					}
//					
//				});
//			}
//			
//			public void onFailure(Throwable e) {
//				FenixAlert.error("ERROR", e.getMessage());
//			}
//			
//		});
		
	}
	
	private static void zoom(String gaul0code, final MapPanel mapPanel) {
		MapServiceEntry.getInstance().getCountryExtents(gaul0code, new AsyncCallback<ClientBBox>() {
			public void onSuccess(ClientBBox bbox) {
				mapPanel.zoomToBBox(bbox);
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	private static String getGaulCode(ComboBox<GaulModelData> gaulList) {
		for (int i = 0 ; i < gaulList.getSelection().size() ; i++)
			return gaulList.getSelection().get(i).getGaulCode();
		return null;
	}
	
	private static String getGaulLabel(ComboBox<GaulModelData> gaulList) {
		for (int i = 0 ; i < gaulList.getSelection().size() ; i++)
			return gaulList.getSelection().get(i).getGaulLabel();
		return null;
	}

	
	public static SelectionChangedListener<WMSModelData> changeWms(final HaitiLayersPanel haitiLayerPanel, final String language) {
		return new SelectionChangedListener<WMSModelData>() {
			public void selectionChanged(SelectionChangedEvent<WMSModelData> se) {				
				final WMSModelData wms = haitiLayerPanel.getWmsList().getValue();
				final ListStore<LayerVO> layerStore = haitiLayerPanel.getLayerStore();
				final ComboBox<LayerVO> layerList = haitiLayerPanel.getLayerList();
				
				layerStore.removeAll();
				layerList.reset();
//				if ( !wms.isInternal() ) {
					if ( haitiLayerPanel.getExternalLayersCached().containsKey(wms.getWmsURL())) {
						layerStore.add(haitiLayerPanel.getExternalLayersCached().get(wms.getWmsURL()));
						layerList.setValue(layerStore.getAt(0));
					}
					else {
//						final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance().retrievingWMSinfo(), I18N.print().pleaseWait(), I18N.print().loading());
//						loadingWindow.showLoadingBox();
						
						HaitiServiceEntry.getInstance().getLayers(wms.getWmsURL(), wms.isInternal(), new ClientBBox(), false,  new AsyncCallback<List<ClientGeoView>>() {
							public void onSuccess(List<ClientGeoView> clientGeoViews) {
//								loadingWindow.destroyLoadingBox();
								if ( clientGeoViews.isEmpty()) {
//									FenixAlert.info(HaitiLangEntry.getInstance(language).retrievingWMSinfo(), wms.getWmsLabel() + " " + HaitiLangEntry.getInstance(language).serverDown());
								}
								else {
									for(ClientGeoView c : clientGeoViews) {
										
										LayerVO layerVo = new LayerVO(c.getClientId(), c.getTitle(), c.getLayerName(), c.getStyleName(), c.getGetMapUrl());
										layerVo.setOpacity(100);
										layerVo.setFenixCode(c.getFenixCode());
										layerVo.setLegendUrl(c.getLagendUrl());
										layerVo.setAbstractAbstract(c.getAbstractAbstract());
										layerVo.setSource(c.getSource());
										layerVo.setSourceContact(c.getSourceContact());
										layerVo.setProvider(c.getProvider());
										layerVo.setProviderContact(c.getProviderContact());
										layerVo.setClientBBox(c.getBBox());
										layerStore.add(layerVo);
									}
						
									haitiLayerPanel.getExternalLayersCached().put(wms.getWmsURL(), layerStore.getModels());							
									layerList.setValue(layerStore.getAt(0));
								}
							}
							public void onFailure(Throwable e) {
//								loadingWindow.destroyLoadingBox();
								FenixAlert.error("ERROR", e.getMessage());
							}
						});
					}
//				}
				
				/** TODO: manage internal layers **/
//				else {
//					
//				}
			}
		};
	}
		
		public static SelectionListener<ButtonEvent> addSelectedLayer(final HaitiMapTabItem haitiMapTabItem, final String language) {
			return new SelectionListener<ButtonEvent>() {
				@SuppressWarnings("unchecked")
				public void componentSelected(ButtonEvent event) {
					
					final HaitiOLPanel haitiOLPanel = haitiMapTabItem.getHaitiOLPanel();
					final ClientMapView mapView = haitiOLPanel.getMapView();
					final MapPanel mapPanel = haitiOLPanel.getMapPanel();
					final HaitiLayersPanel haitiLayersPanel = haitiMapTabItem.getHaitiLayersPanel();
				
					ClientGeoView ret = new ClientGeoView();
					LayerVO vo = haitiLayersPanel.getLayerList().getValue();
					vo.setOlID(ret.getClientId());
					vo.setFenixCode(ret.getFenixCode());
					
					ret.setLayerName(vo.getLayerName());
					ret.setGetMapUrl(vo.getWmsURL());
//					ret.setGetMapUrl(vo.getWmsURL()+ "?"); 
					
					ret.setOpacity(1);
					
					if ( vo.getStyleName() != null )
						ret.setStyleName(vo.getStyleName());
					ret.setBBox(mapView.getBbox());;

					mapPanel.addLayer(ret);
					haitiLayersPanel.addLayerPanelOnTop(vo, haitiMapTabItem, language);
					
//					if ( vo.getFenixCode().equals("HAITI_GAUL0") || vo.getFenixCode().equals("HAITI_GAUL1") || vo.getFenixCode().equals("HAITI_GAUL2") || vo.getFenixCode().equals("HAITI_GAUL3") || vo.getFenixCode().equals("HAITI_GAUL4")) {
//						ClientGeoView hotlinkGV = new ClientGeoView();
//						hotlinkGV = ret.copy();
//						hotlinkGV.setTitle(ret.getTitle() + " ("+HaitiLangEntry.getInstance(language).hotlink() + ")");
//						hotlinkGV.setStyleName(vo.getFenixCode() + "_hotlink");
//						hotlinkGV.setHidden(true);
//						
//						LayerVO hotlinkVO = new LayerVO(hotlinkGV.getTitle(), hotlinkGV.getClientId(), hotlinkGV.getLayerId(), hotlinkGV.getOpacity(), hotlinkGV.getLayerType().name(), hotlinkGV.getLayerName());
//						hotlinkVO.setOlID(hotlinkGV.getClientId());
//						hotlinkVO.setIsHidden(true);
//						hotlinkVO.setWmsURL(hotlinkGV.getGetMapUrl());
//						hotlinkVO.setStyleName(hotlinkGV.getStyleName());
//						hotlinkVO.setFenixCode(hotlinkGV.getFenixCode());
//						hotlinkVO.setAbstractAbstract(hotlinkGV.getAbstractAbstract());
//						hotlinkVO.setSource(hotlinkGV.getSource());
//						hotlinkVO.setSourceContact(hotlinkGV.getSourceContact());
//						hotlinkVO.setProvider(hotlinkGV.getProvider());
//						hotlinkVO.setProviderContact(hotlinkGV.getProviderContact());
//						hotlinkVO.setClientBBox(hotlinkGV.getBBox());
//						mapPanel.addLayer(hotlinkGV);
//						haitiLayersPanel.addLayerPanelOnTop(hotlinkVO, haitiMapTabItem, language);
//					}
	
				}
			};
		}
		
		public static void setCropCalendarProvince(final String gaulCode, final String langCode, final ComboBox<DimensionItemModel> combo, final ListStore<DimensionItemModel> store) {
			store.removeAll();
//			combo.setValue(new DimensionItemModel("", ""));
			combo.clearSelections();
			HaitiServiceEntry.getInstance().getCropCalendarProvinces(gaulCode, langCode, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> codesVo) {
					for(CodeVo code : codesVo)
						store.add(new DimensionItemModel(code.getLabel(), code.getCode()));
					
					if ( store.getCount() != 0)
						combo.setValue(store.getAt(0));
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			});
		}
		

		@SuppressWarnings("deprecation")
		public static SelectionChangedListener<DimensionItemModel> getCropCalendar(final HaitiCropCalendarPanel cropCalendar, final String language) {
				return new SelectionChangedListener<DimensionItemModel>() {
					public void selectionChanged(SelectionChangedEvent<DimensionItemModel> se) {		
						String selectedCountryCode = cropCalendar.getProvincesList().getValue().getCode();
						String url = "../cropCalendars/108/"+selectedCountryCode+ "_" + language.toUpperCase() +".png";
						String iFrame = "<div align='center'> <img src='" +url + "'" + "> </div>";
						cropCalendar.getImage().setHtml(iFrame);		
						cropCalendar.getExportImage().setVisible(true);
						cropCalendar.getPanel().getLayout().layout();
				}
			};
		}
		
		public static SelectionListener<ButtonEvent> exportCalendarImage(final HaitiCropCalendarPanel cropCalendar, final String language) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					String selectedCountryCode = cropCalendar.getProvincesList().getValue().getCode();
					String url = "../cropCalendars/108/"+selectedCountryCode+ "_" + language.toUpperCase() +".png";
					Window.open(url, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				}
			};
		}
		
		
		public static SelectionChangedListener<GaulModelData> buildCropCalendarTab(final ComboBox<GaulModelData> gaulList, final HaitiCropCalendarPanel cropCalendar, final String language) {
			return new SelectionChangedListener<GaulModelData>() {
				public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
					cropCalendar.rebuild(gaulList.getValue().getGaulCode(), language);
				}
			};
		}
		
		
		public static SelectionListener<IconButtonEvent> zoomToCountry(final HaitiMapTabItem haitiMapTabItem) {
			return new SelectionListener<IconButtonEvent>() {
				public void componentSelected(IconButtonEvent ce) {					
					MapServiceEntry.getInstance().getCountryExtents(haitiMapTabItem.getHaitiLayersPanel().getGaulList().getValue().getGaulCode(), new AsyncCallback<ClientBBox>() {
						public void onSuccess(ClientBBox bbox) {
							haitiMapTabItem.getHaitiOLPanel().getMapPanel().zoomToBBox(bbox);						
						}					
						public void onFailure(Throwable caught) {
						}
					});
				}
			};
		}
		
		public static SelectionListener<IconButtonEvent> zoomToLayer(final HaitiMapTabItem haitiMapTabItem) {
			return new SelectionListener<IconButtonEvent>() {
				public void componentSelected(IconButtonEvent ce) {					
					LayerVO layerVO = haitiMapTabItem.getHaitiOLPanel().getMapPanel().getSelectedLayer();
					haitiMapTabItem.getHaitiOLPanel().getMapPanel().zoomToBBox(layerVO.getClientBBox());
				}
			};
		}
		

		public static void getMapsGalleryTree(final HaitiMapsGalleryPanel mapsGalleryPanel, final String countryCode, final String language) {
			HaitiServiceEntry.getInstance().getMapsGallery(countryCode, language, new AsyncCallback<TreeMap<String, List<CodeVo>>>() {
				public void onSuccess(TreeMap<String, List<CodeVo>> result) {
						mapsGalleryPanel.getCategories().putAll(result);						
						for (String key : result.keySet()) 
							mapsGalleryPanel.getCategoriesStore().add(new DimensionItemModel(key, key));
						
						if ( mapsGalleryPanel.getCategoriesStore().getCount() != 0 )
							mapsGalleryPanel.getCategoriesList().setValue(mapsGalleryPanel.getCategoriesStore().getAt(0));
					}					
				public void onFailure(Throwable caught) {
					
				}
			});
		}
		
	
			
		
		public static SelectionChangedListener<DimensionItemModel> setMapsListBox(final HaitiMapsGalleryPanel mapsGalleryPanel) {
			return new SelectionChangedListener<DimensionItemModel>() {
				public void selectionChanged(SelectionChangedEvent<DimensionItemModel> se) {
//					mapsGalleryPanel.getMapsListBox().clear();		
					mapsGalleryPanel.getMapsStore().removeAll();
					
					String category = mapsGalleryPanel.getCategoriesList().getValue().getCode();
					List<CodeVo> mapsList = mapsGalleryPanel.getCategories().get(category);
					
					
					for (CodeVo map : mapsList) 
						mapsGalleryPanel.getMapsStore().add(new DimensionItemModel(map.getLabel(), map.getCode()));
					
					if ( mapsGalleryPanel.getMapsStore().getCount() != 0 )
						mapsGalleryPanel.getMapsList().setValue(mapsGalleryPanel.getMapsStore().getAt(0));					
				}
				
			};
		}
		
		public static SelectionListener<ButtonEvent> exportMapsPDF(final HaitiMapsGalleryPanel mapsGalleryPanel, final String gaulCode, final String language) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
//					String mapName = mapsGalleryPanel.getMapsListBox().getValue(mapsGalleryPanel.getMapsListBox().getSelectedIndex());
					String mapName = mapsGalleryPanel.getMapsList().getValue().getCode();
					String path = "../layerFormatters/"+ gaulCode + "_maps_gallery/pdf/" + mapName + "_" + language.toUpperCase();
//					System.out.println(path);
					String url = path + ".pdf";
					Window.open(url, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				}
			};
		}
		
//		public static ChangeListener buildMapPreview(final HaitiMapsGalleryPanel mapsGalleryPanel, final String gaulCode, final String language, final Integer width, final Integer height) {
//			return new ChangeListener() {				
//				public void onChange(Widget sender) {
//					String mapName = mapsGalleryPanel.getMapsListBox().getValue(mapsGalleryPanel.getMapsListBox().getSelectedIndex());
//					String path = "../layerFormatters/"+ gaulCode + "_maps_gallery/images/" + mapName + "_" + language.toUpperCase();
//					String url = path + ".png";
//					Integer w = width -10;
//					Integer h = height -10;
////					String iFrame = "<div align='center'> <img src='" +url + "'" + " width="+ width +" height="+ height +"> </div>";
//					String iFrame = "<iframe src='" +url + "' width = '" + w.toString() + "' height='" + h.toString() + "' />";
//					
//					
//					mapsGalleryPanel.getImage().setHtml(iFrame);							
////					mapsGalleryPanel.getExportImage().setVisible(true);
//					mapsGalleryPanel.getPanel().getLayout().layout();					
//				}
//				
//			};
//		}
		
		public static SelectionChangedListener<DimensionItemModel> buildMapPreview(final HaitiMapsGalleryPanel mapsGalleryPanel, final String gaulCode, final String language, final Integer width, final Integer height) {
			return new SelectionChangedListener<DimensionItemModel>() {
				public void selectionChanged(SelectionChangedEvent<DimensionItemModel> se) {
//					String mapName = mapsGalleryPanel.getMapsList().getValue().getCode();
					String mapName = mapsGalleryPanel.getMapsList().getValue().getCode();
					String path = "../layerFormatters/"+ gaulCode + "_maps_gallery/images/" + mapName + "_" + language.toUpperCase();
					String url = path + ".png";
					Integer w = width -10;
					Integer h = height -10;
					String iFrame = "<iframe src='" +url + "' width = '" + w.toString() + "' height='" + h.toString() + "' />";
					
					
					mapsGalleryPanel.getImage().setHtml(iFrame);							
//					mapsGalleryPanel.getExportImage().setVisible(true);
					mapsGalleryPanel.getPanel().getLayout().layout();			
				}
				
			};
		}
		
		
}