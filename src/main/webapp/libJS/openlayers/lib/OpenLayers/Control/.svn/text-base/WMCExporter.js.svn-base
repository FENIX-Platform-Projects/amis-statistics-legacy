/* Copyright (c) 2006 MetaCarta, Inc., published under a modified BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/repository-license.txt 
 * for the full text of the license. */


/**
 * @class
 * 
 * @requires OpenLayers/Control.js
 */
OpenLayers.Control.WMCExporter = OpenLayers.Class.create();
OpenLayers.Control.WMCExporter.prototype = 
  OpenLayers.Class.inherit( OpenLayers.Control, {

    /** @type DOMElement */
    element: null,
    
    /** @type String */
    base: '',

    /**
     * @constructor
     * 
     * @param {DOMElement} element
     * @param {String} base
     */
    initialize: function(element, base) {
        OpenLayers.Control.prototype.initialize.apply(this, arguments);
        this.element = OpenLayers.Util.getElement(element);        
        if (base) {
            this.base = base;
        }
    },

    /**
     * 
     */
    destroy: function()  {
        if (this.element.parentNode == this.div) {
            this.div.removeChild(this.element);
        }
        this.element = null;

        this.map.events.unregister('moveend', this, this.updateLink);

        OpenLayers.Control.prototype.destroy.apply(this, arguments); 
    },

    /** Set the map property for the control. 
     * 
     * @param {OpenLayers.Map} map
     */
    setMap: function(map) {
        OpenLayers.Control.prototype.setMap.apply(this, arguments);

        //make sure we have an arg parser attached
        for(var i=0; i< this.map.controls.length; i++) {
            var control = this.map.controls[i];
            if (control.CLASS_NAME == "OpenLayers.Control.ArgParser") {
                break;
            }
        }
        if (i == this.map.controls.length) {
            this.map.addControl(new OpenLayers.Control.ArgParser());       
        }

    },

    /**
     * @type DOMElement
     */    
    draw: function() {
        OpenLayers.Control.prototype.draw.apply(this, arguments);
          
        if (!this.element) {
            this.div.className = this.displayClass;
            this.element = document.createElement("a");
            this.element.style.fontSize="smaller";
            this.element.innerHTML = "WMC Exporter";
            this.element.href="";
			this.element.onclick = function() {
									//alert(this.outstring);
									var newwindow;
									//Encode the xml inside the error message
									encodedHtml = this.outstring;
							    	encodedHtml = encodedHtml.replace(/</g,"&lt;");
							    	encodedHtml = encodedHtml.replace(/>/g,"&gt;"); 
									//popup a message windows to let the use copy the error
									newwindow=window.open('','','menubar=1,scrollbars=1,width=700,height=400,resizable=1') ;
									//menubar=1,location=1,status=1,
									newwindow.document.writeln('<html> <head> <title>Error Parsing GetCapabilties<\/title> <\/head> <body><pre><code>');
									newwindow.document.writeln(encodedHtml);
									newwindow.document.writeln('<\/code><\/pre> <\/body> <\/html>');
									newwindow.document.close();
									return false;
								};
            this.div.appendChild(this.element);
        }
        this.map.events.register('moveend', this, this.updateLink);
        return this.div;
    },
   
    /**
     * 
     */
    updateLink: function() {
        var center = this.map.getCenter();
        var zoom = "zoom=" + this.map.getZoom(); 
        var lat = "lat=" + Math.round(center.lat*100000)/100000;
        var lon = "lon=" + Math.round(center.lon*100000)/100000;

        var layers = "layers=";
        /*for(var i=0; i< this.map.layers.length; i++) {
            var layer = this.map.layers[i];

            if (layer.isBaseLayer) {
                layers += (layer == this.map.baseLayer) ? "B" : "0";
            } else {
                layers += (layer.getVisibility()) ? "T" : "F";           
            }
        }*/
		
		//OpenLayers last build: http://www.openlayers.org/api/OpenLayers.js
		//WMSManager nightly build: http://www.ominiverdi.org/openlayers/nightly/openlayers/build/OpenLayers.js
		var header = '{\n';
		//View Context
  		header +=		'\t"id":"Map3",\n';
  		header +=		'\t"version":"1.1",\n';
		
		//General Section	
		
		var mapBounds = this.map.getExtent();
		
		header +=		'\t"General":{\n';
		
		header +=		'\t\t"title":"Map3",\n';
  		header +=		'\t\t"abstract":"Long description of my map",\n';
  		header +=		'\t\t"KeywordList":"",\n';
  		header +=		'\t\t"BoundingBox":{"minX":' + mapBounds.left + ',"minY":' + mapBounds.bottom + ',"maxX":' + mapBounds.right + ',"maxY":' + mapBounds.top + ',"srs":"' + this.map.projection + '"},\n';
  		header +=		'\t\t"Window":{"width":' + this.map.size.w + ',"height":' + this.map.size.h + '},\n';
		header +=		'\t\t\n';
		header +=		'\t\t"ContactInformation":{}\n';
		header +=		'\t\t\n';
		header +=		'\t\t"LogoURL":null,\n';
		header +=		'\t\t\n';
		header +=		'\t\t"DescriptionURL":null,\n';
		header +=		'\t\t\n';
		
		header +=		'\t},\n';
		var layers = '\t"LayerList":[\n';
		
		for(var i=0; i< this.map.layers.length; i++) {
            var layer = this.map.layers[i];
			layers += '\t\t{ \n';
			
			var serverUrl = layer.WMSinfo[1];
			 
			var aParams = layer.WMSinfo[2].split('!!'); 
			var srs = null;
			var format = null;	
			var transparent = null;
			var exception = null;
			var service = null;
			var version = null;
			var styles = null;
			
			for(y=0;y<aParams.length;y++){
				var keyVal = aParams[y].split('!');
				if(keyVal[0]){
					if(keyVal[0]=='TRANSPARENT') {
						if(keyVal[1]=='TRUE') transparent = 'true';
						else    transparent = 'false';
					}
					if(keyVal[0]=='FORMAT') {
						format = keyVal[1];
					}
					if(keyVal[0]=='SRS') {
						srs = keyVal[1];
					}
					if(keyVal[0]=='SERVICE') {
						service = keyVal[1];
					}
					if(keyVal[0]=='VERSION') {
						version = keyVal[1];
					}
					if(keyVal[0]=='STYLES') {
						styles = keyVal[1];
					}
				}
			}
			
			
			layers += '\t\t"name":"'+ layer.WMSinfo[0] + '",\n';
			layers += '\t\t"label":"'+ layer.WMSinfo[0] + '",\n';
			layers += '\t\t"description":"'+ layer.WMSinfo[0] + '",\n';
			layers += '\t\t"SRS":"' + srs + '",\n';
			
			layers += '\t\t"queryable":false,\n';
			layers += '\t\t"resourceId":9,\n';
			layers += '\t\t"hidden":false,\n';
			layers += '\t\t"server":{ "service":"WMS", "title":"WMSServer", "onlineResource":{"href":"'+ layer.WMSinfo[1] + '","type":"type1"}, "id":1, "version":"1.1.1"},\n';
			
			layers += '\t\t"FormatList":[],\n';//Describe one output image format for the Layer. 
			layers += '\t\t"MetadataURL":[],\n';
			layers += '\t\t"StyleTypeList":[],\n';
			
			
			layers += '\t\t"transparent":'+transparent+',\n';//?!?? does it exists in mapcontext?
			//layers += '\t\t\n';
			//layers += '\t\t\n';
			layers += '\t\t},\n';
			/*layers += 			layer.WMSinfo[0] + '", "';
			layers += 	layer.WMSinfo[1];
			layers += 		'",{';
			var aParams = layer.WMSinfo[2].split('!!'); 	
			for(y=0;y<aParams.length;y++){
				var keyVal = aParams[y].split('!');
				if(keyVal[0]){
					if(keyVal[0]=='TRANSPARENT') {
						if(keyVal[1]=='TRUE')layers +=  'TRANSPARENT : true';
						else layers +=  'TRANSPARENT : false';
					}
					else layers +=  keyVal[0] + ': \'' + keyVal[1] + '\'';
					if(y<aParams.length-1) layers += ',';
				}
			}*/
			
			/*layers += '\n\t\t map.addLayer(layer' + i + ');';	
			if (layer.isBaseLayer) {
				layers += '\n\t\t map.setBaseLayer(layer' + i + ');';	
			} else {
				layers += '\n\t\t layer' + i + '.setVisibility('+layer.getVisibility() +' );';
			}*/
			
        }
       
	    layers +='\t],\n';
		
		var footer = '}';
		
		this.element.outstring = header + layers + footer;
		
    }, 

    /** @final @type String */
    CLASS_NAME: "OpenLayers.Control.WMCExporter"
});
