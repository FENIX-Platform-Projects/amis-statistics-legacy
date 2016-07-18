package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.fao.fenix.core.exception.FenixException;
import org.opengis.referencing.FactoryException;



/**
 *
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SingleSLDUploadServlet extends
	javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 13L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(getClass().getSimpleName() + ":: doPost");

		ServletRequestContext requestContext = new ServletRequestContext(request);
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);

		if (ServletFileUpload.isMultipartContent(requestContext)) {
			try{
				List<FileItem> fileItemList = upload.parseRequest(requestContext);

				if (fileItemList == null || fileItemList.isEmpty()) {
					response.getWriter().println("Exception: File upload error");
					return;
				}

				FileItem externalFileItem = null;

				if(fileItemList.get(0).getName().toLowerCase().endsWith(".sld") ||
						fileItemList.get(0).getName().toLowerCase().endsWith(".xml")){
					externalFileItem = fileItemList.get(0);
					uploadSingleFile(externalFileItem, response);
				}

			} catch (FenixException e) {
				response.getWriter().println("Exception: Error handling sld file: " + e.getMessage());
				e.printStackTrace(System.out);
			} catch (FileUploadException e) {
				response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
				e.printStackTrace(System.out);
				throw new FenixException(e);
			} catch (FactoryException e) {
				response.getWriter().println("Exception: FactoryException while reading sld file: " + e.getMessage());
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