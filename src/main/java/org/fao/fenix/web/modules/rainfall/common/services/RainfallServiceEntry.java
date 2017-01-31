package org.fao.fenix.web.modules.rainfall.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class RainfallServiceEntry {

	private static RainfallServiceAsync rainfallService = null;
	
	public static RainfallServiceAsync getInstance() {
		if (rainfallService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlRainfall = ep.getServiceEntryPointRainfall(GWT.getModuleBaseURL(), GWT.getModuleName());
			rainfallService = (RainfallServiceAsync) GWT.create(RainfallService.class);
			ServiceDefTarget targetRainfallService = (ServiceDefTarget) rainfallService;
			targetRainfallService.setServiceEntryPoint(urlRainfall);
		}
		return rainfallService;
	}
	
}
