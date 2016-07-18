package org.fao.fenix.web.modules.core.common.vo.highcharts;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HighChartLegend implements IsSerializable {
	
	Integer lineHeight = 3;
	
	Integer symbolPadding = 3;
	
	Integer symbolWidth = 10;

	public Integer getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(Integer lineHeight) {
		this.lineHeight = lineHeight;
	}

	public Integer getSymbolPadding() {
		return symbolPadding;
	}

	public void setSymbolPadding(Integer symbolPadding) {
		this.symbolPadding = symbolPadding;
	}

	public Integer getSymbolWidth() {
		return symbolWidth;
	}

	public void setSymbolWidth(Integer symbolWidth) {
		this.symbolWidth = symbolWidth;
	}

	
	

}
