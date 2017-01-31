package org.fao.fenix.web.modules.fpi.server.utils;

import org.fao.fenix.core.domain.index.BovineIndex;
import org.fao.fenix.core.domain.index.CerealsIndex;
import org.fao.fenix.core.domain.index.DairyIndex;
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
import org.fao.fenix.web.modules.fpi.common.vo.BovineIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.CerealsIndexParametersVo;
import org.fao.fenix.web.modules.fpi.common.vo.CerealsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.DairyIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.FeedIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.FoodPriceIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.MealsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.MeatIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.OilsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.OvineIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.PigIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.PoultryIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.RiceIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.SugarIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.WheatIndexVo;

public class FPIVoConverter {

	public static FoodPriceIndexVo foodPriceIndex2Vo(FoodPriceIndex index) {
		FoodPriceIndexVo vo = new FoodPriceIndexVo();
		vo.setCerealsIndex(index.getCerealsIndex());
		vo.setDairyIndex(index.getDairyIndex());
		vo.setDate(index.getDate());
		vo.setFoodPriceIndex(index.getFoodPriceIndex());
		vo.setMeatIndex(index.getMeatIndex());
		vo.setOilsIndex(index.getOilsIndex());
		vo.setSugarIndex(index.getSugarIndex());
		return vo;
	}
	
	public static OilsIndexVo oilsIndex2Vo(OilIndex index) {
		OilsIndexVo vo = new OilsIndexVo();
		vo.setOilsIndex(index.getOilsIndex());
		vo.setDate(index.getDate());
		vo.setSoftOilsIndex(index.getSoftOilsIndex());
		vo.setEdibleOilsIndex(index.getEdibleOilsIndex());
		return vo;
	}
	
	public static SugarIndexVo sugar2Vo(SingleCommodityIndex index) {
		SugarIndexVo vo = new SugarIndexVo();
		vo.setIndex(index.getIndex());
		vo.setReferencePeriod(index.getReferencePeriod());
		return vo;
	}
	
	public static MeatIndexVo meatIndex2Vo(MeatIndex index) {
		MeatIndexVo vo = new MeatIndexVo();
		vo.setDate(index.getDate());
		vo.setMeatPriceIndex(index.getMeatPriceIndex());
		return vo;
	}
	
	public static MealsIndexVo mealsIndex2Vo(MealIndex index) {
		MealsIndexVo vo = new MealsIndexVo();
		vo.setDate(index.getDate());
		vo.setIndex(index.getMealsIndex());
		return vo;
	}
	
	public static FeedIndexVo feedIndex2Vo(FeedIndex index) {
		FeedIndexVo vo = new FeedIndexVo();
		vo.setDate(index.getDate());
		vo.setIndex(index.getFeedIndex());
		return vo;
	}
	
	public static BovineIndexVo bovineIndex2Vo(BovineIndex index) {
		BovineIndexVo vo = new BovineIndexVo();
		vo.setDate(index.getDate());
		vo.setBovineWeightedPrice(index.getBovineWeightedPrice());
		return vo;
	}
	
	public static PigIndexVo pigIndex2Vo(PigIndex index) {
		PigIndexVo vo = new PigIndexVo();
		vo.setDate(index.getDate());
		vo.setPigWeightedPrice(index.getPigWeightedPrice());
		return vo;
	}
	
	public static PoultryIndexVo poultryIndex2Vo(PoultryIndex index) {
		PoultryIndexVo vo = new PoultryIndexVo();
		vo.setDate(index.getDate());
		vo.setPoultryWeightedPrice(index.getPoultryWeightedPrice());
		return vo;
	}
	
	public static OvineIndexVo ovineIndex2Vo(OvineIndex index) {
		OvineIndexVo vo = new OvineIndexVo();
		vo.setDate(index.getDate());
		vo.setOvineWeightedPrice(index.getOvineWeightedPrice());
		return vo;
	}
	
	public static RiceIndexVo riceIndex2Vo(RiceIndex index) {
		RiceIndexVo vo = new RiceIndexVo();
		vo.setAromaticIndex(index.getAromaticIndex());
		vo.setDate(index.getDate());
		vo.setHighQualityIndicaIndex(index.getHighQualityIndicaIndex());
		vo.setJaponicaIndex(index.getJaponicaIndex());
		vo.setLowQualityIndicaIndex(index.getLowQualityIndicaIndex());
		vo.setRiceIndex(index.getRiceIndex());
		return vo;
	}
	
	public static DairyIndexVo dairyIndex2Vo(DairyIndex index) {
		DairyIndexVo vo = new DairyIndexVo();
		vo.setDate(index.getDate());
		vo.setDairyPriceIndex(index.getDairyPriceIndex());
		return vo;
	}
	
	public static WheatIndexVo wheatIndex2Vo(WheatIndex index) {
		WheatIndexVo vo = new WheatIndexVo();
		vo.setDate(index.getDate());
		vo.setWheatIGCRebased(index.getWheatIGCRebased());
		return vo;
	}
	
