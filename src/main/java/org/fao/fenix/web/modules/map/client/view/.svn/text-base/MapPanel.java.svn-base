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

package org.fao.fenix.web.modules.map.client.view;

import org.fao.fenix.web.modules.haiti.client.view.HaitiMapTabItem;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAMapPanel;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
//import org.fao.fenix.web.modules.amis.client.view.map.AMISMapPanel;
import org.fao.fenix.web.modules.amis.client.view.map.AMISPanel;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/** 
 * This Panel contains the OpenLayers object and the related toolbar controlled by fenix.
 */
public class MapPanel {
	
	private ContentPanel mapPanel = new ContentPanel();
	private final String mapDivId;
	private final Long parentwmID;
	private ToolBar toolbar;
	
	private int layers = 0;
	
	private LayerVO selectedLayer;
	
	private Boolean isMapCreated = false;
	
	private ClientBBox currentBoundingBox = new ClientBBox();


	public MapPanel(MapWindow parent) {
					
		mapDivId = "ol_map" + parent.getMapWindowId();
		parentwmID = parent.getMapWindowId();
		mapPanel.setId(mapDivId);				
		mapPanel.setHeaderVisible(false);
		
		toolbar = MapPanelControls.buildToolbar(parent);
	}
	
	public MapPanel(HaitiMapTabItem haitiMapTabItem, String language) {
		
		mapDivId = "ol_map" + haitiMapTabItem.getHaitiOLPanel().getMapWindowId();
		parentwmID = haitiMapTabItem.getHaitiOLPanel().getMapWindowId();
		mapPanel.setId(mapDivId);				
		mapPanel.setHeaderVisible(false);
		
		toolbar = MapPanelControls.buildToolbar(haitiMapTabItem, language);
//		mapPanel.setTopComponent(toolbar);
	}
	
	public MapPanel(HaitiCNSAPanel haitiCNSAPanel, HaitiCNSAMapPanel haitiCNSAMapPanel, String language) {
		mapDivId = "ol_map" + haitiCNSAMapPanel.getHaitiOLPanel().getMapWindowId();
		parentwmID = haitiCNSAMapPanel.getHaitiOLPanel().getMapWindowId();
		mapPanel.setId(mapDivId);				
		mapPanel.setHeaderVisible(false);
//		toolbar = MapPanelControls.buildToolbar(haitiCNSAPanel, language);
		
		System.out.println("mapDivId: " + mapDivId );
		System.out.println("parentwmID: " + parentwmID );
	}
	
//	public MapPanel(AMISMapPanel amisMapPanel, String language) {
//		mapDivId = "ol_map" + amisMapPanel.getAmisOLPanel().getMapWindowId();
//		parentwmID = amisMapPanel.getAmisOLPanel().getMapWindowId();
//		mapPanel.setId(mapDivId);				
//		mapPanel.setHeaderVisible(false);
////		toolbar = MapPanelControls.buildToolbar(haitiCNSAPanel, language);
//		
//		System.out.println("mapDivId: " + mapDivId );
//		System.out.println("parentwmID: " + parentwmID );
//	}
	
	public MapPanel(long mapWindowId, String language) {
		mapDivId = "ol_map" + mapWindowId;
		parentwmID = mapWindowId;
		mapPanel.setId(mapDivId);				
		mapPanel.setHeaderVisible(false);
		System.out.println("mapDivId: " + mapDivId);
	}

	public ContentPanel getGUI()
	{
		return mapPanel;
	}
	
	
					
	public Boolean getIsMapCreated() {
		return isMapCreated;
	}

	/**
	 * Put into the ClientMapView the set of data extracted at build time,
	 * with their current values.
	 */
	public void collectCMVData(ClientMapView cmv) {

		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
		System.out.println("MApPanel::collectCMVData " + bbox);
		cmv.setBbox(bbox);
	}

