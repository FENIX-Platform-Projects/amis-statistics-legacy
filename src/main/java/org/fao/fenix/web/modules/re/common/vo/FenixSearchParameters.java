package org.fao.fenix.web.modules.re.common.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FenixSearchParameters implements IsSerializable {

	private String keyword;

	private String title;

	private List<String> region;

	private List<String> categories;

	private String scope;

	private List<String> resourceTypeList;
	
	private String orderBy;
	
	private String orderType;

	public FenixSearchParameters() {

	}
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getRegion() {
		return region;
	}

	public void setRegion(List<String> region) {
		this.region = region;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<String> getResourceTypeList() {
		return resourceTypeList;
	}

	public void setResourceTypeList(List<String> resourceTypeList) {
		this.resourceTypeList = resourceTypeList;
	}

}
