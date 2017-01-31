/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Handler/Box.js
 */

/**
 * Class: OpenLayers.Control.SelectBox
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
 
var layId = [];
var mapId;
var envelopeFieldsId = [];

OpenLayers.Control.SelectBox = OpenLayers.Class(OpenLayers.Control, {
	/**  
     * Property: box
     * {OpenLayers.Handler.Box}
     */
	box: null,
		
	/** 
     * Property: bounds
     * {OpenLayers.Bounds} 
     */
    bounds: null,
    
    /**
     * Property: type
     * {OpenLayers.Control.TYPE}
     */
    type: OpenLayers.Control.TYPE_TOOL,

    /**
     * Property: out
     * {Boolean} Should the control be used for zooming out?
     */
    out: false,

	/**
     * Constructor: OpenLayers.Control.SelectBox
     * 
     * Parameters:
     * options - {Object}
     */
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.bounds = null;
        try{
			box = new OpenLayers.Handler.Box( this, {"done": this.notice});
        }catch(Exception){};
    },

    draw: function () {
	      // this Handler.Box will intercept the shift-mousedown
	      // before Control.MouseDefault gets to see it
	      this.box = new OpenLayers.Handler.Box( this,
	          {"done": this.notice},
	          {keyMask: this.keyMask});
	      this.box.activate();
	  },
	
	  notice: function (bounds) {
	      var ll = this.map.getLonLatFromPixel(new OpenLayers.Pixel(bounds.left, bounds.bottom)); 
	      var ur = this.map.getLonLatFromPixel(new OpenLayers.Pixel(bounds.right, bounds.top)); 
				
		  /*OpenLayers.Request.GET({
				url: '../SubsetDataSelectServlet?BBOX=' + 
			            ll.lon.toFixed(4) + ',' + 
			            ll.lat.toFixed(4) + ',' + 
			            ur.lon.toFixed(4) + ',' + 
			            ur.lat.toFixed(4),
				user: 'admin',
				password:'geoserver',
				callback: this.handlerGet,
				scope: this
           });
	      
	       alert("BBOX=" + 
	            ll.lon.toFixed(4) + "," + 
	            ll.lat.toFixed(4) + "," + 
	            ur.lon.toFixed(4) + "," + 
	            ur.lat.toFixed(4)); */
		  
		   var minx = ll.lon.toFixed(4); 
	       var miny = ll.lat.toFixed(4); 
	       var maxx = ur.lon.toFixed(4);	
	       var maxy = ur.lat.toFixed(4);
		   
		   if(envelopeFieldsId[0] != null){
			   
			   var minXObj = document.getElementById(envelopeFieldsId[0]);
			   var minYObj = document.getElementById(envelopeFieldsId[1]);
			   var maxXObj = document.getElementById(envelopeFieldsId[2]);
			   var maxYObj = document.getElementById(envelopeFieldsId[3]);			   
			  
			   minXObj.value = minx;
			   minYObj.value = miny;
			   maxXObj.value = maxx;
			   maxYObj.value = maxy;	   
			   
			   envelopeFieldsId = [];   
		   }else{
			   var upper = maxx+","+maxy;
			   var lower = minx+","+miny;			   
			   
			   var filter = '<Filter><BBOX><PropertyName>the_geom</PropertyName><Box srsName="http://www.opengis.net/gml/srs/epsg.xml#4326"><coordinates>'+lower+' '+upper+'</coordinates></Box></BBOX></Filter>';
	
			   var mapObj = document.getElementById(mapId).map; 
			   for(var i=0; i<layId.length; i++){	  				
				   if (layId[i] != null){
						var lay = mapObj.getLayer(layId[i]);		
						lay.mergeNewParams({filter:filter});
						lay.redraw(); 
				   }			
			   } 
		   }		   
	  },
      
	  /*handlerGet: function(req) {
    	var response;
    	if (req.readyState == 4 && req.status == 200) {
    		if (req.responseXML != null)
					response = req.responseXML;
				else
					response = req.responseText;
					
				//alert(response);
			}
	  },*/
		
    CLASS_NAME: "OpenLayers.Control.SelectBox"
});
