package org.fao.fenix.web.modules.amis.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVOBuilder;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;



public class PSDQueryVOBuilder extends AMISQueryVOBuilder {


	public static AMISQueryVO getByDomainStandardQuery(AMISCodesModelData domain, String output, String typeOfOutput, String title, List<String> selects, HashMap<String, String> elements, Boolean isCountryLevel, Boolean isRegionalLevel, Integer limit, String width, String height) {
		// this is the filter based on domain
		AMISQueryVO qvo = getQVOParameters();
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

		// setting the resource type ( i.e. PIE, BAR...)
		qvo.setTypeOfOutput(typeOfOutput);

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
		qvo.setHeight(height);
		qvo.setWidth(width);

		

		return qvo;
	}


	public static Map<String,List<AMISQueryVO>> getByAreaStandardQuery(Map<String,List<AMISQueryVO>> map, AMISCodesModelData area, Boolean isCountryLevel, Boolean isRegionalLevel) {

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

	public static AMISQueryVO getByAreaStandardQuery(AMISCodesModelData area, String output, String typeOfOutput, String title, List<String> selects, HashMap<String, String> elements, HashMap<String, String> items, HashMap<String, String> domains, Boolean isCountryLevel, Boolean isRegionalLevel, Integer limit, String width, String height) {
		// this is the filter based on domain
		AMISQueryVO qvo = getQVOParameters();

		System.out.println("-----------");
		System.out.println("AREA: " + area);
		System.out.println("selects: " + selects);
		System.out.println("limit: " + limit);

		// setting the output ( i.e. MAP, CHART...)
		qvo.setOutput(output);

		// setting the type Of Output ( i.e. PIE, BAR...)
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


	private static void getQCfilters(AMISQueryVO filters) {

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

	

	


}
