package org.fao.fenix.web.modules.fpi.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FpiServiceEntry {

	private static FpiServiceAsync fpiService = null;

	public static FpiServiceAsync getInstance() {
		if (fpiService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFpi = ep.getServiceEntryPointFpi(GWT.getModuleBaseURL(), GWT.getModuleName());
			fpiService = (FpiServiceAsync) GWT.create(FpiService.class);
			ServiceDefTarget targetFpiService = (ServiceDefTarget) fpiService;
			targetFpiService.setServiceEntryPoint(urlFpi);
		}
		return fpiService;
	}
}
