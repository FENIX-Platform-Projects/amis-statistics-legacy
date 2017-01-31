package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

import com.google.gwt.user.client.ui.RootPanel;


public class ADAMFAOViewController extends ADAMViewController {
	
	public static void callFAOView() {
		
		System.out.println("^^^^^^^^^^^^ CALL FAO VIEW ^^^^^^^^^^^^");
		
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> soList = ADAMBoxMaker.soSelected;
		Map<String, String> orList = ADAMBoxMaker.orSelected;

		
	    removeAllContents(false);
        HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		
		 HashMap<String, String> sos = getSO(soList);
		 Boolean soAdded = !sos.isEmpty();		 
		 
		 HashMap<String, String> ors = getSO(orList);
		 Boolean orAdded = !ors.isEmpty();
		
		 System.out.println("callFAOView: countryAdded: " + countryAdded + " | donorAdded: " + donorAdded+ " | soAdded: " + soAdded + " | orsAdded "+orAdded); 
		 System.out.println("callFAOView: filters: " + filters);

		 if( donorAdded && !countryAdded && !soAdded && !orAdded) {
			 createDonorFAOView(donorList, filters);
		 }

		 else if( countryAdded && !donorAdded && !soAdded && !orAdded) {
			 createCountryViewFAOView(gaulList, filters);
		 }

		 else if( countryAdded && donorAdded && !soAdded && !orAdded) {
			 createCountryDonorFAOView(donorList, gaulList, filters);
		 }
		 
		 else if( !countryAdded && !donorAdded && soAdded && !orAdded) {
			 createSOFAOView(sos);
		 }
		 
		 else if( countryAdded && !donorAdded && soAdded && !orAdded) {
			 createCountrySOFAOView(gaulList, sos, filters);
		 }
		 
		 else if( !countryAdded && donorAdded && soAdded && !orAdded) {
			 createDonorSOFAOView(donorList, sos, filters);
		 }
		 
		 else if( countryAdded && donorAdded && soAdded && !orAdded) {
			 createCountryDonorSOFAOView(gaulList, donorList, sos, filters);
		 }
		 
		 /* OR views */
		 else if ( !donorAdded && !countryAdded && !soAdded && orAdded) {
			 ADAMORViewController.createORView(ors, filters);
		 }
		 
		/* country/OR */
		 else if ( countryAdded && !donorAdded && !soAdded && orAdded) {
			 ADAMORViewController.createCountryORView(gaulList, ors, filters);
		 }
		 
		 /* donor/OR */
		 else if ( !countryAdded && donorAdded && !soAdded && orAdded) {
			 ADAMORViewController.createDonorORView(donorList, ors, filters);
		 }
		 
		 /* SO/OR */
		 else if (  !countryAdded && !donorAdded && soAdded && orAdded) {
			 ADAMORViewController.createSoOrView(sos, ors, filters);
		 }
		 
		 /* country/donor/OR*/
		 else if (  countryAdded && donorAdded && !soAdded && orAdded) {
			 ADAMORViewController.createCountryDonorORView(gaulList, donorList, ors, filters);
		 }
		 /* country/SO/OR*/
		 else if ( countryAdded && !donorAdded && soAdded && orAdded ) {
			 ADAMORViewController.createCountrySoOrView(gaulList, sos, ors, filters);
		 }
		 
		 /*donor/SO/OR*/		 
		 else if ( !countryAdded && donorAdded && soAdded && orAdded) {
			 ADAMORViewController.createDonorSoOrView(donorList, sos, ors, filters);
		 }
		 
		 /** country/donor/sector/OR**/
		 else if ( countryAdded && donorAdded && soAdded && orAdded ) {
			 ADAMORViewController.createCountryDonorSoOrView(gaulList, donorList, sos, ors, filters);
		 }
		 
		 else if( !countryAdded && !donorAdded && !soAdded && !orAdded) {
			 /*** global view **/
			 globalAgentFAO();
		 }
		 
		 ADAMVisibilityController.restoreAdamViewVisibility();
	}
	
