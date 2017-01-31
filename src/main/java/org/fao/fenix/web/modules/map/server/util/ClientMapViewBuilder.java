/*
 */
package org.fao.fenix.web.modules.map.server.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;

/**
 * Class ClientMapViewBuilder
 *
 * @author etj
 */
public class ClientMapViewBuilder {

	public static ClientMapView build(MapView mapView) {
		ClientMapView cmv = new ClientMapView(FenixResourceBuilder.build(mapView));

		List<GeoView> gvList = mapView.getGeoViewList();
		Collections.sort(gvList, new CGVComparator());

		for (GeoView geoView : gvList) {
			cmv.addLayer(ClientGeoViewLoader.buildClientResource(geoView));
		}

		cmv.setBbox(ClientGeoViewLoader.buildClientBBox(mapView.getBoundingBox()));

		return cmv;
	}
}

class CGVComparator implements Comparator<GeoView> {

	@Override
	public int compare(GeoView o1, GeoView o2) {
		if(o1.getPosition().intValue() < o2.getPosition().intValue())
			return -1;
		else if(o1.getPosition().intValue() > o2.getPosition().intValue())
			return 1;
		else {// should not happen, however lets give a fallback check
			System.out.println("ERROR: GVs have same position: " + o1 + " -- " + o2);
			return (o1.getId() < o2.getId())? -1 : 1;
		}
	}


}