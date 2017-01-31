package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.DateUtils;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.table.client.view.TableFilters;
import org.fao.fenix.web.modules.table.client.view.TableGrid;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;



public class TableController {

	private TableFilters tableFilters;
	private Map<String, DimensionBeanVO> queryCache;
	private Map<String, List<String>> filterCriteria;
	//private Map<String, List<DimensionItemModel>> dimensionCache;
	private List<TableFilterVO> filters;

	/*public Map<String, List<DimensionItemModel>> getDimensionCache() {
		return dimensionCache;
	}


	public void setDimensionCache(Map<String, List<DimensionItemModel>> dimensionCache) {
		this.dimensionCache = dimensionCache;
	}*/


	public Map<String, List<String>> getFilterCriteria() {
		return filterCriteria;
	}


	public void setTableFilterVOList(List<TableFilterVO> filters) {
		this.filters = filters;
	}

	public List<TableFilterVO> getTableFilterVOList() {
		return filters;
	}


	public void setFilterCriteria(Map<String, List<String>> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}


	public Map<String, DimensionBeanVO> getQueryCache() {
		return queryCache;
	}


	public void setQueryCache(Map<String, DimensionBeanVO> queryCache) {
		this.queryCache = queryCache;
	}


	public TableController(TableFilters tableFilters, Long resourceId) {
		this.tableFilters = tableFilters;
		this.queryCache = new HashMap<String, DimensionBeanVO>();
		this.filterCriteria = new HashMap<String, List<String>>();
		//this.dimensionCache = new HashMap<String, List<DimensionItemModel>>();
		this.filters = new ArrayList<TableFilterVO>();

		TableServiceEntry.getInstance().getFilters(resourceId, new AsyncCallback<List<TableFilterVO>>() {
			public void onSuccess(List<TableFilterVO> filterList) {
				filters.clear();
				for(TableFilterVO f : filterList)
					filters.add(f);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "buildDatasetPanel @ filters");
			}
		});

	}


	public  SelectionListener<IconButtonEvent> viewMetadata(final Long resourceId) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent evt) {
				MEOpener.showMetadata(resourceId, false, false); //check this!!
			}
		};
	}


	public  Listener<ComponentEvent> selectedDimension(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Map<String, DimensionBeanVO> dimensionMap, 
			final VerticalPanel datePanel,  final DateFromToPanel dateFromToPanel, final VerticalPanel dateLUpanel, final DateLastUpdatePanel dateLastUpdatePanel, final String periodicity, final HorizontalPanel xValuesPanel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent event) {
				fillFromList(dimensionValues, selectedValues, dimensionDataList, dimensionMap, datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, periodicity, xValuesPanel);
			};
		};
	}


	public void fillFromList(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Map<String, DimensionBeanVO> dimensionMap, final VerticalPanel datePanel,  final DateFromToPanel dateFromToPanel, final VerticalPanel dateLUpanel, final DateLastUpdatePanel dateLastUpdatePanel, final String periodicity, final HorizontalPanel xValuesPanel) {
		//String dimensionName = dimensionDataList.getSelectedItem().getId();
		final String columnId = dimensionDataList.getSelectedItem().getId();
				
		DimensionBeanVO dimensionVO = dimensionMap.get(columnId);
		
		String startDate = new String();
		String endDate = new String();

		
		if(dimensionVO!=null){
			Map<String, String> values = dimensionVO.getDistinctDimensionValues();
			
			dimensionValues.clear();
			selectedValues.clear();
			
			
			enableDisableDatesPanel(dimensionVO.isDate(), datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, dimensionValues, xValuesPanel);

			
			if(values!=null){
				Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
				for (int i = 0; i < values.size(); i++) {
					Map.Entry<String, String> entry = iterator.next();
					dimensionValues.addItem(entry.getValue(),entry.getKey());
					if ( dimensionVO.isDate() ) { 
						if  ( i == values.size() - 1) 
							startDate = entry.getKey();
						if ( i == 0 ) 
							endDate = entry.getKey();
					}
				}
			}
			if (dimensionVO.isDate()) {
				dateFromToPanel.setPeriodType(periodicity);
				dateLastUpdatePanel.setPeriodType(periodicity);
				DateUtils.setDateFromToDefault(startDate, endDate, periodicity, dateFromToPanel.getYearFrom(), dateFromToPanel.getMonthFrom(), dateFromToPanel.getDayFrom(), dateFromToPanel.getYearTo(), dateFromToPanel.getMonthTo(), dateFromToPanel.getDayTo());
//				DateUtils.setDateLastUpdateDefault(lu.getYears(), lu.getMonths(), lu.getDays(), periodicity, p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()), p.getYearTo().getItemText(p.getYearTo().getSelectedIndex()));			
			}
		} 
	}


	
	public SelectionListener<ButtonEvent> addToFilter(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final DateFromToPanel dateFromToPanel, final DateLastUpdatePanel dateLastUpdatePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				//selected dimension details
				String selectedDimensionLabel = dimensionDataList.getSelectedItem().getText();  
				String selectedDimensionDescriptor = dimensionDataList.getSelectedItem().getId();
//				System.out.println("FILTERS SIZE: " + filters.size());				
//				System.out.println("ADD TO FILTER: " + selectedDimensionLabel + " " + selectedDimensionDescriptor);
//				System.out.println("checkBoxes dateFromToPanel: " +dateFromToPanel.getCheckBox().getValue());
//				System.out.println("checkBoxes dateLastUpdatePanel: " +dateLastUpdatePanel.getCheckBox().getValue());
		
				StringBuilder dimensionBuilder = new StringBuilder();

				if ( !dateFromToPanel.getCheckBox().getValue() && !dateLastUpdatePanel.getCheckBox().getValue()) {
					for(TableFilterVO filter : filters){
						if ( filter.getHeader().equals(selectedDimensionLabel)) {
							filter.getAllowedValues().clear();
							filter.getShowValues().clear();
	
							for(int i =0 ; i < selectedValues.getItemCount(); i++) {
								if (selectedValues.isItemSelected(i)) {
									filter.addAllowedValues(selectedValues.getValue(i));
									filter.addShowValues(selectedValues.getItemText(i));
									filter.getSelectedValuesMap().put(selectedValues.getValue(i), selectedValues.getItemText(i));
//									System.out.println("ADD VALUES TO FILTER: " + selectedValues.getItemText(i) + " " + selectedValues.getValue(i));
								}
							}
						}
					}
				}
				if ( dateFromToPanel.getCheckBox().getValue()) {
					for(TableFilterVO filter : filters){
						if ( filter.getHeader().equals(selectedDimensionLabel)) {
							filter.getAllowedValues().clear();
							filter.getShowValues().clear();
							
							Date fromDate = DateUtils.setFromDate(dateFromToPanel);
							Date toDate = DateUtils.setToDate(dateFromToPanel);
							/** get all dates **/
					
							List<List<String>> dimX = DateUtils.getDates(fromDate, toDate, dimensionValues);			
							
							for(List<String> date: dimX) {
									/** adding date value (0) and date string (1) **/
									filter.addAllowedValues(date.get(0));
									filter.addShowValues(date.get(1));
									filter.getSelectedValuesMap().put(date.get(0), date.get(1));
//									System.out.println("ADD VALUES TO FILTER: " + selectedValues.getItemText(i) + " " + selectedValues.getValue(i));
								
							}
						}
					}
				}
				
				

				tableFilters.queryFilterFieldSet.removeAll();	
				Boolean check = false;
//				System.out.println("FILTERS" + filters.get(0).getResourceId());				
				for(TableFilterVO filter : filters){
//					System.out.println("resourceid: "+ filter.getResourceId());
//					System.out.println("header: "+ filter.getHeader());	
					if ( check && filter.getShowValues().size() > 0) 
						dimensionBuilder.append(tableFilters.andSeparator);			
					for(int i=0; i < filter.getShowValues().size(); i++){				
						if (i == 0)
							dimensionBuilder.append("<b>").append(filter.getHeader()).append(" </b> = ");
						
//						System.out.println("values: "+ filter.getShowValues().get(i));
						if (i != 0) 
								dimensionBuilder.append(tableFilters.orSeparator);
						dimensionBuilder.append(filter.getShowValues().get(i));
						
						check = true;
					}
				}




				tableFilters.queryFilterFieldSet.add(new HTML(dimensionBuilder.toString()));

				//enable the Clear Filter and Load Table Button
				tableFilters.clearFilterButton.enable();
				tableFilters.loadTableButton.enable();

				//refresh
				tableFilters.panel.layout();

			}
		};
	}

	

	public  SelectionListener<ButtonEvent> getData(final TableVO tableVO, final Long resourceId, final TableWindow window, final String caller) {
		
		return new SelectionListener<ButtonEvent>() {
			
			public void componentSelected(ButtonEvent event) {

				List<TableFilterVO> filterList = getTableFilterVOList();

				for(TableFilterVO filter : filterList)
					if(filter.getAllowedValues()!=null && filter.getAllowedValues().size() > 0)
						getFilterCriteria().put(filter.getDataType()+"-"+filter.getDimensionDescriptorId(), filter.getAllowedValues());

				//building the table grid
				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);
				window.tableGrid = new TableGrid(window);

				//remove grid component if already exists
				if(gridComponent!=null )
					window.getCenter().remove(gridComponent);

				//build the grid
				window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
				
				// set the listener to change the decimal places
				SimpleComboBox dp = window.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);
				if (dp.getListeners(Events.SelectionChange).size() == 0);
				dp.addSelectionChangedListener(TableToolbarController.changeDecimalPoint(window, dp, tableVO, resourceId, filterCriteria, "LOAD_BUTTON"));
			
				// set the listener to the Save Table View
				IconButton saveTableViewButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE);
				if (saveTableViewButton!=null && saveTableViewButton.getListeners(Events.Select).size() == 0);
					saveTableViewButton.addSelectionListener(TableSaver.saveTableView(tableVO, filterList, null, false));
				
				
				// set the listener to the Save Table View As
