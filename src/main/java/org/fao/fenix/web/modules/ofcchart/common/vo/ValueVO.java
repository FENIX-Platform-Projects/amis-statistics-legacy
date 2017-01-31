package org.fao.fenix.web.modules.ofcchart.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ValueVO implements IsSerializable {

	private String xLabel;
	
	private Double value;
	
	public ValueVO() {
		xLabel = "";	
	}

	public ValueVO(String xLabel, Double value) {
		this.setxLabel(xLabel);
		this.setValue(value);
	}


	public String getxLabel() {
		return xLabel;
	}



	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}



	public Double getValue() {
		return value;
	}



	public void setValue(Double value) {
		this.value = value;
	}

	
}