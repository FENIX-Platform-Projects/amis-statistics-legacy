package org.fao.fenix.web.modules.cnsa.common.services;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;

public interface CNSAService extends RemoteService {
	
	public HashMap<String, String> getReportInfo(String datasetCode, String type, String language);
	
	public String getReport(String datasetCode, String type, String value, String reportType, String format, String language, String height, String width);
	
	public String exportExcel(String datasetCode, String type, String value, String language);

}
