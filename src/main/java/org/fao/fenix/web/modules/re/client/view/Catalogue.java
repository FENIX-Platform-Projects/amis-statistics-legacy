package org.fao.fenix.web.modules.re.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;


/**
 * The Catalogue class is the table of results, after performing a search for FENIX resource(s). 
 * 
 **/


public abstract class Catalogue {
	
	protected Grid<ResourceChildModel> grid;
	
	protected String[] columnHeaderIds;
	protected ResourceExplorer resourceExplorer;
	
	protected PagingLoader<PagingLoadResult<ResourceChildModel>> loader;
	
	protected ListStore<ResourceChildModel> store = new ListStore<ResourceChildModel>();
	
	protected RpcProxy<PagingLoadResult<ResourceChildModel>> proxy;
	
	protected List<ColumnConfig> configs = new ArrayList<ColumnConfig>(); 
	
	
	protected ColumnModel standardColumnModel; 
	
	protected ColumnModel columnModel; 
	
	private GridSelectionModel<ResourceChildModel> sm = new GridSelectionModel<ResourceChildModel>();
	
	
	public void setColumnModel(ColumnModel columnModel) {
		this.columnModel = columnModel;
	}
	public ColumnModel getColumnModel() {
		return columnModel;
	}
	
	public Catalogue(ResourceExplorer resourceExplorer){
		  this.resourceExplorer=resourceExplorer;	
	}
	
	public ListStore<ResourceChildModel> getStore() {
		return store;
	}

	public void setStore(ListStore<ResourceChildModel> store) {
		this.store = store;
	}

	public Grid<ResourceChildModel> getGrid() {
		return grid;
	}

	
	
	public PagingLoader<PagingLoadResult<ResourceChildModel>> getLoader() {
		return loader;
	}

	public void setLoader(PagingLoader<PagingLoadResult<ResourceChildModel>> loader) {
		this.loader = loader;
	}

	public RpcProxy<PagingLoadResult<ResourceChildModel>> getProxy() {
		return proxy;
	}

	public void setProxy(RpcProxy<PagingLoadResult<ResourceChildModel>> proxy) {
		this.proxy = proxy;
	}
	 
