package org.fao.fenix.web.modules.birt.common;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

public class ISFPCountries {

	private LinkedHashMap<String,String> countryMap;

	public ISFPCountries() {

		countryMap = new LinkedHashMap<String,String>();
		initializeCountryMap();

	}

	private void initializeCountryMap() {

		countryMap.put("1", "Afghanistan");
		countryMap.put("23", "Bangladesh");
		countryMap.put("33", "Bolivia");
		countryMap.put("43", "Burundi");
		countryMap.put("45", "Cameroon");
		countryMap.put("49", "Central African Republic");
		countryMap.put("58", "Comoros");
		countryMap.put("68", "Democratic Republic of the Congo");
		countryMap.put("70", "Djibouti");
		countryMap.put("72", "Dominican Republic");
		countryMap.put("94", "Ghana");
		countryMap.put("103", "Guatemala");
		countryMap.put("106", "Guinea");
		countryMap.put("105", "Guinea Bissau");
		countryMap.put("111", "Honduras");
		countryMap.put("123", "Jamaica");
		countryMap.put("130", "Jordan");
		countryMap.put("133","Kenya");
		countryMap.put("138", "Kyrgyzstan");
		countryMap.put("139", "Lao People's Democratic Republic");
		countryMap.put("144", "Liberia");
		countryMap.put("152", "Malawi");
		countryMap.put("155", "Mali");
		countryMap.put("167", "Mongolia");
		countryMap.put("170", "Mozambique");
		countryMap.put("180", "Nicaragua");
		countryMap.put("181", "Niger");
		countryMap.put("267", "Palestine WBGS");
		countryMap.put("205", "Rwanda");
		countryMap.put("214", "Sao Tome and Principe");
		countryMap.put("217", "Senegal");
		countryMap.put("231", "Sri Lanka");
		countryMap.put("243", "Togo");
		countryMap.put("253", "Uganda");
		countryMap.put("257", "United Republic of Tanzania");
		countryMap.put("261", "Uzbekistan");
		countryMap.put("271", "Zimbabwe");
		
	}
	
	public LinkedHashMap<String, String> getCountryMap() {
		return countryMap;
	}

	public ListBox getCategoriesListBox() {
		ListBox list = new ListBox();
		
		Iterator<Map.Entry<String, String>> keyValuePairs = countryMap.entrySet().iterator();

		for (int i = 0; i < countryMap.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			list.addItem(value, key);

		}
		return list;

	}
	
}
