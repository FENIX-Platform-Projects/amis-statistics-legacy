package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.currency;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataCurrency {
	
	ContentPanel panel;
	
	ContentPanel displayPanel;
	
	FAOSTATCurrencyDisplayPanel currency;

	Button exportButton;
	
	Button tableButton;
	
	
	public FAOSTATMetadataCurrency() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
				
		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);
		
		currency = new FAOSTATCurrencyDisplayPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		
		return panel;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		displayPanel.add(buildCurrency());
		
		p.add(displayPanel);
		
		return p;
	}
	
	private ContentPanel buildCurrency() {
		return currency.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	
	public FAOSTATCurrencyDisplayPanel getCurrency() {
		return currency;
	}



	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}

	
}
