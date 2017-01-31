package org.fao.fenix.web.modules.cnsa.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CNSAServiceEntry {

	private static CNSAServiceAsync service = null;

	public static CNSAServiceAsync getInstance() {
		if (service == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String url = ep.getServiceEntryPointCNSA(GWT.getModuleBaseURL(), GWT.getModuleName());
			service = (CNSAServiceAsync) GWT.create(CNSAService.class);
			ServiceDefTarget targetService = (ServiceDefTarget) service;
			targetService.setServiceEntryPoint(url);
		}
		return service;
	}
	
}
