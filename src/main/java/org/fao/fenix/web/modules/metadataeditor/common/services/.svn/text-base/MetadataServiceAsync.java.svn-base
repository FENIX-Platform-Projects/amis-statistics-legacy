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
package org.fao.fenix.web.modules.metadataeditor.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DatasetTypeVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.FieldMetadataVo;
import org.fao.fenix.web.modules.metadataeditor.common.vo.MetadataVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.DataTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.FeatureCodeSetModelData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetadataServiceAsync {

	public void getMetadataNameValueMap(Long resourceId, AsyncCallback<Map<String, String>> callback);

	public void getOrderedMetadataFieldList(AsyncCallback<List<String>> callback);
	
	@SuppressWarnings("unchecked")
	public void updateMetadata(Map<String, String> metadata, Long resourceId, AsyncCallback callback);
	
	public void getMetadataFields(String resourceType, AsyncCallback<List<String>> callback);
	
    public void getMetadataFields(Long resourceId, AsyncCallback<List<String>> callback);

    public void getResourceMetadataFieldsMap(String resourceType, AsyncCallback<Map<String, String>> callback);
	
	public void getMetadataFieldsMap(Long resourceId, AsyncCallback<Map<String, String>> callback);
		
	@SuppressWarnings("unchecked")
	public void rename(Long resourceId, String newName, AsyncCallback callback);
	
	public void findAllDatasetType(AsyncCallback<List<String>> callback);
	
	public void getTemplateDatasetDescriptors(String templateDatasetName, AsyncCallback<List<DescriptorVO>> callback) throws FenixGWTException;
	
	public void findAllDataType(AsyncCallback<List<DataTypeModelData>> callback);
	
	public void createMetadataFile(ResourceVO rvo, DatasetTypeVO dvo, AsyncCallback<String> callback);
	
	public void findMetadataById(Long resourceId, AsyncCallback<MetadataVO> callback) throws FenixGWTException;
	
	public void findAllGeographicCodingSystems(AsyncCallback<List<FeatureCodeSetModelData>> callback);
	
	public void findAllResourceSpecificFields(String resourceType, AsyncCallback<List<FieldMetadataVo>> callback);
	
	@SuppressWarnings("unchecked")
	public void backup(AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void restore(AsyncCallback callback);
	
	public void findAllCodingSystemTypes(AsyncCallback<List<String>> callback);
	
	public void findAllCodingSystemNames(AsyncCallback<List<String>> callback);
	
	public void findMetadataByGAULCode(String gaulCode, AsyncCallback<List<ResourceVO>> callback);
	
	public void buildDBFeatureLayerResourceVO(Long dbFeatureLayerID, AsyncCallback<ResourceVO> callback) throws FenixGWTException;
	
}
