package org.fao.fenix.web.modules.ipc.common.vo;

import java.util.List;

import org.hibernate.annotations.Index;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProvinceVO implements IsSerializable {

	private Long id;
	
	private String provinceCode;
	
	private String provinceLabel;
	
	@Index(name="ipcContributorID")
	private Long contributor_id;
	
	@Index(name="ipcContributorFN")
	private String contributor_firstName;

	@Index(name="ipcContributorLN")
	private String contributor_lastName;
	
	
	private String period;
	
	private String riskLevel;
	
	private String projectedTrend;
	
	private String phaseClassification;
	
	private String xml;
	
	List<ModuleVO> modules;
	

	public List<ModuleVO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleVO> modules) {
		this.modules = modules;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceLabel() {
		return provinceLabel;
	}

	public void setProvinceLabel(String provinceLabel) {
		this.provinceLabel = provinceLabel;
	}

	public Long getContributor_id() {
		return contributor_id;
	}

	public void setContributor_id(Long contributorId) {
		contributor_id = contributorId;
	}

	public String getContributor_firstName() {
		return contributor_firstName;
	}

	public void setContributor_firstName(String contributorFirstName) {
		contributor_firstName = contributorFirstName;
	}

	public String getContributor_lastName() {
		return contributor_lastName;
	}

	public void setContributor_lastName(String contributorLastName) {
		contributor_lastName = contributorLastName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getPhaseClassification() {
		return phaseClassification;
	}

	public void setPhaseClassification(String phaseClassification) {
		this.phaseClassification = phaseClassification;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
}