package org.fao.fenix.web.modules.venn.common.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennConfigurationBeanVO implements IsSerializable {
	
	List<String> countries;
	
	String type;
	
	VennSetBeanVO setA;
	
	VennSetBeanVO setB;
	
	VennSetBeanVO setC;
	
	VennParametersBeanVO parameters;
	
	
	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VennSetBeanVO getSetA() {
		return setA;
	}

	public void setSetA(VennSetBeanVO setA) {
		this.setA = setA;
	}

	public VennSetBeanVO getSetB() {
		return setB;
	}

	public void setSetB(VennSetBeanVO setB) {
		this.setB = setB;
	}

	public VennSetBeanVO getSetC() {
		return setC;
	}

	public void setSetC(VennSetBeanVO setC) {
		this.setC = setC;
	}

	public VennSetBeanVO getSet(String set){
		if ( set.equals("setA"))
			return setA;
		else if ( set.equals("setB"))
			return setB;
		else if ( set.equals("setC"))
			return setC;
		return null;
	}
	
}
