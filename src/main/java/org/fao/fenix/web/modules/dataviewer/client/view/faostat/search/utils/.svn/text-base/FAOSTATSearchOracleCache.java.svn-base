package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;


public class FAOSTATSearchOracleCache {
	
	// the key should be the Labels...
	// and the value should be additional informations (like what kind of information is)
	public static LinkedHashMap<String, String> itemsMap;
	
	public static List<String> items;
	
	
	public static LinkedHashMap<String, String> elementsMap;
	
	public static List<String> elements;
	
	public static LinkedHashMap<String, String> countriesMap;
	
	public static List<String> countries;

	
	public FAOSTATSearchOracleCache() {
//		if (values == null  ) {
//			valuesMap = new LinkedHashMap<String, String>();
//			values = new ArrayList<String>();
//			// load
//			FAOSTATSearchController.loadOracleCache(values, valuesMap );
//		}
		
		itemsMap = new LinkedHashMap<String, String>();
		items = new ArrayList<String>();
		
		countriesMap = new LinkedHashMap<String, String>();
		countries = new ArrayList<String>();
		
		elementsMap = new LinkedHashMap<String, String>();
		elements = new ArrayList<String>();
		
		FAOSTATSearchController.loadCodingSystem(items, itemsMap, FAOSTATDimensionConstant.ITEMS.toString());
		FAOSTATSearchController.loadCodingSystem(elements, elementsMap, FAOSTATDimensionConstant.ELEMENTS_LIST.toString());
//		FAOSTATSearchController.loadCodingSystem(countries, countriesMap, FAOSTATDimensionConstant.COUNTRIES.toString());

	}


	public static LinkedHashMap<String, String> getItemsMap() {
		return itemsMap;
	}


	public static List<String> getItems() {
		return items;
	}


	public static LinkedHashMap<String, String> getElementsMap() {
		return elementsMap;
	}


	public static List<String> getElements() {
		return elements;
	}


	public static LinkedHashMap<String, String> getCountriesMap() {
		return countriesMap;
	}


	public static List<String> getCountries() {
		return countries;
	}

	
	
	

}
