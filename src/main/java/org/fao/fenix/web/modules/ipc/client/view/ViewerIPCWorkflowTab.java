package org.fao.fenix.web.modules.ipc.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.ipc.client.control.CreateIPCWorkflowController;
import org.fao.fenix.web.modules.ipc.common.vo.IPCGridMD;

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
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ViewerIPCWorkflowTab {

	
	private ContentPanel panel;
	
	public ListBox country = new ListBox();
	
	private ListStore<IPCGridMD> md;
	private Grid<IPCGridMD> grid;
	
	public Button openIPC = new Button("View Workflow");
	
	private String firstWidth = "150px";
	
	
	public ViewerIPCWorkflowTab() {
		panel = new ContentPanel();
		openIPC.setSize(40, 25);
	}
	
	
	
	public ContentPanel build() {
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(10);
		v.add(buildCountrylistBox());
		v.add(addSpace());
		v.add(buildGridPanel());
		v.add(addSpace());
		v.add(buildButtonPanel());
		CreateIPCWorkflowController.fillCountryBox(this);
		panel.add(v);
		panel.setBorders(false);
		enanche();
		return panel;
	}
	
	public HorizontalPanel buildCountrylistBox() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Country: <b>");
		country = new ListBox();
		label.setWidth(firstWidth);
		p.add(label);
		country.setWidth("310px");
		country.addChangeListener(fillGrid(this));
		p.add(country);
		return p;
	}
	
	private void enanche(){
		openIPC.addSelectionListener(CreateIPCWorkflowController.openViewverWindow(this, grid));
	}
	
	
	
	public ContentPanel buildGridPanel(){
		ContentPanel panel = new ContentPanel();
		panel.setBorders(false);
		panel.setPosition(5, 5);
		  
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		 
		md = new ListStore<IPCGridMD>();	    
		 
		
		
		
		ColumnConfig column = new ColumnConfig();  
		column.setId("name");  
		column.setHeader("Workflow Title");  
		column.setWidth(100);  	   
		configs.add(column);

		
		column = new ColumnConfig();  
		column.setId("referenceLayer");  
		column.setHeader("Reference Layer");  
		column.setWidth(100);
		configs.add(column);
		
		column = new ColumnConfig();  
		column.setId("provinceName");  
		column.setHeader("Interested Area");  
		column.setWidth(100);
		configs.add(column);
		
		column = new ColumnConfig();  
		column.setId("contributor");  
		column.setHeader("Contributor");  
		column.setWidth(100);
		configs.add(column);
		
		column = new ColumnConfig();  
		column.setId("description");  
		column.setHeader("Description");  
		column.setWidth(100);  
		configs.add(column); 
		
		column = new ColumnConfig();  
		column.setId("period");  
		column.setHeader("Period");  
		column.setWidth(100);  
		configs.add(column);
		
		column = new ColumnConfig();  
		column.setId("status");  
		column.setHeader("Status");  
		column.setWidth(100);  
		configs.add(column); 
			
	    ColumnModel cm = new ColumnModel(configs);  
  
		grid = new Grid<IPCGridMD>(md ,cm);  
		GridSelectionModel<IPCGridMD> sm =  new GridSelectionModel<IPCGridMD>();
		sm.setSelectionMode(SelectionMode.SINGLE);
		grid.setSelectionModel(sm);
		grid.setSize(450, 440);	
		panel.add(grid);
		panel.setHeaderVisible(false);
//		panel.setSize(450, 300);
		panel.setLayout(new FitLayout());
//		panel.setBodyBorder(true);    

		grid.setStyleAttribute("borderTop", "none");	
		grid.setTrackMouseOver(true);
				
		return panel;
	}
	
	public HorizontalPanel addSpace() {
		HorizontalPanel p = new HorizontalPanel();
		p.add(new HTML("<br>"));
		return p;
	}
	
	public Button buildButtonPanel() {	
		return openIPC;
	}
	
	

	
	@SuppressWarnings("unchecked")
	private ChangeListener fillGrid(final ViewerIPCWorkflowTab tab) {
		ChangeListener t = new ChangeListener() {  
			public void onChange(Widget sender) {
				CreateIPCWorkflowController.fillViewerGrid(tab);
				
			}

			
		};  
		return t;
	}


	public ListStore<IPCGridMD> getMd() {
		return md;
	}



	public void setMd(ListStore<IPCGridMD> md) {
		this.md = md;
	}



	public Grid<IPCGridMD> getGrid() {
		return grid;
	}



	public void setGrid(Grid<IPCGridMD> grid) {
		this.grid = grid;
	}



	public ListBox getCountry() {
		return country;
	}
	
	
	

}
