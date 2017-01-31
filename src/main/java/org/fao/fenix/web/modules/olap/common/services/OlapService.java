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
import com.google.gwt.user.client.rpc.RemoteService;

public interface OlapService extends RemoteService {

	public List<String> findAllCoreDatasetType();
	
	public String createTable(OLAPParametersVo parameters);
	
	public Map<String, String> findAllDatasetTypeDescriptors(String dataseType);
	
	public Map<String, String> findAllCoreDataset();
	
	public Map<String, String> findAllFSATMISDataset();
	
	public List<String> findDimensionValues(String datasetId, String dimension);
	
	public Map<String, String> findDimensionValuesWithLabels(String datasetId, String dimension, String header);
	
	public String createReport(String report, String orientation);
	
	public List<CodeVo> getCodingHierarchyUncles(Long datasetId, String dimension, String header, String code);
	
	public List<CodeVo> getCodingHierarchyFamily(Long datasetId, String dimension, String header, String code);
	
	public List<String> createOLAPMap(OLAPParametersVo p, String geoDataType, String geoLabel);
	
	public List<String> createOLAPMap(OLAPParametersVo p, String geoDataType, String geoLabel, Map<String, String> filters, String html, int intervals, String minColor, String maxColor, boolean showBaseLayer) throws FenixGWTException;
	
	public List<String> createOlapCharts(OLAPParametersVo p, String html);
	
	public PagingLoadResult<FSATMISModelData> findAllFSATMISModelData(final PagingLoadConfig config, Long datasetId);
	
	public String createExcel(OLAPParametersVo params) throws FenixGWTException;
	
	public String findGeoColumnName(Long datasetId);
	
	public List<FSATMISModelData> getFSATMISModelData(Long datasetId);
	
	public List<Map<String, Map<String, Double>>> getChartData(OLAPParametersVo p, String html);
	
	public List<String> getChartsTitles(OLAPParametersVo p, String html);
	
	public List<UniqueValueVO> getDimensionValues(Long datasetID, String columnHeader, String langCode);
	
	public Long saveOlap(OLAPParametersVo olapParametersVo);
	
	public FenixResourceVo getNewOlapResource(Long id);
	
	public List<DatasetVO> loadOlap(Long resourceViewVO);
	
	public ResourceViewVO loadOlapUserSettings(Long resourceViewVO);
	
	public String getIFrame(Long resourceViewID) throws FenixGWTException;
	
	public List<UniqueValueVO> getSavedDimensionValues(Long resourceViewID, String contentDescriptor, String langCode) throws FenixGWTException;
	
	public OLAPParametersVo load(Long mtID) throws FenixGWTException;
	
	public Map<String, List<UniqueValueVO>> showUnSelectedValues(OLAPParametersVo pvo, String langCode) throws FenixGWTException;
	
	public String addToReport(OLAPParametersVo pvo) throws FenixGWTException;
	
	public String getHTML(Long resourceViewID) throws FenixGWTException;
	
}