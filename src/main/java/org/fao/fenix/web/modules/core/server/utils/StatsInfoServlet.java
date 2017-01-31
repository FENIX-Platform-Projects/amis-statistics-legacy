package org.fao.fenix.web.modules.core.server.utils;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.utils.CacheStatsDumper;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 */

public class StatsInfoServlet 
		extends	HttpServlet
		implements Servlet{

	private final static Logger LOGGER = Logger.getLogger(StatsInfoServlet.class);

	static final long serialVersionUID = 77L;

	private CacheStatsDumper statsDumper;

	public void init() throws ServletException {
		super.init();
		System.out.println("=== "+ getClass().getSimpleName() + ":: init() =========================================================");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);		
		statsDumper = (CacheStatsDumper) wac.getBean("cacheStatsDumper");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println(getClass().getSimpleName() + ":: doPost");
		boolean full = request.getParameter("full")!= null;

		Element stats = full ? statsDumper.getFullStats() : statsDumper.getSimpleStats();
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(stats, response.getWriter());
	}
}


