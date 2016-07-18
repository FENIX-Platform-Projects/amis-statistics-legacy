package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATMapDisclaimerPanel {

	public static ContentPanel build() {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.addStyleName("map_disclaimer_panel");
		panel.add(DataViewerClientUtils.addVSpace(7));
		Html html = new Html("<div class='map_disclaimer'>"+FAOSTATLanguage.print().mapDisclaimer()+"</div>");
		panel.add(html);
		return panel;
	}
}
