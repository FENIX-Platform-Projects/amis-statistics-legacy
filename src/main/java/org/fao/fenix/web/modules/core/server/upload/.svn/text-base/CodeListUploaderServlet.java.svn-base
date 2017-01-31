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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.file.MetadataFactory;
import org.fao.fenix.core.utils.DcmtImporter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** This servlet imports a Coding System with no metadata associated, it creates one starting from user's inputs. */
@SuppressWarnings("serial")
public class CodeListUploaderServlet extends HttpServlet implements Servlet {

	private final static Logger LOGGER = Logger.getLogger(CodeListUploaderServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		// useful stuff
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletContext servletContext = this.getServletConfig().getServletContext();
		Long codeListID = null;
		
		// spring beans
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		DcmtImporter dcmtImporter = (DcmtImporter) wac.getBean("dcmtImporter");
				
		try {
			
			List<FileItem> fileItemList = (List<FileItem>)upload.parseRequest(request);
			
			// coding system
			FileItem codingSystemItem = findFileItemByCode("CODING_SYSTEM", fileItemList);
			
			// upload policy
			FileItem policyItem = findFileItemByCode("POLICY", fileItemList);
			String policyValue = extractValue(policyItem);
			UploadPolicy policy = UploadPolicy.valueOfIgnoreCase(policyValue);
			
			// delimiter
			FileItem delimiterItem = findFileItemByCode("DELIMITER", fileItemList);
			String delimiterValue = extractValue(delimiterItem);
			
			// coding system name
			FileItem codingSystemNameItem = findFileItemByCode("CODING_SYSTEM_NAME", fileItemList);
			String codingSystemNameValue = extractValue(codingSystemNameItem);
			
			// coding system type
			FileItem codingSystemTypeItem = findFileItemByCode("CODING_SYSTEM_TYPE", fileItemList);
			String codingSystemTypeValue = extractValue(codingSystemTypeItem);
			CodingType codingType = CodingType.valueOfIgnoreCase(codingSystemTypeValue);
			
			// abstract
			FileItem abstractItem = findFileItemByCode("ABSTRACT", fileItemList);
			String abstractabstract = extractValue(abstractItem);
			
			// source
			Source source = createSource(fileItemList);
			
			File file = createMetadata(codingSystemNameValue, codingType, abstractabstract, source);
			InputStream metadataStream = new FileInputStream(file);
			
			codeListID = dcmtImporter.importCodesFromCSV(metadataStream, codingSystemItem.getInputStream(), delimiterValue, policy);
			LOGGER.info("CodeList imported/updated with ID: " + codeListID);
			
		} catch (FileUploadException e) {
			LOGGER.error(e.getMessage());
			throw new FenixException(e.getMessage());
		}
		
		// done!
		response.getOutputStream().print("codeListID:" + codeListID);
	}
	
	private File createMetadata(String title, CodingType codingType, String abstractabstract, Source source) throws FenixException {
		
		CodingSystem cs = new CodingSystem();
		cs.setRegion("0");
		cs.setAbstractAbstract(abstractabstract);
		cs.setTitle(title);
		cs.setCodingType(codingType.name());
		cs.setSource(source);
		cs.setSharingCode(SharingCode.Public.name());
		cs.setDateLastUpdate(new Date());
		cs.setProvider(source);
		
		MetadataFactory mf = new MetadataFactory();
		File file = null;
		
		try {
			
			file = mf.createCodingSystemMetadata(cs);
			LOGGER.info("Metadata at: " + file.getAbsolutePath());
			return file;
			
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		}
		
	}
	
	private Source createSource(List<FileItem> fileItemList) {
		FileItem sourceNameItem = findFileItemByCode("SOURCE_NAME", fileItemList);
		String sourceName = extractValue(sourceNameItem);
		FileItem sourceRegionItem = findFileItemByCode("SOURCE_REGION", fileItemList);
		String sourceRegion = extractValue(sourceRegionItem);
		Source source = new Source();
		source.setTitle(sourceName);
		source.setCountry(sourceRegion);
		return source;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
}