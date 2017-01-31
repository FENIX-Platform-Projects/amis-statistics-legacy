package org.fao.fenix.web.modules.ofcchart.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DimensionsBeanVO implements IsSerializable {

	private String ds_id;
	private String cs_title;
	private String cs_region;
	private String header;
	private String datatype;
	private String periodType;
	private Boolean isDate;
	
	
	public String getDs_id() {
		return ds_id;
	}

	public void setDs_id(String dsId) {
		ds_id = dsId;
	}

	public String getCs_title() {
		return cs_title;
	}

	public void setCs_title(String csTitle) {
		cs_title = csTitle;
	}

	public String getCs_region() {
		return cs_region;
	}

	public void setCs_region(String csRegion) {
		cs_region = csRegion;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public Boolean getIsDate() {
		return isDate;
	}

	public void setIsDate(Boolean isDate) {
		this.isDate = isDate;
	}
	
}