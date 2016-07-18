package org.fao.fenix.web.modules.adam.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ADAMServiceEntry {

	private static ADAMServiceAsync adamService = null;

	public static ADAMServiceAsync getInstance() {
		if (adamService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlADAM = ep.getServiceEntryPointADAM(GWT.getModuleBaseURL(), GWT.getModuleName());
			adamService = (ADAMServiceAsync) GWT.create(ADAMService.class);
			ServiceDefTarget targetADAMService = (ServiceDefTarget) adamService;
			targetADAMService.setServiceEntryPoint(urlADAM);
		}
		return adamService;
	}
	
}