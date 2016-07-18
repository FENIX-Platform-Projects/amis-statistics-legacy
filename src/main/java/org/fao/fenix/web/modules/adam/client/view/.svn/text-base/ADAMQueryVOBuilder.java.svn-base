package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;

import com.google.gwt.user.client.ui.RootPanel;

public class ADAMQueryVOBuilder {
	
	public static ADAMQueryVO generalQueryVO(String title, String outputType, String datasetCode, List<String> dates, Map<String, List<String>>filters, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setTitle(title);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(datasetCode);
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(datasetCode);
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		wheres.put("year", dates);
		// adding  filters
		wheres.putAll(filters);		
		
		q.setWheres(wheres);
		q.setBoxColor(boxColor);

		return q;
	}
	
	public static ADAMQueryVO topAgriculturalSectorsDonorCountryView(String donorLabel, String gaulLabel, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit, Boolean faoFilter, Boolean agricultureFilter) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());
		q.setTitle("Agricultural Sectors/Channels Table");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setBoxColor(boxColor);
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding filters
		HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
		
		if ( faoFilter ){
			ADAMController.addPurposeCodesFilter(filters, ADAMConstants.faoViewPurposes);
		}
		else if ( agricultureFilter ) {
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			queryFilters.putAll(filters);
			queryFilters.put("dac_sector", dac_sectors);	
			wheres.putAll(queryFilters);
		}

		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		q.setWheres(wheres);
		
	if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	
	public static ADAMQueryVO topAgriculturalCountryView(String gaulLabel, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());
		q.setTitle("Agricultural Sectors/Resource Partner/Channels Table");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setBoxColor(boxColor);
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding filters
		HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
		List<String> dac_sectors = new ArrayList<String>();
		dac_sectors.add("300");
		queryFilters.putAll(filters);
		queryFilters.put("dac_sector", dac_sectors);
		wheres.putAll(queryFilters);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		q.setWheres(wheres);
		
	if ( limit != null)
			q.setLimit(limit);
		return q;
	}
	

	public static ADAMQueryVO mostFinancedSectors(String title, String outputType, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		System.out.println("mostFinancedSectors");
		ADAMQueryVO q = new ADAMQueryVO();
		
//		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		List<String> selects = new ArrayList<String>();
		selects.add("dac_sector");
		selects.add("dac_label");
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
		
//		q.setCodedColumn("dac_sector");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
	if ( limit != null)
			q.setLimit(limit);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		
		// FILTERS
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		if ( !dates.isEmpty())
			wheres.put("year", dates);

		
		q.setWheres(wheres);
		
		return q;
	}
	
	public static ADAMQueryVO mostFinancedSectors(String outputType, String title, List<String> dates, HashMap<String, List<String>> filters, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		
		selects.add("dac_sector");
		selects.add("dac_label");
		
//		selects.add("c.label");
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
		
//		q.setCodedColumn("dac_sector");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		
	if ( limit != null)
			q.setLimit(limit);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
 public static ADAMQueryVO mostFinancedSubSectors(String outputType, String title, List<String> dates, HashMap<String, List<String>> filters, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit, Boolean isOR, Boolean isSO) {
		
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		// checking if there is an OR request
		q.setIsOR(isOR);
		q.setIsSO(isSO);
		
	if ( limit != null)
			q.setLimit(limit);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}


	public static ADAMQueryVO mostFinancedAgriculturalSectors(String outputType, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Agricultural Sectors ("+ADAMController.baseUnit+")");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("purposecode");
		selects.add("purposename");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> dac_sectores = new ArrayList<String>();
		dac_sectores.add("300");
		wheres.put("dac_sector", dac_sectores);
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		if ( limit != null)
			q.setLimit(limit);
	
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
	public static ADAMQueryVO mostFinancedSubSectors(String outputType, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			q.setTitle("Most Financed ORs ("+ADAMController.baseUnit+")");
		} else {
			q.setTitle("Most Financed Sub-Sectors ("+ADAMController.baseUnit+")");
		}
		
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("purposecode");
		selects.add("purposename");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		if ( limit != null)
			q.setLimit(limit);
	
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
	public static ADAMQueryVO donorViewFavouriteChannels(String title, List<String> donorCodes, String donorLabel, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean agricultureFilter) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + "["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("donorcode", donorCodes);
		wheres.put("year", dates);
		
		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}
		
		q.setWheres(wheres);
//		q.setCodedColumn("channelcode");
//		q.setCodingSystem("ADAMChannelCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
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
	
	public static ADAMQueryVO donorCountryViewFavouriteChannels(String donorLabel, String gaulLabel, List<String> dates, Map<String, List<String>> filters, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(donorLabel +"/" + gaulLabel + " - Channels of Delivery");
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.putAll(filters);
		wheres.put("year", dates);
		q.setWheres(wheres);
//		q.setCodedColumn("channelcode");
//		q.setCodingSystem("ADAMChannelCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		return q;
	}

	public static ADAMQueryVO topDonors(List<String> dates, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_DONORS_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Top Ten Resource Partners");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		q.setWheres(wheres);
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);
		
		
		q.setBoxColor(boxColor);
		
	if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	
	public static ADAMQueryVO topDonorsFiltered(List<String> dates, Map<String, List<String>>filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_DONORS_TABLE_FILTERED.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Top Ten Resource Partners");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding other filters
		wheres.putAll(filters);		
		q.setWheres(wheres);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);
		
		q.setBoxColor(boxColor);
		
		if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	
	
	public static ADAMQueryVO topSectorsChannelsDonorCountry(List<String> dates, Map<String, List<String>> filters, String aggregationColumn, String aggregationType,  String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Favorite Sectors/Channels");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding other filters
		wheres.putAll(filters);			
		q.setWheres(wheres);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);
		
		q.setBoxColor(boxColor);
		
		if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	

	
	
//
	public static ADAMQueryVO topSectorsDonorView(String label, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_SECTORS_DONOR_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Top Sectors");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding filters
		wheres.putAll(filters);
		q.setWheres(wheres);
		

		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);
		
		q.setBoxColor(boxColor);
		
		if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}

	public static ADAMQueryVO topAgriculturalSectorsDonorView(String label, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Agricultural Sectors");
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
		List<String> dac_sectors = new ArrayList<String>();
		dac_sectors.add("300");
		queryFilters.putAll(filters);
		queryFilters.put("dac_sector", dac_sectors);
		wheres.putAll(queryFilters);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		q.setWheres(wheres);
		
		if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	
	public static ADAMQueryVO topSectorsDonorViewTable(String label, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_SECTORS_DONOR_VIEW_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Budget by Sub-sectors");
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
		//List<String> dac_sectors = new ArrayList<String>();
		//dac_sectors.add("300");
		queryFilters.putAll(filters);
		//queryFilters.put("dac_sector", dac_sectors);
		wheres.putAll(queryFilters);
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		q.setWheres(wheres);
		
		if ( limit != null)
			q.setLimit(limit);
		
		return q;
	}
	
//	
	public static ADAMQueryVO favouritePurposesQuestionsView(String code, String label, List<String> dates, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.FAVOURITE_PURPOSES_QUESTIONS_VIEW.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());

		q.setTitle("Agricultural Sectors");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(label);
		wheres.put(code, filter);
		wheres.put("year", dates);
		q.setWheres(wheres);
		return q;
	}
