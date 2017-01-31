package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.view.faostat.FAOSTATQueryVOBuilder;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDomains;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATMetadataDomains;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;



public class FAOSTATQueryVOBuilderVisualize extends FAOSTATQueryVOBuilder {

	
	private static DWFAOSTATQueryVO getByDomainStandardQuery(DWCodesModelData domain, String output, String typeOfOutput, String title, List<String> selects, HashMap<String, String> elements, Boolean isCountryLevel, Boolean isRegionalLevel, Integer limit, String width, String height) {
		// this is the filter based on domain
		DWFAOSTATQueryVO qvo = getQVOParameters();
		System.out.println("-----------");
		System.out.println("DOMAIN: " + domain);
		System.out.println("selects: " + selects);
		System.out.println("limit: " + limit);
		
		
		// ORDERBYS
		List<String> orderBys = new ArrayList<String>();
		orderBys.add(qvo.getAggregationType()+"(D.Value)");
		qvo.setOrderBys(orderBys);
		
		qvo.setRunMaxDateQuery(true);
		
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(output);
//		qvo.setResourceType(resourceType);
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);
//		qvo.setOutputType(outputType);
		
		qvo.setTitle(title);
		
		// selects
		qvo.setSelects(selects);
		
		// domain
		qvo.setDomains(getCode(domain));
		
		// set the elements (this is because is the only variable 
		qvo.setElements(elements);
		
		// limit
		if ( limit != null)
			qvo.setLimit(limit);
		
		
		// setting country regional level
		qvo.setIsCountryLevel(isCountryLevel);
		qvo.setIsRegionLevel(isRegionalLevel);
		
		// TODO REMOVE!!!
//		qvo.setSmallHeight(height);
//		qvo.setSmallWidth(width);
//		
//		qvo.setBigHeight("250");
//		qvo.setBigWidth("450");
		
		// TODO: find another way to switch between the domains...
		FAOSTATDomains d = FAOSTATDomains.valueOf(domain.getCode());
		
		switch (d) {
			// PRODUCTION
			case Q:
				
				break;
			
			// CROPS
			case QC:
				getQCfilters(qvo);
				break;

			default:
				break;
		}

		
		return qvo;
	}
	
		
	public static Map<String,List<DWFAOSTATQueryVO>> getByAreaStandardQuery(Map<String,List<DWFAOSTATQueryVO>> map, DWCodesModelData area, Boolean isCountryLevel, Boolean isRegionalLevel) {
	
		for (String s: map.keySet()) {
			List<DWFAOSTATQueryVO> vos = map.get(s);
            for(DWFAOSTATQueryVO qvo: vos){
        		qvo.setAreas(getCode(area));
        		qvo.setIsCountryLevel(isCountryLevel);
        		qvo.setIsRegionLevel(isRegionalLevel);
        		
        		setQVOParameters(qvo);
            }           
	     }
			
		return map;
	}
	
