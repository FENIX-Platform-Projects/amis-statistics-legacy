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
 * Servlet implementation class for Servlet: CodingMappingUploadServlet
 * 
 * @web.servlet name="CodingMappingUploadServlet" display-name="CodingMappingUploadServlet"
 * 
 * @web.servlet-mapping url-pattern="/CodingMappingUploadServlet"
 * 
 * 
 *                      TODO>> The progress bar is following the loading of the file into the server
 *                      memory. It is not reflecting the subsequent process of interpreting the file
 *                      into java objects and writing these to the database.<br/> TODO>> Inform the
 *                      user that the saving in the database has succeeded. <br/>
 * 
 */

public class CodingMappingUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.getRootLogger().warn("CodingMappingUploadServlet: START...");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DcmtImporter dcmtImporter = (DcmtImporter) wac.getBean("dcmtImporter");
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);	
		
		try {
			
			List<FileItem> fileItemList = upload.parseRequest(request);
			
			Logger.getRootLogger().warn("Files found: " + fileItemList.size());
			FileItem metadataSrcFileItem = fileItemList.get(3);
			FileItem metadataDstFileItem = fileItemList.get(5);
			FileItem mappingFileItem = fileItemList.get(1);
			FileItem policyFileItem = fileItemList.get(7);
			FileItem delimiterFileItem = fileItemList.get(6);
		

			List<FileItem> fileList = upload.parseRequest(request);
			Logger.getRootLogger().warn("Files found: " + fileList.size());
			Logger.getRootLogger().warn("M Src " + metadataSrcFileItem.getName());
			Logger.getRootLogger().warn("M Dst " + metadataDstFileItem.getName());
			Logger.getRootLogger().warn("csv " + mappingFileItem.getName());
			Logger.getRootLogger().warn("policy " + policyFileItem.getFieldName());
			Logger.getRootLogger().warn("delimiter" + delimiterFileItem.getFieldName());
			
			
			
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyFileItem.getFieldName());
			String delimiter = delimiterFileItem.getFieldName();
						
			dcmtImporter.importGeneralMappingFromCSV(metadataSrcFileItem.getInputStream(), metadataDstFileItem.getInputStream(), mappingFileItem.getInputStream(), delimiter, policy);		
			
			response.getOutputStream();

		} catch (FileUploadException e) {
			throw new FenixException(e);
		} catch (Exception e) {
			throw new FenixException(e);
		}
		Logger.getRootLogger().warn("CodingMappingUploadServlet: END.");
	}
}