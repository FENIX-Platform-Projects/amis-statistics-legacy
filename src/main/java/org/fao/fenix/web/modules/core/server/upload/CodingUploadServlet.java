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

public class CodingUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger.getRootLogger().warn("CodingUploadServlet: START...");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DcmtImporter dcmtImporter = (DcmtImporter) wac.getBean("dcmtImporter");
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);	
		
		Long codeListID = null;
		
		try {
			
			List fileItemList = upload.parseRequest(request);
			
			FileItem codingFileItem = findFileItemByCode("DATASET", fileItemList);
			FileItem metadataFileItem = findFileItemByCode("METADATA", fileItemList);
			
			FileItem policyFileItem = findFileItemByCode("POLICY", fileItemList);
			String policyValue = extractValue(policyFileItem);
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyValue);
			
			FileItem delimiterFileItem = findFileItemByCode("DELIMITER", fileItemList);
			String delimiter = extractValue(delimiterFileItem);
	
			Logger.getRootLogger().warn("Metadata File:  " + metadataFileItem.getName());
			Logger.getRootLogger().warn("Code File: " + codingFileItem.getName());
			Logger.getRootLogger().warn("Policy: " + policy.name());
			Logger.getRootLogger().warn("Delimiter: " + delimiter);
						
			/** Upload a new CODING SYSTEM **/
			if ( codingFileItem.getName().equals("")){// && mappingFileItem.getName().equals("")) {
				Logger.getRootLogger().warn("Importing the Coding System");
				codeListID = dcmtImporter.importCodingSystem(metadataFileItem.getInputStream());	
			}
			
			/** Upload CODES **/
			else if ( !codingFileItem.getName().equals("")){// && mappingFileItem.getName().equals("") ) {
				
				/** Upload DCMT Codes **/
				if (metadataFileItem.getName().equals("DCMT_metadata.xml")) {
					Logger.getRootLogger().warn("Importing DCMT Codes");
					/** FALSE indicates in the structure that what we are inserting is an element and TRUE an attribute**/
					if (codingFileItem.getName().contains("DCMT_Elements.csv")) {
						codeListID = dcmtImporter.importDCMTCodesFromCSV(metadataFileItem.getInputStream(), codingFileItem.getInputStream(), delimiter, false, policy);
					}
					else if (codingFileItem.getName().contains("DCMT_Attributes.csv")) {
						codeListID = dcmtImporter.importDCMTCodesFromCSV(metadataFileItem.getInputStream(), codingFileItem.getInputStream(), delimiter, true, policy);
					}
				}
				
				/** Upload the codes of a General Coding System **/
				else {
					Logger.getRootLogger().warn("Importing Codes");
					codeListID = dcmtImporter.importCodesFromCSV(metadataFileItem.getInputStream(), codingFileItem.getInputStream(), delimiter, policy);
				}
				
			}
			
			response.getOutputStream();
			response.getOutputStream().print("codeListID:" + codeListID);

		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new FenixException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FenixException(e);
		}
		Logger.getRootLogger().warn("CodingUploadServlet: END.");
	}
	
	private FileItem findFileItemByCode(String code, List<FileItem> fileItemList) {
		for (FileItem i : fileItemList)
			if (i.getFieldName().startsWith(code))
				return i;
		return null;
	}
	
	private String extractValue(FileItem i) {
		int startIDX = 1 + i.getFieldName().indexOf('{');
		if (startIDX == -1)
			return null;
		int endIDX = i.getFieldName().indexOf('}', startIDX);
		if (endIDX == -1)
			return null;
		return i.getFieldName().substring(startIDX, endIDX);
	}
	
}