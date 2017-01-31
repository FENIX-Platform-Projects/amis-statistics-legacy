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
package org.fao.fenix.web.modules.olap.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DescriptorViewVO extends DescriptorVO implements IsSerializable {

	private Boolean isCombined;
	
	private Boolean isSelected;
	
	private Boolean isDimensionVisible;
	
	private Boolean isPlotDimension;
	
	/** X and Y for charts, X, Y, Z, W for OLAP's, not applicable for tables */
	private String axis;
	
	private List<DatasetVO> datasets;
	
	private List<UniqueValueVO> values;
	
	public DescriptorViewVO() {
		super();
		this.setIsCombined(false);
		this.setIsSelected(true);
		this.setIsDimensionVisible(true);
		this.setIsPlotDimension(false);
	}
	
	public void addDataset(DatasetVO dataset) {
		if (this.datasets == null)
			this.datasets = new ArrayList<DatasetVO>();
		this.datasets.add(dataset);
	}
	
	public void addUniqueValue(UniqueValueVO uniqueValue) {
		if (this.values == null)
			this.values = new ArrayList<UniqueValueVO>();
		this.values.add(uniqueValue);
	}

	public Boolean getIsCombined() {
		return isCombined;
	}

	public void setIsCombined(Boolean isCombined) {
		this.isCombined = isCombined;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Boolean getIsDimensionVisible() {
		return isDimensionVisible;
	}

	public void setIsDimensionVisible(Boolean isDimensionVisible) {
		this.isDimensionVisible = isDimensionVisible;
	}

	public Boolean getIsPlotDimension() {
		return isPlotDimension;
	}

	public void setIsPlotDimension(Boolean isPlotDimension) {
		this.isPlotDimension = isPlotDimension;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public Boolean isCombined() {
		return isCombined;
	}

	public void setCombined(Boolean isCombined) {
		this.isCombined = isCombined;
	}

	public Boolean isSelected() {
		return isSelected;
	}

	public void setSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Boolean isPlotDimension() {
		return isPlotDimension;
	}

	public Boolean isDimensionVisible() {
		return isDimensionVisible;
	}

	public void setDimensionVisible(Boolean isDimensionVisible) {
		this.isDimensionVisible = isDimensionVisible;
	}

	public void setPlotDimension(Boolean isPlotDimension) {
		this.isPlotDimension = isPlotDimension;
	}

	public List<DatasetVO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetVO> datasets) {
		this.datasets = datasets;
	}

	public List<UniqueValueVO> getValues() {
		return values;
	}

	public void setValues(List<UniqueValueVO> values) {
		this.values = values;
	}
	
}