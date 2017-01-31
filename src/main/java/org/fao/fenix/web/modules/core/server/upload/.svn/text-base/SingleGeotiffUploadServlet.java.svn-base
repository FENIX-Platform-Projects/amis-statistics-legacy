package org.fao.fenix.web.modules.core.server.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipException;

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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.FenixFakeDataSource;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.map.layer.geotiff.GeoTiffInfo;
import org.fao.fenix.map.layer.geotiff.GeoTiffUtils;
import org.fao.fenix.map.upload.ZippedGeoTiffHandler;
import org.fao.fenix.map.util.IOUtil;
import org.fao.fenix.map.util.Zipper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 * @author $Author: Alessio Fabiani (alessio.fabiani@geo-solutions.it)
 *
 */

public class SingleGeotiffUploadServlet 
		extends	HttpServlet
		implements Servlet{

	private final static Logger LOGGER = Logger.getLogger(SingleGeotiffUploadServlet.class);

	static final long serialVersionUID = 77L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
//		System.out.println(getClass().getSimpleName() + ":: doPost");
		
		UploadListener listener = new UploadListener(request, 30);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		FenixFakeDataSource fenixFakeDataSource = (FenixFakeDataSource) wac.getBean("fenixFakeDataSource");
		
		if (! ServletFileUpload.isMultipartContent(request)) {
			LOGGER.error("Exception: File upload error -- not multipart");
			response.getWriter().println("Exception: File upload error -- not multipart");
			return;
		}

		try {

			List<FileItem> fileItemList = upload.parseRequest(request);

			if (fileItemList == null || fileItemList.isEmpty()) {
				LOGGER.error("Exception: File upload error -- no file found");
				response.getWriter().println("Exception: File upload error -- no file found");
				return;
			}

			FileItem dataFileItem = null;
			FileItem mdFileItem  = null;
			boolean isZip = false;

			for (FileItem fileItem : fileItemList) {
				String fileItemLowerName = fileItem.getName().toLowerCase();
				String extension = FilenameUtils.getExtension(fileItemLowerName);
				if(extension.equals("zip")) {
					LOGGER.info("GeotiffUpload: found ZIP file '"+fileItemLowerName+"'");
					dataFileItem = fileItem;
					isZip = true;
				} else if(extension.equals("tif")) {
					LOGGER.info("GeotiffUpload: found TIFF file '"+fileItemLowerName+"'");
					dataFileItem = fileItem;
				} else if(extension.equals("tiff")) {
					LOGGER.info("GeotiffUpload: found TIFF file '"+fileItemLowerName+"'");
					dataFileItem = fileItem;
				} else if(extension.equals("xml")) {
					LOGGER.info("GeotiffUpload: found metadata file '"+fileItemLowerName+"'");
					mdFileItem = fileItem;
				} else {
					LOGGER.warn("Unknown extension for file '"+fileItemLowerName+"'");
					continue;
				}
			}

			if(dataFileItem == null) {
				LOGGER.error("Missing data file");
				response.getWriter().println("Exception: missing data file");
				return;
			}

			String dataFileName = dataFileItem.getName();
			dataFileName = sanitizeFileName(dataFileName);
			String dataBaseName = FilenameUtils.getBaseName(dataFileName);


			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssSSS");
			Calendar cal = Calendar.getInstance();
			String timestamp = sdf.format(cal.getTime());
			// FIXME: set the dir outside the webapp, in order to preserve data
			// when fenix is redeployed.
//			ServletContextResourceLoader scrl = new ServletContextResourceLoader(this.getServletContext());
//			System.out.println("Path "+ fenixFakeDataSource.getPath2uploadedLayers());
//			final String baseUploadDir = scrl.getResource(fenixFakeDataSource.getPath2uploadedLayers()).getFile().getAbsolutePath();

//			final String baseUploadDir = scrl.getResource("/home/babic/PROVA2").getFile().getAbsolutePath();
//			DefaultResourceLoader scrl = new DefaultResourceLoader();
//			//final String baseUploadDir = scrl.getResource("/dataUpload").getFile().getAbsolutePath();
//			final String baseUploadDir = scrl.getResourceByPath("/home/babic/Upload").getFile().getAbsolutePath();
			//final File uploadDir = new File(baseUploadDir, timestamp + "_" + dataBaseName);
			final String baseUploadDir = fenixFakeDataSource.getPath2uploadedLayers();
			final File uploadDir = new File(baseUploadDir, timestamp + "_" + dataBaseName);
			FileUtils.forceMkdir(uploadDir);

			//===== Upload data
			// we'll have the file named <uploadDir>/<ts>_<basename>/<ts>_<basename>.<ext>

			File uploaded = IOUtil.stream2localfile(dataFileItem.getInputStream(), timestamp+"_"+dataFileName, uploadDir);

			File mdFile = null;
			if(mdFileItem != null) {
				String mdFileName = mdFileItem.getName();
				mdFileName = sanitizeFileName(mdFileName);
				mdFile = IOUtil.stream2localfile(mdFileItem.getInputStream(),
													"metadata.xml",
													uploadDir);
			}

			//===== preprocess data file

			File geotiff = null;
			File []fileList = null;
			if(isZip) {
				LOGGER.info("Zip file");
				fileList = handleZip(uploaded, timestamp+"_"+dataBaseName);
				if(fileList == null) {
					response.getWriter().println("Exception: Error in zip file");
					return;
				}
				//geotiffFile is in first position
				geotiff = fileList[0];
					
			} else if(dataFileItem != null) {
				LOGGER.info("It's not zip file");
				geotiff = uploaded;
			}
			//If there is only geotiffFile
			if((isZip && (fileList.length == 1)) || ((!isZip) && (dataFileItem != null)))
			{
				LOGGER.info("There is only geotiffFile");
				GeoTiffInfo.Instantiator instantiator = new GeoTiffInfo.Instantiator();
				GeoTiffInfo geoTiffInfo = instantiator.createInfo(geotiff);
				if(geoTiffInfo == null) {
					LOGGER.error("Error in TIFF file: " + instantiator.getLastError().getMessage(), instantiator.getLastError());
					response.getWriter().println("Exception: error in TIFF file: " + instantiator.getLastError().getMessage());
					return;
				}
	
				File prj = new File(uploadDir, dataBaseName+".prj");
				if( ! prj.exists()) {
					if( ! GeoTiffUtils.createDefaultPrjFile(geoTiffInfo, uploadDir, timestamp + "_" + dataBaseName)) {
						LOGGER.error("Error creating PRJ file.");
						response.getWriter().println("Exception: Error creating PRJ file");
						return;
					}
				}				
				String crs = geoTiffInfo.getCrs();
				String envelope = geoTiffInfo.getMinx()
							+ "," + geoTiffInfo.getMiny()
							+ "," + geoTiffInfo.getMaxx()
							+ "," + geoTiffInfo.getMaxy();
				String geotiffUrl = URLEncoder.encode( geotiff.getAbsolutePath(), "UTF-8");
				response.getWriter().println("CRS="+(crs != null ? crs : "")
											+ "&BBOX=" + envelope
											+ "&URL=" + geotiffUrl);
				LOGGER.info("GeoTIFF uploaded successfully: " + dataFileName);
			}
			else if(isZip && (fileList.length == 2))
			{				
				GeoTiffInfo.Instantiator instantiator = new GeoTiffInfo.Instantiator();
				GeoTiffInfo geoTiffInfo = instantiator.createInfo(geotiff);
				if(geoTiffInfo == null) {
					LOGGER.error("Error in TIFF file: " + instantiator.getLastError().getMessage(), instantiator.getLastError());
					response.getWriter().println("Exception: error in TIFF file: " + instantiator.getLastError().getMessage());
					return;
				}
				
				File prjOld = new File(uploadDir, dataBaseName+".prj");
				if(prjOld.exists()) {
					//To remove the file prj
					prjOld.delete();
				}
				
				File prj;
				if((prj = GeoTiffUtils.createDefaultPrjFileTfw(uploadDir, timestamp + "_" + dataBaseName)) == null) {
					LOGGER.error("Error creating PRJ file.");
					response.getWriter().println("Exception: Error creating PRJ file");
					return;
				}
	
				File zipOld = new File(uploadDir, timestamp + "_" + dataBaseName +".zip");
				
				if(zipOld.exists()) {
					//To remove the file zip
					zipOld.delete();
				}
				//To create zip file
				File zip = new File(uploadDir, timestamp + "_" + dataBaseName +".zip");
				List <File> files = new ArrayList<File>();
				files.add(fileList[0]);
				files.add(fileList[1]);
				files.add(prj);
				
				Zipper.zipFlat(zip, files);
				
				String crs = geoTiffInfo.getCrs();
				String envelope = geoTiffInfo.getMinx()
							+ "," + geoTiffInfo.getMiny()
							+ "," + geoTiffInfo.getMaxx()
							+ "," + geoTiffInfo.getMaxy();
				String geotiffUrl = URLEncoder.encode( geotiff.getAbsolutePath(), "UTF-8");
				response.getWriter().println("CRS="+(crs != null ? crs : "")
											+ "&BBOX=" + envelope
											+ "&URL=" + geotiffUrl);
				LOGGER.info("GeoTIFF uploaded successfully: " + dataFileName);				
			}

		} catch (FenixException e) {
			response.getWriter().println("Exception: Error handling geotiff file: " + e.getMessage());
			e.printStackTrace(System.out);
		} catch (FileUploadException e) {
			response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
			e.printStackTrace(System.out);
			throw new FenixException(e);
		} catch (IOException e) {
			response.getWriter().println("Exception: Error while deserializing files: " + e.getMessage());
			e.printStackTrace(System.out);
			throw new FenixException(e);
		}
		catch (Exception e) {
			response.getWriter().println("Exception: Unexpected exception: " + e.getMessage());
			e.printStackTrace(System.out);
			throw new FenixException(e);
		} finally {
//				System.out.println("-------- SingleGeotiffUploadServlet END ---------------------");
		}
	}


	private static String sanitizeFileName(String filename) {
		// this is a workaround for the way IE passes params
		int bspos = filename.lastIndexOf('\\');
		if(bspos!=-1) filename = filename.substring(bspos+1);
		return filename;
	}

	private File[] handleZip(File zipFile, String basename) {
		File [] fileList = null;
		int countFile = 0;
		try {
			ZippedGeoTiffHandler geotiffZipHandler = new ZippedGeoTiffHandler();
			geotiffZipHandler.unzip(zipFile, basename, zipFile.getParentFile());
			if (geotiffZipHandler.getGeoTiffFile() == null) {
				LOGGER.error("No TIFF file found in '" + zipFile.getAbsolutePath() + "'");
				return null;
			}

			File geotiffFile = geotiffZipHandler.getGeoTiffFile();
			if (geotiffFile == null) {
				LOGGER.error("Error dezipping file '"+zipFile.getAbsolutePath()+"'");
				return null;
			}
			//If it arrives here there is geotiffFile
			//First position for geotiffFile
			countFile++;
			if(geotiffZipHandler.isTfwExists())
			{
				//Second position for Tfw File
				countFile++;
			}
			fileList = new File[countFile];
			if(countFile == 1)
				fileList[0] =  geotiffFile;
			else if(countFile == 2)
			{
				fileList[0] =  geotiffFile;
				fileList[1] = geotiffZipHandler.getTfwFile();
			}
			
			return fileList;
			
		} catch (ZipException ex) {
			LOGGER.error("Error dezipping file '"+zipFile.getAbsolutePath()+"'");
			return null;
		} catch (IOException ex) {
			LOGGER.error("Error dezipping file '"+zipFile.getAbsolutePath()+"'");
			return null;
		}
	}

}