//	public static DWFAOSTATQueryVO getByAreaStandardQuery(DWCodesModelData area, String output, String typeOfOutput, String title, List<String> selects, HashMap<String, String> elements, HashMap<String, String> items, HashMap<String, String> domains, Boolean isCountryLevel, Boolean isRegionalLevel, Integer limit, String width, String height) {
//		// this is the filter based on domain
//		DWFAOSTATQueryVO qvo = getQVOParameters();
//		
//		System.out.println("-----------");
//		System.out.println("AREA: " + area);
//		System.out.println("selects: " + selects);
//		System.out.println("limit: " + limit);
//		
//		// setting the resource type ( i.e. MAP, CHART...)
//		qvo.setOutput(output);
////		qvo.setResourceType(resourceType);
//		
//		// setting the resource type ( i.e. PIE, BAR...)
//		qvo.setTypeOfOutput(typeOfOutput);
////		qvo.setOutputType(outputType);
//		
//		qvo.setTitle(title);
//		
//		// selects
//		qvo.setSelects(selects);
//		
//		// Items
//		qvo.setItems(items);
//		
//		// Elements
//		qvo.setElements(elements);
//		
//		// Elements
//		qvo.setDomains(domains);
//		
//		// AREAS
//		qvo.setAreas(getCode(area));
//		
//		
//		// limit
//		if ( limit != null)
//			qvo.setLimit(limit);
//		
//		
//		// setting country regional level
//		qvo.setIsCountryLevel(isCountryLevel);
//		qvo.setIsRegionLevel(isRegionalLevel);
//		
//		// TODO REMOVE!!!
//		qvo.setSmallHeight(height);
//		qvo.setSmallWidth(width);
//		
//		qvo.setBigHeight("250");
//		qvo.setBigWidth("450");
//		
//		
//		return qvo;
//	}
	
	
	private static void getQCfilters(DWFAOSTATQueryVO filters) {
			
		// years
		HashMap<String, String> map = new HashMap<String, String>();
		//map.put("2009", "2009");
		//filters.setYears(map);
		
		// item
		map = new HashMap<String, String>();
		// RICE
		map.put("27", "27");	
		filters.setItems(map);
		
		
	}
	
	private static Map<String, String> getCode(DWCodesModelData code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(code.getCode(), code.getLabel());
		return map;
	}

	
	public static DWFAOSTATQueryVO getMetadataClassificationStandardQuery(DWCodesModelData domain, String title, String output, String typeOfOutput,  Map<String, String> groups) {
		// this is the filter based on domain
		DWFAOSTATQueryVO qvo = getQVOParameters();
		System.out.println("-----------");
	
		List<String> selects = new ArrayList<String>();
			
		// ORDERBYS
		List<String> orderBys = new ArrayList<String>();
		orderBys.add("itemname"+qvo.getLanguage().toLowerCase());
		qvo.setOrderBys(orderBys);
			
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(output);
//		qvo.setResourceType(resourceType);
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);
//		qvo.setOutputType(outputType);
		
		qvo.setTitle(title);
		
		
		// metadata group
		qvo.setGroups(groups);
		
		
		//set the appropriatre selects
		FAOSTATMetadataDomains d = FAOSTATMetadataDomains.valueOf(domain.getCode());
		
		switch (d) {
			case FBS:
			case T:
				selects.add("itemfaocode");
			    selects.add("itemname" + FAOSTATConstants.faostatLanguage);
				selects.add("definition" + FAOSTATConstants.faostatLanguage);
				qvo.setSelects(selects);
				break;
			
			case SUA:
			case Q:
			case PRICE:
			case R:
				selects.add("itemfaocode");
			    selects.add("itemhspluscode");
				selects.add("itemname" + FAOSTATConstants.faostatLanguage);
				selects.add("definition" + FAOSTATConstants.faostatLanguage);
				qvo.setSelects(selects);
				break;

			case F:
				selects.add("itemfaocode");
			    selects.add("itemname" + FAOSTATConstants.faostatLanguage);
				qvo.setSelects(selects);
				break;
			default:
				break;
		}

		
		return qvo;
	}
	
	public static DWFAOSTATQueryVO getMetadataStandardQuery(String title, String output, String typeOfOutput,  List<String> selects) {
		// this is the filter based on domain
		DWFAOSTATQueryVO qvo = getQVOParameters();
		System.out.println("-----------");
	
		qvo.setSelects(selects)	;
		
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(output);
//		qvo.setResourceType(resourceType);
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);
//		qvo.setOutputType(outputType);
		
		qvo.setTitle(title);
		

		
		return qvo;
	}
	
	
	
	
	/**public static DWFAOSTATQueryVO getMetadataStandardQuery(String title, String outputType, List<String> selects, List<String> froms, List<String> orderBys, Map<String, String> groups, Map<String, String> domains, Map<String, String> handleNullForFieldMap, Map<String, List<String>> filterMap, List<String> joins) {
		// this is the filter based on domain
		DWFAOSTATQueryVO qvo = getQVOParameters();
		System.out.println("-----------");
	
		qvo.setSelects(selects)	;
		qvo.setFroms(froms)	;
		qvo.setOrderBys(orderBys)	;
		qvo.setSortingOrder("ASC");
		
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput("FAOSTAT_METADATA_TABLE");
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput("HTML");
		
		qvo.setTitle(title);
		
		if(groups !=null && !groups.isEmpty()){
			qvo.setGroups(groups);
		}
		
		if(domains !=null && !domains.isEmpty()){
			qvo.setDomains(domains);
		}
		
		if(handleNullForFieldMap!=null && !handleNullForFieldMap.isEmpty()) {
			qvo.setHandleNullForFieldMap(handleNullForFieldMap);
		}
		
		if(filterMap!=null && !filterMap.isEmpty()) {
			qvo.setFilterMap(filterMap);
		}

		
		
		return qvo;
	}
	**/
	
	
	public static DWFAOSTATQueryVO getMetadataStandardQuery(String title, DataViewerBoxContent outputType, List<String> selects, List<String> froms, List<String> orderBys, Map<String, String> groups, Map<String, String> domains, Map<String, String> handleNullForFieldMap, Map<String, List<String>> filterMap) {
		// this is the filter based on domain
		DWFAOSTATQueryVO qvo = getQVOParameters();
		System.out.println("-----------");
	
		qvo.setSelects(selects)	;
		qvo.setFroms(froms)	;
		qvo.setOrderBys(orderBys)	;
		qvo.setSortingOrder("ASC");
		
		// setting the resource type ( i.e. MAP, CHART...)
		qvo.setOutput(outputType.toString());
		
		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput("HTML");
		
		qvo.setTitle(title);
		
		if(groups !=null && !groups.isEmpty()){
			qvo.setGroups(groups);
		}
		
		if(domains !=null && !domains.isEmpty()){
			qvo.setDomains(domains);
		}
		
		if(handleNullForFieldMap!=null && !handleNullForFieldMap.isEmpty()) {
			qvo.setHandleNullForFieldMap(handleNullForFieldMap);
		}
		
		if(filterMap!=null && !filterMap.isEmpty()) {
			qvo.setFilterMap(filterMap);
		}
		
		return qvo;
	}
	
}
