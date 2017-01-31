package org.fao.fenix.web.modules.core.server.upload;


import java.io.File;
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
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.map.util.IOUtil;
import org.opengis.referencing.FactoryException;
import org.springframework.web.context.support.ServletContextResourceLoader;


/**
 *
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SingleExternalGraphicUploadServlet extends
	javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 76L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(getClass().getSimpleName() + ":: doPost");

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);

		if (ServletFileUpload.isMultipartContent(request)) {
			try{
				List<FileItem> fileItemList = upload.parseRequest(request);

				if (fileItemList == null || fileItemList.isEmpty()) {
					response.getWriter().println("Exception: File upload error");
					return;
				}

				FileItem externalFileItem = null; // = fileItemList.get(1);

				if(fileItemList.get(0).getName().toLowerCase().endsWith(".png") ||
						fileItemList.get(0).getName().toLowerCase().endsWith(".jpeg") ||
							fileItemList.get(0).getName().toLowerCase().endsWith(".jpg") ||
								fileItemList.get(0).getName().toLowerCase().endsWith(".gif")){
					externalFileItem = fileItemList.get(0);
					uploadSingleFile(externalFileItem,response);
				}

			} catch (FenixException e) {
				response.getWriter().println("Exception: Error handling image file: " + e.getMessage());
				e.printStackTrace(System.out);
			} catch (FileUploadException e) {
				response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} catch (FactoryException e) {
				response.getWriter().println("Exception: FactoryException while reading image file: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} finally {
				System.out.println("-------- SingleExternalGraphicUploadServlet END ---------------------");
			}
		}
	}

	private void uploadSingleFile(FileItem externalFileItem, HttpServletResponse response) throws ServletException, IOException, FactoryException{

		if(externalFileItem == null) {
			response.getWriter().println("Exception: Error decoding input stream");
			return;
		}

		// save file to filesystem
		String fileName = externalFileItem.getName();

		// this is a workaround for the way IE passes params
		int bspos = fileName.lastIndexOf('\\');
		if(bspos!=-1) fileName = fileName.substring(bspos+1);

		ServletContext servletContext = this.getServletContext();
		ServletContextResourceLoader scrl = new ServletContextResourceLoader(servletContext);

		String path = scrl.getResource("/imgUpload").getFile().getAbsolutePath();
		File tempFile = IOUtil.stream2localfile(externalFileItem.getInputStream(), fileName, path);

		File externalFile = tempFile;

		if(externalFile == null){
			response.getWriter().println("Exception: Error uploading the file.");
		}else{
			response.getWriter().println("DIR=" + externalFile.getParentFile().getName() + "&NAME=" +
					externalFile.getName());
		}
	}
}
