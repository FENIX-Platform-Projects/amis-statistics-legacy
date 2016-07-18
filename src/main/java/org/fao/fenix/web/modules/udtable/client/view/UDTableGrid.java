package org.fao.fenix.web.modules.udtable.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.udtable.client.control.UDTableGridController;
import org.fao.fenix.web.modules.udtable.client.view.model.DatasetRowModel;
import org.fao.fenix.web.modules.udtable.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.udtable.common.constants.UDTableConstants;
import org.fao.fenix.web.modules.udtable.common.vo.DimensionItemModel;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.google.gwt.i18n.client.NumberFormat;

public class UDTableGrid {

	private List<ColumnConfig> editableConfigs;
	private List<ColumnConfig> readOnlyConfigs;
	private ListStore<DatasetRowModel> store;
	private EditorGrid<DatasetRowModel> grid;
	private ColumnModel colModel;  
	
	private static final NumberFormat numberFormat = NumberFormat.getFormat("0.0");

	private UDTableWindow tableWindow;
	
	//table pager attributes
	//private BasePagingLoader pagingLoader;
	private UDTablePager tablePager;
	
	private UDTableFilter tableFilter;
	
	public UDTableFilter getTableFilter() {
		return tableFilter;
	}

	public void setTableFilter(UDTableFilter tableFilter) {
		this.tableFilter = tableFilter;
	}

	public UDTablePager getTablePager() {
		return tablePager;
	}

	public void setTablePager(UDTablePager tablePager) {
		this.tablePager = tablePager;
	}

	//plugins
	private RowNumberer rowNumberer = new RowNumberer();  
	private CheckBoxSelectionModel<DatasetRowModel> checkBox = new CheckBoxSelectionModel<DatasetRowModel>();  
	private RowExpander rowExpander = new RowExpander();  
	private GridCellRenderer<DatasetRowModel> gridNumber;  
	
	private VerticalPanel pagerPanel;
	
	//page caller
	public String caller = null;
	
	public UDTableGrid(UDTableWindow window, UDTableFilter tableFilter) {
		this.store = new ListStore<DatasetRowModel>();
		tablePager = new UDTablePager();
		pagerPanel = new VerticalPanel();
		
		setReadOnlyConfigs();	
		
		this.tableWindow = window;		
		this.tableFilter = tableFilter;
		
	}
		
//	public void build(final ListUDTableVO tableVO, final Long resourceId, final Map<String, List<String>> filterCriteria, final String pageCaller) {
//		caller = pageCaller;
//		UDTableGridController.build(this, resourceId, filterCriteria, tableVO);
//	}
	
	public VerticalPanel getPagerPanel() {
		return pagerPanel;
	}

	public void setPagerPanel(VerticalPanel pagerPanel) {
		this.pagerPanel = pagerPanel;
	}

	/*public void build(final List<UDTableFilterVO> filters, final Long resourceId, final String pageCaller) {
		caller = pageCaller;
		pagerPanel.add(tablePager.build(this, tableFilter));
	    UDTableGridController.build(this, resourceId, filters);
	}*/
       
	public void build(final List<UDTableFilterVO> filters, final Long resourceId, final String pageCaller, final int startIndex, final int endIndex) {
		caller = pageCaller;
		pagerPanel.add(tablePager.build(this, tableFilter));
		UDTableGridController.build(this, resourceId, filters, startIndex, UDTableConstants.MAX_RESULT_SET_SIZE, tablePager);
	}
	
	public EditorGrid<DatasetRowModel> getEditorGrid() {
		return grid;
	}

	public void setEditorGrid(final ListStore<DatasetRowModel> store, ColumnModel colModel, Long resourceId) {
		this.grid = new EditorGrid<DatasetRowModel>(store , colModel); 
		this.grid.setId("grid"+resourceId);
		//properties
		this.grid.setLoadMask(true);   
		this.grid.setBorders(true);
		this.grid.setSelectionModel(checkBox);  
		this.grid.getView().setForceFit(true); 
		//this.grid.addListener(Events.CellClick, listener)
		//plugins
		this.grid.addPlugin(rowExpander);  
		this.grid.addPlugin(rowNumberer);  
		this.grid.addPlugin(checkBox);
	}
	
