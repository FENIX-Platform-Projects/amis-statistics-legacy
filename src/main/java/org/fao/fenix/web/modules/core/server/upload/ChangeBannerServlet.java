package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.map.util.IOUtil;
import org.springframework.web.context.support.ServletContextResourceLoader;

/**
 * Servlet implementation class for Servlet: SingleDatasetUploadServlet
 * 
 * @web.servlet name="SingleDatasetUploadServlet" display-name="SingleDatasetUploadServlet"
 * 
 * @web.servlet-mapping url-pattern="/SingleDatasetUploadServlet"
 * 
 * 
 *                      TODO>> The progress bar is following the loading of the file into the server
 *                      memory. It is not reflecting the subsequent process of interpreting the file
 *                      into java objects and writing these to the database.<br/> TODO>> Inform the
 *                      user that the saving in the database has succeeded. <br/>
 * 
 */

public class ChangeBannerServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = Logger.getLogger(ChangeBannerServlet.class);
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FenixException {
		
	Logger.getRootLogger().warn("ChangeBannerServlet: START...");
	final Double MAX_SIZE = 1D;
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		if (ServletFileUpload.isMultipartContent(request)) {
			try{

				List<FileItem> fileItemList = upload.parseRequest(request);

				if (fileItemList == null || fileItemList.isEmpty()) {
					LOGGER.error("Exception: File upload error -- no file found");
					response.getWriter().println("Exception: File upload error -- no file found");
					return;					
				}

				
				FileItem leftFileItem = null;
				
				FileItem rightFileItem = null;
				
				try {
//					for (FileItem fileItem : fileItemList) {
						try {
							leftFileItem = fileItemList.get(0);
		//					String fileItemLowerName = fileItem.getName().toLowerCase();
		//					String extension = FilenameUtils.getExtension(fileItemLowerName);
		//					dataFileItem.add(fileItem);
							LOGGER.info("--> " + leftFileItem.getName().toLowerCase());
						} catch (Exception e) {
							LOGGER.info("leftFileItem error1");
						}
						
						try {
							rightFileItem = fileItemList.get(1);
		//					String fileItemLowerName = fileItem.getName().toLowerCase();
		//					String extension = FilenameUtils.getExtension(fileItemLowerName);
		//					dataFileItem.add(fileItem);
							LOGGER.info("--> " + rightFileItem.getName().toLowerCase());
						} catch (Exception e) {
							LOGGER.info("rightFileItem error1");
						}
				}
				catch (Exception e) {
					LOGGER.info("error2");
				}


				ServletContextResourceLoader scrl = new ServletContextResourceLoader(this.getServletContext());
				final String baseUploadDir = scrl.getResource("/fenix/images").getFile().getAbsolutePath();
				LOGGER.info("Upload Image Path: " + baseUploadDir);
				final File uploadDir = new File(baseUploadDir);
				
				
				if ( !leftFileItem.getName().equals(""))
					IOUtil.stream2localfile(leftFileItem.getInputStream(), "FENIX_BANNER.png", uploadDir);
				
				if ( !rightFileItem.getName().equals(""))
					IOUtil.stream2localfile(rightFileItem.getInputStream(), "FAO_UE_BANNER.png", uploadDir);
				
//				String dataFileName = dataFileItem.getName();
				//dataFileName = sanitizeFileName(dataFileName);
//				String dataBaseName = FilenameUtils.getBaseName(dataFileName);


//				System.out.println(dataFileName);
				
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssSSS");
//				Calendar cal = Calendar.getInstance();
//				String timestamp = sdf.format(cal.getTime());
//				 FIXME: set the dir outside the webapp, in order to preserve data
//				 when fenix is redeployed.
//				ServletContextResourceLoader scrl = new ServletContextResourceLoader(this.getServletContext());
//				final String baseUploadDir = scrl.getResource("/imageRepository").getFile().getAbsolutePath();
//				LOGGER.info("Upload Image Path: " + baseUploadDir);
//				final File uploadDir = new File(baseUploadDir);
//				FileUtils.forceMkdir(uploadDir);

				//===== Upload data
				// we'll have the file named <uploadDir>/<ts>_<basename>/<ts>_<basename>.<ext>
				
//				long megaSize = (dataFileItem.getSize()/1024)/1024;
				
//				if (megaSize > MAX_SIZE){
//					throw new FenixException("The image is too large.");
					
//				}

//				File uploaded = IOUtil.stream2localfile(dataFileItem.getInputStream(), dataFileName, uploadDir);
				
				

			} catch (FenixException e) {
				response.getWriter().print(e.getMessage());
				e.printStackTrace(System.out);
			} catch (FileUploadException e) {
				response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} catch (Exception e) {
				response.getWriter().println("Exception: Unexpected exception: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} finally {
//				System.out.println("-------- SingleGeotiffUploadServlet END ---------------------");
			}
	
		}

	}
	


}