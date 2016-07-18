/* Copyright (c) 2006-2007 MetaCarta, Inc., published under a modified BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/repository-license.txt 
 * for the full text of the license. */

/**
 * Note:
 * This work draws heavily from the public domain JSON serializer/deserializer
 *     at http://www.json.org/json.js. Rewritten so that it doesn't modify
 *     basic data prototypes.
 */

/**
 * @requires OpenLayers/Format/JSON.js
 *
 * Class: OpenLayers.Format.OLON
 * A parser to read/write OLON safely.  Create a new instance with the
 *     <OpenLayers.Format.OLON> constructor.
 *
 * Inherits from:
 *  - <OpenLayers.Format.JSON>
 */
OpenLayers.Format.OLON = OpenLayers.Class(OpenLayers.Format.JSON, {
    
    /**
     * Constructor: OpenLayers.Format.OLON
     * Create a new parser for OLON.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be set on
     *     this instance.
     */
    initialize: function(options) {
        OpenLayers.Format.JSON.prototype.initialize.apply(this, [options]);
    },

    /**
     * APIMethod: read
     * Deserialize an OpenLayers JSON string.
     *
     * Parameters:
     * str - {String} An OLON string
     * filter - {Function} A function which will be called for every key and
     *     value at every level of the final result. Each value will be
     *     replaced by the result of the filter function. This can be used to
     *     reform generic objects into instances of classes, or to transform
     *     date strings into Date objects.
     *     
     * Returns:
     * {Object} An object, array, string, or number.
     */
    read: function(str, filter) {
        var data = OpenLayers.Format.JSON.prototype.read(str, filter);
        var instance = OpenLayers.Class.deserialize(data);
        return instance;
    },

    /**
     * APIMethod: write
     * Serialize an object into an OLON string.
     *
     * Parameters:
     * value - {String} The object, array, string, number, boolean or date
     *     to be serialized.
     * pretty - {Boolean} Structure the output with newlines and indentation.
     *     Default is false.
     *
     * Returns:
     * {String} The OpenLayers JSON string representation of the input value.
     */
    write: function(value, pretty) {
        var data = OpenLayers.Class.serialize(value);
        var str = OpenLayers.Format.JSON.prototype.write(data, pretty);
        return str;
    },
    
    CLASS_NAME: "OpenLayers.Format.OLON" 

});     
