package org.fao.fenix.web.modules.udtable.client.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.udtable.client.view.UDTableFilter;
import org.fao.fenix.web.modules.udtable.client.view.UDTableGrid;
import org.fao.fenix.web.modules.udtable.client.view.UDTableWindow;
import org.fao.fenix.web.modules.udtable.common.constants.UDTableConstants;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.DimensionBean;
import org.fao.fenix.web.modules.udtable.common.vo.DimensionItemModel;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.DataList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;



public class UDTableController {
	
	private UDTableFilter tableFilters;
	private Map<String, DimensionBean> queryCache;
	private Map<String, List<String>> filterCriteria;
	private Map<String, List<DimensionItemModel>> dimensionCache;
	private List<UDTableFilterVO> filters;
	private UDTableVO tableVO;

	public UDTableVO getTableVO() {
		return tableVO;
	}


	public void setTableVO(UDTableVO tableVO) {
		this.tableVO = tableVO;
	}


	public Map<String, List<DimensionItemModel>> getDimensionCache() {
		return dimensionCache;
	}


	public void setDimensionCache(Map<String, List<DimensionItemModel>> dimensionCache) {
		this.dimensionCache = dimensionCache;
	}


	public Map<String, List<String>> getFilterCriteria() {
		return filterCriteria;
	}


	public void setUDTableFilterVOList(List<UDTableFilterVO> filters) {
		this.filters = filters;
	}

	public List<UDTableFilterVO> getUDTableFilterVOList() {
		return filters;
	}


	public void setFilterCriteria(Map<String, List<String>> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}


	public Map<String, DimensionBean> getQueryCache() {
		return queryCache;
	}


	public void setQueryCache(Map<String, DimensionBean> queryCache) {
		this.queryCache = queryCache;
	}

     public UDTableController(UDTableFilter tableFilters, final Long resourceId) {
		this.tableFilters = tableFilters;
		this.queryCache = new HashMap<String, DimensionBean>();
		this.filterCriteria = new HashMap<String, List<String>>();
		this.dimensionCache = new HashMap<String, List<DimensionItemModel>>();
		this.filters = new ArrayList<UDTableFilterVO>();

		UDTableServiceEntry.getInstance().getFilters(resourceId, new AsyncCallback<List<UDTableFilterVO>>() {
			public void onSuccess(List<UDTableFilterVO> filterList) {
				filters.clear();
				for(UDTableFilterVO f : filterList)
					filters.add(f);
				
				/*UDTableServiceEntry.getInstance().getDatasetDetails(resourceId, new AsyncCallback<UDTableVO>() {
					public void onSuccess(UDTableVO tableVO) {
						setTableVO(tableVO); 
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "TableController @ getDatasetDetails");
					}
				});*/
			}
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "TableController @ getFilters");
			}
		});

	}


	public UDTableController(UDTableFilter tableFilters) {
		this.tableFilters = tableFilters;
		this.queryCache = new HashMap<String, DimensionBean>();
		this.filterCriteria = new HashMap<String, List<String>>();
		this.dimensionCache = new HashMap<String, List<DimensionItemModel>>();
	}

	
	public  SelectionListener<IconButtonEvent> viewMetadata(final Long resourceId) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent evt) {
				MEOpener.showMetadata(resourceId, false, false); //check this!!
			}
		};
	}
	
	
	public  Listener<ComponentEvent> selectedDimension(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Long resourceId, final String datasetType) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent event) {
				fillFromList(dimensionValues, selectedValues, dimensionDataList, resourceId, datasetType);
			};
		};
	}

	public void fillFromList(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Long resourceId, final String datasetType) {
		String dimensionName = dimensionDataList.getSelectedItem().getId();
		String columnName = dimensionDataList.getSelectedItem().getText();
		
		
		final String dimensionId = dimensionDataList.getSelectedItem().getId();  
			if (datasetType.equals("Core")) {
			UDTableServiceEntry.getInstance().getDimensionValues(resourceId, columnName, getLanguage(), new AsyncCallback<Map<String, String>>() {
				public void onSuccess(Map<String, String> values) {
					dimensionValues.clear();
					selectedValues.clear();
					Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
					for (int i = 0; i < values.size(); i++) {
						Map.Entry<String, String> entry = iterator.next();
						dimensionValues.addItem(entry.getValue(),entry.getKey());
					}
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "fillFromList @ getDimensionValues");
				}
				
			});
			} else {
				UDTableServiceEntry.getInstance().getDimensionValues(resourceId, columnName, getLanguage(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> values) {	
						dimensionValues.clear();
						selectedValues.clear();
						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
						for (int i = 0; i < values.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();
							dimensionValues.addItem(entry.getValue(),entry.getKey());
						}
					}
	
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "fillFromList @ getDimensionValues");
					}
				});
			}
		}
	
	//public void fillFromList2(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final UDTableVO UDTableVO) {
	
