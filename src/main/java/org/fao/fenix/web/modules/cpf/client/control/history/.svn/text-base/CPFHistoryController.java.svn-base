package org.fao.fenix.web.modules.cpf.client.control.history;

import org.fao.fenix.web.modules.cpf.common.constants.CPFCurrentView;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.user.client.History;

public class CPFHistoryController {

	
	public static Listener<ComponentEvent> setHistoryItem(final CPFCurrentView currentView) {
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
	 * 
	 * @param currentView
	 * @return 
	 * @return
	 */
	
	public static void setHistoryAnchor(final CPFCurrentView currentView) {
		System.out.println("CPFHistoryController setHistoryAnchor - currentView: " + currentView.toString());

		switch (currentView) {
		case HOME:
			History.newItem(CPFCurrentView.HOME.name()); 
			break;
		default:
			break;
		}
	}

}
