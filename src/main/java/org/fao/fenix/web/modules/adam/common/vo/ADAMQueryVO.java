package org.fao.fenix.web.modules.adam.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ADAMQueryVO implements IsSerializable {
	
	private String text;
	
	/** BAR, LINE, PIE, MAP, TABLE, OLAP, TEXT */
	private String resourceType;

	/** QUESTION TYPE (this is used for distinguish, what kind of table is it */
	private String outputType;
	
	/** this is used for the SOs and ORs*/
	private String filterFrameworkType;
	
	private String returnFrameworkType;
	
	private String title;
	
	private String subTitle;
	
	private String switchTitle;
	
	private String description;
	
	private String question;
	
	private String xLabel;
	
	private String yLabel;
	
	private String smallWidth;
	
	private String smallHeight;
	
	private String bigWidth;
	
	private String bigHeight;
	
	private String mediumWidth;
	
	private String mediumHeight;
	
	private String measurementUnit;
	
	private String aggregationLabel;
	
	private List<String> selects = new ArrayList<String>();
	
	private Map<String, String> selectsMap = new LinkedHashMap<String, String>();
	
	private Map<String, String> aggregations = new HashMap<String, String>();
	
	private List<String> datasetCodes = new ArrayList<String>();
	
	private List<String> tableNames = new ArrayList<String>();
	
	private List<String> aggregatedViewNames = new ArrayList<String>();
	
	private Map<String, List<String>> wheres = new HashMap<String, List<String>>();
	
	private Map<String, List<String>> likes = new HashMap<String, List<String>>();
	
	private Map<String, List<String>> series = new HashMap<String, List<String>>();
	
	private Map<String, String> calculatedSeries = new LinkedHashMap<String, String>();
	
	private Map<String, String> seriesLabels = new LinkedHashMap<String, String>();
			
	private Map<String, List<String>> whereDates = new HashMap<String, List<String>>();
	
	private List<String> orders = new ArrayList<String>();
	
	private List<String> groups = new ArrayList<String>();
	
	private int limit;
	
	private int width;
	
	private int height;
	
	private boolean small;
	
	private String codingSystem;
	
	private String codedColumn;
	
	private String langCode;
	
	//	private Boolean sort; 
	
	private String sortingOrder; 
	
	private String boxColor;
	
	private String orderByColumn;
	
	/*** MAPS **/
	
	private String layerName = "layer_gaul0_simple"; 

	private ADAMQueryVO switchResource;
	
	private List<ADAMQueryVO> switchResources = new ArrayList<ADAMQueryVO>();
	
	// ADAM Venn && FPMIS requests 
	
	private Map<String, String> recipients;
	
	private Map<String, String> donors;
	
	private Map<String, String> channels;
	
	private Map<String, String> sectors;
	
	private Map<String, String> priorities;
	
	private Boolean isOR = false;
	
	private Boolean isSO = false;
	
	private String datasetSource;
	
	private ADAMCurrentVIEW currentView;
	
	private Boolean runFAOSectorQuery = false;
	
	private Boolean isAgricultureFilter = false;
	
//	private Integer roundValue = 2;
	
	
	public Boolean isAgricultureFilter() {
		return isAgricultureFilter;
	}

	public void setIsAgricultureFilter(Boolean isAgricultureFilter) {
		this.isAgricultureFilter = isAgricultureFilter;
	}

	public ADAMCurrentVIEW getCurrentView() {
		return currentView;
	}

	public void setCurrentView(ADAMCurrentVIEW currentView) {
		this.currentView = currentView;
	}

	private ADAMQueryVO secondQuery;
	
	//MATRIX
	// it's used for the text in the fist column of the matrix
	private String matrixColumnText;

	
	private String disclaimer;


	public String getSmallWidth() {
		return smallWidth;
	}

	public void setSmallWidth(String smallWidth) {
		this.smallWidth = smallWidth;
	}

	public String getSmallHeight() {
		return smallHeight;
	}

	public void setSmallHeight(String smallHeight) {
		this.smallHeight = smallHeight;
	}

	public String getBigWidth() {
		return bigWidth;
	}

	public void setBigWidth(String bigWidth) {
		this.bigWidth = bigWidth;
	}

	public String getBigHeight() {
		return bigHeight;
	}

	public void setBigHeight(String bigHeight) {
		this.bigHeight = bigHeight;
	}

	public String getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(String boxColor) {
		this.boxColor = boxColor;
	}

	

	public String getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(String sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public Map<String, String> getAggregations() {
		return aggregations;
	}

	public void setAggregations(Map<String, String> aggregations) {
		this.aggregations = aggregations;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCodingSystem() {
		return codingSystem;
	}

	public void setCodingSystem(String codingSystem) {
		this.codingSystem = codingSystem;
	}

	public String getCodedColumn() {
		return codedColumn;
	}

	public void setCodedColumn(String codedColumn) {
		this.codedColumn = codedColumn;
	}

	public String getTitle() {
		return title;
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

	public int getWidth() {
		return width;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public List<String> getSelects() {
		return selects;
	}

	public void setSelects(List<String> selects) {
		this.selects = selects;
	}

	public List<String> getDatasetCodes() {
		return datasetCodes;
	}

	public void setDatasetCodes(List<String> datasetCodes) {
		this.datasetCodes = datasetCodes;
	}

	public List<String> getTableNames() {
		return tableNames;
	}
	
	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

	public List<String> getAggregatedViewNames() {
		return aggregatedViewNames;
	}

	public void setAggregatedViewNames(List<String> aggregatedViewNames) {
		this.aggregatedViewNames = aggregatedViewNames;
	}
	
	public List<String> getOrders() {
		return orders;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public Map<String, List<String>> getWhereDates() {
		return whereDates;
	}

	public void setWhereDates(Map<String, List<String>> whereDates) {
		this.whereDates = whereDates;
	}

	public void setWheres(Map<String, List<String>> wheres) {
		this.wheres = wheres;
	}

	public void setSeries(Map<String, List<String>> series) {
		this.series = series;
	}
	
	public void setCalculatedSeries(Map<String, String> calculatedSeries) {
		this.calculatedSeries = calculatedSeries;
	}
	

	public void setLikes(Map<String, List<String>> likes) {
		this.likes = likes;
	}

	public Map<String, List<String>> getWheres() {
		return wheres;
	}
	
	public Map<String, List<String>> getSeries() {
		return series;
	}
	
	public Map<String, String> getCalculatedSeries() {
		return calculatedSeries;
	}

	public Map<String, List<String>> getLikes() {
		return likes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}


	public Map<String, String> getRecipients() {
		return recipients;
	}

	public List<ADAMQueryVO> getSwitchResources() {
		return switchResources;
	}

	public void setSwitchResources(List<ADAMQueryVO> switchResources) {
		this.switchResources = switchResources;
	}

	public void setRecipients(Map<String, String> recipients) {
		this.recipients = recipients;
	}

	public Map<String, String> getDonors() {
		return donors;
	}

	public void setDonors(Map<String, String> donors) {
		this.donors = donors;
	}

	public Map<String, String> getChannels() {
		return channels;
	}

	public void setChannels(Map<String, String> channels) {
		this.channels = channels;
	}

	public Map<String, String> getSectors() {
		return sectors;
	}

	public void setSectors(Map<String, String> sectors) {
		this.sectors = sectors;
	}

	public Map<String, String> getPriorities() {
		return priorities;
	}

	public void setPriorities(Map<String, String> priorities) {
		this.priorities = priorities;
	}

	public String getMediumWidth() {
		return mediumWidth;
	}

	public void setMediumWidth(String mediumWidth) {
		this.mediumWidth = mediumWidth;
	}

	public String getMediumHeight() {
		return mediumHeight;
	}

	public void setMediumHeight(String mediumHeight) {
		this.mediumHeight = mediumHeight;
	}

	public Boolean getIsOR() {
		return isOR;
	}

	public void setIsOR(Boolean isOR) {
		this.isOR = isOR;
	}

	public Boolean getIsSO() {
		return isSO;
	}

	public void setIsSO(Boolean isSO) {
		this.isSO = isSO;
	}

	public String getFilterFrameworkType() {
		return filterFrameworkType;
	}

	public void setFilterFrameworkType(String filterFrameworkType) {
		this.filterFrameworkType = filterFrameworkType;
	}

	public String getReturnFrameworkType() {
		return returnFrameworkType;
	}

	public void setReturnFrameworkType(String returnFrameworkType) {
		this.returnFrameworkType = returnFrameworkType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getSeriesLabels() {
		return seriesLabels;
	}

	public void setSeriesLabels(Map<String, String> seriesLabels) {
		this.seriesLabels = seriesLabels;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public ADAMQueryVO getSecondQuery() {
		return secondQuery;
	}

	public void setSecondQuery(ADAMQueryVO secondQuery) {
		this.secondQuery = secondQuery;
	}

	public String getSwitchTitle() {
		return switchTitle;
	}

	public void setSwitchTitle(String switchTitle) {
		this.switchTitle = switchTitle;
	}

	public String getMatrixColumnText() {
		return matrixColumnText;
	}

	public void setMatrixColumnText(String matrixColumnText) {
		this.matrixColumnText = matrixColumnText;
	}

	public String getDatasetSource() {
		return datasetSource;
	}

	public void setDatasetSource(String datasetSource) {
		this.datasetSource = datasetSource;
	}

	public Boolean getRunFAOSectorQuery() {
		return runFAOSectorQuery;
	}

	public void setRunFAOSectorQuery(Boolean runFAOSectorQuery) {
		this.runFAOSectorQuery = runFAOSectorQuery;
	}

	public String getAggregationLabel() {
		return aggregationLabel;
	}

	public void setAggregationLabel(String aggregationLabel) {
		this.aggregationLabel = aggregationLabel;
	}

	public Map<String, String> getSelectsMap() {
		return selectsMap;
	}

	public void setSelectsMap(Map<String, String> selectsMap) {
		this.selectsMap = selectsMap;
	}

	
	
	
	
	
	
}