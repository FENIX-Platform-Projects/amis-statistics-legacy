package org.fao.fenix.web.modules.venn.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class VennServiceEntry {

	
	private static VennServiceAsync vennService = null;

	public static VennServiceAsync getInstance() {
		if (vennService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlCfs = ep.getServiceEntryPointVenn(GWT.getModuleBaseURL(), GWT.getModuleName());
			vennService = (VennServiceAsync) GWT.create(VennService.class);
			ServiceDefTarget targetCfsService = (ServiceDefTarget) vennService;
			targetCfsService.setServiceEntryPoint(urlCfs);
		}
		return vennService;
	}
}
