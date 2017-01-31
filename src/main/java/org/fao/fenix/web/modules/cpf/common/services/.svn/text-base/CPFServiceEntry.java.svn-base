package org.fao.fenix.web.modules.cpf.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CPFServiceEntry {

	private static CPFServiceAsync CPFService = null;

	public static CPFServiceAsync getInstance() {
		if (CPFService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCPF = ep.getServiceEntryPointCPF(GWT.getModuleBaseURL(), GWT.getModuleName());
			CPFService = (CPFServiceAsync) GWT.create(CPFService.class);
			ServiceDefTarget targetCPFService = (ServiceDefTarget) CPFService;
			targetCPFService.setServiceEntryPoint(urlCPF);
		}
		return CPFService;
	}

}
