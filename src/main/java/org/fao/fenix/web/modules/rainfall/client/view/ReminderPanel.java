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
import org.fao.fenix.web.modules.rainfall.client.control.RainfallController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.tree.Tree;

public class ReminderPanel {

	private ContentPanel reminderPanel;
	
	private Button remove;
	
	private Button removeAll;
	
	private Tree tree;
	
	public ReminderPanel() {
		reminderPanel = new ContentPanel();
		reminderPanel.setHeaderVisible(false);
		remove = new Button(BabelFish.print().removeSelection());
		removeAll = new Button(BabelFish.print().removeAll());
		tree = new Tree();
		tree.setCheckable(true);
	}
	
	public ContentPanel build() {
		reminderPanel.add(tree);
		reminderPanel.setBottomComponent(buidlButtonsPanel());
		reminderPanel.setHeight("400px");
		return reminderPanel;
	}
	
	private HorizontalPanel buidlButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		buttonsPanel.add(remove);
		remove.addSelectionListener(RainfallController.removeSelection(tree));
		buttonsPanel.add(removeAll);
		removeAll.addSelectionListener(RainfallController.removeAll(tree));
		return buttonsPanel;
	}

	public ContentPanel getReminderPanel() {
		return reminderPanel;
	}

	public Button getRemove() {
		return remove;
	}

	public Button getRemoveAll() {
		return removeAll;
	}

	public Tree getTree() {
		return tree;
	}
	
}