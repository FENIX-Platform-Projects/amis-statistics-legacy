/* Copyright (c) 2006-2007 MetaCarta, Inc., published under a modified BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/repository-license.txt 
 * for the full text of the license. */

/**
 * @requires OpenLayers/Format/KML.js
 *
 * Class: OpenLayers.Format.KML3
 * Read/Wite KML3. Create a new instance with the <OpenLayers.Format.KML3>
 *     constructor. 
 * 
 * Inherits from:
 *  - <OpenLayers.Format.KML>
 */
OpenLayers.Format.KML3 = OpenLayers.Class(OpenLayers.Format.KML, {
    
    /**
     * APIProperty: kmlns
     * {String} KML3 Namespace to use. Defaults to 2.1 namespace.
     */
    kmlns: "http://earth.google.com/kml/2.1",
    
    /**
     * APIProperty: kmlPrefix
     * {String} Alias for KML namespace.
     */
    kmlPrefix: "",

    /**
     * APIProperty: gmlns
     * {String} GML namespace to use.
     */
    gmlns: "http://www.opengis.net/gml",
    
    /**
     * APIProperty: gmlPrefix
     * {String} Alias for GML namespace.
     */
    gmlPrefix: "gml",

    /**
     * Constructor: OpenLayers.Format.KML3
     * Create a new parser for KML3.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be set on
     *     this instance.
     */
    initialize: function(options) {
        OpenLayers.Format.KML.prototype.initialize.apply(this, [options]);
    },

    /**
     * APIMethod: read
     * Read data from a string, and return a list of features. 
     * 
     * Parameters: 
     * data - {String} or {DOMElement} data to read/parse.
     *
     * Returns:
     * {Array(<OpenLayers.Feature.Vector>)} List of features.
     */
    read: function(data) {
        var features = OpenLayers.Format.KML.prototype.read.apply(this, [data]);
    },
    
    /**
     * APIMethod: write
     * Accept Feature Collection, and return a string. 
     * 
     * Parameters:
     * features - An array of <OpenLayers.Feature.Vector> features.
     *
     * Returns:
     * {String} A KML3 string.
     */
    write: function(map) {
        var doc = this.createElementX(this.kmlns, this.kmlPrefix, "kml");
        var layer, type, writer, node;
        for(var i=0; i<map.layers.length; ++i) {
            layer = map.layers[i];
            type = layer.CLASS_NAME;
            writer = this.writeNetworkLink[type];
            if(writer) {
                node = writer.apply(this, [layer]);
                if(node) {
                    doc.appendChild(node);
                }
            }
        }
        return OpenLayers.Format.XML.prototype.write(doc);
    },

    /**
     *
     */
    writeNetworkLink: {
        "OpenLayers.Layer.WMS": function(layer) {
            var network = this.createElementX(
                this.kmlns, this.kmlPrefix, "NetworkLink"
            );
            var name = this.createElementX(
                this.kmlns, this.kmlPrefix, "name", layer.name
            );
            network.appendChild(name);
            
            var bounds = layer.maxExtent;
            if(bounds) {
                var envelope = this.createElementX(
                    this.gmlns, this.gmlPrefix, "envelope"
                );
                var llc = this.createElementX(
                    this.gmlns, this.gmlPrefix, "lowerCorner",
                    bounds.left + " " + bounds.bottom
                );
                var urc = this.createElementX(
                    this.gmlns, this.gmlPrefix, "upperCorner",
                    bounds.right + " " + bounds.top
                );
                envelope.appendChild(llc);
                envelope.appendChild(urc);
                network.appendChild(envelope);
            }
            
            var link = this.createElementX(
                this.kmlns, this.kmlPrefix, "Link"
            );
            var server = this.createElementX(
                this.kmlns, this.kmlPrefix, "Service"
            );
            server.setAttribute("service", "OGC:WMS");
            server.setAttribute("version", layer.params["VERSION"]);
            var url = layer.url instanceof Array ? url[0] : layer.url;
            server.setAttribute("href", url);
            
            var paramNames = [
                "LAYERS", "STYLES", "FORMAT", "TRANSPARENT"
            ];
            
            var node;
            for(var i=0; i<paramNames.length; ++i) {
                var key = paramNames[i];
                if(layer.params[key]) {
                    node = this.createParamNode(layer.params[key], key);
                    link.appendChild(node);
                }
            }
            link.appendChild(server);
            
            network.appendChild(link);
            return network;
        },
        "OpenLayers.Layer.Vector": function(layer) {
            var folder;
            if(layer.features.length > 0) {
                this.foldersName = layer.name;
                folder = this.createFolderXML();
                for(var i=0; i<layer.features.length; ++i) {
                    folder.appendChild(
                        this.createPlacemarkXML(layer.features[i])
                    );
                }
            }
            return folder;
        }
    },
    
    /**
     * createParamNode
     */
    createParamNode: function(param, name) {
        var node = this.createElementX(
            this.kmlns, this.kmlPrefix, "param"
        );
        node.setAttribute("name", name);
        if(param instanceof Array) {
            var item;
            for(var i=0; i<param.length; ++i) {
                item = this.createElementX(
                    this.kmlns, this.kmlPrefix, "item", param[i]
                );
                node.appendChild(item);
            }
        } else {
            node.appendChild(this.createTextNode(param));
        }
        return node;
    },

    /**
     * Method: createElementX
     */
    createElementX: function(uri, prefix, local, child) {
        var qualified = prefix ? prefix + ":" + local : local;
        if(typeof child == "string") {
            child = this.createTextNode(child);
        }
        var element = this.createElementNS(uri, qualified);
        if(child) {
            element.appendChild(child);
        }
        return element;
    },
    
    CLASS_NAME: "OpenLayers.Format.KML3" 
});
