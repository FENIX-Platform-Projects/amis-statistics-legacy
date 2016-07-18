package org.fao.fenix.web.modules.birt.common.vo;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartWizardBean implements IsSerializable {

	private String aggregation;
	private String background;
	private String width;
	private String height;
	private String chartType;
	private String dim2_3D;
	private String disposition;
	private String title;
	private String xAxisTitle;
	private boolean xAxisShowLabels;
	private String rotateXLabels;
	private String yAxisTitle;
	private boolean yAxisShowLabels;
	private DateVo mostRecent;
	private boolean isMostRecent;
	private boolean isXdate;
	
	// title format
	private Boolean titleIsBold = true;
	private Boolean titleIsItalic = false;
	private Boolean titleIsUnderline = false;
	
	
	
	/**
	 * 0 - Name DS 1 - ID DS
	 */
	private List<List<String>> datasetId;
	private String dimensionX;
	private Map<String, String> dimensionY;
	private List<List<String>> dimensionValuesX;
	private Map<String, List<List<String>>> dimensionValuesY;
	private Map<String, List<List<String>>> dimensionValuesYBar;
	private Map<String, List<List<String>>> dimensionValuesYLine;
	private Map<String, String> scalesMap;
	private boolean doubleScale;
	private String ySecondAxisTitle;
	// private List<String> firstYAxis;
	// private String scaleFirstY;
	// private List<String> secondYAxis;
	// private String scaleSecondY;
	private Map<String, List<List<String>>> otherDimension;
	private String posLegend;
	private boolean showLegend;
	private boolean flip;
	
	private ChartBean chartReportBean;

	public ChartWizardBean() {

	}

	public ChartWizardBean(String chartType, String title,
			List<List<String>> datasetId, String dimensionX,
			Map<String, String> dimensionY,
			List<List<String>> dimensionValuesX,
			Map<String, List<List<String>>> dimensionValuesY) {
		this.setChartType(chartType);
		this.setDatasetId(datasetId);
		this.setDimensionX(dimensionX);
		this.setDimensionY(dimensionY);
		this.setDimensionValuesX(dimensionValuesX);
		this.setDimensionValuesY(dimensionValuesY);
		this.setTitle(title);
	}
	
	public ChartWizardBean(String chartType, String title, ChartBean chartReportBean) { 
		this.setChartType(chartType);
		this.setTitle(title);
		this.setChartReportBean(chartReportBean);
	}

	public String getAggregation() {
		return aggregation;
	}

	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}

	public String getYSecondAxisTitle() {
		return ySecondAxisTitle;
	}

	public void setYSecondAxisTitle(String secondAxisTitle) {
		ySecondAxisTitle = secondAxisTitle;
	}

	public boolean isDoubleScale() {
		return doubleScale;
	}

	public void setDoubleScale(boolean doubleScale) {
		this.doubleScale = doubleScale;
	}

	public Map<String, String> getScalesMap() {
		return scalesMap;
	}

	public void setScalesMap(Map<String, String> scalesMap) {
		this.scalesMap = scalesMap;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public boolean isShowLegend() {
		return showLegend;
	}

	public void setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
	}

	public String getRotateXLabels() {
		return rotateXLabels;
	}

	public void setRotateXLabels(String rotateXLabels) {
		this.rotateXLabels = rotateXLabels;
	}

	public String getXAxisTitle() {
		return xAxisTitle;
	}

	public void setXAxisTitle(String axisTitle) {
		xAxisTitle = axisTitle;
	}

	public boolean isXAxisShowLabels() {
		return xAxisShowLabels;
	}

	public void setXAxisShowLabels(boolean axisShowLabels) {
		xAxisShowLabels = axisShowLabels;
	}

	public String getYAxisTitle() {
		return yAxisTitle;
	}

	public void setYAxisTitle(String axisTitle) {
		yAxisTitle = axisTitle;
	}

	public boolean isYAxisShowLabels() {
		return yAxisShowLabels;
	}

	public void setYAxisShowLabels(boolean axisShowLabels) {
		yAxisShowLabels = axisShowLabels;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getPosLegend() {
		return posLegend;
	}

	public void setPosLegend(String posLegend) {
		this.posLegend = posLegend;
	}

	public boolean isFlip() {
		return flip;
	}

	public void setFlip(boolean flip) {
		this.flip = flip;
	}

	public Map<String, List<List<String>>> getDimensionValuesYBar() {
		return dimensionValuesYBar;
	}

	public void setDimensionValuesYBar(
			Map<String, List<List<String>>> dimensionValuesYBar) {
		this.dimensionValuesYBar = dimensionValuesYBar;
	}

	public Map<String, List<List<String>>> getDimensionValuesYLine() {
		return dimensionValuesYLine;
	}

	public void setDimensionValuesYLine(
			Map<String, List<List<String>>> dimensionValuesYLine) {
		this.dimensionValuesYLine = dimensionValuesYLine;
	}

	public Map<String, List<List<String>>> getOtherDimension() {
		return otherDimension;
	}

	public void setOtherDimension(Map<String, List<List<String>>> otherDimension) {
		this.otherDimension = otherDimension;
	}

	public String getDim2_3D() {
		return dim2_3D;
	}

	public void setDim2_3D(String dim2_3D) {
		this.dim2_3D = dim2_3D;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<List<String>> getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(List<List<String>> datasetId) {
		this.datasetId = datasetId;
	}

	public List<List<String>> getDimensionValuesX() {
		return dimensionValuesX;
	}

	public void setDimensionValuesX(List<List<String>> dimensionValuesX) {
		this.dimensionValuesX = dimensionValuesX;
	}

	public Map<String, List<List<String>>> getDimensionValuesY() {
		return dimensionValuesY;
	}

	public void setDimensionValuesY(
			Map<String, List<List<String>>> dimensionValuesY) {
		this.dimensionValuesY = dimensionValuesY;
	}

	public String getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(String dimensionX) {
		this.dimensionX = dimensionX;
	}

	public Map<String, String> getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(Map<String, String> dimensionY) {
		this.dimensionY = dimensionY;
	}

	public DateVo getMostRecent() {
		return mostRecent;
	}

	public void setMostRecent(DateVo mostRecent) {
		this.mostRecent = mostRecent;
	}

	public boolean isMostRecent() {
		return isMostRecent;
	}

	public void setMostRecent(boolean isMostRecent) {
		this.isMostRecent = isMostRecent;
	}

	public boolean isXdate() {
		return isXdate;
	}

	public void setXdate(boolean isXdate) {
		this.isXdate = isXdate;
	}

	public ChartBean getChartReportBean() {
		return chartReportBean;
	}

	public void setChartReportBean(ChartBean chartReportBean) {
		this.chartReportBean = chartReportBean;
	}

	public Boolean getTitleIsBold() {
		return titleIsBold;
	}

	public void setTitleIsBold(Boolean titleIsBold) {
		this.titleIsBold = titleIsBold;
	}

	public Boolean getTitleIsItalic() {
		return titleIsItalic;
	}

	public void setTitleIsItalic(Boolean titleIsItalic) {
		this.titleIsItalic = titleIsItalic;
	}

	public Boolean getTitleIsUnderline() {
		return titleIsUnderline;
	}

	public void setTitleIsUnderline(Boolean titleIsUnderline) {
		this.titleIsUnderline = titleIsUnderline;
	}
	
	

}
