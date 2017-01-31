package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.map.geoserver.io.GeoserverLayerWriter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LayerUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2034770678978451992L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		GeoserverLayerWriter geoserverLayerWriter = (GeoserverLayerWriter) wac.getBean("geoserverLayerWriter");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
			try {
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				List<?> items = upload.parseRequest(request);
				Iterator<?> itr = items.iterator();

				while(itr.hasNext()) {
					FileItem item = (FileItem) itr.next();

					if(!item.isFormField()) {
						String dsName = item.getName();
						       dsName = dsName.substring(0, dsName.lastIndexOf(".")).replaceAll("\\.", "_");
						       dsName+= "_ds";
						geoserverLayerWriter.writeShapeFileTo(item.getInputStream(), dsName);
					} 
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
				throw new FenixException(e);
			}
		}
		
		
		System.out.println("-------- LayerUploadServlet touched post ---------------------");
	}

}