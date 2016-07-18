package org.fao.fenix.web.modules.imftool.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class IMFServiceEntry {

	private static IMFServiceAsync imfService = null;

	public static IMFServiceAsync getInstance() {
		if (imfService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlIMF = ep.getServiceEntryPointIMF(GWT.getModuleBaseURL(), GWT.getModuleName());
			imfService = (IMFServiceAsync) GWT.create(IMFService.class);
			ServiceDefTarget targetIMFService = (ServiceDefTarget) imfService;
			targetIMFService.setServiceEntryPoint(urlIMF);
		}
		return imfService;
	}
	
}