package org.fao.fenix.web.modules.venn.common.vo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennBeanVO implements IsSerializable {
	
	
	/** this is the list of the countries (code, label) **/
	private HashMap<String, String> countries = new HashMap<String, String>();

	
	/** this used for the Venn **/
	private VennGraphBeanVO vennGraphBeanVO = new VennGraphBeanVO();
	
	/** contains the projects (and intersections), key is the country  code **/
	private HashMap<String, VennCountryBeanVO> vennCountryBeanVO  = new HashMap<String, VennCountryBeanVO>();
	
	/** String "countryCode_chartType" or "chartType"(if multicountry) 
	 * 	if the category has more charts, use the hashmap list 
	 * **/
	
	private LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
	
	

	
	
	public VennGraphBeanVO getVennGraphBeanVO() {
		return vennGraphBeanVO;
	}

	public void setVennGraphBeanVO(VennGraphBeanVO vennGraphBeanVO) {
		this.vennGraphBeanVO = vennGraphBeanVO;
	}

	public HashMap<String, VennCountryBeanVO> getVennCountryBeanVO() {
		return vennCountryBeanVO;
	}

	public void setVennCountryBeanVO(
			HashMap<String, VennCountryBeanVO> vennCountryBeanVO) {
		this.vennCountryBeanVO = vennCountryBeanVO;
	}

	

	

	public LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getVennChartsBeanVO() {
		return vennChartsBeanVO;
	}

	public void setVennChartsBeanVO(
			LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO) {
		this.vennChartsBeanVO = vennChartsBeanVO;
	}

	public HashMap<String, String> getCountries() {
		return countries;
	}

	public void setCountries(HashMap<String, String> countries) {
		this.countries = countries;
	}

	


	

}
