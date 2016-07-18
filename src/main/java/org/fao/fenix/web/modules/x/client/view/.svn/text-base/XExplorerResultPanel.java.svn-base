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
package org.fao.fenix.web.modules.x.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.x.common.vo.XExplorerModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;

public class XExplorerResultPanel {

	private ContentPanel resultPanel;

	private Button downloadButton;
	
	private Button deleteButton;

	private Button closeButton;
	
	private XExplorerPager pager;
	
	private ListStore<XExplorerModelData> store;
	
	private Grid<XExplorerModelData> grid;

	public XExplorerResultPanel() {
		resultPanel = new ContentPanel();
		resultPanel.setHeaderVisible(false);
		pager = new XExplorerPager();
	}

	public ContentPanel build() {
		resultPanel.add(createTable());
		resultPanel.setBottomComponent(buildButtonsPanel());
		return resultPanel;
	}
	
	public ContentPanel createTable() {
		
		List<ColumnConfig> configs = createColumnConfigurations();

		store = new ListStore<XExplorerModelData>();

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setScrollMode(Scroll.AUTO);
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(475, 550);
		
		cp.setBottomComponent(pager.build());

		GridSelectionModel<XExplorerModelData> gsm = new GridSelectionModel<XExplorerModelData>();
		gsm.setSelectionMode(SelectionMode.SINGLE);
		
		grid = new Grid<XExplorerModelData>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.getView().setForceFit(true); 
		grid.setLoadMask(true);   
		grid.setBorders(false);
		grid.setStripeRows(true);
		grid.setSelectionModel(gsm);
		cp.add(grid);
		
		return cp;
	}
	
	private List<ColumnConfig> createColumnConfigurations() {
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig column = new ColumnConfig("name", "Name", 400);
		configs.add(column);

		column = new ColumnConfig("node", "Node", 150);
		configs.add(column);

		column = new ColumnConfig("source", "Source", 250);
		configs.add(column);

		column = new ColumnConfig("region", "Geographic Area", 150);
		configs.add(column);

		column = new ColumnConfig("dateLastUpdate", "Date Last Updated", 150);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column);
		
		return configs;
	}

	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		downloadButton = new Button("Download");
		downloadButton.setMinWidth(220);
		deleteButton = new Button("Delete");
		deleteButton.setMinWidth(50);
		closeButton = new Button("Close");
		closeButton.setMinWidth(50);
		buttonsPanel.add(downloadButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(closeButton);
		return buttonsPanel;
	}

	public ListStore<XExplorerModelData> getStore() {
		return store;
	}

	public XExplorerPager getPager() {
		return pager;
	}

	public Grid<XExplorerModelData> getGrid() {
		return grid;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public Button getDownloadButton() {
		return downloadButton;
	}

}