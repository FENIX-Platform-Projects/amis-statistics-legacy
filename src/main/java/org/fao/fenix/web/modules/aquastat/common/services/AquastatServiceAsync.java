package org.fao.fenix.web.modules.aquastat.common.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AquastatServiceAsync {

	@SuppressWarnings("unchecked")
	public void createCharts(AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void createCharts(String areaCode, AsyncCallback callback);
	
}
