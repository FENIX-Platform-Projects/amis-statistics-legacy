package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.utils;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class FAOSTATCompareMultiselectionItem {
	
	CheckBox checkbox;
	
	DWCodesModelData code;
	
	FAOSTATCompareMultiselection filterPanel;
	
	HorizontalPanel panel;

	public FAOSTATCompareMultiselectionItem(FAOSTATCompareMultiselection filterPanel) {
		
		// TODO: right way to do it...?
		this.filterPanel = filterPanel;
		
		checkbox = new CheckBox();
		code = new DWCodesModelData();
	}
	
	public HorizontalPanel build(DWCodesModelData code, Boolean isSelected) {
		panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.code = code;
		this.checkbox.setValue(isSelected);
		
		setPanelStyle();
		
		checkbox.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("HERE.." );
				
				// TODO: Reload the list of selected items. could be finded a more efficied way to do ti
				//FAOSTATVisualizeByDomainController.buildListCurrentSelectedCodes(filterPanel);
				
				setPanelStyle();
			}
		});
		
		Html title = new Html("<div class='multiselection_item'> " + code.getLabel() + "</div>");
		
		panel.add(DataViewerClientUtils.addHSpace(5));	
		panel.add(checkbox);
		panel.add(DataViewerClientUtils.addHSpace(5));	
		panel.add(title);
		
		return panel;
	}
	
	public void setPanelStyle() {
		if ( checkbox.getValue() )
			panel.setStyleName("visualize_multiselection_item_selected");
		else 
			panel.setStyleName("visualize_multiselection_item");
	}

	public CheckBox getCheckbox() {
		return checkbox;
	}

	public DWCodesModelData getCode() {
		return code;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}
	
	
	
}
