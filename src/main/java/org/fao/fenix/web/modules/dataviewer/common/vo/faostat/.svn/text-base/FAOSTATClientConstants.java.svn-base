package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;


import com.google.gwt.user.client.Window;

public class FAOSTATClientConstants {
	
	
	
	// TODO: check the CSS to get the right width and height (and the margins)
	public static Integer INTERFACE_WIDTH = 1100;	
	
	// height
	public static Integer HEADER_HEIGHT = 65;	
	
	// height
	public static Integer MENU_HEIGHT = 30;	
	
	// height
	public static Integer SUBMENU_HEIGHT = 45;	
	
	// height
	public static Integer SEPARATION = 15;	
	
	// margin + height
	public static Integer FOOTER_HEIGHT = 20 + 30;	

	// TODO: check if the SUBMENU IS ACTIVE
	public static Integer getMainContentHeight(Boolean hasSubMenu) {
		
		if ( hasSubMenu ) {
			System.out.println("hasSubMenu HEIGHT: " + (MAIN_CONTENT_MIN_HEIGHT - SUBMENU_HEIGHT));
			return MAIN_CONTENT_MIN_HEIGHT - SUBMENU_HEIGHT;
		}
		else { 
			System.out.println("!hasSubMenu HEIGHT: " + (MAIN_CONTENT_MIN_HEIGHT));
			return MAIN_CONTENT_MIN_HEIGHT;
		}
	}

	public static Integer MAIN_CONTENT_MIN_HEIGHT = Window.getClientHeight() - (HEADER_HEIGHT + MENU_HEIGHT + FOOTER_HEIGHT + SEPARATION);

}
