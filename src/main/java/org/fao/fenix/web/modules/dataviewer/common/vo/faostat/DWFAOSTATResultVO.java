package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartVO;
import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DWFAOSTATResultVO implements IsSerializable {
	
	// Coding System
	
	private LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	
	
	// OTUPUT TYPE
	private String text;
	
	private String output;

	private String typeOfOutput;

	private String title;
	
	private LinkedHashMap<String, Map<String, Double>> chartValues = new LinkedHashMap<String, Map<String,Double>>();
	
	private LinkedHashMap<String, String> serieMeasurementUnits = new LinkedHashMap<String, String>(); 
	
	private LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>(); 
	
//	private HighChartVO highChartVO = new HighChartVO();
	
	private List<String> tableHeaders = new ArrayList<String>();
	
	private List<List<String>> tableContents = new ArrayList<List<String>>();
	
	private String tableHTML;
	
	private Long rows;
	
	private String measurementUnit;
	
	private List<Integer> quantitiesIdx;
	
	private Integer summaryTableColumn;
	
	private Integer groupingTableColumn;
	
	private Boolean groupTable = false;
	
	/**
	 * Date range parameters
	 */
	private String maxDateLimit;
	private Integer timeIntervalSpan;
	
	/** CODING SYSTEMS **/
	
	// list of codes
	private List<DWCodesModelData> codes;
	
	// code, related codes
	private LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> codesHierachy;
	
	private String exportFilename;
	
	private Boolean sortXaxis = false;
	
	// HighChart
	
	//HighChartVO highChart;
	
	
	/** QUERY PARAMETERS **/
	
	// SEARCH
	private Map<String, String> domains = new HashMap<String, String>();
	
	private Map<String, String> groups = new HashMap<String, String>();
	
	private Map<String, String> areas = new HashMap<String, String>();
	
	private Map<String, String> years = new HashMap<String, String>();
	
	private Map<String, String> items = new HashMap<String, String>();
	
	private Map<String, String> elements = new HashMap<String, String>();
	
	private Map<String, String> elementsList = new HashMap<String, String>();
	
	// entry point code
	LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResuts;
	
	String searchType;
	
	
	//Notes
	List<NoteVO> notes;
	
	// chart
	String palette;
	
	String url;
	
	FAOSTATMapsVO mapsVO = new FAOSTATMapsVO();	
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LinkedHashMap<String, Map<String, Double>> getChartValues() {
		return chartValues;
	}

	public void setChartValues(
			LinkedHashMap<String, Map<String, Double>> chartValues) {
		this.chartValues = chartValues;
	}

	public List<String> getTableHeaders() {
		return tableHeaders;
	}

	public void setTableHeaders(List<String> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}

	public List<List<String>> getTableContents() {
		return tableContents;
	}

	public void setTableContents(List<List<String>> tableContents) {
		this.tableContents = tableContents;
	}

	public String getTableHTML() {
		return tableHTML;
	}

	public void setTableHTML(String tableHTML) {
		this.tableHTML = tableHTML;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public List<Integer> getQuantitiesIdx() {
		return quantitiesIdx;
	}

	public void setQuantitiesIdx(List<Integer> quantitiesIdx) {
		this.quantitiesIdx = quantitiesIdx;
	}

	public Integer getSummaryTableColumn() {
		return summaryTableColumn;
	}

	public void setSummaryTableColumn(Integer summaryTableColumn) {
		this.summaryTableColumn = summaryTableColumn;
	}

	public Integer getGroupingTableColumn() {
		return groupingTableColumn;
	}

	public void setGroupingTableColumn(Integer groupingTableColumn) {
		this.groupingTableColumn = groupingTableColumn;
	}

	public Boolean getGroupTable() {
		return groupTable;
	}

	public void setGroupTable(Boolean groupTable) {
		this.groupTable = groupTable;
	}

	public LinkedHashMap<String, String> getValues() {
		return values;
	}

	public void setValues(LinkedHashMap<String, String> values) {
		this.values = values;
	}

	public List<DWCodesModelData> getCodes() {
		return codes;
	}

	public void setCodes(List<DWCodesModelData> codes) {
		this.codes = codes;
	}

	public LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> getCodesHierachy() {
		return codesHierachy;
	}

	public void setCodesHierachy(
			LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> codesHierachy) {
		this.codesHierachy = codesHierachy;
	}
	
	public String getMaxDateLimit() {
		return maxDateLimit;
	}

	public void setMaxDateLimit(String maxDateLimit) {
		this.maxDateLimit = maxDateLimit;
	}
	
	public Integer getTimeIntervalSpan() {
		return timeIntervalSpan;
	}

	public void setTimeIntervalSpan(Integer timeIntervalSpan) {
		this.timeIntervalSpan = timeIntervalSpan;
	}

	/*public HighChartVO getHighChart() {
		return highChart;
	}

	public void setHighChart(HighChartVO highChart) {
		this.highChart = highChart;
	}*/
	public String getExportFilename() {
		return exportFilename;
	}

	public void setExportFilename(String exportFilename) {
		this.exportFilename = exportFilename;
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

	public Map<String, String> getElementsList() {
		return elementsList;
	}

	public void setElementsList(Map<String, String> elementsList) {
		this.elementsList = elementsList;
	}

	public LinkedHashMap<String, FAOSTATSearchResultsVO> getSearchResuts() {
		return searchResuts;
	}

	public void setSearchResuts(
			LinkedHashMap<String, FAOSTATSearchResultsVO> searchResuts) {
		this.searchResuts = searchResuts;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Boolean getSortXaxis() {
		return sortXaxis;
	}

	public void setSortXaxis(Boolean sortXaxis) {
		this.sortXaxis = sortXaxis;
	}

	public LinkedHashMap<String, String> getSerieMeasurementUnits() {
		return serieMeasurementUnits;
	}

	public void setSerieMeasurementUnits(
			LinkedHashMap<String, String> serieMeasurementUnits) {
		this.serieMeasurementUnits = serieMeasurementUnits;
	}

	public LinkedHashMap<String, String> getMeasurementUnits() {
		return measurementUnits;
	}

	public void setMeasurementUnits(LinkedHashMap<String, String> measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

//	public HighChartVO getHighChartVO() {
//		return highChartVO;
//	}
//
//	public void setHighChartVO(HighChartVO highChartVO) {
//		this.highChartVO = highChartVO;
//	}
	
	public List<NoteVO> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteVO> notes) {
		this.notes = notes;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FAOSTATMapsVO getMapsVO() {
		return mapsVO;
	}

	public void setMapsVO(FAOSTATMapsVO mapsVO) {
		this.mapsVO = mapsVO;
	}
}
