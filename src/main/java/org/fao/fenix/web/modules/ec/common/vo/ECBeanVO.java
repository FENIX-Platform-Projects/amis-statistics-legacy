package org.fao.fenix.web.modules.ec.common.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ECBeanVO implements IsSerializable {

	private List<String> createChartFileNames;
	
	private String countryCode;
	
	private String countryLabel;
	
	private String reportDateString;
	
	private String reportDateField;
	
	private String language = "EN";
	
	private Boolean isHaitiEmergency = false;
	
	private Boolean exportPDF = false;
	
	
//	String showPriceLegend = "GIEWS";
//	
//	String showFoodBalanceLegend = "GIEWS";
//	
//	String showVegetationConditionLegend = "JRC";
//	
//	String unhcrDate = "22/09/2010";
//	
//	String viewData;
//	
//	String reportType = "STANDARD";
	
	String showPriceLegend;
	
	String showFoodBalanceLegend;
	
	String showVegetationConditionLegend;
	
	String unhcrDate;
	
	String viewData;
	
	String reportType;
	

	public List<String> getCreateChartFileNames() {
		return createChartFileNames;
	}

	public void setCreateChartFileNames(List<String> createChartFileNames) {
		this.createChartFileNames = createChartFileNames;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryLabel() {
		return countryLabel;
	}

	public void setCountryLabel(String countryLabel) {
		this.countryLabel = countryLabel;
	}

	public Boolean getIsHaitiEmergency() {
		return isHaitiEmergency;
	}

	public void setIsHaitiEmergency(Boolean isHaitiEmergency) {
		this.isHaitiEmergency = isHaitiEmergency;
	}

	public String getReportDateString() {
		return reportDateString;
	}

	public void setReportDateString(String reportDateString) {
		this.reportDateString = reportDateString;
	}

	public String getReportDateField() {
		return reportDateField;
	}

	public void setReportDateField(String reportDateField) {
		this.reportDateField = reportDateField;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getExportPDF() {
		return exportPDF;
	}

	public void setExportPDF(Boolean exportPDF) {
		this.exportPDF = exportPDF;
	}

	public String getShowPriceLegend() {
		return showPriceLegend;
	}

	public void setShowPriceLegend(String showPriceLegend) {
		this.showPriceLegend = showPriceLegend;
	}

	public String getShowFoodBalanceLegend() {
		return showFoodBalanceLegend;
	}

	public void setShowFoodBalanceLegend(String showFoodBalanceLegend) {
		this.showFoodBalanceLegend = showFoodBalanceLegend;
	}

	public String getShowVegetationConditionLegend() {
		return showVegetationConditionLegend;
	}

	public void setShowVegetationConditionLegend(
			String showVegetationConditionLegend) {
		this.showVegetationConditionLegend = showVegetationConditionLegend;
	}

	public String getUnhcrDate() {
		return unhcrDate;
	}

	public void setUnhcrDate(String unhcrDate) {
		this.unhcrDate = unhcrDate;
	}

	public String getViewData() {
		return viewData;
	}

	public void setViewData(String viewData) {
		this.viewData = viewData;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	

	
	
	
}
