/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.web.modules.birt.server.utils.chart.ChartUpdater;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UpdateChartServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UpdateChartServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		Long datasetId = Long.parseLong(request.getParameter("datasetId"));
		LOGGER.info("\tdatasetId: " + datasetId);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		LOGGER.info("### DO GET: START...");
		Long datasetId = Long.parseLong(request.getParameter("datasetId"));
		LOGGER.info("\tdatasetId: " + datasetId);
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		ChartUpdater chartUpdater = (ChartUpdater) wac.getBean("chartUpdater");
//		chartUpdater.updateDatasetCharts(datasetId);
		LOGGER.info("\tchartUpdater is null? " + (chartUpdater == null));
		LOGGER.info("### DO GET: DONE!!!");
	}

}