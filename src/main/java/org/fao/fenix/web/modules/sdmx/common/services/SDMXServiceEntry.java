package org.fao.fenix.web.modules.sdmx.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class SDMXServiceEntry {

	private static SDMXServiceAsync sdmxService = null;

	public static SDMXServiceAsync getInstance() {
		if (sdmxService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlTable = ep.getServiceEntryPointSDMX(GWT.getModuleBaseURL(), GWT.getModuleName());
			sdmxService = (SDMXServiceAsync) GWT.create(SDMXService.class);
			ServiceDefTarget targetSDMXService = (ServiceDefTarget) sdmxService;
			targetSDMXService.setServiceEntryPoint(urlTable);
		}
		return sdmxService;
	}
	
}