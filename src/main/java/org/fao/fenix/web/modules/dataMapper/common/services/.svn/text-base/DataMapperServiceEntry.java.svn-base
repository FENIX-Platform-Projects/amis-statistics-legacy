package org.fao.fenix.web.modules.dataMapper.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class DataMapperServiceEntry {

	private static DataMapperServiceAsync dataMapperService = null;

	public static DataMapperServiceAsync getInstance() {
		if (dataMapperService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String url = ep.getServiceEntryPointDataMapper(GWT.getModuleBaseURL(), GWT.getModuleName());
			dataMapperService = (DataMapperServiceAsync) GWT.create(DataMapperService.class);
			ServiceDefTarget targetECService = (ServiceDefTarget) dataMapperService;
			targetECService.setServiceEntryPoint(url);
		}
		return dataMapperService;
	}
	
}
