package org.fao.fenix.web.modules.fieldclimate.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FCServiceEntry {

	private static FCServiceAsync fcService = null;

	public static FCServiceAsync getInstance() {
		if (fcService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFCService = ep.getServiceEntryPointFC(GWT.getModuleBaseURL(), GWT.getModuleName());
			fcService = (FCServiceAsync) GWT.create(FCService.class);
			ServiceDefTarget targetFCService = (ServiceDefTarget) fcService;
			targetFCService.setServiceEntryPoint(urlFCService);
		}
		return fcService;
	}
	
}
