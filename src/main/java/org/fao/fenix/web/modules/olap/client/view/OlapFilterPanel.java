package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;

public class OlapFilterPanel {

	private ContentPanel filterPanel;
	
	public Button addFilterButton;
	
	public Button removeFilterButton;

	public OlapFilterPanel() {
		filterPanel = new ContentPanel();
		filterPanel.setLayout(new AccordionLayout());
		filterPanel.setHeaderVisible(false);
		filterPanel.setBodyBorder(false);
		filterPanel.setIconStyle("filter");
	}
	
	public ContentPanel build() {
		filterPanel.setBottomComponent(buildButtonsPanel());
		return filterPanel;
	}
	
	public HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		addFilterButton = new Button(BabelFish.print().addFilter());
		buttonsPanel.add(addFilterButton);
		removeFilterButton = new Button(BabelFish.print().removeFilter());
		buttonsPanel.add(removeFilterButton);
		return buttonsPanel;
	}
	
	public ContentPanel getFilterPanel() {
		return filterPanel;
	}

	public Button getAddFilterButton() {
		return addFilterButton;
	}

	public Button getRemoveFilterButton() {
		return removeFilterButton;
	}

}