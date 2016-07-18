package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.utils.ADAMSubSectorORUtils;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

public class ADAMSubSectorViewController extends ADAMViewController {

	
    
	//Global View
	static void createSubSectorView(final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Sub-Sector View:", null, null, null, subSectorList, null);

			
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, filters );
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		// BOTTOM_RIGHT
		//ADAMQueryVO topCountriesChart = createCountriesChartVO("Top Ten Recipient Countries", "Time series of Top Recipient Countries", filters );
		//ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), topCountriesChart, objectWindowId);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);
		
		// MAP_LEFT
		//ADAMQueryVO googleMap = createCountryMapVO("Budget per Country", filters);
		//ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
		addCountryMap(null, subSectorList, filters);
	}

	static void createCountrySubSectorView(final Map<String, String> gaulList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Sub-Sector View:", gaulList, null, null, subSectorList, null);

		// TOP_LEFT		
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, filters );
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, null, subSectorList, filters);
	}

	static void createDonorSubSectorView(final Map<String, String> donorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Resource Partner/Sub-Sector View:", null, donorList, null, subSectorList, null);

		// TOP_LEFT		
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		addDonorRelatedObject(subSectorList, donorList, null, filters);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryMap(null, subSectorList, filters);
		//ADAMQueryVO googleMap = createCountryMapVO("Sub-Sectors Budget per Country", filters);
		//ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);			
	}

	static void createSectorSubSectorView(final Map<String, String> sectorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Sector/Sub-Sector View:", null, null, sectorList, subSectorList, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, filters );
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);
		//ADAMQueryVO topCountriesChart = createCountriesChartVO("Top Ten Recipient Countries", "Time series of Top Recipient Countries", filters );
		//ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), topCountriesChart, objectWindowId);

		// MAP_LEFT
		addCountryMap(null, subSectorList, filters);
		//ADAMQueryVO googleMap = createCountryMapVO("Budget per Country", filters);
		//ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);

	}

	static void createCountryDonorSubSectorView(final Map<String, String> gaulList, final Map<String, String> donorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Resource Partner/Sub-Sector View:", gaulList, donorList, null, subSectorList, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		addDonorRelatedObject(subSectorList,donorList, gaulList, filters);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, donorList, subSectorList, filters);

	}

	static void createCountrySectorSubSectorView(final Map<String, String> gaulList, final Map<String, String> sectorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Sector/Sub-Sector View:", gaulList, null, sectorList, subSectorList, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, filters );
		ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, null, subSectorList, filters);

	}

	static void createDonorSectorSubSectorView(final Map<String, String> donorList, final Map<String, String> sectorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Resource Partner/Sector/Sub-Sector View:", null, donorList, sectorList, subSectorList, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		addDonorRelatedObject(subSectorList, donorList, null, filters);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryMap(null, subSectorList, filters);
		//ADAMQueryVO googleMap = createCountryMapVO("Budget per Country", filters);
		//ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);
	}

	static void createCountryDonorSectorSubSectorView(final Map<String, String> gaulList, final Map<String, String> donorList, final Map<String, String> sectorList, final Map<String, String> subSectorList, HashMap<String, List<String>> filters){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Recipient Country/Resource Partner/Sector/Sub-Sector View:", gaulList, donorList, sectorList, subSectorList, null);

		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createSubSectorsTimeSeriesChartVO(filters);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		addDonorRelatedObject(subSectorList, donorList, gaulList, filters);

		// BOTTOM_RIGHT
		addSubSectorRelatedObject(subSectorList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, donorList, subSectorList, filters);

	}

	
	private static ADAMQueryVO createSubSectorComparisonChartVO(final Map<String, String> subSectorList, HashMap<String, List<String>> filters ){
		
		//String selectedSubSector = ADAMViewMenuBuilder.getUnFormattedDescription(subSectorList);
		String selectedSubSector = getFormattedSubSector(subSectorList);
		
        
		
		ADAMQueryVO subSectorComparisonChart = ADAMQueryVOBuilder.compareSubSectorAgainstTop10(ADAMBoxContent.BAR_STACK_SECTOR_COMPARISON.name(), selectedSubSector+" compared with the Top 10 "+getSubSectorLabel(),filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, false);
		setAggregatedViewName(subSectorComparisonChart, false);
		subSectorComparisonChart.setDisclaimer(ADAMSubSectorORUtils.getDifferenceChartDisclaimer(selectedSubSector, crs_aggregationColumn.getGaulLabel()));

		if(!ADAMBoxMaker.sectorsSelected.containsKey("Dac_9999")){
			ADAMQueryVO compareToFaoRelatedSectorsChart = ADAMQueryVOBuilder.compareSubSectorAgainstTop10(ADAMBoxContent.BAR_STACK_SECTOR_COMPARISON.name(), selectedSubSector+" compared with the FAO Related Sectors",filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, true);
			setAggregatedViewName(compareToFaoRelatedSectorsChart, false);
			compareToFaoRelatedSectorsChart.setDisclaimer(ADAMSubSectorORUtils.getDifferenceChartDisclaimer(selectedSubSector, crs_aggregationColumn.getGaulLabel()));
			subSectorComparisonChart.getSwitchResources().add(compareToFaoRelatedSectorsChart);
			compareToFaoRelatedSectorsChart.getSwitchResources().add(subSectorComparisonChart);
		}
			
		return subSectorComparisonChart;
	}

	private static ADAMQueryVO createSubSectorPieChartVO(HashMap<String, List<String>> filters ){

			
		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.PIE.toString(), filters, baseDate, getSubSectorLabel()+" ("+ADAMController.baseUnit+")", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, 8);	
		setAggregatedViewName(topPurposeChart, false);


		//ADAMQueryVO topPurposeChartBar = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.BAR.toString(), filters, new ArrayList<String>(), "Bar Chart Comparison of Selected Sub-Sectors", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		ADAMQueryVO topPurposeChartBar = ADAMQueryVOBuilder.mostFinancedPurposeSectors(ADAMBoxContent.BAR.toString(), filters, baseDate, "Bar Chart Comparison of Selected "+getSubSectorLabel(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		setAggregatedViewName(topPurposeChartBar, false);

		topPurposeChart.getSwitchResources().add(topPurposeChartBar);
		topPurposeChartBar.getSwitchResources().add(topPurposeChart);

		return topPurposeChart;
	}
	
	
	private static ADAMQueryVO createAssistanceTableVO(Map<String, String> subSectorList, final String selectedItem, HashMap<String, List<String>> filters, boolean isCountry, String selectedCountriesLabel ){

		String title="";

		
		if(subSectorList.size() > 1)  {
			if(isCountry)
				title = "Total Assistance to the "+getSubSectorLabel()+" by Year for "+selectedItem + " ("+ADAMController.baseUnit+")";
			else
				title = selectedItem+" Assistance to the Selected "+getSubSectorLabel()+" for "+selectedCountriesLabel+" by Year ("+ADAMController.baseUnit+")";
		}	
		else { 
			if(isCountry)
				title = "Total Assistance to "+getFormattedSubSector(subSectorList)+" by Year for " +selectedItem+" ("+ADAMController.baseUnit+")";
			else
				title = selectedItem+" Assistance to "+getFormattedSubSector(subSectorList)+" for "+selectedCountriesLabel+ " by Year ("+ADAMController.baseUnit+")";
		}
		
		
		List<String> selects = new ArrayList<String>();
		selects.add("ROUND(CAST(extract(year from year) AS NUMERIC), 0) ");
		selects.add("to_char(ROUND(CAST(SUM(usd_commitment) AS NUMERIC), 2), 'FM999G999G990D00') AS commitment"); //adds the thousand separator
		selects.add("to_char(ROUND(CAST(SUM(usd_commitment_defl) AS NUMERIC), 2), 'FM999G999G990D00') AS commitment_defl");
		selects.add("to_char(ROUND(CAST(SUM(usd_disbursement) AS NUMERIC), 2), 'FM999G999G990D00') AS disbursement");
		selects.add("to_char(ROUND(CAST(SUM(usd_disbursement_defl) AS NUMERIC), 2), 'FM999G999G990D00') AS disbursement_defl");

		ADAMQueryVO qvo = ADAMQueryVOBuilder.getTypesOfContribution(title, selects, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT));
		setAggregatedViewName(qvo, false);

		return qvo;
	}
	
	private static ADAMQueryVO createImplementingAgencyTableVO(Map<String, String> subSectorList, final String selectedItem, HashMap<String, List<String>> filters){

		String title="";
		
		
		if(subSectorList.size() > 1)  
			title = "Total Budget provided to Top Implementing Agencies by all Selected Resource Partner(s) for the Selected "+getSubSectorLabel()+" ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
	
		else  
			title = "Total Budget provided to Top Implementing Agencies by all Selected Resource Partner(s) for "+getFormattedSubSector(subSectorList)+" ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		List<String> selects = new ArrayList<String>();
		selects.add("channelname ");
		selects.add("to_char(ROUND(CAST(SUM(usd_commitment) AS NUMERIC), 2), 'FM999G999G990D00') AS commitment");//adds the thousand separator
		selects.add("to_char(ROUND(CAST(SUM(usd_disbursement) AS NUMERIC), 2), 'FM999G999G990D00') AS disbursement");

		ADAMQueryVO qvo = ADAMQueryVOBuilder.getTopImplementingAgencies(title, selects, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT));
		setAggregatedViewName(qvo, false);

		return qvo;
	}

	static ADAMQueryVO createCountryMapVO(final String title, HashMap<String, List<String>> filters ){
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery(title + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		setAggregatedViewName(googleMap, false); 
		
		return googleMap;
	}

	private static ADAMQueryVO createDonorsChartVO(Map<String, String> donorList, HashMap<String, List<String>> filters ){

		String title = ADAMSubSectorORUtils.getApproriateBarChartTitle(donorList, false);
		String timeseriesTitle = ADAMSubSectorORUtils.getApproriateTimeseriesTitle(donorList, false);

		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topDonorsChart, false);

		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), timeseriesTitle, filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);		
		topDonorsChartTimeSerie.getWhereDates().put("year", baseDate);
		
		if(donorList==null || donorList!=null && donorList.size() > 3)
			topDonorsChartTimeSerie.setDisclaimer(ADAMSubSectorORUtils.getLimitDisclaimer());
		
		setAggregatedViewName(topDonorsChartTimeSerie, false);

		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);

		return topDonorsChart;
	}

