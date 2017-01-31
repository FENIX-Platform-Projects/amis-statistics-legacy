package org.fao.fenix.web.modules.re.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class REServiceEntry {

	private static REServiceAsync reService = null;

	public static REServiceAsync getInstance() {
		if (reService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlRE = ep.getServiceEntryPointRe(GWT.getModuleBaseURL(), GWT.getModuleName());
			reService = (REServiceAsync) GWT.create(REService.class);
			ServiceDefTarget targetREService = (ServiceDefTarget) reService;
			targetREService.setServiceEntryPoint(urlRE);
		}
		return reService;
	}
}