	public void createMap(ClientMapView map)
	{
		
		ClientBBox box = map.getBbox();
		NativeOLCalls.createMap(box.getMinLon(), box.getMinLat(), 
					box.getMaxLon(), box.getMaxLat(), 
					mapDivId, parentwmID);
		
		for(ClientGeoView gview : map.getLayerList())
		{
			addLayer(gview);
		}

		// OL workaround: if first layer (OL's base layer) is hidden,
		//  OL may not properly initialize it as hidden.
		ClientGeoView firstgv = map.getLayerList().get(0);
		NativeOLCalls.layerVisibility(mapDivId, !firstgv.isHidden(), ""+firstgv.getClientId());

		NativeOLCalls.zoomToMapExtent(mapDivId);
	}
	
	public void createADAMMap(ClientMapView map, int numZoomLevels, int minScale, int maxScale)
	{
		System.out.println("CREATEMAP: " + mapDivId);
		ClientBBox box = map.getBbox();
		NativeOLCalls.createADAMMap(box.getMinLon(), box.getMinLat(), box.getMaxLon(), box.getMaxLat(), mapDivId, parentwmID, numZoomLevels, minScale, maxScale);
		
		for(ClientGeoView gview : map.getLayerList())
		{
			addLayer(gview);
		}

		// OL workaround: if first layer (OL's base layer) is hidden,
		//  OL may not properly initialize it as hidden.
		ClientGeoView firstgv = map.getLayerList().get(0);
		NativeOLCalls.layerVisibility(mapDivId, !firstgv.isHidden(), ""+firstgv.getClientId());

		NativeOLCalls.zoomToMapExtent(mapDivId);
	}
	
	public void createMapHaiti(ClientMapView map, String language)
	{
		isMapCreated = true;
		ClientBBox box = map.getBbox();
		NativeOLCalls.createMapHaiti(box.getMinLon(), box.getMinLat(), 
					box.getMaxLon(), box.getMaxLat(), 
					mapDivId, parentwmID, language);
		
		for(ClientGeoView gview : map.getLayerList())
		{
			addLayer(gview);
		}

		// OL workaround: if first layer (OL's base layer) is hidden,
		//  OL may not properly initialize it as hidden.
		ClientGeoView firstgv = map.getLayerList().get(0);
		NativeOLCalls.layerVisibility(mapDivId, !firstgv.isHidden(), ""+firstgv.getClientId());

		NativeOLCalls.zoomToMapExtent(mapDivId);
		NativeOLCalls.setMapPanel(this);
	}
	
	public void addLayer(ClientGeoView gview)
	{
		System.out.println("Map Panel addLayer Start ");
		System.out.println("Map Panel addLayer Start mapDivId "+mapDivId);
		System.out.println("Map Panel addLayer Start gview.getGetMapUrl() "+gview.getGetMapUrl());
		System.out.println("Map Panel addLayer Start gview.getTitle() "+gview.getTitle());
		System.out.println("Map Panel addLayer Start gview.getClientId() "+gview.getClientId());
			NativeOLCalls.createOLLayer(mapDivId, 
							gview.getGetMapUrl(), 
							gview.getTitle(), 
							createOLLayerOption(gview, layers>0), 
							""+gview.getClientId());

			layers++;
	}

	public void addLayer(ClientGeoView gview, int pos) {
		addLayer(gview);
		int delta = pos - layers + 1;
		if(delta != 0)
			NativeOLCalls.raiseLayer(mapDivId, ""+gview.getClientId(), delta); // fixme layer sorting direction
	}

