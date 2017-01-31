package org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu;



import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.FAOSTAT;

import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.History;

import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabBar;

public class FAOSTATMainMenu {

	
	private ContentPanel panel;
	private HorizontalPanel menuPanel;
	
	
	
	// main menu          
	private TabBar tabBar = new TabBar();  
		
	public HorizontalPanel getMenuPanel() {
		return menuPanel;
	}



	public FAOSTATMainMenu() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		menuPanel = new HorizontalPanel();
		menuPanel.setHeight(28);
		menuPanel.addStyleName("menu");
	}

	public ContentPanel build(FAOSTAT faostat) {

		menuPanel.add(buildPanel(FAOSTATLanguage.print().home(), FAOSTATCurrentView.HOME));
		menuPanel.add(buildPanel(FAOSTATLanguage.print().browseData(), FAOSTATCurrentView.VISUALIZE));
		menuPanel.add(buildPanel(FAOSTATLanguage.print().downloadData(), FAOSTATCurrentView.DOWNLOAD));
		menuPanel.add(buildPanel(FAOSTATLanguage.print().compareData(), FAOSTATCurrentView.COMPARE));
		menuPanel.add(buildPanel(FAOSTATLanguage.print().searchData(), FAOSTATCurrentView.SEARCH_DATA));	
		menuPanel.add(buildPanel(FAOSTATLanguage.print().faostatAnalysis(), FAOSTATCurrentView.ANALYSIS));
		menuPanel.add(buildPanel(FAOSTATLanguage.print().methodsAndStandards(), FAOSTATCurrentView.METADATA));
		
		panel.add(menuPanel);
	    
	    return panel;
	}
	
	private HorizontalPanel buildPanel(String title, FAOSTATCurrentView currentView) {
		HorizontalPanel p = new HorizontalPanel();
		p.setId(currentView.name());
		
		p.setData("base-style-name", "tab");

		/** TODO: remove with the new interface **/
		p.setHeight(28);
		
		//set default HOME as selected
		if(p.getId().equals(FAOSTATCurrentView.HOME.name()))
			p.addStyleName("tab-selected");
		else 
			p.addStyleName("tab");
		
		ClickHtml html = new ClickHtml();
		html.setId(currentView.name());
		html.setHtml("<div class='main-menu-title'>" + title + "</div>");
		
		html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryItem(currentView));

		p.add(html);
		return p;
	}
	
     
    /** 
     * observe tabs 
     */ 
    public boolean onBeforeTabSelected(SourcesTabEvents sender, int tabIndex) { 
            return true; 
    } 
     
   
    /** 
     * are there params in historyToken 
     *  
     * @return 
     */ 
    private boolean isParamsInHistoryToken() { 
            String s = History.getToken(); 
             
            if (s.contains("?")) { 
                    return true; 
            } else { 
                    return false; 
            } 
    }



	public TabBar getTabBar() {
		return tabBar;
	}



	public void setTabBar(TabBar tabBar) {
		this.tabBar = tabBar;
	} 
   

}