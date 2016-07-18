package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.selectors;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.selectors.FAOSTATSelectorController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class FAOSTATSelectorPanel {
	
	
	ContentPanel panel;
	
	protected String title;

	protected String selectionType;

	public FAOSTATSelectorPanel() {	
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
	
	}
	
	
	public CheckBoxListView<DWCodesModelData> initializeCheckBoxList(DWFAOSTATQueryVO qvo, String width, String height){
		CheckBoxListView<DWCodesModelData> checkBoxList = new CheckBoxListView<DWCodesModelData>();
		checkBoxList.setDisplayProperty("label");
		checkBoxList.setWidth(width);
		checkBoxList.setHeight(height);
		checkBoxList.setData("QVO", qvo);
		
		return checkBoxList;
	}
	
	public ComboBox<DWCodesModelData> initializeComboBox(DWFAOSTATQueryVO qvo, String width, String height){
		ComboBox<DWCodesModelData> combo = new ComboBox<DWCodesModelData>();
		combo = new ComboBox<DWCodesModelData>();
		combo.setDisplayField("label");
		combo.setEmptyText(FAOSTATLanguage.print().pleaseSelect());
		combo.setWidth(width);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setData("QVO", qvo);
		//combo.setTemplate(getTemplate());  
		//combo.enableEvents(false);
		
		return combo;
	}
	
	
	
	

  
   public CheckBoxListView<DWCodesModelData> buildCheckBoxList(DWFAOSTATQueryVO qvo, String boxId, Boolean addSelectAll, String width, String height, DWCodesModelData modelToSetAsSelected) {	
	   CheckBoxListView<DWCodesModelData> view = initializeCheckBoxList(qvo, width, height);
	    ListStore<DWCodesModelData> store = new ListStore<DWCodesModelData>();
	    view.setStore(store);
	 	FAOSTATSelectorController.getSelectors(this, view, store, qvo, qvo.getTypeOfOutput(), addSelectAll, modelToSetAsSelected);

//	 	FAOSTATSelectorController.getSelectors(this, view, store, qvo, qvo.getResourceType(), addSelectAll, modelToSetAsSelected);
		
		return view;
	}
   
   public ComboBox<DWCodesModelData> buildComboBox(DWFAOSTATQueryVO qvo, String boxId, Boolean addSelectAll, String width, String height, DWCodesModelData modelToSetAsSelected) {	
	    ComboBox<DWCodesModelData> combo = initializeComboBox(qvo, width, height);
	    combo.setFieldLabel(qvo.getTitle());
	    ListStore<DWCodesModelData> store = new ListStore<DWCodesModelData>();
	    combo.setStore(store);
	    combo.setId(boxId);
	    combo.setEmptyText(FAOSTATLanguage.print().pleaseSelect());
		FAOSTATSelectorController.getSelectors(this, combo, store, qvo, qvo.getTypeOfOutput(), addSelectAll, modelToSetAsSelected);

//		FAOSTATSelectorController.getSelectors(this, combo, store, qvo, qvo.getResourceType(), addSelectAll, modelToSetAsSelected);
		
		
		return combo;
	}



	

	public HorizontalPanel addTitle(String title) {
		HorizontalPanel p = new HorizontalPanel();
		p.addStyleName("download_tab");
		Html html = new Html();
		html.setHtml("<div class='download_tab_title'>" + title + "</div>");
		p.add(html);
		return p;
	}
	
	
	public ContentPanel getPanel() {
		return panel;
	}

	public String getTitle() {
		return title;
	}

	public String getSelectionType() {
		return selectionType;
	}
	
	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;
	
}
