package org.fao.fenix.web.modules.udtable.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.udtable.client.view.UDTableToolbar;
import org.fao.fenix.web.modules.udtable.client.view.UDTableWindow;
import org.fao.fenix.web.modules.udtable.client.view.model.DatasetRowModel;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;

public class UDTableToolbarController {
    
	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final UDTableWindow tableWindow, final Long resourceId, final String typeExport) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {

				final Map<String, List<String>> filterCriteria = tableWindow.getUDTableFilter().getUDTableController().getFilterCriteria();

			/*	UDTableServiceEntry.getInstance().getColumnConfig(resourceId, new AsyncCallback<Map<String, String>>() {

					public void onSuccess(Map<String, String> result) {

						final List<String> columnNames = new ArrayList<String>();

						//Add the row ID as the first column
						columnNames.add("ID");

						//set the column header and types
						Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();

						for (int i = 0; i < result.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();
							columnNames.add((String)entry.getKey());
						}

						final LoadingWindow loadingWindow = new LoadingWindow(I18N.print().exportingTable(), I18N.print().pleaseWait(), I18N.print().exporting());
						loadingWindow.showLoadingBox();

						UDTableServiceEntry.getInstance().getFilteredRecords(resourceId, columnNames, filterCriteria, new AsyncCallback<List<List<String>>>() {
							public void onSuccess(List<List<String>> result) {

								result.add(0, columnNames);
								
								BirtServiceEntry.getInstance().exportTable(result, typeExport, new AsyncCallback<String>() {
									public void onSuccess(String result) {
										loadingWindow.destroyLoadingBox();
										Window.open(result, "_blank", "status=no");							
									}
									public void onFailure(Throwable caught) {
										loadingWindow.destroyLoadingBox();
										FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@exportTable");
									}
								});
							}
							public void onFailure(Throwable caught) {
								loadingWindow.destroyLoadingBox();
								FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@ getFilteredRecords");
							}
						});


					}


					public void onFailure(Throwable caught) {

						FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@ getColumnConfig");
					}
				});*/


			}
		};
	}

	public static SelectionListener<IconButtonEvent> switchView(final Long resourceId, final UDTableWindow tableWindow, final UDTableToolbar tableToolbar, final String action ) {
		return new SelectionListener<IconButtonEvent>() {
			
			public void componentSelected(IconButtonEvent event) {
			
				final EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				
				if(action.equals(FenixToolbarConstants.EDIT)) {
					System.out.println("EDIT IS PRESSED");
					
					tableToolbar.getToolbar().removeAll();
					tableToolbar.appendEditIcons(tableToolbar.getToolbar(), tableWindow, resourceId);
					
					
				/*	UDTableServiceEntry.getInstance().getColumnConfig(resourceId, new AsyncCallback<Map<String, String>>() {
		    			
			    		public void onSuccess(Map<String, String> result) {
			    			
			    			//clear configs	
			    			if(tableWindow.getUDTableGrid().getEditableConfigs()!=null)
			    				tableWindow.getUDTableGrid().getEditableConfigs().clear();
			    			 
			    			 tableWindow.getUDTableGrid().setEditableConfigs();
			    			 
			    			 final List<String> columnNames = new ArrayList<String>();

			    			//Add the row ID as the first column
				    		columnNames.add("ID");
				    		tableWindow.getUDTableGrid().getEditableConfigs().add(tableWindow.getUDTableGrid().getRowIdColumnConfiguration());
			    				
			    			
			    			 //set the column header and types
			    			Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();

			    			for (int i = 0; i < result.size(); i++) {
			    				Map.Entry<String, String> entry = iterator.next();

			    				String columnName = (String)entry.getKey();
			    				columnNames.add(columnName);
			    				
			    				String columnDataType = (String)entry.getValue();

			    				//column header
			    				ColumnConfig columnConfig = new ColumnConfig();  
			    				columnConfig.setId("column"+(i+1));  // i+1 because column0 = row id 
			    				columnConfig.setHeader(columnName);  
			    				
			    				
			    				System.out.println("column name length = "+columnName.length());
			    				columnConfig.setWidth(100);  
			    				
			    					
			    				//editable configuration
			    				tableWindow.getUDTableGrid().getEditableConfigs().add(tableWindow.getUDTableGrid().getColumnConfiguration(columnDataType, columnConfig, (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO), tableWindow.getDatasetGrid(), resourceId, columnName)); 
				    			
			    			}
			    			
			    			grid.reconfigure(grid.getStore(), new ColumnModel(tableWindow.getUDTableGrid().getEditableConfigs()));
			    			
			    			grid.el().unmask();
			    			grid.getView().setForceFit(true); 
			    			grid.getView().refresh(true);
							
							
			    	}
			    		
			  
					public void onFailure(Throwable caught) {
				   
		 			FenixAlert.alert("TableToolbarController:build RPC Failed", "TableToolbarController @ getColumnConfig");
		 		}
		 	});*/
		
					
					
				}
				else {
					System.out.println("VIEW IS PRESSED");
					
					tableToolbar.getToolbar().removeAll();					
					tableToolbar.appendViewIcons(tableToolbar.getToolbar(), tableWindow, resourceId);
					
					//if(tableWindow.getDatasetGrid().getReadOnlyConfigs()==null)
					//	 tableWindow.getDatasetGrid().setReadOnlyConfigs();
	    			
					grid.reconfigure(grid.getStore(), new ColumnModel(tableWindow.getUDTableGrid().getReadOnlyConfigs()));
					
					grid.el().unmask();
					grid.getView().setForceFit(true);
					grid.getView().refresh(true);
					
				}
			}
		};
	}
	

	public static SelectionListener<IconButtonEvent> validateModifiedRecords(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
					
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				
				ColumnModel cm = grid.getColumnModel();
				List<Record> modifiedRecords =  grid.getStore().getModifiedRecords();
				
				if(!validate(modifiedRecords, cm)){
					FenixAlert.error(BabelFish.print().missingFields(), BabelFish.print().pleaseFillRequiredFields());	
				} else  saveAllChanges(resourceId, tableWindow);
				
			}
		};
	}
	
	public static Boolean validate(List<Record> modifiedRecords, ColumnModel cm) {
		System.out.println(" -----  cm.getColumnCount() = "+cm.getColumnCount());
		Boolean validate = true;

		for (int j = 0; j < modifiedRecords.size();j++) {
			Record record =  modifiedRecords.get(j);
		
			for (int i = 0;i < cm.getColumnCount(); i++) {
				if(cm.getColumnById("column"+i)!=null){
					if((String)record.get("column"+i)==null){
						//System.out.println("-------- column "+i +"IS NULL");
						validate = false;
						break;
					}
				}
				
			}

		}

		return validate;
	}
	
	public static SelectionListener<IconButtonEvent> confirmDeleteListener(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {

			public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				
            	if(md.size() > 0) 
				  MessageBox.confirm(BabelFish.print().confirmDelete(), BabelFish.print().deleteWarning(), handleDeleteResponseListener(event, resourceId, tableWindow)); 
			    else FenixAlert.alert(BabelFish.print().alert(), BabelFish.print().pleaseSelectRowToDelete());
			}
		};
	}
	
	private static Listener<MessageBoxEvent> handleDeleteResponseListener(final ComponentEvent event, final Long resourceId, final UDTableWindow tableWindow) {
		return new Listener<MessageBoxEvent>() {

			 public void handleEvent(MessageBoxEvent event) {
//				        Dialog dialog = (Dialog) event.component;
				        Button btn = event.getButtonClicked();
				        Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
				        if(btn.getText().equals("Yes")) // FIXME i18n
				        	removeTableRow(event, resourceId, tableWindow);
		     }  
		};
	}
	
	private static void removeTableRow(final ComponentEvent event, final Long resourceId, final UDTableWindow tableWindow) {
			
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				
				List<String> deleteRowList = new ArrayList<String>();

				for (DatasetRowModel item: md){
						String rowId = item.get("column0");
						if(rowId!=null && rowId.length() > 0)
							deleteRowList.add(rowId);

						grid.getStore().remove(item);

						if (grid.getStore().getCount() == 0) {  
							event.getComponent().disable();
						}  
					}

					if(deleteRowList.size() > 0) { 

						/*UDTableServiceEntry.getInstance().deleteRow(deleteRowList, new AsyncCallback() {
							public void onSuccess(Object result) {
								FenixAlert.info(I18N.print().delete(), I18N.print().deleteSuccessful());
							}
							public void onFailure(Throwable caught) {
								Info.display("TableToolbarController: deleteRow", caught.getLocalizedMessage());
							}
						});*/

					}
				
				/*  grid.getStore().remove(grid.getStore().getAt(0));  
				       if (grid.getStore().getCount() == 0) {  
			        	 event.component.disable();  
			         }  */
		
		
	}
	
	public static SelectionListener<IconButtonEvent> copyTableRowListener(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			
			public void componentSelected(IconButtonEvent event) {
				
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				ColumnModel columnModel = tableWindow.getUDTableGrid().getEditorGrid().getColumnModel();
				
				columnModel.getColumnCount();
				
            	List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				
            	if(md.size() > 0) {
            		for (int i=0; i < md.size(); i++){		
            			DatasetRowModel item = md.get(i);
            			DatasetRowModel newRowModel = new DatasetRowModel();
            			newRowModel.set("column0", "");//set the ID column to empty

            			for (int j=1; j < columnModel.getColumnCount(); j++){
            				//System.out.println("---- item.get(column+j) = "+item.get("column"+j));
            				newRowModel.set("column"+j, item.get("column"+j));
            			}

            			grid.getStore().insert(newRowModel, 0);
            			grid.startEditing(0, 0);  

            			if (grid.getStore().getCount() == 0) {  
            				event.getComponent().disable();
            			}  
            		}
            	} else FenixAlert.alert(BabelFish.print().alert(), BabelFish.print().pleaseSelectRowToCopy());
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> insertTableRowListener(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			
         public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				DatasetRowModel newRowModel = new DatasetRowModel();
				newRowModel.set("column0", "");
				List<DatasetRowModel> md = new ArrayList<DatasetRowModel>();
				md.add(newRowModel);
				grid.stopEditing();  
				grid.getStore().insert(newRowModel, 0);	
				grid.getSelectionModel().setSelection(md); //set as the selected row
								
				grid.startEditing(0, 1);  
				
			}
		};
				
	}
	
	private static void saveAllChanges(final Long resourceId, final UDTableWindow tableWindow) {
					
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				
				ColumnModel cm = grid.getColumnModel();
				
				List<Record> modifiedRecords =  grid.getStore().getModifiedRecords();
				
				Map<String, Map<String, String>> modifiedRecordsMap =  new HashMap<String, Map<String, String>>();
				Map<Integer, Map<String, String>> newRecordsMap =  new HashMap<Integer, Map<String, String>>();
				Map<String, String> columnIdentifierMap =  new HashMap<String, String>();
				
				//for (Record record: modifiedRecords){
				for (int j = 0; j < modifiedRecords.size(); j++) {
					Record record = modifiedRecords.get(j);
					ModelData model = record.getModel();
					
					String contentId = (String)model.get("column0");
				
					Map<String, Object> changesmap = record.getChanges();
					Iterator<Map.Entry<String, Object>> iterator   = changesmap.entrySet().iterator();

					Map<String, String> modifiedPropertiesMap =  new HashMap<String, String>();
				    for (int i = 0; i < changesmap.size(); i++) {

						Map.Entry<String, Object> entry = iterator.next();
						String modifiedColumn =  entry.getKey();
						Object originalValue = entry.getValue();
						String newValue = (String)model.get(modifiedColumn);
						
					    String fieldName = cm.getColumnById(modifiedColumn).getId();
					
						System.out.println("--- changesmap property modified = "+  modifiedColumn + " original value = "+originalValue + " new value = "+newValue + " rowId = "+ model.get("column0") + " fieldName = " +  fieldName);
						
						modifiedPropertiesMap.put(modifiedColumn, newValue);
						columnIdentifierMap.put(modifiedColumn, cm.getColumnById(modifiedColumn).getHeader());
						
					}
					
				    
				    if(contentId!=null && contentId.length() > 0) {//overwrite 
				    	  System.out.println("-------------------- TABLETOOLBARCONTROLLER OVERWRITE contentId = "+ contentId);
						  
				    	modifiedRecordsMap.put(contentId, modifiedPropertiesMap);
				    	
				    /*	UDTableServiceEntry.getInstance().overwriteDatasetRowContent(resourceId, modifiedRecordsMap, columnIdentifierMap, new AsyncCallback() {
							public void onSuccess(Object result) {
								FenixAlert.info(I18N.print().save(), I18N.print().saveCompleted());
							}
							public void onFailure(Throwable caught) {
								Info.display("TableToolbarController: overwriteDatasetRowContent", caught.getLocalizedMessage());
							}
						});*/
				    	
				    }
				    else { //new records append
				    	 System.out.println("-------------------- TABLETOOLBARCONTROLLER APPEND !");
				    	newRecordsMap.put(j,modifiedPropertiesMap);
				    	
				     /*  	UDTableServiceEntry.getInstance().appendDatasetRowContent(resourceId, newRecordsMap, columnIdentifierMap, new AsyncCallback() {
							public void onSuccess(Object result) {
								FenixAlert.info(I18N.print().save(), I18N.print().saveCompleted());
							}
							public void onFailure(Throwable caught) {
								Info.display("TableToolbarController: overwriteDatasetRowContent", caught.getLocalizedMessage());
							}
						});*/
				    }
				    //else 
				    	

				}
				
				
				

				grid.getStore().commitChanges();  
		
	}
	
	public static SelectionListener<ComponentEvent> updateSelectedRows(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				for (DatasetRowModel item: md){
					//FenixAlert.alert("updateSelectedRows", "updateSelectedRows");
					grid.getStore().getRecord(item).commit(false); 
				}

			
			}
		};
	}

	public static SelectionListener<IconButtonEvent> resetAllChanges(final Long resourceId, final UDTableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getUDTableGrid().getEditorGrid();
				grid.getStore().rejectChanges();  
			}
		};
	}
	
}
