package org.fao.fenix.web.modules.giews.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class GIEWSServiceEntry {

	private static GIEWSServiceAsync giewsService = null;

	public static GIEWSServiceAsync getInstance() {
		if (giewsService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlGIEWS = ep.getServiceEntryPointGIEWS(GWT.getModuleBaseURL(), GWT.getModuleName());
			giewsService = (GIEWSServiceAsync) GWT.create(GIEWSService.class);
			ServiceDefTarget targetIPCService = (ServiceDefTarget) giewsService;
			targetIPCService.setServiceEntryPoint(urlGIEWS);
		}
		return giewsService;
	}
	
}