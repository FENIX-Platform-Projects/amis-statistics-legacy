package org.fao.fenix.web.modules.adam.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ADAMDonorChannelOfCooperationVO implements IsSerializable {
	
	private String referenceDate;
	private String channelOfCooperation;
	private String applicationAndNegotiationProcess;
	private String annualFundingCycle;
	private String specialCharacteristics;
	private String budgetRevisionPolicies;
	private String accruedInterestPolicies;
	
	
	public String getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String getAnnualFundingCycle() {
		return annualFundingCycle;
	}
	public void setAnnualFundingCycle(String annualFundingCycle) {
		this.annualFundingCycle = annualFundingCycle;
	}
	public String getSpecialCharacteristics() {
		return specialCharacteristics;
	}
	public void setSpecialCharacteristics(String specialCharacteristics) {
		this.specialCharacteristics = specialCharacteristics;
	}
	
	public String getChannelOfCooperation() {
		return channelOfCooperation;
	}
	public void setChannelOfCooperation(String channelOfCooperation) {
		this.channelOfCooperation = channelOfCooperation;
	}
	public String getApplicationAndNegotiationProcess() {
		return applicationAndNegotiationProcess;
	}
	public void setApplicationAndNegotiationProcess(
			String applicationAndNegotiationProcess) {
		this.applicationAndNegotiationProcess = applicationAndNegotiationProcess;
	}
	
	public String getAccruedInterestPolicies() {
		return accruedInterestPolicies;
	}
	public void setAccruedInterestPolicies(
			String accruedInterestPolicies) {
		this.accruedInterestPolicies = accruedInterestPolicies;
	}
	
	public String getBudgetRevisionPolicies() {
		return budgetRevisionPolicies;
	}
	public void setBudgetRevisionPolicies(
			String budgetRevisionPolicies) {
		this.budgetRevisionPolicies = budgetRevisionPolicies;
	}
}