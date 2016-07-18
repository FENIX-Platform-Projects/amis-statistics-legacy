package org.fao.fenix.web.modules.amis.client.view.map;



import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.RootPanel;

public class AMISMap {
	
//	AMISMapWorld amisMapWorld;
	
//	static AMISMapRegional amisMapRegional;
	
//	static ContentPanel panel;
//	
//	static ContentPanel mapPanel;
	
	public AMISMap() {
//		panel = new ContentPanel();
//		panel.setBodyBorder(false);
//		panel.setHeaderVisible(false);
//		
//		mapPanel = new ContentPanel();
//		mapPanel.setBodyBorder(false);
//		mapPanel.setHeaderVisible(false);
		
//		amisMapWorld = new AMISMapWorld();
		
//		amisMapRegional = new AMISMapRegional();
//		buildWorldMap();
	}
	
//	public ContentPanel build() {
//		panel.add(mapPanel);
//		buildWorldMap();
////		buildRegionalMap("AU");
//		return panel;
//	}

	public void buildWorldMap() {
//		ContentPanel mapPanel = new ContentPanel();
//		mapPanel.removeAll();
//		if (RootPanel.get("WORLD_MAP").getWidgetCount() > 0)
//			RootPanel.get("WORLD_MAP").remove(RootPanel.get("WORLD_MAP").getWidget(0));
		
		AMISMapWorld amisMapWorld = new AMISMapWorld();
//		mapPanel.layout();
		amisMapWorld.getPanel().layout();
//		mapPanel.add(amisMapWorld.build());
//		mapPanel.layout();
		amisMapWorld.getPanel().layout();

		RootPanel.get("WORLD_MAP").clear();

		RootPanel.get("WORLD_MAP").add(amisMapWorld.build());
//		mapPanel.layout();
		amisMapWorld.getPanel().layout();

		

		
//		AMISMapRegional amisMapRegional = new AMISMapRegional();
//		mapPanel.add(amisMapRegional.build("AU"));

//		panel.layout();
	} 
	
	public void buildWorldMap2() {
		AMISMapWorld2 amisMapWorld = new AMISMapWorld2();

		RootPanel.get("WORLD_MAP").clear();

//		amisMapWorld.buildMap("amis-images/map-images/WORLD.png", "WORLD");
		amisMapWorld.buildMap("amis-images/map-images/BLANK.gif", "BLANK");

		
		RootPanel.get("WORLD_MAP").add(amisMapWorld.getPanel());
		amisMapWorld.getPanel().layout();
	} 
	
	public void buildWorldMap3() {
		AMISMapWorld3 amisMapWorld = new AMISMapWorld3();

		RootPanel.get("WORLD_MAP").clear();

//		amisMapWorld.buildMap("amis-images/map-images/WORLD.png", "WORLD");
//		amisMapWorld.buildMap("amis-images/map-images/BLANK.gif", "BLANK");
		amisMapWorld.buildMap("amis-images/map-images/BLANK.png", "BLANK");

		
		RootPanel.get("WORLD_MAP").add(amisMapWorld.getPanel());
		amisMapWorld.getPanel().layout();
	} 
	
	
	public static void buildRegionalMap(String regionID) {
		RootPanel.get("WORLD_MAP").clear();
		System.out.println("REGIONID: " + regionID);
		AMISMapRegional amisMapRegional = new AMISMapRegional();
		RootPanel.get("WORLD_MAP").add(amisMapRegional.build(regionID));
		amisMapRegional.getPanel().layout();

	} 
	
//	public static void buildRegionalMap(String regionID, String countryID) {
//		System.out.println("buildRegionalMap regionID: " + regionID + " | " + countryID);
////		mapPanel.removeAll();
//		AMISMapRegional amisMapRegional = new AMISMapRegional();
//		mapPanel.add(amisMapRegional.build(regionID, countryID));
//		panel.layout();
//
//	} 
	

	
}
