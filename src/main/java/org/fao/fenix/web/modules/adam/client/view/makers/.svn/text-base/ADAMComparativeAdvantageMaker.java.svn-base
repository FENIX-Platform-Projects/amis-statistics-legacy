package org.fao.fenix.web.modules.adam.client.view.makers;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMFAOComparativeAdvantageController;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class ADAMComparativeAdvantageMaker extends ADAMBoxMaker {

	public static VerticalPanel buildTable(ADAMResultVO rvo, ADAMQueryVO qvo) {
		VerticalPanel p = new VerticalPanel();
		
		if(rvo.getBaseModels().size() > 0) {
			VerticalPanel h1 = buildTableBoxMenu(rvo, qvo, ADAMConstants.BIGGER_MENU_GAP);
			HorizontalPanel h2 = buildTableBox(rvo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
			p.add(h1);
			p.add(h2);
		} else {
			p.setStyleAttribute("padding", "20px");
			
			HorizontalPanel hp = new HorizontalPanel();
			hp.setSpacing(2);
			
			Html noDataMessage = new Html("<b>No FAO delivery data is available for the selections made. The FAO delivery is required to calculate the FAO Comparative Advantage, as detailed </b>");
			
			ClickHtml here = new ClickHtml();
			here.setHtml("<b>here.</b>");
			here.setStyleAttribute("cursor", "pointer");
			here.setStyleAttribute("text-decoration", "underline");	
			here.addListener(Events.OnClick, ADAMController.showReadMoreListener(rvo.getDescription(), "Explanation note of the Revealed FAO Comparative Advantage calculations", 650));
			
			hp.add(noDataMessage);
			hp.add(here);
			
			p.add(hp);
		}

		return p;
	}
	
	private static VerticalPanel buildTableBoxMenu(ADAMResultVO rvo, ADAMQueryVO qvo, String labelWidth) { 
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(buildBoxHeader(rvo, labelWidth, null, false));
		vPanel.add(buildComparativeAdvantageIconsToolbar(rvo, qvo));
		return vPanel;
	}
	
	private static HorizontalPanel buildComparativeAdvantageIconsToolbar(ADAMResultVO rvo, ADAMQueryVO qvo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);
			
		Button viewSummary = createToolbarButton(null);
		viewSummary.setIconStyle("tableIcon");
		viewSummary.removeAllListeners();
		
		if(qvo.getAggregations()==null){
			viewSummary.setText("View Aggregated Year Summary");
			viewSummary.addSelectionListener(ADAMFAOComparativeAdvantageController.buildAggregatedYearTable(rvo, qvo, "ANALYSE_TOP"));
		} else {
			viewSummary.setText("View Year Breakdown");
			viewSummary.addSelectionListener(ADAMFAOComparativeAdvantageController.buildByYearTable(rvo, qvo, "ANALYSE_TOP"));
		}
		
		if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){	
			if(!ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
				toolBar.add(viewSummary);
				toolBar.add(new SeparatorToolItem());  
			}	
		}
		
		Button exportExcel = createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		exportExcel.addSelectionListener(ADAMController.exportToExcel(rvo, true));
		
		toolBar.add(exportExcel);
		
		toolBar.add(new SeparatorToolItem());  
		
		toolBar.add(addReadMoreButton(rvo.getDescription(), "Explanation note of the Revealed FAO Comparative Advantage calculations", 650, 0, false));	
		
		return panel;
	}
	
	/*public static VerticalPanel buildBigTable(ADAMResultVO vo,  ADAMQueryVO qvo, String position, String color) {
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		
		HorizontalPanel h1 = buildTableBoxMenu(vo, qvo, ADAMConstants.SMALL_MENU_GAP_WIDTH, position, color,  null, true, true);
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = ADAMTableMaker.buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		h2.add(cp);
		p.add(h1);
//		h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), true));
		p.add(h2);
		return p;
	}*/
	
	private static HorizontalPanel buildTableBox(ADAMResultVO rvo, String width, String height) {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setHorizontalAlign(HorizontalAlignment.LEFT);
		hp.setStyleAttribute("margin-left", "5px");
		hp.add(ADAMTableMaker.buildGrid(rvo, width, height));
		return hp;
	}

}