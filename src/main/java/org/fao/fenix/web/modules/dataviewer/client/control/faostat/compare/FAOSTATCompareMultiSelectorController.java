package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareByArea;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.utils.FAOSTATCompareMultiselection;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.google.gwt.user.client.ui.FlexTable;


public class FAOSTATCompareMultiSelectorController  {
	
	public final static HashMap<String,  List<DWCodesModelData>> currentSelection = new LinkedHashMap<String, List<DWCodesModelData>>();
	
//	public static SelectionListener<ButtonEvent> openMultiSelectionWindow(final FieldSet fieldSet, final ComboBox<DWCodesModelData> itemCombo, final ComboBox<DWCodesModelData> elementCombo, final FAOSTATCompareByArea area, final HashMap<String, String> subDomainFilterValue) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				
//				//ComboBox<DWCodesModelData> domainscombo = area.getColumn2ComboBoxById(FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name());
//				
//				//if(domainscombo!=null) {
//						
//				System.out.println("%%%%%%%%%%%%%%%%%%%%5 subDomainFilterValue = "+subDomainFilterValue);
//					new FAOSTATCompareMultiselection(area,itemCombo,elementCombo).build(fieldSet, subDomainFilterValue);
//					
//					/*** FieldSet fieldSet = new FieldSet();   
//					 fieldSet.setHeading("Select Item to Add");   
//					 fieldSet.setCollapsible(true);   
//
//					 FormLayout layout = new FormLayout();   
//					 layout.setLabelWidth(75);   
//					 fieldSet.setLayout(layout);   
//					  
//					final  ComboBox<DWCodesModelData> newcombo = selectorPanel.buildComboBox("Item", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), false, false, "150px", "400px");
//						
//					// fieldSet.add(combo);
//					 
//					int index = area.getColumn2().indexOf(combo);
//					
//					 area.getColumn2().insert(newcombo, index+1, area.getFormData());
//					 
//					 area.getSelectionPanel().layout();***/
//					
//					//final  ComboBox<DWCodesModelData> combo = selectorPanel.buildComboBox("Item", subDomainFilterValues, FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString(), false, false, "150px", "400px");
//					//combo.addSelectionChangedListener(getElements(area, holder, combo));   
//					
//				}
//				
//			//}
//		};
//	}
	
