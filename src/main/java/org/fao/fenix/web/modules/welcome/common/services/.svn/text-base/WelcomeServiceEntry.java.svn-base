package org.fao.fenix.web.modules.welcome.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class WelcomeServiceEntry {

	private static WelcomeServiceAsync welcomeService = null;

	public static WelcomeServiceAsync getInstance() {
		if (welcomeService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlWelcome = ep.getServiceEntryPointWelcome(GWT.getModuleBaseURL(), GWT.getModuleName());
			welcomeService = (WelcomeServiceAsync) GWT.create(WelcomeService.class);
			ServiceDefTarget targetWelcomeService = (ServiceDefTarget) welcomeService;
			targetWelcomeService.setServiceEntryPoint(urlWelcome);
		}
		return welcomeService;
	}
	
}