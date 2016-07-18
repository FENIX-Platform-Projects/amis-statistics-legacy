/**
 * 
 */
package org.fao.fenix.web.modules.core.server.utils;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alessio
 *
 */
public class SubsetDataSelectServlet extends
		javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -913344848712987735L;
	static final Logger LOGGER = Logger.getLogger(SubsetDataSelectServlet.class.getPackage().getName());

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		LOGGER.info(req.getParameter("BBOX"));
	}

}
