package org.fao.fenix.web.modules.excelimporter.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ExcelImporterServiceEntry {

	private static ExcelImporterServiceAsync excelImporterService = null;

	public static ExcelImporterServiceAsync getInstance() {
		if (excelImporterService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlExcelImporterService = ep.getServiceEntryPointExcelImporter(GWT.getModuleBaseURL(), GWT.getModuleName());
			excelImporterService = (ExcelImporterServiceAsync) GWT.create(ExcelImporterService.class);
			ServiceDefTarget targetExcelImporterService = (ServiceDefTarget) excelImporterService;
			targetExcelImporterService.setServiceEntryPoint(urlExcelImporterService);
		}
		return excelImporterService;
	}
	
}