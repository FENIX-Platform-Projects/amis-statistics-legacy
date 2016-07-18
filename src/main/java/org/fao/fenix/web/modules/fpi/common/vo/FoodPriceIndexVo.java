package org.fao.fenix.web.modules.fpi.common.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FoodPriceIndexVo implements IsSerializable {

	private Date date;
	private double foodPriceIndex;
	private double meatIndex;
	private double dairyIndex;
	private double cerealsIndex;
	private double oilsIndex;
	private double sugarIndex;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getFoodPriceIndex() {
		return foodPriceIndex;
	}

	public void setFoodPriceIndex(double foodPriceIndex) {
		this.foodPriceIndex = foodPriceIndex;
	}

	public double getMeatIndex() {
		return meatIndex;
	}

	public void setMeatIndex(double meatIndex) {
		this.meatIndex = meatIndex;
	}

	public double getDairyIndex() {
		return dairyIndex;
	}

	public void setDairyIndex(double dairyIndex) {
		this.dairyIndex = dairyIndex;
	}

	public double getCerealsIndex() {
		return cerealsIndex;
	}

	public void setCerealsIndex(double cerealsIndex) {
		this.cerealsIndex = cerealsIndex;
	}

	public double getOilsIndex() {
		return oilsIndex;
	}

	public void setOilsIndex(double oilsIndex) {
		this.oilsIndex = oilsIndex;
	}

	public double getSugarIndex() {
		return sugarIndex;
	}

	public void setSugarIndex(double sugarIndex) {
		this.sugarIndex = sugarIndex;
	}
	
}
