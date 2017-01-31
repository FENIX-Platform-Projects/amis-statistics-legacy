package org.fao.fenix.web.modules.coding.common.vo;


import com.google.gwt.user.client.rpc.IsSerializable;

public class CodeVo implements IsSerializable {

	private String title;
	private String region;
	private String code;
	private String label;
	private String langCode;
	private String description;
	private String dcmtCode;
	private Integer level;
	private String parent;

	public CodeVo(){	
	}
	
	public CodeVo(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public String getDcmtCode() {
		return dcmtCode;
	}
	public void setDcmtCode(String dcmtCode) {
		this.dcmtCode = dcmtCode;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
}
