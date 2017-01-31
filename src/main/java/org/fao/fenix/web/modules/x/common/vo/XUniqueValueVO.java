package org.fao.fenix.web.modules.x.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class XUniqueValueVO implements IsSerializable {

	private Long id;

	private Long xResourceID;

	private Long xDescriptorID;

	private String code;

	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getxResourceID() {
		return xResourceID;
	}

	public void setxResourceID(Long xResourceID) {
		this.xResourceID = xResourceID;
	}

	public Long getxDescriptorID() {
		return xDescriptorID;
	}

	public void setxDescriptorID(Long xDescriptorID) {
		this.xDescriptorID = xDescriptorID;
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

}