    public void removeLayer(long gvId) {
        NativeOLCalls.removeLayer(mapDivId, ""+gvId);
		layers--;
    }
    
	
    private static String createOLLayerOption(ClientGeoView layer, boolean isBase) {
        ClientBBox box = layer.getBBox();

        String szOL = "params={"
					+ "layers: \"" + layer.getLayerName() + "\""
					+ ",transparent:" + isBase;
					
		if(layer.getLayerType() == ClientGeoView.LayerType.EXTERNAL)
			; // DO NOTHING format = ""; // default one
		else if(layer.getLayerType() == ClientGeoView.LayerType.RASTER)
			; // DO NOTHING format = ""; // TODO: force jpg?
		else // all the other ones: internal vect
			szOL += ",format: \"image/png\"";

		if(layer.getStyleName() != null)
			szOL += ",styles: \"" + layer.getStyleName() + "\"";

//						+ ",tiled: 'true'"
//						+ ",tilesOrigin: \"" + box.getMinLon() + "," + box.getMinLat() + "\""
//						+ format
		szOL += "};"
					+"options={"
						+ "visibility: " + !layer.isHidden()
						+ ",fenixMaxExtent: new $wnd.OpenLayers.Bounds(" 
								+ box.getMinLon() + "," + box.getMinLat() + "," 
								+ box.getMaxLon() + "," + box.getMaxLat() + ")"
						+ ",buffer:0" // {Integer} Used only when in gridded mode, this specifies the number of extra rows and colums of tiles on each side which will surround the minimum grid tiles to cover the map.
					+ "};";
        System.out.println("OPTIONS " + szOL);
		return szOL;
    }

	public ToolBar getToolbar()
	{
		return toolbar;
	}
	
	public void setLayerVisibility(long gvid, boolean visible)
	{
		NativeOLCalls.layerVisibility(mapDivId, visible, ""+gvid);
	}

	public void setLayerOpacity(long gvid, float opacity)
	{
		NativeOLCalls.setLayerOpacity(mapDivId, opacity, ""+gvid);
	}

	public void redrawLayer(long gvid)
	{
		NativeOLCalls.redrawLayer(mapDivId, ""+gvid);
	}
    
	public void zoomToLayer(long gvid)
	{
		NativeOLCalls.zoomToLayer(mapDivId, ""+gvid);
	}
    
	public void zoomToBBox(ClientBBox box)
    {
		NativeOLCalls.zoomToBBox(mapDivId, 
                                box.getMinLon(), box.getMinLat(), 
                                box.getMaxLon(), box.getMaxLat());    
    }
    
    public void moveLayerUp(long gvid)
    {
		NativeOLCalls.raiseLayer(mapDivId, ""+gvid, -1); // fixme layer sorting direction
    }

    public void moveLayerDown(long gvid)
    {
		NativeOLCalls.raiseLayer(mapDivId, ""+gvid, 1); // fixme layer sorting direction
    }

	public void windowMoved() {
		NativeOLCalls.updateSize(mapDivId);
	}

	public LayerVO getSelectedLayer() {
		return selectedLayer;
	}

	public void setSelectedLayer(LayerVO selectedLayer) {
		this.selectedLayer = selectedLayer;
	}

	public String getMapDivId() {
		return mapDivId;
	}
	
	public void zoomToExtend()
    {
		NativeOLCalls.zoomToMapExtent(mapDivId);   
    }
	
	public void setNumZoomLevels(int numZoomLevels)
    {
		NativeOLCalls.setNumZoomLevels(mapDivId, numZoomLevels);   
    }
	
	public void zoomToMaxExtent()
    {
		NativeOLCalls.zoomToMaxExtent(mapDivId);   
    }
	
	public ClientBBox getCurrentBoundingBox(String mapId){
		
		
//		System.out.println("\\\\\\\\\\\\\\");
//		System.out.println("getting bb: " + mapDivId);
//		System.out.println("getting bb: " +currentBoundingBox.getMinLat());
//		System.out.println("getting bb: " +currentBoundingBox.getMinLon());
//		System.out.println("getting bb: " +currentBoundingBox.getMaxLat());
//		System.out.println("getting bb: " +currentBoundingBox.getMaxLon());
//		System.out.println("\\\\\\\\\\\\\\");
		return currentBoundingBox;
	}
	
	public ClientBBox getCurrentOpenLayerBoundingBox(String mapID)
    {
//		System.out.println("getCurrentOpenLayerBoundingBox mapID: " + mapID);
//		System.out.println("here: " + currentBoundingBox.getMaxLat());
		NativeOLCalls.getCurrentBoundingBox(mapID); 
		return currentBoundingBox;
    }
	

	
	public void zoomToBBoxNotRecenter(ClientBBox box)
    {
		NativeOLCalls.zoomToBBoxNotRecenter(mapDivId, 
                                box.getMinLon(), box.getMinLat(), 
                                box.getMaxLon(), box.getMaxLat());    
    }
	
