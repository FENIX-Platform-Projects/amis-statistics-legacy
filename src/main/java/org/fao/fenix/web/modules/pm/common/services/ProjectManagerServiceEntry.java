package org.fao.fenix.web.modules.pm.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ProjectManagerServiceEntry {

	private static ProjectManagerServiceAsync projectManagerService = null;

	public static ProjectManagerServiceAsync getInstance() {
		if (projectManagerService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlPM = ep.getServiceEntryPointProjectManager(GWT.getModuleBaseURL(), GWT.getModuleName());
			projectManagerService = (ProjectManagerServiceAsync) GWT.create(ProjectManagerService.class);
			ServiceDefTarget targetPMService = (ServiceDefTarget) projectManagerService;
			targetPMService.setServiceEntryPoint(urlPM);
		}
		return projectManagerService;
	}
}
