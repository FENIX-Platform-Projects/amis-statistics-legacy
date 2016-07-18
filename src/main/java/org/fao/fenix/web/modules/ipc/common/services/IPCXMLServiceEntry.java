package org.fao.fenix.web.modules.ipc.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class IPCXMLServiceEntry {

	private static IPCXMLServiceAsync ipcxmlService = null;

	public static IPCXMLServiceAsync getInstance() {
		if (ipcxmlService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlIPCXML = ep.getServiceEntryPointIPCXML(GWT.getModuleBaseURL(), GWT.getModuleName());
			ipcxmlService = (IPCXMLServiceAsync) GWT.create(IPCXMLService.class);
			ServiceDefTarget targetIPCXMLService = (ServiceDefTarget) ipcxmlService;
			targetIPCXMLService.setServiceEntryPoint(urlIPCXML);
		}
		return ipcxmlService;
	}
	
}