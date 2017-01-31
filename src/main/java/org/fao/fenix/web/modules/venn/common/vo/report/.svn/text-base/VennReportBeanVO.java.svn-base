package org.fao.fenix.web.modules.venn.common.vo.report;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennReportBeanVO implements IsSerializable {
	
	// countries
	LinkedHashMap<String, String> countries = new LinkedHashMap<String, String>();

	private VennReportGraphBeanVO vennGraphReportBean = new VennReportGraphBeanVO();
	
	private LinkedHashMap<String, VennReportTablesBeanVO> vennTabsTablesReportBean = new LinkedHashMap<String, VennReportTablesBeanVO>();
	
	
	// this for used for the standard report
	private HashMap<String, VennCountryBeanVO> vennCountryBeanVO  = new HashMap<String, VennCountryBeanVO>();
	
	// charts to plot put in the report
	private LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
	
	
	private LinkedHashMap<String, VennReportTableValuesBeanVO> vennTablesReportBean = new LinkedHashMap<String, VennReportTableValuesBeanVO>();
	
	
	// maps 
	
	private LinkedHashMap<String, VennReportMapBeanVO> vennMapReportBean = new LinkedHashMap<String, VennReportMapBeanVO>();
	
	
	private String reportType;

	public VennReportGraphBeanVO getVennGraphReportBean() {
		return vennGraphReportBean;
	}

	public void setVennGraphReportBean(VennReportGraphBeanVO vennGraphReportBean) {
		this.vennGraphReportBean = vennGraphReportBean;
	}

	

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public HashMap<String, VennCountryBeanVO> getVennCountryBeanVO() {
		return vennCountryBeanVO;
	}



	public LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getVennChartsBeanVO() {
		return vennChartsBeanVO;
	}

	public void setVennChartsBeanVO(LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO) {
		this.vennChartsBeanVO = vennChartsBeanVO;
	}

	public LinkedHashMap<String, String> getCountries() {
		return countries;
	}

	public void setCountries(LinkedHashMap<String, String> countries) {
		this.countries = countries;
	}

	public LinkedHashMap<String, VennReportTablesBeanVO> getVennTabsTablesReportBean() {
		return vennTabsTablesReportBean;
	}

	public void setVennTabsTablesReportBean(
			LinkedHashMap<String, VennReportTablesBeanVO> vennTabsTablesReportBean) {
		this.vennTabsTablesReportBean = vennTabsTablesReportBean;
	}

	

	public void setVennCountryBeanVO(
			HashMap<String, VennCountryBeanVO> vennCountryBeanVO) {
		this.vennCountryBeanVO = vennCountryBeanVO;
	}

	public LinkedHashMap<String, VennReportMapBeanVO> getVennMapReportBean() {
		return vennMapReportBean;
	}

	public void setVennMapReportBean(
			LinkedHashMap<String, VennReportMapBeanVO> vennMapReportBean) {
		this.vennMapReportBean = vennMapReportBean;
	}

	public LinkedHashMap<String, VennReportTableValuesBeanVO> getVennTablesReportBean() {
		return vennTablesReportBean;
	}

	public void setVennTablesReportBean(
			LinkedHashMap<String, VennReportTableValuesBeanVO> vennTablesReportBean) {
		this.vennTablesReportBean = vennTablesReportBean;
	}

	




	
}
