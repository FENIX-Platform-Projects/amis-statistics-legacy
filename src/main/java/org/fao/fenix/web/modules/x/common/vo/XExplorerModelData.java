package org.fao.fenix.web.modules.x.common.vo;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class XExplorerModelData extends BaseModel implements IsSerializable {

	private String name;
	
	private String node;
	
	private String url;
	
	private String localID;
	
	private String resourceID;
	
	private String source;
	
	private String region;
	
	private Date dateLastUpdate;
	
	private Date startDate;
	
	private Date endDate;
	
	private String abstractAbstract;
	
	private String category;
	
	private String code;
	
	private String keywords;
	
	private String provider;
	
	private String datasetType;
	
	private String periodTypeCode;
	
	private String resourceType;
	
	public XExplorerModelData() {
		this.setName("");
		this.setNode("");
		this.setRegion("");
		this.setSource("");
		this.setLocalID("");
		this.setResourceID("");
		this.setAbstractAbstract("");
		this.setCategory("");
		this.setCode("");
		this.setDatasetType("");
		this.setKeywords("");
		this.setPeriodTypeCode("");
		this.setProvider("");
		this.setResourceType("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		set("name", this.name);
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
		set("node", this.node);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		set("source", this.source);
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
		set("region", this.region);
	}

	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
		set("dateLastUpdate", this.dateLastUpdate);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		set("url", this.url);
	}

	public String getLocalID() {
		return localID;
	}

	public void setLocalID(String localID) {
		this.localID = localID;
		set("localID", this.localID);
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
		set("resourceID", this.resourceID);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		set("startDate", this.startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		set("endDate", this.endDate);
	}

	public String getAbstractAbstract() {
		return abstractAbstract;
	}

	public void setAbstractAbstract(String abstractAbstract) {
		this.abstractAbstract = abstractAbstract;
		set("abstractAbstract", this.abstractAbstract);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		set("category", this.category);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		set("code", this.code);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
		set("keywords", this.keywords);
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
		set("provider", this.provider);
	}

	public String getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(String datasetType) {
		this.datasetType = datasetType;
		set("datasetType", this.datasetType);
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
		set("periodTypeCode", this.periodTypeCode);
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
		set("resourceType", this.resourceType);
	}
	
}