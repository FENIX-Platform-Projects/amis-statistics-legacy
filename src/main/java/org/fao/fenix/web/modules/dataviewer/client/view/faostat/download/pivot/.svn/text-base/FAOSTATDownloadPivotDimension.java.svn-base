package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.dnd.DragSource;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class FAOSTATDownloadPivotDimension {
	
	ContentPanel panel;
	
	String columnCode;
	
	// this is used for the switch in FAOSTATController
	FAOSTATDimensionConstant filterType;
	
	FAOSTATDownloadPivotDimension dimension;
	
	ComboBox<DWCodesModelData> combo;
	
	ListStore<DWCodesModelData> store;
	
	public FAOSTATDownloadPivotDimension() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
	}
	
	public ContentPanel build(String label, String columnCode, FAOSTATDimensionConstant pivotFilterType){
		panel.removeAll();
		
		panel.setStyleName("pivot_selector_box");
		
		panel.add(buildListBox());
		
		return panel;
	}
	
	private HorizontalPanel buildListBox() {
		HorizontalPanel panel = new HorizontalPanel();
		
		combo = new ComboBox<DWCodesModelData>();
		store = new ListStore<DWCodesModelData>();
		
		combo.setStore(store);
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		combo.setStore(store);
		combo.setWidth(100);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setTemplate(getTemplate());  
		
		
		return panel;
	}
	
	public ContentPanel buildDD(String label, String columnCode, FAOSTATDimensionConstant pivotFilterType){
		panel.removeAll();
		
		panel.setStyleName("pivot_selector_box");
		
		this.columnCode = columnCode;
		
		this.filterType = pivotFilterType;
		
		final Html html = new Html("<div class='pivot_selector_item'>" + label + "</div>");
		
		panel.add(html);
		
		dimension = this;
		
		
		DragSource source = new DragSource(panel) {
	        @Override  
	        protected void onDragStart(DNDEvent event) {  
	          // by default drag is allowed  
	          event.setData(dimension);  
	          event.getStatus().update(El.fly(html.getElement()).cloneNode(true));  
	        }  
	      };  
	      
	    source.setGroup("pivot");  
		return panel;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public FAOSTATDimensionConstant getFilterType() {
		return filterType;
	}
	

	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;

	

}
