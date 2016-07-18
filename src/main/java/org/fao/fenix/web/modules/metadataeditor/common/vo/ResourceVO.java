/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.metadataeditor.common.vo;

import java.util.Date;
import java.util.Map;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class ResourceVO extends BaseModel implements IsSerializable {

	private long resourceId;

	private String title;
	
	private String abstractAbstract;
	
	private String keywords;
	
	private String provider;
	
	private String providerRegion;
	
	private String providerContact;
	
	private String contact;
	
	private String region;
	
	private String categories;

	private Date startDate;

	private Date endDate;

	private String sharingCode;

	private Date dateLastUpdate;

	private String source;
	
	private String sourceRegion;
	
	private String sourceContact;

	private String code;
	
	private String periodTypeCode;
	
	private String featureCodeSet;
	
	private String resourceType;
	
	private String imageLink;
	
	private Map<String, String> resourceSpecificMap;
	
	private boolean hasWritePermission = false;
	private boolean hasDeletePermission = false;
	private boolean hasDownloadPermission = false;
	
	private String aggregatedTableName;
	
	public ResourceVO() {
		this.setAbstractAbstract("");
		this.setCategories("");
		this.setCode("");
		this.setContact("");
		this.setDateLastUpdate(new Date());
		this.setEndDate(new Date());
		this.setKeywords("");
		this.setPeriodTypeCode("");
		this.setProvider("");
		this.setRegion("");
		this.setResourceId(0);
		this.setSharingCode("Public");
		this.setSource("");
		this.setSourceRegion("");
		this.setStartDate(new Date());
		this.setTitle("");
		this.setFeatureCodeSet("");
		this.setResourceType("");
		this.setImageLink("");
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
		set("resourceType", this.resourceType);
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
		set("resourceId", this.resourceId);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		set("title", this.title);
	}

	public String getAbstractAbstract() {
		return abstractAbstract;
	}

	public void setAbstractAbstract(String abstractAbstract) {
		this.abstractAbstract = abstractAbstract;
		set("abstractAbstract", this.abstractAbstract);
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
		set("contact", this.contact);
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
		set("region", this.region);
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
		set("categories", this.categories);
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

	public String getSharingCode() {
		return sharingCode;
	}

	public void setSharingCode(String sharingCode) {
		this.sharingCode = sharingCode;
		set("sharingCode", this.sharingCode);
	}

	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
		set("dateLastUpdate", this.dateLastUpdate);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		set("source", this.source);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		set("code", this.code);
	}

	public String getSourceRegion() {
		return sourceRegion;
	}

	public void setSourceRegion(String sourceRegion) {
		this.sourceRegion = sourceRegion;
		set("sourceRegion", this.sourceRegion);
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
		set("periodTypeCode", this.periodTypeCode);
	}

	public String getFeatureCodeSet() {
		return featureCodeSet;
	}

	public void setFeatureCodeSet(String featureCodeSet) {
		this.featureCodeSet = featureCodeSet;
		set("featureCodeSet", this.featureCodeSet);
	}

	public String getProviderRegion() {
		return providerRegion;
	}

	public void setProviderRegion(String providerRegion) {
		this.providerRegion = providerRegion;
		set("providerRegion", this.providerRegion);
	}

	public String getProviderContact() {
		return providerContact;
	}

	public void setProviderContact(String providerContact) {
		this.providerContact = providerContact;
		set("providerContact", this.providerContact);
	}

	public String getSourceContact() {
		return sourceContact;
	}

	public void setSourceContact(String sourceContact) {
		this.sourceContact = sourceContact;
		set("sourceContact", this.sourceContact);
	}

	public Map<String, String> getResourceSpecificMap() {
		return resourceSpecificMap;
	}

	public void setResourceSpecificMap(Map<String, String> resourceSpecificMap) {
		this.resourceSpecificMap = resourceSpecificMap;
	}
	
	public boolean isHasWritePermission() {
		return hasWritePermission;
	}

	public void setHasWritePermission(boolean hasWritePermission) {
		this.hasWritePermission = hasWritePermission;
	}

	public boolean isHasDeletePermission() {
		return hasDeletePermission;
	}

	public void setHasDeletePermission(boolean hasDeletePermission) {
		this.hasDeletePermission = hasDeletePermission;
	}

	public boolean isHasDownloadPermission() {
		return hasDownloadPermission;
	}

	public void setHasDownloadPermission(boolean hasDownloadPermission) {
		this.hasDownloadPermission = hasDownloadPermission;
	}

	public String getAggregatedTableName() {
		return aggregatedTableName;
	}
	
	public void setAggregatedTableName(String aggregatedTableName) {
		this.aggregatedTableName = aggregatedTableName;
	}
}