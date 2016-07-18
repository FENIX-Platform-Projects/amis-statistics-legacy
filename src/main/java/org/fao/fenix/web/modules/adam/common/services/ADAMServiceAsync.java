package org.fao.fenix.web.modules.adam.common.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.Donor;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.model.FAOSectorMatrixModel;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ADAMServiceAsync {

	public void findAll(String prefix, String codingSystemCode, AsyncCallback<List<CodeVo>> callback);
	
	public void findChildren(String prefix, List<String> parents, String codingSystemCode, AsyncCallback<List<CodeVo>> callback);
	
	public void getKeyMessages(String code, AsyncCallback<Map<String, String>> callback);
	
	public void askADAM(ADAMQueryVO qvo, AsyncCallback<ADAMResultVO> callback);
	
	public void createReport(HashMap<String, ADAMQueryVO> qvos, ADAMReportBeanVO reportBean, AsyncCallback callback);
	
	public void joinDataset(ADAMQueryVO qvo, AsyncCallback<JoinDatasetVO> callback);
	
	public void createDonorMatrixViews(AsyncCallback<Long> callback);

	public void getDonorMatrixRows(OLAPParametersVo params, AsyncCallback<List<DonorMatrixModel>> callback);
	
	public void populateEmptyAxesDonorMatrix(ADAMDonorMatrixVO vo, List<String> dates, AsyncCallback<ADAMDonorMatrixVO> callback);

	public void exportExcelTable(String title, List<String> headers, List<List<String>> table, Boolean isAgroupedTable, AsyncCallback<String> callback);
	
	public void cleanLayersAndViews(HashMap<String, String> datasetProjectionViews, AsyncCallback callback);
	
	public void getCountriesList(String codingSystemTitle, AsyncCallback<LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>>> callback);
	
	public void getDonorProfileVO(String donorcode, String donorlabel, String selectedDataset, AsyncCallback<ADAMDonorProfileVO> callback);
	
	public void getDonorsWithProfiles(AsyncCallback<List<Donor>> callback);
	
	public void getRecipientsForFAORegion(String regionCode, AsyncCallback<Map<String, String>> callback);
		
	public void createExcelReport(LinkedHashMap<String, ADAMQueryVO> qvos,LinkedHashMap<String, OLAPParametersVo> matrixvos, AsyncCallback<String> callback);

	public void getFAOSectorMatrixRows(OLAPParametersVo params, AsyncCallback<List<FAOSectorMatrixModel>> callback);
	
	public void populateVOWithSectors(OLAPParametersVo params, String recipientCode, AsyncCallback<OLAPParametersVo> callback);
	
	public void createChartFromRVO(ADAMQueryVO qvo, ADAMResultVO rvo, AsyncCallback<ADAMResultVO> callback);
	
	public void convertISO3toDatasetCS(List<String> iso3codes, String codingSystem, String langCode, ADAMSelectedDataset selectedDataset, AsyncCallback<List<CodeVo>> callback);

	
	public void getResourcePartnerProfile(ADAMDonorProfileVO vo, String donorcode, String selectedDataset, AsyncCallback<ADAMResultVO> callback);
	
}