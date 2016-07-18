package org.fao.fenix.web.modules.x.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.x.XImporter;
import org.fao.fenix.web.modules.core.server.upload.MonitoredDiskFileItemFactory;
import org.fao.fenix.web.modules.core.server.upload.UploadListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class XImporterServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	private static final Logger LOGGER = Logger.getLogger(XImporterServlet.class);

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		LOGGER.info("XImporterServlet: START...");
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		XImporter xImporter = (XImporter) wac.getBean("xImporter");

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			
			List fileItemList = upload.parseRequest(request);
			FileItem rssFileItem = (FileItem)fileItemList.get(0);
			
			LOGGER.info(rssFileItem.getFieldName() + ": " + rssFileItem.getName());
			
			if (rssFileItem == null || !rssFileItem.getFieldName().equals("fileName"))
				throw new FenixException("rssFileItem is not valid");
			
			if (rssFileItem.getName().endsWith("xml"))
				xImporter.importRSS(rssFileItem.getInputStream());
			
			LOGGER.info("XImporterServlet: END.");
			
		} catch (FileUploadException e) {
			response.getOutputStream().print("ERROR: " + e.getMessage());
			LOGGER.info("XImporterServlet: EXIT with exc", e);
		} catch (Exception e) { // catch all
			response.getOutputStream().print("ERROR: " + e.getMessage());	
			LOGGER.info("XImporterServlet: EXIT with exc", e);			
		}

	}
	
}
