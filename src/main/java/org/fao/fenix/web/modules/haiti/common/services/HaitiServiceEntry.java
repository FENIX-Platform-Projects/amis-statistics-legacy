package org.fao.fenix.web.modules.haiti.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class HaitiServiceEntry {

	private static HaitiServiceAsync haitiService = null;

	public static HaitiServiceAsync getInstance() {
		if (haitiService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlHaiti = ep.getServiceEntryPointHaiti(GWT.getModuleBaseURL(), GWT.getModuleName());
			haitiService = (HaitiServiceAsync) GWT.create(HaitiService.class);
			ServiceDefTarget targetHaitiService = (ServiceDefTarget) haitiService;
			targetHaitiService.setServiceEntryPoint(urlHaiti);
		}
		return haitiService;
	}
	
}