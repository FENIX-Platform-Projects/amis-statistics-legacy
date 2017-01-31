package org.fao.fenix.web.modules.core.common.vo.highcharts;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HighChartTooltip implements IsSerializable {
	
	
	Boolean crosshairs = true;
	
	Boolean shared = true;

	public Boolean getCrosshairs() {
		return crosshairs;
	}

	public void setCrosshairs(Boolean crosshairs) {
		this.crosshairs = crosshairs;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}
	

	
}
