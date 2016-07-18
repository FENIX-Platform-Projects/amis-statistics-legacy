package org.fao.fenix.web.modules.udtable.client.view;

import java.util.Iterator;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.udtable.client.control.UDTableController;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.DimensionBean;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.DataListSelectionModel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;


public class UDTableFilter {

	public ContentPanel panel;
	
    private DataList dimensionDataList;
	
	private UDTableController udtableController;
		
	private ButtonBar addToFilterButtonBar = new ButtonBar();
	private Button addToFilterButton;
	
	private ButtonBar buttonBar = new ButtonBar();
	public Button loadTableButton;
	public Button clearFilterButton;
	
	public FieldSet queryFilterFieldSet;
	private FieldSet dimensionFieldSet;
	private FieldSet dimensionValuesFieldSet;
		
	public FormPanel form;
	
	
	private UDTableWindow udtableWindow;
	
	private ListBox dimensionValues; 
	private ListBox selectedValues;
	private Button addAll;
	private Button removeAll;
	private Button addSelected;
	private Button removeSelected;
//	private String lang = "EN";
			
	
	public String andSeparator = "<br/><b>"+BabelFish.print().and()+"</b><br/>" ;
	public String orSeparator = " <b>"+BabelFish.print().or()+"</b> ";
	
	public UDTableFilter(UDTableWindow window, Long resourceId) {
		System.out.println("------------- START TABLE FILTERS -------------------");
		
		this.udtableController = new UDTableController(this, resourceId);
		this.udtableWindow = window;
		
		//initialize
		panel = new ContentPanel(new FlowLayout());
		
		form = new FormPanel();
		
		dimensionDataList = new DataList();
		
		dimensionFieldSet = new FieldSet();
		dimensionValuesFieldSet  = new FieldSet();
		queryFilterFieldSet = new FieldSet();


		
		addToFilterButton = new Button(BabelFish.print().addSelectedValuesToFilter());
		loadTableButton = new Button(BabelFish.print().loadTable());
		clearFilterButton = new Button(BabelFish.print().clearFilter());
		
		dimensionValues = new ListBox();
		selectedValues = new ListBox();
		addAll = new Button(">>");
		removeAll = new Button("<<");;
		addSelected = new Button(">");;
		removeSelected = new Button("<");
		
		format();
	}
	
	public ContentPanel build(Long resourceId, boolean getAllData) {

//	public ContentPanel build(Long resourceId, List<UDTableFilterVO> filters) {
	//	buildDatasetFilterPanel(resourceId, filters);
	    buildDatasetFilterPanel(resourceId, getAllData);
		enhanceButtons();
		return panel;
	}
	
