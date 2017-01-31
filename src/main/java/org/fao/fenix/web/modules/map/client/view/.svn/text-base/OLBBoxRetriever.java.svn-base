package org.fao.fenix.web.modules.map.client.view;

import org.fao.fenix.web.modules.map.common.vo.ClientBBox;

public class OLBBoxRetriever {

	private double x0,x1,y0,y1;

	public OLBBoxRetriever(String mapId) {
		getBBox(mapId);
	}

	public ClientBBox getBbox() {
		ClientBBox bbox = new ClientBBox();
		bbox.setSrs("EPSG:4326"); // fixme
		bbox.setMinLon(x0);
		bbox.setMinLat(y0);
		bbox.setMaxLon(x1);
		bbox.setMaxLat(y1);
		return bbox;
	}

    private native void getBBox(String mapID) /*-{
        var mapObj = $doc.getElementById(mapID).map;
	    var bbox = mapObj.getExtent();  
        var arr = bbox.toArray(); // {Array} array of left, bottom, right, top
        this.@org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever::x0 = arr[0];
        this.@org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever::y0 = arr[1];
        this.@org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever::x1 = arr[2];
        this.@org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever::y1 = arr[3];
	 }-*/;
    
}