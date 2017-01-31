package org.fao.fenix.web.modules.core.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FenixResourceVo implements IsSerializable {

	private String id;
	private String name;
	private String title;
	private String project;
	private String type;
	private String url;
	private String birtViewMode;
	private String lastUpdated;
	private String category;
	private String region;
	private String source;
	private String periodTypeCode;
	private String localID; //  used for X or for ResourceView to store dataset's ID
	private boolean hasWritePermission = false;
	private boolean hasDeletePermission = false;
	private boolean hasDownloadPermission = false;
	private boolean isDatasetFlex = false;

	public FenixResourceVo() {
	}

	static public void copy(FenixResourceVo src, FenixResourceVo dst) {
		dst.id = src.id;
		dst.name = src.name;
		dst.title = src.title;
		dst.project = src.project;
		dst.type = src.type;
		dst.url = src.url;
		dst.birtViewMode = src.birtViewMode;
		dst.lastUpdated = src.lastUpdated;
		dst.category = src.category;
		dst.region = src.region;
		dst.localID = src.localID;
	}

	public FenixResourceVo(String id, String project, String name, String type, String url) {
		this.setId(id);
		this.setProject(project);
		this.setTitle(name);
		this.setType(type);
		this.setUrl(url);
	}

	public FenixResourceVo(String id, String project, String name, String type, String url, String birtViewMode) {
		this.setId(id);
		this.setProject(project);
		this.setTitle(name);
		this.setType(type);
		this.setUrl(url);
		this.setBirtViewMode(birtViewMode);
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getLocalID() {
		return localID;
	}

	public void setLocalID(String localID) {
		this.localID = localID;
	}

	public void setDatasetFlex(boolean isDatasetFlex) {
		this.isDatasetFlex = isDatasetFlex;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBirtViewMode() {
		return birtViewMode;
	}

	public void setBirtViewMode(String birtViewMode) {
		this.birtViewMode = birtViewMode;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
	}
	
	public void setIsDatasetFlex(boolean isDatasetFlex) {
		this.isDatasetFlex = isDatasetFlex;
	}

	public boolean isDatasetFlex() {
		return isDatasetFlex;
	}
}