/*
	private static ADAMQueryVO createCountriesChartVO(final String title, final String timeseriesTitle, HashMap<String, List<String>> filters ){
		ADAMQueryVO topCountriesChart = ADAMQueryVOBuilder.topCountriesChart(ADAMBoxContent.BAR.toString(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topCountriesChart, false);

		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		ADAMQueryVO topCountriesChartTimeSerie = ADAMQueryVOBuilder.topCountriesChart(ADAMBoxContent.TIMESERIE.toString(), timeseriesTitle, filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);		
		setAggregatedViewName(topCountriesChartTimeSerie, false);

		topCountriesChart.getSwitchResources().add(topCountriesChartTimeSerie);
		topCountriesChartTimeSerie.getSwitchResources().add(topCountriesChart);

		return topCountriesChart;
	}*/

	private static ADAMQueryVO createSubSectorsTimeSeriesChartVO(HashMap<String, List<String>> filters ){
		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		
		    ADAMQueryVO topSectorChart = null;
		
			if(baseDate.size() > 1) { // TIME-SERIES
			   topSectorChart = ADAMQueryVOBuilder.mostFinancedSubSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected "+getSubSectorLabel(), new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit, false, false);	
			   topSectorChart.getWhereDates().put("year", baseDate);
			   
			} else { // BAR
				topSectorChart = ADAMQueryVOBuilder.mostFinancedSubSectors(ADAMBoxContent.BAR.toString(), "Total "+crs_aggregationColumn.getGaulLabel()+ " ("+ADAMController.baseUnit+") for the Selected "+getSubSectorLabel()+" in "+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel(), baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10, false, false);	
					
			
			}		
		
		
		//ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSubSectors(ADAMBoxContent.TIMESERIE.toString(), "Time series of Selected Sub-Sectors", new ArrayList<String>(), filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit, false, false);	
		//setAggregatedViewName(topSectorTimeSerie, false);
		
		setAggregatedViewName(topSectorChart, false);
		
		return topSectorChart;
	}

	private static void addDonorRelatedObject(Map<String, String> subSectorList, Map<String, String> donorList, Map<String, String> gaulList, HashMap<String, List<String>> filters){
		if(donorList.size() > 1) { // CHART
			ADAMQueryVO topDonorsChart = createDonorsChartVO(donorList, filters );
			ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		} else { // TABLE
			String selectedDonor = ADAMViewMenuBuilder.getUnFormattedDescription(donorList);
			String selectedCountriesLabel = ADAMSubSectorORUtils.getRecipientCountryLabel(gaulList);
			
			ADAMQueryVO qvo = createAssistanceTableVO(subSectorList, selectedDonor, filters, false, selectedCountriesLabel);
			ADAMTableController.addTable("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), qvo, objectWindowId) ;
		}		
	}

	private static void addSubSectorRelatedObject(Map<String, String> subSectorList, HashMap<String, List<String>> filters){
		if(subSectorList.size() > 1) { // SUB-SECTOR PIE CHART			
			ADAMQueryVO topPurposeChart = createSubSectorPieChartVO(filters );
			ADAMTableController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);
		}
		else {
			//SUB-SECTOR COMPARISON CHART		
			ADAMQueryVO subSectorComparisonChart = createSubSectorComparisonChartVO(subSectorList, filters );
			ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), subSectorComparisonChart, objectWindowId);
		}
	}


	private static void addCountryRelatedObject(Map<String, String> gaulList, Map<String, String> donorList, Map<String, String> subSectorList, HashMap<String, List<String>> filters){
		if(gaulList.size() > 1) { // MAP
			addCountryMap(gaulList, subSectorList, filters);
			//ADAMQueryVO googleMap = createCountryMapVO("Budget for Selected Sub-Sectors by Country", filters);
			//ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);			
		} else { // TABLE
			String selectedCountry = ADAMViewMenuBuilder.getUnFormattedDescription(gaulList);
			//Don't filter on the donor
			//if(donorList!=null && filters.containsKey("donorcode"))
				//filters.remove("donorcode");
			
			ADAMQueryVO qvo = createImplementingAgencyTableVO(subSectorList, selectedCountry, filters);
			
			//ADAMQueryVO qvo = createAssistanceTableVO(subSectorList, selectedCountry, filters, true, "");
			ADAMTableController.addTable("MAP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), qvo, objectWindowId) ;
		}
	}
	
	private static void addCountryMap(Map<String, String> gaulList, Map<String, String> subSectorList, HashMap<String, List<String>> filters) {
		String title = "";
		
		if(subSectorList.size() == 1) {
			//for(String key: subSectorList.keySet())
				title ="Budget for "+getFormattedSubSector(subSectorList) +" by Country ";
		}	
		else 
			title = "Budget for Selected "+getSubSectorLabel()+" by Country ";
		
		
		ADAMQueryVO q = createCountryMapVO(title, filters);
		
		ADAMQueryVO c = createCountriesChartVO(gaulList, filters, q);		
		
		q.getSwitchResources().add(c);
		c.getSwitchResources().add(q);
		
		ADAMMapController.addGoogleMapsADAMView(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, false, objectWindowId);			
		
	}
		
	static ADAMQueryVO createCountriesChartVO(final Map<String, String> gaulList, HashMap<String, List<String>> filters, ADAMQueryVO mapVo){

		String title = ADAMSubSectorORUtils.getApproriateBarChartTitle(gaulList, true);
		String timeseriesTitle = ADAMSubSectorORUtils.getApproriateTimeseriesTitle(gaulList, true);

		ADAMQueryVO topCountriesChart = ADAMQueryVOBuilder.topCountriesChart(ADAMBoxContent.BAR.toString(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		setAggregatedViewName(topCountriesChart, false);

		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		ADAMQueryVO topCountriesChartTimeSerie = ADAMQueryVOBuilder.topCountriesChart(ADAMBoxContent.TIMESERIE.toString(), timeseriesTitle, filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);		
		topCountriesChartTimeSerie.getWhereDates().put("year", baseDate);
		
		if(gaulList==null || gaulList!=null && gaulList.size() > 3)
			topCountriesChartTimeSerie.setDisclaimer(ADAMSubSectorORUtils.getLimitDisclaimer());
	
		setAggregatedViewName(topCountriesChartTimeSerie, false);
	
		if(gaulList==null || gaulList!=null && gaulList.size() > 3)
			topCountriesChartTimeSerie.setDisclaimer(ADAMSubSectorORUtils.getLimitDisclaimer());

		if(mapVo!=null){
			topCountriesChartTimeSerie.getSwitchResources().add(mapVo);	
			mapVo.getSwitchResources().add(topCountriesChartTimeSerie);	
		}

		topCountriesChart.getSwitchResources().add(topCountriesChartTimeSerie);
		topCountriesChartTimeSerie.getSwitchResources().add(topCountriesChart);

		System.out.println("^^^^^^^ topCountriesChartTimeSerie.getSwitchResources() = "+topCountriesChartTimeSerie.getSwitchResources().size());
		System.out.println("^^^^^^^ topCountriesChart.getSwitchResources() = "+topCountriesChart.getSwitchResources().size());
		System.out.println("^^^^^^^ mapVO.getSwitchResources() = "+mapVo.getSwitchResources().size());
		

		return topCountriesChart;
	}
	

	private static String getSubSectorLabel(){
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			return "ORs";
		else
			return "Sub-Sectors";
	}
	
	private static String getFormattedSubSector(final Map<String, String> subSectorList){
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			//code = SubDac_I_I3
			String c = "";
			for(String code : subSectorList.keySet()) {
				c = getSubSectorCode(code);
			}
			
			//String code = ADAMViewMenuBuilder.getUnFormattedKeyDescription(subSectorList); //pick out the key = code
			return c;
	    }
		else
			return ADAMViewMenuBuilder.getUnFormattedDescription(subSectorList);//pick out the value
	}
}


