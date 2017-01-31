package org.fao.fenix.web.modules.fsatmis.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.view.OlapFilterPanel;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FSATMISFilterPanel extends OlapFilterPanel {

	public Button refreshTableButton;
	
	public FSATMISFilterPanel() {
		super();
	}
	
	@Override
	public HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		super.addFilterButton = new Button(BabelFish.print().addFilter());
		buttonsPanel.add(addFilterButton);
		super.removeFilterButton = new Button(BabelFish.print().removeFilter());
		buttonsPanel.add(removeFilterButton);
		refreshTableButton = new Button(BabelFish.print().refresh());
		buttonsPanel.add(refreshTableButton);
		
		return buttonsPanel;
	}

	public Button getRefreshTableButton() {
		return refreshTableButton;
	}
	
}
