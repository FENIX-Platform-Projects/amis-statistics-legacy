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

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class ResourceViewVO extends ResourceVO implements IsSerializable {

	private String type;

	private String query;

	private List<DatasetVO> datasets;
	
	private List<DescriptorViewVO> descriptorViews;

	private List<ResourceViewSettingVO> settings;

	/**
	 * Very specific for OLAP resources. It stores the whole OLAP table in HTML
	 * format for quick recovery. The user has to update this field if the OLAP
	 * table changes.
	 */
	private String olapHTML;
	
	public void addDataset(DatasetVO dataset) {
		if (this.datasets == null)
			this.datasets = new ArrayList<DatasetVO>();
		this.datasets.add(dataset);
	}
	
	public void addDescriptorView(DescriptorViewVO descriptorView) {
		if (this.descriptorViews == null)
			this.descriptorViews = new ArrayList<DescriptorViewVO>();
		this.descriptorViews.add(descriptorView);
	}
	
	public void addResourceViewSetting(ResourceViewSettingVO resourceViewSetting) {
		if (this.settings == null)
			this.settings = new ArrayList<ResourceViewSettingVO>();
		this.settings.add(resourceViewSetting);
	}
	
	/** Aggregation function used to create the OLAP */
	private String olapFunction;

	public List<DescriptorViewVO> getDescriptorViews() {
		return descriptorViews;
	}

	public void setDescriptorViews(List<DescriptorViewVO> descriptorViews) {
		this.descriptorViews = descriptorViews;
	}

	public String getOlapFunction() {
		return olapFunction;
	}

	public void setOlapFunction(String olapFunction) {
		this.olapFunction = olapFunction;
	}

	public String getOlapHTML() {
		return olapHTML;
	}

	public void setOlapHTML(String olapHTML) {
		this.olapHTML = olapHTML;
	}

	public List<DatasetVO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetVO> datasets) {
		this.datasets = datasets;
	}

	public List<ResourceViewSettingVO> getSettings() {
		return settings;
	}

	public void setSettings(List<ResourceViewSettingVO> settings) {
		this.settings = settings;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}