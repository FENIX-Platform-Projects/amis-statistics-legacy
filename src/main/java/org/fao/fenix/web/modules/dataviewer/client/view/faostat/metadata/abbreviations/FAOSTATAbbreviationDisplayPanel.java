package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.abbreviations;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata.FAOSTATMetadataController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATAbbreviationDisplayPanel {
		
	ContentPanel panel;
	
	public FAOSTATAbbreviationDisplayPanel() {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);

	}
	
	public ContentPanel build(FAOSTATMetadataAbbreviation metadata) {
		
		VerticalPanel v = new VerticalPanel();
		   
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
        v.add(panel);
        
        panel.add(v);
        
		FAOSTATMetadataController.getAbbreviations(panel);		
		
		return panel;
	}
	
	
	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
	}
		
}
