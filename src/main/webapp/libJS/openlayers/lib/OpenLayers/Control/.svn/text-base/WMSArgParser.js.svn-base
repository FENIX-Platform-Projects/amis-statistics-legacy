/* Copyright (c) 2006 MetaCarta, Inc., published under a modified BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/repository-license.txt 
 * for the full text of the license. */


/**
 * @class
 * 
 * @requires OpenLayers/Control.js
 */
OpenLayers.Control.ArgParser = OpenLayers.Class.create();
OpenLayers.Control.ArgParser.prototype = 
  OpenLayers.Class.inherit( OpenLayers.Control, {

    /** @type OpenLayers.LonLat */
    center: null,
    
    /** @type int */
    zoom: null,

    /** @type Array */
    layers: null,

    /**
     * @constructor
     * 
     * @param {DOMElement} element
     * @param {String} base
     */
    initialize: function(element, base) {
        OpenLayers.Control.prototype.initialize.apply(this, arguments);
    },

    /** Set the map property for the control. 
     * 
     * @param {OpenLayers.Map} map
     */
    setMap: function(map) {
		
        OpenLayers.Control.prototype.setMap.apply(this, arguments);

        //make sure we dont already have an arg parser attached
        for(var i=0; i< this.map.controls.length; i++) {
            var control = this.map.controls[i];
            if ( (control != this) &&
                 (control.CLASS_NAME == "OpenLayers.Control.ArgParser") ) {
                break;
            }
        }
        if (i == this.map.controls.length) {

            var args = OpenLayers.Util.getArgs();
			
            if (args.lat && args.lon) {
                this.center = new OpenLayers.LonLat(parseFloat(args.lon),
                                                   parseFloat(args.lat));
				
                if (args.zoom) {
                    this.zoom = parseInt(args.zoom);
                }
    
                // when we add a new baselayer to see when we can set the center
                this.map.events.register('changebaselayer', this, 
                                         this.setCenter);
                this.setCenter();
            }
    
            if (args.layers) {
				
                this.layers = args.layers;
    
                // when we add a new layer, set its visibility 
                this.map.events.register('addlayer', this, 
                                         this.configureLayers);
                this.configureLayers();
            }
        }
    },
   
    /** As soon as a baseLayer has been loaded, we center and zoom
     *   ...and remove the handler.
     */
    setCenter: function() {
        this.map.maxExtent = new OpenLayers.Bounds(-180, -90, 180, 90);
        if (this.map.baseLayer) {
            //dont need to listen for this one anymore
            this.map.events.unregister('changebaselayer', this, 
                                       this.setCenter);
                                       
            this.map.setCenter(this.center, this.zoom);
        }
    },

    /** As soon as all the layers are loaded, cycle through them and 
     *   hide or show them. 
     */
    configureLayers: function() {
		
		var aLayers = this.layers.split('||');
        if (aLayers.length) { 
            this.map.events.unregister('addlayer', this, this.configureLayers);
			
			//WEIRD TRICK TO AVOID A JS ERROR !!!!!!!
			this.map.popups = []; 
			
            for(var i=0; i < aLayers.length; i++) {
               // alert('1');
                var aLayer = aLayers[i].split('|');
                if(aLayer.length>1){
					var c = aLayer[0];
					//alert(aLayer[1]);
					//WMSinfo
					var layerName = aLayer[1];
					var url = aLayer[2];
					var params = aLayer[3];
					var wmsLayerString =  aLayer[4];
					//alert(wmsLayerString);
					//WMS request vars
					var format = '';//FORMAT
					var exceptions = '';//EXCEPTIONS
					var service = 'WMS';//SERVICE
					var version = '';//VERSION
					var request = "getMap";//REQUEST
					var styles = '';//STYLES
					
					//aWMSLayers params
					var aParams = params.split('!!');
					for(j=0;j<aParams.length;j++){
						var param = aParams[j].split('!');
						switch(param[0]){
							case 'FORMAT':
							format = param[1];
							break;
							case 'EXCEPTIONS':	
							exceptions = param[1];
							break;
							case 'VERSION':	
							version = param[1];
							break;
							case 'STYLES':	
							styles = param[1];
							break;
						};
						
					}
					//var Name= layerName;
					//var Title = layerName;
					//var Abstract = '';
					//var BoundingBox = '';
					//var LegendURL = '';
					//var queryable = false;
					
					
					
					
					
					var layerTitle = '';
					//wmsLayerString
						

	                if (c == "B") {
						
						

						var layer = new OpenLayers.Layer.WMS( layerName,
						 url, {layers: layerName, EXCEPTIONS: exceptions} );
						
						layer.aWMSLayers = [];
						aWMSlayer = unescape(wmsLayerString).split('$$$');
						for(u=0;u<aWMSlayer.length;u++){
							if(aWMSlayer[u].length)layer.aWMSLayers.push(aWMSlayer[u].split('@')); 
							//alert(wmsLayer);
						}
						
						//if(!layer.aWMSLayers) layer.aWMSLayers = [];
						//layer.aWMSLayers.push(Name,Title,Abstract,BoundingBox,LegendURL);
						layer.WMSinfo = [layerName,url,params,layer.aWMSLayers];
						this.map.addLayer(layer);
						//alert('22');
	                    this.map.setBaseLayer(layer);
						//alert('23');
						
	                } else if ( (c == "T") || (c == "F") ) {
						
						var layer = new OpenLayers.Layer.WMS( layerName, 
	                    url, {layers: layerName,format: format,TRANSPARENT: "TRUE", EXCEPTIONS: exceptions} );
						
						layer.aWMSLayers = [];
						aWMSlayer = unescape(wmsLayerString).split('$$$');
						for(u=0;u<aWMSlayer.length;u++){
							if(aWMSlayer[u].length)layer.aWMSLayers.push(aWMSlayer[u].split('@')); 
							//alert(aWMSlayer[u].split('@'));
						}
						

						layer.WMSinfo = [layerName,url,params,aWMSlayer];
						layer.isBaseLayer = false; 
						layer.setVisibility(c == "T");
						this.map.addLayer(layer);
	                    
						
	                }
					
				}	
	        }
				
			//var bounds = new OpenLayers.Bounds(-180,-87,90,180); 
					
			//this.map.zoomToExtent(bounds);
			
        }
    },     
    
    /** @final @type String */
    CLASS_NAME: "OpenLayers.Control.ArgParser"
});