	public ClientBBox getFinalBB(String mapID) {
//		System.out.println("||||||||||||||||||||||||||");
		
		String bbox = NativeOLCalls.getBbox();
		if ( bbox == null )
			return new ClientBBox();
//		System.out.println("bbox: " + bbox + " | " +  mapID);;
		
		// parse bbox
//		bbox: left-bottom=(-75.27674509670373,16.91198512313836) right-top=(-67.52397895593845,20.5884401268537);
//		Double minx = -75.27674509670373;
//		Double miny = 26.91198512313836;
//		Double maxx = -47.52397895593845;
//		Double maxy = 21.5884401268537;
		
		String minxString = bbox.substring(bbox.indexOf("(") +1 , bbox.indexOf(","));
		bbox = bbox.substring(bbox.indexOf(",") +1 );

		String minyString = bbox.substring(0, bbox.indexOf(")"));
		bbox = bbox.substring(bbox.indexOf(")"));
		
		String maxxString = bbox.substring(bbox.indexOf("(") +1  , bbox.indexOf(","));
		bbox = bbox.substring(bbox.indexOf(",") +1 );
		
		String maxyString = bbox.substring(0, bbox.indexOf(")"));
		
//		System.out.println("minxString: " + minxString);
//		System.out.println("minyString: " + minyString);
//		System.out.println("maxxString: " + maxxString);
//		System.out.println("maxyString: " + maxyString);
		
		
		
//		currentBoundingBox.setMinLat(Double.valueOf(minyString));
//		currentBoundingBox.setMinLon(Double.valueOf(minxString));
//		currentBoundingBox.setMinLat(Double.valueOf(maxyString));
//		currentBoundingBox.setMaxLon(Double.valueOf(maxxString));
		
		ClientBBox bb = new ClientBBox();
		
		bb.setMinLat(Double.valueOf(minyString));
		bb.setMinLon(Double.valueOf(minxString));
		bb.setMaxLat(Double.valueOf(maxyString));
		bb.setMaxLon(Double.valueOf(maxxString));
		
//		System.out.println("||||||||||||||||||||||||||");
		return bb;
	}
	
	public void setBoundingBox(String bbox) {
//		System.out.println("||||||||||||||||||||||||||");
//		System.out.println("bbox: " + bbox + " | " +  mapDivId);;
		
		// parse bbox
//		bbox: left-bottom=(-75.27674509670373,16.91198512313836) right-top=(-67.52397895593845,20.5884401268537);
//		Double minx = -75.27674509670373;
//		Double miny = 26.91198512313836;
//		Double maxx = -47.52397895593845;
//		Double maxy = 21.5884401268537;
		
		String minxString = bbox.substring(bbox.indexOf("(") +1 , bbox.indexOf(","));
		bbox = bbox.substring(bbox.indexOf(",") +1 );

		String minyString = bbox.substring(0, bbox.indexOf(")"));
		bbox = bbox.substring(bbox.indexOf(")"));
		
		String maxxString = bbox.substring(bbox.indexOf("(") +1  , bbox.indexOf(","));
		bbox = bbox.substring(bbox.indexOf(",") +1 );
		
		String maxyString = bbox.substring(0, bbox.indexOf(")"));
		
//		System.out.println("minxString: " + minxString);
//		System.out.println("minyString: " + minyString);
//		System.out.println("maxxString: " + maxxString);
//		System.out.println("maxyString: " + maxyString);
		
		
		
//		currentBoundingBox.setMinLat(Double.valueOf(minyString));
//		currentBoundingBox.setMinLon(Double.valueOf(minxString));
//		currentBoundingBox.setMinLat(Double.valueOf(maxyString));
//		currentBoundingBox.setMaxLon(Double.valueOf(maxxString));
		
		ClientBBox bb = new ClientBBox();
		
		bb.setMinLat(Double.valueOf(minyString));
		bb.setMinLon(Double.valueOf(minxString));
		bb.setMaxLat(Double.valueOf(maxyString));
		bb.setMaxLon(Double.valueOf(maxxString));
		
		System.out.println("||||||||||||||||||||||||||");
		
//		setCurrentBoundingBox(bb);
	}
	
}

