package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;



public class ADAMViewController extends ADAMController {
	
	// Sub Sector/OR Label
	public static String subSectorORLabel = "Sub-Sectors";
	public static String sectorSOLabel = "Sectors";


	public static void callADAMView() {
		
		 
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
		Map<String, String> subSectorsList = ADAMBoxMaker.subSectorsSelected;
		Map<String, String> channelList = ADAMBoxMaker.channelsSelected;
				
		
		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		// System.out.println("countryAdded: " + countryAdded);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		// System.out.println("donorAdded: " + donorAdded);
		 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		// System.out.println("sectorAdded: " + sectorAdded);
		 Boolean subSectorAdded =  addSubSectorFilter(filters, subSectorsList);
		// System.out.println("subSectorAdded: " + subSectorAdded);
		 
		 System.out.println("callADAMView: countryAdded: " + countryAdded + " | donorAdded: " + donorAdded+ " | sectorAdded: " + sectorAdded + " | subSectorAdded "+subSectorAdded); 					
			
		 Boolean channelAdded =  addChannelFilter(filters, channelList);
		// System.out.println("channelAdded: " + channelAdded);

		 System.out.println("filters: " + filters);
		
		// if(sectorAdded) {
		//	 faoSectorAdded = checkFAOSectorSelection(sectorList);
		// }
		
		 ADAMVisibilityController.restoreAdamViewVisibility();

		 /** donor **/
		 
		 if( donorAdded && !countryAdded && !sectorAdded && !subSectorAdded && !channelAdded ) {
			 createDonorView(donorList, filters);
		 }

		 /** country **/
		 
		 else if( countryAdded && !donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryView(gaulList, filters);
		 }
		 
		 /** country/donor **/

		 else if( countryAdded && donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryDonorView(donorList, gaulList, filters);
		 }

		 /** sector **/
		 
		 else if ( !donorAdded && !countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createSectorView(sectorList, filters);
		 }
		 
		 /** country/sector **/
		 
		 else if (!donorAdded && countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountrySectorView(gaulList, sectorList, filters);
		 }
		 
		 /** donor/sector **/
		 else if (donorAdded && !countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createDonorSectorView(donorList, sectorList, filters);
		 }
		 
		 /** Country/donor/sector **/		 
		 else if (donorAdded && countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryDonorSectorView(gaulList, donorList, sectorList, filters);
		 }
		 
		 /** sub sector **/
		 else if ( !donorAdded && !countryAdded && !sectorAdded && subSectorAdded && !channelAdded) {
			 ADAMSubSectorViewController.createSubSectorView(subSectorsList, filters);
		 }
		 
		 /** country/sub sector **/
		 else if ( countryAdded && !donorAdded && !sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createCountrySubSectorView(gaulList, subSectorsList, filters);
		 }
		 
		 /** donor/sub sector **/
		 else if ( !countryAdded && donorAdded && !sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createDonorSubSectorView(donorList, subSectorsList, filters);
		 }
		  /** sector/sub sector **/
		 else if ( !countryAdded && !donorAdded && sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createSectorSubSectorView(sectorList, subSectorsList, filters);
		 }
		 /** country/donor/sub sector **/
		 else if ( countryAdded && donorAdded && !sectorAdded && subSectorAdded  && !channelAdded ) {
			 ADAMSubSectorViewController.createCountryDonorSubSectorView(gaulList, donorList, subSectorsList, filters);
		 }
		 /**  country/sector/sub sector **/
		 else if ( countryAdded && !donorAdded && sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createCountrySectorSubSectorView(gaulList, sectorList, subSectorsList, filters);
		 }
		 /**donor/sector/sub sector**/
		 else if ( !countryAdded && donorAdded && sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createDonorSectorSubSectorView(donorList, sectorList, subSectorsList, filters);
		 }
		 /** country/donor/sector/subsector**/
		 else if ( countryAdded && donorAdded && sectorAdded && subSectorAdded && !channelAdded ) {
			 ADAMSubSectorViewController.createCountryDonorSectorSubSectorView(gaulList, donorList, sectorList, subSectorsList, filters);
		 }
		 
		 
		 // channels (impleting agencies) **/
/**
		 else if ( !countryAdded && !donorAdded && !sectorAdded && channelAdded ) {
			 createChannelView(channelList, filters);
		 }
		 // country/channels
		 else if ( countryAdded && !donorAdded && !sectorAdded && channelAdded ) {
			 createCountryChannelView(gaulList, channelList, filters);
		 }
		 // donor/channels
		 else if ( !countryAdded && donorAdded && !sectorAdded && channelAdded ) {
			 createDonorChannelView(gaulList, channelList, filters);
		 }
		// sector/channels
		 else if ( !countryAdded && !donorAdded && sectorAdded && channelAdded ) {
			 createSectorChannelView(gaulList, channelList, filters);
		 }
		// country/donor/channels
		 else if ( countryAdded && donorAdded && !sectorAdded && channelAdded ) {
			 createCountryDonorChannelView(gaulList, donorList, channelList, filters);
		 }
		// country/sector/channels
		 else if ( countryAdded && !donorAdded && sectorAdded && channelAdded ) {
			 createCountrySectorChannelView(gaulList, sectorList, channelList, filters);
		 }
		// donor/sector/channels
		 else if ( !countryAdded && donorAdded && sectorAdded && channelAdded ) {
			 createDonorSectorChannelView(donorList, sectorList, channelList, filters);
		 }
		// country/donor/sector/channels
		 else if ( countryAdded && donorAdded && sectorAdded && channelAdded ) {
//			 createCountryDonorSectorChannelView(gaulList, channelList, filters);
		 }
		 **/

		 else if( !countryAdded && !donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 /*** global view **/
			 //globalAgentWithAgricultureADAM(ADAM.getAdamWrapper());
			 globalAgentADAM(ADAM.getAdamWrapper());
		 }

		 // restore custom
		 restoreCustomContent();
		 
	}




