package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATDownloadTitlePanel {

	ContentPanel panel;
	
	public FAOSTATDownloadTitlePanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setLayout(new FillLayout());
	}
	
	public ContentPanel build() {
		return panel;
	}
	
	public void build(String title, String dateLastUpdate) {

		panel.removeAll();
		
		panel.add(buildTitle(title, dateLastUpdate));
		
	}
	
	public HorizontalPanel buildTitle(String title, String dateLastUpdate) {
		HorizontalPanel hz_panel = new HorizontalPanel();
		hz_panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		hz_panel.setVerticalAlign(VerticalAlignment.BOTTOM);
		HTML html = new HTML("<div class='download_title' style='cursor: pointer;' >" + title + "</div>");
		hz_panel.add(html);
		HTML dateLU = new HTML("<div class='download_title_date'>" + dateLastUpdate + "</div>");
		hz_panel.add(dateLU);
		return hz_panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}	
	
}