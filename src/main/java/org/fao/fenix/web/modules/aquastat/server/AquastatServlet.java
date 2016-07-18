package org.fao.fenix.web.modules.aquastat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class AquastatServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final Logger LOGGER = Logger.getLogger(AquastatServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		ServiceType service = ServiceType.valueOf(request.getParameter("serviceType"));
		LOGGER.info(service.name());
		if (service != null) {
			switch (service) {
				case ONE: createCharts(request.getParameter("countryCode")); break;
				case ALL: createCharts(); break;
				case MULTIPLE: createCharts(createAreaCodesList(request.getParameter("countryCode"))); break;
			}
		}
		
	}
	
	private List<String> createAreaCodesList(String codes) {
		List<String> l = new ArrayList<String>();
		StringTokenizer t = new StringTokenizer(codes, ", ");
		while (t.hasMoreTokens())
			l.add(t.nextToken().trim());
		LOGGER.info(l.size() + " codes:");
		for (String s : l)
			LOGGER.info("Code: " + s);
		return l;
	}
	
	public void createCharts(String areaCode) {
		
		long t0 = System.currentTimeMillis();
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		AquastatLib aquastatLib = (AquastatLib) wac.getBean("aquastatLib");
		
		List<String> countries = new ArrayList<String>();
		countries.add(areaCode);
		aquastatLib.writeFiles(countries);
		
		LOGGER.info("Charts created for " + areaCode + " in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
	}
	
	public void createCharts(List<String> areaCodes) {
		
		long t0 = System.currentTimeMillis();
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		AquastatLib aquastatLib = (AquastatLib) wac.getBean("aquastatLib");
		
//		int[] rows = aquastatLib.createAquastatFiles(areaCodes);
//		aquastatLib.importDatasets(rows);
		aquastatLib.writeFiles(areaCodes);
		
		LOGGER.info("Charts created for: " + areaCodes + " in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
	}
	
	public void createCharts() {
		
		long t0 = System.currentTimeMillis();
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		AquastatLib aquastatLib = (AquastatLib) wac.getBean("aquastatLib");
		
//		int[] rows = aquastatLib.createAquastatFiles();
//		aquastatLib.importDatasets(rows);
		aquastatLib.writeFiles(new ArrayList<String>());
		
		LOGGER.info("Charts created for ALL countries in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		doPost(request, response);
	}
	
	enum ServiceType {
		ONE, MULTIPLE, ALL;
	}

}
