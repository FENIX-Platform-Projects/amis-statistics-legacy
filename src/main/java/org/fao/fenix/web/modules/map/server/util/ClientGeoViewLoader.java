/*
 */

package org.fao.fenix.web.modules.map.server.util;

import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedData;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView.LayerType;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView.VectorType;

/**
 *
 * @author etj
 */
public class ClientGeoViewLoader {
	
	static public ClientGeoView buildClientResource(GeoView geoView) {
		ClientGeoView cgv = buildClientResource(geoView.getWmsMapProvider());

		cgv.setGeoViewId(geoView.getId());
		cgv.setTitle(geoView.getTitle());

		cgv.setHidden(geoView.isHidden());
		cgv.setStyleName(geoView.getStyleName());

//		cgv.setCurrFormat(geoView.getFormat());
//		cgv.setFormat(geoView.getFormat());

		return cgv;
	}

	static public ClientGeoView buildClientResource(WMSMapProvider wmp) {
		ClientGeoView cgv = new ClientGeoView();

		cgv.setFenixCode(wmp.getCode());
		
		cgv.setQueryable(wmp.isQueryable());
		cgv.setBBox(buildClientBBox(wmp.getBoundingBox()));
		
		// this will be useful in the future
		//cgv.setJoinable(wmp instanceof DBFeatureLayer && ((DBFeatureLayer)wmp).isJoinable() );
		cgv.setJoinable(wmp instanceof DBFeatureLayer);

		if ( wmp.getResourceId() != null)
			cgv.setLayerId(wmp.getResourceId());
		cgv.setLayerName(wmp.getLayerName());
		cgv.setGetMapUrl(wmp.getGetMapUrl());

        boolean ispd = wmp instanceof ProjectedData;
		cgv.setJoined(ispd);
		if( ispd ) {
			ProjectedData pd = (ProjectedData)wmp;
			cgv.setRelatedDatasetId(pd.getDataset().getResourceId());
		}

        // settings that may be overwritten by geoview >>>>>>>>>>>>>>>>>>>>>>>>>
		cgv.setTitle(wmp.getTitle());
//		cgv.setCurrFormat(wmp.getCurrentFormat());
//		cgv.setFormat(wmp.getCurrentFormat());
		// cgv.setHidden(wmp.isHidden());
		// cgv.setId(wmp.getId());
		// cgv.setPosition(wmp.getPosition());
		cgv.setStyleName(wmp.getDefaultStyle());
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		// mapping layer types
		LayerType  ltype = ClientGeoView.LayerType.UNDEF;
		VectorType vtype = ClientGeoView.VectorType.UNDEF;

		switch(wmp.getLayerType()) {
			case WMSMapProvider.TYPE_VECTOR_GENERIC:
				ltype = ClientGeoView.LayerType.VECTOR;
				vtype = ClientGeoView.VectorType.POINT; // fixme
				break;
			case WMSMapProvider.TYPE_VECTOR_POINT:
				ltype = ClientGeoView.LayerType.VECTOR;
				vtype = ClientGeoView.VectorType.POINT;
				break;
			case WMSMapProvider.TYPE_VECTOR_LINE:
				ltype = ClientGeoView.LayerType.VECTOR;
				vtype = ClientGeoView.VectorType.LINE;
				break;
			case WMSMapProvider.TYPE_VECTOR_POLY:
				ltype = ClientGeoView.LayerType.VECTOR;
				vtype = ClientGeoView.VectorType.POLY;
				break;
			case WMSMapProvider.TYPE_RASTER:
				ltype = ClientGeoView.LayerType.RASTER;
				break;
			case WMSMapProvider.TYPE_EXTERNAL:
				ltype = ClientGeoView.LayerType.EXTERNAL;
				break;
			default:
				ltype = ClientGeoView.LayerType.UNDEF;
		}
		cgv.setLayerType(ltype);
		cgv.setVectorType(vtype);

		return cgv;
	}

	static public ClientBBox buildClientBBox(BoundingBox bbox) {
		ClientBBox cbb = new ClientBBox();
		
		cbb.setSrs(bbox.getSrs());
		cbb.setMaxLat(bbox.getMaxY());
		cbb.setMaxLon(bbox.getMaxX());
		cbb.setMinLat(bbox.getMinY());
		cbb.setMinLon(bbox.getMinX());

		return cbb;
	}

}
