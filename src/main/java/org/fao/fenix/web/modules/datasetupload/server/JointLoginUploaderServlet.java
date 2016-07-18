package org.fao.fenix.web.modules.datasetupload.server;

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
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.web.modules.core.server.upload.MonitoredDiskFileItemFactory;
import org.fao.fenix.web.modules.core.server.upload.UploadListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class JointLoginUploaderServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final Logger LOGGER = Logger.getLogger(JointLoginUploaderServlet.class);
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		LOGGER.info("JointLoginUploaderServlet: START...");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DatasetImporter datasetImporter = (DatasetImporter) wac.getBean("datasetImporter");

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			
			List fileItemList = upload.parseRequest(request);
			
			FileItem datasetFileItem = (FileItem)fileItemList.get(2);
			FileItem metadataFileItem = (FileItem)fileItemList.get(3);
			FileItem delimiterFileItem = (FileItem)fileItemList.get(4);
			FileItem policyFileItem = (FileItem)fileItemList.get(5);
			
			
			LOGGER.info("datasetFileItem: " + datasetFileItem.getName());
			LOGGER.info("metadataFileItem: " + metadataFileItem.getName());
			LOGGER.info("delimiterFileItem: " + delimiterFileItem.getFieldName());
			LOGGER.info("policyFileItem: " + policyFileItem.getFieldName());
			
			if (metadataFileItem == null || !metadataFileItem.getFieldName().equals("metadataFileName"))
				throw new FenixException("metadataFileItem is not valid " + metadataFileItem.getFieldName());
			if (datasetFileItem == null || !datasetFileItem.getFieldName().equals("fileName"))
				throw new FenixException("datasetFileItem is not valid");
			
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyFileItem.getFieldName());
			
			LOGGER.info("UploadPolicy field name: " + policyFileItem.getFieldName() + " policy name is: " + policy.name());
			
			String delimiter = delimiterFileItem.getFieldName();
			
			if (datasetFileItem.getName().endsWith("csv"))
				datasetImporter.importDataset(datasetFileItem.getInputStream(), metadataFileItem.getInputStream(), policy, delimiter);
			else if (datasetFileItem.getName().endsWith("zip"))
				datasetImporter.bulkImport(datasetFileItem.getInputStream(), UploadPolicy.ignore, delimiter);

			//response.getOutputStream().print("OK");
			LOGGER.info("JointLoginUploaderServlet: END.");
			
		} catch (FileUploadException e) {
			response.getOutputStream().print("ERROR: " + e.getMessage());
			LOGGER.error("JointLoginUploaderServlet: EXIT with exc", e);
		} catch (Exception e) { // catchall
			response.getOutputStream().print("ERROR: " + e.getMessage());	
			LOGGER.error("JointLoginUploaderServlet: EXIT with exc", e);			
		}

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}