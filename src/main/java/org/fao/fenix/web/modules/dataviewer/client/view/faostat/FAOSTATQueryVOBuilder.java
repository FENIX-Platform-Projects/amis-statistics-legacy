package org.fao.fenix.web.modules.dataviewer.client.view.faostat;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

public class FAOSTATQueryVOBuilder {
	
	
	public static void setQVOParameters(DWFAOSTATQueryVO qvo) {
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
	}
	
	public static DWFAOSTATQueryVO getQVOParameters() {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		return qvo;
	}
	
	public static void getQVOParameters(DWFAOSTATQueryVO qvo) {
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
	}
	

	
	public static DWFAOSTATQueryVO createQVO(String output, String typeOfOutput, List<String> selects, Map<String, String> domains, Map<String, String> areas, Map<String, String> elements, Map<String, String> items,  Map<String, String> years) {
		DWFAOSTATQueryVO qvo =  getQVOParameters();

		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(output);
//		qvo.setResourceType(resourceType);
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);
//		qvo.setOutputType(outputType);
		
		// Selects
		qvo.setSelects(selects);
		
		
		// setting the filters
		if ( domains.isEmpty() && domains != null)
			qvo.setDomains(domains);
		
		if ( areas.isEmpty() && areas != null)
			qvo.setAreas(areas);
		
		if ( elements.isEmpty() && elements != null)
		qvo.setElements(elements);
		
		if ( items.isEmpty() && items != null)
			qvo.setItems(items);
		
		if ( years.isEmpty() && years != null)
			qvo.setYears(years);

		return qvo;
	}
	
	public static DWFAOSTATQueryVO createQVO(DWFAOSTATQueryVO filters, String output, String typeOfOutput, Boolean isCountryLevel, Boolean isRegionalLevel) {
		DWFAOSTATQueryVO qvo =  getQVOParameters();
		
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(output);
//		qvo.setResourceType(resourceType);
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);
//		qvo.setOutputType(outputType);
		
		// Selects
		qvo.setSelects(filters.getSelects());
		
		
		
		// setting the filters
		if ( filters.getDomains().isEmpty() && filters.getDomains() != null)
			qvo.setDomains(filters.getDomains());
		
		if ( filters.getAreas().isEmpty() && filters.getAreas() != null)
			qvo.setAreas(filters.getAreas());
		
		if ( filters.getElements().isEmpty() && filters.getElements() != null)
		qvo.setElements(filters.getElements());
		
		if ( filters.getItems().isEmpty() && filters.getItems() != null)
			qvo.setItems(filters.getItems());
		
		if ( filters.getYears().isEmpty() && filters.getYears() != null)
			qvo.setYears(filters.getYears());
		
			
		// setting country regional level
		qvo.setIsCountryLevel(isCountryLevel);
		qvo.setIsRegionLevel(isRegionalLevel);
		
		// limit
		qvo.setLimit(filters.getLimit());
		
		return qvo;
	}
	
//	public DWFAOSTATQueryVO createQVO(String resourceType, String outputType, Map<String, String> domains, Map<String, String> areas, Map<String, String> elements, Map<String, String> items,  Map<String, String> years, Boolean isCountryLevel, Boolean isRegionalLevel) {
//		DWFAOSTATQueryVO qvo = createQVO(resourceType, outputType, domains, areas, elements, items, years);
//		
//		// setting country regional level
//		qvo.setIsCountryLevel(isCountryLevel);
//		qvo.setIsRegionLevel(isRegionalLevel);
//		
//		return qvo;
//	}
	
	
	

}
