package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class FAOSTATVisualizeFilter {

	ComboBox<DWCodesModelData> combo;
	ListStore<DWCodesModelData> store;
	
	HashMap<String, String> selectedCodes ;

	FAOSTATVisualizeMultiselectionFilter multiselection;
	
	String title;
	
	org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter filterVO;
	
	public FAOSTATVisualizeFilter() {
		combo = new ComboBox<DWCodesModelData>();
		store = new ListStore<DWCodesModelData>();
		
		selectedCodes = new HashMap<String, String>();
		
		multiselection = new FAOSTATVisualizeMultiselectionFilter();
		
		title = new String();
	}
	

	// TODO: Probably the title should be in the filterVO
	public HorizontalPanel buildStandardFilter(FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter filterVO, DWCodesModelData code, String title) {	
		this.filterVO = filterVO;
		this.title = title;

		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());  
		
		// seeting the width
		if ( filterVO.getWidth() != null ) {
			System.out.println("setting width: " + filterVO.getWidth());
			combo.setWidth(filterVO.getWidth());
		}
		else 
			combo.setWidth(100);
		
		if ( filterVO.getHeight() != null ) {
			System.out.println("setting height: " + filterVO.getHeight());
			combo.setHeight(filterVO.getHeight());
		}

		if ( filterVO.getUseCodingSystem() ) {
			// build dynamic drop down
			return buildWithCodingSystem(visualization, code);
		}
		else {
			// build with fixed drop down
			return buildWithFixedValues(visualization, code);
		}
	}

	private HorizontalPanel buildWithCodingSystem(FAOSTATVisualize visualization, DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);

		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " +  FAOSTATLanguage.print().getString(title) +" </div>"));
//		p.add(new Html("<div class='visualize_filters_combo'> " + BabelFish.print().getString(title) +" </div>"));
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		

		
		if ( filterVO.getIsMultiSelection() )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
			
			/** TODO: do the multiselection is a more efficient way... (i.e. not use pass parameter _MULTI)**/
			icon.addSelectionListener(FAOSTATVisualizeByDomainController.multiselectionFilter(visualization, this, filterVO.getFilterType() +"_MULTI", code));
			
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		combo.enableEvents(false);
		
		combo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateFilter(visualization, this, combo, filterVO.getFilterType(), code, selectedCodes));
		
		
		LinkedHashMap<String, String> domainCodes = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> filterCodes = new LinkedHashMap<String, String>();
		if ( !filterVO.getCodes().isEmpty() ) {
			domainCodes.put(code.getCode(), code.getLabel());
			for(DWCodesModelData c : filterVO.getCodes()) {
//				System.out.println("code " + c.getCode() + " | " + c.getLabel() + " | " + c.getDomain());
				filterCodes.put(c.getCode(), c.getCode());
				domainCodes.put(c.getDomain(), c.getDomain());
			}
		}
		else if ( code != null ){
			domainCodes.put(code.getCode(), code.getLabel());
		} 
		

		if ( !filterVO.getDomains().isEmpty() ) {
			domainCodes.putAll(filterVO.getDomains());
		}
		
		
		FAOSTATVisualizeController.loadCodingSystem(combo, store, filterVO.getFilterType(), domainCodes, filterVO.getDefaultCodes(), selectedCodes, filterCodes, filterVO.getIsMultiSelection());

		return p;
	}
	
	
	private HorizontalPanel buildWithFixedValues(FAOSTATVisualize visualization, DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);

		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'> " +  FAOSTATLanguage.print().getString(title) +" </div>"));
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(combo);
		p.setHeight(25);
		
		if ( filterVO.getIsMultiSelection() )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
			
			/** TODO: do the multiselection is a more efficient way... (i.e. not use pass parameter _MULTI)**/
//			icon.addSelectionListener(FAOSTATVisualizeByDomainController.multiselectionFilter(visualization, this, filterVO.getFilterType() +"_MULTI", code));
			
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		combo.enableEvents(false);
		
		combo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateFilter(visualization, this, combo, filterVO.getFilterType(), code, selectedCodes));
		
		loadFixedValues();
		
		return p;
	}
	
	// TODO: Call the server to retrieve the codes and the labels (for now they are taken from the XML)
	private void loadFixedValues() {
		
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	
		/** TODO: language... **/
		DWCodesModelData multiselection = null;
		if ( filterVO.getIsMultiSelection() ) { 
			multiselection = new DWCodesModelData("MULTI", FAOSTATLanguage.print().multiselection());			
			store.add(multiselection);
		}

		System.out.println("filterVO.getCodes(): " + filterVO.getCodes().size());
		for(DWCodesModelData code : filterVO.getCodes()) {
			System.out.println("code: " + code.getCode() + " |  " + code.getLabel());

			store.add(code);
			map.put(code.getCode(), code.getLabel());
				
				// defualt codes TODO: Optimze...
				if ( filterVO.getDefaultCodes() != null && !filterVO.getDefaultCodes().isEmpty() ) {
					if ( filterVO.getDefaultCodes().size() > 1 ) {
						// set MULTI (multiselection) as default code
						combo.setValue(multiselection);
					}
					else {
						 if (code.getCode().equals(filterVO.getDefaultCodes().get(0).getCode())) {
							 combo.setValue(code);
						}
					}
				}
				else {
					combo.setValue(store.getAt(0));
					selectedCodes.put(store.getAt(0).getCode(), store.getAt(0).getLabel());
				}
			}
			
			if ( filterVO.getDefaultCodes() != null && !filterVO.getDefaultCodes().isEmpty() ) {
				for(DWCodesModelData code : filterVO.getDefaultCodes() ) {
					selectedCodes.put(code.getCode(), map.get(code.getCode()));
				}
			}
			
			System.out.println("-----> SELECTED CODES: " + selectedCodes);

			combo.enableEvents(true);
		
	}
	
	public ComboBox<DWCodesModelData> getCombo() {
		return combo;
	}

	public ListStore<DWCodesModelData> getStore() {
		return store;
	}

	public HashMap<String, String> getSelectedCodes() {
		return selectedCodes;
	}

	public FAOSTATVisualizeMultiselectionFilter getMultiselection() {
		return multiselection;
	}

	
	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;

	public String getTitle() {
		return title;
	}  
	
	
	
	
	
}
