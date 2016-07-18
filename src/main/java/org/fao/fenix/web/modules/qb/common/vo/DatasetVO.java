package org.fao.fenix.web.modules.qb.common.vo;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DatasetVO implements IsSerializable {
	private String datasetTitle;
	private Date dateLastUpdate;
	private String periodTypeCode;
	private int dimensionSize;
	private Map<String, DimensionBeanVO> dimensionDescriptorMap = new LinkedHashMap<String, DimensionBeanVO>();
	private String region;
	private String datasetType;
	private String datasetExplicitType;
	private String language = "EN";
	private int aliasIndex;
	
	/* core or flex */
	public String getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(String datasetType) {
		this.datasetType = datasetType;
	}
	
    /* e.g. UnfavourableProspects*/
	public String getDatasetExplicitType() {
		return datasetExplicitType;
	}

	public void setDatasetExplicitType(String datasetExplicitType) {
		this.datasetExplicitType = datasetExplicitType;
	}

	public String getDatasetTitle() {
		return datasetTitle;
	}

	public void setDatasetTitle(String datasetTitle) {
		this.datasetTitle = datasetTitle;
	}

	public Date getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(Date dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}

	public String getPeriodTypeCode() {
		return periodTypeCode;
	}

	public void setPeriodTypeCode(String periodTypeCode) {
		this.periodTypeCode = periodTypeCode;
	}

	public int getDimensionSize() {
		return dimensionSize;
	}

	public void setDimensionSize(int dimensionSize) {
		this.dimensionSize = dimensionSize;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
		
	// DimensionDescriptorMap key = column_id (e.g column1,column2 etc) 
	//                        value = DimensionBeanVO
    public Map<String, DimensionBeanVO> getDimensionDescriptorMap() {
		return dimensionDescriptorMap;
	}

	public void setDimensionDescriptorMap(Map<String, DimensionBeanVO> dimensionDescriptorMap) {
		this.dimensionDescriptorMap = dimensionDescriptorMap;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getAliasIndex() {
		return aliasIndex;
	}

	public void setAliasIndex(int index) {
		this.aliasIndex = index;
	}
	
	
}
