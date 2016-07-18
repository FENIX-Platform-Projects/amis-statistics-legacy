package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMFAOViewController;
import org.fao.fenix.web.modules.adam.client.control.ADAMViewController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.control.history.ADAMHistoryController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennRecipientMatrixController;
import org.fao.fenix.web.modules.adam.client.history.ADAMHistory;
import org.fao.fenix.web.modules.adam.client.view.ADAMDataSourceSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.analyse.ADAMAnalysisListPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;


public class ADAMAnalyseDataController extends ADAMViewController {
	
	
	public static ADAMAnalyseVIEW analyseView;
	private static ADAMAnalysisListPanel analysesPanel;
	
	public static HashMap<String, List<String>> filters;
		
	public static void createAnalyseDataView() {
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();
		
		filters = getFilters();
		System.out.println("filters: " + filters);

		analysesPanel = new ADAMAnalysisListPanel(filters);

		removeAllCustomObjects();	

		ADAMVisibilityController.restoreAnalyseDataViewVisibility();

		addDataSourceSelector();
		
		addClassificationSelector();

		clearContents("createAnalyseDataView");
		
		/*RootPanel.get("CENTER").setStyleName("no-border-box");	 
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
			RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));

		RootPanel.get("ANALYSE_TOP").setStyleName("big-box content");	 
		if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));*/

		
		if(analyseView == null){ 
			System.out.println("########## 1 createAnalyseDataView CALL createAnalysisItems() IF ........"+analyseView + " history token "+History.getToken());
			RootPanel.get("ANALYSE_TOP").setStyleName("big-box-border-white content");	
			
			if(History.getToken()!=null){
				
				//parsedHistoryToken = get the exact history token ... remove any URL parameters
				String parsedHistoryToken = ADAMHistory.historyListener.getHistoryToken(History.getToken());
				
				System.out.println("########## 2 createAnalyseDataView CALL createAnalysisItems() IF ........"+analyseView + " parsed history token "+parsedHistoryToken);

				if(ADAMAnalyseVIEW.getCurrentView(parsedHistoryToken)!=null) {
					analyseView = ADAMAnalyseVIEW.getCurrentView(parsedHistoryToken);
					
					System.out.println("########## 3 createAnalyseDataView analyseView = "+analyseView);
					
					ADAMAnalyseValidationController.validate(filters, "createAnalyseDataView()", analysesPanel);
				} else {
					createAnalysisItems(filters);
				}	
			} 	else {
				createAnalysisItems(filters);
			}	
			
		} else {			
			System.out.println("########## 1 createAnalyseDataView CALL createAnalysisItems() ELSE ........"+analyseView);
			ADAMAnalyseValidationController.validate(filters, "createAnalyseDataView()", analysesPanel);
		}
	}
	
