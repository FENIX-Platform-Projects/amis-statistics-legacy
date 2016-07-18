package org.fao.fenix.web.modules.chartdesigner.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartDesignerHighlightVO implements IsSerializable {

	private Long datasetID;
	
	private boolean show = false;
	
	private String highlightType = "";
	
	private String color = "";
	
	private String size = "";
	
	private String style = "";
	
	private String symbol = "";
	
	private String alias = "";
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getHighlightType() {
		return highlightType;
	}

	public void setHighlightType(String highlightType) {
		this.highlightType = highlightType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}