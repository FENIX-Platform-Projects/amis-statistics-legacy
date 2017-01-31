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
 
 
OpenLayers.Control.SelectBoxReset = OpenLayers.Class(OpenLayers.Control, {
    /**
     * Property: type
     * {OpenLayers.Control.TYPE}
     */
    type: OpenLayers.Control.TYPE_BUTTON,
	
	/**
     * Method: trigger
     */
    trigger: function(){
		   var filter = '';
		   		   
	   	   var mapObj = document.getElementById(mapId).map; 
		   for(var i=0; i<layId.length; i++){	  				
			   if (layId[i] != null){
				    var lay = mapObj.getLayer(layId[i]);		
			   		lay.mergeNewParams({filter:filter});
			  		lay.redraw(); 
			   }			
		   } 
	  },
		
		/**
		 *
		 */
    CLASS_NAME: "OpenLayers.Control.SelectBoxReset"
});
