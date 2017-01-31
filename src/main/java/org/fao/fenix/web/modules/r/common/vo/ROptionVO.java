package org.fao.fenix.web.modules.r.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ROptionVO implements IsSerializable {

	private String optionCode;
	
	private String optionName;
	
	private String defaultValue;
	
	private String optionDescription;
	
	private List<String> optionValues;
	
	public ROptionVO() {
		optionValues = new ArrayList<String>();
	}
	
	public void addOptionValue(String optionValue) {
		if (this.optionValues == null)
			this.optionValues = new ArrayList<String>();
		this.optionValues.add(optionValue);
	}

	public String getOptionDescription() {
		return optionDescription;
	}

	public void setOptionDescription(String optionDescription) {
		this.optionDescription = optionDescription;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<String> getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(List<String> optionValues) {
		this.optionValues = optionValues;
	}
	
}