package org.fao.fenix.web.modules.table.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.client.control.TableGridController;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.i18n.client.NumberFormat;

public class TableGrid  {

	private List<ColumnConfig> editableConfigs;
	private List<ColumnConfig> readOnlyConfigs;
	private ListStore<DatasetRowModel> store;
	private EditorGrid<DatasetRowModel> grid;
	private ColumnModel colModel;  
	
	private static final NumberFormat numberFormat = NumberFormat.getFormat("#,##0.00");
	
	private TableWindow tableWindow;
	
	//table pager attributes
	private BasePagingLoader pagingLoader;
	
	//plugins
	private RowNumberer rowNumberer = new RowNumberer();  
	private GridSelectionModel<DatasetRowModel> checkBox = new GridSelectionModel<DatasetRowModel>();
	private RowExpander rowExpander = new RowExpander();  
	private GridCellRenderer<DatasetRowModel> gridNumber;  

	private static GridCellRenderer<DatasetRowModel> quantityRenderer;
	private static NumberFormat number;


	
	//page caller
	public String caller = null;
	
	public TableGrid(TableWindow window) {
		this.store = new ListStore<DatasetRowModel>();
		
		setReadOnlyConfigs();	
		
		this.tableWindow = window;		
	}
		
	public void build(final TableVO tableVO, final Long resourceId, final Map<String, List<String>> filterCriteria, final String pageCaller) {
		caller = pageCaller;
		TableGridController.build(this, resourceId, filterCriteria, tableVO);
	}
	
	public void build(final String precision, final TableVO tableVO, final Long resourceId, final Map<String, List<String>> filterCriteria, final String pageCaller) {
		caller = pageCaller;
		TableGridController.build(precision, this, resourceId, filterCriteria, tableVO);
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
			
//		this.grid.addPlugin(checkBox);
	}
		
	public List<ColumnConfig> setEditableConfigs() {
		this.editableConfigs = new ArrayList<ColumnConfig>();
		this.editableConfigs.add(rowNumberer);
//		this.editableConfigs.add(checkBox.getColumn());
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
	
	public void setLoaderStore(BasePagingLoader loader) {
		this.store = new ListStore<DatasetRowModel>(loader);
	}
	
	public ColumnModel getColumnModel() {
		return colModel;
	}

	public void setColumnModel(ColumnModel colModel) {
		this.colModel = colModel;
	}
	
	public TableWindow getTableWindow() {
		return tableWindow;
	}
	
	public void setTableWindow(TableWindow tableWindow) {
		this.tableWindow = tableWindow;
	}
			
	public BasePagingLoader getProxyLoader() {
		return pagingLoader;
	}

	public ColumnConfig getRowIdColumnConfiguration(){
	
		ColumnConfig columnConfig = new ColumnConfig();  
		columnConfig.setId("column0");  
		columnConfig.setHeader(TableConstants.ID);
		columnConfig.setHidden(true); //hide ID column
		
		return columnConfig;
	}
	
	public ColumnConfig getColumnConfiguration (final ColumnConfig columnConfig, final DimensionBeanVO dimension, final String datasetPeriodType){
		//GWT.log("getColumnConfiguration:  columnConfig.getId() = "+columnConfig.getId(), null);
		
		final ComboBox<DimensionItemModel> combo = new ComboBox<DimensionItemModel>();  
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTypeAhead(true);
		combo.setAllowBlank(false);  
		combo.setAutoValidate(true);  
		combo.setDisplayField("name");
		combo.setTitle(BabelFish.print().rightClickToAddNewItem());
		
		//GWT.log("getColumnConfiguration: column descriptor = "+dimension.getColumnDataType() + " dimension.hasCodingSystem() = "+dimension.hasCodingSystem(), null);

		
		if(dimension.hasCodingSystem()) {
			Map<String, String> values = dimension.getDistinctDimensionValues();
			CodingSystemVo codingSystemVo = dimension.getCodingSystemVO();
			
			fillComboBox(combo, columnConfig, values, codingSystemVo);
		}
		else {
			if(dimension.getColumnDataType().equals("date")) {

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
							date = FieldParser.parseDate(value.toString(), datasetPeriodType);


						System.out.println("date = "+date );

						return date;  
					}  

					@Override  
					public Object postProcessValue(Object value) {  
						if (value == null) {  
							return value;  
						}  
						return FieldParser.formatDate((Date)value, datasetPeriodType);
					}  
				};  

				columnConfig.setEditor(editor);  
			} else if(dimension.getColumnDataType().equals("quantity")) {
				NumberField number = new NumberField();
				number.setAllowBlank(true);  
				number.setAutoValidate(true);  


				CellEditor editor = new CellEditor(number) {  
					@Override  
					public Object preProcessValue(Object value) {  
						Double number = null;
						if (value == null || value.toString().equalsIgnoreCase("Not available"))
							number = null;						    
						else
							number = numberFormat.parse(value.toString());

						return number;  
					}  

					@Override  
					public Object postProcessValue(Object value) {  
						if (value == null) {  
							return "Not available";  
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
				TextField<String> text = new TextField<String>();
				text.setAllowBlank(false);  
				text.setAutoValidate(true);  
				columnConfig.setEditor(new CellEditor(text));
			} 
		}

		return columnConfig;

	}
	
  private void fillComboBox(final ComboBox<DimensionItemModel> combo, final ColumnConfig columnConfig, final Map<String, String> values, final CodingSystemVo codingSystemVo){
		
		Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();

		List<DimensionItemModel> dimensionValues = new ArrayList<DimensionItemModel>();

		for (int i = 0; i < values.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
			dimensionValues.add(new DimensionItemModel(entry.getValue(), entry.getKey()));
		}
		
		final ListStore<DimensionItemModel> store = new ListStore<DimensionItemModel>();  
		store.add(dimensionValues);  
		combo.setStore(store); 

		//set Context Menu to add new items, from the coding system
		Menu contextMenu = new Menu();   
		  
	    MenuItem addNewItem = new MenuItem();   
	    addNewItem.setText("["+BabelFish.print().addNewItem()+"]");   
//	    addNewItem.addSelectionListener(new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent evt) {
//				GWT.log("fillComboBox:   combo before = "+combo, null);
////				combo.getStore().insert(new DimensionItemModel("TestLabel", "TestCode"), 0); //set as selected item	
//				combo.setValue(new DimensionItemModel("TestLabel", "TestCode"));
//				for (int i = 0 ; i < combo.getStore().getCount() ; i++)
//					System.out.println("!!!!!!!!!!!!!!! " + combo.getStore().getAt(i).getName());
//				GWT.log("fillComboBox:   combo after = "+combo, null);		
//			}
//		});
	    addNewItem.addSelectionListener(TableGridController.openDCMT(store, combo, codingSystemVo));
	    contextMenu.add(addNewItem);   
	  
		combo.setContextMenu(contextMenu);
		
		CellEditor editor = setComboCellEditor(combo);
		columnConfig.setEditor(editor);     
		
	}
	
	public GridCellRenderer<DatasetRowModel> getGridNumber() {
		return gridNumber;
	}

	public void setGridNumber(GridCellRenderer<DatasetRowModel> gridNumber) {
		this.gridNumber = gridNumber;
	}
	
	private CellEditor setComboCellEditor(final ComboBox<DimensionItemModel> combo){
		return new CellEditor(combo) {  
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
	}
	
}
