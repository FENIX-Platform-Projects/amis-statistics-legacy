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

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.extjs.gxt.ui.client.widget.tree.Tree.CheckCascade;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class TreeRegionPanel {

	private ContentPanel panel;

	private ListBox countryList;

	private ListBox regionList;

	private Tree tree;
	
	private Tree stationTree;
	
	private TextField<String> oracle;

	private VerticalPanel wrapper;

	public TreeRegionPanel() {
		panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setHeaderVisible(false);
		tree = new Tree();
		stationTree = new Tree();
		wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		panel.setHeight("200px");
	}

	public ContentPanel build() {
		wrapper.add(buildOraclePanel());
		wrapper.add(buildForestPanel());
		panel.add(wrapper);
		return panel;
	}
	
	private HorizontalPanel buildOraclePanel() {
		HorizontalPanel oraclePanel = new HorizontalPanel();
		oraclePanel.setSpacing(10);
		HTML label = new HTML(
				"<div style='font-family: Sans-Serif; font-weight:bold; color: #15428B;'>"
						+ BabelFish.print().region() + ": </div>");
		label.setWidth("150px");
		oracle = new TextField<String>();
		oracle.setWidth("500px");
		oraclePanel.add(label);
		oraclePanel.add(oracle);
		return oraclePanel;
	}
	
	private HorizontalPanel buildForestPanel() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		horizontalPanel.add(buildTreePanel());
		horizontalPanel.add(buildStationTreePanel());
		return horizontalPanel;
	}

	private VerticalPanel buildTreePanel() {
		VerticalPanel treePanel = new VerticalPanel();
		tree.setCheckable(true);
		tree.getStyle().setLeafIconStyle("workspaceMap");
		treePanel.setWidth("250px");
		treePanel.add(tree);
		return treePanel;
	}
	
	private VerticalPanel buildStationTreePanel() {
		VerticalPanel stationTreePanel = new VerticalPanel();
		stationTree.getRootItem().add(new TreeItem("Ground Stations"));
		stationTree.setCheckable(true);
		stationTree.setCheckStyle(CheckCascade.NONE);
		stationTree.getStyle().setLeafIconStyle("workspaceTable");
//		stationTreePanel.add(stationTree);
		stationTreePanel.setWidth("150px");
		return stationTreePanel;
	}

	public ListBox getCountryList() {
		return countryList;
	}

	public ListBox getRegionList() {
		return regionList;
	}

	public Tree getTree() {
		return tree;
	}

	public TextField<String> getOracle() {
		return oracle;
	}

	public Tree getStationTree() {
		return stationTree;
	}

}