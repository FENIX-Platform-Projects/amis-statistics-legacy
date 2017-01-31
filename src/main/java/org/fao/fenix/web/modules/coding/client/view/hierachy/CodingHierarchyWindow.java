package org.fao.fenix.web.modules.coding.client.view.hierachy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.control.hierarchy.CodingHierarchyController;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingHiearachyMD;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;



public class CodingHierarchyWindow extends FenixWindow {
	
	private HorizontalPanel panel;
	private Button search;
	private ListBox selectionList;
	private Button addSelection;
	
	private CodeVo code;
	private Long codingSystemId;
	private HashMap<String, Integer> level;
	private Integer codeLevel;
	
	private CHReminderPanel reminderPanel;
	
	//table pager attributes
	private BasePagingLoader pagingLoader;
	
	public int pageSize= TableConstants.PAGE_SIZE; //equivalent to items per page
	private PagingToolBar toolBar = new PagingToolBar(pageSize);   

	private ListStore<CodingHiearachyMD> md;
	private Grid<CodingHiearachyMD> grid;
	private CheckBoxSelectionModel<CodingHiearachyMD> sm;
	
	
	public void build(Long codingsystemId, CodeVo codeVo, List<CodeVo> resultedCodes) {
		ContentPanel panel = new ContentPanel();
		code = codeVo;
		codingSystemId = codingsystemId;
		level = new HashMap<String, Integer>();
	
		panel.setStyleName("my-bg");
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(buildSearchPanel());
		vPanel.add(buildCenterPanel());
//		vPanel.add(buildAddSelectionPanel());
//		vPanel.add(buildButtonsPanel());
//		panel.setBodyBorder(false);
		panel.add(vPanel);


		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		
		panel.setHeaderVisible(false);
		enhance();
		format();
		show();
		panel.setScrollMode(Scroll.AUTO);
		CodingHierarchyController.initializeGrid(this, getCodingSystemId());
	}
	

		
	private void enhance() {
		search.addSelectionListener(CodingHierarchyController.getNewCodes(this, codingSystemId, selectionList));
		addSelection.addSelectionListener(CodingHierarchyController.addUserSelection(grid, getReminderPanel().getTree()));
//		searchCode.addSelectionListener(DcmtCodingCreatorController.searchCodeButton(grid,  window));
//		saveToDb.addSelectionListener(DcmtCodingCreatorController.uploadModifiedCodes(getDcmtCodingCreatorMenu()));		
	}
	
	private HorizontalPanel buildCenterPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(buildGridPanel());
		reminderPanel = new CHReminderPanel();
		panel.add(reminderPanel.build());
		return panel;
	}
	

	private HorizontalPanel buildSearchPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		HTML title = new HTML("<b>" +  BabelFish.print().code() + ": </b>");
		HTML label = new HTML(getCode().getLabel());
		selectionList = new ListBox();
		getSelectionList(selectionList);
		title.setWidth("35px");
		label.setWidth("90px");
		selectionList.setWidth("200px");
		search = new Button("Search");
		panel.setWidth("450px");
		panel.add(title);
		panel.add(label);
		panel.add(selectionList);
		panel.add(search);
		return panel;
	}
	
	
	private VerticalPanel buildAddSelectionPanel() {
		ContentPanel panel = new ContentPanel();
		VerticalPanel vPanel = new VerticalPanel();
		panel.setPosition(10, 5);
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(15);
		addSelection = new Button("Add Selection");
		hPanel.add(addSelection);
		vPanel.add(hPanel);
		panel.setHeaderVisible(false);	
		panel.setBorders(false);
		panel.setSize(300, 35);
		return vPanel;
	}
	
	
	
