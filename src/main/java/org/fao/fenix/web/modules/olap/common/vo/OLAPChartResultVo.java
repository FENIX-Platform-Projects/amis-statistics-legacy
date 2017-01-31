package org.fao.fenix.web.modules.olap.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OLAPChartResultVo implements IsSerializable {
	
	String reportName;
	String iFrame;
	String chartType;
	
	public String getReportName() {
		return reportName;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public String getIFrame() {
		return iFrame;
	}
	
	public void setIFrame(String frame) {
		iFrame = frame;
	}
	
	public String getChartType() {
		return chartType;
	}
	
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

}
