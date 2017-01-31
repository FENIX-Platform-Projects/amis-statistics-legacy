package org.fao.fenix.web.modules.fixer.control.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FixerServiceEntry {

	private static FixerServiceAsync fixerService = null;

	public static FixerServiceAsync getInstance() {
		if (fixerService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFpi = ep.getServiceEntryPointFixerService(GWT.getModuleBaseURL(), GWT.getModuleName());
			fixerService = (FixerServiceAsync) GWT.create(FixerService.class);
			ServiceDefTarget targetFpiService = (ServiceDefTarget) fixerService;
			targetFpiService.setServiceEntryPoint(urlFpi);
		}
		return fixerService;
	}
}
