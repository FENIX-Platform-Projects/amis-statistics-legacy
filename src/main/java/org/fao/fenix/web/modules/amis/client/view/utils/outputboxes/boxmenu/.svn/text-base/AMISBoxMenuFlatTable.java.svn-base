package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class AMISBoxMenuFlatTable extends BoxMenu {
	
public static ContentPanel buildMenu(AMISQueryVO qvo, AMISResultVO rvo, String width){ 
		
		// main panel
		ContentPanel panel = new ContentPanel();
				
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		panel.setBodyBorder(false);
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("table_top_panel");
		
		panel.setWidth(width);
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		panelL.addStyleName("content_chart_top_panel_left");
		panelR.addStyleName("content_chart_top_panel_right");

  	    panelR.add(buildButtons(qvo, rvo));
		panelL.add(buildTitle(qvo, rvo, "table_title")); 

		//panelR.add(buildButtons(qvo, rvo));
//		panelL.add(buildTitle(qvo, rvo, "content_title")); 

		
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
		exportToExcel.addSelectionListener(AMISController.exportTableExcel(rvo));
		
		panel.add(exportToExcel);

		return panel;
	}

}
