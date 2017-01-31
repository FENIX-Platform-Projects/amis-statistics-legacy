package org.fao.fenix.web.modules.wcct.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.wcct.client.control.WCCTController;
import org.fao.fenix.web.modules.wcct.client.view.vo.WCCTTableMD;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class TableCenterPanel {
	
	private VerticalPanel panel;
	private ListStore<WCCTTableMD> listStore;
	private Grid<WCCTTableMD> grid;
	private ToolBar toolbar;
	
	public TableCenterPanel(){
		System.out.println("end creating map");
		listStore = new ListStore<WCCTTableMD>();
		panel = new VerticalPanel();
		toolbar = new ToolBar();
	}
	
	public VerticalPanel build(WCCTTool wcctTool){
		insertViewIcons(toolbar);
		panel.add(toolbar);
		panel.add(buildGridPanel());
		return panel;
	}
	
	public HorizontalPanel buildButtons(){
		HorizontalPanel panel = new HorizontalPanel();
		return panel;
	}
	
	public VerticalPanel buildGridPanel(){
		VerticalPanel panel = new VerticalPanel();
		panel.setPosition(5, 5);
		  
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();  
		column.setId("country");  
		column.setHeader(BabelFish.print().country());  
		column.setWidth(140);  	   
		configs.add(column);
		 
		
		column = new ColumnConfig();  
		column.setId("crop");  
		column.setHeader(BabelFish.print().crop());  
		column.setWidth(140);
		configs.add(column);
		

		column = new ColumnConfig();  
		column.setId("cropStage");  
		column.setHeader(BabelFish.print().cropStage());  
		column.setWidth(140);  	   
		configs.add(column);
		
		column = new ColumnConfig();  
		column.setId("further");  
		column.setHeader("Further");  
		column.setWidth(140);  
		configs.add(column); 
		 
		
		column = new ColumnConfig();  
		column.setId("startMonth");  
		column.setHeader(BabelFish.print().startMonth());  
		column.setWidth(90);
		configs.add(column);
		
		 
		column = new ColumnConfig();  
		column.setId("startDek");  
		column.setHeader(BabelFish.print().startDek());  
		column.setWidth(80);  
		configs.add(column); 
		
		column = new ColumnConfig();  
		column.setId("endMonth");  
		column.setHeader(BabelFish.print().endMonth());  
		column.setWidth(90);
		configs.add(column);
		
		 
		column = new ColumnConfig();  
		column.setId("endDek");  
		column.setHeader(BabelFish.print().endDek());  
		column.setWidth(80);  
		configs.add(column); 
			
	    ColumnModel cm = new ColumnModel(configs);  
		grid = new Grid<WCCTTableMD>(listStore ,cm);
//		grid.setHeight(390);
		grid.setSize(935, 383);
		
		grid.setStripeRows(true);
		grid.setStyleAttribute("borderTop", "none");
		grid.setBorders(true);	
		panel.add(grid);
		panel.setSize(970, 390);
		panel.setScrollMode(Scroll.AUTOX);
		return panel;
	}
	
	private void insertViewIcons(ToolBar toolbar) {
		IconButton iconButton;
		
		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO PDF ADAPTER
		IconButton exportToPdfButton = getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		exportToPdfButton.addSelectionListener(WCCTController.buildExportToSelectionListener(listStore, "pdf"));

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(BabelFish.print().sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		exportToXlsButton.addSelectionListener(WCCTController.buildExportToSelectionListener(listStore, "xls"));

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(BabelFish.print().sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		exportToPptButton.addSelectionListener(WCCTController.buildExportToSelectionListener(listStore, "ppt"));
	}
	
	private void addListenerToButton(IconButton exportToXlsButton,
			SelectionListener<ComponentEvent> buildExportToSelectionListener) {
		// TODO Auto-generated method stub
		
	}

	public IconButton getIconButton(String key) {
		return (IconButton) toolbar.getData(key);
	}


	public ListStore<WCCTTableMD> getListStore() {
		return listStore;
	}

	public void setListStore(ListStore<WCCTTableMD> listStore) {
		this.listStore = listStore;
	}

	public Grid<WCCTTableMD> getGrid() {
		return grid;
	}

	public void setGrid(Grid<WCCTTableMD> grid) {
		this.grid = grid;
	}
	
	public void addButtonToTheToolbar(IconButton button, String key) {
		toolbar.add(button);
		toolbar.setData(key, button);
	}

	public VerticalPanel getPanel() {
		return panel;
	}
	
	
}
