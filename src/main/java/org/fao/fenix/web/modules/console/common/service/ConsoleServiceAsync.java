package org.fao.fenix.web.modules.console.common.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConsoleServiceAsync {

	public void getLogsList(AsyncCallback<List<String>> logsList);
	
	public void getLogContent(String fileName, AsyncCallback<String> fileContent);
	
	public void getMetadataTemplateNames(AsyncCallback<List<String>> callback);
	
	public void getMetadataTemplate(String metadataTemplateName, AsyncCallback<String> callback);
	
}
