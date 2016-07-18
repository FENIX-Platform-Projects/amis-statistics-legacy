package org.fao.fenix.web.modules.amis.client.view.download;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class AMISDownloadTitlePanel {

	ContentPanel panel;
	
	public AMISDownloadTitlePanel() {
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
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		
	
		Html html = new Html("<div class='download_title'>" + title + "</div>");
		panel.add(html);
		return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
	
	
}
