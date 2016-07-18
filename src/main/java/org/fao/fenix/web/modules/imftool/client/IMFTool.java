package org.fao.fenix.web.modules.imftool.client;

import org.fao.fenix.web.modules.imftool.client.control.IMFToolController;
import org.fao.fenix.web.modules.imftool.client.view.IMFToolPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class IMFTool implements EntryPoint {

	public void onModuleLoad() {
		IMFToolPanel imfToolPanel = new IMFToolPanel();
		RootPanel.get("thisissick").add(imfToolPanel.build());
		IMFToolController.getLastDataInfo(imfToolPanel);
	}
	
}