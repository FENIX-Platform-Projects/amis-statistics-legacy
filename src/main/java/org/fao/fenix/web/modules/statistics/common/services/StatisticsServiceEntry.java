package org.fao.fenix.web.modules.statistics.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class StatisticsServiceEntry {

	private static StatisticsServiceAsync statisticsService = null;

	public static StatisticsServiceAsync getInstance() {
		if (statisticsService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlWelcome = ep.getServiceEntryPointStatistics(GWT.getModuleBaseURL(), GWT.getModuleName());
			statisticsService = (StatisticsServiceAsync) GWT.create(StatisticsService.class);
			ServiceDefTarget targetWelcomeService = (ServiceDefTarget) statisticsService;
			targetWelcomeService.setServiceEntryPoint(urlWelcome);
		}
		return statisticsService;
	}
	
}