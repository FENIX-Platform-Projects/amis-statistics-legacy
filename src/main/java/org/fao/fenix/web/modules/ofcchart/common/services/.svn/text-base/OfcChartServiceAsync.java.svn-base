package org.fao.fenix.web.modules.ofcchart.common.services;



import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.ParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.client.rpc.AsyncCallback;





public interface OfcChartServiceAsync {	
	
	public void createChart(OLAPParametersVo p, AsyncCallback<OfcChartBeanVO> callback);
	
	public void createChart(List<ParametersVo> p, AsyncCallback<OfcChartBeanVO> callback);
	
	public void getChart(List<OfcParametersVo> parameter, AsyncCallback<List<OfcChartBeanVO>> callback);
	
//	public void saveTmpChart(String image, AsyncCallback<String> callback);
	
	public void saveChart(String image, AsyncCallback<String> callback);
	
	public void saveCharts(List<String> image, AsyncCallback<List<String>> callback);
	
	public void waitForChart(AsyncCallback callback);
	
	public void getXCommonDimensions(List<DatasetVO> datasets, AsyncCallback<HashMap<String, List<DimensionsBeanVO>>> callback);
	
	public void getFirstCode(String datasetId, String header, String lang, AsyncCallback<HashMap<String, String>> callback);
	
}