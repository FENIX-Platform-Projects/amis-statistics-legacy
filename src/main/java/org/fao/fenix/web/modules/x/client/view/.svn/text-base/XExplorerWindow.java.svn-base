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

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.x.client.control.XExplorerController;
import org.fao.fenix.web.modules.x.client.control.XExplorerDatasetFilterController;
import org.fao.fenix.web.modules.x.client.control.XExplorerPagerController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class XExplorerWindow extends FenixWindow {

	private XExplorerSearchPanel xExplorerSearchPanel;
	
	private XExplorerResultPanel xExplorerResultPanel;
	
	public XExplorerWindow() {
		xExplorerSearchPanel = new XExplorerSearchPanel();
		xExplorerResultPanel = new XExplorerResultPanel();
	}
	
	public void build() {
		buildWestPanel();
		buildCenterPanel();
		enhanceButtons();
		enhancePager();
		enhanceTable();
		format();
		show();
	}
	
	private void enhanceButtons() {
//		xExplorerSearchPanel.getSearchButton().addSelectionListener(XExplorerController.search(xExplorerSearchPanel));
		xExplorerSearchPanel.getSearchButton().addSelectionListener(XExplorerController.search(xExplorerSearchPanel, xExplorerResultPanel));
		xExplorerResultPanel.getDeleteButton().addSelectionListener(XExplorerController.delete(xExplorerSearchPanel, xExplorerResultPanel));
		xExplorerResultPanel.getCloseButton().addSelectionListener(XExplorerController.close(this));
		xExplorerSearchPanel.getSynchButton().addSelectionListener(XExplorerController.synchronize(xExplorerSearchPanel, xExplorerResultPanel));
		xExplorerResultPanel.getDownloadButton().addSelectionListener(XExplorerController.download(xExplorerSearchPanel, xExplorerResultPanel));
	}
	
	@SuppressWarnings("deprecation")
	private void enhanceTable() {
		// Row-click listener on the grid
		xExplorerResultPanel.getGrid().addListener(Events.RowClick, XExplorerDatasetFilterController.rowClickListener(this));
		// fill values on dataset dimension's change
		xExplorerSearchPanel.getxExplorerDatasetFilter().getDatasetDimensionsList().addChangeListener(XExplorerDatasetFilterController.datasetDimensionChange(xExplorerSearchPanel, xExplorerResultPanel));
		// values oracle listener
		xExplorerSearchPanel.getxExplorerDatasetFilter().getValuesOracle().addKeyListener(XExplorerDatasetFilterController.valuesOracleListener(xExplorerSearchPanel.getxExplorerDatasetFilter().getValuesOracle(), xExplorerSearchPanel, xExplorerResultPanel));
	}
	
	private void enhancePager() {
		XExplorerPager pager = xExplorerResultPanel.getPager();
		pager.getNextButton().addSelectionListener(XExplorerPagerController.next(xExplorerSearchPanel, xExplorerResultPanel, pager));
		pager.getPrevButton().addSelectionListener(XExplorerPagerController.prev(xExplorerSearchPanel, xExplorerResultPanel, pager));
		pager.getFirstButton().addSelectionListener(XExplorerPagerController.first(xExplorerSearchPanel, xExplorerResultPanel, pager));
		pager.getLastButton().addSelectionListener(XExplorerPagerController.last(xExplorerSearchPanel, xExplorerResultPanel, pager));
		pager.getGoToPageButton().addSelectionListener(XExplorerPagerController.goToPage(xExplorerSearchPanel, xExplorerResultPanel, pager));
	}
	
	private void buildWestPanel() {
		setWestProperties();
		fillWestPart(xExplorerSearchPanel.build());
		getWestData().setSize(300);
		getWest().setHeading("Search Parameters");
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeading("Result List");
		
		getCenter().add(xExplorerResultPanel.build());
		
//		xExplorerResultPanel.build();
//		getCenter().add(xExplorerResultPanel.getGrid());
//		getCenter().setBottomComponent(xExplorerResultPanel.getPager().build());
		
		getCenter().setSize(200, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize("800px", "650px");
		getWindow().setHeading("X - Information eXchange Tool");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("xExplorer");
	}

	public XExplorerSearchPanel getxExplorerSearchPanel() {
		return xExplorerSearchPanel;
	}

	public XExplorerResultPanel getxExplorerResultPanel() {
		return xExplorerResultPanel;
	}
	
}