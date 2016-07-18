package org.fao.fenix.web.modules.coding.common.vo;





import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;




public class DcmtCodingCreatorGridMD extends BaseTreeModel implements IsSerializable  {

	private String langCode; 
	
	public DcmtCodingCreatorGridMD() {
	}
	
	public DcmtCodingCreatorGridMD(String code, String dcmtCode, String label){
	  set("code", code);
	  set("dcmtCode", dcmtCode);   
	  set("label", label);
//	  set("dcmtCode", "");
	}
	
	public String getCode() {
	   return (String) get("code");
	 }
	public String getDcmtCode() {
	   return (String) get("dcmtCode");
	 }
	public String getLabel() {
	   return (String) get("label");
	 }
	public void setDcmtCode(String dcmtCode) {
		this.set("dcmtCode", dcmtCode);
	}
	public void setFromCode(String fromCode) {
		this.set("fromCode", fromCode);
	}
	public void setLangCode(String langCode) {
		this.set("langCode", langCode);
	}
	
}
