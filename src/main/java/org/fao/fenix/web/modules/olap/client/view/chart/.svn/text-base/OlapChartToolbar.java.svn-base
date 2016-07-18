package org.fao.fenix.web.modules.olap.client.view.chart;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartViewerController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class OlapChartToolbar {
	
	ToolBar toolBar;
	
	public ToolBar getToolBar() {
		return toolBar;
	}
	
	public OlapChartToolbar(String rptDesign, String chartType,HTML content){
		
		toolBar = new ToolBar();
		
		IconButton iconButton;
		
		iconButton = new IconButton("chartEdit");
		iconButton.setTitle(BabelFish.print().formatChart());
		iconButton.addSelectionListener(ChartViewerController.formatChart(rptDesign, chartType, content));
		toolBar.add(iconButton);
	
	}
	
}