	public List<ColumnConfig> setEditableConfigs() {
		this.editableConfigs = new ArrayList<ColumnConfig>();
		this.editableConfigs.add(rowNumberer);
		this.editableConfigs.add(checkBox.getColumn());
		return editableConfigs;
	}
	
	public List<ColumnConfig> setReadOnlyConfigs() {
		this.readOnlyConfigs = new ArrayList<ColumnConfig>();
		this.readOnlyConfigs.add(rowNumberer);
		return readOnlyConfigs;
	}
	
	public List<ColumnConfig> getEditableConfigs() {
		return editableConfigs;
	}

	
	public List<ColumnConfig> getReadOnlyConfigs() {
		return readOnlyConfigs;
	}

	public void setReadOnlyConfigs(List<ColumnConfig> readOnlyConfigs) {
	   this.readOnlyConfigs = readOnlyConfigs;
	   
	}

	public ListStore<DatasetRowModel> getStore() {
		return store;
	}

	public void setStore(ListStore<DatasetRowModel> store) {
		this.store = store;
	}
	
	//public void setLoaderStore(BasePagingLoader loader) {
	//	this.store = new ListStore<DatasetRowModel>(loader);
	//}
	
	public ColumnModel getColumnModel() {
		return colModel;
	}

	public void setColumnModel(ColumnModel colModel) {
		this.colModel = colModel;
	}
	
	public UDTableWindow getTableWindow() {
		return tableWindow;
	}
	
	public void setUDTableWindow(UDTableWindow tableWindow) {
		this.tableWindow = tableWindow;
	}
			
	//public BasePagingLoader getProxyLoader() {
	//	return pagingLoader;
	//}

	public ColumnConfig getRowIdColumnConfiguration(){
	
		ColumnConfig columnConfig = new ColumnConfig();  
		columnConfig.setId("column0");  
		columnConfig.setHeader(UDTableConstants.ID);
		columnConfig.setHidden(true); //hide ID column
		
		return columnConfig;
	}
	
