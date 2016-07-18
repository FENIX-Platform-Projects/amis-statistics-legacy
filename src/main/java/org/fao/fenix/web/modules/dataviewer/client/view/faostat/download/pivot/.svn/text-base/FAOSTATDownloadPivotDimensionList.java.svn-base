package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.dnd.DragSource;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.ListBox;

public class FAOSTATDownloadPivotDimensionList {
	
	ContentPanel panel;
	
	String currentCode;
	
	ComboBox<DWCodesModelData> combo;
	
	ListStore<DWCodesModelData> store;
	
	ListBox listBox;
	
	String comboWidth = "80px";
	
	public FAOSTATDownloadPivotDimensionList() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
	}
	
	public ContentPanel build(String title, FAOSTATDimensionConstant pivotFilterType){
		panel.removeAll();
		
		HorizontalPanel p = new HorizontalPanel();
		
		p.setVerticalAlign(VerticalAlignment.MIDDLE);

		this.currentCode = pivotFilterType.toString();
		
		p.add(buildTitle(title));	
		p.add(DataViewerClientUtils.addHSpace(5));	
		p.add(buildListBox(pivotFilterType.toString()));
		
		
		
		panel.add(p);
		
		return panel;
	}
	
	private HorizontalPanel buildTitle(String title){
		HorizontalPanel panel = new HorizontalPanel();
		
		Html html = new Html("<div class='download_option_text'>" + title + "</div>");

		html.setWidth(60);
		panel.add(html);
		
		return panel;
	}
	
	private HorizontalPanel buildListBox(String pivotFilterType) {
		HorizontalPanel panel = new HorizontalPanel();
		
		combo = new ComboBox<DWCodesModelData>();
		store = new ListStore<DWCodesModelData>();
		
		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setStore(store);
		combo.setWidth(comboWidth);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());  
		
		buildDefaultValues(pivotFilterType);
		
		panel.add(combo);
		
		return panel;
	}
	
	private void buildDefaultValues(String pivotFilterType) {
		store.add(new DWCodesModelData(FAOSTATDimensionConstant.COUNTRIES.toString(), FAOSTATLanguage.print().countries()));
		store.add(new DWCodesModelData(FAOSTATDimensionConstant.ITEMS.toString(), FAOSTATLanguage.print().items()));
//		store.add(new DWCodesModelData(FAOSTATDimensionConstant.ELEMENTS_LIST.toString(), FAOSTATLanguage.print().elements()));
		store.add(new DWCodesModelData(FAOSTATDimensionConstant.ELEMENTS.toString(), FAOSTATLanguage.print().elements()));
		store.add(new DWCodesModelData(FAOSTATDimensionConstant.YEARS.toString(), FAOSTATLanguage.print().years()));
		
		for (int i = 0; i < store.getCount() ; i++ ) {
			if ( store.getAt(i).getCode().equals(pivotFilterType)) {
				combo.setValue(store.getAt(i));
			}
		}
	}

	public void removeSelectedItems(List<String> selectedItems, String newItemSelected) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		
		// TODO: keep track of the selected one, if the selected one == newItemSelected
		// then 
		
		for (int i = 0; i < store.getCount() ; i++ ) {
			for(String item : selectedItems) {
				if ( store.getAt(i).getCode().equals(item)) {
					codes.add(store.getAt(i));
				}
			}
		}
		
		// TODO: remove items
		System.out.println("CODES: " + codes.size());
		
		
		// TODO: select item
		for (int i = 0; i < store.getCount() ; i++ ) {
			if ( store.getAt(i).getCode().equals(newItemSelected)) {
				combo.setValue(store.getAt(i));
			}
		}
	}
	

	

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}

	public String getCurrentCode() {
		return currentCode;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	

	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;

	public ComboBox<DWCodesModelData> getCombo() {
		return combo;
	}

	public ListStore<DWCodesModelData> getStore() {
		return store;
	}

	

}
