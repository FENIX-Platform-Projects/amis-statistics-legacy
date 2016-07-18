package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class AMISBoxMenuChart extends BoxMenu {
	
public static ContentPanel buildMenu(AMISQueryVO qvo, AMISResultVO rvo, String width, String gapWidth){ 
		
		// main panel
		ContentPanel panel = new ContentPanel();

		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("chart_top_panel");

	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		//panelL.addStyleName("content_chart_top_panel_left");
		//panelR.addStyleName("content_chart_top_panel_right");

			
		panelL.add(buildTitle(qvo, rvo, "chart_title")); 
		
		
		// build right menu
		panelR.add(buildButtons(qvo, rvo));

		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
				
		//panel.setTopComponent(toolBar);
		panel.add(toolBar);
		
		return panel;
	}

	
	private static HorizontalPanel buildButtons(AMISQueryVO qvo, AMISResultVO rvo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(2);
		
		Button exportToExcel = new Button("Extract Data To Excel");
		exportToExcel.setIconStyle("sendToExcel");
		exportToExcel.addSelectionListener(AMISController.exportChartExcel(rvo));
		
		panel.add(exportToExcel);

		return panel;
	}

}
