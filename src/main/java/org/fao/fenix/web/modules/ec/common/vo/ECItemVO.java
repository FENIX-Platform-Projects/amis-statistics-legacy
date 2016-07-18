package org.fao.fenix.web.modules.ec.common.vo;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ECItemVO implements IsSerializable {

	private String code;
	
	private LinkedHashMap<String, ECItemConfigurationVO> dates;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LinkedHashMap<String, ECItemConfigurationVO> getDates() {
		return dates;
	}

	public void setDates(LinkedHashMap<String, ECItemConfigurationVO> dates) {
		this.dates = dates;
	}

}
