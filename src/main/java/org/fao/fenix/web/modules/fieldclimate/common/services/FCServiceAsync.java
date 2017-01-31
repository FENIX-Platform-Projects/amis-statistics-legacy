package org.fao.fenix.web.modules.fieldclimate.common.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FCServiceAsync {

	public void synchronize(String station, int entries, AsyncCallback<Object> callback);
	
}