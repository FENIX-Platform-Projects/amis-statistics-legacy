package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.history.ADAMHistoryController;
import org.fao.fenix.web.modules.adam.client.view.ADAMDataSourceSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.analyse.ADAMAnalysisListPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DatasetModel;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMAnalyseValidationController extends ADAMAnalyseDataController {
	
	public static Listener<BaseEvent> setTypeAndValidateListener(final ADAMAnalyseVIEW type, final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {	
				ClickHtml html = (ClickHtml)ce.getSource();
				//Highlight the text, to indicate it has been selected
				html.setStyleAttribute("color", "white");
				
				setTypeAndValidate(type, filters, holder);
				
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> setTypeAndValidateSelectionListener(final ADAMAnalyseVIEW type, final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {	
				setTypeAndValidate(type, filters, holder);
			}
		};
	}
	
	public static void setTypeAndValidate(final ADAMAnalyseVIEW type, final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		System.out.println("########## 2 setTypeAndValidate ........type "+type);
		
		    analyseView = type;
			validate(filters, "setTypeAndValidate", holder);
	}
	
	public static void validate(final HashMap<String, List<String>> filters, String calledFrom, final ADAMAnalysisListPanel holder) {
		//System.out.println("!!!! MAIN validate() CALLED FROM "+calledFrom+ " !!!tokensAsync = " + tokensAsync +" | "+ tokensReached);
		System.out.println("########## 3 validate() ........calledFrom "+calledFrom);
		
		//Following views cannot be loaded when FPMIS dataset has been selected, so before the error message is displayed, the combo boxes need
		// to be loaded first
		if(analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX) || analyseView.equals(ADAMAnalyseVIEW.ANALYSE_FAO_COMPARATIVE_ADVANTAGE) || analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PROJECTS)){	
			
			System.out.println("########## 4 validate() ........analyseView "+analyseView);
			
			
			if (RootPanel.get("ANALYSE_TOP").getWidgetCount() > 0)
				RootPanel.get("ANALYSE_TOP").remove(RootPanel.get("ANALYSE_TOP").getWidget(0));
			
			createAnalysisItems(filters);
			
			if(tokensAsync == 0 && tokensReached == 0)
				 standardValidation(filters, holder);
			else if(tokensAsync == tokensReached)
				 standardValidation(filters, holder);
			else	
				validateAfterCallsCompleted(filters, holder);
			
		}	
		else {
			tokensAsync = 0;
			tokensReached = 0;
            if(timer!=null)
            	timer.cancel();
            
            standardValidation(filters, holder);
		}	
    }
	
	private static void standardValidation(final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		if(isValidated()) {
			clearContents("standardValidation");
		
			ADAMHistoryController.setHistoryItem(ADAMController.currentVIEW, analyseView.name());
			//openAnalyseView(filters);	
		}
		else {	
			//highlightSelectedAnalysis(holder);
			showSelectionRequirementsMessage(analyseView);				
		}		
    }
	
	
	
	/*static void highlightSelectedAnalysis(ADAMAnalysisListPanel holder) {
		for(ADAMAnalyseVIEW key: holder.analysesMap.keySet()){
			
			Component comp = holder.panel.getItemByItemId(key.name());
			HorizontalPanel labelHolder = (HorizontalPanel)comp;
			
			if(labelHolder!=null){
			Component labelComp = labelHolder.getItemByItemId(key.name()+"_LABEL");
			Component descComp = labelHolder.getItemByItemId(key.name()+"_DESC");
			
			if(key!=analyseView){
				if(labelComp!=null)
					labelComp.setStyleAttribute("color", "#1B65A4");
				if(descComp!=null)
					descComp.setStyleAttribute("color", "#474747");
			} else { //highlighted red
				if(labelComp!=null)
					labelComp.setStyleAttribute("color", "red");
				if(descComp!=null)
					descComp.setStyleAttribute("color", "red");
			}
			}
		}

	}
	*/
	


	private static void validateAfterCallsCompleted(final HashMap<String, List<String>> filters, final ADAMAnalysisListPanel holder) {
		System.out.println("--- validateAfterCallsCompleted ");
		if (tokensAsync > 0)
			checkIfAllAsyncCallsAreReturnedThenValidate(isValidated(), filters, holder);
    }
	
	public static boolean isValidated(){
		
		boolean isValidated = false;
			
		switch (analyseView) {
		case ANALYSE_VENN: 
			if(!isGlobalSelected(ADAMBoxMaker.countriesSelected)) {
				isValidated = true;
				break;
			} break;	
		case ANALYSE_PRIORITIES:
			if(!isGlobalSelected(ADAMBoxMaker.countriesSelected)) {
				isValidated = true;
				break;
			} break;
		case ANALYSE_FAO_COMPARATIVE_ADVANTAGE: 
			if(!isFPMISDatasetSelected()) {
				isValidated = true;
				break;
			} break;
		case ANALYSE_IMPLEMENTING_AGENCIES: 
			if(!isFPMISDatasetSelected()) {
				isValidated = true;
				break;
			} break;
		case ANALYSE_PROJECTS: 
			if(isFPMISDatasetSelected()) {
				isValidated = false;
				break;
			} else if(isFAOView()) {
				isValidated = false;
				break;
			} else if(isTooManySelections()){
				isValidated = false;
				break;
			} else {
				isValidated = true;
				break;
			}
		case ANALYSE_PARTNER_MATRIX: 
			if(isFPMISDatasetSelected()) {
				isValidated = false;
				break;
			} else if(isFAOView()) {
				isValidated = false;
				break;
			} else {
				isValidated = true;
				break;
			} 
		}
		System.out.println("isValidated() = "+isValidated);
		
		return isValidated;
	}
	
	private static Boolean isFPMISDatasetSelected(){
		boolean fpmisDatasetSelected = false;

		DatasetModel currentlySelectedDataset = ADAMDataSourceSelection.datasetCombo.getValue();

		if(currentlySelectedDataset.getCode().equals(ADAMSelectedDataset.ADAM_FPMIS.name())){
			fpmisDatasetSelected = true;
		}

		else {
			fpmisDatasetSelected = false;
		}

		return fpmisDatasetSelected;
	}

	private static Boolean isTooManySelections(){

		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
	
		if( isGlobalSelected(donorList) && isGlobalSelected(gaulList)) 
			return true;
		else
			return false;
	}
	
	private static Boolean isFAOView(){
		boolean isFAOView = false;
	
		if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
			isFAOView = true;
		}	
		
		return isFAOView;
	}
	
	public static void showSelectionRequirementsMessage(ADAMAnalyseVIEW analyseView){
		MessageBox box = new MessageBox();
		box.setMinWidth(550);
		box.setModal(false);
		box.setIcon(MessageBox.INFO);
		
		switch (analyseView) {
		case ANALYSE_VENN: 
			box.setTitle("Venn Analysis Requirements");
			box.setMessage(atLeastOneCountryMessage());
		    break;
		case ANALYSE_PRIORITIES: 
			 box.setTitle("Priority Analysis Requirements");
			 box.setMessage(atLeastOneCountryMessage());
			 break;
		case ANALYSE_FAO_COMPARATIVE_ADVANTAGE: 
			 box.setTitle("FAO Comparative Advantage Requirements");
			 box.setMessage(onlyOECDDataSourceMessage());
			 break;
		case ANALYSE_PROJECTS: 
			 box.setTitle("View Projects Requirements");
			 if(isFPMISDatasetSelected()) {
				 box.setMessage(onlyOECDDataSourceMessage());
				 break;
			 } 
			 if(isFAOView()) {
				 box.setMessage(onlyDacClassificationMessage());
				 break;
			 }
			 if(isTooManySelections()) {
				 box.setMessage("Selecting all Recipient Countries and all Resource Partners would result in too many projects for display, please refine your selection.");
				 break;		
			 } 
		case ANALYSE_IMPLEMENTING_AGENCIES: 
			 box.setTitle("Implementing Agencies Analysis Requirements");
			 box.setMessage(onlyOECDDataSourceMessage());
			break;	
		case ANALYSE_PARTNER_MATRIX: 
			 box.setTitle("Resource Partner Matrix Requirements");
			 if(isFPMISDatasetSelected()) {
				 box.setMessage(onlyOECDDataSourceMessage());
				 break;
			 } 
			 if(isFAOView()) {
				 box.setMessage(onlyDacClassificationMessage());
				 break;
			 }
		}
		
		
		box.show();
	  }

	
	public static void disableSelectors(ADAMAnalyseVIEW analyseView){

		switch (analyseView) {
		case ANALYSE_VENN: 
			disableSectorRelatedLists();
			ADAMQuantityColumn.list.disable();
			break;
		case ANALYSE_PRIORITIES: 
			disableSectorRelatedLists();
			ADAMQuantityColumn.list.disable();			 
			break;
		case ANALYSE_FAO_COMPARATIVE_ADVANTAGE: 
			disableSectorRelatedLists();
			disableDonorList();	
			ADAMQuantityColumn.list.disable();
			disableDataSourceList();
			break;
		case ANALYSE_PROJECTS: 
			ADAMQuantityColumn.list.disable();
			disableClassificationList();
			disableDataSourceList();
			break;		
		case ANALYSE_IMPLEMENTING_AGENCIES: 
			disableDataSourceList();
			break;	
		case ANALYSE_PARTNER_MATRIX: 
			disableSectorRelatedLists();
			ADAMQuantityColumn.list.disable();
			disableClassificationList();
			disableDataSourceList();
			break;	
		}
	}
	
	private static void disableSectorRelatedLists(){
		
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
			if(!ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
				Map<String, String> selectedSectorItems = ADAMBoxMaker.sectorsSelected;
				
				// If already FAO Related Sectors, don't set value
				if(selectedSectorItems.size() > 1 || !selectedSectorItems.containsKey("Dac_9999")) {
					GaulModelData model = ADAMBoxMaker.sectorStore.findModel("gaulCode", "Dac_9999");
					ADAMBoxMaker.sectorList.setValue(model);
				}	
			}

			Map<String, String> selectedSubSectorItems = ADAMBoxMaker.subSectorsSelected;
			
			// If already global, don't set value
			if(selectedSubSectorItems.size() > 1 || !isGlobalSelected(selectedSubSectorItems)) {
				ADAMBoxMaker.subSectorList.setValue(ADAMBoxMaker.subSectorStore.getAt(0));
			}

			ADAMBoxMaker.sectorList.disable();
			ADAMBoxMaker.subSectorList.disable();
		}	
		else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
			Map<String, String> selectedSOsItems = ADAMBoxMaker.soSelected;
    		Map<String, String> selectedOrsItems = ADAMBoxMaker.orSelected;
			
    		// If already global, don't set value
			if(selectedSOsItems.size() > 1 || !isGlobalSelected(selectedSOsItems)) {
				ADAMBoxMaker.soList.setValue(ADAMBoxMaker.soStore.getAt(0)); 
			}
			// If already global, don't set value
			if(selectedOrsItems.size() > 1 || !isGlobalSelected(selectedOrsItems)) {
				ADAMBoxMaker.orList.setValue(ADAMBoxMaker.orStore.getAt(0));
			}
			
			ADAMBoxMaker.soList.disable();
			ADAMBoxMaker.orList.disable();
		}
	}
	
	private static void disableDonorList(){

		Map<String, String> selectedDonorItems = ADAMBoxMaker.donorsSelected;

		// If already global, don't set value
		if(selectedDonorItems.size() > 1 || !isGlobalSelected(selectedDonorItems)) {
			ADAMBoxMaker.donorList.setValue(ADAMBoxMaker.donorStore.getAt(0));
		}

		ADAMBoxMaker.donorList.disable();	

	}

	private static void disableDataSourceList(){

		// If already OECD, don't set value
		if(!ADAMDataSourceSelection.datasetCombo.getValue().getCode().equals(ADAMSelectedDataset.ADAM_CRS.name())){
			ADAMDataSourceSelection.datasetCombo.setValue(ADAMDataSourceSelection.datasetStore.getAt(0));	
		}

		ADAMDataSourceSelection.datasetCombo.disable();

	}

	private static void disableClassificationList(){

		
		// If already DAC, don't set value
		if(ADAMSwitchClassificationPanel.classificationCheckBox.getValue()) { // is FAO SF
			ADAMSwitchClassificationPanel.classificationCheckBox.setValue(false); //false = OECD-DAC 
		} 
		
		/*if(!ADAMSwitchClassificationPanel.classificationList.getValue().getGaulCode().equals("DAC")){
			ADAMSwitchClassificationPanel.classificationList.setValue(ADAMSwitchClassificationPanel.classificationStore.getAt(0));    
		}*/

		ADAMSwitchClassificationPanel.classificationCheckBox.disable();
	}



	private static Boolean isGlobalSelected(final Map<String, String> selectedItems) {
	
		Boolean added = true;	
		
		for(String key : selectedItems.keySet()) {
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = false;
				break;
			}
		}
		
		return added;
	}
	
	private static String atLeastOneCountryMessage(){
		return "This analysis requires <b> at least 1 Recipient Country selection</b>. Please select a Recipient Country.";
	}
	
	private static String onlyOECDDataSourceMessage(){
		return "This analysis can <b>only</b> be applied to the <b>OECD-Creditor Reporting System (CRS) data source</b>. Please change the data source.";
	}
	
	private static String onlyDacClassificationMessage(){
		return "This analysis can <b>only</b> be applied to the <b>OECD-DAC Sector classification</b>. Please change the classification.";
	}
	
	
}
