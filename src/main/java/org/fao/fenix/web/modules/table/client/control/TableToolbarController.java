package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.view.MTDatasourceFieldSet;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.r.client.view.RFunctionWindow;
import org.fao.fenix.web.modules.r.common.services.RServiceEntry;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableGrid;
import org.fao.fenix.web.modules.table.client.view.TableToolbar;
import org.fao.fenix.web.modules.table.client.view.TableTypeExportWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class TableToolbarController {
	
	public static SelectionListener<IconButtonEvent> excelExport(final TableWindow tableWindow, final Long resourceId) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				TableTypeExportWindow window = new TableTypeExportWindow(tableWindow, resourceId);
			}
		};
	}
	
public static SelectionChangedListener<BaseModel> changeDecimalPoint(final TableWindow tableWindow, final SimpleComboBox precision, final TableVO tableVO, final Long resourceId, final Map<String, List<String>> filterCriteria, final String displayMode) {
	return new SelectionChangedListener<BaseModel>() {
		public void selectionChanged(SelectionChangedEvent<BaseModel> event) {
			
			//building the table grid
			Component gridComponent = tableWindow.getCenter().getItemByItemId("grid"+resourceId);
			tableWindow.tableGrid = new TableGrid(tableWindow);

			//remove grid component if already exists
			if(gridComponent!=null )
				tableWindow.getCenter().remove(gridComponent);

			//build the grid
			tableWindow.tableGrid.build(precision.getSimpleValue().toString(), tableVO, resourceId, filterCriteria, "LOAD_BUTTON");
			
			tableWindow.getCenter().layout();
			
			SimpleComboBox dp = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);		
			if (dp.getListeners(Events.SelectionChange).size() == 0);
			dp.addSelectionChangedListener(TableToolbarController.changeDecimalPoint(tableWindow, dp, tableVO, resourceId, filterCriteria, "LOAD_BUTTON"));
		
			// set the listener to the Save Table View
			IconButton saveTableViewButton = tableWindow.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE);
			if (saveTableViewButton!=null && saveTableViewButton.getListeners(Events.Select).size() == 0);
				//saveTableViewButton.addSelectionListener(TableSaver.saveTableView(tableVO, filterList, null, false));
		
			
		}
		};
}
	public static SelectionListener<ButtonEvent> exportToExcel(final TableTypeExportWindow window, final TableWindow tableWindow, final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				/** type "code", "label", "Code and Label" **/
				System.out.println(window.getCode().getValue());
				System.out.println(window.getLabel().getValue());
				System.out.println(window.getCodelabel().getValue());

				if ( window.getCode().getValue() || window.getLabel().getValue() || window.getCodelabel().getValue()) {
					String t = new String();
					if ( window.getCode().getValue() )
						t = "code";
					else if (window.getLabel().getValue())
						t = "label";
					else if (window.getCodelabel().getValue())
						t = "codelabel";

					final String type = t;
					System.out.println(type);

					window.getWindow().close();

					final Map<String, List<String>> filterCriteria = tableWindow.getTableFilters().getTableController().getFilterCriteria();

					TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
					Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
					Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();

					final List<String> columnNames = new ArrayList<String>();

					//set the column header
					for (int i = 0; i < dimensionDetailsMap.size(); i++) {
						Map.Entry<String, DimensionBeanVO> entry = iterator.next();
						columnNames.add(entry.getValue().getHeader());
					}

					final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportingTable(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
					loadingWindow.showLoadingBox();
					
					//decimal places
					final String precision = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION).getSimpleValue().toString();
					
					if(precision==null)
						System.out.println("precision = IS NULL");
					else 
						System.out.println("precision = "+precision);
					
					TableServiceEntry.getInstance().getFilteredRecordsToExport(precision, resourceId, columnNames, filterCriteria, type, CheckLanguage.getLanguage(), new AsyncCallback<List<List<String>>>() {
						public void onSuccess(List<List<String>> r) {

							TableServiceEntry.getInstance().createExcel(resourceId, type, tableWindow.getTableFilters().getPanel().getHeading(), r, precision, new AsyncCallback<String>() {
								public void onSuccess(String result) {
									Window.open("../olapExcel/" + result, "_blank", "status=no");
									loadingWindow.destroyLoadingBox();
								}
								public void onFailure(Throwable caught) {
									FenixAlert.alert("RPC Failed", "exportTable");
									loadingWindow.destroyLoadingBox();
								}
							});
						}
						public void onFailure(Throwable caught) {
							loadingWindow.destroyLoadingBox();
							FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@ getFilteredRecords");
						}
					});

				}

			}
		};
	}
	
    
	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final TableWindow tableWindow, final Long resourceId, final String typeExport) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final Map<String, List<String>> filterCriteria = tableWindow.getTableFilters().getTableController().getFilterCriteria();

				TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
				Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
				Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();

				final List<String> columnNames = new ArrayList<String>();

				//Add the row ID as the first column
				columnNames.add("ID");

				//set the column header
				for (int i = 0; i < dimensionDetailsMap.size(); i++) {
					Map.Entry<String, DimensionBeanVO> entry = iterator.next();
					columnNames.add(entry.getValue().getHeader());
				}

				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportingTable(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
				loadingWindow.showLoadingBox();

				//decimal places
				final String precision = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION).getSimpleValue().toString();
				
			
				TableServiceEntry.getInstance().getFilteredRecords(precision, resourceId, columnNames, filterCriteria, CheckLanguage.getLanguage(), new AsyncCallback<List<List<String>>>() {
					public void onSuccess(List<List<String>> result) {

						result.add(0, columnNames);
						System.out.println(result.size());

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

		};
	}

	public static SelectionListener<IconButtonEvent> switchView(final Long resourceId,
			final TableWindow tableWindow, final TableToolbar tableToolbar,
			final String action ) {
		return new SelectionListener<IconButtonEvent>() {

			public void componentSelected(IconButtonEvent event) {

				final EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();

				if(action.equals(FenixToolbarConstants.EDIT)) {
					System.out.println("switchView: EDIT IS PRESSED");

                    SimpleComboBox dp = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);
					String selectedPrecision = dp.getSimpleValue().toString();
					
					tableToolbar.getToolbar().removeAll();
					tableToolbar.setSelectedPrecision(selectedPrecision);
					
					//SimpleComboBox dp = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);
					/**if(dp!=null) {
						String selectedPrecision = dp.getSimpleValue().toString();

						tableToolbar.getToolbar().removeAll();
						tableToolbar.setSelectedPrecision(selectedPrecision);
					}***/
					
					final TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
					
					tableToolbar.appendEditIcons(tableToolbar.getToolbar(), tableWindow, resourceId, tableVO.getDatasetTitle());
										
					Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
					Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();

					//clear configs	
					if(tableWindow.getDatasetGrid().getEditableConfigs()!=null)
						tableWindow.getDatasetGrid().getEditableConfigs().clear();

					tableWindow.getDatasetGrid().setEditableConfigs();
					
					final List<String> columnNames = new ArrayList<String>();

					//Add the row ID as the first column
					columnNames.add("ID");
					tableWindow.getDatasetGrid().getEditableConfigs().add(tableWindow.getDatasetGrid().getRowIdColumnConfiguration());


					//set the editable column config
					for (int i = 0; i < dimensionDetailsMap.size(); i++) {
						Map.Entry<String, DimensionBeanVO> entry = iterator.next();

						String columnId = entry.getKey();
						DimensionBeanVO bean = entry.getValue();

						String columnName = bean.getHeader();
						columnNames.add(columnName);

						//set column configuration
						ColumnConfig columnConfig = new ColumnConfig();
						columnConfig.setId(columnId);  
						columnConfig.setHeader(columnName);
						columnConfig.setWidth(100);

						//editable configuration
						tableWindow.getDatasetGrid().getEditableConfigs().add(tableWindow.getDatasetGrid().getColumnConfiguration(columnConfig, bean, tableVO.getPeriodTypeCode())); 

					}

					grid.reconfigure(grid.getStore(), new ColumnModel(tableWindow.getDatasetGrid().getEditableConfigs()));

					grid.el().unmask();
					grid.getView().setForceFit(true); 
					grid.getView().refresh(true);


				}
				else {
					System.out.println("VIEW IS PRESSED");

					tableToolbar.getToolbar().removeAll();
					
					TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
					
					tableToolbar.appendViewIcons(tableToolbar.getToolbar(), tableWindow, resourceId,  tableVO.getDatasetTitle());
					tableToolbar.setSelectedPrecision(null);
					
					final Map<String, List<String>> filterCriteria = tableWindow.getTableFilters().getTableController().getFilterCriteria();
					
					
					SimpleComboBox dp = tableWindow.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);
					System.out.println("################# SelectionChange size = "+dp.getListeners(Events.SelectionChange).size());
					if (dp.getListeners(Events.SelectionChange).size() == 0);
					dp.addSelectionChangedListener(TableToolbarController.changeDecimalPoint(tableWindow, dp, tableVO, resourceId, filterCriteria, "LOAD_BUTTON"));
				
					grid.reconfigure(grid.getStore(), new ColumnModel(tableWindow.getDatasetGrid().getReadOnlyConfigs()));

					grid.el().unmask();
					grid.getView().setForceFit(true);
					grid.getView().refresh(true);

				}
			}
		};
	}
	

	public static SelectionListener<IconButtonEvent> validateModifiedRecords(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
					
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
				
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
	
	public static SelectionListener<IconButtonEvent> confirmDeleteListener(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {

			public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
				List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				
            	if(md.size() > 0) 
				  MessageBox.confirm(BabelFish.print().confirmDelete(), BabelFish.print().deleteWarning(), handleDeleteResponseListener(event, resourceId, tableWindow)); 
			    else FenixAlert.alert(BabelFish.print().alert(), BabelFish.print().pleaseSelectRowToDelete());
			}
		};
	}
	
	private static Listener<MessageBoxEvent> handleDeleteResponseListener(final ComponentEvent event, final Long resourceId, final TableWindow tableWindow) {
		return new Listener<MessageBoxEvent>() {

			 public void handleEvent(MessageBoxEvent event) {
//				        Dialog dialog = (Dialog) event.getComponent();
				       Button btn = event.getButtonClicked();
				      //  Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
				        if(btn.getText().equals("Yes"))
				        	removeTableRow(event, resourceId, tableWindow);	        	
		     }  
		};
	}
	
	private static void removeTableRow(final ComponentEvent event, final Long resourceId, final TableWindow tableWindow) {
			
				TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		
				final EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
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

						TableServiceEntry.getInstance().deleteRow(deleteRowList, tableVO.getDatasetType(), resourceId, new AsyncCallback() {
							public void onSuccess(Object result) {
								FenixAlert.info(BabelFish.print().delete(), BabelFish.print().deleteSuccessful());
								BirtServiceEntry.getInstance().chartUpdate(resourceId, new AsyncCallback() {
									public void onFailure(Throwable arg0) {
										
									}

									public void onSuccess(Object arg0) {
									}
								});
							//	new UpdateChart(Long.toString(resourceId));
							//	grid.recalculate();
							}
							public void onFailure(Throwable caught) {
								Info.display("TableToolbarController: deleteRow", caught.getLocalizedMessage());
							}
						});

					}
				
				/*  grid.getStore().remove(grid.getStore().getAt(0));  
				       if (grid.getStore().getCount() == 0) {  
			        	 event.component.disable();  
			         }  */
		
		
	}
	
	public static SelectionListener<IconButtonEvent> copyTableRowListener(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			
			public void componentSelected(IconButtonEvent event) {
				
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
				ColumnModel columnModel = tableWindow.getDatasetGrid().getEditorGrid().getColumnModel();
					
            	List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
            	
            	List<DatasetRowModel> mdCopy = new ArrayList<DatasetRowModel>();
            	
		 	    if(md.size() > 0) {
            		for (int i=0; i < md.size(); i++){		
            			DatasetRowModel item = md.get(i);
            			DatasetRowModel newRowModel = new DatasetRowModel();
            			newRowModel.set("column0", "");//set the ID column to empty
            			mdCopy.add(newRowModel);
        				grid.stopEditing();  
            			grid.getStore().insert(newRowModel, 0);
            			grid.getSelectionModel().setSelection(mdCopy); //set as the selected row
                		
            			Record newRowRecord = grid.getStore().getRecord(newRowModel);
                		
            			for (int j=1; j < columnModel.getColumnCount()-1; j++){
            				//System.out.println("---- column"+j+" = "+item.get("column"+j));
            				newRowRecord.set("column"+j, item.get("column"+j));
            			}
            			
            			if (grid.getStore().getCount() == 0) {  
            				event.getComponent().disable();
            			}  
            		}
            	} else FenixAlert.alert(BabelFish.print().alert(), BabelFish.print().pleaseSelectRowToCopy());
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> insertTableRowListener(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			
         public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
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
	
	private static void saveAllChanges(final Long resourceId, final TableWindow tableWindow) {

		final EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();

		TableVO tableVO = (TableVO)tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
		
		ColumnModel cm = grid.getColumnModel();

		List<Record> modifiedRecords =  grid.getStore().getModifiedRecords();
				
		Map<String, Map<String, String>> modifiedRecordsMap =  new HashMap<String, Map<String, String>>();
		Map<Integer, Map<String, String>> newRecordsMap =  new HashMap<Integer, Map<String, String>>();
	
		//new ones
		Map<String, DimensionBeanVO> dimensionIdentifierMap =  new HashMap<String, DimensionBeanVO>();

		int j = 0;
		for (Record modifiedRecord: modifiedRecords){
			ModelData model = modifiedRecord.getModel();
			String recordId = (String)model.get("column0"); //i.e. contentId for core dataset or flexrowId for flex dataset
			
			//modified fields: For New Rows/Existing Rows
			Map<String, Object> modifiedFields = modifiedRecord.getChanges();
			Iterator<Map.Entry<String, Object>> modifiedFieldsIterator   = modifiedFields.entrySet().iterator();

			Map<String, String> modifiedPropertiesMap =  new HashMap<String, String>();
			
			for (int i = 0; i < modifiedFields.size(); i++) {

				Map.Entry<String, Object> entry = modifiedFieldsIterator.next();
				String modifiedColumnId =  entry.getKey();
				Object originalValue = entry.getValue();
				String newValue = (String)model.get(modifiedColumnId);
				DimensionBeanVO bean = dimensionDetailsMap.get(modifiedColumnId);

				String fieldName = cm.getColumnById(modifiedColumnId).getId();

				System.out.println("--- changesmap property modified = "+  modifiedColumnId + " original value = "+originalValue + " new value = "+newValue + " rowId = "+ model.get("column0") + " fieldName = " +  fieldName);

				modifiedPropertiesMap.put(modifiedColumnId, newValue);
				dimensionIdentifierMap.put(modifiedColumnId, bean);

			}

			if(recordId!=null && recordId.length() > 0) { 
				modifiedRecordsMap.put(recordId, modifiedPropertiesMap);
			} else {
				newRecordsMap.put(j,modifiedPropertiesMap);
			}			
			j++;
		}

		if(modifiedRecordsMap.size() > 0 || newRecordsMap.size() > 0) {//overwrite or append

			final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Saving Changes in the Dataset. Updating Charts and Multidimensional Tables in External Websites.", BabelFish.print().pleaseWait());
			TableServiceEntry.getInstance().saveDataset(resourceId, modifiedRecordsMap, newRecordsMap, dimensionIdentifierMap, new AsyncCallback() {
				public void onSuccess(Object result) {
					l.destroyLoadingBox();
					grid.getStore().commitChanges();
					FenixAlert.info(BabelFish.print().save(), BabelFish.print().saveCompleted());
					BirtServiceEntry.getInstance().chartUpdate(resourceId, new AsyncCallback() {
						public void onFailure(Throwable arg0) {
							l.destroyLoadingBox();
							l.destroyLoadingBox();
						}

				
						public void onSuccess(Object arg0) {
							l.destroyLoadingBox();
							l.destroyLoadingBox();
						}
					});
					l.destroyLoadingBox();
//					new UpdateChart(Long.toString(resourceId));
					//tableWindow.getTablePager().refresh();
				}
				public void onFailure(Throwable caught) {
					l.destroyLoadingBox();
					Info.display("TableToolbarController: saveDataset", caught.getLocalizedMessage());
					l.destroyLoadingBox();
				}
			});

		}
	}


	
	public static SelectionListener<ComponentEvent> updateSelectedRows(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
				List<DatasetRowModel> md = grid.getSelectionModel().getSelectedItems();
				for (DatasetRowModel item: md){
					//FenixAlert.alert("updateSelectedRows", "updateSelectedRows");
					grid.getStore().getRecord(item).commit(false); 
				}

			
			}
		};
	}

	public static SelectionListener<IconButtonEvent> resetAllChanges(final Long resourceId, final TableWindow tableWindow) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				EditorGrid<DatasetRowModel> grid = tableWindow.getDatasetGrid().getEditorGrid();
				grid.getStore().rejectChanges();  
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showFilters(final TableWindow tableWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				BorderLayout layout = (BorderLayout)tableWindow.getWindow().getLayout();
				ToggleButton toggle = (ToggleButton)ce.getButton();
				if(toggle.isPressed()) {
					layout.expand(LayoutRegion.WEST);
					toggle.setText("Close Filters");
				} else {
					layout.collapse(LayoutRegion.WEST);
					toggle.setText("Open Filters");
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> openChart(final long resourceId, final String datasetTitle) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				ChartDesignerWindow w = new ChartDesignerWindow();
				w.build();
				ChartDesignerDatasourceFieldSet fs = new ChartDesignerDatasourceFieldSet("LINE");
				w.getChartDesignerPanel().getDatasourcePanel().getModels().clear();
				ResourceChildModel m = new ResourceChildModel();
				m.setId(String.valueOf(resourceId));
				m.setName(datasetTitle);
				w.getChartDesignerPanel().getDatasourcePanel().getModels().add(m);
				ChartDesignerController.loadDatasetAgent(w, fs, true);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> openMultidimensionalTable(final long resourceId, final String datasetTitle) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				MTWindow w = new MTWindow();
				w.build();
	        	MTDatasourceFieldSet fs = new MTDatasourceFieldSet();
	        	w.getMtPanel().getMtDatasourcePanel().getModels().clear();
	        	ResourceChildModel m = new ResourceChildModel();
				m.setId(String.valueOf(resourceId));
				m.setName(datasetTitle);
				w.getMtPanel().getMtDatasourcePanel().getModels().add(m);
				MTController.loadDatasetAgent(w, fs, null);
			}
		};
	}
	
	private static void updateOlapDimensions(OlapWindow win) {
		String datasetId = win.getTabPanel().getSingleDatasetPanel().getDataSource().getValue(win.getTabPanel().getSingleDatasetPanel().getDataSource().getSelectedIndex());
		ListBox x = win.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		ListBox y = win.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		ListBox z = win.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		ListBox w = win.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		ListBox values = win.getTabPanel().getSingleDatasetPanel().getValues(); 
		OlapController.updateDimensions(datasetId, x, y, z, w, values, null, null);
	}
}