	public ColumnConfig getColumnConfiguration (final String columnDataType, ColumnConfig columnConfig, UDTableVO tableVO, UDTableGrid datasetGrid, Long resourceId, String columnName){

		// final String gaulRef = tableVO.getGaulReference();
		 final String periodType = tableVO.getPeriodTypeCode();

		//columnConfig.setId(columnDataType);
		 
       if(columnDataType.equals("date")) {
       	
			 DateField dateField = new DateField();
			 dateField.setAllowBlank(false);  
			 dateField.setAutoValidate(true); 
			 

			 CellEditor editor = new CellEditor(dateField) {  
				 @Override  
				 public Object preProcessValue(Object value) {  
					Date date = null;
					
					 if (value == null)
						 date  = new Date();
					 else
						 date = FieldParser.parseDate(value.toString(), periodType);
					 
					 
					 System.out.println("date = "+date );
					 
					 return date;  
				 }  

				 @Override  
				 public Object postProcessValue(Object value) {  
					 if (value == null) {  
						 return value;  
					 }  
					 return FieldParser.formatDate((Date)value, periodType);
				 }  
			 };  

			 columnConfig.setEditor(editor);  
		 } 
		 else if(columnDataType.equals("quantity")) {
			 NumberField number = new NumberField();
			 number.setAllowBlank(false);  
			 number.setAutoValidate(true);  
			

	         CellEditor editor = new CellEditor(number) {  
				 @Override  
				 public Object preProcessValue(Object value) {  
					 Double number = null;
					 if (value == null)
						 number = 0.00;
					 else
						 number = numberFormat.parse(value.toString());
						 
					 return number;  
				 }  

				 @Override  
				 public Object postProcessValue(Object value) {  
					 if (value == null) {  
						 return value;  
					 }  
					//return (Double)value;   
					return numberFormat.format((Double)value);
				 }  

			 };  

			// columnConfig.setRenderer(datasetGrid.getGridNumber());
			 //columnConfig.setEditor(new CellEditor(number));  
			columnConfig.setEditor(editor);  

		 } 
		 else {
			 final Map<String, List<DimensionItemModel>> dimensionCache = datasetGrid.getTableWindow().getUDTableFilter().getUDTableController().getDimensionCache();
			 final ComboBox<DimensionItemModel> combo = new ComboBox<DimensionItemModel>();  
			 combo.setTypeAhead(true);
			 combo.setTriggerAction(TriggerAction.ALL); 
			 combo.setAllowBlank(false);  
			 combo.setAutoValidate(true);  
			
			 
			 Iterator<Map.Entry<String, List<DimensionItemModel>>> iterator   = dimensionCache.entrySet().iterator();

			 for (int i = 0; i < dimensionCache.size(); i++) {

				 Map.Entry<String, List<DimensionItemModel>> entry = iterator.next();

				 System.out.println("--- DIMENSION CACHE key = "+  entry.getKey());
			 }

			 if(dimensionCache.containsKey(columnDataType)){
				 List<DimensionItemModel> values = dimensionCache.get(columnDataType);
				 System.out.println(" values = "+values.size());

				 ListStore<DimensionItemModel> dimensionItems = new ListStore<DimensionItemModel>();  
				 dimensionItems.add(values);  

				 combo.setStore(dimensionItems); 
				 combo.setDisplayField("name");  
			 } else {
				 System.out.println("--- columnDataType = "+  columnDataType);
				 
			/*	 UDTableServiceEntry.getInstance().getDimensionValues(resourceId, columnName, new AsyncCallback<Map<String, String>>() {

					 public void onSuccess(Map<String, String> result) {
						 Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();

						 List<DimensionItemModel> dimensionValues = new ArrayList<DimensionItemModel>();

						 for (int i = 0; i < result.size(); i++) {
							 Map.Entry<String, String> entry = iterator.next();
							 dimensionValues.add(new DimensionItemModel(entry.getValue(), entry.getKey()));
						 }
						 
						 dimensionCache.put(columnDataType, dimensionValues);
						 ListStore<DimensionItemModel> dimensionItems = new ListStore<DimensionItemModel>();  
						 dimensionItems.add(dimensionValues);  
						 
						 combo.setStore(dimensionItems); 
						 combo.setDisplayField("name");  
					 }
					 public void onFailure(Throwable caught) {
						 FenixAlert.alert("TableGridController:build RPC Failed", "TableGridController @ getColumnConfig");
					 }
				 });*/
			 }
			 

			 /*	 combo.addListener(Events.SelectionChange, new Listener(){
				 public void handleEvent(BaseEvent event) {
		 			 TreeRegionPanel tree = new TreeRegionPanel();
					 tree.build();

					 new RainfallWindow().build();

		 		    }
			 });*/

			 CellEditor editor = new CellEditor(combo) {  
				 @Override  
				 public Object preProcessValue(Object value) {  

					 if (value == null) {  
						 return value;  
					 }  
					 return combo.getStore().findModel("name", value.toString());    
				 }  

				 @Override  
				 public Object postProcessValue(Object value) {  
					 if (value == null) {  
						 return value;  
					 }  

					 return ((ModelData) value).get("name");  
				 }  
			 }; 
			
			 columnConfig.setEditor(editor);       

		 } 
		 // else {
		 //	 TextField<String> text = new TextField<String>();  
		 //	 text.setAllowBlank(false);  
		 //	 text.setAutoValidate(true);  
		 //	 columnConfig.setEditor(new CellEditor(text));
		 // }

		 return columnConfig;

	 }
	
	
	public GridCellRenderer<DatasetRowModel> getGridNumber() {
		return gridNumber;
	}

	public void setGridNumber(GridCellRenderer<DatasetRowModel> gridNumber) {
		this.gridNumber = gridNumber;
	}
	
}
