package org.fao.fenix.web.modules.ipc.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IPCMapValuesVO implements IsSerializable {

	private String projectedPhase;
	
	private String riskLevel;
	
	private String projectedTrend;

	public String getProjectedPhase() {
		return projectedPhase;
	}

	public void setProjectedPhase(String projectedPhase) {
		this.projectedPhase = projectedPhase;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getProjectedTrend() {
		return projectedTrend;
	}

	public void setProjectedTrend(String projectedTrend) {
		this.projectedTrend = projectedTrend;
	}
	
}