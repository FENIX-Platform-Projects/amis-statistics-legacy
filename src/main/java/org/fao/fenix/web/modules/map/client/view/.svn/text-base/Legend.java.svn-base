package org.fao.fenix.web.modules.map.client.view;


import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.HTML;



public class Legend {

	private String layerTitle;
	private String layerName;		
	private String styleName;	
	private String geoserverUrl;	
	private static long timestamp = 0;		
	
	
	public Legend(){
	}
	
	public Legend(LayerItem layerItem){
		
		this.layerName = layerItem.getLayerName();
		this.styleName =  layerItem.getLayerStyleName();
		this.layerTitle = layerItem.getLayerTitle();
		
		String url = (layerItem.getWMSUrl().indexOf("wms") > 0 ?
				layerItem.getWMSUrl().substring(0, layerItem.getWMSUrl().indexOf("wms")) : layerItem.getWMSUrl());
		this.geoserverUrl = url;
		
		this.timestamp += 1; 
		
		showLegend();
	}
	
	private void showLegend(){
		String url = this.geoserverUrl+
			"wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER="+
			this.layerName+"&STYLE="+this.styleName+
			"&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP="+this.timestamp;

		
		Window legend = new Window();
		
		legend.setTitle("Legend");
		legend.setHeading(layerTitle);
		legend.setCollapsible(false);
		legend.setWidth(300);
		legend.setHeight(200);
		legend.setPosition(10, 350);
		legend.focus();
		legend.setPlain(true);
		
		VerticalPanel panel = new VerticalPanel();
		
		String table = "<table><tbody><tr><td style='font-size:12px; font-weight:bold;'>" +
				"Legend: </td><td></td></tr><tr><td></td><td style='font-size:12px'><img src="+url+" style='font-weight:bold'>" +
				"</img></td></tr></tbody></table></br>";
		
		panel.add(new HTML(table));
		
		legend.add(panel);		
		legend.show();		
	}
}
