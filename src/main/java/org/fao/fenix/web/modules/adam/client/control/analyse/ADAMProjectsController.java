package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMViewController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.control.utils.ADAMSubSectorORUtils;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.client.view.projects.ADAMShowProjectsPanel;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMProjectsController extends ADAMController {
	
	protected static void showProjectsAgent(HashMap<String, List<String>> filters) {
		
	/*	Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
		Map<String, String> subSectorList = ADAMBoxMaker.subSectorsSelected;
						
		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		Boolean countryAdded = addCountryFilter(filters, gaulList);
		//System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		//System.out.println("donorAdded: " + donorAdded);
		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		//System.out.println("sectorAdded: " + sectorAdded);
		Boolean subSectorAdded =  addSubSectorFilter(filters, subSectorList);*/
		
		//Boolean purposeAdded =  addPurposeCodesFilter(filters, purposesList);
		//System.out.println("purposesAdded: " + purposeAdded);
		
	/*	if( !countryAdded && !donorAdded && !sectorAdded && !subSectorAdded) {
			*//*** global view **//*
			// do nothing
			//ADAMViewMenuBuilder.hideWaitingPanel();
//			ADAMViewMenu.hideWaitingPanel();
			FenixAlert.info("List of Projects", "The query returns too many projects");
		}
		else {*/
			//System.out.println("SHOWING PROJECTS");
			showProjectsAgentView(filters);
		//}
		
	}
	

	
	public static void showProjectsAgentView(final HashMap<String, List<String>> filters) {
		  
		  RootPanel.get("ANALYSE_TOP").setStyleName("big-box content");	 
		
		    ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
			ADAMViewController.setAggregatedViewName(qSize, false);
			
			RootPanel.get("ANALYSE_TOP").add(new ADAMLoadingPanel().buildWaitingPanel());
			
			ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
				public void onSuccess(ADAMResultVO vo) {
					//System.out.println("vo size: " + vo.getRows());
					
					if ( vo.getRows() < 15000 ) {
						final Long rows = vo.getRows();
						
						
						ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(), baseDate, filters, false);
						ADAMViewController.setAggregatedViewName(q, false);
						setSelects(filters, q);
						q.setTitle(buildTitle(rows, filters));
						
						ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
							public void onSuccess(ADAMResultVO vo) {
								//ADAMViewMenuBuilder.hideWaitingPanel();
//								ADAMViewMenu.hideWaitingPanel();
								
							ADAMVisibilityController.restoreProjectsVisibility();
								
							ADAMShowProjectsPanel adamShowProjectsPanel = new ADAMShowProjectsPanel();
							//adamShowProjectsPanel.build(vo, rows);
						//	adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(vo.getTableHTML());
							//adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + rows + "</b><div>");
								
							//adamShowProjectsPanel.getPanel().layout();
							
							if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
								RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
							
							RootPanel.get("ANALYSE_TOP").add(adamShowProjectsPanel.build(vo, rows));
							
							ADAMProjectsController.initializeFixedHeaderTableJS();
							
							//RootPanel.get("ANALYSE_TOP").add(adamShowProjectsPanel.getPanel());

								
							}
							public void onFailure(Throwable e) {
								//ADAMViewMenuBuilder.hideWaitingPanel();
//								ADAMViewMenu.hideWaitingPanel();
//								FenixAlert.error(BabelFish.print().error(), e.getMessage());
							}
						});
					}
					else {
						if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
							RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
						//ADAMViewMenuBuilder.hideWaitingPanel();
//						ADAMViewMenu.hideWaitingPanel();
						FenixAlert.info("View Projects", "The current selection has resulted in too many projects (" + vo.getRows()+") for display, please refine your search.");
					}
					
				}
				public void onFailure(Throwable e) {
//					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			});
		}
	
	
	private static String buildTitle(Long projectNumber, final HashMap<String, List<String>> filters){
		String title = projectNumber+ " Projects";
		
		
		if(filters.containsKey("recipientcode") && filters.get("recipientcode").size() == 1) {
			String selectedCountriesLabel = ADAMSubSectorORUtils.getRecipientCountryLabel(ADAMBoxMaker.countriesSelected);
			title += " for "+ selectedCountriesLabel;	
		}	
		
		if(filters.containsKey("donorcode") && filters.get("donorcode").size() == 1) {
			String selectedDonor = ADAMViewMenuBuilder.getUnFormattedDescription(ADAMBoxMaker.donorsSelected);
			title += " funded by "+ selectedDonor;
			
		} 
		
		if(filters.containsKey("dac_sector") && filters.get("dac_sector").size() == 1) {
			String selectedSector = ADAMViewMenuBuilder.getUnFormattedDescription(ADAMBoxMaker.sectorsSelected);
			title += " in  "+selectedSector;
		} 
		
		if(currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS))
		title += " as reported to OECD-DAC";
		
		return title;
	}
	
	private static void setSelects(final HashMap<String, List<String>> filters, ADAMQueryVO qvo){
		
		if(filters.containsKey("recipientcode") && filters.get("recipientcode").size() == 1) {
			qvo.getSelectsMap().remove("recipientname");
		}	
		
		if(filters.containsKey("donorcode") && filters.get("donorcode").size() == 1) {
			qvo.getSelectsMap().remove("donorname");
		} 
		
		if(filters.containsKey("dac_sector") && filters.get("dac_sector").size() == 1) {
			qvo.getSelectsMap().remove("dac_label");
		} 
	}
	
	
	//only fixed header
	// $wnd.$('#project_table').fixedHeaderTable({ altClass: 'odd', footer: false, fixedColumns: 1 });
	//fixed column problem in IE when increase size of the table
	
	public static native void initializeFixedHeaderTableJS()/*-{
	   $wnd.$('#project_table').fixedHeaderTable({ altClass: 'odd', footer: false });
	
      }-*/;
	
}	