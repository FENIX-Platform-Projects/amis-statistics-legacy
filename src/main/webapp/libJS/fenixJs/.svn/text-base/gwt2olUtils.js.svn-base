/*
 * Create a new OL map and add it to passed div
 */
 
function createMap(divID){
	/*
	 * we need a proxy to load data frome remote server
	 */
	OpenLayers.ProxyHost = "../proxy?url=";
	var divElement=$(divID);
	divElement.innerHTML = '';	          
 	var map = new OpenLayers.Map( $(divID),{controls:[]});
 	divElement.map=map;
 	map.addControl(new OpenLayers.Control.MousePosition());
 	map.addControl(new OpenLayers.Control.OverviewMap());
 	map.addControl(new OpenLayers.Control.KeyboardDefaults());
 	map.addControl(new OpenLayers.Control.ScaleLine());
 
 	// pan = new OpenLayers.Control.DragPan();
    // zb = new OpenLayers.Control.ZoomBox({title:"Zoom box: Selecting it you can zoom on an area by clicking and dragging."});
    // var panel = new OpenLayers.Control.Panel({defaultControl: pan});
    map.addControl(new OpenLayers.Control.PanZoomBar());
 
    //if( ! $(divID + 'olpanel')) 
    //     alert("parent div '"+(divID + 'olpanel')+"' not found");
  
    var navigationToolbarControls = [
                         new OpenLayers.Control.Navigation({title:"Map Pan: Click and Drag Map"}),
                         new OpenLayers.Control.ZoomBox({title:"Zoom Box: Click and Drag Map Area"}),
                         new OpenLayers.Control.SelectBox({title:"Filter Area: Click and Drag Map Area"}),
                         new OpenLayers.Control.SelectBoxReset({title:"Click to Remove Filter"})
                         //new OpenLayers.Control.DragPan(),
                         //new OpenLayers.Control.ZoomToMaxExtent({title:"Zoom to the max extent"})
    ];
  
    var navToolbar = new OpenLayers.Control.Panel({
    	displayClass: 'olControlNavToolbar',
    	defaultControl: navigationToolbarControls[0]
    });
  
    navToolbar.addControls(navigationToolbarControls);

    map.addControl(navToolbar);

  }


function createMapOriginal(divID){
	/*
	 * we need a proxy to load data frome remote server
	 */
	OpenLayers.ProxyHost = "../proxy?url=";
	var divElement=$(divID);
	divElement.innerHTML = '';
	var map = new OpenLayers.Map( $(divID),{controls:[]});
	divElement.map=map;
	//ows=new OpenLayers.Control.OWSManager(aWMSServers );
	map.addControl(new OpenLayers.Control.MousePosition());
	map.addControl(new OpenLayers.Control.OverviewMap());
	map.addControl(new OpenLayers.Control.KeyboardDefaults());
	//map.addControl(new OpenLayers.Control.SLDManager() );
	//map.addControl(new OpenLayers.Control.NavToolbar());
	map.addControl(new OpenLayers.Control.ScaleLine());

	pan = new OpenLayers.Control.DragPan();
    zb = new OpenLayers.Control.ZoomBox({title:"Zoom box: Selecting it you can zoom on an area by clicking and dragging."});
    var panel = new OpenLayers.Control.Panel({defaultControl: pan});
//    panel.addControls([
//    	pan,
//      zb,
//        new OpenLayers.Control.SelectBox(),
//		new OpenLayers.Control.SelectBoxReset()
        //new OpenLayers.Control.ZoomToMaxExtent({title:"Zoom to the max extent"})
//    ]);
//    map.addControl(panel);
	map.addControl(new OpenLayers.Control.PanZoomBar());

    if( ! $(divID + 'olpanel')) 
        alert("parent div '"+(divID + 'olpanel')+"' not found");
//    else
//        alert("parent div found");

    var extpanel = new OpenLayers.Control.NavToolbar({'div':OpenLayers.Util.getElement(divID + 'olpanel')});
    extpanel.addControls([
        new OpenLayers.Control.SelectBox(),
		new OpenLayers.Control.SelectBoxReset()
    ]);

    map.addControl(extpanel);

	//map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending':false}));
	//map.addControl( ows );
	//ows.minimizeControl();
}


function saveMapContext(url,div){
   	var divElement= $(div) ;
	var uri = "../saveWMC?";
	var postBody=WMCLoader.writeContext(divElement.map);
// WMC should be passed as body for encoding problem, not as post param
	var id='id='+url.slice((url.lastIndexOf('/')+1));
//We need also to pass the WMC ID better to move inside WMC	
	uri+=id;
	new OpenLayers.Ajax.Request(uri,{
	//							  method: 'post',
	//							  parameters: id,
			                      postBody: postBody,
								  onComplete: function(){alert('Updated');}, 
	     	                      onFailure: function(){alert('Failed')}
			                      });
}
/*
 * List of availabele WMS
 */	
var aWMSServers=new Array(
    ['Fenix Demo (SLD)' ,'http://ldvapp07.fao.org:8030/geoserverDemo/wms?','http://ldvapp07.fao.org:8030/geoserverDemo/sldservice/'],
	[ 'ominiverdi.org (SLD)' ,'http://test.ominiverdi.org:8080/geoserverSld/wms?','http://test.ominiverdi.org:8080/geoserverSld/sldservice/'],
	['Fenix Demo Ominiverdi (SLD) ' ,'http://test.ominiverdi.org:8080/geoserverDemo/wms?','http://test.ominiverdi.org:8080/geoserverDemo/sldservice/'],
	['OpenLayers WMS','http://labs.metacarta.com/cgi-bin/mapserv?map=/www/labs/map/vmap0.map'],//content type: application/vnd.ogc.wms_xml
	['Telascience','http://wms.telascience.org/cgi-bin/ngBM_wms?STYLES=&TIME=2004-09&'],		
	['World - NASA Blue Marble Next Generation','http://wms.jpl.nasa.gov/wms.cgi?'],
	['World - Demis World Map','http://www2.demis.nl/mapserver/Request.asp?'],
	['NATO','http://geos2.nurc.nato.int/geoserver/wms'],
	['CubeWerx','http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?config=main&'],	//['QGIS WMS server at ethz.ch','http://karlinapp.ethz.ch/fcgi-bin/qgis_wms_dir/europe/qgis_wms_serv.fcgi?'],
	['AU - Aims Australia','http://adc.aims.gov.au:9555/atlas/SstWmsServer?'],
	['BR - Unidades de Conservacao do Brasil (dados preliminares)','http://mapas.mma.gov.br/cgi-bin/mapserv?map=/opt/www/html/webservices/ucs.map&'],
	['SP - Catalunya - ICC Web Map Service','http://shagrat.icc.es/lizardtech/iserv/ows?'],
	['SP - Junta Andalucia','http://www.andaluciajunta.es/IDEAndalucia/IDEAwms/wms/MTA100v?'],
	['SP - Comunidad Valenciana - Conselleria de Territori i Habitatge, GVA - Servici WMS: wms_senderos','http://orto.cth.gva.es/wmsconnector/com.esri.wms.Esrimap/wms_senderos?']
);