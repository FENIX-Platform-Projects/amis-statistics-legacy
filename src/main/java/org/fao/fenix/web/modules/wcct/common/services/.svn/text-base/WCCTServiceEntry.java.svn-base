package org.fao.fenix.web.modules.wcct.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class WCCTServiceEntry {

	private static WCCTServiceAsync wcctService = null;

	public static WCCTServiceAsync getInstance() {
		if (wcctService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCfs = ep.getServiceEntryPointWcct(GWT.getModuleBaseURL(), GWT.getModuleName());
			wcctService = (WCCTServiceAsync) GWT.create(WCCTService.class);
			ServiceDefTarget targetCfsService = (ServiceDefTarget) wcctService;
			targetCfsService.setServiceEntryPoint(urlCfs);
		}
		return wcctService;
	}
	
}
