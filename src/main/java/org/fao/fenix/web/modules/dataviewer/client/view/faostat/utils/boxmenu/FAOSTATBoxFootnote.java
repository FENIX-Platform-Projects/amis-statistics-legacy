package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATBoxFootnote {
	
	public static HorizontalPanel buildFootnote(String footnote) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.add(new Html("<div class='footnote'> " + footnote + " </div>"));
		
		return panel;
	}
}
