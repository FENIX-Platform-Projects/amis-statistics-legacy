package org.fao.fenix.web.modules.map.server;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.TabularLayer;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.CQLFilter;
import org.fao.fenix.core.domain.map.cql.CQLFilterOp;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.DataStore;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedData;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedDataDimension;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.coding.HSDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.core.persistence.map.GeoViewDao;
import org.fao.fenix.core.persistence.map.ProjectedDataDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.map.geoserver.GeoServerDao;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.core.persistence.utils.DBTableInfo;
import org.fao.fenix.core.persistence.utils.DatasetUtils;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.persistence.utils.DBTableInfo.Field;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.GaulUtils;
import org.fao.fenix.map.geoserver.ProjectedDataMetadao;
import org.fao.fenix.map.geoserver.io.GeoserverIO;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.mapretriever.ImageMerger;
import org.fao.fenix.map.mapretriever.LegendRetriever;
import org.fao.fenix.map.mapretriever.MapRetriever;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.map.quickmap.DBFLGeometryResolver;
import org.fao.fenix.map.quickmap.GeoPairImpl;
import org.fao.fenix.map.quickmap.LayerValuesProviderImpl;
import org.fao.fenix.map.quickmap.QuickFeatureBuilder;
import org.fao.fenix.map.quickmap.QuickFeatureCollection;
import org.fao.fenix.map.quickmap.QuickMap;
import org.fao.fenix.map.quickmap.QuickMap.Range;
import org.fao.fenix.map.quickmap.iface.FeatureBuilder;
import org.fao.fenix.map.quickmap.iface.GeoPair;
import org.fao.fenix.map.quickmap.iface.GeometryResolver;
import org.fao.fenix.map.quickmap.iface.LayerValuesProvider;
import org.fao.fenix.map.style.TextPosition;
import org.fao.fenix.map.style.TextSymbUtil;
import org.fao.fenix.map.util.ColourUtil;
import org.fao.fenix.map.wmc.WMContextBuilder;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.core.server.utils.WMSPreharvester;
import org.fao.fenix.web.modules.core.server.utils.map.GSLayerSynchronizerWrapper;
import org.fao.fenix.web.modules.core.server.utils.map.GeoTiffHarvesterWrapper;
import org.fao.fenix.web.modules.core.server.utils.map.LayerImporterWrapper;
import org.fao.fenix.web.modules.map.common.services.MapService;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.map.common.vo.Position;
import org.fao.fenix.web.modules.map.common.vo.StaticProjectionVO;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;
import org.fao.fenix.web.modules.map.server.util.ClientGeoViewLoader;
import org.fao.fenix.web.modules.map.server.util.ClientMapViewBuilder;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;
import org.fao.fenix.web.modules.olap.server.utils.OLAPUtils;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.geonet.lib.wmc.om.WMCViewContext;
import org.fao.geonet.lib.wmc.util.WMC2jdom;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MapServiceImpl extends RemoteServiceServlet implements MapService {

	private static final Logger LOGGER = Logger.getLogger(MapServiceImpl.class);

	private DatasetDao datasetDao;
	
	private MapDao mapDao;
	
	private GeoViewDao geoViewDao;
	
	private GeoServerDao geoServerDao;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private ProjectedDataMetadao projectedDataMetadao;
	
	private ProjectedDataDao projectedDataDao;
	
	private HSDao hsDao;
	
	private LayerGaulUtils layerGaulUtils;
	
	private DBTableInfo tableInfo;

	private LayerImporterWrapper layerImporter;
	
	private GSLayerSynchronizerWrapper gsSynch;
	
	private GeoTiffHarvesterWrapper geotiffHarvester;

	private MapViewUtils mapViewUtils = null;
	
	private Resource exportPath = null;
	
	private FileFactory fileFactory;
	
	private CodecDao codecDao;
	
	private UniqueValuesDao uniqueValuesDao;

	private UrlFinder urlFinder;
	
	public List<Long> findAllCountryLayers(String gaul0code) {
		
		List<Long> ids = new ArrayList<Long>();
		
//		Long baseID = wmsMapProviderDao.findLayerByCode("layer_baseraster");
//		ids.add(baseID);
		
		List<Long> startIDs = wmsMapProviderDao.findStartLayers(gaul0code);
		for (Long startID : startIDs)
			ids.add(startID);
		
//		List<Long> countryIDs = wmsMapProviderDao.findAllCountryLayers(gaul0code);
//		for (Long countryID : countryIDs)
//			ids.add(countryID);
		
		List<Long> gaulIDs = wmsMapProviderDao.findAllGAUL(gaul0code);
		for (Long gaulID : gaulIDs)
			ids.add(gaulID);
		
		return ids;
	}

	@Override
	public ClientMapView getMap(long mapId) {
		MapView mapView = mapDao.findById(mapId);
		return ClientMapViewBuilder.build(mapView);
	}

	public List<ClientGeoView> getLayers(List<Long> layerIds) {
        List<ClientGeoView> ret = new ArrayList<ClientGeoView>();

        for(Long layerId: layerIds) {
    		WMSMapProvider wmsMapProvider = wmsMapProviderDao.findById(layerId);
    		LOGGER.info("wmsMapProvider.getLayerType()" + wmsMapProvider.getLayerType());
 
            ClientGeoView cgv = ClientGeoViewLoader.buildClientResource(wmsMapProvider);
            ret.add(cgv);
        }

        return ret;
    }


	public Long createMap(ClientMapView cmv) {
		MapView mapView = getMapViewUtils().build(cmv);
//		MapView mapView = new MapView();
//		mapView.setTitle(cmv.getTitle());
//		ClientBBox cbb = cmv.getBbox();
//		BoundingBox bb = new BoundingBox(cbb.getSrs(), cbb.getMinLon(), cbb.getMinLat(), cbb.getMaxLon(), cbb.getMaxLat());
//		mapView.setBoundingBox(bb);
//
//		System.out.println("CreateMap: TITLE '"+cmv.getTitle()+"' " + bb);
//
//		int pos = 0;
//		for (ClientGeoView cgv : cmv.getLayerList()) {
//			GeoView geoView = buildGeoView(cgv);
//			geoView.setPosition(pos++);
//			mapView.addGeoView(geoView);
//			System.out.println("CreateMap: " + geoView);
//		}

		mapDao.save(mapView);

		System.out.println("CreateMap: ID " + mapView.getResourceId());
		for (GeoView geoView : mapView.getDetachedSortedGeoViewList()) {
			System.out.println("CreateMap: NEW LAYER " + geoView);
		}

		return mapView.getResourceId();
	}

	public Boolean canUpdate(long mapId) {
		try{
			MapView mapView = mapDao.findById(mapId);
//			mapDao.validateAutorisation(mapView);
			return true;
		} catch(FenixException _) {
			return false;
		}
	}


	
	
	// TODO test
	public ClientMapView updateMap(ClientMapView cmv) {
		System.out.println("delete and save");
		
		MapView mapViewNew = getMapViewUtils().build(cmv);

		mapDao.save(mapViewNew);
		
		
		MapView mapView = mapDao.findById(Long.parseLong(cmv.getId()));

		
		mapDao.delete(mapView);
		return ClientMapViewBuilder.build(mapViewNew);
	}
	
	
//	// TODO test
//	public ClientMapView updateMap(ClientMapView cmv) {
//		MapView mapView = mapDao.findById(Long.parseLong(cmv.getId()));
//
//		ClientBBox cbb = cmv.getBbox();
//		BoundingBox bb = new BoundingBox(cbb.getSrs(), cbb.getMinLon(), cbb.getMinLat(), cbb.getMaxLon(), cbb.getMaxLat());
//		mapView.setBoundingBox(bb);
//
//		// We are going to manipulate the geoview list directly.
//		// The dao is supposed to do it (or the entityManager itself) but it
//		// proved to not work perfectly.
//		// Please move this logic into the DAO.
//
//		// 1) remove deleted gv
//		List<GeoView> removedList = new ArrayList<GeoView>();
//
//		for (Iterator<GeoView> vIter = mapView.getGeoViewList().iterator(); vIter.hasNext();) {
//			GeoView geoView = vIter.next();
//			boolean found = false;
//			for (ClientGeoView cgv : cmv.getLayerList()) {
//				if(cgv.isStored()) {
//					if(cgv.getGeoViewId() == geoView.getId()) {
//						found = true;
//						break;
//					}
//				}
//			}
//			if(!found) {
//				System.out.println("Removing " + geoView);
//				removedList.add(geoView);
//				vIter.remove();
//			}
//		}
//		// TODO: perhaps we'll also have to remove the geoviews individually
//
//		// 2) insert new and reorder
//		int pos = 0;
//		for (Iterator<ClientGeoView> cgvIter = cmv.getLayerList().iterator(); cgvIter.hasNext();) {
//			ClientGeoView cgv = cgvIter.next();
//			if(cgv.isStored()) {
//				GeoView gv = find(mapView.getGeoViewList(), cgv.getGeoViewId());
//				System.out.println("Updating " + gv + " from " + cgv);
//				gv.setTitle(cgv.getTitle());
//				gv.setHidden(cgv.isHidden());
//				gv.setPosition(pos);
//				gv.setStyleName(cgv.getStyleName());
//			} else {
//				GeoView gv = getMapViewUtils().buildGeoView(cgv);
//				gv.setPosition(pos);
//				System.out.println("Building " + gv + " from " + cgv);
//				mapView.addGeoView(gv);
//			}
//			pos++;
//		}
//
//		// 3) persist
//		for (GeoView geoView : removedList) {
//			// TODO: improve handling of PD: it should be automatically deleted
//			//   when the using GV is removed.
//			//   Problems: it should also unpublish it from GS, passing through the pdmetadao
//			mapDao.delete(geoView);
//
//			// A projectedData is used once and only once in a GeoView.
//			// If this GV was related to a PD, remove it too.
//			WMSMapProvider	wmp = geoView.getWmsMapProvider();
//			if(wmp instanceof ProjectedData) {
//				projectedDataMetadao.remove((ProjectedData)wmp);
//			}
//		}
//
//		mapDao.update(mapView);
//		return ClientMapViewBuilder.build(mapView);
//	}

	private GeoView find(List<GeoView> gvlist, long id) {
		for (GeoView geoView : gvlist) {
			if(geoView.getId() == id)
				return geoView;
		}
		return null;
	}


	@Override
	public List<FenixResourceVo> getJoinableDataList(long geoViewId) {
		return new ArrayList<FenixResourceVo>();
	}

	/**
	 * <LI> Creates a ProjectedData an publish it </LI>
	 * <LI> Creates a GV using that PD </LI>
	 * <LI> Adds the GV to the MV just after the original GV </LI>
	 *
	 * @param mapViewId The id of the MV that PD is going to be added
	 * @param geoViewId The id of the GV in the MV, whose related layer is going to be joined
	 * @param datasetId The id of the dataset to be projected
	 *
	 * @return The ClientGeoView related to the added GV
	 */
	public ClientGeoView addProjectedData(long mapViewId, long geoViewId, long datasetId)
			throws FenixGWTException {
		System.out.println("mapViewId: " + mapViewId);
		final MapView mapView = mapDao.findById(mapViewId);
		GeoView geoView = null;
		System.out.println("geoViewId: " + geoViewId);
		int idx = 0; // index of the gv in the list
		for (GeoView gv : mapView.getGeoViewList()) {
			System.out.println("gv.getId(): " + gv.getId());
			if(gv.getId() == geoViewId) {
				geoView = gv;
				break;
			}
			idx++;
		}

		if(geoView == null) {
			LOGGER.error("GeoView #" + geoViewId + " is not found in MapView #" + mapViewId);
			throw new FenixGWTException("Internal error: layer not found in map");
		}

		final WMSMapProvider wmp = geoView.getWmsMapProvider();
				
		//LOGGER.error(wmp.getClass().getSimpleName());

//		if (!(wmp instanceof DBFeatureLayer)) {
//			LOGGER.error("Layer '" + wmp.getLayerName()
//						+ "' (id:" + wmp.getResourceId() + ")"
//						+ " is a " + wmp.getClass().getSimpleName()
//						+ " and cannot be joined.");
//			throw new FenixGWTException("Internal error: layer has bad type.");
//		}

		final ProjectedData pd = projectData(datasetId, (DBFeatureLayer)wmp);

		final GeoView pdatagv = new GeoView();
		pdatagv.setWmsMapProvider(pd);
		pdatagv.setTitle(pd.getTitle());

		//-- add the GV to the map
		// shift of one position all the GVs that are after the point of insertion
		for (GeoView gv : mapView.getGeoViewList()) {
			if(gv.getPosition() > idx) {
				gv.setPosition(gv.getPosition() + 1); 
			}
		}
		pdatagv.setPosition(idx+1);
		mapView.addGeoView(pdatagv);

		geoViewDao.save(pdatagv);
		mapDao.save(mapView);

		return ClientGeoViewLoader.buildClientResource(pdatagv);
	}

	/**
	 * Creates a ProjectedData entry and a related GeoView associated to it
	 *
	 * TODO: PD may become dangling if they are not disposed correctly
	 */
	private ProjectedData projectData(long datasetId, DBFeatureLayer dbfl)
			throws FenixGWTException {

		System.out.println("projectData start");
		// -- retrieve dataset
		// TODO: this is a workaround to retrieve a coredataset
		//		 the flexdataset are not available to make a join
//		final Dataset dataset = datasetDao.findById(datasetId);
//		if(dataset instanceof FlexDataset || ((CoreDataset)dataset).getCoreType()==CoreDataset.CoreType.QUALITATIVE) {
//			LOGGER.warn("Dataset '" + dataset.getTitle() + "' is not of the right type and can't be projected.");
//			throw new FenixGWTException("Dataset '" + dataset.getTitle()
//					+ "' is not of the right type and can't be projected.");
//		}
		
		final CoreDataset dataset = datasetDao.findCoreDatasetById(datasetId);
		if(dataset == null) {
			LOGGER.warn("Dataset '" + dataset.getTitle() + "' is not of the right type and can't be projected.");
			throw new FenixGWTException("Dataset '" + dataset.getTitle()
					+ "' is not of the right type and can't be projected.");
		}

		if(! DatasetUtils.containsFeatureCode(dataset)) {
			LOGGER.warn("Dataset '" + dataset.getTitle() + "' has no geographic references.");
			throw new FenixGWTException("Dataset '" + dataset.getTitle()
					+ "' has no geographic references.");
		}

        final String title = "Proj '" + dataset.getTitle() + "' ON '" + dbfl.getTitle() + "'";
        final String abs   = "Projection of data '" + dataset.getTitle() + "' on layer '" + dbfl.getTitle() + "'";
        
        System.out.println("projectData setting informations");
		// - create projection
		final ProjectedData pd = new ProjectedData(dataset, dbfl);
		pd.setTitle(title);
		pd.setAbstractAbstract(abs);

		if(! projectedDataDao.doesContainRows(pd, false))
			throw new FenixGWTException("The selected dataset would project no data onto this layer");

		projectedDataMetadao.save(pd, title, abs);
		return pd;
	}

	@Override
	public void setProjectedDimensions(long geoViewId, List<ClientProjectedDataDimension> dimensions)
			throws FenixGWTException {

		GeoView geoView = geoViewDao.findById(geoViewId);
		WMSMapProvider wmp = geoView.getWmsMapProvider();

		if (!(wmp instanceof ProjectedData))
			throw new IllegalArgumentException("GeoView " + geoViewId
					+ " references to a " + wmp.getClass().getSimpleName()
					+ ". ProjectedData was needed");

		ProjectedData projectedData = (ProjectedData) wmp;
		for (ClientProjectedDataDimension cpdd : dimensions) {
			ProjectedDataDimension pdd;
			if(cpdd.isUnconstrained) {
				pdd = projectedData.unconstrainDimension(cpdd.name);
			}
			else {
				pdd = projectedData.setDimValue(cpdd.name, cpdd.currentId);
			}

			LOGGER.info("Setting filter for " +  projectedData + " : " + pdd);
		}

//		if(!projectedDataDao.doesContainRows(projectedData, true)) {
//			return false;
//			//throw new FenixGWTException("The selected dimensions set would project no data onto this layer");
//		}


		try {
			projectedDataMetadao.update(projectedData, geoServerDao);
		} catch (IllegalArgumentException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	@Override
	public ClientProjectedDataDimension[] getProjectedDimensions(long geoViewId) {
		GeoView geoView = geoViewDao.findById(geoViewId);
		WMSMapProvider wmp = geoView.getWmsMapProvider();

		if (!(wmp instanceof ProjectedData)) {
			throw new IllegalArgumentException("GeoView " + geoViewId
					+ " references to a " + wmp.getClass().getSimpleName()
					+ ". ProjectedData was needed");
		}

		ProjectedData projectedData = (ProjectedData) wmp;
		long datasetId = projectedData.getDataset().getResourceId();
		Map<String, ProjectedDataDimension> mapDim = projectedData.getBoundDimensions();

		ClientProjectedDataDimension ret[] = new ClientProjectedDataDimension[mapDim.size()];
		int idx = 0;

		LOGGER.info("RETRIEVING PROJECTED DIMENSIONS for" +
						" pd:" + projectedData.getResourceId() +
						" projected on ds:" + datasetId );
		
		for (String viewMappedName : mapDim.keySet()) {
			ProjectedDataDimension pdim = mapDim.get(viewMappedName);
			ClientProjectedDataDimension cpdd = new ClientProjectedDataDimension(viewMappedName);
			if(pdim.isUnconstrained())
				cpdd.isUnconstrained = true;
			else
				cpdd.currentId = pdim.getVal();
			cpdd.label = pdim.getHeader();
			
			
			
			Dataset dataset = datasetDao.findById(datasetId);
			
			LOGGER.info("Retrieving distinct values on ds:" + datasetId + " for dim[" + viewMappedName + "]");
			LOGGER.info("pdim.getType(): " + pdim.getType());
			LOGGER.info("getFieldName(): " + pdim.getFieldName());
			LOGGER.info("getHeader(): " + pdim.getHeader());

			
			/** WORKAROUND FOR THE MEASURENT UNITS **/
			if(pdim.getFieldName().contains(DataType.measurementUnit.name())) {
				List/*<? extends Object[]>*/ allvalues = datasetDao.getDistinctValues(datasetId, pdim.getHeader());
				//
							/** TODO: it's all wrong, the labels are not traslated at all, just for the HS **/
							
	
				// copy values content into client bean
				ArrayList<String[]> clientList = new ArrayList<String[]>(allvalues.size());
				for (Object object : allvalues) {
					Object list2[] = (Object[])object;
					String idval[] = new String[2];
					idval[0] = list2[0].toString();
					idval[1] = list2[1].toString();
					clientList.add(idval);			
				}
//				LOGGER.info("MEASUREMENT UNIT CLIENT: " + clientList);
				cpdd.allowableValues = clientList;
				ret[idx++] = cpdd;
			}
			else {
				List<UniqueTextValues> valuesText = uniqueValuesDao.getText(datasetId, pdim.getHeader(), "EN");
				List<UniqueDateValues> valuesDate = new ArrayList<UniqueDateValues>();
				
				if ( valuesText.isEmpty() ) {
					valuesDate = uniqueValuesDao.getDates(datasetId, pdim.getHeader());
				}
				
				if ( !valuesText.isEmpty() ) {
					ArrayList<String[]> clientList = new ArrayList<String[]>(valuesText.size());
					for(UniqueTextValues text : valuesText ) {
						String idval[] = new String[2];			
						idval[0] = text.getValue();
						idval[1] = text.getLabel();
						clientList.add(idval);					
					}	
					cpdd.allowableValues = clientList;
					ret[idx++] = cpdd;
				}
				else if ( !valuesDate.isEmpty() ) {
					ArrayList<String[]> clientList = new ArrayList<String[]>(valuesText.size());
					for(UniqueDateValues date : valuesDate ) {
						String idval[] = new String[2];			
						idval[0] = FieldParser.parseDate(date.getValue());
						idval[1] = FieldParser.parseDate(date.getDate());
						clientList.add(idval);					
					}	
					cpdd.allowableValues = clientList;
					ret[idx++] = cpdd;
				}
			}
			
			System.out.println("cpdd: " + cpdd);
			System.out.println("ret: " + ret);
			
			
			
			
//			List/*<? extends Object[]>*/ allvalues = datasetDao.getDistinctValues(datasetId, pdim.getHeader());
//
//			/** TODO: it's all wrong, the labels are not traslated at all, just for the HS **/
//			
//			
//			// copy values content into client bean
//			ArrayList<String[]> clientList = new ArrayList<String[]>(allvalues.size());
//			for (Object object : allvalues) {
//				Object list2[] = (Object[])object;
//				String idval[] = new String[2];
//				idval[0] = list2[0].toString();
//				idval[1] = list2[1].toString();
//				
//				
//				
//			
//				if(pdim.getType().equals(DataType.commodityCode.name())) {
////					// hack: it's not said that it matches cmommcode datatype name
////					// temporary: this transcoding should be done by getDistinctValues() itself
////					//idval[1] =  hsDao.findByCodeName("0", idval[0]); // FIXME region==0?!?!?
////					idval[1] =  codecDao.getLabelFromCodeCodingSystem(idval[0], "HS", "0", "EN");
////				}
//				
//				for(Descriptor descriptor : dataset.getDatasetType().getDescriptors()) {
//					if(pdim.getType().equals(descriptor.getDataType().name())) {
//						// translate the code
//						for (Option o : descriptor.getOptions()) {
//							CodingType ct = CodingType.valueOfIgnoreCase(o.getOptionName());
//							if (ct != null) {
//								System.out.println("CS: " + o.getOptionValue());
//								idval[1] =  codecDao.getLabelFromCodeCodingSystem(idval[0], o.getOptionValue(), "0", "EN");
//							}
//						}
//						
//						
//						// break
//						break;
//					}
//					
//				}
				
//				
//			}
//			
//			cpdd.allowableValues = clientList;
//			ret[idx++] = cpdd;
		}

		return ret;
	}

//	/**
//	 * @param Dataset is not used at the moment, but will give us info about transcoding.
//	 */
//	private Map<String, String> transcode(Dataset dataset, String dimName, List allvalues) {
//		Map<String, String> ret = new HashMap<String, String>();
//
//		// how do we find out if these fields need transcoding?
//		if(dimName.equalsIgnoreCase("commoditycode")) { // FIXME: this is extremely ugly
//			for (Object value : allvalues) {            // the loop is even uglier....
//				String hs = hsDao.findByCodeName("0", value.toString()); // FIXME region==0?!?!?
//				ret.put(value.toString(), hs);
//			}
//		} else {
//			for (Object value : allvalues) {            // the loop is even uglier....
//				 ret.put(value.toString(), value.toString());
//			}
//		}
//		return ret;
//	}

	
	@Override
	public String[] getColumnNames(long wmsMapProviderId) {
		TabularLayer tabularLayer = getTabularLayer(wmsMapProviderId);
		List<String> fieldList = geoServerDao.getColumnNames(tabularLayer);

		// remove the geometry column
		List<String> filteredfieldList = new ArrayList<String>(fieldList.size());
		for (String fieldName : fieldList) {
			if (fieldName.equalsIgnoreCase(tabularLayer.getGeomColumn())) {
				continue;
			} else {
				filteredfieldList.add(fieldName);
			}
		}

		return filteredfieldList.toArray(new String[filteredfieldList.size()]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<List<String>> getRecords(long wmsMapProviderId,
			List<String> columnNames) {
		TabularLayer tabularLayer = getTabularLayer(wmsMapProviderId);
		List originalRowList = geoServerDao.getColumnValues(tabularLayer,
				columnNames);
		// Copy content in a new List: original objects may be unavailable to
		// GWT.
		List<List<String>> rowList = new ArrayList<List<String>>();

		for (Object[] originalRow : (List<Object[]>) originalRowList) {
			List<String> row = new ArrayList<String>(originalRow.length);
			for (Object field : originalRow) {
				row.add(field.toString());
			}
			rowList.add(row);
		}
		return rowList;
	}

	private TabularLayer getTabularLayer(long wmsMapProviderId) {

		WMSMapProvider wmp = wmsMapProviderDao.findById(wmsMapProviderId);
		return checkTabularLayer(wmp);
	}
	
	public TabularLayer checkTabularLayer(WMSMapProvider wmp) throws IllegalArgumentException {
	    TabularLayer tabularLayer;

	    if(wmp instanceof DBFeatureLayer) {
		tabularLayer = (DBFeatureLayer) wmp;
	    } else if(wmp instanceof ProjectedData) {
		tabularLayer = (ProjectedData) wmp;
	    } else {
		throw new IllegalArgumentException("Given layer is not a TabularLayer (" + wmp.getClass().getSimpleName() + ")");
	    }

	    return tabularLayer;
	}
	

	public void setGeoViewStyle(long geoViewId, String styleName, String sldBody)
	{
		GeoView geoView = geoViewDao.findById(geoViewId);
		WMSMapProvider wmp = geoView.getWmsMapProvider();
		
		// fetch the local geoserver
		GeoServer gs = null;
		String layername = null;

		if( wmp instanceof InternalWMSLayer ) {
			gs = ((InternalWMSLayer)wmp).getGeoServer();
			layername = ((InternalWMSLayer)wmp).getLayerBaseName();
		} else if( wmp instanceof ProjectedData ) {
			gs = ((ProjectedData)wmp).getFeatureType().getDataStore().getGeoServer();
			layername = ((ProjectedData)wmp).getFeatureType().getLayerName();
		} else {
			throw new IllegalArgumentException("GeoView " + geoViewId + " references to a non internal layer.");
		}

		geoView.setStyleName(styleName);
		//geoView.setSldBody(sldBody);  // FIXME if SLD Body is too long an SQL Exception is thrown
		geoViewDao.update(geoView);

		GeoserverPublisher.createStyleForLayer(gs, styleName, sldBody, layername);
	}

	private MapViewUtils getMapViewUtils() {
		if(mapViewUtils == null) {
			mapViewUtils = new MapViewUtils();
			mapViewUtils.setWmsMapProviderDao(wmsMapProviderDao);
		}
		return mapViewUtils;
	}

	/**
	 *
	 * TODO: returning a URL would be much better
	 */
	public String exportAsImage(ClientMapView cmv) {
		
		MapView mapView = getMapViewUtils().build(cmv);
		MapRetriever mapRetriever = new MapRetriever(mapView, System.getProperty("java.io.tmpdir"));
		
		mapRetriever.setWidth(1024);
		mapRetriever.setHeight(768);
		
		BufferedImage bi = mapRetriever.getMapImage();

		try {
			File png = File.createTempFile("map", ".png", exportPath.getFile());
			ImageIO.write(bi, "png", png);
			png.deleteOnExit(); // todo: we need a process that deletes temp files after a while
			return png.getName();
		} catch (IOException ex) {
			LOGGER.warn("Error in saving map image", ex);
			return null;
		}
	}
	
	/**
	 * 
	 * TODO: doesn't work with external WMS!!!!!
	 */
	public String exportAsZip(ClientMapView cmv, Boolean getLegends, Integer width, Integer height) {
		
		MapView mapView = getMapViewUtils().build(cmv);
		LOGGER.info(mapView.getBoundingBox());
			
		MapRetriever mapRetriever = new MapRetriever(mapView, System.getProperty("java.io.tmpdir"));
		
		mapRetriever.setWidth(width);
		mapRetriever.setHeight(height);
		

		BufferedImage bi = mapRetriever.getMapImage();


		File map;
		try {
			map = File.createTempFile("map", ".png", exportPath.getFile());
			ImageIO.write(bi, "png", map);
			LOGGER.info(map.getAbsolutePath() + " | " + map.getName());
//			map.deleteOnExit(); // todo: we need a process that deletes temp files after a while
//			return png.getName();
		} catch (IOException ex) {
			LOGGER.warn("Error in saving map image", ex);
			return null;
		}
		
		// legends 
		int i=0;
	
		List<GeoView> gvList = new ArrayList<GeoView>();
		try {
			for (ClientGeoView clientGeoView : cmv.getLayerList()) {
	//			LOGGER.info(clientGeoView.getTitle() + " | " + clientGeoView.getLagendUrl());
				gvList.add(mapViewUtils.buildGeoView(clientGeoView, false));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		LegendRetriever lr = new LegendRetriever();
		
		String fileName;
		List<File> legends = new ArrayList<File>();
		
		
		for (GeoView gv : gvList){
			lr.setLayer(gv);
			
			try {
				fileName = exportPath.getFile() + File.separator + gv.getTitle() + ".png";
			
				File legendImage = new File(fileName); 
				
				if(lr.retrieve(legendImage)) {
//					LOGGER.info("Legend added");
					
				} else {
					LOGGER.warn("ERROR adding legend.");
				}
				legends.add(legendImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		String zipPath = fileFactory.createMapLegendZip(map, legends, BirtUtil.randomFileExport()); 
		
//		for(ClientGeoView legend : cmv.getLayerList()) {
//			// for each layer creates a legend file
//			System.out.println("legends : " + i + " | " + legend.getTitle() + " | " + legend.getLayerId() + " | " + legend.getLagendUrl() + " | " + legend.getGetMapUrl());
//			
//			String fileName;
//			try {
//				fileName = exportPath.getFile() + File.separator + legend.getTitle() + "_" + i + ".png";
//			
//				File legendImage = new File(fileName); 
//				LegendRetriever lr = new LegendRetriever();
//				
//				if(lr.retrieve(legendImage)) {
//					System.out.println("Legend added");
//	
//				} else {
//					System.out.println("ERROR adding legend.");
//				}
//				
//	//			File png = File.createTempFile("legend" + i, ".png", exportPath.getFile());
//	//			ImageIO.write(legendImage, "png", png);
//	//			png.deleteOnExit(); // todo: we need a process that deletes temp files after a while
//				i++;	
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
//		}
		LOGGER.info("ZIPPATH: " + zipPath);
		
		return zipPath;
	}
	
	

	public String exportAsWmc(ClientMapView cmv) {
		MapView mapView = getMapViewUtils().build(cmv);
		WMCViewContext context = WMContextBuilder.buildWMCContext(mapView);
		WMC2jdom w2j = new WMC2jdom();
		Element root = w2j.toElement(context);
		String ret = new XMLOutputter(Format.getPrettyFormat()).outputString(root);
		return ret;
	}

	public ClientBBox getCountryExtents(String gaul0Code) throws FenixGWTException {
		BoundingBox bb = layerGaulUtils.getExtent(0, Integer.parseInt(gaul0Code));
		if(bb == null) 
			throw new FenixGWTException("Error retrieving GAUL0 info on server. Is GAUL0 layer properly installed?");

		ClientBBox cbb = ClientGeoViewLoader.buildClientBBox(bb);
		return cbb;
	}

	//--------------------------------------------------------------------------
	public List<String> getLayerFieldnames(long dbfLayerId) throws FenixGWTException {

		WMSMapProvider mp = wmsMapProviderDao.findById(dbfLayerId);
		if(mp != null && mp instanceof DBFeatureLayer) {
			DBFeatureLayer dbfl = (DBFeatureLayer)mp;
			List<Field> tableFields = tableInfo.getFields(dbfl.getTablename());
			List<String> ret = new ArrayList<String>(tableFields.size());
			for (Field field : tableFields) {
				ret.add(field.getName());
			}
			return ret;
		} else
			throw new FenixGWTException("Error handling requested layer.");
	}

	public String createLabelStyle(long layerId, StyleDefinition styleDefinition) throws FenixGWTException {

		Color color = ColourUtil.hex2colour(styleDefinition.getColor());
		TextPosition textPosition = mapPosition(styleDefinition.getPosition());
		String sld = TextSymbUtil.createLabelStyle(
				styleDefinition.getFieldName(),
				color,
				styleDefinition.getFontName(),
				styleDefinition.getFontSize(),
				textPosition);
		String stylename = "label_" + layerId 
				+ "_" + styleDefinition.getFieldName().toLowerCase()
				+ "_" + ColourUtil.colour2string(color)
				+ "_" + styleDefinition.getFontName().toLowerCase().replace(' ', '-')
				+ "_" + styleDefinition.getFontSize()
				+ "_" + styleDefinition.getPosition();

		WMSMapProvider mp = wmsMapProviderDao.findById(layerId);
		GeoServer geoServer = ((InternalWMSLayer)mp).getGeoServer();

		GeoserverIO gsio = new GeoserverIO();
		if( ! gsio.existsStyle(geoServer, stylename)) {
			LOGGER.info("Creating style " + stylename);			
			GeoserverPublisher.publishLayerStyle(geoServer, stylename, sld, stylename);
		}
		
		return stylename;
	}
	
	
	private TextPosition mapPosition(Position position) {
		switch(position) {
			case Bottom: return TextPosition.Bottom;
			case BottomLeft: return TextPosition.BottomLeft;
			case BottomRight: return TextPosition.BottomRight;
			case Center: return TextPosition.Center;
			case Left: return TextPosition.Left;
			case Right: return TextPosition.Right;
			case Top: return TextPosition.Top;
			case TopLeft: return TextPosition.TopLeft;
			case TopRight: return TextPosition.TopRight;
			default:
				throw new IllegalArgumentException("Unknown position " + position );
		}

	}
	
	public List<ClientGeoView> getLayersByCodingSystem(String codingSystemTitle, String prefix) {
		LOGGER.info(codingSystemTitle);
		LOGGER.info(prefix);
		List<Code> layers = codecDao.findAllCodesByCSTitleAndPrefix(codingSystemTitle, prefix);
		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		for (Code layer : layers) {
//			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByTitle(layer.getLabel());
			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByCode(layer.getLabel());
			ClientGeoView c = MapUtils.wmsMapProvider2ClientGeoView(urlFinder.geoserverUrl, dbLayer);
			if ( layer.getDescription().equals("off"))
				c.setHidden(true);
			else
				c.setHidden(false);
			clientGeoViews.add(c);
		}
		return clientGeoViews;
	}
	
	public List<ClientGeoView> getLayersByCode(List<String> layersCode) {

		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		System.out.println("Class: MapServiceImpl Function: getLayersByCode Text: Start ");
		for (String layerCode : layersCode) {
			System.out.println("Class: MapServiceImpl Function: getLayersByCode Text: layerCode = "+layerCode);
			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByCode(layerCode);
			System.out.println("Class: MapServiceImpl Function: getLayersByCode Text: After dbLayer");
			ClientGeoView c = MapUtils.wmsMapProvider2ClientGeoView(urlFinder.geoserverUrl, dbLayer);
			if(c!=null)
			{
				System.out.println("Class: MapServiceImpl Function: getLayersByCode Text: After c.getTitle() = "+c.getTitle());
			}
			clientGeoViews.add(c);
		}
		System.out.println("Class: MapServiceImpl Function: getLayersByCode Text: Before return  ");
		return clientGeoViews;
	}
	
	

	public ClientGeoView createJoinDatasetView(JoinDatasetVO joinDatasetVO) throws FenixGWTException {
		ClientGeoView cgv = new ClientGeoView();
		LOGGER.info("here");
		
		String datasetViewName = joinDatasetVO.getDatasetViewName();
//		String layerTableName = joinDatasetVO.getLayerCode();
		GeoView layer = new GeoView(wmsMapProviderDao.findByCode(joinDatasetVO.getLayer()));
		
		String layerTableName = null;
		String layerJoinView = joinDatasetVO.getLayerJoinView();
		/*** TODO: setting lower case in the bean **/
		String sldStyleColumnName = joinDatasetVO.getSldStyleColumnName().toLowerCase();
		String joinDatasetColumnName = joinDatasetVO.getJoinDatasetColumnName();
		String joinLayerColumnName = joinDatasetVO.getJoinLayerColumnName();
		
		
		
		final WMSMapProvider wmp = layer.getWmsMapProvider();

		FeatureType joinedLayer = null;
		DataStore joinDatastore = null;
		
		try {
			DBFeatureLayer dbfl =  (DBFeatureLayer) wmp;
			
			if ( joinDatasetVO.getJoinLayerColumnName() == null ) {
				LOGGER.info("dbfl.getJoinColumn(): " + dbfl.getJoinColumn());
				joinLayerColumnName = dbfl.getJoinColumn();
				joinLayerColumnName = "\"" + joinLayerColumnName + "\""; // adding quotes since the name may have uppercase letters
			}
			layerTableName = dbfl.getTablename();

			// LAYER INFO
			joinedLayer = copyFeatureType(dbfl);
			joinDatastore = copyDataStore(dbfl.getFeatureType().getDataStore());
			
			joinedLayer.setDataStore(joinDatastore);
			
//			joinedLayer = dbfl.getFeatureType();
			
			cgv = MapUtils.wmsMapProvider2ClientGeoView(urlFinder.geoserverUrl, dbfl);
			
			cgv.setLayerName(layerJoinView);
			cgv.setTitle(layerJoinView);
			cgv.setStyleName(layerJoinView);
	
		}
		catch (Exception e) {
			LOGGER.error("the layer is not joinable");
		}
//		if ( joinedLayer != null)
//			joinedLayer.setLayerName(layerJoinView.toLowerCase());
//		else 
//			return null;

		
		
		LOGGER.info("joinLayerColumnName: " + joinLayerColumnName);
		LOGGER.info("joinedLayer: " + joinedLayer);
		LOGGER.info("layerJoinView: " + layerJoinView);
		LOGGER.info("sldStyleColumnName: " + sldStyleColumnName);

			
	
		// create layer
		projectedDataDao.createJoinDatasetView(layerJoinView, layerTableName, datasetViewName, joinDatasetColumnName, joinLayerColumnName);
		
		layerJoinView = layerJoinView.toLowerCase();
		
		
		
	
	
		joinedLayer.setLayerName(layerJoinView.toLowerCase());
		
		// publish layer
		GeoserverPublisher.publishShapeFile(joinedLayer);
		
		
		LOGGER.info("----------------------");
		LOGGER.info("joinLayerColumnName: " + joinLayerColumnName);
		LOGGER.info("layerJoinView: " + joinedLayer);
		LOGGER.info("sldStyleColumnName: " + layerJoinView);
		LOGGER.info("----------------------");
		
		// create sld
//		GeoserverPublisher.createClassifiedSLD(joinedLayer, layerJoinView, sldStyleColumnName);
		
		/** TODO: has to be tested...**/
		List<Double> values = projectedDataDao.getAllLayerValues(layerJoinView, "quantity");
		GeoserverPublisher.createClassifiedSLD(joinedLayer, layerJoinView, sldStyleColumnName, "quantile", values, 6);

		
		
		LOGGER.info("----");
		
		LOGGER.info(cgv.getStyleName());
		LOGGER.info(cgv.getTitle());
		LOGGER.info(cgv.getLayerName());
		LOGGER.info(cgv.getLayerName());
		
	
		
		
		return cgv;
	}
	
	private FeatureType copyFeatureType(DBFeatureLayer baseLayer) {
		FeatureType ft = new FeatureType();
		ft.setBbox(baseLayer.getBoundingBox());
		
		
		return ft;
	}
	
	private DataStore copyDataStore(DataStore datastore) {
		DataStore ds = new DataStore();
		
		ds.setDsType(datastore.getDsType());
		ds.setGeoServer(datastore.getGeoServer());
		ds.setId(datastore.getId());
		ds.setName(datastore.getName());
		ds.setNameSpace(datastore.getNameSpace());
	
		
		LOGGER.info("datastore.getDsType(): " + datastore.getDsType());
		LOGGER.info("datastore.getName(): " + datastore.getName());
		LOGGER.info("datastore.getNameSpace(): " + datastore.getNameSpace());
		return ds;
	}
	

	
	public List<String> createStaticMap(StaticProjectionVO vo) {
		// initiate result
		List<String> paths = new ArrayList<String>();
		
//		// layer values provider
		LayerValuesProviderImpl provider = new LayerValuesProviderImpl(vo.getGaulLevel());
		

//		 GAUL level
		int gaulLevel = 0;
		if (provider.getLayerCode().charAt(provider.getLayerCode().length() - 1) == '1')
			gaulLevel = 1;
		else if (provider.getLayerCode().charAt(provider.getLayerCode().length() - 1) == '2')
			gaulLevel = 2;
//		
		LOGGER.info("GAUL LEVEL: " + gaulLevel);
		
		// create GeoPair
		for(String projection : vo.getProjections().keySet()) {
			GeoPairImpl geoPair = new GeoPairImpl(projection, vo.getProjections().get(projection));
			provider.addPair(geoPair);
		}
		
		GeometryResolver geometryResolver = new DBFLGeometryResolver(provider);
		((DBFLGeometryResolver)geometryResolver).setLayerGaulUtils(layerGaulUtils);
		
		FeatureBuilder featureBuilder = new QuickFeatureBuilder(geometryResolver);

		QuickFeatureCollection fc = new QuickFeatureCollection(provider, featureBuilder);
		QuickMap quickMap = new QuickMap();
		quickMap.setWidth(vo.getWidth());
		quickMap.setHeight(vo.getHeight());
		quickMap.setIntervals(vo.getIntervals());
		
		quickMap.setMaxColor(OLAPUtils.getUserColor(vo.getMinColor())); 
		quickMap.setMinColor(OLAPUtils.getUserColor(vo.getMaxColor())); 
		
		BufferedImage quickimage = quickMap.render(fc);
		
		List<Range> ranges = quickMap.getLastRanges();
		
		CQLFilter gaul1Filters = getFilter(provider, 1);
		
		try {
			GeoView geoViewG0;
			
			BoundingBox bbox = quickMap.getLastBbox();

			// building the lower layers
			List<GeoView> lowerLayers = new ArrayList<GeoView>();	
			for(String layer_code : vo.getLowerLayers()) {
				geoViewG0 = new GeoView(wmsMapProviderDao.findByCode(layer_code));
				lowerLayers.add(geoViewG0);
			}
//											
//			if (showBaseLayer) {
//				geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_baseraster"));
//				lowerLayers.add(geoViewG0);
//			}
//			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
//			lowerLayers.add(geoViewG0);

			// building the upper layers
			List<GeoView> upperLayers = new ArrayList<GeoView>();	
			for(String layer_code : vo.getUpperLayers()) {
				geoViewG0 = new GeoView(wmsMapProviderDao.findByCode(layer_code));
				upperLayers.add(geoViewG0);
			}
			
			LOGGER.info("LOWER LAYERS: " + lowerLayers);
			LOGGER.info("UPPERLAYER LAYERS: " + upperLayers);

			// the style can be created once for all
			if( vo.getShowLabels() ) {
				String gaulCode = "layer_gaul" + gaulLevel;
				WMSMapProvider gaul =  wmsMapProviderDao.findByCode(gaulCode);
				String admName = GaulUtils.getMainLabelFieldname(gaulLevel);
				String labels = createLabelStyle(gaul, admName, "000000");
			}
			
//			Long layerId = olapDao.findLayerIdFromLayerCode(provider.getLayerCode());
//			String admName = GaulUtils.getMainLabelFieldname(gaulLevel);
//			String labels = createLabelStyle(layerId, admName, "000000");
//
//			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
//			geoViewG0.setStyleName(labels);		
//			geoViewG0.setCqlFilter(gaul1Filters);
//			upperLayers.add(geoViewG0);			
//			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul0"));
//			upperLayers.add(geoViewG0);			

			BufferedImage lowerImage = getLayerImage(bbox, lowerLayers, vo.getHeight(), vo.getWidth());
			BufferedImage upperImage = getLayerImage(bbox, upperLayers, vo.getHeight(), vo.getWidth());
			
			// create additional layers
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			
			images.add(lowerImage);
			images.add(quickimage);
			images.add(upperImage);
			
			// add transparencies
			List<Float> transparencies = new ArrayList<Float>();
			for (int i = 0 ; i < images.size() ; i++)
				transparencies.add(new Float(1.0));
			
			// merge and save
			BufferedImage merged = ImageMerger.merge(images, transparencies);
			String mergedFileName = String.valueOf(Math.random()) + "_MERGED.png";
			File mergedFile = new File(Setting.getSystemPath() + File.separator + "exportObject" + File.separator + mergedFileName);
			ImageIO.write(merged, "png", mergedFile);

			StringBuffer table = createImageTableWithSideLegend(vo, mergedFileName, ranges);
			String mapPath = createImageWithLegend(table);
			paths.add(mapPath);
			
		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FenixGWTException e) {
			LOGGER.error(e);
		}
		
		// return result
		return paths;
	}


	private StringBuffer createImageTableWithSideLegend(StaticProjectionVO vo, String mapPath, List<Range> ranges) {
		StringBuffer sb = new StringBuffer();
//		DecimalFormat twoDigit = new DecimalFormat("#,##0.00");
		DecimalFormat twoDigit = new DecimalFormat("#");
		sb.append("<table width='100%'>");
		
//		sb.append("<tr><td colspan = '2' align='center'><font size='6'>" + mapTitle + "</font></td>");
		
		Range firstRange = ranges.get(0);
		String firstRGB = Integer.toHexString(firstRange.color.getRGB());
		firstRGB = firstRGB.substring(2, firstRGB.length());
		sb.append("<tr><td rowspan = '" + ranges.size() + "'><img src='" + mapPath + "'/></td>");
		sb.append("<td><b>Legend</b></td></tr>");
		
		if (ranges.size() > 1) {
			for (int i = 1 ; i < ranges.size() ; i++) {
				Range r = ranges.get(i);
				String rgb = Integer.toHexString(r.color.getRGB());
				rgb = rgb.substring(2, rgb.length());
				sb.append("<tr>");
				sb.append("<td width='20%' bgcolor='#" + rgb + "'><font size='3'>" + twoDigit.format(r.min) + " to " + twoDigit.format(r.max) + "</font></td>");
//				sb.append("<td width='20%' bgcolor='#" + rgb + "'><font size='4'> <center>" + twoDigit.format(r.max) + " </center> </font></td>");
				sb.append("</tr>");
			}
		}
		
		sb.append("</table>");
//		sb.append(createFiltersLegend(p, filters).toString());
		return sb;
	}
	
	
	private String createImageWithLegend(StringBuffer table) {
		String path = String.valueOf(Math.random()) + "_MAP.html";
		File file = new File(Setting.getSystemPath() + File.separator + "exportObject" + File.separator + path);

		try {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(table.toString().getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
		return path;
	}
	
	
	private CQLFilter getFilter(LayerValuesProvider provider, int gaulLevel) {
		List<CQLFilter> filters = new ArrayList<CQLFilter>();
		
		for (GeoPair geoPair : provider) {
			filters.add(getFilter(geoPair.getFID(), gaulLevel));
		}

		return CQLFilterOp.or(filters);
	}
	
	public CQLFilter getFilter(String regionCode, int gaulLevel) {
		if(gaulLevel==0)
			return GaulCQLFilterFactory.createGaul0Is(regionCode);
		else if(gaulLevel==1)
			return GaulCQLFilterFactory.createGaul1Is(regionCode);
		else 
			throw new IllegalArgumentException("unknown GAUL level");
	}
	
	private BufferedImage getLayerImage(BoundingBox bbox, List<GeoView> gvlist, int height, int width) {
		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
		mm.setHeight(height);
		mm.setWidth(width);
		mm.setBoundingBox(bbox);

		for (GeoView geoView : gvlist) {
			System.out.println("GEOVIEWWWW");
			System.out.println(geoView.getWmsMapProvider().getGetMapUrl());
			System.out.println(geoView.getWmsMapProvider().getLayerName());
			System.out.println(geoView.getStyleName());
			System.out.println(geoView.getCqlFilter());
			System.out.println(geoView.getOpacity());
			geoView.setOpacity(new Float(1));
			mm.addLayer(geoView);			
		}

		// create the image
		BufferedImage image = mm.getMapImage();
		return image;
	}
	
	public String createLabelStyle(WMSMapProvider mp, String fieldName, String scolor) throws FenixGWTException {

		Color color = ColourUtil.hex2colour(scolor);
		String sld = TextSymbUtil.createLabelStyle(fieldName, color);
		String stylename = "label_" + mp.getResourceId() + "_"+fieldName.toLowerCase() +"_" + ColourUtil.colour2string(color);

		String layername = mp.getLayerName().replace(":", "_");
		GeoServer geoServer = ((InternalWMSLayer)mp).getGeoServer();

		GeoserverIO gsio = new GeoserverIO();
		if( ! gsio.existsStyle(geoServer, stylename)) {
//			LOGGER.info("Creating style " + stylename);			
			GeoserverPublisher.publishLayerStyle(geoServer, stylename, sld, stylename);
		}
		
		return stylename;
	}
	

	
	
	public void depublishLayer(String layerName) {
		List<GeoServer> geoservers = geoServerDao.findAllGeoServers();
		
		if ( !geoservers.isEmpty()) {
			LOGGER.info("depublish layer: " + layerName);
			GeoserverPublisher.deleteShapefile(geoservers.get(0), layerName);
		}
	}
	

	public void depublishLayer(Long layerId) {
		List<GeoServer> geoservers = geoServerDao.findAllGeoServers();
		
		// todo: remove
		LOGGER.info("depublish layerId: " + layerId);
		
		WMSMapProvider layer = mapDao.getLayer(layerId);
		if ( !geoservers.isEmpty()) {
			LOGGER.info("depublish layer: " + layer.getLayerName());
			GeoserverPublisher.deleteShapefile(geoservers.get(0), layer.getLayerName());
		}
	}

	
	public void depublishStyle(String styleName) {
		List<GeoServer> geoservers = geoServerDao.findAllGeoServers();
		if ( !geoservers.isEmpty())
			GeoserverPublisher.deleteStylefile(geoservers.get(0), styleName);
	}

	//--------------------------------------------------------------------------

	public void runWMSHarvester()
	{
		throw new UnsupportedOperationException("Not supported yet."); // TODO FIXME
	}

	public void runLayerImporter() {
		layerImporter.setThreadedStartDelay(0);
		layerImporter.runThreaded();
	}

	public void runGSSynch() {
		gsSynch.setThreadedStartDelay(0);
		gsSynch.runThreaded();
	}

	public boolean runGeotiffHarvester() {
		geotiffHarvester.setThreadedStartDelay(0);
		return geotiffHarvester.run();
	}

	//--------------------------------------------------------------------------

	public void setLayerImporter(LayerImporterWrapper layerImporter) {
		this.layerImporter = layerImporter;
	}

	public void setGsSynch(GSLayerSynchronizerWrapper gsSynch) {
		this.gsSynch = gsSynch;
	}

	public void setGeotiffHarvester(GeoTiffHarvesterWrapper geotiffHarvester) {
		this.geotiffHarvester = geotiffHarvester;
	}

	//--------------------------------------------------------------------------
	
	public void setHsDao(HSDao hsDao) {
		this.hsDao = hsDao;
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

	public void setGeoServerDao(GeoServerDao geoServerDao) {
		this.geoServerDao = geoServerDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setProjectedDataMetadao(ProjectedDataMetadao projectedDataMetadao) {
		this.projectedDataMetadao = projectedDataMetadao;
	}

	public void setProjectedDataDao(ProjectedDataDao projectedDataDao) {
		this.projectedDataDao = projectedDataDao;
	}

	public void setExportPath(Resource exportPath) {
		this.exportPath = exportPath;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setTableInfo(DBTableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}


	


	public void setFileFactory(FileFactory fileFactory) {
		this.fileFactory = fileFactory;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	
	
	
	public ClientGeoView createQuickJoinView(JoinDatasetVO joinDatasetVO) throws FenixGWTException {
		ClientGeoView cgv = new ClientGeoView();
		
		LOGGER.info("CREATE MAP TABLE");
		
//		String randomHex = randomHex();
		
//		String tablename = "quick_map_table_" + randomHex;
//		String stylename = "quick_map_style_" + randomHex;
		
		String tablename =  joinDatasetVO.getDatasetViewName();
//		String joinDatasetVO.getLayerJoinView();
		//		String stylename =  joinDatasetVO.getjoi;
		
		LOGGER.info("TABLE NAME:" + tablename);
		
		mapDao.createMapTable(tablename, joinDatasetVO.getJoinedValues());
		
		LOGGER.info("TABLE CREATED");
		
		GeoView layer = new GeoView(wmsMapProviderDao.findByCode(joinDatasetVO.getLayer()));
		
		String layerTableName = null;
		String layerJoinView = joinDatasetVO.getLayerJoinView();
		/*** TODO: setting lower case in the bean **/
		String sldStyleColumnName = joinDatasetVO.getSldStyleColumnName().toLowerCase();
		String joinDatasetColumnName = joinDatasetVO.getJoinDatasetColumnName();
		String joinLayerColumnName = joinDatasetVO.getJoinLayerColumnName();
		
		
		
		final WMSMapProvider wmp = layer.getWmsMapProvider();

		FeatureType joinedLayer = null;
		
		try {
			DBFeatureLayer dbfl =  (DBFeatureLayer) wmp;
			if ( joinDatasetVO.getJoinLayerColumnName() == null ) {
				LOGGER.info("dbfl.getJoinColumn(): " + dbfl.getJoinColumn());
				joinLayerColumnName = dbfl.getJoinColumn();
				joinLayerColumnName = "\"" + joinLayerColumnName + "\""; // adding quotes since the name may have uppercase letters
			}
			layerTableName = dbfl.getTablename();
			joinedLayer = dbfl.getFeatureType();
			
			cgv = MapUtils.wmsMapProvider2ClientGeoView(urlFinder.geoserverUrl, dbfl);
			
			cgv.setLayerName(layerJoinView);
			cgv.setTitle(layerJoinView);
			cgv.setStyleName(layerJoinView);
	
		}
		catch (Exception e) {
			LOGGER.error("the layer is not joinable");
		}
//		if ( joinedLayer != null)
//			joinedLayer.setLayerName(layerJoinView.toLowerCase());
//		else 
//			return null;

		
		
		LOGGER.info("joinLayerColumnName: " + joinLayerColumnName);
		LOGGER.info("joinedLayer: " + joinedLayer);
		LOGGER.info("layerJoinView: " + layerJoinView);
		LOGGER.info("sldStyleColumnName: " + sldStyleColumnName);

			
	
		// create layer
		projectedDataDao.createJoinDatasetView(layerJoinView, layerTableName, tablename, joinDatasetColumnName, joinLayerColumnName);
		
		
		
		layerJoinView = layerJoinView.toLowerCase();
		
		
		
		
		
		joinedLayer.setLayerName(layerJoinView.toLowerCase());
		
		// publish layer
		GeoserverPublisher.publishShapeFile(joinedLayer);
		
		
		LOGGER.info("----------------------");
		LOGGER.info("joinedLayer: " + joinLayerColumnName);
		LOGGER.info("layerJoinView: " + joinedLayer);
		LOGGER.info("sldStyleColumnName: " + layerJoinView);
		LOGGER.info("----------------------");
		
		// create sld
//		GeoserverPublisher.createClassifiedSLD(joinedLayer, layerJoinView, sldStyleColumnName);
		
		/** TODO: has to be tested...**/
		List<Double> values = projectedDataDao.getAllLayerValues(layerJoinView, "quantity");
		GeoserverPublisher.createClassifiedSLD(joinedLayer, layerJoinView, sldStyleColumnName, "quantile", values, 6);

		
		
		LOGGER.info("----");
		
		LOGGER.info(cgv.getStyleName());
		LOGGER.info(cgv.getTitle());
		LOGGER.info(cgv.getLayerName());
		LOGGER.info("layer ID: " +cgv.getLayerId());
		LOGGER.info("----");
		
		cgv.setLayerId(0);
		cgv.setIsQuickPrj(true);
		
		LOGGER.info("layer ID setted: " +cgv.getLayerId());
		LOGGER.info("----"); 
		
	
		
		
		return cgv;
	}

	
	public List<List<String>> parseFeatureInfoHTML(String html, String type) {
		List<List<String>> transpose = new ArrayList<List<String>>();
		LOGGER.info(html);
		
		
		List<List<String>> tableList = getHTMLTable(html);
		
		transpose = transpose(tableList);
		
//		LOGGER.info("transpose");
		
//		LOGGER.info(transpose);
		
		
		return transpose;
	}
	
	public List<List<String>> getHTMLTable(String html) {
//		LOGGER.info(html);
		List<List<String>> tableList = new ArrayList<List<String>>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		
		
		InputStream stream = new ByteArrayInputStream(html.getBytes("UTF-8"));
		org.w3c.dom.Document document = db.parse(stream);
		org.w3c.dom.Element element = document.getDocumentElement();
		

		
		List<String> headers = new ArrayList<String>();
		// header
		org.w3c.dom.NodeList headerName = element.getElementsByTagName("tr");
		for (int i = 0; i < headerName.getLength(); i++) {
			org.w3c.dom.Element headerNameElement = (org.w3c.dom.Element) headerName.item(i);
			org.w3c.dom.NodeList headerListNode = headerNameElement.getElementsByTagName("th");
			for(int j = 0; j < headerListNode.getLength(); j++) {
				org.w3c.dom.Element thElement = (org.w3c.dom.Element) headerListNode.item(j);
				String value = thElement.getTextContent();
				headers.add(value);
			}
			
		}
		tableList.add(headers);
		
		
		org.w3c.dom.NodeList contentNode = element.getElementsByTagName("tr");
		for (int i = 0; i < contentNode.getLength(); i++) {
			List<String> content = new ArrayList<String>();
			org.w3c.dom.Element contentNodeElement = (org.w3c.dom.Element) contentNode.item(i);
			org.w3c.dom.NodeList contentNodeListNode = contentNodeElement.getElementsByTagName("td");
			for(int j = 0; j < contentNodeListNode.getLength(); j++) {
				org.w3c.dom.Element tdElement = (org.w3c.dom.Element) contentNodeListNode.item(j);
				String value = tdElement.getTextContent();
				content.add(value);
			}
		
			if ( !content.isEmpty() ) {
//				System.out.println("CONTENT: " + content);
				tableList.add(content);
			}
			
		}
		
		
		
//		LOGGER.info(tableList);
		
		
		// content
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tableList;
		
	}
	
	public static List<List<String>> transpose(List<List<String>> list) {
		String[][] grid = new String[list.get(0).size()][list.size()];
		List<List<String>> result = new ArrayList<List<String>>(grid.length);
		for (int i = 0 ; i < list.size() ; i++) 
			for (int j = 0 ; j < list.get(i).size() ; j++) 
				grid[j][i] = list.get(i).get(j);
		for (int i = 0 ; i < grid.length ; i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0 ; j < grid[i].length ; j++) 
				row.add(grid[i][j]);
			result.add(row);
		}
		return result;
	}





	




	



	
}
