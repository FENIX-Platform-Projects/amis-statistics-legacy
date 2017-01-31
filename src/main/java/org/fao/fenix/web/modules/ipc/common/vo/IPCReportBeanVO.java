package org.fao.fenix.web.modules.ipc.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IPCReportBeanVO implements IsSerializable {
	
	private Long workFlowId;
	
	private Long workId;
	
	private String country;
	
	private String referenceLayer;

	private Long contributorId;
	
	private String contributorFirstName;
	
	private String contributorLastName;
	
	private String moderatorFullName;
	
	private String moduleOne;
	
	private String moduleTwo;
	
	private String moduleThree;
	
	private String description;
	
	private String title;
	
	private String period;
	
	private String status;


	public String getContributorFirstName() {
		return contributorFirstName;
	}

	public void setContributorFirstName(String contributorFirstName) {
		this.contributorFirstName = contributorFirstName;
	}


	public String getContributorLastName() {
		return contributorLastName;
	}

	public void setContributorLastName(String contributorLastName) {
		this.contributorLastName = contributorLastName;
	}

	public String getModuleOne() {
		return moduleOne;
	}

	public void setModuleOne(String moduleOne) {
		this.moduleOne = moduleOne;
	}

	public String getModuleTwo() {
		return moduleTwo;
	}

	public void setModuleTwo(String moduleTwo) {
		this.moduleTwo = moduleTwo;
	}

	public String getModuleThree() {
		return moduleThree;
	}

	public void setModuleThree(String moduleThree) {
		this.moduleThree = moduleThree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getReferenceLayer() {
		return referenceLayer;
	}

	public void setReferenceLayer(String referenceLayer) {
		this.referenceLayer = referenceLayer;
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Long getContributorId() {
		return contributorId;
	}

	public void setContributorId(Long contributorId) {
		this.contributorId = contributorId;
	}
	
	public String getModeratorFullName() {
		return moderatorFullName;
	}

	public void setModeratorFullName(String moderatorFullName) {
		this.moderatorFullName = moderatorFullName;
	}


}