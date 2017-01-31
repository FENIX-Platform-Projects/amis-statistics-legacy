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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChartDesignerServiceAsync {

	public void loadDataset(List<ResourceChildModel> models, String langCode, AsyncCallback<ResourceViewVO> callback) throws FenixGWTException;
	
	public void loadValues(List<Long> datasetIDs, List<DescriptorViewVO> descriptorViewVOs, String langCode, AsyncCallback<List<UniqueValueVO>> callback) throws FenixGWTException;
	
	public void viewChart(List<ChartDesignerParametersVO> p, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void calculateAxisStep(List<Long> datasetIDs, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void save(List<ChartDesignerParametersVO> p, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void update(Long chartDesignID, List<ChartDesignerParametersVO> p, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void openAsOLAP(List<ChartDesignerParametersVO> p, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void exportAsExcel(List<ChartDesignerParametersVO> p, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void exportAsCSV(List<ChartDesignerParametersVO> p, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	public void exportToWebsite(Long chartDesignID, AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	
	public void getNewOlapResource(Long id, AsyncCallback<FenixResourceVo> callback) throws FenixGWTException;
	
	public void load(Long chartDesignID, AsyncCallback<List<ChartDesignerParametersVO>> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void recreateCharts(AsyncCallback callback) throws FenixGWTException;
	
	public void findSavedCodes(Long datasetID, Long chartDesignID, String contentDescriptor, AsyncCallback<List<String>> callback) throws FenixGWTException;
	
	public void addToReport(List<ChartDesignerParametersVO> vos, AsyncCallback<String> callback) throws FenixGWTException;
	
}