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
package org.fao.fenix.web.modules.olap.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.fsatmis.common.vo.FSATMISModelData;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OlapServiceAsync {

	public void findAllCoreDatasetType(AsyncCallback<List<String>> callback);
	
	public void createTable(OLAPParametersVo parameters, AsyncCallback<String> callback);
	
	public void findAllDatasetTypeDescriptors(String dataseType, AsyncCallback<Map<String, String>> callback);
	
	public void findAllCoreDataset(AsyncCallback<Map<String, String>> callback);
	
	public void findAllFSATMISDataset(AsyncCallback<Map<String, String>> callback);
	
	public void findDimensionValues(String datasetId, String dimension, AsyncCallback<List<String>> callback);
	
	public void findDimensionValuesWithLabels(String datasetId, String dimension, String header, AsyncCallback<Map<String, String>> callback);
	
	public void createReport(String report, String orientation, AsyncCallback<String> callback);
	
	public void getCodingHierarchyUncles(Long datasetId, String dimension, String header, String code, AsyncCallback<List<CodeVo>> callback);
	
	public void getCodingHierarchyFamily(Long datasetId, String dimension, String header, String code, AsyncCallback<List<CodeVo>> callback);
	
	public void createOLAPMap(OLAPParametersVo p, String geoDataType, String geoLabel, AsyncCallback<List<String>> callback);
	
	public void createOLAPMap(OLAPParametersVo p, String geoDataType, String geoLabel, Map<String, String> filters, String html, int intervals, String minColor, String maxColor, boolean showBaseLayer, AsyncCallback<List<String>> callback);
	
	public void createOlapCharts(OLAPParametersVo p, String html, AsyncCallback<List<String>> callback);
	
	public void findAllFSATMISModelData(PagingLoadConfig config, Long datasetId, AsyncCallback<PagingLoadResult<FSATMISModelData>> callback);
	
	public void createExcel(OLAPParametersVo params, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findGeoColumnName(Long datasetId, AsyncCallback<String> callback);
	
	public void getFSATMISModelData(Long datasetId, AsyncCallback<List<FSATMISModelData>> callback);
	
	public void getChartData(OLAPParametersVo p, String html, AsyncCallback<List<Map<String, Map<String, Double>>>> callback);
	
	public void getChartsTitles(OLAPParametersVo p, String html, AsyncCallback<List<String>> callback);
	
	public void getDimensionValues(Long datasetID, String columnHeader, String langCode, AsyncCallback<List<UniqueValueVO>> callback);
	
	public void saveOlap(OLAPParametersVo olapParametersVo, AsyncCallback<Long> callback);
	
	public void getNewOlapResource(Long id, AsyncCallback<FenixResourceVo> callback);
	
	public void loadOlap(Long resourceViewVO, AsyncCallback<List<DatasetVO>> callback);
	
	public void loadOlapUserSettings(Long resourceViewVO, AsyncCallback<ResourceViewVO> callback);
	
	public void getIFrame(Long resourceViewID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void getSavedDimensionValues(Long resourceViewID, String contentDescriptor, String langCode, AsyncCallback<List<UniqueValueVO>> callback) throws FenixGWTException;
	
	public void load(Long mtID, AsyncCallback<OLAPParametersVo> callback) throws FenixGWTException;
	
	public void showUnSelectedValues(OLAPParametersVo pvo, String langCode, AsyncCallback<Map<String, List<UniqueValueVO>>> callback) throws FenixGWTException;
	
	public void addToReport(OLAPParametersVo pvo, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void getHTML(Long resourceViewID, AsyncCallback<String> callback) throws FenixGWTException;
	
}