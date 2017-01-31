package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.classification;

import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataClassification {
	
	ContentPanel panel;
	
	ContentPanel domainsPanel;
	
	ContentPanel displayPanel;
	
	FAOSTATClassificationDomainPanel domains;

	Button exportButton;
	
	Button tableButton;
	
	
	public FAOSTATMetadataClassification() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(true) + "px"));
				
		domainsPanel = new ContentPanel();
		domainsPanel.setBodyBorder(false);
		domainsPanel.setHeaderVisible(false);
		
		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);
		
		domains = new FAOSTATClassificationDomainPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildDomains());
		
		p.add(displayPanel);
		
		return p;
	}
	
	private ContentPanel buildDomains() {
		return domains.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getDomainsPanel() {
		return domainsPanel;
	}

	
	public FAOSTATClassificationDomainPanel getDomains() {
		return domains;
	}



	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}

	
}
