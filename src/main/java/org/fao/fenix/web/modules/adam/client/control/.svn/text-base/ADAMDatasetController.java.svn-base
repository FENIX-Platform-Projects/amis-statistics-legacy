package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMDatasetController {

     public static void callDatasetFilterAgent(final ADAMCurrentVIEW view) {

		System.out.println(" ^^^^^^^^^^^^^^^^^^^##### ADAMDatasetController: callDatasetFilterAgent "+ADAMController.currentlySelectedDatasetCode);
		if(!view.equals(ADAMCurrentVIEW.HOME)) {
			//disableMenuItems(view);
		    rePopulateFilters(view);
			
		} 
		
		//ADAMController.callView(view);
		
	};
	
	/*private static void disableMenuItems(final ADAMCurrentVIEW currentVIEW) {

		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			if (RootPanel.get("TAB3").getWidgetCount() > 0)
				RootPanel.get("TAB3").remove(RootPanel.get("TAB3").getWidget(0));
			
			
			if (RootPanel.get("TAB3").getWidgetCount() > 0) {
				ClickHtml html = (ClickHtml)RootPanel.get("TAB3").getWidget(0);
				html.removeAllListeners();
				html.addListener(Events.OnClick, ADAMController.openNotAvailableMessage("Resource Partner Matrix", "Does not apply when OECD-Creditor Reporting System (CRS) Data Source is not selected"));
			}
			
			if (RootPanel.get("TAB4").getWidgetCount() > 0)
				RootPanel.get("TAB4").remove(RootPanel.get("TAB4").getWidget(0));
			
			RootPanel.get("TAB4").setStyleName("");
			
			//RootPanel.get("TAB3").setStyleName("");
			
			
		} else {
			ClickHtml donorMatrix = new ClickHtml();
			donorMatrix.setHtml(ADAMLanguage.print().resourcePartnerMatrix());
			RootPanel.get("TAB3").add(donorMatrix);
			donorMatrix.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX));
			RootPanel.get("TAB3").setStyleName("tab_menu_default");

			if (RootPanel.get("TAB3").getWidgetCount() > 0) {
				ClickHtml html = (ClickHtml)RootPanel.get("TAB3").getWidget(0);
				html.removeAllListeners();
				html.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX, ADAMCurrentVIEW.DONORMATRIX.name()));
			}
			
			ClickHtml donorProfile = new ClickHtml();
			donorProfile.setHtml(ADAMLanguage.print().resourcePartnerProfiles());
			RootPanel.get("TAB4").add(donorProfile);
			donorProfile.addListener(Events.OnClick, ADAMController.openADAM("TAB4", ADAMCurrentVIEW.PROFILES));
			RootPanel.get("TAB4").setStyleName("tab_menu_default");
			
		}

	}	
	*/
	
	private static void rePopulateFilters(final ADAMCurrentVIEW currentVIEW) {
		if(!currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW))
			ADAMController.currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
				
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			if (RootPanel.get("CLASSIFICATION").getWidgetCount() > 0)
				RootPanel.get("CLASSIFICATION").remove(RootPanel.get("CLASSIFICATION").getWidget(0));

			ADAMController.baseUnit = "USD";
			ADAMController.baseUnitShort = "$";
			ADAMViewController.subSectorORLabel = "ORs";
			ADAMViewController.sectorSOLabel = "SOs";
		} else {
			ADAMSwitchClassificationPanel.isBuild = false;
			
			ADAMController.baseUnit = "USD Mil";
			ADAMController.baseUnitShort = "$Mil";
			ADAMViewController.subSectorORLabel = "Sub-Sectors";
			ADAMViewController.sectorSOLabel = "Sectors";
		}

		System.out.println(" ^^^^^^^^^^^^^^^^^^^##### ADAMDatasetController: ADAMSwitchSelectionPanel.isBuild 1 "+ADAMSwitchClassificationPanel.isBuild);
		
		ADAMBoxMaker.updateCountrySelector();
		ADAMBoxMaker.updateDonorSelector();
		ADAMBoxMaker.updateSectorSelector();
		//when sector updated, subselector automatically updated, so no need to call it.
		//ADAMBoxMaker.updateSubSectorSelector();
		
		//update box labels only
		ADAMBoxMaker.updateSubSectorSelectorLabels();
		
		ADAMQuantityColumn.updateAggregationSelector();	
		ADAMReferencePeriodPanel.updateReferenceDateSelector();
		
		System.out.println(" ^^^^^^^^^^^^^^^^^^^##### ADAMDatasetController: ADAMSwitchSelectionPanel.isBuild 2 "+ADAMSwitchClassificationPanel.isBuild);
		
		    
	}	
	
	public static SelectionListener<IconButtonEvent> showDatasourceInfo() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				StringBuilder sb = new StringBuilder();
				sb.append("<font style='text-align:justify;'><br/><font color='#1B65A4'><b>Data Sources</b></font><br/><br/> ");
				sb.append("The following data sources can be selected in ADAM:<br/><br/> ");
				sb.append("<b>OECD-CRS</b>: Organisation for Economic Co-operation and Development (OECD) is the official source of statistics, including aid statistics, on the 23 OECD DAC (Development Assistance Committee) member countries. Aid activity data are collected on an annual basis and are consultable through the <a href='http://stats.oecd.org/Index.aspx?DatasetCode=CRSNEW' target='_blank'> Creditor Reporting System (CRS) Aid Activities database</a>.<br/><br/>");
				sb.append("<b>FPMIS</b>: The FAO Programme Information Management System, provides corporate information on all activities funded from FAO's voluntary resources, as well as activities falling under FAO's Technical Cooperation Programme. The data only dates back to 2010, when FAO switched to its current Results-Based Management approach.<br/><br/>");
				sb.append("</font>");
					
				Text label = new Text(sb.toString()); 
				  
				//"<b><a href='http://www.aiddata.org/home/index' target='_blank'>AidData</a></b>: used by ADAM for resource partners not included in OECD-CRS. AidData aggregates information from multiple sources of global aid flows.</font>"); 
				label.addStyleName("pad-text");
				label.setStyleAttribute("backgroundcolor", "white");
				
				LayoutContainer container = new LayoutContainer();
				container.setLayout(new FitLayout());
				//container.seHeight(250);
				
				ContentPanel panel = new ContentPanel();				
				panel.setHeaderVisible(false);
				//panel.setHeight(250);
				panel.add(label);
				container.add(panel);

				Window window = new Window();
				window.setHeight(320);
				window.setWidth(465);
				window.setLayout(new FitLayout());
				
								window.add(container);
				window.setHeaderVisible(true);
				window.setHeading("ADAM Data Source Information");
				
				window.show();
			}
		};
	}

/*	private static void rePopulateFilters(final ADAMCurrentVIEW currentVIEW) {

		if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
			ADAMBoxMaker.updateCountrySelector();
			ADAMBoxMaker.updateDonorSelector();
		}	

		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
			
			if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
				if (RootPanel.get("CLASSIFICATION").getWidgetCount() > 0)
					RootPanel.get("CLASSIFICATION").remove(RootPanel.get("CLASSIFICATION").getWidget(0));

			} else {
				ADAMSwitchSelectionPanel.isBuild = false;
			}

			ADAMBoxMaker.updateSectorSelector();
			ADAMBoxMaker.updateSubSectorSelector();
			ADAMQuantityColumn.updateAggregationSelector();	
		}

		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
			ADAMQuantityColumn.updateAggregationSelector();	

		}

		if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {	
			ADAMReferencePeriodPanel.updateReferenceDateSelector();

		}
	}*/	

}
