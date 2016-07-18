package org.fao.fenix.web.modules.udtable.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UDTableFilterVO extends UDTableHeaderVO implements IsSerializable {

	private List<String> allowedValues;
	
	private List<String> showValues;
	
	private boolean showCode;
	
	private long descriptorId;
	
	public UDTableFilterVO() {
		this.allowedValues = new ArrayList<String>();
		this.showValues = new ArrayList<String>();
		this.showCode = false;
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

}