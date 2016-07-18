package org.fao.fenix.web.modules.amis.client.view.compare;

import org.fao.fenix.web.modules.amis.client.control.history.AMISHistoryController;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class AMISCompareSubMenu {
	
private ContentPanel panel;
private HorizontalPanel menuPanel;

public HorizontalPanel getMenuPanel() {
	return menuPanel;
}

public AMISCompareSubMenu() {	
	panel = new ContentPanel();
	panel.setBodyBorder(false);
	panel.setHeaderVisible(false);
	panel.setBorders(false);
	menuPanel = new HorizontalPanel();
	menuPanel.setHeight(35);
	menuPanel.setStyleAttribute("backgroundColor", "#FFFFFF");
	menuPanel.setWidth(800);
	menuPanel.setBorders(false);
	
}


 public ContentPanel build(AMISCurrentView currentView) {
	menuPanel.add(buildPanel("Standard View &amp; Compare", AMISCurrentView.COMPARE_DATASOURCES, currentView));
	menuPanel.add(addSeparator());
	menuPanel.add(buildPanel("Activity Tracking", AMISCurrentView.COMPARE_ACTIVITY_TRACKING, currentView));

	panel.add(menuPanel);
	
    return panel;
}
	
 
private HorizontalPanel buildPanel(String title, AMISCurrentView view, AMISCurrentView currentView) {
	
	HorizontalPanel p = new HorizontalPanel();
	p.setId(view.name());
	p.setData("base-style-name", "submenu");
	
	if(p.getId().equals(currentView.name())){
			p.addStyleName("submenu-selected");
		} else {
			p.addStyleName("submenu");
		}
	//p.addStyleName("secondary-menu-title");
	
	
	System.out.println("$$%$%$%%$% AMISCompareSubMenu selected: "+currentView.name() + " : "+ p.getStyleName());
	ClickHtml html = new ClickHtml();
	html.setId(currentView.name());
	html.setHtml("<div>"+title+"</div>");
	
	html.addListener(Events.OnClick, AMISHistoryController.setHistoryItem(view));
	
	p.add(html);
	return p;
}


private HorizontalPanel addSeparator() {
	
	HorizontalPanel p = new HorizontalPanel();
	p.addStyleName("submenu-separator");
	p.add(new Html("<div>|</div>"));
	
	return p;
}

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
	
}