	private static void createDonorView(final Map<String, String> map, HashMap<String, List<String>> filters ){
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
		ADAMQueryVO topSectorChart = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.BAR.name(), "Most Financed "+sectorSOLabel+"", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSectorTable = ADAMQueryVOBuilder.topSectorsDonorView(label, baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), timeserieLimit);
		ADAMQueryVO topSectorChartTimeSerie = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.TIMESERIE.name(), "Time series of Most Financed "+sectorSOLabel+"", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorChartTimeSerie.getWhereDates().put("year", baseDate);
		topSectorChart.getSwitchResources().add(topSectorTable);
		topSectorChart.getSwitchResources().add(topSectorChartTimeSerie);
		
		topSectorTable.getSwitchResources().add(topSectorChart);
		topSectorTable.getSwitchResources().add(topSectorChartTimeSerie);
		
		topSectorChartTimeSerie.getSwitchResources().add(topSectorChart);

		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorChart, objectWindowId);
//		ADAMTableController.addTable("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topSectorsDonorView(label, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR)), objectWindowId);


		// TOP_RIGHT
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.donorViewFavouriteChannels("Channels of Delivery", codes, label, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId);
		// Agriculture Related: ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.donorViewFavouriteChannels("Channels of Delivery (Agriculture)", codes, label, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true), objectWindowId);

		
		// MAP_RIGHT
		//Agriculture related
		/*ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topAgriculturalSectorsDonorView(label, baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);	*/

		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topSectorsDonorViewTable(label, baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);	
		topAgriculturalSectorChartSerie.getWhereDates().put("year", baseDate);
		
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorTable);
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorChartSerie);
		
		
		topAgriculturalSectorTable.getSwitchResources().add(topAgriculturalSectorChart);
		topAgriculturalSectorTable.getSwitchResources().add(topAgriculturalSectorChartSerie);
		
		topAgriculturalSectorChartSerie.getSwitchResources().add(topAgriculturalSectorChart);
			
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);

		

		// MAP_LEFT
		/*Agriculture Related  
		 ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries in Agriculture", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		 */
		
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
//		ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter(label, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
		/*Agriculture Related  
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, true, objectWindowId);
		 */
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);

