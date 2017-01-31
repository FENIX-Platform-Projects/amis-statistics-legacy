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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.DataStore;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.FeatureLayer;
import org.fao.fenix.core.domain.map.layer.ShpFeatureLayer;
import org.fao.fenix.core.persistence.SaveDao;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.utils.Domainer;
import org.fao.fenix.core.x.XLayerUtils;
import org.fao.fenix.map.geoserver.io.GeoServerRESTHelper;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.upload.ShpInfo;
import org.fao.fenix.map.upload.ZippedShpHandler;
import org.fao.fenix.map.util.IOUtil;
import org.fao.fenix.map.util.Zipper;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ShpFeatureLayerPersister {

	private WMSMapProviderDao wmsMapProviderDao;

	private XLayerUtils xLayerUtils;

	private SaveDao saveDao;

	private SaveUniqueDao saveUniqueDao;

	private final static Logger LOGGER = Logger.getLogger(ShpFeatureLayerPersister.class);

	public Long persist(String mergedFilePath) throws XLayerException {

		// unzip merged file
		List<String> unzipped = unzip(mergedFilePath);
		String zipNamePath = unzipped.get(0);
		String xmlNamePath = unzipped.get(1);

		if ((zipNamePath == null) || (xmlNamePath == null))
			throw new XLayerException("Failure in unzipping the archive. Partial files will be removed from your Workstation and from the remote node.");

		// create DataStore
		DataStore ds = parseDataStore(xmlNamePath);
		saveDao.save(ds);

		// create BoundingBox
		BoundingBox bb = parseBoundingBox(xmlNamePath);

		// create FeatureType
		FeatureType ft = parseFeatureType(xmlNamePath);
		ft.setBbox(bb);
		ft.setDataStore(ds);
		saveDao.save(ft);

		LOGGER.info("DATASTORE SAVED WITH ID: " + ds.getId());
		LOGGER.info("FEATURETYPE SAVED WITH ID: " + ft.getId());

		// create ShpFeatureLayer
		ShpFeatureLayer shp = parseShpFeatureLayer(xmlNamePath);
		shp.setFeatureType(ft);
		saveDao.save(ft);

		// import the shape
		return importLayer(shp, new File(xmlNamePath), zipNamePath);
	}

	private Long importLayer(ShpFeatureLayer shp, File metadataFile, String zipPath) {

		ZippedShpHandler zsh = new ZippedShpHandler();
		File rezipFile = null;

		try {

			String storename    = shp.getFeatureType().getDataStore().getName();
			String layername    = shp.getFeatureType().getLayerName();
			GeoServer geoserver = shp.getFeatureType().getDataStore().getGeoServer();

			List<File> unzippedFiles = unzipFiles(zipPath);
			for (File f : unzippedFiles)
				LOGGER.info("\t" + f.getAbsolutePath());

			File shapeFile = getFileByExtension(unzippedFiles, ".shp");
			ShpInfo shpInfo = new ShpInfo(shapeFile);
			shp.getFeatureType().setBbox(shpInfo.getBBox());

			// rezip files with the proper names [???]
			rezipFile = new File(System.getProperty("java.io.tmpdir"), layername + ".zip");
			Zipper.zipFlat(rezipFile, unzippedFiles);

			// send stuff to geoserver
			String restPath = "/rest/folders/" + storename
							+ "/layers/" + layername + "/file.shp?"
							+ "namespace=fenix"
							+ "&SRS=4326&SRSHandling=Force"; // hack
			boolean sent = GeoServerRESTHelper.putBinaryFileTo(geoserver, new FileInputStream(rezipFile), restPath);
			if(!sent)
				LOGGER.error("Could not send "+ shp +" to geoserver (" + metadataFile.getAbsolutePath() + ")");

			// handle SLD
			File sldFile = getFileByExtension(unzippedFiles, ".sld");
			if(sldFile != null) {
				String sldbody = IOUtil.read(sldFile);
				if(sldbody != null) {
					String stylename = layername;
					if(GeoserverPublisher.publishLayerStyle(geoserver, stylename, sldbody, stylename)) {
						if(GeoserverPublisher.setDefaultStyle(geoserver, layername, stylename)) {
							LOGGER.info("Style " + stylename + " set for layer " + layername);
							shp.getFeatureType().setDefaultStyle(stylename);
						} else {
							LOGGER.warn("Could not set style " + stylename + " for layer " + layername);
						}
					} else {
						LOGGER.warn("Could not save style " + stylename);
					}
				} else {
					LOGGER.warn("Could not read SLD " + sldFile);
				}
			}

			return insertLayerInFenix(shp);

		} catch (IllegalAttributeException e) {
			LOGGER.error(e.getMessage());
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (FactoryException e) {
			LOGGER.error(e.getMessage());
		} catch (TransformException e) {
			LOGGER.error(e.getMessage());
		} catch (ZipException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			zsh.cleanup(); // clean extracted files
			if(rezipFile != null)
				LOGGER.warn("TODO: DELETE ZIPFILE " + rezipFile);
		}

		return null;
	}

	private Long insertLayerInFenix(FeatureLayer fl) {
		LOGGER.info("Inserting " + fl + " in fenix");
		new Domainer().populateFenixSystemUser();
		wmsMapProviderDao.save(fl);
		new Domainer().unPopulateFenixSystemUser();
		return fl.getResourceId();
	}

	private ShpFeatureLayer parseShpFeatureLayer(String xmlNamePath) {

		ShpFeatureLayer shp = new ShpFeatureLayer();
		Element element = getRootElement(xmlNamePath);

		String title = ((Element)element.getElementsByTagName("title").item(0)).getTextContent();
		String code = ((Element)element.getElementsByTagName("code").item(0)).getTextContent();
		String abstractabstract = ((Element)element.getElementsByTagName("abstractAbstract").item(0)).getTextContent();
		String keywords = ((Element)element.getElementsByTagName("keywords").item(0)).getTextContent();
		String region = ((Element)element.getElementsByTagName("region").item(0)).getTextContent();
		Date datelastupdate = new Date();
		String categories = ((Element)element.getElementsByTagName("categories").item(0)).getTextContent();
		SharingCode sc = SharingCode.valueOfIgnoreCase(((Element)element.getElementsByTagName("sharingCode").item(0)).getTextContent());

		String sourceName = ((Element)element.getElementsByTagName("source").item(0)).getTextContent();
		String sourceRegion = ((Element)element.getElementsByTagName("source").item(0)).getAttribute("country");
		Source source = saveUniqueSource(sourceName, sourceRegion);

		String providerName = ((Element)element.getElementsByTagName("provider").item(0)).getTextContent();
		String providerRegion = ((Element)element.getElementsByTagName("provider").item(0)).getAttribute("country");
		Source provider = saveUniqueSource(providerName, providerRegion);

		shp.setTitle(title);
		shp.setAbstractAbstract(abstractabstract);
		shp.setCategories(categories);
		shp.setCode(code);
		shp.setDateLastUpdate(datelastupdate);
		shp.setKeywords(keywords);
		shp.setRegion(region);
		shp.setSharingCode(sc.name());
		shp.setSource(source);
		shp.setProvider(provider);

		return shp;
	}

	private Source saveUniqueSource(String title, String region) {
		Source source = new Source(title, region);
		return saveUniqueDao.saveUnique(source);
	}

	private FeatureType parseFeatureType(String xmlNamePath) {
		FeatureType ft = new FeatureType();
		Element element = getRootElement(xmlNamePath);
		NodeList featuretypeNode = element.getElementsByTagName("featuretype");
		for (int i = 0 ; i < featuretypeNode.getLength() ; i++) {
			Element featuretypeElement = (Element)featuretypeNode.item(i);
			String title = ((Element)featuretypeElement.getElementsByTagName("title").item(0)).getTextContent();
			String abstractabstract = ((Element)featuretypeElement.getElementsByTagName("abstract").item(0)).getTextContent();
			String defaultstyle = ((Element)featuretypeElement.getElementsByTagName("defaultstyle").item(0)).getTextContent();
			String layername = ((Element)featuretypeElement.getElementsByTagName("layername").item(0)).getTextContent();
			ft.setAbstract(abstractabstract);
			ft.setDefaultStyle(defaultstyle);
			ft.setLayerName(layername);
			ft.setTitle(title);
		}
		return ft;
	}

	private BoundingBox parseBoundingBox(String xmlNamePath) {
		BoundingBox bb = new BoundingBox();
		Element element = getRootElement(xmlNamePath);
		NodeList bboxNode = element.getElementsByTagName("bbox");
		for (int i = 0 ; i < bboxNode.getLength() ; i++) {
			Element bboxElement = (Element)bboxNode.item(i);
			double minx = Double.parseDouble(((Element)bboxElement.getElementsByTagName("minx").item(0)).getTextContent());
			double miny = Double.parseDouble(((Element)bboxElement.getElementsByTagName("miny").item(0)).getTextContent());
			double maxx = Double.parseDouble(((Element)bboxElement.getElementsByTagName("maxx").item(0)).getTextContent());
			double maxy = Double.parseDouble(((Element)bboxElement.getElementsByTagName("maxy").item(0)).getTextContent());
			String srs = ((Element)bboxElement.getElementsByTagName("srs").item(0)).getTextContent();
			bb.setMaxX(maxx);
			bb.setMaxY(maxy);
			bb.setMinX(minx);
			bb.setMinY(miny);
			bb.setSrs(srs);
			LOGGER.info(bb.getSrs() + " | " + bb.getMinX() + " | " + bb.getMaxX() + " | " + bb.getMinY() + " | " + bb.getMaxY());
		}
		return bb;
	}

	private DataStore parseDataStore(String xmlNamePath) {
		DataStore ds = new DataStore();
		Element element = getRootElement(xmlNamePath);
		NodeList datastoreNode = element.getElementsByTagName("datastore");
		for (int i = 0 ; i < datastoreNode.getLength() ; i++) {
			Element datastoreElement = (Element)datastoreNode.item(i);
			String name = ((Element)datastoreElement.getElementsByTagName("name").item(0)).getTextContent();
			String namespace = ((Element)datastoreElement.getElementsByTagName("namespace").item(0)).getTextContent();
			ds.setName(name);
			ds.setNameSpace(namespace);
			ds.setDsType("Shapefile");
			ds.setGeoServer(wmsMapProviderDao.getGeoserver());
			LOGGER.info(ds.getName() + " | " + ds.getNameSpace() + " | " + ds.getDsType() + " | " + ds.getGeoServer().getId());
		}
		return ds;
	}

	private Element getRootElement(String xmlPath) {
		try {
			InputStream is =  new FileInputStream(new File(xmlPath));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			return document.getDocumentElement();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	private File getFileByExtension(List<File> unzippedFiles, String extension) {
		for (File f : unzippedFiles)
			if (f.getAbsolutePath().endsWith(extension))
				return f;
		return null;
	}

	private List<String> unzip(String zipPath) {
		return xLayerUtils.unzip(zipPath);
	}

	private List<File> unzipFiles(String zipPath) {
		return xLayerUtils.unzipFiles(zipPath);
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setxLayerUtils(XLayerUtils xLayerUtils) {
		this.xLayerUtils = xLayerUtils;
	}

	public void setSaveDao(SaveDao saveDao) {
		this.saveDao = saveDao;
	}

	public void setSaveUniqueDao(SaveUniqueDao saveUniqueDao) {
		this.saveUniqueDao = saveUniqueDao;
	}

}