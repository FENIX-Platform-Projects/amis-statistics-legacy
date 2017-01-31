package org.fao.fenix.web.modules.edi.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FEWSNETServiceEntry {

	private static FEWSNETServiceAsync fewsnetService = null;

	public static FEWSNETServiceAsync getInstance() {
		if (fewsnetService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFEWSNETService = ep.getServiceEntryPointFEWSNET(GWT.getModuleBaseURL(), GWT.getModuleName());
			fewsnetService = (FEWSNETServiceAsync) GWT.create(FEWSNETService.class);
			ServiceDefTarget targetFEWSNETService = (ServiceDefTarget) fewsnetService;
			targetFEWSNETService.setServiceEntryPoint(urlFEWSNETService);
		}
		return fewsnetService;
	}
	
}