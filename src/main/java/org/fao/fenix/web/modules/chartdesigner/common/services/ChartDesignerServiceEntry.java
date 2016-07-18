package org.fao.fenix.web.modules.chartdesigner.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ChartDesignerServiceEntry {

	private static ChartDesignerServiceAsync chartDesignerService = null;

	public static ChartDesignerServiceAsync getInstance() {
		if (chartDesignerService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlChartDesigner = ep.getServiceEntryPointChartDesigner(GWT.getModuleBaseURL(), GWT.getModuleName());
			System.out.println("URL: " + urlChartDesigner);
			chartDesignerService = (ChartDesignerServiceAsync) GWT.create(ChartDesignerService.class);
			ServiceDefTarget targetChartDesignerService = (ServiceDefTarget) chartDesignerService;
			System.out.println("TARGET: " + targetChartDesignerService);
			targetChartDesignerService.setServiceEntryPoint(urlChartDesigner);
		}
		return chartDesignerService;
	}
	
}