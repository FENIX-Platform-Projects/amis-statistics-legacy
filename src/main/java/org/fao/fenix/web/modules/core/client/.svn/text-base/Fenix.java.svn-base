package org.fao.fenix.web.modules.core.client;

import org.fao.fenix.web.modules.core.client.control.menu.FenixMenuBuilder;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.client.utils.URLInterceptor;
import org.fao.fenix.web.modules.corejs.client.FenixJS;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.welcome.client.control.SplashWindowController;
import org.timepedia.exporter.client.Exporter;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Fenix implements EntryPoint {
	
	public static int COUNTER_1 = 0;
	
	public static int COUNTER_2 = 2;

	public void onModuleLoad() {

		// build toolbox
//		new FenixToolbox();

		// parse URL parameters
		new URLInterceptor();

		// add menus
		buildMenu(false, true);

		try {
			Exporter export = (Exporter)GWT.create(FenixJS.class);
	        export.export();
	    } catch (Exception e) {
	    	FenixAlert.error("ERROR", e.getMessage());
	    }
	    
	    SplashWindowController.getNodeSettings(null);
	    
	}

	public static void buildMenu(boolean removeOldMenu, boolean isLogin) {

		FenixMenuBuilder b = new FenixMenuBuilder();
		String width = String.valueOf(getScreenWidth()) + "px";

		HorizontalPanel menuPanel = new HorizontalPanel();
		menuPanel.setSpacing(2);

		LayoutContainer secondaryToolbarContainer = new LayoutContainer();

		ToolBar mainToolbar = new ToolBar();
		ToolBar secondaryToolbar = new ToolBar();

		String lang = CheckLanguage.getLanguage();
		if (lang.equalsIgnoreCase("AR")) {
			mainToolbar.setAlignment(HorizontalAlignment.RIGHT);
			secondaryToolbar.setAlignment(HorizontalAlignment.LEFT);
		} else {
			secondaryToolbar.setAlignment(HorizontalAlignment.RIGHT);
		}

		// mainToolbar.setWidth("800px");
		// secondaryToolbar.setWidth("250px");

		b.buildToolBar("main-menu-admin", mainToolbar, isLogin);
		b.buildToolBar("secondary-menu-admin", secondaryToolbar, isLogin);

		HorizontalPanel spacerPanel = new HorizontalPanel();
		spacerPanel.setWidth(calculateToolbarSpacerWidth(width, 1060));

		menuPanel.add(mainToolbar);
		// menuPanel.add(spacerPanel);
		secondaryToolbarContainer.add(secondaryToolbar);

		secondaryToolbarContainer.setData("secondaryToolbar", secondaryToolbar);

		// menuPanel.setWidth(width);
		menuPanel.setBorders(true);

		if (removeOldMenu) {
			RootPanel.get("topMenu").remove(0);
			RootPanel.get("menu").remove(0);
		}

		// RootPanel.get("topMenu").add(secondaryToolbarContainer);
		// RootPanel.get("menu").add(menuPanel);

		RootPanel.get("menu").add(secondaryToolbarContainer);
		RootPanel.get("topMenu").add(menuPanel);
	}

	private void buildBannerPanner() {

		String width = String.valueOf(getScreenWidth()) + "px";

		HorizontalPanel bannerPanel = new HorizontalPanel();
		bannerPanel.setSpacing(1);

		Image fenixBanner = new Image("images/FENIX_BANNER.png");
		Image faoLogo = new Image("images/FAO_UE_BANNER.png");

		int otherComponentsWidth = faoLogo.getWidth() + fenixBanner.getWidth();

		HorizontalPanel spacerPanel = new HorizontalPanel();
		spacerPanel.setWidth(calculateToolbarSpacerWidth(width,
				otherComponentsWidth));

//		bannerPanel.add(faoLogo);
		bannerPanel.add(fenixBanner);

		RootPanel.get("banner").add(bannerPanel);
	}

	private void addLeaf() {
		Image leaf = new Image("images/Leaf_small.png");
		RootPanel.get("leaf").add(leaf);
	}

	private static String calculateToolbarSpacerWidth(String screenWidth,
			int otherComponentsWidth) {
		int w = Integer.parseInt(screenWidth.substring(0,
				screenWidth.length() - 2));
		return String.valueOf(0.99 * (w - otherComponentsWidth)) + "px";
		// return String.valueOf((w - otherComponentsWidth)) + "px";
	}

	public static native int getScreenWidth() /*-{
												return $wnd.screen.width;
												}-*/;

}