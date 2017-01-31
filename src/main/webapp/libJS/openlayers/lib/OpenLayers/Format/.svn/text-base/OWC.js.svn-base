/* Copyright (c) 2006-2007 MetaCarta, Inc., published under a modified BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/repository-license.txt 
 * for the full text of the license. */

/**
 * @author Julien-Samuel Lacroix
 * @author Lorenzo Becchi
 * @author Tim Schaub
 * 
 * @requires OpenLayers/Format/XML.js
 *
 * Class: OpenLayers.Format.OWC
 * Read/Wite Web Map Context (OWC). Create a new instance with the <OpenLayers.Format.OWC>
 *     constructor.  Supports the OGC OWC  version 0.2.1
 * 
 * Inherits from:
 *  - <OpenLayers.Format>
 */
OpenLayers.Format.OWC = OpenLayers.Class(OpenLayers.Format.XML, {
    
  
    /**
     * Constant: OWC_NS
     * {String} default Name Space for OGC OWC version 1.1
     */
	//OWC_NS: 'http://www.opengeospatial.net/context',//http://www.opengeospatial.net/context
	
    /**
     * Constructor: OpenLayers.Format.GML
     * Create a new parser for GML.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be set on
     *     this instance.
     */
    initialize: function(options) {
        // compile regular expressions once instead of every time they are used
        this.regExes = {
            trimSpace: (/^\s*|\s*$/g),
            removeSpace: (/\s*/g),
            splitSpace: (/\s+/),
            trimComma: (/\s*,\s*/g)
        };
        OpenLayers.Format.XML.prototype.initialize.apply(this, [options]);
    },

    /**
     * APIMethod: read
     * Read data from a XML DOM Document, and return a OLContext object. 
     * 
     * Parameters:
     * XML DOM - {DOMDocument} XML DOM of a OGC OWC Document.
     *
     * Returns:
     * {<OpenLayers.Format.OLON>}  the OpenLayers OLON object.
     */
	
    read: function(data) {
		
		var olon = new OpenLayers.Format.OLON();
		
		console.log('start read');
		var OLContext = {};
		OLContext.options = {};
		
        if(typeof data == "string") { 
            data = OpenLayers.Format.XML.prototype.read.apply(this, [data]);
        }
		
		var context = this.findChildByName(data,'OWSContext');

		
		//PARSE MAP
		console.log('parse map');
													   
		OLContext.id = context.getAttribute('id');
		console.log('id: ' + OLContext.id);
		OLContext.version = context.getAttribute('version');
		console.log('version: ' + OLContext.version);
		OLContext.CLASS_NAME = "OpenLayers.Map";
		
		//PARSE GENERAL OBJECT
		//choosen attributes:
		//"BoundingBox":{"minX":-281.25,"minY":-166.9921875,"maxX":281.25,"maxY":166.9921875,"srs":"EPSG:4326"},
		//"Window":{"width":800,"height":475},
		
		console.log('parse general');
		OLContext.general = {};
		var general = this.findChildByName(context,'General');
		if(general){
			OLContext.general.title = this.concatChildValues(this.findChildByName(general,'ows:Title'));
			
			//BBOX -> center & resolution
			/*
				<ows:BoundingBox crs="EPSG:4326">
				<ows:LowerCorner>-71.1485566986829 42.2593928033786</ows:LowerCorner>
				<ows:UpperCorner>-71.0016725358029 42.4399863588876</ows:UpperCorner>
				</ows:BoundingBox>
			 */
			var bbox = this.findChildByName(general,'ows:BoundingBox');
			var lowerCorner = this.concatChildValues(this.findChildByName(bbox,'ows:LowerCorner'));
			var minx = lowerCorner.split(' ')[0];
			var miny = lowerCorner.split(' ')[1];
			var upperCorner = this.concatChildValues(this.findChildByName(bbox,'ows:UpperCorner'));
			var maxx = upperCorner.split(' ')[0];
			var maxy = upperCorner.split(' ')[1];
			var srs = bbox.getAttribute('crs');
			
			console.log(minx);
			var bounds = new OpenLayers.Bounds.fromArray([minx,miny,maxx,maxy]);
			console.log(bounds);
			OLContext.center = bounds.getCenterLonLat();
			OLContext.center.CLASS_NAME = "OpenLayers.LonLat";
			OLContext.resolution =  78271.51695;
			OLContext.bounds = bounds;
			OLContext.projection = srs;
		}
		//OLContext.general.title
		
		
		//PARSE LAYERLIST
		console.log('parse layer list');
		OLContext.layers = [];
		var resourcelist = this.findChildByName(context,'ResourceList');
		//var layerlist = this.findChildByName(resourcelist,'LayerList');
		var layers = this.findChildrenByName(resourcelist,'Layer');
		for(i=0;i<layers.length;i++){
			layer = layers[i];
			console.log('layer: '+i);
			//getting porperties values
			var name = this.concatChildValues(this.findChildByName(layer,'ows:Identifier'));
			var title = this.concatChildValues(this.findChildByName(layer,'ows:Title'));
			var srs = this.concatChildValues(this.findChildByName(layer,'ows:AvailableCRS'));
			var srss = srs.split(' ');
			srs = srss[0];//bad choice - forcing to first srs available
			
			//GET server values
			var server = this.findChildByName(layer,'Server');
			var version = server.getAttribute('version');
			var service = server.getAttribute('service');//TODO parse to see if not WMS
			var onlineResource = this.findChildByName(server,'OnlineResource');
			var url = onlineResource.getAttribute('xlink:href');
			
			//built layer structure
			layer = {};
			layer.CLASS_NAME = "OpenLayers.Layer.WMS";
			layer.name =  title;
            layer.url =  url;
            layer.params = {}; 
			layer.params.LAYERS = name;
			layer.params.FORMAT = 'image/png';
			layer.params.SERVICE = 'WMS';
			layer.params.VERSION = version;
			layer.params.REQUEST = 'GetMap';
			layer.params.STYLES = '';
			layer.params.EXCEPTIONS = 'application/vnd.ogc.se_inimage';
			//layer.params.SRS = srs;
			//OLContext.projection = srs;
			
			OLContext.layers[i] = layer;
			
		}
		
		//OUTPUT
		console.log('out');
        return olon.write(OLContext,true);
    },
    
    
    /**
     * APIMethod: write
     * Generate a OWC document string given a OpenLayers Context obj. 
     * 
     * Parameters:
     * OpenLayers MAP - {<OpenLayers.Map>} the OpenLayers Map object
     *
     * Returns:
     * node - {<DOMElement>} A DOM obj representing a OWC document.
     */
    write: function(map) {
		
		var OLON = OpenLayers.Class.serialize(map);
		
		
		var OWC = this.createElementNS(this.OWC_NS,
                                       "ViewContext");
		OWC.setAttribute('id', 'hardcoded id');
		OWC.setAttribute('version', 'hardcoded version');
			
		//GENERAL SECTION
		if(OLON){
			var general = this.createElementNS(this.OWC_NS,'General');
			var title = this.createElementNS(this.OWC_NS,'Title');
			title.appendChild(this.createTextNode('hardcoded title'));
			general.appendChild(title);
					
			//BOUNDING BOX
			//BoundingBox SRS="EPSG:4326" minx="-180.000000" miny="-90.000000" maxx="180.000000" maxy="90.000000"
			var olbbox = map.getExtent().toArray();
			var projection = map.getProjection();
			var boundingBox = this.createElementNS(this.OWC_NS,'BoundingBox');
			boundingBox.setAttribute('SRS', projection);
			boundingBox.setAttribute('minx', olbbox[0]);
			boundingBox.setAttribute('miny', olbbox[1]);
			boundingBox.setAttribute('maxx', olbbox[2]);
			boundingBox.setAttribute('maxy', olbbox[3]);
			general.appendChild(boundingBox);
		
			
			OWC.appendChild(general);
		}	
		
		//LAYERS SECTION
		if(OLON.layers){
			var layerList = this.createElementNS(this.OWC_NS,'LayerList');
			for(var i=0; i< OLON.layers.length; ++i) {
				var oLayer = OLON.layers[i];
				var layer = this.createElementNS(this.OWC_NS,'Layer');
				layer.setAttribute('queryable', '1 - hardcoded');
				layer.setAttribute('hidden', 'visibility');
				//should I look directly in the Map object?
				//or should I expect to find visibility in the OLON?
				
				if(oLayer.url){
					//NAME -> params:LAYERS
					if(oLayer.params.LAYERS) var layers = oLayer.params.LAYERS;
					else layers = '???';
					var name = this.createElementNS(this.OWC_NS,'Name');
					name.appendChild(this.createTextNode(layers));
					layer.appendChild(name);
					
					//TITLE -> name
					if(oLayer.name) var olname = oLayer.name;
					else olname = '???';
					var title = this.createElementNS(this.OWC_NS,'Title');
					title.appendChild(this.createTextNode(olname));
					layer.appendChild(title);
					
					//SRS -> params:SRS
					if(oLayer.params.SRS) var olsrs = oLayer.params.SRS;
					else olsrs = '???';
					var srs = this.createElementNS(this.OWC_NS,'SRS');
					srs.appendChild(this.createTextNode(olsrs));
					layer.appendChild(srs);
					
					//SERVER
					//Server service="OGC:WMS" version="1.1.0" title="ESA CubeSERV"
					var server = this.createElementNS(this.OWC_NS,'Server');
					if(oLayer.params.SERVICE) var service = 'OCG:' + oLayer.params.SERVICE;
					else service = '???';
					if(oLayer.params.VERSION) var version =  oLayer.params.VERSION;
					else version = '???';
					server.setAttribute('service',service);//params::service
					server.setAttribute('version',version);//params::version
					server.setAttribute('title','...');//server title not needed
					//OnlineResource xlink:type="simple" xlink:href="http://myserver"
					var onlineResource = this.createElementNS(this.OWC_NS,'OnlineResource');
					onlineResource.setAttribute('xlink:type','simple');//params::service
					onlineResource.setAttribute('xlink:href',oLayer.url);//params::version
					server.appendChild(onlineResource);
					layer.appendChild(server);
					
					//FORMATLIST -> params:FORMAT
					//need to be looped through all server's supported formats
					var formatList = this.createElementNS(this.OWC_NS,'FormatList');
					if(oLayer.params.FORMAT) var olformat = oLayer.params.FORMAT;
					else olformat = '???';
					var format = this.createElementNS(this.OWC_NS,'Format');
					format.setAttribute('current','1');
					format.appendChild(this.createTextNode(olformat));
					formatList.appendChild(format);
					layer.appendChild(formatList);
					
					//STYLETYPELIST -> params:STYLE
					//should be the list of all styles available
					var StyleTypeList = this.createElementNS(this.OWC_NS,'StyleTypeLyst');
					if(oLayer.params.STYLE) var olstyle = oLayer.params.STYLE;
					else olstyle = '???';
					/*var format = this.createElementNS(this.OWC_NS,'FormatList');
					format.setAttribute('current','1');
					format.appendChild(this.createTextNode(olformat));*/
					//HOW TO DEFINE THE DEFAULT STYLE?!??!
					layer.appendChild(StyleTypeList);
					
					
					
					
				}
				layerList.appendChild(layer);
			}
			/*
			if(OLContext.general.title){
				var title = this.createElementNS(this.OWC_NS,'Title');
				title.appendChild(this.createTextNode(OLContext.general.title));
				general.appendChild(title);
			}*/
			
			OWC.appendChild(layerList);
		}					   
		
		
        return OpenLayers.Format.XML.prototype.write.apply(this, [OWC]);
        
        
    },
	
	
	/** 
     * @private
     * APIMethod: findChildByName
     * get first occurence of named tag from node children.
     * no recursion
     * 
     * Parameters:
     * Tag Nam - {String} the name of the tag to match
     *
     * Returns:
     * node - {<DOMElement>} the DOM Node correspondant to tag name
     *
     */
    findChildByName: function(n,name) {
		if(!n)return false;
		var x = n.firstChild;
		while (x)
	    {
	    	if(x.nodeName==name) return x;
			else if(x==n.lastChild)return null;
			else x=x.nextSibling;
	    }
	},
	
	/** 
     * @private
     * APIMethod: findChildrenByName
     * returns an array of node matching the named tag 
     * no recursion
     * 
     * Parameters:
     * Tag Nam - {String} the name of the tag to match
     *
     * Returns:
     * {Array (node - {<DOMElement>})} the array of  DOM Nodes correspondant to tag name
     *
     */
    findChildrenByName: function(n,name) {
		if(!n)return false;
		var nodes=n.childNodes;
		var aNode = [];
		for (i=0;i<nodes.length;i++) {
			var x=n.childNodes[i];
			if(x.nodeName==name) aNode.push(x) ;
		}
		
		return aNode;
	},
	

    CLASS_NAME: "OpenLayers.Format.OWC" 
});



