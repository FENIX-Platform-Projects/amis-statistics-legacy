package org.fao.fenix.web.modules.ipc.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DropDownVO implements IsSerializable {

	private String code;
	
	private String name;
	
	private String dropDownCode;
	
	private String dropDownLabel;
	
	public DropDownVO() {
		code = new String();
		name = new String();
		dropDownCode = new String();
		dropDownLabel = new String();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDropDownCode() {
		return dropDownCode;
	}

	public void setDropDownCode(String dropDownCode) {
		this.dropDownCode = dropDownCode;
	}

	public String getDropDownLabel() {
		return dropDownLabel;
	}

	public void setDropDownLabel(String dropDownLabel) {
		this.dropDownLabel = dropDownLabel;
	}
	
}