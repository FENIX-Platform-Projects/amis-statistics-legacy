package org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

public class CropMaps {
	
	private LayerGaulUtils layerGaulUtils;
	private WMSMapProviderDao wmsMapProviderDao;
	private CodecDao codecDao;
	
	public CropLegend getCropMapImagesForCountryBrief(String regionCode) throws FenixGWTException {

		CropLegend cropLegend = new CropLegend();
		
		try {

				// Find the needed layers
				List<String> cropLayerList = new ArrayList<String>();
				String layers = codecDao.getLabelFromCodeCodingSystem(regionCode,"Crops_per_country", "0", "EN");
				if (layers.equals(regionCode)){
					return null;
				}
				StringTokenizer layerCodes = new StringTokenizer(layers,";");
				while (layerCodes.hasMoreTokens()) {
					cropLayerList.add(layerCodes.nextToken());
			     }
				
				// create the map builder
				WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
				mm.setHeight(270);
				mm.setWidth(330);
				
				WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
				GeoView gv1 = new GeoView(gaul1);
				gv1.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(regionCode));
				gv1.setStyleName("zzlayer_gaul1_black");
				mm.addLayer(gv1);
				
				String titleFile="";
				int count=0;
				for (String cropLayer : cropLayerList){
					if (count == 0) titleFile += cropLayer;
					else titleFile += "-" + cropLayer;
					cropLegend.addSetCodeCropLayers(cropLayer);
					List<String> temp = new ArrayList<String>();
					StringTokenizer cropName = new StringTokenizer(cropLayer,"_");
					while (cropName.hasMoreTokens()) {
						temp.add(cropName.nextToken());
				    }
					cropLegend.addSetCropName(temp.get(1));
					mm.addLayer(wmsMapProviderDao.findByCode(cropLayer));
					count++;
					if (count > 2) break;
				}
				
				WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
				GeoView gv0 = new GeoView(gaul0);
				gv0.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(regionCode));
				gv0.setStyleName("polygon_filled");
				mm.addLayer(gv0);
				
				// get the bounding box
				BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(regionCode));
				mm.setBoundingBox(bbox);

				// compute the map image
				BufferedImage image = mm.getMapImage();
				File file = new File(System.getProperty("java.io.tmpdir") + File.separator + titleFile + ".png");
				ImageIO.write(image, "png", file);

				// add to the list
				cropLegend.setPathMapFile(System.getProperty("java.io.tmpdir") + File.separator + titleFile + ".png");
		

		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}

		return cropLegend;
	}
	
	public CropLegend getCroplandMapImagesForCountryBrief(String regionCode) throws FenixGWTException {

		CropLegend cropLegend = new CropLegend();
		
		try {

				// Find the needed layers
				List<String> cropLayerList = new ArrayList<String>();
				String layers = codecDao.getLabelFromCodeCodingSystem(regionCode,"Cropland", "0", "EN");
				
				if (layers.equals(regionCode) || layers.equals("")) return null;
				
				StringTokenizer layerCodes = new StringTokenizer(layers,";");
				while (layerCodes.hasMoreTokens()) {
					cropLayerList.add(layerCodes.nextToken());
			     }
				
				// create the map builder
				WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
				mm.setHeight(270);
				mm.setWidth(330);
				
				WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
				GeoView gv1 = new GeoView(gaul1);
				gv1.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(regionCode));
				gv1.setStyleName("zzlayer_gaul1_black");
				mm.addLayer(gv1);
				
				String titleFile="";
				int count=0;
				for (String cropLayer : cropLayerList){
					if (count == 0) titleFile += cropLayer;
					else titleFile += "-" + cropLayer;
					cropLegend.addSetCodeCropLayers(cropLayer);
					List<String> temp = new ArrayList<String>();
					StringTokenizer cropName = new StringTokenizer(cropLayer,"_");
					while (cropName.hasMoreTokens()) {
						temp.add(cropName.nextToken());
				    }
					cropLegend.addSetCropName(temp.get(1));
					mm.addLayer(wmsMapProviderDao.findByCode(cropLayer));
					count++;
					if (count > 2) break;
				}
				
				WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
				GeoView gv0 = new GeoView(gaul0);
				gv0.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(regionCode));
				gv0.setStyleName("polygon_filled");
				mm.addLayer(gv0);
				
				// get the bounding box
				BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(regionCode));
				mm.setBoundingBox(bbox);

				// compute the map image
				BufferedImage image = mm.getMapImage();
				File file = new File(System.getProperty("java.io.tmpdir") + File.separator + titleFile + ".png");
				ImageIO.write(image, "png", file);

				// add to the list
				cropLegend.setPathMapFile(System.getProperty("java.io.tmpdir") + File.separator + titleFile + ".png");
		

		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}

		return cropLegend;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}
	

}
