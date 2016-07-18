package org.fao.fenix.web.modules.wcct.client.view.vo;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class WCCTTableMD extends BaseTreeModel implements IsSerializable  {
	
	public WCCTTableMD() {
	}
	
	public WCCTTableMD(String country, String crop, String further, String cropStage, String startMonth, String startDek, String endMonth, String endDek){
	  set("country", country);
	  set("crop", crop);   
	  set("further", further);
	  set("cropStage", cropStage);
	  set("startMonth", startMonth);
	  set("startDek", startDek);
	  set("endMonth", endMonth);
	  set("endDek", endDek);
	}
	
	
	public String getCountry() {
		return (String) get("country");
	}
	public String getCrop() {
		return (String) get("crop");
	}
	public String getFurther() {
		return (String) get("further");
	}
	public String getCropStage() {
		return (String) get("cropStage");
	}
	public String getStartmonth() {
		return (String) get("startMonth");
	}
	public String getStartDek() {
		return (String) get("startDek");
	}
	public String getEndMonth() {
		return (String) get("endMonth");
	}
	public String getEndDek() {
		return (String) get("endDek");
	}
	
	
}
