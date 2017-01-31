package org.fao.fenix.web.modules.ipc.common.vo;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WorkflowInfoVO implements IsSerializable {

	private Long workflow_id;
	
	private IPCCodesVO geographicArea;
	
	private IPCCodesVO referenceLayer;
	
//	private List<IPCCodesVO> interestedAreas;
	
	private String workflowName;
	
	private String description;

	private IPCCodesVO status;
	
	private Date date;
	
	private String period;
	
	private Long moderator_id;
	
	private String moderatorFirstName;
	
	private String moderatorLastName;
	
	private List<UserVO> observers;
	
	private List<UserVO> contributors;
	
	private List<ProvinceVO> provinces;
	
	public Long getWorkflowId() {
		return workflow_id;
	}

	public void setWorkflowId(Long workflow_id) {
		this.workflow_id = workflow_id;
	}

	
	
	
	
	public IPCCodesVO getGeographicArea() {
		return geographicArea;
	}

	public void setGeographicArea(IPCCodesVO geographicArea) {
		this.geographicArea = geographicArea;
	}

	public IPCCodesVO getReferenceLayer() {
		return referenceLayer;
	}

	public void setReferenceLayer(IPCCodesVO referenceLayer) {
		this.referenceLayer = referenceLayer;
	}

//	public List<IPCCodesVO> getInterestedAreas() {
//		return interestedAreas;
//	}
//
//	public void setInterestedAreas(List<IPCCodesVO> interestedAreas) {
//		this.interestedAreas = interestedAreas;
//	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IPCCodesVO getStatus() {
		return status;
	}

	public void setStatus(IPCCodesVO status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	

	public Long getModerator_id() {
		return moderator_id;
	}

	public void setModerator_id(Long moderatorId) {
		moderator_id = moderatorId;
	}

	

	


	public String getModeratorFirstName() {
		return moderatorFirstName;
	}

	public void setModeratorFirstName(String moderatorFirstName) {
		this.moderatorFirstName = moderatorFirstName;
	}

	public String getModeratorLastName() {
		return moderatorLastName;
	}

	public void setModeratorLastName(String moderatorLastName) {
		this.moderatorLastName = moderatorLastName;
	}

	

	public List<UserVO> getObservers() {
		return observers;
	}

	public void setObservers(List<UserVO> observers) {
		this.observers = observers;
	}

	

	public List<UserVO> getContributors() {
		return contributors;
	}

	public void setContributors(List<UserVO> contributorsId) {
		contributors = contributorsId;
	}

	public List<ProvinceVO> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ProvinceVO> provinces) {
		this.provinces = provinces;
	}

	
}