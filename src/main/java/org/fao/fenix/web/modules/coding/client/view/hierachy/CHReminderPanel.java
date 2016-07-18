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
package org.fao.fenix.web.modules.coding.client.view.hierachy;

import org.fao.fenix.web.modules.coding.client.control.hierarchy.CodingHierarchyController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.tree.Tree;

public class CHReminderPanel {

	private ContentPanel reminderPanel;
	
	private Button remove;
	
	private Button removeAll;
	
	private Button extract;
	
	private Tree tree;
	
	public CHReminderPanel() {
		reminderPanel = new ContentPanel();
		reminderPanel.setHeaderVisible(false);
		reminderPanel.setScrollMode(Scroll.AUTO);
		remove = new Button(BabelFish.print().removeSelection());
		removeAll = new Button(BabelFish.print().removeAll());
		extract = new Button("extract");
		tree = new Tree();
		tree.setCheckable(true);
	}
	
	public ContentPanel build() {
		reminderPanel.setPosition(5, 5);
		reminderPanel.add(tree);
		reminderPanel.setBottomComponent(buidlButtonsPanel());
		reminderPanel.setHeight("400px");
		return reminderPanel;
	}
	
	private HorizontalPanel buidlButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		buttonsPanel.add(remove);
		remove.addSelectionListener(CodingHierarchyController.removeSelection(tree));
		buttonsPanel.add(removeAll);
		buttonsPanel.add(extract);
		removeAll.addSelectionListener(CodingHierarchyController.removeAll(tree));
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