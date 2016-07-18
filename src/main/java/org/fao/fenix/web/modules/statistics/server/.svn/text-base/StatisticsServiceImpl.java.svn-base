package org.fao.fenix.web.modules.statistics.server;

import java.util.Map;

import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.statistics.StatisticsUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.statistics.common.services.StatisticsService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class StatisticsServiceImpl extends RemoteServiceServlet implements StatisticsService {
	
	private StatisticsUtils statisticsUtils;
	
	public String executeCommandLine(String input) throws FenixGWTException {
		 try {
			return statisticsUtils.executeCommandLine(input);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Map<String, String> getMapManual() throws FenixGWTException {
		try {
			return statisticsUtils.getMapManual();
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	public Map<String, String> getMapConsole() throws FenixGWTException {
		try {
			return statisticsUtils.getMapConsole();
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public void setStatisticsUtils(StatisticsUtils statisticsUtils) {
		this.statisticsUtils = statisticsUtils;
	}
	
}