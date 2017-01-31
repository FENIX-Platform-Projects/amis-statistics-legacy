package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;


public class FAOSTATConstantsFilters {
	
	
		
	public static Map<String, String> dimensionCodingSystem;
	
	static {
		dimensionCodingSystem = new HashMap<String, String>();
		
		dimensionCodingSystem.put("DOMAINS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS.toString());
		dimensionCodingSystem.put("DOMAINS_FOR_GROUP", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_DOMAINS_FOR_GROUP.toString());
		dimensionCodingSystem.put("ELEMENTS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT.toString());
		dimensionCodingSystem.put("ELEMENTS_LIST", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_LIST.toString());

		dimensionCodingSystem.put("ITEMS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString());
		dimensionCodingSystem.put("COUNTRIES", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("COUNTRIES_AREAS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString());
		dimensionCodingSystem.put("AREAS_WORLD", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString());
		dimensionCodingSystem.put("AREAS_FAO", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString());
		dimensionCodingSystem.put("YEARS", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
	
		dimensionCodingSystem.put("TIMERANGE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());

		// MULTI Selection
		dimensionCodingSystem.put("ITEMS_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString());
		dimensionCodingSystem.put("COUNTRIES_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("AREAS_WORLD_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("AREAS_FAO_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		dimensionCodingSystem.put("YEARS_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
		dimensionCodingSystem.put("ELEMENTS_MULTI", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT.toString());
		
		dimensionCodingSystem.put("TIMERANGE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
		
		// COMPARE Selection
		dimensionCodingSystem.put("ITEMS_COMPARE", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT.toString());

	}

	
}
