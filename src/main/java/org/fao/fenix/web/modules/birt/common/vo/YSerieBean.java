package org.fao.fenix.web.modules.birt.common.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class YSerieBean implements IsSerializable {

	private String label;
	
	private List<Double> values;
	
	/** it's in RGB STYLE **/
	private List<Integer> color;

	public String getLabel() {
		return label;
	}
	
	public YSerieBean(){}
	
	public YSerieBean(String label, List<Double> values) { 
		this.label = label;
		this.values = values;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Double> getValues() {
		return values;
	}

	

	public List<Integer> getColor() {
		return color;
	}

	public void setColor(List<Integer> color) {
		this.color = color;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	
}
