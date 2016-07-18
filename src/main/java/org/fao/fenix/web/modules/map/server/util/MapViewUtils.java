/*

 */

package org.fao.fenix.web.modules.map.server.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.layer.ExternalWMSLayer;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.map.wms.harvest.CapabilitiesFetcher;
import org.fao.fenix.map.wms.harvest.WMSHarvester;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView.LayerType;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView.VectorType;
import org.fao.geonet.lib.wms.schema.type.WMSCapabilities;
import org.fao.geonet.lib.wms.schema.type.WMSLayer;

/**
 *
 * @author ETj
 */
public class MapViewUtils {
	
	private static final Logger LOGGER = Logger.getLogger(MapViewUtils.class);
	
	private WMSMapProviderDao wmsMapProviderDao;

	public MapView build(ClientMapView cmv) {
		MapView mapView = new MapView();
		mapView.setTitle(cmv.getTitle());
		ClientBBox cbb = cmv.getBbox();
		BoundingBox bb = new BoundingBox(cbb.getSrs(), cbb.getMinLon(), cbb.getMinLat(), cbb.getMaxLon(), cbb.getMaxLat());
		mapView.setBoundingBox(bb);

		LOGGER.info("CreateMap: TITLE '"+cmv.getTitle()+"' " + bb );

		int pos = 0;
		for (ClientGeoView cgv : cmv.getLayerList()) {
			GeoView geoView = buildGeoView(cgv, false);
			geoView.setPosition(pos++);
			mapView.addGeoView(geoView);
			LOGGER.info("CreateMap: " + geoView + " | " + cgv.getLayerType());
			
		
//			cgv.setLayerType(LayerType.VECTOR);
//			cgv.setVectorType(VectorType.POLY);
//			LOGGER.info("FORCE LAYER TYPE TO VECTOR!!! REMOVE!!!!!!!!!" +  cgv.getLayerType() +  "| " + cgv.getVectorType());
		
		}

		return mapView;
	}

	public GeoView buildGeoView(ClientGeoView cgv, Boolean isHaiti) {
		GeoView geoView = new GeoView();
		LOGGER.info("layername | layerid "+ cgv.getLayerName() + " | " + cgv.getLayerId());
		LOGGER.info("isHaiti " + isHaiti);
		LOGGER.info("cgv.getIsQuickPrj(): " + cgv.getIsQuickPrj());
		
		/** this is used for Haiti **/
		if ( isHaiti ) {
			/** TODO: could be used just the cgv instead of the query?? **/
			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByTitle(cgv.getLayerName());
			if (dbLayer == null ) {
				LOGGER.info("dbLayer == null");
				ExternalWMSLayer ext = buildLayer(cgv, cgv.getBBox(), cgv.getGetMapUrl(), "image/png");
				geoView = new GeoView(ext);
			}
			else 
				geoView = new GeoView(dbLayer);
			
		}
		else if ( cgv.getLayerId() != 0) {
			LOGGER.info("cgv.getLayerId() != 0");
			LOGGER.info("-----> "+ cgv.getLayerName() + " | " + cgv.getLayerId());
			WMSMapProvider wmsMapProvider = wmsMapProviderDao.findById(cgv.getLayerId());
			geoView = new GeoView(wmsMapProvider);
			
		}
		else if ( cgv.getIsQuickPrj() ) {	
				LOGGER.info("---> cgv.getIsQuickPrj(): " + cgv.getIsQuickPrj());
				ExternalWMSLayer ext = buildLayer(cgv, cgv.getBBox(), cgv.getGetMapUrl(), "image/png");
				geoView = new GeoView(ext);
		}
		
		else {
			LOGGER.info("ExternalWMSLayer ext");
			ExternalWMSLayer ext = getLayers(cgv.getGetMapUrl(), cgv.getLayerName());
			geoView = new GeoView(ext);
		}
		
		float opacity = (float) cgv.getOpacity() / 100;

		geoView.setStyleName(cgv.getStyleName());
		geoView.setTitle(cgv.getTitle());
		geoView.setHidden(cgv.isHidden());
		geoView.setOpacity(opacity);
		
		LOGGER.info("geoview type: " + opacity + " | " + cgv.isHidden());
		return geoView;
	}
	
	/** TODO: change it, it doesn't have to loop all the times...**/
	public ExternalWMSLayer getLayers(String url, String layerName) {
		WMSCapabilities capabilities;
		
		try {
			capabilities = CapabilitiesFetcher.fetchCapabilities(url);
			List<WMSLayer> layerList = WMSHarvester.getLayers(capabilities);
			for(WMSLayer layer: layerList) {	
				if ( layer.getName().equals(layerName)) 
					return WMSHarvester.buildLayer(layer, url, "image/png");				
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return null;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}
	
	public static ExternalWMSLayer buildLayer(ClientGeoView clientGeoView, ClientBBox bbox, String getMapURL, String format)	
	{
		ExternalWMSLayer ext = new ExternalWMSLayer();
		ext.setLayerName(clientGeoView.getLayerName());
		ext.setTitle(clientGeoView.getTitle());
		ext.setGetMapUrl(getMapURL);
		ext.setBoundingBox(new BoundingBox(bbox.getSrs(), bbox.getMinLat(), bbox.getMinLon(), bbox.getMaxLat(), bbox.getMaxLon()));
		ext.setAbstractAbstract(clientGeoView.getAbstractAbstract());
		ext.setCurrFormat(format);
		ext.setDefaultStyle(clientGeoView.getStyleName());

		return ext;
	}
}
