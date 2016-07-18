package org.fao.fenix.web.modules.rainfall.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.fao.fenix.core.domain.comparison.QuantitativeCoreContentComparator;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.rainfall.RainfallBean;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.rainfall.RainfallDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate.RainfallCharts;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;

public class RainfallUtils {
	
	private WMSMapProviderDao wmsMapProviderDao;

	private LayerGaulUtils layerGaulUtils;
	
	private CodecDao codecDao;
	
	private RainfallDao rainfallDao;
	
	private static Map<Integer, String> monthLabelMap;

	private static Map<String, List<String>> imagesMap;
	
	private static Map<String, List<String>> ndviMap;

	private static Map<String, List<String>> countriesMap;
	
	private static Map<String, List<String>> countriesNdviMap;
	
	static {
		imagesMap = new HashMap<String, List<String>>();
		ndviMap = new HashMap<String, List<String>>();
		countriesMap = new HashMap<String, List<String>>();
		countriesNdviMap = new HashMap<String, List<String>>();
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

	public List<String> getMapImagesForCountryBrief(List<Date> dates, String regionCode) throws FenixGWTException {

		List<String> images = new ArrayList<String>();

		try {

			for (Date date : dates) {

				// create layer's code
				String layerCode = createLayerCodeCountryBrief(date);

				// Find the needed layers
				WMSMapProvider vgt = wmsMapProviderDao.findByCode(layerCode);
				
				if (vgt == null){
					images.add("No Map");
					continue;
				}
				
				WMSMapProvider gaul1 = wmsMapProviderDao.findByCode("layer_gaul1");
				GeoView gv1 = new GeoView(gaul1);
				gv1.setStyleName("zzlayer_gaul1_black");
				gv1.setCqlFilter(GaulCQLFilterFactory.createGaul0Is(regionCode));

				// create the map builder
				WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
				mm.setHeight(200);
				mm.setWidth(300);
				mm.addLayer(vgt);
				mm.addLayer(gv1);

				// get the bounding box
				BoundingBox bbox = layerGaulUtils.getExtent(0, Integer.parseInt(regionCode));
				mm.setBoundingBox(bbox);

				// compute the map image
				BufferedImage image = mm.getMapImage();
				File file = new File(System.getProperty("java.io.tmpdir") + File.separator + layerCode + ".png");
				ImageIO.write(image, "png", file);

				// add to the list
				images.add(System.getProperty("java.io.tmpdir") + File.separator + layerCode + ".png");
			}

		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}

		return images;
	}
	
	public RainfallCharts createRainfallChartsToCountryBrief(RainfallParameters parameters) throws FenixGWTException {
		List<RainfallBean> beans = createRainfallBeansForCountryBrief(parameters);
		RainfallCharts rainfallReport = new RainfallCharts(beans);
		rainfallReport.createCharts();
		return rainfallReport;
	}
	
	@SuppressWarnings("deprecation")
	private List<RainfallBean> createRainfallBeansForCountryBrief(RainfallParameters p) throws FenixGWTException {

		List<RainfallBean> beans = new ArrayList<RainfallBean>();
		List<IndexCoreContent> averageRainfall = null;
		List<QuantitativeCoreContent> firstRainfall = null;
		List<QuantitativeCoreContent> secondRainfall = null;

		for (String regionCode : p.getRegion()) {

			RainfallBean b = new RainfallBean();

			b.setTitle(codecDao.getLabelFromCodeCodingSystem(regionCode, "GAUL1", "0", "EN"));
			b.setXLabel("Dekads (10 days interval)");
			b.setYLabel("Estimated Rainfall (mm)");
			b.setHasChart(p.hasChart());
			b.setHasMap(p.hasMap());
			b.setHasTable(p.hasTable());
			b.setHasGeneralClimate(p.isHasGeneralClimate());
			b.setHasNdvi(p.isHasNdvi());
			b.setDates(createDekadeDates(p.getYearOneDates()));
			
			int firstDate = 1900 + p.getYearOneDates().get(0).getYear();
			int secondDate = 1900 + p.getYearOneDates().get((p.getYearOneDates().size()-1)).getYear(); 
			if (firstDate == secondDate){
				b.setYearOne(1900 + p.getYearOneDates().get(0).getYear());
				b.setYearTwo(null);
			}
			else{
				b.setYearOne(1900 + p.getYearOneDates().get(0).getYear());
				b.setYearTwo(1900 + p.getYearOneDates().get((p.getYearOneDates().size()-1)).getYear());
			} 
			
			String featureCode = regionCode;

			List<Date> dates = p.getYearOneDates();
			List<IndexCoreContent> tmpAvg = rainfallDao.findRainfallAverage(regionCode, p.getYearOneDates(), "FAO-NOAA", "FAO-NOAA-GAUL1");
			
			if (tmpAvg.isEmpty())
				tmpAvg = rainfallDao.findRainfallAverage(regionCode, dates, "FAO-JRC", "FAO-JRC-GAUL1");
			averageRainfall = tmpAvg;

			if (averageRainfall.size() > 0) {
				Date baseDateFrom = averageRainfall.get(0).getBaseDateFrom();
				Date baseDateTo = averageRainfall.get(0).getBaseDateTo();
				String rainfallAverage = "(" + String.valueOf(1900 + baseDateFrom.getYear()) + " - " + String.valueOf(1900 + baseDateTo.getYear()) + ")";
				b.setAverageRange(rainfallAverage);
			}

			List<QuantitativeCoreContent> tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), "GAUL", "FAO-NOAA", "FAO-NOAA-GAUL1");
			if (tmpRain.isEmpty())
				tmpRain = rainfallDao.findRainfall(featureCode, p.getYearOneDates(), "GAUL", "FAO-JRC", "FAO-JRC-GAUL1");
			firstRainfall = tmpRain;

			normalizeArrays(p.getYearOneDates(), firstRainfall, secondRainfall, averageRainfall);

			List<String> firstIstogramValues = new ArrayList<String>();
			List<String> lineValues = new ArrayList<String>();

			for (QuantitativeCoreContent q : firstRainfall)
				if (q.getQuantity() != null)
					firstIstogramValues.add(String.valueOf(q.getQuantity().intValue()));
				else
					firstIstogramValues.add("null");

			if (p.hasAverage())
				for (IndexCoreContent i : averageRainfall)
					if (i.getQuantity() != null)
						lineValues.add(String.valueOf(i.getQuantity().intValue()));
					else
						lineValues.add("null");

			b.setFirstIstogramValues(firstIstogramValues);
			b.setLineValues(lineValues);

			beans.add(b);
		}

