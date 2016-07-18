package org.fao.fenix.web.modules.cpf.client.control.menu;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.cpf.client.lang.CPFLanguage;
import org.fao.fenix.web.modules.cpf.client.view.home.CPFHome;
import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;
import org.fao.fenix.web.modules.cpf.common.constants.CPFCurrentView;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class CPFMenuController {

	
	public static void openView(String historyToken, CPFMainMenu mainMenu) {
		CPFCurrentView currentView = CPFCurrentView.getCurrentView(historyToken);
		
		System.out.println("openView: "+currentView + " historyToken "+ historyToken);
		
		//Call the view
		openViewAgent(currentView, mainMenu);
	}
	
	public static Listener<ComponentEvent> openView(final CPFCurrentView currentView, final CPFMainMenu menubar) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {

				HorizontalPanel p = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(currentView.name());
                p.removeStyleName(p.getStyleName());
			    String baseStyleName = (String)p.getData("base-style-name");
			    
			    
			    for (CPFCurrentView view: CPFCurrentView.values()) {
			    	if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) {
			    		HorizontalPanel otherPanel = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(view.name());
			    		otherPanel.setStyleName(baseStyleName);
			    		ClickHtml html = (ClickHtml)otherPanel.getItemByItemId(view.name());						   
			    		html.setHtml("<div class='main-menu-title'>" + CPFLanguage.print().getString(view.name()) + "</div>");
			    		
			    	}
			    	else {
			    		ClickHtml html = (ClickHtml)p.getItemByItemId(currentView.name());
			    		p.setStyleName(baseStyleName+"-selected");
			    		html.setHtml("<div class='main-menu-title-selected'>" + CPFLanguage.print().getString(currentView.name()) + "</div>");
			    		
			    	}
			    }

				openViewAgent(currentView, menubar);
			}
		};
	}


	/**
	 *
	 * This method call the selected view
	 *
	 * set the selected style to the panel and
	 * set the default style to the others
	 *
	 *
	 * @param currentView
	 * @return
	 * @return
	 */

	public static void openViewAgent(final CPFCurrentView currentView, final CPFMainMenu menubar) {
		System.out.println("VIEW: " + currentView.toString());

		/**
		 * TODO: select/unselect styles
		 *
		 */

		// remove MAIN_CONTENT
		if (RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0)
			RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));

		//CPFHome.hideHomeVisibility();

		switch (currentView) {
		case HOME:
			setMainMenuTabStyle(CPFCurrentView.HOME, menubar);
			new CPFHome(menubar).build();
			break;
		default:
			break;
		}
		
	}	
		
	public static void setMainMenuTabStyle(final CPFCurrentView currentView, final CPFMainMenu menubar){

		HorizontalPanel selectedPanel = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(currentView.name());

		if(selectedPanel!=null){
			selectedPanel.removeStyleName(selectedPanel.getStyleName());

			String baseStyleName = (String)selectedPanel.getData("base-style-name");

			for (CPFCurrentView view: CPFCurrentView.values()) {
				if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) {	    		 
					menubar.getMenuPanel().getItemByItemId(view.name()).setStyleName(baseStyleName);
				}
				else {
					selectedPanel.setStyleName(baseStyleName+"-selected");
				}	
			}
		}	
	}



}
