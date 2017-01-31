package org.fao.fenix.web.modules.table.client.view;


import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.r.client.view.RMenu;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;


public class TableWindow extends FenixWindow {
	
	private TableToolbar tableToolbar;
	
	private TableFilters tableFilters;
	
	private RMenu rMenu;
	
	public TableGrid tableGrid;
	
	public int pageSize= TableConstants.PAGE_SIZE; //equivalent to items per page
	private PagingToolBar toolBar = new PagingToolBar(pageSize);   

	public void build(Long resourceId, final boolean isEditable, final boolean isFlex, String datasetTitle) {
		tableFilters = new TableFilters(this, resourceId);
		tableFilters.build(resourceId, false);
		setSize(600, 650);
		resizeWest(522);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
		setCenterProperties();
		tableToolbar = new TableToolbar(this, resourceId, isEditable, isFlex, datasetTitle);
		RMenu rMenu = new RMenu();
		tableToolbar.getToolbar().add(rMenu.build());
		RController.buildRMenu(this, rMenu);
		getCenter().setTopComponent(tableToolbar.getToolbar());
		
		VerticalPanel bottomPanel = new VerticalPanel();
		bottomPanel.add(getTablePager());
		//SocialBar sb = new SocialBar();
		//bottomPanel.add(sb.getSocialBar(ResourceType.DATASET, String.valueOf(resourceId)));
		getCenter().setBottomComponent(bottomPanel);
		
		format();
		
		
//		ReportWizardController.addToReport(null, this);
	}
	
	public void buildForReportWizard(Long resourceId, final boolean isEditable, final boolean isFlex, ReportViewer reportViewer, String datasetTitle) {
		tableFilters = new TableFilters(this, resourceId);
		tableFilters.buildForReport(resourceId, false, reportViewer);
		resizeWest(500);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
		setCenterProperties();		
		getCenter().setTopComponent(new TableToolbar(this, resourceId, isEditable, isFlex, datasetTitle).getToolbar());
		
		VerticalPanel bottomPanel = new VerticalPanel();
		bottomPanel.add(getTablePager());
		//SocialBar sb = new SocialBar();
		//bottomPanel.add(sb.getSocialBar(ResourceType.DATASET, String.valueOf(resourceId)));
		
		getCenter().setBottomComponent(bottomPanel);
		format();
	}
	
	
	public void showAllData(Long resourceId, final boolean isEditable, final boolean isFlex, String datasetTitle) {
		tableFilters = new TableFilters(this, resourceId);
		tableFilters.build(resourceId, true);
		setSize(600, 650);
		resizeWest(522);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
		setCenterProperties();
		tableToolbar = new TableToolbar(this, resourceId, isEditable, isFlex, datasetTitle);
		RMenu rMenu = new RMenu();
		tableToolbar.getToolbar().add(rMenu.build());
		RController.buildRMenu(this, rMenu);
		getCenter().setTopComponent(tableToolbar.getToolbar());
		
		VerticalPanel bottomPanel = new VerticalPanel();
		bottomPanel.add(getTablePager());
		//SocialBar sb = new SocialBar();
		//bottomPanel.add(sb.getSocialBar(ResourceType.DATASET, String.valueOf(resourceId)));
		getCenter().setBottomComponent(bottomPanel);
		
		format();
		
		
//		ReportWizardController.addToReport(null, this);
	}	
	
	
    public void buildLatestData(Long resourceId, final boolean isEditable, final boolean isFlex, String datasetTitle) {
    	tableFilters = new TableFilters(this, resourceId);
		tableFilters.build(resourceId, true);
	
		resizeWest(500);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
				
		setCenterProperties();
		getCenter().setTopComponent(new TableToolbar(this, resourceId, isEditable, isFlex, datasetTitle).getToolbar());
		
		/*TableVO tableVO = (TableVO)tableFilters.getPanel().getData(TableConstants.TABLE_VO);
			
		TableController controller = tableFilters.getTableController();
		controller.getLatestData(tableVO, resourceId, this, "LOAD_BUTTON");*/
	
		VerticalPanel bottomPanel = new VerticalPanel();
		bottomPanel.setBorders(true);
		bottomPanel.add(getTablePager());
		
		getCenter().setBottomComponent(bottomPanel);
		
		//getCenter().setBottomComponent(getTablePager());
		format();
		
	}
    
    public void buildFilteredTable(Long resourceId, final boolean isEditable, final boolean isFlex, String datasetTitle) {
    	tableFilters = new TableFilters(this, resourceId);
		tableFilters.build(resourceId, true);
	
		resizeWest(500);
		setWestProperties();
		getWest().add(tableFilters.getPanel());
		addWestPartToWindow();
				
		setCenterProperties();
		getCenter().setTopComponent(new TableToolbar(this, resourceId, isEditable, isFlex, datasetTitle).getToolbar());
		
		/*TableVO tableVO = (TableVO)tableFilters.getPanel().getData(TableConstants.TABLE_VO);
			
		TableController controller = tableFilters.getTableController();
		controller.getLatestData(tableVO, resourceId, this, "LOAD_BUTTON");*/
		
		VerticalPanel bottomPanel = new VerticalPanel();
		bottomPanel.add(getTablePager());
		//SocialBar sb = new SocialBar();
		//bottomPanel.add(sb.getSocialBar(ResourceType.DATASET, String.valueOf(resourceId)));
		
		getCenter().setBottomComponent(bottomPanel);
		
		format();
		
	}

	
	public void format() {
		setSize(TableConstants.TABLE_WINDOW_WIDTH, "650px");//TableConstants.TABLE_WINDOW_HEIGHT);
		setCollapsible(true);
		
		getCenter().setWidth(TableConstants.CENTER_WIDTH);
		getCenter().setScrollMode(Scroll.NONE);
		getWest().setWidth("500px");//TableConstants.WEST_WIDTH
		
		setTitle(BabelFish.print().tableView()); 
		getCenter().setHeaderVisible(false);
		
		getWest().setHeading(BabelFish.print().datasetFilter());
		
		getWindow().setMaximizable(true);
	}

	public TableToolbar getTableToolbar() {
		return tableToolbar;
	}

	public void setTableToolbar(TableToolbar tableToolbar) {
		this.tableToolbar = tableToolbar;
	}

	public TableFilters getTableFilters() {
		return tableFilters;
	}

	public void setTableFilters(TableFilters tableFilters) {
		this.tableFilters = tableFilters;
	}

	
	public TableGrid getDatasetGrid() {
		return tableGrid;
	}

	public void setDatasetGrid(TableGrid datasetGrid) {
		this.tableGrid = datasetGrid;
	}
	
	public PagingToolBar getTablePager() {
		return toolBar;   
	}
}
