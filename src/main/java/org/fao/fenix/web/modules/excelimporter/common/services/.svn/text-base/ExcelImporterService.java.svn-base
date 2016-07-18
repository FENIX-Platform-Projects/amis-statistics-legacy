package org.fao.fenix.web.modules.excelimporter.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ExcelImporterService extends RemoteService {

	public List<CodingNameModelData> findAllCodingSystems(String codingType);
	
	public List<ResourceChildModel> findSimilarDatasets(String sourceName, String sourceRegion, String periodTypeCode, List<DescriptorVO> descriptorVOs);
	
	public String exportCSV(Long datasetID) throws FenixGWTException;
	
	public String exportXML(Long datasetID) throws FenixGWTException;
	
	public String exportZIP(Long datasetID) throws FenixGWTException;
	
}