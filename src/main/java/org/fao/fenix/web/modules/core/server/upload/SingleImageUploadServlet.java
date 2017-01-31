package org.fao.fenix.web.modules.core.server.upload;

import java.io.IOException;
import java.io.InputStream;
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
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.image.FenixImage;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.SaveDao;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SingleImageUploadServlet extends HttpServlet implements Servlet {

	private final static Logger LOGGER = Logger.getLogger(SingleImageUploadServlet.class);

	static final long serialVersionUID = 77L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// useful stuff
		final Double MAX_SIZE = 1D;
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletContext servletContext = this.getServletConfig().getServletContext();
		Long imageID = null;

		// spring beans
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		SaveDao saveDao = (SaveDao) wac.getBean("saveDao");
		SaveUniqueDao saveUniqueDao = (SaveUniqueDao) wac.getBean("saveUniqueDao");
		FenixPermissionManager fenixPermissionManager = (FenixPermissionManager) wac.getBean("fenixPermissionManager");

		try {

			List<FileItem> fileItemList = (List<FileItem>) upload.parseRequest(request);

			// image
			FileItem imageItem = findFileItemByCode("IMAGE", fileItemList);
			
			// title
			FileItem titleItem = findFileItemByCode("TITLE", fileItemList);
			String titleValue = extractValue(titleItem);
			
			// keywords
			FileItem keywordsItem = findFileItemByCode("KEYWORDS", fileItemList);
			String keywordsValue = extractValue(keywordsItem);
			
			// code
			FileItem codeItem = findFileItemByCode("CODE", fileItemList);
			String codeValue = extractValue(codeItem);
			
			// abstract
			FileItem abstractItem = findFileItemByCode("ABSTRACT", fileItemList);
			String abstractValue = extractValue(abstractItem);
			
			// region
			FileItem regionItem = findFileItemByCode("REGION", fileItemList);
			String regionValue = extractValue(regionItem);
			
			// category
			FileItem categoryItem = findFileItemByCode("CATEGORY", fileItemList);
			String categoryValue = extractValue(categoryItem);
			
			// source
			Source source = createSource(fileItemList);
			saveUniqueDao.saveUnique(source);
			LOGGER.info("Source has been saved with ID: " + source.getId());
			
			// create the stream
			byte[] bFile = new byte[imageItem.get().length];
			try {
				InputStream fileInputStream = imageItem.getInputStream();
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// populate the image
			FenixImage img = new FenixImage();
			img.setImage(bFile);
			img.setSource(source);
			img.setTitle(titleValue);
			img.setKeywords(keywordsValue);
			img.setCode(codeValue);
			img.setAbstractAbstract(abstractValue);
			img.setRegion(regionValue);
			img.setCategories(categoryValue);
			
			// save the image
			saveDao.save(img);
			imageID = img.getResourceId();
			LOGGER.info("Image has been saved with ID: " + img.getId());
			
			// set permissions
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, imageID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, imageID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, imageID, true);
			fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, imageID, true);

		} catch (FileUploadException e) {
			LOGGER.error(e.getMessage());
			throw new FenixException(e.getMessage());
		}

		// done!
		response.getOutputStream().print("imageID:" + imageID);
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

}
