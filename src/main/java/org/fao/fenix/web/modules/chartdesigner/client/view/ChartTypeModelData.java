package org.fao.fenix.web.modules.chartdesigner.client.view;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class ChartTypeModelData extends BaseModel implements IsSerializable {

	private String chartTypeCode;
	
	private String chartTypeLabel;
	
	public ChartTypeModelData() {
		
	}
	
	public ChartTypeModelData(String chartTypeCode, String chartTypeLabel) {
		this.setChartTypeCode(chartTypeCode);
		this.setChartTypeLabel(chartTypeLabel);
	}

	public String getChartTypeCode() {
		return chartTypeCode;
	}

	public void setChartTypeCode(String chartTypeCode) {
		this.chartTypeCode = chartTypeCode;
		set("chartTypeCode", this.chartTypeCode);
	}

	public String getChartTypeLabel() {
		return chartTypeLabel;
	}

	public void setChartTypeLabel(String chartTypeLabel) {
		this.chartTypeLabel = chartTypeLabel;
		set("chartTypeLabel", this.chartTypeLabel);
	}
	
}