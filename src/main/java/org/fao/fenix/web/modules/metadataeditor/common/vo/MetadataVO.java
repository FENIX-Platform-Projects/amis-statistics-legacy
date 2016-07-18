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
package org.fao.fenix.web.modules.metadataeditor.common.vo;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.FeatureCodeSetModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MetadataVO implements IsSerializable {

	private String resourceType;
	
	private ResourceVO resourceVo;
	
	private DatasetTypeVO datasetTypeVo;
	
	private GaulModelData gaulModelData;
	
	private GaulModelData sourceGaulModelData;
	
	private GaulModelData providerGaulModelData;
	
	private CategoryModelData categoryModelData;
	
	private FeatureCodeSetModelData featureCodeSetModelData;

	private FieldMetadataVo fieldMetadataVos[];
	
	private Map<FieldMetadataVo, String> resourceSpecificFieldMap;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public ResourceVO getResourceVo() {
		return resourceVo;
	}

	public void setResourceVo(ResourceVO resourceVo) {
		this.resourceVo = resourceVo;
	}

	public GaulModelData getGaulModelData() {
		return gaulModelData;
	}

	public void setGaulModelData(GaulModelData gaulModelData) {
		this.gaulModelData = gaulModelData;
	}

	public CategoryModelData getCategoryModelData() {
		return categoryModelData;
	}

	public void setCategoryModelData(CategoryModelData categoryModelData) {
		this.categoryModelData = categoryModelData;
	}

	public GaulModelData getSourceGaulModelData() {
		return sourceGaulModelData;
	}

	public void setSourceGaulModelData(GaulModelData sourceGaulModelData) {
		this.sourceGaulModelData = sourceGaulModelData;
	}

	public DatasetTypeVO getDatasetTypeVo() {
		return datasetTypeVo;
	}

	public void setDatasetTypeVo(DatasetTypeVO datasetTypeVo) {
		this.datasetTypeVo = datasetTypeVo;
	}

	public FeatureCodeSetModelData getFeatureCodeSetModelData() {
		return featureCodeSetModelData;
	}

	public void setFeatureCodeSetModelData(FeatureCodeSetModelData featureCodeSetModelData) {
		this.featureCodeSetModelData = featureCodeSetModelData;
	}

	public GaulModelData getProviderGaulModelData() {
		return providerGaulModelData;
	}

	public void setProviderGaulModelData(GaulModelData providerGaulModelData) {
		this.providerGaulModelData = providerGaulModelData;
	}

	public FieldMetadataVo[] getFieldMetadataVos() {
		return fieldMetadataVos;
	}

	public void setFieldMetadataVos(FieldMetadataVo[] fieldMetadataVos) {
		this.fieldMetadataVos = fieldMetadataVos;
	}

	public Map<FieldMetadataVo, String> getResourceSpecificFieldMap() {
		return resourceSpecificFieldMap;
	}

	public void setResourceSpecificFieldMap(Map<FieldMetadataVo, String> resourceSpecificFieldMap) {
		if (this.resourceSpecificFieldMap == null)
			this.resourceSpecificFieldMap = new HashMap<FieldMetadataVo, String>();
		this.resourceSpecificFieldMap = resourceSpecificFieldMap;
	}
	
}