	public static CerealsIndexVo cerealsIndex2CerealsIndexVo(CerealsIndex index) {
		CerealsIndexVo vo = new CerealsIndexVo();
		vo.setBarleyPriceMonthlyWeighted(index.getBarleyPriceMonthlyWeighted());
		vo.setCerealsPriceIndex(index.getCerealsPriceIndex());
		vo.setCoarseGrainsPriceIndex(index.getCoarseGrainsPriceIndex());
		vo.setGrainsPriceIndex(index.getGrainsPriceIndex());
		vo.setMaizePriceMonthlyWeighted(index.getMaizePriceMonthlyWeighted());
		vo.setDate(index.getDate());
		vo.setRicePriceMonthlyWeighted(index.getRicePriceMonthlyWeighted());
		vo.setWheatPriceMonthlyWeighted(index.getWheatPriceMonthlyWeighted());
		vo.setWorldExportWeightsCoarseGrainsBarley(index.getWorldExportWeightsCoarseGrainsBarley());
		vo.setWorldExportWeightsCoarseGrainsMaize(index.getWorldExportWeightsCoarseGrainsMaize());
		vo.setWorldExportWeightsGrainsTotal(index.getWorldExportWeightsGrainsTotal());
		vo.setRicePriceIndex(index.getRicePriceIndex());
		return vo;
	}
	
	public static CerealsIndexParametersVo cerealsIndexParameters2CerealsIndexParametersVo(CerealsIndexParameters parameters) {
		CerealsIndexParametersVo vo = new CerealsIndexParametersVo();
		vo.setBarleyDenominator(parameters.getBarleyDenominator());
		vo.setBarleyFixedAggregate(parameters.getBarleyFixedAggregate());
		vo.setBarleyFixedWeight(parameters.getBarleyFixedWeight());
		vo.setBarleyPriceBaseYearAverage(parameters.getBarleyPriceBaseYearAverage());
		vo.setCoarseGrainFixedAggregate(parameters.getCoarseGrainFixedAggregate());
		vo.setCoarseGrainsBarleyDenominator(parameters.getCoarseGrainsBarleyDenominator());
		vo.setCoarseGrainsBarleyFixedWeight(parameters.getCoarseGrainsBarleyFixedWeight());
		vo.setCoarseGrainsDenominator(parameters.getCoarseGrainsDenominator());
		vo.setCoarseGrainsMaizeDenominator(parameters.getCoarseGrainsMaizeDenominator());
		vo.setCoarseGrainsMaizeFixedWeight(parameters.getCoarseGrainsMaizeFixedWeight());
		vo.setGrainFixedAggregate(parameters.getGrainFixedAggregate());
		vo.setGrainsDenominator(parameters.getGrainsDenominator());
		vo.setMaizeDenominator(parameters.getMaizeDenominator());
		vo.setMaizeFixedAggregate(parameters.getMaizeFixedAggregate());
		vo.setMaizePriceBaseYearAverage(parameters.getMaizePriceBaseYearAverage());
		vo.setMaizeFixedWeight(parameters.getMaizeFixedWeight());
		vo.setRiceDenominator(parameters.getRiceDenominator());
		vo.setRiceFixedAggregate(parameters.getRiceFixedAggregate());
		vo.setRiceFixedWeight(parameters.getRiceFixedWeight());
		vo.setRicePriceBaseYearAverage(parameters.getRicePriceBaseYearAverage());
		vo.setWheatDenominator(parameters.getWheatDenominator());
		vo.setWheatFixedAggregate(parameters.getWheatFixedAggregate());
		vo.setWheatFixedWeight(parameters.getWheatFixedWeight());
		vo.setWheatPriceBaseYearAverage(parameters.getWheatPriceBaseYearAverage());
		return vo;
	}
	
	public static CerealsIndexParameters cerealsIndexParametersVo2CerealsIndexParameters(CerealsIndexParametersVo parameters) {
		CerealsIndexParameters vo = new CerealsIndexParameters();
		vo.setBarleyDenominator(parameters.getBarleyDenominator());
		vo.setBarleyFixedAggregate(parameters.getBarleyFixedAggregate());
		vo.setBarleyFixedWeight(parameters.getBarleyFixedWeight());
		vo.setBarleyPriceBaseYearAverage(parameters.getBarleyPriceBaseYearAverage());
		vo.setCoarseGrainFixedAggregate(parameters.getCoarseGrainFixedAggregate());
		vo.setCoarseGrainsBarleyDenominator(parameters.getCoarseGrainsBarleyDenominator());
		vo.setCoarseGrainsBarleyFixedWeight(parameters.getCoarseGrainsBarleyFixedWeight());
		vo.setCoarseGrainsDenominator(parameters.getCoarseGrainsDenominator());
		vo.setCoarseGrainsMaizeDenominator(parameters.getCoarseGrainsMaizeDenominator());
		vo.setCoarseGrainsMaizeFixedWeight(parameters.getCoarseGrainsMaizeFixedWeight());
		vo.setGrainFixedAggregate(parameters.getGrainFixedAggregate());
		vo.setGrainsDenominator(parameters.getGrainsDenominator());
		vo.setMaizeDenominator(parameters.getMaizeDenominator());
		vo.setMaizeFixedAggregate(parameters.getMaizeFixedAggregate());
		vo.setMaizePriceBaseYearAverage(parameters.getMaizePriceBaseYearAverage());
		vo.setMaizeFixedWeight(parameters.getMaizeFixedWeight());
		vo.setRiceDenominator(parameters.getRiceDenominator());
		vo.setRiceFixedAggregate(parameters.getRiceFixedAggregate());
		vo.setRiceFixedWeight(parameters.getRiceFixedWeight());
		vo.setRicePriceBaseYearAverage(parameters.getRicePriceBaseYearAverage());
		vo.setWheatDenominator(parameters.getWheatDenominator());
		vo.setWheatFixedAggregate(parameters.getWheatFixedAggregate());
		vo.setWheatFixedWeight(parameters.getWheatFixedWeight());
		vo.setWheatPriceBaseYearAverage(parameters.getWheatPriceBaseYearAverage());
		return vo;
	}
	
}
