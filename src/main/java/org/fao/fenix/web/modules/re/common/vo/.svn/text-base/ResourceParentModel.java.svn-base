package org.fao.fenix.web.modules.re.common.vo;

import java.util.HashMap;
import java.util.Map;


/**
 * The ResourceParentModel class is a representation of a FenixResourceVo object which has children associated with it e.g Project Resource
 * As displayed in the Catalogue
 **/

public class ResourceParentModel extends ResourceChildModel {


	public ResourceParentModel() {

	}

	public ResourceParentModel(String name, String type) {
		setName(name, type);
	}

	public void setName(String name, String type) {
		System.out.println("name = "+name);
		System.out.println("type = "+type);
		
		String presentableType =  getLabel(type);
		System.out.println("presentableType = "+presentableType);
		if(name.equals(type) && presentableType!=null){
			set("name", presentableType);
		} 
		else set("name", name);

	}
	
	public void setName(String name) {
		set("name", name);
	}
	
	public void setType(String type) {
		set("type", type);
	}

	public String getName() {
		return get("name");
	}

   public static Map<String,String> resourceNameMap = new HashMap<String,String>();
	static {
		resourceNameMap.put(ResourceType.MAPVIEW, "Maps");
		resourceNameMap.put(ResourceType.CHARTVIEW, "Charts");
		resourceNameMap.put(ResourceType.TABLEVIEW, "Tables");
		resourceNameMap.put(ResourceType.PROJECT, "Projects");
		resourceNameMap.put(ResourceType.TEXTVIEW, "Texts");
		resourceNameMap.put(ResourceType.REPORT, "Reports");
		resourceNameMap.put(ResourceType.DATASET,"Datasets");
		resourceNameMap.put(ResourceType.PATTERN,"Patterns");
		resourceNameMap.put(ResourceType.LAYER, "Layers");
		resourceNameMap.put("AgriculturalProductionPattern", "Agricultural Production Data Themes");
		resourceNameMap.put("DemographyPattern", "Demography Data Themes");
		resourceNameMap.put("LivestockProductionPattern", "Livestock Production Data Themes");
		resourceNameMap.put("PricePattern", "Price Data Themes");
		resourceNameMap.put("TradePattern", "Trade Data Themes");
		resourceNameMap.put("SocioEconomicPattern", "Socio-economic Data Themes");
		resourceNameMap.put("MacroEconomicsPattern", "Macro-economics Data Themes");
		resourceNameMap.put("AgriculturalInputPattern", "Agricultural Input Data Themes");
	}

	public static String getLabel(String resourceType) {
		return (String) resourceNameMap.get(resourceType);
	}

	public String getType() {
		return get("type");
	}

}
