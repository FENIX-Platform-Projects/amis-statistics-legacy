package org.fao.fenix.web.modules.rainfall.client;

import org.fao.fenix.web.modules.rainfall.client.view.RainfallTool;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallToolResult;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Rainfall implements EntryPoint {

	public void onModuleLoad() {
		
		// create tool
		RainfallTool t = new RainfallTool();
		t.build();
		
		// logos
		Image giewsLogo = new Image("rainfall-images/giews_logo_blue.png");
		Image faoLogo = new Image("rainfall-images/fao_logo_blue.png");
		
		// create banner
		RootPanel.get("rainfall").add(t.getToolBase());
		RootPanel.get("giewsLogo").add(giewsLogo);
		RootPanel.get("faoLogo").add(faoLogo);
		
		// add result panel
		new RainfallToolResult();
		
		// add a leaf...
//		Image leaf = new Image("images/Leaf_small.png");
//		VerticalPanel leafPanel = new VerticalPanel();
//		leafPanel.add(leaf);
//		RootPanel.get("leaf").add(leafPanel);
	}
	
	
	
}