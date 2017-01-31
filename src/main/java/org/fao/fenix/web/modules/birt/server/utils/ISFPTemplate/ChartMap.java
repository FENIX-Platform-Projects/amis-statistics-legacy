package org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate;

import java.util.LinkedHashMap;

public class ChartMap {
	
	private LinkedHashMap<String,String> countryMap;

	private static ChartMap instance = null;
	
	public static ChartMap getInstance() {
		if (instance == null){
			instance = new ChartMap();
		}
		return instance;
	}
	
	public ChartMap() {
		countryMap = new LinkedHashMap<String,String>();
		initializeCountryMap();
	}

	private void initializeCountryMap() {

		countryMap.put("1", "Afghanistan.png");
		countryMap.put("23", "Bangladesh.png");
		countryMap.put("33", "Bolivia.png");
		countryMap.put("43", "Burundi.png");
		//countryMap.put("45", "Cameroon");
		//countryMap.put("49", "Central African Republic");
		//countryMap.put("58", "Comoros");
		countryMap.put("68", "Congo.png");
		//countryMap.put("70", "Djibouti");
		countryMap.put("72", "DominicanRepublic.png");
		countryMap.put("94", "Ghana.png");
		countryMap.put("103", "Guatemala.png");
		countryMap.put("106", "Guinea.png");
		//countryMap.put("105", "Guinea Bissau");
		countryMap.put("111", "Honduras.png");
		//countryMap.put("123", "Jamaica");
		//countryMap.put("130", "Jordan");
		countryMap.put("133","Kenya.png");
		//countryMap.put("138", "Kyrgyzstan Republic");
		//countryMap.put("139", "Lao People's Democratic Republic");
		//countryMap.put("144", "Liberia");
		countryMap.put("152", "Malawi.png");
		countryMap.put("155", "Mali.png");
		//countryMap.put("167", "Mongolia");
		countryMap.put("170", "Mozambique.png");
		countryMap.put("180", "Nicaragua.png");
		countryMap.put("181", "Niger.png");
		//countryMap.put("91", "Palestine/WBGS");
		countryMap.put("205", "Rwanda.png");
		//countryMap.put("214", "Sao Tome and Principe");
		countryMap.put("217", "Senegal.png");
		countryMap.put("231", "SriLanka.png");
		countryMap.put("257", "Tanzania.png");
		countryMap.put("243", "Togo.png");
		countryMap.put("253", "Uganda.png");
		//countryMap.put("261", "Uzbekistan");
		countryMap.put("271", "Zimbabwe.png");
		
	}

	public String getChart(String gauldCode) {
		return countryMap.get(gauldCode);
	}

}
