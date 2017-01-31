package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OfcParametersVo implements IsSerializable {
	
	private DatasetVO dataset;
	
	/** Core, flex, index **/
	private String DatasetType;
	
	private HashMap<String, String> xLabels;
	
	private HashMap<String, String> yLabels;
	
	private List<String> measuramentUnit;
	
	private DimensionsBeanVO xDimension;
	
	private DimensionsBeanVO yDimension;
		
	private List<OfcFilterVo> filters;
	
	
	public OfcParametersVo() {
		filters = new ArrayList<OfcFilterVo>();
		xLabels = new HashMap<String, String>();
		yLabels = new HashMap<String, String>();
		measuramentUnit = new ArrayList<String>();
		xDimension = new DimensionsBeanVO();
		yDimension = new DimensionsBeanVO();
	}
	
	


	public DatasetVO getDataset() {
		return dataset;
	}

	public void setDataset(DatasetVO dataset) {
		this.dataset = dataset;
	}

	public HashMap<String, String> getxLabels() {
		return xLabels;
	}

	public void setxLabels(HashMap<String, String> xLabels) {
		this.xLabels = xLabels;
	}

	public HashMap<String, String> getyLabels() {
		return yLabels;
	}

	public void setyLabels(HashMap<String, String> yLabels) {
		this.yLabels = yLabels;
	}

	public List<String> getMeasuramentUnit() {
		return measuramentUnit;
	}

	public void setMeasuramentUnit(List<String> measuramentUnit) {
		this.measuramentUnit = measuramentUnit;
	}

	public DimensionsBeanVO getxDimension() {
		return xDimension;
	}

	public void setxDimension(DimensionsBeanVO xDimension) {
		this.xDimension = xDimension;
	}

	public DimensionsBeanVO getyDimension() {
		return yDimension;
	}

	public void setyDimension(DimensionsBeanVO yDimension) {
		this.yDimension = yDimension;
	}

	public List<OfcFilterVo> getFilters() {
		return filters;
	}

	public void setFilters(List<OfcFilterVo> filters) {
		this.filters = filters;
	}


	public String getDatasetType() {
		return DatasetType;
	}


	public void setDatasetType(String datasetType) {
		DatasetType = datasetType;
	}

}