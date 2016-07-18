package org.fao.fenix.web.modules.fpi.common.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MealsIndexVo implements IsSerializable {

	private Date date;
	
	private double index;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getIndex() {
		return index;
	}

	public void setIndex(double index) {
		this.index = index;
	}
	
}
