package org.fao.fenix.web.modules.giews.server;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedData;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.project.Project;
import org.fao.fenix.core.domain.project.ProjectObject;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.MetadataDao;
import org.fao.fenix.core.persistence.crisis.CrisisDao;
import org.fao.fenix.core.persistence.crisis.CrisisResultBean;
import org.fao.fenix.core.persistence.isfp.ISFPDao;
import org.fao.fenix.core.persistence.map.GeoViewDao;
import org.fao.fenix.core.persistence.map.ProjectedDataDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.map.updater.CurrentLayerUpdater;
import org.fao.fenix.core.persistence.map.updater.LinkedLayerProvider;
import org.fao.fenix.core.persistence.perspective.ChartDao;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.core.persistence.perspective.ProjectDao;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.map.geoserver.ProjectedDataMetadao;
import org.fao.fenix.map.mapretriever.MapRetriever;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.giews.common.services.GIEWSService;
import org.fao.fenix.web.modules.giews.common.vo.MapAndProject;
import org.fao.fenix.web.modules.rainfall.server.utils.AfricanGaulCode;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GIEWSServiceImpl extends RemoteServiceServlet implements GIEWSService {

	private CodecDao codecDao;
	private ProjectDao projectDao;
	private MetadataDao metadataDao;
	private TextDao textDao;
	private DatasetDao datasetDao;
	private MapDao mapDao;
	private GeoViewDao geoViewDao;
	private LayerGaulUtils layerGaulUtils;
	private WMSMapProviderDao wmsMapProviderDao;
	private CurrentLayerUpdater currentLayerUpdater;
	private ISFPDao isfpDao;
	private CrisisDao crisisDao;
	private ProjectedDataDao projectedDataDao;
	private ProjectedDataMetadao projectedDataMetadao;
	private ChartDao chartDao;
	
	private static final Logger LOGGER = Logger.getLogger(GIEWSServiceImpl.class);
	
	private static final Integer LAYERS = 6;
	private static final List<String> BLACKLIST = new ArrayList<String>();
	private static final int BUFFER = 2048;
	
	static {
		BLACKLIST.add("10");
	}

	private String geGIEWSMapFolderPath(){
		return Setting.getSystemPath() + File.separator + "GiewsMaps" + File.separator;
	}
	
	public MapAndProject getProjectbyCode(String code){
		MapAndProject mapAndProject = new MapAndProject();
				
		List<Project> project = projectDao.findByCode(code);
		List<ResourceChildModel> modelList = new ArrayList<ResourceChildModel>();
		if (project.size() != 0){
			 ResourceChildModel model = new ResourceChildModel(project.get(0).getTitle());
			 model.set("dateModified", project.get(0).getDateLastUpdate());
			 model.set("region", project.get(0).getRegion());
			 model.set("category", project.get(0).getCategories());
			 model.setId(String.valueOf(project.get(0).getResourceId()));
			 model.setType(ResourceType.Project.name());
			 
			 modelList.add(model);
			 mapAndProject.setProjectModel(modelList);
			 
			 List<Map<String, String>> result = new ArrayList<Map<String,String>>();
			 for  (ProjectObject object : project.get(0).getProjectObjectList()){
				 //ndvi map
//				 MapView mapView = mapDao.findByCode(object.getCode());
//				 if (mapView != null && object.getCode().equals("GIEWS_NDVI")){
//					 Map<String, String> mapInfo = new HashMap<String, String>();
//					 mapInfo.put("id", String.valueOf(mapView.getResourceId()));
//					 mapInfo.put("title", mapView.getTitle());
//					 result.add(mapInfo);
//					 continue;
//				 }
				 
				 MapView mapView = mapDao.findByCode(object.getCode());
				 if (mapView != null && object.getCode().equals("GIEWS_NDVI_DA")){
					 Map<String, String> mapInfo = new HashMap<String, String>();
					 mapInfo.put("id", String.valueOf(mapView.getResourceId()));
					 mapInfo.put("title", mapView.getTitle());
					 result.add(mapInfo);
					 continue;
				 }
				 
				 //rainfall
				 String codeString = ""; 
				 if (AfricanGaulCode.africaGaulCodes.contains(project.get(0).getRegion())) codeString = "NOAA";
				 else codeString = "JRC";
				 
//				 if (mapView != null && object.getCode().equals("GIEWS_RAINFALL_" + codeString)){
//					 Map<String, String> mapInfo = new HashMap<String, String>();
//					 mapInfo.put("id", String.valueOf(mapView.getResourceId()));
//					 mapInfo.put("title", mapView.getTitle());
//					 result.add(mapInfo);
//					 continue;
//				 }
				 
				 if (mapView != null && object.getCode().equals("GIEWS_RAINFALL_" + codeString + "_DA")){
					 Map<String, String> mapInfo = new HashMap<String, String>();
					 mapInfo.put("id", String.valueOf(mapView.getResourceId()));
					 mapInfo.put("title", mapView.getTitle());
					 result.add(mapInfo);
					 continue;
				 }
				 
				 
			 }
			 
			 mapAndProject.setMapInfo(result);
			 
			 //textview country brief
			 TextView textViewCB= isfpDao.getCountryBriefText(project.get(0).getRegion());
			 if (textViewCB != null){
				 List<Long> textViewList = new ArrayList<Long>();
				 textViewList.add(textViewCB.getResourceId());
				 mapAndProject.setTextView(textViewList);
			 }
			 
			 
		} else return null;
		 
		return mapAndProject;
	}
	
	public MapAndProject getmapIdbyCode(String code){
		MapAndProject mapAndProject = new MapAndProject();
		MapView mapView = mapDao.findByCode(code);
		 
		 if (mapView != null){
			 List<Map<String, String>> result = new ArrayList<Map<String,String>>();
			 Map<String, String> mapInfo = new HashMap<String, String>();
			 mapInfo.put("id", String.valueOf(mapView.getResourceId()));
			 mapInfo.put("title", mapView.getTitle());
			 result.add(mapInfo);
			 mapAndProject.setMapInfo(result);
		 } else return null; 
		 
		 List<Project> project = projectDao.findByCode("Global_World");
		 if (project.size() != 0){
			 List<ResourceChildModel> modelList = new ArrayList<ResourceChildModel>();
			 ResourceChildModel model = new ResourceChildModel(project.get(0).getTitle());
			 model.set("dateModified", project.get(0).getDateLastUpdate());
			 model.set("region", project.get(0).getRegion());
			 model.set("category", project.get(0).getCategories());
			 model.setId(String.valueOf(project.get(0).getResourceId()));
			 model.setType(ResourceType.Project.name()); 
			 
			 modelList.add(model);
			 
			 mapAndProject.setProjectModel(modelList); 
		 }
		 
		 
		 return mapAndProject;
	}
	
	private String formatDecade(String dekDate){
			
		String year = dekDate.substring(0,4);
		
		Map<String,String> mapMonth = new HashMap<String, String>();
		mapMonth.put("01", "January");
		mapMonth.put("02", "February");
		mapMonth.put("03", "March");
		mapMonth.put("04", "April");
		mapMonth.put("05", "May");
		mapMonth.put("06", "June");
		mapMonth.put("07", "July");
		mapMonth.put("08", "August");
		mapMonth.put("09", "September");
		mapMonth.put("10", "October");
		mapMonth.put("11", "November");
		mapMonth.put("12", "December");
				
		String month = mapMonth.get(dekDate.substring(5,7));
		String dek = dekDate.substring((dekDate.length()-1));
		
		return month + " " + year + " dekad " + dek;
		
	}
	
	public void createGiewsMapsImages(){
		 CrisisResultBean crisisResultBean = crisisDao.getCountriesCrisis();
		 
		createNDVIMapImages();
		createRainfallMapImages();
		createEarlyWarningMapImages();
		createEarlyWarningDateFile(crisisResultBean);
	}
	
    private void createEarlyWarningMapImages(){
		
		Dataset datasetCrisis = datasetDao.findByTitle("Countries in crisis requiring external assistance"); 
		Dataset datasetUnfavourable = datasetDao.findByTitle("Countries with unfavourable prospects for current crops"); 
		
		if (datasetCrisis == null)
			throw new NullPointerException("datasetCrisis not found");
		if (datasetUnfavourable == null)
			throw new NullPointerException("datasetUnfavourable not found");

		//COUNTRIES IN CRISIS REQUIRING EXTERNAL ASSISTANCE MAP
		createEarlyWarningMap(datasetCrisis, "CountriesInCrisis", false);

		//COUNTRIES WITH UNFAVOURABLE PROSPECTS FOR CURRENT CROPS MAP
		createEarlyWarningMap(datasetUnfavourable, "UnfavourableProspects", true);

	}

	private void createNDVIMapImages(){
		
		MapView ndviNormalMapView = mapDao.findByCode("World_GIEWS_NDVI");
				
		if (ndviNormalMapView!=null){
			
			//html file with the decade of the map NDVI
			for (GeoView gv : ndviNormalMapView.getDetachedSortedGeoViewList()){
				if (!gv.isHidden()){
					if (gv.getTitle().contains("dek")){
						String parserDekString = gv.getTitle().substring((gv.getTitle().length()-12), gv.getTitle().length());
						String mapDecade = formatDecade(parserDekString);
						try {
							File file = new File(geGIEWSMapFolderPath() + "ndviDekade.html");
							FileOutputStream stream = new FileOutputStream(file);
							stream.write("<span style='font-family:arial; color:#575757; font-weight:bold; font-size:12px;'>".getBytes());
							stream.write(mapDecade.getBytes());
							stream.write("</span>".getBytes());
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			
			MapRetriever map = new MapRetriever(ndviNormalMapView,System.getProperty("java.io.tmpdir"));
			map.setHeight(273);
			map.setWidth(750);
			BufferedImage image = map.getMapImage();
			try{
				
				File ndviNormal = new File(geGIEWSMapFolderPath() + "ndviNormal.png");
				ImageIO.write(image, "png", ndviNormal);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		MapView ndviDaMapView = mapDao.findByCode("World_GIEWS_NDVI_DA");
		if (ndviDaMapView!=null){
			MapRetriever map = new MapRetriever(ndviDaMapView,System.getProperty("java.io.tmpdir"));
			map.setHeight(273);
			map.setWidth(750);
			BufferedImage image = map.getMapImage();
			try{
				
				File ndviDa = new File(geGIEWSMapFolderPath() + "ndviDa.png");
				ImageIO.write(image, "png", ndviDa);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	private void createRainfallMapImages(){
		
		MapView rfeNormalMapView = mapDao.findByCode("World_GIEWS_RFE");
		if (rfeNormalMapView!=null){
			
			//html file with the decade of the map NDVI
			for (GeoView gv : rfeNormalMapView.getDetachedSortedGeoViewList()){
				if (!gv.isHidden()){
					if (gv.getTitle().contains("dek")){
						String parserDekString = gv.getTitle().substring((gv.getTitle().length()-12), gv.getTitle().length());
						String mapDecade = formatDecade(parserDekString);
						try {
							File file = new File(geGIEWSMapFolderPath() + "rfeDekade.html");
							FileOutputStream stream = new FileOutputStream(file);
							stream.write("<span style='font-family:Arial; color:#575757; font-weight:bold; font-size:12px;'>".getBytes());
							stream.write(mapDecade.getBytes());
							stream.write("</span>".getBytes());
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			
			MapRetriever map = new MapRetriever(rfeNormalMapView,System.getProperty("java.io.tmpdir"));
			map.setHeight(273);
			map.setWidth(750);
			BufferedImage image = map.getMapImage();
			try{
				
				File rfeNormal = new File(geGIEWSMapFolderPath() + "rfeNormal.png");
				ImageIO.write(image, "png", rfeNormal);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		MapView rfeDaMapView = mapDao.findByCode("World_GIEWS_RFE_DA");
		if (rfeDaMapView!=null){
			MapRetriever map = new MapRetriever(rfeDaMapView,System.getProperty("java.io.tmpdir"));
			map.setHeight(273);
			map.setWidth(750);
			BufferedImage image = map.getMapImage();
			try{
				
				File rfeDa = new File(geGIEWSMapFolderPath() + "rfeDa.png");
				ImageIO.write(image, "png", rfeDa);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void updateLayers() {
		LOGGER.info("Create or update layers... START");
		LOGGER.info("NOAA");
		LinkedLayerProvider noaaLLP = CurrentLayerUpdater.RFENOAA;
		currentLayerUpdater.update(noaaLLP, LAYERS);
		LOGGER.info("NOAA-DA");
		LinkedLayerProvider noaaDaLLP = CurrentLayerUpdater.RFENOAADA;
		currentLayerUpdater.update(noaaDaLLP, LAYERS);
		LOGGER.info("JRC");
		LinkedLayerProvider jrcLLP = CurrentLayerUpdater.RFEJRC;
		currentLayerUpdater.update(jrcLLP, LAYERS);
		LOGGER.info("JRC-DA");
		LinkedLayerProvider jrcDaLLP = CurrentLayerUpdater.RFEJRCDA;
		currentLayerUpdater.update(jrcDaLLP, LAYERS);
		LOGGER.info("NDVi");
		LinkedLayerProvider ndviLLP = CurrentLayerUpdater.NDVI;
		currentLayerUpdater.update(ndviLLP, LAYERS);
		LOGGER.info("NDVI-DA");
		LinkedLayerProvider ndviDaLLP = CurrentLayerUpdater.NDVIDA;
		currentLayerUpdater.update(ndviDaLLP, LAYERS);
		LOGGER.info("Create or update layers... DONE");
	}
	
	public void createSingleProject(String code, String label) {
		
		// update-create maps
		updateLayers();
		
		// create rainfall map
		MapView globalRainfall = createGlobalRainfallMap();
		MapView globalRainfallDA = createGlobalRainfallMapDA();
		MapView africaRainfall = createAfricanRainfallMap();
		MapView africaRainfallDA = createAfricanRainfallMapDA();
		MapView ndvi = createNDVIMap();
		MapView ndviDA = createNDVIMapDA();
		
		// blacklist
		if (!BLACKLIST.contains(code)) {
		
			// create project's metadata
			Map<String, String> map = createMetadataMap(code, label);
			
			// create project
			Project project = createProject(map);
			
			// add rainfall map
			if (AfricanGaulCode.africaGaulCodes.contains(code)) {
				project.addProjectObject(africaRainfall);
				project.addProjectObject(africaRainfallDA);
			} else {
				project.addProjectObject(globalRainfall);
				project.addProjectObject(globalRainfallDA);
			}
			
			// add NDVI
			project.addProjectObject(ndvi);
			project.addProjectObject(ndviDA);
			
			// add texts and datasets
			addTextsToProject(project, code);
			addDatasetsToProject(project, code);
		}
	}
	
	public void createProjects() {
		
		// update-create maps
		updateLayers();
		
		// retrieve country codes
		List<Code> codes = codecDao.findAllGaul0();
		
		// create rainfall map
		MapView globalRainfall = createGlobalRainfallMap();
		MapView globalRainfallDA = createGlobalRainfallMapDA();
		MapView africaRainfall = createAfricanRainfallMap();
		MapView africaRainfallDA = createAfricanRainfallMapDA();
		MapView ndvi = createNDVIMap();
		MapView ndviDA = createNDVIMapDA();
		
		for (Code code : codes) {
			
			// blacklist
			if (!BLACKLIST.contains(code.getCode())) {
			
				// create project's metadata
				Map<String, String> map = createMetadataMap(code);
				
				// create project
				Project project = createProject(map);
				
				// add rainfall map
				if (AfricanGaulCode.africaGaulCodes.contains(code.getCode())) {
					project.addProjectObject(africaRainfall);
					project.addProjectObject(africaRainfallDA);
				} else {
					project.addProjectObject(globalRainfall);
					project.addProjectObject(globalRainfallDA);
				}
				
				// add NDVI
				project.addProjectObject(ndvi);
				project.addProjectObject(ndviDA);
				
				// add texts and datasets
				addTextsToProject(project, code.getCode());
				addDatasetsToProject(project, code.getCode());
				
			}
		}
	}
	
	private MapView createGlobalRainfallMap() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("Rainfall (10-daily)");
		mapView.setCode("GIEWS_RAINFALL_JRC");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// set the bounding box (GLOBAL)
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.RFEJRC.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				LOGGER.info("[JRC] Adding " + wmpRainfall.getCode());
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("Rainfall (10-daily) CURRENT");
				else 
					geoViewRainfall.setTitle("Rainfall (10-daily) CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private MapView createGlobalRainfallMapDA() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("Rainfall (10-daily) - Difference with Average");
		mapView.setCode("GIEWS_RAINFALL_JRC_DA");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// set the bounding box (GLOBAL)
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.RFEJRCDA.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				LOGGER.info("[JRC-DA] Adding " + wmpRainfall.getCode());
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("Rainfall (10-daily) - Difference with Average CURRENT");
				else
					geoViewRainfall.setTitle("Rainfall (10-daily) - Difference with Average CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private MapView createAfricanRainfallMap() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("Rainfall (10-daily)");
		mapView.setCode("GIEWS_RAINFALL_NOAA");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// set the bounding box (GLOBAL)
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.RFENOAA.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("Rainfall (10-daily) CURRENT");
				else
					geoViewRainfall.setTitle("Rainfall (10-daily) CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private MapView createAfricanRainfallMapDA() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("Rainfall (10-daily) - Difference with Average");
		mapView.setCode("GIEWS_RAINFALL_NOAA_DA");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// set the bounding box (GLOBAL)
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.RFENOAADA.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("Rainfall (10-daily) - Difference with Average CURRENT");
				else
					geoViewRainfall.setTitle("Rainfall (10-daily) - Difference with Average CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private MapView createNDVIMap() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("Vegetation Index (NDVI - 10-daily)");
		mapView.setCode("GIEWS_NDVI");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// zoom on country
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.NDVI.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				LOGGER.info("[NDVI] Adding " + wmpRainfall.getCode());
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("Vegetation Index (NDVI - 10-daily) CURRENT");
				else
					geoViewRainfall.setTitle("Vegetation Index (NDVI - 10-daily) CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private MapView createNDVIMapDA() {
		
		// initiate map
		MapView mapView = new MapView();
		mapView.setTitle("NDVI (10-daily) - Difference with Average");
		mapView.setCode("GIEWS_NDVI_DA");
		mapView.setSharingCode(SharingCode.PublicAndDownload.name());
		
		// zoom on country
		mapView.setBoundingBox(new BoundingBox());
		
		// save the map view
		mapDao.save(mapView);
		
		// add base layer
		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
		GeoView geoViewBaseLayer = new GeoView(baseLayer);
		geoViewBaseLayer.setTitle("Base Layer");
		mapView.addGeoView(geoViewBaseLayer);
		
		// add last layers
		for (int i = 0 ; i < LAYERS ; i++) {
		
			// create layer's code
			String layerCode = CurrentLayerUpdater.NDVIDA.getLinkedCodeBase() + String.valueOf(i);
			
			// add rainfall layer
			WMSMapProvider wmpRainfall = wmsMapProviderDao.findByCode(layerCode);
			if (wmpRainfall != null) {
				LOGGER.info("[NDVI-DA] Adding " + wmpRainfall.getCode());
				GeoView geoViewRainfall = new GeoView();
				geoViewRainfall.setWmsMapProvider(wmpRainfall);
				if (i > 0)
					geoViewRainfall.setTitle("NDVI (10-daily) - Difference with Average CURRENT");
				else
					geoViewRainfall.setTitle("NDVI (10-daily) - Difference with Average CURRENT-" + i);
				if (i > 0)
					geoViewRainfall.setHidden(true);
				mapView.addGeoView(geoViewRainfall);
				geoViewDao.save(geoViewRainfall);
			}
		
		}
		
		// add crops
		addCropLayers(mapView);
		
		// add basic layers
		addBasicLayers(mapView);
		
		// save the map view
		mapDao.update(mapView);
		
		return mapView;
	}
	
	private void addBasicLayers(MapView mapView) {
		
//		// add base layer
//		WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
//		GeoView geoViewBaseLayer = new GeoView(baseLayer);
//		geoViewBaseLayer.setTitle("Base Layer");
//		mapView.addGeoView(geoViewBaseLayer);
		
		// add GAUL2 layer
		WMSMapProvider wmpGaul2 = wmsMapProviderDao.findByCode("layer_gaul2");
		DBFeatureLayer gaul2 = (DBFeatureLayer)wmpGaul2;
		GeoView geoViewG2 = new GeoView();
		geoViewG2.setWmsMapProvider(gaul2);
		geoViewG2.setTitle("Administrative Level 2");
		geoViewG2.setHidden(true);
		mapView.addGeoView(geoViewG2);
		geoViewDao.save(geoViewG2);
		
		// add GAUL1 layer
		WMSMapProvider wmpGaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
		DBFeatureLayer gaul1 = (DBFeatureLayer)wmpGaul1;
		GeoView geoViewG1 = new GeoView();
		geoViewG1.setWmsMapProvider(gaul1);
		geoViewG1.setTitle("Administrative Level 1");
		geoViewG1.setStyleName("zzlayer_gaul1_black");
		mapView.addGeoView(geoViewG1);
		geoViewDao.save(geoViewG1);
		
		// add GAUL0 layer
		WMSMapProvider wmpGaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
		DBFeatureLayer gaul0 = (DBFeatureLayer)wmpGaul0;
		GeoView geoViewG0 = new GeoView(wmpGaul0);
		geoViewG0.setWmsMapProvider(gaul0);
		geoViewG0.setTitle("Country Boundary");
		geoViewG0.setStyleName("zzlayer_gaul0");
		mapView.addGeoView(geoViewG0);
		geoViewDao.save(geoViewG0);
	}
	
	private void addCropLayers(MapView mapView) {
		
		// crop list
		String[] crops = new String[]{"banana", "barley", "cassava", 
									  "coffee", "cotton", "maize", 
									  "millet", "pulses", "rice", "sorghum", 
									  "soybeans", "sugarcane", "teff", "wheat"};
		// add layer
		for (String cropName : crops) {
		
			// add GAUL0 layer
			WMSMapProvider wmp = wmsMapProviderDao.findByCode("world_" + cropName);
//			LOGGER.info("world_" + cropName + " is null? " + (wmp == null));
			if (wmp != null) {
				GeoView geoViewCrop = new GeoView();
				geoViewCrop.setWmsMapProvider(wmp);
				geoViewCrop.setTitle("World " + cropName);
				geoViewCrop.setHidden(true);
				mapView.addGeoView(geoViewCrop);
				geoViewDao.save(geoViewCrop);
			}
		
		}
	}
	
	private void addTextsToProject(Project project, String gaul0code) {
		List<TextView> texts = textDao.findByRegion(gaul0code);
		for (TextView tv : texts)
			project.addProjectObject(tv);
		projectDao.update(project);
	}
	
	private void addDatasetsToProject(Project project, String gaul0code) {
		List<Dataset> datasets = datasetDao.findByRegion(gaul0code);
		for (Dataset d : datasets)
			project.addProjectObject(d);
		projectDao.update(project);
	}
	
	private Project createProject(Map<String, String> map) {
		Project project = new Project();
		project.setTitle(map.get("title"));
		project.setSharingCode(SharingCode.PublicAndDownload.name());
		projectDao.save(project);
		metadataDao.updateMetadata(map, project.getResourceId());
		return project;
	}
	
	private Map<String, String> createMetadataMap(Code code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", code.getLabel());
		map.put("abstractAbstract", code.getLabel() + " Project of GIEWS (Global Information and Early Warning System) Division of FAO");
		map.put("keywords", code.getLabel() + ", project");
		map.put("provider", "GIEWS Workstation");
		map.put("contact", "Info-GIEWS-Workstation@fao.org");
		map.put("region", code.getCode());
		map.put("categories", "023");
		map.put("startDate", FieldParser.parseDate(new Date()));
		map.put("endDate", FieldParser.parseDate(new Date()));
		map.put("sharingCode", SharingCode.PublicAndDownload.name());
		map.put("dateLastUpdate", FieldParser.parseDate(new Date()));
		map.put("source", "FAO");
		map.put("sourceRegion", "0");
		map.put("code", code.getCode() + "_GIEWS");
		return map;
	}
	
	private Map<String, String> createMetadataMap(String code, String label) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", label);
		map.put("abstractAbstract", label + " Project of GIEWS (Global Information and Early Warning System) Division of FAO");
		map.put("keywords", label + ", project");
		map.put("provider", "GIEWS Workstation");
		map.put("contact", "Info-GIEWS-Workstation@fao.org");
		map.put("region", code);
		map.put("categories", "023");
		map.put("startDate", FieldParser.parseDate(new Date()));
		map.put("endDate", FieldParser.parseDate(new Date()));
		map.put("sharingCode", SharingCode.PublicAndDownload.name());
		map.put("dateLastUpdate", FieldParser.parseDate(new Date()));
		map.put("source", "FAO");
		map.put("sourceRegion", "0");
		map.put("code", code + "_GIEWS");
		return map;
	}

	public void updateProjects() {
		updateLayers();
		updateTexts();
		updateDatasets();
	}
	
	public void updateTexts() {
		List<Project> projects = projectDao.findByCode("_GIEWS");
		LOGGER.info(projects.size() + " Projects Found");
		for (Project p : projects) {
			String region = p.getRegion();
			List<TextView> texts = textDao.findByRegion(region);
			LOGGER.info("["+p.getRegion()+"]\t" + texts.size() + " Texts Found");
			List<ProjectObject> projectObjects = p.getProjectObjectList();
			LOGGER.info("["+p.getRegion()+"]\t" + projectObjects.size() + " Projects Objects Found");
			for (TextView tv : texts) {
				boolean add = true;
				for (ProjectObject po : projectObjects) {
					if (po.getResourceId().longValue() == tv.getResourceId().longValue()) {
						add = false;
						break;
					}
				}
				if (add)
					p.addProjectObject(tv);
			}
			LOGGER.info("["+p.getRegion()+"]\t" + projectObjects.size() + " Projects Objects Found");
		}
	}
	
	public void updateDatasets() {
		List<Project> projects = projectDao.findByCode("_GIEWS");
		LOGGER.info(projects.size() + " Projects Found");
		for (Project p : projects) {
			String region = p.getRegion();
			List<Dataset> datasets = datasetDao.findByRegion(region);
			LOGGER.info("["+p.getRegion()+"]\t" + datasets.size() + " Datasets Found");
			List<ProjectObject> projectObjects = p.getProjectObjectList();
			LOGGER.info("["+p.getRegion()+"]\t" + projectObjects.size() + " Projects Objects Found");
			for (Dataset d : datasets) {
				boolean add = true;
				for (ProjectObject po : projectObjects) {
					if (po.getResourceId().longValue() == d.getResourceId().longValue()) {
						add = false;
						break;
					}
				}
				if (add)
					p.addProjectObject(d);
			}
			LOGGER.info("["+p.getRegion()+"]\t" + projectObjects.size() + " Projects Objects Found");
		}
	}
	
public String createEarlyWarningResources(){
	
	   CrisisResultBean crisisResultBean = crisisDao.getCountriesCrisis();
		
	   createEarlyWarningMapImages();
	   createEarlyWarningDateFile(crisisResultBean);
		
	   //COUNTRIES IN CRISIS REQUIRING EXTERNAL ASSISTANCE TABLE
	  	try {	
			
			File file = new File(getEarlyWarningFolderPath() + File.separator +"CountriesInCrisisTable.html");
    		FileOutputStream stream = new FileOutputStream(file);
            stream.write(crisisResultBean.getHtmlTable().getBytes());
			stream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		//COUNTRIES WITH UNFAVOURABLE PROSPECTS FOR CURRENT CROPS TABLE
		String htmlString = crisisDao.getUnfavourableProspects();
		try {
			File file = new File(getEarlyWarningFolderPath()  + File.separator + "CountriesUnfavourableProspects.html");
			FileOutputStream stream = new FileOutputStream(file);

			stream.write(htmlString.getBytes());
		
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return htmlString;
		
	}
	
private void createEarlyWarningDateFile(CrisisResultBean crisisResultBean) {
	try {	

		File file = new File(Setting.getSystemPath() + File.separator + "earlyWarning" + File.separator +"CountryInCrisisLatestDate.html");
		FileOutputStream stream = new FileOutputStream(file);
		stream.write("<span style='font-family:arial; color:#575757; font-weight:bold; font-size:12px;'>".getBytes());
		stream.write(crisisResultBean.getLatestDate().getBytes());
		stream.write("</span>".getBytes());
		stream.close();
	}
	catch (IOException e) {
		e.printStackTrace();
	}

}

public void createEarlyWarningOutputZipFile(){
	
		try {
	         BufferedInputStream origin = null;
	         FileOutputStream dest = new FileOutputStream(Setting.getSystemPath() + File.separator + "earlyWarning" + File.separator + "output.zip");
	          
	         ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
	        
	         byte data[] = new byte[BUFFER];
	        
	         // get a list of files from crisis directory
	         File f = new File(getEarlyWarningFolderPath());
	         String files[] = f.list();

	         for (int i=0; i<files.length; i++) {
	            System.out.println("Adding: "+files[i]);
	            FileInputStream fi = new FileInputStream(getEarlyWarningFolderPath() + File.separator + files[i]);
	            origin = new BufferedInputStream(fi, BUFFER);
	            ZipEntry entry = new ZipEntry(files[i]);
	            out.putNextEntry(entry);
	            int count;
	            while((count = origin.read(data, 0, 
	              BUFFER)) != -1) {
	               out.write(data, 0, count);
	            }
	            origin.close();
	         }
	         out.close();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }

	}
	
	private void createEarlyWarningMap(Dataset dataset, String dataType, boolean isUnfavourableProspects){

		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
		mm.setHeight(273);
		mm.setWidth(750);
		mm.setBackgroundColor("99CDFF"); //blue all polgons

		// set the bounding box ()
		mm.setBoundingBox(new BoundingBox());

		mm = addEarlyWarningLayers(mm, dataset,isUnfavourableProspects, dataType);

		// compute the map image
		BufferedImage largeImage = mm.getMapImage();
	
		if (largeImage==null)
			throw new FenixException("The large image is NULL");

		File file = new File(getEarlyWarningFolderPath() + File.separator + dataType+"Big.png");

		try{
			ImageIO.write(largeImage, "png", file);
		} catch(Exception ex){
			ex.printStackTrace();
		}

	    //Re-set the height for smaller image
		mm.setHeight(122);
		mm.setWidth(335);
		BufferedImage imageSmall = mm.getMapImage();

		if (imageSmall==null)
			throw new FenixException("The small image is NULL");

		File file2 = new File(getEarlyWarningFolderPath() + File.separator + dataType+"Small.png");

		try{
			ImageIO.write(imageSmall, "png", file2);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private String getEarlyWarningFolderPath(){
		return Setting.getSystemPath() + File.separator + "earlyWarning" + File.separator +  "outputs";
	}
	
   private WMSMapRetriever addEarlyWarningLayers(WMSMapRetriever mm, Dataset dataset, boolean isUnfavourableProspects, String dataType){
		
		//Add GAUL0 layer (create GeoView)
		WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
		if (gaul0 == null)
			throw new NullPointerException("Gaul 0 (layer_gaul0) not found");

		if (!(gaul0 instanceof DBFeatureLayer)) {
			LOGGER.error("Layer '" + gaul0.getLayerName() + "' (id:" + gaul0.getResourceId() + ")" + " is a " + gaul0.getClass().getSimpleName() + " and cannot be joined.");
			throw new FenixException("Layer '" + gaul0.getLayerName() + "' (id:" + gaul0.getResourceId() + ")" + " is a " + gaul0.getClass().getSimpleName() + " and cannot be joined.");
		}

		GeoView gaul0GeoView = new GeoView();
		gaul0GeoView.setWmsMapProvider(gaul0);
		gaul0GeoView.setStyleName("polygon_filled_white"); // set the World to white fill
		mm.addLayer(gaul0GeoView);

		// Create the projected data layer (create GeoView)
		DBFeatureLayer dbfl = (DBFeatureLayer) gaul0;

		// Create projection
		ProjectedData pd = new ProjectedData(dataset, dbfl);
		pd.setTitle(dataset.getTitle());
		pd.setAbstractAbstract(dataset.getAbstractAbstract());
		pd.setDimValue(DataType.date.name(), crisisDao.getMostRecentDateCountriesCrisis(dataType).toString());
		pd.unconstrainDimension(DataType.firstIndicator.name());
		pd.unconstrainDimension(DataType.secondIndicator.name());
		
		

		if (!projectedDataDao.doesContainRows(pd, false))
			throw new FenixException("The selected dataset would project no data onto this layer");

		//projectedDataMetadao.save(pd, dataset.getTitle(), dataset.getAbstractAbstract());
		
		projectedDataMetadao.saveWithDimensionsSet(pd, dataset.getTitle(), dataset.getAbstractAbstract());

		//Set the legend (style) for the projected data
		GeoView pdatagv = new GeoView();
		if(isUnfavourableProspects)
			pdatagv.setStyleName("giewsunfavourable");
		else pdatagv.setStyleName("giewscountrycrises"); 
		pdatagv.setWmsMapProvider(pd);
		pdatagv.setTitle(pd.getTitle());

		// Add the projected data layer to the map
		mm.addLayer(pdatagv);

		return mm;
	}

    public Long  getChartByTitle(String title){
    	List<ChartView> charts = chartDao.findByTitle(title);
    	return charts.get(0).getResourceId();
	}
	
	public void deleteProjects() {
		projectDao.deleteProjectsWhereCodeLike("_GIEWS");
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}

	public void setGeoViewDao(GeoViewDao geoViewDao) {
		this.geoViewDao = geoViewDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setCurrentLayerUpdater(CurrentLayerUpdater currentLayerUpdater) {
		this.currentLayerUpdater = currentLayerUpdater;
	}

	public void setIsfpDao(ISFPDao isfpDao) {
		this.isfpDao = isfpDao;
	}
	
	public void setCrisisDao(CrisisDao crisisDao) {
		this.crisisDao = crisisDao;
	}

	public void setProjectedDataDao(ProjectedDataDao projectedDataDao) {
		this.projectedDataDao = projectedDataDao;
	}

	public void setProjectedDataMetadao(ProjectedDataMetadao projectedDataMetadao) {
		this.projectedDataMetadao = projectedDataMetadao;
	}
	
	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}
	
}