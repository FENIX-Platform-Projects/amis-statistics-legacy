package org.fao.fenix.web.modules.chartdesigner.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartDesignerParametersVO implements IsSerializable {

	private List<Long> datasourceIDs = new ArrayList<Long>();

	private List<ChartDesignerFilterVO> filters = new ArrayList<ChartDesignerFilterVO>();
	
	private List<ChartDesignerYAxisParametersVO> yAxes = new ArrayList<ChartDesignerYAxisParametersVO>();
	
	private List<ChartDesignerHighlightVO> highlights = new ArrayList<ChartDesignerHighlightVO>();

	private String chartType = "LINE";

	private String aggregationFunction = "AVG";

	private Map<Long, String> plotDimensionMap = new HashMap<Long, String>();

	private Map<String, String> seriesColorMap = new HashMap<String, String>();

	private boolean xMonthlyAggregated = false;

	private boolean xYearlyAggregated = false;

	private boolean xUseFromDateToDate = false;

	private Date xFromDate = new Date();

	private Date xToDate = new Date();

	private boolean xUseMostRecentData = false;

	private int xLatestYears = 0;

	private int xLatestMonths = 0;

	private int xLatestDays = 0;

	private String xLabel = "";

	private String xColor = "#000000";

	private String xFontFamily = "1";

	private String xSize = "1";
	
	private String xValuesColor = "#000000";

	private String xValuesFontFamily = "1";

	private String xValuesSize = "1";

	private int xOrientation = 45;

	private boolean xGrid;

	private List<String> xContentDescriptors = new ArrayList<String>();

	private List<String> xCodes = new ArrayList<String>();
	
	private List<String> xLabels = new ArrayList<String>();

	private String titleLabel = "";

	private String titleColor = "#000000";

	private String titleFontFamily = "1";

	private String titleSize = "1";
	
	private String subTitleLabel = "";
	
	private String subTitleColor = "#000000";
	
	private String subTitleFontFamily = "1";
	
	private String subTitleSize = "1";

	private String legendLabel = "";

	private String legendColor = "#000000";

	private String legendFontFamily = "1";

	private String legendSize = "1";
	
	private int imageWidth = 640;
	
	private int imageHeight = 315;
	
	private String imagePath = "";
	
	private String xGridColor = "#000000";
	
	private String xGridLineStyle = "1";
	
	private String legendPosition = "bottomright";
	
	private int xAxisNumberOfIntervals = 10;
	
	private String xAxisColor = "#000000";
	
	private String xAxisTicksColor = "#000000";
	
	private String subTitle = "";
	
	private boolean showLegend = true;
	
	private boolean showBorder = true;
	
	private double xLimFactor = 1.0;
	
	private double labelsDistanceFromAxis = 3.0;
	
	private boolean legendHorizontal = true;
	
	private boolean xAxisEquidistantDates = true;
	
	private String datesFormat = "YYYY-MM-DD";
	
	public String getDatesFormat() {
		return datesFormat;
	}

	public void setDatesFormat(String datesFormat) {
		this.datesFormat = datesFormat;
	}
		
	public boolean isxAxisEquidistantDates() {
		return xAxisEquidistantDates;
	}

	public void setxAxisEquidistantDates(boolean xAxisEquidistantDates) {
		this.xAxisEquidistantDates = xAxisEquidistantDates;
	}

	public double getLabelsDistanceFromAxis() {
		return labelsDistanceFromAxis;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setLabelsDistanceFromAxis(double labelsDistanceFromAxis) {
		this.labelsDistanceFromAxis = labelsDistanceFromAxis;
	}

	public boolean isLegendHorizontal() {
		return legendHorizontal;
	}

	public void setLegendHorizontal(boolean legendHorizontal) {
		this.legendHorizontal = legendHorizontal;
	}
	
	public void addYAxis(ChartDesignerYAxisParametersVO vo) {
		if (this.yAxes == null)
			this.yAxes = new ArrayList<ChartDesignerYAxisParametersVO>();
		this.yAxes.add(vo);
	}
	
	public void addHighlight(ChartDesignerHighlightVO vo) {
		if (this.highlights == null)
			this.highlights = new ArrayList<ChartDesignerHighlightVO>();
		this.highlights.add(vo);
	}
	
	public void addFilter(ChartDesignerFilterVO vo) {
		if (this.filters == null)
			this.filters = new ArrayList<ChartDesignerFilterVO>();
		this.filters.add(vo);
	}

	public String getSubTitleLabel() {
		return subTitleLabel;
	}

	public void setSubTitleLabel(String subTitleLabel) {
		this.subTitleLabel = subTitleLabel;
	}

	public String getSubTitleColor() {
		return subTitleColor;
	}

	public void setSubTitleColor(String subTitleColor) {
		this.subTitleColor = subTitleColor;
	}

	public String getSubTitleFontFamily() {
		return subTitleFontFamily;
	}

	public void setSubTitleFontFamily(String subTitleFontFamily) {
		this.subTitleFontFamily = subTitleFontFamily;
	}

	public String getSubTitleSize() {
		return subTitleSize;
	}

	public void setSubTitleSize(String subTitleSize) {
		this.subTitleSize = subTitleSize;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public List<ChartDesignerYAxisParametersVO> getyAxes() {
		return yAxes;
	}

	public void setyAxes(List<ChartDesignerYAxisParametersVO> yAxes) {
		this.yAxes = yAxes;
	}

	public List<ChartDesignerHighlightVO> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<ChartDesignerHighlightVO> highlights) {
		this.highlights = highlights;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public boolean isShowLegend() {
		return showLegend;
	}

	public void setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
	}
	
	
	

	public boolean isShowBorder() {
		return showBorder;
	}

	public void setShowBorder(boolean showBorder) {
		this.showBorder = showBorder;
	}

	public double getxLimFactor() {
		return xLimFactor;
	}

	public void setxLimFactor(double xLimFactor) {
		this.xLimFactor = xLimFactor;
	}

	public String getxAxisTicksColor() {
		return xAxisTicksColor;
	}

	public void setxAxisTicksColor(String xAxisTicksColor) {
		this.xAxisTicksColor = xAxisTicksColor;
	}

	public String getxAxisColor() {
		return xAxisColor;
	}

	public void setxAxisColor(String xAxisColor) {
		this.xAxisColor = xAxisColor;
	}

	public int getxAxisNumberOfIntervals() {
		return xAxisNumberOfIntervals;
	}

	public void setxAxisNumberOfIntervals(int xAxisNumberOfIntervals) {
		this.xAxisNumberOfIntervals = xAxisNumberOfIntervals;
	}

	public String getLegendPosition() {
		return legendPosition;
	}

	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public List<Long> getDatasourceIDs() {
		return datasourceIDs;
	}

	public void setDatasourceIDs(List<Long> datasourceIDs) {
		this.datasourceIDs = datasourceIDs;
	}

	public List<ChartDesignerFilterVO> getFilters() {
		return filters;
	}

	public void setFilters(List<ChartDesignerFilterVO> filters) {
		this.filters = filters;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getAggregationFunction() {
		return aggregationFunction;
	}

	public void setAggregationFunction(String aggregationFunction) {
		this.aggregationFunction = aggregationFunction;
	}

	public Map<Long, String> getPlotDimensionMap() {
		return plotDimensionMap;
	}

	public void setPlotDimensionMap(Map<Long, String> plotDimensionMap) {
		this.plotDimensionMap = plotDimensionMap;
	}

	public Map<String, String> getSeriesColorMap() {
		return seriesColorMap;
	}

	public void setSeriesColorMap(Map<String, String> seriesColorMap) {
		this.seriesColorMap = seriesColorMap;
	}

	public boolean isxMonthlyAggregated() {
		return xMonthlyAggregated;
	}

	public void setxMonthlyAggregated(boolean xMonthlyAggregated) {
		this.xMonthlyAggregated = xMonthlyAggregated;
	}

	public boolean isxYearlyAggregated() {
		return xYearlyAggregated;
	}

	public void setxYearlyAggregated(boolean xYearlyAggregated) {
		this.xYearlyAggregated = xYearlyAggregated;
	}

	public boolean isxUseFromDateToDate() {
		return xUseFromDateToDate;
	}

	public void setxUseFromDateToDate(boolean xUseFromDateToDate) {
		this.xUseFromDateToDate = xUseFromDateToDate;
	}

	public Date getxFromDate() {
		return xFromDate;
	}

	public void setxFromDate(Date xFromDate) {
		this.xFromDate = xFromDate;
	}

	public Date getxToDate() {
		return xToDate;
	}

	public void setxToDate(Date xToDate) {
		this.xToDate = xToDate;
	}

	public boolean isxUseMostRecentData() {
		return xUseMostRecentData;
	}

	public void setxUseMostRecentData(boolean xUseMostRecentData) {
		this.xUseMostRecentData = xUseMostRecentData;
	}

	public int getxLatestYears() {
		return xLatestYears;
	}

	public void setxLatestYears(int xLatestYears) {
		this.xLatestYears = xLatestYears;
	}

	public int getxLatestMonths() {
		return xLatestMonths;
	}

	public void setxLatestMonths(int xLatestMonths) {
		this.xLatestMonths = xLatestMonths;
	}

	public int getxLatestDays() {
		return xLatestDays;
	}

	public void setxLatestDays(int xLatestDays) {
		this.xLatestDays = xLatestDays;
	}

	public String getxLabel() {
		return xLabel;
	}

	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public String getxColor() {
		return xColor;
	}

	public void setxColor(String xColor) {
		this.xColor = xColor;
	}

	public String getxFontFamily() {
		return xFontFamily;
	}

	public void setxFontFamily(String xFontFamily) {
		this.xFontFamily = xFontFamily;
	}

	public String getxSize() {
		return xSize;
	}

	public void setxSize(String xSize) {
		this.xSize = xSize;
	}

	public int getxOrientation() {
		return xOrientation;
	}

	public void setxOrientation(int xOrientation) {
		this.xOrientation = xOrientation;
	}

	public boolean isxGrid() {
		return xGrid;
	}

	public void setxGrid(boolean xGrid) {
		this.xGrid = xGrid;
	}

	public List<String> getxContentDescriptors() {
		return xContentDescriptors;
	}

	public void setxContentDescriptors(List<String> xContentDescriptors) {
		this.xContentDescriptors = xContentDescriptors;
	}

	public List<String> getxCodes() {
		return xCodes;
	}

	public void setxCodes(List<String> xCodes) {
		this.xCodes = xCodes;
	}

	public String getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getTitleFontFamily() {
		return titleFontFamily;
	}

	public void setTitleFontFamily(String titleFontFamily) {
		this.titleFontFamily = titleFontFamily;
	}

	public String getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(String titleSize) {
		this.titleSize = titleSize;
	}

	public String getLegendLabel() {
		return legendLabel;
	}

	public void setLegendLabel(String legendLabel) {
		this.legendLabel = legendLabel;
	}

	public String getLegendColor() {
		return legendColor;
	}

	public void setLegendColor(String legendColor) {
		this.legendColor = legendColor;
	}

	public String getLegendFontFamily() {
		return legendFontFamily;
	}

	public void setLegendFontFamily(String legendFontFamily) {
		this.legendFontFamily = legendFontFamily;
	}

	public String getLegendSize() {
		return legendSize;
	}

	public void setLegendSize(String legendSize) {
		this.legendSize = legendSize;
	}

	public List<String> getxLabels() {
		return xLabels;
	}

	public void setxLabels(List<String> xLabels) {
		this.xLabels = xLabels;
	}

	public String getxValuesColor() {
		return xValuesColor;
	}

	public void setxValuesColor(String xValuesColor) {
		this.xValuesColor = xValuesColor;
	}

	public String getxValuesFontFamily() {
		return xValuesFontFamily;
	}

	public void setxValuesFontFamily(String xValuesFontFamily) {
		this.xValuesFontFamily = xValuesFontFamily;
	}

	public String getxValuesSize() {
		return xValuesSize;
	}

	public void setxValuesSize(String xValuesSize) {
		this.xValuesSize = xValuesSize;
	}

	public String getxGridColor() {
		return xGridColor;
	}

	public void setxGridColor(String xGridColor) {
		this.xGridColor = xGridColor;
	}

	public String getxGridLineStyle() {
		return xGridLineStyle;
	}

	public void setxGridLineStyle(String xGridLineStyle) {
		this.xGridLineStyle = xGridLineStyle;
	}

}