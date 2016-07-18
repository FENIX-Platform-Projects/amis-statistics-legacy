package org.fao.fenix.web.modules.core.common.vo.highcharts;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.highcharts.HighChartConstants;


import com.google.gwt.user.client.rpc.IsSerializable;

//TODO: to be extended...
public class HighChartVO implements IsSerializable {
	
	String title;
	
	String type;
	
	Boolean isStacked = false;
	
	// refering to Stacking.NORMAL or Stacking.PERCENT
	// String stackedType = "NORMAL" (Staking Constant -> "NORMAL" or "PERCENT");
	
	// this is for the values on the xAxis
	List<String> xValues;
	
	List<HighChartValueVO> series = new ArrayList<HighChartValueVO>();

	/** TODO: to remove... **/
	LinkedHashMap<String, Map<String, Double>> chartValues = new LinkedHashMap<String, Map<String,Double>>();
	
	LinkedHashMap<String, String> serieMeasurementUnits = new LinkedHashMap<String, String>(); 
	
	// MU, position on the Y-Axis (0, 1, 2...)
	LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>(); 
	
	Boolean renderXValues = true;
	
	
	
	/** settings **/
	
	// X-Axis
	HighChartXAxis xAxis = new HighChartXAxis();
	
	//	Toolptip
	HighChartTooltip tooltip = new HighChartTooltip();
	
	//	Legend
	HighChartLegend legend = new HighChartLegend();
	

	
	// parameters...
//	HashMap<HighChartParametersConstants, String> parameters = new HashMap<HighChartParametersConstants, String>();
	
	HashMap<HighChartParametersConstants, String> parameters = new HashMap<HighChartParametersConstants, String>();


	

	
	

	public List<String> getxValues() {
		return xValues;
	}

	public void setxValues(List<String> xValues) {
		this.xValues = xValues;
	}

	public List<HighChartValueVO> getSeries() {
		return series;
	}

	public void setSeries(List<HighChartValueVO> series) {
		this.series = series;
	}

	public Boolean getRenderXValues() {
		return renderXValues;
	}

	public void setRenderXValues(Boolean renderXValues) {
		this.renderXValues = renderXValues;
	}

	public LinkedHashMap<String, Map<String, Double>> getChartValues() {
		return chartValues;
	}

	public void setChartValues(
			LinkedHashMap<String, Map<String, Double>> chartValues) {
		this.chartValues = chartValues;
	}

	public LinkedHashMap<String, String> getSerieMeasurementUnits() {
		return serieMeasurementUnits;
	}

	public void setSerieMeasurementUnits(
			LinkedHashMap<String, String> serieMeasurementUnits) {
		this.serieMeasurementUnits = serieMeasurementUnits;
	}

	public LinkedHashMap<String, String> getMeasurementUnits() {
		return measurementUnits;
	}

	public void setMeasurementUnits(LinkedHashMap<String, String> measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsStacked() {
		return isStacked;
	}

	public void setIsStacked(Boolean isStacked) {
		this.isStacked = isStacked;
	}

	public HighChartXAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(HighChartXAxis xAxis) {
		this.xAxis = xAxis;
	}

	public HighChartTooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(HighChartTooltip tooltip) {
		this.tooltip = tooltip;
	}

	public HighChartLegend getLegend() {
		return legend;
	}

	public void setLegend(HighChartLegend legend) {
		this.legend = legend;
	}

	public HashMap<HighChartParametersConstants, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<HighChartParametersConstants, String> parameters) {
		this.parameters = parameters;
	}

	
	
	
	
}
