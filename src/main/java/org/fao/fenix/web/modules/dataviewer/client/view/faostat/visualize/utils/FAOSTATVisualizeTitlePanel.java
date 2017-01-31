package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATVisualizeTitlePanel {

	ContentPanel panel;
	
	public FAOSTATVisualizeTitlePanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
	}
	
	public ContentPanel build() {
		return panel;
	}
	
	public void build(String title) {

		panel.removeAll();
		
		panel.add(buildTitle(title));
		
	}
	
	private HorizontalPanel buildTitle(String title) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.CENTER);
		
		panel.addStyleName("visualize_title");
		
		Html html = new Html("<div class='visualize_title'>" + title + "</div>");
		panel.add(html);
		return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
	
	
}
