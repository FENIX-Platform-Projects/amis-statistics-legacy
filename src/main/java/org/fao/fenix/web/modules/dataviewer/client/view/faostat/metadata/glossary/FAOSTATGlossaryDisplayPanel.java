package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.glossary;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata.FAOSTATMetadataController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATGlossaryDisplayPanel {
		
	ContentPanel panel;
	
   
	public FAOSTATGlossaryDisplayPanel() {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);

	}
	
	public ContentPanel build(FAOSTATMetadataGlossary metadata) {
		
		VerticalPanel v = new VerticalPanel();
		   
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
        v.add(panel);
        
        panel.add(v);
        
		FAOSTATMetadataController.getGlossary(panel);		
		
		return panel;
	}
	
	
	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
	}
		
}
