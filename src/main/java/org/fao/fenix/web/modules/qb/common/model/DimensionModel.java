package org.fao.fenix.web.modules.qb.common.model;


import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class DimensionModel extends BaseTreeModel implements IsSerializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DimensionModel() {
	}

	public DimensionModel(String name, String code, String dataType) {
		setName(name);
		setCode(code);
		setDataType(dataType);
	}

	

	private void setName(String name) {
		set("name", name);
	}

	private void setCode(String code) {
		set("code", code);
	}

	private void setDataType(String dataType) {
		set("dataType", dataType);
	}

	public void setPeriodType(String periodType) {
		set("periodType", periodType);
	}

	public void setCodingSystemTitle(String csTitle) {
		set("csTitle", csTitle);
	}
	
	public void setCodingSystemRegion(String csRegion) {
		set("csRegion", csRegion);
	}

	public void setDimensionName(String name) {
		set("dimensionName", name);
	}

	public String getDimensionName() {
		return get("dimensionName");
	}
	
	public String getName() {
		return get("name");
	}

	public String getCode() {
		return get("code");
	}
	
	public String getDataType() {
		return get("dataType");
	}
	
	public String getPeriodType() {
		return get("periodType");
	}
	
	public String getCodingSystemTitle() {
		return get("csTitle");
	}
	
	public String getCodingSystemRegion() {
		return get("csRegion");
	}
}
