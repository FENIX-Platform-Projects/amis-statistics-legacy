package org.fao.fenix.web.modules.edi.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FAOStatServiceEntry {

	private static FAOStatServiceAsync faoStatService = null;

	public static FAOStatServiceAsync getInstance() {
		if (faoStatService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFaoStatService = ep.getServiceEntryPointFaoStat(GWT.getModuleBaseURL(), GWT.getModuleName());
			faoStatService = (FAOStatServiceAsync) GWT.create(FAOStatService.class);
			ServiceDefTarget targetFaoStatService = (ServiceDefTarget) faoStatService;
			targetFaoStatService.setServiceEntryPoint(urlFaoStatService);
		}
		return faoStatService;
	}
	
}