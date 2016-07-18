package org.fao.fenix.web.modules.amis.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class AMISServiceEntry {

	private static AMISServiceAsync amisService = null;

	public static AMISServiceAsync getInstance() {
		if (amisService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlamis = ep.getServiceEntryPointAMIS(GWT.getModuleBaseURL(), GWT.getModuleName());
			amisService = (AMISServiceAsync) GWT.create(AMISService.class);
			ServiceDefTarget targetamisService = (ServiceDefTarget) amisService;
			targetamisService.setServiceEntryPoint(urlamis);
		}
		return amisService;
	}

}
