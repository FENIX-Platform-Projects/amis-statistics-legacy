package org.fao.fenix.web.modules.fpi.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MeasurementUnitVo implements IsSerializable {

	long id;
	double constant;
	String symbol;
	String label;
	boolean visible;
	boolean isCurrency;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getConstant() {
		return constant;
	}

	public void setConstant(double constant) {
		this.constant = constant;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isCurrency() {
		return isCurrency;
	}

	public void setCurrency(boolean isCurrency) {
		this.isCurrency = isCurrency;
	}

}
