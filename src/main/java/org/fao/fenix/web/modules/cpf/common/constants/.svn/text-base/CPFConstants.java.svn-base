package org.fao.fenix.web.modules.cpf.common.constants;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.cpf.server.CPFGetCodingSystem;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum CPFConstants implements IsSerializable {

	// Outputs and Types of Outputs
	CHART,
	BAR, PIE, TIMESERIES, SCATTER, BAR_WITH_CATEGORIES, AREA, STACKED,

	HTML,

	MAP,
	TABLE,
	PIVOT_TABLE,
	
	// Exports
	EXPORT,
	EXPORT_CSV, EXPORT_EXCEL,
	EXPORT_EXCEL_VALUES,

	// Retrieving Informations
	CODING_SYSTEM,
	CODING_SYSTEM_DAC,
	CODING_SYSTEM_SO,
	CODING_SYSTEM_COUNTRIES,
	CODING_SYSTEM_YEARS,
	
	// Selection/Filter Types
	TIMERANGE, 
	COUNTRIES,
	YEARS,
	DAC,
	SO,
	
	//Coding Systems
	CPF_NAMES,
	CPF_CODES,
	FAO_FRAMEWORK,
	
	
	AGGREGATION_TYPE;	

  public static Map<String, String> codingSystemMap;

	static {
		codingSystemMap = new HashMap<String, String>();

		codingSystemMap.put(COUNTRIES.toString(),CODING_SYSTEM_COUNTRIES.toString());
		codingSystemMap.put(SO.toString(), CODING_SYSTEM_SO.toString());

		codingSystemMap.put(DAC.toString(), CODING_SYSTEM_DAC.toString());
		codingSystemMap.put(YEARS.toString(), CODING_SYSTEM_YEARS.toString());
		
		//Timerange
		codingSystemMap.put(TIMERANGE.toString(),TIMERANGE.toString());

	}
	
	
	public static String defaultLanguage = "en";


	
}