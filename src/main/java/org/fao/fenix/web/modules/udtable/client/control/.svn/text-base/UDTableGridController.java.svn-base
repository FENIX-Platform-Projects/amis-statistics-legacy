package org.fao.fenix.web.modules.udtable.client.control;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.util.Pager.FenixPager;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.udtable.client.view.UDTableGrid;
import org.fao.fenix.web.modules.udtable.client.view.UDTablePager;
import org.fao.fenix.web.modules.udtable.client.view.model.DatasetRowModel;
import org.fao.fenix.web.modules.udtable.common.constants.UDTableConstants;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableHeaderVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableRowVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class UDTableGridController {
		

	 //PagedTable
	 public static void build(final UDTableGrid datasetGrid, final Long resourceId,  final List<UDTableFilterVO> filters, final int startIndex, final int maxResultSetSize, final UDTablePager pager) {
         
			final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
			loadingWindow.showLoadingBox();
		
 				datasetGrid.setReadOnlyConfigs(datasetGrid.getReadOnlyConfigs());
 					UDTableServiceEntry.getInstance().getTable(filters, CheckLanguage.getLanguage(), startIndex, maxResultSetSize, true, new AsyncCallback<UDTableVO>() {
						public void onSuccess(UDTableVO table) {
							System.out.println("PAGED TABLE result:");
							//printTable(table);
							
							
							pager.setFenixPager(new FenixPager(table.getDatasetRecordCount(), UDTableConstants.MAX_RESULT_SET_SIZE));
							pager.writeInfo();
							pager.validateButtons();
							
						
							final List<String> columnNames = new ArrayList<String>();
							
							//Add the row ID as the first column
			    			columnNames.add("ID");
			    			datasetGrid.getReadOnlyConfigs().add(datasetGrid.getRowIdColumnConfiguration());
			    			
							int k = 0;
							for (UDTableHeaderVO h : table.getHeaders()) {
								System.out.print(h.getHeader() + "\t|\t");
							
								String columnName = h.getHeader();

				    			columnNames.add(columnName);
							
			    				String columnDataType = h.getDataType();
			
			    				//column header
			    				ColumnConfig columnConfig = new ColumnConfig();  
			    				columnConfig.setId("column"+(k));  // i+1 because column0 = row id 
			    				columnConfig.setHeader(columnName);  
			    				
			    				
			    				System.out.println("column name length = "+columnName.length() + " | " + columnName);
			    				columnConfig.setWidth(100);

			    				//non-editable configuration
			    				datasetGrid.getReadOnlyConfigs().add(columnConfig);
			    				k++;
							}
							
							//clear the store
			    			datasetGrid.getStore().removeAll();	
	 
							
		    				ListStore<DatasetRowModel> store = datasetGrid.getStore();
		    				store.removeAll();
		    				List<DatasetRowModel> rows = new ArrayList<DatasetRowModel>();
		    			
		    				System.out.println(table.getRows().size() + " ROWS SIZEEEE");
		    				
		    				for (int i = 0 ; i < table.getRows().size() ; i++) {
		    					UDTableRowVO row = table.getRows().get(i);
		    					
		    					List<String> a = new ArrayList<String>();
		    					for (int j = 0 ; j < row.getCells().size() ; j++) {	
//		    						System.out.print(row.getCells().get(j).getCode() + " | ");
		    						System.out.print(row.getCells().get(j).getValue() + " | ");
		    						a.add(row.getCells().get(j).getValue());
		    					}
		    					rows.add(new DatasetRowModel(a));
		    					
		    				}
		    				
		    				store.add(rows);
		    				System.out.println(rows.size() + " | " + store.getCount() + " | ROWS SIZE");
		    				datasetGrid.setStore(store);
		    				
		    				 //bind loader to the pager
			    		   
			    		    
			    		   
			    		   //non-editable configuration
			    		   datasetGrid.setEditorGrid(datasetGrid.getStore(), new ColumnModel(datasetGrid.getReadOnlyConfigs()), resourceId);
			    		   
			    		  
			    			//add the populated grid to the centre panel and refresh table pager
			    			datasetGrid.getTableWindow().getCenter().add(datasetGrid.getEditorGrid());
			    			
			    			datasetGrid.getTableWindow().getCenter().setBottomComponent(datasetGrid.getPagerPanel());
			    			
			    	    	datasetGrid.getTableWindow().addCenterPartToWindow();
			    			
			    			
			    			//collapse/expand the West side, to refresh the panel
			    		   if(datasetGrid.getTableWindow().getWest().isExpanded()) {
			    		   	 datasetGrid.getTableWindow().getWest().collapse();
			               } else  datasetGrid.getTableWindow().getWest().expand();
			    		
			    	    	     
			    		    datasetGrid.caller = null;
		    				
							System.out.println("END");
							loadingWindow.destroyLoadingBox();
						}
						
						public void onFailure(Throwable caught) {
						    loadingWindow.destroyLoadingBox();
				 			FenixAlert.alert("TableGridController:build RPC Failed", "TableGridController @ getValues");
				 		}
				});

	 }
			
			
			
			
			
	 
	 
	
	 	private static void printTable(UDTableVO table) {
			
		 		for (int i = 0 ; i < table.getRows().size() ; i++) {
				UDTableRowVO row = table.getRows().get(i);
				for (int j = 0 ; j < row.getCells().size() ; j++)
//					System.out.print(row.getCells().get(j).getCode() + " | ");
					System.out.print(row.getCells().get(j).getValue() + " | ");
				System.out.println();
			}
		}
	 	
//	 	private void print(UDTable table) {
//			for (UDTableHeader h : table.getHeaders())
//				System.out.print(h.getHeader() + "\t|\t");
//			System.out.println();
//			for (int i = 0 ; i < table.getRows().size() ; i++) {
//				UDTableRow row = table.getRows().get(i);
//				for (int j = 0 ; j < row.getCells().size() ; j++)
//					System.out.print(row.getCells().get(j).getValue() + "\t|\t");
//				System.out.println();
//			}
//		}
}

