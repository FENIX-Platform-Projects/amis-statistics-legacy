package org.fao.fenix.web.modules.cfs.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CFSServiceEntry {

	private static CFSServiceAsync cfsService = null;

	public static CFSServiceAsync getInstance() {
		if (cfsService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCfs = ep.getServiceEntryPointCfs(GWT.getModuleBaseURL(), GWT.getModuleName());
			cfsService = (CFSServiceAsync) GWT.create(CFSService.class);
			ServiceDefTarget targetCfsService = (ServiceDefTarget) cfsService;
			targetCfsService.setServiceEntryPoint(urlCfs);
		}
		return cfsService;
	}
	
}
