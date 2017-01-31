package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTML;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class LayerMetadata {

	private String layerName;
	private String layerListName;
	private String styleName;	
	private String geoserverUrl;		
	private JavaScriptObject capabilities;
	
	
	public LayerMetadata(){
		
	}
	
	public LayerMetadata(LayerItem layerItem){
		
		this.layerName = layerItem.getLayerName();
		this.styleName =  layerItem.getLayerStyleName();
		this.layerListName = layerItem.getLayerTitle();
		
		String url = (layerItem.getWMSUrl().indexOf("wms") > 0 ? 
				layerItem.getWMSUrl().substring(0, layerItem.getWMSUrl().indexOf("wms")) : layerItem.getWMSUrl());
		this.geoserverUrl = url;
		
		getCapabilities(this.geoserverUrl, this.styleName, this.layerName);
	}
	
	private native void getCapabilities(String geoserverUrl, String styleName, String layerName)/*-{
		
		var url = geoserverUrl+"wms?request=getCapabilities";
				
		function handlerCapabilities(req){	
	
			var xmlDoc = req.responseXML;
			var nsResolver = xmlDoc.createNSResolver( xmlDoc.ownerDocument == null ? xmlDoc.documentElement : xmlDoc.ownerDocument.documentElement);
			
			var name = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/Name ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			
			var keywords = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/KeywordList", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			
			var srs = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/SRS", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			
			var latLonBBox = [];
			latLonBBox[0] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/LatLonBoundingBox/@minx ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			latLonBBox[1] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/LatLonBoundingBox/@miny ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			latLonBBox[2] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/LatLonBoundingBox/@maxx ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			latLonBBox[3] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/LatLonBoundingBox/@maxy ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );		
	
			var BBox = [];
			BBox[0] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/BoundingBox/@minx ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );		
			BBox[1] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/BoundingBox/@miny ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );		
			BBox[2] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/BoundingBox/@maxx ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );		
			BBox[3] = xmlDoc.evaluate("//Layer[Name='"+layerName+"']/BoundingBox/@maxy ", xmlDoc, nsResolver, XPathResult.STRING_TYPE, null );
			
			var string = "<table><tbody>";
			
			string += "<tr><td style='font-size:12px; font-weight:bold;'>Style:</td><td style='font-size:12px'>"+styleName+"</td></tr>";
			string += "<tr><td style='font-size:12px; font-weight:bold;'>Name:</td><td style='font-size:12px'>"+name.stringValue+"</td></tr>";
			string += "<tr><td style='font-size:12px; font-weight:bold;'>Keywords:</td><td style='font-size:12px'>"+keywords.stringValue+"</td></tr>";
			string += "<tr><td style='font-size:12px; font-weight:bold;'>SRS:</td><td style='font-size:12px'>"+srs.stringValue+"</td></tr>";
			
			string += "<tr><td style='font-size:12px; font-weight:bold;'>latLonBBox:</td><td style='font-size:12px'>  minX "+latLonBBox[0].stringValue+"</td><td style='font-size:12px'>  minY "+latLonBBox[1].stringValue+"</td>";		
			string += "<tr><td></td><td style='font-size:12px'>  maxX "+latLonBBox[2].stringValue+"</td><td style='font-size:12px'>  maxY "+latLonBBox[3].stringValue+"</td></tr>";
			
			string += "<tr><td style='font-size:12px; font-weight:bold;'>BBox:</td><td style='font-size:12px'>  minX "+BBox[0].stringValue+"</td><td style='font-size:12px'>  minY "+BBox[1].stringValue+"</td>";		
			string += "<tr><td></td><td style='font-size:12px'>  maxX "+BBox[2].stringValue+"</td><td style='font-size:12px'>  maxY "+BBox[3].stringValue+"</td></tr>";
			
			string += "</tbody></table>";
			
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.LayerMetadata::capabilities = string;			
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.LayerMetadata::showLayerMetadata()();	
		}

	 	$wnd.OpenLayers.Request.GET({
			url: url,
			callback: handlerCapabilities,
			scope: this
		});
				
	}-*/;
	
	private void showLayerMetadata(){
		
		Window metadata = new Window();
		
		metadata.setTitle("Layer Metadata - "+this.layerListName);
		metadata.setHeading("Layer Metadata - "+this.layerListName);
		metadata.setCollapsible(true);
		metadata.setWidth(450);
		metadata.setHeight(300);
		metadata.setPosition(10, 350);
		metadata.setPlain(true);
		
		VerticalPanel panel = new VerticalPanel();		
		panel.add(new HTML(this.capabilities.toString()));
		
		metadata.add(panel);		
		metadata.show();		
	}
}