	public static HashMap<String, String> getSO(final Map<String, String> soList) {
		HashMap<String, String> so = new HashMap<String, String>();

		for(String key : soList.keySet()) {
			String code = key;
			String label = soList.get(key);
			System.out.println("gaulCode " + code);
			System.out.println("gaulLabel " + label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				code = getCode(code);
				so.put(code, label);
			}
		}
	
		return so;

	}
	
	
	public static void globalAgentFAO() {
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

		removeBoxes();
		//removeQuestions();
		//filters();
		addViewMenu(globalTitle, null, null, null, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		
		// TOP_LEFT
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SOs)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Ten Resource Partners (by SO)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);

		// BOTTOM_LEFT
		addCountryMap(null, new HashMap<String, List<String>>(), ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false);
		//ADAMMapController.addGoogleMapsFAOView("Budget by Country - ", new HashMap<String, List<String>>(), ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), objectWindowId, true, false);
		
		// BOTTOM_RIGHT
		ADAMQueryVO topORsChart = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.BAR.name(), "Top Organizational Results", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), ADAMBoxContent.RETURN_OR.toString(), new HashMap<String, String>());
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topORsChart, objectWindowId);
		
	
	} 
	
	
	
	
	private static void createDonorFAOView(final Map<String, String> map, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		System.out.println("createDonorView objectWindowId: " + objectWindowId );

		String label = new String();
		List<String> codes = new ArrayList<String>();

		for(String code : map.keySet()) {
			codes.add(getCode(code));
			label = label + " " + map.get(code);
		}

		addViewMenu("Donor View:", null, map, null, null, null);


		// TOP_LEFT
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
		

		// TOP_RIGHT
		ADAMQueryVO favoriteChannels = ADAMQueryVOBuilderFAO.favouriteChannelsFAOView("Channels of Delivery in SO", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>());
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), favoriteChannels, objectWindowId);


		// MAP_LEFT
		addCountryMap(null, filters, ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false);
		//ADAMMapController.addGoogleMapsFAOView("Budget by Country - ",filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), objectWindowId, true, false);

		
		// MAP_RIGHT
		ADAMQueryVO topORsChart = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.BAR.name(), "Top Organizational Results", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), ADAMBoxContent.RETURN_OR.toString(), new HashMap<String, String>());
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topORsChart, objectWindowId);
		
	}
	
	
	private static void createCountryViewFAOView(final Map<String, String> map, HashMap<String, List<String>> filters){
		ADAMController.containsMaps = false;

		objectWindowId = objectWindow.getNext();

		System.out.println("createCountryView objectWindowId: " + objectWindowId );

		String label = new String();
		List<String> codes = new ArrayList<String>();

		for(String code : map.keySet()) {
			String c = getCode(code);
			codes.add(c);
			label = label + " " + map.get(code);
		}


		addViewMenu("Country View:", map, null, null, null, null);

		// TOP_LEFT
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
		
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Ten Resource Partners (by SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);

		// MAP_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.BAR.name(), "Top Organizational Results", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), ADAMBoxContent.RETURN_OR.toString(), new HashMap<String, String>());
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		


		// BOTTOM_LEFT
		addCountryMap(map, filters, ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false);
		

		/*Map<String, String> cleanMap = getCleanMap(map);


		HashMap<String, String> setA = new HashMap<String, String>();
		HashMap<String, String> setB = new HashMap<String, String>();
		HashMap<String, String> setC = new HashMap<String, String>();
		setA.putAll(cleanMap);
		setB.put("41301", "FAO");
//		setC.put("filter_all", "Other Donors");
		
		String setAtitle = "Recipients";
		String setAHoverTitle = "Government";
		
		String setBtitle = "FAO";
		String setBHoverTitle = "CPF";
		
//		if ( cleanMap.size() == 1) {
//			for(String key : cleanMap.keySet())
//				setAtitle = cleanMap.get(key);
//		} 
			
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, "Resource Partners", setAHoverTitle, setBHoverTitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
*/
	}
	
	private static void createCountryDonorFAOView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = false;

		objectWindowId = objectWindow.getNext();

		System.out.println("createCountryDonorView objectWindowId: " + objectWindowId );

		String donorLabel = new String();
		String gaulLabel = new String();
		List<String> codes = new ArrayList<String>();

		for(String code : donorList.keySet()) {
			String c = getCode(code);
			codes.add(c);
			donorLabel = donorLabel + " " + donorList.get(code);
		}

		for(String code : gaulList.keySet()) {
			String c = getCode(code);
			codes.add(c);
			gaulLabel = gaulLabel + " " + gaulList.get(code);
		}

		/*** TODO: UPDATE KEY ***/

		addViewMenu("Country/Donor View:", gaulList, donorList, null, null, null);

		// TOP_LEFT
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.BAR.name(), "Top Strategic Objectives (SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), ADAMConstants.faoSO);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
		
		
		// TOP_RIGHT
		ADAMQueryVO favoriteChannels = ADAMQueryVOBuilderFAO.favouriteChannelsFAOView("Channels of Delivery (in SO)", filters,  baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>());
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), favoriteChannels, objectWindowId);


		// MAP_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.BAR.name(), "Top Organizational Results", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.FILTER_ALL_SO.toString(), ADAMBoxContent.RETURN_OR.toString(), new HashMap<String, String>());
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);
		
		

		
		// BOTTOM_LEFT
		addCountryMap(gaulList, filters, ADAMBoxContent.FILTER_ALL_SO.toString(), new HashMap<String, String>(), true, false);
	
		
		/*
		Map<String, String> countries = getCleanMap(gaulList);
		
		Map<String, String> donors = getCleanMap(donorList);


		HashMap<String, String> setA = new HashMap<String, String>();
		HashMap<String, String> setB = new HashMap<String, String>();
		HashMap<String, String> setC = new HashMap<String, String>();
		setA.putAll(countries);
		setB.put("41301", "FAO");
		setC.putAll(donors);
		
		String setAtitle = "Recipients";
		String setAHoverTitle = "Government";
		
		String setBtitle = "FAO";
		String setBHoverTitle = "CPF";

//		if ( countries.size() == 1) {
//			for(String key : countries.keySet())
//				setAtitle = countries.get(key);
//		} 
		
		String setCtitle = "Resource Partners";
//		if ( donors.size() == 1) {
//			for(String key : donors.keySet())
//				setBtitle = donors.get(key);
//		} 
		
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, setCtitle,  setAHoverTitle, setBHoverTitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);*/

	}
	

	
	private static void createSOFAOView(final HashMap<String, String> soList){
		
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

	//	System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
		//removeQuestions();
		filters();
		addViewMenu("FAO Strategic Framework View:", null, null, soList, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		
		// TOP_LEFT
//		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Most Financed Sectors", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 5);	
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
	
		
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Ten Resource Partners (by SO)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_BY_SO.toString(), soList, true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		// BOTTOM_LEFT
		ADAMMapController.addGoogleMapsFAOView("Budget by SO - ",new HashMap<String, List<String>>(), ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_BY_SO.toString(), soList, objectWindowId, true, false);

		
		// BOTTOM_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_OR_DONOR.name(), "Organizational Results breakdown", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true);
		toORsChart.setPriorities(soList);
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);

	}
	
	
	private static void createCountrySOFAOView(final Map<String, String> gaulList, final HashMap<String, String> soList, final HashMap<String, List<String>> filters){
		
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

		//System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
		//removeQuestions();
		filters();
		addViewMenu("Recipient Country/FAO Strategic Framework View:", gaulList, null, soList, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		
		// TOP_LEFT
//		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Most Financed Sectors", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 5);	
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
	
		
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Resource Partners (by SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_BY_SO.toString(), soList, true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		// BOTTOM_LEFT
		ADAMMapController.addGoogleMapsFAOView("Budget by SO - ",filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_BY_SO.toString(), soList, objectWindowId, true, false);

		
		// BOTTOM_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_OR_DONOR.name(), "Organizational Results breakdown",filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true);
		toORsChart.setPriorities(soList);
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);

	}
	
	
	private static void createDonorSOFAOView(final Map<String, String> donorList, final HashMap<String, String> soList, final HashMap<String, List<String>> filters){
		
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

		//System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
		//removeQuestions();
		filters();
		addViewMenu("Resource Partner/FAO Strategic Framework View:", null, donorList, soList, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		
		// TOP_LEFT
//		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Most Financed Sectors", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 5);	
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
	
		
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Resource Partners (by SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_BY_SO.toString(), soList, true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		// BOTTOM_LEFT
		ADAMMapController.addGoogleMapsFAOView("Budget by SO - ",filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_BY_SO.toString(), soList, objectWindowId, true, false);

		
		// BOTTOM_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_OR_DONOR.name(), "Organizational Results breakdown",filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true);
		toORsChart.setPriorities(soList);
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);

	}
	
	
	
	private static void createCountryDonorSOFAOView(final Map<String, String> gaulList, final Map<String, String> donorList, final HashMap<String, String> soList, final HashMap<String, List<String>> filters){
		
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

		//System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
	//	removeQuestions();
		filters();
		addViewMenu("Recipient Country/Resource Partner/FAO SO View:", gaulList, donorList, soList, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		
		// TOP_LEFT
//		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Most Financed Sectors", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 5);	
		ADAMQueryVO topSOsChart = ADAMQueryVOBuilderFAO.viewSOs(ADAMBoxContent.TIMESERIE.name(), "Time series of Stategic Objective (SO)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  ADAMBoxContent.RETURN_SO.toString(), soList);
		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSOsChart, objectWindowId);
	
		
		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.name(), "Top Resource Partners (by SO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.FILTER_BY_SO.toString(), soList, true, false, 10);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		// BOTTOM_LEFT
		ADAMMapController.addGoogleMapsFAOView("Budget by SO - ",filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.FILTER_BY_SO.toString(), soList, objectWindowId, true, false);

		
		// BOTTOM_RIGHT
		ADAMQueryVO toORsChart = ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.BAR_STACK_OR_DONOR.name(), "Organizational Results breakdown",filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true);
		toORsChart.setPriorities(soList);
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), toORsChart, objectWindowId);

	}


	private static void addCountryMap(Map<String, String> gaulList, HashMap<String, List<String>> filters, String returnType, HashMap<String, String> priorities, boolean isSO, boolean isOr) {
        String title = "Budget by Country ";
		
		if(gaulList!=null && !gaulList.isEmpty() && gaulList.size() == 1)
			title += "Sub-Regional Context - ";
		else
			title += "- ";
		
		/*if(gaulList!=null && !gaulList.isEmpty() && gaulList.size() == 1)
			title = "Budget of "+ ADAMViewMenuBuilder.getUnFormattedDescription(gaulList) + " in the Sub-Regional Context - ";
		else
			title = "Budget by Country - ";*/
		
		ADAMQueryVO q = ADAMMapController.getGoogleMapsFAOViewVO(title, filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, returnType, priorities, objectWindowId, isSO, isOr);
	
		ADAMQueryVO c = ADAMORViewController.createCountriesChartVO(gaulList, priorities, filters, q);	
		
		q.getSwitchResources().add(c);
		c.getSwitchResources().add(q);
		
		ADAMMapController.addGoogleMaps(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true,  objectWindowId);
			
	}
	
}
