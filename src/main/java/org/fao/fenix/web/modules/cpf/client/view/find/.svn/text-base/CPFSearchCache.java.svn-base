package org.fao.fenix.web.modules.cpf.client.view.find;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.cpf.client.control.find.CPFFindController;
import org.fao.fenix.web.modules.cpf.common.constants.CPFConstants;


public class CPFSearchCache {

	// the key should be the Labels...
	// and the value should be additional informations (like what kind of information is)
	public static LinkedHashMap<String, String> cpfNamesMap;
	public static List<String> cpfNames;
	
	public static LinkedHashMap<String, String> cpfCodesMap;
	public static List<String> cpfCodes;

	public static LinkedHashMap<String, String> faoStrategicFrameworksMap;
	public static List<String> faoStrategicFrameworks;

	public static LinkedHashMap<String, String> countriesMap;
	public static List<String> countries;


	public CPFSearchCache() {
		cpfNamesMap = new LinkedHashMap<String, String>();
		cpfNames = new ArrayList<String>();

		faoStrategicFrameworksMap = new LinkedHashMap<String, String>();
		faoStrategicFrameworks= new ArrayList<String>();

		countriesMap = new LinkedHashMap<String, String>();
		countries = new ArrayList<String>();

		CPFFindController.loadCodingSystem(cpfNames, cpfNamesMap, CPFConstants.CPF_NAMES.toString());
		CPFFindController.loadCodingSystem(cpfCodes, cpfCodesMap, CPFConstants.CPF_CODES.toString());
    	CPFFindController.loadCodingSystem(faoStrategicFrameworks, faoStrategicFrameworksMap, CPFConstants.FAO_FRAMEWORK.toString());
    	CPFFindController.loadCodingSystem(countries, countriesMap, CPFConstants.COUNTRIES.toString());

	}


	public static LinkedHashMap<String, String> getCpfNamesMap() {
		return cpfNamesMap;
	}


	public List<String> getCpfNames() {
		return cpfNames;
	}


	public static LinkedHashMap<String, String> getFAOStrategicFrameworkMap() {
		return faoStrategicFrameworksMap;
	}


	public List<String> getFAOStrategicFrameworks() {
		return faoStrategicFrameworks;
	}


	public static LinkedHashMap<String, String> getCountriesMap() {
		return countriesMap;
	}


	public List<String> getCountries() {
		return countries;
	}


	public static LinkedHashMap<String, String> getCpfCodesMap() {
		return cpfCodesMap;
	}


	public static void setCpfCodesMap(LinkedHashMap<String, String> cpfCodesMap) {
		CPFSearchCache.cpfCodesMap = cpfCodesMap;
	}


	public List<String> getCpfCodes() {
		return cpfCodes;
	}


	public static void setCpfCodes(List<String> cpfCodes) {
		CPFSearchCache.cpfCodes = cpfCodes;
	}





}