//		ADAMQueryVO y = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, null);
//		ADAMQueryVO x = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Most financed countries in Agriculture", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, null);
//		x.setSecondQuery(y);
//	
//		ADAMController.addChart("MAP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), x, objectWindowId);

		
		
	}

	
	private static void createCountryView(final Map<String, String> map, HashMap<String, List<String>> filters){
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
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), "Most Financed "+sectorSOLabel+"", baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		topSector.getSwitchResources().add(topSectorTimeSerie);
		topSectorTimeSerie.getSwitchResources().add(topSector);
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSector , objectWindowId);

		
		
		// TOP_RIGHT
		/*
		 Agriculture related
		 ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners (Agriculture)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, 10);	
		ADAMQueryVO topDonorsTable = ADAMQueryVOBuilder.topDonorsFiltered(baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (Agriculture)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
		*/
		
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsTable = ADAMQueryVOBuilder.topDonorsFiltered(baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		
		topDonorsChart.getSwitchResources().add(topDonorsTable);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		
		topDonorsTable.getSwitchResources().add(topDonorsChart);
		topDonorsTable.getSwitchResources().add(topDonorsChartTimeSerie);
		
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
	
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		
		
		// BOTTOM_LEFT	
//        ADAMTableController.addTable("BOTTOM_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topAgriculturalCountryView("", baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10), objectWindowId);
	

		// BOTTOM_RIGHT
		/*
		 * Agriculture related 
		 * ADAMQueryVO topAgriculChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, 8);	
		 
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		*/
		
		ADAMQueryVO topAgriculChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);			 
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topAgriculChartTimeSerie.getWhereDates().put("year", baseDate);
		topAgriculChart.getSwitchResources().add(topAgriculChartTimeSerie);
		topAgriculChartTimeSerie.getSwitchResources().add(topAgriculChart);
		
		ADAMTableController.addChart("BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculChart, objectWindowId);

		
		addCountryMap(map, filters);
		
		/*// BOTTOM_LEFT	
		Map<String, String> cleanMap = getCleanMap(map);

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
	
	
	private static void createCountryDonorView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters ){
		
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
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), "Most Financed "+sectorSOLabel+"", baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		
		ADAMQueryVO topSectorsTable = ADAMQueryVOBuilder.topSectorsChannelsDonorCountry(baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
		topSector.getSwitchResources().add(topSectorsTable);
		topSector.getSwitchResources().add(topSectorTimeSerie);	
		
		topSectorsTable.getSwitchResources().add(topSector);
		topSectorsTable.getSwitchResources().add(topSectorTimeSerie);
		
		topSectorTimeSerie.getSwitchResources().add(topSector);
		
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSector, objectWindowId);

		
		// TOP_RIGHT
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.topChannelsChart("Channels of Delivery", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false), objectWindowId); 
		//Agriculture Related: ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.topChannelsChart("Channels of Delivery (Agriculture)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true), objectWindowId); 


		// BOTTOM_RIGHT
		/*
		Agriculture related 
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")" ,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topAgriculturalSectorsDonorCountryView(donorLabel, gaulLabel, baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10, true, false);
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
        */
		
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")" ,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topAgriculturalSectorsDonorCountryView(donorLabel, gaulLabel, baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10, false, false);
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topAgriculChartTimeSerie.getWhereDates().put("year", baseDate);
		
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorTable);
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculChartTimeSerie);

		topAgriculturalSectorTable.getSwitchResources().add(topAgriculturalSectorChart);
		topAgriculturalSectorTable.getSwitchResources().add(topAgriculChartTimeSerie);

		topAgriculChartTimeSerie.getSwitchResources().add(topAgriculturalSectorChart);		
		
		ADAMTableController.addChart("BOTTOM_RIGHT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);


		addCountryMap(gaulList, filters);

		
		
		/*Map<String, String> countries = getCleanMap(gaulList);
		
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
		
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, setCtitle, setAHoverTitle, setBHoverTitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);*/

	}


	private static void createSectorView(final Map<String, String> map, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		
		
		System.out.println("createSectorView objectWindowId: " + objectWindowId );

		addViewMenu("Sector View:", null, null, map, null, null);

		// TOP_LEFT
			
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topSectorTimeSerie, true);
		
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

	
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topDonorsChart, false);
		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);		
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topDonorsChartTimeSerie, false);
		
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		
		// MAP_LEFT
		addCountryMap(null, filters);
		
		/*boolean addedVenn = addCountrySectorRelatedObject(null, null, map, filters);*/
		String position = "MAP_RIGHT";
		
		/*if(addedVenn)
			position = "BOTTOM_RIGHT";*/
			
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		setAggregatedViewName(topPurposeChart, false);
		
		/* Agriculture related
		 * ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of Sub-sectors of Selected Sectors", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		 */
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topPurposeChartChartTimeSerie, false);
		
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);

		
	   /** ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false);
		
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		setAggregatedViewName(countriesChart, false);;
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);**/
	
	}

	
	
	private static void createCountrySectorView(final Map<String, String> gaulList, final Map<String, String> sectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		System.out.println("createCountrySectorView objectWindowId: " + objectWindowId );

				

		addViewMenu("Recipient Country/Sector View:", gaulList, null, sectorList, null, null);


		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topSectorTimeSerie, true);
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		
		
		

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topDonorsChart, false);
		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topDonorsChartTimeSerie, false);
		
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		

		// MAP_LEFT
		
		addCountryMap(gaulList, filters);
		
		/*boolean addedVenn = addCountrySectorRelatedObject(gaulList, null, sectorList, filters);*/
		String position = "MAP_RIGHT";
		
		/*if(addedVenn)
			position = "BOTTOM_RIGHT";*/
		
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		setAggregatedViewName(topPurposeChart, false);
		
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topPurposeChartChartTimeSerie, false);
		
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);


		
		/*ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false);
		
		Agriculture related
		 * ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		
		
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		setAggregatedViewName(countriesChart, false);
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);*/
	}

	
	private static void createDonorSectorView(final Map<String, String> donorList, final Map<String, String> sectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		System.out.println("createDonorSectorView objectWindowId: " + objectWindowId );

				

		addViewMenu("Resource Partner/Sector View:", null, donorList, sectorList, null, null);


		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel, new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topSectorTimeSerie, true);
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topDonorsChart, false);
		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topDonorsChartTimeSerie, false);
		
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		
	

		// MAP_LEFT	
		addCountryMap(null, filters);
		
		/*boolean addedVenn = addCountrySectorRelatedObject(null, donorList, sectorList, filters);*/
		String position = "MAP_RIGHT";
		
		/*if(addedVenn)
			position = "BOTTOM_RIGHT";*/
		
       // MAP_RIGHT		
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate,  subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		setAggregatedViewName(topPurposeChart, false);
		
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topPurposeChartChartTimeSerie, false);
		
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);

		
		/*ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false);
		
		 agriculture related 
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		setAggregatedViewName(countriesChart, false);
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);*/
	}
	
	
	private static void createCountryDonorSectorView(final Map<String, String> gaulList, final Map<String, String> donorList, final Map<String, String> sectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		System.out.println("createCountryDonorSectorView objectWindowId: " + objectWindowId );

				
		addViewMenu("Recipient Country/Resource Partner/Sector View:", gaulList, donorList, sectorList, null, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topSectorTimeSerie, true);
		
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topDonorsChart, false);
		
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topDonorsChartTimeSerie, false);
		
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
	

		// MAP_LEFT
		
		addCountryMap(gaulList, filters);
		
		/*boolean addedVenn = addCountrySectorRelatedObject(gaulList, donorList, sectorList, filters);*/
		String position = "MAP_RIGHT";
		
		/*if(addedVenn)
			position = "BOTTOM_RIGHT";
		*/
		
		
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		setAggregatedViewName(topPurposeChart, false);
		
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		setAggregatedViewName(topPurposeChartChartTimeSerie, false);
		
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);

		
		/*ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country  - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false);
		
		*//**agriculture related
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
	    **//*
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		setAggregatedViewName(countriesChart, false);
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);*/
	}
	
	
	/*** channels ***/
	
	private static void createChannelView(final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		System.out.println("createChannelView objectWindowId: " + objectWindowId );



		addViewMenu("Implementing Agency View:", null, null, null, null, channelList);


		// TOP_LEFT
		ADAMQueryVO topSectorChart = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.BAR.name(), "Most Financed "+sectorSOLabel+"", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSectorChartTimeSerie = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.TIMESERIE.name(), "Time series of Most Financed "+sectorSOLabel+"", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorChartTimeSerie.getWhereDates().put("year", baseDate);
		topSectorChart.getSwitchResources().add(topSectorChartTimeSerie);	
		topSectorChartTimeSerie.getSwitchResources().add(topSectorChart);

		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorChart, objectWindowId);

		// TOP_RIGHT
		/*Agriculture related:
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners (Agriculture)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (Agriculture)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
		**/
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
	
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		
		
		// MAP_RIGHT
		/*Agriculture related:
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);	
        */
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);	
		topAgriculturalSectorChartSerie.getWhereDates().put("year", baseDate);
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorChartSerie);
		topAgriculturalSectorChartSerie.getSwitchResources().add(topAgriculturalSectorChart);
			
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);

		

		// MAP_LEFT
		/*Agriculture related:
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries in Agriculture", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		*/
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);


	}
	
	
	private static void createCountryChannelView(final Map<String, String> gaulList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Implementing Agency View:", gaulList, null, null, null, channelList);


		// TOP_LEFT
		ADAMQueryVO topSectorChart = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.BAR.name(), "Most Financed "+sectorSOLabel+"", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSectorChartTimeSerie = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.TIMESERIE.name(), "Time series of Most Financed "+sectorSOLabel+"", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorChartTimeSerie.getWhereDates().put("year", baseDate);
		topSectorChart.getSwitchResources().add(topSectorChartTimeSerie);	
		topSectorChartTimeSerie.getSwitchResources().add(topSectorChart);

		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorChart, objectWindowId);

		// TOP_RIGHT
		/**Agriculture related
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners (Agriculture)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (Agriculture)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
		**/
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
	
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		
		
		// MAP_RIGHT
		/**Agriculture related
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);	
        **/
		
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);	
		topAgriculturalSectorChartSerie.getWhereDates().put("year", baseDate);
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorChartSerie);
		topAgriculturalSectorChartSerie.getSwitchResources().add(topAgriculturalSectorChart);
			
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);

		

		// MAP_LEFT
		/**Agriculture related
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries in Agriculture", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		**/
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);


	}
	
	private static void createDonorChannelView(final Map<String, String> donorList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		
		System.out.println("createDonorChannelView");

		addViewMenu("Resource Partner/Implementing Agency View:", null, donorList, null, null, channelList);


		// TOP_LEFT
		ADAMQueryVO topSectorChart = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.BAR.name(), "Most Financed "+sectorSOLabel+"", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSectorChartTimeSerie = ADAMQueryVOBuilder.topSectorsChart(ADAMBoxContent.TIMESERIE.name(), "Time series of Most Financed "+sectorSOLabel+"", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorChartTimeSerie.getWhereDates().put("year", baseDate);
		topSectorChart.getSwitchResources().add(topSectorChartTimeSerie);	
		topSectorChartTimeSerie.getSwitchResources().add(topSectorChart);

		ADAMController.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorChart, objectWindowId);

		// TOP_RIGHT
		/**Agriculture related	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (Agriculture)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
        **/
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChartTimeSerie, objectWindowId);

		
		
		// MAP_RIGHT
		/**Agriculture related
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);	
		 **/
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.name(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculturalSectorChartSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.name(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);	
		topAgriculturalSectorChartSerie.getWhereDates().put("year", baseDate);
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculturalSectorChartSerie);
		topAgriculturalSectorChartSerie.getSwitchResources().add(topAgriculturalSectorChart);
			
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);

		

		// MAP_LEFT
		
		/**Agriculture related
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries in Agriculture", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		 */
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Most financed countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
	}
	
	private static void createSectorChannelView(final Map<String, String> sectorList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Sector/Implementing Agency View:", null, null, sectorList, null, channelList);


		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		
		
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);



		// MAP_LEFT
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + "("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		/*Agriculture related 
		 * ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		*/
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
	}
	
	
	private static void createCountrySectorChannelView(final Map<String, String> countryList, final Map<String, String> sectorList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Sector/Implementing Agency View:", countryList, null, sectorList, null, channelList);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);			
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);


		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);



		// MAP_LEFT
		/**Agriculture related
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		**/
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
	}
	
	private static void createCountryDonorChannelView(final Map<String, String> countryList, final Map<String, String> donorList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Resource Partner/Implementing Agency View:", countryList, donorList, null, null, channelList);

	
		// TOP_LEFT
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.BAR.toString(), "Most Financed "+sectorSOLabel+"", baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);	
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		topSector.getSwitchResources().add(topSectorTimeSerie);	

		topSectorTimeSerie.getSwitchResources().add(topSector);
		
		
		ADAMController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSector, objectWindowId);
	
		
		// TOP_RIGHT
		/**Agriculture related
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (In Agriculture)", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
		 **/
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChartTimeSerie, objectWindowId);	
		
		// BOTTOM_RIGHT
		/**Agriculture related
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, "FAO related sectors breakdown ("+ADAMController.baseUnit+")" ,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), true, 8);	
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of FAO related sectors breakdown", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		 **/
		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, subSectorORLabel+" ("+ADAMController.baseUnit+")" ,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topAgriculChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topAgriculChartTimeSerie.getWhereDates().put("year", baseDate);
		
		topAgriculturalSectorChart.getSwitchResources().add(topAgriculChartTimeSerie);
		topAgriculChartTimeSerie.getSwitchResources().add(topAgriculturalSectorChart);		
		
		ADAMTableController.addChart("BOTTOM_RIGHT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);
		
	/*	// VENN
		
		Map<String, String> countries = getCleanMap(countryList);
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
	
	//	if ( countries.size() == 1) {
	//		for(String key : countries.keySet())
	//			setAtitle = countries.get(key);
	//	} 
		
		String setCtitle = "Resource Partners";
	//	if ( donors.size() == 1) {
	//		for(String key : donors.keySet())
	//			setBtitle = donors.get(key);
	//	} 
		
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, setCtitle, setAHoverTitle, setBHoverTitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
	
		*/
		addCountryMap(countryList, filters);
	}
	
	private static void createDonorSectorChannelView(final Map<String, String> donorList, final Map<String, String> sectorList, final Map<String, String> channelList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Resource Partner/Sector/Implementing Agency View:", null, donorList, sectorList, null, channelList);
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+sectorSOLabel+"", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);		
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);


		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);
		
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		
		
		// MAP_RIGHT
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, ""+subSectorORLabel+" of Selected "+sectorSOLabel+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		ADAMQueryVO topPurposeChartChartTimeSerie = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.TIMESERIE.toString(), filters, new ArrayList<String>(), "Time series of "+subSectorORLabel+" of Selected "+sectorSOLabel+"", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		topPurposeChartChartTimeSerie.getWhereDates().put("year", baseDate);
		topPurposeChart.getSwitchResources().add(topPurposeChartChartTimeSerie);
		topPurposeChartChartTimeSerie.getSwitchResources().add(topPurposeChart);
		
		ADAMTableController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);



		// MAP_LEFT
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		
		/**Agriculture related
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		**/
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
	}

	
	
	public static ADAMQueryVO setAggregatedViewName(ADAMQueryVO vo, boolean addDisclaimer){
      	List<String> tableName = new ArrayList<String>();
		
        //Dac_9999 = Agricultural/FAO Related Sub Sectors 
      	
      //	System.out.println("XXXXX setAggregatedViewName: ADAMBoxMaker.sectorsSelected" +ADAMBoxMaker.sectorsSelected);
      	
      	if(ADAMBoxMaker.sectorsSelected.containsKey("Dac_9999") || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
		//if(ADAMBoxMaker.faoSectorAdded) {
			tableName.clear();
			tableName.add(ADAMSelectedDataset.FAO_SUB_SECTORS_DATA.name().toLowerCase());
			vo.setAggregatedViewNames(tableName);
			/*if(addDisclaimer)
				vo.setDisclaimer(getFAOSubSectorDisclaimer());*/
			
			
		}
		else {
			tableName.clear();
			vo.setAggregatedViewNames(tableName);
			
		}
		
	//	System.out.println("ADAMViewController:: setAggregatedViewName: Aggregated View Names = "+ vo.getAggregatedViewNames());
		
		return vo;
	}
	
    static String getFAOSubSectorDisclaimer(){	
		return "Click <a href='adam-docs/adam_fao_sector_breakdown.pdf' target='_blank'>here</a> for the ADAM Disclaimer";	
	}
    
    
    private static boolean addCountrySectorRelatedObject(Map<String, String> gaulList, Map<String, String> donorList, Map<String, String> sectorList, HashMap<String, List<String>> filters){
		boolean addedVenn = false;
		
    	if(gaulList!=null && gaulList.size() == 1) {
			//add the venn only if Agriculture Related Sectors is selected
			System.out.println("sectorList size is  "+sectorList.size() + " | contents: "+sectorList);
			
			if(sectorList.size() == 1 && sectorList.containsKey("Dac_9999")) {
				addedVenn = true;
				addCountryPrioritiesVenn(gaulList, donorList);	
    		}	
    		else {
    			addedVenn = false;
    			ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
    			setAggregatedViewName(googleMap, false);
    		
    			ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
    			countriesChart.getWhereDates().put("year", baseDate);
    			setAggregatedViewName(countriesChart, false);;
    		
    			googleMap.getSwitchResources().add(countriesChart);
    			countriesChart.getSwitchResources().add(googleMap);
    	
    			ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
    		}
		}
		else {
			addedVenn = false;
			ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
			setAggregatedViewName(googleMap, false);
			
			ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
			countriesChart.getWhereDates().put("year", baseDate);
			setAggregatedViewName(countriesChart, false);;
			
			googleMap.getSwitchResources().add(countriesChart);
			countriesChart.getSwitchResources().add(googleMap);
			ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);	
		} 
    	
    	return addedVenn;
	}
    
    
    private static boolean addCountryMap(Map<String, String> gaulList, Map<String, String> donorList, Map<String, String> sectorList, HashMap<String, List<String>> filters){
		boolean addedVenn = false;

    	ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false);
		
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries", filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		setAggregatedViewName(countriesChart, false);;
		
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);	

    	return addedVenn;
	}
	
	/*public static void addCountryPrioritiesVenn(Map<String, String> gaulList, Map<String, String> donorList){
       
		HashMap<String, String> setA = new HashMap<String, String>();
		HashMap<String, String> setB = new HashMap<String, String>();
		HashMap<String, String> setC = new HashMap<String, String>();
		
		
		Map<String, String> countries = getCleanMap(gaulList);
		
		setA.putAll(countries);
		setB.put("41301", "FAO");
		
		if(donorList!=null && !donorList.isEmpty()) {
			Map<String, String> donors = getCleanMap(donorList);
			setC.putAll(donors);
		}
		
		String setAtitle = "Recipients";
		String setAHoverTitle = "Government";
		
		String setBtitle = "FAO";
		String setBHoverTitle = "CPF";

		String setCtitle = "Resource Partners";
		
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, setCtitle, setAHoverTitle, setBHoverTitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
	
	}*/
	
	public static void addCountryPrioritiesVenn(Map<String, String> gaulList, Map<String, String> donorList){
	       
		HashMap<String, String> setA = new HashMap<String, String>();
		HashMap<String, String> setB = new HashMap<String, String>();
		HashMap<String, String> setC = new HashMap<String, String>();
		
		
		Map<String, String> countries = getCleanMap(gaulList);
		
		setA.putAll(countries);
		setB.put("41301", "FAO");
		
		if(donorList!=null && !donorList.isEmpty()) {
			Map<String, String> donors = getCleanMap(donorList);
			setC.putAll(donors);
		}
		
		String setAtitle = "Recipients";
		String setAHoverTitle = "Government";
		
		String setBtitle = "FAO";
		String setBHoverTitle = "CPF";

		String setCtitle = "Resource Partners";
		
		ADAMVennController.addPrioritiesVenn(setAtitle, setBtitle, setCtitle, setAHoverTitle, setBHoverTitle, "ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
	
	}
	
	
	private static void addCountryMap(Map<String, String> gaulList, HashMap<String, List<String>> filters) {
		
		String title = "Budget by Country ";
		
		if(gaulList!=null && !gaulList.isEmpty() && gaulList.size() == 1)
			title += "Sub-Regional Context - ";
		else
			title += "- ";
		
		ADAMQueryVO q = ADAMSubSectorViewController.createCountryMapVO(title, filters);
		
		ADAMQueryVO c = ADAMSubSectorViewController.createCountriesChartVO(gaulList, filters, q);		
		
		q.getSwitchResources().add(c);
		c.getSwitchResources().add(q);
		
		ADAMMapController.addGoogleMapsADAMView(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);			
		
	}
}
