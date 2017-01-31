package org.fao.fenix.web.modules.esoko.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class EsokoServiceEntry {

	private static EsokoServiceAsync esokorService = null;

	public static EsokoServiceAsync getInstance() {
		if (esokorService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlEsokoService = ep.getServiceEntryPointEsoko(GWT.getModuleBaseURL(), GWT.getModuleName());
			esokorService = (EsokoServiceAsync) GWT.create(EsokoService.class);
			ServiceDefTarget targetEsokoService = (ServiceDefTarget) esokorService;
			targetEsokoService.setServiceEntryPoint(urlEsokoService);
		}
		return esokorService;
	}
	
}