package org.fao.fenix.web.modules.x.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class XExplorerSearchParametersVO implements IsSerializable {

	private String resourceType;
	
	private String title;
	
	private String keywords;
	
	private String geographicArea;
	
	private String category;
	
	private String values;
	
	private List<String> urls;
	
	private String orderBy;
	
	private String orderDirection;
	
	private int firstResultIndex;
	
	private int maxResults;
	
	private int maxItemsPerPage;
	
	private String language;
	
	public XExplorerSearchParametersVO() {
		this.setCategory("");
		this.setGeographicArea("");
		this.setKeywords("");
		this.setOrderBy("");
		this.setOrderDirection("");
		this.setResourceType("");
		this.setTitle("");
		this.setUrls(new ArrayList<String>());
		this.setValues("");
		this.setLanguage("EN");
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGeographicArea() {
		return geographicArea;
	}

	public void setGeographicArea(String geographicArea) {
		this.geographicArea = geographicArea;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public void addUrl(String url) {
		if (this.urls == null)
			this.urls = new ArrayList<String>();
		this.urls.add(url);
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public int getFirstResultIndex() {
		return firstResultIndex;
	}

	public void setFirstResultIndex(int firstResultIndex) {
		this.firstResultIndex = firstResultIndex;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getMaxItemsPerPage() {
		return maxItemsPerPage;
	}

	public void setMaxItemsPerPage(int maxItemsPerPage) {
		this.maxItemsPerPage = maxItemsPerPage;
	}
	
}