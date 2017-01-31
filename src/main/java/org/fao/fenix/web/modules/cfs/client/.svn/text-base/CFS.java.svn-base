package org.fao.fenix.web.modules.cfs.client;

import org.fao.fenix.web.modules.cfs.client.view.CFSTool;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class CFS implements EntryPoint {

	public void onModuleLoad() {
		
		// logos
		Image giewsLogo = new Image("rainfall-images/giews_logo_blue.png");
		Image faoLogo = new Image("rainfall-images/fao_logo_blue.png");
		RootPanel.get("giewsLogo").add(giewsLogo);
		RootPanel.get("faoLogo").add(faoLogo);
		
		// tool
		CFSTool tool = new CFSTool();
		tool.build();
		RootPanel.get("cfs").add(tool.getToolBase());
	}
	
}
