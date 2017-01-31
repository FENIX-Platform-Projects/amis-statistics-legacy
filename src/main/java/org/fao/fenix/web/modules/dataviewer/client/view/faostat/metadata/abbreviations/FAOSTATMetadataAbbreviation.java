package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.abbreviations;

import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataAbbreviation {
	
	ContentPanel panel;
	
	ContentPanel displayPanel;
	
	FAOSTATAbbreviationDisplayPanel abbreviation;

	Button exportButton;
	
	Button tableButton;
	
	
	public FAOSTATMetadataAbbreviation() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.MAIN_CONTENT_MIN_HEIGHT) + "px");
				
		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);
		
		abbreviation = new FAOSTATAbbreviationDisplayPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		displayPanel.add(buildAbbreviations());
		
		p.add(displayPanel);
		
		return p;
	}
	
	private ContentPanel buildAbbreviations() {
		System.out.println("buildAbbreviations  .........");
		
		return abbreviation.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	
	public FAOSTATAbbreviationDisplayPanel getAbbreviations() {
		return abbreviation;
	}



	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}

	
}
