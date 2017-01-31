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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

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
import org.fao.fenix.core.x.PayloadParser;
import org.fao.fenix.core.x.XLayerUtils;
import org.fao.fenix.map.geoserver.GeoserverImporter;
import org.fao.fenix.map.geoserver.io.GeoServerRESTHelper;
import org.fao.fenix.map.layer.geotiff.GeoTiffInfo;
import org.fao.fenix.map.layer.geotiff.GeoTiffUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.geotools.gce.geotiff.GeoTiffReader;

public class RasterLayerPersister {

	private GeoServer defaultGeoserver;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private GeoServerDao geoserverDao;
	
	private XLayerUtils xLayerUtils;
	
	static final private Logger LOGGER = Logger.getLogger(RasterLayerPersister.class);
	
	public Long persist(String mergedFilePath) throws FenixGWTException, XLayerException {
		
		// initialize the result
		Long rasterLayerID = null;
		
		LOGGER.info("UNZIPPING: " + mergedFilePath);
		
		// unzip merged file
		List<String> unzipped = unzip(mergedFilePath);
		String xmlNamePath = findFileByExtension(unzipped, ".xml");
		String zipNamePath = findFileByExtension(unzipped, ".zip");
		
		LOGGER.info("Raster   ZIP: " + zipNamePath);
		LOGGER.info("Metadata XML: " + xmlNamePath);
		
		if ((zipNamePath == null) || (xmlNamePath == null))
			throw new XLayerException("Failure in unzipping the archive. Partial files will be removed from your Workstation and from the remote node.");
		
		// fetch raster properties
		String crs = getMetadataTagContent(xmlNamePath, "srs");
		String minx = getMetadataTagContent(xmlNamePath, "minx");
		String miny = getMetadataTagContent(xmlNamePath, "miny");
		String maxx = getMetadataTagContent(xmlNamePath, "maxx");
		String maxy = getMetadataTagContent(xmlNamePath, "maxy");
		
		// unzip layer file
		List<String> layerComponents = unzip(zipNamePath);
		String aux = findFileByExtension(layerComponents, ".aux");
		String sld = findFileByExtension(layerComponents, ".sld");
		String tif = findFileByExtension(layerComponents, ".tif");
		if (tif == null)
			tif = findFileByExtension(layerComponents, ".tiff");
		
		LOGGER.info("aux: " + aux);
		LOGGER.info("sld: " + sld);
		LOGGER.info("tif: " + tif);
		
		final File geotiffFile = new File(tif);
		
		GeoTiffInfo.Instantiator instantiator = new GeoTiffInfo.Instantiator();
		GeoTiffInfo geoTiffInfo = instantiator.createInfo(geotiffFile);
		if(geoTiffInfo == null) 
			throw new FenixGWTException("The GeoTIFF file could not be parsed: " + instantiator.getLastError().getMessage());
		
		final GeoTiffReader tiffReader = geoTiffInfo.getReader();
		/*
		try {
			int ovsteps = GeoTiffUtils.generateOverviews(tiffReader);
			if (ovsteps < 0) 
				LOGGER.info(">>>>>>>>>> Error creating overviews on " + tif); // TODO someone fix the overviews!!!
			else 
				LOGGER.info("Generated "+ovsteps+" overview steps for " + geotiffFile.getAbsolutePath());
		} catch (Exception e) {
			LOGGER.info("Generate Overviews FAILED. Skipping...");
		}
		*/
		try {
		
			String baseName = FilenameUtils.getBaseName(geotiffFile.getName());
			String storeName = baseName + "_store";
			
			String geoserverURL = defaultGeoserver.getRestUrl();
			
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
			if (!sent) 
				throw new FenixGWTException("Error publishing layer on GS");
			
			RasterLayer rl;
			rl = buildLayerByMetadata(geotiffFile);
			if (rl == null) {
				rl = new RasterLayer();
				rl.setTitle(getMetadataTagContent(xmlNamePath, "title"));
				rl.setAbstractAbstract(getMetadataTagContent(xmlNamePath, "abstractAbstract"));
				rl.setDateLastUpdate(new Date());
				rl.setKeywords(getMetadataTagContent(xmlNamePath, "keywords"));
//				rl.setRequestedStyle(getMetadataTagContent(xmlNamePath, "defaultstyle"));
				rl.setRequestedStyle("raster");
			}
			
			GeoserverImporter gi = new GeoserverImporter();			
			Coverage coverage = new Coverage();
			coverage.setBbox(new BoundingBox(crs, 
											 Double.parseDouble(minx), 
											 Double.parseDouble(miny), 
											 Double.parseDouble(maxx), 
											 Double.parseDouble(maxy)));
			
			CoverageStore coverageStore = new CoverageStore();
			coverageStore.setGeoServer(defaultGeoserver);
			coverageStore.setName(storeName);
			coverageStore.setNameSpace("fenix");
			geoserverDao.save(coverageStore);
			
			coverage.setCoverageStore(coverageStore);				
			coverage.setDefaultStyle(rl.getRequestedStyle());
			coverage.setDescription(getMetadataTagContent(xmlNamePath, "description"));
			coverage.setLabel(getMetadataTagContent(xmlNamePath, "label"));
			coverage.setLayerName(baseName);
			gi.createInternalLayer(coverage);
			
			rl.setCoverage(coverage);
			wmsMapProviderDao.save(rl);
			rasterLayerID = rl.getResourceId();
			LOGGER.info("Layer " + rl + " imported successfully with ID " + rasterLayerID);
			
		} catch (MalformedURLException e) {
			throw new FenixGWTException(e.getMessage());
		}
		
		// return saved layer's ID
		return rasterLayerID;
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
	
	private String findFileByExtension(List<String> filenames, String extension) {
//		LOGGER.info(filenames.size() + " files to search trough");
		for (String n : filenames) {
			LOGGER.info("\t" + n);
			if (n.endsWith(extension)) {
				return n;
			}
		}
		return null;
	}
	
	private String getMetadataTagContent(String metadataPath, String tagName) {
		return PayloadParser.getMetadataTagContent(metadataPath, tagName);
	}
	
	private List<String> unzip(String zipPath) {
		return xLayerUtils.unzip(zipPath);
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

	public void setxLayerUtils(XLayerUtils xLayerUtils) {
		this.xLayerUtils = xLayerUtils;
	}
	
}