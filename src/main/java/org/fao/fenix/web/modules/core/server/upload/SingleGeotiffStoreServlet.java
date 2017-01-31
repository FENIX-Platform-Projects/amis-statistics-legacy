package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.Coverage;
import org.fao.fenix.core.domain.map.geoserver.CoverageStore;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.map.layer.RasterLayer;
import org.fao.fenix.core.domain.map.metadata.LayerMetadataParser;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.map.geoserver.GeoServerDao;
import org.fao.fenix.map.geoserver.GeoserverImporter;
import org.fao.fenix.map.geoserver.io.GeoServerRESTHelper;
import org.fao.fenix.map.layer.geotiff.GeoTiffInfo;
import org.fao.fenix.map.layer.geotiff.GeoTiffUtils;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 * @author $Author: Alessio Fabiani (alessio.fabiani@geo-solutions.it)
 *
 */

public class SingleGeotiffStoreServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	

	static final private Logger LOGGER = Logger.getLogger(SingleGeotiffStoreServlet.class);
	static final long serialVersionUID = 7923L;
	
	private GeoServer defaultGeoserver;
	private WMSMapProviderDao wmsMapProviderDao;
	private GeoServerDao geoserverDao;
	private WebApplicationContext wac;
	private boolean tfw;
	

	public void init() throws ServletException {
		super.init();
		System.out.println("=== "+ getClass().getSimpleName() + ":: init() =========================================================");
		ServletContext servletContext = this.getServletConfig().getServletContext();
		
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		defaultGeoserver = (GeoServer) wac.getBean("geoserver");
		wmsMapProviderDao = (WMSMapProviderDao) wac.getBean("wmsMapProviderDao");
		geoserverDao = (GeoServerDao) wac.getBean("geoServerDao");
		tfw = false;
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		System.out.println("-------- SingleGeotiffStoreServlet start ---------------------");
		tfw = false;
		Map params = request.getParameterMap();

		String CRS  = ((String[]) params.get("CRS"))[0];
		String MINX = ((String[]) params.get("MINX"))[0];
		String MINY = ((String[]) params.get("MINY"))[0];
		String MAXX = ((String[]) params.get("MAXX"))[0];
		String MAXY = ((String[]) params.get("MAXY"))[0];

		String geotiffPathUrl = ((String[]) params.get("geotiff_path_url"))[0];
		geotiffPathUrl = URLDecoder.decode(geotiffPathUrl, "UTF-8");

		final File geotiffFile = new File(geotiffPathUrl);
		
		if(geotiffFile.getParentFile().isDirectory())
		{
			FilenameFilter filter = new FilenameFilter() {
				String basename = FilenameUtils.getBaseName(geotiffFile.getName());
				@Override
				public boolean accept(File dir, String name) {
					return name.equals(basename +".tfw");
				}
			};
			File [] fileArray = geotiffFile.getParentFile().listFiles(filter);
			LOGGER.info("fileArray.lenght "+fileArray.length);
			if(fileArray.length > 0)
			{
				LOGGER.info("There is tfw file.");
				tfw = true;
			}			
		}
		else
		{
			LOGGER.error(geotiffFile.getParentFile().getName() + "is not a directory");
		}
		
		GeoTiffInfo.Instantiator instantiator = new GeoTiffInfo.Instantiator();
		GeoTiffInfo geoTiffInfo = instantiator.createInfo(geotiffFile);
		if(geoTiffInfo == null) {
			LOGGER.error("The GeoTIFF file could not be parsed: " + instantiator.getLastError().getMessage(), instantiator.getLastError());
			response.getWriter().println("ERROR: invalidInput");
			return;
		}

		final GeoTiffReader tiffReader = geoTiffInfo.getReader();

		LOGGER.info("skipped the generating of the overviews");
		/*
		try {
			int ovsteps = GeoTiffUtils.generateOverviews(tiffReader);
			if ( ovsteps < 0) {
				LOGGER.error("ovsteps < 0 Error creating overviews on " + geotiffPathUrl);
				response.getWriter().println("ERROR: invalidInput");
				//return;
			} else
				LOGGER.info("Generated "+ovsteps+" overview steps for "
							+ geotiffFile.getAbsolutePath());
		} catch(Exception e) {
				// sometimes generateOverviews fails: we'll have to update GeoTools,
				// but in the meanwhile let's log the error and then ignore it
				LOGGER.error("Error creating overviews on " + geotiffPathUrl, e);
		}
*/
		// TIFF file is sane

		// The tiff file is stored in a unique generated directory, based on a timestamp.
		// A metadata file may exist, called metadata.xml.
		// Since gs creates layers based on the data file name, we won't need to
		// rename the layer in order for it to have a unique name.

		try {
			String geoserverURL = defaultGeoserver.getRestUrl();
			String baseName = FilenameUtils.getBaseName(geotiffFile.getName());
			String storeName = baseName + "_store";
			if(!tfw)
			{
				LOGGER.info("There isn't tfw file");
				String extension = getExtension(geotiffFile);
				String geofile = geoserverURL+"/rest/workspaces/fenix/coveragestores/"+storeName+"/external.geotiff?configure=first\\&coverageName="+baseName;
				
				URL geoserverREST_URL2 = new URL(geoserverURL+"/rest/workspaces/fenix/coveragestores");
				URL geoserverREST_URL3 = new URL(geofile);
				String content = "<coverageStore><name>"+storeName+"</name><type>GeoTIFF</type><workspace>fenix</workspace><enabled>true</enabled><url>file:"+geotiffFile.getAbsolutePath()+"</url></coverageStore>";
				String geotiffPathUrl2 = "file:"+ geotiffFile.getAbsolutePath();
				
				boolean sent = GeoServerRESTHelper.putContentRaster(geoserverREST_URL2, 
						content, 
						geoserverREST_URL3,
						geotiffPathUrl2,
						defaultGeoserver.getUsername(),
						defaultGeoserver.getPassword());
				if (! sent) {
					LOGGER.error("Error publishing layer on GS");
					response.getWriter().println("ERROR: publishing");
					return;
				}
			}
			else
			{
				LOGGER.info("There is tfw file");
				URL geoserverREST_URL = new URL(geoserverURL+"/rest/workspaces/fenix/coveragestores/"+ storeName + "/file.worldimage");
				String nameNoExtension = FilenameUtils.removeExtension(geotiffFile.getAbsolutePath());
				String content = nameNoExtension + ".zip";
				boolean sent = GeoServerRESTHelper.putContentRasterTfw(geoserverREST_URL, 
						content, defaultGeoserver.getUsername(), defaultGeoserver.getPassword());
				if (! sent) {
					LOGGER.error("Error publishing layer on GS");
					response.getWriter().println("ERROR: publishing");
					return;
				}
			}
			
			// ///////
			//
			// Import Raster into GeoServer and update Fenix stuff
			//
			// ///////
			
			RasterLayer rl;
			rl = buildLayerByMetadata(geotiffFile);
			if (rl == null) {
				rl = new RasterLayer();

				SimpleDateFormat sdf = new SimpleDateFormat("'on 'yyyy-MM-dd' at 'HH:mm:ss");
				Date date = Calendar.getInstance().getTime();
				String timestamp = sdf.format(date);

				rl.setTitle("Raster layer uploaded " + timestamp);
				rl.setAbstractAbstract("Layer uploaded " + timestamp);
				rl.setDateLastUpdate(date);
				rl.setKeywords("raster upload");
				rl.setRequestedStyle("raster");
			}
			
			GeoserverImporter gi = new GeoserverImporter();			
			Coverage coverage = new Coverage();

			coverage.setBbox(
					new BoundingBox(
							CRS, 
							Double.parseDouble(MINX), 
							Double.parseDouble(MINY), 
							Double.parseDouble(MAXX), 
							Double.parseDouble(MAXY)
					)
			);

			CoverageStore coverageStore = new CoverageStore();
			coverageStore.setGeoServer(defaultGeoserver);
			coverageStore.setName(storeName);
			coverageStore.setNameSpace("fenix");
			geoserverDao.save(coverageStore);

			coverage.setCoverageStore(coverageStore);				
			coverage.setDefaultStyle(rl.getRequestedStyle());
			coverage.setDescription("Layer imported by FENIX:" + baseName);
			coverage.setLabel(baseName + " (RST)");
			coverage.setLayerName(baseName);
			gi.createInternalLayer(coverage);

			rl.setCoverage(coverage);
			wmsMapProviderDao.save(rl);
			LOGGER.info("Layer " + rl + " imported successfully.");

			response.getWriter().println(rl.getResourceId());
		} finally {
		}
	}	
	
	private RasterLayer buildLayerByMetadata(File geotiffFile) {
		File parent = geotiffFile.getParentFile();
		File mdFile = new File(parent, "metadata.xml");
		if(! mdFile.canRead())
			return null;
		else {
			try {
				InternalWMSLayer il = LayerMetadataParser.parse(mdFile);
				if (!(il instanceof RasterLayer)) {
					LOGGER.warn("Metadata describes a wrong resource ("+il+")");
					return null;
				}
				return (RasterLayer) il;
			} catch (Exception ex) {
				LOGGER.error("Error parsing Raster metadata", ex);
				return null;
			}
		}
	}
	
	public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (f.isDirectory())
        	ext = null;
        else if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
	
	public boolean isTwf() {
		return tfw;
	}

	public void setTwf(boolean tfw) {
		this.tfw = tfw;
	}

}
