package org.fao.fenix.web.modules.rainfall.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.CQLFilter;
import org.fao.fenix.core.domain.map.cql.CQLFilterOp;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.rainfall.RainfallBean;
import org.fao.fenix.core.domain.rainfall.RainfallConfigurationBean;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.rainfall.RainfallDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallService;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallConfigurationVo;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;
import org.fao.fenix.web.modules.rainfall.server.birt.CreateReport;
import org.fao.fenix.web.modules.rainfall.server.utils.AfricanGaulCode;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RainfallServiceImpl extends RemoteServiceServlet implements RainfallService {

	private CodecDao codecDao;

	private RainfallDao rainfallDao;
	
	private RainfallUtils rainfallUtils;

	private WMSMapProviderDao wmsMapProviderDao;

	private LayerGaulUtils layerGaulUtils;

	private static final Logger LOGGER = Logger.getLogger(RainfallServiceImpl.class);
	
	private static Map<Integer, String> monthLabelMap;

	private static Map<String, List<String>> imagesMap;
	
	private static Map<String, List<String>> ndviMap;

	private static Map<String, List<String>> countriesMap;
	
	private static Map<String, List<String>> provincesMap;
	
	private static Map<String, List<String>> countriesNdviMap;
	
	private static Map<String, List<String>> provincesNdviMap;
	
	static {
		imagesMap = new HashMap<String, List<String>>();
		ndviMap = new HashMap<String, List<String>>();
		countriesMap = new HashMap<String, List<String>>();
		provincesMap = new HashMap<String, List<String>>();
		countriesNdviMap = new HashMap<String, List<String>>();
		provincesNdviMap = new HashMap<String, List<String>>();
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "Jan");
		monthLabelMap.put(1, "Feb");
		monthLabelMap.put(2, "Mar");
		monthLabelMap.put(3, "Apr");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "Jun");
		monthLabelMap.put(6, "Jul");
		monthLabelMap.put(7, "Aug");
		monthLabelMap.put(8, "Sep");
		monthLabelMap.put(9, "Oct");
		monthLabelMap.put(10, "Nov");
		monthLabelMap.put(11, "Dec");
	}

	public void deleteFavourite(String filename) {
		rainfallDao.deleteFavourite(filename);
	}

	public void saveFavourite(RainfallConfigurationVo vo, String filename) {
		RainfallConfigurationBean b = vo2bean(vo);
		rainfallDao.saveFile(filename, b);
	}

	public RainfallConfigurationVo getRainfallConfiguration(String filename) {
		RainfallConfigurationBean bean = rainfallDao.getRainfallConfiguration(filename);
		return bean2vo(bean);
	}

	private RainfallConfigurationBean vo2bean(RainfallConfigurationVo vo) {
		RainfallConfigurationBean b = new RainfallConfigurationBean();
		b.setAverage(vo.getAverage());
		b.setChart(vo.getChart());
		b.setCompareYear(vo.getCompareYear());
		b.setCumulate(vo.getCumulate());
		b.setFromDecade(vo.getFromDecade());
		b.setFromYear(vo.getFromYear());
		b.setMap(vo.getMap());
		b.setTable(vo.getTable());
		b.setTemplate(vo.getTemplate());
		b.setToDecade(vo.getToDecade());
		b.setToYear(vo.getToYear());
		List<Code> regions = new ArrayList<Code>();
		for (CodeVo cvo : vo.getRegions()) {
			Code c = new Code();
			c.setCode(cvo.getCode());
			c.setDescription(cvo.getDescription());
			c.setLabel(cvo.getLabel());
			regions.add(c);
		}
		b.setRegions(regions);
		return b;
	}

	private RainfallConfigurationVo bean2vo(RainfallConfigurationBean b) {
		RainfallConfigurationVo vo = new RainfallConfigurationVo();
		List<CodeVo> regions = new ArrayList<CodeVo>();
		for (Code code : b.getRegions()) {
			CodeVo cv = new CodeVo();
			cv.setCode(code.getCode());
			cv.setDescription(code.getDescription());
			cv.setLabel(code.getLabel());
			regions.add(cv);
		}
		vo.setRegions(regions);
		vo.setAverage(b.getAverage());
		vo.setChart(b.getChart());
		vo.setCompareYear(b.getCompareYear());
		vo.setCumulate(b.getCumulate());
		vo.setFromDecade(b.getFromDecade());
		vo.setFromYear(b.getFromYear());
		vo.setMap(b.getMap());
		vo.setTable(b.getTable());
		vo.setTemplate(b.getTemplate());
		vo.setToDecade(b.getToDecade());
		vo.setToYear(b.getToYear());
		return vo;
	}

	public List<String> findAllRainfallConfiguration() {
		return rainfallDao.findAllRainfallConfiguration();
	}

	public String createRainfallReport(RainfallParameters parameters) throws FenixGWTException {
		List<RainfallBean> beans = createRainfallBeans(parameters);
		CreateReport rainfallReport = new CreateReport(beans);
		return rainfallReport.createReport();
	}

	// TODO
	private String getGaulLevel(String gaulCode) {
		String gaulLevel = codecDao.getGaulLevel(gaulCode);
		LOGGER.info("GAUL Level: " + gaulLevel + " for " + gaulCode);
		return gaulLevel;
	}

	private List<RainfallBean> createRainfallBeans(RainfallParameters p) throws FenixGWTException {

		List<RainfallBean> beans = new ArrayList<RainfallBean>();
		imagesMap = new HashMap<String, List<String>>();
		ndviMap = new HashMap<String, List<String>>();
		countriesMap = new HashMap<String, List<String>>();
		countriesNdviMap = new HashMap<String, List<String>>();

		LOGGER.info("Adding maps? " + p.hasMap());
		
		// create GAUL beans
		if (p.getRegion().size() > 0) {
			for (String regionCode : p.getRegion()) {
				String gaulLevel = getGaulLevel(regionCode);
				LOGGER.info("GAUL LEVEL: " + gaulLevel);
				beans.add(createRainfallBean(p, regionCode, "GAUL", gaulLevel));
			}
		}

		// create GTS beans
		if (p.getStations().size() > 0) {
			for (String regionCode : p.getStations()) {
//				String gaulLevel = getGaulLevel(regionCode);
//				LOGGER.info("GAUL Level for '" + regionCode + "' (GTS): " + gaulLevel); 
				beans.add(createRainfallBean(p, regionCode, "GTS", "1"));
			}
		}

		// retrieve maps
		if (p.hasMap()) {
//			LOGGER.info("Initiating countries map...");
			initiateCountriesMap(beans);
//			LOGGER.info(countriesMap.keySet().size() + " items in the countries map");
			if (!countriesMap.isEmpty()) {
				for (String gaul0code : countriesMap.keySet()) 
					getMapImages(p, gaul0code, countriesMap.get(gaul0code), provincesMap.get(gaul0code));
			} else {
				for (String gaul0code : provincesMap.keySet())
					getMapImages(p, gaul0code, countriesMap.get(gaul0code), provincesMap.get(gaul0code));
			}
		}
		
		// retrieve NDVI
		if (p.isHasNdvi()) {
			initiateCountriesNdviMap(beans);
			if (!countriesNdviMap.isEmpty()) {
				for (String gaul0code : countriesNdviMap.keySet()) 
					getNdviImages(p, gaul0code, countriesNdviMap.get(gaul0code), provincesNdviMap.get(gaul0code));
			} else {
				for (String gaul0code : provincesNdviMap.keySet())
					getNdviImages(p, gaul0code, countriesNdviMap.get(gaul0code), provincesNdviMap.get(gaul0code));
			}
		}

		// set images
		for (RainfallBean b : beans) {
			
			// rainfall images
			if (b.isHasMap()) {
				b.setImages(imagesMap.get(b.getGaul0code()));
			} else {
				b.setImages(new ArrayList<String>());
			}
			
			// NDVI images
			if (b.isHasNdvi()) {
				b.setNdviImages(ndviMap.get(b.getGaul0code()));
			} else {
				b.setNdviImages(new ArrayList<String>());
			}
		}

		return beans;
	}

	private void initiateCountriesMap(List<RainfallBean> beans) {
		List<String> gaul0codes = new ArrayList<String>();
		for (RainfallBean b : beans)
			if (!gaul0codes.contains(b.getGaul0code()))
				gaul0codes.add(b.getGaul0code());
		for (String g0 : gaul0codes) {
			List<String> gaul1codes = new ArrayList<String>();
			List<String> gaul2codes = new ArrayList<String>();
			for (RainfallBean b : beans) {
				if ((b.getGaul0code() != null) && (b.getGaul1code() != null) && (b.getGaul0code().equals(g0)) && (!gaul1codes.contains(b.getGaul1code())))
					gaul1codes.add(b.getGaul1code());
				if ((b.getGaul0code() != null) && (b.getGaul2code() != null) && (b.getGaul0code().equals(g0)) && (!gaul2codes.contains(b.getGaul2code())))
					gaul2codes.add(b.getGaul2code());
			}
			if (!gaul1codes.isEmpty())
				countriesMap.put(g0, gaul1codes);
			if (!gaul2codes.isEmpty())
				provincesMap.put(g0, gaul2codes);
		}
	}
	
	private void initiateCountriesNdviMap(List<RainfallBean> beans) {
		List<String> gaul0codes = new ArrayList<String>();
		for (RainfallBean b : beans)
			if (!gaul0codes.contains(b.getGaul0code()))
				gaul0codes.add(b.getGaul0code());
		for (String g0 : gaul0codes) {
			List<String> gaul1codes = new ArrayList<String>();
			List<String> gaul2codes = new ArrayList<String>();
			for (RainfallBean b : beans) {
				if ((b.getGaul0code() != null) && (b.getGaul1code() != null) && (b.getGaul0code().equals(g0)) && (!gaul1codes.contains(b.getGaul1code())))
					gaul1codes.add(b.getGaul1code());
				if ((b.getGaul0code() != null) && (b.getGaul2code() != null) && (b.getGaul0code().equals(g0)) && (!gaul2codes.contains(b.getGaul2code())))
					gaul2codes.add(b.getGaul2code());
			}
			if (!gaul1codes.isEmpty())
				countriesNdviMap.put(g0, gaul1codes);
			if (!gaul2codes.isEmpty())
				provincesNdviMap.put(g0, gaul2codes);
		}
	}

	private String getGaul0CodeFromGaul1(String gaul1code) {
//		LOGGER.info("Get GAUL1 from " + gaul1code);
		String gaulCode = codecDao.findGaul0CodeFromGaul1Code(gaul1code);
//		Logger.getRootLogger().warn(gaulCode + " found as GAUL0 code for " + gaul1code);
		return gaulCode;
	}
	
	/**
	 * 
	 * @param p
	 * @param regionCode
	 *            Usually is the GAUL1, otherwise generic GTS code
	 * @param codingType
	 * @return
	 * @throws FenixGWTException
	 */
	@SuppressWarnings("deprecation")
	private RainfallBean createRainfallBean(RainfallParameters p, String regionCode, String codingType, String gaulLevel) throws FenixGWTException {

		RainfallBean b = new RainfallBean();
		List<RainfallBean> beans = new ArrayList<RainfallBean>();
		List<IndexCoreContent> averageRainfall = null;
		List<QuantitativeCoreContent> firstRainfall = null;
		List<QuantitativeCoreContent> secondRainfall = null;
		
		b.setTitle(createTitle(regionCode, codingType));
		b.setXLabel(p.getXLabel());
		b.setYLabel(p.getYLabel());
		b.setHasChart(p.hasChart());
		b.setHasMap(p.hasMap());
		b.setHasTable(p.hasTable());
		b.setHasNdvi(p.isHasNdvi());
		b.setHasGeneralClimate(p.isHasGeneralClimate());
		b.setDates(rainfallUtils.createDekadeDates(p.getYearOneDates()));
		b.setYearOne(p.getYearOne());
		b.setYearTwo(p.getYearTwo());
		
		String gaul0code = "";
		if (!codingType.equals("GTS")) {
			gaul0code = getGaul0CodeFromGaul1(regionCode);
		} else {
			String gaul1code = codecDao.findGaul1CodeFromGtsCode(regionCode);
			gaul0code = getGaul0CodeFromGaul1(gaul1code);
		}
		b.setGaul0code(gaul0code);
		
		b.setGaul0label(codecDao.getLabelFromCodeCodingSystem(b.getGaul0code(), "GAUL0", "0", "EN"));
		b.setGeneralClimate(rainfallDao.getGeneralClimate(b.getGaul0code()));
		
		if (gaulLevel.equalsIgnoreCase("1")) {
			b.setGaul1code(regionCode);
			b.setGaul1label(codecDao.getLabelFromCodeCodingSystem(b.getGaul1code(), "GAUL1", "0", "EN"));
		} else if (gaulLevel.equalsIgnoreCase("2")) {
			b.setGaul2code(regionCode);
			b.setGaul2label(codecDao.getLabelFromCodeCodingSystem(b.getGaul2code(), "GAUL2", "0", "EN"));
		}

		String featureCode = regionCode;
		
		// GTS have no average, set to false
		if (codingType.equals("GTS")) 
			p.setHasAverage(false);

		if (p.hasAverage()) {

			List<Date> dates = p.getYearOneDates();
			if ((p.getYearTwo() != null) && (p.getYearTwo() < p.getYearOne()))
				dates = p.getYearOneDates();

			if (!p.hasCumulate()) {
				if (codingType.equals("GAUL")) {
					List<IndexCoreContent> tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-NOAA", ("FAO-NOAA-GAUL" + gaulLevel));
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-NOAA", ("FAO-NOAA-GAUL2"));
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-JRC", ("FAO-JRC-GAUL" + gaulLevel));
					averageRainfall = tmpAvg;
				} else if (codingType.equals("GTS")) {
					String gaul1code = codecDao.findGaul1CodeFromGtsCode(featureCode);
					List<IndexCoreContent> tmpAvg = rainfallDao.findRainfallAverage(gaul1code, p.getYearOneDates(), "FAO-NOAA", "GTS");
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(gaul1code, p.getYearOneDates(), "FAO-NOAA", "GTS");
					averageRainfall = tmpAvg;
				}
			} else {
				List<IndexCoreContent> tmp = new ArrayList<IndexCoreContent>();
				if (codingType.equals("GAUL")) {
					List<IndexCoreContent> tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-NOAA", ("FAO-NOAA-GAUL" + gaulLevel));
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-NOAA", ("FAO-NOAA-GAUL2"));
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(featureCode, p.getYearOneDates(), "FAO-JRC", ("FAO-JRC-" + gaulLevel));
					averageRainfall = tmpAvg;
				} else if (codingType.equals("GTS")) {
					String gaul1code = codecDao.findGaul1CodeFromGtsCode(featureCode);
					List<IndexCoreContent> tmpAvg = rainfallDao.findRainfallAverage(gaul1code, p.getYearOneDates(), "FAO-NOAA", "GTS");
					if (tmpAvg.isEmpty())
						tmpAvg = rainfallDao.findRainfallAverage(gaul1code, p.getYearOneDates(), "FAO-NOAA", "GTS");
					averageRainfall = tmpAvg;
				}
				averageRainfall = cumulateAverage(tmp);
			}

			if (averageRainfall.size() == 0)
				throw new FenixGWTException("No rainfall average(s) found in this range for: " + b.getGaul1label());

			Date baseDateFrom = averageRainfall.get(averageRainfall.size() - 1).getBaseDateFrom();
			Date baseDateTo = averageRainfall.get(averageRainfall.size() - 1).getBaseDateTo();
			String rainfallAverage = "(" + String.valueOf(1900 + baseDateFrom.getYear()) + " - " + String.valueOf(1900 + baseDateTo.getYear()) + ")";
			b.setAverageRange(rainfallAverage);
		}

		List<QuantitativeCoreContent> tmpRain = new ArrayList<QuantitativeCoreContent>();
		if (!codingType.equals("GTS")) {
//			LOGGER.info(p.getYearOneDates().size() + " dates to QUERY.");
			tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), codingType, "FAO-NOAA", ("FAO-NOAA-GAUL" + gaulLevel));
			if (tmpRain.isEmpty())
				tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), codingType, "FAO-NOAA", ("FAO-NOAA-GAUL2"));
			if (tmpRain.isEmpty())
				tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), codingType, "FAO-JRC", ("FAO-JRC-GAUL" + gaulLevel));
		} else {
			tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), codingType, "FAO-NOAA", "GTS");
		}
		firstRainfall = tmpRain;
