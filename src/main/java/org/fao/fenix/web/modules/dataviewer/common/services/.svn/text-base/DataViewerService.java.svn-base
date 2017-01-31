package org.fao.fenix.web.modules.dataviewer.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.BulkDownloadVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface DataViewerService extends RemoteService {

	public DWFAOSTATResultVO askDataViewerFAOSTAT(DWFAOSTATQueryVO qvo) throws FenixGWTException;
	
	public List<DWFAOSTATResultVO> askDataViewerFAOSTAT(List<DWFAOSTATQueryVO> qvos) throws FenixGWTException;

	
	public FAOSTATVisualizeSettingsVO getFAOSTATQueryVOs(FAOSTATCurrentView currentView, String filename, String lang) throws FenixGWTException;

	public String export(DWFAOSTATResultVO rvo);
	
	public List<Map<String, String>> read(String lang, int items) throws FenixGWTException;
	
	public Map<String, List<String>> getLatestDatabaseUpdates(DWFAOSTATQueryVO qvo, String lang, String languageName) throws FenixGWTException;
	
	public Map<String, List<BulkDownloadVO>> findAllBulkDownloads(String lang) throws FenixGWTException;
	
	public List<DWCodesModelData> getCodes(String cs, String domainCode, String language) throws FenixGWTException;
	
}