	private static void addDataSourceSelector() {
		
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){		
		//if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) || currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)){
			if (!ADAMDataSourceSelection.isBuild()) {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.build());
			}
			else {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.getPanel());
			}
		
		}	
	}
	
	private static void addClassificationSelector() {
		
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
		 if(!ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
		    	if (RootPanel.get("CLASSIFICATION").getWidgetCount() == 0 || !ADAMSwitchClassificationPanel.getIsBuild()) {
		    		RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());		
		  	    		ADAMSwitchClassificationPanel.isBuild = true;
		    	} 
		    }
		}
	
	}

	private static HashMap<String, List<String>> getFilters(){
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW))
			filters = setADAMViewFilters();
		else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
			filters = setFAOViewFilters();

	    return filters;
	}
	
	private static HashMap<String, List<String>> setADAMViewFilters(){
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
		Map<String, String> subSectorsList = ADAMBoxMaker.subSectorsSelected;
		Map<String, String> channelList = ADAMBoxMaker.channelsSelected;
				
		
		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		 Boolean subSectorAdded =  addSubSectorFilter(filters, subSectorsList);
		 Boolean channelAdded =  addChannelFilter(filters, channelList);
		 
		 return filters;
	}
	
	private static HashMap<String, List<String>> setFAOViewFilters(){
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> soList = ADAMBoxMaker.soSelected;
		Map<String, String> orList = ADAMBoxMaker.orSelected;
	
		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		
		 HashMap<String, String> sos = ADAMFAOViewController.getSO(soList);
		 Boolean soAdded = !sos.isEmpty();		 
		 
		 HashMap<String, String> ors = ADAMFAOViewController.getSO(orList);
		 Boolean orAdded = !ors.isEmpty();
		 
		 return filters;
	}
	
	
	
	protected static void createAnalysisItems(HashMap<String, List<String>> filters) {
	   ADAMVisibilityController.removeAnalyseViewMenuContent();
	   RootPanel.get("ANALYSE_TOP").add(analysesPanel);
	}
	

	public static void openAnalyseView(HashMap<String, List<String>> filters){
		System.out.println("%%% openAnalyseView = "+analyseView);
		
		/*if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX)){
			if (tokensAsync > 0)
				checkIfAllTheAsyncCallsAreReturned(true);	
		}
		else
			*/
		
		ADAMAnalyseValidationController.disableSelectors(analyseView);
	
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
		
		buildSelectedAnalysisTitlePanel(filters);
		
		addAnalyseViewMenu("", gaulList, donorList);
		
		if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_VENN)){
			addCountryPrioritiesVenn(gaulList, donorList);	
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PRIORITIES)){
			ADAMVennRecipientMatrixController.buildVennRecipientMatrixAgent(null, true, "ANALYSE_TOP", "ANALYSE_MIDDLE", "ANALYSE_BOTTOM");
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PROJECTS)){
			ADAMProjectsController.showProjectsAgent(filters);
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_IMPLEMENTING_AGENCIES)){
			ADAMChannelsController.createChannelsViewAgent();  	
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX)){
			ADAMDonorMatrixController.createDonorMatrixView(donorList, gaulList, filters);
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_FAO_COMPARATIVE_ADVANTAGE)){
			ADAMFAOComparativeAdvantageController.buildADAMComparativeAdvantageAgent();    	
		}
	}
	
	
	
	public static SelectionListener<ButtonEvent> returnBackToAnalysesList(final HashMap<String, List<String>> filters) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				
				analyseView = null;
				
				//set History token to analyse
				ADAMHistoryController.setHistoryAnchor(ADAMController.currentVIEW, ADAMCurrentVIEW.ANALYSE.name());
				
				clearContents("returnBackToAnalysesList");
		
				ADAMVisibilityController.enableAllSelectors();
				
				RootPanel.get("ANALYSE_TOP").setStyleName("big-box-border-white content");	 
				createAnalysisItems(filters);
			}
		};
	}
	
	
	
	public static SelectionListener<ButtonEvent> openRelatedAnalysis(final HashMap<String, List<String>> filters, final ADAMAnalyseVIEW relatedAnalysis) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				analyseView = relatedAnalysis;
						 
				//clearContents("openRelatedAnalysis");
				
				ADAMAnalyseValidationController.setTypeAndValidate(analyseView, filters, null);
			}
		};
	}
	
	static void clearContents(String calledFrom){
		System.out.println("@@@@@ clearContents calledFrom = "+calledFrom);
		
		ADAMVisibilityController.removeAnalyseViewMenuContent();
			
		RootPanel.get("CENTER").setStyleName("no-border-box");	 
		if (RootPanel.get("CENTER").getWidgetCount() > 0)
		   RootPanel.get("CENTER").remove(RootPanel.get("CENTER").getWidget(0));
		
		RootPanel.get("ANALYSE_TITLE").setStyleName("no-border-box");	 
		if (RootPanel.get("ANALYSE_TITLE").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TITLE").remove(RootPanel.get("ANALYSE_TITLE").getWidget(0));
		
		RootPanel.get("ANALYSE_TOP").setStyleName("no-border-box");	 
		if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
		
		RootPanel.get("ANALYSE_MIDDLE").setStyleName("no-border-box");	 
		if (RootPanel.get("ANALYSE_MIDDLE").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_MIDDLE").remove(RootPanel.get("ANALYSE_MIDDLE").getWidget(0));
		
		RootPanel.get("ANALYSE_BOTTOM").setStyleName("no-border-box");	 
		if (RootPanel.get("ANALYSE_BOTTOM").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_BOTTOM").remove(RootPanel.get("ANALYSE_BOTTOM").getWidget(0));
		
		RootPanel.get("TOP_LEFT").setStyleName("no-border-box");	 
		if (RootPanel.get("TOP_LEFT").getWidgetCount() > 0)
			RootPanel.get("TOP_LEFT").remove(RootPanel.get("TOP_LEFT").getWidget(0));
		
		RootPanel.get("TOP_RIGHT").setStyleName("no-border-box");	 
		if (RootPanel.get("TOP_RIGHT").getWidgetCount() > 0)
			RootPanel.get("TOP_RIGHT").remove(RootPanel.get("TOP_RIGHT").getWidget(0));
		
		RootPanel.get("BOTTOM_LEFT").setStyleName("no-border-box");	 
		if (RootPanel.get("BOTTOM_LEFT").getWidgetCount() > 0)
			RootPanel.get("BOTTOM_LEFT").remove(RootPanel.get("BOTTOM_LEFT").getWidget(0));
		
		RootPanel.get("BOTTOM_RIGHT").setStyleName("no-border-box");	 
		if (RootPanel.get("BOTTOM_RIGHT").getWidgetCount() > 0)
			RootPanel.get("BOTTOM_RIGHT").remove(RootPanel.get("BOTTOM_RIGHT").getWidget(0));
		
		RootPanel.get("MAP_LEFT").setStyleName("no-border-box");	 
		if (RootPanel.get("MAP_LEFT").getWidgetCount() > 0)
			RootPanel.get("MAP_LEFT").remove(RootPanel.get("MAP_LEFT").getWidget(0));
		
		RootPanel.get("MAP_RIGHT").setStyleName("no-border-box");	 
		if (RootPanel.get("MAP_RIGHT").getWidgetCount() > 0)
			RootPanel.get("MAP_RIGHT").remove(RootPanel.get("MAP_RIGHT").getWidget(0));
		
	}
	
	private static void addAnalyseViewMenu(String title, Map<String, String> countries, Map<String, String> donors) {
		ADAMVisibilityController.removeAnalyseViewMenuContent();
		ADAMVisibilityController.restoreAnalyseViewMenuVisibility();
		
		System.out.println("---------------- addAnalyseViewMenu  CALLED !!!");
		
		if(!currentVIEW.equals(ADAMCurrentVIEW.HOME))	{	
			RootPanel.get("ANALYSE_VIEWMENU_TITLE").add(ADAMViewMenuBuilder.buildTitle(title));
			
			if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
				Map<String, String> sectors = ADAMBoxMaker.sectorsSelected;
				Map<String, String> subSectors = ADAMBoxMaker.subSectorsSelected;
				Map<String, String> channels = ADAMBoxMaker.channelsSelected;
				
			    RootPanel.get("ANALYSE_VIEWMENU_CONTENT").add(ADAMViewMenuBuilder.buildDescriptionPanel(countries, donors, sectors, subSectors, channels));
			}
			else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
				Map<String, String> soList = ADAMBoxMaker.soSelected;
				Map<String, String> orList = ADAMBoxMaker.orSelected;
				RootPanel.get("ANALYSE_VIEWMENU_CONTENT").add(ADAMViewMenuBuilder.buildDescriptionPanel(countries, donors, soList, orList, null));
			}
		}	
		
		/*if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
			if (!ADAMDataSourceSelection.isBuild()) {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.build());
			}
			else {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.getPanel());
			}
		}	*/
	}
	/*public static Listener<BaseEvent> getVennView(final Map<String, String> gaulList, final Map<String, String> donorList) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {	
				addCountryPrioritiesVenn(gaulList, donorList);			        	
			}
		};
	}
	
	public static Listener<BaseEvent> getDonorMatrixView(final Map<String, String> gaulList, final Map<String, String> donorList, final HashMap<String, List<String>> filters) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {		
				createDonorMatrixView(donorList, gaulList, filters);	        	
			}
		};
	}
	
public static Listener<BaseEvent> getPriorityMatricesView() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {		
				ADAMVennRecipientMatrixController.buildVennRecipientMatrixAgent(null, true);      	
			}
		};
	}
	
	public static Listener<BaseEvent> getFAOComparativeAdvantageView() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {		
				ADAMFAOComparativeAdvantageController.buildADAMComparativeAdvantageAgent();    	
			}
		};
	}
	
	public static Listener<BaseEvent> getProjectsView() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {		
				//showProjectsFAOView();
			}
		};
	}
	
	public static Listener<BaseEvent> getImplementingAgenciesView() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {		
				ADAMChannelsController.createChannelsViewAgent();  	
			}
		};
	}
	*/
	private static void buildSelectedAnalysisTitlePanel(HashMap<String, List<String>> filters){
		
		VerticalPanel titleHolder = new VerticalPanel();
		titleHolder.removeAll();
		
		String title = analysesPanel.getAnalysisTitle(analyseView);
		String description = analysesPanel.getAnalysisDescription(analyseView, false);
			
		titleHolder.add(ADAMAnalysisListPanel.buildSelectedAnalysisTitleDescriptionPanel(title, description, filters));
			
		if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_VENN)){
			titleHolder.add(ADAMAnalysisListPanel.buildRelatedAnalysisPanel(analysesPanel.getAnalysisTitle(ADAMAnalyseVIEW.ANALYSE_PRIORITIES), analysesPanel.getAnalysisDescription(ADAMAnalyseVIEW.ANALYSE_PRIORITIES, true), filters, ADAMAnalyseVIEW.ANALYSE_PRIORITIES));
		} else if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PRIORITIES)){
			titleHolder.add(ADAMAnalysisListPanel.buildRelatedAnalysisPanel(analysesPanel.getAnalysisTitle(ADAMAnalyseVIEW.ANALYSE_VENN), analysesPanel.getAnalysisDescription(ADAMAnalyseVIEW.ANALYSE_VENN, true), filters, ADAMAnalyseVIEW.ANALYSE_VENN));
		} 
		
			
		RootPanel.get("ANALYSE_TITLE").setStyleName("big-box content");	 
		if (RootPanel.get("ANALYSE_TITLE").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_TITLE").remove(RootPanel.get("ANALYSE_TITLE").getWidget(0));
		RootPanel.get("ANALYSE_TITLE").add(titleHolder);	
	}

	
	
	public static void checkIfAllAsyncCallsAreReturnedThenValidate(final boolean isValidated, final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		timer = new Timer() {
			public void run() {
				if ( tokensAsync != 0 ) {
					System.out.println("---$$$ checkIfAllAsyncCallsAreReturnedThenValidate || tokensAsync: " + tokensAsync + " | tokensReached: " + tokensReached + " | isValidated "+isValidated);

					if ( tokensAsync == tokensReached ) {
						System.out.println(" checkIfAllAsyncCallsAreReturned: STOP tokensAsync = tokensReached ok: ");
						tokensAsync = 0;
						tokensReached = 0;
						timer.cancel();
						
						if(!isValidated) {
							//set the Analyse List
							
							//ADAMAnalyseValidationController.highlightSelectedAnalysis(holder);
									
							System.out.println("checkIfAllAsyncCallsAreReturnedThenValidate isValidated FALSE ");
							ADAMAnalyseValidationController.showSelectionRequirementsMessage(analyseView);		
							
							
						}
						else {
							System.out.println("checkIfAllAsyncCallsAreReturnedThenValidate isValidated TRUE ");
							clearContents("checkIfAllAsyncCallsAreReturnedThenValidate");
							
							//ADAMHistoryController.setHistoryItem(ADAMController.currentVIEW, analyseView.name());
							openAnalyseView(filters);	
							
						}						
					} 
				}
			}
		};
		timer.scheduleRepeating(1000);

	}
}
