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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataViewerServiceAsync {
	
	public void askDataViewerFAOSTAT(DWFAOSTATQueryVO qvo, AsyncCallback<DWFAOSTATResultVO> callback) throws FenixGWTException;
	
	public void askDataViewerFAOSTAT(List<DWFAOSTATQueryVO> qvos, AsyncCallback<List<DWFAOSTATResultVO>> callback) throws FenixGWTException;

	
	public void getFAOSTATQueryVOs(FAOSTATCurrentView currentView, String filename, String lang, AsyncCallback<FAOSTATVisualizeSettingsVO> callback) throws FenixGWTException;

	public void export(DWFAOSTATResultVO rvo, AsyncCallback<String> callback);
	
	public void read(String lang, int items, AsyncCallback<List<Map<String, String>>> callback) throws FenixGWTException;
		
	public void getLatestDatabaseUpdates(DWFAOSTATQueryVO qvo, String lang, String languageName, AsyncCallback<Map<String, List<String>>> callback) throws FenixGWTException;
	
	public void findAllBulkDownloads(String lang, AsyncCallback<Map<String, List<BulkDownloadVO>>> callback) throws FenixGWTException;

	public void getCodes(String cs, String domainCode, String language, AsyncCallback<List<DWCodesModelData>> callback) throws FenixGWTException;

	
}