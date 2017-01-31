package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;


public class ADAMQueryVOBuilderFAO extends ADAMQueryVOBuilder {
	
	
	public static ADAMQueryVO topDonorsChartFAOview(String chartType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, HashMap<String, String> priorities, boolean isSO, boolean isOR, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
		if(isSO)
			q.setFilterFrameworkType(filterFramework);
		
		q.setPriorities(priorities);
		
		if(isOR)
			q.setReturnFrameworkType(filterFramework);
		
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setOutputType(chartType);
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		List<String> selects = new ArrayList<String>();
		selects.add("donorcode");
		selects.add("donorname");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		q.setWheres(wheres);

		if(limit!=null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		if(isOR)
			q.setIsOR(isOR);
		
		//q.setIsSO(isSO);
		
		return q;
	}
	
	public static ADAMQueryVO timeseriesChartFAOview(String title,  List<String> selects, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, HashMap<String, String> priorities, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		
	    // Only ORS
		q.setReturnFrameworkType(filterFramework);
		q.setPriorities(priorities);
		
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setOutputType(ADAMBoxContent.TIMESERIE.name());
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setSelects(selects);

		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		q.setWheres(wheres);

		if(limit!=null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		q.setIsOR(true);
		
		return q;
	}
	
	public static ADAMQueryVO topCountriesChartFAOview(String chartType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, HashMap<String, String> priorities, boolean isSO, boolean isOR, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
		if(isSO)
		q.setFilterFrameworkType(filterFramework);
		
		q.setPriorities(priorities);
		
		if(isOR)
			q.setReturnFrameworkType(filterFramework);
		
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setOutputType(chartType);
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		List<String> selects = new ArrayList<String>();
		selects.add("recipientcode");
		selects.add("recipientname");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		q.setWheres(wheres);

		if(limit!=null)
			q.setLimit(limit);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		if(isOR)
			q.setIsOR(isOR);
		//q.setIsOR(isOR);
		//q.setIsSO(isSO);
		
		return q;
	}
	
	
	public static ADAMQueryVO oRsFAOview(String chartType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, HashMap<String, String> priorities, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		q.setPriorities(priorities);
		q.setReturnFrameworkType(filterFramework);
		
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setOutputType(chartType);
		q.setTitle(title);

		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		q.setWheres(wheres);

		if(limit!=null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		q.setIsOR(true);
		
		
		return q;
	}
	
public static ADAMQueryVO compareORAgainstTop10(String chartype, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String returnFramework, HashMap<String, String> priorities) {
		
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(chartype);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		q.setxLabel("");
		q.setyLabel(aggregationLabel + " Difference ["+ADAMController.baseUnitShort+"]");
		
		
		q.setPriorities(priorities);
		q.setReturnFrameworkType(returnFramework);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);

		q.setWheres(wheres);

		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// checking if there is an OR request
		q.setIsOR(true);
		q.setIsSO(false);
		
		
		return q;
	}

	public static ADAMQueryVO googleMapsQueryFAOView(String title, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String filterFramework, HashMap<String, String> priorities) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
		q.setFilterFrameworkType(filterFramework);
		q.setPriorities(priorities);
		
		
		q.setOutputType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR_GOOGLE.name());
		q.setResourceType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR_GOOGLE.name());
		q.setTitle(title);
//		q.setTitle("Budget in Agriculture Per Country - " + title + " ("+ADAMController.baseUnit+")");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);

		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);
		
		// adding filters
		HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
		
		queryFilters.putAll(filters);
		wheres.putAll(queryFilters);

		
		
		
		q.setWheres(wheres);
		

		return q;
	}
	
	
	
	public static ADAMQueryVO viewORs(String chartype, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, String returnFrameworkType, HashMap<String, String> priorities) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
		q.setFilterFrameworkType(filterFramework);
		q.setReturnFrameworkType(returnFrameworkType);
		q.setPriorities(priorities);
		
		q.setOutputType(chartype);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		List<String> selects = new ArrayList<String>();
		selects.add("purposecode");
		selects.add("purposename");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);

		q.setWheres(wheres);

		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// checking if there is an OR request
		q.setIsOR(true);
		
		//q.setDisclaimer(getORsDisclaimer());
		
		return q;
	}
	
	
	
	public static ADAMQueryVO favouriteChannelsFAOView(String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String filterFramework, HashMap<String, String> priorities) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
		q.setFilterFrameworkType(filterFramework);
		q.setPriorities(priorities);
		
		
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + "["+ADAMController.baseUnit+"]");
		
		List<String> selects = new ArrayList<String>();
		selects.add("channelcode");
		selects.add("channelname");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
	    q.setDatasetCodes(datasetCodes);
	    q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
	    
	    q.setMeasurementUnit(ADAMController.baseUnitShort);
	    
	    Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		
		q.setWheres(wheres);

		
		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	
	
	public static ADAMQueryVO viewSOs(String chartype, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, String returnFrameworkType, Map<String, String> priorities) {
		ADAMQueryVO q = new ADAMQueryVO();
		
		// SOs and ORs
//		q.setFilterFrameworkType(filterFramework);
		q.setReturnFrameworkType(returnFrameworkType);
		q.setPriorities(priorities);
		
		q.setOutputType(chartype);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		List<String> selects = new ArrayList<String>();
		selects.add("purposecode");
		selects.add("purposename");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		

		q.setWheres(wheres);

		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// checking if there is an OR request
		q.setIsOR(true);
		q.setIsSO(true);
		
		return q;
	}
	
	 public static ADAMQueryVO mostFinancedORs(String outputType, String title, List<String> dates, HashMap<String, List<String>> filters, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit,  String returnFrameworkType, Map<String, String> ors) {
			
			ADAMQueryVO q = new ADAMQueryVO();
			q.setReturnFrameworkType(returnFrameworkType);
			q.setPriorities(ors);
			
			q.setOutputType(outputType);
			q.setResourceType(ADAMBoxContent.CHART.name());
			q.setTitle(title);
			q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
			List<String> selects = new ArrayList<String>();
			
			selects.add("purposecode");
			selects.add("purposename");
			
			q.setSelects(selects);
			Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
			
			if ( !dates.isEmpty())
				wheres.put("year", dates);
			
			
			q.setWheres(wheres);
			wheres.putAll(filters);
			Map<String, String> aggregations = new HashMap<String, String>();
			aggregations.put(aggregationColumn, "SUM");
			q.setAggregations(aggregations);
			List<String> datasetCodes = new ArrayList<String>();
			datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
			q.setDatasetCodes(datasetCodes);
			q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
			
			q.setMeasurementUnit(ADAMController.baseUnitShort);
			
		if ( limit != null)
				q.setLimit(limit);
			q.setSortingOrder("DESC");
			q.setBoxColor(boxColor);
			
			// setting dimensions
			q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
			q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
			q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
			q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
			
			
			// checking if there is an OR request
			q.setIsOR(true);
			q.setIsSO(true);
			
			return q;
		}
	 
	 public static ADAMQueryVO getTable(String tableType, String title, HashMap<String, String> ors, List<String> dates, HashMap<String, List<String>> filters, String boxColor, String returnFrameworkType, Integer limit) {
			ADAMQueryVO q = new ADAMQueryVO();
			q.setReturnFrameworkType(returnFrameworkType);
			q.setPriorities(ors);
			
			q.setOutputType(tableType);
			q.setTitle(title);
			List<String> datasetCodes = new ArrayList<String>();
			datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
			q.setDatasetCodes(datasetCodes);
			q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
			
			q.setMeasurementUnit(ADAMController.baseUnitShort);
			
			q.setBoxColor(boxColor);
				
			Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
			wheres.put("year", dates);
			
			// adding filters
			HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
			queryFilters.putAll(filters);
			wheres.putAll(queryFilters);
			q.setWheres(wheres);
			
	
			// setting dimensions
			q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
			q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
			q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
			q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
			
			q.setIsOR(true);
		
			if(limit!=null)
				q.setLimit(limit);
			
			
			return q;
		}
	 
	 
	public static String getORsDisclaimer(){
		
		return "This data has been converted from the OECD-DAC Classification to the FAO Strategic Framework; budget maybe split among different ORs.";
		
	}
}