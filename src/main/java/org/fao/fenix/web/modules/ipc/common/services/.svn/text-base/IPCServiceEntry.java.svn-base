package org.fao.fenix.web.modules.ipc.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class IPCServiceEntry {

	private static IPCServiceAsync ipcService = null;

	public static IPCServiceAsync getInstance() {
		if (ipcService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlIPC = ep.getServiceEntryPointIPC(GWT.getModuleBaseURL(), GWT.getModuleName());
			ipcService = (IPCServiceAsync) GWT.create(IPCService.class);
			ServiceDefTarget targetIPCService = (ServiceDefTarget) ipcService;
			targetIPCService.setServiceEntryPoint(urlIPC);
		}
		return ipcService;
	}
}
