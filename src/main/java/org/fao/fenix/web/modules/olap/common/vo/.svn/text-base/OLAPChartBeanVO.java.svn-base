package org.fao.fenix.web.modules.olap.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OLAPChartBeanVO implements IsSerializable {

	private String title;
	
	private String xLabel;
	
	private String yLabel;
	
	private String XParameter;
	
	private String YParameter;
	
	private List<String> xValues;
	
	private Map<String, List<String>> ySeries;
	
	private String chartType;
	
	public OLAPChartBeanVO() {
		title = "";
		xLabel = "";
		yLabel = "";
		XParameter = "";
		YParameter = "";
		chartType = "Bar";
		xValues = new ArrayList<String>();
		ySeries = new HashMap<String, List<String>>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getXParameter() {
		return XParameter;
	}

	public void setXParameter(String parameter) {
		XParameter = parameter;
	}

	public String getYParameter() {
		return YParameter;
	}

	public void setYParameter(String parameter) {
		YParameter = parameter;
	}

	public List<String> getXValues() {
		return xValues;
	}

	public void setXValues(List<String> values) {
		xValues = values;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public Map<String, List<String>> getYSeries() {
		return ySeries;
	}

	public void setYSeries(Map<String, List<String>> series) {
		ySeries = series;
	}
	
}