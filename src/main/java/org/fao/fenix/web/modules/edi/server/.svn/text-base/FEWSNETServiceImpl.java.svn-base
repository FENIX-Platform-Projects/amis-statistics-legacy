package org.fao.fenix.web.modules.edi.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.persistence.file.MetadataFactory;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.map.GeoTiffHarvesterWrapper;
import org.fao.fenix.web.modules.edi.common.services.FEWSNETService;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class FEWSNETServiceImpl extends RemoteServiceServlet implements FEWSNETService {

	private static final Logger LOGGER = Logger.getLogger(FAOStatServiceImpl.class);
	
	private String dir;
	
	private String bootDir;
	
	private MetadataFactory metadataFactory;
	
	private GeoTiffHarvesterWrapper geotiffHarvester;
	
	public FEWSNETServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public List<String> getZipFiles(List<String> zipURLs) throws FenixGWTException {
		List<String> zipFiles = new ArrayList<String>();
		try {
			for (String zipURL : zipURLs) 
				zipFiles.add(copyZIPLocally(zipURL));
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
		return zipFiles;
	}
	
	public List<String> importZipFiles(String zipURL, ResourceVO rvo, String requestedStyle) throws FenixGWTException {
		
		// initiate the output
		List<String> bin = new ArrayList<String>();
		org.fao.fenix.core.domain.Resource r = vo2rsrc(rvo);
		
		// download files
		List<String> zipURLs = new ArrayList<String>();
		zipURLs.add(zipURL);
		List<String> zipFiles = getZipFiles(zipURLs);
		
		for (String zipFile : zipFiles) {
			bin.add(dir + File.separator + zipFile);
			List<String> outFilenames = unzip(dir + File.separator + zipFile);
			for (String outFilename : outFilenames) {
				bin.add(outFilename);
				String geoTifName = UUID.randomUUID().toString();
				if (outFilename.endsWith("tif")) {
					String command = "gdal_translate -a_srs EPSG:4326 " + outFilename + " " + bootDir + File.separator + geoTifName + ".tif";
					LOGGER.info("GDAL Command: " + command);
					try {
						Process process = Runtime.getRuntime().exec(command);
						int returnCode = process.waitFor();
						LOGGER.info("[GDAL] - " + returnCode);
					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					} catch (InterruptedException e) {
						LOGGER.error(e.getMessage());
					}
					String metadataFile = metadataFactory.createGeoTifMetadata(bootDir + File.separator + geoTifName, r, requestedStyle).getAbsolutePath();
					LOGGER.info("Metadata Available at: " + metadataFile);
				}
			}
		}
		
		// return files to be deleted
		return bin;
	}
	
	/** Run the <code>geotiffHarvester</code> and delete zip(s) and unzipped file(s). */
	public Boolean harvest(List<String> bin) throws FenixGWTException {
		geotiffHarvester.setThreadedStartDelay(0);
		boolean isDone = geotiffHarvester.run();
		if (isDone) {
			for (String s : bin) {
				try {
					File f = new File(s);
					LOGGER.info("Delete [" + s + "]..." + f.delete());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					throw new FenixGWTException(e.getMessage());
				}
			}
			return isDone;
		}
		return false;
	}
	
	private org.fao.fenix.core.domain.Resource vo2rsrc(ResourceVO vo) {
		org.fao.fenix.core.domain.Resource r = new org.fao.fenix.core.domain.Resource();
		r.setAbstractAbstract(vo.getAbstractAbstract());
		r.setCategories(vo.getCategories());
		r.setCode(vo.getCode());
		r.setDateLastUpdate(vo.getDateLastUpdate());
		r.setEndDate(vo.getEndDate());
		r.setKeywords(vo.getKeywords());
		r.setProvider(new Source(vo.getProvider(), vo.getProviderRegion(), vo.getProviderContact()));
		r.setRegion(vo.getRegion());
		r.setResourceId(vo.getResourceId());
		r.setSharingCode(vo.getSharingCode());
		r.setSource(new Source(vo.getSource(), vo.getSourceRegion(), vo.getSourceContact()));
		r.setStartDate(vo.getStartDate());
		r.setTitle(vo.getTitle());
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> unzip(String filename) {
		List<String> outFilenames = new ArrayList<String>();
		try {
			// Open the ZIP file
			String inFilename = filename;
			LOGGER.info("ZIP FILE NAME " + inFilename);
			ZipInputStream in = new ZipInputStream(new FileInputStream(inFilename));
			// list zip entries
			List list = list(inFilename);
			for (int i = 0; i < list.size(); i++) {
				// get entry
				ZipEntry entry = in.getNextEntry();
				// Open the output file
				String outFilename = System.getProperty("java.io.tmpdir") + "/" + (String) list.get(i);
				LOGGER.info(outFilename);
				OutputStream out = new FileOutputStream(outFilename);
				// Transfer bytes from the ZIP file to the output file
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				outFilenames.add(outFilename);
				// Close the streams
				out.close();
			}
			// Close the streams
			in.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return outFilenames;
	}
	
	@SuppressWarnings("unchecked")
	private static List list(String resourceName) {
		ArrayList list = new ArrayList();
		try {
			// Open the ZIP file
			LOGGER.info("LIST ZIP " + resourceName);
			ZipFile zipfile = new ZipFile(resourceName);
			// Enumerate each entry
			for (Enumeration entries = zipfile.entries(); entries.hasMoreElements();) {
				// add the entry name to the result list
				list.add(((ZipEntry) entries.nextElement()).getName());
			}
		} catch (IOException e) {
			LOGGER.info("IO EXCEPTION: " + e.getMessage());
		}
		return list;
	}
	
	private String copyZIPLocally(String zipURL) throws FenixGWTException {
		String filename = UUID.randomUUID().toString() + ".zip";
		try {
			URL url = new URL(zipURL);
			URLConnection con = url.openConnection();
			con.connect();
			InputStream urlfs = con.getInputStream();
			int c;
			File file = new File(dir + File.separator + filename);
			FileOutputStream stream = new FileOutputStream(file);
			while ((c = urlfs.read()) != -1)
				stream.write((byte) c);
			stream.close();
			urlfs.close();			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return filename;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setMetadataFactory(MetadataFactory metadataFactory) {
		this.metadataFactory = metadataFactory;
	}

	public void setBootDir(String bootDir) {
		this.bootDir = bootDir;
	}

	public void setGeotiffHarvester(GeoTiffHarvesterWrapper geotiffHarvester) {
		this.geotiffHarvester = geotiffHarvester;
	}
	
}