package org.fao.fenix.web.modules.dataviewer.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class DataViewerServiceEntry {
	
	private static DataViewerServiceAsync dataViewerService = null;

	public static DataViewerServiceAsync getInstance() {
		if (dataViewerService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urldataviewer = ep.getServiceEntryPointDataViewer(GWT.getModuleBaseURL(), GWT.getModuleName());
			dataViewerService = (DataViewerServiceAsync) GWT.create(DataViewerService.class);
			ServiceDefTarget targetdataviewerService = (ServiceDefTarget) dataViewerService;
			targetdataviewerService.setServiceEntryPoint(urldataviewer);
		}
		return dataViewerService;
	}

}
