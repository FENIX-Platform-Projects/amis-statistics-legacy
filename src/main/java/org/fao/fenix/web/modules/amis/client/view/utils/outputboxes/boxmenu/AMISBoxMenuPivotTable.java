package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class AMISBoxMenuPivotTable extends BoxMenu {
	
public static ContentPanel buildMenu(AMISQueryVO qvo, AMISResultVO rvo, String width){ 
		
		// main panel
		ContentPanel panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_pivot_top_panel");
//		toolBar.setHeight(20);
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
//		panelL.addStyleName("content_chart_top_panel_left");
//		panelR.addStyleName("content_chart_top_panel_right");

		
		panelL.add(buildTitle(qvo, rvo, "pivot_table_title")); 

		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.setTopComponent(toolBar);
		
//		panel.add(DataViewerClientUtils.addVSpace(10));	
		return panel;
	}

	public static HorizontalPanel buildTitle(AMISQueryVO qvo, AMISResultVO rvo, String titleStyle) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
	
		panel.add(FormattingUtils.addHSpace(2));
		
		panel.add(FormattingUtils.addHSpace(2));
		
		String title = rvo.getTitle();

	
		panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
		
		return panel;
	}

//	private static HorizontalPanel buildButtons(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
//		HorizontalPanel panel = new HorizontalPanel();
//		panel.setSpacing(2);
//		Menu moreMenu = new Menu();
//		SplitButton more = new SplitButton("Export");
//		more.setIconStyle("exportIcon");
//	
//		more.setMenu(moreMenu);
//
////		MenuItem exportImage = new MenuItem("Export Image");
////		exportImage.setIconStyle("pdfIcon");
////		exportImage.addSelectionListener(FAOSTATChartController.exportChartImage(rvo));
//		
//		MenuItem exportExcel = new MenuItem("Export Excel");
//	
//		if(qvo.getResourceType().equalsIgnoreCase(DataViewerBoxContent.CHART.toString())){
//		exportExcel.setIconStyle("sendToExcel");
//		exportExcel.addSelectionListener(FAOSTATMainController.exportChartExcel(rvo));
//		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
//		exportAllResources.setIconStyle("");
//		}
//		else if(qvo.getResourceType().equalsIgnoreCase(DataViewerBoxContent.TABLE.toString())) {
//			exportExcel.setIconStyle("sendToExcel");
//			exportExcel.addSelectionListener(FAOSTATMainController.exportTableExcel(rvo));		
//		}
////		moreMenu.add(exportImage);
//		moreMenu.add(exportExcel);
//
//		
//		panel.add(more);
//
////		panel.add(DataViewerClientUtils.addHSpace(2));
//		return panel;
//	}

}
