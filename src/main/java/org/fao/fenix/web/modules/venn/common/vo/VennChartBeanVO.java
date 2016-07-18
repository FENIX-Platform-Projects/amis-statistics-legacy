package org.fao.fenix.web.modules.venn.common.vo;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;


import com.google.gwt.user.client.rpc.IsSerializable;

public class VennChartBeanVO implements IsSerializable {
	
	ChartWizardBean chart;
	
	String text;
	
	String iframePortlet;
	
	String iframeCentralPanel;
	
	/**  the values of the chart 
	 * 	 (i.e. for each donor, the related info (like sum, avg, count)
	 * 
	 * 	 
	 * **/
	
	LinkedHashMap<String, Double> sum = new LinkedHashMap<String, Double>();
	
	LinkedHashMap<String, Double> avg = new LinkedHashMap<String, Double>();
	
	LinkedHashMap<String, Double> count = new LinkedHashMap<String, Double>();
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ChartWizardBean getChart() {
		return chart;
	}

	public void setChart(ChartWizardBean chart) {
		this.chart = chart;
	}

	public String getIframePortlet() {
		return iframePortlet;
	}

	public void setIframePortlet(String iframePortlet) {
		this.iframePortlet = iframePortlet;
	}

	public String getIframeCentralPanel() {
		return iframeCentralPanel;
	}

	public void setIframeCentralPanel(String iframeCentralPanel) {
		this.iframeCentralPanel = iframeCentralPanel;
	}

	public LinkedHashMap<String, Double> getSum() {
		return sum;
	}

	public void setSum(LinkedHashMap<String, Double> sum) {
		this.sum = sum;
	}

	public LinkedHashMap<String, Double> getAvg() {
		return avg;
	}

	public void setAvg(LinkedHashMap<String, Double> avg) {
		this.avg = avg;
	}

	public LinkedHashMap<String, Double> getCount() {
		return count;
	}

	public void setCount(LinkedHashMap<String, Double> count) {
		this.count = count;
	}

	
	
	
	
	
	
}