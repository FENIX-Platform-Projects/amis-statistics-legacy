package org.fao.fenix.web.modules.amis.common.vo;

import java.util.List;

import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AMISFilterVO implements IsSerializable {

	String filterType; // this is used for the coding system

	Boolean isMultiSelection = false;

	// TODO: this is not in place yes, but should be the coding system that should be called
	//       where there is the multiselection
	String multiselectionFilterType;

	// this variable is used to fill the drop down when the interface is build (with the DefaultCodes)
	List<AMISCodesModelData> defaultCodes;

	// this are the list of the codes (if it's not used the coding system but a list of fixed values)
	List<AMISCodesModelData> codes;

	Boolean useCodingSystem = true;

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

	public List<AMISCodesModelData> getDefaultCodes() {
		return defaultCodes;
	}

	public void setDefaultCodes(List<AMISCodesModelData> defaultCodes) {
		this.defaultCodes = defaultCodes;
	}

	public List<AMISCodesModelData> getCodes() {
		return codes;
	}

	public void setCodes(List<AMISCodesModelData> codes) {
		this.codes = codes;
	}

	public Boolean getUseCodingSystem() {
		return useCodingSystem;
	}

	public void setUseCodingSystem(Boolean useCodingSystem) {
		this.useCodingSystem = useCodingSystem;
	}








}
