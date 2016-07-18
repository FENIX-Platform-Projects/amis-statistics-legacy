package org.fao.fenix.web.modules.udtable.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class UDTableServiceEntry {

	private static UDTableServiceAsync udtableService = null;

	public static UDTableServiceAsync getInstance() {
		if (udtableService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlUDTable = ep.getServiceEntryPointUDTable(GWT.getModuleBaseURL(), GWT.getModuleName());
			udtableService = (UDTableServiceAsync) GWT.create(UDTableService.class);
			ServiceDefTarget targetTextService = (ServiceDefTarget) udtableService;
			targetTextService.setServiceEntryPoint(urlUDTable);
		}
		return udtableService;
	}
	
}