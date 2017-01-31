package org.fao.fenix.web.modules.venn.common.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennSetBeanVO implements IsSerializable {
	
	// this is used all for ALL the rest function
	String organizationType;
	
	String organization;
	
	List<String> strategies;

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public List<String> getStrategies() {
		return strategies;
	}

	public void setStrategies(List<String> strategies) {
		this.strategies = strategies;
	}
	

}
