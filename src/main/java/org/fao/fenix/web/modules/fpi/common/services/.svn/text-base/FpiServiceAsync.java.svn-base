package org.fao.fenix.web.modules.fpi.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.core.common.vo.HSVo;
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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FpiServiceAsync {

	public void getContinents(AsyncCallback<List<FenixGaulVo>> callback);

	public void getRegionsByContinentName(String continent, AsyncCallback<List<FenixGaulVo>> callback);

	public void getNationsByRegionName(String region, AsyncCallback<List<FenixGaulVo>> callback);

	public void getProvencesByNationCode(long gaul0Code, AsyncCallback<List<FenixGaulVo>> callback);

	public void getCitiesByProvenceCode(long gaul1Code, AsyncCallback<List<FenixGaulVo>> callback);

	public void getLevel0HSCode(AsyncCallback<List<HSVo>> callback);

	public void getLevel1HSCode(String codeLevel0, AsyncCallback<List<HSVo>> callback);

	public void getLevel2HSCode(String codeLevel1, AsyncCallback<List<HSVo>> callback);

	public void calculateCerealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<CerealsIndexVo>> callback);

	public void calculateRiceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<RiceIndexVo>> callback);

	public void populateFPI(AsyncCallback callback);

	public void calculateFoodPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<FoodPriceIndexVo>> callback);

	public void calculateMeatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<MeatIndexVo>> callback);

	public void calculateDairyPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<DairyIndexVo>> callback);

	public void calculateOilsPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<OilsIndexVo>> callback);

	public void calculateSugarPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<SugarIndexVo>> callback);

	public void findAllPricesForDataEntry(String priceType, String commodityCode, String featureCode, int year, AsyncCallback<List<DataEntryVo>> callback);

	public void findAllTradesForDataEntry(String priceType, String commodityCode, String featureCode, int year, AsyncCallback<List<DataEntryVo>> callback);
	
	public void populateExchangeRates(AsyncCallback callback);
	
	public void populateMeasurementUnits(AsyncCallback callback);
	
	public void findAllCurrencies(AsyncCallback callback);
	
	public void findAllMeasurementUnits(AsyncCallback<List<String>> callback);
	
	public void saveDailyData(List<SaveDataVo> list, String datasetType, String source, AsyncCallback<List<SaveDataVo>> callback);

	public void saveWeeklyData(List<SaveDataVo> list, String datasetType, String source, AsyncCallback<List<SaveDataVo>> callback);

	public void saveMonthlyData(List<SaveDataVo> list, String datasetType, String source, AsyncCallback<List<SaveDataVo>> callback);
	
	public void getAllMU(AsyncCallback<List<MeasurementUnitVo>> callback);
	
	public void updateMU(List<MeasurementUnitVo> list, AsyncCallback callback); 
	
	public void calculateBovinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<BovineIndexVo>> callback);
	
	public void calculateOvinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<OvineIndexVo>> callback);
	
	public void calculatePoultryPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<PoultryIndexVo>> callback);

	public void calculatePigPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<PigIndexVo>> callback);

	public void calculateWheatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<WheatIndexVo>> callback);
	
	public void calculateMealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<MealsIndexVo>> callback);
	
	public void calculateFeedIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<FeedIndexVo>> callback);
	
	public void calculateWheatIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod, AsyncCallback<List<WheatIndexVo>> callback) throws FenixGWTException;
	
	public void createExcel(String indexName, List<List<String>> table, AsyncCallback<String> callback);
	
}