	public List<ColumnConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<ColumnConfig> configs) {		
		this.configs = configs;
	}

	

	public void setColumnModel(List<ColumnConfig> columns) {
		//List<ColumnConfig> columns = getStandardColumnConfigurations();
		this.columnModel = new ColumnModel(columns);  	
	}
	
	
	public ColumnModel getStandardColumnModel() {
		return standardColumnModel;
	}

	public void setStandardColumnModel(List<ColumnConfig> columns) {
		//List<ColumnConfig> columns = getStandardColumnConfigurations();
		this.standardColumnModel = new ColumnModel(columns);  	
	}
	
	/*public void setGridProperties(final PagingLoader<PagingLoadResult<ResourceChildModel>> loader){
		grid.setTrackMouseOver(false);  
		grid.addListener(Events.Attach, new Listener<GridEvent<ResourceChildModel>>() {  
			public void handleEvent(GridEvent<ResourceChildModel> be) {  
				loader.load(0, 500);  
			}  
		});  
		grid.setTrackMouseOver(false);  
		grid.setLoadMask(true);  
		grid.setBorders(true);  
		grid.setAutoExpandColumn("name");  

		BufferView view = new BufferView();  
		view.setScrollDelay(0);  
		view.setRowHeight(34);  

		grid.setView(view);  
		   
	}*/
	
	public void setGridProperties(){
		grid.setTrackMouseOver(false);  
		grid.setLoadMask(true);  
		grid.setSelectionModel(sm);
		grid.setBorders(true);  
		
		BufferView view = new BufferView();  
		view.setScrollDelay(0);  
		view.setRowHeight(34);  

		grid.setView(view);  		   
	}
	
	public void setGrid(final ListStore<ResourceChildModel> store, ColumnModel colModel){
		grid = new Grid<ResourceChildModel>(store , colModel); 
		grid.setTrackMouseOver(false);  
		grid.setLoadMask(true);  
		grid.setSelectionModel(sm);
		grid.setBorders(true);  
		
		BufferView view = new BufferView();  
		view.setScrollDelay(0);  
		view.setRowHeight(34);  

		grid.setView(view);  		   
	}
	
	
	    
	
    public ColumnConfig buildColumnConfiguration(TableColumn tableColumn, String columnHeaderLabel){

    	ColumnConfig column = new ColumnConfig();  
    	column.setId(tableColumn.name);
    	column.setHeader(columnHeaderLabel);
    	column.setWidth(tableColumn.defaultWidth);
    	
    	if(tableColumn.name.equalsIgnoreCase("name")){
    	 column.setRenderer(new GridCellRenderer<ResourceChildModel>() {  
    		   
    		       public Object render(ResourceChildModel model, String property, ColumnData config,  
    		          int rowIndex, int colIndex, ListStore<ResourceChildModel> store, Grid<ResourceChildModel> grid) {  
    		        return "<img src='"+ResourceType.resourceIconMapPath.get(model.getType())+"'/><font color='#385F95'><b> "
    		            + model.get("name") + " </b></font>";  
    		       }  
    		   
        }); 
    	} else if (tableColumn.name.equalsIgnoreCase("dateModified")){
    		column.setDateTimeFormat(DateTimeFormat.getMediumDateTimeFormat());  
    	} 
    	
    	
		return column;
	}
    
   
	public static class TableColumn {
		public String name;
		int minWidth = 75;
		public int defaultWidth = 150;
		boolean sortable = true;
		boolean resizable = true;

		public TableColumn(String name) {
			this.name = name;
		}

		public TableColumn(String name, int minWidth, int defaultWidth) {
			this.name = name;
			this.minWidth = minWidth;
			this.defaultWidth = defaultWidth;
		}

		public TableColumn(String name, int minWidth, int defaultWidth, boolean sortable, boolean resizable) {
			this.name = name;
			this.minWidth = minWidth;
			this.defaultWidth = defaultWidth;
			this.sortable = sortable;
			this.resizable = resizable;
		}
	}

	protected final static TableColumn DATASETTABLECOLUMNS[] = new TableColumn[] {
		new TableColumn("name", 75, 240),
		new TableColumn("source"),
		//new TableColumn("periodTypeCode"),
		new TableColumn("region"),
		new TableColumn("dateModified")
	};
	
	public final static TableColumn STANDARDTABLECOLUMNS[] = new TableColumn[] {
		new TableColumn("name", 75, 240),
		new TableColumn("source"),
		//new TableColumn("periodTypeCode"),
		new TableColumn("region"),
		new TableColumn("dateModified")
	};


    
    public List<ColumnConfig> getStandardColumnConfigurations(){
	  	
    	List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		for (TableColumn tableColumn : STANDARDTABLECOLUMNS) {
    		columns.add(buildColumnConfiguration(tableColumn, BabelFish.print().getString(tableColumn.name)));
		}
    	    	
    	return columns;
    }
    
 
    
    
    public String getIconPath(ResourceChildModel model) {   

    	String type = model.getType(); 

    	System.out.println("catalogue.getIconPath: type = "+ type);
    	
    	if (!(model instanceof ResourceParentModel)) {   
    		System.out.println("model is not instance ResourceParentModel");
    		// Use image paths rather than style names to set the icon
    		String iconPath = ResourceType.resourceIconMapPath.get(type);
    		GWT.log("ICON PATH: " + iconPath, new Throwable());
    		if(iconPath==null)
    			iconPath = ResourceType.resourceIconMapPath.get(ResourceType.DEFAULT); 
            
    		return iconPath;
    		
    	} else {
    		GWT.log("Model is instanceof ResourceParentModel", new Throwable());
    	}
    	return null;   
    } 

  
	 
}
