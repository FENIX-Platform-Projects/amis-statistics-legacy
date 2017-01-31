package org.fao.fenix.web.modules.dataMapper.client;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.dataMapper.client.control.DataMapperController;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperMapPanel;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperTab;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperToolbar;
import org.fao.fenix.web.modules.dataMapper.client.view.DataMapperTopToolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

public class DataMapper implements EntryPoint {

	
	DataMapperMapPanel dataMapperMapPanel;
	
	public DataMapper() {
		
	}
	
	public void onModuleLoad() {

//		 logo
		Image faoLogo = new Image("datamapper-images/ws_mapper_fusion.jpg");
//		faoLogo.setSize("100px", "100px");
		RootPanel.get("LOGO").add(faoLogo);
		
		// map panel
		dataMapperMapPanel = new DataMapperMapPanel("EN");
		RootPanel.get("MAP_PANEL").add(dataMapperMapPanel.build("800px", "400px"));
		
		DataMapperController.initializePredifinedMap("START", dataMapperMapPanel);
		
//		RootPanel.get("MAIN_MENU").add(adamToolbar.buildMainMenuBar());
		
		// options	
		DataMapperTab dataMapperTab = new DataMapperTab();
		RootPanel.get("OPTIONS_PANEL").add(dataMapperTab.build());
		
		DataMapperToolbar bottomPanel = new DataMapperToolbar();
		RootPanel.get("TOOLBAR_PANEL").add(bottomPanel.build(dataMapperMapPanel, dataMapperTab));
		
		DataMapperTopToolbar toolbarTop = new DataMapperTopToolbar();
		RootPanel.get("TOOLBAR_TOP_PANEL").add(toolbarTop.build(dataMapperMapPanel, dataMapperTab));
		
		
		Window.addWindowClosingHandler(new ClosingHandler(){
			public void onWindowClosing(ClosingEvent arg0) {
				 System.out.println("cleaning function");
				 DataMapperController.cleanLayersAndViews();
			}
		});
		
		
	}
	
	
	

	
}