//	
	public static ADAMQueryVO questionsViewBudgetPerSector(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());

		q.setTitle(label + " - Budget Per Sector");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("dac_sector");
		selects.add("dac_label");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(code);
		wheres.put("recipientcode", filter);
		wheres.put("year", dates);
		q.setWheres(wheres);
//		q.setCodedColumn("dac_sector");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
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
//	
	public static ADAMQueryVO questionsViewMajorDonors(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Most active resource partners in " + label);
		
//		q.setTitle(label + " - Budget Per Sector");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("donorcode");
		selects.add("donorname");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(code);
		wheres.put("recipientcode", filter);
		wheres.put("year", dates);
		q.setWheres(wheres);
//		q.setCodedColumn("donorcode");
//		q.setCodingSystem("CRSDonors");
//		q.setLangCode(CheckLanguage.getLanguage());
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
	
	public static ADAMQueryVO questionsViewMajorDonorsInAgriculture(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Most active resource partners in the agricultural sector");
		
//		q.setTitle(label + " - Budget Per Sector");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("donorcode");
		selects.add("donorname");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filterRecipient = new ArrayList<String>();
	
		filterRecipient.add(code);
		wheres.put("recipientcode", filterRecipient);
		
		List<String> filterSecotor = new ArrayList<String>();
		filterSecotor.add("300");
		wheres.put("dac_sector", filterSecotor);
		
		wheres.put("year", dates);
		
		q.setWheres(wheres);
//		q.setCodedColumn("donorcode");
//		q.setCodingSystem("CRSDonors");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		return q;
	}
	
	public static ADAMQueryVO questionsViewFavouriteDeliveryChannel(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Most used channels of delivery in " + label);
		
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filterRecipient = new ArrayList<String>();
		filterRecipient.add(code);
		wheres.put("recipientcode", filterRecipient);
		wheres.put("year", dates);
		q.setWheres(wheres);
//		q.setCodedColumn("channelcode");
//		q.setCodingSystem("ADAMChannelCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(7);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	public static ADAMQueryVO questionsViewFAOProjectTypes(String code, String label,  List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("FAO interventions in " + label);
 
//		q.setTitle(label + " - Budget Per Sector");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("purposecode");
		selects.add("purposename");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "COUNT");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filterRecipient = new ArrayList<String>();
		
		filterRecipient.add(code);
		wheres.put("recipientcode", filterRecipient);
		
		List<String> filterSector = new ArrayList<String>();
		filterSector.add("41301");
		wheres.put("channelcode", filterSector);
		
		wheres.put("year", dates);
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("CRSPurposes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(7);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	
	public static ADAMQueryVO questionsViewFAOProjectTypes(String label,  HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());

		q.setTitle(label + " - Budget Per Sector");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
//		selects.add("c.label");
		selects.add("purposecode");
		selects.add("purposename");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "COUNT");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		wheres.putAll(filters);
		
		List<String> filterSector = new ArrayList<String>();
		// fao channel code
		filterSector.add("41301");
		wheres.put("channelcode", filterSector);
		
		wheres.put("year", dates);
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("CRSPurposes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(7);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	/*** country ***/
	
	
	public static ADAMQueryVO mostFinancedSectors(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());

		q.setTitle(label + " - Favourite Sectors");
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
//		selects.add("c.label");
		selects.add("dac_sector");
		selects.add("dac_label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(code);
		wheres.put("recipientcode", filter);
		wheres.put("year", dates);
		q.setWheres(wheres);


//		q.setCodedColumn("dac_sector");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(10);
		q.setSortingOrder("DESC");
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	
	public static ADAMQueryVO mostFinancedSectorsByDonor(String code, String label, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Most financed sectors");
//		q.setTitle(label + "'s most financed sectors");
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
//		selects.add("c.label");
		selects.add("dac_sector");
		selects.add("dac_label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(code);
		wheres.put("donorcode", filter);
		wheres.put("year", dates);
		q.setWheres(wheres);


//		q.setCodedColumn("dac_sector");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		q.setLimit(10);
		q.setSortingOrder("DESC");
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	

	
	
	
	/***
	 * 
	 * 
	 * 
	 * @param filters
	 * @param dates
	 * @param boxColor
	 * @return
	 */
	public static ADAMQueryVO mostFinancedPurposeSectors(String outputType, HashMap<String, List<String>> filters, List<String> dates, String title, String boxColor, String aggregationColumn, String aggregationLabel, Boolean faoFilter, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title); 
//		q.setTitle("Sub-sectoral breakdown of " + label + " aid to agriculture");
		
//		q.setTitle(label + " - Agricultural Sectors");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
//		selects.add("c.label");
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
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> dac_sectores = new ArrayList<String>();
		
		if ( faoFilter ){
			// TODO: test
			ADAMController.addPurposeCodesFilter(filters, ADAMConstants.faoViewPurposes);
//			dac_sectores.add("300");
//			wheres.put("dac_sector", dac_sectores);
		}
		
		if ( !dates.isEmpty() )
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		
		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	public static ADAMQueryVO mostFinancedPurposeSectors(String outputType, HashMap<String, List<String>> filters, List<String> dates, String title, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title); 
//		q.setTitle("Sub-sectoral breakdown of " + label + " aid to agriculture");
		
//		q.setTitle(label + " - Agricultural Sectors");
		// q.setxLabel("Sectors");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
//		selects.add("c.label");
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
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> dac_sectores = new ArrayList<String>();
		dac_sectores.add("300");
		wheres.put("dac_sector", dac_sectores);
		
		if ( !dates.isEmpty() )
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		q.setWheres(wheres);
//		q.setCodedColumn("purposecode");
//		q.setCodingSystem("ADAMSectorCodes");
//		q.setLangCode(CheckLanguage.getLanguage());
		
		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	
	public static ADAMQueryVO mostFinancedCountriesByDonor(String donorCode, String label, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		//System.out.println("mostCountriesByDonor");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Top ten recipent of " + label + " aid flows ");
		
		// q.setxLabel("Countries");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("recipientcode");
		selects.add("recipientname");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		q.setWheres(wheres);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
//		q.setCodedColumn("recipientcode");
//		q.setCodingSystem("CRSRecipients");
//		q.setCodingSystem("CRS_RECIPIENTS");
//		q.setLangCode(CheckLanguage.getLanguage());
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
	
	
	
	
		
	
	public static ADAMQueryVO countriesBudgetByFilter(String label, List<String> dates, HashMap<String, List<String>> filters, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR.toString());
		q.setResourceType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR.name());
		q.setTitle(label + " - Budget Per Country");
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		q.setWheres(wheres);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		
		return q;
	}
	
	
	public static ADAMQueryVO topCountriesByDonor(String label, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, String boxColor, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_COUNTRIES_BY_DONOR.name());
		q.setTitle("Main recipients of " + label + " and sectoral allocation");
//		q.setTitle(label + " - Top Countries/Sectors");
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
		
		// aggregation column and type (SUM, AVG etc)
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		
		q.setLimit(limit);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	
	
	// GET PROJECTS
	public static ADAMQueryVO getProjects(Map<String, String> selectsMap, List<String> dates, HashMap<String, List<String>> filters, Boolean addFAOFilters) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PROJECTS_LIST_CRS.toString());
		q.setResourceType(ADAMBoxContent.PROJECTS_LIST_CRS.name());
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		q.setSelectsMap(selectsMap);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		/** other filters **/
		
		if ( addFAOFilters ) {
			ADAMController.addPurposeCodesFilter(filters, ADAMConstants.faoViewPurposes);
		}
		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		

	
		
			
		q.setWheres(wheres);
		
		return q;
	}
	
	// GET ROWS
	public static ADAMQueryVO getRowsCount(List<String> dates, HashMap<String, List<String>> filters) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.ROWS_COUNT.toString());
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		q.setWheres(wheres);
		return q;
	}
	
	/// CREATE RESOURCE
	
	public static ADAMQueryVO createCRSResource(HashMap<String, List<String>> filters, List<String> dates, String codedColumn,  String aggregationColumn, String aggregationType, String outputType, String resourceType, String xLabel, String measurementUnit, Integer limit, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
//		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setResourceType(resourceType);
		q.setTitle("");
		// q.setxLabel(xLabel);
		q.setyLabel(measurementUnit);
		

		

		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
//		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		/** TODO: for the series **/
//		Map<String, List<String>> series = new HashMap<String,  List<String>>();
//		series.put("year", dates);
//		q.setSeries(series);
		
		q.setWheres(wheres);
		
		
			
		
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
//		q.setCodedColumn("purposecode");
//		q.setCodedColumn(codedColumn);
//		q.setCodingSystem(getCodingSystem(codedColumn));
		
		// if coding system is null -> then don't codify it
		
		List<String> selects = new ArrayList<String>();
//		if ( q.getCodingSystem() == null )
//			selects.add(codedColumn);
//		else
		selects.add(codedColumn);
		selects.add(getColumnLabel(codedColumn));
		q.setSelects(selects);
		
		q.setLangCode(CheckLanguage.getLanguage());

		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
	public static ADAMQueryVO totalODADonorCommitment(Map<String, String> donorMap, Map<String, String> recipientsMap, List<String> dates, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR_WITH_CATEGORIES.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("FAO Sectors and Total ODA commitment by Top Countries for the Selected Timeperiod");
	//	q.setSubTitle("* FAO Related Sectors:  Production Sectors (Agriculture, Forestry and Fishing);  Food Security Assistance; Rural Development; Humanitarian Aid (Emergency Response, Reconstruction Relief and Rehabilitations, Disaster Prevention and Preparedness)");
		q.setDescription("The plotted commitments shown in the chart, are calculated as a sum of the selected resource partners");
		
		// q.setxLabel("Recipient Countries");
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		//selects.add("recipientcode");
		selects.add("c.label");
			
		q.setSelects(selects);
		//Map<String, String> aggregations = new HashMap<String, String>();
		//aggregations.put("\"Total ODA\"", "totalOdaSum");
		//aggregations.put("\"FAO sectors\"", "totalFaoSum");
		//q.setAggregations(aggregations);
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		List<String> viewCodes = new ArrayList<String>();
		viewCodes.add("adam_donormatrix_data");
		q.setAggregatedViewNames(viewCodes);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> donorsFilter = new ArrayList<String>();
		for(String code: donorMap.keySet())
			donorsFilter.add(code);
		wheres.put("donorcode", donorsFilter);
		List<String> filter = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filter.add(code);
		wheres.put("recipientcode", filter);
		wheres.put("year", dates);
		//wheres.put("year", dates);
		q.setWheres(wheres);
		

	//	Map<String, List<String>> series = new HashMap<String,  List<String>>();
		//List<String> seriesList = new ArrayList<String>();
		//seriesList.add("\"Total ODA\"");
		//seriesList.add("\"FAO sectors\"");
		//series.put("AGGREGATED", seriesList);
		
		
		
		Map<String, String> calculatedSeries = new LinkedHashMap<String, String>();
		calculatedSeries.put("\"FAO sectors\"", "SUM"); 
		calculatedSeries.put("\"Total ODA\"", "SUM");
		
		q.setCalculatedSeries(calculatedSeries);
		
		Map<String, String> seriesLabels = new LinkedHashMap<String, String>();
	    seriesLabels.put("\"FAO sectors\"", "FAO sectors");
		seriesLabels.put("\"Total ODA\"", "Total");
			
		q.setSeriesLabels(seriesLabels);
		
			
		q.setCodedColumn("recipientcode");
		q.setCodingSystem("ADAMRecipients");
		q.setLangCode(CheckLanguage.getLanguage());
	    q.setLimit(10); // top 15
	    q.setOrderByColumn("\"FAO sectors\""); // the order by column  determines the ordering of the recipient countries
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		//q.setSmallWidth("750");
		//q.setSmallHeight("270");
		q.setSmallWidth("650");
		q.setSmallHeight("290");
		
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);

		return q;
	}
	
	public static ADAMQueryVO totalODADonorCommitmentTimeSeries(Map<String, String> donorMap, Map<String, String> recipientsMap, String boxColor) {
		System.out.println("totalODADonorCommitmentTimeSeries");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TIMESERIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Total ODA Commitment for the Top 5 Countries Over Time");
		q.setDescription("The plotted total ODA commitment shown in the chart, is calculated as a sum of the selected resource partners");
		
		
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("recipientcode");
		selects.add("recipientname"); // replace recipientname with recipientcode IN LOCAL
		q.setSelects(selects);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put("usd_commitment", "SUM");
		q.setAggregations(aggregations);
		
		q.setLimit(5); //Top 5 countries
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		
		// FILTERS
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> donorsFilter = new ArrayList<String>();
		for(String code: donorMap.keySet())
			donorsFilter.add(code);
		wheres.put("donorcode", donorsFilter);
		List<String> filter = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filter.add(code);
		wheres.put("recipientcode", filter);
		
		
		q.setWheres(wheres);
		
		return q;
	}
	
	
	public static ADAMQueryVO faoSectorODADonorCommitmentTimeSeries(Map<String, String> donorMap, Map<String, String> recipientsMap, String boxColor) {
		System.out.println("faoSectorODADonorCommitmentTimeSeries");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TIMESERIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("FAO Sectors ODA commitment for the Top 5 Countries Over Time");
		q.setDescription("The plotted FAO sectors ODA commitment shown in the chart, is calculated as a sum of the selected resource partners");
		
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("recipientcode");
		selects.add("recipientname"); // replace recipientname with recipientcode IN LOCAL
		q.setSelects(selects);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put("usd_commitment", "SUM");
		q.setAggregations(aggregations);
		
		q.setLimit(5); //Top 5 partners
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		
		// FILTERS
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		List<String> faoSectorFilter = new ArrayList<String>();	
		for(String code: ADAMConstants.faoViewPurposes.keySet())
			faoSectorFilter.add(code);
		wheres.put("purposecode", faoSectorFilter);
		List<String> donorsFilter = new ArrayList<String>();
		for(String code: donorMap.keySet())
			donorsFilter.add(code);
		wheres.put("donorcode", donorsFilter);
		List<String> filter = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filter.add(code);
		wheres.put("recipientcode", filter);
		
		
		q.setWheres(wheres);
		
		return q;
	}
	
	
	
	
	public static ADAMQueryVO totalODACountryCommitment(Map<String, String> recipientsMap, Map<String, String> donorsMap, List<String> dates, String boxColor) {
		
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR_WITH_CATEGORIES.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("FAO Sectors and Total ODA commitment by Top Partners for the Selected Timeperiod");
	//	q.setSubTitle("* FAO Related Sectors:  Production Sectors (Agriculture, Forestry and Fishing);  Food Security Assistance; Rural Development; Humanitarian Aid (Emergency Response, Reconstruction Relief and Rehabilitations, Disaster Prevention and Preparedness)");
		q.setDescription("The plotted commitments shown in the chart, are calculated as a sum of the selected recipient countries");
		// q.setxLabel("Recipient Countries");
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		//selects.add("recipientcode");
		selects.add("c.label");
			
		q.setSelects(selects);
		//Map<String, String> aggregations = new HashMap<String, String>();
		//aggregations.put("\"Total ODA\"", "totalOdaSum");
		//aggregations.put("\"FAO sectors\"", "totalFaoSum");
		//q.setAggregations(aggregations);
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		List<String> viewCodes = new ArrayList<String>();
		viewCodes.add("adam_donormatrix_data");
		q.setAggregatedViewNames(viewCodes);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filterRecipients = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filterRecipients.add(code);
		wheres.put("recipientcode", filterRecipients);
		List<String> filter = new ArrayList<String>();
		for(String code: donorsMap.keySet())
			filter.add(code);
		wheres.put("donorcode", filter);
		wheres.put("year", dates);
		//wheres.put("year", dates);
		q.setWheres(wheres);
		

	//	Map<String, List<String>> series = new HashMap<String,  List<String>>();
		//List<String> seriesList = new ArrayList<String>();
		//seriesList.add("\"Total ODA\"");
		//seriesList.add("\"FAO sectors\"");
		//series.put("AGGREGATED", seriesList);
		
		Map<String, String> calculatedSeries = new LinkedHashMap<String, String>();
		calculatedSeries.put("\"FAO sectors\"", "SUM"); 
		calculatedSeries.put("\"Total ODA\"", "SUM");
		
	
		q.setCalculatedSeries(calculatedSeries);
		
		Map<String, String> seriesLabels = new LinkedHashMap<String, String>();
		seriesLabels.put("\"FAO sectors\"", "FAO sectors");
		seriesLabels.put("\"Total ODA\"", "Total");
		
		q.setSeriesLabels(seriesLabels);
		
			
		q.setCodedColumn("donorcode");
		q.setCodingSystem("ADAMDonors");
		q.setLangCode(CheckLanguage.getLanguage());
	    
		q.setLimit(10); // top 15
	    q.setOrderByColumn("\"FAO sectors\""); // the order by column  determines the ordering of the donors
	    q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		//q.setSmallWidth("750");
		//q.setSmallHeight("270");
		q.setSmallWidth("650");
		q.setSmallHeight("280");
		
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);

		return q;
	}
	
	public static ADAMQueryVO totalODACountryCommitmentTimeSeries(Map<String, String> donorMap, Map<String, String> recipientsMap, String boxColor) {
		System.out.println("totalODACountryCommitmentTimeSeries");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TIMESERIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Total ODA commitment for the Top 5 Partners Over Time");
		q.setDescription("The plotted total ODA commitment shown in the chart, is calculated as a sum of the selected recipient countries");
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("donorcode");
		selects.add("donorname"); // replace donorname with donorcode IN LOCAL
		q.setSelects(selects);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put("usd_commitment", "SUM");
		q.setAggregations(aggregations);
		
		q.setLimit(5); //Top 5 donors
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		
		// FILTERS
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> donorsFilter = new ArrayList<String>();
		for(String code: donorMap.keySet())
			donorsFilter.add(code);
		wheres.put("donorcode", donorsFilter);
		List<String> filter = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filter.add(code);
		wheres.put("recipientcode", filter);
		
		
		q.setWheres(wheres);
		
		return q;
	}
	
	
	public static ADAMQueryVO faoSectorODACountryCommitmentTimeSeries(Map<String, String> donorMap, Map<String, String> recipientsMap, String boxColor) {
		System.out.println("faoSectorODACountryCommitmentTimeSeries");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TIMESERIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("FAO Sectors ODA commitment for the Top 5 Partners Over Time");
		q.setDescription("The plotted FAO sectors ODA commitment shown in the chart, is calculated as a sum of the selected recipient countries");
			
		q.setyLabel("ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("donorcode");
		selects.add("donorname"); // replace donorname with donorcode IN LOCAL
		q.setSelects(selects);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put("usd_commitment", "SUM");
		q.setAggregations(aggregations);
		
		q.setLimit(5); //Top 5 donors
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		
		// FILTERS
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		List<String> faoSectorFilter = new ArrayList<String>();	
		for(String code: ADAMConstants.faoViewPurposes.keySet())
			faoSectorFilter.add(code);
		wheres.put("purposecode", faoSectorFilter);
		List<String> donorsFilter = new ArrayList<String>();
		for(String code: donorMap.keySet())
			donorsFilter.add(code);
		wheres.put("donorcode", donorsFilter);
		List<String> filter = new ArrayList<String>();
		for(String code: recipientsMap.keySet())
			filter.add(code);
		wheres.put("recipientcode", filter);
		
		
		q.setWheres(wheres);
		
		return q;
	}
	
	public static ADAMQueryVO totalODADonorCommitmentOriginal(String donorcode, String label, List<String> dates, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR_WITH_CATEGORIES.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle("Total ODA Commitment of "+label+" on FAO Related Sectors* (top 15 recipients shown)");
		q.setSubTitle("* FAO Related Sectors include:  Agriculture, Forestry, Fishing, Basic Health, Water Supply & Sanitation, Government & Civil Society, Other Social Infrastructure & Services, Business & Other Services, Agro-industries, Trade Policies & Regulations, General Environmnet Protection, Rural development, Dev. Food Aid/Food Security Ass., Emergency Response, Reconstruction Relief & Rehabilitation and Disaster Prevention & Preparedness");
		
		// q.setxLabel("Recipient Countries");
		q.setyLabel("Total ODA Commitment ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("c.label");
		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put("\"Total ODA\"", "SUM");
		q.setAggregations(aggregations);
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		List<String> viewCodes = new ArrayList<String>();
		viewCodes.add("adam_donormatrix_data");
		q.setAggregatedViewNames(viewCodes);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(donorcode);
		wheres.put("donorcode", filter);
		//wheres.put("year", dates);
		q.setWheres(wheres);
		

		Map<String, List<String>> series = new HashMap<String,  List<String>>();
		series.put("year", dates);		
		q.setSeries(series);
			
		q.setCodedColumn("recipientcode");
		q.setCodingSystem("ADAMRecipients");
		q.setLangCode(CheckLanguage.getLanguage());
	    q.setLimit(15); // top 15
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);

		return q;
	}
	
	
	public static ADAMQueryVO donorProfile(String donorcode, String label, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.DONOR_PROFILE_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());
		q.setTitle("Profile");
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add("ADAM_DONORMATRIX_PROFILES");
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		
		//adding filters
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(donorcode);
		wheres.put("donorcode", filter);
		q.setWheres(wheres);
			
		q.setBoxColor(boxColor);

		return q;
	}
	
	
	public static ADAMQueryVO donorProcesses(String donorcode, String label, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.DONOR_PROCESSES_TABLE.name());
		q.setResourceType(ADAMBoxContent.TABLE.name());
		q.setTitle("Processes");
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add("ADAM_DONORMATRIX_PROCESSES");
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		
		//adding filters
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		List<String> filter = new ArrayList<String>();
		filter.add(donorcode);
		wheres.put("donorcode", filter);
		q.setWheres(wheres);
			
		q.setBoxColor(boxColor);

		return q;
	}
	
	
	
	
	public static ADAMQueryVO viewMajorDonors(String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);

		
		// q.setxLabel("");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
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
	
	
	public static ADAMQueryVO viewPurposes(String chartype, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean isOR, Boolean isSO) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(chartype);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		
		// q.setxLabel("Purposes");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
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
		q.setIsOR(isOR);
		q.setIsSO(isSO);
		
		//if(isSO)
		//	q.setDisclaimer(ADAMQueryVOBuilderFAO.getORsDisclaimer());
		
		//if(isOR)
		//	q.setDisclaimer(ADAMQueryVOBuilderFAO.getORsDisclaimer());
		
		return q;
	}
	
	
	public static ADAMQueryVO viewMajorRecipients(String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.BAR.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
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
	
	public static ADAMQueryVO viewCountriesAndSectors(String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.TOP_COUNTRIES_BY_DONOR.name());
		q.setTitle(title);
//		q.setTitle(label + " - Top Countries/Sectors");
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
		
		return q;
	}
	

	
	public static ADAMQueryVO topDonorsChart(String outputType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean isOR, Boolean agricultureFilter, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
	
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}

		q.setWheres(wheres);

		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// is called from VENN or based on OR?
		q.setIsOR(isOR);
		
		return q;
	}
	
	
	public static ADAMQueryVO topCountriesChart(String outputType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean isOR, Boolean agricultureFilter, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
	
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
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
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}

		q.setWheres(wheres);

		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// is called from VENN or based on OR?
		q.setIsOR(isOR);
		
		return q;
	}
	
	
	public static ADAMQueryVO topChannelsChart(String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean isOR, Boolean agricultureFilter) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.PIE.name());
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("channelcode");
		selects.add("channelname");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		wheres.put("year", dates);
		
		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}

		q.setWheres(wheres);

		q.setLimit(10);
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		// Venn OR request?
		q.setIsOR(isOR);
		
		return q;
	}
	
	
	public static ADAMQueryVO topSectorsChart(String outputType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);


		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("dac_sector");
		selects.add("dac_label");

		q.setSelects(selects);
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);

		q.setWheres(wheres);

		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
	
	/** VENN DIAGRAM **/
	
	public static ADAMQueryVO getPriorities(ADAMBoxContent priorityType, String datasetCode, HashMap<String, String> recipients, HashMap<String, String> channels, HashMap<String, String> donors) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.VENN_PRIORITIES.name());
		q.setResourceType(priorityType.name());
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(datasetCode);
		q.setDatasetCodes(datasetCodes);
		
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setRecipients(recipients);
		q.setDonors(donors);
		q.setChannels(channels);
		
		
		return q;
	}
	
	public static ADAMQueryVO getVennMatrix(ADAMBoxContent priorityType, String datasetCode, Map<String, String> recipients, Map<String, String> channels, Map<String, String> donors) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.VENN_MATRIX.name());
		q.setResourceType(priorityType.name());
		q.setCurrentView(ADAMController.currentVIEW);
		q.setMeasurementUnit(ADAMController.baseUnitShort);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(datasetCode);
		q.setDatasetCodes(datasetCodes);
		
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
	
		q.setRecipients(recipients);
		q.setDonors(donors);
		q.setChannels(channels);
		
		
		return q;
	}
	
	
	
	public static ADAMQueryVO googleMapsQuery(String title, List<String> dates, HashMap<String, List<String>> filters, String aggregationColumn, String aggregationType, Boolean agricultureFilter) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR_GOOGLE.name());
		q.setResourceType(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR_GOOGLE.name());
