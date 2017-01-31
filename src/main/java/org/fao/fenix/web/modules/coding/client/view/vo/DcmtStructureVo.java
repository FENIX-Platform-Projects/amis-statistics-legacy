package org.fao.fenix.web.modules.coding.client.view.vo;


import com.google.gwt.user.client.rpc.IsSerializable;

public class DcmtStructureVo implements IsSerializable {

	private String father;
	
	private String code;
	
	
	
	
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
