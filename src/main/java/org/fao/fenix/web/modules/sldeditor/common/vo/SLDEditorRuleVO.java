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
package org.fao.fenix.web.modules.sldeditor.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SLDEditorRuleVO implements IsSerializable {

	public SLDEditorConstants type;
	
	public String ruleName;
	
	public Map<String, String> fontParameters;
	
	public Map<String, String> strokeParameters;
	
	public Map<String, String> fillParameters;
	
	public List<Interval> fillParametersIntervals;
	
	public String extremeValues;
	
	public boolean is;
	
	public String filterPropertyName;
	
	public String filterPropertyValue;
	
	public String labelPropertyName;

	public String labelIntervalPropertyName;
	
	public SLDEditorConstants comparison;
	
	public boolean createInterval;
	
	//Rasters
	
	//Slider Opacity
	public Map<String, String> sliderOpacityOnlyMap;
	
	//Color Map
	
	public Map<String, String> colorMapItem;
	
	public List<ColorMapBigGrid> itemsBigGrid;
	
	public List<ColorMapSmallGrid> itemsSmallGrid;
	
	//Channel Selection
	public Map<String, String> channelSelectionMap;
	
	//Contrast Enhancement
	
	public Map<String, String> contrastEnhancementMap;
	
	public List<RGBContrast> rgbcontrast;
	
	public List<ColorMapSmallGrid> graycontrast;
	
	public boolean colorMapSelected;
	
	public boolean colorMapGlobalOpacity;
	
	//Creating Sld Editor Interface
	
	public String typeSld;
	
	//For import SLD
	
	public String nameUserLayer;
	
	public String titleUserStyle;
	
	public String abstractUserStyle;

	public String contrastValueType;
	
	public String gridType;
	
	public boolean colorMapSlider;
	
	public double minTot;
	
	public double maxTot;
	
	public String minColor;
	
	public String maxColor;
	
	public boolean colorMapFound;
	
	public SLDEditorRuleVO() {
		type = SLDEditorConstants.LINE;
		fontParameters = new HashMap<String, String>();
		strokeParameters = new HashMap<String, String>();
		fillParameters = new HashMap<String, String>();
		comparison = SLDEditorConstants.PropertyIsEqualTo;
		
		//Raster
		sliderOpacityOnlyMap = new HashMap<String, String>();
		colorMapItem = new HashMap<String, String>();
		channelSelectionMap = new HashMap<String, String>();
		contrastEnhancementMap = new HashMap<String, String>();
		colorMapSelected = false;
		colorMapGlobalOpacity = false;
		colorMapSlider = false;
		rgbcontrast = new ArrayList<RGBContrast>();
		colorMapFound = false;
	}

	public SLDEditorConstants getType() {
		return type;
	}

	public void setType(SLDEditorConstants type) {
		this.type = type;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public String getExtremeValues() {
		return extremeValues;
	}

	public void setExtremeValues(String extremeValues) {
		this.extremeValues = extremeValues;
	}

	public Map<String, String> getFontParameters() {
		return fontParameters;
	}

	public void setFontParameters(Map<String, String> fontParameters) {
		this.fontParameters = fontParameters;
	}

	public Map<String, String> getStrokeParameters() {
		return strokeParameters;
	}

	public void setStrokeParameters(Map<String, String> strokeParameters) {
		this.strokeParameters = strokeParameters;
	}

	public Map<String, String> getFillParameters() {
		return fillParameters;
	}

	public void setFillParameters(Map<String, String> fillParameters) {
		this.fillParameters = fillParameters;
	}

	public boolean isIs() {
		return is;
	}

	public void setIs(boolean is) {
		this.is = is;
	}

	public String getFilterPropertyName() {
		return filterPropertyName;
	}

	public void setFilterPropertyName(String filterPropertyName) {
		this.filterPropertyName = filterPropertyName;
	}

	public String getFilterPropertyValue() {
		return filterPropertyValue;
	}

	public void setFilterPropertyValue(String filterPropertyValue) {
		this.filterPropertyValue = filterPropertyValue;
	}

	public String getLabelPropertyName() {
		return labelPropertyName;
	}

	public void setLabelPropertyName(String labelPropertyName) {
		this.labelPropertyName = labelPropertyName;
	}

	public SLDEditorConstants getComparison() {
		return comparison;
	}

	public void setComparison(SLDEditorConstants comparison) {
		this.comparison = comparison;
	}
	
	public List<Interval> getFillParametersIntervals() {
		return fillParametersIntervals;
	}

	public void setFillParametersIntervals(List<Interval> fillParametersIntervals) {
		this.fillParametersIntervals = fillParametersIntervals;
	}
	
	public boolean isCreateInterval() {
		return createInterval;
	}

	public void setCreateInterval(boolean createInterval) {
		this.createInterval = createInterval;
	}

	public String getLabelIntervalPropertyName() {
		return labelIntervalPropertyName;
	}

	public void setLabelIntervalPropertyName(String labelIntervalPropertyName) {
		this.labelIntervalPropertyName = labelIntervalPropertyName;
	}
	
	public Map<String, String> getSliderOpacityOnlyMap() {
		return sliderOpacityOnlyMap;
	}

	public Map<String, String> getColorMapItem() {
		return colorMapItem;
	}
	
	public List<ColorMapBigGrid> getItemsColorMapBigGrids() {
		return itemsBigGrid;
	}

	public void setItemsColorMapBigGrids(List<ColorMapBigGrid> itemsBigGrid) {
		this.itemsBigGrid = itemsBigGrid;
	}
	
	public List<ColorMapSmallGrid> getItemsSmallGrid() {
		return itemsSmallGrid;
	}

	public void setItemsSmallGrid(List<ColorMapSmallGrid> itemsSmallGrid) {
		this.itemsSmallGrid = itemsSmallGrid;
	}

	public Map<String, String> getChannelSelectionMap() {
		return channelSelectionMap;
	}

	public void setChannelSelectionMap(Map<String, String> channelSelectionMap) {
		this.channelSelectionMap = channelSelectionMap;
	}
	
	public Map<String, String> getContrastEnhancementMap() {
		return contrastEnhancementMap;
	}

	public void setContrastEnhancementMap(Map<String, String> contrastEnhancementMap) {
		this.contrastEnhancementMap = contrastEnhancementMap;
	}
	
	public List<RGBContrast> getRgbcontrast() {
		return rgbcontrast;
	}

	public void setRgbcontrast(List<RGBContrast> rgbcontrast) {
		this.rgbcontrast = rgbcontrast;
	}

	public List<ColorMapSmallGrid> getGraycontrast() {
		return graycontrast;
	}

	public void setGraycontrast(List<ColorMapSmallGrid> graycontrast) {
		this.graycontrast = graycontrast;
	}
	public boolean isColorMapSelected() {
		return colorMapSelected;
	}

	public void setColorMapSelected(boolean colorMapSelected) {
		this.colorMapSelected = colorMapSelected;
	}
	
	public boolean isColorMapGlobalOpacity() {
		return colorMapGlobalOpacity;
	}

	public void setColorMapGlobalOpacity(boolean colorMapGlobalOpacity) {
		this.colorMapGlobalOpacity = colorMapGlobalOpacity;
	}

	public String getTypeSld() {
		return typeSld;
	}

	public void setTypeSld(String typeSld) {
		this.typeSld = typeSld;
	}
	
	public String getNameUserLayer() {
		return nameUserLayer;
	}

	public void setNameUserLayer(String nameUserLayer) {
		this.nameUserLayer = nameUserLayer;
	}
	
	public String getTitleUserStyle() {
		return titleUserStyle;
	}

	public void setTitleUserStyle(String titleUserStyle) {
		this.titleUserStyle = titleUserStyle;
	}
	
	public String getAbstractUserStyle() {
		return abstractUserStyle;
	}

	public void setAbstractUserStyle(String abstractUserStyle) {
		this.abstractUserStyle = abstractUserStyle;
	}
	
	public String getContrastValueType() {
		return contrastValueType;
	}

	public void setContrastValueType(String contrastValueType) {
		this.contrastValueType = contrastValueType;
	}

	public String getGridType() {
		return gridType;
	}

	public void setGridType(String gridType) {
		this.gridType = gridType;
	}
	
	public boolean isColorMapSlider() {
		return colorMapSlider;
	}

	public void setColorMapSlider(boolean colorMapSlider) {
		this.colorMapSlider = colorMapSlider;
	}
	
	public double getMinTot() {
		return minTot;
	}

	public void setMinTot(double minTot) {
		this.minTot = minTot;
	}

	public double getMaxTot() {
		return maxTot;
	}

	public void setMaxTot(double maxTot) {
		this.maxTot = maxTot;
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

	public boolean isColorMapFound() {
		return colorMapFound;
	}

	public void setColorMapFound(boolean colorMapFound) {
		this.colorMapFound = colorMapFound;
	}		
}