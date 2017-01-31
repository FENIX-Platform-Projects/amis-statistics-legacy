package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.map.geoserver.GeoServerDao;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.util.IOUtil;
import org.opengis.referencing.FactoryException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class SLDUploaderServlet extends
	javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 13L;

	static final Logger LOGGER = Logger.getLogger(SLDUploaderServlet.class.getPackage().getName());


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		ServletRequestContext requestContext = new ServletRequestContext(request);
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);

		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);


		if (ServletFileUpload.isMultipartContent(requestContext)) {
			try{
				List<FileItem> fileItemList = upload.parseRequest(requestContext);

				if (fileItemList == null || fileItemList.isEmpty()) {
					response.getWriter().println("Exception: File upload error");
					return;
				}

//				for(FileItem fileItem : fileItemList) {
//					LOGGER.info(fileItem.getName() + " | " + fileItem.getFieldName() + " | " + fileItem.getString());
//				}

				// stylename
				String stylename = fileItemList.get(0).getString();

				// handle SLD
				FileItem sldFileItem = null;

				if(fileItemList.get(1).getName().toLowerCase().endsWith(".sld") ||
						fileItemList.get(1).getName().toLowerCase().endsWith(".xml")){
					sldFileItem = fileItemList.get(1);


//					uploadSingleFile(externalFileItem, response);

					LOGGER.info("sldFileItem.getName(): "+ sldFileItem.getName());

					File sldFile = IOUtil.stream2tempfile(sldFileItem.getInputStream(),
							sldFileItem.getName());
//					// SLD STUFF
					// Taking the GeoServer saved on the DB to publish the style
					if ( sldFile != null) {
						LOGGER.info("Handling the SLD file");
						GeoServerDao geoServerDao = (GeoServerDao) wac.getBean("geoServerDao");
						List<GeoServer> geoServers = geoServerDao.findAllGeoServers();

						if ( !geoServers.isEmpty() ) {
							if( geoServers.size() > 1) {
								LOGGER.warning("---------------------------------------------------------------------------");
								LOGGER.warning("ALERT!: there are " + geoServers.size() + " geoServers saved in GeoServer Table");
								LOGGER.warning("List of GeoServers");
								for(GeoServer geoServer : geoServers)
									LOGGER.warning("-> " + geoServer.getWmsUrl());
								LOGGER.warning("The system is going to publish the style in the first available geoserver");
								LOGGER.warning("---------------------------------------------------------------------------");
							}

							if(sldFile != null) {

								// how to handle tjhe multiple
								String sldbody = IOUtil.read(sldFile);
								if(sldbody != null) {

									LOGGER.info("saving " + stylename);

									if(GeoserverPublisher.publishLayerStyle(geoServers.get(0), stylename, sldbody, sldFile.getName())) {
										LOGGER.info("Style saved " + stylename);
									} else {
										LOGGER.warning("Could not save style " + stylename);
									}
								} else {
									LOGGER.warning("Could not read SLD " + sldFile);
								}
							}
						}
					}
				}

			} catch (FenixException e) {
				response.getWriter().println("Exception: Error handling sld file: " + e.getMessage());
				e.printStackTrace(System.out);
			} catch (FileUploadException e) {
				response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} finally {
				System.out.println("-------- SingleSLDUploadServlet END ---------------------");
			}
		}
	}

	private void uploadSingleFile(FileItem singleFileItem, HttpServletResponse response) throws ServletException, IOException, FactoryException{
		if(singleFileItem == null) {
			response.getWriter().println("Exception: Error decoding input stream");
			return;
		}else{
			String sld = stream2String(singleFileItem.getInputStream());

			if(sld.indexOf("\n") != -1){
				String[] result = sld.split("\n");
				sld = "";
				for(int i=0; i<result.length; i++){
					sld = sld.concat(result[i]);
				}
			}

			sld = sld.substring(sld.indexOf("<StyledLayerDescriptor"),sld.indexOf("</StyledLayerDescriptor>") + 24);
			String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			sld = xmlHeader + sld;

			response.getWriter().write("SLD=" + URLEncoder.encode(sld,"UTF-8"));
		}
	}

	private static String stream2String(InputStream is) throws IOException {
		byte buff[] = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int read;
        while((read = is.read(buff)) != -1) {
            sb.append(new String(buff, "UTF-8").toCharArray(), 0, read);
        }
        return sb.toString();
    }
}