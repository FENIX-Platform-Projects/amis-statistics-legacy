package org.fao.fenix.web.modules.sifsia.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class SifsiaServiceEntry {

	private static SifsiaServiceEntry sifsiaService = null;

	public static SifsiaServiceEntry getInstance() {
		if (sifsiaService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String url = ep.getServiceServletEntryPointSifsia(GWT.getModuleBaseURL(), GWT.getModuleName());
			sifsiaService = (SifsiaServiceEntry) GWT.create(SifsiaService.class);
			ServiceDefTarget targetTableService = (ServiceDefTarget) sifsiaService;
			targetTableService.setServiceEntryPoint(url);
		}
		return sifsiaService;
	}
	
}
