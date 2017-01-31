package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.LineChart;

public class OfcSeriesVO implements IsSerializable {
	
	/** DatasetId, List<BarChart> **/
	HashMap<String, List<BarChart>> barChartSeries;

	/** DatasetId, List<BarChart> **/
	HashMap<String, List<List<LineChart>>> lineChartSeries;
	
	
	public OfcSeriesVO(){
		barChartSeries = new HashMap<String, List<BarChart>>();
		lineChartSeries = new HashMap<String, List<List<LineChart>>>();
	}

	public HashMap<String, List<BarChart>> getBarChartSeries() {
		return barChartSeries;
	}

	public void setBarChartSeries(HashMap<String, List<BarChart>> barChartSeries) {
		this.barChartSeries = barChartSeries;
	}

	public HashMap<String, List<List<LineChart>>> getLineChartSeries() {
		return lineChartSeries;
	}

	public void setLineChartSeries(
			HashMap<String, List<List<LineChart>>> lineChartSeries) {
		this.lineChartSeries = lineChartSeries;
	}
	
}