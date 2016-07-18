/* Should load an MapContex in the passed map
 * parameter:
 * map: ol map where to load mapcpntext
 * url: the mapcontext url for donwload
 * 
 */
var WMCLoader = {
	 olon :  new OpenLayers.Format.OLON(),
	 wmc  :  new OpenLayers.Format.WMC(),
	 map  : null,
	 ows_url : null,
     olon_str : null,
	 loadWMC : function(map, url){
				this.map=map;
				this.ows_url=url;
				OpenLayers.loadURL(this.ows_url, null, this, this.loadSucces, this.failed);	
			},
     loadSucces : function(request){
		this.olon_str = this.wmc.read(request.responseText);
		//alert(this.wmc_str);
		this.applyOLON();	
		},
	 failed : function(){
	 	alert("Unable to Load "+ this.url);
	 },
	 clear: function() {
			for(var i=0; i < this.map.getNumLayers(); i++) 
				if(this.map.layers[i])					
					this.map.layers[i].destroy(true);			
	 },
	 loadSZ: function(map,wmcSz){
	 	this.map=map;
		this.olon_str=this.wmc.read(wmcSz);
		this.applyOLON();
	 },
	 applyOLON: function() {
           this.clear();
           var obj = this.olon.read(this.olon_str);
			if(obj.projection){
				this.map.projection = obj.projection;
			}
			//console.log('proj: '+obj.projection + ' '+ this.map.projection);
			this.map.addLayers(obj.layers);			
			this.map.bounds = obj.bounds;				
			this.map.zoomToExtent(obj.bounds);			
			if(obj.resolution && obj.center) {
                //zoom = map.getZoomForResolution(obj.resolution, true);
                //map.setCenter(obj.center, zoom);
            }   
        },
	 writeContext: function(map){
	 	this.map=map
		return this.wmc.write(this.map);		
	 }
};