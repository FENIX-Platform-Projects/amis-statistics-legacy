package org.fao.fenix.web.modules.fpi.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.FenixGaul0;
import org.fao.fenix.core.domain.coding.FenixGaul1;
import org.fao.fenix.core.domain.coding.FenixGaul2;
import org.fao.fenix.core.domain.coding.HS;
import org.fao.fenix.core.domain.constants.PeriodTypeCode;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.domain.index.BovineIndex;
import org.fao.fenix.core.domain.index.CerealsIndex;
import org.fao.fenix.core.domain.index.DairyIndex;
import org.fao.fenix.core.domain.index.FPIConst;
import org.fao.fenix.core.domain.index.FeedIndex;
import org.fao.fenix.core.domain.index.FoodPriceIndex;
import org.fao.fenix.core.domain.index.MealIndex;
import org.fao.fenix.core.domain.index.MeatIndex;
import org.fao.fenix.core.domain.index.OilIndex;
import org.fao.fenix.core.domain.index.OvineIndex;
import org.fao.fenix.core.domain.index.PigIndex;
import org.fao.fenix.core.domain.index.PoultryIndex;
import org.fao.fenix.core.domain.index.RiceIndex;
import org.fao.fenix.core.domain.index.SingleCommodityIndex;
import org.fao.fenix.core.domain.index.WheatIndex;
import org.fao.fenix.core.domain.index.parameters.CerealsIndexParameters;
import org.fao.fenix.core.domain.mu.MeasurementUnit;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.coding.FenixGaulDao;
import org.fao.fenix.core.persistence.coding.HSDao;
import org.fao.fenix.core.persistence.index.IndexCalculator;
import org.fao.fenix.core.persistence.index.IndexParametersCalculator;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.core.common.vo.FpiGaulVo;
import org.fao.fenix.web.modules.core.common.vo.HSVo;
import org.fao.fenix.web.modules.fpi.common.constants.GAULCodeSelectorConstants;
import org.fao.fenix.web.modules.fpi.common.services.FpiService;
import org.fao.fenix.web.modules.fpi.common.vo.BovineIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.CerealsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.DairyIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.DataEntryVo;
import org.fao.fenix.web.modules.fpi.common.vo.FeedIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.FoodPriceIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.MealsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.MeasurementUnitVo;
import org.fao.fenix.web.modules.fpi.common.vo.MeatIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.OilsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.OvineIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.PeriodTypeCodeVo;
import org.fao.fenix.web.modules.fpi.common.vo.PigIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.PoultryIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.RiceIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.SaveDataVo;
import org.fao.fenix.web.modules.fpi.common.vo.SugarIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.WheatIndexVo;
import org.fao.fenix.web.modules.fpi.server.utils.FPIVoConverter;
import org.fao.fenix.web.modules.fpi.server.utils.MUComparator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class FpiServiceImpl extends RemoteServiceServlet implements FpiService {

	FenixGaulDao fenixGaulDao;

	HSDao hsDao;

	DatasetDao datasetDao;

	IndexCalculator indexCalculator;
	
	FPIExcel fpiExcel;
	
	public String createExcel(String indexName, List<List<String>> table) {
		return fpiExcel.createExcel(indexName, table);
	}
	
	public void populateExchangeRates() {
		// FIXME		
	}
	
	public void populateFPI() {
		// FIXME
	}
	
	public void populateMeasurementUnits() {
		// FIXME
	}
	
	// FIXME
	public List<MeasurementUnitVo> getAllMU() {
//		List<MeasurementUnit> list = measurementUnitDao.findAll();
//		List<MeasurementUnitVo> result = new ArrayList<MeasurementUnitVo>();
//		for (int i = 0; i < list.size(); i++)
//			result.add(mu2vo(list.get(i)));
//		sortMu(result);
//		return result;
		return new ArrayList<MeasurementUnitVo>();
	}

	public List<MeasurementUnitVo> sortMu(List<MeasurementUnitVo> list) {
		MUComparator comparator = new MUComparator();
		Collections.sort(list, comparator);
		return list;
	}

	// FIXME
	public void updateMU(List<MeasurementUnitVo> list) {
//		for (int i = 0; i < list.size(); i++) {
//			MeasurementUnit unit = measurementUnitDao.findById(list.get(i).getId());
//			unit.setVisible(list.get(i).isVisible());
//			unit.setCurrency(list.get(i).isCurrency());
//			measurementUnitDao.update(unit);
//		}
	}

	// FIXME
	public MeasurementUnitVo mu2vo(MeasurementUnit mu) {
		MeasurementUnitVo vo = new MeasurementUnitVo();
//		vo.setConstant(mu.getConstant());
//		vo.setCurrency(mu.isCurrency());
		vo.setId(mu.getId());
//		vo.setLabel(mu.getLabel().getEn());
		vo.setSymbol(mu.getSymbol());
//		vo.setVisible(mu.getVisible());
		return vo;
	}

	// FIXME
	public MeasurementUnit vo2mu(MeasurementUnitVo vo) {
		MeasurementUnit mu = new MeasurementUnit();
//		mu.setConstant(vo.getConstant());
//		mu.setCurrency(vo.isCurrency());
		mu.setId(vo.getId());
//		mu.setLabel(new MeasurementUnitLabel(vo.getLabel()));
		mu.setSymbol(vo.getSymbol());
//		mu.setVisible(vo.isVisible());
		return mu;
	}

	// FIXME ?
	public void setMetadataWholesale(CoreDataset dataset, List<QuantitativeCoreContent> contentList, PeriodTypeCodeVo periodTypeCode, String source, String title) {
//		dataset.setContentList(contentList);
//		dataset.setPeriodTypeCode(periodTypeCode);
//		dataset.setDateLastUpdate(new Date());
//		dataset.setSource(source);
//		dataset.setTitle(title);
	}

	// FIXME ?
	public void saveDailyData(List<SaveDataVo> list, String datasetType, String source) {

//		List<NumericMuCommodityCurrencyMarket> contentList = createContentListForWholesaleDataset(list);
//		PatternMetadataMap patternMetadataMap = new PatternMetadataMap("PricePattern", "World", source, "", "world", "Daily");
//
//		if (datasetType.equals("International")) {
//			InternationalPriceDataset dataset = new InternationalPriceDataset();
//			dataset.setContentList(createContentList(list));
//			dataset.setPeriodTypeCode("Daily");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Consumer")) {
//			ConsumerPriceDataset dataset = new ConsumerPriceDataset();
//			dataset.setPeriodTypeCode("Daily");
//			dataset.setSharingCode("Public");
//			dataset.setContentList(contentList);
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Producer")) {
//			ProducerPriceDataset dataset = new ProducerPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Daily");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Retail")) {
//			RetailPriceDataset dataset = new RetailPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Daily");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Wholesale")) {
//			WholesalePriceDataset dataset = new WholesalePriceDataset();
//			List<NumericMuCommodityCurrencyMarket> contentListWholesale = createContentListForWholesaleDataset(list);
//			dataset.setContentList(contentListWholesale);
//			dataset.setPeriodTypeCode("Daily");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		}
//
//		saveWeeklyDataFromDaily(list, datasetType, source);

	}

	// FIXME ?
	public void saveWeeklyDataFromDaily(List<SaveDataVo> list, String datasetType, String source) {
//		List<SaveDataVo> weekly = new ArrayList<SaveDataVo>();
//		int counter = 0;
//		double total = 0;
//		int entries = 0;
//		for (int i = 0; i < list.size(); i++) {
//			if (counter < 7) {
//				counter++;
//				SaveDataVo parent = list.get(i);
//				total += parent.getValue();
//				entries++;
//			} else {
//				SaveDataVo vo = new SaveDataVo();
//				SaveDataVo parent = list.get(i - 6); // to have the week date
//				vo.setAccessType(parent.getAccessType());
//				vo.setCommodityCode(parent.getCommodityCode());
//				vo.setCurrency(parent.getCommodityCode());
//				vo.setDatasetType(parent.getDatasetType());
//				vo.setDataSource(parent.getDataSource());
//				vo.setDate(new Date(parent.getDate().getYear(), parent.getDate().getMonth(), (i - 6)));
//				vo.setMeasurementUnit(parent.getMeasurementUnit());
//				vo.setPeriodType("Weekly");
//				vo.setRegion(parent.getRegion());
//				vo.setTransportType(parent.getTransportType());
//				System.out.println("GENERATED " + total + " / " + entries + " = " + (total / entries));
//				vo.setValue(total / entries);
//				weekly.add(vo);
//				counter = 0;
//				total = 0;
//				entries = 0;
//			}
//		}
//		System.out.println(weekly.size() + " created from daily data");
//		saveWeeklyData(weekly, datasetType, source);
	}

	// FIXME ?
	public void saveWeeklyData(List<SaveDataVo> list, String datasetType, String source) {

//		List<NumericMuCommodityCurrencyMarket> contentList = createContentListForWholesaleDataset(list);
//		PatternMetadataMap patternMetadataMap = new PatternMetadataMap("PricePattern", "World", source, "", "world", "Daily");
//
//		if (datasetType.equals("International")) {
//			InternationalPriceDataset dataset = new InternationalPriceDataset();
//			dataset.setContentList(createContentList(list));
//			dataset.setPeriodTypeCode("Weekly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Consumer")) {
//			ConsumerPriceDataset dataset = new ConsumerPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Weekly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Producer")) {
//			ProducerPriceDataset dataset = new ProducerPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Weekly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Retail")) {
//			RetailPriceDataset dataset = new RetailPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Weekly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Wholesale")) {
//			WholesalePriceDataset dataset = new WholesalePriceDataset();
//			List<NumericMuCommodityCurrencyMarket> contentListWholesale = createContentListForWholesaleDataset(list);
//			dataset.setContentList(contentListWholesale);
//			dataset.setPeriodTypeCode("Weekly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		}
//
//		saveMonthlyDataFromWeekly(list, datasetType, source);
	}

	// FIXME ?
	public void saveMonthlyDataFromWeekly(List<SaveDataVo> list, String datasetType, String source) {
//		System.out.println("received " + list.size() + " weekly data");
//		List<SaveDataVo> daily = new ArrayList<SaveDataVo>();
//		for (int i = 0; i < 12; i++) {
//			double total = 0;
//			double entries = 0;
//			for (int j = 0; j < list.size(); j++) {
//				SaveDataVo parent = list.get(j);
//				if (list.get(j).getDate().getMonth() == i) {
//					total += parent.getValue();
//					entries++;
//					System.out.println(total + " / " + entries);
//				}
//			}
//			SaveDataVo parent = list.get(0);
//			SaveDataVo vo = new SaveDataVo();
//			vo.setAccessType(parent.getAccessType());
//			vo.setCommodityCode(parent.getCommodityCode());
//			vo.setCurrency(parent.getCommodityCode());
//			vo.setDatasetType(parent.getDatasetType());
//			vo.setDataSource(parent.getDataSource());
//			vo.setDate(new Date(parent.getDate().getYear(), i, 1));
//			vo.setMeasurementUnit(parent.getMeasurementUnit());
//			vo.setPeriodType("Monthly");
//			vo.setRegion(parent.getRegion());
//			vo.setTransportType(parent.getTransportType());
//			vo.setValue(total / entries);
//			daily.add(vo);
//		}
//		System.out.println("*** SENDING " + daily.size() + " MONTHLY PRICES");
//		saveMonthlyData(daily, datasetType, source);
	}

	// FIXME ?
	public void saveMonthlyData(List<SaveDataVo> list, String datasetType, String source) {
//		List<NumericMuCommodityCurrencyMarket> contentList = createContentListForWholesaleDataset(list);
//		PatternMetadataMap patternMetadataMap = new PatternMetadataMap("PricePattern", "World", source, "", "world", "Daily");
//
//		if (datasetType.equals("International")) {
//			InternationalPriceDataset dataset = new InternationalPriceDataset();
//			dataset.setContentList(createContentList(list));
//			dataset.setPeriodTypeCode("Monthly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Consumer")) {
//			ConsumerPriceDataset dataset = new ConsumerPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Monthly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Producer")) {
//			ProducerPriceDataset dataset = new ProducerPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Monthly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Retail")) {
//			RetailPriceDataset dataset = new RetailPriceDataset();
//			dataset.setContentList(contentList);
//			dataset.setPeriodTypeCode("Monthly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		} else if (datasetType.equals("Wholesale")) {
//			WholesalePriceDataset dataset = new WholesalePriceDataset();
//			List<NumericMuCommodityCurrencyMarket> contentListWholesale = createContentListForWholesaleDataset(list);
//			dataset.setContentList(contentListWholesale);
//			dataset.setPeriodTypeCode("Monthly");
//			dataset.setSharingCode("Public");
//			dataset.setDateLastUpdate(new Date());
//			dataset.setSource(source);
//			dataset.setTitle(source + " " + datasetType + " Prices");
//			datasetDao.saveOrAppendUnderKey(dataset);
//		}
	}

	public List<QuantitativeCoreContent> createContentList(List<SaveDataVo> list) {
		List<QuantitativeCoreContent> commodities = new ArrayList<QuantitativeCoreContent>();
		for (int i = 0; i < list.size(); i++) {
			SaveDataVo vo = list.get(i);
			QuantitativeCoreContent c = new QuantitativeCoreContent();
			c.setCommodityCode(vo.getCommodityCode());
			c.setFeatureCode(vo.getRegion());
			//c.setMeasurementUnit(vo.getMeasurementUnit()); // FIXME MU is an object
			c.setQuantity(vo.getValue()); 
			c.setDate(vo.getDate());
			commodities.add(c);
		}
		return commodities;
	}

	public List<QuantitativeCoreContent> createContentListForWholesaleDataset(List<SaveDataVo> list) {
		List<QuantitativeCoreContent> commodities = new ArrayList<QuantitativeCoreContent>();
		for (int i = 0; i < list.size(); i++) {
			SaveDataVo vo = list.get(i);
			QuantitativeCoreContent c = new QuantitativeCoreContent();
			c.setCommodityCode(vo.getCommodityCode());
			c.setFeatureCode(vo.getRegion());
			// c.setMeasurementUnit(vo.getMeasurementUnit()); // FIXME MU is an object
			c.setQuantity(vo.getValue());
			c.setDate(vo.getDate());
			commodities.add(c);
		}
		return commodities;
	}

	public Dataset getPriceDataset(String type) {
		List<Dataset> list = datasetDao.findAll();
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getClass().getSimpleName().equals(type + "PriceDataset"))
				return list.get(i);
		CoreDataset dataset = new CoreDataset();
		return dataset;
	}

	public List<FoodPriceIndexVo> calculateFoodPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		// ANNUAL INDEX
		if (timePeriod.equals(PeriodTypeCodeVo.annual))
			return calculateFoodPriceIndexAnnual(startBaseYear, endBaseYear, startYear, endYear);
		// MONTHLY INDEX
		List<FoodPriceIndexVo> list = new ArrayList<FoodPriceIndexVo>();
		try {
			List<FoodPriceIndex> result = indexCalculator.calculateFoodPriceIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.foodPriceIndex2Vo(result.get(i)));
		} catch (FenixException e) {
			Logger.getRootLogger().error(e.getLocalizedMessage());
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}
	
	public List<WheatIndexVo> calculateWheatIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<WheatIndex> result = indexCalculator.calculateWheatIndex(startBaseYear, endBaseYear, startYear, endYear);
		List<WheatIndexVo> vos = new ArrayList<WheatIndexVo>();
		for (WheatIndex w : result) {
			WheatIndexVo vo = new WheatIndexVo();
			vo.setDate(w.getDate());
			vo.setWheatIGCRebased(w.getWheatIGCRebased());
			vos.add(vo);
		}
		return vos;
	}

	public List<FoodPriceIndexVo> calculateFoodPriceIndexAnnual(int startBaseYear, int endBaseYear, int startYear, int endYear) throws FenixGWTException {
		List<FoodPriceIndexVo> annual = new ArrayList<FoodPriceIndexVo>();
		try {
			List<FoodPriceIndexVo> monthly = calculateFoodPriceIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			List<MeatIndexVo> meatMonthly = calculateMeatPriceIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			List<DairyIndexVo> dairyMonthly = calculateDairyPriceIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			List<CerealsIndexVo> cerealsMonthly = calculateCerealsIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			List<OilsIndexVo> oilsMonthly = calculateOilsPriceIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			List<SugarIndexVo> sugarMonthly = calculateSugarPriceIndex(startBaseYear, endBaseYear, startYear, endYear, PeriodTypeCodeVo.monthly);
			for (int i = startYear; i <= endYear; i++) {
				FoodPriceIndexVo vo = new FoodPriceIndexVo();
				vo.setFoodPriceIndex(calculateFoodPriceIndexAnnual(monthly, i).getFoodPriceIndex());
				vo.setMeatIndex(calculateMeatPriceIndexAnnual(meatMonthly, i).getMeatPriceIndex());
				vo.setDairyIndex(calculateDairyPriceIndexAnnual(dairyMonthly, i).getDairyPriceIndex());
				vo.setCerealsIndex(calculateCerealsPriceIndexAnnual(cerealsMonthly, i).getCerealsPriceIndex());
				vo.setOilsIndex(calculateOilsPriceIndexAnnual(oilsMonthly, i).getOilsIndex());
				vo.setSugarIndex(calculateSugarPriceIndexAnnual(sugarMonthly, i).getIndex());
				FieldParser parser = new FieldParser();
				vo.setDate(parser.parseDate(i + "-" + 11 + "-" + 28));
				annual.add(vo);
			}
		} catch (FenixGWTException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return annual;
	}

	public FoodPriceIndexVo calculateFoodPriceIndexAnnual(List<FoodPriceIndexVo> monthly, int year) {
		FoodPriceIndexVo vo = new FoodPriceIndexVo();
		List<FoodPriceIndexVo> annual = new ArrayList<FoodPriceIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getFoodPriceIndex();
		vo.setFoodPriceIndex(total / annual.size());
		System.out.println(year + " - " + total + " / " + annual.size() + " = " + vo.getFoodPriceIndex());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<RiceIndexVo> calculateRiceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<RiceIndexVo> list = new ArrayList<RiceIndexVo>();
		try {
			List<RiceIndex> result = indexCalculator.calculateRiceIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.riceIndex2Vo(result.get(i)));
			if (timePeriod.equals(FPIConst.ANNUAL_TIME_PERIOD)) {
				List<RiceIndexVo> annualList = new ArrayList<RiceIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateRicePriceIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public RiceIndexVo calculateRicePriceIndexAnnual(List<RiceIndexVo> monthly, int year) {
		RiceIndexVo vo = new RiceIndexVo();
		List<RiceIndexVo> annual = new ArrayList<RiceIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getRiceIndex();
		vo.setRiceIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getLowQualityIndicaIndex();
		vo.setLowQualityIndicaIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getHighQualityIndicaIndex();
		vo.setHighQualityIndicaIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getJaponicaIndex();
		vo.setJaponicaIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getAromaticIndex();
		vo.setAromaticIndex(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<PigIndexVo> calculatePigPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<PigIndexVo> list = new ArrayList<PigIndexVo>();
		try {
			List<PigIndex> result = indexCalculator.calculatePigIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY PIG " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.pigIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<PigIndexVo> annualList = new ArrayList<PigIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculatePigPriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY PIG " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public PigIndexVo calculatePigPriceIndexAnnual(List<PigIndexVo> monthly, int year) {
		PigIndexVo vo = new PigIndexVo();
		List<PigIndexVo> annual = new ArrayList<PigIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getPigWeightedPrice();
		vo.setPigWeightedPrice(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<PoultryIndexVo> calculatePoultryPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<PoultryIndexVo> list = new ArrayList<PoultryIndexVo>();
		try {
			List<PoultryIndex> result = indexCalculator.calculatePoultryIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY POULTRY " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.poultryIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<PoultryIndexVo> annualList = new ArrayList<PoultryIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculatePoultryPriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY POULTRY " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public PoultryIndexVo calculatePoultryPriceIndexAnnual(List<PoultryIndexVo> monthly, int year) {
		PoultryIndexVo vo = new PoultryIndexVo();
		List<PoultryIndexVo> annual = new ArrayList<PoultryIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getPoultryWeightedPrice();
		vo.setPoultryWeightedPrice(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<BovineIndexVo> calculateBovinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<BovineIndexVo> list = new ArrayList<BovineIndexVo>();
		try {
			List<BovineIndex> result = indexCalculator.calculateBovineIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY BOVINE " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.bovineIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<BovineIndexVo> annualList = new ArrayList<BovineIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateBovinePriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY BOVINE " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public BovineIndexVo calculateBovinePriceIndexAnnual(List<BovineIndexVo> monthly, int year) {
		BovineIndexVo vo = new BovineIndexVo();
		List<BovineIndexVo> annual = new ArrayList<BovineIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getBovineWeightedPrice();
		vo.setBovineWeightedPrice(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<OvineIndexVo> calculateOvinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<OvineIndexVo> list = new ArrayList<OvineIndexVo>();
		try {
			List<OvineIndex> result = indexCalculator.calculateOvineIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY OVINE " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.ovineIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<OvineIndexVo> annualList = new ArrayList<OvineIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateOvinePriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY OVINE " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public OvineIndexVo calculateOvinePriceIndexAnnual(List<OvineIndexVo> monthly, int year) {
		OvineIndexVo vo = new OvineIndexVo();
		List<OvineIndexVo> annual = new ArrayList<OvineIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getOvineWeightedPrice();
		vo.setOvineWeightedPrice(total / annual.size());
		vo.setDate(FieldParser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}
	
	public List<MealsIndexVo> calculateMealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<MealsIndexVo> list = new ArrayList<MealsIndexVo>();
		try {
			List<MealIndex> result = indexCalculator.calculateMealsIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.mealsIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<MealsIndexVo> annualList = new ArrayList<MealsIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateMealsIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public MealsIndexVo calculateMealsIndexAnnual(List<MealsIndexVo> monthly, int year) {
		MealsIndexVo vo = new MealsIndexVo();
		List<MealsIndexVo> annual = new ArrayList<MealsIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getIndex();
		vo.setIndex(total / annual.size());
		vo.setDate(FieldParser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}
	
	public List<FeedIndexVo> calculateFeedIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<FeedIndexVo> list = new ArrayList<FeedIndexVo>();
		try {
			List<FeedIndex> result = indexCalculator.calculateFeedIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.feedIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<FeedIndexVo> annualList = new ArrayList<FeedIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateFeedIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public FeedIndexVo calculateFeedIndexAnnual(List<FeedIndexVo> monthly, int year) {
		FeedIndexVo vo = new FeedIndexVo();
		List<FeedIndexVo> annual = new ArrayList<FeedIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getIndex();
		vo.setIndex(total / annual.size());
		vo.setDate(FieldParser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<MeatIndexVo> calculateMeatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<MeatIndexVo> list = new ArrayList<MeatIndexVo>();
		try {
			List<MeatIndex> result = indexCalculator.calculateMeatIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.meatIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<MeatIndexVo> annualList = new ArrayList<MeatIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateMeatPriceIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public MeatIndexVo calculateMeatPriceIndexAnnual(List<MeatIndexVo> monthly, int year) {
		MeatIndexVo vo = new MeatIndexVo();
		List<MeatIndexVo> annual = new ArrayList<MeatIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getMeatPriceIndex();
		vo.setMeatPriceIndex(total / annual.size());
		vo.setDate(FieldParser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<DairyIndexVo> calculateDairyPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<DairyIndexVo> list = new ArrayList<DairyIndexVo>();
		try {
			List<DairyIndex> result = indexCalculator.calculateDairyIndex(startBaseYear, endBaseYear, startYear, endYear);
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.dairyIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<DairyIndexVo> annualList = new ArrayList<DairyIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateDairyPriceIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public DairyIndexVo calculateDairyPriceIndexAnnual(List<DairyIndexVo> monthly, int year) {
		DairyIndexVo vo = new DairyIndexVo();
		List<DairyIndexVo> annual = new ArrayList<DairyIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getDairyPriceIndex();
		vo.setDairyPriceIndex(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<WheatIndexVo> calculateWheatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<WheatIndexVo> list = new ArrayList<WheatIndexVo>();
		try {
			List<WheatIndex> result = indexCalculator.calculateWheatIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY WHEAT " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.wheatIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<WheatIndexVo> annualList = new ArrayList<WheatIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateWheatPriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY WHEAT " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public WheatIndexVo calculateWheatPriceIndexAnnual(List<WheatIndexVo> monthly, int year) {
		WheatIndexVo vo = new WheatIndexVo();
		List<WheatIndexVo> annual = new ArrayList<WheatIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getWheatIGCRebased();
		vo.setWheatIGCRebased(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<CerealsIndexVo> calculateCerealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<CerealsIndexVo> list = new ArrayList<CerealsIndexVo>();
		try {
			List<CerealsIndex> result = indexCalculator.calculateCerealsIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println(result.size() + " CEREALS INDICES COMPUTED BY PERSISTENCE.");
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.cerealsIndex2CerealsIndexVo(result.get(i)));
			System.out.println(list.size() + " CEREALS INDICES CONVERTED TO VO.");
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<CerealsIndexVo> annualList = new ArrayList<CerealsIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateCerealsPriceIndexAnnual(list, i));
				return annualList;
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public CerealsIndexVo calculateCerealsPriceIndexAnnual(List<CerealsIndexVo> monthly, int year) {
		CerealsIndexVo vo = new CerealsIndexVo();
		List<CerealsIndexVo> annual = new ArrayList<CerealsIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getCerealsPriceIndex();
		vo.setCerealsPriceIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getCoarseGrainsPriceIndex();
		vo.setCoarseGrainsPriceIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getGrainsPriceIndex();
		vo.setGrainsPriceIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getRicePriceIndex();
		vo.setRicePriceIndex(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<OilsIndexVo> calculateOilsPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<OilsIndexVo> list = new ArrayList<OilsIndexVo>();
		try {
			List<OilIndex> result = indexCalculator.calculateOilIndex(startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY OIL " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.oilsIndex2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<OilsIndexVo> annualList = new ArrayList<OilsIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateOilsPriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY OIL " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public OilsIndexVo calculateOilsPriceIndexAnnual(List<OilsIndexVo> monthly, int year) {
		OilsIndexVo vo = new OilsIndexVo();
		List<OilsIndexVo> annual = new ArrayList<OilsIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getDate().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getOilsIndex();
		vo.setOilsIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getSoftOilsIndex();
		vo.setSoftOilsIndex(total / annual.size());
		total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getEdibleOilsIndex();
		vo.setEdibleOilsIndex(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setDate(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public List<SugarIndexVo> calculateSugarPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException {
		List<SugarIndexVo> list = new ArrayList<SugarIndexVo>();
		try {
			PeriodTypeCode periodTypeCode = PeriodTypeCode.valueOfIgnoreCase(timePeriod.name());
			List<SingleCommodityIndex> result = indexCalculator.calculateSingleCommodityIndex(FPIConst.SUGAR_HS_CODE, FPIConst.WORLD, FPIConst.SUGAR_SC, periodTypeCode, startBaseYear, endBaseYear, startYear, endYear);
			System.out.println("MONTHLY SUGAR " + result.size());
			for (int i = 0; i < result.size(); i++)
				list.add(FPIVoConverter.sugar2Vo(result.get(i)));
			if (timePeriod.equals(PeriodTypeCodeVo.annual)) {
				List<SugarIndexVo> annualList = new ArrayList<SugarIndexVo>();
				for (int i = startYear; i <= endYear; i++)
					annualList.add(calculateSugarPriceIndexAnnual(list, i));
				return annualList;
			}
			System.out.println("MONTHLY SUGAR " + list.size());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public SugarIndexVo calculateSugarPriceIndexAnnual(List<SugarIndexVo> monthly, int year) {
		SugarIndexVo vo = new SugarIndexVo();
		List<SugarIndexVo> annual = new ArrayList<SugarIndexVo>();
		for (int i = 0; i < monthly.size(); i++)
			if ((monthly.get(i).getReferencePeriod().getYear() + 1900) == year)
				annual.add(monthly.get(i));
		double total = 0;
		for (int i = 0; i < annual.size(); i++)
			total += annual.get(i).getIndex();
		vo.setIndex(total / annual.size());
		FieldParser parser = new FieldParser();
		vo.setReferencePeriod(parser.parseDate(year + "-" + 11 + "-" + 28));
		return vo;
	}

	public Map<String, Double> calculateCerealsIndexParameters(int startBaseYear, int endBaseYear) {
		CerealsIndexParameters parameters = IndexParametersCalculator.calculateCerealsIndexParameters(startBaseYear, endBaseYear);
		Map<String, Double> map = new HashMap<String, Double>();
		Method[] methods = parameters.getClass().getMethods();
		try {
			for (int i = 0; i < methods.length; i++) {
				String name = methods[i].getName();
				if (name.contains("get") && !name.contains("Class")) {
					Double value = (Double) methods[i].invoke(parameters, null);
					System.out.println(name + " = " + value);
					map.put(name, value);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return map;
	}

	public List<DataEntryVo> findAllPricesForDataEntry(String priceType, String commodityCode, String featureCode, int year) throws FenixGWTException {
		System.out.println(">>>>>>>>>> " + priceType + " | " + commodityCode + " | " + featureCode + " | " + year);
		List<DataEntryVo> list = new ArrayList<DataEntryVo>();
		try {
			if (!priceType.equals("Wholesale")) {
				// List<QuantitativeCoreContent> commodities = indexCalculator.findAllInternationalPrices(commodityCode, featureCode, PeriodTypeCode.monthly, year, year);
				List<QuantitativeCoreContent> commodities = new ArrayList<QuantitativeCoreContent>();
				for (int i = 0; i < commodities.size(); i++) {
					QuantitativeCoreContent com = commodities.get(i);
					DataEntryVo vo = new DataEntryVo();
					vo.setCommodityCode(com.getCommodityCode());
					vo.setDate(com.getDate());
					vo.setFeatureCode(com.getFeatureCode());
					vo.setPriceType(priceType);
					vo.setValue(com.getQuantity());
					list.add(vo);
				}
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public List<DataEntryVo> findAllTradesForDataEntry(String priceType, String commodityCode, String featureCode, int year) throws FenixGWTException {
		List<DataEntryVo> list = new ArrayList<DataEntryVo>();
		try {
//			List<QuantitativeCoreContent> commodities = indexCalculator.findAllTradeNumericCommodity(priceType, commodityCode, featureCode, PeriodTypeCode.monthly, year, year);
			List<QuantitativeCoreContent> commodities = new ArrayList<QuantitativeCoreContent>();
			for (int i = 0; i < commodities.size(); i++) {
				QuantitativeCoreContent com = commodities.get(i);
				DataEntryVo vo = new DataEntryVo();
				vo.setCommodityCode(com.getCommodityCode());
				vo.setDate(com.getDate());
				vo.setFeatureCode(com.getFeatureCode());
				vo.setPriceType(priceType);
				vo.setValue(com.getQuantity());
				list.add(vo);
			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getLocalizedMessage());
		}
		return list;
	}

	public List<FenixGaulVo> getContinents() {
		List<FenixGaul0> list = fenixGaulDao.findAllGaul0();
		List<String> continents = new ArrayList<String>();
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++)
			if (!continents.contains(list.get(i).getContinent())) {
				continents.add(list.get(i).getContinent());
				result.add(mapFanixGaul0(list.get(i), GAULCodeSelectorConstants.CONTINENT));
			}
		return result;
	}

	public List<FenixGaulVo> getRegionsByContinentName(String continent) {
		List<FenixGaul0> list = fenixGaulDao.findRegionByContinentName(continent);
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++)
			result.add(mapFanixGaul0(list.get(i), GAULCodeSelectorConstants.REGION));
		return result;
	}

	public List<FenixGaulVo> getNationsByRegionName(String region) {
		List<FenixGaul0> list = fenixGaulDao.findNationByRegionName(region);
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++)
			result.add(mapFanixGaul0(list.get(i), GAULCodeSelectorConstants.NATION));
		return result;
	}

	public List<FenixGaulVo> getProvencesByNationCode(long gaul0Code) {
		List<FenixGaul1> list = fenixGaulDao.findProvenceByNationCode(gaul0Code);
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++)
			result.add(mapFanixGaul1(list.get(i), GAULCodeSelectorConstants.PROVENCE));
		return result;
	}

	public List<FenixGaulVo> getCitiesByProvenceCode(long gaul1Code) {
		List<FenixGaul2> list = fenixGaulDao.findCityByProvenceCode(gaul1Code);
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++)
			result.add(mapFanixGaul2(list.get(i), GAULCodeSelectorConstants.CITY));
		return result;
	}

	public FenixGaulVo mapFanixGaul0(FenixGaul0 gaul, String level) {
		FenixGaulVo vo = new FenixGaulVo();
		vo.setCode(gaul.getCode());
		vo.setContinent(gaul.getContinent());
		vo.setName(gaul.getName());
		vo.setRegion(gaul.getRegion());
		vo.setLevel(level);
		return vo;
	}

	public FenixGaulVo mapFanixGaul1(FenixGaul1 gaul, String level) {
		FenixGaulVo vo = new FenixGaulVo();
		vo.setCode(gaul.getCode());
		vo.setContinent(gaul.getContinent());
		vo.setName(gaul.getName());
		vo.setRegion(gaul.getRegion());
		vo.setLevel(level);
		return vo;
	}

	public FenixGaulVo mapFanixGaul2(FenixGaul2 gaul, String level) {
		FenixGaulVo vo = new FenixGaulVo();
		vo.setCode(gaul.getCode());
		vo.setContinent(gaul.getContinent());
		vo.setName(gaul.getName());
		vo.setRegion(gaul.getRegion());
		vo.setLevel(level);
		return vo;
	}

	public List<HSVo> getLevel0HSCode() {
		List<HS> list = hsDao.findAllLevel0();
		List<HSVo> result = new ArrayList<HSVo>();
		for (int i = 0; i < list.size(); i++) {
			HS hs = list.get(i);
			result.add(mapHS((HS) list.get(i)));
		}
		return result;
	}

	public List<HSVo> getLevel1HSCode(String codeLevel0) {
		List<HS> list = hsDao.findAllLevel1(codeLevel0);
		List<HSVo> result = new ArrayList<HSVo>();
		for (int i = 0; i < list.size(); i++) {
			HS hs = list.get(i);
			result.add(mapHS((HS) list.get(i)));
		}
		return result;
	}

	public List<HSVo> getLevel2HSCode(String codeLevel1) {
		List<HS> list = hsDao.findAllLevel2(codeLevel1);
		List<HSVo> result = new ArrayList<HSVo>();
		for (int i = 0; i < list.size(); i++) {
			HS hs = list.get(i);
			result.add(mapHS((HS) list.get(i)));
		}
		return result;
	}

	public HSVo mapHS(HS hs) {
		HSVo vo = new HSVo();
		vo.setCode(hs.getCode());
		vo.setDescription(hs.getDescription());
		vo.setId(hs.getId());
		return vo;
	}

	// FIXME
	public List<String> findAllCurrencies() {
//		List<String> currencies = new ArrayList<String>();
//		List<MeasurementUnit> result = measurementUnitDao.findAllCurrencies();
//		for (int i = 0; i < result.size(); i++)
//			if (!currencies.contains(result.get(i).getSymbol()))
//				currencies.add(result.get(i).getSymbol());
//		return currencies;
		return new ArrayList<String>();
	}

	// FIXME
	public List<String> findAllMeasurementUnits() {
//		List<String> mus = new ArrayList<String>();
//		List<MeasurementUnit> result = measurementUnitDao.findAllVisibleNonCurrencies();
//		for (int i = 0; i < result.size(); i++)
//			if (!mus.contains(result.get(i).getSymbol()))
//				mus.add(result.get(i).getSymbol());
//		return sort(mus);
		return new ArrayList<String>();
	}

	public List<String> sort(List<String> list) {
		String[] x = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
			x[i] = list.get(i);
		String temp;
		int j = x.length - 1;
		while (j > 0) {
			for (int i = 0; i < j; i++) {
				if (x[i].compareTo(x[i + 1]) > 0) {
					temp = x[i];
					x[i] = x[i + 1];
					x[i + 1] = temp;
				}
			}
			j--;
		}
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < x.length; i++)
			result.add(x[i]);
		return result;
	}

	public List getAdm0ByRegionName(String name) {
		return new ArrayList();
	}

	public List getRegionByContinentName(String continent) {
		return new ArrayList();
	}

	public FpiGaulVo domainToVo(FenixGaulDao domain) {
		return new FpiGaulVo();
	}

	public void setFenixGaulDao(FenixGaulDao fenixGaulDao) {
		this.fenixGaulDao = fenixGaulDao;
	}

	public void setHsDao(HSDao hsDao) {
		this.hsDao = hsDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setIndexCalculator(IndexCalculator indexCalculator) {
		this.indexCalculator = indexCalculator;
	}

	public void setFpiExcel(FPIExcel fpiExcel) {
		this.fpiExcel = fpiExcel;
	}

}