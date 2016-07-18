package org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu;


import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.analysis.FAOSTATAnalysisController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata.FAOSTATMetadataController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.FAOSTAT;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.home.FAOSTATHome;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu.FAOSTATMainMenu;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATMenuController {

	
	public static void setMainMenuTabStyle(final FAOSTATCurrentView currentView, final FAOSTATMainMenu menubar){

		System.out.println("setMainMenuTabStyle: view "+currentView + "menubar "+menubar);

		HorizontalPanel selectedPanel = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(currentView.name());

		if(selectedPanel!=null){
			selectedPanel.removeStyleName(selectedPanel.getStyleName());

			String baseStyleName = (String)selectedPanel.getData("base-style-name");

			for (FAOSTATCurrentView view: FAOSTATCurrentView.values()) {
				if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) {	    		 
					menubar.getMenuPanel().getItemByItemId(view.name()).setStyleName(baseStyleName);
				}
				else {
					selectedPanel.setStyleName(baseStyleName+"-selected");
				}	
			}
		}	
	}

	
	public static void openView(final String historyToken, final FAOSTATMainMenu menubar){	
		FAOSTATCurrentView currentView = FAOSTATCurrentView.getCurrentView(historyToken);
		
		System.out.println("openView: "+currentView + " historyToken "+ historyToken + "menubar "+menubar);
				
		//Rebuild the language links based on the current history
		FAOSTATHome.buildAndReplaceLanguageElement("language", historyToken);
		
		//Call the view
		openViewAgent(currentView, menubar);
	}
		
	public static Listener<ComponentEvent> setHistoryItem(final FAOSTATCurrentView currentView) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				
			  setHistoryAnchor(currentView);
			}
		};
	}

	
	/**
	 * 
	 * This method sets the History Anchor for the selected view
	 * 
	 * @param currentView
	 * @return 
	 * @return
	 */
	public static void setHistoryAnchor(final FAOSTATCurrentView currentView) {
		System.out.println("-------------> FAOSTATMenuController setHistoryAnchor - currentView: " + currentView.toString());
		

		
		History.removeHistoryListener(FAOSTAT.historyListener);
		
		switch (currentView) {
		case HOME:
			History.newItem(FAOSTATCurrentView.HOME.name()); 
			break;
		case DOWNLOAD:
			History.newItem(FAOSTATCurrentView.DOWNLOAD.name()); 
			break;
		case DOWNLOAD_STANDARD:
			History.newItem(FAOSTATCurrentView.DOWNLOAD_STANDARD.name());
			break;
		case DOWNLOAD_BULK:
			History.newItem(FAOSTATCurrentView.DOWNLOAD_BULK.name()); 
			break;
		case VISUALIZE:
			History.newItem(FAOSTATCurrentView.VISUALIZE.name()); 
			break;
		case VISUALIZE_BY_TOPIC:
			History.newItem(FAOSTATCurrentView.VISUALIZE_BY_TOPIC.name()); 
			break;
		case VISUALIZE_BY_DOMAIN:
			History.newItem(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN.name()); 
			break;
		case VISUALIZE_BY_AREA:
			History.newItem(FAOSTATCurrentView.VISUALIZE_BY_AREA.name()); 
			break;
		case VISUALIZE_TOP_20:
			History.newItem(FAOSTATCurrentView.VISUALIZE_TOP_20.name()); 
			break;
		case METADATA:
			History.newItem(FAOSTATCurrentView.METADATA.name()); 
			break;
		case METADATA_ABBREVIATIONS:
			History.newItem(FAOSTATCurrentView.METADATA_ABBREVIATIONS.name()); 
			break;
		case METADATA_GLOSSARY:
			History.newItem(FAOSTATCurrentView.METADATA_GLOSSARY.name()); 
			break;
		case METADATA_CLASSIFICATION:
			History.newItem(FAOSTATCurrentView.METADATA_CLASSIFICATION.name()); 
			break;
		case METADATA_UNITS:
			History.newItem(FAOSTATCurrentView.METADATA_UNITS.name()); 
			break;
		case METADATA_CURRENCY:
			History.newItem(FAOSTATCurrentView.METADATA_CURRENCY.name()); 
			break;
		case METADATA_METHODOLOGY:
			History.newItem(FAOSTATCurrentView.METADATA_METHODOLOGY.name()); 
			break;
		case COMPARE:
			History.newItem(FAOSTATCurrentView.COMPARE.name()); 
			break;
		case COMPARE_BY_AREA:
			History.newItem(FAOSTATCurrentView.COMPARE_BY_AREA.name()); 
			break;
		case SEARCH_DATA:
			History.newItem(FAOSTATCurrentView.SEARCH_DATA.name()); 
			break;
		case ANALYSIS:
			History.newItem(FAOSTATCurrentView.ANALYSIS.name()); 
			break;
		default:
			break;
		}
		
		History.addHistoryListener(FAOSTAT.historyListener);
		History.fireCurrentHistoryState();
	}
	
	
	/**
	 * 
	 * This method calls the selected view
	 * 
	 * set the selected style to the menu bar and 
	 * set the default style to the others and load the view
	 * 
	 * 
	 * @param currentView
	 * @param menubar
	 * @return 
	 * @return
	 */
	public static void openViewAgent(final FAOSTATCurrentView currentView, final FAOSTATMainMenu menubar) {
		System.out.println("FAOSTATMenuController openViewAgent() - VIEW: " + currentView.toString());
		
		// TODO: should get DOWNLOAD_STANDARD instead of DOWNLOAD as an entry point
		GoogleAnalyticsController.trackEvent(currentView.toString(), "Access to " + currentView.toString(), "");

		// remove MAIN_CONTENT AND SECONDARY_MENU
		if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0) 
			RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));
		if (RootPanel.get("SECONDARY_MENU_VISUALIZE").getWidgetCount() > 0 && !currentView.name().contains(FAOSTATCurrentView.VISUALIZE.name()))
			RootPanel.get("SECONDARY_MENU_VISUALIZE").remove(RootPanel.get("SECONDARY_MENU_VISUALIZE").getWidget(0));
		
		if (RootPanel.get("SECONDARY_MENU_DOWNLOAD").getWidgetCount() > 0 && !currentView.name().contains(FAOSTATCurrentView.DOWNLOAD.name()))
			RootPanel.get("SECONDARY_MENU_DOWNLOAD").remove(RootPanel.get("SECONDARY_MENU_DOWNLOAD").getWidget(0));
		
		if (RootPanel.get("SECONDARY_MENU_METADATA").getWidgetCount() > 0 && !currentView.name().contains(FAOSTATCurrentView.METADATA.name()))
			RootPanel.get("SECONDARY_MENU_METADATA").remove(RootPanel.get("SECONDARY_MENU_METADATA").getWidget(0));
		if (RootPanel.get("SECONDARY_MENU_COMPARE").getWidgetCount() > 0 && !currentView.name().contains(FAOSTATCurrentView.COMPARE.name()))
			RootPanel.get("SECONDARY_MENU_COMPARE").remove(RootPanel.get("SECONDARY_MENU_COMPARE").getWidget(0));

		//FAOSTATHome.hideHomeVisibility();

		switch (currentView) {
		case HOME:
			setMainMenuTabStyle(FAOSTATCurrentView.HOME, menubar);
			new FAOSTATHome(menubar).build();
			break;
		case DOWNLOAD:
			setMainMenuTabStyle(FAOSTATCurrentView.DOWNLOAD, menubar);
			FAOSTATDownloadController.callDownloadView();
			break;
		case DOWNLOAD_STANDARD:
			setMainMenuTabStyle(FAOSTATCurrentView.DOWNLOAD, menubar);
			FAOSTATDownloadController.callDownloadSubView(currentView);
			break;
		case DOWNLOAD_BULK:
			setMainMenuTabStyle(FAOSTATCurrentView.DOWNLOAD, menubar);
			FAOSTATDownloadController.callDownloadSubView(currentView);
			break;
		case VISUALIZE:
			setMainMenuTabStyle(FAOSTATCurrentView.VISUALIZE, menubar);
			FAOSTATVisualizeController.callVisualizeView();
			break;
		case VISUALIZE_BY_TOPIC:
			setMainMenuTabStyle(FAOSTATCurrentView.VISUALIZE, menubar);
			//sub-view
			FAOSTATVisualizeController.callVisualizeSubView(currentView);
			break;
		case VISUALIZE_BY_DOMAIN:
			setMainMenuTabStyle(FAOSTATCurrentView.VISUALIZE, menubar);
			//sub-view
			FAOSTATVisualizeController.callVisualizeSubView(currentView);
			break;
		case VISUALIZE_BY_AREA:			
			setMainMenuTabStyle(FAOSTATCurrentView.VISUALIZE, menubar);
			//setSecondaryMenuTabStyle(FAOSTATCurrentView.VISUALIZE_BY_AREA, menubar);
			//sub-view
			FAOSTATVisualizeController.callVisualizeSubView(currentView);
			break;
		case VISUALIZE_TOP_20:
			setMainMenuTabStyle(FAOSTATCurrentView.VISUALIZE, menubar);
			//sub-view
			FAOSTATVisualizeController.callVisualizeSubView(currentView);
			break;
		case METADATA:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			FAOSTATMetadataController.callMetadataView();
			break;
		case METADATA_ABBREVIATIONS:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case METADATA_GLOSSARY:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case METADATA_CLASSIFICATION:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case METADATA_UNITS:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case METADATA_CURRENCY:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case METADATA_METHODOLOGY:
			setMainMenuTabStyle(FAOSTATCurrentView.METADATA, menubar);
			//sub-view
			FAOSTATMetadataController.callMetadataSubView(currentView);
			break;
		case COMPARE:
			setMainMenuTabStyle(FAOSTATCurrentView.COMPARE, menubar);
			FAOSTATCompareController.callCompareView();
			break;
		case COMPARE_BY_AREA:
			setMainMenuTabStyle(FAOSTATCurrentView.COMPARE, menubar);
			FAOSTATCompareController.callCompareViewAgent(currentView);
			break;
		case COMPARE_DATA:
			setMainMenuTabStyle(FAOSTATCurrentView.COMPARE_DATA, menubar);
			FAOSTATCompareController.callCompareViewAgent(currentView);
			break;
		case SEARCH_DATA:
			setMainMenuTabStyle(FAOSTATCurrentView.SEARCH_DATA, menubar);
			FAOSTATSearchController.callSearchViewAgent(currentView);
			break;
		case ANALYSIS:
			setMainMenuTabStyle(FAOSTATCurrentView.ANALYSIS, menubar);
			FAOSTATAnalysisController.callSearchViewAgent(currentView);
			break;
		default:
			break;
		}
	}

}
