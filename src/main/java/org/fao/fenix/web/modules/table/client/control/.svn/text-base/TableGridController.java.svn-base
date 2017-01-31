package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchWindow;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableGrid;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;

public class TableGridController {
	
   static String datasetPrecision = "2";
	
	public static void build(String precision, final TableGrid tableGrid, final Long resourceId,  final Map<String, List<String>> filterCriteria, final TableVO tableVO) {
		datasetPrecision = precision;	
		build(tableGrid, resourceId, filterCriteria, tableVO) ;
	}
	
	//Build Non-Editable Grid
	public static void build(final TableGrid tableGrid, final Long resourceId,  final Map<String, List<String>> filterCriteria, final TableVO tableVO) {
		 
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();

		Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
		Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();

		//clear the store
		tableGrid.getStore().removeAll();

		final List<String> columnNames = new ArrayList<String>();

		//Add the row ID as the first column
		columnNames.add("ID");
		tableGrid.getReadOnlyConfigs().add(tableGrid.getRowIdColumnConfiguration());
			
         
	    final NumberFormat currency = NumberFormat.getCurrencyFormat();
		//final NumberFormat decimalFormnat = NumberFormat.getFormat("0,000");
		final NumberCellRenderer<Grid<DatasetRowModel>> numberRenderer = new NumberCellRenderer<Grid<DatasetRowModel>>(currency);
		
		    GridCellRenderer<DatasetRowModel> gridNumber = new GridCellRenderer<DatasetRowModel>() {  
		       public String render(DatasetRowModel model, String property, ColumnData config, int rowIndex, int colIndex,  
			          ListStore<DatasetRowModel> store, Grid<DatasetRowModel> grid) {  
		    	     if(model.get(property) == null) return null;
		            NumberFormat nf = NumberFormat.getFormat("0,000");
		            try {
		            	  System.out.println("--------- format: "+nf.format(((Number)model.get(property)).doubleValue()));
		                return nf.format(((Number)model.get(property)).doubleValue());
		            } catch (Exception e) {
		            	  System.out.println("--------- no format "+model.get(property).toString());
		                return model.get(property).toString();
		            }
		            //  System.out.println("--------- "+model.get(property) + "property: "+property+" renderer: "+numberRenderer.render(null, property, model.get(property).toString()));
			       
		             // return numberRenderer.render(null, property, model.get(property).toString());  
		      }  
		     };  
			   
			
		
		     /*GridCellRenderer<DatasetRowModel> gridNumber = new GridCellRenderer<DatasetRowModel>() {
		      public String render(DatasetRowModel model, String property, ColumnData config, int rowIndex,
		          int colIndex, ListStore<DatasetRowModel> store, Grid<DatasetRowModel> grid) {
		    	  double val = decimalFormnat.parse(model.get(property).toString());
		    	  System.out.println("--------- "+val + "renderer: "+numberRenderer.render(null, property, val));
			    	
		        String style = val < 0 ? "red" : "green";
		         return numberRenderer.render(null, property,val);
		      }
		    };
		    
		final GridCellRenderer<DatasetRowModel> gridNumber = new GridCellRenderer<DatasetRowModel>() {
		      public String render(DatasetRowModel model, String property, ColumnData config, int rowIndex,
		          int colIndex, ListStore<DatasetRowModel> store, Grid<DatasetRowModel> grid) {
		    	  System.out.println("--------- "+model.get(property) + "renderer: "+numberRenderer.render(null, property, model.get(property)));
		    	  
		    	  return numberRenderer.render(null, property, model.get(property));
		      }
		    };*/
		    
		//set the read only column config
		for (int i = 0; i < dimensionDetailsMap.size(); i++) {
			Map.Entry<String, DimensionBeanVO> entry = iterator.next();

			String columnId = entry.getKey();
			DimensionBeanVO dimensionVO = entry.getValue();

			String columnName = dimensionVO.getHeader();
			columnNames.add(columnName);

			//column configuration
			ColumnConfig columnConfig = new ColumnConfig();
			columnConfig.setId(columnId);  
			columnConfig.setHeader(columnName);
			columnConfig.setWidth(100);
				
			if(dimensionVO.getColumnDataType().equals("quantity")) {
				//columnConfig.setNumberFormat(NumberFormat.getCurrencyFormat()); 
				columnConfig.setRenderer(gridNumber);
				columnConfig.setAlignment(HorizontalAlignment.RIGHT);
				//columnConfig.setRenderer(gridNumber);
			}	

			tableGrid.getReadOnlyConfigs().add(columnConfig);
		}

		tableGrid.setReadOnlyConfigs(tableGrid.getReadOnlyConfigs());
	
		//Get dataset rows
		final RpcProxy proxy = new RpcProxy() {
			@Override
			public void load(Object loadConfig, AsyncCallback callback) {
				tableGrid.getStore().removeAll();
				TableServiceEntry.getInstance().getFilteredData(datasetPrecision, (PagingLoadConfig) loadConfig, resourceId, columnNames, filterCriteria, tableGrid.caller, CheckLanguage.getLanguage(), callback);
			}

		};

		// set the proxy for the BasePagingLoader (i.e. table pager)
		final BasePagingLoader pagingLoader = new BasePagingLoader(proxy);
		pagingLoader.setRemoteSort(true);
		pagingLoader.load(0, TableConstants.PAGE_SIZE);

		pagingLoader.addLoadListener(new LoadListener() {
			public void loaderLoad(LoadEvent le) {
				loadingWindow.destroyLoadingBox();
			}

			public void loaderLoadException(LoadEvent le) {
				loadingWindow.destroyLoadingBox();
			}
		});
			
		//set the store
		final ListStore<DatasetRowModel> store = new ListStore<DatasetRowModel>(pagingLoader);		
		tableGrid.setStore(store);

		//bind loader to the pager
		tableGrid.getTableWindow().getTablePager().bind(pagingLoader);

		//set read only configuration
		tableGrid.setEditorGrid(tableGrid.getStore(), new ColumnModel(tableGrid.getReadOnlyConfigs()), resourceId);
		
		//add the populated grid to the centre panel and refresh table pager
		tableGrid.getTableWindow().getCenter().add(tableGrid.getEditorGrid());
		
		//	datasetGrid.getTableWindow().getTablePager().refresh();
		if (tableGrid.caller!=null){
			//datasetGrid.getTableWindow().getTablePager().first(); // move to the first page
			tableGrid.getTableWindow().getTablePager().recalculate(); // recalculate
			
		}
		
		tableGrid.getTableWindow().addCenterPartToWindow();
		
		//collapse/expand the West side, to refresh the panel
		//if(tableGrid.getTableWindow().getWest().isExpanded()) {
			//tableGrid.getTableWindow().getWest().collapse();
		//} else
			//tableGrid.getTableWindow().getWest().expand();
		
		tableGrid.getTableWindow().getWest().collapse();
		tableGrid.getTableWindow().getWest().expand();
		
		if(tableGrid.getTableWindow().getTableFilters()==null)
			System.out.println("null!!!");
			
		tableGrid.getTableWindow().getWindow().getLayout().layout();
		
		tableGrid.caller = null;
		
		
		if (tableGrid.getTableWindow().getTableFilters().addToReportButton != null)
			tableGrid.getTableWindow().getTableFilters().addToReportButton.enable();
	}

	
	public static SelectionListener<MenuEvent> openDCMT(final ListStore<DimensionItemModel> listStore, final ComboBox<DimensionItemModel> combo,  final CodingSystemVo codingSystemVo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				CodingSearchWindow window = new CodingSearchWindow();
				window.build(listStore, combo, codingSystemVo);
				//GWT.log("getColumnConfiguration:  openDCMT listStore = "+listStore, null);
				//GWT.log("getColumnConfiguration:  openDCMT combo = "+combo, null);	
			////	DimensionItemModel model = new DimensionItemModel("TestLabel", "TestCode");	  
			//	listStore.add(model);
//				combo.setValue(model); //set as selected item	
			//	for (int i = 0 ; i < combo.getStore().getCount() ; i++)
			//		System.out.println("!!!!!!!!!!!!!!! " + combo.getStore().getAt(i).getName());
			//	combo.select(combo.getStore().getCount() - 1);
			}
		};
	}
}

