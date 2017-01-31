package org.fao.fenix.web.modules.r.common.vo;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RUserSettingsVO implements IsSerializable {
	
	private Long datasetID;
	
	private RFunctionVO rFunctionVO;

	/** Map<label, dataType> */
	private Map<String, String> mainDimension;
	
	/** Map<code, label> */
	private Map<String, String> mainDimensionValues;
	
	/** List<Map<label, dataType>> */
	private Map<String, String> otherDimensions;
	
	/** Map<label, Map<code, label>> */
	private Map<String, Map<String, String>> otherDimensionsValues;
	
	/** Map<optionName, optionValue> */
	private Map<String, String> options;
	
	public RUserSettingsVO() {
		rFunctionVO = new RFunctionVO();
		mainDimension = new HashMap<String, String>();
		mainDimensionValues = new HashMap<String, String>();
		otherDimensions = new HashMap<String, String>();
		otherDimensionsValues = new HashMap<String, Map<String,String>>();
		options = new HashMap<String, String>();
	}

	public RFunctionVO getrFunctionVO() {
		return rFunctionVO;
	}

	public void setrFunctionVO(RFunctionVO rFunctionVO) {
		this.rFunctionVO = rFunctionVO;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public Map<String, String> getMainDimension() {
		return mainDimension;
	}

	public void setMainDimension(Map<String, String> mainDimension) {
		this.mainDimension = mainDimension;
	}

	public Map<String, String> getMainDimensionValues() {
		return mainDimensionValues;
	}

	public void setMainDimensionValues(Map<String, String> mainDimensionValues) {
		this.mainDimensionValues = mainDimensionValues;
	}

	public Map<String, String> getOtherDimensions() {
		return otherDimensions;
	}

	public void setOtherDimensions(Map<String, String> otherDimensions) {
		this.otherDimensions = otherDimensions;
	}

	public Map<String, Map<String, String>> getOtherDimensionsValues() {
		return otherDimensionsValues;
	}

	public void setOtherDimensionsValues(
			Map<String, Map<String, String>> otherDimensionsValues) {
		this.otherDimensionsValues = otherDimensionsValues;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	
}