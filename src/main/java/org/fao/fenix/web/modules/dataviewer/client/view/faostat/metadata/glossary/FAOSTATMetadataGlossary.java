package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.glossary;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataGlossary {
	
	ContentPanel panel;
	
	ContentPanel displayPanel;
	
	FAOSTATGlossaryDisplayPanel glossary;

	Button exportButton;
	
	Button tableButton;
	
	
	public FAOSTATMetadataGlossary() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
					
		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);
		
		glossary = new FAOSTATGlossaryDisplayPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		displayPanel.add(buildGlossary());
		
		p.add(displayPanel);
		
		return p;
	}
	
	private ContentPanel buildGlossary() {
		return glossary.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
	public FAOSTATGlossaryDisplayPanel getGlossary() {
		return glossary;
	}


	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}

	
}
