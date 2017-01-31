package org.fao.fenix.web.modules.fpi.common.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SugarIndexVo implements IsSerializable {

	private String commodityCode;
	private String featureCode;
	private Date referencePeriod;
	private double index;
	private double priceMonthly;
	private double priceAverage;
	private double aggregate;

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public Date getReferencePeriod() {
		return referencePeriod;
	}

	public void setReferencePeriod(Date referencePeriod) {
		this.referencePeriod = referencePeriod;
	}

	public double getIndex() {
		return index;
	}

	public void setIndex(double index) {
		this.index = index;
	}

	public double getPriceMonthly() {
		return priceMonthly;
	}

	public void setPriceMonthly(double priceMonthly) {
		this.priceMonthly = priceMonthly;
	}

	public double getPriceAverage() {
		return priceAverage;
	}

	public void setPriceAverage(double priceAverage) {
		this.priceAverage = priceAverage;
	}

	public double getAggregate() {
		return aggregate;
	}

	public void setAggregate(double aggregate) {
		this.aggregate = aggregate;
	}
	
}
