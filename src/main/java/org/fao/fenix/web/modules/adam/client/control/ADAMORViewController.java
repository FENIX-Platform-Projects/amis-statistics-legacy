package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.utils.ADAMSubSectorORUtils;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

import com.google.gwt.user.client.ui.RootPanel;

public class ADAMORViewController extends ADAMFAOViewController {

	//Global View
    static void createORView(final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;

		removeAllBoxes();
		
		addViewMenu("Organizational Result View:", null, null, null, orList, null);
		
		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, orList, filters);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
	
		// BOTTOM_RIGHT
		//ADAMQueryVO topCountriesChart = createCountriesChartVO(null, orList, filters );
		//ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), topCountriesChart, objectWindowId);
		
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);
		
		// MAP_RIGHT
		addCountryMap(null, orList, filters);
		
	}

	static void createCountryORView(final Map<String, String> gaulList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Recipient Country/OR View:", gaulList, null, null, orList, null);
		
		restoreVisibility();
			
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, orList, filters);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
			
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, null, orList, filters);
		
	}
	
	static void createDonorORView(final Map<String, String> donorList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Resource Partner/OR View:", null, donorList, null, orList, null);
		
		restoreVisibility();
			
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);

		// TOP_RIGHT
		addDonorRelatedObject(orList, donorList, null, filters);
		
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryMap(null, orList, filters);
		
	}
	
	static void createSoOrView(final HashMap<String, String> soList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("SO/OR View:", null, null, soList, orList, null);

		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, orList, filters);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
			
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryMap(null, orList, filters);
		
	}
	

	static void createCountryDonorORView(final Map<String, String> gaulList, final Map<String, String> donorList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Recipient Country/Resource Partner/OR View:", gaulList, donorList, null, orList, null);

		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		// TOP_RIGHT
		addDonorRelatedObject(orList, donorList, gaulList, filters);
		
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, donorList, orList, filters);
		
	}
	
	
	static void createCountrySoOrView(final Map<String, String> gaulList, final HashMap<String, String> soList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Recipient Country/SO/OR View:", gaulList, null, soList, orList, null);

		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		// TOP_RIGHT
		ADAMQueryVO topDonorsChart = createDonorsChartVO(null, orList, filters);
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
			
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, null, orList, filters);
		
	}
	

	static void createDonorSoOrView(final Map<String, String> donorList, final HashMap<String, String> soList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Resource Partner/SO/OR View:", null, donorList, soList, orList, null);

		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		// TOP_RIGHT
		addDonorRelatedObject(orList, donorList, null, filters);
		
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryMap(null, orList, filters);
		
	}
	
	static void createCountryDonorSoOrView(final Map<String, String> gaulList, final Map<String, String> donorList, final HashMap<String, String> soList, final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.FAOVIEW;
	
		removeAllBoxes();
		
		addViewMenu("Recipient Country/Resource Partner/SO/OR View:", gaulList, donorList, soList, orList, null);

		restoreVisibility();
		
		// TOP_LEFT
		ADAMQueryVO topSectorTimeSerie = createORsTimeSeriesChartVO(filters, orList);
		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		// TOP_RIGHT
		addDonorRelatedObject(orList, donorList, gaulList, filters);
		
		// BOTTOM_RIGHT
		addORRelatedObject(orList, filters);

		// MAP_LEFT
		addCountryRelatedObject(gaulList, donorList, orList, filters);
		
	}
	
	private static ADAMQueryVO createORComparisonChartVO(final HashMap<String, String> orList, HashMap<String, List<String>> filters ){
		String selectedOr = ADAMViewMenuBuilder.getUnFormattedKey(orList);	
		ADAMQueryVO orComparisonChart = ADAMQueryVOBuilderFAO.compareORAgainstTop10(ADAMBoxContent.BAR_STACK_SECTOR_COMPARISON.name(), selectedOr+" compared with the Top Ten ORs",filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), orList);
		orComparisonChart.setDisclaimer(ADAMSubSectorORUtils.getDifferenceChartDisclaimer(selectedOr, crs_aggregationColumn.getGaulLabel()));

		return orComparisonChart;
	}

	private static ADAMQueryVO createORPieChartVO(HashMap<String, String> ors, HashMap<String, List<String>> filters ){

		ADAMQueryVO topPurposeChart = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.PIE.name(), "ORs ("+ADAMController.baseUnit+")", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  null, ADAMBoxContent.RETURN_OR.toString(), ors);
				
		ADAMQueryVO topPurposeChartBar = ADAMQueryVOBuilderFAO.oRsFAOview(ADAMBoxContent.BAR.toString(), "Bar Chart Comparison of Selected ORs", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), ors, 10);
		
		topPurposeChart.getSwitchResources().add(topPurposeChartBar);
		topPurposeChartBar.getSwitchResources().add(topPurposeChart);

		return topPurposeChart;
	}

	private static ADAMQueryVO createAssistanceTableVO(HashMap<String, String> orList, final String selectedItem, HashMap<String, List<String>> filters, final boolean isCountry, String selectedCountriesLabel){
		String title="";

		if(orList.size() > 1)  {
			if(isCountry)
				title = "Total Assistance to the Selected ORs by Year for "+selectedItem + " ("+ADAMController.baseUnit+")";
			else
				title = selectedItem+" Assistance to the Selected ORs for "+selectedCountriesLabel+" by Year ("+ADAMController.baseUnit+")";
		}	
		else { 
			if(isCountry)
				title = "Total Assistance to "+ADAMViewMenuBuilder.getUnFormattedKey(orList)+" by Year for " +selectedItem+" ("+ADAMController.baseUnit+")";
			else
				title = selectedItem+" Assistance to "+ADAMViewMenuBuilder.getUnFormattedKey(orList)+" for "+selectedCountriesLabel+ " by Year ("+ADAMController.baseUnit+")";
		}
		 
		ADAMQueryVO qvo = ADAMQueryVOBuilderFAO.getTable(ADAMBoxContent.CONTRIBUTION_TABLE.name(), title, orList, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), ADAMBoxContent.RETURN_OR.toString(), null);
		
		return qvo;
	}

	private static ADAMQueryVO createImplementingAgencyTableVO(HashMap<String, String> orList, final String selectedItem, HashMap<String, List<String>> filters){

		String title="";
		
		if(orList.size() > 1)  
			title = "Total Budget provided to Top Implementing Agencies by all Selected Resource Partner(s) for the Selected ORs ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
	
		else 
			title = "Total Budget provided to Top Implementing Agencies by all Selected Resource Partner(s) for "+ADAMViewMenuBuilder.getUnFormattedKey(orList)+" ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
	
		ADAMQueryVO qvo = ADAMQueryVOBuilderFAO.getTable(ADAMBoxContent.IMPLEMENTING_AGENCIES_TABLE.name(), title, orList, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), ADAMBoxContent.RETURN_OR.toString(), 10);
		
		return qvo;
	}
	
	private static ADAMQueryVO createDonorsChartVO(Map<String, String> donorList, HashMap<String, String> orList, HashMap<String, List<String>> filters ){

		String title = ADAMSubSectorORUtils.getApproriateBarChartTitle(donorList, false);
		String timeseriesTitle = ADAMSubSectorORUtils.getApproriateTimeseriesTitle(donorList, false);
		
		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilderFAO.topDonorsChartFAOview(ADAMBoxContent.BAR.toString(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), orList, false, true, 10);
		List<String> selects = new ArrayList<String>();
		selects.add("donorname");
			
		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		ADAMQueryVO topDonorsChartTimeSerie = ADAMQueryVOBuilderFAO.timeseriesChartFAOview(timeseriesTitle,  selects, filters, new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), orList, timeserieLimit);
		if(donorList==null || donorList!=null && donorList.size() > 3)
			topDonorsChartTimeSerie.setDisclaimer(ADAMSubSectorORUtils.getLimitDisclaimer());
		
		
		topDonorsChart.getSwitchResources().add(topDonorsChartTimeSerie);
		topDonorsChartTimeSerie.getSwitchResources().add(topDonorsChart);

		return topDonorsChart;
	}


	public static ADAMQueryVO createCountriesChartVO(final Map<String, String> gaulList, HashMap<String, String> ors, HashMap<String, List<String>> filters, ADAMQueryVO mapVo){
		
		String title = ADAMSubSectorORUtils.getApproriateBarChartTitle(gaulList, true);
		String timeseriesTitle = ADAMSubSectorORUtils.getApproriateTimeseriesTitle(gaulList, true);
	
		ADAMQueryVO topCountriesChart = ADAMQueryVOBuilderFAO.topCountriesChartFAOview(ADAMBoxContent.BAR.toString(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), ors, false, true, 10);
		
		List<String> selects = new ArrayList<String>();
		selects.add("recipientname");
		
		//dates = new ArrayList<String>() i.e. get all dates for timeseries
		ADAMQueryVO topCountriesChartTimeSerie = ADAMQueryVOBuilderFAO.timeseriesChartFAOview(timeseriesTitle,  selects, filters,  new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), ADAMBoxContent.RETURN_OR.toString(), ors, timeserieLimit);
		if(gaulList==null || gaulList!=null && gaulList.size() > 3)
			topCountriesChartTimeSerie.setDisclaimer(ADAMSubSectorORUtils.getLimitDisclaimer());
		
		if(mapVo!=null){
			topCountriesChartTimeSerie.getSwitchResources().add(mapVo);	
			mapVo.getSwitchResources().add(topCountriesChartTimeSerie);	
		}
		
		topCountriesChart.getSwitchResources().add(topCountriesChartTimeSerie);
		topCountriesChartTimeSerie.getSwitchResources().add(topCountriesChart);

		return topCountriesChart;
	}

	private static ADAMQueryVO createORsTimeSeriesChartVO(HashMap<String, List<String>> filters, final HashMap<String, String> ors ){
		String title="";
		
		if(ors.size() == 1) {
			for(String key: ors.keySet())
				title ="Time series for "+key;
		}	
		else 
			title = "Time series of Selected ORs ";
		
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilderFAO.viewORs(ADAMBoxContent.TIMESERIE.name(), title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(),  null, ADAMBoxContent.RETURN_OR.toString(), ors);
		return topSectorTimeSerie;
	}

	private static void addDonorRelatedObject(HashMap<String, String> orList, Map<String, String> donorList, Map<String, String> gaulList, HashMap<String, List<String>> filters){
		
		if(donorList.size() > 1) { // CHART
			ADAMQueryVO topDonorsChart = createDonorsChartVO(donorList, orList, filters );
			ADAMController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
		} else { // TABLE
			String selectedDonor = ADAMViewMenuBuilder.getUnFormattedDescription(donorList);
			String selectedCountriesLabel = ADAMSubSectorORUtils.getRecipientCountryLabel(gaulList);
			
			ADAMQueryVO qvo = createAssistanceTableVO(orList, selectedDonor, filters, false, selectedCountriesLabel);
			ADAMTableController.addTable("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), qvo, objectWindowId) ;
		}		
	}

	private static void addORRelatedObject(HashMap<String, String> orList, HashMap<String, List<String>> filters){
		if(orList.size() > 1) { // OR PIE CHART			
			ADAMQueryVO topPurposeChart = createORPieChartVO(orList,filters );
			ADAMTableController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topPurposeChart, objectWindowId);
		}
		else {
			//OR COMPARISON CHART		
			ADAMQueryVO orComparisonChart = createORComparisonChartVO(orList, filters );
			ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), orComparisonChart, objectWindowId);
		}
	}


	private static void addCountryRelatedObject(Map<String, String> gaulList, Map<String, String> donorList, HashMap<String, String> orList, HashMap<String, List<String>> filters){
		if(gaulList.size() > 1) { // MAP
			 addCountryMap(gaulList, orList, filters);
		} else { // TABLE
			//Don't filter on the donor
			//if(donorList!=null && filters.containsKey("donorcode"))
			//	filters.remove("donorcode");
			
			String selectedCountry = ADAMViewMenuBuilder.getUnFormattedDescription(gaulList);
			
			ADAMQueryVO qvo = createImplementingAgencyTableVO(orList, selectedCountry, filters);
			//ADAMQueryVO qvo = createAssistanceTableVO(orList, selectedCountry, filters, true, "");
			ADAMTableController.addTable("MAP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), qvo, objectWindowId) ;
		}
	}
	
	private static void addCountryMap(Map<String, String> gaulList, HashMap<String, String> orList, HashMap<String, List<String>> filters) {
		String title = "";
		
		if(orList.size() == 1) {
			for(String key: orList.keySet())
				title ="Budget for "+key +" by Country - ";
		}	
		else 
			title = "Budget for Selected ORs by Country - ";
		
		
		ADAMQueryVO q = ADAMMapController.getGoogleMapsFAOViewVO(title, filters, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, ADAMBoxContent.RETURN_OR.toString(), orList, objectWindowId, false, true);
		
		ADAMQueryVO c = createCountriesChartVO(gaulList, orList, filters, q);		
		
		q.getSwitchResources().add(c);
		c.getSwitchResources().add(q);
		
		ADAMMapController.addGoogleMaps(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true,  objectWindowId);
		
	}
	
	private static void removeAllBoxes(){
		
		removeBoxes();
		//removeQuestions();
		//filters();
	}
	
	private static void restoreVisibility(){
        ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");
	
	}
	
	
}
