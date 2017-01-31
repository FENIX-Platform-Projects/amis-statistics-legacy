package org.fao.fenix.web.modules.core.common.vo.highcharts;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mortbay.html.Link;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HighChartValueVO implements IsSerializable {

	// name of the serie
	String name;
	
	// code
	String code;
	
	// list of the values
	List<Double> values;
	
	LinkedHashMap<String, Double> valuesHM = new LinkedHashMap<String, Double>();
	
	String measurementUnit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public Map<String, Double> getValuesHM() {
		return valuesHM;
	}

	public void setValuesHM(LinkedHashMap<String, Double> valuesHM) {
		this.valuesHM = valuesHM;
	}


	
	
	
		
}
