/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security Information for Action Programme
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
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface MetadataService extends RemoteService {

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getMetadataNameValueMap(Long resourceId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getOrderedMetadataFieldList();

	@Secured( { "ROLE_USER" })
	public void updateMetadata(Map<String, String> metadata, Long resourceId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getMetadataFields(String resourceType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getMetadataFields(Long resourceId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getResourceMetadataFieldsMap(String resourceType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getMetadataFieldsMap(Long resourceId);

	@Secured( { "ROLE_USER" })
	public void rename(Long resourceId, String newName);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> findAllDatasetType();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DescriptorVO> getTemplateDatasetDescriptors(String templateDatasetName) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DataTypeModelData> findAllDataType();
	
	@Secured( { "ROLE_USER" })
	public String createMetadataFile(ResourceVO rvo, DatasetTypeVO dvo);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public MetadataVO findMetadataById(Long resourceId) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FeatureCodeSetModelData> findAllGeographicCodingSystems();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FieldMetadataVo> findAllResourceSpecificFields(String resourceType);
	
	@Secured( { "ROLE_USER" })
	public void backup() throws FenixGWTException;
	
	@Secured( { "ROLE_USER" })
	public void restore() throws FenixGWTException;
	
	public List<String> findAllCodingSystemTypes();
	
	public List<String> findAllCodingSystemNames();
	
	public List<ResourceVO> findMetadataByGAULCode(String gaulCode) throws FenixGWTException;
	
	public ResourceVO buildDBFeatureLayerResourceVO(Long dbFeatureLayerID) throws FenixGWTException;

}
