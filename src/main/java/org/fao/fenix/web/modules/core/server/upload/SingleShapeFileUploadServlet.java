package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.map.upload.ShpInfo;
import org.fao.fenix.map.upload.ZippedShpHandler;
import org.fao.fenix.map.util.IOUtil;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;

/**
 * Servlet implementation class for Servlet: SingleShapeFileUploadServlet
 *
 * @web.servlet name="SingleShapeFileUploadServlet"
 *              display-name="SingleShapeFileUploadServlet"
 *
 * @web.servlet-mapping url-pattern="/SingleShapeFileUploadServlet"
 *
 *
 * TODO>> The progress bar is following the loading of the file into the server
 * memory. It is not reflecting the subsequent process of interpreting the file
 * into java objects and writing these to the database.<br/> TODO>> Inform the
 * user that the saving in the database has succeeded. <br/>
 *
 */

public class SingleShapeFileUploadServlet extends
		javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final Logger LOGGER = Logger.getLogger(SingleShapeFileUploadServlet.class.getPackage().getName());

	static final long serialVersionUID = 789L;

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("=== "+ getClass().getSimpleName() + ":: init() =========================================================");
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(getClass().getSimpleName() + ":: doPost" );

		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);

		if (ServletFileUpload.isMultipartContent(request)) {
			try
			{
				//LOGGER.info("-------- SingleShapeFileUploadServlet start ---------------------");

				List<FileItem> fileItemList = upload.parseRequest(request);

				if (fileItemList == null || fileItemList.isEmpty()) {
					response.getWriter().println("Exception: File upload error");
					return;
				}

				FileItem zipFileItem = null; // = fileItemList.get(0);
				FileItem mdFileItem  = null;

				if(fileItemList.size() == 1) {
					zipFileItem = fileItemList.get(0);
				} else {
					if(fileItemList.get(0).getName().toLowerCase().endsWith(".zip")) {
						zipFileItem = fileItemList.get(0);
						mdFileItem = fileItemList.get(1);
					} else {
						zipFileItem = fileItemList.get(1);
						mdFileItem = fileItemList.get(0);
					}
				}

				if(zipFileItem == null) {
					response.getWriter().println("Exception: Error decoding input stream");
					return;
				}

//				if (fileItemList != null && !fileItemList.isEmpty()
//						&& fileItemList.get(0) != null
//						&& !fileItemList.get(0).getName().isEmpty()) {
//					shapeFileItem = fileItemList.get(0);

				// save file to filesystem
				String zipName = zipFileItem.getName();
				// this is a workaround for the way IE passes params
				int bspos = zipName.lastIndexOf('\\');
				if(bspos!=-1)
					zipName = zipName.substring(bspos+1);

				File zipTemp = IOUtil.stream2tempfile(zipFileItem.getInputStream(),
														zipName);

				ZippedShpHandler shpZipHandler = new ZippedShpHandler();
				shpZipHandler.unzip(zipTemp, "file"); // filenames are unneeded
				File shapeFile = shpZipHandler.getShapeFile();
				if(shapeFile != null) {
					ShpInfo shpInfo = new ShpInfo(shapeFile); // throws a lot of stuff
					shpInfo.printInfoAsURL(response.getWriter());

					if(mdFileItem != null) {
						if(mdFileItem.getSize() > 0) {
							File unzippedDir = shapeFile.getParentFile();
							File mdFile = new File(unzippedDir, "DBFeatureLayer.xml");
							IOUtil.stream2file(mdFileItem.getInputStream(), mdFile);
						}
					}
				} else
					response.getWriter().println("Exception: Error dezipping the archive.");

//			} catch (MalformedURLException ex) {
//				response.getWriter().println("Exception: Error handling shape file: " + e.getMessage());
//				Logger.getLogger(SingleShapeFileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
			} catch (FactoryException ex) {
				Logger.getLogger(SingleShapeFileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
				response.getWriter().println("Exception: Error handling shape file: " + ex.getMessage());
			} catch (TransformException ex) {
				Logger.getLogger(SingleShapeFileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
				response.getWriter().println("Exception: Error handling shape file: " + ex.getMessage());
			} catch (FenixException e) {
				response.getWriter().println("Exception: Error handling shape file: " + e.getMessage());
				e.printStackTrace(System.out);
			} catch (FileUploadException e) {
				response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} finally {
				LOGGER.info("-------- SingleShapeFileUploadServlet END ---------------------");
			}
		}
	}

}
