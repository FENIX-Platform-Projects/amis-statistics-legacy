package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OfcChartBeanVO implements IsSerializable {
	
	private String datasetId;
	
	private String datasetName;
	
	private Number xsteps;
	
	private List<String> xAxisLabels;
	
	private List<String> ySelection;
	
	private List<OfcFilterVo> filters;

	/* this is for the legend **/
	private List<String> yLabels;
	
	private HashMap<String, String> xLabels;
	
	private HashMap<String, HashMap<String, List<ValueVO>>> values;
	
	private HashMap<String, List<ValueVO>> valuesNF;
	
	private String measuramentUnit;
	
	private boolean hasFilters;
	
	public OfcChartBeanVO(){
		yLabels = new ArrayList<String>();
		values = new HashMap<String, HashMap<String, List<ValueVO>>>();	
		valuesNF = new HashMap<String, List<ValueVO>>();
		ySelection = new ArrayList<String>();
		filters = new ArrayList<OfcFilterVo>();
		measuramentUnit = new String();
		datasetId = new String();
		datasetName = new String();
		xLabels = new HashMap<String, String>();
		xAxisLabels = new ArrayList<String>();
		
	}

	public List<String> getyLabels() {
		return yLabels;
	}

	public void setyLabels(List<String> yLabels) {
		this.yLabels = yLabels;
	}

	

	public List<String> getySelection() {
		return ySelection;
	}

	public void setySelection(List<String> ySelection) {
		this.ySelection = ySelection;
	}

	
	public List<OfcFilterVo> getFilters() {
		return filters;
	}

	public void setFilters(List<OfcFilterVo> filters) {
		this.filters = filters;
	}

	

	public HashMap<String, HashMap<String, List<ValueVO>>> getValues() {
		return values;
	}

	public void setValues(HashMap<String, HashMap<String, List<ValueVO>>> values) {
		this.values = values;
	}

	public HashMap<String, List<ValueVO>> getValuesNF() {
		return valuesNF;
	}

	public void setValuesNF(HashMap<String, List<ValueVO>> valuesNF) {
		this.valuesNF = valuesNF;
	}

	public boolean isHasFilters() {
		return hasFilters;
	}

	public void setHasFilters(boolean hasFilters) {
		this.hasFilters = hasFilters;
	}

	public String getMeasuramentUnit() {
		return measuramentUnit;
	}

	public void setMeasuramentUnit(String measuramentUnit) {
		this.measuramentUnit = measuramentUnit;
	}

	public String getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

	public HashMap<String, String> getxLabels() {
		return xLabels;
	}

	public void setxLabels(HashMap<String, String> xLabels) {
		this.xLabels = xLabels;
	}

	public List<String> getxAxisLabels() {
		return xAxisLabels;
	}

	public void setxAxisLabels(List<String> xAxisLabels) {
		this.xAxisLabels = xAxisLabels;
	}

	public Number getXsteps() {
		return xsteps;
	}

	public void setXsteps(Number xsteps) {
		this.xsteps = xsteps;
	}

	
}