//		LOGGER.info(firstRainfall.size() + " 1st rainfall");

		if (firstRainfall.size() == 0)
			throw new FenixGWTException("No data found in this range and/or for region: " + b.getGaul1label());
		if (p.hasCumulate())
			firstRainfall = cumulateRaifall(firstRainfall);

		if (!p.getYearTwoDates().isEmpty()) {
			List<QuantitativeCoreContent> tmp2ndRain = rainfallDao.findRainfall(featureCode, p.getYearTwoDates(), codingType, "FAO-NOAA", ("FAO-NOAA-GAUL" + gaulLevel));
			if (tmp2ndRain.isEmpty())
				tmp2ndRain = rainfallDao.findRainfall(featureCode, p.getYearTwoDates(), codingType, "FAO-NOAA", ("FAO-NOAA-GAUL2"));
			if (tmp2ndRain.isEmpty())
				tmp2ndRain = rainfallDao.findRainfall(featureCode, p.getYearTwoDates(), codingType, "FAO-JRC", ("FAO-JRC-GAUL" + gaulLevel));
			secondRainfall = tmp2ndRain;
			if (secondRainfall.size() == 0)
				throw new FenixGWTException("No data found in this range and/or for region: " + b.getGaul1label());
			if (p.hasCumulate())
				secondRainfall = cumulateRaifall(secondRainfall);
		}

		rainfallUtils.normalizeArrays(p.getYearOneDates(), firstRainfall, secondRainfall, averageRainfall);

		List<String> firstIstogramValues = new ArrayList<String>();
		List<String> secondIstogramValues = new ArrayList<String>();
		List<String> lineValues = new ArrayList<String>();

		for (QuantitativeCoreContent q : firstRainfall)
			if (q.getQuantity() != null)
				firstIstogramValues.add(String.valueOf(q.getQuantity().intValue()));
			else
				firstIstogramValues.add("null");

		if (!p.getYearTwoDates().isEmpty())
			for (QuantitativeCoreContent q : secondRainfall)
				if (q.getQuantity() != null)
					secondIstogramValues.add(String.valueOf(q.getQuantity().intValue()));
				else
					secondIstogramValues.add("null");

		if (p.hasAverage())
			for (IndexCoreContent i : averageRainfall)
				if (i.getQuantity() != null)
					lineValues.add(String.valueOf(i.getQuantity().intValue()));
				else
					lineValues.add("null");

		b.setFirstIstogramValues(firstIstogramValues);
		b.setSecondIstogramValues(secondIstogramValues);
		b.setLineValues(lineValues);
		b.setYearOne(1900 + p.getYearOneDates().get(0).getYear());

		if (!p.getYearTwoDates().isEmpty())
			b.setYearTwo(1900 + p.getYearTwoDates().get(0).getYear());
		else
			b.setYearTwo(null);

		boolean isCrossYear = isCrossYear(p);
		String yearOneRange = String.valueOf(1900 + p.getYearOneDates().get(0).getYear());
		if (isCrossYear)
			yearOneRange += "-" + String.valueOf(1900 + p.getYearOneDates().get(p.getYearOneDates().size() - 1).getYear());
		b.setYearOneRange(yearOneRange);
		if (p.getYearTwoDates().size() > 0) {
			String yearTwoRange = String.valueOf(1900 + p.getYearTwoDates().get(0).getYear());
			if (isCrossYear)
				yearTwoRange += "-" + String.valueOf(1900 + p.getYearTwoDates().get(p.getYearTwoDates().size() - 1).getYear());
			b.setYearTwoRange(yearTwoRange);
		}
		
		if (p.hasTable())
			b.setTable(createRainfallTable(b));
		else
			b.setTable(new ArrayList<List<String>>());
		
		if (b.isHasGeneralClimate())
			b.setGeneralClimateImage(getGeneralClimateMap(b.getGaul0code()));

		// printRainfallBean(b);
		return b;
	}
	
	@SuppressWarnings("deprecation")
	private boolean isCrossYear(RainfallParameters p) {
		Date t = p.getYearOneDates().get(0);
		for (Date d : p.getYearOneDates())
			if (d.getYear() > t.getYear())
				return true;
		return false;
	}

	private void printRainfallBean(RainfallBean b) {
		LOGGER.info(b.getAverageRange());
		LOGGER.info(b.getGaul0code());
		LOGGER.info(b.getGaul0label());
		LOGGER.info(b.getGaul1code());
		LOGGER.info(b.getGaul1label());
		LOGGER.info(b.getTitle());
		LOGGER.info(b.getXLabel());
		LOGGER.info(b.getYLabel());
	}

	private String getGeneralClimateMap(String regionCode) throws FenixGWTException {

		String imageUrl = "";

		try {

			// create the map builder
			WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
			mm.setHeight(400);
			mm.setWidth(600);

			// add base layer
			WMSMapProvider baseLayer = wmsMapProviderDao.findByCode("layer_baseraster");
			mm.addLayer(baseLayer);

			WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
			GeoView gv0 = new GeoView(gaul0);
			gv0.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(regionCode));
			gv0.setStyleName("polygon_filled");
			mm.addLayer(gv0);

			WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
			GeoView gv1 = new GeoView(gaul1);
			gv1.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(regionCode));
			gv1.setStyleName("zzlayer_gaul1_black");
			mm.addLayer(gv1);

			WMSMapProvider gts = wmsMapProviderDao.findByCode("layer_world_station");
			GeoView gvs = new GeoView(gts);
			gvs.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(regionCode));
			mm.addLayer(gvs); // DONE restored

			// avoid GAUL2
			if (regionCode != null) {

				// get the bounding box
				BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(regionCode));
				mm.setBoundingBox(bbox);

				// compute the map image
				BufferedImage image = mm.getMapImage();
				File file = new File(System.getProperty("java.io.tmpdir") + File.separator + regionCode + ".png");
				ImageIO.write(image, "png", file);

				// add to the list
				imageUrl = System.getProperty("java.io.tmpdir") + File.separator + regionCode + ".png";
			}

		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}

		return imageUrl;
	}

	private List<String> getMapImages(RainfallParameters p, String gaul0code, List<String> gaul1codes, List<String> gaul2codes) throws FenixGWTException {

		if (imagesMap.keySet().contains(gaul0code)) {

			return imagesMap.get(gaul0code);

		} else {

			List<String> images = new ArrayList<String>();
			List<Date> dates = getLastDecade(p);
			GeoView geoViewG0 = null;
			GeoView geoViewG1 = null;
			GeoView geoViewG2 = null;

			try {
				
				// GAUL0
				WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
				geoViewG0 = new GeoView(gaul0);
				geoViewG0.setStyleName("polygon_filled");
				geoViewG0.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(gaul0code));
				
				// GAUL2
				if ((gaul2codes != null) && !gaul2codes.isEmpty()) {
					
					// add GAUL2 layer
					WMSMapProvider gaul2 = wmsMapProviderDao.findByCode("layer_gaul2");
					geoViewG2 = new GeoView(gaul2);
					geoViewG2.setStyleName("zzlayer_gaul2_black");

					// add filtered GAUL2 layers
					List<CQLFilter> filters = new ArrayList<CQLFilter>();
					CQLFilter f;
					for (String g2 : gaul2codes) {
						f = GaulCQLFilterFactory.createGaul2Is(g2);
						filters.add(f);
					}
					CQLFilter cqfilter = CQLFilterOp.or(filters);
					geoViewG2.setCqlFilter(cqfilter);
				}
				
				// GAUL1
				if ((gaul1codes != null) && !gaul1codes.isEmpty()) {
					
					// add GAUL1 layer
					WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
					geoViewG1 = new GeoView(gaul1);
					geoViewG1.setStyleName("zzlayer_gaul1_black");

					// add filtered GAUL1 layers
					List<CQLFilter> filters = new ArrayList<CQLFilter>();
					CQLFilter f;
					for (String g1 : gaul1codes) {
						f = GaulCQLFilterFactory.createGaul1Is(g1);
						filters.add(f);
					}
					CQLFilter cqfilter = CQLFilterOp.or(filters);
					geoViewG1.setCqlFilter(cqfilter);
				}

				for (Date date : dates) {

					// create layer's code
					String layerCode = "";
					if (AfricanGaulCode.africaGaulCodes.contains(gaul0code))
						layerCode = createAfricaLayerCode(date);
					else
						layerCode = createLayerCode(date);
//					LOGGER.info("Adding map: " + layerCode);

					// create the map builder
					WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
					mm.setHeight(200);
					mm.setWidth(300);

					// add RF layer
					LOGGER.info("layerCode" + layerCode);
					WMSMapProvider vgt = wmsMapProviderDao.findByCode(layerCode);
					LOGGER.info("vgt is null? " + (vgt == null));
					
					// avoid empty layers
					if (vgt != null) {
					
						// add rainfall layer
						mm.addLayer(vgt);
						
						// add GAUL0 layer
						mm.addLayer(geoViewG0);
						
						if ((gaul2codes != null) && !gaul2codes.isEmpty()) 
							mm.addLayer(geoViewG2);
	
						if ((gaul1codes != null) && !gaul1codes.isEmpty()) 
							mm.addLayer(geoViewG1);
	
						// create files
						if (gaul0code != null) {
	
							// get the bounding box
							BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(gaul0code));
							mm.setBoundingBox(bbox);
							
							// random number to separat4e images
							double rand = Math.random() * 1000000;
	
							// compute the map image
							BufferedImage image = mm.getMapImage();
							File file = new File(System.getProperty("java.io.tmpdir") + File.separator + rand + gaul0code + "_" + layerCode + "MAP_.png");
							ImageIO.write(image, "png", file);
	
							// add to the list
							images.add(System.getProperty("java.io.tmpdir") + File.separator + rand + gaul0code + "_" + layerCode + "MAP_.png");
//							LOGGER.info("Rainfall added: " + images.get(images.size() - 1));
						}
					
					} 
				}

			} catch (IOException e) {
				throw new FenixGWTException(e.getMessage());
			}

			imagesMap.put(gaul0code, images);
			return images;

		}
	}
	
	private List<String> getNdviImages(RainfallParameters p, String gaul0code, List<String> gaul1codes, List<String> gaul2codes) throws FenixGWTException {

		if (ndviMap.keySet().contains(gaul0code)) {

			return ndviMap.get(gaul0code);

		} else {

			List<String> ndviImages = new ArrayList<String>();
			List<Date> dates = getLastDecade(p);
			GeoView geoViewG0 = null;
			GeoView geoViewG1 = null;
			GeoView geoViewG2 = null;

			try {
				
				// GAUL0
				WMSMapProvider gaul0 = wmsMapProviderDao.findByCode("layer_gaul0");
				LOGGER.info("G0 is null?" + (gaul0 == null));
				geoViewG0 = new GeoView(gaul0);
				geoViewG0.setStyleName("polygon_filled");
				geoViewG0.setCqlFilter(GaulCQLFilterFactory.createGaul0IsNot(gaul0code));
				
				// GAUL2
				if ((gaul2codes != null) && !gaul2codes.isEmpty()) {
					
					// add GAUL2 layer
					WMSMapProvider gaul2 = wmsMapProviderDao.findByCode("layer_gaul2");
					LOGGER.info("G2 is null?" + (gaul2 == null));
					geoViewG2 = new GeoView(gaul2);
					geoViewG2.setStyleName("zzlayer_gaul2_black");

					// add filtered GAUL2 layers
					List<CQLFilter> filters = new ArrayList<CQLFilter>();
					CQLFilter f;
					for (String g2 : gaul2codes) {
						f = GaulCQLFilterFactory.createGaul2Is(g2);
						filters.add(f);
					}
					CQLFilter cqfilter = CQLFilterOp.or(filters);
					geoViewG2.setCqlFilter(cqfilter);
				}
				
				// GAUL1
				if ((gaul1codes != null) && !gaul1codes.isEmpty()) {
					
					// add GAUL1 layer
					WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
					LOGGER.info("G1 is null?" + (gaul1 == null));
					geoViewG1 = new GeoView(gaul1);
					geoViewG1.setStyleName("zzlayer_gaul1_black");

					// add filtered GAUL1 layers
					List<CQLFilter> filters = new ArrayList<CQLFilter>();
					CQLFilter f;
					for (String g1 : gaul1codes) {
						f = GaulCQLFilterFactory.createGaul1Is(g1);
						filters.add(f);
					}
					CQLFilter cqfilter = CQLFilterOp.or(filters);
					geoViewG1.setCqlFilter(cqfilter);
				}

				for (Date date : dates) {

					// create layer's code
					String layerCode = createNdviLayerCode(date);
					LOGGER.info("Adding NDVI: " + layerCode);

					// create the map builder
					WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
					mm.setHeight(200);
					mm.setWidth(300);

					// add RF layer
					WMSMapProvider vgt = wmsMapProviderDao.findByCode(layerCode);
					LOGGER.info("NDVI for "+layerCode+" is null?" + (vgt == null));
					
					// avoid empty layers
					if (vgt != null) {
					
						// add NDVI layer
						mm.addLayer(vgt);
	
						// add GAUL0 layer
						mm.addLayer(geoViewG0);
	
						// add GAUL2 layer
						if ((gaul2codes != null) && !gaul2codes.isEmpty()) 
							mm.addLayer(geoViewG2);
						
						// add GAUL1 layer
						if ((gaul1codes != null) && !gaul1codes.isEmpty()) 
							mm.addLayer(geoViewG1);
	
						// create files
						if (gaul0code != null) {
	
							// get the bounding box
							BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(gaul0code));
							mm.setBoundingBox(bbox);
							
							// random number to separat4e images
							double rand = Math.random() * 1000000;
	
							// compute the map image
							BufferedImage image = mm.getMapImage();
							File file = new File(System.getProperty("java.io.tmpdir") + File.separator + rand + gaul0code + "_" + layerCode + "_NDVI.png");
							ImageIO.write(image, "png", file);
	
							// add to the list
							ndviImages.add(System.getProperty("java.io.tmpdir") + File.separator + rand + gaul0code + "_" + layerCode + "_NDVI.png");
//							LOGGER.info("NDVI added: " + ndviImages.get(ndviImages.size() - 1));
						}
					
					}
				}

			} catch (IOException e) {
				throw new FenixGWTException(e.getMessage());
			}

			ndviMap.put(gaul0code, ndviImages);
			return ndviImages;

		}
	}

	@SuppressWarnings("deprecation")
	private String createAfricaLayerCode(Date date) {
		String code = "raster_di";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	private String createLayerCode(Date date) {
		String code = "raster_ri";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	private String createNdviLayerCode(Date date) {
		String code = "raster_dv";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}

	private List<Date> getLastDecade(RainfallParameters p) {
		List<Date> dates = p.getYearOneDates();
		Date first = p.getYearOneDates().get(0);
		if (p.getYearTwoDates().size() > 0) {
			Date second = p.getYearTwoDates().get(0);
			if (second.compareTo(first) > 0)
				dates = p.getYearTwoDates();
		}
		// if (dates.size() > 3)
		// return dates.subList((dates.size() - 4), (dates.size() - 1));
		return dates;
	}

	private String createTitle(String regionFeatureCode, String codingType) {
		if (codingType.equals("GAUL")) {
			String countryName = codecDao.findGaul0LabelFromGaul1Code(regionFeatureCode);
			String regionName = codecDao.getLabelFromCodeCodingSystem(regionFeatureCode, "GAUL1", "0", "EN");
			if (countryName == null) {
				regionName = codecDao.getLabelFromCodeCodingSystem(regionFeatureCode, "GAUL2", "0", "EN");
				countryName = codecDao.findGaul1LabelFromGaul2Code(regionFeatureCode);
			}
			return regionName + " (" + countryName + ")";
		} else if (codingType.equals("GTS")) {
			String regionName = codecDao.getLabelFromCodeCodingSystem(regionFeatureCode, "GTS", "0", "EN");
			String gaul1code = codecDao.findGaul1CodeFromGtsCode(regionFeatureCode);
			String gaul1Label = codecDao.getLabelFromCodeCodingSystem(gaul1code, "GAUL1", "0", "EN");
			String countryName = codecDao.findGaul0LabelFromGaul1Code(gaul1code);
			return regionName + " (" + gaul1Label + ", " + countryName + ") [Station]";
		}
		return "";
	}

	private List<QuantitativeCoreContent> cumulateRaifall(List<QuantitativeCoreContent> rainfall) {
		for (int i = 0; i < rainfall.size(); i++) {
			if (i > 0) {
				QuantitativeCoreContent q = rainfall.get(i);
				q.setQuantity(q.getQuantity() + rainfall.get(i - 1).getQuantity());
			}
		}
		return rainfall;
	}

	private List<IndexCoreContent> cumulateAverage(List<IndexCoreContent> average) {
		for (int i = 0; i < average.size(); i++) {
			if (i > 0) {
				IndexCoreContent q = average.get(i);
				q.setQuantity(q.getQuantity() + average.get(i - 1).getQuantity());
			}
		}
		return average;
	}

	private List<List<String>> createRainfallTable(RainfallBean b) {
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		headers.add("Date");
		headers.add("Rainfall " + b.getYearOneRange() + " (mm)"); 
		if (!b.getSecondIstogramValues().isEmpty())
			headers.add("Rainfall " + b.getYearOneRange() + " (mm)");
		if (b.getLineValues().size() > 0)
			headers.add("Average " + b.getAverageRange());
		table.add(headers);
		for (int i = 0; i < b.getFirstIstogramValues().size(); i++) {
			List<String> row = new ArrayList<String>();
			row.add(b.getDates().get(i));
			if (!b.getFirstIstogramValues().get(i).equals("null"))
				row.add(b.getFirstIstogramValues().get(i));
			else
				row.add("n.a.");
			if (!b.getSecondIstogramValues().isEmpty())
				if (!b.getSecondIstogramValues().get(i).equals("null"))
					row.add(b.getSecondIstogramValues().get(i));
				else
					row.add("n.a.");
			if (b.getLineValues().size() > 0) 
				if (!b.getLineValues().get(i).equals("null")) 
					row.add(b.getLineValues().get(i));
				else 
					row.add("n.a.");
			table.add(row);
		}
		return table;
	}

	

	public List<CodeVo> findAllGaul0() {
		List<Code> codes = codecDao.findAllGaul0();
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}

	public List<CodeVo> findAllGaul1FromGaul0(String gaul0) {
		List<Code> codes = codecDao.findAllGaul1FromGaul0(gaul0);
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}

	public List<CodeVo> findAllGaul2FromGaul1(String gaul1) {
		List<Code> codes = codecDao.findAllGaul2FromGaul1(gaul1);
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}

	public List<CodeVo> findAllRainstationFromGaul1(String gaul1) {
		List<CodeVo> vos = new ArrayList<CodeVo>();
		List<Code> codes = codecDao.findRainStationsFromGaul1(gaul1);
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}

	private CodeVo code2vo(Code c) {
		CodeVo vo = new CodeVo();
		vo.setCode(c.getCode());
		vo.setDescription(c.getDescription());
		vo.setLabel(c.getLabel());
		vo.setLangCode(c.getLangCode());
		return vo;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setRainfallDao(RainfallDao rainfallDao) {
		this.rainfallDao = rainfallDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setRainfallUtils(RainfallUtils rainfallUtils) {
		this.rainfallUtils = rainfallUtils;
	}

}
