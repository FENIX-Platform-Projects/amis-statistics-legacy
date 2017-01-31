package org.fao.fenix.web.modules.core.client.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.aquastat.client.control.AquastatController;
import org.fao.fenix.web.modules.birt.client.countrybrief.CountryBriefController;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.fpi.client.view.index.FPIIndexWindow;
import org.fao.fenix.web.modules.giews.common.services.GIEWSServiceEntry;
import org.fao.fenix.web.modules.giews.common.vo.MapAndProject;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueContextMenuImageController;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueContextMenuOlapController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.Tonictionary;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.text.client.view.viewer.TextViewer;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;
import org.fao.fenix.web.modules.welcome.client.control.SplashWindowFactory;
import org.fao.fenix.web.modules.welcome.client.view.SplashWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class URLInterceptor {

	int x=80;
	int y=100;
	
	public URLInterceptor() {
		String params = getParamString();
		if (params != null && params.length() > 0) {
			if (params.contains("gwt.codesvr")) {
				SplashWindowFactory.getInstance(false);
			} 
			params = remove(params);
			parseParams(params);
		}
		else
			executeDefault();
		checkBrowser();
	}
	
	public void checkBrowser() {
		String userBrowser = getUserBrowser();
		String userBrowserVersion= getUserBrowserVersion();
		boolean firefox = userBrowser.contains("Firefox");
		boolean hostedMode = userBrowser.contains("Gecko");
		boolean msie7 = userBrowserVersion.contains("MSIE 7");
		boolean msie8 = userBrowserVersion.contains("MSIE 8");
		if (!firefox && !hostedMode && !(msie7 || msie8))
			FenixAlert.info("INFO", BabelFish.print().browserAlert());
	}
	
	private native String getUserBrowser() /*-{
		return navigator.userAgent;
	}-*/;
	
	private native String getUserBrowserVersion() /*-{
		return navigator.appVersion;
	}-*/;

	private native String getParamString() /*-{
		return $wnd.location.search;
	}-*/;

	private void parseParams(String params) {
		int paramNameIndex = 0;
		for (int i = 0; i < params.length(); i++) {
			
			if (params.charAt(i) == '=') {
				paramNameIndex = i;
				break;
			}
		}
		String paramName = params.substring(1, paramNameIndex);
		String paramValue = params.substring(1 + paramNameIndex);
		execute(paramName, paramValue);
	}

	private String remove(String gwtCode) {
		String code = gwtCode.replace("gwt.codesvr=127.0.1.1:9997&", "");
		return code;
	}
	
	private void execute(String paramName, String paramValue) {
		
		// generic scope
		if (paramName.equals("scope")) 
			executeScope(paramValue);
		
		// open chart
		else if (paramName.equals("openChartResource")) {
			GIEWSServiceEntry.getInstance().getChartByTitle(paramValue, new AsyncCallback<Long>(){		
				public void onSuccess(final Long chartId) {
					new ChartViewer(chartId.toString());
					
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("getmapIdbyCode", caught.getMessage());
				}
				
			});
		} 
		
		// Open Chart
		else if (paramName.equals("openChart")) {
			new ChartViewer(paramValue);
		}

		// Open OLAP		
		else if (paramName.equals("openOLAP")) {
			CatalogueContextMenuOlapController.open(Long.valueOf(paramValue), "URLInterceptor @ 125", WhoCall.USER, "");
		}
		
		// Open Image
		else if (paramName.equals("openImage")) {
			CatalogueContextMenuImageController.open(Long.valueOf(paramValue));
		}
		
		// AQUASTAT
		else if (paramName.equals("aquastat")) {
			if (paramValue.equalsIgnoreCase("all"))
				AquastatController.createCharts();
			else
				AquastatController.createCharts(paramValue);
		} 
		
		// open a map
		else if (paramName.equals("openMapResource")){
			GIEWSServiceEntry.getInstance().getmapIdbyCode(paramValue, new AsyncCallback<MapAndProject>(){
				
				public void onSuccess(final MapAndProject info) {
					if (info.getMapInfo() != null){
						for (Map<String, String> map : info.getMapInfo()){
							
							MapServiceEntry.getInstance().getMap(Long.valueOf(map.get("id")), new AsyncCallback<ClientMapView>() {
								public void onSuccess(final ClientMapView cmv) {
									if (info.getProjectModel().get(0).getRegion() != null && !info.getProjectModel().get(0).getRegion().equals("0")) {
										MapServiceEntry.getInstance().getCountryExtents(info.getProjectModel().get(0).getRegion(), new AsyncCallback<ClientBBox>() {
											public void onSuccess(ClientBBox bbox) {
												cmv.setBbox(bbox);
												MapWindow mw = new MapWindow(cmv);
												mw.getWindow().setPagePosition(x, y);
												mw.getWindow().setPagePosition(150, 200);
												mw.show();
											}
											public void onFailure(Throwable e) {
												FenixAlert.alert("RPC Failed", e.getMessage());
												MapWindow mw = new MapWindow(cmv);
												mw.getWindow().setPagePosition(150, 200);
												mw.show();
											}
										});
									} else {
										MapWindow mw = new MapWindow(cmv);
										mw.getWindow().setPagePosition(x, y);
										mw.getWindow().setPagePosition(150, 200);
										mw.show();
									}
								}
								public void onFailure(Throwable e) {
									FenixAlert.alert("RPC Failed", e.getMessage());
								}
							});
							
						}
						
					}
					
					if (info.getProjectModel() != null){
						 PMModel.addProjectToProjectManager(info.getProjectModel());
					}
					
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("getmapIdbyCode", caught.getMessage());
				}
				
			});
		} 
		
		// open a project
		else if (paramName.equals("openGiewsProject")){
			GIEWSServiceEntry.getInstance().getProjectbyCode(paramValue, new AsyncCallback<MapAndProject>(){
				
				public void onSuccess(final MapAndProject info) {
					
					if (info.getMapInfo() != null){
						
						for (Map<String, String> map : info.getMapInfo()){
							
							MapServiceEntry.getInstance().getMap(Long.valueOf(map.get("id")), new AsyncCallback<ClientMapView>() {
								public void onSuccess(final ClientMapView cmv) {
									if (info.getProjectModel().get(0).getRegion() != null && !info.getProjectModel().get(0).getRegion().equals("0")) {
										MapServiceEntry.getInstance().getCountryExtents(info.getProjectModel().get(0).getRegion(), new AsyncCallback<ClientBBox>() {
											public void onSuccess(ClientBBox bbox) {
												cmv.setBbox(bbox);
												MapWindow mw = new MapWindow(cmv);
												mw.getWindow().setPagePosition(x, y);
												x+=80;
												y+=70;
												mw.show();
											}
											public void onFailure(Throwable e) {
												FenixAlert.alert("RPC Failed", e.getMessage());
												MapWindow mw = new MapWindow(cmv);
												mw.getWindow().setPagePosition(x, y);
												x+=80;
												y+=70;
												mw.show();
											}
										});
									} else {
										MapWindow mw = new MapWindow(cmv);
										mw.getWindow().setPagePosition(x, y);
										x+=80;
										y+=70;
										mw.show();
									}
								}
								public void onFailure(Throwable e) {
									FenixAlert.alert("RPC Failed", e.getMessage());
								}
							});
							
						}
						
					}
					
					if (info.getTextView() != null){
						TextServiceEntry.getInstance().getText(info.getTextView().get(0), new AsyncCallback<List<String>>() {

							public void onSuccess(final List<String> result) {
								TextViewer textViewer = new TextViewer(info.getTextView().get(0), result);
								textViewer.build(false);
								textViewer.getWindow().setPagePosition(400, 400);
							}
							public void onFailure(Throwable caught) {
								FenixAlert.alert("Error: RPC Failed", "TextService:getText() Failed");
							}
						});
						
						
					}
					
					if (info.getProjectModel() != null){
						 PMModel.addProjectToProjectManager(info.getProjectModel());
					}
					
				}
				
				public void onFailure(Throwable caught) {
					FenixAlert.alert("getProjectbyCode", caught.getMessage());
				}
				
			});
		}
		
		// open a dataset in the project manager
		else if (paramName.equals("openFSATMISDataset")) {
			List<ResourceChildModel> selectedItems = new ArrayList<ResourceChildModel>();
			ResourceChildModel resourceChild = new ResourceChildModel("FS-ATMIS Dataset");
			resourceChild.setId(paramValue);
			resourceChild.setType(ResourceType.DATASET);
			selectedItems.add(resourceChild);
			PMModel.addResourceToProjectManager(selectedItems, ResourceType.DATASET);
			FenixAlert.info(BabelFish.print().information(), BabelFish.print().urlInterceptorInfo());
		}
		
		else if (paramName.equals("openProjectDataset")) {
			List<ResourceChildModel> selectedItems = new ArrayList<ResourceChildModel>();
			ResourceChildModel resourceChild = new ResourceChildModel("Dataset");
			resourceChild.setId(paramValue);
			resourceChild.setType(ResourceType.DATASET);
			selectedItems.add(resourceChild);
			PMModel.addResourceToProjectManager(selectedItems, ResourceType.DATASET);
			FenixAlert.info(BabelFish.print().information(), BabelFish.print().urlInterceptorInfo());
		}
		
		else if (paramName.equals("openDatasetCode")) {
			List<ResourceChildModel> selectedItems = new ArrayList<ResourceChildModel>();
			
			final String datasetCode = paramValue;
			
			TableServiceEntry.getInstance().getDatasetByCode(paramValue, new AsyncCallback<Long>() {
				public void onSuccess(Long datasetId) {
					TableWindow window = new TableWindow();
					window.buildFilteredTable(Long.valueOf(datasetId), false, false, datasetCode);
					window.show();
				}
				
				public void onFailure(Throwable arg0) {
//					FenixAlert.error("Dataset", "There is any dataset with that code");					
				}

				
				
			});
			
			

		}
		
		else if (paramName.equals("openText")) {
			ResourceOpener.openTextViewer(Long.parseLong(paramValue), false);
		}
		
		else if (paramName.equals("openMap")) {
			List<Long> layerIds = new ArrayList<Long>();
			layerIds.add(Long.parseLong(paramValue));
			MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {
				public void onSuccess(List<ClientGeoView> cgvlist) {
					ClientMapView mapView = new ClientMapView();
					mapView.setTitle("Layer");
					ClientBBox bbox = null;
					for (ClientGeoView clientGeoView : cgvlist) {
						mapView.addLayer(clientGeoView);
						if(bbox==null)
							bbox = clientGeoView.getBBox();
						else
							bbox = bbox.union(clientGeoView.getBBox());
					}
					mapView.setBbox(bbox);
					new MapWindow(mapView);
				}
				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "CatalogueLayerController @ onDoubleClick");
				}
			});
		}
		
	}
	
	private void executeDefault() {
//		new Welcome().build();
		SplashWindowFactory.getInstance(false);
	}

	private void executeScope(String paramValue) {
		if (paramValue.toLowerCase().equals("fpi")) {
			FPIIndexWindow window = new FPIIndexWindow();
			window.build();
			window.show();
		} else if (paramValue.equals("CountryBrief")){
			new CountryBriefController();
		} else if (paramValue.equals("tonictionary")) {
			Tonictionary t = new Tonictionary();
			t.build();
		}
	}

}
