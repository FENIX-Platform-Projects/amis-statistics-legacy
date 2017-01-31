package org.fao.fenix.web.modules.map.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class MapServiceEntry {

	private static MapServiceAsync service = null;

	public static MapServiceAsync getInstance() {
		if (service == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String epUrl = ep.getServiceEntryPointMap(GWT.getModuleBaseURL(), GWT.getModuleName());
			service = (MapServiceAsync) GWT.create(MapService.class);
			ServiceDefTarget targetService = (ServiceDefTarget) service;
			targetService.setServiceEntryPoint(epUrl);
		}
		return service;
	}
}
