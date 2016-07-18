/*
 *  Copyright (C) 2007-2008 Food and Agriculture Organization of the
 *  United Nations (FAO-UN)
 * 
 * 	This program is free software; you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation; either version 2 of the License, or (at
 * 	your option) any later version.
 * 
 * 	This program is distributed in the hope that it will be useful, but
 * 	WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * 	General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package org.fao.fenix.web.modules.cfs.client.view;


import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.Counter;

import com.extjs.gxt.ui.client.widget.ContentPanel;


/** 
 * This Panel contains the OpenLayers object and the related toolbar controlled by fenix.
 *
 * @author ETj
 */
public class CFSMapPanel {
	
	private ContentPanel mapPanel;
//	private ToolBar toolbar;

	private static Counter uniqIdGenerator = new Counter(100);
	private final long uniqId = uniqIdGenerator.getNext();

	private final static ClientBBox initialBBox = new ClientBBox("EPSG:4326", -180, 180, -90, 90);
	private final static String serverURL = "http://localhost:8080/geoserver/wms?";
	private final static String layerName = "layer_cfsgaul0";

	public CFSMapPanel() {
		mapPanel = new ContentPanel();
		mapPanel.setId(getUniqId());
		mapPanel.setHeaderVisible(false);
		
//		toolbar = MapPanelControls.buildToolbar(parent);
	}

	public String getUniqId() {
		return "CFSMAP_" + uniqId;
	}

	public ContentPanel getGUI()
	{
		return mapPanel;
	}
					
	public void createMap()
	{		
		CFSNativeOLCalls.createMap(initialBBox.getMinLon(), initialBBox.getMinLat(),
					initialBBox.getMaxLon(), initialBBox.getMaxLat(),
					getUniqId(), -9999L);
		
		CFSNativeOLCalls.createOLLayer(getUniqId(),
						serverURL,
						layerName,
						createOLLayerOption(layerName, true),
						"CFS");

		CFSNativeOLCalls.zoomToMapExtent(getUniqId());
	}	
	
    private static String createOLLayerOption(String layerName, boolean isBase) {
        ClientBBox box = initialBBox;
		String format;
//		if(layer.getLayerType() == ClientGeoView.LayerType.EXTERNAL)
//			format = ""; // default one
//		else if(layer.getLayerType() == ClientGeoView.LayerType.RASTER)
//			format = ""; // TODO: force jpg?
//		else // all the other ones: internal vect
			format = ",format: \"image/png\"";

        String szOL = 
        			"params={"
						+ "layers: \"" + layerName + "\""
						+ ",transparent:" + isBase
//						+ ",tiled: 'true'"
//						+ ",tilesOrigin: \"" + box.getMinLon() + "," + box.getMinLat() + "\""
						+ format
					+"};"
					+"options={"
						+ "visibility: true" 
						+ ",fenixMaxExtent: new $wnd.OpenLayers.Bounds(" 
								+ box.getMinLon() + "," + box.getMinLat() + "," 
								+ box.getMaxLon() + "," + box.getMaxLat() + ")"
						+ ",buffer:0" // {Integer} Used only when in gridded mode, this specifies the number of extra rows and colums of tiles on each side which will surround the minimum grid tiles to cover the map.
					+ "};";
        return szOL;
    }

	
	public void setLayerVisibility(long gvid, boolean visible)
	{
		CFSNativeOLCalls.layerVisibility(getUniqId(), visible, ""+gvid);
	}

	public void setLayerOpacity(long gvid, float opacity)
	{
		CFSNativeOLCalls.setLayerOpacity(getUniqId(), opacity, ""+gvid);
	}

	public void redrawLayer(long gvid)
	{
		CFSNativeOLCalls.redrawLayer(getUniqId(), ""+gvid);
	}
    
	public void zoomToLayer(long gvid)
	{
		CFSNativeOLCalls.zoomToLayer(getUniqId(), ""+gvid);
	}
    
	public void zoomToBBox(ClientBBox box)
    {
		CFSNativeOLCalls.zoomToBBox(getUniqId(),
                                box.getMinLon(), box.getMinLat(), 
                                box.getMaxLon(), box.getMaxLat());    
    }
    
    public void moveLayerUp(long gvid)
    {
		CFSNativeOLCalls.raiseLayer(getUniqId(), ""+gvid, -1); // fixme layer sorting direction
    }

    public void moveLayerDown(long gvid)
    {
		CFSNativeOLCalls.raiseLayer(getUniqId(), ""+gvid, 1); // fixme layer sorting direction
    }
    
}