//	private VerticalPanel buildButtonsPanel() {
//		ContentPanel panel = new ContentPanel();
//		VerticalPanel vPanel = new VerticalPanel();
//		panel.setPosition(0, 5);
//		HorizontalPanel hPanel = new HorizontalPanel();
//		hPanel.setSpacing(5);
//		extract = new Button("extract");
//		clear = new Button(I18N.print().clear());
//		cancel = new Button(I18N.print().close());
//		hPanel.add(extract);
//		hPanel.add(clear);
//		hPanel.add(cancel);
//		vPanel.add(hPanel);
//		panel.setHeaderVisible(false);	
//		panel.setBorders(false);
//		panel.setSize(700, 35);
//		return vPanel;
//	}

	
	

	
	public void getSelectionList(ListBox listBox) {
//		listBox.addItem("----");
//		DcmtCodingCreatorController.findAllCodingSystems(listBox);
		CodingHierarchyController.getCsDescriptions(this, getCodingSystemId(), listBox);
	}
	

	
	
	public VerticalPanel buildGridPanel(){
		ContentPanel panel = new ContentPanel();
		VerticalPanel vPanel = new VerticalPanel();
		panel.setPosition(5, 5);
		  
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		
		//Get dataset rows
		final RpcProxy proxy = new RpcProxy() {   
			@Override  
			public void load(Object loadConfig, AsyncCallback callback) {   
//				datasetGrid.getStore().removeAll();
//				TableServiceEntry.getInstance().getFilteredData((PagingLoadConfig) loadConfig, resourceId, columnNames, filterCriteria, datasetGrid.caller, callback);
			}
			
		};   

		// set the proxy for the BasePagingLoader (i.e. table pager)
		final BasePagingLoader pagingLoader = new BasePagingLoader(proxy);   
		pagingLoader.setRemoteSort(true);   
		pagingLoader.load(0, TableConstants.PAGE_SIZE); 
		
		pagingLoader.addLoadListener(new LoadListener() {
		    public void loaderLoad(LoadEvent le) {
		    	final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
		    	loadingWindow.destroyLoadingBox();
		    }
		    
		    public void loaderLoadException(LoadEvent le) {
		    	final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
		    	loadingWindow.destroyLoadingBox();
		    }
		});

		
		
		//set the store
		final ListStore<DatasetRowModel> store = new ListStore<DatasetRowModel>(pagingLoader);
		
		 
		sm = new CheckBoxSelectionModel<CodingHiearachyMD>();  
		
		configs.add(sm.getColumn());
		
		md = new ListStore<CodingHiearachyMD>();	    
		 
		
		ColumnConfig column = new ColumnConfig();  
		column.setId("code");  
		column.setHeader(BabelFish.print().code());  
		column.setWidth(100);  	   
		configs.add(column);
		 
		
		column = new ColumnConfig();  
		column.setId("label");  
		column.setHeader(BabelFish.print().label());  
		column.setWidth(300);
		configs.add(column);
			
	    ColumnModel cm = new ColumnModel(configs);  
		
	    
	    
		grid = new Grid<CodingHiearachyMD>(md ,cm);  
		sm.setSelectionMode(SelectionMode.MULTI);

		grid.setSelectionModel(sm);
		grid.setSize(450, 350);	
		
		grid.setBorders(true);  
		grid.addPlugin(sm);
		 
		panel.add(grid);
		panel.setHeaderVisible(false);
		panel.setSize(450, 350);
		panel.setLayout(new FitLayout());
		
		grid.setStyleAttribute("borderTop", "none");	
		grid.setTrackMouseOver(true);
		
		
		   

		     
		vPanel.add(panel);
		vPanel.add(toolBar);
		vPanel.add(buildAddSelectionPanel());

		return vPanel;
	}
	

	
	

	
	
	protected void format() {
		setSize(800, 550);	
		window.setHeading("<b>  </b>");
//		getCenter().setHeaderVisible(false);
	}

	
	
	public ListBox getSelectionList() {
		return selectionList;
	}

	public void setSelectionList(ListBox selectionList) {
		this.selectionList = selectionList;
	}

	public CodeVo getCode() {
		return code;
	}

	public void setCode(CodeVo code) {
		this.code = code;
	}

	public Long getCodingSystemId() {
		return codingSystemId;
	}

	public void setCodingSystemId(Long codingSystemId) {
		this.codingSystemId = codingSystemId;
	}

	public Grid<CodingHiearachyMD> getGrid() {
		return grid;
	}

	public void setGrid(Grid<CodingHiearachyMD> grid) {
		this.grid = grid;
	}

	public void setPanel(HorizontalPanel panel) {
		this.panel = panel;
	}

	public void setMd(ListStore<CodingHiearachyMD> md) {
		this.md = md;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public Button getSearch() {
		return search;
	}

	public ListStore<CodingHiearachyMD> getMd() {
		return md;
	}



	public HashMap<String, Integer> getLevel() {
		return level;
	}



	public void setLevel(HashMap<String, Integer> level) {
		this.level = level;
	}



	public Integer getCodeLevel() {
		return codeLevel;
	}



	public void setCodeLevel(Integer codeLevel) {
		this.codeLevel = codeLevel;
	}



	public CheckBoxSelectionModel<CodingHiearachyMD> getSm() {
		return sm;
	}



	public void setSm(CheckBoxSelectionModel<CodingHiearachyMD> sm) {
		this.sm = sm;
	}



	public CHReminderPanel getReminderPanel() {
		return reminderPanel;
	}



	public void setReminderPanel(CHReminderPanel reminderPanel) {
		this.reminderPanel = reminderPanel;
	}
	
	
	

}