//class NativeOLCalls {
//	
//	public static MapPanel mapPanel;
//	
//	private static ListStore<FeatureInfoModelData> store;
//	
//	private static Grid<FeatureInfoModelData> grid;
//	
//	
//
//
//    public ListStore<FeatureInfoModelData> getStore() {
//		return store;
//	}
//
//	public static void setStore(ListStore<FeatureInfoModelData> liststore) {
//		store = liststore;
//	}
//
//	public Grid<FeatureInfoModelData> getGrid() {
//		return grid;
//	}
//
//	public static void setGrid(Grid<FeatureInfoModelData> gridfi) {
//		grid = gridfi;
//	}
//
//	public MapPanel getMapPanel() {
//		return mapPanel;
//	}
//
//	public static void setMapPanel(MapPanel panel) {
//		mapPanel = panel;
//	}
//
//	public static native void updateSize(String mapID) /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       mapObj.updateSize();
//    }-*/;
//
//    /*
//     * manage map's layers visibility
//     */
//    public static native void layerVisibility(String mapID, boolean visible, String layerId) /*-{
//       var mapObj = $doc.getElementById(mapID).map;     
//       var layer = mapObj.getLayer(layerId);
//       layer.setVisibility(visible);
//    }-*/;
//
//	public static native void setLayerOpacity(String mapID, float opacity, String layerId)  /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       var layer = mapObj.getLayer(layerId);
//       layer.setOpacity(opacity);
//    }-*/;
//
//    /*
//     * zoomToLayer
//     */
//    public static native void zoomToLayer(String mapID, String layerId) /*-{
//       var mapObj = $doc.getElementById(mapID).map;     
//       var layer = mapObj.getLayer(layerId);
//       var extent =  layer.fenixMaxExtent;
//       mapObj.zoomToExtent(extent);
//    }-*/;
//
//
//	public static LayerItem getCurrentLayer(Long mwid) {
//		System.out.println("Looking for mw " + mwid);
//		MapWindow mw = MapWindow.getMapWindow(mwid);
//		System.out.println("Found mw " + mw);
//		LayerItem li = mw.getSelectedLayer();
//		if(li == null) {
//			System.out.println("No selected layer");
//			return null;
//		}
//		System.out.println("Selected layer is " + li);
//		String layername = li.getLayerName();
//		System.out.println("Layername is " + layername);
//
//		return li;
//	}
//	
//	public static LayerVO getCurrentLayerHaiti(Long mwid) {
//		System.out.println("getCurrentLayerHaiti Looking for mw " + mwid);
//		if ( mapPanel != null){
//			System.out.println("is not null");
//		}
//		
//		if ( mapPanel == null ) 
//			System.out.println("is null");
//		
//		if ( mapPanel.getSelectedLayer() != null)
//			System.out.println("getSelectedLayer is not null" + mapPanel.getSelectedLayer().getLayerTitle());
//		else 
//			System.out.println("getSelectedLayer is null");
//		
//		LayerVO layerVo = mapPanel.getSelectedLayer();
////		layerVo.setLayerName(mapPanel.getSelectedLayer().getLayerName());
//		
//
////		layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
////		layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
//		System.out.println("Selected layer is " + layerVo.getLayerTitle() + " | " + layerVo.getLayerName() + "  |  " + layerVo.getWmsURL());
//
//		
//		return layerVo;
//	}
//
//    /* create ol map */
//    public static native void createMap(double minx, double miny, 
//										double maxx, double maxy, 
//										String mapID, Long mwID) /*-{
//       $wnd.createMap(mapID);
//
//	   // ////////////
//	   // getter of the map id for data subsetting.
//	   // ////////////
//	   $wnd.layId = [];
//	   $wnd.mapId = mapID;
//
//	   var mapObj = $doc.getElementById(mapID).map;
//	   var maxExtent= new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
//	   var maxRes=(maxx-minx)/256;
//	   //mapObj.maxResolution=maxRes;
//	   mapObj.fenixMapExtent=maxExtent;
//	   var globExtent= new $wnd.OpenLayers.Bounds(-180, -90, 180, 90);
//	   mapObj.maxExtent=globExtent;
//
//		// function needed to extract the response content (couldnt find a way to do it in java)
//		function getFeatureInfoResponse(response){
//			var html = response.responseText;
//			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::show(Ljava/lang/String;)(html);
//			// @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::showGFI(Ljava/lang/String;)(html);
//		};
//
//	   //=== support GetFeatureInfo
//		mapObj.events.register('click', mapObj, function (e) {
//			var layer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayer(Ljava/lang/Long;)(mwID);
//																  
//			if( ! layer) {
//				@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Attention", "No layer selected");
//				return;
//			}
//
//			var layername = layer.@org.fao.fenix.web.modules.map.client.control.vo.LayerItem::getLayerName()();
//			var wmsurl    = layer.@org.fao.fenix.web.modules.map.client.control.vo.LayerItem::getWMSUrl()();
//
//			@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Info", "getFeatureInfo on " + layername);
//
//			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepare(Ljava/lang/Long;)(mwID);
//			var params = {
//				REQUEST: "GetFeatureInfo",
//				EXCEPTIONS: "application/vnd.ogc.se_xml",
//				BBOX: mapObj.getExtent().toBBOX(),
//				X: e.xy.x,
//				Y: e.xy.y,
//				INFO_FORMAT: 'text/html',
//				QUERY_LAYERS: layername,
//				FEATURE_COUNT: 50,
//				Srs: 'EPSG:4326',
//				Layers: layername,
//				Styles: '',
//				WIDTH: mapObj.size.w,
//				HEIGHT: mapObj.size.h,
//				format: 'image/png'};
//
//			$wnd.OpenLayers.loadURL(wmsurl, params, this,
//									getFeatureInfoResponse,
//									getFeatureInfoResponse);
//
//			$wnd.OpenLayers.Event.stop(e);
//		});
//
//    }-*/;	
//    
//    
//    /* create ol map HAITI */
//  
//    
//   
//    public static native void createMapHaiti(double minx, double miny, 
//										double maxx, double maxy, 
//										String mapID, Long mwID, String language) /*-{
//       $wnd.createMap(mapID);
//
//	   $wnd.layId = [];
//	   $wnd.mapId = mapID;
//
//	   var mapObj = $doc.getElementById(mapID).map;
//	   var maxExtent= new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
//	   var maxRes=(maxx-minx)/256;
//	   mapObj.fenixMapExtent=maxExtent;
// 	   var globExtent= new $wnd.OpenLayers.Bounds(minx - 10, miny -10, maxx + 10, maxy + 10);
//	   mapObj.maxExtent=globExtent;
//	   
//	   // THIS IS A FIXED SCALE FOR HAITI...WE SHOULD FIND A SCALE ADAPTED TO THE BOUNDRIES
//	   mapObj.maxScale = 1/ 5000;
//	   mapObj.minScale = 1/ 15000000;
//
//		// function needed to extract the response content (couldnt find a way to do it in java)
//		function getFeatureInfoResponse(response){
//		
//			var currentLayer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayerHaiti(Ljava/lang/Long;)(mwID);
//			var fenixCode = currentLayer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getFenixCode()();
////			alert(fenixCode);
//			
//			var html = response.responseText;
//			var args = html + "~" + language + "~" + fenixCode;
//			
// 			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::showHaiti(Ljava/lang/String;)(args);
//		};
//
//	   //=== support GetFeatureInfo
//		mapObj.events.register('click', mapObj, function (e) {
//			var layer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayerHaiti(Ljava/lang/Long;)(mwID);
//													  
//			if( ! layer) {
//				@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Attention", "No layer selected");
//				return;
//			}
//
// 			var layername = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getLayerName()();
//		    var wmsurl    = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getWmsURL()();
//
//			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepareHaiti(Ljava/lang/String;)(layername);
//
//			var params = {
//				REQUEST: "GetFeatureInfo",
//				EXCEPTIONS: "application/vnd.ogc.se_xml",
//				BBOX: mapObj.getExtent().toBBOX(),
//				X: e.xy.x,
//				Y: e.xy.y,
//				INFO_FORMAT: 'text/html',
//				QUERY_LAYERS: layername,
//				FEATURE_COUNT: 50,
//				Srs: 'EPSG:4326',
//				Layers: layername,
//				Styles: '',
//				WIDTH: mapObj.size.w,
//				HEIGHT: mapObj.size.h,
//				format: 'image/png'};
//
//			$wnd.OpenLayers.loadURL(wmsurl, params, this,
//									getFeatureInfoResponse,
//									getFeatureInfoResponse);
//
//			$wnd.OpenLayers.Event.stop(e);
//		});
//
//    }-*/;	
//    
//
//    public static native void createOLLayer(String mapID, String getMapUrl,
//            String title, String optionSz, String layerId) /*-{
//       //alert(optionSz+" "+title+" "+getMapUrl);
//       eval(optionSz);
//       
//       // ////////////
//	   // getter of the layer id for data subsetting. 
//	   // ////////////
//       $wnd.layId.push(layerId);
//       
//       var wms = new $wnd.OpenLayers.Layer.WMS(title, getMapUrl, params , options); 
//       wms.id=layerId;
//       wms.buffer=0;
//       //wms.displayOutsideMaxExtent(true);
//       wms.transitionEffect='resize';
//       var mapObj = $doc.getElementById(mapID).map;     
//       mapObj.addLayer(wms);
//       //mapObj.zoomToMaxExtent();
//    }-*/;
//
//    public static native void removeLayer(String mapID, String layerId) /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       var layer = mapObj.getLayer(layerId); 
//       
//	   // //////////////
//       // removing layer id for data subsetting.
//       // //////////////
//       for(var i=0; i<$wnd.layId.length; i++){
//       		if ($wnd.layId[i] == layerId){  
//				$wnd.layId[i] = null;
//       			break;
//       		}
//       }       
//
//       layer.destroy();
//    }-*/;
//
//    public static native void raiseLayer(String mapID, String layerId, int delta) /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       var layer = mapObj.getLayer(layerId); 
//       mapObj.raiseLayer(layer, delta);
//    }-*/;
//    
//    public static native void redrawLayer(String mapID, String layerId) /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       var layer = mapObj.getLayer(layerId); 
//       layer.mergeNewParams({'random':Math.random()}); 
//       //layer.redraw();
//    }-*/;
//	
////    public native void redrawLayer(String mapIndex, String id) /*-{
////      //  alert(mapIndex+" layerID "+id);
////       var mapID = 'mappa'+mapIndex; 
////       var mapObj = $doc.getElementById(mapID).map;     
////       var layer = mapObj.getLayer(id);
////       layer.mergeNewParams({'random':Math.random()}); 
////       //layer.redraw(true);
////    }-*/;	
//    
//    public static native void zoomToMapExtent(String mapID) /*-{
//       var mapObj = $doc.getElementById(mapID).map;
//       var mapExtent=mapObj.fenixMapExtent; 
//       mapObj.zoomToExtent(mapExtent); 
//    }-*/;
//
//    public static native void zoomToBBox(String mapID,
//                                            double minx, double miny, 
//                                            double maxx, double maxy ) /*-{
//        var bbox = new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
//        var mapObj = $doc.getElementById(mapID).map;     
//        mapObj.zoomToExtent(bbox,true);
//    }-*/;
//
//}
