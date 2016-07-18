package org.fao.fenix.web.modules.ofcchart.common.services;



import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.ParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.client.rpc.RemoteService;




public interface OfcChartService extends RemoteService {

	public OfcChartBeanVO createChart(OLAPParametersVo p);
	
	public OfcChartBeanVO createChart(List<ParametersVo> p);
	
	public List<OfcChartBeanVO> getChart(List<OfcParametersVo> parameters);

	public String saveChart(String image);
	
//	public String saveTmpChart(String image);
	
	public List<String> saveCharts(List<String> images);
	
	public void waitForChart();
	
	public HashMap<String, List<DimensionsBeanVO>> getXCommonDimensions(List<DatasetVO> datasets);
	
	public HashMap<String, String> getFirstCode(String datasetId, String header, String lang);
	
}