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
import com.google.gwt.user.client.rpc.RemoteService;

public interface ADAMService extends RemoteService {

	public List<CodeVo> findAll(String prefix, String codingSystemCode);
	
	public List<CodeVo> findChildren(String prefix, List<String> parents, String codingSystemCode);
	
	public Map<String, String> getKeyMessages(String code);
	
	public ADAMResultVO askADAM(ADAMQueryVO qvo);
	
	public String createReport(HashMap<String, ADAMQueryVO> qvos, ADAMReportBeanVO reportBean);
	
	public JoinDatasetVO joinDataset(ADAMQueryVO qvo);
	
	public Long createDonorMatrixViews();
	
	public List<DonorMatrixModel> getDonorMatrixRows(OLAPParametersVo params);
	
	public ADAMDonorMatrixVO populateEmptyAxesDonorMatrix(ADAMDonorMatrixVO vo, List<String> dates);
	
	public String exportExcelTable(String title, List<String> headers, List<List<String>> table, Boolean isAgroupedTable);
	
	public void cleanLayersAndViews(HashMap<String, String> datasetProjectionViews);
	
	public LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> getCountriesList(String codingSystemTitle);
	
	public ADAMDonorProfileVO getDonorProfileVO(String donorcode, String donorlabel, String selectedDataset);
	
	public List<Donor> getDonorsWithProfiles();
	
	public Map<String, String> getRecipientsForFAORegion(String regionCode);
	
	public String createExcelReport(LinkedHashMap<String, ADAMQueryVO> qvos,LinkedHashMap<String, OLAPParametersVo> matrixvos);
	
	public List<FAOSectorMatrixModel> getFAOSectorMatrixRows(OLAPParametersVo params);
	
	public OLAPParametersVo populateVOWithSectors(OLAPParametersVo params, String recipientCode);
	
	public ADAMResultVO createChartFromRVO(ADAMQueryVO qvo, ADAMResultVO rvo);
	
	public List<CodeVo> convertISO3toDatasetCS(List<String> iso3codes, String codingSystem, String langCode, ADAMSelectedDataset selectedDataset);
	
	public ADAMResultVO getResourcePartnerProfile(ADAMDonorProfileVO vo, String donorcode, String selectedDataset);
	
	
}