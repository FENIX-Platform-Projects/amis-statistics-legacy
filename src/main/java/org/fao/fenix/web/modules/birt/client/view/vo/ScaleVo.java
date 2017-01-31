package org.fao.fenix.web.modules.birt.client.view.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ScaleVo implements IsSerializable {
	
	String min;
	String max;
	String stepNumber;
	String fractionDigits;
	boolean autoScale;
	
	
	boolean secondScaleIsThere;
	String minScale2;
	String maxScale2;
	String stepNumberScale2;
	String fractionDigitsScale2;
	boolean autoScaleScale2;
	
	public boolean isSecondScaleIsThere() {
		return secondScaleIsThere;
	}

	public void setSecondScaleIsThere(boolean secondScaleIsThere) {
		this.secondScaleIsThere = secondScaleIsThere;
	}

	public String getMinScale2() {
		return minScale2;
	}

	public void setMinScale2(String minScale2) {
		this.minScale2 = minScale2;
	}

	public String getMaxScale2() {
		return maxScale2;
	}

	public void setMaxScale2(String maxScale2) {
		this.maxScale2 = maxScale2;
	}

	public String getStepNumberScale2() {
		return stepNumberScale2;
	}

	public void setStepNumberScale2(String stepNumberScale2) {
		this.stepNumberScale2 = stepNumberScale2;
	}

	public String getFractionDigitsScale2() {
		return fractionDigitsScale2;
	}

	public void setFractionDigitsScale2(String fractionDigitsScale2) {
		this.fractionDigitsScale2 = fractionDigitsScale2;
	}

	public boolean isAutoScaleScale2() {
		return autoScaleScale2;
	}

	public void setAutoScaleScale2(boolean autoScaleScale2) {
		this.autoScaleScale2 = autoScaleScale2;
	}

	public String getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(String fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(String stepNumber) {
		this.stepNumber = stepNumber;
	}

	public boolean isAutoScale() {
		return autoScale;
	}
	
	public void setAutoScale(boolean autoScale) {
		this.autoScale = autoScale;
	}
	
}
