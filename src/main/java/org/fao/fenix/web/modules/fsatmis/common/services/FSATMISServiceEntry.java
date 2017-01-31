package org.fao.fenix.web.modules.fsatmis.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class FSATMISServiceEntry {

	private static FSATMISServiceAsync fsatmisService = null;

	public static FSATMISServiceAsync getInstance() {
		if (fsatmisService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlFSATMIS = ep.getServiceEntryPointFSATMIS(GWT.getModuleBaseURL(), GWT.getModuleName());
			fsatmisService = (FSATMISServiceAsync) GWT.create(FSATMISService.class);
			ServiceDefTarget targetFSATMISService = (ServiceDefTarget) fsatmisService;
			targetFSATMISService.setServiceEntryPoint(urlFSATMIS);
		}
		return fsatmisService;
	}
	
}