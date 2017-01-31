package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.utils;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;   
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareMultiSelectorController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareByArea;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;

public class FAOSTATCompareMultiselection  {
	
	public ContentPanel panel;
	
	public ContentPanel selectionPanel;
	
	public ContentPanel selectionPanelContent;
	
	public ContentPanel currentSelectionPanel;

	///
	public CheckBoxListView<DWCodesModelData> checkBoxListView;
	public FAOSTATCompareByArea compareByArea;
	public FAOSTATCompareSelectorPanel selectorPanel;
	public ComboBox<DWCodesModelData> comboBox;
	
	public DWCodesModelData selectedElementModel;
	public DWCodesModelData selectedItemModel;
	
	////
	public ListStore<DWCodesModelData> store;
	
	public ListView<DWCodesModelData> list;
	
	public List<FAOSTATCompareMultiselectionItem> multiSelectionItems;
	
	
	Html currentSelectionMessage;
	
   FlexTable currentSelectionTable = new FlexTable();   

   public Window window = new Window();
   
   
   public FAOSTATCompareMultiselection(FAOSTATCompareByArea compareByArea, ComboBox<DWCodesModelData> selectedItemFilter, ComboBox<DWCodesModelData> selectedElementFilter) {
		//super();
		this.checkBoxListView = new CheckBoxListView<DWCodesModelData>();
		this.compareByArea = compareByArea;
		this.selectorPanel = new FAOSTATCompareSelectorPanel();
		this.comboBox = new ComboBox<DWCodesModelData>();
		this.selectedElementModel =selectedElementFilter.getSelection().get(0);
		this.selectedItemModel =selectedItemFilter.getSelection().get(0);
		
		List<DWCodesModelData> item_element_list = new ArrayList<DWCodesModelData>();
		item_element_list.add(0, selectedItemModel); //item
		item_element_list.add(1, selectedElementModel); //element
		
		//initialize map with the current drop-down selections
		FAOSTATCompareMultiSelectorController.currentSelection.put(selectedElementModel.getCode() + ":" + selectedItemModel.getCode(), item_element_list);

	}
   
	/**public FAOSTATCompareMultiselection(FAOSTATCompareByArea compareByArea) {
		//super();
		this.checkBoxListView = new CheckBoxListView<DWCodesModelData>();
		this.compareByArea = compareByArea;
		this.selectorPanel = new FAOSTATCompareSelectorPanel();

		
	}***/
	
	public void build(FieldSet fieldSet, HashMap<String, String> subDomainFilterValues) {
		
		window.setHeading(FAOSTATLanguage.print().elementItemSelector());
		//window.setAutoHeight(true);
		//window.setAutoWidth(true);
		window.setModal(true);
	    window.setWidth(900);
	   window.setHeight(500);
	  // window.setStyleAttribute("backgroundColor", "white"); 
	 
	   final BorderLayout layout = new BorderLayout();   
	     window.setLayout(layout);   
	     window.setStyleAttribute("padding", "10px");   
	   
	     
	    TabPanel tabPanel = new TabPanel();   
	    tabPanel.setWidth(350);   
	    tabPanel.setAutoHeight(true);   
	    

	    TabItem byElementTab = new TabItem(FAOSTATLanguage.print().byElement());   
	    byElementTab.addListener(Events.Select, new Listener<ComponentEvent>() {   
	      public void handleEvent(ComponentEvent be) {   
	       // Window.alert("Event Tab Was Selected");   
	      }   
	    });   
	    byElementTab.addStyleName("pad-text");   
	    byElementTab.addText(FAOSTATLanguage.print().selectElementAndAssociatedItems());   
	   


	   /** TabItem eventTab = new TabItem("By Item");   
	    eventTab.addListener(Events.Select, new Listener<ComponentEvent>() {   
	      public void handleEvent(ComponentEvent be) {   
	       // Window.alert("Event Tab Was Selected");   
	      }   
	    });   
	    
	    eventTab.addStyleName("pad-text");   
	    eventTab.addText("I am tab 4's content. I also have an event listener attached.");   
	    tabPanel.add(eventTab);  ***/
	    
	   //  LayoutContainer container = new LayoutContainer();
	  //   final BorderLayout layout = new BorderLayout();   
	  //   container.setLayout(layout);   
	  //   container.setStyleAttribute("padding", "10px");   
	  //   container.setStyleAttribute("backgroundColor", "white"); 
	     


	     //container.setScrollMode(Scroll.AUTOY);   

	       
	   //  ContentPanel centerPanel = new ContentPanel();  
	    // centerPanel.setScrollMode(Scroll.AUTOX);   

	     BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);   
	     centerData.setMargins(new Margins(0));   
	     
