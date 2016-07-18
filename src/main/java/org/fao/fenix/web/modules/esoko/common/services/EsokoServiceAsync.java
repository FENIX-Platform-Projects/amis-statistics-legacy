package org.fao.fenix.web.modules.esoko.common.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EsokoServiceAsync {

	public void synchronize(String locationID, String timePeriod, AsyncCallback<Long> callback);
	
}