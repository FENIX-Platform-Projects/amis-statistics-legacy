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
package org.fao.fenix.web.modules.x.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.fao.fenix.web.modules.x.common.vo.XDescriptorVO;
import org.fao.fenix.web.modules.x.common.vo.XExplorerSearchParametersVO;
import org.fao.fenix.web.modules.x.common.vo.XResourceVO;
import org.fao.fenix.web.modules.x.common.vo.XUniqueValueVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface XService extends RemoteService {

	public void createDatasetRSS(boolean addUniqueValues) throws FenixGWTException;
	
	public void createTextRSS() throws FenixGWTException;
	
	public void createRasterRSS() throws FenixGWTException;
	
	public void createVectorRSS() throws FenixGWTException;
	
	public Map<String, String> findAllNodes() throws FenixGWTException;
	
	public List<XResourceVO> search(XExplorerSearchParametersVO pvo) throws FenixGWTException;
	
	public int getSearchSize(XExplorerSearchParametersVO pvo) throws FenixGWTException;
	
	public String requestUpdate(String url, String resourceType, int maxResults) throws FenixGWTException;
	
	public String requestDataset(String url, String localID, Map<Map<String, String>, Map<String, String>> filterMap, Map<String, XDescriptorVO> descriptorMap) throws FenixGWTException;
	
	public List<XDescriptorVO> findAllXDescriptorByXResourceID(Long xResourceID) throws FenixGWTException;
	
	public List<XUniqueValueVO> findAllXUniqueValuesByXResourceIDAndXDescriptorID(Long xResourceID, Long xDescriptorID) throws FenixGWTException;
	
	public void createXResource(List<Long> resourceID, String resourceType) throws FenixGWTException;
	
	public void delete(String resourceID) throws FenixGWTException;
	
	public void exportShapefile(Long dbFeatureLayerID) throws FenixGWTException;
	
	public String requestDBFeatureLayerChunkList(String url, String localResourceID) throws FenixGWTException;
	
	public List<String> findAllChunks(String localResourceID, String url) throws FenixGWTException;
	
	public String requestChunk(String url, String chunkPath, String localResourceID, int counter) throws FenixGWTException;
	
	public List<String> requestAllChunks(String url, List<String> chunkPaths, String localResourceID) throws FenixGWTException;
	
	public String mergeChunks(List<String> chunkPaths) throws FenixGWTException;
	
	public List<String> findAllDownloadedChunks(String localResourceID) throws FenixGWTException;
	
	public Long importDBFeatureLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException;
	
	public String deleteChunks(String url, String localID, Long importedLayerID, String layerType) throws FenixGWTException;
	
	public String deleteChunks(String url, String localID) throws FenixGWTException;
	
	public String requestText(String url, String localID) throws FenixGWTException;
	
	public String getFenixURL() throws FenixGWTException;
	
	public String requestShpFeatureLayerChunkList(String url, String localResourceID) throws FenixGWTException;
	
	public String requestRasterLayerChunkList(String url, String localResourceID) throws FenixGWTException;
	
	public Long importShpFeatureLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException;
	
	public Long importRasterLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException;
	
	public List<Long> checkEMail() throws FenixGWTException;
	
}