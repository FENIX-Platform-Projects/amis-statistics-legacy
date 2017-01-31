package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.common.vo.DataViewerDBVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DWFAOSTATQueryVO extends DataViewerDBVO implements IsSerializable {

	private String text;
	
	/** OUTPUT: CHART, MAP, TABLE, PIVOT_TABLE  */
	private String output;

	/** TYPE: BAR_WITH_CATEGORIES, PIE, EXPORT_CSV */
	private String typeOfOutput;
	
	private DWFAOSTATResultVO rvo;
	
	private Boolean runAsyncCall = true;
	
	private String title;
	
	private String subTitle;
	
	private Boolean showTimerangeTitle = true;
	
	private String switchTitle;
	
	private String description;
	
	private String question;
	
	private String xLabel;
	
	private String yLabel;
	
	private String width;
	
	private String height;
	
	private String measurementUnit;
	
	private List<String> selects = new ArrayList<String>();
	
	private Map<String, String> selectsAlias = new HashMap<String, String>();
	
	private List<String> froms = new ArrayList<String>();
	
	// TODO: USING AN LINKEDHASHMAP, giving the column to order by, and the type of sorting..
	private List<String> orderBys = new ArrayList<String>();
	
	private Map<String, String> aggregations = new HashMap<String, String>();
	
	private Map<String, String> groupBys = new HashMap<String, String>();

	private String aggregationType = "AVG";
	
	private Integer limit;
	
	// variables used for the nested queries
	private Integer nestedLimit;
	
	private String nestedLimitField;
	
	// this is used to sum the others 
	private boolean sumOthers = false;
	
	private Integer othersLimit;
	
	private List<String> orderBysNested = new ArrayList<String>();
	
	private String sortingOrderNested = "DESC"; 
	
	private boolean small;
	
	private String langCode;
	
	private String sortingOrder = "DESC"; 
	
	//private Boolean orderByColumn = false;
	
	private String groupCode;
	
	private String groupLabel;
	
		
	/** QUERY PARAMETERS **/
	
	// is it useful of check on 'TM' domain?
	private Boolean tradeMatrix = false;
		
	private Map<String, String> domains = new HashMap<String, String>();
	
	private Map<String, String> groups = new HashMap<String, String>();
	
	private Map<String, String> areas = new HashMap<String, String>();
	
	private boolean enableZoomTo = true;
	private Map<String, String> zoomto = new HashMap<String, String>();
	
	// WORKAROUND this has been made for EU on the TOP 
	private Map<String, String> areasBlacklisted = new HashMap<String, String>();
	
	// this is for the trade matrix
	private Map<String, String> reportedAreas = new HashMap<String, String>();
	
	private Map<String, String> partnerAreas = new HashMap<String, String>();
	
	private Map<String, String> years = new HashMap<String, String>();
	
	private Map<String, String> items = new HashMap<String, String>();
	
	private Map<String, String> itemsLevel = new HashMap<String, String>();
	
	private Map<String, String> elements = new HashMap<String, String>();
	
	private Map<String, String> elementsList = new HashMap<String, String>();
	
	/** this is used in the coding system to retrieve the right Level of the elements **/
	private Map<String, String> level = new HashMap<String, String>();

	
	/** Coding System **/
	
	private Boolean getTotalAndList = false;
	
	private Map<String, String> aggregationsFilter = new HashMap<String, String>();
	
	/** **/
	
	
	/** Handle null values for specified fields**/
	private Map<String, String> handleNullForFieldMap =  new HashMap<String, String>();
	
	
	/** filterMap: filterField, filterFieldValues **/
	private Map<String, List<String>> filterMap =  new HashMap<String, List<String>>();
	
	
	private String sql;
	
	
	
	// TODO: Do we need BOTH??
	private Boolean isCountryLevel = true;
	
	private Boolean isRegionLevel = false;
	
	private Boolean addFlag = true;
	
	private Boolean addLabels = true;
	
	/**
	 * Date range parameters
	 */
	private Boolean runMaxDateQuery = false;
	private String maxDateLimit;
	private Integer timeSpan;
	private String timeSpanType;
	
	/*
	 * Calculation Query parameters
	 */
	private Boolean runCalculationQuery = false;
	private CalculationParametersVO calculationParametersVO;
	
	/*
	 * Calculation Query parameters
	 */
	private Boolean runJoinQuery = false;
	private FAOSTATJoinQueryVO joinQueryVO;
	
	private Boolean isCompare = false;
	
	
	/** PIVOT TABLE **/
	
	// variables used in the stored procedure
	private String nestedby; // this variable is the @pivotdimension1
	
	private String xAxis; // this variable is the @pivotdimension2 

	private String y1Axis; // this variable the first variable listed in the @extracolumns parameter
	
	private String y2Axis;// this variable the second variable listed in the @extracolumns parameter

	// extra table columns
	// show Codes
	private Boolean showCodes = false;
	
	// show MeasurementUnit // TODO: change in showUnits
	private Boolean showMeasurementUnit = true;
	
	// show Null ( this is used also in the normal queries)
//	private Boolean showNull = true;
	private Boolean showNull = false;
	
	// show Official flags
	private Boolean showOfficialflags = false;
	
	// thousand Separator
	private String thousandSeparator = "";
	
	//decimal separator
	private String decimalseparator = ".";
	
	// number of decimals 
	private String showDec = "2";
	
	private Boolean isFBS = false;
	
	
	
	/**
	 * Metadata in Fenix DB parameters
	 */
	private String fenixDBTableName;
	private List<String> tableHeaders = new ArrayList<String>();
	private String fenixDatasetCode;
	
	
	
	// FIXED QVO (TODO: should be a list of not applicable filters [filtersType] i.e. not apply the filter for the items
	// 			   instead of a Boolean)

	// TODO: change name of variable
	// applyOnlyYearFilter - isFixedAvoidingYears (APPLY_ONLY_YEAR_FILTER)
	private Boolean applyOnlyYearFilter = false; // everything is fixed (just not the years)
	
	// applyAllFiltersExceptYears - isFixedYears (APPLY_ALL_FILTERS_EXCEPT_YEARS)
	private Boolean applyAllFiltersExceptYears = false; // it's fixed just the years, other filters are applicable
	
	// applyAllFiltersExceptAggregrationType - isFixedAggregationType (APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE)
	private Boolean applyAllFiltersExceptAggregrationType = false; // it's fixed just the aggregationType, other filters are appliable

	// not apply any of the filters
	private Boolean notApplyFilters = false;
	
	// applyAllFiltersExceptAreas - (APPLY_ALL_FILTERS_EXCEPT_AREAS)
	private Boolean applyAllFiltersExceptAreas = false; // it's fixed just the years, other filters are applicable
	
//	 applyAllFiltersExceptAreas - (APPLY_ALL_FILTERS_EXCEPT_ITMS)
	private Boolean applyAllFiltersExceptItems = false; 
//	 applyAllFiltersExceptAreas - (APPLY_ONLY_AREAS)
	private Boolean applyOnlyAreasFilter = false;
//	 applyAllFiltersExceptAreas - (APPLY_ONLY_AREAS)
	private Boolean applyOnlyItemsFilter = false;

	private HashMap<String, String> applicableFilters = new HashMap<String, String>();
	
	private HashMap<String, String> notApplicableFilters = new HashMap<String, String>();
	
	
	
	// Tables variables
	
	private Boolean showColumnHeaders = true;
	
	
	// SEARCH 
	
	// from (key) - to (value)
	private HashMap<String, String> rangeCodes = new HashMap<String, String>();
	
	
	// JOIN
	
	
	// CHARTS
	String palette;
	
	private String joinColumn;
	
	
	// footnote
	
	private String footnote;
	
	private boolean useCustomColors = false;
	
	private boolean useAbsoluteValues = false;
	
	private ValueCondition valueCondition;
	
	private Double valueTreshold;
	
	private FAOSTATMapsVO mapsVO = new FAOSTATMapsVO();	
	
	public boolean isUseAbsoluteValues() {
		return useAbsoluteValues;
	}

	public void setUseAbsoluteValues(boolean useAbsoluteValues) {
		this.useAbsoluteValues = useAbsoluteValues;
	}

	public Double getValueTreshold() {
		return valueTreshold;
	}

	public void setValueTreshold(Double valueTreshold) {
		this.valueTreshold = valueTreshold;
	}

	public ValueCondition getValueCondition() {
		return valueCondition;
	}

	public void setValueCondition(ValueCondition valueCondition) {
		this.valueCondition = valueCondition;
	}

	public boolean isUseCustomColors() {
		return useCustomColors;
	}

	public void setUseCustomColors(boolean useCustomColors) {
		this.useCustomColors = useCustomColors;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}


	public Map<String, String> getDomains() {
		return domains;
	}

	public void setDomains(Map<String, String> domains) {
		this.domains = domains;
	}

	public Map<String, String> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, String> groups) {
		this.groups = groups;
	}
	
	public Map<String, String> getAreas() {
		return areas;
	}

	public void setAreas(Map<String, String> areas) {
		this.areas = areas;
	}

	public Map<String, String> getYears() {
		return years;
	}

	public void setYears(Map<String, String> years) {
		this.years = years;
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public Map<String, String> getElements() {
		return elements;
	}

	public void setElements(Map<String, String> elements) {
		this.elements = elements;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getTypeOfOutput() {
		return typeOfOutput;
	}

	public void setTypeOfOutput(String typeOfOutput) {
		this.typeOfOutput = typeOfOutput;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSwitchTitle() {
		return switchTitle;
	}

	public void setSwitchTitle(String switchTitle) {
		this.switchTitle = switchTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public List<String> getSelects() {
		return selects;
	}

	public void setSelects(List<String> selects) {
		this.selects = selects;
	}

	public Map<String, String> getAggregations() {
		return aggregations;
	}

	public void setAggregations(Map<String, String> aggregations) {
		this.aggregations = aggregations;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public List<String> getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(List<String> orderBys) {
		this.orderBys = orderBys;
	}

	public Boolean getIsCountryLevel() {
		return isCountryLevel;
	}

	public void setIsCountryLevel(Boolean isCountryLevel) {
		this.isCountryLevel = isCountryLevel;
	}

	public Boolean getIsRegionLevel() {
		return isRegionLevel;
	}

	public void setIsRegionLevel(Boolean isRegionLevel) {
		this.isRegionLevel = isRegionLevel;
	}

	public void setFroms(List<String> froms) {
		this.froms = froms;
	}

	public List<String> getFroms() {
		return froms;
	}

	public Boolean getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(Boolean addFlag) {
		this.addFlag = addFlag;
	}

	public Boolean getAddLabels() {
		return addLabels;
	}

	public void setAddLabels(Boolean addLabels) {
		this.addLabels = addLabels;
	}

	public Integer getNestedLimit() {
		return nestedLimit;
	}

	public void setNestedLimit(Integer nestedLimit) {
		this.nestedLimit = nestedLimit;
	}

	public String getNestedLimitField() {
		return nestedLimitField;
	}

	public void setNestedLimitField(String nestedLimitField) {
		this.nestedLimitField = nestedLimitField;
	}

	
	public Boolean getRunMaxDateQuery() {
		return runMaxDateQuery;
	}
	
	public void setRunMaxDateQuery(Boolean runMaxDateQuery) {
		this.runMaxDateQuery = runMaxDateQuery;
	}

	public String getMaxDateLimit() {
		return maxDateLimit;
	}

	public void setMaxDateLimit(String maxDateLimit) {
		this.maxDateLimit = maxDateLimit;
	}
	
	public Integer getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Integer timeSpan) {
		this.timeSpan = timeSpan;
	}

    public String getAggregationType() {
		return aggregationType;
	}

	public void setAggregationType(String aggregationType) {
		this.aggregationType = aggregationType;
	}

	public Boolean getGetTotalAndList() {
		return getTotalAndList;
	}

	public void setGetTotalAndList(Boolean getTotalAndList) {
		this.getTotalAndList = getTotalAndList;
	}

	public Map<String, String> getAggregationsFilter() {
		return aggregationsFilter;
	}

	public void setAggregationsFilter(Map<String, String> aggregationsFilter) {
		this.aggregationsFilter = aggregationsFilter;
	}
	
	public Boolean getRunCalculationQuery() {
		return runCalculationQuery;
	}

	public void setRunCalculationQuery(Boolean runCalculationQuery) {
		this.runCalculationQuery = runCalculationQuery;
	}

	public CalculationParametersVO getCalculationParametersVO() {
		return calculationParametersVO;
	}

	public void setCalculationParametersVO(
			CalculationParametersVO calculationParametersVO) {
		this.calculationParametersVO = calculationParametersVO;
	}

	public String getTimeSpanType() {
		return timeSpanType;
	}

	public void setTimeSpanType(String timeSpanType) {
		this.timeSpanType = timeSpanType;
	}
	
	
	public Map<String, String> getElementsList() {
		return elementsList;
	}

	public void setElementsList(Map<String, String> elementsList) {
		this.elementsList = elementsList;
	}
	
	public String getNestedby() {
		return nestedby;
	}

	public void setNestedby(String nestedby) {
		this.nestedby = nestedby;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getY1Axis() {
		return y1Axis;
	}

	public void setY1Axis(String y1Axis) {
		this.y1Axis = y1Axis;
	}

	public String getY2Axis() {
		return y2Axis;
	}

	public void setY2Axis(String y2Axis) {
		this.y2Axis = y2Axis;
	}
	
	

	public String getFenixDBTableName() {
		return fenixDBTableName;
	}

	public void setFenixDBTableName(String fenixDBTableName) {
		this.fenixDBTableName = fenixDBTableName;
	}
	
	public List<String> getTableHeaders() {
		return tableHeaders;
	}

	public void setTableHeaders(List<String> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	public String getFenixDatasetCode() {
		return fenixDatasetCode;
	}

	public void setFenixDatasetCode(String fenixDatasetCode) {
		this.fenixDatasetCode = fenixDatasetCode;
	}

	public Boolean getShowCodes() {
		return showCodes;
	}

	public void setShowCodes(Boolean showCodes) {
		this.showCodes = showCodes;
	}

	public Boolean getShowMeasurementUnit() {
		return showMeasurementUnit;
	}

	public void setShowMeasurementUnit(Boolean showMeasurementUnit) {
		this.showMeasurementUnit = showMeasurementUnit;
	}

	public Boolean getShowNull() {
		return showNull;
	}

	public void setShowNull(Boolean showNull) {
		this.showNull = showNull;
	}

	public Boolean getShowOfficialflags() {
		return showOfficialflags;
	}

	public void setShowOfficialflags(Boolean showOfficialflags) {
		this.showOfficialflags = showOfficialflags;
	}

	public String getThousandSeparator() {
		return thousandSeparator;
	}

	public void setThousandSeparator(String thousandSeparator) {
		this.thousandSeparator = thousandSeparator;
	}

	public String getDecimalseparator() {
		return decimalseparator;
	}

	public void setDecimalseparator(String decimalseparator) {
		this.decimalseparator = decimalseparator;
	}

	public String getShowDec() {
		return showDec;
	}

	public void setShowDec(String showDec) {
		this.showDec = showDec;
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

	public Boolean getApplyOnlyYearFilter() {
		return applyOnlyYearFilter;
	}

	public void setApplyOnlyYearFilter(Boolean applyOnlyYearFilter) {
		this.applyOnlyYearFilter = applyOnlyYearFilter;
	}

	public Boolean getApplyAllFiltersExceptYears() {
		return applyAllFiltersExceptYears;
	}

	public void setApplyAllFiltersExceptYears(Boolean applyAllFiltersExceptYears) {
		this.applyAllFiltersExceptYears = applyAllFiltersExceptYears;
	}

	public Boolean getApplyAllFiltersExceptAggregrationType() {
		return applyAllFiltersExceptAggregrationType;
	}

	public void setApplyAllFiltersExceptAggregrationType(
			Boolean applyAllFiltersExceptAggregrationType) {
		this.applyAllFiltersExceptAggregrationType = applyAllFiltersExceptAggregrationType;
	}

	public Boolean getShowColumnHeaders() {
		return showColumnHeaders;
	}

	public void setShowColumnHeaders(Boolean showColumnHeaders) {
		this.showColumnHeaders = showColumnHeaders;
	}

	public Boolean getNotApplyFilters() {
		return notApplyFilters;
	}

	public void setNotApplyFilters(Boolean notApplyFilters) {
		this.notApplyFilters = notApplyFilters;
	}

	public Boolean getApplyAllFiltersExceptAreas() {
		return applyAllFiltersExceptAreas;
	}

	public void setApplyAllFiltersExceptAreas(Boolean applyAllFiltersExceptAreas) {
		this.applyAllFiltersExceptAreas = applyAllFiltersExceptAreas;
	}

	public HashMap<String, String> getRangeCodes() {
		return rangeCodes;
	}

	public void setRangeCodes(HashMap<String, String> rangeCodes) {
		this.rangeCodes = rangeCodes;
	}

	public List<String> getOrderBysNested() {
		return orderBysNested;
	}

	public void setOrderBysNested(List<String> orderBysNested) {
		this.orderBysNested = orderBysNested;
	}

	public String getSortingOrderNested() {
		return sortingOrderNested;
	}

	public void setSortingOrderNested(String sortingOrderNested) {
		this.sortingOrderNested = sortingOrderNested;
	}

	public Boolean getRunJoinQuery() {
		return runJoinQuery;
	}

	public void setRunJoinQuery(Boolean runJoinQuery) {
		this.runJoinQuery = runJoinQuery;
	}

	public FAOSTATJoinQueryVO getJoinQueryVO() {
		return joinQueryVO;
	}

	public void setJoinQueryVO(FAOSTATJoinQueryVO joinQueryVO) {
		this.joinQueryVO = joinQueryVO;
	}

	public Map<String, String> getSelectsAlias() {
		return selectsAlias;
	}

	public void setSelectsAlias(Map<String, String> selectsAlias) {
		this.selectsAlias = selectsAlias;
	}

	public Map<String, String> getItemsLevel() {
		return itemsLevel;
	}

	public void setItemsLevel(Map<String, String> itemsLevel) {
		this.itemsLevel = itemsLevel;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public Map<String, String> getGroupBys() {
		return groupBys;
	}

	public void setGroupBys(Map<String, String> groupBys) {
		this.groupBys = groupBys;
	}

	public Map<String, String> getReportedAreas() {
		return reportedAreas;
	}

	public void setReportedAreas(Map<String, String> reportedAreas) {
		this.reportedAreas = reportedAreas;
	}

	public Map<String, String> getPartnerAreas() {
		return partnerAreas;
	}

	public void setPartnerAreas(Map<String, String> partnerAreas) {
		this.partnerAreas = partnerAreas;
	}

	public Boolean getIsTradeMatrix() {
		return tradeMatrix;
	}

	public void setIsTradeMatrix(Boolean tradeMatrix) {
		this.tradeMatrix = tradeMatrix;
	}

	public Boolean getTradeMatrix() {
		return tradeMatrix;
	}

	public void setTradeMatrix(Boolean tradeMatrix) {
		this.tradeMatrix = tradeMatrix;
	}

	public Boolean getApplyAllFiltersExceptItems() {
		return applyAllFiltersExceptItems;
	}

	public void setApplyAllFiltersExceptItems(Boolean applyAllFiltersExceptItems) {
		this.applyAllFiltersExceptItems = applyAllFiltersExceptItems;
	}

	public Boolean getApplyOnlyAreasFilter() {
		return applyOnlyAreasFilter;
	}

	public void setApplyOnlyAreasFilter(Boolean applyOnlyAreasFilter) {
		this.applyOnlyAreasFilter = applyOnlyAreasFilter;
	}

	public HashMap<String, String> getApplicableFilters() {
		return applicableFilters;
	}

	public void setApplicableFilters(HashMap<String, String> applicableFilters) {
		this.applicableFilters = applicableFilters;
	}

	public HashMap<String, String> getNotApplicableFilters() {
		return notApplicableFilters;
	}

	public void setNotApplicableFilters(HashMap<String, String> notApplicableFilters) {
		this.notApplicableFilters = notApplicableFilters;
	}

	public Boolean getApplyOnlyItemsFilter() {
		return applyOnlyItemsFilter;
	}

	public void setApplyOnlyItemsFilter(Boolean applyOnlyItemsFilter) {
		this.applyOnlyItemsFilter = applyOnlyItemsFilter;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public Boolean getIsFBS() {
		return isFBS;
	}

	public void setIsFBS(Boolean isFBS) {
		this.isFBS = isFBS;
	}

	public Map<String, String> getHandleNullForFieldMap() {
		return handleNullForFieldMap;
	}

	public void setHandleNullForFieldMap(Map<String, String> handleNullForFieldMap) {
		this.handleNullForFieldMap = handleNullForFieldMap;
	}

	public Map<String, List<String>> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, List<String>> filterMap) {
		this.filterMap = filterMap;
	}

	public DWFAOSTATResultVO getRvo() {
		return rvo;
	}

	public void setRvo(DWFAOSTATResultVO rvo) {
		this.rvo = rvo;
	}

	public Boolean getRunAsyncCall() {
		return runAsyncCall;
	}

	public void setRunAsyncCall(Boolean runAsyncCall) {
		this.runAsyncCall = runAsyncCall;
	}

	public Map<String, String> getAreasBlacklisted() {
		return areasBlacklisted;
	}

	public void setAreasBlacklisted(Map<String, String> areasBlacklisted) {
		this.areasBlacklisted = areasBlacklisted;
	}

	public String getFootnote() {
		return footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public Boolean getShowTimerangeTitle() {
		return showTimerangeTitle;
	}

	public void setShowTimerangeTitle(Boolean showTimerangeTitle) {
		this.showTimerangeTitle = showTimerangeTitle;
	}

	public Map<String, String> getLevel() {
		return level;
	}

	public void setLevel(Map<String, String> level) {
		this.level = level;
	}
	
	/**
	 * this is to clone the class
	 * @param source
	 * @return
	 */
	public DWFAOSTATQueryVO clone(DWFAOSTATQueryVO source) {
		DWFAOSTATQueryVO clone = new DWFAOSTATQueryVO();
		
		// TODO: copy all the get and setters		
		
		return clone;
	}

	public Boolean getIsCompare() {
		return isCompare;
	}

	public void setIsCompare(Boolean isCompare) {
		this.isCompare = isCompare;
	}

	public boolean isSumOthers() {
		return sumOthers;
	}

	public void setSumOthers(boolean sumOthers) {
		this.sumOthers = sumOthers;
	}

	public Integer getOthersLimit() {
		return othersLimit;
	}

	public void setOthersLimit(Integer othersLimit) {
		this.othersLimit = othersLimit;
	}

	public FAOSTATMapsVO getMapsVO() {
		return mapsVO;
	}

	public void setMapsVO(FAOSTATMapsVO mapsVO) {
		this.mapsVO = mapsVO;
	}

	public boolean isEnableZoomTo() {
		return enableZoomTo;
	}

	public void setEnableZoomTo(boolean enableZoomTo) {
		this.enableZoomTo = enableZoomTo;
	}

	public Map<String, String> getZoomto() {
		return zoomto;
	}

	public void setZoomto(Map<String, String> zoomto) {
		this.zoomto = zoomto;
	}	
}