	     currentSelectionPanel = new ContentPanel();  
	     currentSelectionPanel.setIconStyle("selectedIcon");
	     currentSelectionPanel.setHeading(FAOSTATLanguage.print().currentSelections());   
	     //currentSelectionPanel.setLayout(new RowLayout(Orientation.VERTICAL));   
	     currentSelectionPanel.add(buildCurrentSelectionToolbar());	
	     //	currentSelectionPanel.add(buildCurrentSelection());		
		
			
			
	   
	     
	     Button addSelectionsButton = new Button(FAOSTATLanguage.print().apply());
	     addSelectionsButton.addSelectionListener(FAOSTATCompareMultiSelectorController.addSelections(this, fieldSet));
	     
	     Button cancelButton = new Button(FAOSTATLanguage.print().cancel());
	     cancelButton.addSelectionListener(FAOSTATCompareMultiSelectorController.cancel(this));
	     
	     currentSelectionPanel.addButton(cancelButton);  
	     currentSelectionPanel.addButton(addSelectionsButton);  
	     currentSelectionPanel.setButtonAlign(HorizontalAlignment.RIGHT);   


	      
	     ContentPanel westPanel = new ContentPanel();  
	     BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 350);   
	     westData.setSplit(true);   
	     westData.setCollapsible(true);   
	     westData.setMargins(new Margins(0,5,0,0));   
	     
	     //selectionPanel.setHeading("Element and Items Selection");  
	     selectionPanel = new ContentPanel();
	     selectionPanel.setHeaderVisible(false);
	     selectionPanel.setBodyBorder(false);
	     selectionPanel.setLayout(new RowLayout(Orientation.VERTICAL));   
	    // panel.setSize(500, 300);   
	     //panel.setFrame(true);   
	     //panel.setCollapsible(true);   
	   
	     Text label1 = new Text(FAOSTATLanguage.print().selectElement());   
	     label1.addStyleName("pad-text");   
	     label1.setStyleAttribute("backgroundColor", "white");   
	     //label1.setBorders(true);   

	 
	    if(FAOSTATCompareMultiSelectorController.currentSelection.containsKey(selectedElementModel.getCode() + ":" + selectedItemModel.getCode()))
	    	 comboBox = selectorPanel.buildComboBox(FAOSTATLanguage.print().element(), subDomainFilterValues, FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), FAOSTATDimensionConstant.ELEMENTS_FOR_ITEM.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT.toString(), false, false, "300px", "400px", selectedElementModel);
		else
	    	 comboBox = selectorPanel.buildComboBox(FAOSTATLanguage.print().element(), subDomainFilterValues, FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), FAOSTATDimensionConstant.ELEMENTS_FOR_ITEM.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT.toString(), false, false, "300px", "400px", null);

		 
	 	//comboBox.setValue(selectedElementFilter.getSelection().get(0));
	 	
	 	if(FAOSTATCompareMultiSelectorController.currentSelection.containsKey(selectedElementModel.getCode() + ":" + selectedItemModel.getCode())) {
	 		if(comboBox.getValue() != null && comboBox.getValue().getCode().equals(selectedElementModel.getCode()))
	 			comboBox.addSelectionChangedListener(FAOSTATCompareMultiSelectorController.reBuildCheckBoxListView(this, selectorPanel, comboBox, subDomainFilterValues, selectedItemModel));	
	 		else
	 			comboBox.addSelectionChangedListener(FAOSTATCompareMultiSelectorController.reBuildCheckBoxListView(this, selectorPanel, comboBox, subDomainFilterValues, null));
	 	}		
	 	else
	 		comboBox.addSelectionChangedListener(FAOSTATCompareMultiSelectorController.reBuildCheckBoxListView(this, selectorPanel, comboBox, subDomainFilterValues, null));
	
	 	
	 	
	     Text label2 = new Text(FAOSTATLanguage.print().selectItems());   
	     label2.addStyleName("pad-text");   
	    // label2.setStyleAttribute("backgroundColor", "white");   
	     //label2.setBorders(true);   
	   
	   
	     HashMap<String, String> elementFilterValues = selectorPanel.buildFilter(selectedElementModel);
			  
	     System.out.println("##################### FAOSTATCompareMultiselection START checkBoxListView ...");
	    if(FAOSTATCompareMultiSelectorController.currentSelection.containsKey(selectedElementModel.getCode() + ":" + selectedItemModel.getCode())) {
	    	if(comboBox.getValue() != null && comboBox.getValue().getCode().equals(selectedElementModel.getCode()))
	    		checkBoxListView = selectorPanel.buildCheckBoxList(FAOSTATLanguage.print().item(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), true, false, "300px", "200px", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name(), selectedItemModel);
	 		else
	 			checkBoxListView = selectorPanel.buildCheckBoxList(FAOSTATLanguage.print().item(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), true, false, "300px", "200px", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name(), null);	
	    }
	    else
			 checkBoxListView = selectorPanel.buildCheckBoxList(FAOSTATLanguage.print().item(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), true, false, "300px", "200px", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name(), null);	
		
	     
	     
	   checkBoxListView.setId("ITEM_SELECTOR");
		//checkBoxListView.setChecked(selectedItemFilter.getSelection().get(0), true);
		checkBoxListView.addListener(Events.OnClick,  FAOSTATCompareMultiSelectorController.setCurrentSelection(null, this, checkBoxListView, comboBox));
		 System.out.println("##################### FAOSTATCompareMultiselection END");
		
		/**        new Listener<BaseEvent>() {   
	    			public void handleEvent(BaseEvent ce) {
	    				FenixAlert.info("1", "1", "1");
	    				final Html html = new Html();
	    			String message = "";
	    		    int counter = 0;
	    			for(DWCodesModelData model: checkBoxListView.getChecked()) {
	    				     message+= model.getLabel() + " " + comboBox.getSelection().get(0).getLabel() +"</br>";
	    				    
	    				     if(counter < checkBoxListView.getChecked().size()-1)
	    				    	message+= ", " ;
	    				     
	    				     counter++;	    			
	    		     }	     
	    			
	    			 System.out.println("THE MESSAGE::::: "+message);
	    			html.setHtml(message);
	    			
	    			currentSelection.add(html, new RowData(1, -1, new Margins(4)));  
	    			currentSelection.layout();
	    			
	    			//currentSelection.add(new Html(message));
	    			//currentSelection.layout();
	    			//area.getMessage().setHtml(message);
            }
	}); **/
		//checkBoxListView.setId(FAOSTATDimensionConstant.ITEMS.name().toString());  
	     
		  //after checkbox initialized
		currentSelectionPanel.add(buildCurrentSelection());		
		  
			
		selectionPanel.add(label1, new RowData(1, -1, new Margins(4)));   
		selectionPanel.add(comboBox, new RowData(1, -1, new Margins(4)));   
		//selectionPanel.add(label2, new RowData(1, 1, new Margins(0, 4, 0, 4))); 
		selectionPanel.add(label2, new RowData(1, -1, new Margins(4))); 
		selectionPanel.add(checkBoxListView, new RowData(1, -1, new Margins(4)));   
	   
	   
	  // ORIGINAL  container.add(selectionPanel, new FlowData(10));  
		//ORIGINAL byElementTab.add(container);
	     
	     byElementTab.add(selectionPanel);
	    
	     tabPanel.add(byElementTab);
	     tabPanel.setStyleAttribute("backgroundColor", "white");
	    
		
	     westPanel.add(tabPanel);
	     //centerPanel.add(currentSelectionPanel);
	     
	     
	     window.add(tabPanel, westData);   
	     window.add(currentSelectionPanel, centerData);   
	     
	     //ORIGINAL window.add(tabPanel);
	     //ORIGINAL window.add(currentSelectionPanel);
			
	    // window.add(container);
	     
		 window.show();
	     
	     
	     
		/***DWCodesModelData selectedFilterModel = selectedFilter.getSelection().get(0);
		
		panel = new ContentPanel();
		panel.setStyleAttribute("padding-left", "10px");
		selectionPanel = new ContentPanel();
		selectionPanel.setHeading("Select the element and click on the associated items below");
		
		currentSelection = new ContentPanel();
		currentSelection.setHeading("Currently Selected Items/Elements");
		currentSelection.add(new Html("test"));
		//System.out.println("SELECTED CODE BUILD: " + filter.getSelectedCodes());
		
		panel.setWidth(750);
		
		//panel.removeAll();
		//selectionPanel.removeAll();
//		selectionPanel.add(selectionPanelContent);
		
		selectionPanel.setWidth(400);
		

		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(buildButtons(window));
		panel.add(DataViewerClientUtils.addHSpace(5));
		final ComboBox<DWCodesModelData> comboBox = selectorPanel.buildComboBox("Element", subDomainFilterValues, FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), FAOSTATDimensionConstant.ELEMENTS_FOR_ITEM.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT.toString(), false, false, "350px", "400px");
		comboBox.addSelectionChangedListener(FAOSTATCompareMultiSelectorController.reBuildCheckBoxListView(this, selectorPanel, comboBox, selectedFilter, subDomainFilterValues));
		selectionPanel.add(DataViewerClientUtils.addVSpace(10));
		selectionPanel.add(comboBox);
		
		selectionPanel.add(DataViewerClientUtils.addVSpace(10));
		HashMap<String, String> elementFilterValues = selectorPanel.buildFilter(selectedFilterModel);
		final CheckBoxListView<DWCodesModelData> checkBoxListView = selectorPanel.buildCheckBoxList("Items", FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), true, false, "300px", "200px", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name());
		checkBoxListView.setId(FAOSTATDimensionConstant.ITEMS.name().toString());
		
		checkBoxListView.addListener(Events.OnClick,   
		        new Listener<BaseEvent>() {   
	    			public void handleEvent(BaseEvent ce) {
	    				
	    			String message = "Test";
	    		    int counter = 0;
	    			for(DWCodesModelData model: checkBoxListView.getChecked()) {
	    				     message+= model.getLabel() + " " + comboBox.getSelection().get(0).getLabel() +"</br>";
	    				     
	    				     if(counter < checkBoxListView.getChecked().size()-1)
	    				    	message+= ", " ;
	    				     
	    				     counter++;	    			
	    		     }	     
	    			
	    			currentSelection.add(new Html(message));
	    			currentSelection.layout();
	    			//area.getMessage().setHtml(message);
            }
	}); 
    			
		selectionPanel.add(checkBoxListView);
				  
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		h.add(selectionPanel);
		
		//buildItemsSelectionPanel(selectedFilterModel);

		
		//selectionPanel.add(comboBox);
		
		panel.add(DataViewerClientUtils.addHSpace(10));
		h.add(buildCurrentSelection());
		
		panel.add(h);
		
		window.add(panel);
		
		window.show();***/
	}

	   
	
	   
	 
				
	
	
	private HorizontalPanel buildButtons(Window window) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setSpacing(5);
		
	//	panel.add(addApply(window, visualization, filter, filterTypeMulti, code, filter.getSelectedCodes()));
		panel.add(addCancel(window));
		panel.add(addSelectAll());
		panel.add(addDeselectAll());
		
		return panel;
	}
	
	private HorizontalPanel addSelectAll() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().selectAll());
		button.setIconStyle("addIcon");
		
	//	button.addSelectionListener(FAOSTATVisualizeByDomainController.selectAllMultiselection(this));
		
		panel.add(button);
		
		return panel;
	}
	
	private HorizontalPanel addDeselectAll() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().deselectAll());
		button.setIconStyle("removeIcon");
		
		//button.addSelectionListener(FAOSTATVisualizeByDomainController.deselectAllMultiselection(this));

		panel.add(button);
		
		return panel;
	}
	
	private Button addApply(Window window, FAOSTATVisualizeByDomain visualization, String filterTypeMulti,  DWCodesModelData code, HashMap<String, String> selectedCodes) {
		Button button = new Button(FAOSTATLanguage.print().apply());
		button.setIconStyle("greenThick");
		/** TODO: do the multiselection is a more efficient way...**/
		//button.addSelectionListener(FAOSTATVisualizeByDomainController.updateMultiselectionFilter(window, visualization, filter, multiSelectionItems, filterTypeMulti.replace("_MULTI", ""), code, selectedCodes));
		return button;
	}
	
	private Button addCancel(Window window) {
		
		Button button = new Button(FAOSTATLanguage.print().cancel());
		button.setIconStyle("delete");
		
		button.addSelectionListener(FAOSTATVisualizeByDomainController.closeWindow(window));
		
		return button;
	}

	
	
	/**public List<FAOSTATCompareMultiselectionItem> getMultiSelectionItems() {
		return multiSelectionItems;
	}

	public void setMultiSelectionItems(List<FAOSTATCompareMultiselectionItem> multiSelectionItems) {
		this.multiSelectionItems = multiSelectionItems;
	}***/

	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public ContentPanel getCurrentSelectionPanel() {
		return currentSelectionPanel;
	}

	public ContentPanel getSelectionPanelContent() {
		return selectionPanelContent;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public CheckBoxListView<DWCodesModelData> getCheckBoxListById(String id){
		   Component component = selectionPanel.getItemByItemId(id);	   
		   
		   if(component==null)
			   return null;
		   else
			   return (CheckBoxListView<DWCodesModelData>) component;
	}
	
	public Boolean isRemovedCheckBoxList(String id){			
		CheckBoxListView<DWCodesModelData> list = getCheckBoxListById(id);
		System.out.println("-------- LIST IS "+list);
		if(list!=null) {
			
			selectionPanel.remove(list);	
		  return true;
		}	
		else
			return false;
			
	}
	
	private FlexTable buildCurrentSelection(){
		 
		currentSelectionTable.getElement().getStyle().setProperty("margin", "10px");   
		currentSelectionTable.setCellSpacing(5);   
		currentSelectionTable.setCellPadding(20);   
		    
		currentSelectionTable.setHTML(0, 0, "<div style='font-size: 11px; width: 10px'></span>");   
		currentSelectionTable.setHTML(0, 1, "<div style='font-size: 11px; width: 150px; padding-right:10px;'>"+FAOSTATLanguage.print().items()+":</span>");   
		currentSelectionTable.setHTML(0, 2, "<div style='font-size: 11px; width: 100px'>"+FAOSTATLanguage.print().elements()+"</span>"); 
		currentSelectionTable.setHTML(0, 3, "<div style='font-size: 11px; width: 10px'></span>");   
		
		//if(checkBoxListView.isRendered()) { //PROBLEM IS HERE, THE CHECKBOX IS NOT RENDERED!!
			FAOSTATCompareMultiSelectorController.setSelection(currentSelectionTable, this, checkBoxListView, comboBox);
		//}
		
		/** int row = 1;
		 for(String code: FAOSTATCompareMultiSelectorController.currentSelection.keySet()){
		    	//System.out.println("code: "+code + " --- ");
			   List<DWCodesModelData> item_element = FAOSTATCompareMultiSelectorController.currentSelection.get(code);
			   
			    IconButton remove = new IconButton("unCheckIcon");
				remove.setTitle("Clear this selection");
				currentSelectionTable.setWidget(row, 0, remove);   
				
				for(int column=0; column < item_element.size(); column++){
					HorizontalPanel panel = new HorizontalPanel();
					DWCodesModelData itemModel = item_element.get(column);
					remove.addSelectionListener(FAOSTATCompareMultiSelectorController.deselectItem(checkBoxListView, itemModel, code, currentSelectionTable, row));
					
					
					panel.add(new Html(itemModel.getLabel()));
					// table.setWidget(counter, 0, remove);   
					//System.out.println("label"+ i +": "+ item_element.get(i).getLabel());
					currentSelectionTable.setWidget(row, column+1, panel);   
				}
				
		    	row++;
		    	
			}**/
		 
		
		
		return currentSelectionTable;
	}
	
	
	
	private ToolBar buildCurrentSelectionToolbar(){
		 
		ToolBar toolBar = new ToolBar();   
		  
	    Button clearAll = new Button(FAOSTATLanguage.print().clearAll());   
	    clearAll.setIconStyle("deleteObj");
	    clearAll.addSelectionListener(FAOSTATCompareMultiSelectorController.clearAllSelections(this));

	    toolBar.add(clearAll);
		
		
		return toolBar;
	}
	
	
	
	public FlexTable getCurrentSelectionTable(){
		return currentSelectionTable;
	}
	
	

	
	/**private Html buildCurrentSelectionMessage(){
		
		
		currentSelectionMessage = new Html();
		currentSelectionMessage.setStyleName("selected_message");
		currentSelectionMessage.setHtml("");
	
		return currentSelectionMessage;
	}
	
	public Html getCurrentSelectionMessage(){
		return currentSelectionMessage;
	}***/
	
}
