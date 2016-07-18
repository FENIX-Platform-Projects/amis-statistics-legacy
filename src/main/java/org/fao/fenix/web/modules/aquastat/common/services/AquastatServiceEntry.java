package org.fao.fenix.web.modules.aquastat.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class AquastatServiceEntry {

	private static AquastatServiceAsync aquastatService = null;

	public static AquastatServiceAsync getInstance() {
		if (aquastatService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlAquastat = ep.getServiceEntryPointAquastat(GWT.getModuleBaseURL(), GWT.getModuleName());
			aquastatService = (AquastatServiceAsync) GWT.create(AquastatService.class);
			ServiceDefTarget targetAquastatService = (ServiceDefTarget) aquastatService;
			targetAquastatService.setServiceEntryPoint(urlAquastat);
		}
		return aquastatService;
	}
	
}