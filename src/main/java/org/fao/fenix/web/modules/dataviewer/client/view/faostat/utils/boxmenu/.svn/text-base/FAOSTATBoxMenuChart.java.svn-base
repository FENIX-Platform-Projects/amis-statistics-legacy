package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATBoxMenuChart extends FAOSTATBoxMenu {
	
public static ContentPanel buildMenu(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String width, String gapWidth, Boolean addExports){ 
		
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
		if ( addExports ) {
			panelR.add(buildButtons(qvo, rvo));
		}

		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.add(toolBar);
		
		return panel;
	}
	
	private static HorizontalPanel buildButtons(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(2);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton(FAOSTATLanguage.print().export());
		more.setIconStyle("exportIcon");
	
		more.setMenu(moreMenu);

//		MenuItem exportImage = new MenuItem("Export Image");
//		exportImage.setIconStyle("pdfIcon");
//		exportImage.addSelectionListener(FAOSTATChartController.exportChartImage(rvo));
		
		MenuItem exportExcel = new MenuItem(FAOSTATLanguage.print().exportToExcel());
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(FAOSTATMainController.exportChartExcel(rvo));
//		exportExcel.addSelectionListener(FAOSTATMainController.exportCSV(qvo));
		MenuItem exportAllResources = new MenuItem(FAOSTATLanguage.print().exportVisibleResources());
		exportAllResources.setIconStyle("");
		
//		moreMenu.add(exportImage);
		moreMenu.add(exportExcel);

		panel.add(more);
		return panel;
	}

}
