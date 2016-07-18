package org.fao.fenix.web.modules.aquastat.common.services;

import com.google.gwt.user.client.rpc.RemoteService;

public interface AquastatService extends RemoteService {

	public void createCharts();
	
	public void createCharts(String areaCode);
	
}
