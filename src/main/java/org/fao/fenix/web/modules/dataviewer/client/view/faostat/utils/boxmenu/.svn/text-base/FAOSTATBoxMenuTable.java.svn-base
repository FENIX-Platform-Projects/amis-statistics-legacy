package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATBoxMenuTable extends FAOSTATBoxMenu {
	
public static ContentPanel buildMenu(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String width, String gapWidth){ 
		
		// main panel
		ContentPanel panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_chart_top_panel");
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		panelL.addStyleName("content_chart_top_panel_left");
		panelR.addStyleName("content_chart_top_panel_right");

		panelL.add(buildTitle(qvo, rvo, "content_title")); 
	
		
		// build right menu
		panelR.add(buildButtons(qvo, rvo));

		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.setTopComponent(toolBar);
		
		panel.add(DataViewerClientUtils.addVSpace(10));	
		return panel;
	}

	
	private static HorizontalPanel buildButtons(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(2);
		Button exportExcel = new Button(FAOSTATLanguage.print().exportToExcel());
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(FAOSTATMainController.exportTableExcelButton(rvo));		

		panel.add(exportExcel);

		return panel;
	}

}
