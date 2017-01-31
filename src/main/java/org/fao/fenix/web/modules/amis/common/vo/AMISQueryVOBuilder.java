package org.fao.fenix.web.modules.amis.common.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

public class AMISQueryVOBuilder {

	public static AMISQueryVO setFENIXDatasetParameters(AMISQueryVO qvo) {
		qvo.setOutput(AMISConstants.FENIX_DATABASE.toString());
		qvo.setTypeOfOutput(AMISConstants.FENIX_DATASET.toString());		
	    Map<String, String> codes = new  HashMap<String, String>();	   
	    codes.put(AMISConstants.CBS.toString(), AMISConstants.AMIS.toString());
	    codes.put(AMISConstants.PSD.toString(), AMISConstants.AMIS.toString());
	    codes.put(AMISConstants.AMIS.toString(), AMISConstants.AMIS.toString());
	    codes.put(AMISConstants.AMIS_ELEMENTS.toString(), AMISConstants.AMIS_ELEMENTS.toString());
	    codes.put(AMISConstants.AMIS_PRODUCTS.toString(), AMISConstants.AMIS_PRODUCTS.toString());
	    codes.put(AMISConstants.AMIS_COUNTRIES.toString(), AMISConstants.AMIS_COUNTRIES.toString());
		    
	    qvo.setDatasetCodes(codes);
		
		return qvo;
	}
	
	
	public static AMISResultVO setFAOSTATParameters(AMISResultVO rvo) {
		rvo.setDatabase(AMISConstants.FAOSTAT_DATABASE.toString());
		return rvo;
	}
	
	public static void setQVOParameters(AMISQueryVO qvo) {
		AMISConstantsVO.setLanguageSettings(qvo);
	}
	
	
    public static Map<String,List<AMISQueryVO>> getCountryStandardQuery(Map<String,List<AMISQueryVO>> map, AMISCodesModelData area, Boolean isCountryLevel, Boolean isRegionalLevel) {

		for (String s: map.keySet()) {
			List<AMISQueryVO> vos = map.get(s);
            for(AMISQueryVO qvo: vos){
        		qvo.setAreas(getCode(area));
        		qvo.setIsCountryLevel(isCountryLevel);
        		qvo.setIsRegionLevel(isRegionalLevel);

        		setQVOParameters(qvo);
            }
	     }

		return map;
	}

	public static AMISQueryVO getByCountryStandardQuery(AMISCodesModelData area, String output, String typeOfOutput, String title, List<String> selects, HashMap<String, String> elements, HashMap<String, String> items, HashMap<String, String> domains, Boolean isCountryLevel, Boolean isRegionalLevel, Integer limit, String width, String height) {
		// this is the filter based on domain
		AMISQueryVO qvo = getQVOParameters();

		System.out.println("-----------");
		System.out.println("AREA: " + area);
		System.out.println("selects: " + selects);
		System.out.println("limit: " + limit);

		// setting the output ( i.e. MAP, CHART...)
		qvo.setOutput(output);

		// setting the type of output ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);

		qvo.setTitle(title);

		// selects
		qvo.setSelects(selects);

		// Items
		qvo.setItems(items);

		// Elements
		qvo.setElements(elements);

		// Elements
		qvo.setDomains(domains);

		// AREAS
		qvo.setAreas(getCode(area));


		// limit
		if ( limit != null)
			qvo.setLimit(limit);


		// setting country regional level
		qvo.setIsCountryLevel(isCountryLevel);
		qvo.setIsRegionLevel(isRegionalLevel);

		// TODO REMOVE!!!
		qvo.setHeight(height);
		qvo.setWidth(width);

		return qvo;
	}


	public static AMISQueryVO getQVOParameters() {
		AMISQueryVO qvo =  new AMISQueryVO();
		//FAOSTATConstants.setFAOSTATDBSettings(qvo);
		return qvo;
	}


	public static AMISQueryVO createQVO(String output, String typeOfOutput, List<String> selects, Map<String, String> domains, Map<String, String> areas, Map<String, String> elements, Map<String, String> items,  Map<String, String> years) {
		AMISQueryVO qvo =  getQVOParameters();

		// setting the output type ( i.e. MAP, CHART...)
		qvo.setOutput(output);

		// setting the type of output ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);

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



	public static AMISQueryVO createQVO(AMISQueryVO filters, String outputType, String typeOfOutput, Boolean isCountryLevel, Boolean isRegionalLevel) {
		AMISQueryVO qvo =  getQVOParameters();

		// setting the output ( i.e. MAP, CHART...)
		qvo.setOutput(outputType);

		// setting the type of output ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);

		// Selects
		qvo.setSelects(filters.getSelects());



		// setting the filters
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


	protected static Map<String, String> getCode(AMISCodesModelData code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(code.getCode(), code.getLabel());
		return map;
	}

}
