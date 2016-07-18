package org.fao.fenix.web.modules.communication.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CommunicationServiceEntry {

	private static CommunicationServiceAsync communicationService = null;

	public static CommunicationServiceAsync getInstance() {
		if (communicationService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCommunication = ep.getServiceEntryPointCommunication(GWT.getModuleBaseURL(), GWT.getModuleName());
			communicationService = (CommunicationServiceAsync) GWT.create(CommunicationService.class);
			ServiceDefTarget targetFpiService = (ServiceDefTarget) communicationService;
			targetFpiService.setServiceEntryPoint(urlCommunication);
		}
		return communicationService;
	}
	
}
