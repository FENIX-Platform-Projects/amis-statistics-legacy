package org.fao.fenix.web.modules.map.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.services.superif.TableViewDataProvider;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.map.common.vo.StaticProjectionVO;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface MapService extends RemoteService, TableViewDataProvider {

	public List<Long> findAllCountryLayers(String gaul0code);

	public ClientMapView getMap(long mapId);
	public List<ClientGeoView> getLayers(List<Long> layerIds);

	@Secured( { "ROLE_USER" })
	public Long createMap(ClientMapView cmv);

	@Secured( { "ROLE_USER" })
	public ClientMapView updateMap(ClientMapView cmv);

	public Boolean canUpdate(long mapId);

	/**
	 * Return the list of Datalist that may be projected on the layer referenced by the given geoView.
	 */
	public List<FenixResourceVo> getJoinableDataList(long geoViewId);
		
	/**
	 * Projects a dataset on a layer.
	 * <UL>
	 * <LI>Create a ProjectedData entry.</LI>
	 * <LI>Publish a new layer on GeoServer</LI>
	 * <LI>Create a GeoView associated to the new PD entry</LI>
	 * <LI>Add the GeoView to the current MapView</LI>
	 * </UL>
	 */
//	public ClientGeoView projectData(long dataListId, long layerId);
	public ClientGeoView addProjectedData(long mapViewId, long geoViewId, long datasetId)
			throws FenixGWTException;

	/**
	 * Return the associated dimensions of the projection.
	 * Using ArrayList instead of List to help gwt compilation.
	 */
	public ClientProjectedDataDimension[] getProjectedDimensions(long geoViewId);

	/**
	 * @throws if the current set of dimensions would return no geometries.
	 */
	public void setProjectedDimensions(long geoViewId, List<ClientProjectedDataDimension> dimensions)
			throws FenixGWTException;
	
	/**
	 * 
	 * @param geoViewId
	 * @param styleName
	 * @param sldBody
	 * @param asyncCallback
	 */
	public void setGeoViewStyle(long geoViewId, String styleName, String sldBody);

	public String exportAsImage(ClientMapView cmv);
	public String exportAsWmc(ClientMapView cmv);

	public ClientBBox getCountryExtents(String gaul0Code) throws FenixGWTException;

	public void runLayerImporter();
	public void runGSSynch();
	public boolean runGeotiffHarvester();

	public List<String> getLayerFieldnames(long layerId) throws FenixGWTException;
	public String createLabelStyle(long layerId, StyleDefinition styleDefinition) throws FenixGWTException;
	
	public String exportAsZip(ClientMapView cmv, Boolean getLegends, Integer width, Integer height); 
			
	
	public List<ClientGeoView> getLayersByCodingSystem(String codingSystemTitle, String prefix);
	
	
	public ClientGeoView createJoinDatasetView(JoinDatasetVO joinDatasetVO) throws FenixGWTException;
	
	public List<ClientGeoView> getLayersByCode(List<String> layersCode);
	
	public List<String> createStaticMap(StaticProjectionVO vo);
	
	public void depublishLayer(Long layerId);
	
	public void depublishLayer(String layerName);
	
	public void depublishStyle(String styleName);
	
	public ClientGeoView createQuickJoinView(JoinDatasetVO joinDatasetVO) throws FenixGWTException;
	
	public List<List<String>> parseFeatureInfoHTML(String html, String type);
}