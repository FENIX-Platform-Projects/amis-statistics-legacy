package org.fao.fenix.web.modules.map.common.vo;

import java.util.List;
import java.util.Map;

import org.fao.fenix.map.quickmap.iface.LayerValuesProvider;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class StaticProjectionVO implements IsSerializable {
	
//	LayerValuesProvider layerValuesProvider;
	
	String gaulLevel = "layer_gaul0";
	
	private Map<String, Double> projections;
	
	private int intervals = 6;
	
	private String minColor = "RED";
	
	private String maxColor = "GREEN";
	
	private List<String> lowerLayers;
	
	private List<String> upperLayers;
	
	private Boolean showLabels = true;
	
	private Boolean maxExtension = true;
	
	private int height = 400;
//	
	private int width = 800;
	
//	private int height = 800;
//	
//	private int width = 600;

	

	public Map<String, Double> getProjections() {
		return projections;
	}

	public void setProjections(Map<String, Double> projections) {
		this.projections = projections;
	}

	public int getIntervals() {
		return intervals;
	}

	public void setIntervals(int intervals) {
		this.intervals = intervals;
	}

	public String getMinColor() {
		return minColor;
	}

	public void setMinColor(String minColor) {
		this.minColor = minColor;
	}

	public String getMaxColor() {
		return maxColor;
	}

	public void setMaxColor(String maxColor) {
		this.maxColor = maxColor;
	}

	public Boolean getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(Boolean showLabels) {
		this.showLabels = showLabels;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

//	public LayerValuesProvider getLayerValuesProvider() {
//		return layerValuesProvider;
//	}

//	public void setLayerValuesProvider(LayerValuesProvider layerValuesProvider) {
//		this.layerValuesProvider = layerValuesProvider;
//	}

	public List<String> getLowerLayers() {
		return lowerLayers;
	}

	public void setLowerLayers(List<String> lowerLayers) {
		this.lowerLayers = lowerLayers;
	}

	public List<String> getUpperLayers() {
		return upperLayers;
	}

	public void setUpperLayers(List<String> upperLayers) {
		this.upperLayers = upperLayers;
	}

	public String getGaulLevel() {
		return gaulLevel;
	}

	public void setGaulLevel(String gaulLevel) {
		this.gaulLevel = gaulLevel;
	}

	public Boolean getMaxExtension() {
		return maxExtension;
	}

	public void setMaxExtension(Boolean maxExtension) {
		this.maxExtension = maxExtension;
	}
	
	

}