class CFSNativeOLCalls {
    /*
     * manage map's layers visibility
     */
    public static native void layerVisibility(String mapID, boolean visible, String layerId) /*-{
       var mapObj = $doc.getElementById(mapID).map;     
       var layer = mapObj.getLayer(layerId);
       layer.setVisibility(visible);
    }-*/;

	public static native void setLayerOpacity(String mapID, float opacity, String layerId)  /*-{
       var mapObj = $doc.getElementById(mapID).map;
       var layer = mapObj.getLayer(layerId);
       layer.setOpacity(opacity);
    }-*/;

    /*
     * zoomToLayer
     */
    public static native void zoomToLayer(String mapID, String layerId) /*-{
       var mapObj = $doc.getElementById(mapID).map;     
       var layer = mapObj.getLayer(layerId);
       var extent =  layer.fenixMaxExtent;
       mapObj.zoomToExtent(extent);
    }-*/;

    /* create ol map */
    public static native void createMap(double minx, double miny, 
										double maxx, double maxy, 
										String mapID, Long mwID) /*-{
       $wnd.createMap(mapID);

	   // ////////////
	   // getter of the map id for data subsetting.
	   // ////////////
	   $wnd.layId = [];
	   $wnd.mapId = mapID;

	   var mapObj = $doc.getElementById(mapID).map;
	   var maxExtent= new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
	   var maxRes=(maxx-minx)/256;
	   //mapObj.maxResolution=maxRes;
	   mapObj.fenixMapExtent=maxExtent;
	   var globExtent= new $wnd.OpenLayers.Bounds(-180, -90, 180, 90);
	   mapObj.maxExtent=globExtent;

		// function needed to extract the response content (couldnt find a way to do it in java)
		function getFeatureInfoResponse(response){
			var html = response.responseText;
			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::show(Ljava/lang/String;)(html);
			// @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::showGFI(Ljava/lang/String;)(html);
		};

	   //=== support GetFeatureInfo
		mapObj.events.register('click', mapObj, function (e) {
			$wnd.OpenLayers.Event.stop(e);
		});

    }-*/;	

    public static native void createOLLayer(String mapID, String getMapUrl,
            String title, String optionSz, String layerId) /*-{
       //alert(optionSz+" "+title+" "+getMapUrl);
       eval(optionSz);
       
       // ////////////
	   // getter of the layer id for data subsetting. 
	   // ////////////
       $wnd.layId.push(layerId);
       
       var wms = new $wnd.OpenLayers.Layer.WMS(title, getMapUrl, params , options); 
       wms.id=layerId;
       wms.buffer=0;
       //wms.displayOutsideMaxExtent(true);
       wms.transitionEffect='resize';
       var mapObj = $doc.getElementById(mapID).map;     
       mapObj.addLayer(wms);
       //mapObj.zoomToMaxExtent();
    }-*/;

    public static native void removeLayer(String mapID, String layerId) /*-{
       var mapObj = $doc.getElementById(mapID).map;
       var layer = mapObj.getLayer(layerId); 
       
	   // //////////////
       // removing layer id for data subsetting.
       // //////////////
       for(var i=0; i<$wnd.layId.length; i++){
       		if ($wnd.layId[i] == layerId){  
				$wnd.layId[i] = null;
       			break;
       		}
       }       

       layer.destroy();
    }-*/;

    public static native void raiseLayer(String mapID, String layerId, int delta) /*-{
       var mapObj = $doc.getElementById(mapID).map;
       var layer = mapObj.getLayer(layerId); 
       mapObj.raiseLayer(layer, delta);
    }-*/;
    
    public static native void redrawLayer(String mapID, String layerId) /*-{
       var mapObj = $doc.getElementById(mapID).map;
       var layer = mapObj.getLayer(layerId); 
       layer.mergeNewParams({'random':Math.random()}); 
       //layer.redraw();
    }-*/;
	
//    public native void redrawLayer(String mapIndex, String id) /*-{
//      //  alert(mapIndex+" layerID "+id);
//       var mapID = 'mappa'+mapIndex; 
//       var mapObj = $doc.getElementById(mapID).map;     
//       var layer = mapObj.getLayer(id);
//       layer.mergeNewParams({'random':Math.random()}); 
//       //layer.redraw(true);
//    }-*/;	
    
    public static native void zoomToMapExtent(String mapID) /*-{
       var mapObj = $doc.getElementById(mapID).map;
       var mapExtent=mapObj.fenixMapExtent; 
       mapObj.zoomToExtent(mapExtent); 
    }-*/;

    public static native void zoomToBBox(String mapID,
                                            double minx, double miny, 
                                            double maxx, double maxy ) /*-{
        var bbox = new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
        var mapObj = $doc.getElementById(mapID).map;     
        mapObj.zoomToExtent(bbox,true);
    }-*/;

}

