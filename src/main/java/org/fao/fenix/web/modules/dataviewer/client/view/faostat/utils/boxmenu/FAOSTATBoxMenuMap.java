package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATBoxMenuMap extends FAOSTATBoxMenu {
	
	public static ContentPanel buildMenu(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String width, String gapWidth, String baseURL, Boolean addJoinTypeButton, ContentPanel mapPanel){ 
		
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
		panelR.add(buildButtons(qvo, rvo, baseURL, addJoinTypeButton, mapPanel));

		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.add(toolBar);

		panel.add(DataViewerClientUtils.addVSpace(2));	
		return panel;
	}
	
	private static HorizontalPanel buildButtons(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String baseURL, Boolean addJoinTypeButton, ContentPanel mapPanel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(2);

		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton(FAOSTATLanguage.print().export());
		more.setIconStyle("exportIcon");
	
		more.setMenu(moreMenu);
	
		MenuItem exportExcel = new MenuItem(FAOSTATLanguage.print().exportToExcel());
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(FAOSTATMainController.exportChartExcel(rvo));
//		exportExcel.addSelectionListener(FAOSTATMainController.exportCSV(qvo));
		MenuItem exportAllResources = new MenuItem(FAOSTATLanguage.print().exportVisibleResources());
		exportAllResources.setIconStyle("");
		moreMenu.add(exportExcel);
		
		SplitButton joinSlitButton = new SplitButton(FAOSTATLanguage.print().mapType());
		joinSlitButton.setIconStyle("mapIcon");
		Menu joinMenu = new Menu();
		MenuItem shaded = new MenuItem(FAOSTATLanguage.print().shaded());
		shaded.setIconStyle("shadedIcon");
		String shadedURL = baseURL + "&jointype=shaded";
		shaded.addSelectionListener(FAOSTATMainController.refreshMapContent(mapPanel, shadedURL));
		
		MenuItem points = new MenuItem(FAOSTATLanguage.print().points());
		points.setIconStyle("pointsIcon");
		String pointsURL = baseURL + "&jointype=point";
		points.addSelectionListener(FAOSTATMainController.refreshMapContent(mapPanel, pointsURL));	
		
		joinMenu.add(shaded);
		joinMenu.add(points);
		joinSlitButton.setMenu(joinMenu);
		
		if ( addJoinTypeButton )
			panel.add(joinSlitButton);
		
		panel.add(DataViewerClientUtils.addHSpace(3));
		panel.add(more);
		
		return panel;
	}
	


}