//		q.setTitle("Budget in Agriculture Per Country - " + title + " ("+ADAMController.baseUnit+")");
		q.setTitle(title);
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

		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}
		
		
		
		q.setWheres(wheres);
		

		return q;
	}
	
	
	public static ADAMQueryVO mostFinancedCountries(String outputType, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean agricultureFilter, Integer limit) {
		//System.out.println("mostCountriesByDonor");
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		
		// q.setxLabel("Countries");
		q.setyLabel(aggregationLabel + " ["+ADAMController.baseUnit+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add("recipientcode");
		selects.add("recipientname");
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
		if ( agricultureFilter ) {
			// adding filters
			List<String> dac_sectors = new ArrayList<String>();
			dac_sectors.add("300");
			wheres.put("dac_sector", dac_sectors);
		}
		
		q.setWheres(wheres);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		
		q.setBoxColor(boxColor);
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		
		return q;
	}
	
	
	
	public static ADAMQueryVO createMatrix(String outputType, String resourceType, String matrixColumnText, String firstColumnCode, String firstColumnLabel, String secondColumnCode, String secondColumnLabel, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(resourceType);
		q.setTitle(title);
		q.setMatrixColumnText(matrixColumnText);
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		List<String> selects = new ArrayList<String>();
		selects.add(firstColumnCode);
		selects.add(firstColumnLabel);
		selects.add(secondColumnCode);
		selects.add(secondColumnLabel);
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
				
		q.setWheres(wheres);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		
		q.setBoxColor(boxColor);

		
		return q;
	}
	
	public static ADAMQueryVO createFAOMatrix(String outputType, String resourceType, String matrixColumnText, String firstColumnCode, String firstColumnLabel, String secondColumnCode, String secondColumnLabel, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Integer limit, HashMap<String, String> sos, HashMap<String, String> ors, String returnType) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(resourceType);
		q.setTitle(title);
		q.setMatrixColumnText(matrixColumnText);
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		if(!ors.isEmpty()) {
			q.setPriorities(ors); //selectedORs
			q.setReturnFrameworkType(ADAMBoxContent.RETURN_OR.name());
		}	
		else {
			q.setPriorities(sos); //selectedSOs
			q.setReturnFrameworkType(returnType);
		}
			
		List<String> selects = new ArrayList<String>();
		selects.add(firstColumnCode);
		selects.add(firstColumnLabel);
		selects.add(secondColumnCode);
		selects.add(secondColumnLabel);
//		selects.add("c.label");
		q.setSelects(selects);
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
				
		q.setWheres(wheres);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		if ( limit != null)
			q.setLimit(limit);
		
		q.setSortingOrder("DESC");
		
		q.setBoxColor(boxColor);

		
		return q;
	}
	
	public static ADAMQueryVO getTypesOfContribution(String title, List<String> selects, List<String> dates, HashMap<String, List<String>> filters, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.CONTRIBUTION_TABLE.name());
		q.setTitle(title);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		
		q.setSelects(selects);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding filters
		HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
		queryFilters.putAll(filters);
		wheres.putAll(queryFilters);
		q.setWheres(wheres);
		
		q.setOrderByColumn("ROUND(CAST(extract(year from year) AS NUMERIC), 0)");
		q.setSortingOrder("DESC");
	
		// aggregation column and type (SUM, AVG etc)
		/**Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, aggregationType);
		q.setAggregations(aggregations);

		
		q.setLimit(limit);**/
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		return q;
	}
	
	public static ADAMQueryVO getTopImplementingAgencies(String title, List<String> selects, List<String> dates, HashMap<String, List<String>> filters, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.IMPLEMENTING_AGENCIES_TABLE.name());
		q.setTitle(title);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		q.setBoxColor(boxColor);
		
		q.setSelects(selects);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		wheres.put("year", dates);
		
		// adding filters
		HashMap<String, List<String>> queryFilters = new HashMap<String, List<String>>();
		queryFilters.putAll(filters);
		wheres.putAll(queryFilters);
		q.setWheres(wheres);
		
		q.setOrderByColumn("commitment");
		q.setSortingOrder("DESC NULLS LAST");
		
		// setting dimensions
		q.setSmallWidth(ADAMConstants.SMALL_TABLE_CHART_WIDTH);
		q.setSmallHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		q.setBigWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		q.setBigHeight(ADAMConstants.BIG_TABLE_CHART_HEIGHT);
				
		q.setLimit(10);
		
		return q;
	}
	
	
	
	
	public static ADAMQueryVO createFRPMISRequest(String outputType, String resourceType, String url) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(resourceType);
		
		q.setText("https://extranet.fao.org/fpmis/FPMISReportServlet.jsp?APD=&countryId=ID&div=&fundG=&type=countryprofileopen&lng=EN&qlfrs=&UF=N&typeUF=&colorder=2345&pwb=&sorttype=1");
		
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		
		return q;
	}
	
	public static ADAMQueryVO getFRMIPCountryID(String title, Map<String, String> recipients) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(ADAMBoxContent.FPMIS_GET_COUNTRY_ID.toString());
		q.setResourceType(ADAMBoxContent.FPMIS_GET_COUNTRY_ID.toString());
		q.setTitle(title);
		q.setRecipients(recipients);
		q.setMeasurementUnit(ADAMController.baseUnitShort);

		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		return q;
	}
	
	
	private static String getCodingSystem(String codedColumn) {
		return ADAMConstants.crsCodingSystems.get(codedColumn);
	}
	
	private static String getColumnLabel(String codedColumn) {
		return ADAMConstants.crsColumnLabel.get(codedColumn);
	}

	public static LinkedHashMap<String, String> getCRSSelectProjectsList() {
		LinkedHashMap<String, String> selects = new LinkedHashMap<String, String>();
		selects.put("projecttitle", "projecttitle");
		selects.put("year", "ROUND(CAST(extract(year from year) AS NUMERIC), 0) AS year");
		selects.put("donorname", "donorname");
		selects.put("agencyname", "agencyname");	
		selects.put("recipientname", "recipientname");
		selects.put("dac_label", "dac_label");
		selects.put("purposename", "purposename");
		selects.put("channelname", "channelname");
		selects.put("channelreportedname", "channelreportedname");
		//Values with a large number of decimal places, are rounded to 4 decimal places
		selects.put("usd_disbursement", "CASE WHEN LENGTH(cast(usd_disbursement as varchar)) - position('.' in cast(usd_disbursement as varchar)) > 4 THEN ROUND(CAST( usd_disbursement as numeric ), 4) ELSE usd_disbursement END AS usd_disbursement");
		selects.put("usd_commitment","CASE WHEN LENGTH(cast(usd_commitment as varchar)) - position('.' in cast(usd_commitment as varchar)) > 4 THEN ROUND(CAST( usd_commitment as numeric ), 4) ELSE usd_commitment END AS usd_commitment");
		
		return selects;
	}
	
	
	public static ADAMQueryVO createComparativeAdvantageMatrix(String outputType, String resourceType, String matrixColumnText, List<String> selects, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor) {
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(outputType);
		q.setResourceType(resourceType);
		q.setTitle(title);
		q.setMatrixColumnText(matrixColumnText);
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		q.setSelects(selects);
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();
		if ( !dates.isEmpty())
			wheres.put("year", dates);
		
		/** other filters **/
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));		
		q.setWheres(wheres);
		
		q.setAggregations(null);
		
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		//if ( limit != null)
		//	q.setLimit(limit);
		
		//q.setSortingOrder("DESC");
		
		q.setBoxColor(boxColor);

		
		return q;
	}
	
