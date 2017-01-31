package org.fao.fenix.web.modules.console.common.service;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ConsoleServiceEntry {

	private static ConsoleServiceAsync consoleService = null;

	public static ConsoleServiceAsync getInstance() {
		if (consoleService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlConsole = ep.getServiceEntryPointConsole(GWT.getModuleBaseURL(), GWT.getModuleName());
			consoleService = (ConsoleServiceAsync) GWT.create(ConsoleService.class);
			ServiceDefTarget targetConsoleService = (ServiceDefTarget) consoleService;
			targetConsoleService.setServiceEntryPoint(urlConsole);
		}
		return consoleService;
	}
	
}
