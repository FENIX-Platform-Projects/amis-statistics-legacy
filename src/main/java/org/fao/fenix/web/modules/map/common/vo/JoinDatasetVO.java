package org.fao.fenix.web.modules.map.common.vo;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class JoinDatasetVO implements IsSerializable {

	// TODO: there should be additional information of the dataset (to save and retrieve the layer)
	// 		 and probably 		 

	
	// this is the datasetview
	private String datasetViewName;
	
	// this is the this is the layer code for the projection
	private String layer;
	
	// this is the name of the produced joined layer (if it is null there will be generated a random name)
	private String layerJoinView;
	
	// by default the style is rendered on the quantity column
	private String sldStyleColumnName = "quantity";
	
	// by default the join is made on featuredcode of the datasetview
	private String joinDatasetColumnName = "featurecode";
	
	// by default is the join column of the layer (otherwise could be passed as parameter)
	private String joinLayerColumnName;

	
	// this is used in DataMapper
	private Map<String, Double> joinedValues;

	public String getDatasetViewName() {
		return datasetViewName;
	}

	public void setDatasetViewName(String datasetViewName) {
		this.datasetViewName = datasetViewName;
	}

	

	

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLayerJoinView() {
		return layerJoinView;
	}

	public void setLayerJoinView(String layerJoinView) {
		this.layerJoinView = layerJoinView;
	}

	public String getSldStyleColumnName() {
		return sldStyleColumnName;
	}

	public void setSldStyleColumnName(String sldStyleColumnName) {
		this.sldStyleColumnName = sldStyleColumnName;
	}

	public String getJoinDatasetColumnName() {
		return joinDatasetColumnName;
	}

	public void setJoinDatasetColumnName(String joinDatasetColumnName) {
		this.joinDatasetColumnName = joinDatasetColumnName;
	}

	public String getJoinLayerColumnName() {
		return joinLayerColumnName;
	}

	public void setJoinLayerColumnName(String joinLayerColumnName) {
		this.joinLayerColumnName = joinLayerColumnName;
	}

	public Map<String, Double> getJoinedValues() {
		return joinedValues;
	}

	public void setJoinedValues(Map<String, Double> joinedValues) {
		this.joinedValues = joinedValues;
	}

	
	
	

}
