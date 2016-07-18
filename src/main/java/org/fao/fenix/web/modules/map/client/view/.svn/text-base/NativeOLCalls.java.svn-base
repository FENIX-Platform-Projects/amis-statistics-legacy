package org.fao.fenix.web.modules.map.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.haiti.common.vo.FeatureInfoModelData;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class NativeOLCalls {
	
		public static String bbox;
		
		public static MapPanel mapPanel;
		
		private static ListStore<FeatureInfoModelData> store;
		
		private static Grid<FeatureInfoModelData> grid;
		
		private static Float lon;
		
		private static Float lat;
		
		public static String getBbox() {
			return bbox;
		}
		
		public static void setBbox(String bbox) {
			NativeOLCalls.bbox = bbox;
		}


	    public ListStore<FeatureInfoModelData> getStore() {
			return store;
		}

		public static void setStore(ListStore<FeatureInfoModelData> liststore) {
			store = liststore;
		}

		public Grid<FeatureInfoModelData> getGrid() {
			return grid;
		}

		public static void setGrid(Grid<FeatureInfoModelData> gridfi) {
			grid = gridfi;
		}

		public MapPanel getMapPanel() {
			return mapPanel;
		}

		public static void setMapPanel(MapPanel panel) {
			mapPanel = panel;
		}
		
//		public static void addPopup(String mapID, String lon, String lat) {
//			System.out.println("ADDING POP UP: " + lon + " | " + lat);
////		NativeOLCalls.addPopupOL(mapID, lon, lat);
//		}
		
		public static void setLonLat(String lonLat) {
			System.out.println("setLonLat: " + lonLat);
			List<String> lonLatList = getLonLat(lonLat);
			Float lonf = Float.valueOf(lonLatList.get(0));
			Float latf = Float.valueOf(lonLatList.get(1));
			lon = lonf;
			lat = latf;
			System.out.println("lon: " + lon);
			System.out.println("lat: " + lat);
		}
		
		public static void addPopup(String mapID, Float lon, Float lat, String html) {
			System.out.println("ADDING POP UP: " + mapID + " | " + lon + " | " + lat);
			System.out.println("HTML: " + html);
			if ( html.equals("")) {
				FenixAlert.info("Attention", "No additional info on the selected polygon");
			}
			else
				NativeOLCalls.addPopupOL(mapID, lon, lat, html);
		}
		
		public static void addPopup(String mapID, String lonLat, String html) {
			List<String> lonLatList = getLonLat(lonLat);
			Float lon = Float.valueOf(lonLatList.get(0));
			Float lat = Float.valueOf(lonLatList.get(1));
			System.out.println("ADDING POP UP: " + lon + " | " + lat);
	   		NativeOLCalls.addPopupOL(mapID, lon, lat, "HTML");
		}
		
		private static List<String> getLonLat(String lonLat) {
			List<String> result = new ArrayList<String>();
//			System.out.println("1) " + lonLat.indexOf("lon=") + 4);
			String lon = lonLat.substring(lonLat.indexOf("lon=") + 4, lonLat.indexOf(","));
//			System.out.println("2) " + lonLat.indexOf(",lat=") + 5 );
			String lat = lonLat.substring(lonLat.indexOf(",lat=") + 5 );
			System.out.println("LON: " + lon);
			System.out.println("LAT: " + lat);
			result.add(lon);
			result.add(lat);
			return result;			
		}

		public static native void updateSize(String mapID) /*-{
	   	   var mapObj = $doc.getElementById(mapID).map;
	       mapObj.updateSize();
	    }-*/;

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


		public static LayerItem getCurrentLayer(Long mwid) {
			System.out.println("Looking for mw " + mwid);
			MapWindow mw = MapWindow.getMapWindow(mwid);
			System.out.println("Found mw " + mw);
			LayerItem li = mw.getSelectedLayer();
			if(li == null) {
				System.out.println("No selected layer");
				return null;
			}
			System.out.println("Selected layer is " + li);
			String layername = li.getLayerName();
			System.out.println("Layername is " + layername);

			return li;
		}
		
		public static LayerVO getCurrentLayerHaiti(Long mwid) {
			System.out.println("getCurrentLayerHaiti Looking for mw " + mwid);
			if ( mapPanel != null){
				System.out.println("is not null");
			}
			
			if ( mapPanel == null ) 
				System.out.println("is null");
			
			if ( mapPanel.getSelectedLayer() != null)
				System.out.println("getSelectedLayer is not null" + mapPanel.getSelectedLayer().getLayerTitle());
			else 
				System.out.println("getSelectedLayer is null");
			
			LayerVO layerVo = mapPanel.getSelectedLayer();
//			layerVo.setLayerName(mapPanel.getSelectedLayer().getLayerName());
			

//			layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
//			layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
			System.out.println("Selected layer is " + layerVo.getLayerTitle() + " | " + layerVo.getLayerName() + "  |  " + layerVo.getWmsURL());

			
			return layerVo;
		}
		
		public static LayerVO getCurrentLayerADAM(Long mwid) {
			System.out.println("getCurrentLayerADAM Looking for mw " + mwid);
			if ( mapPanel != null){
				System.out.println("is not null");
			}
			
			if ( mapPanel == null ) 
				System.out.println("is null");
			
			if ( mapPanel.getSelectedLayer() != null)
				System.out.println("getSelectedLayer is not null" + mapPanel.getSelectedLayer().getLayerTitle());
			else 
				System.out.println("getSelectedLayer is null");
			
			LayerVO layerVo = mapPanel.getSelectedLayer();
//			layerVo.setLayerName(mapPanel.getSelectedLayer().getLayerName());
			

//			layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
//			layerVo.setWmsURL("http://localhost:8080/geoserver/wms");
			System.out.println("Selected layer is " + layerVo.getLayerTitle() + " | " + layerVo.getLayerName() + "  |  " + layerVo.getWmsURL());

			
			return layerVo;
		}
		
		

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
				var layer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayer(Ljava/lang/Long;)(mwID);
																	  
				if( ! layer) {
					@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Attention", "No layer selected");
					return;
				}

				var layername = layer.@org.fao.fenix.web.modules.map.client.control.vo.LayerItem::getLayerName()();
				var wmsurl    = layer.@org.fao.fenix.web.modules.map.client.control.vo.LayerItem::getWMSUrl()();


				@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Info", "getFeatureInfo on " + layername);

				@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepare(Ljava/lang/Long;)(mwID);
				var params = {
					REQUEST: "GetFeatureInfo",
					EXCEPTIONS: "application/vnd.ogc.se_xml",
					BBOX: mapObj.getExtent().toBBOX(),
					X: e.xy.x,
					Y: e.xy.y,
					INFO_FORMAT: 'text/html',
					QUERY_LAYERS: layername,
					FEATURE_COUNT: 50,
					Srs: 'EPSG:4326',
					Layers: layername,
					Styles: '',
					WIDTH: mapObj.size.w,
					HEIGHT: mapObj.size.h,
					format: 'image/png'};

				$wnd.OpenLayers.loadURL(wmsurl, params, this,
										getFeatureInfoResponse,
										getFeatureInfoResponse);

				$wnd.OpenLayers.Event.stop(e);
			});

	    }-*/;	
	    
	    
	    /* create ol map with zoom scales */
	    public static native void createADAMMap(double minx, double miny, 
											double maxx, double maxy, 
											String mapID, Long mwID, int numZoomLevels, int minScale, int maxScale) /*-{
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
		   
		  
//		   mapObj.maxScale = 1/ maxScale;
//		   mapObj.minScale = 1/ minScale;

 
		   
		    mapObj.numZoomLevels = numZoomLevels;
//  			mapObj.numZoomLevels = 1;

			// function needed to extract the response content (couldnt find a way to do it in java)
			function getFeatureInfoResponse(response){
//				alert("getFeatureInfoResponse");
				var html = response.responseText;
//				var lon = @org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::getLon()();
//				var lat = @org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::getLat()();
//				var mapID = @org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::getMapID()();
//				alert("mapID lonlat: " + mapID + " | " + lon + " | " + lat);
 
 				@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::showADAMpopup(Ljava/lang/String;)(html);
//				@org.fao.fenix.web.modules.map.client.view.NativeOLCalls::addPopup(Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;)(mapID, lon, lat, html);
//				@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::show(Ljava/lang/String;)(html);
				// @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::showGFI(Ljava/lang/String;)(html);
			};

		   //=== support GetFeatureInfo
			mapObj.events.register('click', mapObj, function (e) {


				
				var lonLat = mapObj.getLonLatFromViewPortPx(e.xy);
				
//				@org.fao.fenix.web.modules.map.client.view.NativeOLCalls::addPopup(Ljava/lang/String;Ljava/lang/String;)(mapID, lonLat.toString());

				var layer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayerADAM(Ljava/lang/Long;)(mwID);
														  
				if( ! layer) {
					@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Attention", "No layer selected");
					return;
				}

	 			var layername = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getLayerName()();
			    var wmsurl    = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getWmsURL()();


//				alert("layername-wmsurl: " + layername + " | " + wmsurl);

//				@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Info", "getFeatureInfo on " + layername);

				@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepareADAMpopup(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)(mwID, mapID, lonLat.toString());
//  			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepare(Ljava/lang/Long;)(mwID);
				var params = {
					REQUEST: "GetFeatureInfo",
					EXCEPTIONS: "application/vnd.ogc.se_xml",
					BBOX: mapObj.getExtent().toBBOX(),
					X: e.xy.x,
					Y: e.xy.y,
					INFO_FORMAT: 'text/html',
					QUERY_LAYERS: layername,
					FEATURE_COUNT: 50,
					Srs: 'EPSG:4326',
					Layers: layername,
					Styles: '',
					WIDTH: mapObj.size.w,
					HEIGHT: mapObj.size.h,
					format: 'image/png'};

				$wnd.OpenLayers.loadURL(wmsurl, params, this,
										getFeatureInfoResponse,
										getFeatureInfoResponse);




				$wnd.OpenLayers.Event.stop(e);
			});

	    }-*/;	
	    
	    
	    
	    
	    /* create ol map HAITI */
	  
	    
	   
	    public static native void createMapHaiti(double minx, double miny, 
											double maxx, double maxy, 
											String mapID, Long mwID, String language) /*-{
	       $wnd.createMap(mapID);

		   $wnd.layId = [];
		   $wnd.mapId = mapID;

		   var mapObj = $doc.getElementById(mapID).map;
		   var maxExtent= new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
		   var maxRes=(maxx-minx)/256;
		   mapObj.fenixMapExtent=maxExtent;
	 	   var globExtent= new $wnd.OpenLayers.Bounds(minx - 10, miny -10, maxx + 10, maxy + 10);
		   mapObj.maxExtent=globExtent;
		   
		   // THIS IS A FIXED SCALE FOR HAITI...WE SHOULD FIND A SCALE ADAPTED TO THE BOUNDRIES
		   mapObj.maxScale = 1/ 5000;
		   mapObj.minScale = 1/ 15000000;

			// function needed to extract the response content (couldnt find a way to do it in java)
			function getFeatureInfoResponse(response){
			
				var currentLayer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayerHaiti(Ljava/lang/Long;)(mwID);
				var fenixCode = currentLayer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getFenixCode()();
//				alert(fenixCode);
				
				var html = response.responseText;
				var args = html + "~" + language + "~" + fenixCode;
				
	 			@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::showHaiti(Ljava/lang/String;)(args);
			};

		   //=== support GetFeatureInfo
			mapObj.events.register('click', mapObj, function (e) {
				var layer = @org.fao.fenix.web.modules.map.client.view.NativeOLCalls::getCurrentLayerHaiti(Ljava/lang/Long;)(mwID);
														  
				if( ! layer) {
					@com.extjs.gxt.ui.client.widget.Info::display(Ljava/lang/String;Ljava/lang/String;)("Attention", "No layer selected");
					return;
				}

	 			var layername = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getLayerName()();
			    var wmsurl    = layer.@org.fao.fenix.web.modules.haiti.common.vo.LayerVO::getWmsURL()();

				@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::prepareHaiti(Ljava/lang/String;)(layername);

				var params = {
					REQUEST: "GetFeatureInfo",
					EXCEPTIONS: "application/vnd.ogc.se_xml",
					BBOX: mapObj.getExtent().toBBOX(),
					X: e.xy.x,
					Y: e.xy.y,
					INFO_FORMAT: 'text/html',
					QUERY_LAYERS: layername,
					FEATURE_COUNT: 50,
					Srs: 'EPSG:4326',
					Layers: layername,
					Styles: '',
					WIDTH: mapObj.size.w,
					HEIGHT: mapObj.size.h,
					format: 'image/png'};

				$wnd.OpenLayers.loadURL(wmsurl, params, this,
										getFeatureInfoResponse,
										getFeatureInfoResponse);

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
		
//	    public native void redrawLayer(String mapIndex, String id) /*-{
//	      //  alert(mapIndex+" layerID "+id);
//	       var mapID = 'mappa'+mapIndex; 
//	       var mapObj = $doc.getElementById(mapID).map;     
//	       var layer = mapObj.getLayer(id);
//	       layer.mergeNewParams({'random':Math.random()}); 
//	       //layer.redraw(true);
//	    }-*/;	
	    
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

	    /** TODO: the changing of a zoom level it doesn't work **/
	    public static native void setNumZoomLevels(String mapID, int numZoomLevels ) /*-{
	    	var mapObj = $doc.getElementById(mapID).map;     		
			mapObj.numZoomLevels = numZoomLevels;
		}-*/;
	    
	
	    public static native void zoomToMaxExtent(String mapID) /*-{
			var mapObj = $doc.getElementById(mapID).map;
	        var globExtent= new $wnd.OpenLayers.Bounds(-180, -90, 180, 90);
	       mapObj.zoomToExtent(globExtent); 
		}-*/;
	    
	    public static native void zoomToBBoxNotRecenter(String mapID,
	            double minx, double miny, 
	            double maxx, double maxy ) /*-{
			var bbox = new $wnd.OpenLayers.Bounds(minx, miny,maxx,maxy);
			var mapObj = $doc.getElementById(mapID).map;     
			mapObj.zoomToExtent(bbox,false);
		}-*/;

		public static native void getCurrentBoundingBox(String mapID) /*-{
			var mapObj = $doc.getElementById(mapID).map;     
			//alert(mapObj.getExtent().toString() + " | " + mapID);
			//@org.fao.fenix.web.modules.map.client.view.NativeOLCalls::setCurrentBoundingBox(Ljava/lang/String;Ljava/lang/Long;)(mapObj.getExtent().toString());
			@org.fao.fenix.web.modules.map.client.view.NativeOLCalls::setCurrentBoundingBox(Ljava/lang/String;Ljava/lang/String;)(mapObj.getExtent().toString(), mapID);
		}-*/;
		
		
		  public static native void addPopupOL(String mapID, float lon, float lat, String html) /*-{
//		   alert("adding popup");   
		//  alert("remove");
//		  alert("sdio[ajipuadsfjhpiusdfh");
//		  	alert("lonlat: " + lon +" | " + lat);
			  var mapObj = $doc.getElementById(mapID).map;
//			  	alert("size: " + mapObj.getImageSize());
  
  // WORKS
//			var popup = new $wnd.OpenLayers.Popup("chicken",
//                   new $wnd.OpenLayers.LonLat(lon,lat),
//                   new $wnd.OpenLayers.Size(200,200),
//                   "example popup",
//                   true);
 
 			var popup = new $wnd.OpenLayers.Popup.FramedCloud("chicken", 
                                                         new $wnd.OpenLayers.LonLat(lon,lat), 
                                                          
                                                         new $wnd.OpenLayers.Size(50,50), 
                                                         html, 
                                                        null, 
                                                         true);

//			var popup = new $wnd.OpenLayers.Popup.FramedCloud("chicken", 
//                                                         new $wnd.OpenLayers.LonLat(lon,lat), 
//                                                          
//                                                         new $wnd.OpenLayers.Size(50,50), 
//                                                         "<div style='background-color:#FFFFE5; width:auto;height:auto;'>Hellow<div>lon ="+lon+" lat ="+lat+"</div></div>", 
//                                                        null, 
//                                                         true); 
                                         
                            popup.closeOnMove = true;       
                           
                            mapObj.addPopup(popup);
                               


			  
//			var popup = new $wnd.OpenLayers.Popup.FramedCloud("featurePopup", 
//                                         new $wnd.OpenLayers.LonLat(lon,lat),
//                                          new $wnd.OpenLayers.Size(50,50),
//                                         "<b>example popup</b>",
// 										 mapObj,
//                                         null, true, null);
//             alert("addPopup3");                            

//          alert("addPopup4");             
//            mapObj.addPopup(popup);
//              alert("addPopup5");             
            
//			         mapObj.addPopup(new $wnd.OpenLayers.Popup.FramedCloud(
//		                            "chicken",
//		                             new $wnd.OpenLayers.LonLat(5,21),
//		                            null,
//		                             "test",
//		                            null,
//		                            true
//		                        ));
//
//				var markers = new $wnd.OpenLayers.Layer.Markers("Markers");
//		        mapObj.addLayer(markers);
//
//		 			var size = new $wnd.OpenLayers.Size(10,17);
//		            var offset = new $wnd.OpenLayers.Pixel(-(size.w/2), -size.h);
//
//
//				var icon = new $wnd.OpenLayers.Icon('/home/vortex/Desktop/image.png',size,offset);
//
//				markers.addMarker(new $wnd.OpenLayers.Marker(new $wnd.OpenLayers.LonLat(5,21),icon));
//				
//				var marker = new $wnd.OpenLayers.Marker(new $wnd.OpenLayers.LonLat(5,21),icon.clone());
//
//				markers.addMarker(marker); 
//
//			  
//			  
////			  var popup = new $wnd.OpenLayers.Popup.FramedCloud("chicken",
////				            new $wnd.OpenLayers.LonLat(5,21),
////				            new $wnd.OpenLayers.Size(200,200),
////				            "test",
////				            true, null);
//		//
////				mapObj.addPopup(popup);
//				
////				var markers = new wnd.OpenLayers.Layer.Markers("zibo");
////		        mapObj.addLayer(markers);
//		//
////		        var feature = new wnd.OpenLayers.Feature(layer,new wnd.OpenLayers.LonLat(5,21));
//
//
////		         var marker = feature.createMarker();
//		            
////		         var  markers.addMarker(marker);
//		         
////		         popup = feature.createPopup(true);
//		         
////		          popup.setContentHTML("<div style='background-color:red; width:150;height:100'><a href='http://www.somethingconstructive.net' target='_blank'>click me</a></div>");
////		                popup.setBackgroundColor("yellow");
////		                popup.setOpacity(0.7);
////		                markers.map.addPopup(popup);


			}-*/;
		
		public static void setCurrentBoundingBox(String boundingBox, String mapId) {
//			System.out.println("NATIVE setCurrentBoundingBox bbox: " + boundingBox + " | " + mapId);
//			mapPanel.setBoundingBox(bbox);
			bbox = boundingBox;
//			System.out.println("NATIVE setCurrentBoundingBox bbox: " + bbox + " | " + mapId);
		
		}
		

	   
	}


