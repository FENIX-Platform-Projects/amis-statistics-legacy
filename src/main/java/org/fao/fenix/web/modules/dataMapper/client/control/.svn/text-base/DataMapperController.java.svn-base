package org.fao.fenix.web.modules.dataMapper.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.view.ADAMMapWrapper;
import org.fao.fenix.web.modules.adam.client.view.ADAMOLPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMWrapper;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.coding.common.services.CodingService;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.server.security.FenixLogin;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperContent;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperMapPanel;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperOLPanel;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperOptions;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperRegion;
import org.fao.fenix.web.modules.dataMapper.common.services.DataMapperServiceEntry;
import org.fao.fenix.web.modules.ec.common.services.ECServiceEntry;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.map.common.vo.StaticProjectionVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.wcct.client.view.ChartCenterPanel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class DataMapperController {

	
	public static void getCountries(final DataMapperRegion dataMapperRegion) {
		DataMapperServiceEntry.getInstance().getAllCodes("GAUL0", "EN", new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
				System.out.println("HERE: " + result.size());
				VerticalPanel panel = dataMapperRegion.getPanel();
				
				for( CodeVo codeVo : result) {
					DataMapperContent dataMapper = new DataMapperContent();
				
					panel.add(dataMapper.build(codeVo.getCode(), codeVo.getLabel(), null));
					dataMapperRegion.getCountries().put(codeVo.getCode(), dataMapper);
				}
				
				panel.layout();
			}
			
			public void onFailure(Throwable arg0) {
				
			}
		});
	}
	

	public static SelectionListener<ButtonEvent> createMap(final DataMapperMapPanel dataMapperMapPanel, final DataMapperRegion dataMapperRegion, final DataMapperOptions dataMapperOptions) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				StaticProjectionVO vo = new StaticProjectionVO();
				
				List<String> lowerlayers = new ArrayList<String>();
				lowerlayers.add("layer_gaul0");
				vo.setLowerLayers(lowerlayers);
			
				List<String> upperLayers = new ArrayList<String>();
				upperLayers.add("layer_gaul0");
				vo.setUpperLayers(upperLayers);
				
				
				HashMap<String, Double> projections = getValues(dataMapperRegion.getCountries());
				
				
				vo.setHeight(300);
				
				vo.setProjections(projections);
				
				try {
					vo.setIntervals(Integer.valueOf(dataMapperOptions.getIntervals().getValue()));
				}catch (Exception e) {
					FenixAlert.info("Error", "insert a number");
				}
				
				vo.setMinColor("RED");
				vo.setMaxColor("LIGHT_GRAY");
//				vo.setMaxColor("WHITE");
//				final LoadingWindow window = new LoadingWindow("", "", "");
//				window.showLoadingBox();
				
				JoinDatasetVO joinDatasetVO = new JoinDatasetVO();
				
				Double d = Random.nextDouble();
				String randomvalue = d.toString();
				randomvalue = randomvalue.replace("-", "");
				randomvalue = randomvalue.replace(".", "");
				System.out.println("randomvalue: " + randomvalue);
				
				joinDatasetVO.setLayer("layer_gaul0");
				joinDatasetVO.setDatasetViewName("quick_table_" + randomvalue);
				
				joinDatasetVO.setLayerJoinView("quick_layer_" + randomvalue);
				joinDatasetVO.setJoinedValues(vo.getProjections());
