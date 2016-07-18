package org.fao.fenix.web.modules.tinymcereport.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class TinyMCEServiceEntry {

	private static TinyMCEServiceAsync tinyMCEService = null;

	public static TinyMCEServiceAsync getInstance() {
		if (tinyMCEService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlTinyMCE = ep.getServiceEntryPointTinyMCE(GWT.getModuleBaseURL(), GWT.getModuleName());
			tinyMCEService = (TinyMCEServiceAsync) GWT.create(TinyMCEService.class);
			ServiceDefTarget targetTinyMCEService = (ServiceDefTarget) tinyMCEService;
			targetTinyMCEService.setServiceEntryPoint(urlTinyMCE);
		}
		return tinyMCEService;
	}
	
}