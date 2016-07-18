package org.fao.fenix.web.modules.venn.common.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennConfigurationBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableValuesBeanVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface VennServiceAsync {
	
	public void calculateIntersections(VennResultsVO results, List<String> countryCodes, String language, AsyncCallback<VennBeanVO> callback);
	
	public void createVenn(VennBeanVO vennBean, String vennType, AsyncCallback<List<VennIframeVO>> callback);
	
	public void createHighlightedVenn(VennBeanVO vennBean, String vennType, String intersection, AsyncCallback<List<VennIframeVO>> callback);
	
	public void findOrganizationsType(String language, AsyncCallback<HashMap<String, String>> callback);
	
	public void findOrganizations(String organizationType, String language, AsyncCallback<HashMap<String, String>> callback);
	
	public void findSOIFA(String organizationType, String organization, String language, AsyncCallback<HashMap<String, String>> callback);
	
	public void findStrategiesLvl1(String organizationType, String organization, String soifa, String language, AsyncCallback<HashMap<String, String>> callback);

	public void findStrategiesLvl2(String organizationType, String organization, String soifa, String codeLvl1, String language, AsyncCallback<HashMap<String, String>> callback);
	
	public void getChart(VennBeanVO bean, String servletType, String chartType, AsyncCallback<LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>>> callback);
	
	public void getDACCodes(HashMap<String, String> aCodes, HashMap<String, String> bCodes,HashMap<String, String> cCodes, String language,  AsyncCallback<VennResultsVO> callback);
	
	public void getCountriesList(String language, AsyncCallback<List<CodeVo>> callback);
		
	public void createVennMap(VennBeanVO vennBean, String geoDataType, String geoLabel, int intervals, String minColor, String maxColor, boolean showBaseLayer, AsyncCallback<List<String>> callback);

	public void getDAC_FAO_SO_Codes(HashMap<String, String> dacCodes, AsyncCallback<HashMap<String, HashMap<String, String>>> callback);
	
	public void  getConfiguration(String xml, Boolean isFile, AsyncCallback<VennConfigurationBeanVO> callback);
	
	public void vennReport(VennReportBeanVO vennReportBean, AsyncCallback<String> callback);
	
	public void createSummaryReportTables(VennBeanVO bean, AsyncCallback<LinkedHashMap<String, VennReportTableValuesBeanVO>> callback);
	
	public void getStrategicObjectives(HashMap<String, String> codes, String language, AsyncCallback<HashMap<String, HashMap<String, String>>> callback);
	
}
