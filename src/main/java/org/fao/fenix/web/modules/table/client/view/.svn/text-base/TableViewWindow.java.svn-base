package org.fao.fenix.web.modules.table.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.r.client.view.RMenu;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.client.control.TableViewController;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class TableViewWindow extends FenixWindow {

	private Long tableViewID;
	
	private TableViewToolbar tableToolbar;
	
	private TableViewFilters tableFilters;
	
	public TableViewGrid tableGrid;
	
	public int pageSize= TableConstants.PAGE_SIZE; //equivalent to items per page
	
	private PagingToolBar toolBar = new PagingToolBar(pageSize);   
	
	public void build(Long tableViewID, Long resourceId, final boolean isEditable, final boolean isFlex) {
		tableFilters = new TableViewFilters(this, resourceId, tableViewID);
		tableFilters.build(resourceId, tableViewID, false);
		resizeWest(522);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
		setCenterProperties();
		tableToolbar = new TableViewToolbar(this, resourceId, isEditable, isFlex);
		RMenu rMenu = new RMenu();
		tableToolbar.getToolbar().add(rMenu.build());
		RController.buildRMenu(this, rMenu);
		getCenter().setTopComponent(tableToolbar.getToolbar());
		getCenter().setBottomComponent(getTablePager());
		format();
		TableViewController tvc = new TableViewController(getTableFilters(), resourceId, tableViewID);
		tvc.loadUserSelections(resourceId, tableViewID, this);
		this.setTableViewID(tableViewID);
	}
	
	public void format() {
		setSize(TableConstants.TABLE_WINDOW_WIDTH, "650px");
		setCollapsible(true);
		getCenter().setWidth(TableConstants.CENTER_WIDTH);
		getCenter().setScrollMode(Scroll.NONE);
		getWest().setWidth("500px");//TableConstants.WEST_WIDTH
		setTitle(BabelFish.print().tableView()); 
		getCenter().setHeaderVisible(false);
		getWest().setHeading(BabelFish.print().datasetFilter());
		getWindow().setMaximizable(true);
	}

	public TableViewToolbar getTableToolbar() {
		return tableToolbar;
	}

	public void setTableViewToolbar(TableViewToolbar tableToolbar) {
		this.tableToolbar = tableToolbar;
	}

	public TableViewFilters getTableFilters() {
		return tableFilters;
	}

	public void setTableViewFilters(TableViewFilters tableFilters) {
		this.tableFilters = tableFilters;
	}

	
	public TableViewGrid getDatasetGrid() {
		return tableGrid;
	}

	public void setDatasetGrid(TableViewGrid datasetGrid) {
		this.tableGrid = datasetGrid;
	}
	
	public PagingToolBar getTablePager() {
		return toolBar;   
	}

	public Long getTableViewID() {
		return tableViewID;
	}

	public void setTableViewID(Long tableViewID) {
		this.tableViewID = tableViewID;
	}
	
}