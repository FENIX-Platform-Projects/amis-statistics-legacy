package org.fao.fenix.web.modules.venn.common.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennConfigurationBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableValuesBeanVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface VennService extends RemoteService {
	
	public VennBeanVO calculateIntersections(VennResultsVO results, List<String> countryCodes, String language);
	
	public List<VennIframeVO> createVenn(VennBeanVO vennBean, String vennType);
	
	public List<VennIframeVO> createHighlightedVenn(VennBeanVO vennBean, String vennType, String intersection);
	
	public HashMap<String, String> findOrganizationsType(String language);
	
	public HashMap<String, String> findOrganizations(String organizationType, String language);
	
	public HashMap<String, String> findSOIFA(String organizationType, String organization, String language);
	
	public HashMap<String, String> findStrategiesLvl1(String organizationType, String organization, String soifa, String language);
	
	public HashMap<String, String> findStrategiesLvl2(String organizationType, String organization, String soifa, String codeLvl1, String language);

	public LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getChart(VennBeanVO bean, String servletType, String chartType);
	
	public VennResultsVO getDACCodes(HashMap<String, String> aCodes, HashMap<String, String> bCodes,HashMap<String, String> cCodes, String language);
	
	public List<CodeVo> getCountriesList(String language);
	
	public List<String> createVennMap(VennBeanVO vennBean, String geoDataType, String geoLabel, int intervals, String minColor, String maxColor, boolean showBaseLayer) throws FenixGWTException;

	public HashMap<String, HashMap<String, String>> getDAC_FAO_SO_Codes(HashMap<String, String> dacCodes);

	public VennConfigurationBeanVO getConfiguration(String xml, Boolean isFile);

	public String vennReport(VennReportBeanVO vennReportBean);
	
	public LinkedHashMap<String, VennReportTableValuesBeanVO> createSummaryReportTables(VennBeanVO bean);
	
	public HashMap<String, HashMap<String, String>> getStrategicObjectives(HashMap<String, String> codes, String language);
			
}
