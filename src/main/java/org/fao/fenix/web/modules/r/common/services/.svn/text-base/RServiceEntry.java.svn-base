package org.fao.fenix.web.modules.r.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class RServiceEntry {

	private static RServiceAsync rService = null;

	public static RServiceAsync getInstance() {
		if (rService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlR = ep.getServiceEntryPointR(GWT.getModuleBaseURL(), GWT.getModuleName());
			rService = (RServiceAsync) GWT.create(RService.class);
			ServiceDefTarget targetRService = (ServiceDefTarget) rService;
			targetRService.setServiceEntryPoint(urlR);
		}
		return rService;
	}
	
}