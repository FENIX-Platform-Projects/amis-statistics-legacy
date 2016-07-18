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
import org.springframework.security.annotation.Secured;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;

public interface TableService extends RemoteService {

	public Map<String, List<String>> loadFilterCriteria(Long resourceViewID);
	
	public Long saveTableView(Long tableViewID, ResourceViewVO rvo, boolean saveAs) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getRecordSize(Long resourceId);
	
	@Secured( {"ROLE_USER" })
    public Long createNewTableResource(Long resourceId);
	
	@Secured( {"ROLE_USER" })
	public FenixResourceVo getNewResource(Long id) throws FenixGWTException;
	
	@Secured( {"ROLE_USER" })
	public Long saveTableView(Long datasetID, List<String> list);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Long getDatasetIdFromTableViewId(Long tableViewId);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getDimensionValues(Long datasetId, String dimensionName, String lang);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getDimensionValues(List<Long> datasetId, String dimensionName, String lang);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public TableVO getDatasetDetails(Long resourceId, String language);
		
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public PagingLoadResult<DatasetRowModel> getFilteredData(String datasetPrecision, PagingLoadConfig config, Long resourceId, List<String> headerList, Map<String, List<String>> filterSelection, String caller, String language);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getFilteredRecords(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String language);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getFilteredRecordsToExport(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String type, String language);
		
	@Secured( { "ROLE_USER" }) 
	public void saveDataset(Long datasetId, Map<String, Map<String, String>> modifiedRecords, Map<Integer, Map<String, String>> newRecords, Map<String, DimensionBeanVO> dimensions);
	
	
	@Secured( { "ROLE_USER" }) 
	public void deleteRow(List<String> rowIdList, String datasetType, Long datasetID);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" }) 
	public Boolean isCoreDataset(Long datasetId);	
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<TableFilterVO> getFilters(Long datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" }) 
	public Long getCountriesCrisisDatasetID();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" }) 
	public Long getUnfavourableProspectsDatasetID();	
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" }) 
	public String getMostRecentDate(String datasetType);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<List<String>>> getColumnsValues(Long datasetId, List<String> columnNames, String lang);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void createGhostTables();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DimensionsBeanVO> hasValidDimensions(String datasetId, List<String> dimensions, String lang);
	
	public String createExcel(Long datasetId, String type, String title, List<List<String>> table, String precision);
	
	public Long getDatasetByCode(String datasetCode);
}
