package org.fao.fenix.web.modules.udtable.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.olap.common.vo.OLAPChartResultVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableHeaderVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UDTableServiceAsync {

	public void getTable(List<UDTableFilterVO> filtersVO, String language, boolean addCode, AsyncCallback<UDTableVO> callback);
	
	public void getTable(List<UDTableFilterVO> filtersVO, String language, int startIndex, int elements, boolean addCode, AsyncCallback<UDTableVO> callback);
	
	public void createOLAPChartBeans(OLAPParametersVo p, AsyncCallback<List<OLAPChartResultVo>> callback);
	
	public void getTableHeaders(List<UDTableFilterVO> filtersVO, String language, AsyncCallback<List<UDTableHeaderVO>> callback);
	
	public void createGhostTables(AsyncCallback<String> callback);
	
	public void getDatasetDetails(Long resourceId, AsyncCallback<UDTableVO> callback);
	
	public void getDimensionValues(Long datasetId, String dimensionName, String lang, AsyncCallback<Map<String, String>> callback);
	
	public void getFilters(Long datasetId, AsyncCallback<List<UDTableFilterVO>> callback);
	
	public void getResultSetSizeForVOFilter(List<UDTableFilterVO> filtersVo, String language, AsyncCallback<Integer> callback);
}
