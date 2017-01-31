/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.cpf.common.vo;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.highcharts.HighChartVO;
import org.fao.fenix.web.modules.cpf.common.model.CPFCodesModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CPFResultVO implements IsSerializable {

	// Coding System

	private LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();


	// OTUPUT TYPE
	private String output;

	private String typeOfOutput;

	private String title;
    private String aggregationLabel;
	private String timerangeLabel;

	private String imageWidth;
	private String imageHeight;
	private String imagePath;

    private String htmlWidth;
	private String htmlHeight;

	private HighChartVO highChartVO = new HighChartVO();
	
	private String text;

	
	/** Dataset Details **/

	private String fenixDatasetCode;
	private String fenixDatasetTableName;
	private String database;


	private LinkedHashMap<String, Map<String, Double>> chartValues = new LinkedHashMap<String, Map<String,Double>>();

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
	private List<CPFCodesModelData> codes;

	// code, related codes
	private LinkedHashMap<CPFCodesModelData, List<CPFCodesModelData>> codesHierachy;

	private String exportFilename;

	// HighChart

	HighChartVO highChart;


	String source;

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


	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getImageHeight() {
			return imageHeight;
		}

		public void setImageHeight(String imageHeight) {
			this.imageHeight = imageHeight;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAggregationLabel() {
			return aggregationLabel;
	}

	public void setAggregationLabel(String aggregationLabel) {
			this.aggregationLabel = aggregationLabel;
	}

	public String getTimerangeLabel() {
			return timerangeLabel;
	}

	public void setTimerangeLabel(String timerangeLabel) {
			this.timerangeLabel = timerangeLabel;
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

	public List<CPFCodesModelData> getCodes() {
		return codes;
	}

	public void setCodes(List<CPFCodesModelData> codes) {
		this.codes = codes;
	}

	public LinkedHashMap<CPFCodesModelData, List<CPFCodesModelData>> getCodesHierachy() {
		return codesHierachy;
	}

	public void setCodesHierachy(
			LinkedHashMap<CPFCodesModelData, List<CPFCodesModelData>> codesHierachy) {
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

	public HighChartVO getHighChart() {
		return highChart;
	}

	public void setHighChart(HighChartVO highChart) {
		this.highChart = highChart;
	}

	public String getExportFilename() {
		return exportFilename;
	}

	public void setExportFilename(String exportFilename) {
		this.exportFilename = exportFilename;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFenixDatasetTableName() {
		return fenixDatasetTableName;
	}

	public void setFenixDatasetTableName(String fenixDatasetTableName) {
		this.fenixDatasetTableName = fenixDatasetTableName;
	}

	public String getFenixDatasetCode() {
		return fenixDatasetCode;
	}

	public void setFenixDatasetCode(String fenixDatasetCode) {
		this.fenixDatasetCode = fenixDatasetCode;
	}
	
	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public HighChartVO getHighChartVO() {
		return highChartVO;
	}

	public void setHighChartVO(HighChartVO highChartVO) {
		this.highChartVO = highChartVO;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}