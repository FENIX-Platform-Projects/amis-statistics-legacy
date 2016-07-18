package org.fao.fenix.web.modules.fsatmis.client;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTool;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class FSATMIS implements EntryPoint {

	public void onModuleLoad() {
	
		// build tool
		FSATMISTool t = new FSATMISTool();
		t.build();
		
		// logos
		Image giewsLogo = new Image("rainfall-images/giews_logo_blue.png");
		Image faoLogo = new Image("rainfall-images/fao_logo_blue.png");
		
		// create banner
		RootPanel.get("rainfall").add(t.getToolBase());
		RootPanel.get("giewsLogo").add(giewsLogo);
		RootPanel.get("faoLogo").add(faoLogo);
	}
	
}