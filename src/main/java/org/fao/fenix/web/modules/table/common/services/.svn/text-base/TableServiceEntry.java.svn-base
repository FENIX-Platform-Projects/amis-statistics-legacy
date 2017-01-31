package org.fao.fenix.web.modules.table.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class TableServiceEntry {

	private static TableServiceAsync tableService = null;

	public static TableServiceAsync getInstance() {
		if (tableService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlTable = ep.getServiceEntryPointTable(GWT.getModuleBaseURL(), GWT.getModuleName());
			tableService = (TableServiceAsync) GWT.create(TableService.class);
			ServiceDefTarget targetTableService = (ServiceDefTarget) tableService;
			targetTableService.setServiceEntryPoint(urlTable);
		}
		return tableService;
	}
	
}
