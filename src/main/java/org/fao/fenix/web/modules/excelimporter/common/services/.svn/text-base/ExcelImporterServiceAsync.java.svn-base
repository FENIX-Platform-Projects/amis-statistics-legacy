package org.fao.fenix.web.modules.excelimporter.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ExcelImporterServiceAsync {

	public void findAllCodingSystems(String codingType, AsyncCallback<List<CodingNameModelData>> callback);
	
	public void findSimilarDatasets(String sourceName, String sourceRegion, String periodTypeCode, List<DescriptorVO> descriptorVOs, AsyncCallback<List<ResourceChildModel>> callback);
	
	public void exportCSV(Long datasetID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void exportXML(Long datasetID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void exportZIP(Long datasetID, AsyncCallback<String> callback) throws FenixGWTException;
	
}