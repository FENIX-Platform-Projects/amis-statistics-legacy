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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface XServiceAsync {

	@SuppressWarnings("unchecked")
	public void createDatasetRSS(boolean addUniqueValues, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void createTextRSS(AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void createRasterRSS(AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void createVectorRSS(AsyncCallback callback) throws FenixGWTException;
	
	public void findAllNodes(AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	
	public void search(XExplorerSearchParametersVO pvo, AsyncCallback<List<XResourceVO>> callback) throws FenixGWTException;
	
	public void getSearchSize(XExplorerSearchParametersVO pvo, AsyncCallback<Integer> callback) throws FenixGWTException;
	
	public void requestUpdate(String url, String resourceType, int maxResults, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void requestDataset(String url, String localID, Map<Map<String, String>, Map<String, String>> filterMap, Map<String, XDescriptorVO> descriptorMap, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findAllXDescriptorByXResourceID(Long xResourceID, AsyncCallback<List<XDescriptorVO>> callback) throws FenixGWTException;
	
	public void findAllXUniqueValuesByXResourceIDAndXDescriptorID(Long xResourceID, Long xDescriptorID, AsyncCallback<List<XUniqueValueVO>> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void createXResource(List<Long> resourceID, String resourceType, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void delete(String resourceID, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void exportShapefile(Long dbFeatureLayerID, AsyncCallback callback) throws FenixGWTException;
	
	public void requestDBFeatureLayerChunkList(String url, String localResourceID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findAllChunks(String localResourceID, String url, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	public void requestChunk(String url, String chunkPath, String localResourceID, int counter, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void requestAllChunks(String url, List<String> chunkPaths, String localResourceID, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	public void mergeChunks(List<String> chunkPaths, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findAllDownloadedChunks(String localResourceID, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	public void importDBFeatureLayer(String url, String localID, String mergedFilePath, AsyncCallback<Long> callback) throws FenixGWTException, XLayerException;
	
	public void deleteChunks(String url, String localID, Long importedLayerID, String layerType, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void deleteChunks(String url, String localID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void requestText(String url, String localID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void getFenixURL(AsyncCallback<String> callback) throws FenixGWTException;
	
	public void requestShpFeatureLayerChunkList(String url, String localResourceID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void requestRasterLayerChunkList(String url, String localResourceID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void importShpFeatureLayer(String url, String localID, String mergedFilePath, AsyncCallback<Long> callback) throws FenixGWTException, XLayerException;
	
	public void importRasterLayer(String url, String localID, String mergedFilePath, AsyncCallback<Long> callback) throws FenixGWTException, XLayerException;
	
	public void checkEMail(AsyncCallback<List<Long>> callback) throws FenixGWTException;
	
}