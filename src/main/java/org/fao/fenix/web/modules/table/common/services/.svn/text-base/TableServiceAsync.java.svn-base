package org.fao.fenix.web.modules.table.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TableServiceAsync {

	public void loadFilterCriteria(Long resourceViewID, AsyncCallback<Map<String, List<String>>> callback);
	
	public void saveTableView(Long tableViewID, ResourceViewVO rvo, boolean saveAs, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void getRecordSize(Long resourceId, AsyncCallback<Integer> callback);
	
	public void createNewTableResource(Long resourceId, AsyncCallback<Long> callback);
	
	public void getNewResource(Long id, AsyncCallback<FenixResourceVo> callback) throws FenixGWTException;
	
	public void saveTableView(Long datasetID, List<String> list, AsyncCallback<Long> callback);
	
	public void getDatasetIdFromTableViewId(Long tableViewId, AsyncCallback<Long> callback);
	

	public void  getDimensionValues(Long datasetId, String dimensionName, String lang, AsyncCallback<Map<String, String>> callback);
	
	public void  getDimensionValues(List<Long> datasetId, String dimensionName, String lang, AsyncCallback<Map<String, String>> callback);
	
		
	public void getDatasetDetails(Long resourceId, String language, AsyncCallback<TableVO> callback);
	
	public void getFilteredData(String datasetPrecision, PagingLoadConfig config, Long datasetId, List<String> headerList, Map<String, List<String>> filterSelection, String caller, String language, AsyncCallback<PagingLoadResult<DatasetRowModel>> callback);
	
	public void getFilteredRecords(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String language, AsyncCallback<List<List<String>>> callback);
	
	public void getFilteredRecordsToExport(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String type, String language, AsyncCallback<List<List<String>>> callback);
	
	public void saveDataset(Long datasetId, Map<String, Map<String, String>> updatedRecords, Map<Integer, Map<String, String>> newRecords, Map<String, DimensionBeanVO> dimensions, AsyncCallback<?> callback);
	
	public void deleteRow(List<String> rowIdList, String datasetType, Long datasetID, AsyncCallback<?> callback);
	
	public void isCoreDataset(Long datasetId, AsyncCallback<Boolean> callback);	
		
    public void getCountriesCrisisDatasetID(AsyncCallback<Long> callback);
	
	public void getUnfavourableProspectsDatasetID(AsyncCallback<Long> callback);	
	
	public void getMostRecentDate(String datasetType, AsyncCallback<String> callback);

	public void getFilters(Long datasetId, AsyncCallback<List<TableFilterVO>> callback);
	
	public void getColumnsValues(Long datasetId, List<String> columnNames, String lang, AsyncCallback<List<List<List<String>>>> callback);

	public void createGhostTables(AsyncCallback<Void> callback);
	
	public void hasValidDimensions(String datasetId, List<String> dimensions, String lang, AsyncCallback<List<DimensionsBeanVO>> callback);

	public void createExcel(Long datasetId, String type, String title, List<List<String>> table, String precision, AsyncCallback<String> callback);
	
	public void getDatasetByCode(String datasetCode, AsyncCallback<Long> callback);

}
