/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.core.server.upload;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.DataStore;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.map.metadata.LayerMetadataParser;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.FenixFakeDataSource;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.map.geoserver.GeoServerDao;
import org.fao.fenix.core.x.PayloadParser;
import org.fao.fenix.core.x.XLayerUtils;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.upload.PGHandler;
import org.fao.fenix.map.upload.ShpInfo;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;

public class ShapeFilePersister {

	private FenixFakeDataSource fenixFakeDataSource;

	private WMSMapProviderDao wmsMapProviderDao;

	private GeoServerDao geoserverDao;

	private GeoServer defaultGeoserver;

	private DataStore defaultDatastore;

	private XLayerUtils xLayerUtils;

	private final static Logger LOGGER =  Logger.getLogger(ShapeFilePersister.class);

	@SuppressWarnings("static-access")
	public Long persist(String mergedPath) throws XLayerException {

		// initialize the result
		Long dbFeatureLayerID = null;

		// unzip merged file
		List<String> unzipped = unzip(mergedPath);
		String xmlNamePath = findFileByExtension(unzipped, ".xml");
		String zipNamePath = findFileByExtension(unzipped, ".zip");

		if ((zipNamePath == null) || (xmlNamePath == null))
			throw new XLayerException("Failure in unzipping the archive. Partial files will be removed from your Workstation and from the remote node.");

		// unzip layer file
		List<String> layerComponents = unzip(zipNamePath);
		String shpNamePath = findFileByExtension(layerComponents, ".shp");
		String shxNamePath = findFileByExtension(layerComponents, ".shx");
		String dbfNamePath = findFileByExtension(layerComponents, ".dbf");
		String sldNamePath = findFileByExtension(layerComponents, ".sld");

		LOGGER.info(shpNamePath + ", " + shxNamePath + ", " + dbfNamePath + ", " + sldNamePath + ", "  +xmlNamePath);
		String joincolumn = getMetadataTagContent(xmlNamePath, "joincolumn");
		String title = getMetadataTagContent(xmlNamePath, "title");
		String tablename = getMetadataTagContent(xmlNamePath, "tablename");
		String abstractAbstract = getMetadataTagContent(xmlNamePath, "abstractAbstract");

		LOGGER.info("joincolumn: " + joincolumn);
		LOGGER.info("title: " + title);
		LOGGER.info("tablename: " + tablename);
		LOGGER.info("abstractAbstract: " + abstractAbstract);

		if (fenixFakeDataSource != null) {

			// FENIX credentials
			String url = fenixFakeDataSource.getUrl();
			String username = fenixFakeDataSource.getUsername();
			String password = fenixFakeDataSource.getPassword();

			// server details
			String host = getHost(url);
			int port = getPort(url);
			String database = getDB(url);
			PGHandler pg = new PGHandler(host, port, database, username, password);
			File shapeFile = new File(shpNamePath);

			// Find or create GeoServer instance
			GeoServer geoserver;
			List<GeoServer> gsList = geoserverDao.findAllGeoServers();
			if (gsList.size() > 0) {
				geoserver = gsList.get(0);
			} else {
				LOGGER.info("Creating GeoServer instance in database.");
				geoserver = defaultGeoserver;
				geoserverDao.save(geoserver);
			}

			// Find or create DataStore instance
			DataStore fenixDs = null;
			List<DataStore> dsList = geoserverDao.findAllDataStores();
			if (dsList.size() > 0) {
				for (DataStore dataStore : dsList) {
					if (dataStore.getName().equals("fenixDB") && dataStore.getNameSpace().equals("fenix")) {
						fenixDs = dataStore;
						break;
					}
				}
			}

			// Creating Datastore instance in database
			if (fenixDs == null) {
				LOGGER.info("Creating Datastore instance in database.");
				fenixDs = defaultDatastore;
				fenixDs.setGeoServer(geoserver);
				geoserverDao.save(fenixDs);
			}

			// Create DBFeatureLayer
			DBFeatureLayer dbfl;
			dbfl = buildLayerByMetadata(shapeFile);
			if (dbfl == null) {
				dbfl = new DBFeatureLayer();
				dbfl.setTitle(title);
				dbfl.setAbstractAbstract(abstractAbstract);
				dbfl.setSharingCode(SharingCode.Private.name());
				dbfl.setTablename(tablename);
				dbfl.setJoinColumn(joincolumn);
			}

			// create feature type
			FeatureType ft = new FeatureType();
			ft.setTitle(dbfl.getTitle());
			ft.setAbstract(dbfl.getAbstractAbstract());
			ft.setDataStore(fenixDs);
			ft.setLayerName(dbfl.getTablename());

			// imported layers
			ft.setBbox(new BoundingBox());
			ft.setDefaultStyle("default");
			dbfl.setFeatureType(ft);
			dbfl.setDateLastUpdate(new Date());

			// shape info
			try {

				ShpInfo shpInfo = new ShpInfo(shapeFile);
				ft.setBbox(shpInfo.getBBox());
				ft.getBbox().setSrs("EPSG:4326"); // TODO make it more dynamic...

				switch (shpInfo.getDimensions()) {
					case 2: ft.setDefaultStyle("polygon"); break;
					case 1: ft.setDefaultStyle("line"); break;
					case 0: ft.setDefaultStyle("point"); break;
					default: ft.setDefaultStyle("default"); // ??
				}

				wmsMapProviderDao.save(dbfl); // I have the ID at this point
				dbFeatureLayerID = dbfl.getResourceId();

				tablename = "zlayer_" + dbfl.getResourceId();
				dbfl.setTablename(tablename);
				ft.setLayerName(tablename);

				wmsMapProviderDao.update(dbfl);

				if(pg.importShp(shapeFile, tablename)) {
					wmsMapProviderDao.fixJoinColumn(dbfl);
					GeoserverPublisher gp = new GeoserverPublisher();
					gp.publishShapeFile(dbfl.getFeatureType());
					LOGGER.info("Layer " + dbfl + " imported successfully.");
				} else {
					wmsMapProviderDao.delete(dbfl);
					LOGGER.error("ERROR: " + pg.getLastBadFeature());
				}

			}  catch (FactoryException e) {
				LOGGER.error(e.getMessage());
			} catch (TransformException e) {
				LOGGER.error(e.getMessage());
			} catch (FenixException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			} finally {
				LOGGER.info("DBFeatureLayer Succesfully imported with ID: " + dbFeatureLayerID);
			}

		}

		// return layer's ID
		return dbFeatureLayerID;
	}

