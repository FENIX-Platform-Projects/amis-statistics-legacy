package org.fao.fenix.web.modules.excelimporter.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.utils.XLS2CSV;
import org.fao.fenix.web.modules.core.server.upload.MonitoredDiskFileItemFactory;
import org.fao.fenix.web.modules.core.server.upload.UploadListener;

@SuppressWarnings("serial")
public class ParseDatasetHeadersServlet extends HttpServlet implements Servlet  {

	private final static Logger LOGGER = Logger.getLogger(ParseDatasetHeadersServlet.class);
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
		LOGGER.info("Parse Dataset's headers: START...");
		
//		ServletContext servletContext = this.getServletConfig().getServletContext();
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			
			List fileItemList = upload.parseRequest(request);
			
			FileItem datasetFileItem = findFileItemByCode("DATASET", fileItemList);
			
			List<String> headers = new ArrayList<String>();
			
			if (datasetFileItem.getName().endsWith("csv")) {
				
				FileItem delimiterFileItem = findFileItemByCode("DELIMITER", fileItemList);
				String delimiter = extractValue(delimiterFileItem);
				headers = XLS2CSV.parseDatasetHeadersFromCSV(datasetFileItem.getInputStream(), delimiter);
				
			} else if (datasetFileItem.getName().endsWith("xls")) {
				
				FileItem excelTemplateFileItem = findFileItemByCode("TEMPLATE", fileItemList);
				String excelTemplate = extractValue(excelTemplateFileItem);
				headers = XLS2CSV.parseDatasetHeadersFromExcel(datasetFileItem.getInputStream(), excelTemplate);
				
			}
			
			response.getOutputStream().print(createResponse(headers));
			
		}  catch (FileUploadException e) {
			response.getOutputStream().print("ERROR: " + e.getMessage());
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			response.getOutputStream().print("ERROR: " + e.getMessage());	
			LOGGER.error(e.getMessage());			
		}
		
		LOGGER.info("Parse Dataset's headers: DONE");
	}
	
	private String createResponse(List<String> headers) {
		StringBuilder sb = new StringBuilder();
		for (String h : headers) {
			LOGGER.info("Adding header: " + h);
			sb.append(h);
		}
		String result = sb.toString();
		if (result.charAt(result.length() - 1) == ':')
			result = result.substring(0, (result.length() - 1));
		LOGGER.info(result);
		return result;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
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