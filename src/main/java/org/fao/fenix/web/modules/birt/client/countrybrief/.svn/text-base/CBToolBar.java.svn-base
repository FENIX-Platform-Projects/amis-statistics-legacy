package org.fao.fenix.web.modules.birt.client.countrybrief;

import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class CBToolBar {

	ToolBar toolBar;
	ReportView reportView;
	
	public ToolBar getToolBar() {
		return toolBar;
	}
	
	public CBToolBar(ReportView reportView){
		toolBar = new ToolBar();
		this.reportView = reportView;
		ToggleButton toggle = new ToggleButton("Select provinces");
		toggle.setBorders(true);
		toggle.addSelectionListener(ProvincesController.getAllProvinces(reportView));
		toolBar.add(toggle);
	}
	
}
