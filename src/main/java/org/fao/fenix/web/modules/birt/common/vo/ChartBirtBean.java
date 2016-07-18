package org.fao.fenix.web.modules.birt.common.vo;


import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartBirtBean implements  IsSerializable {

	private String rptdesign;

	private String chartElement_ID;

	public String getRptdesign() {
		return rptdesign;
	}

	public void setRptdesign(String rptdesign) {
		this.rptdesign = rptdesign;
	}

	public String getChartElement_ID() {
		return chartElement_ID;
	}

	public void setChartElement_ID(String chartElementID) {
		chartElement_ID = chartElementID;
	}
	

	
}
