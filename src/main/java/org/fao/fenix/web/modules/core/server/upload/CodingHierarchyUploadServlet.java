package org.fao.fenix.web.modules.core.server.upload;

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
import org.fao.fenix.core.utils.DcmtImporter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class for Servlet: CodingUploadServlet
 * 
 * @web.servlet name="CodingUploadServlet" display-name="CodingUploadServlet"
 * 
 * @web.servlet-mapping url-pattern="/CodingUploadServlet"
 * 
 * 
 *                      TODO>> The progress bar is following the loading of the file into the server
 *                      memory. It is not reflecting the subsequent process of interpreting the file
 *                      into java objects and writing these to the database.<br/> TODO>> Inform the
 *                      user that the saving in the database has succeeded. <br/>
 * 
 */

public class CodingHierarchyUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.getRootLogger().warn("CodingHierarchyUploadServlet: START...");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DcmtImporter dcmtImporter = (DcmtImporter) wac.getBean("dcmtImporter");
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);	
		
		try {
			List fileItemList = upload.parseRequest(request);
			
			FileItem codingFileItem = (FileItem)fileItemList.get(0);
			FileItem metadataFileItem = (FileItem)fileItemList.get(1);
			FileItem delimiterFileItem = (FileItem)fileItemList.get(2);
			FileItem policyFileItem = (FileItem)fileItemList.get(3);
			

//			Logger.getRootLogger().warn("Size: " + fileItemList.size());
			Logger.getRootLogger().warn("Metadata File:  " + metadataFileItem.getName());
			Logger.getRootLogger().warn("Code File: " + codingFileItem.getName());
			Logger.getRootLogger().warn("Policy: " + policyFileItem.getFieldName());
			Logger.getRootLogger().warn("Delimiter: " + delimiterFileItem.getFieldName());
			
			if (metadataFileItem == null || !metadataFileItem.getFieldName().equals("metadataFileName"))
				throw new FenixException("metadataFileItem is not valid " + metadataFileItem.getFieldName());
			if (codingFileItem == null || !codingFileItem.getFieldName().equals("fileName"))
				throw new FenixException("codes hierarchy is not valid");
			
			
				
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyFileItem.getFieldName());
			String delimiter = delimiterFileItem.getFieldName();
			
			dcmtImporter.importCodingSystemHierarchy(metadataFileItem.getInputStream(), codingFileItem.getInputStream(),  delimiter, policy);
			

			Logger.getRootLogger().warn("CodingHierarchyUploadServlet: END.");

		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new FenixException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FenixException(e);
		}
	
	}
}