	public static SelectionChangedListener<DWCodesModelData> reBuildCheckBoxListView(final FAOSTATCompareMultiselection compareMultiselection, final FAOSTATCompareSelectorPanel selectorPanel, final ComboBox<DWCodesModelData> comboBox, final  HashMap<String, String> subDomainFilterValues, final DWCodesModelData modelToSetAsSelected) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				  HashMap<String, String> elementFilterValues = selectorPanel.buildFilter(comboBox.getSelection().get(0));
	              
				
				  if(compareMultiselection.isRemovedCheckBoxList("ITEM_SELECTOR")) {
					  CheckBoxListView<DWCodesModelData> checkBoxListView = selectorPanel.buildCheckBoxList("Items", FAOSTATDimensionConstant.ITEMS.name().toString(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT.toString(), true, false, "300px", "200px", subDomainFilterValues,  FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), elementFilterValues,  FAOSTATDimensionConstant.ELEMENTS.name(), modelToSetAsSelected);
					  checkBoxListView.setId("ITEM_SELECTOR");
				  checkBoxListView.addListener(Events.OnClick, setCurrentSelection(compareMultiselection.getCurrentSelectionTable(), compareMultiselection, checkBoxListView, comboBox));
					
				  
					 // checkBoxListView.setItemSelector("div.thumb-wrap");   
  
				 /** checkBoxListView.getSelectionModel().addListener(Events.SelectionChange,   
					        new Listener<SelectionChangedEvent<BeanModel>>() {   
					  
					          public void handleEvent(SelectionChangedEvent<BeanModel> be) {   
					        	  compareMultiselection.currentSelection.setHeading("CheckBox ListView (" + be.getSelection().size()   
					                + " items selected)");   
					        	  
					     
					          }   
					  
					        });  **/
						  
						  /* new Listener<BaseEvent>() {   
					   public void handleEvent(BaseEvent ce) {

						   String message = "&nbsp;&nbsp;&nbsp;Selected areas: ";
						   int counter = 0;
						   if(compareMultiselection.checkBoxListView.isRendered()){
						   for(DWCodesModelData model: compareMultiselection.checkBoxListView.getChecked()) {
							   message+= model.getLabel();

							   if(counter < compareMultiselection.checkBoxListView.getChecked().size()-1)
								   message+= ", " ;

							   counter++;

						   }	     
						   }
						   //area.getMessage().setHtml(message);
					   }
				   }); */
				  
				  compareMultiselection.selectionPanel.add(checkBoxListView, new RowData(1, -1, new Margins(4)));   
				  compareMultiselection.selectionPanel.layout();
				  } else 
					  System.out.println("DID NOT REMOVE THE CHECKLIST!!!");
				 // compareMultiselection.selectionPanel.add(checkBoxListView);
			}
		};
	}
	
	
	public static Listener<BaseEvent> setCurrentSelection(final FlexTable table, final FAOSTATCompareMultiselection compareMultiselection, final CheckBoxListView<DWCodesModelData> checkBoxListView, final ComboBox<DWCodesModelData> comboBox) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				
				setSelection(table, compareMultiselection, checkBoxListView, comboBox);	
    		    
    		    
			}
		};
	}
	
	
	
	public static void setSelection(final FlexTable table, final FAOSTATCompareMultiselection compareMultiselection, final CheckBoxListView<DWCodesModelData> checkBoxListView, final ComboBox<DWCodesModelData> comboBox) {
				
				String message = "";
				
				System.out.println("****** START  setCurrentSelection ..... size = " + FAOSTATCompareMultiSelectorController.currentSelection.size());
				
				    
				//String htmlValue = currentMessage.getHtml().toString();
				//String selectedElement = comboBox.getSelection().get(0).getLabel();
				
				//System.out.println("htmlValue "+htmlValue + " selected element: "+selectedElement);
				
				//if(!htmlValue.contains(selectedElement)){
				//	System.out.println("CONTAINS!!");
				//	 message = currentMessage.getHtml().toString();
				//}
				//if(currentMessage.getHtml()!=null &&  !currentMessage.getHtml().toString().contains(comboBox.getSelection().get(0).getLabel()));
    		  	  // message = currentMessage.getHtml().toString();
				       
				
				//list 1 = current checked selection
				// list 2 = full store
				
				//map = all checked selections
				
				
				if(checkBoxListView.isRendered()){
								

					if(checkBoxListView.getChecked().size() > 0){
						
						System.out.println("CHECKED ITEMS SIZE "+ checkBoxListView.getChecked().size());
						
						//List<String> itemCodes = new ArrayList<String>();
						Set<String> itemCodes = new HashSet<String>();


						
					    for(String code: currentSelection.keySet()){
					    	//System.out.println("code: "+code + " --- ");
						  if(code.contains(comboBox.getSelection().get(0).getCode())){
							  System.out.println("ELEMENT CODE ITEMS "+currentSelection.get(code).get(0).getCode() + " : "+currentSelection.get(code).get(0).getLabel());
							  itemCodes.add(currentSelection.get(code).get(0).getCode());
						  }
						  
						}
						
						
						for(DWCodesModelData checked: checkBoxListView.getChecked()) {
							List<DWCodesModelData> item_element_list = new ArrayList<DWCodesModelData>();
							
							String element_item = comboBox.getSelection().get(0).getCode() + ":" + checked.getCode();	
							item_element_list.add(0, checked); //item
							item_element_list.add(1, comboBox.getSelection().get(0)); //element
							
							
							//for(String itemCode: itemCodes){	
								System.out.println(">>> CHECKED CODE "+checked.getCode());
								if(itemCodes.contains(checked.getCode())) {
									System.out.println(checked.getCode()+ " IS IN LIST AND REMOVED FROM LIST");								
									itemCodes.remove(checked.getCode());
								} 
								
								
								
									
						//	}
						    	
							currentSelection.put(comboBox.getSelection().get(0).getCode() + ":" + checked.getCode(), item_element_list);
							System.out.println("CHECKED ADDED CODE "+checked.getCode() + " : "+checked.getLabel());
						
							
							//if(model.equals(checked))
							
							
									
							//else
							//	if(currentSelection.containsKey(element_item))
							//		currentSelection.remove(element_item);
									
						}	
						
						for(String itemCode: itemCodes) {
						    currentSelection.remove(comboBox.getSelection().get(0).getCode() + ":" +itemCode);
							System.out.println("PREVIOUSLY CHECKED REMOVED "+comboBox.getSelection().get(0).getCode() + ":" +itemCode);	
						}
						
						/*for(DWCodesModelData model: checkBoxListView.getStore().getModels()) {
							String element_item = comboBox.getSelection().get(0).getCode() + ":" + model.getCode();	
							
							for(DWCodesModelData checked: checkBoxListView.getChecked()) {
								System.out.println("CHECKED "+checked.getLabel());
								
								if(model.equals(checked))
									currentSelection.put(comboBox.getSelection().get(0).getCode() + ":" + model.getCode(), comboBox.getSelection().get(0).getLabel() + ": " + model.getLabel());
								
								else
									if(currentSelection.containsKey(element_item))
										currentSelection.remove(element_item);
										
							}	
						}*/
					}else {
						for(DWCodesModelData model: checkBoxListView.getStore().getModels()) {
							String element_item = comboBox.getSelection().get(0).getCode() + ":" + model.getCode();	
							
							if(currentSelection.containsKey(element_item)){
								System.out.println("PREVIOUSLY_CHECKED ....."+model.getLabel());
								currentSelection.remove(element_item);
							}
						}
					
					
						
					
					}
					
					
					
							
					/***if(checkBoxListView.getChecked().size() > 0){
						
						for(DWCodesModelData model: checkBoxListView.getStore().getModels()) {
							
							
							String element_item = comboBox.getSelection().get(0).getCode() + ":" + model.getCode();	
							
							
							for(DWCodesModelData checked: checkBoxListView.getChecked()) {
								System.out.println("CHECKED "+checked.getLabel());
								
								if(model.equals(checked))
									currentSelection.remove(element_item);
										
								
								
							}	
							if(currentSelection.containsKey(element_item) && ){
								currentSelection.remove(element_item);
							}
						}
						
						
						for(DWCodesModelData model: checkBoxListView.getChecked()) {
							System.out.println("CHECKED "+model.getLabel());
							
							
							
							
							currentSelection.put(comboBox.getSelection().get(0).getCode() + ":" + model.getCode(), comboBox.getSelection().get(0).getLabel() + ": " + model.getLabel());
							//message+= comboBox.getSelection().get(0).getLabel() + ": " +model.getLabel() + "</br>"; 			
						}	
					}
					else {
						for(DWCodesModelData model: checkBoxListView.getStore().getModels()) {
							String element_item = comboBox.getSelection().get(0).getCode() + ":" + model.getCode();	
							if(currentSelection.containsKey(element_item)){
								currentSelection.remove(element_item);
							}
						}
					}***/

    		    } 
    		    
				
				 int row = 1;
				 table.clear();
				 table.setCellPadding(5);
				 table.setCellSpacing(5);
				
				 
				    for(String code: currentSelection.keySet()){
				    	//System.out.println("code: "+code + " --- ");
					   List<DWCodesModelData> item_element = currentSelection.get(code);
					   
					    IconButton selectedBullet = new IconButton("selectedBulletIcon");
					  	table.setWidget(row, 0, selectedBullet);   
						
						IconButton remove = new IconButton("unCheckIcon");
					    remove.setTitle("Clear this selection");
							
						for(int column=0; column < item_element.size(); column++){
							HorizontalPanel panel = new HorizontalPanel();
							DWCodesModelData itemModel = item_element.get(column);
							remove.addSelectionListener(FAOSTATCompareMultiSelectorController.deselectItem(checkBoxListView, itemModel, code, table, row));
							
							
							panel.add(new Html(itemModel.getLabel()));
							// table.setWidget(counter, 0, remove);   
							//System.out.println("label"+ i +": "+ item_element.get(i).getLabel());
							 table.setWidget(row, column+1, panel);   
						}
						
					   table.setWidget(row, 3, remove);   
						
				    	row++;
				    	
					}
				    
				    
					System.out.println("****** table ....."+table);
					

				   /*** FlexTable table = new FlexTable();   
				    table.getElement().getStyle().setProperty("margin", "10px");   
				    table.setCellSpacing(8);   
				    table.setCellPadding(4);   
				    
				    table.setHTML(0, 0, "<div style='font-size: 12px; width: 100px'>Item:</span>");   
				    table.setHTML(0, 1, "<div style='font-size: 12px; width: 100px'>Element:</span>");   
				  
				    int counter = 1;
				    for(String code: currentSelection.keySet()){
	    				
					   List<DWCodesModelData> item_element = currentSelection.get(code);
	    				for(int i=0; i < item_element.size(); i ++){
	    					 table.setWidget(counter, i, new Html(item_element.get(i).getLabel()));   
	    				}
	    				
				    	counter++;
				    	
	    			}***/
				    
				  

    		  // String message = "";
    			/**for(String code: currentSelection.keySet()){
    				 message += currentSelection.get(code) +"<br/>";
    			}
    		    compareMultiselection.getCurrentSelectionMessage().setHtml(message);**/
				//compareMultiselection.getCurrentSelection().setTable(message);
					
					
					
			System.out.println("****** END  setCurrentSelection .....");
    		  	compareMultiselection.currentSelectionPanel.layout();		
    		    
    		    

		
	}
	
	
	public static SelectionListener<IconButtonEvent> deselectItem(final CheckBoxListView<DWCodesModelData> checkBoxListView, final DWCodesModelData item,  final String key, final FlexTable  table, final int row) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				
				System.out.println("checked_size BEFORE: "+ checkBoxListView.getChecked().size());
				
				for(DWCodesModelData checked_before: checkBoxListView.getChecked()) {
					System.out.println("checked_before: "+ checked_before.getLabel() + "; ");
				}
				
				DWCodesModelData foundModel = checkBoxListView.getStore().findModel("code", item.getCode());
				if(foundModel!=null) {
					checkBoxListView.setChecked(foundModel, false);
				}	
				/*if(checkBoxListView.getStore().findModel(item)!=null)
					System.out.println(item.getLabel()+" IS IN STORE");
				else
					System.out.println(item.getLabel()+ " IS not in STORE");
				*/
				
				System.out.println("ITEM LABEL BEFORE: "+ item.getLabel());
				
				//checkBoxListView.setChecked(item, false);
				//checkBoxListView.refresh();
				
				
				if(currentSelection.containsKey(key)) {
					currentSelection.remove(key);
					table.removeRow(row);
				}
				
				System.out.println("checked_size AFTER: "+ checkBoxListView.getChecked().size());
				for(DWCodesModelData checked_after: checkBoxListView.getChecked()) {
					System.out.println("checked_after: "+ checked_after.getLabel() + "; ");
				}
				
				
				
				//compareMultiselection.getCurrentSelectionPanel().layout();
				//checkBoxListView.refresh();
				
			}
		};
	}
	
	
	public static void selectItem(final CheckBoxListView<DWCodesModelData> checkBoxListView, final DWCodesModelData item,  final String key, final FlexTable  table, final int row) {
		       checkBoxListView.setChecked(item, true);
		
    }
		
	
	
	
	public static SelectionListener<ButtonEvent> clearAllSelections(final FAOSTATCompareMultiselection compareMultiselection) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				CheckBoxListView<DWCodesModelData> view = compareMultiselection.getCheckBoxListById("ITEM_SELECTOR");
				view.getSelectionModel().deselectAll();
				view.refresh();
				
				currentSelection.clear();
				compareMultiselection.getCurrentSelectionTable().clear();

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addSelections(final FAOSTATCompareMultiselection compareMultiselection, final FieldSet filterSet) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
				
				
				HashMap<String, String> items = new HashMap<String, String>();
				HashMap<String, String> elements = new HashMap<String, String>();
				
	            Component component = filterSet.getItemByItemId("MULTISELECTION_LABEL");

				if(component!=null) {
					VerticalPanel label = (VerticalPanel) component;
					label.removeAll();
					filterSet.remove(label);				
				}
				
				String multiselection = "<font size='1px'>";
				
				for(String code: currentSelection.keySet()){
					System.out.println("code: "+code + " --- ");
					String[] element_item = code.split(":");
					String element = element_item[0];
					String item = element_item[1];
					List<DWCodesModelData> item_element = currentSelection.get(code);

					System.out.println("element: "+element + " item "+item);
					items.put(item, item_element.get(0).getLabel());
					elements.put(element, item_element.get(1).getLabel());
					
					multiselection += " "+ item_element.get(0).getLabel() +": " + item_element.get(1).getLabel() +"<br/><br/>";
					
					/**for(int column=0; column < item_element.size(); column++){
						DWCodesModelData itemModel = item_element.get(column);
						multiselection += " "+ itemModel.getLabel() +" ";
						
					}**/
					
					
				}
				
				multiselection += "</font>";
				
				if(!currentSelection.isEmpty()){
					VerticalPanel panel = new VerticalPanel();
					panel.setId("MULTISELECTION_LABEL");
					panel.setStyleAttribute("backgroundColor", "#F0F0F0");
					panel.setSpacing(5);
					
					panel.add(new Html("<font size='1px'><b>"+FAOSTATLanguage.print().currentSelections()+":</b></font>"));
					
					Html label = new Html(multiselection);
					panel.add(label);
					
					filterSet.add(panel);  
				}
				
				
				qvo.setItems(items);
				qvo.setElements(elements);
				
				
				System.out.println("ITEMS:::: "+qvo.getItems());
				System.out.println("ELEMENTS:::: "+qvo.getElements());
				
				filterSet.setData("UPDATED_QVO", qvo);
				
				
				compareMultiselection.compareByArea.getSelectionPanel().layout();
				//compareMultiselection.compareByArea.getAreaPanel().layout();
				
				compareMultiselection.window.close();

			}
		};
	}
	

	public static SelectionListener<ButtonEvent> cancel(final FAOSTATCompareMultiselection compareMultiselection) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent ce) {
				
				CheckBoxListView<DWCodesModelData> view = compareMultiselection.getCheckBoxListById("ITEM_SELECTOR");
				view.getSelectionModel().deselectAll();
				view.refresh();			
				currentSelection.clear();				
				compareMultiselection.getCurrentSelectionTable().clear();
							
				compareMultiselection.window.close();

			}
		};
	}
	
}
