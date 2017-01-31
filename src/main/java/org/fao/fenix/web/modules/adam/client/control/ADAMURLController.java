package org.fao.fenix.web.modules.adam.client.control;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.history.ADAMHistory;
import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMTabMenu;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.enums.ADAMURLParameters;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.coding.common.services.CodingServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ADAMURLController  {
	
	// URL example http://fenix.fao.org:8050/fenix-web/adam/ADAM.html?view=ADAMVIEW&recipientCRS=268
	
	/*public static Integer tokensAsync = 0;
	
	public static Integer tokensReached = 0;
	
	private static Timer timer;
	*/
	
	private static boolean isRecipientISO3 = false;
	
	
	/*public static void createView() {

		// this method checks how many asynccalls will be called (from the URL Parameters)
		getTokensAsyncallsCount();
		System.out.println("getTokensAsyncallsCount: tokensAsync: " + tokensAsync);
		
		
		// Switch by the selected view
		List<String> view = ADAM.parameters.get(ADAMURLParameters.view);
		List<String> iso3Codes = ADAM.parameters.get(ADAMURLParameters.recipientISO3);
	
		if(iso3Codes != null)
			isRecipientISO3 = true;
			
		
		if ( view == null ) {
			FenixAlert.error("Error", "The link is not working");
		}
		else {
			System.out.println("VIEW: " + view.get(0));
			
			ADAMCurrentVIEW selectedView = ADAMCurrentVIEW.valueOf(view.get(0));
			
			// build TABS
			ADAMTabMenu.build(selectedView);
			
			switch (selectedView) {
			case ADAMVIEW:
				createURLADAMView();
				break;
			case FAOVIEW:
				createURLFAOView();
				break;
			case ANALYSE_PARTNER_MATRIX:
				createURLMatrixView();
				break;
			default:
				break;
			}
			
		}

	}
	
	// this method checks how many asynccalls will be called (from the parameters)
	private static void getTokensAsyncallsCount() {
		for(ADAMURLParameters filter : ADAM.parameters.keySet()) {
			switch (filter) {
			case recipientCRS:
				tokensAsync = tokensAsync +1;
				break;
			case recipientISO3:
				tokensAsync = tokensAsync +1;
				break;
			case regionFAO:
				tokensAsync = tokensAsync +1;
				break;	
			case partnerCRS:
				tokensAsync = tokensAsync +1;
				break;
				
			case sectorDAC:
				tokensAsync = tokensAsync +1;
				break;
			case SO:
				tokensAsync = tokensAsync +1;
				break;
			default:
				break;
			}
			
		}
	}
	
	private static void createURLADAMView() {
		System.out.println("createADAMView");
		
		// get filters
		setFilters(ADAMCurrentVIEW.ADAMVIEW, ADAMController.selectedTab);
		
		// create and set interface filters
	   // filters();

	    
	    // call the view
		if(!isRecipientISO3)
			checkIfAllTheAsyncCallsAreReturned(ADAMCurrentVIEW.ADAMVIEW, ADAMController.selectedTab);
	}
	
	private static void createURLFAOView() {
		System.out.println("createADAMView");
		
		// get filters
		setFilters(ADAMCurrentVIEW.FAOVIEW, ADAMController.selectedTab);
		
		// create and set interface filters
	   // filters();

	    
	    // call the view
		if(!isRecipientISO3)
	    checkIfAllTheAsyncCallsAreReturned(ADAMCurrentVIEW.FAOVIEW, ADAMController.selectedTab);
	}
	
	private static void createURLMatrixView() {
		System.out.println("createMatrixView");
		
		// get filters
		ADAMAnalyseDataController.analyseView = ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX;
		
		setFilters(ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSE.name());
		
		ADAMQuantityColumn.isBuild = true;
		
		if(!isRecipientISO3)
		    checkIfAllTheAsyncCallsAreReturned(ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSE.name());
	}
	
 public static void createFilteredView(String historyToken, ADAMURLParameters filter) {
		System.out.println("createMatrixView");
		
		// get filters
		//ADAMAnalyseDataController.analyseView = ADAMAnalyseVIEW.ANALYSE_PARTNER_MATRIX;
		
		setFilters(ADAMCurrentVIEW.ADAMVIEW, historyToken);
		
		ADAMQuantityColumn.isBuild = true;
		
		if(!isRecipientISO3)
		    checkIfAllTheAsyncCallsAreReturned(ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSE.name());
	}*/
	
	
/*	// set filters for the server calls
	public static void setFilters(final ADAMCurrentVIEW view, final String selectedTab, ADAMURLParameters filter) {
		
		switch (filter) {
			case recipientCRS:
				//setCountriesFilter();
				break;
				
			case recipientISO3: 				
				setISO3CountriesFilter(view, selectedTab);
				break;	
				
			case regionFAO:
				//setRegionFilter();
				break;
				
			case partnerCRS:
				//setDonorsFilter();
				break;
				
			case sectorDAC:
				//setSectorsFilter();
				break;
			case SO:
				setSO();
				break;
				
			case date:
				//setRangeDate();
				break;
			
			case quantity:
				//setQuantityColumn();
				break;
				
		
			default:
				break;
			}

	}*/
	
	
 /* private static void setFilters(final ADAMCurrentVIEW view, final String selectedTab) {
		
		for(ADAMURLParameters filter : ADAM.parameters.keySet()) {
			switch (filter) {
			case recipientCRS:
				setCountriesFilter();
				break;
				
			case recipientISO3: 				
				setISO3CountriesFilter(view, selectedTab);
				break;	
				
			case regionFAO:
				setRegionFilter();
				break;
				
			case partnerCRS:
				setDonorsFilter();
				break;
				
			case sectorDAC:
				setSectorsFilter();
				break;
			case SO:
				setSO();
				break;
				
			case date:
				setRangeDate();
				break;
			
			case quantity:
				setQuantityColumn();
				break;
				
		
			default:
				break;
			}
			
		}
	}
	
	private static void setRegionFilter(){
		
		List<String> codes = ADAM.parameters.get(ADAMURLParameters.regionFAO);
		
		// set DROP DOWN selection
		if(codes.size() > 1) {
			// multiselection 
			ADAMBoxMaker.selectedCountryFromURL = "MULTI";
		}
		else {
			if(codes.size()  == 1) {
				ADAMBoxMaker.selectedCountryFromURL = "Gaul_" + codes.get(0);
			}
		}
		
		System.out.println("setRegionFilter: " + codes);
		
		ADAMServiceEntry.getInstance().getRecipientsForFAORegion(codes.get(0), new AsyncCallback<Map<String,String>>() {
			public void onSuccess(Map<String, String> recipients) {
				
				System.out.println("recipients: "  +recipients);
				
				ADAMBoxMaker.countriesSelected.clear();

				for(String code: recipients.keySet()) {
					System.out.println("CODE: " + code);
//					ADAMBoxMaker.countriesSelected.put(code, recipients.get(code));
					ADAMBoxMaker.countriesSelected.put("Gaul_"+code, recipients.get(code));
				}


				tokensReached = tokensReached +1;
			}
			
			
			
			public void onFailure(Throwable result) {
				System.out.println("ERROR: " );
				Info.display("Error ", "Error getting Recipients For FAO Region", result.getLocalizedMessage());
				
				tokensReached = tokensReached +1;
			}
		});
		
	}
	
	
	
	private static void setCountriesFilter(){
		
		List<String> codes = ADAM.parameters.get(ADAMURLParameters.recipientCRS);
		
		// set DROP DOWN selection
		if(codes.size() > 1) {
			// multiselection 
			ADAMBoxMaker.selectedCountryFromURL = "MULTI";	
		}
		else {
			System.out.println("recipient: " + codes.get(0));
			if(codes.size()  == 1) {
				ADAMBoxMaker.selectedCountryFromURL = "Gaul_" + codes.get(0);
			}
		}
		
		// getting CRS recipients labels
		CodingServiceEntry.getInstance().getCodes(codes, "CRS_RECIPIENTS", "EN", new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {

				for(CodeVo code : result) {
					System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					ADAMBoxMaker.countriesSelected.put("Gaul_" + code.getCode(), code.getLabel());
				}

				
				System.out.println("ADAMBoxMaker.countriesSelected: " + ADAMBoxMaker.countriesSelected);
				
				tokensReached = tokensReached +1;
			}
			
			public void onFailure(Throwable arg0) {
				
				System.out.println("FAILED: ");
				tokensReached = tokensReached +1;
			}
		});
		
		

	}*/
	
	public static void setISO3CountriesFilter(final ADAMCurrentVIEW view, final String selectedTab, List<String> codes){
		//final List<String> codes = ADAM.parameters.get(ADAMHistory.get.recipientISO3);	
		
		System.out.println("setISO3CountriesFilter: codes = "+codes);
		
		//tokensAsync = tokensAsync +1;
		
		String cs = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			cs = "FPMIS_RECIPIENTS";
		else
			cs = "ADAM_RECIPIENTS";
		
		//Get corresponding CRS recipients labels
		ADAMServiceEntry.getInstance().convertISO3toDatasetCS(codes, cs, "EN", ADAMController.currentlySelectedDatasetCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {

				System.out.println("$$$ setISO3CountriesFilter: result = "+result);
				
				// set DROP DOWN selection
				if(result.size()  == 1) {
					ADAMBoxMaker.countriesSelected.clear();
					
					ADAMBoxMaker.selectedCountryFromURL = "Gaul_" + result.get(0).getCode();
					ADAMBoxMaker.countriesSelected.put("Gaul_" + result.get(0).getCode(), result.get(0).getLabel());
				
					//if(ADAMBoxMaker.selectedCountryFromURL!=null){
					//}
					
					
					System.out.println("setISO3CountriesFilter: ADAMBoxMaker.gaulStore.findModel("+ result.get(0).getCode()+") "+ADAMBoxMaker.gaulStore.findModel("gaulCode", ADAMBoxMaker.selectedCountryFromURL));
					ADAMBoxMaker.selectedCountryFromURL = "Gaul_"+result.get(0).getCode();
					
					System.out.println("$$$ setISO3CountriesFilter: ADAMBoxMaker.selectedCountryFromURL "+ADAMBoxMaker.selectedCountryFromURL);
					System.out.println("$$$ setISO3CountriesFilter: ADAMBoxMaker.countriesSelected (single-select):: " + ADAMBoxMaker.countriesSelected);
					
					//ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulStore.getAt(0)); //just to trigger update
					
					//ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulStore.findModel("Gaul_" +result.get(0).getCode()));
				}
				else {
					if(result.size() > 1){
						ADAMBoxMaker.countriesSelected.clear();
						
						for(CodeVo code : result) {
							System.out.println("code: " + code.getCode() + " | " + code.getLabel());
							ADAMBoxMaker.countriesSelected.put("Gaul_" + code.getCode(), code.getLabel());
						}
						
						System.out.println("$$$ setISO3CountriesFilter: ADAMBoxMaker.countriesSelected (multi-select): " + ADAMBoxMaker.countriesSelected);			

						ADAMBoxMaker.selectedCountryFromURL = "MULTI";					
						//ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulStore.findModel("MULTI"));
						
					}
				}
				
				
				//tokensReached++;
				
				//tokensReached = tokensReached +1;
				
		        // call the view
		      // checkIfAllTheAsyncCallsAreReturned(view, selectedTab);
			}
			
			public void onFailure(Throwable arg0) {
				//tokensReached++;
				System.out.println("FAILED: setISO3CountriesFilter ");
				//tokensReached = tokensReached +1;
			}
		});
		
		

	}
	
	
	/*public static void setISO3CountriesFilter(final ADAMCurrentVIEW view, final String selectedTab){
		final List<String> codes = ADAM.parameters.get(ADAMURLParameters.recipientISO3);	
		
		
		String cs = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			cs = "FPMIS_RECIPIENTS";
		else
			cs = "CRS_RECIPIENTS";
		
		//Get corresponding CRS recipients labels
		ADAMServiceEntry.getInstance().convertISO3toDatasetCS(codes, cs, "EN", ADAMController.currentlySelectedDatasetCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {

				// set DROP DOWN selection
				if(result.size()  == 1) {
					ADAMBoxMaker.selectedCountryFromURL = "Gaul_" + result.get(0).getCode();
					ADAMBoxMaker.countriesSelected.put("Gaul_" + result.get(0).getCode(), result.get(0).getLabel());
				}
				else {
					ADAMBoxMaker.selectedCountryFromURL = "MULTI";
					for(CodeVo code : result) {
						System.out.println("code: " + code.getCode() + " | " + code.getLabel());
						ADAMBoxMaker.countriesSelected.put("Gaul_" + code.getCode(), code.getLabel());
					}

				}
				System.out.println("ADAMBoxMaker.countriesSelected: " + ADAMBoxMaker.countriesSelected);
				
				tokensReached = tokensReached +1;
				
		        // call the view
		       checkIfAllTheAsyncCallsAreReturned(view, selectedTab);
			}
			
			public void onFailure(Throwable arg0) {
				
				System.out.println("FAILED: ");
				tokensReached = tokensReached +1;
			}
		});
		
		

	}
	
	private static void setDonorsFilter(){
		
		List<String> codes = ADAM.parameters.get(ADAMURLParameters.partnerCRS);
		
		// set DROP DOWN selection
		if(codes.size() > 1) {
			// multiselection 
			ADAMBoxMaker.selectedDonorFromURL = "MULTI";
		}
		else {
			if(codes.size()  == 1) {
				ADAMBoxMaker.selectedDonorFromURL = "Donor_" + codes.get(0);
			}
		}
		
		
		// getting CRS labels
		CodingServiceEntry.getInstance().getCodes(codes, "CRS_DONORS", "EN", new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
	
				for(CodeVo code : result) {
					System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					ADAMBoxMaker.donorsSelected.put("Donor_" + code.getCode(), code.getLabel());
				}

				
				System.out.println("ADAMBoxMaker.donorsSelected: " + ADAMBoxMaker.donorsSelected);
				
				tokensReached = tokensReached +1;
			}
			
			public void onFailure(Throwable arg0) {
				
				System.out.println("FAILED: ");
				tokensReached = tokensReached +1;
			}
		});
		
//		TODO: ARE USED THE LABEL?? IF YES, retrieve them 
//		for(String code : codes) {
//			ADAMBoxMaker.donorsSelected.put(code, code);
//		}
//		
//		System.out.println("ADAMBoxMaker.donorsSelected: " + ADAMBoxMaker.donorsSelected);
	}
	
	private static void setSectorsFilter(){
		
		List<String> codes = ADAM.parameters.get(ADAMURLParameters.sectorDAC);
		
		// set DROP DOWN selection
		if(codes.size() > 1) {
			// multiselection 
			ADAMBoxMaker.selectedSectorFromURL = "MULTI";
		}
		else {
			if(codes.size()  == 1) {
				ADAMBoxMaker.selectedSectorFromURL = "Dac_" + codes.get(0);
			}
		}
		
		// getting CRS labels
		CodingServiceEntry.getInstance().getCodes(codes, "DAC", "EN", new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
	
				for(CodeVo code : result) {
					System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					ADAMBoxMaker.sectorsSelected.put("Dac_" + code.getCode(), code.getLabel());
				}

				
				System.out.println("ADAMBoxMaker.sectorsSelected: " + ADAMBoxMaker.sectorsSelected);
				
				tokensReached = tokensReached +1;
			}
			
			public void onFailure(Throwable arg0) {
				
				System.out.println("FAILED: ");
				tokensReached = tokensReached +1;
			}
		});
	}
	
	private static void setSO(){
		
		List<String> codes = ADAM.parameters.get(ADAMURLParameters.SO);
		
		// set DROP DOWN selection
		//TODO: the SO are not in a multiselection  mode 
		if(codes.size() > 1) {
			FenixAlert.info("Multiselection", "The SOs are not yet in a multiselection mode");
			// multiselection 
			ADAMBoxMaker.selectedSOFromURL = "MULTI";
		}
		else {
			if(codes.size()  == 1) {
				ADAMBoxMaker.selectedSOFromURL = "SO_" + codes.get(0);
			}
		}
		
		
		// getting CRS recipients labels
		CodingServiceEntry.getInstance().getCodes(codes, "FAO_SO", "EN", new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
	
				for(CodeVo code : result) {
					System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					ADAMBoxMaker.soSelected.put("SO_" + code.getCode(), code.getLabel());
				}

				
				System.out.println("ADAMBoxMaker.soSelected: " + ADAMBoxMaker.soSelected);
				
				tokensReached = tokensReached +1;
			}
			
			public void onFailure(Throwable arg0) {
				
				System.out.println("FAILED: ");
				tokensReached = tokensReached +1;
			}
		});
	}
*/
	private static void setQuantityColumn(){
		/** TODO **/
		
	}
	
	/*private static void setRangeDate(){
		
		List<String> dates = ADAM.parameters.get(ADAMURLParameters.date);
		
		String date = dates.get(0);
		String fromDate = null;
		String toDate = null;
		
		
		if ( date.contains("-")) {
			fromDate = "01-01-" + date.substring(0, date.indexOf("-"));
			System.out.println("fromdate: " + fromDate);
			
			toDate =  "01-01-" + date.substring(date.indexOf("-") +1);
			System.out.println("toDate: " + toDate);
		}
		else {
			fromDate =  "01-01-" + date;
			toDate =   "01-01-" + date;
		}
		
		changeReferencePeriod(fromDate, toDate);

	}
	
	public static void changeReferencePeriod(final String from, final String to) {

			Date fromDate = FieldParser.parseDate(from);
			Date toDate =  FieldParser.parseDate(to);

			// error message
			if ( fromDate.compareTo(toDate) > 0 ) {
				FenixAlert.error("Select date", ("Date selection is wrong"));
//				Window.alert("Date selection is wrong");

			}
			else if ( fromDate.compareTo(toDate) == 0 ) {
				baseDate.clear();
			baseDate.add(to);

			}
			else if ( fromDate.compareTo(toDate) < 0 ) {
				baseDate.clear();

				while( fromDate.compareTo(toDate) <= 0 ) {
					System.out.println("dates: " + fromDate + " | " + toDate);

					baseDate.add(FieldParser.formatDate(fromDate, "formatterMinusReverse"));

					fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
				}

			}

			System.out.println("basedate: " + baseDate);
			
			// SETTING the ComboBox parameter
			ADAMReferencePeriodPanel.fromDateURLParameter = from;		
			ADAMReferencePeriodPanel.toDateURLParameter = to;
	}
	
	
	
	public static void checkIfAllTheAsyncCallsAreReturned(final ADAMCurrentVIEW view, final String selectedTab) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					*//** TODO CHANGE+ THE VIEW **//*
					//callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, view);
					callViewAgent(view, "checkIfAllTheAsyncCallsAreReturned", selectedTab);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(1000);

	
	}
	
	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}*/
	
}