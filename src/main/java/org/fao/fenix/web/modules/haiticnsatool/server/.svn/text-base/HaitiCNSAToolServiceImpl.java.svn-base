package org.fao.fenix.web.modules.haiticnsatool.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.cnsa.CNSADao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.haiticnsatool.common.services.HaitiCNSAToolService;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class HaitiCNSAToolServiceImpl extends RemoteServiceServlet implements HaitiCNSAToolService {

	private CodecDao codecDao;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private UrlFinder urlFinder;
	
	private String dir;
	
	private HaitiChartUtils haitiChartUtils;
	
	private CNSADao cnsaDao;
	
	private static final Logger LOGGER = Logger.getLogger(HaitiCNSAToolServiceImpl.class);
	
	public HaitiCNSAToolServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	

	@SuppressWarnings("static-access")
	private ClientGeoView wmsMapProvider2ClientGeoView(WMSMapProvider f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBoundingBox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getResourceId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(urlFinder.geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		c.setAbstractAbstract(f.getAbstractAbstract());
		c.setSource(f.getSource().getTitle());
		c.setSourceContact(f.getSource().getContact());
		c.setProvider(f.getProvider().getTitle());
		c.setProviderContact(f.getProvider().getContact());
		c.setFenixCode(f.getCode());
		return c;
	}
	
	@SuppressWarnings("unused")
	private ClientGeoView featureType2ClientGeoView(FeatureType f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBbox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(urlFinder.geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		return c;
	}
	
	private ClientBBox bbox2ClientBBox(BoundingBox b) {
		ClientBBox c = new ClientBBox();
		c.setMaxLat(b.getMaxY());
		c.setMaxLon(b.getMaxX());
		c.setMinLat(b.getMinY());
		c.setMinLon(b.getMinX());
		c.setSrs(b.getSrs());
		return c;
	}


	/*** THIS METHODS ARE USED FOR THE CNSA TOOL **/
	
	public HashMap<String, String> retrieveCNSADates(String countryCode) {
		return cnsaDao.getReportDates("EMODIS_108");
	}


	public List<ClientGeoView> retrieveCNSALayers(String countryCode, String date) {
		
		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		Date startDate = FieldParser.parseDate(date);
		LOGGER.info("getting layers");
		
		
		LOGGER.info("startDate" + startDate);	
		LOGGER.info("countryCode" + countryCode);
		
		// base layer
		WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByCode("CARIBBEANS");
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);
			clientGeoViews.add(c);
		}	

		
		// ndvi-va
		dbLayer = wmsMapProviderDao.getLayer("ndvi_va", startDate, countryCode);
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);	
			clientGeoViews.add(c);
		}
		
		// ndvi
		dbLayer = wmsMapProviderDao.getLayer("ndvi", startDate, countryCode);
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);
			c.setHidden(true);
			clientGeoViews.add(c);
		}
		
		// agric_land
		dbLayer = wmsMapProviderDao.getLayerByCode("agric_land");
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);	
			c.setHidden(true);
			clientGeoViews.add(c);
		}
	
		// gaul1
		dbLayer = wmsMapProviderDao.getLayerByCode("HAITI_GAUL1");
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);	
			clientGeoViews.add(c);
		}
		
		// cities a livello1
		dbLayer = wmsMapProviderDao.getLayerByCode("HAITI_GAUL1_CITIES");
		if ( dbLayer != null) {
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);	
			clientGeoViews.add(c);
		}
		
		
		
		LOGGER.info("size" + clientGeoViews.size());
		
		return clientGeoViews;
	}
	
	

	public String retrieveCNSAChart(String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language) {
		Date startDate = FieldParser.parseDate(date);
		LOGGER.info("getting chart date: " + date);
		LOGGER.info("getting chart date: " + startDate);
		String w = width.replace("px", "");
		String h = height.replace("px", "");
			
		return haitiChartUtils.getProvinceDateChart(chartTitle, yAxesTitle, startDate, language, w, h);
	}
	
	
	
	public String exportReport(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport, String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language ) {

		// map
		MapViewUtils mapViewUtils = new MapViewUtils();
		mapViewUtils.setWmsMapProviderDao(wmsMapProviderDao);
		MapView mapView = mapViewUtils.build(cmv);

		List<GeoView> gvList = new ArrayList<GeoView>();
		for (ClientGeoView clientGeoView : cmv.getLayerList()) {
			gvList.add(mapViewUtils.buildGeoView(clientGeoView, true));
		}

		// chart 
		Date startDate = FieldParser.parseDate(date);
		String w = width.replace("px", "");
		String h = height.replace("px", "");
		ChartWizardBean chartBean = haitiChartUtils.getChartchartWizardBean(chartTitle, yAxesTitle, startDate, language, w, h);
		
		
		CreateHaitiCNSAReport report = new CreateHaitiCNSAReport(chartBean, mapView, gvList, w, h);
		return report.createReport(mapView, gvList, w, h);

	}
	
	

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setHaitiChartUtils(HaitiChartUtils haitiChartUtils) {
		this.haitiChartUtils = haitiChartUtils;
	}

	public void setCnsaDao(CNSADao cnsaDao) {
		this.cnsaDao = cnsaDao;
	}

	


	

	
	
	
}