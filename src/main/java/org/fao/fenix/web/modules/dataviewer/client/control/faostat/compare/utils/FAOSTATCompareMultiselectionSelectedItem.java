package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.utils;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class FAOSTATCompareMultiselectionSelectedItem {
	
	IconButton icon;
	
	DWCodesModelData code;
	
	HorizontalPanel panel;
	
	FAOSTATCompareMultiselection filterPanel;
	
	public FAOSTATCompareMultiselectionSelectedItem(FAOSTATCompareMultiselection filterPanel) {
		this.filterPanel = filterPanel;
		
		icon = new IconButton("delete");
		code = new DWCodesModelData();
	}
	
	public HorizontalPanel build(DWCodesModelData code) {
		panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		this.code = code;
		
		//icon.addSelectionListener(FAOSTATVisualizeByDomainController.deselectItem(filterPanel, this));
		
		Html title = new Html("<div class='multiselection_item'> " + code.getLabel() + "</div>");
		
		panel.add(icon);
		panel.add(DataViewerClientUtils.addHSpace(5));	
		panel.add(title);
		
		return panel;
	}

	

	public DWCodesModelData getCode() {
		return code;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}
	
	
	
	
}
