package org.fao.fenix.web.modules.bangladesh.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class BangladeshServiceEntry {

	private static BangladeshServiceAsync bangladeshService = null;

	public static BangladeshServiceAsync getInstance() {
		if (bangladeshService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlBangladesh = ep.getServiceEntryPointBangladesh(GWT.getModuleBaseURL(), GWT.getModuleName());
			bangladeshService = (BangladeshServiceAsync) GWT.create(BangladeshService.class);
			ServiceDefTarget targetBangladeshService = (ServiceDefTarget) bangladeshService;
			targetBangladeshService.setServiceEntryPoint(urlBangladesh);
		}
		return bangladeshService;
	}
	
}
