/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.control.ConfigurationController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ConfigurationPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private ListBox configurationList;
	
	private Button load;
	
	private Button edit;
	
	private Button remove;
	
	public ConfigurationPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		configurationList = new ListBox();
		load = new Button(BabelFish.print().loadAndCreate());
		edit = new Button(BabelFish.print().load());
		remove = new Button(BabelFish.print().deleteFavourite());
	}
	
	public ContentPanel build() {
		wrapper.add(buildListPanel());
		wrapper.add(buildButtonsPanel());
		panel.add(wrapper);
		panel.setHeight("325px");
		return panel;
	}
	
	private HorizontalPanel buildListPanel() {
		HorizontalPanel listPanel = new HorizontalPanel();
		listPanel.setSpacing(10);
		HTML label = new HTML("<b>" + BabelFish.print().favourite() + ": </b>");
		listPanel.add(label);
		listPanel.add(configurationList);
		listPanel.setWidth("700px");
		configurationList.setWidth("550px");
		listPanel.setBorders(true);
		ConfigurationController.fillConfigurationList(configurationList);
		return listPanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		buttonsPanel.add(load);
		buttonsPanel.add(edit);
		buttonsPanel.add(remove);
		buttonsPanel.setWidth("700px");
		buttonsPanel.setBorders(true);
		return buttonsPanel;
	}

	public ListBox getConfigurationList() {
		return configurationList;
	}

	public Button getLoad() {
		return load;
	}

	public Button getEdit() {
		return edit;
	}

	public Button getRemove() {
		return remove;
	}
	
}
