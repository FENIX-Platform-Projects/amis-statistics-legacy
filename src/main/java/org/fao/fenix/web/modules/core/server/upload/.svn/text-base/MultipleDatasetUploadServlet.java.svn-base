package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class for Servlet: MultipleDatasetUploadServlet
 * 
 * @web.servlet name="MultipleDatasetUploadServlet" display-name="MultipleDatasetUploadServlet"
 * 
 * @web.servlet-mapping url-pattern="/MultipleDatasetUploadServlet"
 * 
 * 
 * TODO>> The progress bar is following the loading of the file into the server
 * memory. It is not reflecting the subsequent process of interpreting the file
 * into java objects and writing these to the database.<br/> 
 * TODO>> Inform the user that the saving in the database has succeeded. <br/>
 * 
 */

public class MultipleDatasetUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	static ApplicationContext applicationContext;

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out
				.println("===MultipleDatasetUploadServlet init(ServletConfig config)=========================================================");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		MultipleDatasetUploadServlet.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
//		DatasetLoader fenixLoader = new DatasetLoader();
//		ServletContext servletContext = this.getServletConfig().getServletContext();
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//		DatasetDao datasetDao = (DatasetDao) wac.getBean("datasetDao");
//		UploadListener listener = new UploadListener(request, 30);
//		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			System.out.println("-------- MultipleDatasetUploadServlet start ---------------------");
//			List<FileItem> fileItemList = upload.parseRequest(request);
//			FileItem datasetFileItem = null;
//
//			if (fileItemList.get(0) != null && !fileItemList.get(0).getName().equals("")) {
//				datasetFileItem = fileItemList.get(0);
//			}
//
//			FileItem metadataFileItem = null;
//			if (fileItemList.get(1) != null && !fileItemList.get(1).getName().equals("")) {
//				metadataFileItem = fileItemList.get(1);
//			}
//			Dataset dataset = fenixLoader.loadDataAndMetadata(metadataFileItem.getName(), metadataFileItem
//					.getInputStream(), datasetFileItem.getName(), datasetFileItem.getInputStream());
//
//			if (dataset == null)
//				throw new FenixException("Error: something went wrong wile trying to load the dataset, it is null");
//			else
//				datasetDao.save(dataset);
//
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//			throw new FenixException(e);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FenixException(e);
//		}
//		System.out.println("-------- MultipleDatasetUploadServlet END ---------------------");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}