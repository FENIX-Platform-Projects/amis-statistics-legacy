package org.fao.fenix.web.modules.statistics.common.services;

import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StatisticsServiceAsync {
	public void executeCommandLine(String input, AsyncCallback<String> callback) throws FenixGWTException;
	public void getMapManual(AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	public void getMapConsole(AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
}