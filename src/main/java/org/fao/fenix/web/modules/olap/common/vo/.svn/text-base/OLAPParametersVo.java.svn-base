package org.fao.fenix.web.modules.olap.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OLAPParametersVo implements IsSerializable {

	private Long dataSourceId;

	private String dataSourceType = "";

	private String dataSourceTitle = "";

	private List<String> chartsTitles = new ArrayList<String>();

	private Map<String, String> xLabels = new LinkedHashMap<String, String>();

	private Map<String, String> yLabels = new HashMap<String, String>();

	private Map<String, String> zLabels = new HashMap<String, String>();

	private Map<String, String> wLabels = new HashMap<String, String>();

	private Boolean showGridLine=true;

	private Boolean showShading=true;

	private Boolean showBorder=true;

	private Boolean showColSubject=true;

	private Boolean showSubColSubject=true;
	
	private Boolean showRowSubject=true;

	private Boolean showSubRowSubject=true;
	
	private Boolean showSubRowSummary=true;
	
	private Boolean showSubColSummary=true;

	private String x = "";

	private String y = "";

	private String z = "";

	private String w = "";

	private String xLabel = "";

	private String yLabel = "";

	private String zLabel = "";

	private String wLabel = "";

	private String value = "";
	
	private String xLabelShowTitle = "";

	private String yLabelShowTitle  = "";

	private String zLabelShowTitle  = "";

	private String wLabelShowTitle  = "";

	private String valueLabel = "";

	private String function = "";
	
	private String colFunction = "";
	
	private String rowFunction = "";
	
	private String subRowFunction = "";
	
	private String subColFunction = "";
	
	private String shading1Color;
	
	private String shading2Color;

	private List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();

	private boolean showSingleValues = true;

	private String chartType = "";

	private int decimals = 0;

	private Boolean showBaseLayer = false;

	private Boolean useFlashCharts = false;

	private String reportOrientation = "";

	private int mapClasses = 10;

	private String minColor = "";

	private String maxColor = "";

	/** Map<columnHeader, Map<code, uniqueValueID>> */
	private Map<String, Map<String, String>> xUniqueValuesMap = new HashMap<String, Map<String, String>>();

	/** Map<columnHeader, Map<code, uniqueValueID>> */
	private Map<String, Map<String, String>> zUniqueValuesMap = new HashMap<String, Map<String, String>>();

	/** Map<columnHeader, Map<code, uniqueValueID>> */
	private Map<String, Map<String, String>> yUniqueValuesMap = new HashMap<String, Map<String, String>>();

	/** Map<columnHeader, Map<code, uniqueValueID>> */
	private Map<String, Map<String, String>> wUniqueValuesMap = new HashMap<String, Map<String, String>>();

	private Map<String, String> xCodes = new HashMap<String, String>();

	private String olapHtml = "";

	private boolean aggregateXAsMonthly = false;

	private boolean aggregateZAsMonthly = false;

	private boolean aggregateYAsMonthly = false;

	private boolean aggregateWAsMonthly = false;

	private boolean aggregateXAsYearly = false;

	private boolean aggregateZAsYearly = false;

	private boolean aggregateYAsYearly = false;

	private boolean aggregateWAsYearly = false;

	private Long resourceViewID;

	private TreeSet<String> sortedXLabels = new TreeSet<String>();

	private TreeSet<String> sortedZLabels = new TreeSet<String>();

	private TreeSet<String> sortedWLabels = new TreeSet<String>();

	private TreeSet<String> sortedYLabels = new TreeSet<String>();

	private Date xFromDate = new Date();

	private Date xToDate = new Date();

	private Date xMostRecentDataStartDate = new Date();

	private boolean xUseFromDateToDate = false;

	private boolean xUseMostRecentData = false;

	private Date zFromDate = new Date();

	private Date zToDate = new Date();

	private Date zMostRecentDataStartDate = new Date();

	private boolean zUseFromDateToDate = false;

	private boolean zUseMostRecentData = false;

	private Date yFromDate = new Date();

	private Date yToDate = new Date();

	private Date yMostRecentDataStartDate = new Date();

	private boolean yUseFromDateToDate = false;

	private boolean yUseMostRecentData = false;

	private Date wFromDate = new Date();

	private Date wToDate = new Date();

	private Date wMostRecentDataStartDate = new Date();

	private boolean wUseFromDateToDate = false;

	private boolean wUseMostRecentData = false;

	private String colHeaderColor;

	private String subColHeaderColor;

	private String rowHeaderColor;

	private String subRowHeaderColor;

	private String contentBackgroundColor;

	private String colFontColor;

	private String subColFontColor;

	private String rowFontColor;

	private String subRowFontColor;

	private String contentFontColor;

	private String fontFamily;

	private String fontSize;

	private boolean showTotals = false;

	private Boolean showRowSummary = false;

	private Boolean showColSummary = false;

	private boolean showRowVariation = false;

	private boolean showColVariation = false;

	private String summaryBackgroundColor;

	private String summaryFontColor;

	private String olapTitleFontColor;

	private String olapTitle;

	private String notesFontColor;

	private String notesTitle;

	private int xLatestYears;

	private int xLatestMonths;

	private int xLatestDays;

	private int zLatestYears;

	private int zLatestMonths;

	private int zLatestDays;

	private int wLatestYears;

	private int wLatestMonths;

	private int wLatestDays;

	private int yLatestYears;

	private int yLatestMonths;

	private int yLatestDays;

	private boolean showXLabel = true;

	private boolean showYLabel = true;

	private boolean showZLabel = true;
	
	private boolean showWLabel = true;
	
	

	private String zHeader;

	private String aggregatedTableViewName;

	private List<String> aggregateSelectedXDimensions;
	private List<String> aggregateSelectedYDimensions;
	private List<String> aggregateSelectedZDimensions;

	private boolean sortXLabels = true;

	private String functionWidth = "75px";

	private String functionHeight = "25px";

	private String columnLabelWidth = "125px";

	private Map<String, String> columnLabelsWidths = new HashMap<String, String>();

	private Map<String, String> rowLabelsHeights = new HashMap<String, String>();

	private String subRowLabelWidth = "75px";

	private String periodTypeCode = "daily";

	private String datesFormat = "YYYY-MM-DD";

	private String variationThreshold = "5";

	private Boolean showVariationArrows = true;

	private int decimalsForTotals = 0;

	private int decimalsForVariation = 0;

	private String variationThresholdYellow = "5";

	private String variationThresholdRed = "10";

	public String getVariationThresholdYellow() {
		return variationThresholdYellow;
	}

	public void setVariationThresholdYellow(String variationThresholdYellow) {
		this.variationThresholdYellow = variationThresholdYellow;
	}

	public String getVariationThresholdRed() {
		return variationThresholdRed;
	}

	public void setVariationThresholdRed(String variationThresholdRed) {
		this.variationThresholdRed = variationThresholdRed;
	}

	public int getDecimalsForTotals() {
		return decimalsForTotals;
	}

	public void setDecimalsForTotals(int decimalsForTotals) {
		this.decimalsForTotals = decimalsForTotals;
	}

	public int getDecimalsForVariation() {
		return decimalsForVariation;
	}

	public void setDecimalsForVariation(int decimalsForVariation) {
		this.decimalsForVariation = decimalsForVariation;
	}

	public Boolean getShowVariationArrows() {
		return showVariationArrows;
	}

	public void setShowVariationArrows(Boolean showVariationArrows) {
		this.showVariationArrows = showVariationArrows;
	}

	public String getVariationThreshold() {
		return variationThreshold;
	}

	public void setVariationThreshold(String variationThreshold) {
		this.variationThreshold = variationThreshold;
	}

	public String getDatesFormat() {
		return datesFormat;
	}

	public void setDatesFormat(String datesFormat) {
		this.datesFormat = datesFormat;
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
	}

	public String getSubRowLabelWidth() {
		return subRowLabelWidth;
	}

	public void setSubRowLabelWidth(String subRowWidth) {
		this.subRowLabelWidth = subRowWidth;
	}

	public String getzHeader() {
		return zHeader;
	}

	public void setzHeader(String zHeader) {
		this.zHeader = zHeader;
	}

	public String getFunctionWidth() {
		return functionWidth;
	}

	public void setFunctionWidth(String functionWidth) {
		this.functionWidth = functionWidth;
	}

	public String getFunctionHeight() {
		return functionHeight;
	}

	public void setFunctionHeight(String functionHeight) {
		this.functionHeight = functionHeight;
	}

	public String getColumnLabelWidth() {
		return columnLabelWidth;
	}

	public void setColumnLabelWidth(String columnLabelWidth) {
		this.columnLabelWidth = columnLabelWidth;
	}

	public Map<String, String> getColumnLabelsWidths() {
		return columnLabelsWidths;
	}

	public void setColumnLabelsWidths(Map<String, String> columnLabelsWidths) {
		this.columnLabelsWidths = columnLabelsWidths;
	}

	public Map<String, String> getRowLabelsHeights() {
		return rowLabelsHeights;
	}

	public void setRowLabelsHeights(Map<String, String> rowLabelsHeights) {
		this.rowLabelsHeights = rowLabelsHeights;
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

	public int getzLatestYears() {
		return zLatestYears;
	}

	public void setzLatestYears(int zLatestYears) {
		this.zLatestYears = zLatestYears;
	}

	public int getzLatestMonths() {
		return zLatestMonths;
	}

	public void setzLatestMonths(int zLatestMonths) {
		this.zLatestMonths = zLatestMonths;
	}

	public int getzLatestDays() {
		return zLatestDays;
	}

	public void setzLatestDays(int zLatestDays) {
		this.zLatestDays = zLatestDays;
	}

	public int getwLatestYears() {
		return wLatestYears;
	}

	public void setwLatestYears(int wLatestYears) {
		this.wLatestYears = wLatestYears;
	}

	public int getwLatestMonths() {
		return wLatestMonths;
	}

	public void setwLatestMonths(int wLatestMonths) {
		this.wLatestMonths = wLatestMonths;
	}

	public int getwLatestDays() {
		return wLatestDays;
	}

	public void setwLatestDays(int wLatestDays) {
		this.wLatestDays = wLatestDays;
	}

	public int getyLatestYears() {
		return yLatestYears;
	}

	public void setyLatestYears(int yLatestYears) {
		this.yLatestYears = yLatestYears;
	}

	public int getyLatestMonths() {
		return yLatestMonths;
	}

	public void setyLatestMonths(int yLatestMonths) {
		this.yLatestMonths = yLatestMonths;
	}

	public int getyLatestDays() {
		return yLatestDays;
	}

	public void setyLatestDays(int yLatestDays) {
		this.yLatestDays = yLatestDays;
	}

	public String getSummaryBackgroundColor() {
		return summaryBackgroundColor;
	}

	public void setSummaryBackgroundColor(String summaryBackgroundColor) {
		this.summaryBackgroundColor = summaryBackgroundColor;
	}

	public String getSummaryFontColor() {
		return summaryFontColor;
	}

	public void setSummaryFontColor(String summaryFontColor) {
		this.summaryFontColor = summaryFontColor;
	}

	public boolean isShowTotals() {
		return showTotals;
	}

	public void setShowTotals(boolean showTotals) {
		this.showTotals = showTotals;
	}

	public String getColHeaderColor() {
		return colHeaderColor;
	}

	public void setColHeaderColor(String colHeaderColor) {
		this.colHeaderColor = colHeaderColor;
	}

	public String getSubColHeaderColor() {
		return subColHeaderColor;
	}

	public void setSubColHeaderColor(String subColHeaderColor) {
		this.subColHeaderColor = subColHeaderColor;
	}

	public String getRowHeaderColor() {
		return rowHeaderColor;
	}

	public void setRowHeaderColor(String rowHeaderColor) {
		this.rowHeaderColor = rowHeaderColor;
	}

	public String getSubRowHeaderColor() {
		return subRowHeaderColor;
	}

	public void setSubRowHeaderColor(String subRowHeaderColor) {
		this.subRowHeaderColor = subRowHeaderColor;
	}

	public String getContentBackgroundColor() {
		return contentBackgroundColor;
	}

	public void setContentBackgroundColor(String contentBackgroundColor) {
		this.contentBackgroundColor = contentBackgroundColor;
	}

	public String getColFontColor() {
		return colFontColor;
	}

	public void setColFontColor(String colFontColor) {
		this.colFontColor = colFontColor;
	}

	public String getSubColFontColor() {
		return subColFontColor;
	}

	public void setSubColFontColor(String subColFontColor) {
		this.subColFontColor = subColFontColor;
	}

	public String getRowFontColor() {
		return rowFontColor;
	}

	public void setRowFontColor(String rowFontColor) {
		this.rowFontColor = rowFontColor;
	}

	public String getSubRowFontColor() {
		return subRowFontColor;
	}

	public void setSubRowFontColor(String subRowFontColor) {
		this.subRowFontColor = subRowFontColor;
	}

	public String getContentFontColor() {
		return contentFontColor;
	}

	public void setContentFontColor(String contentFontColor) {
		this.contentFontColor = contentFontColor;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public Long getResourceViewID() {
		return resourceViewID;
	}

	public void setResourceViewID(Long resourceViewID) {
		this.resourceViewID = resourceViewID;
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

	public Date getxMostRecentDataStartDate() {
		return xMostRecentDataStartDate;
	}

	public void setxMostRecentDataStartDate(Date xMostRecentDataStartDate) {
		this.xMostRecentDataStartDate = xMostRecentDataStartDate;
	}

	public boolean isxUseFromDateToDate() {
		return xUseFromDateToDate;
	}

	public void setxUseFromDateToDate(boolean xUseFromDateToDate) {
		this.xUseFromDateToDate = xUseFromDateToDate;
	}

	public boolean isxUseMostRecentData() {
		return xUseMostRecentData;
	}

	public void setxUseMostRecentData(boolean xUseMostRecentData) {
		this.xUseMostRecentData = xUseMostRecentData;
	}

	public Date getzFromDate() {
		return zFromDate;
	}

	public void setzFromDate(Date zFromDate) {
		this.zFromDate = zFromDate;
	}

	public Date getzToDate() {
		return zToDate;
	}

	public void setzToDate(Date zToDate) {
		this.zToDate = zToDate;
	}

	public Date getzMostRecentDataStartDate() {
		return zMostRecentDataStartDate;
	}

	public void setzMostRecentDataStartDate(Date zMostRecentDataStartDate) {
		this.zMostRecentDataStartDate = zMostRecentDataStartDate;
	}

	public boolean iszUseFromDateToDate() {
		return zUseFromDateToDate;
	}

	public void setzUseFromDateToDate(boolean zUseFromDateToDate) {
		this.zUseFromDateToDate = zUseFromDateToDate;
	}

	public boolean iszUseMostRecentData() {
		return zUseMostRecentData;
	}

	public void setzUseMostRecentData(boolean zUseMostRecentData) {
		this.zUseMostRecentData = zUseMostRecentData;
	}

	public Date getyFromDate() {
		return yFromDate;
	}

	public void setyFromDate(Date yFromDate) {
		this.yFromDate = yFromDate;
	}

	public Date getyToDate() {
		return yToDate;
	}

	public void setyToDate(Date yToDate) {
		this.yToDate = yToDate;
	}

	public Date getyMostRecentDataStartDate() {
		return yMostRecentDataStartDate;
	}

	public void setyMostRecentDataStartDate(Date yMostRecentDataStartDate) {
		this.yMostRecentDataStartDate = yMostRecentDataStartDate;
	}

	public boolean isyUseFromDateToDate() {
		return yUseFromDateToDate;
	}

	public void setyUseFromDateToDate(boolean yUseFromDateToDate) {
		this.yUseFromDateToDate = yUseFromDateToDate;
	}

	public boolean isyUseMostRecentData() {
		return yUseMostRecentData;
	}

	public void setyUseMostRecentData(boolean yUseMostRecentData) {
		this.yUseMostRecentData = yUseMostRecentData;
	}

	public Date getwFromDate() {
		return wFromDate;
	}

	public void setwFromDate(Date wFromDate) {
		this.wFromDate = wFromDate;
	}

	public Date getwToDate() {
		return wToDate;
	}

	public void setwToDate(Date wToDate) {
		this.wToDate = wToDate;
	}

	public Date getwMostRecentDataStartDate() {
		return wMostRecentDataStartDate;
	}

	public void setwMostRecentDataStartDate(Date wMostRecentDataStartDate) {
		this.wMostRecentDataStartDate = wMostRecentDataStartDate;
	}

	public boolean iswUseFromDateToDate() {
		return wUseFromDateToDate;
	}

	public void setwUseFromDateToDate(boolean wUseFromDateToDate) {
		this.wUseFromDateToDate = wUseFromDateToDate;
	}

	public boolean iswUseMostRecentData() {
		return wUseMostRecentData;
	}

	public void setwUseMostRecentData(boolean wUseMostRecentData) {
		this.wUseMostRecentData = wUseMostRecentData;
	}

	public boolean isAggregateXAsMonthly() {
		return aggregateXAsMonthly;
	}

	public void setAggregateXAsMonthly(boolean aggregateXAsMonthly) {
		this.aggregateXAsMonthly = aggregateXAsMonthly;
	}

	public boolean isAggregateZAsMonthly() {
		return aggregateZAsMonthly;
	}

	public void setAggregateZAsMonthly(boolean aggregateZAsMonthly) {
		this.aggregateZAsMonthly = aggregateZAsMonthly;
	}

	public boolean isAggregateYAsMonthly() {
		return aggregateYAsMonthly;
	}

	public void setAggregateYAsMonthly(boolean aggregateYAsMonthly) {
		this.aggregateYAsMonthly = aggregateYAsMonthly;
	}

	public boolean isAggregateWAsMonthly() {
		return aggregateWAsMonthly;
	}

	public void setAggregateWAsMonthly(boolean aggregateWAsMonthly) {
		this.aggregateWAsMonthly = aggregateWAsMonthly;
	}

	public boolean isShowBaseLayer() {
		return showBaseLayer;
	}

	public void setShowBaseLayer(boolean showBaseLayer) {
		this.showBaseLayer = showBaseLayer;
	}

	public boolean isUseFlashCharts() {
		return useFlashCharts;
	}

	public void setUseFlashCharts(boolean useFlashCharts) {
		this.useFlashCharts = useFlashCharts;
	}

	public String getReportOrientation() {
		return reportOrientation;
	}

	public void setReportOrientation(String reportOrientation) {
		this.reportOrientation = reportOrientation;
	}

	public int getMapClasses() {
		return mapClasses;
	}

	public void setMapClasses(int mapClasses) {
		this.mapClasses = mapClasses;
	}

	public String getMinColor() {
		return minColor;
	}

	public void setMinColor(String minColor) {
		this.minColor = minColor;
	}

	public String getMaxColor() {
		return maxColor;
	}

	public void setMaxColor(String maxColor) {
		this.maxColor = maxColor;
	}

	public String getOlapHtml() {
		return olapHtml;
	}

	public void setOlapHtml(String olapHtml) {
		this.olapHtml = olapHtml;
	}

	public boolean isShowSingleValues() {
		return showSingleValues;
	}

	public void setShowSingleValues(boolean showSingleValues) {
		this.showSingleValues = showSingleValues;
	}

	public Boolean getShowRowSummary() {
		return showRowSummary;
	}

	public void setShowRowSummary(Boolean showRowSummary) {
		this.showRowSummary = showRowSummary;
	}

	public Boolean getShowColSummary() {
		return showColSummary;
	}

	public void setShowColSummary(Boolean showColSummary) {
		this.showColSummary = showColSummary;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public Map<String, String> getXLabels() {
		return xLabels;
	}

	public void setXLabels(Map<String, String> labels) {
		xLabels = labels;
	}

	public Map<String, String> getYLabels() {
		return yLabels;
	}

	public void setYLabels(Map<String, String> labels) {
		yLabels = labels;
	}

	public Map<String, String> getZLabels() {
		return zLabels;
	}

	public void setZLabels(Map<String, String> labels) {
		zLabels = labels;
	}

	public Map<String, String> getWLabels() {
		return wLabels;
	}

	public void setWLabels(Map<String, String> labels) {
		wLabels = labels;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getXLabel() {
		return xLabel;
	}

	public void setXLabel(String label) {
		xLabel = label;
	}

	public String getYLabel() {
		return yLabel;
	}

	public void setYLabel(String label) {
		yLabel = label;
	}

	public String getZLabel() {
		return zLabel;
	}

	public void setZLabel(String label) {
		zLabel = label;
	}

	public String getZHeader() {
		return zHeader;
	}

	public void setZHeader(String header) {
		zHeader = header;
	}

	public String getWLabel() {
		return wLabel;
	}

	public void setWLabel(String label) {
		wLabel = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueLabel() {
		return valueLabel;
	}

	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<OLAPFilterVo> getFilters() {
		return filters;
	}

	public void setFilters(List<OLAPFilterVo> filters) {
		this.filters = filters;
	}

	public List<String> getChartsTitles() {
		return chartsTitles;
	}

	public void setChartsTitles(List<String> chartsTitles) {
		this.chartsTitles = chartsTitles;
	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	public Map<String, String> getxLabels() {
		return xLabels;
	}

	public void setxLabels(Map<String, String> xLabels) {
		this.xLabels = xLabels;
	}

	public Map<String, String> getyLabels() {
		return yLabels;
	}

	public void setyLabels(Map<String, String> yLabels) {
		this.yLabels = yLabels;
	}

	public Map<String, String> getzLabels() {
		return zLabels;
	}

	public void setzLabels(Map<String, String> zLabels) {
		this.zLabels = zLabels;
	}

	public Map<String, String> getwLabels() {
		return wLabels;
	}

	public void setwLabels(Map<String, String> wLabels) {
		this.wLabels = wLabels;
	}

	public String getxLabel() {
		return xLabel;
	}

	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public String getyLabel() {
		return yLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public String getzLabel() {
		return zLabel;
	}

	public void setzLabel(String zLabel) {
		this.zLabel = zLabel;
	}

	public String getwLabel() {
		return wLabel;
	}

	public void setwLabel(String wLabel) {
		this.wLabel = wLabel;
	}

	public Map<String, Map<String, String>> getxUniqueValuesMap() {
		return xUniqueValuesMap;
	}

	public void setxUniqueValuesMap(
			Map<String, Map<String, String>> xUniqueValuesMap) {
		this.xUniqueValuesMap = xUniqueValuesMap;
	}

	public Map<String, Map<String, String>> getzUniqueValuesMap() {
		return zUniqueValuesMap;
	}

	public void setzUniqueValuesMap(
			Map<String, Map<String, String>> zUniqueValuesMap) {
		this.zUniqueValuesMap = zUniqueValuesMap;
	}

	public Map<String, Map<String, String>> getyUniqueValuesMap() {
		return yUniqueValuesMap;
	}

	public void setyUniqueValuesMap(
			Map<String, Map<String, String>> yUniqueValuesMap) {
		this.yUniqueValuesMap = yUniqueValuesMap;
	}

	public Map<String, Map<String, String>> getwUniqueValuesMap() {
		return wUniqueValuesMap;
	}

	public void setwUniqueValuesMap(
			Map<String, Map<String, String>> wUniqueValuesMap) {
		this.wUniqueValuesMap = wUniqueValuesMap;
	}

	public boolean isAggregateXAsYearly() {
		return aggregateXAsYearly;
	}

	public void setAggregateXAsYearly(boolean aggregateXAsYearly) {
		this.aggregateXAsYearly = aggregateXAsYearly;
	}

	public boolean isAggregateZAsYearly() {
		return aggregateZAsYearly;
	}

	public void setAggregateZAsYearly(boolean aggregateZAsYearly) {
		this.aggregateZAsYearly = aggregateZAsYearly;
	}

	public boolean isAggregateYAsYearly() {
		return aggregateYAsYearly;
	}

	public void setAggregateYAsYearly(boolean aggregateYAsYearly) {
		this.aggregateYAsYearly = aggregateYAsYearly;
	}

	public boolean isAggregateWAsYearly() {
		return aggregateWAsYearly;
	}

	public void setAggregateWAsYearly(boolean aggregateWAsYearly) {
		this.aggregateWAsYearly = aggregateWAsYearly;
	}

	public Long getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Long dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getDataSourceTitle() {
		return dataSourceTitle;
	}

	public void setDataSourceTitle(String dataSourceTitle) {
		this.dataSourceTitle = dataSourceTitle;
	}

	public boolean isShowXLabel() {
		return showXLabel;
	}

	public void setShowXLabel(boolean showXLabel) {
		this.showXLabel = showXLabel;
	}

	public boolean isShowYLabel() {
		return showYLabel;
	}

	public void setShowYLabel(boolean showYLabel) {
		this.showYLabel = showYLabel;
	}

	public boolean isShowZLabel() {
		return showZLabel;
	}

	public void setShowZLabel(boolean showZLabel) {
		this.showZLabel = showZLabel;
	}
	
	public boolean isShowWLabel() {
		return showWLabel;
	}

	public void setShowWLabel(boolean showWLabel) {
		this.showWLabel = showWLabel;
	}

	@Override
	public String toString() {
		String out = "Datasource Title: " + this.getDataSourceTitle() + "\n"
				+ "DataSourceID: " + this.getDataSourceId() + "\n"
				+ "DataSource Type: " + this.getDataSourceType() + "\n" + "X: "
				+ this.getX() + "\n" + "Y: " + this.getY() + "\n" + "Z: "
				+ this.getZ() + "\n" + "W: " + this.getW() + "\n" + "X Label: "
				+ this.getXLabel() + "\n" + "Y Label: " + this.getYLabel()
				+ "\n" + "Z Label: " + this.getZLabel() + "\n" + "W Label: "
				+ this.getWLabel() + "\n" + "Value Label: "
				+ this.getValueLabel() + "\n" + "Function: "
				+ this.getFunction() + "\n" + "Show Single Values? "
				+ this.isShowSingleValues() + "\n" + "Show Row Summary? "
				+ this.getShowRowSummary() + "\n" + "Show Col Summary? "
				+ this.getShowColSummary() + "\n" + "Show Border? "
				+ this.getShowBorder() + "\n" +  "Show GridLine? "
				+ this.getShowGridLine() + "\n" 
				+ "Show Shading? "+ this.getShowShading() + "\n" 
				+ "Shading1Color? "+ this.getShading1Color() + "\n" 
				+ "Shading2Color? "+ this.getShading2Color() + "\n" 
				+ "Show RowFunction   ? "+ this.getRowFunction   () + "\n" 
				+ "Show SubColFunction? "+ this.getSubColFunction() + "\n" 
				+ "Show SubRowFunction? "+ this.getSubRowFunction() + "\n" 
				+ "Show ColFunction   ? "+ this.getColFunction   () + "\n" 
				+ "Show Row Subject? "
				+ this.getShowRowSubject() + "\n" + "Show Sub Row Subject? "
				+ this.getShowSubRowSubject() + "\n" + "Show Column Subject? "
				+ this.getShowColSubject() + "\n" + "Show Sub Column Subject? "
				+ this.getShowSubColSubject() + "\n"
				+ "Chart Type: " + this.getChartType() + "\n" + "Decimals: "
				+ this.getDecimals() + "\n";

		for (String c : this.getXLabels().keySet())
			out += "\tX Code: " + c + " > " + this.getXLabels().get(c) + "\n";
		for (String c : this.getYLabels().keySet())
			out += "\tY Code: " + c + " > " + this.getYLabels().get(c) + "\n";
		for (String c : this.getZLabels().keySet())
			out += "\tZ Code: " + c + " > " + this.getZLabels().get(c) + "\n";
		for (String c : this.getWLabels().keySet())
			out += "\tW Code: " + c + " > " + this.getWLabels().get(c) + "\n";
		for (OLAPFilterVo vo : this.getFilters())
			out += vo.toString();
		return out;
	}

	public TreeSet<String> getSortedXLabels() {
		return sortedXLabels;
	}

	public void setSortedXLabels(TreeSet<String> sortedXLabels) {
		this.sortedXLabels = sortedXLabels;
	}

	public TreeSet<String> getSortedZLabels() {
		return sortedZLabels;
	}

	public void setSortedZLabels(TreeSet<String> sortedZLabels) {
		this.sortedZLabels = sortedZLabels;
	}

	public TreeSet<String> getSortedWLabels() {
		return sortedWLabels;
	}

	public void setSortedWLabels(TreeSet<String> sortedWLabels) {
		this.sortedWLabels = sortedWLabels;
	}

	public TreeSet<String> getSortedYLabels() {
		return sortedYLabels;
	}

	public void setSortedYLabels(TreeSet<String> sortedYLabels) {
		this.sortedYLabels = sortedYLabels;
	}

	public Map<String, String> getxCodes() {
		return xCodes;
	}

	public void setxCodes(Map<String, String> xCodes) {
		this.xCodes = xCodes;
	}

	public boolean isSortXLabels() {
		return sortXLabels;
	}

	public void setSortXLabels(boolean sortXLabels) {
		this.sortXLabels = sortXLabels;
	}

	public String getAggregatedTableViewName() {
		return aggregatedTableViewName;
	}

	public void setAggregatedTableViewName(String aggregatedTableViewName) {
		this.aggregatedTableViewName = aggregatedTableViewName;
	}

	public List<String> getAggregateSelectedZDimensions() {
		return aggregateSelectedZDimensions;
	}

	public void setAggregateSelectedZDimensions(
			List<String> aggregateSelectedZDimensions) {
		this.aggregateSelectedZDimensions = aggregateSelectedZDimensions;
	}

	public List<String> getAggregateSelectedYDimensions() {
		return aggregateSelectedYDimensions;
	}

	public void setAggregateSelectedYDimensions(
			List<String> aggregateSelectedYDimensions) {
		this.aggregateSelectedYDimensions = aggregateSelectedYDimensions;
	}

	public List<String> getAggregateSelectedXDimensions() {
		return aggregateSelectedXDimensions;
	}

	public void setAggregateSelectedXDimensions(
			List<String> aggregateSelectedXDimensions) {
		this.aggregateSelectedXDimensions = aggregateSelectedXDimensions;
	}

	public boolean isShowRowVariation() {
		return showRowVariation;
	}

	public void setShowRowVariation(boolean showRowVariation) {
		this.showRowVariation = showRowVariation;
	}

	public boolean isShowColVariation() {
		return showColVariation;
	}

	public void setShowColVariation(boolean showColVariation) {
		this.showColVariation = showColVariation;
	}

	public String getOlapTitleFontColor() {
		return olapTitleFontColor;
	}

	public void setOlapTitleFontColor(String olapTitleFontColor) {
		this.olapTitleFontColor = olapTitleFontColor;
	}

	public String getOlapTitle() {
		return olapTitle;
	}

	public void setOlapTitle(String olapTitle) {
		this.olapTitle = olapTitle;
	}

	public String getNotesFontColor() {
		return notesFontColor;
	}

	public void setNotesFontColor(String notesFontColor) {
		this.notesFontColor = notesFontColor;
	}

	public String getNotesTitle() {
		return notesTitle;
	}

	public void setNotesTitle(String notesTitle) {
		this.notesTitle = notesTitle;
	}

	public Boolean getShowGridLine() {
		return showGridLine;
	}

	public void setShowGridLine(Boolean showGridLine) {
		this.showGridLine = showGridLine;
	}

	public Boolean getShowShading() {
		return showShading;
	}

	public void setShowShading(Boolean showShading) {
		this.showShading = showShading;
	}

	public Boolean getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(Boolean showBorder) {
		this.showBorder = showBorder;
	}
//
	public Boolean getShowRowSubject() {
		return showRowSubject;
	}

	public void setShowRowSubject(Boolean showRowSubject) {
		this.showRowSubject = showRowSubject;
	}
	
	public Boolean getShowSubRowSubject() {
		return showSubRowSubject;
	}

	public void setShowSubRowSubject(Boolean showSubRowSubject) {
		this.showSubRowSubject = showSubRowSubject;
	}
	//
	public Boolean getShowColSubject() {
		return showColSubject;
	}

	public void setShowColSubject(Boolean showColSubject) {
		this.showColSubject = showColSubject;
	}

	public Boolean getShowSubColSubject() {
		return showSubColSubject;
	}

	public void setShowSubColSubject(Boolean showSubColSubject) {
		this.showSubColSubject = showSubColSubject;
	}

	public Boolean getShowSubRowSummary() {
		return showSubRowSummary;
	}

	public void setShowSubRowSummary(Boolean showSubRowSummary) {
		this.showSubRowSummary = showSubRowSummary;
	}

	public Boolean getShowSubColSummary() {
		return showSubColSummary;
	}

	public void setShowSubColSummary(Boolean showSubColSummary) {
		this.showSubColSummary = showSubColSummary;
	}

	public String getColFunction() {
		return colFunction;
	}

	public void setColFunction(String colFunction) {
		this.colFunction = colFunction;
	}

	public String getRowFunction() {
		return rowFunction;
	}

	public void setRowFunction(String rowFunction) {
		this.rowFunction = rowFunction;
	}

	public String getSubRowFunction() {
		return subRowFunction;
	}

	public void setSubRowFunction(String subRowFunction) {
		this.subRowFunction = subRowFunction;
	}

	public String getSubColFunction() {
		return subColFunction;
	}

	public void setSubColFunction(String subColFunction) {
		this.subColFunction = subColFunction;
	}

	public String getShading1Color() {
		return shading1Color;
	}

	public void setShading1Color(String shading1Color) {
		this.shading1Color = shading1Color;
	}

	public String getShading2Color() {
		return shading2Color;
	}

	public void setShading2Color(String shading2Color) {
		this.shading2Color = shading2Color;
	}

	public String getxLabelShowTitle() {
		return xLabelShowTitle;
	}

	public void setxLabelShowTitle(String xLabelShowTitle) {
		this.xLabelShowTitle = xLabelShowTitle;
	}

	public String getyLabelShowTitle() {
		return yLabelShowTitle;
	}

	public void setyLabelShowTitle(String yLabelShowTitle) {
		this.yLabelShowTitle = yLabelShowTitle;
	}

	public String getzLabelShowTitle() {
		return zLabelShowTitle;
	}

	public void setzLabelShowTitle(String zLabelShowTitle) {
		this.zLabelShowTitle = zLabelShowTitle;
	}

	public String getwLabelShowTitle() {
		return wLabelShowTitle;
	}

	public void setwLabelShowTitle(String wLabelShowTitle) {
		this.wLabelShowTitle = wLabelShowTitle;
	}
	
}