	public void buildDatasetFilterPanel(final Long resourceId, final boolean getAllData) {
//	public void buildDatasetFilterPanel(final Long resourceId, final List<UDTableFilterVO> filters) {
		UDTableServiceEntry.getInstance().getDatasetDetails(resourceId, new AsyncCallback<UDTableVO>() {
			public void onSuccess(UDTableVO tableVO) {
				  udtableController.setTableVO(tableVO);
			/*	filters.clear();
				UDTableServiceEntry.getInstance().getFilters(resourceId, new AsyncCallback<List<UDTableFilterVO>>() {
					public void onSuccess(List<UDTableFilterVO> filterList) {
						for(UDTableFilterVO f : filterList)
							filters.add(f);
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "buildDatasetPanel @ filters");
					}
				});*/
					
				//System.out.println("UDTABLEFILTER -> FILTER SIZE: "+filters.size());
				panel.setHeading(tableVO.getDatasetTitle());
			    panel.setData(TableConstants.TABLE_VO, tableVO);
				//add toolbar
				panel.add(getToolbar(resourceId));
				
				//add Form elements
               	//FieldSet1: Set dimension list for the dataset:
				//the first dimension is pre-selected
				
				Iterator<Map.Entry<String, DimensionBean>> iterator = tableVO.getDimensionDescriptorMap().entrySet().iterator();

				for (int i = 0; i < tableVO.getDimensionDescriptorMap().size(); i++) {
					System.out.println("CREATING dimensiondatalist: " + tableVO.getDatasetTitle());
					Map.Entry<String, DimensionBean> entry = iterator.next();
					DimensionBean bean = entry.getValue();
					DataListItem item = new DataListItem();
					item.setText(bean.getLabel());
					item.setId(bean.getColumnDescriptor());
					dimensionDataList.add(item);
				}
				
				
				dimensionDataList.setSelectedItem(dimensionDataList.getItem(0)); //set the first item selected
				dimensionDataList.setHeight("100px");
				dimensionDataList.setScrollMode(Scroll.AUTO);
			
				dimensionFieldSet.add(dimensionDataList);
				form.add(dimensionFieldSet);  

				//FieldSet2: List the Dimension values, for the selected dimension:
				//the from list is pre-filled with the first dimension values
				//udtableController.fillFromList2(dimensionValues, selectedValues, dimensionDataList, resourceId, tableVO.getDatasetType());
				udtableController.fillFromList2(dimensionValues, selectedValues, dimensionDataList, resourceId, tableVO.getDatasetType());
				
				
				//populate the 'from' list with the appropriate dimension values
				dimensionDataList.addListener(Events.SelectionChange, udtableController.selectedDimension(dimensionValues, selectedValues, dimensionDataList, resourceId, tableVO.getDatasetType()));  
				dimensionValuesFieldSet.add(createDimensionValesPanel());
				
				//Add the 'Add to Filter Button'
				addToFilterButton.addSelectionListener(udtableController.addToFilter2(selectedValues, dimensionDataList));
			//	addToFilterButton.addSelectionListener(udtableController.addToFilter2(selectedValues, dimensionDataList, filters));
addToFilterButtonBar.add(addToFilterButton);
				
				dimensionValuesFieldSet.add(addToFilterButtonBar);
	            form.add(dimensionValuesFieldSet);

				//FieldSet3: Selected Criteria
	            form.add(queryFilterFieldSet);
	            
	         	//Add the 'Load Table Button'
	            loadTableButton.addSelectionListener(udtableController.getData(resourceId, udtableWindow, udtableWindow.getTableFilter(), "LOAD_BUTTON"));
			  	
			  	if(udtableController.getQueryCache().size()==0) {
			  		clearFilterButton.disable();
			    	loadTableButton.disable();
			    }
			    clearFilterButton.addSelectionListener(udtableController.clearFilter2(resourceId));
			   //  clearFilterButton.addSelectionListener(udtableController.clearFilter2(resourceId, filters));
			    /*** list BOx **/
			   
			    if(getAllData){
			    	UDTableController controller = getUDTableController();
			    	controller.getLatestData(tableVO, resourceId, udtableWindow, "LOAD_BUTTON");
			    }

			    buttonBar.add(clearFilterButton);
			    buttonBar.add(loadTableButton);
				
	            form.add(buttonBar);

				panel.add(form);
								
				udtableWindow.show();
				
				//refresh
				panel.getLayout().layout();
				
							}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "buildDatasetPanel @ TableFilters");
			}
		});


	}
	
	public void buildDatasetFilterPanelXXX(final Long resourceId, final boolean getAllData) {
			
		UDTableServiceEntry.getInstance().getDatasetDetails(resourceId, new AsyncCallback<UDTableVO>() {
			public void onSuccess(UDTableVO tableVO) {
				    udtableController.setTableVO(tableVO);
				    
					//System.out.println("UDTABLEFILTER -> FILTER SIZE: "+filters.size());
				    panel.setHeading(tableVO.getDatasetTitle());
				    panel.setData(TableConstants.TABLE_VO, tableVO);
					//add toolbar
					panel.add(getToolbar(resourceId));
					
					//add Form elements
	               	//FieldSet1: Set dimension list for the dataset:
					//the first dimension is pre-selected
					
					Iterator<Map.Entry<String, DimensionBean>> iterator = tableVO.getDimensionDescriptorMap().entrySet().iterator();

					for (int i = 0; i < tableVO.getDimensionDescriptorMap().size(); i++) {
						System.out.println("CREATING dimensiondatalist: " + tableVO.getDatasetTitle());
						Map.Entry<String, DimensionBean> entry = iterator.next();
						DimensionBean bean = entry.getValue();
						DataListItem item = new DataListItem();
						item.setText(bean.getLabel());
						item.setId(bean.getColumnDescriptor());
						dimensionDataList.add(item);
					}
					
					
					dimensionDataList.setSelectedItem(dimensionDataList.getItem(0)); //set the first item selected
					dimensionDataList.setHeight("100px");
					dimensionDataList.setScrollMode(Scroll.AUTO);
				
					dimensionFieldSet.add(dimensionDataList);
					form.add(dimensionFieldSet);  

					//FieldSet2: List the Dimension values, for the selected dimension:
					//the from list is pre-filled with the first dimension values
					//udtableController.fillFromList2(dimensionValues, selectedValues, dimensionDataList, resourceId, tableVO.getDatasetType());
					//udtableController.fillFromList2(dimensionValues, selectedValues, dimensionDataList, tableVO);
					
					
					//populate the 'from' list with the appropriate dimension values
					dimensionDataList.addListener(Events.SelectionChange, udtableController.selectedDimension(dimensionValues, selectedValues, dimensionDataList, resourceId, tableVO.getDatasetType()));  
					dimensionValuesFieldSet.add(createDimensionValesPanel());
					
					//Add the 'Add to Filter Button'
					addToFilterButton.addSelectionListener(udtableController.addToFilter2(selectedValues, dimensionDataList));
				    addToFilterButtonBar.add(addToFilterButton);
					
					dimensionValuesFieldSet.add(addToFilterButtonBar);
		            form.add(dimensionValuesFieldSet);

					//FieldSet3: Selected Criteria
		            form.add(queryFilterFieldSet);
		            
		         	//Add the 'Load Table Button'
				    loadTableButton.addSelectionListener(udtableController.getData(resourceId, udtableWindow, udtableWindow.getTableFilter(), "LOAD_BUTTON"));
				  	
				  	if(udtableController.getQueryCache().size()==0) {
				  		clearFilterButton.disable();
				    	loadTableButton.disable();
				    }
				    clearFilterButton.addSelectionListener(udtableController.clearFilter2(resourceId));
				   //  clearFilterButton.addSelectionListener(udtableController.clearFilter2(resourceId, filters));
				    /*** list BOx **/
				   
				    if(getAllData){
				    	UDTableController controller = getUDTableController();
				    	controller.getLatestData(tableVO, resourceId, udtableWindow, "LOAD_BUTTON");
				    }

				    buttonBar.add(clearFilterButton);
				    buttonBar.add(loadTableButton);
					
		            form.add(buttonBar);

					panel.add(form);
									
					udtableWindow.show();
					
					//refresh
					panel.getLayout().layout();
			}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "buildDatasetPanel @ TableFilters");
					}
				});
		}
	
	
	private HorizontalPanel createDimensionValesPanel(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(dimensionValues);
		panel.add(createDimensionValuesButtonPanel());
		panel.add(selectedValues);
		return panel;
	}
	
	private VerticalPanel createDimensionValuesButtonPanel(){
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.add(addAll);
		panel.add(addSelected);
		panel.add(removeSelected);
		panel.add(removeAll);		
		return panel;
	}
	public ToolBar getToolbar(Long resourceId) {
		
		ToolBar toolBar = new ToolBar();
			
	    IconButton iconButton;
		
		iconButton = new IconButton("reMetadataIcon");
		iconButton.setTitle(BabelFish.print().viewMetadata());
		iconButton.addSelectionListener(udtableController.viewMetadata(resourceId));
		toolBar.add(iconButton);
		
		
		return toolBar;
	}

	
	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
	
	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}
	
	private void format() {
		//set titles
		dimensionDataList.setTitle(BabelFish.print().pleaseSelect());
		
		dimensionFieldSet.setHeading(BabelFish.print().datasetDimensions()); 
		dimensionValuesFieldSet.setHeading(BabelFish.print().datasetValues()); 
		queryFilterFieldSet.setHeading(BabelFish.print().selectedCriteriaForFiltering()); 
		
		//set sizes
		panel.setWidth(500); 
		form.setWidth(500); 
		
		dimensionFieldSet.setWidth(445);
		dimensionValuesFieldSet.setWidth(445);
		queryFilterFieldSet.setWidth(445);
		queryFilterFieldSet.setHeight(80);
		queryFilterFieldSet.setStyleAttribute("fontSize", "6px");
		
		//formatting
		panel.setScrollMode(Scroll.AUTOY);
		
		form.setFrame(true);  
	    form.setLayout(new FlowLayout()); 
		form.setHeaderVisible(false);
		
		dimensionDataList.setFlatStyle(true);  
		dimensionDataList.setSelectionMode(SelectionMode.SINGLE);  
		DataListSelectionModel sm = dimensionDataList.getSelectionModel();  
		dimensionDataList.setSelectionModel(sm);  
		dimensionDataList.setBorders(true);  
		
		queryFilterFieldSet.setCollapsible(true);
		queryFilterFieldSet.setScrollMode(Scroll.AUTO);
		
//		addToFilterButtonBar.setCellSpacing(10);
//		addToFilterButtonBar.setButtonAlign(HorizontalAlignment.RIGHT);
		addToFilterButtonBar.setSpacing(10);
		addToFilterButtonBar.setAlignment(HorizontalAlignment.RIGHT);

//		buttonBar.setCellSpacing(10);
//		buttonBar.setButtonAlign(HorizontalAlignment.CENTER);
		buttonBar.setSpacing(10);
		buttonBar.setAlignment(HorizontalAlignment.CENTER);
		
		/*** LIST BOX ***/
		dimensionValues.setSize("200px", "150px");
		selectedValues.setSize("200px", "150px");
		dimensionValues.setMultipleSelect(true);
		selectedValues.setMultipleSelect(true);
		addAll.setWidth("50px");
		addSelected.setWidth("50px");
		removeSelected.setWidth("50px");
		removeAll.setWidth("50px");	
	}
	
	private void enhanceButtons(){
		addAll.addSelectionListener(udtableController.addAll(dimensionValues, selectedValues));
		addSelected.addSelectionListener(udtableController.addSelectedValues(dimensionValues, selectedValues));
		removeSelected.addSelectionListener(udtableController.removeSelected(selectedValues));
		removeAll.addSelectionListener(udtableController.removeAll(selectedValues));
	}

	public UDTableController getUDTableController() {
		return udtableController;
	}



	public UDTableWindow getUDTableWindow() {
		return udtableWindow;
	}

}
