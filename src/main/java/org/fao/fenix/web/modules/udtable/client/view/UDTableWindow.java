package org.fao.fenix.web.modules.udtable.client.view;


import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.udtable.common.constants.UDTableConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class UDTableWindow extends FenixWindow {
	
	private UDTableToolbar tableToolbar;
	
	private UDTableFilter tableFilter;
	
	public UDTableGrid tableGrid;
	
	//List<UDTableFilterVO> filters;
	
	public int pageSize= UDTableConstants.PAGE_SIZE; //equivalent to items per page
	private PagingToolBar toolBar = new PagingToolBar(pageSize);   
	
	public void build(Long resourceId, final boolean isEditable, final boolean isFlex) {
		System.out.println("UDTableWindow");
		//filters = new ArrayList<UDTableFilterVO>();
		tableFilter = new UDTableFilter(this, resourceId);
	//	tableFilter.build(resourceId, filters);
	   tableFilter.build(resourceId, false);
	
		resizeWest(500);
		setWestProperties();
		getWest().add(tableFilter.getPanel());
		addWestPartToWindow();
				
		setCenterProperties();
		getCenter().setTopComponent(new UDTableToolbar(this, resourceId, isEditable, isFlex).getToolbar());
		getCenter().setBottomComponent(getTablePager());
		format();
//		show();
	}
	
 public void buildLatestData(Long resourceId, final boolean isEditable, final boolean isFlex) {
    	tableFilter = new UDTableFilter(this, resourceId);
    	tableFilter.build(resourceId, true);
	
		resizeWest(500);
		setWestProperties();
		getWest().add(tableFilter.getPanel());
		addWestPartToWindow();
				
		setCenterProperties();
		getCenter().setTopComponent(new UDTableToolbar(this, resourceId, isEditable, isFlex).getToolbar());
		
		/*TableVO tableVO = (TableVO)tableFilters.getPanel().getData(TableConstants.TABLE_VO);
			
		TableController controller = tableFilters.getTableController();
		controller.getLatestData(tableVO, resourceId, this, "LOAD_BUTTON");*/
		
		getCenter().setBottomComponent(getTablePager());
		format();
		
	}

	public void format() {
		setSize(UDTableConstants.TABLE_WINDOW_WIDTH, "650px");//TableConstants.TABLE_WINDOW_HEIGHT);
		setCollapsible(true);
		
		getCenter().setWidth(UDTableConstants.CENTER_WIDTH);
		getCenter().setScrollMode(Scroll.NONE);
		getWest().setWidth("500px");//TableConstants.WEST_WIDTH
		
		setTitle(BabelFish.print().tableView()+": UDTABLE for "); 
		getCenter().setHeaderVisible(false);
		
		getWest().setHeading(BabelFish.print().datasetFilter());
		
		getWindow().setMaximizable(true);
	}

	public UDTableToolbar getUDTableToolbar() {
		return tableToolbar;
	}

	public void setUDTableToolbar(UDTableToolbar tableToolbar) {
		this.tableToolbar = tableToolbar;
	}

	public UDTableFilter getUDTableFilter() {
		return tableFilter;
	}

	public void setUDTableFilter(UDTableFilter tableFilter) {
		this.tableFilter = tableFilter;
	}

	
	public UDTableGrid getUDTableGrid() {
		return tableGrid;
	}

	public void setUDTableGrid(UDTableGrid tableGrid) {
		this.tableGrid = tableGrid;
	}
	
	public PagingToolBar getTablePager() {
		return toolBar;   
	}

	public UDTableFilter getTableFilter() {
		return tableFilter;
	}
	
	
}
