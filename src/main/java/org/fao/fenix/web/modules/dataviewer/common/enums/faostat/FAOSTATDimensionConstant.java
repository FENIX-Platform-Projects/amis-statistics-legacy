package org.fao.fenix.web.modules.dataviewer.common.enums.faostat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum FAOSTATDimensionConstant implements IsSerializable {

	GROUPS, DOMAINS, DOMAINS_FOR_GROUP,
	
	COUNTRIES, COUNTRIES_AREAS, AREAS_WORLD, AREAS_WORLD_AGGREGATED, AREAS_FAO, AREAS_ALL,
	
    ELEMENTS, ELEMENTS_LIST, ELEMENTS_FOR_ITEM,
    ITEMS,  
    
    YEARS,
	TIMERANGE,
	
	
	AGGREGATION_TYPE,
	
	SORTING;
	
	
	// STORED PROCEDES DIMENSIONS (these are the dimensions for the stored procedures...)
//	countries, 
//	items,
//	years,
//	extra;
	
	// this convers the dimension to the Store Procedure accepted dimension
	public static HashMap<String, String> dimension_to_sp = new HashMap<String, String>();
	
	static {
		dimension_to_sp = new HashMap<String, String>();

		// OLD Store Procedure
//		dimension_to_sp.put(ELEMENTS.toString(), "extra");
//		dimension_to_sp.put(COUNTRIES.toString(), "countries");
//		dimension_to_sp.put(YEARS.toString(), "years");
//		dimension_to_sp.put(ITEMS.toString(), "items");
		
		dimension_to_sp.put(ELEMENTS.toString(), "element");
		dimension_to_sp.put(COUNTRIES.toString(), "area");
		dimension_to_sp.put(YEARS.toString(), "year");
		dimension_to_sp.put(ITEMS.toString(), "item");

	}
	
	public static HashMap<String, String> dimension_to_sp_matrix = new HashMap<String, String>();
	
	static {
		dimension_to_sp_matrix = new HashMap<String, String>();

//		dimension_to_sp_matrix.put(ELEMENTS.toString(), "extra");
//		dimension_to_sp_matrix.put(COUNTRIES.toString(), "reporters");
//		dimension_to_sp_matrix.put(YEARS.toString(), "years");
//		dimension_to_sp_matrix.put(ITEMS.toString(), "items");
		
		dimension_to_sp_matrix.put(ELEMENTS.toString(), "element");
		dimension_to_sp_matrix.put(COUNTRIES.toString(), "area");
		dimension_to_sp_matrix.put(YEARS.toString(), "year");
		dimension_to_sp_matrix.put(ITEMS.toString(), "item");

	}
	
	
public static Map<String, String> dimensionCodingSystem;
	
	static {
		dimensionCodingSystem = new HashMap<String, String>();
		
		dimensionCodingSystem.put("DOMAINS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS.toString());
		dimensionCodingSystem.put("GROUPS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_GROUPS.toString());
		
		dimensionCodingSystem.put("DOMAINS_FOR_GROUP", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_DOMAINS_FOR_GROUP.toString());
		dimensionCodingSystem.put("ELEMENTS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT.toString());
		dimensionCodingSystem.put("ELEMENTS_FOR_ITEM", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT.toString());
		dimensionCodingSystem.put("ITEMS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString());
		
		
		dimensionCodingSystem.put("COUNTRIES", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("AREAS_WORLD", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString());
		dimensionCodingSystem.put("AREAS_FAO", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString());
		dimensionCodingSystem.put("AREAS_ALL", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString());
		dimensionCodingSystem.put("YEARS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
	
		dimensionCodingSystem.put("TIMERANGE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());

		// MULTI Selection
		dimensionCodingSystem.put("ITEMS_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString());
		dimensionCodingSystem.put("COUNTRIES_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("AREAS_WORLD_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("AREAS_FAO_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("YEARS_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
	
		dimensionCodingSystem.put("TIMERANGE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
		
		// COMPARE Selection
		dimensionCodingSystem.put("ITEMS_COMPARE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT.toString());

	}
	
	
	public static Boolean isTradeMatrix(String domainCode) {
		// remove from the list
		// FT - Forestry trade matrix
		// TM - Trade Matrix
		if( domainCode.equalsIgnoreCase("TM") ||  domainCode.equalsIgnoreCase("FT")) {
			return true;
		}
		return false;
		
		
	}
}
