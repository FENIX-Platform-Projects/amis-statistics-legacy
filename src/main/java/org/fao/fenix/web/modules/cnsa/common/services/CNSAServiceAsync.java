package org.fao.fenix.web.modules.cnsa.common.services;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CNSAServiceAsync {
	
	public void getReportInfo(String datasetCode, String type, String language, AsyncCallback<HashMap<String, String>> callback);
	
	public void getReport(String datasetCode, String type, String value, String reportType, String format, String language, String height, String width, AsyncCallback<String> callback);
	
	public void exportExcel(String datasetCode, String type, String value, String language, AsyncCallback<String> callback);
	
}