public static ADAMQueryVO setFAOComparativeAdvantageDescription(ADAMQueryVO q){
		
		String dac_description = "" +
		"<div style='padding:20px'>" +
		"<p><b>The FAO comparative advantage feature is based on a calculation (as detailed below) which uses data as it is reported by resource partners to the OECD-DAC. "+
		"This is a calculated and objective output based on this information. More information available at country-level may provide a different output."+
		"</b></p>" +
		"<p>The Revealed FAO Comparative Advantage table displays five indicators for each year, country and DAC sector.</p>" +
		"<p><b>The formulas below use the following conventions:</b></p>" +
		"<p>" +
		"    <p>- <b>\"a\"</b> = sub-sector according to OECD-DAC nomenclature</p>" +
		"    <p>- <b>\"z\"</b> = country</p>" +
		"    <p>- <b>FAO_az</b> = FAO delivery in sub-sector \"a\" and country \"z\"</p>" +
		"    <p>- <b>FAO_totz</b> = Total FAO delivery in country \"z\"</p>" +
		"    <p>- <b>TOT_az</b> = Total delivery in sub-sector \"a\" and country \"z\" by all ODA implementing agencies</p>" +
		"    <p>- <b>TOT_agric_z</b> = Total delivery in agricultural related sectors by all ODA implementing agencies in country \"z\"</p>" +
		"</p>" +
		"<p><b>Indicators</b>:</p>" +
		"<p>" +
		"    <p>(1) Percentage of FAO delivery in sub-sector \"a\" and country \"z\" over total FAO delivery in country \"z\" <br/>= <b>FAO_az</b> / <b>FAO_totz</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% Delivery over Total FAO Delivery</b>\")</p>" +
		"    <p>(2) Percentage of FAO delivery in sub-sector \"a\" and country \"z\" over total delivery by all ODA implementing agencies in sub-sector \"a\" and country \"z\" <br/>= <b>FAO_az</b> / <b>TOT_az</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% FAO Delivery over Total Delivery</b>\")</p>" +
		"    <p>(3) Percentage of total FAO delivery in country \"z\" over total agricultural delivery by all ODA implementing agencies in country \"z\" <br/>= <b>FAO_totz</b> / <b>TOT_agric_z</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% Total FAO Delivery over Total Agriculture</b>\")</p>" +
		"    <p>(4) <b>Comparative advantage ratio</b> = (<b>FAO_az</b> / <b>TOT_az</b>) / (<b>FAO_totz</b> / <b>TOT_agric_z</b>)</p>" +
		"    <p>(5) <b>Does FAO have a Comparative Advantage?</b>: if (4) <b>> 1</b> = <font color='#1F9901'><b>yes</b></font>; if (4) <b>< 1 </b> = <font color='#1D4589'><b>no</b></font></p>" +
		"</p>" +
		"</div>";
		
		String or_description = "" +
		"<div style='padding:20px'>" +
		"<p><b>The FAO comparative advantage feature is based on a calculation (as detailed below) which uses data as it is reported by resource partners to the OECD-DAC. "+
		"This is a calculated and objective output based on this information. More information available at country-level may provide a different output."+
		"</b></p>" +
		"<p>The Revealed FAO Comparative Advantage table displays five indicators for each year, country and FAO Organizational Result (OR).</p>" +
		"<p>The data has been converted from the OECD-DAC Classification to the FAO Strategic Framework, so the budget maybe split among different ORs.</p>" +
		"<p><b>The formulas below use the following conventions:</b></p>" +
		"<p>" +
		"    <p>- <b>\"a\"</b> = OR</p>" +
		"    <p>- <b>\"z\"</b> = country</p>" +
		"    <p>- <b>FAO_az</b> = FAO delivery for OR \"a\" and country \"z\"</p>" +
		"    <p>- <b>FAO_totz</b> = Total FAO delivery in country \"z\"</p>" +
		"    <p>- <b>TOT_az</b> = Total delivery for OR \"a\" and country \"z\" by all ODA implementing agencies</p>" +
		"    <p>- <b>TOT_agric_z</b> = Total delivery for all ORs by all ODA implementing agencies in country \"z\"</p>" +
		"</p>" +
		"<p><b>Indicators</b>:</p>" +
		"<p>" +
		"    <p>(1) Percentage of FAO delivery for OR \"a\" and country \"z\" over total FAO delivery in country \"z\" <br/>= <b>FAO_az</b> / <b>FAO_totz</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% Delivery over Total FAO Delivery</b>\")</p>" +
		"    <p>(2) Percentage of FAO delivery for OR \"a\" and country \"z\" over total delivery by all ODA implementing agencies for OR \"a\" and country \"z\" <br/>= <b>FAO_az</b> / <b>TOT_az</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% FAO Delivery over Total Delivery</b>\")</p>" +
		"    <p>(3) Percentage of total FAO delivery in country \"z\" over total ORs delivery by all ODA implementing agencies in country \"z\" <br/>= <b>FAO_totz</b> / <b>TOT_agric_z</b></p>" +
		"    <p>(Column header used in the ADAM tables: \"<b>% Total FAO Delivery over Total Agriculture</b>\")</p>" +
		"    <p>(4) <b>Comparative advantage ratio</b> = (<b>FAO_az</b> / <b>TOT_az</b>) / (<b>FAO_totz</b> / <b>TOT_agric_z</b>)</p>" +
		"    <p>(5) <b>Does FAO have a Comparative Advantage?</b>: if (4) <b>> 1</b> = <font color='#1F9901'><b>yes</b></font>; if (4) <b>< 1 </b> = <font color='#1D4589'><b>no</b></font></p>" +
		"</p>" +
		"</div>";
	
		if(q.getIsOR())
			q.setDescription(or_description);
		else
			q.setDescription(dac_description);
        
        return q;
	}

	
	public static ADAMQueryVO compareSubSectorAgainstTop10(String chartype, String title, HashMap<String, List<String>> filters, List<String> dates, String boxColor, String aggregationColumn, String aggregationLabel, Boolean isOR, Boolean isSO, Boolean isAgricultureFilter) {
		
		ADAMQueryVO q = new ADAMQueryVO();
		q.setOutputType(chartype);
		q.setResourceType(ADAMBoxContent.CHART.name());
		q.setTitle(title);
		q.setxLabel("");
		q.setyLabel(aggregationLabel + " Difference ["+ADAMController.baseUnitShort+"]");
		q.setMeasurementUnit(ADAMController.baseUnitShort);
		q.setAggregationLabel(aggregationLabel);
		
		Map<String, String> aggregations = new HashMap<String, String>();
		aggregations.put(aggregationColumn, "SUM");
		q.setAggregations(aggregations);
		List<String> datasetCodes = new ArrayList<String>();
		datasetCodes.add(ADAMController.currentlySelectedDatasetCode.name());
		q.setDatasetCodes(datasetCodes);
		q.setDatasetSource(ADAMController.currentlySelectedDatasetCode.name());
		
		Map<String, List<String>> wheres = new HashMap<String,  List<String>>();

		
		for(String column : filters.keySet()) 
			wheres.put(column, filters.get(column));
		
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
		q.setIsOR(isOR);
		q.setIsSO(isSO);
		
		q.setIsAgricultureFilter(isAgricultureFilter);
		
		//if(isSO)
			//q.setDisclaimer(ADAMQueryVOBuilderFAO.getORsDisclaimer());
		
		//if(isOR)
			//q.setDisclaimer(ADAMQueryVOBuilderFAO.getORsDisclaimer());
		
		return q;
	}
	

	  
	public static void addRecipientFilters(HashMap<String, List<String>> hashMap, List<String> codes) {
		hashMap.put("recipientcode", codes);
	}
	
	public static void addSectorCodeFilters(HashMap<String, List<String>> hashMap, List<String> codes) {
		hashMap.put("dac_sector", codes);
	}
	
	
	public static void addDonorFilters(HashMap<String, List<String>> hashMap, List<String> codes) {
		hashMap.put("donorcode", codes);
	}
	
	public static void addPurposeCodeFilters(HashMap<String, List<String>> hashMap, List<String> codes) {
		hashMap.put("purposecode", codes);
	}
	
	public static void addChannelCodeFilters(HashMap<String, List<String>> hashMap, List<String> codes) {
		hashMap.put("channelcode", codes);
	}
}