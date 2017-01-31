package org.fao.fenix.web.modules.aquastat.server;

import org.fao.fenix.web.modules.aquastat.common.services.AquastatService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AquastatServiceImpl extends RemoteServiceServlet implements AquastatService {

	private AquastatLib aquastatLib;
	
	public void createCharts() {
		int[] rows = aquastatLib.createAquastatFiles();
		aquastatLib.importDatasets(rows);
	}
	
	public void createCharts(String areaCode) {
		
	}

	public void setAquastatLib(AquastatLib aquastatLib) {
		this.aquastatLib = aquastatLib;
	}
	
}