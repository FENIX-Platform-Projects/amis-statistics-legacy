package org.fao.fenix.web.modules.map.server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;

import com.extjs.gxt.ui.client.widget.Html;

public class MapUtils {

	
	
	@SuppressWarnings("static-access")
	public static ClientGeoView wmsMapProvider2ClientGeoView(String geoserverUrl, WMSMapProvider f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBoundingBox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getResourceId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		c.setAbstractAbstract(f.getAbstractAbstract());
		c.setSource(f.getSource().getTitle());
		c.setSourceContact(f.getSource().getContact());
		c.setProvider(f.getProvider().getTitle());
		c.setProviderContact(f.getProvider().getContact());
		c.setFenixCode(f.getCode());
		return c;
	}
	
	@SuppressWarnings("static-access")
	public static ClientGeoView wmsMapProvider2ClientGeoView(String geoserverUrl, DBFeatureLayer f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBoundingBox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getResourceId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		c.setAbstractAbstract(f.getAbstractAbstract());
		c.setSource(f.getSource().getTitle());
		c.setSourceContact(f.getSource().getContact());
		c.setProvider(f.getProvider().getTitle());
		c.setProviderContact(f.getProvider().getContact());
		c.setFenixCode(f.getCode());
		return c;
	}
	
	@SuppressWarnings("static-access")
	public static ClientGeoView wmsGenericMapProvider2ClientGeoView(WMSMapProvider f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBoundingBox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getResourceId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setStyleName(f.getDefaultStyle());
		c.setAbstractAbstract(f.getAbstractAbstract());
		c.setSource(f.getSource().getTitle());
		c.setSourceContact(f.getSource().getContact());
		c.setProvider(f.getProvider().getTitle());
		c.setProviderContact(f.getProvider().getContact());
		c.setFenixCode(f.getCode());
		return c;
	}
	
	public static ClientBBox bbox2ClientBBox(BoundingBox b) {
		ClientBBox c = new ClientBBox();
		c.setMaxLat(b.getMaxY());
		c.setMaxLon(b.getMaxX());
		c.setMinLat(b.getMinY());
		c.setMinLon(b.getMinX());
		c.setSrs(b.getSrs());
		return c;
	}
	
	public static LinkedHashMap<Long, LayerVO> clientGeoViewsTOLayerVO(List<ClientGeoView> cgvlist) {
		LinkedHashMap<Long, LayerVO> layerVOHM = new LinkedHashMap<Long, LayerVO>();
		List<LayerVO> layerVOs = new ArrayList<LayerVO>();
		ClientBBox bbox = null;
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
			vo.setLegendUrl(getLegendURL(vo));
			layerVOs.add(vo);
		}
		for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
			if ( i == 0) {
				layerVOs.get(i).setIsBaseLayer(true);
			}
			layerVOHM.put(layerVOs.get(i).getOlID(), layerVOs.get(i));
		}
		
		return layerVOHM;
	}
	
	public static String getLegendURL(LayerVO vo) {
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
	
	public static String getLegendURL(ClientGeoView clientGeoView) {
		String geoserverURL = (clientGeoView.getGetMapUrl().indexOf("wms") > 0 ? clientGeoView.getGetMapUrl().substring(0, clientGeoView.getGetMapUrl().indexOf("wms")) : clientGeoView.getGetMapUrl());
		String layerName = clientGeoView.getLayerName();
		String styleName = clientGeoView.getStyleName();
		String url = new String();

			url = geoserverURL + 
					     "wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER=" + 
					     layerName + 
					     "&STYLE=" + 
					     styleName + 
					     "&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP=" + 
					     0;
		
		System.out.println("LEGEND URL: " + url);
		return url;
	}
	
}
