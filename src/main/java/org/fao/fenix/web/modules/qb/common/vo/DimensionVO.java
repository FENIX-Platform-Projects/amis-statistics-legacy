package org.fao.fenix.web.modules.qb.common.vo;

import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;


public class DimensionVO extends DimensionBeanVO {

	private String ds_id;
	private String periodType;

	public String getDs_id() {
		return ds_id;
	}

	public void setDs_id(String dsId) {
		ds_id = dsId;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

}