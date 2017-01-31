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
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface FpiService extends RemoteService {

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixGaulVo> getContinents();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixGaulVo> getRegionsByContinentName(String continent);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixGaulVo> getNationsByRegionName(String region);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixGaulVo> getProvencesByNationCode(long gaul0Code);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixGaulVo> getCitiesByProvenceCode(long gaul1Code);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<HSVo> getLevel0HSCode();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<HSVo> getLevel1HSCode(String codeLevel0);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<HSVo> getLevel2HSCode(String codeLevel1);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CerealsIndexVo> calculateCerealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void populateFPI();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FoodPriceIndexVo> calculateFoodPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<WheatIndexVo> calculateWheatIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<MeatIndexVo> calculateMeatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<RiceIndexVo> calculateRiceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DairyIndexVo> calculateDairyPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<OilsIndexVo> calculateOilsPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<SugarIndexVo> calculateSugarPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DataEntryVo> findAllPricesForDataEntry(String priceType, String commodityCode, String featureCode, int year) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<DataEntryVo> findAllTradesForDataEntry(String priceType, String commodityCode, String featureCode, int year) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void populateExchangeRates();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void populateMeasurementUnits();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> findAllCurrencies();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> findAllMeasurementUnits();

	@Secured( { "ROLE_FPI" })
	public void saveDailyData(List<SaveDataVo> list, String datasetType, String source);

	@Secured( { "ROLE_FPI" })
	public void saveWeeklyData(List<SaveDataVo> list, String datasetType, String source);

	@Secured( { "ROLE_FPI" })
	public void saveMonthlyData(List<SaveDataVo> list, String datasetType, String source);

	@Secured( { "ROLE_ADMIN" })
	public List<MeasurementUnitVo> getAllMU();

	@Secured( { "ROLE_ADMIN" })
	public void updateMU(List<MeasurementUnitVo> list);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<BovineIndexVo> calculateBovinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<OvineIndexVo> calculateOvinePriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<PoultryIndexVo> calculatePoultryPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<PigIndexVo> calculatePigPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<WheatIndexVo> calculateWheatPriceIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<MealsIndexVo> calculateMealsIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FeedIndexVo> calculateFeedIndex(int startBaseYear, int endBaseYear, int startYear, int endYear, PeriodTypeCodeVo timePeriod) throws FenixGWTException;
	
	public String createExcel(String indexName, List<List<String>> table);
}
