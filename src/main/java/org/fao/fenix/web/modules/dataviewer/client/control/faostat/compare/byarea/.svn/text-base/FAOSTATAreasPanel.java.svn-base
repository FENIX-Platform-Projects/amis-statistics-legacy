package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareMultiSelectorController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.hibernate.hql.ast.tree.FromClause;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class FAOSTATAreasPanel {
		
   FAOSTATCompareSelectorPanel selectorPanel;
   HorizontalPanel h0 = new HorizontalPanel();
	
   
   ContentPanel panel;
      
	public FAOSTATAreasPanel() {
		selectorPanel = new FAOSTATCompareSelectorPanel();
       
	}
	
	public void build(final FAOSTATCompareByArea area) {	
	
	
	final CheckBoxListView<DWCodesModelData> view = selectorPanel.buildCheckBoxList(FAOSTATLanguage.print().allAreas(), FAOSTATDimensionConstant.AREAS_ALL.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString(), true, false, "300px", "200px", null);
	
		view.addListener(Events.OnClick,   
		        new Listener<BaseEvent>() {   
	    			public void handleEvent(BaseEvent ce) {
	    				
	    			String message = "&nbsp;&nbsp;&nbsp;"+FAOSTATLanguage.print().selectedAreas()+": ";
	    		    int counter = 0;
	    			for(DWCodesModelData model: view.getChecked()) {
	    				     message+= model.getLabel();
	    				     
	    				     if(counter < view.getChecked().size()-1)
	    				    	message+= ", " ;
	    				     
	    				     counter++;
	    			
	    		     }	     
	    			
	    			area.getMessage().setHtml(message);
            }
	}); 
	    
		view.setId("COMPARE_SELECTOR");
		
		area.getColumn1().add(DataViewerClientUtils.addVSpace(10));
		area.getColumn1().add(view, area.getFormData());
		
		Button b = new Button(FAOSTATLanguage.print().clearAreaSelections());
		b.setIconStyle("deleteObj");
		b.addSelectionListener(FAOSTATCompareController.clearSelections(view, area));
		area.getColumn1().add(DataViewerClientUtils.addVSpace(5));
		area.getColumn1().add(b);
		
		    
	    final VerticalPanel domains = new VerticalPanel();
	
	    final  ComboBox<DWCodesModelData> combo = selectorPanel.buildComboBox(FAOSTATLanguage.print().selectADomain(), null, FAOSTATDimensionConstant.GROUPS.name(), FAOSTATDimensionConstant.GROUPS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_GROUPS.toString(), false, false, "150px", "400px", null);
		combo.addSelectionChangedListener(getSubDomains(area, domains, combo));   
			
		area.getColumn2().add(combo, area.getFormData());
		
		   

		DWCodesModelData fromDate = new DWCodesModelData("2000", "2000");
	   final  ComboBox<DWCodesModelData> fromDateCombo = selectorPanel.buildComboBox(FAOSTATLanguage.print().selectFromYear(), null, FAOSTATDimensionConstant.TIMERANGE.name()+"_FROM", FAOSTATDimensionConstant.TIMERANGE.name()+"_FROM", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString(), false, false, "150px", "400px", fromDate);
	  // combo.addSelectionChangedListener(getSubDomains(area, domains, combo));   
	    area.getColumn3().add(fromDateCombo,area.getFormData());
	    area.getColumn3().add(DataViewerClientUtils.addVSpace(10));
	   
	   
		DWCodesModelData toDate = new DWCodesModelData("2010", "2010");
	  final  ComboBox<DWCodesModelData> toDateCombo = selectorPanel.buildComboBox(FAOSTATLanguage.print().toYear(), null,  FAOSTATDimensionConstant.TIMERANGE.name()+"_TO", FAOSTATDimensionConstant.TIMERANGE.name()+"_TO", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString(), false, false, "150px", "400px", toDate);
	    // combo.addSelectionChangedListener(getSubDomains(area, domains, combo));   
	  area.getColumn3().add(toDateCombo,area.getFormData());
	  
	  
	  //area.getColumn4().add(toDateCombo,area.getFormData());
		   
			
	  // final  ComboBox<DWCodesModelData> toDateCombo = selectorPanel.buildComboBox("To Year", null, FAOSTATDimensionConstant.TIMERANGE.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString(), false, false, "150px", "400px");
		  // combo.addSelectionChangedListener(getSubDomains(area, domains, combo));   
	  // area.getColumn4().insert(toDateCombo, 0, area.getFormData());
			
		
		
		//domains.add(combo);
		   
		
				
		/*    selectorPanel.getCheckBoxList().getSelectionModel().addListener(Events.SelectionChange,   
				        new Listener<SelectionChangedEvent<DWCodesModelData>>() {   
				  
				          public void handleEvent(SelectionChangedEvent<DWCodesModelData> be) {   
				        	  
				        	//  selectorPanel.getCheckBoxList().refresh(); 
				        	// area.getSelectionPanel().setHeading("Compare: (" + be.getSelection().size()   
				              //        + " items selected ) checked: "+selectorPanel.getCheckBoxList().getChecked().size());   
				        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ "+ selectorPanel.getCheckBoxList().getChecked().size()   
				                      + " items checked!)");
				        	
				        	//area.getMessage().setHtml(String.valueOf(selectorPanel.getCheckBoxList().getChecked().size()));
				        	
				        	//area.getSelectionPanel().layout();
				          } 
				          
				          
			}); */
	    
		//h0.add(view);
		//h0.add(domains);
		
		//h0.add(area.getForm());
		
	 //area.getSelectionPanel().add(view);
	 //area.getSelectionPanel().add(combo);
	 
		
		
	 area.getSelectionPanel().add(area.getForm());
	   
	   
	   
	   //area.getPanel().add(panel); 

	}
	
	
	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
	}
	
	public  SelectionChangedListener<DWCodesModelData> getSubDomains(final FAOSTATCompareByArea area, final VerticalPanel holder, final ComboBox<DWCodesModelData> combo) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {				
				HashMap<String, String> filter = selectorPanel.buildFilter((DWCodesModelData)combo.getSelection().get(0));
				
				area.removeColumn2ComboBoxById(FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name());
				
				final  ComboBox<DWCodesModelData> newSubDomainCombo = selectorPanel.buildComboBox(FAOSTATLanguage.print().subDomain(), filter, FAOSTATDimensionConstant.GROUPS.name(), FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_DOMAINS_FOR_GROUP.toString(), false, false, "150px", "400px", null);
				newSubDomainCombo.addSelectionChangedListener(getItems(area, holder, newSubDomainCombo));   
				//holder.insert(comboSubDomain, 1);
				

				area.getColumn2().insert(DataViewerClientUtils.addVSpace(10), 1);
				area.getColumn2().insert(newSubDomainCombo, 2, area.getFormData());
				
				area.getSelectionPanel().layout();
			}
		};
	}
	
	public  SelectionChangedListener<DWCodesModelData> getItems(final FAOSTATCompareByArea area, final VerticalPanel holder, final ComboBox<DWCodesModelData> combo) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				HashMap<String, String> subDomainFilterValues = selectorPanel.buildFilter((DWCodesModelData)combo.getSelection().get(0));
				
				Component component = area.getColumn2().getItemByItemId("ITEM_ELEMENT");
			
				if(component!=null) {
					FieldSet fs = (FieldSet) component;
					area.getColumn2().remove(fs);				
				}
				
			
				FieldSet fieldSet = new FieldSet();
				fieldSet.setHeading(FAOSTATLanguage.print().itemsElements());
				fieldSet.setCollapsible(true);
				fieldSet.setId("ITEM_ELEMENT");
					
				FormLayout layout = new FormLayout();   
			    layout.setLabelWidth(60);   
			    fieldSet.setLayout(layout); 
							
				
				final  ComboBox<DWCodesModelData> combo = selectorPanel.buildComboBox(FAOSTATLanguage.print().item(), subDomainFilterValues, FAOSTATDimensionConstant.DOMAINS_FOR_GROUP.name(), FAOSTATDimensionConstant.ITEMS.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_COMPARE.toString(), false, false, "150px", "400px", null);
				combo.addSelectionChangedListener(getElements(area, holder, combo, fieldSet, subDomainFilterValues));   
				//holder.insert(combo, 2);	
			
				fieldSet.add(combo, area.getFormData());
				
				area.getColumn2().insert(DataViewerClientUtils.addVSpace(15), 3);
				area.getColumn2().insert(fieldSet, 4);
				//area.getColumn2().insert(fieldSet, 2);
				
				
				area.getSelectionPanel().layout();
			}
		};
	}
	
	
	public SelectionChangedListener<DWCodesModelData> getElements(final FAOSTATCompareByArea area, final VerticalPanel holder, final ComboBox<DWCodesModelData> itemCombo, final FieldSet fieldSet, final HashMap<String, String> subDomainFilterValues) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				HashMap<String, String> itemFilterValues = selectorPanel.buildFilter((DWCodesModelData)itemCombo.getSelection().get(0));
				
				Component component = area.getColumn2().getItemByItemId("ITEM_ELEMENT");
				
				if(component!=null) {

					FieldSet fs = (FieldSet) component;
					Component comp =  fs.getItemByItemId(FAOSTATDimensionConstant.ELEMENTS_FOR_ITEM.name().toString());
					
					if(comp!=null) {
						ComboBox<DWCodesModelData> combo = (ComboBox<DWCodesModelData>) comp;
						fs.remove(combo);			    
					}
						
//					Component comp2 =  fs.getItemByItemId("SELECT_MORE_ITEMS");

//					if(comp!=null && comp2!=null) {
//						ComboBox<DWCodesModelData> combo = (ComboBox<DWCodesModelData>) comp;
//						Button button = (Button) comp2;
//						fs.remove(combo);
//						fs.remove(button);					    
//					}

				} else
					System.out.println("ITEM_ELEMENT FieldSet is null ");

					
							
				final  ComboBox<DWCodesModelData> combo = selectorPanel.buildComboBox(FAOSTATLanguage.print().element(), itemFilterValues, FAOSTATDimensionConstant.ITEMS.name(), FAOSTATDimensionConstant.ELEMENTS_FOR_ITEM.name(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT.toString(), false, false, "150px", "400px", null);
				//combo.addSelectionChangedListener(getElements(, holder, combo));   
				//holder.insert(combo, 3);
			
				
				 
				//Select More Items
//				 Button b = new Button(FAOSTATLanguage.print().selectMoreItemsElements());
//				 b.setId("SELECT_MORE_ITEMS");
//				 b.setWidth(50);
//				 b.setIconStyle("addIcon");
//				 b.addSelectionListener(FAOSTATCompareMultiSelectorController.openMultiSelectionWindow(fieldSet, itemCombo, combo, area, subDomainFilterValues));
				// area.getColumn2().add(DataViewerClientUtils.addVSpace(5));
		   		// area.getColumn2().add(b);
				
				
				  fieldSet.add(combo, area.getFormData());
//				  fieldSet.add(DataViewerClientUtils.addVSpace(5));
//				  fieldSet.add(b, area.getFormData());
		   		
				//area.getColumn2().insert(combo, 3, area.getFormData());
				
				
			
				
				  area.getSelectionPanel().layout();
			}
		};
	}

	
	
	
	
	
	
	
}
