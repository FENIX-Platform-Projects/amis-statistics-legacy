package org.fao.fenix.web.modules.amis.client.view.map;


import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

public class AMISMapRegional {
	
	static ContentPanel panel;
	
	static String imagePath;
	
	public AMISMapRegional() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setWidth(804);
		panel.setHeight(576);
	}
	
	public ContentPanel build(String regionID) {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		buildMap(regionID, null);
		return panel;
	}
	
	public ContentPanel build(String regionID, String countryID) {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		buildMap(regionID, countryID);
		return panel;
	}
	


	private void buildMap(String regionID, String countryID) {
		String imagePath = null;
		
		if( countryID == null) {
			imagePath = "amis-images/map-images/" + regionID + "_REGION.png";
		}
		else {
			imagePath = "amis-images/map-images/" + regionID + "_" + countryID +".png";
		}

		
		System.out.println("building image: " + imagePath);
		
		AMISMapConstants region = AMISMapConstants.valueOf(regionID);
		
		switch (region) {
			case AFRICA:
				AMISMapAfricaRegion.buildAfricanMap(imagePath, "AFRICA_REGION");
				break;
		}
	}
	

	
	
	
	


	public ContentPanel getPanel() {
		return panel;
	}
	
	
	
	
	
	

}
