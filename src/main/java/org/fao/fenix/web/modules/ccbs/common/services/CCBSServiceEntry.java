package org.fao.fenix.web.modules.ccbs.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CCBSServiceEntry {
	
	private static CCBSServiceAsync ccbsService = null;

	public static CCBSServiceAsync getInstance() {
		if (ccbsService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCCBS = ep.getServiceEntryPointCCBS(GWT.getModuleBaseURL(), GWT.getModuleName());
			ccbsService = (CCBSServiceAsync) GWT.create(CCBSService.class);
			ServiceDefTarget targetCCBSService = (ServiceDefTarget) ccbsService;
			targetCCBSService.setServiceEntryPoint(urlCCBS);
		}
		return ccbsService;
	}

}