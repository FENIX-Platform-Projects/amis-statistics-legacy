package org.fao.fenix.web.modules.amis.client.view.utils.filters;

import org.fao.fenix.web.modules.amis.client.control.AMISMultiSelectionController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class AMISMultiselectionItem {
	
	CheckBox checkbox;
	
	AMISCodesModelData code;
	
	AMISMultiselectionFilter filterPanel;
	
	HorizontalPanel panel;

	public AMISMultiselectionItem(AMISMultiselectionFilter filterPanel) {
		
		// TODO: right way to do it...?
		this.filterPanel = filterPanel;
		
		checkbox = new CheckBox();
		code = new AMISCodesModelData();
	}
	
	public HorizontalPanel build(AMISCodesModelData code, Boolean isSelected) {
		panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.code = code;
		this.checkbox.setValue(isSelected);
		
		setPanelStyle();
		
		checkbox.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("HERE.." );
				
				// TODO: Reload the list of selected items. could be finded a more efficied way to do ti
				AMISMultiSelectionController.buildListCurrentSelectedCodes(filterPanel);
				
				setPanelStyle();
			}
		});
		
		Html title = new Html("<div class='multiselection_item'> " + code.getLabel() + "</div>");
		
		panel.add(FormattingUtils.addHSpace(5));	
		panel.add(checkbox);
		panel.add(FormattingUtils.addHSpace(5));	
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

	public AMISCodesModelData getCode() {
		return code;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}
	
	
	
}
