package org.fao.fenix.web.modules.haiticnsatool.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class HaitiCNSAToolServiceEntry {

	private static HaitiCNSAToolServiceAsync haitiCNSAToolService = null;

	public static HaitiCNSAToolServiceAsync getInstance() {
		if (haitiCNSAToolService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlHaiti = ep.getServiceEntryPointHaitiCNSATool(GWT.getModuleBaseURL(), GWT.getModuleName());
			haitiCNSAToolService = (HaitiCNSAToolServiceAsync) GWT.create(HaitiCNSAToolService.class);
			ServiceDefTarget targetHaitiService = (ServiceDefTarget) haitiCNSAToolService;
			targetHaitiService.setServiceEntryPoint(urlHaiti);
		}
		return haitiCNSAToolService;
	}
	
}