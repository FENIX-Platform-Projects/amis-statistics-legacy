package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.units;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataUnits {
	
	ContentPanel panel;
	
	ContentPanel displayPanel;
	
	FAOSTATUnitDisplayPanel units;

	Button exportButton;
	
	Button tableButton;
	
	
	public FAOSTATMetadataUnits() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
					
		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);
		
		units = new FAOSTATUnitDisplayPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		displayPanel.add(buildUnits());
		
		p.add(displayPanel);
		
		return p;
	}
	
	private ContentPanel buildUnits() {
		return units.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
	public FAOSTATUnitDisplayPanel getUnits() {
		return units;
	}


	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}

	
}
