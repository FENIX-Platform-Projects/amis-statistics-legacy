package org.fao.fenix.web.modules.adam.client.control;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.lang.ADAMLanguage;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMToolbar;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMDonorProfileMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class DonorProfileController extends ADAMController{

	
	public static Command setDonorProfileView(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				currentVIEW = ADAMCurrentVIEW.PROFILES;
				ADAMController.containsMaps = false;
				objectWindowId = objectWindow.getNext();
						
				removeBoxes();
				//removeQuestions();
				filters();		
			}
		};
	};
	
	/**public static void addDonorProfile(final Map<String, String> donorList) {
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();

		ADAMVisibilityController.restoreAdamViewVisibility();
		RootPanel.get("CENTER").setStyleName("big-green-box border content");	
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
			RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
		//System.out.println("--------- donorList.isEmpty() = "+donorList.isEmpty());
			
		if(!donorList.isEmpty()) {
			boolean noDonor = false;
			
			for(String code : donorList.keySet()) {
				
				System.out.println("--------- code = "+code);
				if(!code.equalsIgnoreCase("") && !code.equalsIgnoreCase("GLOBAL") && !code.equalsIgnoreCase("") && !ADAMBoxMaker.donorList.getValue().getGaulLabel().equalsIgnoreCase("Multi Selection") && !ADAMBoxMaker.donorList.getValue().getGaulLabel().equalsIgnoreCase("All Selected")){
					final String c = getCode(code);
					
					 ADAMServiceEntry.getInstance().getDonorProfileVO (c, ADAMBoxMaker.donorList.getValue().getGaulLabel(), new AsyncCallback<ADAMDonorProfileVO>() {
							public void onSuccess(final ADAMDonorProfileVO vo) {
						
											ADAMDonorProfileVO updatedVo = addSectionTitles(vo);
								            //RootPanel.get("VIEWMENU_RIGHT").add(ADAMDonorProfileMaker.buildRightPanel(true, updatedVo));
								
								            VerticalPanel panel = ADAMDonorProfileMaker.build(updatedVo, c);
								            RootPanel.get("CENTER").add(panel);
										
											loadingWindow.destroyLoadingBox();
																						
											
							}
						public void onFailure(Throwable caught) {
								Info.display("Retrieved Donor Profile: Failed", "Error in retrieving Donor Profile", caught.getLocalizedMessage());
								loadingWindow.destroyLoadingBox();
							}
						});
					 
					
				} else {
					noDonor = true;
					 
					loadingWindow.destroyLoadingBox();
				}
				
				if(noDonor){
					 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
			         RootPanel.get("CENTER").add(panel);						
				}
					
			}
		} else {
			 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
	         RootPanel.get("CENTER").add(panel);
				
			loadingWindow.destroyLoadingBox();
		}
		
		
	}
	
	public static void addDonorProfile_ORIGINAL(final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> matrixModels, final Map<String, String> donorList, final String donorCode, final String donorLabel) {
		
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();

		ADAMVisibilityController.restoreAdamViewVisibility();
		RootPanel.get("CENTER").setStyleName("big-green-box border content");	
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
			RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
		
		
		
		if(donorCode!=null && !donorCode.equalsIgnoreCase("")) {
					 ADAMServiceEntry.getInstance().getDonorProfileVO (donorCode, donorLabel, new AsyncCallback<ADAMDonorProfileVO>() {
							public void onSuccess(final ADAMDonorProfileVO vo) {
						
											ADAMDonorProfileVO updatedVo = addSectionTitles(vo);
								            //RootPanel.get("VIEWMENU_RIGHT").add(ADAMDonorProfileMaker.buildRightPanel(true, updatedVo));
								
								            VerticalPanel panel = ADAMDonorProfileMaker.build(matrixVo, matrixModels, updatedVo, donorCode, donorLabel);
								            RootPanel.get("CENTER").add(panel);
										
											loadingWindow.destroyLoadingBox();
																						
											
							}
						public void onFailure(Throwable caught) {
								Info.display("Retrieved Donor Profile: Failed", "Error in retrieving Donor Profile", caught.getLocalizedMessage());
								loadingWindow.destroyLoadingBox();
							}
						});	
			
		} else {
			 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
	         RootPanel.get("CENTER").add(panel);
				
			loadingWindow.destroyLoadingBox();
		}
		
		
	}
	**/
	
	public static void addDonorProfile(final Map<String, String> donorList) {
		//final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loading(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		//loadingWindow.showLoadingBox();

		
		ADAMVisibilityController.restoreAdamViewVisibility();
		//RootPanel.get("CENTER").setStyleName("big-box content");	
		RootPanel.get("CENTER").setStyleName("no-border-box");	 
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
			RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
		
		RootPanel.get("CENTER").add(new ADAMLoadingPanel().buildWaitingPanelWhite());
		
		//System.out.println("--------- donorList.isEmpty() = "+donorList.isEmpty());
			
		if(!donorList.isEmpty()) {
			boolean noDonor = false;
			
			for(String code : donorList.keySet()) {
			
				System.out.println("--------- code = "+code);
				if(!code.equalsIgnoreCase("") && !code.equalsIgnoreCase("GLOBAL") && !code.equalsIgnoreCase("") && !ADAMBoxMaker.donorList.getValue().getGaulLabel().equalsIgnoreCase("Multi Selection") && !ADAMBoxMaker.donorList.getValue().getGaulLabel().equalsIgnoreCase("All Selected")){
					
					final ADAMDonorProfileVO vo = new ADAMDonorProfileVO();
					vo.setResourcePartnerName(ADAMBoxMaker.donorList.getValue().getGaulLabel());
					addSectionTitles(vo);
					
					final String c = getCode(code);
				    String selectedDataset = ADAMController.currentlySelectedDatasetCode.toString();
						
					  ADAMServiceEntry.getInstance().getResourcePartnerProfile(vo, c, selectedDataset, new AsyncCallback<ADAMResultVO>() {
										public void onSuccess(final ADAMResultVO rvo) {
											
											if (RootPanel.get("CENTER").getWidgetCount() > 0)
												RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
										
											
											RootPanel.get("CENTER").add(buildHTMLPanel(rvo.getTableHTML(), rvo.getDonorProfileVO()));

											//loadingWindow.destroyLoadingBox();																						
											
							}
						public void onFailure(Throwable caught) {
								Info.display("Retrieved Donor Profile: Failed", "Error in retrieving Donor Profile", caught.getLocalizedMessage());
								if (RootPanel.get("CENTER").getWidgetCount() > 0)
									RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
							
								
								//loadingWindow.destroyLoadingBox();
							}
						});
					 
					
				} else {
					noDonor = true;
					if (RootPanel.get("CENTER").getWidgetCount() > 0)
						RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
					//loadingWindow.destroyLoadingBox();
				}
				
				if(noDonor){
					 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
					 panel.setStyleAttribute("backgroundColor", "#FFFFFF");
					 panel.setBorders(false);
					 RootPanel.get("CENTER").add(panel);						
				}
					
			}
		} else {
			 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
			 panel.setStyleAttribute("backgroundColor", "#FFFFFF");
			 panel.setBorders(false);
			 
			 if (RootPanel.get("CENTER").getWidgetCount() > 0)
					RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
			 
	         RootPanel.get("CENTER").add(panel);
				
			//loadingWindow.destroyLoadingBox();
		}
		
		
	}
	
  public static void addDonorProfile(final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> matrixModels, final Map<String, String> donorList, final String donorCode, final String donorLabel) {
		
		//final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loading(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		//loadingWindow.showLoadingBox();

		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("CENTER").setStyleName("big-box content");	
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
			RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
		
		RootPanel.get("CENTER").add(new ADAMLoadingPanel().buildWaitingPanelWhite());
		
		if(donorCode!=null && !donorCode.equalsIgnoreCase("")) {
			ADAMDonorProfileVO vo = new ADAMDonorProfileVO();
			vo.setResourcePartnerName(donorLabel);
			addSectionTitles(vo);
			
			String selectedDataset = ADAMController.currentlySelectedDatasetCode.toString();
			
					 ADAMServiceEntry.getInstance().getResourcePartnerProfile(vo, donorCode, selectedDataset, new AsyncCallback<ADAMResultVO>() {
							public void onSuccess(final ADAMResultVO rvo) {
								
								 if (RootPanel.get("CENTER").getWidgetCount() > 0)
										RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
								 
								RootPanel.get("CENTER").add(buildHTMLPanel(rvo.getTableHTML(), rvo.getDonorProfileVO()));

								//loadingWindow.destroyLoadingBox();	
																						
											
							}
						public void onFailure(Throwable caught) {
								Info.display("Retrieved Donor Profile: Failed", "Error in retrieving Donor Profile", caught.getLocalizedMessage());
								 if (RootPanel.get("CENTER").getWidgetCount() > 0)
										RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
								//loadingWindow.destroyLoadingBox();
							}
						});	
			
		} else {
			 VerticalPanel panel = ADAMDonorProfileMaker.build(null, null);
			 panel.setStyleAttribute("backgroundColor", "#FFFFFF");
			 panel.setBorders(false);
				
			 if (RootPanel.get("CENTER").getWidgetCount() > 0)
					RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
			 
	         RootPanel.get("CENTER").add(panel);
				
			//loadingWindow.destroyLoadingBox();
		}
		
		
	}
	
	public static SelectionListener<ButtonEvent> exportPDF(final ADAMDonorProfileVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("exportPDF: parameters: "+vo);
							
				ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
				adamReportBeanVO.setCenterTitle(ADAMBoxMaker.donorList.getValue().getGaulLabel());
				adamReportBeanVO.setLeftTitle("Resource Partner Profile");
				adamReportBeanVO.setRightTitle(vo.getProfileReferenceDate());
				adamReportBeanVO.setDonorProfileVO(vo);
				adamReportBeanVO.setReportType(ADAMCurrentVIEW.PROFILES.name());
				
				ADAMViewMenuBuilder.showWaitingPanel("Creating PDF...");
				
				ADAMServiceEntry.getInstance().createReport(null, adamReportBeanVO, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						ADAMViewMenuBuilder.hideWaitingPanel();
						com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
					}
					
					public void onFailure(Throwable result) {
						ADAMViewMenuBuilder.hideWaitingPanel();
					}
				});
			}
		};
	}
	
	
	public static Listener<ComponentEvent> exportProfile(final ADAMDonorProfileVO vo) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				System.out.println("exportPDF: parameters: "+vo);
				
				ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
				adamReportBeanVO.setCenterTitle("");
				adamReportBeanVO.setLeftTitle(ADAMBoxMaker.donorList.getValue().getGaulLabel());
				adamReportBeanVO.setRightTitle(""); //vo.getProfileReferenceDate()
				adamReportBeanVO.setDonorProfileVO(vo);
				adamReportBeanVO.setReportType(ADAMCurrentVIEW.PROFILES.name());
				
				ADAMViewMenuBuilder.showWaitingPanel("Creating PDF...");
				
				ADAMServiceEntry.getInstance().createReport(null, adamReportBeanVO, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						ADAMViewMenuBuilder.hideWaitingPanel();
						com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
					}
					
					public void onFailure(Throwable result) {
						ADAMViewMenuBuilder.hideWaitingPanel();
					}
				});	
			}
		};
	}	
	
	public static Listener<BaseEvent> openADAMDonorView(final String donorCode, final String donorLabel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				
				ADAMBoxMaker.donorsSelected.clear();
				ADAMBoxMaker.donorsSelected.put("Donor_"+donorCode, donorLabel);
				ADAMBoxMaker.donorList.setValue(ADAMBoxMaker.donorList.getStore().findModel("gaulCode", "Donor_"+donorCode));
					
				ADAMBoxMaker.countriesSelected.clear();
				
				callViewAgent(ADAMCurrentVIEW.ADAMVIEW, "openADAMDonorView", ADAMCurrentVIEW.BROWSE.name());	
				
				RootPanel.get("TAB5").setStyleName("tab_menu_default");
				RootPanel.get("TAB2").setStyleName("tab_menu_selected");
			        	
			}
		};
	}
	
	
	public static Listener<BaseEvent> openADAMDonorViewForFAORegion(final String regionCode) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loading(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();

				ADAMServiceEntry.getInstance().getRecipientsForFAORegion(regionCode, new AsyncCallback<Map<String,String>>() {
					public void onSuccess(Map<String, String> recipients) {
						ADAMBoxMaker.countriesSelected.clear();
						//ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulList.getStore().findModel("gaulCode", "Gaul_"+donorCode));
						
						for(String code: recipients.keySet()) {
							ADAMBoxMaker.countriesSelected.put("Gaul_"+code, recipients.get(code));
						}
						
						callViewAgent(ADAMCurrentVIEW.ADAMVIEW, "getRecipientsForFAORegion", ADAMCurrentVIEW.BROWSE.name());	
						
						loadingWindow.destroyLoadingBox();
						
						RootPanel.get("TAB5").setStyleName("tab_menu_default");
						RootPanel.get("TAB2").setStyleName("tab_menu_selected");
					}
					
					public void onFailure(Throwable result) {
						Info.display("Error ", "Error getting Recipients For FAO Region", result.getLocalizedMessage());
						loadingWindow.destroyLoadingBox();
					}
				});
				
			        	
			}
		};
	}
	
	static ADAMDonorProfileVO addSectionTitles(ADAMDonorProfileVO vo) {
		List<String> headers = new LinkedList<String>();
		
		vo.setProfileTitle(ADAMLanguage.print().profile());
		
		vo.setPriorityThemesTitle(ADAMLanguage.print().overallPriorityThemes());
		vo.setPriorityGeographicalAreasTitle(ADAMLanguage.print().priorityGeographicalAreas());
		vo.setFundingBodiesTitle(ADAMLanguage.print().fundingBodies());
		vo.setChannelsOfCooperationTitle(ADAMLanguage.print().channelsOfCooperation());
		vo.setFavouredFundingArrangementsTitle(ADAMLanguage.print().favouredFundingArrangements());
		vo.setAnnualFundingCycleTitle(ADAMLanguage.print().annualFundingCycle());
		vo.setApplicationAndNegotiationProcessTitle(ADAMLanguage.print().applicationAndNegotiationProcess());
		vo.setBudgetRevisionPoliciesTitle(ADAMLanguage.print().policyOnApprovalBudgetRevision());
		vo.setAccruedInterestPoliciesTitle(ADAMLanguage.print().policyOnUseAccruedInterest());
		vo.setSpecialCharacteristicsTitle(ADAMLanguage.print().specialCharacteristicsFeatures());
		vo.setExternalLinksTitle(ADAMLanguage.print().externalLinks());
		vo.setResourceMobilizationOfficerTitle(ADAMLanguage.print().resourceMobilizationOfficer());
		vo.setEmailTitle(ADAMLanguage.print().email());
			
		
		headers.add(ADAMLanguage.print().overallPriorityThemes());
		headers.add(ADAMLanguage.print().priorityGeographicalAreas());
		headers.add(ADAMLanguage.print().fundingBodies());
		headers.add(ADAMLanguage.print().channelsOfCooperation());
		headers.add(ADAMLanguage.print().favouredFundingArrangements());
		headers.add(ADAMLanguage.print().annualFundingCycle());
		headers.add(ADAMLanguage.print().applicationAndNegotiationProcess());
		headers.add(ADAMLanguage.print().policyOnApprovalBudgetRevision());
		headers.add(ADAMLanguage.print().policyOnUseAccruedInterest());
		headers.add(ADAMLanguage.print().specialCharacteristicsFeatures());
		headers.add(ADAMLanguage.print().externalLinks());
		
		vo.setHeaders(headers);
		
		return vo;
	}	
	
	
	private static HTMLPanel buildHTMLPanel(String profileHtml, ADAMDonorProfileVO vo){
		
		HTMLPanel html = new HTMLPanel(profileHtml);
		html.setStyleName("align-left");

		ClickHtml clickHtml = new ClickHtml();
		clickHtml.setStyleName("profile-title");
		
		if(html.getElementById("PROFILE-EXPORT")!=null) {
			clickHtml.setHtml(html.getElementById("PROFILE-EXPORT").getInnerHTML());
			clickHtml.addListener(Events.OnClick, exportProfile(vo));

			html.addAndReplaceElement(clickHtml, "PROFILE-EXPORT");
		}
		
		HorizontalPanel waitingPanel = ADAMDonorProfileMaker.buildWaitingPanel("200px");
		
		if(html.getElementById("PROFILE-EXPORT-WAITINGPANEL")!=null) {
			html.addAndReplaceElement(waitingPanel, "PROFILE-EXPORT-WAITINGPANEL");
		}
		
		return html;
	}
	
	
	
	/**static ADAMDonorProfileVO addSectionTitles_ORIGINAL(ADAMDonorProfileVO vo) {
			
		vo.setResponsibleOfficerTitle(BabelFish.print().responsibleOfficer());
		vo.setResponsibleOfficerEmailTitle(BabelFish.print().emailOnly());
		vo.setPriorityThemesTitle(BabelFish.print().overallPriorityThemes());
		vo.setKeySOsTitle(BabelFish.print().faoStrategicObjectives());
		vo.setKeySOsDisclaimer(BabelFish.print().faoStrategicObjectivesDisclaimer());
		vo.setExternalLinksTitle(BabelFish.print().webLinks());
	    vo.setPriorityGeographicalAreasTitle(BabelFish.print().priorityGeographicalAreas());
		vo.setFavouredFundingArrangementsTitle(BabelFish.print().favouredFundingArrangements());
		vo.setExternalFundingForDeliveryPeriodsTitle(BabelFish.print().deliveryBySources());
		if(vo.getRegionalCountriesReferenceDate()!=null)
			vo.setRegionalRecipientCountriesTitle(BabelFish.print().recipientCountriesFAOProjects() + " "+vo.getRegionalCountriesReferenceDate());
		else
			vo.setRegionalRecipientCountriesTitle(BabelFish.print().recipientCountriesFAOProjects());
		
		vo.setFundingBodiesTitle(BabelFish.print().fundingBodies());
		vo.setChannelsOfCooperationTitle(BabelFish.print().channelsOfCooperation());
		vo.setApplicationAndNegotiationProcessTitle(BabelFish.print().processOfApplication());
		vo.setAnnualFundingCycleTitle(BabelFish.print().annualFundingCycle());
		vo.setSpecialCharacteristicsTitle(BabelFish.print().specialCharacteristics());
		vo.setBudgetRevisionPoliciesTitle("Policies on Approving Budget Revisions");
		vo.setAccruedInterestPoliciesTitle("Policies on the Use of Accrued Interest");
	
		return vo;
	}	**/
	
	
	
}