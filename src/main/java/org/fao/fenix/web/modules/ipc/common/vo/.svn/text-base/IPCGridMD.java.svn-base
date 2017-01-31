package org.fao.fenix.web.modules.ipc.common.vo;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class IPCGridMD extends BaseTreeModel implements IsSerializable  {

	private Long workId;
		
	private Long contributor_id;
	
	private String contributorFirstName;
	
	private String contributorLastName;
		
	private String moderatorFullName;
	
	private Long workflowId;
	
	private Date date;
	
	private String countryCode;
	
	private String countryLabel;
	
//	private String xml;
	
	private String statusCode;
	
	private String referenceLayerCode;
	
	IPCGridMD() {
	}
	
	public IPCGridMD(String name, String referenceLayer, String description, String period, String status, String moderator){
	  set("name", name);
	  set("referenceLayer", referenceLayer);   
	  set("description", description);
	  set("period", period);
	  set("status", status);
	  set("moderatorFullName", moderator);
//	  setModeratorFullName(moderator);
	}
	
	public IPCGridMD(String name, String referenceLayer, String province, String contributor, String description, String period, String status, String moderator){
		  set("name", name);
		  set("referenceLayer", referenceLayer);
		  set("provinceName", province);
		  set("contributor", contributor);
		  set("description", description);
		  set("period", period);
		  set("status", status);
		  setModeratorFullName(moderator);
		}
	
	public String getName() {
	   return (String) get("name");
	}
	public String getReferenceLayer() {
	   return (String) get("referenceLayer");
	}
	public String getDescription() {
	   return (String) get("description");
	}
	public String getPeriod() {
	   return (String) get("period");
	}
	public String getStatus() {
	   return (String) get("status");
	}
	
	
	
	
	public void setName(String name) {
		this.set("name", name);
	}
	public void setReferenceLayer(String referenceLayer) {
		this.set("referenceLayer", referenceLayer);
	}
	public void setDescription(String description) {
		this.set("description", description);
	}
	public void setPeriod(String period) {
		this.set("period", period);
	}
	public void setStatus(String status) {
		this.set("status", status);
	}



	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public Long getContributor_id() {
		return contributor_id;
	}

	public void setContributor_id(Long contributor_id) {
		this.contributor_id = contributor_id;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getModeratorFullName() {
		return moderatorFullName;
	}

	public void setModeratorFullName(String moderatorFullName) {
		this.moderatorFullName = moderatorFullName;
	}

//	public String getXml() {
//		return xml;
//	}
//
//	public void setXml(String xml) {
//		this.xml = xml;
//	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryLabel() {
		return countryLabel;
	}

	public void setCountryLabel(String countryLabel) {
		this.countryLabel = countryLabel;
	}

	public String getReferenceLayerCode() {
		return referenceLayerCode;
	}

	public void setReferenceLayerCode(String referenceLayerCode) {
		this.referenceLayerCode = referenceLayerCode;
	}
	
	
}
