package org.fao.fenix.web.modules.coding.common.services;


import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;



public class CodingServiceEntry {
	
	private static CodingServiceAsync codingService = null;

	public static CodingServiceAsync getInstance() {
		if (codingService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCoding = ep.getServiceEntryPointCodingService(GWT.getModuleBaseURL(), GWT.getModuleName());
			codingService = (CodingServiceAsync) GWT.create(CodingService.class);
			ServiceDefTarget codingTableService = (ServiceDefTarget) codingService;
			codingTableService.setServiceEntryPoint(urlCoding);		
		}
		return codingService;
	}
}
