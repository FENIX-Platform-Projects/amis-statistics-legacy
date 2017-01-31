package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FAOSTATVisualizeFilter implements IsSerializable {
	
	String filterType; // this is used for the coding system
	
	Boolean isMultiSelection = false;
	
	// TODO: this is not in place yes, but should be the coding system that should be called
	//       where there is the multiselection
	String multiselectionFilterType;
	
	// this variable is used to fill the drop down when the interface is build (with the DefaultCodes)
	List<DWCodesModelData> defaultCodes;
	
	// this are the list of the codes (if it's not used the coding system but a list of fixed values)
	List<DWCodesModelData> codes;
	
	Boolean useCodingSystem = true;
	
	Boolean disaggregated = false;
	
	// used for the itemlevel (or aggregations)
	String level;
	
	// this is for the country based
	String startYear;
	String endYear;
	
	Map<String, String> domains = new LinkedHashMap<String, String>();
	
	String width;
	
	String height;

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public Boolean getIsMultiSelection() {
		return isMultiSelection;
	}

	public void setIsMultiSelection(Boolean isMultiSelection) {
		this.isMultiSelection = isMultiSelection;
	}

	public String getMultiselectionFilterType() {
		return multiselectionFilterType;
	}

	public void setMultiselectionFilterType(String multiselectionFilterType) {
		this.multiselectionFilterType = multiselectionFilterType;
	}

	public List<DWCodesModelData> getDefaultCodes() {
		return defaultCodes;
	}

	public void setDefaultCodes(List<DWCodesModelData> defaultCodes) {
		this.defaultCodes = defaultCodes;
	}

	public List<DWCodesModelData> getCodes() {
		return codes;
	}

	public void setCodes(List<DWCodesModelData> codes) {
		this.codes = codes;
	}

	public Boolean getUseCodingSystem() {
		return useCodingSystem;
	}

	public void setUseCodingSystem(Boolean useCodingSystem) {
		this.useCodingSystem = useCodingSystem;
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

	public Boolean getDisaggregated() {
		return disaggregated;
	}

	public void setDisaggregated(Boolean disaggregated) {
		this.disaggregated = disaggregated;
	}

	public Map<String, String> getDomains() {
		return domains;
	}

	public void setDomains(Map<String, String> domains) {
		this.domains = domains;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	

}
