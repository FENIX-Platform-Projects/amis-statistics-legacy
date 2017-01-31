package org.fao.fenix.web.modules.amis.client.view.map;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTML;

public class AMISMapWorld {
	
	static ContentPanel panel;
	
	static String imagePath;
	
	public AMISMapWorld() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build() {
		panel.removeAll();
		imagePath = "amis-images/map-images/WORLD.png";
		panel.add(buildWorldMap());
//		panel.add(buildWorldMap("amis-images/map-images/world.png"));
		return panel;
	}
	
	public static void build(String path) {
		panel.removeAll();
		imagePath = path;
		panel.add(buildWorldMap());
		panel.layout();
	}
	
	
	public static HTML  buildWorldMap() {
	//	ClickHtml mapHtml = new ClickHtml();
		
		HTML mapHtml = new HTML();
		StringBuffer map = new StringBuffer();
		
		String mapID = String.valueOf((Math.random() * 10));
		mapID = mapID.replace(".", "");
		
		System.out.println("mapID: " + mapID);
		
		
	
		
		map.append("<div class=\"zindex\">");
		map.append("<MAP name='"+ mapID +"'> ");
		
		// AFRICA
		map.append("<area " +
				// " class='border iradius16 iopacity50 icolorffffff' " +
				   " onmouseover=\"setRegionID('AFRICA', event)\" " +
				   " onClick=\"setRegionID('AFRICA', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"339,150,381,146,442,162,454,188,476,219,496,215,493,234,465,263,468,283,494,282,481,319,442,322,426,344,404,348,385,296,376,240,329,240,306,214,308,183,339,150\" " +
				   " />");
		
		// ASIA
		map.append("<area " +
				   " onmouseover=\"setRegionID('ASIA', event)\" " +
				   " onClick=\"setRegionID('ASIA', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"417,59,430,26,695,55,721,75,712,150,682,188,746,256,747,274,698,282,646,269,618,235,596,188,576,221,582,234,560,218,524,182,499,209,474,214,447,170,422,138,450,116,414,86,417,59\" " +
				   " />");
		
		// EUROPE
		map.append("<area " +
				   " onmouseover=\"setRegionID('EUROPE', event)\" " +
				   " onClick=\"setRegionID('EUROPE', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"332,145,336,96,349,86,362,106,364,74,373,26,410,26,412,92,444,112,410,146,388,146,375,142,340,148,332,145\" " +
				   " />");
		
		// NORTH_AMERICA
		map.append("<area " +
				   " onmouseover=\"setRegionID('NORTH_AMERICA', event)\" " +
				   " onClick=\"setRegionID('NORTH_AMERICA', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"38,55,133,40,216,20,256,19,215,41,237,64,229,119,144,164,175,199,1125,200,124,221,101,211,66,196,48,151,58,109,47,81,1,94,21,65,38,55\" " +
				   " />");
					
		// SOUTH_AMERICA
		map.append("<area " +
				   " onmouseover=\"setRegionID('SOUTH_AMERICA', event)\" " +
				   " onClick=\"setRegionID('SOUTH_AMERICA', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"139,228,162,214,206,232,259,270,243,313,198,378,224,393,211,405,183,396,167,354,160,303,127,266,140,229\" " +
				   " />");
		
		// OCEANIA
		map.append("<area " +
				   " onmouseover=\"setRegionID('OCEANIA', event)\" " +
				   " onClick=\"setRegionID('OCEANIA', event)\" "+
				   " shape=\"poly\" " +
				   " coords=\"660,311,715,281,751,279,796,303,796,361,750,385,706,373,696,343,645,347,660,311\" " +
				   " />");
		
		map.append("</MAP> ");
		
		map.append("<IMG class=\"mapper\" src=\""+ imagePath + "\"  Usemap='#"+ mapID +"' >"); 
		map.append("</div> ");
		

				
	
		mapHtml.setHTML(map.toString());
	
	
		mapHtml.addMouseMoveHandler(new MouseMoveHandler() {

			public void onMouseMove(MouseMoveEvent arg0) {
				changeImageJS();
			}
		});
		
		mapHtml.addMouseOutHandler(new MouseOutHandler() {
	
			public void onMouseOut(MouseOutEvent arg0) {
				changeImageJS();
			}
		});
	
		mapHtml.addClickHandler(t());
		
		return mapHtml;
	}


	
	
	public static ClickHandler changeImage() {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("changeImage!");
				changeImageJS();
			}
		};
	}
	
	public static void changeImagePath(String regionID, String highlighted) {
		System.out.println("changeImagePath: " + regionID + " |" + highlighted + "|");
		
		String imagePath = null;
		if ( highlighted != null )
			 imagePath = "amis-images/map-images/"+ regionID +"_" + highlighted + ".png";
		else 
			 imagePath = "amis-images/map-images/"+ regionID + ".png";

//		System.out.println("changeImagePath: " + imagePath);
		build(imagePath);
	}
	
	public static native void changeImageJS()/*-{
	
	  	var regionID = $wnd.regionID;
		 
		  	 
	  	if ( regionID != '' ) {
	  		//alert(regionID + " change image is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld::changeImagePath(Ljava/lang/String;Ljava/lang/String;)(regionID, "HIGHLIGHTED");
	  		//@org.fao.fenix.web.modules.adam.client.view.venn.ADAMVennPriorities::callPriorityAgent(Ljava/lang/String;)(intersection);
	  		$wnd.regionID = '';
	  	}
	  	else{
	  		  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapWorld::changeImagePath(Ljava/lang/String;Ljava/lang/String;)("WORLD", null);
	  		//alert(regionID + " change is null");
	  	}
	  	
		// setting the default value
	  	// $wnd.regionID = '';
		//alert("$wnd.regionID: " + $wnd.regionID);
	
	}-*/;

	
	public static ClickHandler t() {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("ClickHandler!");
				switchImage();
			}
		};
	}
	
	
	public static native void switchImage()/*-{
	
	  	var regionID = $wnd.regionID;

	  	if ( regionID != '' ) {
	  		//alert(regionID + " switchImage is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMap::buildRegionalMap(Ljava/lang/String;)(regionID);
	  	}
	  	else{
	  		alert(regionID + " switchImage is null");
	  	}
	  	
		// setting the default value
	  	$wnd.regionID = '';
		//alert("$wnd.regionID: " + $wnd.regionID);
	
	}-*/;

	public ContentPanel getPanel() {
		return panel;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
