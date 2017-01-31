package org.fao.fenix.web.modules.map.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.services.superif.TableViewDataProviderAsync;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.map.common.vo.StaticProjectionVO;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MapServiceAsync extends TableViewDataProviderAsync {
	
	public void findAllCountryLayers(String gaul0code, AsyncCallback<List<Long>> callback);
	
	// map stuff
//	public void createEmptyMap(AsyncCallback<ClientMapView> callback);
	public void getMap(long mapId, AsyncCallback<ClientMapView> callback);
	public void getLayers(List<Long> layerIds, AsyncCallback<List<ClientGeoView>> callback);

	public void createMap(ClientMapView cmv, AsyncCallback<Long> asyncCallback);
	public void updateMap(ClientMapView cmv, AsyncCallback<ClientMapView> asyncCallback);

	public void canUpdate(long mapId, AsyncCallback<Boolean> callback);

//	public void addLayerToMap(long mapViewId, long layerId, AsyncCallback<ClientGeoView> callback);
//	public void addGeoViewToMap(long mapViewId, long geoViewId, AsyncCallback<Void> callback);
//	public void removeGeoViewFromMap(long mapViewId, long geoviewId, AsyncCallback<Void> asyncCallback);

	//projected data stuff
	public void getJoinableDataList(long geoViewId, AsyncCallback<List<FenixResourceVo>> asyncCallback);
//	public void projectData(long dataListId, long layerId, AsyncCallback<ClientGeoView> asyncCallback);
	public void addProjectedData(long mapViewId, long geoViewId, long datasetId, AsyncCallback<ClientGeoView> asyncCallback);

	public void getProjectedDimensions(long geoViewId, AsyncCallback<ClientProjectedDataDimension[]> asyncCallback);
	public void setProjectedDimensions(long geoViewId, List<ClientProjectedDataDimension> dimensions, AsyncCallback<Void> asyncCallback);

	public void setGeoViewStyle(long geoViewId, String styleName, String sldBody, AsyncCallback<Void> asyncCallback);

	public void exportAsImage(ClientMapView cmv, AsyncCallback<String> asyncCallback);
	public void exportAsWmc(ClientMapView cmv, AsyncCallback<String> asyncCallback);
	public void getCountryExtents(String gaul0Code, AsyncCallback<ClientBBox> asyncCallback);

	public void runLayerImporter(AsyncCallback<Void> asyncCallback);
	public void runGSSynch(AsyncCallback<Void> asyncCallback);
	public void runGeotiffHarvester(AsyncCallback<Boolean> asyncCallback);

	public void getLayerFieldnames(long layerId, AsyncCallback<List<String>> asyncCallback);
	public void createLabelStyle(long layerId, StyleDefinition styleDefinition, AsyncCallback<String> asyncCallback);
	
	public void exportAsZip(ClientMapView cmv, Boolean getLegends, Integer width, Integer height, AsyncCallback<String> asyncCallback);
	
	
	public void getLayersByCodingSystem(String codingSystemTitle, String prefix,  AsyncCallback<List<ClientGeoView>> callback);
	
	
	public void createJoinDatasetView(JoinDatasetVO joinDatasetVO, AsyncCallback<ClientGeoView> asyncCallback);
	
	public void getLayersByCode(List<String> layersCode, AsyncCallback<List<ClientGeoView>> asyncCallback);
	
	public void createStaticMap(StaticProjectionVO vo, AsyncCallback<List<String>> asyncCallback);
	
	public void depublishLayer(Long layerId, AsyncCallback asyncCallback);

	public void depublishLayer(String layerName, AsyncCallback asyncCallback);
	
	public void depublishStyle(String styleName, AsyncCallback asyncCallback);
	
	public void createQuickJoinView(JoinDatasetVO joinDatasetVO, AsyncCallback<ClientGeoView> asyncCallback);
	
	public void parseFeatureInfoHTML(String html, String type, AsyncCallback<List<List<String>>> asyncCallback);

}
