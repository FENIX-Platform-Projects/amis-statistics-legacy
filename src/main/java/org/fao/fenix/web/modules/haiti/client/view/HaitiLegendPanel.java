package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class HaitiLegendPanel {

	private ContentPanel legendPanel;
	
	public HaitiLegendPanel(String language) {
		legendPanel = new ContentPanel(new FitLayout());
		legendPanel.setScrollMode(Scroll.AUTO);
		legendPanel.setHeading(HaitiLangEntry.getInstance(language).legend());
	}
	
	public ContentPanel build() {
		return legendPanel;
	}

	public ContentPanel getLegendPanel() {
		return legendPanel;
	}
	
}
