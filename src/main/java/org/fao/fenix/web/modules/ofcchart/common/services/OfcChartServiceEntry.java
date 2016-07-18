package org.fao.fenix.web.modules.ofcchart.common.services;


import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;



public class OfcChartServiceEntry {
	
	private static OfcChartServiceAsync ofcChartService = null;

	public static OfcChartServiceAsync getInstance() {
		if (ofcChartService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCoding = ep.getServiceEntryPointOfcChart(GWT.getModuleBaseURL(), GWT.getModuleName());
			ofcChartService = (OfcChartServiceAsync) GWT.create(OfcChartService.class);
			ServiceDefTarget chartService = (ServiceDefTarget) ofcChartService;
			chartService.setServiceEntryPoint(urlCoding);		
		}
		return ofcChartService;
	}
}
