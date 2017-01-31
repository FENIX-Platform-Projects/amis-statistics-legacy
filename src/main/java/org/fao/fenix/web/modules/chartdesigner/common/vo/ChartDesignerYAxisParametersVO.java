package org.fao.fenix.web.modules.chartdesigner.common.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartDesignerYAxisParametersVO implements IsSerializable {

	private Long datasetID;
	
	private Map<String, String> yCodes = new HashMap<String, String>();
	
	private String header = "";
	
	private String contentDescriptor = "";
	
	private boolean monthlyAggregated = false;
	
	private boolean yearlyAggregated = false;
	
	private boolean fromDateToDate = false;
	
	private boolean mostRecentData = false;
	
	private Date fromDate = new Date();
	
	private Date toDate = new Date();
	
	private int latestYears = 0;
	
	private int latestMonths = 0;
	
	private int latestDays = 0;
	
	private Map<String, String> seriesColorsMap = new HashMap<String, String>();
	
	private Map<String, String> headerYCodeMap = new HashMap<String, String>();
	
	private double yMin = 0;
	
	private double yMax = 100;
	
	private String axisTitle = "";
	
	private String fontStyle = "1";
	
	private String fontSize = "1";
	
	private String fontColor = "#000000";
	
	private String valuesFontStyle = "1";
	
	private String valuesFontSize = "1";
	
	private String valuesFontColor = "#000000";
	
	private String axisColor = "#000000";
	
	private String ticksColor = "#000000";
	
	private double step = 0;
	
	private String labelOrientation = "1";
	
	private String axisPosition = "2";
	
	private String scaleType = "LINEAR";
	
	private String lineType = "1";
	
	private String lineWidth = "1";
	
	private boolean yGrid;
	
	private String yGridColor = "#000000";
	
	private String yGridLineStyle = "1";
	
	private boolean yAxisEquidistantDates = true;
	
	private boolean stacked = false;
	
	private String pointType = "1";
	
	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
		
	public boolean isStacked() {
		return stacked;
	}

	public void setStacked(boolean stacked) {
		this.stacked = stacked;
	}

	public boolean isyAxisEquidistantDates() {
		return yAxisEquidistantDates;
	}

	public void setyAxisEquidistantDates(boolean yAxisEquidistantDates) {
		this.yAxisEquidistantDates = yAxisEquidistantDates;
	}

	public boolean isyGrid() {
		return yGrid;
	}

	public void setyGrid(boolean yGrid) {
		this.yGrid = yGrid;
	}

	public String getyGridColor() {
		return yGridColor;
	}

	public void setyGridColor(String yGridColor) {
		this.yGridColor = yGridColor;
	}

	public String getyGridLineStyle() {
		return yGridLineStyle;
	}

	public void setyGridLineStyle(String yGridLineStyle) {
		this.yGridLineStyle = yGridLineStyle;
	}
	
	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(String lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}
	
	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContentDescriptor() {
		return contentDescriptor;
	}

	public void setContentDescriptor(String contentDescriptor) {
		this.contentDescriptor = contentDescriptor;
	}

	public boolean isMonthlyAggregated() {
		return monthlyAggregated;
	}

	public void setMonthlyAggregated(boolean monthlyAggregated) {
		this.monthlyAggregated = monthlyAggregated;
	}

	public boolean isYearlyAggregated() {
		return yearlyAggregated;
	}

	public void setYearlyAggregated(boolean yearlyAggregated) {
		this.yearlyAggregated = yearlyAggregated;
	}

	public boolean isFromDateToDate() {
		return fromDateToDate;
	}

	public void setFromDateToDate(boolean fromDateToDate) {
		this.fromDateToDate = fromDateToDate;
	}

	public boolean isMostRecentData() {
		return mostRecentData;
	}

	public void setMostRecentData(boolean mostRecentData) {
		this.mostRecentData = mostRecentData;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getLatestYears() {
		return latestYears;
	}

	public void setLatestYears(int latestYears) {
		this.latestYears = latestYears;
	}

	public int getLatestMonths() {
		return latestMonths;
	}

	public void setLatestMonths(int latestMonths) {
		this.latestMonths = latestMonths;
	}

	public int getLatestDays() {
		return latestDays;
	}

	public void setLatestDays(int latestDays) {
		this.latestDays = latestDays;
	}

	public double getyMin() {
		return yMin;
	}

	public void setyMin(double yMin) {
		this.yMin = yMin;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	public String getAxisTitle() {
		return axisTitle;
	}

	public void setAxisTitle(String axisTitle) {
		this.axisTitle = axisTitle;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getAxisColor() {
		return axisColor;
	}

	public void setAxisColor(String axisColor) {
		this.axisColor = axisColor;
	}

	public String getTicksColor() {
		return ticksColor;
	}

	public void setTicksColor(String ticksColor) {
		this.ticksColor = ticksColor;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public String getLabelOrientation() {
		return labelOrientation;
	}

	public void setLabelOrientation(String labelOrientation) {
		this.labelOrientation = labelOrientation;
	}

	public String getAxisPosition() {
		return axisPosition;
	}

	public void setAxisPosition(String axisPosition) {
		this.axisPosition = axisPosition;
	}

	public Map<String, String> getyCodes() {
		return yCodes;
	}

	public void setyCodes(Map<String, String> yCodes) {
		this.yCodes = yCodes;
	}

	public Map<String, String> getSeriesColorsMap() {
		return seriesColorsMap;
	}

	public void setSeriesColorsMap(Map<String, String> seriesColorsMap) {
		this.seriesColorsMap = seriesColorsMap;
	}

	public String getValuesFontStyle() {
		return valuesFontStyle;
	}

	public void setValuesFontStyle(String valuesFontStyle) {
		this.valuesFontStyle = valuesFontStyle;
	}

	public String getValuesFontSize() {
		return valuesFontSize;
	}

	public void setValuesFontSize(String valuesFontSize) {
		this.valuesFontSize = valuesFontSize;
	}

	public String getValuesFontColor() {
		return valuesFontColor;
	}

	public void setValuesFontColor(String valuesFontColor) {
		this.valuesFontColor = valuesFontColor;
	}

	public Map<String, String> getHeaderYCodeMap() {
		return headerYCodeMap;
	}

	public void setHeaderYCodeMap(Map<String, String> headerYCodeMap) {
		this.headerYCodeMap = headerYCodeMap;
	}
	
}