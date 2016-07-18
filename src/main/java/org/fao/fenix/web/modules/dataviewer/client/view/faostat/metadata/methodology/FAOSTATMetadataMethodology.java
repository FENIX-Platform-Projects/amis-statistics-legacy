package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.methodology;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATMetadataMethodology {

	ContentPanel panel;

	ContentPanel typesPanel;

	ContentPanel displayPanel;

	FAOSTATMethodologyTypesPanel types;

	Button exportButton;

	Button tableButton;


	public FAOSTATMetadataMethodology() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);

		typesPanel = new ContentPanel();
		typesPanel.setBodyBorder(false);
		typesPanel.setHeaderVisible(false);

		displayPanel = new ContentPanel();
		displayPanel.setAutoWidth(true);
		displayPanel.setAutoHeight(true);
		displayPanel.setBodyBorder(false);
		displayPanel.setHeaderVisible(false);

		types = new FAOSTATMethodologyTypesPanel();

	}

	public ContentPanel build() {
		panel.add(new Html(""));
		panel.add(buildMainPanel());


		return panel;
	}

	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);

		p.add(buildTypes());

		p.add(displayPanel);

		return p;
	}

	private ContentPanel buildTypes() {
		return types.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getMethodologyTypesPanel() {
		return typesPanel;
	}


	public FAOSTATMethodologyTypesPanel getTypes() {
		return types;
	}



	public ContentPanel getDisplayPanel() {
		return displayPanel;
	}


}
