package org.fao.fenix.web.modules.amis.client.view.compare.utils;

import org.fao.fenix.web.modules.amis.client.control.compare.AMISSelectorController;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompare;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class AMISSelectorPanel {


	ContentPanel panel;

	protected String title;

	protected String selectionType;

	public AMISSelectorPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);


	}


	public CheckBoxListView<AMISCodesModelData> initializeCheckBoxList(AMISQueryVO qvo, String width, String height, String id){
		//CheckBoxListView<AMISCodesModelData> checkBoxList = new CheckBoxListView<AMISCodesModelData>();
		CheckBoxListView<AMISCodesModelData> checkBoxList = new CheckBoxListView<AMISCodesModelData>() {   
			@Override  
			protected AMISCodesModelData prepareData(AMISCodesModelData model) {   
				String is_aggregated = model.get("is_aggregated");   
				String label = model.get("label");  
				String unit_databases = model.get("unit_databases");          

				if(is_aggregated == null || is_aggregated.equalsIgnoreCase("FALSE"))
					if(unit_databases!=null)
						model.set("label",label+" "+ unit_databases);
					else
						model.set("label",label);   
				else if(is_aggregated.equalsIgnoreCase("TRUE"))
					model.set("label","<b>"+label+"<b> " + unit_databases);   


				return model;   
			}   

		};   
		
		checkBoxList.setDisplayProperty("label");
		checkBoxList.setWidth(width);
		checkBoxList.setHeight(height);
		checkBoxList.setData("QVO", qvo);
		checkBoxList.setBorders(true);
		checkBoxList.setId(id);
		//checkBoxList.setTemplate(getTemplate());   
			
		return checkBoxList;
	}

	public ComboBox<AMISCodesModelData> initializeComboBox(AMISQueryVO qvo, String width, String height){
		ComboBox<AMISCodesModelData> combo = new ComboBox<AMISCodesModelData>();
		combo = new ComboBox<AMISCodesModelData>();
		combo.setDisplayField("label");
		combo.setEmptyText("Please select");
		combo.setWidth(width);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setData("QVO", qvo);
		//combo.setTemplate(getTemplate());
		//combo.enableEvents(false);

		return combo;
	}


   public CheckBoxListView<AMISCodesModelData> buildCheckBoxList(AMISCompare compare, AMISQueryVO qvo, String boxId, Boolean addSelectAll, String width, String height, AMISCodesModelData modelToSetAsSelected) {
	   CheckBoxListView<AMISCodesModelData> view = initializeCheckBoxList(qvo, width, height, boxId);
	    ListStore<AMISCodesModelData> store = new ListStore<AMISCodesModelData>();
	    view.setStore(store);
	 	AMISSelectorController.getSelectors(compare, this, view, store, qvo, qvo.getTypeOfOutput(), addSelectAll, modelToSetAsSelected);

		return view;
	}

   public ComboBox<AMISCodesModelData> buildComboBox(AMISQueryVO qvo, String boxId, Boolean addSelectAll, String width, String height, AMISCodesModelData modelToSetAsSelected) {
	    ComboBox<AMISCodesModelData> combo = initializeComboBox(qvo, width, height);
	    combo.setFieldLabel(qvo.getTitle());
	    ListStore<AMISCodesModelData> store = new ListStore<AMISCodesModelData>();
	    combo.setStore(store);
	    combo.setId(boxId);
	    combo.setEmptyText("Select");
		AMISSelectorController.getSelectors(this, combo, store, qvo, qvo.getTypeOfOutput(), addSelectAll, modelToSetAsSelected);


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
	
	
	private native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<tpl if="is_aggregated == \'TRUE\'">',
	    '<div class="x-view-item x-view-item-check"><b>{label}</b> {unit_databases}</div>', 
	    '</tpl>',
	    '<tpl if="is_aggregated == \'FALSE\'">',
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label} {unit_databases}</div>', 
	    '</tpl>',
	    '<tpl if="is_aggregated == \'undefined\'">',
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label} {unit_databases}</div>', 
	    '</tpl>',
	    '</tpl>' 
	    ].join(""); 
	  }-*/;  

}
