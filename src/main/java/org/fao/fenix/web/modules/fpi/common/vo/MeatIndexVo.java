package org.fao.fenix.web.modules.fpi.common.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MeatIndexVo implements IsSerializable {

	private Date date;
	private double poultryPriceMonthlyWeighted;
	private double pigPriceMonthlyWeighted;
	private double bovinePriceMonthlyWeighted;
	private double ovinePriceMonthlyWeighted;
	private double worldExportWeightsMeatsTotal;
	private double meatPriceIndex;
	private double meatFixedAggregate;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPoultryPriceMonthlyWeighted() {
		return poultryPriceMonthlyWeighted;
	}

	public void setPoultryPriceMonthlyWeighted(double poultryPriceMonthlyWeighted) {
		this.poultryPriceMonthlyWeighted = poultryPriceMonthlyWeighted;
	}

	public double getPigPriceMonthlyWeighted() {
		return pigPriceMonthlyWeighted;
	}

	public void setPigPriceMonthlyWeighted(double pigPriceMonthlyWeighted) {
		this.pigPriceMonthlyWeighted = pigPriceMonthlyWeighted;
	}

	public double getBovinePriceMonthlyWeighted() {
		return bovinePriceMonthlyWeighted;
	}

	public void setBovinePriceMonthlyWeighted(double bovinePriceMonthlyWeighted) {
		this.bovinePriceMonthlyWeighted = bovinePriceMonthlyWeighted;
	}

	public double getOvinePriceMonthlyWeighted() {
		return ovinePriceMonthlyWeighted;
	}

	public void setOvinePriceMonthlyWeighted(double ovinePriceMonthlyWeighted) {
		this.ovinePriceMonthlyWeighted = ovinePriceMonthlyWeighted;
	}

	public double getWorldExportWeightsMeatsTotal() {
		return worldExportWeightsMeatsTotal;
	}

	public void setWorldExportWeightsMeatsTotal(double worldExportWeightsMeatsTotal) {
		this.worldExportWeightsMeatsTotal = worldExportWeightsMeatsTotal;
	}

	public double getMeatPriceIndex() {
		return meatPriceIndex;
	}

	public void setMeatPriceIndex(double meatPriceIndex) {
		this.meatPriceIndex = meatPriceIndex;
	}

	public double getMeatFixedAggregate() {
		return meatFixedAggregate;
	}

	public void setMeatFixedAggregate(double meatFixedAggregate) {
		this.meatFixedAggregate = meatFixedAggregate;
	}

}
