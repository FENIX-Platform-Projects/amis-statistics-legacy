package org.fao.fenix.web.modules.chartdesigner.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ChartDesignerService extends RemoteService {

	public ResourceViewVO loadDataset(List<ResourceChildModel> models, String langCode) throws FenixGWTException;
	
	public List<UniqueValueVO> loadValues(List<Long> datasetIDs, List<DescriptorViewVO> descriptorViewVOs, String langCode) throws FenixGWTException;
	
	public String viewChart(List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public String calculateAxisStep(List<Long> datasetIDs) throws FenixGWTException;
	
	public Long save(List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public Long update(Long chartDesignID, List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public String openAsOLAP(List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public String exportAsExcel(List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public List<String> exportAsCSV(List<ChartDesignerParametersVO> p) throws FenixGWTException;
	
	public Map<String, String> exportToWebsite(Long chartDesignID) throws FenixGWTException;
	
	public FenixResourceVo getNewOlapResource(Long id) throws FenixGWTException;
	
	public List<ChartDesignerParametersVO> load(Long chartDesignID) throws FenixGWTException;
	
	public void recreateCharts() throws FenixGWTException;
	
	public List<String> findSavedCodes(Long datasetID, Long chartDesignID, String contentDescriptor) throws FenixGWTException;
	
	public String addToReport(List<ChartDesignerParametersVO> vos) throws FenixGWTException;
	
}