public void fillFromList2(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Long resourceId,  final String datasetType) {
		//String dimensionName = dimensionDataList.getSelectedItem().getId();
		final String columnName = dimensionDataList.getSelectedItem().getText();
       // final String datasetType = UDTableVO.getDatasetType();
       // final Long resourceId = UDTableVO.getResourceId();

		if(dimensionCache.containsKey(columnName)){
			List<DimensionItemModel> values = dimensionCache.get(columnName);

			dimensionValues.clear();
			selectedValues.clear();

			for (DimensionItemModel model: values) {
				dimensionValues.addItem(model.getName(),model.getCode());
			}

		} else {
                System.out.println("datasetType = "+datasetType);
                System.out.println("columnName = "+columnName);

			//final String dimensionId = dimensionDataList.getSelectedItem().getId();  
			if (datasetType.equals("Core")) {
				UDTableServiceEntry.getInstance().getDimensionValues(resourceId, columnName, getLanguage(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> values) {
						List<DimensionItemModel> dimensionItemList = new ArrayList<DimensionItemModel>();

						dimensionValues.clear();
						selectedValues.clear();
						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
						for (int i = 0; i < values.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();
							dimensionValues.addItem(entry.getValue(),entry.getKey());
							dimensionItemList.add(new DimensionItemModel(entry.getValue(), entry.getKey()));
						}

						//to be used by the grid
						dimensionCache.put(columnName, dimensionItemList);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "fillFromList @ getDimensionValues");
					}

				});
			} else {
				System.out.println("datasetType: " + datasetType);
				UDTableServiceEntry.getInstance().getDimensionValues(resourceId, columnName, getLanguage(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> values) {	
						List<DimensionItemModel> dimensionItemList = new ArrayList<DimensionItemModel>();


						dimensionValues.clear();
						selectedValues.clear();
						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
						for (int i = 0; i < values.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();
							dimensionValues.addItem(entry.getValue(),entry.getKey());
							dimensionItemList.add(new DimensionItemModel(entry.getValue(), entry.getKey()));
						}

						//to be used by the grid
						dimensionCache.put(columnName, dimensionItemList);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "fillFromList @ getDimensionValues");
					}
				});
			}
		}
	}


	public SelectionListener<ButtonEvent> addToFilter2(final ListBox selectedValues, final DataList dimensionDataList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				//selected dimension details
				String selectedDimensionLabel = dimensionDataList.getSelectedItem().getText();  
				String selectedDimensionDescriptor = dimensionDataList.getSelectedItem().getId();
				System.out.println("FILTERS SIZE: " + filters.size());				
				System.out.println("ADD TO FILTER: " + selectedDimensionLabel + " " + selectedDimensionDescriptor);

				StringBuilder dimensionBuilder = new StringBuilder();


				for(UDTableFilterVO filter : filters){
					if ( filter.getHeader().equals(selectedDimensionLabel)) {
						filter.getAllowedValues().clear();
						filter.getShowValues().clear();

						for(int i =0 ; i < selectedValues.getItemCount(); i++) {
							if (selectedValues.isItemSelected(i)) {
								filter.addAllowedValues(selectedValues.getValue(i));
								filter.addShowValues(selectedValues.getItemText(i));

								System.out.println("ADD VALUES TO FILTER: " + selectedValues.getItemText(i) + " " + selectedValues.getValue(i));
							}
						}
					}
				}

				tableFilters.queryFilterFieldSet.removeAll();	
				Boolean check = false;
				System.out.println("FILTERS" + filters.get(0).getResourceId());				
				for(UDTableFilterVO filter : filters){
					System.out.println("resourceid: "+ filter.getResourceId());
					System.out.println("header: "+ filter.getHeader());	
					if ( check && filter.getShowValues().size() > 0) 
						dimensionBuilder.append(tableFilters.andSeparator);			
					for(int i=0; i < filter.getShowValues().size(); i++){				
						if (i == 0)
							dimensionBuilder.append("<b>").append(filter.getHeader()).append(" </b> = ");
						
						System.out.println("values: "+ filter.getShowValues().get(i));
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
	
	
	public void getLatestData(final UDTableVO tableVO, final Long resourceId, final UDTableWindow window, final String caller) {

		/*UDTableServiceEntry.getInstance().getMostRecentDate(tableVO.getDatasetExplicitType(), new AsyncCallback<String>() {
			public void onSuccess(String result) {

				System.out.println("------------ result = "+ result);
				List<String> dimensionCodes = new ArrayList<String>();
				dimensionCodes.add(result);
				getFilterCriteria().put("date",dimensionCodes);

				//building the table grid
				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);

				window.tableGrid = new UDTableGrid(window);

				//remove grid component if already exists
				if(gridComponent!=null ){
					window.getCenter().remove(gridComponent);
				} 

				System.out.println("------------ filterCriteria = "+ filterCriteria.size());


				//build the grid
				window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("getMostRecentDate RPC Failed", "TableController @ getLatestData");
			}
		});*/




	}
	
	
	
		public  SelectionListener<ButtonEvent> clearFilter2(final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				List<UDTableFilterVO> filterList = getUDTableFilterVOList();

				if(filterList.size() > 0 ){	
					for(UDTableFilterVO filter : filterList) {
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

	public  SelectionListener<ComponentEvent> clearFilter(final Long resourceId) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {

				Map<String, DimensionBean> queryMap = getQueryCache();
				
				//clear maps
				if(queryMap!=null) {
					queryMap.clear();
					getFilterCriteria().clear();
				}
				
				
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
	
	/*** SELECTION FILTER BUTTONS ***/
	
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
	
	public  SelectionListener<ComponentEvent> clearFilter(final Long resourceId, final List<UDTableFilterVO> filters) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				
				for(UDTableFilterVO filter : filters) {
					filter.getAllowedValues().clear();
					filter.getShowValues().clear();
				}
				

				Map<String, DimensionBean> queryMap = getQueryCache();
				
				//clear maps
				if(queryMap!=null) {
					queryMap.clear();
					getFilterCriteria().clear();
				}
				
				
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
	
	//final UDTableVO tableVO, 
	public  SelectionListener<ButtonEvent> getData(final Long resourceId, final UDTableWindow window, final UDTableFilter tableFilter, final String caller) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				List<UDTableFilterVO> filterList = getUDTableFilterVOList();

				for(UDTableFilterVO filter : filterList){
					System.out.println("-------- filter.getDataType() = "+filter.getDataType() + " ======= filter.getAllowedValues() size " + filter.getAllowedValues().size());
					if(filter.getAllowedValues()!=null && filter.getAllowedValues().size() > 0)
						getFilterCriteria().put(filter.getDataType(), filter.getAllowedValues());
				}

				//building the table grid
				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);

				window.tableGrid = new UDTableGrid(window, tableFilter);

				//remove grid component if already exists
				if(gridComponent!=null ){
					window.getCenter().remove(gridComponent);
				} 


				//build the grid
				//window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
				
				//paged table controller		
				window.tableGrid.build(filterList, resourceId, caller, 0, UDTableConstants.MAX_RESULT_SET_SIZE);

			}
		};
	}
	
	//public  SelectionListener<ComponentEvent> getData(final Long resourceId, final UDTableWindow window, final String caller) {
	//	return new SelectionListener<ComponentEvent>() {
	//	public void componentSelected(ComponentEvent event) {
	//		System.out.println("LOAD GRID");
//				Map<String, DimensionBean> queryMap = getQueryCache();
//				
//				//build filteriaCriteria Map with the codes only
//				Iterator<Map.Entry<String, DimensionBean>> iterator = queryMap.entrySet().iterator();
//
//				for (int i = 0; i <queryMap.size(); i++) {
//					Map.Entry<String, DimensionBean> entry = iterator.next();
//
//					DimensionBean bean = entry.getValue();
//					List<DimensionItemModel> dimensionItemList = bean.getSelectedDimensionItemModelList();
//					
//					List<String> dimensionCodes = new ArrayList<String>();
//
//					for(DimensionItemModel item : dimensionItemList)
//					{
//						dimensionCodes.add(item.getCode());
//					}
//					
//					filterCriteria.put(bean.getColumnDescriptor(), dimensionCodes);	
//				}
//				
//				
//				//building the table grid
//				Component gridComponent = window.getCenter().getItemByItemId("grid"+resourceId);
//		
	//			window.tableGrid = new UDTableGrid(window);
//				
//				
//				//remove grid component if already exists
//				if(gridComponent!=null ){
//					window.getCenter().remove(gridComponent);
//				} 
//				
//				
//				//build the grid
	/*			if ( filters.isEmpty() ){
					System.out.println("is null");
				}
				else {
					System.out.println("not " + filters.size() + " | "+ resourceId );
				}
			
				window.tableGrid.build(filters, resourceId,  caller);
		
			}
		};
	}*/
	
	
	public SelectionListener<ComponentEvent> addToFilter(final ListBox selectedValues, final DataList dimensionDataList, final List<UDTableFilterVO> filters) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {

				//selected dimension details
				String selectedDimensionLabel = dimensionDataList.getSelectedItem().getText();  
				String selectedDimensionDescriptor = dimensionDataList.getSelectedItem().getId();
				
				System.out.println("FILTERS SIZE: " + filters.size());
				
				System.out.println("ADD TO FILTER: " + selectedDimensionLabel + " " + selectedDimensionDescriptor);
				
				StringBuilder dimensionBuilder = new StringBuilder();
				
				
				for(UDTableFilterVO filter : filters){
					if ( filter.getHeader().equals(selectedDimensionLabel)) {
						filter.getAllowedValues().clear();
						filter.getShowValues().clear();
						for(int i =0 ; i < selectedValues.getItemCount(); i++) {
							if ( selectedValues.isItemSelected(i)) {
								filter.addShowValues(selectedValues.getItemText(i));
								filter.addAllowedValues(selectedValues.getValue(i));
								System.out.println("ADD VALUES TO FILTER: " + selectedValues.getItemText(i) + " " + selectedValues.getValue(i));
							}
						}
					}
				}
				
				tableFilters.queryFilterFieldSet.removeAll();	
				Boolean check = false;
				System.out.println("FILTERS" + filters.get(0).getResourceId());				
				for(UDTableFilterVO filter : filters){
					System.out.println("resourceid: "+ filter.getResourceId());
					System.out.println("header: "+ filter.getHeader());	
					if ( check && filter.getShowValues().size() > 0) 
						dimensionBuilder.append(tableFilters.andSeparator);			
					for(int i=0; i < filter.getShowValues().size(); i++){				
						if (i == 0)
							dimensionBuilder.append("<b>").append(filter.getHeader()).append(" </b> = ");
						System.out.println("values: "+ filter.getShowValues().get(i));
//						if ( i % 2 == 0) {
							if (i != 0) 
								dimensionBuilder.append(tableFilters.orSeparator);
							dimensionBuilder.append(filter.getShowValues().get(i));
//						}
						check = true;
					}
				}
				
				
//				
				tableFilters.queryFilterFieldSet.add(new HTML(dimensionBuilder.toString()));
//			
//				//enable the Clear Filter and Load Table Button
				tableFilters.clearFilterButton.enable();
				tableFilters.loadTableButton.enable();

				//refresh
				tableFilters.panel.layout();

			}
		};
	}
	
	public static String getLanguage(){
		return CheckLanguage.getLanguage();	
	}
	
	
}