	private String findFileByExtension(List<String> filenames, String extension) {
		for (String n : filenames)
			if (n.endsWith(extension))
				return n;
		return null;
	}

	private DBFeatureLayer buildLayerByMetadata(File shapeFile) {
		File parent = shapeFile.getParentFile();
		File mdFile = new File(parent, "DBFeatureLayer.xml");
		if (!mdFile.canRead()) {
			return null;
		} else {
			try {
				InternalWMSLayer il = LayerMetadataParser.parse(mdFile);
				if (!(il instanceof DBFeatureLayer)) {
					LOGGER.info("Metadata describes a wrong resource");
					return null;
				}
				return (DBFeatureLayer) il;
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
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

	private String getMetadataTagContent(String metadataPath, String tagName) {
		return PayloadParser.getMetadataTagContent(metadataPath, tagName);
	}

	private List<String> unzip(String zipPath) {
		return xLayerUtils.unzip(zipPath);
	}

	public void setFenixFakeDataSource(FenixFakeDataSource fenixFakeDataSource) {
		this.fenixFakeDataSource = fenixFakeDataSource;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setGeoserverDao(GeoServerDao geoserverDao) {
		this.geoserverDao = geoserverDao;
	}

	public void setDefaultGeoserver(GeoServer defaultGeoserver) {
		this.defaultGeoserver = defaultGeoserver;
	}

	public void setDefaultDatastore(DataStore defaultDatastore) {
		this.defaultDatastore = defaultDatastore;
	}

	public void setxLayerUtils(XLayerUtils xLayerUtils) {
		this.xLayerUtils = xLayerUtils;
	}

}