		return beans;
	}
	
	@SuppressWarnings("deprecation")
	public List<String> createDekadeDates(List<Date> dates) {
		List<String> dekades = new ArrayList<String>();
		for (Date d : dates) {
			String dek = monthLabelMap.get(d.getMonth()) + " - ";
			if (d.getDate() < 11)
				dek += "d1";
			else if (d.getDate() > 10 && d.getDate() < 21)
				dek += "d2";
			else if (d.getDate() > 20)
				dek += "d3";
			dekades.add(dek);
		}
		return dekades;
	}
	
	@SuppressWarnings("deprecation")
	private String createLayerCodeCountryBrief(Date date) {
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
	
	@SuppressWarnings("deprecation")
	public void normalizeArrays(List<Date> dates, List<QuantitativeCoreContent> firstRainfall, List<QuantitativeCoreContent> secondRainfall, List<IndexCoreContent> averageRainfall) {
		
		for (Date d : dates) {

			boolean firstHasDate = false;
			for (QuantitativeCoreContent q : firstRainfall) {
				if ((q.getDate().getMonth() == d.getMonth()) && (q.getDate().getDate() == d.getDate()) && (q.getDate().getYear() == d.getYear())) {
					firstHasDate = true;
					break;
				}
			}

			if (!firstHasDate) {
				QuantitativeCoreContent q = new QuantitativeCoreContent();
				q.setQuantity(null);
				q.setDate(d);
				firstRainfall.add(q);
			}

			boolean secondHasDate = false;
			if (secondRainfall != null) {
				for (QuantitativeCoreContent q : secondRainfall) {
					if ((q.getDate().getMonth() == d.getMonth()) && (q.getDate().getDate() == d.getDate()) && (q.getDate().getYear() == d.getYear())) {
						secondHasDate = true;
						break;
					}
				}
				if (!secondHasDate) {
					QuantitativeCoreContent q = new QuantitativeCoreContent();
					q.setQuantity(null);
					q.setDate(d);
					secondRainfall.add(q);
				}
			}

		}
		
		QuantitativeCoreContentComparator c = new QuantitativeCoreContentComparator();
//		IndexCoreContentComparator i = new IndexCoreContentComparator();
		
		Collections.sort(firstRainfall, c);
		if (secondRainfall != null)
			Collections.sort(secondRainfall, c);
		
		normalizeAverage(firstRainfall, secondRainfall, averageRainfall);
	}
	
	@SuppressWarnings("deprecation")
	private void normalizeAverage(List<QuantitativeCoreContent> firstRainfall, List<QuantitativeCoreContent> secondRainfall, List<IndexCoreContent> averageRainfall) {
		List<IndexCoreContent> tmp = new ArrayList<IndexCoreContent>();
		for (QuantitativeCoreContent q : firstRainfall) {
			if ((averageRainfall != null) && !averageRainfall.isEmpty()) {
				for (IndexCoreContent i : averageRainfall) {
					if ((i.getDate().getDate() == q.getDate().getDate()) && (i.getDate().getMonth() == q.getDate().getMonth())) {
						tmp.add(i);
						break;
					}
				}
			}
		}
		averageRainfall = tmp;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setRainfallDao(RainfallDao rainfallDao) {
		this.rainfallDao = rainfallDao;
	}
	
}