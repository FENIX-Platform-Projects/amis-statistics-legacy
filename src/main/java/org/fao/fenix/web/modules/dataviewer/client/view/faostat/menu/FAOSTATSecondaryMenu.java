package org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class FAOSTATSecondaryMenu {

	
	private ContentPanel panel;
	
	public FAOSTATSecondaryMenu() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build() {
		HorizontalPanel p = new HorizontalPanel();
		panel.addStyleName("transparent_bg");
		p.setSpacing(10);
		p.addStyleName("transparent_bg");
  
		p.add(addSpacing());
		p.add(buildMenuPanel("Domain"));
		p.add(buildMenuPanel("Countries"));
		p.add(buildMenuPanel("Indicators"));
		

		panel.add(p);
	    return panel;
	}
	
	private HorizontalPanel buildMenuPanel(String title) {
		HorizontalPanel p = new HorizontalPanel();

		p.addStyleName("secondary-menu-title box");
		
		IconButton icon = new IconButton("arrow-down_disabled");

		p.add(new Html("<div class='underlined'>" + title + "</div>"));
		p.add(icon);
		return p;
	}
	
	
	
	
	private HorizontalPanel addSpacing() {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(15);
		return p;
	}
	

}