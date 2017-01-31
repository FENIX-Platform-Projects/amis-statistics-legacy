package org.fao.fenix.web.modules.coding.common.vo;


import com.google.gwt.user.client.rpc.IsSerializable;

public class MappingVo implements IsSerializable {

	private String fromCode;
	
	private String dcmtCode;
	
	private String title;
	
	private String region;

	
	
	public String getFromCode() {
		return fromCode;
	}
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	public String getDcmtCode() {
		return dcmtCode;
	}
	public void setDcmtCode(String dcmtCode) {
		this.dcmtCode = dcmtCode;
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
}
