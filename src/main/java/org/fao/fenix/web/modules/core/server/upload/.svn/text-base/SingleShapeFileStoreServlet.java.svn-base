package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.DataStore;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.map.metadata.LayerMetadataParser;
import org.fao.fenix.core.persistence.FenixFakeDataSource;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.map.geoserver.GeoServerDao;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.upload.PGHandler;
import org.fao.fenix.map.upload.ShpInfo;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class for Servlet: SingleShapeFileUploadServlet
 *
 * @web.servlet name="SingleShapeFileUploadServlet" display-name="SingleShapeFileUploadServlet"
 *
 * @web.servlet-mapping url-pattern="/SingleShapeFileUploadServlet"
 *
 *
 *                      TODO>> The progress bar is following the loading of the file into the server
 *                      memory. It is not reflecting the subsequent process of interpreting the file
 *                      into java objects and writing these to the database.<br/>
 *                      TODO>> Inform the user that the saving in the database has succeeded. <br/>
 *
 */

public class SingleShapeFileStoreServlet
		extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	static final Logger LOGGER = Logger.getLogger(SingleShapeFileStoreServlet.class);
	static final long serialVersionUID = 790L;

//	private static String DEFAULT_CRS = "EPSG:4326";

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		ServletContext servletContext = this.getServletConfig().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		FenixFakeDataSource fenixFakeDataSource = (FenixFakeDataSource) wac.getBean("fenixFakeDataSource");
		WMSMapProviderDao wmsMapProviderDao = (WMSMapProviderDao) wac.getBean("wmsMapProviderDao");
		GeoServerDao geoserverDao = (GeoServerDao) wac.getBean("geoServerDao");
		GeoServer defaultGeoserver = (GeoServer) wac.getBean("geoserver");
		DataStore defaultDatastore = (DataStore) wac.getBean("dbdatastore");

		try {
			LOGGER.info("-------- SingleShapeFileStoreServlet start ---------------------");

			Map params = request.getParameterMap();

//			String CRS = ((String[]) params.get("CRS"))[0];
//			String MINX = ((String[]) params.get("MINX"))[0];
//			String MINY = ((String[]) params.get("MINY"))[0];
//			String MAXX = ((String[]) params.get("MAXX"))[0];
//			String MAXY = ((String[]) params.get("MAXY"))[0];
//
//			String GEOM = ((String[]) params.get("GEOM"))[0];
//			String TYPE = ((String[]) params.get("TYPE"))[0];
			String COLUMN = ((String[]) params.get("selected_attribute"))[0];
			String SHAPEFILE = ((String[]) params.get("shape_path_url"))[0];
			SHAPEFILE = URLDecoder.decode(SHAPEFILE, "UTF-8");
			SHAPEFILE = SHAPEFILE.toLowerCase().endsWith(".shp") ?
							SHAPEFILE :
							SHAPEFILE.substring(0, SHAPEFILE.toLowerCase().indexOf(".shp") + 4);

			if (fenixFakeDataSource != null) {
				// ////////////////////////////////////////////////////////////////////
				//
				// CREATING The GeoServer FeatureTypes.
				//
				// ////////////////////////////////////////////////////////////////////

				String url = fenixFakeDataSource.getUrl();
				String username = fenixFakeDataSource.getUsername();
				String password = fenixFakeDataSource.getPassword();

				String host = getHost(url);
				int port = getPort(url);
				String database = getDB(url);
				PGHandler pg = new PGHandler(host, port, database, username, password);
				File shapeFile = new File(SHAPEFILE);

				// Find or create GeoServer instance
				GeoServer geoserver;
				List<GeoServer> gsList = geoserverDao.findAllGeoServers();
				if (gsList.size() > 0)
					geoserver = gsList.get(0);
				else {
					log("Creating GeoServer instance in database.");
					geoserver = defaultGeoserver;
					geoserverDao.save(geoserver);
				}

				// Find or create DataStore instance
				DataStore fenixDs = null;
				List<DataStore> dsList = geoserverDao.findAllDataStores();
				if (dsList.size() > 0) {
					for (DataStore dataStore : dsList) {
						if (dataStore.getName().equals("fenixDB") &&
								dataStore.getNameSpace().equals("fenix")) {
							fenixDs = dataStore;
							break;
						}
					}
				}

				if (fenixDs == null) {
					log("Creating Datastore instance in database.");
					fenixDs = defaultDatastore;
					fenixDs.setGeoServer(geoserver);
					geoserverDao.save(fenixDs);
				}

				// Create DBFeatureLayer
				DBFeatureLayer dbfl;
				dbfl = buildLayerByMetadata(shapeFile);

				if (dbfl == null) { // no metadata file

					dbfl = new DBFeatureLayer();
					dbfl.setTitle("Layer uploaded");
					dbfl.setAbstractAbstract("-");
					dbfl.setSharingCode(SharingCode.Private.name());
					dbfl.setTablename("unset"); // set it later
					dbfl.setJoinColumn(COLUMN);
				}

				FeatureType ft = new FeatureType();
				ft.setTitle(dbfl.getTitle());
				ft.setAbstract(dbfl.getAbstractAbstract());
				ft.setDataStore(fenixDs);

				ft.setLayerName(dbfl.getTablename()); // we know it in advance ONLY for auto
				// imported layers
				ft.setBbox(new BoundingBox()); // set it after shp parsing
				ft.setDefaultStyle("default"); // set it after shp parsing
				dbfl.setFeatureType(ft);

				ShpInfo shpInfo = new ShpInfo(shapeFile);
				ft.setBbox(shpInfo.getBBox());
				if(ft.getBbox().getSrs() == null) {
					// maybe the user set it by hand
					String CRS = ((String[]) params.get("CRS"))[0];
					LOGGER.warn("Null SRS for shape " + dbfl.getResourceId()
									+ "-- Trying user supplied value '"+CRS+"'");
					ft.getBbox().setSrs(CRS);
				}
				//To Obtain default Style from metadato
				String defaultStyle;
				if((defaultStyle = obtainDefaultStyle(shapeFile)) == null)
				{
				//There is not default style in metadato
				switch (shpInfo.getDimensions()) {
				case 2:
					ft.setDefaultStyle("polygon");
					break;
				case 1:
					ft.setDefaultStyle("line");
					break;
				case 0:
					ft.setDefaultStyle("point");
					break;
				default:
					ft.setDefaultStyle("default"); // ??
				}
				}
				else
				{
					//There is default style in metadato
					ft.setDefaultStyle(defaultStyle);
				}
				wmsMapProviderDao.save(dbfl); // ok, get the id

				String tablename = "zlayer_" + dbfl.getResourceId();
				dbfl.setTablename(tablename);
				ft.setLayerName(tablename);

				wmsMapProviderDao.update(dbfl);

				if(pg.importShp(shapeFile, tablename)) {
					//wmsMapProviderDao.fixJoinColumn(dbfl);

					// the table layer has been written: verify its field names
					wmsMapProviderDao.fixJoinColumn(dbfl);

					GeoserverPublisher gp = new GeoserverPublisher();
					//To Publish the Shapefile on Geoserver
					gp.publishShapeFile(dbfl.getFeatureType());
					log("Layer " + dbfl + " imported successfully.");

					response.getWriter().println(dbfl.getResourceId());
					// ///////
					//
					// Import FType into GeoServer and update Fenix stuff
					//
					// ///////

					//setting default style on db




				} else { // error importing shp into db
					wmsMapProviderDao.delete(dbfl);
					response.getWriter().println("ERROR: " + pg.getLastBadFeature());
//					response.getWriter().println("ERROR: error importing features");
				}

			} else {
				LOGGER.error("Fenix DataSource id NULL");
				response.getWriter().println("ERROR: Fenix DataSource id NULL");
			}
		} catch (Exception e) {
			LOGGER.error("Unexpected exception", e);
			response.getWriter().println("ERROR: Unexpected exception: " + e.getMessage());
		} finally {
			LOGGER.info("-------- SingleShapeFileStoreServlet END ---------------------");
		}
	}

	public String obtainDefaultStyle(File shapeFile) {
		File parent = shapeFile.getParentFile();
		String style = null;
		File mdFile = new File(parent, "DBFeatureLayer.xml");
		if (!mdFile.canRead())
			return style;
		else {
				try {
					FileInputStream fis = new FileInputStream(mdFile);
					SAXBuilder saxparser = new SAXBuilder();
					Document doc = saxparser.build(fis);
					Element root = doc.getRootElement();
					List<Element> list= root.getChildren();
					Iterator<Element> el =list.iterator();
					Element elemStyle;
					while(el.hasNext())
					{
						elemStyle = el.next();
						if(elemStyle.getName().equalsIgnoreCase("DefaultStyle"))
						{
							style = elemStyle.getValue();
						}
					}
				} catch (FileNotFoundException e) {
					LOGGER.error("Getting DefaultStyle FileNotFoundException");
					e.printStackTrace();
				}
				catch (JDOMException e) {
					LOGGER.error("Getting DefaultStyle JDOMException");
					e.printStackTrace();
				} catch (IOException e) {
					LOGGER.error("Getting DefaultStyle IOException");
					e.printStackTrace();
				}
		}
	return style;
}

	private DBFeatureLayer buildLayerByMetadata(File shapeFile) {
		File parent = shapeFile.getParentFile();
		File mdFile = new File(parent, "DBFeatureLayer.xml");
		if (!mdFile.canRead())
			return null;
		else {
			try {
				InternalWMSLayer il = LayerMetadataParser.parse(mdFile);
				if (!(il instanceof DBFeatureLayer)) {
					log("Metadata describes a wrong resource");
					return null;
				}
				return (DBFeatureLayer) il;
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
				log("Error processing metadata file: " + ex.getMessage());
				return null;
			}
		}
	}

	private String getDB(final String url) {
		String DB = null;

		DB = url.substring(url.lastIndexOf("/") + 1, url.length());

		return DB;
	}

	private int getPort(final String url) {
		String port = null;

		String host = url.substring(url.indexOf("://") + 3);
		port = host.indexOf(":") > 0 ? host.substring(host.indexOf(":"), host.indexOf("/")) : "5432";

		return Integer.parseInt(port);
	}

	private String getHost(final String url) {
		String host = null;

		host = url.substring(url.indexOf("://") + 3);
		host = host.indexOf(":") > 0 ? host.substring(0, host.indexOf(":")) : host.substring(0, host.indexOf("/"));

		return host;
	}
}