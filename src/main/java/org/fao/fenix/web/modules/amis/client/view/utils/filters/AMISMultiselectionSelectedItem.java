package org.fao.fenix.web.modules.amis.client.view.utils.filters;

import org.fao.fenix.web.modules.amis.client.control.AMISMultiSelectionController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;


import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class AMISMultiselectionSelectedItem {
	
	IconButton icon;
	
	AMISCodesModelData code;
	
	HorizontalPanel panel;
	
	AMISMultiselectionFilter filterPanel;
	
	public AMISMultiselectionSelectedItem(AMISMultiselectionFilter filterPanel) {
		this.filterPanel = filterPanel;
		
		icon = new IconButton("delete");
		code = new AMISCodesModelData();
	}
	
	public HorizontalPanel build(AMISCodesModelData code) {
		panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.code = code;
		
		icon.addSelectionListener(AMISMultiSelectionController.deselectItem(filterPanel, this));
		
		Html title = new Html("<div class='multiselection_item'> " + code.getLabel() + "</div>");
		
		panel.add(icon);
		panel.add(FormattingUtils.addHSpace(5));	
		panel.add(title);
		
		return panel;
	}

	

	public AMISCodesModelData getCode() {
		return code;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}
	
	
	
	
}
