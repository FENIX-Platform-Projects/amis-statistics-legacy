package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ParametersVo implements IsSerializable {

	private Long datasetId;
	
	private String datasetTitle;
	
	private Map<String, String> xLabels;
	
	private Map<String, String> yLabels;
	
	private Map<String, String> zLabels;
	
	private Map<String, String> wLabels;
	
	private String x;
	
	private String y;
	
	private String z;
	
	private String w;
	
	private String xLabel;
	
	private String yLabel;
	
	private String zLabel;
	
	private String wLabel;
	
	private String value;
	
	private String valueLabel;
	
	private String function;
	
	private boolean aggregateX;
	
	private boolean aggregateY;
	
	private boolean aggregateZ;
	
	private boolean aggregateW;
	
	private String xFather;
	
	private String xFatherLabel;
	
	private String yFather;
	
	private String yFatherLabel;
	
	private String zFather;
	
	private String zFatherLabel;
	
	private String wFather;
	
	private String wFatherLabel;
	
	private List<OfcFilterVo> filters;
	
	private boolean showSingleValues;
	
	private boolean showTotals;
	
	private String chartType;
	
	public ParametersVo() {
		filters = new ArrayList<OfcFilterVo>();
		xLabels = new HashMap<String, String>();
		zLabels = new HashMap<String, String>();
		yLabels = new HashMap<String, String>();
		wLabels = new HashMap<String, String>();
	}
	
	public boolean isShowSingleValues() {
		return showSingleValues;
	}

	public void setShowSingleValues(boolean showSingleValues) {
		this.showSingleValues = showSingleValues;
	}

	public boolean isShowTotals() {
		return showTotals;
	}

	public void setShowTotals(boolean showTotals) {
		this.showTotals = showTotals;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public Long getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(Long datasetId) {
		this.datasetId = datasetId;
	}

	public String getDatasetTitle() {
		return datasetTitle;
	}

	public void setDatasetTitle(String datasetTitle) {
		this.datasetTitle = datasetTitle;
	}

	public Map<String, String> getXLabels() {
		return xLabels;
	}

	public void setXLabels(Map<String, String> labels) {
		xLabels = labels;
	}

	public Map<String, String> getYLabels() {
		return yLabels;
	}

	public void setYLabels(Map<String, String> labels) {
		yLabels = labels;
	}

	public Map<String, String> getZLabels() {
		return zLabels;
	}

	public void setZLabels(Map<String, String> labels) {
		zLabels = labels;
	}

	public Map<String, String> getWLabels() {
		return wLabels;
	}

	public void setWLabels(Map<String, String> labels) {
		wLabels = labels;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getXLabel() {
		return xLabel;
	}

	public void setXLabel(String label) {
		xLabel = label;
	}

	public String getYLabel() {
		return yLabel;
	}

	public void setYLabel(String label) {
		yLabel = label;
	}

	public String getZLabel() {
		return zLabel;
	}

	public void setZLabel(String label) {
		zLabel = label;
	}

	public String getWLabel() {
		return wLabel;
	}

	public void setWLabel(String label) {
		wLabel = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueLabel() {
		return valueLabel;
	}

	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public boolean isAggregateX() {
		return aggregateX;
	}

	public void setAggregateX(boolean aggregateX) {
		this.aggregateX = aggregateX;
	}

	public boolean isAggregateY() {
		return aggregateY;
	}

	public void setAggregateY(boolean aggregateY) {
		this.aggregateY = aggregateY;
	}

	public boolean isAggregateZ() {
		return aggregateZ;
	}

	public void setAggregateZ(boolean aggregateZ) {
		this.aggregateZ = aggregateZ;
	}

	public boolean isAggregateW() {
		return aggregateW;
	}

	public void setAggregateW(boolean aggregateW) {
		this.aggregateW = aggregateW;
	}

	public List<OfcFilterVo> getFilters() {
		return filters;
	}

	public void setFilters(List<OfcFilterVo> filters) {
		this.filters = filters;
	}

	public String getXFather() {
		return xFather;
	}

	public void setXFather(String father) {
		xFather = father;
	}

	public String getXFatherLabel() {
		return xFatherLabel;
	}

	public void setXFatherLabel(String fatherLabel) {
		xFatherLabel = fatherLabel;
	}

	public String getYFather() {
		return yFather;
	}

	public void setYFather(String father) {
		yFather = father;
	}

	public String getYFatherLabel() {
		return yFatherLabel;
	}

	public void setYFatherLabel(String fatherLabel) {
		yFatherLabel = fatherLabel;
	}

	public String getZFather() {
		return zFather;
	}

	public void setZFather(String father) {
		zFather = father;
	}

	public String getZFatherLabel() {
		return zFatherLabel;
	}

	public void setZFatherLabel(String fatherLabel) {
		zFatherLabel = fatherLabel;
	}

	public String getWFather() {
		return wFather;
	}

	public void setWFather(String father) {
		wFather = father;
	}

	public String getWFatherLabel() {
		return wFatherLabel;
	}

	public void setWFatherLabel(String fatherLabel) {
		wFatherLabel = fatherLabel;
	}
	
}