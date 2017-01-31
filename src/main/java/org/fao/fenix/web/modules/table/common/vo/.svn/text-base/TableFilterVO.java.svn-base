package org.fao.fenix.web.modules.table.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TableFilterVO extends TableHeaderVO implements IsSerializable {

	private List<String> allowedValues;

	private List<String> showValues;

	private boolean showCode;

	private long descriptorId;
	
	private Map<String, String> selectedValuesMap;

	public TableFilterVO() {
		this.allowedValues = new ArrayList<String>();
		this.showValues = new ArrayList<String>();
		this.showCode = false;
		this.selectedValuesMap = new HashMap<String, String>();
	}

	public List<String> getShowValues() {
		return showValues;
	}

	public void addShowValues(String value) {
		if (this.showValues == null)
			this.showValues = new ArrayList<String>();
		this.showValues.add(value);
	}

	public void addAllowedValues(String value) {
		if (this.allowedValues == null)
			this.allowedValues = new ArrayList<String>();
		this.allowedValues.add(value);
	}

	public void setShowValues(List<String> showValues) {
		this.showValues = showValues;
	}

	public List<String> getAllowedValues() {
		return allowedValues;
	}

	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = allowedValues;
	}

	public boolean showCode() {
		return showCode;
	}

	public void setShowCode(boolean showCode) {
		this.showCode = showCode;
	}

	public long getDimensionDescriptorId() {
		return descriptorId;
	}

	public void setDimensionDescriptorId(long descriptorId) {
		this.descriptorId = descriptorId;
	}

	public Map<String, String> getSelectedValuesMap() {
		return selectedValuesMap;
	}

	public void setSelectedValuesMap(Map<String, String> selectedValuesMap) {
		this.selectedValuesMap = selectedValuesMap;
	}

}