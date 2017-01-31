package org.fao.fenix.web.modules.dataviewer.server.utils.lang;

import java.util.HashMap;

import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;

public class FAOSTATLanguageConstantsServer {
	
	static HashMap<String, String> langE;
	
	static HashMap<String, String> langF;
	
	static HashMap<String, String> langS;
	
	static {
		langE = new HashMap<String, String>();
		langF = new HashMap<String, String>();
		langS = new HashMap<String, String>();
		
		// growth rate
		langE.put("GROWTH_RATE", "Growth Rate");
		langF.put("GROWTH_RATE", "Taux de Croissance");
		langS.put("GROWTH_RATE", "Indice de Crecimiento");
		
		// annual growth rate
		langE.put("ANNUAL_GROWTH_RATE_LEAST_SQUARE", "Annual Growth Rate");
		langF.put("ANNUAL_GROWTH_RATE_LEAST_SQUARE", "Taux de Croissance");
		langS.put("ANNUAL_GROWTH_RATE_LEAST_SQUARE", "Indice de Crecimiento");

		// Others
		langE.put("OTHERS", "Others");
		langF.put("OTHERS", "Others");
		langS.put("OTHERS", "Others");
	}
	
	public static String print(String value, String language) {
		String label = null;
		
		if (language.equalsIgnoreCase("e")) {
			label = langE.get(value);
		}
		else if (language.equalsIgnoreCase("f")) {
			label = langF.get(value);
		}
		else if (language.equalsIgnoreCase("s")) {
			label = langS.get(value);
		}
		
		if ( label == null )
			return value;
		
		return label;
	}
	
	public static HashMap<String, String> pivotLabels;
	
	static {
		pivotLabels = new HashMap<String, String>();
		
		// English
		pivotLabels.put("itemE", "Item");
		pivotLabels.put("itemcodeE", "Item Codes");
		pivotLabels.put("areaE", "Country");
		pivotLabels.put("areacodeE", "Country Codes");
		pivotLabels.put("elementE", "Element");
		pivotLabels.put("elementcodeE", "Element Codes");
		pivotLabels.put("yearE", "Year");
		pivotLabels.put("reporterE", "Reporter");
		pivotLabels.put("reportercodeE", "Reporter Codes");
		pivotLabels.put("partnerE", "Partner");
		pivotLabels.put("partnercodeE", "Partner Codes");

		
		// French
		pivotLabels.put("itemF", "Produit");
		pivotLabels.put("itemcodeF", "Codes Articles");
		pivotLabels.put("areaF", "Pays");
		pivotLabels.put("areacodeF", "Codes Pays");
		pivotLabels.put("elementF", "Élément");
		pivotLabels.put("elementcodeF", "Codes Élément");
		pivotLabels.put("yearF", "Année");
		pivotLabels.put("reporterF", "Reporter");
		pivotLabels.put("reportercodeF", "Reporter Codes");
		pivotLabels.put("partnerF", "Partner");
		pivotLabels.put("partnercodeF", "Partner Codes");
		
		
		// Spanish
		pivotLabels.put("itemS", "Ítem");
		pivotLabels.put("itemcodeS", "Códigos de Ítems");
		pivotLabels.put("areaS", "País");
		pivotLabels.put("areacodeS", "Códigos de País");
		pivotLabels.put("elementS", "Elemento");
		pivotLabels.put("elementcodeS", "Códigos de Elementos");
		pivotLabels.put("yearS", "Año");
		pivotLabels.put("reporterS", "Reporter");
		pivotLabels.put("reportercodeS", "Reporter Codes");
		pivotLabels.put("partnerS", "Partner");
		pivotLabels.put("partnercodeS", "Partner Codes");
	}		
	
	


}
