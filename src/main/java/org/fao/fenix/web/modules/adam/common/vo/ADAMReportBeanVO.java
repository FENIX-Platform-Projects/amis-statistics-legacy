package org.fao.fenix.web.modules.adam.common.vo;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ADAMReportBeanVO implements IsSerializable {
	
	String reportType;
	
	String rightTitle;
	
	String littleLeftTitle;

	String leftTitle;
	
	String centerTitle;
	
	String viewDescription;
	
	String reportNote;
	
	List<String> donors;
	
	List<String> countries;
		
	
	HashMap<String, ADAMResultVO> resources = new HashMap<String, ADAMResultVO>();
	
	ADAMDonorProfileVO donorProfileVO;
	
	
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public HashMap<String, ADAMResultVO> getResources() {
		return resources;
	}
	
	public void setResources(HashMap<String, ADAMResultVO> resources) {
		this.resources = resources;
	}
	
	public String getRightTitle() {
		return rightTitle;
	}

	public void setRightTitle(String rightTitle) {
		this.rightTitle = rightTitle;
	}

	public String getLeftTitle() {
		return leftTitle;
	}

	public void setLeftTitle(String leftTitle) {
		this.leftTitle = leftTitle;
	}

	public String getCenterTitle() {
		return centerTitle;
	}

	public void setCenterTitle(String centerTitle) {
		this.centerTitle = centerTitle;
	}

	public String getViewDescription() {
		return viewDescription;
	}

	public void setViewDescription(String viewDescription) {
		this.viewDescription = viewDescription;
	}

	public String getReportNote() {
		return reportNote;
	}

	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}

	public ADAMDonorProfileVO getDonorProfileVO() {
		return donorProfileVO;
	}

	public void setDonorProfileVO(ADAMDonorProfileVO donorProfileVO) {
		this.donorProfileVO = donorProfileVO;
	}

	public List<String> getDonors() {
		return donors;
	}

	public void setDonors(List<String> donors) {
		this.donors = donors;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getLittleLeftTitle() {
		return littleLeftTitle;
	}

	public void setLittleLeftTitle(String littleLeftTitle) {
		this.littleLeftTitle = littleLeftTitle;
	}

	
	
}
