package org.fao.fenix.web.modules.amis.client.view.map;

import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.HTML;

public class AMISMapAfricaRegion extends AMISMapRegional {
	
	public static void buildAfricanMap(String path, String defaultMap) {
		panel.removeAll();
		imagePath = path;
		panel.add(buildAfricanMapHTML(defaultMap));
		panel.layout();
	}
	
	
	public static HTML buildAfricanMapHTML(final String defaultMap) {

		
		System.out.println("AMISMapAfricaRegion buildMap");

		
		HTML mapHtml = new HTML();
		StringBuffer map = new StringBuffer();

		
		String mapID = String.valueOf((Math.random() * 10));
		mapID = mapID.replace(".", "");
		
		System.out.println("mapID: " + mapID);
		
		map.append("<div class=\"zindex\">");
		map.append("<MAP name='"+ mapID +"'> ");

		
		map.append(" <area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('EGYPT', '90', 'Egypt', event)\"  onmousemove=\"showToolTip(event,'Egypt');return false\" onmouseout=\"hideToolTip(); cleanRegionID('asd', event)\"" +
				   " onClick=\"setCountryID('EGYPT', '90', 'Egypt', event)\" "+		
				   
				   " coords=\"437,221, 437,222, 437,222, 437,222, 438,223, 440,223, 441,223, 441,223, 442,223, 442,222, 442,222, 443,222, 443,222, 443,223, 444,224, 445,225, 445,226, 445,226, 445,227, 445,227, 445,228, 445,230, 445,231, 445,231, 445,232, 444,232, 444,232, 443,231, 443,231, 442,230, 442,229, 441,227, " +
				   " 440,226, 440,226, 439,225, 439,226, 439,226, 439,227, 440,228, 440,229, 441,229, 441,230, 442,230, 443,233, 444,235, 448,240, 449,242, 450,243, 450,245, 450,245, 448,247, 447,247, 446,248, 445,248, 440,248, 438,248, 435,248, 425,248, 424,248, 423,248, 422,248, 422,248, 422,248, 422,247, 421,246, 421,246, 421,244, 420,231, " +
				   " 420,229, 420,228, 420,228, 420,227, 419,227, 419,227, 419,225, 419,225, 419,225, 419,224, 419,224, 419,224, 419,223, 419,223, 419,223, 419,222, 419,222, 419,221, 419,221, 421,221, 422,221, 424,222, 424,222, 425,222, 425,222, 425,222, 427,223, 430,223, 430,223, 430,223, 431,223, 432,222, 433,222, 435,221, 436,222, 437,221\" />");

		map.append("<area shape=\"rect\" " +
				   " onmouseover=\"setCountryID('SOUTH_AFRICA', '90', 'South Africa', event)\"  onmousemove=\"showToolTip(event,'1)australia place');return false\" onmouseout=\"hideToolTip(); cleanRegionID('asd', event)\"" +
				   " onClick=\"setCountryID('SOUTH_AFRICA', '90', 'South Africa', event)\" "+	
				   
				   " coords=\"398,371,441,407\" />");
		
		map.append("<area shape=\"poly\" " +
				   " onmouseover=\"setCountryID('NIGERIA', '90', 'Nigeria', event)\"  onmousemove=\"showToolTip(event,'1)australia place');return false\" onmouseout=\"hideToolTip(); cleanRegionID('asd', event)\"" +
				   " onClick=\"setCountryID('NIGERIA', '90', 'Nigeria', event)\" "+		
				   
				   " coords=\"370,271, 370,271, 370,271, 370,271, 371,271, 371,271, 371,272, 372,272, 372,272, 372,272, 373,272, 373,273, 373,273, 373,273, 374,273, 375,273, 375,273, 375,273, 376,273, 376,272, 376,272, 376,273, 377,273, 377,273, 377,273, 378,273, 378,273, 379,273, 379,273, 380,273, 380,273, 381,273,  " +
				   " 381,273, 382,273, 382,273, 382,273, 382,273, 382,273, 384,272, 384,272, 385,272, 385,272, 386,272, 386,272, 387,273, 387,273, 388,273, 388,273, 388,273, 389,273, 389,273, 389,273, 390,272, 390,272, 391,271, 392,271, 392,271, 392,272, 393,272, 393,272, 393,273, 395,275, 395,276, 395,277, 395,277, 394,277, 394,278, 394,278, " +
				   " 393,278, 393,278, 393,279, 392,279, 392,279, 392,280, 392,281, 391,283, 390,285, 388,287, 388,288, 387,289, 386,291, 386,291, 386,291, 385,291, 385,291, 384,290, 383,290, 382,290, 382,290, 382,290, 382,291, 379,293, 379,294, 379,294, 379,295, 379,295, 379,296, 378,296, 377,297, 377,297, 374,297, 373,297, 372,297, 371,297, " +
				   " 371,297, 370,297, 370,297, 370,297, 370,296, 370,296, 370,295, 369,295, 369,294, 368,293, 368,293, 368,293, 368,292, 367,292, 367,292, 367,292, 366,292, 366,292, 365,292, 365,292, 364,292, 364,292, 363,292, 363,292, 362,292, 362,292, 362,291, 362,287, 362,286, 362,285, 362,285, 362,285, 363,283, 364,282, 364,282, 365,280, " +
				   " 365,280, 365,279, 365,278, 364,278, 364,278, 364,277, 364,275, 364,274, 365,274, 365,274, 365,274, 365,273, 366,273, 366,273, 366,273, 366,272, 366,272, 366,272, 367,272, 367,271, 367,271, 368,271, 368,271, 369,271, 369,271, 370,271\" />");
		
		map.append("</MAP> ");
		
		map.append("<IMG class='mapper' src='"+ imagePath +"' Usemap='#"+ mapID +"' >"); 
		map.append("</div> ");

		mapHtml.setHTML(map.toString());
		
		mapHtml.addMouseMoveHandler(new MouseMoveHandler() {

			public void onMouseMove(MouseMoveEvent arg0) {
				changeImageJS(defaultMap);
			}
		});
		
		mapHtml.addMouseOutHandler(new MouseOutHandler() {
	
			public void onMouseOut(MouseOutEvent arg0) {
				changeImageJS(defaultMap);
			}
		});
		
		mapHtml.addClickHandler(getSelectedCountry(defaultMap));
		return mapHtml;
	}
	
	
	public static ClickHandler changeImage(final String defaultMap) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("changeImage!");
				changeImageJS(defaultMap);
			}
		};
	}
	
	public static void changeImagePath(String countryFile, String highlighted, String defaultMap) {
		System.out.println("changeImagePath: " + countryFile + " |" + highlighted + "|");
		
		String imagePath = null;

		if ( countryFile != null ) {
			 imagePath = "amis-images/map-images/"+ countryFile + "_" + highlighted + ".png";
//			 defaultMap = countryID + "_" + highlighted;
		}
		else {
			 imagePath = "amis-images/map-images/"+ defaultMap + ".png";
		}

		System.out.println("changeImagePath: " + imagePath);
		System.out.println("defaultMap: " + defaultMap);
		buildAfricanMap(imagePath, defaultMap);
	}
	
	public static native void changeImageJS(String defaultMap)/*-{
	
	  	var countryFile = $wnd.countryFile;
		  	 
	  	if ( countryFile != '' ) {
	  		//alert(countryFile + " change image is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapAfricaRegion::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, "HIGHLIGHTED", defaultMap);
	  	}
	  	else{
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapAfricaRegion::changeImagePath(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(null, null, defaultMap);
	  		//alert(countryID + " change is null");
	  	}
	  	
	  	
	  	// setting the default value
	  	$wnd.countryID = '';
		$wnd.countryFile = '';
		$wnd.countryLabel = '';
	  	
		//alert("$wnd.regionID: " + $wnd.regionID);
	
	}-*/;

	
	
	public static ClickHandler getSelectedCountry(final String defaultMap) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				System.out.println("ClickHandler getSelectedCountry: ");
				getSelectedCountryJS(defaultMap);
				
			}
		};
	}
	
	public static void selectCountry(String countryFile, String countryID, String countryLabel) {
		System.out.println("selectCountry: " + countryFile + " | " + countryID + " | " + countryLabel);
		
		String defaultMap = countryFile + "_HIGHLIGHTED";
		String imagePath = "amis-images/map-images/"+ defaultMap + ".png";


		System.out.println("selectCountry: " + imagePath);
		System.out.println("defaultMap: " + defaultMap);
		buildAfricanMap(imagePath, defaultMap);
		AMISHome.getDatasetViewAgent(countryID, countryLabel);
	}

	public static native void getSelectedCountryJS(String defaultMap)/*-{
	
	  
	  	var countryID = $wnd.countryID;
	  	var countryFile = $wnd.countryFile;
	  	var countryLabel = $wnd.countryLabel;

	  	if ( countryID != '' ) {
//	  		alert(countryID + " selectCountry is not null");
	  		@org.fao.fenix.web.modules.amis.client.view.map.AMISMapAfricaRegion::selectCountry(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(countryFile, countryID, countryLabel);
	  	}
	  	else{
	  		//alert(countryID + " selectCountry is null");
	  	}
	  	
		// setting the default value
	  	$wnd.countryID = '';
	  	$wnd.countryFile = '';
	  	$wnd.countryLabel = '';
//		alert("$wnd.countryID: " + $wnd.countryID);
	
	}-*/;

}
