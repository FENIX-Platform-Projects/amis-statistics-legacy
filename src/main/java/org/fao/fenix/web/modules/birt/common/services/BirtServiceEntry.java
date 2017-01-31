package org.fao.fenix.web.modules.birt.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class BirtServiceEntry {

	private static BirtServiceAsync birtService = null;

	public static BirtServiceAsync getInstance() {
		if (birtService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlBirt = ep.getServiceEntryPointBirt(GWT.getModuleBaseURL(), GWT.getModuleName());
			birtService = (BirtServiceAsync) GWT.create(BirtService.class);
			ServiceDefTarget targetBirtService = (ServiceDefTarget) birtService;
			targetBirtService.setServiceEntryPoint(urlBirt);
		}
		return birtService;
	}
}