//				IconButton saveTableViewAsButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE_AS);
//				if (saveTableViewAsButton.getListeners(Events.Select).size() == 0);
//					saveTableViewAsButton.addSelectionListener(MESaveAs.prepopulateTableView(resourceId, true, false, window, tableVO, filterList));
			}
			
		};
		
	}

	public void getLatestData(final TableVO tableVO, final Long resourceId, final TableWindow window, final String caller) {

		TableServiceEntry.getInstance().getMostRecentDate(tableVO.getDatasetExplicitType(), new AsyncCallback<String>() {
			public void onSuccess(String result) {

//				System.out.println("------------ result = "+ result);
				List<String> dimensionCodes = new ArrayList<String>();
				dimensionCodes.add(result);
				getFilterCriteria().put("date",dimensionCodes);

				//building the table grid
				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);

				window.tableGrid = new TableGrid(window);

				//remove grid component if already exists
				if(gridComponent!=null ){
					window.getCenter().remove(gridComponent);
				} 

//				System.out.println("------------ filterCriteria = "+ filterCriteria.size());


				//build the grid
				window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("getMostRecentDate RPC Failed", "TableController @ getLatestData");
			}
		});




	}


	
	public void getAllData(final TableVO tableVO, final Long resourceId, final TableWindow window, final String caller) {

				List<TableFilterVO> filterList = getTableFilterVOList();
				
				for(TableFilterVO filter : filterList)
					if(filter.getAllowedValues()!=null && filter.getAllowedValues().size() > 0)
						getFilterCriteria().put(filter.getDataType()+"-"+filter.getDimensionDescriptorId(), filter.getAllowedValues());

		
				List<String> dimensionCodes = new ArrayList<String>();

				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);

				window.tableGrid = new TableGrid(window);


				if(gridComponent!=null ){
					window.getCenter().remove(gridComponent);
				} 

				//build the grid
				window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
				
				// set the listener to change the decimal places
				SimpleComboBox dp = window.getTableToolbar().getComboBox(FenixToolbarConstants.CHANGE_PRECISION);
				if (dp.getListeners(Events.SelectionChange).size() == 0);
				dp.addSelectionChangedListener(TableToolbarController.changeDecimalPoint(window, dp, tableVO, resourceId, filterCriteria, "LOAD_BUTTON"));
			
				// set the listener to the Save Table View
				IconButton saveTableViewButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE);
				System.out.println("saveTableViewButton = "+saveTableViewButton);
				
				if (saveTableViewButton!=null && saveTableViewButton.getListeners(Events.Select).size() == 0)
					saveTableViewButton.addSelectionListener(TableSaver.saveTableView(tableVO, filterList, null, false));
				
				
	}



	public  SelectionListener<ButtonEvent> clearFilter(final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				List<TableFilterVO> filterList = getTableFilterVOList();

				if(filterList.size() > 0 ){	
					for(TableFilterVO filter : filterList) {
						filter.getAllowedValues().clear();
						filter.getShowValues().clear();						
					}

					getFilterCriteria().clear();	
				}


				//clear maps
				/*if(queryMap!=null) {
					queryMap.clear();
					getFilterCriteria().clear();
				}*/


				// clear contents of the FieldSet
				tableFilters.queryFilterFieldSet.removeAll();

				//disable the Clear Filter and Load Table Button
				tableFilters.clearFilterButton.disable();
				tableFilters.loadTableButton.disable();

				//refresh
				tableFilters.panel.layout();	
			}
		};
	}

	

	private  void setStoreSorter(ListStore<DimensionItemModel> store) {
		store.setStoreSorter(new StoreSorter<DimensionItemModel>() {   
			@Override  
			public int compare(Store store, DimensionItemModel m1, DimensionItemModel m2, String property) {   
				boolean m1Folder = m1 instanceof DimensionItemModel;   
				boolean m2Folder = m2 instanceof DimensionItemModel;   

				if (m1Folder && !m2Folder) {   
					return -1;   
				} else if (!m1Folder && m2Folder) {   
					return 1;   
				}   

				// System.out.println("---------------- ############ setStoreSorter: property" + property);

				return super.compare(store, m1, m2, "name");   
			}   
		});   
	}

	/*****
	 *  ADDED TO UDT 
	 *  ****/


	public  SelectionListener<ButtonEvent> addAll(final ListBox dimensionValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();
				for(int i=0; i < dimensionValues.getItemCount(); i++){
					selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
					selectedValues.setItemSelected(i, true);
				}

			}
		};
	}

	public  SelectionListener<ButtonEvent> addSelectedValues(final ListBox dimensionValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				for(int i=0; i < dimensionValues.getItemCount(); i++){
					if(dimensionValues.isItemSelected(i)){
						/** check if it's not already selected **/
						if ( selectedValues.getItemCount() != 0 ) {
							Boolean check = false;
							for(int j=0; j < selectedValues.getItemCount(); j++){
								if(selectedValues.getItemText(j).equals(dimensionValues.getItemText(i))){
									check = true;
									break;									
								}	
							}
							if ( !check ) 
								selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
						}
						else 
							selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
					}
				}

				for(int i=0; i < selectedValues.getItemCount(); i++)
					selectedValues.setItemSelected(i, true);

			}
		};
	}


	public  SelectionListener<ButtonEvent> removeAll(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();	
			}
		};
	}

	public  SelectionListener<ButtonEvent> removeSelected(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				ListBox backup = new ListBox();
				for(int i=0; i < selectedValues.getItemCount(); i++){
					if(!selectedValues.isItemSelected(i)){
						backup.addItem(selectedValues.getItemText(i), selectedValues.getValue(i));
					}
				}
				selectedValues.clear();

				for(int i=0; i < backup.getItemCount(); i++){
					selectedValues.addItem(backup.getItemText(i), backup.getValue(i));		
				}
			}
		};
	}

	
	private void enableDisableDatesPanel(Boolean isDate, VerticalPanel datePanel, DateFromToPanel dateFromToPanel, VerticalPanel dateLUpanel, DateLastUpdatePanel dateLastUpdatePanel, ListBox dimensionXValues, HorizontalPanel xValuesPanel ) { 
		if (isDate) {
			datePanel.setVisible(true);
			dateLUpanel.setVisible(true);
		}
		else {
			resetDatePanel(datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, dimensionXValues, xValuesPanel);
			datePanel.setVisible(false);
			dateLUpanel.setVisible(false);
		}
	}
	
	private void resetDatePanel(VerticalPanel datePanel, DateFromToPanel dateFromToPanel, VerticalPanel dateLUpanel, DateLastUpdatePanel dateLastUpdatePanel, ListBox dimensionXValues, HorizontalPanel xValuesPanel) {
		dateLastUpdatePanel.getCheckBox().setValue(false);
		dateFromToPanel.getCheckBox().setValue(false);
		dimensionXValues.setEnabled(true);
		xValuesPanel.setEnabled(true);
		dateLastUpdatePanel.getMonths().setEnabled(false);
		dateLastUpdatePanel.getYears().setEnabled(false);
		dateLastUpdatePanel.getDays().setEnabled(false);
		dateFromToPanel.getYearFrom().setEnabled(false);
		dateFromToPanel.getMonthFrom().setEnabled(false);
		dateFromToPanel.getDayFrom().setEnabled(false);
		dateFromToPanel.getYearTo().setEnabled(false);
		dateFromToPanel.getMonthTo().setEnabled(false);
		dateFromToPanel.getDayTo().setEnabled(false);
//		dateFromToPanel.getFromDateField().setEnabled(false);
//		dateFromToPanel.getToDateField().setEnabled(false);
		datePanel.setVisible(false);
		dateLUpanel.setVisible(false);
	}
}
