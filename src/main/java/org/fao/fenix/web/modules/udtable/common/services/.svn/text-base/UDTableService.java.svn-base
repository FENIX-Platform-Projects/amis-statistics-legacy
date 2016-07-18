package org.fao.fenix.web.modules.udtable.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.olap.common.vo.OLAPChartResultVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableHeaderVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface UDTableService extends RemoteService {

	public UDTableVO getTable(List<UDTableFilterVO> filtersVO, String language, boolean addCode);
	
	public UDTableVO getTable(List<UDTableFilterVO> filtersVO, String language, int startIndex, int elements, boolean addCode);
	
	public List<OLAPChartResultVo> createOLAPChartBeans(OLAPParametersVo p);
	
	public List<UDTableHeaderVO> getTableHeaders(List<UDTableFilterVO> filtersVO, String language);
	
	public String createGhostTables();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public UDTableVO getDatasetDetails(Long resourceId);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Map<String, String> getDimensionValues(Long datasetId, String dimensionName, String lang);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<UDTableFilterVO> getFilters(Long datasetId);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getResultSetSizeForVOFilter(List<UDTableFilterVO> filtersVo, String language);
	
}
