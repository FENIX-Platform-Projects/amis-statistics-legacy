package org.fao.fenix.web.modules.coding.common.vo;





import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;




public class CodingHiearachyMD extends BaseTreeModel implements IsSerializable  {

	private String langCode; 
	
	public CodingHiearachyMD() {
	}
	
	public CodingHiearachyMD(String code, String label){
	  set("code", code);
	  set("label", label);
	}
	
	public CodingHiearachyMD(String code, String label, Integer level, String parent){
		  set("code", code);
		  set("label", label);
		  set("level", level);
		  set("parent", parent);
		}
	
	public String getCode() {
	   return (String) get("code");
	}
	
	public Integer getlevel() {
	   return (Integer) get("level");
	}
	
	public String getLabel() {
	   return (String) get("label");
	}
	
	public String getParenT() {
	   return (String) get("parent");
	}

		
	
	public void setN_level(Integer level) {
		this.set("level", level);
	}
	
	public void setParent(String parent) {
		this.set("parent", parent);
	}
	
	
}
