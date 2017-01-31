package org.fao.fenix.web.modules.ec.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ECServiceEntry {

	private static ECServiceAsync ecService = null;

	public static ECServiceAsync getInstance() {
		if (ecService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlEC = ep.getServiceEntryPointEC(GWT.getModuleBaseURL(), GWT.getModuleName());
			ecService = (ECServiceAsync) GWT.create(ECService.class);
			ServiceDefTarget targetECService = (ServiceDefTarget) ecService;
			targetECService.setServiceEntryPoint(urlEC);
		}
		return ecService;
	}
	
}
