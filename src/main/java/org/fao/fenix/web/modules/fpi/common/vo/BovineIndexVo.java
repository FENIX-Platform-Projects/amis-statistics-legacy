package org.fao.fenix.web.modules.fpi.common.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BovineIndexVo implements IsSerializable {

	private double bovineWeightedPrice;
	
	private Date date;

	public double getBovineWeightedPrice() {
		return bovineWeightedPrice;
	}

	public void setBovineWeightedPrice(double bovineWeightedPrice) {
		this.bovineWeightedPrice = bovineWeightedPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