//				joinDatasetVO.setDatasetViewName("datasetView_test");
				
				System.out.println(joinDatasetVO.getLayerJoinView());
				
				dataMapperMapPanel.getDatasetLayerPrj().put(joinDatasetVO.getDatasetViewName(), joinDatasetVO.getLayerJoinView());
								
				
				MapServiceEntry.getInstance().createQuickJoinView(joinDatasetVO, new AsyncCallback<ClientGeoView>() {
					public void onSuccess(ClientGeoView cgv) {
						System.out.println("here");
						
						
						removeAllLayers(dataMapperMapPanel);
						
						LayerVO vo = clientGeoViewToLayerVO(cgv);
						vo.setLegendUrl(getLegendURL(vo));
						
						dataMapperMapPanel.getLayers().put(vo.getOlID(), vo);
						

						dataMapperMapPanel.getOlPanel().getMapPanel().addLayer(cgv);
						
						// setting legend
						vo.setLegendUrl(getLegendURL(vo));
						
						VerticalPanel infoPanel = new VerticalPanel();
						infoPanel.setVerticalAlign(VerticalAlignment.TOP);
						infoPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
						
						infoPanel.setSpacing(10);
						infoPanel.add(new Html("<div class='small-content'><b>Legend:</b></div>"));
						infoPanel.add(new Html("<div> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));

						if (RootPanel.get("LEGEND_PANEL").getWidgetCount() > 0)
							RootPanel.get("LEGEND_PANEL").remove(RootPanel.get("LEGEND_PANEL").getWidget(0));
			

						RootPanel.get("LEGEND_PANEL").add(infoPanel);
						
						infoPanel.layout();
						
						

						
						/** ADDING THE GAUL0  AND LABELS **/
						List<String> layersToAdd = new ArrayList<String>();
						layersToAdd.add("layer_gaul0");
//						layersToAdd.add("layer_gaul0");
	//
						MapServiceEntry.getInstance().getLayersByCode(layersToAdd, new AsyncCallback<List<ClientGeoView>>() {
							public void onSuccess(List<ClientGeoView> clientGeoViews) {

								// adding label style								
					
								for(ClientGeoView clientGeoView : clientGeoViews) {
//									clientGeoView.setStyleName("label_quantity_000000");
									clientGeoView.setStyleName("label_adm0_name_000000");
									clientGeoView.setLayerId(0);
									clientGeoView.setIsQuickPrj(true);

									// track the layer
									LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
									
									dataMapperMapPanel.getLayers().put(vo.getOlID(), vo);
									

									
									dataMapperMapPanel.getOlPanel().getMapPanel().addLayer(clientGeoView);
								
					
								}
							}
								

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub
							}
						});

					}
					public void onFailure(Throwable e) {
						
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
				});
			
//				MapServiceEntry.getInstance().createStaticMap(vo, new AsyncCallback<List<String>>() {
//					public void onSuccess(List<String> mapPaths) {
//						window.destroyLoadingBox();
//						
////						for (String map : mapPaths) {
//							
////							String iFrame = "<iframe width='100%' height='100%' src='../exportObject/" +mapPaths.get(1) + "'" + "> </iframe>";
//
//						
//							VerticalPanel legend = new VerticalPanel();
//							HTML content = new HTML(mapPaths.get(0));
//							
//							legend.add(content);
//							
//							Image image = new Image("../exportObject/" + mapPaths.get(1));
//							VerticalPanel panel = new VerticalPanel();
////							panel.setHeight(400);
//							
//	
//							panel.add(image);
//							
//							if (RootPanel.get("MAP_PANEL").getWidgetCount() > 0)
//								RootPanel.get("MAP_PANEL").remove(RootPanel.get("MAP_PANEL").getWidget(0));
//							if (RootPanel.get("LEGEND_PANEL").getWidgetCount() > 0)
//								RootPanel.get("LEGEND_PANEL").remove(RootPanel.get("LEGEND_PANEL").getWidget(0));
//							
////							RootPanel.get(position).add(panel);
//							RootPanel.get("MAP_PANEL").add(panel);
//							
//							RootPanel.get("LEGEND_PANEL").add(legend);
//							
//							panel.layout();
////						}
//						
//
//					}
//					public void onFailure(Throwable e) {
//						window.destroyLoadingBox();
//
//						FenixAlert.error(BabelFish.print().error(), e.getMessage());
//					}
//				});
			}
		};
	}
	
	private static HashMap<String, Double> getValues(LinkedHashMap<String, DataMapperContent> countries) {
		HashMap<String, Double> hashMap = new HashMap<String, Double>();
		
		
		System.out.println("->" + countries.keySet());
		for(String key : countries.keySet()){ 
			try {
				System.out.println("countries.get(key).getValue().getValue()" + countries.get(key).getValue().getValue());
				String v = countries.get(key).getValue().getValue();
			if( v != null )
				hashMap.put(key, Double.valueOf(v));
			} catch (Exception e) {
				
			}
		}
		
		System.out.println(hashMap);
		return hashMap;
	}
	
	public static void createMap(final String position ) {
		
		StaticProjectionVO vo = new StaticProjectionVO();
		
		List<String> lowerLayers = new ArrayList<String>();
		lowerLayers.add("layer_baseraster");
		vo.setLowerLayers(lowerLayers);
		
		List<String> upperLayers = new ArrayList<String>();
		upperLayers.add("layer_gaul0");
		vo.setUpperLayers(upperLayers);
		
		
		HashMap<String, Double> projections = new HashMap<String, Double>();
		projections.put("100", new Double(1));
		projections.put("1", new Double(100));
		projections.put("2", new Double(50));
		
		vo.setProjections(projections);
		
		vo.setIntervals(6);
		
		vo.setMinColor("RED");
		vo.setMaxColor("GREEN");
		
		MapServiceEntry.getInstance().createStaticMap(vo, new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> mapPaths) {
				
				for (String map : mapPaths) {
					Image image = new Image("../exportObject/" + map);
					ContentPanel panel = new ContentPanel();
					panel.add(image);
//					RootPanel.get(position).add(panel);
					RootPanel.get(position).add(panel);
					panel.getLayout().layout();
				}
				

			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});


	}


	
	
	public static void initializePredifinedMap(final String mapType, final DataMapperMapPanel dataMapperMapPanel) {
		final DataMapperOLPanel olPanel = dataMapperMapPanel.getOlPanel();
		
		System.out.println("here");
//		final ADAMLayersHandle adamLayersHandle = adamMapWrapper.getAdamMapPanel().getAdamLayersHandle();
		
		final ClientMapView mapView = olPanel.getMapView();
		final MapPanel mapPanel = olPanel.getMapPanel();



		MapServiceEntry.getInstance().getLayersByCodingSystem("datamapper", mapType, new AsyncCallback<List<ClientGeoView>>() {
			public void onSuccess(List<ClientGeoView> cgvlist) {
//				ADAMResultVO adamResultVO = new ADAMResultVO();
//				adamResultVO.setBoxColor(color);
				mapView.setTitle("");
				ClientBBox bbox = null;

				int i = 0;
				for (ClientGeoView clientGeoView : cgvlist) {
					if (bbox == null)
						bbox = clientGeoView.getBBox();
					else
						bbox = bbox.union(clientGeoView.getBBox());
					
					LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
					vo.setLegendUrl(getLegendURL(vo));

					/** TODO: this is a workaround for the GAUL0 **/
//					if ( i == 0 || i == 1)
					if ( i == 0 )
						vo.setIsBaseLayer(true);



//					if ( i == cgvlist.size() - 1) {
//						infoPanel.removeAll();
//						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
//
////						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
////						infoPanel.layout();
//					}

					dataMapperMapPanel.getLayers().put(vo.getOlID(), vo);
//					adamResultVO.addLayer(vo);
					mapView.addLayer(clientGeoView);

					i++;
				}


				mapView.setBbox(bbox);
				mapPanel.createADAMMap(mapView, 13, 100000000, 5000000);

//				mapPanel.createMap(mapView, 13,1000000000, 5000);
//				mapPanel.createMap(mapView);
				mapPanel.zoomToMaxExtent();


//				adamMapWrapper.setAdamResultVO(adamResultVO);
				
//				adamResultVO.setOutputType(ADAMBoxContent.MAP.toString());
//				adamResultVO.setResourceType(ADAMBoxContent.MAP.toString());
//				
//				mapMap.put(position, adamMapWrapper.getAdamResultVO());
//				currentUsedObjects.put(position, ADAMBoxContent.MAP);
				
				
				/*** TODO HERE IT'S TO MAKE THE JOIN TO THE MAP ***/
				
//				ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("Amount od...auioa", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name()), objectWindowId);

			}

			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}

		});
	}
	

	
	private static void removeAllLayers(DataMapperMapPanel dataMapperMapPanel) {
		List<Long> gvids = new ArrayList<Long>();

		for (Long gvid : dataMapperMapPanel.getLayers().keySet()) {
			LayerVO layerVo = dataMapperMapPanel.getLayers().get(gvid);
			if ( !layerVo.isBaseLayer())
				gvids.add(gvid);
		}
		MapPanel mapPanel = dataMapperMapPanel.getOlPanel().getMapPanel();
		for(Long gvid : gvids) {
			mapPanel.removeLayer(gvid);
			dataMapperMapPanel.getLayers().remove(gvid);
		}
	}
	
	
	private static LayerVO clientGeoViewToLayerVO(ClientGeoView clientGeoView){
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
		vo.setIsQuickPrj(clientGeoView.getIsQuickPrj());
		if ( clientGeoView.getLagendUrl() != null)
			vo.setLegendUrl(getLegendURL(vo));
		return vo;
	}
	
	private static String getLegendURL(LayerVO vo) {
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
		System.out.println("LEGEND URL: " + url);
		return url;
	}
	
	public static SelectionListener<ButtonEvent> exportMapAsZip(final DataMapperMapPanel dataMapperMapPanel, final String language) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportMapAsZipAgent(dataMapperMapPanel, language);
			}
		};
	}
	
	private static void exportMapAsZipAgent(final DataMapperMapPanel dataMapperMapPanel, final String language) {
		ClientMapView cmv = createClientMapView(dataMapperMapPanel);
		final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
		loadingWindow.showLoadingBox();
		

		MapServiceEntry.getInstance().exportAsZip(cmv, true, 1024, 768, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				loadingWindow.destroyLoadingBox();
				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
			public void onFailure(Throwable e) {
				loadingWindow.destroyLoadingBox();
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}

	
	private static ClientMapView createClientMapView(DataMapperMapPanel dataMapperMapPanel) {
		String mapDivId = dataMapperMapPanel.getOlPanel().getMapPanel().getMapDivId();
		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
		ClientMapView cmv = new ClientMapView();
		cmv.setBbox(bbox);
//		List<LayerVO> vos = getSortedLayers(adamMapWrapper.getAdamResultVO().getLayersMap());
		List<LayerVO> vos = getLayers(dataMapperMapPanel.getLayers());

		for (LayerVO vo : vos) {
			ClientGeoView cgv = new ClientGeoView();
			cgv.setBBox(bbox);
			cgv.setGetMapUrl(vo.getWmsURL());
			cgv.setLayerName(vo.getLayerName());
			cgv.setStyleName(vo.getStyleName());
			cgv.setTitle(vo.getLayerTitle());
			cgv.setIsQuickPrj(vo.getIsQuickPrj());
			cgv.setHidden(false);
			cgv.setFenixCode(vo.getFenixCode());
			if ( vo.getLayerID() != null)
				cgv.setLayerId(vo.getLayerID());
			cmv.addLayer(cgv);
			
			System.out.println("EXPORTING LAYER: " + cgv.getLayerName() + " | " + cgv.getLayerId() + " | " + cgv.getIsQuickPrj());
		}
		return cmv;
	}
	
	private static List<LayerVO> getLayers(LinkedHashMap<Long, LayerVO> layersMap) {
		List<LayerVO> vos = new ArrayList<LayerVO>();
		for(Long id : layersMap.keySet()) {
			vos.add(layersMap.get(id));
		}

		List<LayerVO> result = new ArrayList<LayerVO>();

		for(int i = 0 ; i < vos.size() ; i++) {
			result.add(vos.get(i));
		}
		return result;
	}

	
	public static void cleanLayersAndViews() {
		if ( ADAMWrapper.getDatasetProjectionViews() != null) {
			System.out.println("cleaning projections and layer: " + DataMapperMapPanel.getDatasetLayerPrj());
			
			for (String tablename : DataMapperMapPanel.getDatasetLayerPrj().keySet()) {
				System.out.println("DROP TABLE: " + tablename);
				DataMapperServiceEntry.getInstance().dropTable(tablename, new AsyncCallback() {
					public void onFailure(Throwable arg0) {
					}
					public void onSuccess(Object arg0) {
					}
				});
			}
			for ( String key : DataMapperMapPanel.getDatasetLayerPrj().keySet()) {
				System.out.println("depublish layer");
				MapServiceEntry.getInstance().depublishLayer(DataMapperMapPanel.getDatasetLayerPrj().get(key), new AsyncCallback() {
					public void onFailure(Throwable arg0) {
						System.out.println("depublished layer");
					}
					public void onSuccess(Object arg0) {
					}
				});			
				System.out.println("depublish style");
				MapServiceEntry.getInstance().depublishStyle(DataMapperMapPanel.getDatasetLayerPrj().get(key), new AsyncCallback() {
					public void onFailure(Throwable arg0) {
						System.out.println("depublished style");
					}
					public void onSuccess(Object arg0) {
					}
				});
			}
		}

	}
}


