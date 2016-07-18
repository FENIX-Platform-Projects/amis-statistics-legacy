package org.fao.fenix.web.modules.aquastat.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.comparison.StringComparator;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.olap.chart.OLAPChartConfiguration;
import org.fao.fenix.core.olap.chart.OLAPChartFactory;
import org.fao.fenix.core.olap.chart.OLAPChartType;
import org.fao.fenix.core.persistence.aquastat.AquastatConnector;
import org.fao.fenix.core.persistence.aquastat.AquastatDao;
import org.fao.fenix.core.persistence.security.AuthUtils;
import org.fao.fenix.core.utils.AquastatUtils;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.core.utils.FTPConnector;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.jfree.chart.plot.PlotOrientation;
import org.springframework.core.io.Resource;

public class AquastatLib {

	private AquastatDao aquastatDao;

	private DatasetImporter datasetImporter;
	
	private String dir;
	
	private AquastatConnector aquastatConnector;

	private static final Logger LOGGER = Logger.getLogger(AquastatLib.class);
	
	public AquastatLib(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void writeFiles(List<String> countries) {

		if (countries.isEmpty()) {
			aquastatConnector.openConnection();
			countries = aquastatConnector.findAllAquastatCountries();
			LOGGER.info(countries.size() + " countries found");
			aquastatConnector.closeConnection();
		}
		
		createPopulationChart(countries);
		
		moveFiles();
	}
	
	public void moveFiles() {
		FTPConnector c = new FTPConnector();
		c.move("ext-ftp.fao.org", "FTP_GIEWS", "giewsuser", dir);
	}
	
	public void createPopulationChart(List<String> countries) {
		
		aquastatConnector.openConnection();
		
		for (String areaCode : countries) {
			
			// PROPORTION OF TOTAL WATER RESOURCES USED - Line Chart
			List<String> variableNames = new ArrayList<String>();
			variableNames = new ArrayList<String>();
			variableNames.add("Freshwater withdrawal as % of total renewable water resources (actual)");
			Map<String, Map<String, Double>> freshwaterMap = aquastatConnector.createPopulationMap("aqsv_giews_cnt", areaCode, variableNames);
			OLAPChartConfiguration c = createChartConfiguration("Proportion of total water resources used", "Year", "Total renewable water resources (%)", OLAPChartType.Line);
			c.setLegend(false);
			OLAPChartFactory.createFile(c, freshwaterMap, dir, null, areaCode + "_FRESHWATER.png");
			
			// IRRIGATION - Bar Chart
			variableNames = new ArrayList<String>();
			variableNames.add("Cultivated area (arable land + permanent crops)");
			variableNames.add("Irrigation potential");
			variableNames.add("Area equipped for irrigation: total");
			variableNames.add("Area equipped for irrigation: actually irrigated");
			Map<String, Map<String, Double>> irrigationMap = aquastatConnector.createFreshwaterMap("aqsv_giews_grp", areaCode, variableNames);
			c = createChartConfiguration("Irrigation", "Indicator", "Area (1000 ha)", OLAPChartType.Bar);
			c.setLegend(true);
			c.setTooltips(false);
			OLAPChartFactory.createFile(c, irrigationMap, dir, null, areaCode + "_IRRIGATION.png");
			
			// WATER WITHDRAWAL BY SECTOR - Pie Chart
			variableNames = new ArrayList<String>();
			variableNames.add("Agricultural water withdrawal");
			variableNames.add("Industrial water withdrawal");
			variableNames.add("Municipal water withdrawal");
			Map<String, Map<String, Double>> firstPieMap = aquastatConnector.createNonPopulationMap("aqsv_giews_grp", areaCode, variableNames);
			c = createChartConfiguration("Water withdrawal by sector", "Indicator", "Area", OLAPChartType.Pie);
			c.setLegend(false);
			c.setTooltips(false);
			String[] pie1parameters = new String[]{"Agricultural water withdrawal", "Industrial water withdrawal", "Municipal water withdrawal"};
			OLAPChartFactory.createFile(c, firstPieMap, dir, pie1parameters, areaCode + "_PIE1.png");
			
			// WATER WITHDRAWAL BY SOURCE - PieChart
			variableNames = new ArrayList<String>();
			variableNames.add("Groundwater withdrawal");
			variableNames.add("Surface water withdrawal");
			variableNames.add("Desalinated water produced");
			variableNames.add("Treated wastewater reused");
			Map<String, Map<String, Double>> secondPieMap = aquastatConnector.createNonPopulationMap("aqsv_giews_grp", areaCode, variableNames);
			c = createChartConfiguration("Water withdrawal by source", "Indicator", "Area", OLAPChartType.Pie);
			c.setLegend(false);
			c.setTooltips(false);
			String[] pie2parameters = new String[]{"Groundwater withdrawal", "Surface water withdrawal", "Desalinated water produced", "Treated wastewater reused"};
			OLAPChartFactory.createFile(c, secondPieMap, dir, pie2parameters, areaCode + "_PIE2.png");
			
			// AREA EQUIPPED FOR FULL CONTROL BY TYPE - PieChart
			variableNames = new ArrayList<String>();
			variableNames.add("Area equipped for full control irrigation by groundwater");
			variableNames.add("Area equipped for full control irrigation by surface water");
			variableNames.add("Area equipped for full control irrigation by non-conventional sources of water");
			variableNames.add("Area equipped for full control irrigation by mixed surface water and groundwater");
			Map<String, Map<String, Double>> thirdPieMap = aquastatConnector.createNonPopulationMap("aqsv_giews_grp", areaCode, variableNames);
			c = createChartConfiguration("Area equipped for full control irrigation by source", "Indicator", "Area", OLAPChartType.Pie);
			c.setLegend(false);
			c.setTooltips(false);
			String[] pie3parameters = new String[]{"Area equipped for full control irrigation by groundwater", "Area equipped for full control irrigation by surface water"};
			OLAPChartFactory.createFile(c, thirdPieMap, dir, pie3parameters, areaCode + "_PIE3.png");
			
			// AREA EQUIPPED FOR FULL CONTROL BY SOURCE - PieChart
			variableNames = new ArrayList<String>();
			variableNames.add("Area equipped for full control irrigation: localized irrigation");
			variableNames.add("Area equipped for full control irrigation: sprinkler irrigation");
			variableNames.add("Area equipped for full control irrigation: surface irrigation");
			Map<String, Map<String, Double>> fourthPieMap = aquastatConnector.createNonPopulationMap("aqsv_giews_grp", areaCode, variableNames);
			c = createChartConfiguration("Area equipped for full control irrigation by type", "Indicator", "Area", OLAPChartType.Pie);
			c.setLegend(false);
			c.setTooltips(false);
			String[] pie4parameters = new String[]{"Area equipped for full control irrigation: localized irrigation", "Area equipped for full control irrigation: sprinkler irrigation", "Area equipped for full control irrigation: surface irrigation"};
			OLAPChartFactory.createFile(c, fourthPieMap, dir, pie4parameters, areaCode + "_PIE4.png");
			
			LOGGER.info("Country " + areaCode + " complete.");
		}
		
		aquastatConnector.closeConnection();
		
	}

	public void createFreshWaterChart(List<String> countries) {
		
		long t0 = System.currentTimeMillis();
		
		List<String> allowedCodes = new ArrayList<String>();
		allowedCodes.add("18");
		
		for (String areaCode : countries) {
			OLAPChartConfiguration c = createChartConfiguration("Proportion of total freshwater resources used", "Year", "%", OLAPChartType.Line);
			c.setLegend(true);
			Map<String, Map<String, Double>> values = new TreeMap<String, Map<String, Double>>(new StringComparator());
			List<Integer> waterYears = aquastatDao.findAllPopulationYears(areaCode, "18");
			if (!waterYears.isEmpty()) {
				List<QuantitativeCoreContent> waterContents = aquastatDao.findPopulation(areaCode, waterYears);
				for (Integer year : waterYears) {
					Map<String, Double> waterMap = createFirstIndicatorValueMap(waterContents, allowedCodes, year);
					values.put(String.valueOf(year), waterMap);
				}
				Code code = aquastatConnector.getCodecDao().getCodeFromDescription(areaCode, "AQUASTAT-GAUL", "0", "EN");
				if (code != null) {
					String filename = code.getCode();
					OLAPChartFactory.createFile(c, values, dir, null, filename + "_FRESHWATER.png");
//					LOGGER.info(filename + "_FRESHWATER.png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
				} else {
					OLAPChartFactory.createFile(c, values, dir, null, areaCode + "_FRESHWATER.png");
//					LOGGER.info(areaCode + "_FRESHWATER.png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
				}
			}
		}
	}

	public void createPieChart(String title, String filename, List<String> indicatorCodes, List<String> countries) {

		long t0 = System.currentTimeMillis();
//		List<String> countries = aquastatDao.findAllPopulationCountries();

		for (String areaCode : countries) {
			
			OLAPChartConfiguration c = createChartConfiguration(title, "Indicator", "Area", OLAPChartType.Pie);
			Map<String, Map<String, Double>> values = new TreeMap<String, Map<String, Double>>(new StringComparator());

			Map<String, Double> map = new HashMap<String, Double>();
			for (String indicatorCode : indicatorCodes) {
				QuantitativeCoreContent q = aquastatDao.findSingleWater(areaCode, indicatorCode);
				if (q != null)
					map.put(AquastatUtils.reverseMap.get(indicatorCode), q.getQuantity());
			}
			values.put(title, map);

			Code code = aquastatConnector.getCodecDao().getCodeFromDescription(areaCode, "AQUASTAT-GAUL", "0", "EN");
			if (code != null) {
				String aquastatCode = code.getCode();
				OLAPChartFactory.createFile(c, values, dir, null, aquastatCode + "_" + filename + ".png");
//				LOGGER.info(aquastatCode + "_" + filename + ".png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
			} else {
				OLAPChartFactory.createFile(c, values, dir, null, areaCode + "_" + filename + ".png");
//				LOGGER.info(areaCode + "_" + filename + ".png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
			}
		}
	}

	public void createBarChart(List<String> countries) {

		long t0 = System.currentTimeMillis();
//		List<String> countries = aquastatDao.findAllPopulationCountries();

		List<String> allowedCodes = new ArrayList<String>();
		allowedCodes.add("16");
		allowedCodes.add("21");
		allowedCodes.add("15");
		allowedCodes.add("11");
		
		for (String areaCode : countries) {
			
			OLAPChartConfiguration c = createChartConfiguration("Irrigation", "Indicator", "Area", OLAPChartType.Bar);
			Map<String, Map<String, Double>> values = new TreeMap<String, Map<String, Double>>(new StringComparator());
			
			List<QuantitativeCoreContent> cultivatedArea = aquastatDao.findWater(areaCode, "16");
			Map<String, Double> cultivatedMap = createFirstIndicatorValueMap(cultivatedArea, allowedCodes);
			values.put("Cultivated area", cultivatedMap);

			List<QuantitativeCoreContent> potential = aquastatDao.findWater(areaCode, "21");
			Map<String, Double> potentialMap = createFirstIndicatorValueMap(potential, allowedCodes);
			values.put("Irrigation potential", potentialMap);

			List<QuantitativeCoreContent> equipped = aquastatDao.findWater(areaCode, "15");
			Map<String, Double> equippedMap = createFirstIndicatorValueMap(equipped, allowedCodes);
			values.put("Area equipped for irrigation", equippedMap);

			List<QuantitativeCoreContent> irrigated = aquastatDao.findWater(areaCode, "11");
			Map<String, Double> irrigatedMap = createFirstIndicatorValueMap(irrigated, allowedCodes);
			values.put("Area equipped for full control irrigation by other sources", irrigatedMap);

			Code code = aquastatConnector.getCodecDao().getCodeFromDescription(areaCode, "AQUASTAT-GAUL", "0", "EN");
			if (code != null) {
				String filename = code.getCode();
				OLAPChartFactory.createFile(c, values, dir, null, filename + "_IRRIGATION.png");
//				LOGGER.info(filename + "_IRRIGATION.png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
			} else {
				OLAPChartFactory.createFile(c, values, dir, null, areaCode + "_IRRIGATION.png");
//				LOGGER.info(areaCode + "_IRRIGATION.png created in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
			}
		}
	}

	@SuppressWarnings("deprecation")
	public Map<String, Double> createYearValueMap(List<QuantitativeCoreContent> contents) {
		Map<String, Double> map = new HashMap<String, Double>();
		for (QuantitativeCoreContent q : contents)
			map.put(String.valueOf(1900 + q.getDate().getYear()), q.getQuantity());
		return map;
	}

	public Map<String, Double> createFirstIndicatorValueMap(List<QuantitativeCoreContent> contents, List<String> allowedCode, int year) {
		List<QuantitativeCoreContent> tmp = new ArrayList<QuantitativeCoreContent>();
		for (QuantitativeCoreContent q : contents) 
			if ((1900 + q.getDate().getYear()) == year)
				tmp.add(q);
		Map<String, Double> map = new HashMap<String, Double>();
		for (QuantitativeCoreContent q : tmp)
			if (allowedCode.contains(q.getFirstIndicator()))
				map.put(AquastatUtils.reverseMap.get(q.getFirstIndicator()), q.getQuantity());
		return map;
	}
	
	public Map<String, Double> createFirstIndicatorValueMap(List<QuantitativeCoreContent> contents, List<String> allowedCode) {
		Map<String, Double> map = new HashMap<String, Double>();
		for (QuantitativeCoreContent q : contents)
			if (allowedCode.contains(q.getFirstIndicator()))
				map.put(AquastatUtils.reverseMap.get(q.getFirstIndicator()), q.getQuantity());
		return map;
	}

	public OLAPChartConfiguration createChartConfiguration(String title, String xLabel, String yLabel, OLAPChartType chartType) {
		OLAPChartConfiguration c = new OLAPChartConfiguration();
		c.setChartType(chartType);
		c.setLegend(false);
		c.setPlotOrientation(PlotOrientation.VERTICAL);
		c.setTooltips(false);
		c.setUrls(false);
		c.setTitle(title);
		c.setxLabel(xLabel);
		c.setyLabel(yLabel);
		return c;
	}

	public void importDatasets(int[] rows) {
		AuthUtils authUtils = new AuthUtils();
		authUtils.loginAsSystemUser();
		try {
			if (rows[0] > 0) {
				long t0 = System.currentTimeMillis();
				FileInputStream dfile = new FileInputStream(new File(System.getProperty("java.io.tmpdir") + File.separator + "AquastatPopulationRelatedDataset.csv"));
				FileInputStream mfile = new FileInputStream(new File(Setting.getSystemPath() + "/aquastat/" + "Population_metadata.xml"));
				datasetImporter.importDataset(dfile, mfile, UploadPolicy.ignore, "");
//				LOGGER.info("Population Dataset imported in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
			}
			if (rows[1] > 0) {
				long t0 = System.currentTimeMillis();
				FileInputStream dfile = new FileInputStream(new File(System.getProperty("java.io.tmpdir") + File.separator + "AquastatWaterConsumptionDataset.csv"));
				FileInputStream mfile = new FileInputStream(new File(Setting.getSystemPath() + "/aquastat/" + "Consumption_metadata.xml"));
				datasetImporter.importDataset(dfile, mfile, UploadPolicy.ignore, "");
//				LOGGER.info("Water Consumption Dataset imported in " + ((System.currentTimeMillis() - t0)) + " millisecond(s)");
				
			}
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (FenixException e) {
			LOGGER.error(e.getMessage());
		}
		authUtils.logout();
	}

	public int[] createAquastatFiles() {
		return aquastatConnector.createAquastatFiles();
	}

	public int[] createAquastatFiles(String areaCode) {
		return aquastatConnector.createAquastatFiles(areaCode);
	}
	
	public int[] createAquastatFiles(List<String> areaCodes) {
		return aquastatConnector.createAquastatFiles(areaCodes);
	}

	public void setAquastatDao(AquastatDao aquastatDao) {
		this.aquastatDao = aquastatDao;
	}

	public void setDatasetImporter(DatasetImporter datasetImporter) {
		this.datasetImporter = datasetImporter;
	}

	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setAquastatConnector(AquastatConnector aquastatConnector) {
		this.aquastatConnector = aquastatConnector;
	}

}