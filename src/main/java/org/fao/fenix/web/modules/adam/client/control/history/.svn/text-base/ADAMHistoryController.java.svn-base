package org.fao.fenix.web.modules.adam.client.control.history;



import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.history.ADAMHistory;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.user.client.History;

public class ADAMHistoryController {

	/**
	 * 
	 * This method sets the History Anchor for the selected view
	 * 
	 * 
	 * @param currentView
	 * @return 
	 * @return
	 */
	
	
	public static void setHistoryItem(final ADAMCurrentVIEW currentView, final String selectedTab) {
		setHistoryAnchor(currentView, selectedTab);
	}
	
	public static Listener<ComponentEvent> setHistoryItemListener(final ADAMCurrentVIEW currentView, final String selectedTab) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				setHistoryAnchor(currentView, selectedTab);
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
	public static void setHistoryAnchor(final ADAMCurrentVIEW currentView, final String selectedTab) {
		System.out.println("-------------> ADAMHistoryController setHistoryAnchor - currentView: " + currentView);
		ADAMController.currentVIEW = currentView;
		ADAMController.selectedTab = selectedTab;
		
		ADAMCurrentVIEW tab = ADAMCurrentVIEW.getCurrentView(selectedTab);
		
		History.removeHistoryListener(ADAMHistory.historyListener);
		
		switch (tab) {
		case HOME:
			History.newItem(ADAMCurrentVIEW.HOME.name()); 
			break;
		case BROWSE:
			History.newItem(ADAMCurrentVIEW.BROWSE.name()); 
			break;
		case ANALYSE:
			History.newItem(ADAMCurrentVIEW.ANALYSE.name());
			break;
		case ANALYSE_VENN:
			History.newItem(ADAMCurrentVIEW.ANALYSE_VENN.name()); 
			break;
		case ANALYSE_PRIORITIES:
			History.newItem(ADAMCurrentVIEW.ANALYSE_PRIORITIES.name()); 
			break;
		case ANALYSE_PARTNER_MATRIX:
			History.newItem(ADAMCurrentVIEW.ANALYSE_PARTNER_MATRIX.name()); 
			break;
		case ANALYSE_PROJECTS:
			History.newItem(ADAMCurrentVIEW.ANALYSE_PROJECTS.name()); 
			break;
		case ANALYSE_IMPLEMENTING_AGENCIES:
			History.newItem(ADAMCurrentVIEW.ANALYSE_IMPLEMENTING_AGENCIES.name()); 
			break;
		case ANALYSE_FAO_COMPARATIVE_ADVANTAGE:
			History.newItem(ADAMCurrentVIEW.ANALYSE_FAO_COMPARATIVE_ADVANTAGE.name()); 
			break;
		case PROFILES:
			History.newItem(ADAMCurrentVIEW.PROFILES.name()); 
			break;
		case LINKS:
			History.newItem(ADAMCurrentVIEW.LINKS.name()); 
			break;
		default:
			break;
		}
		
		History.addHistoryListener(ADAMHistory.historyListener);
		History.fireCurrentHistoryState();
	}

}
