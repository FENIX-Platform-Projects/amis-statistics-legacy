package org.fao.fenix.web.modules.fpi.client.view.utils;

import java.util.Date;

import org.fao.fenix.web.modules.fpi.common.vo.BovineIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.CerealsIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.DairyIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.EconomicsIndexVo;
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

import com.extjs.gxt.ui.client.widget.table.TableItem;

public class TableItemBuilder {

	public static TableItem buildTableItem(EconomicsIndexVo value) {
		Object[] values = new Object[4];
		Date tableDate = value.getDate();
		// tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getBasePrice();
		values[2] = value.getPrice();
		values[3] = value.getIndex();
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForCereals(CerealsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		// tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getCerealsPriceIndex();
		values[2] = value.getGrainsPriceIndex();
		values[3] = value.getCoarseGrainsPriceIndex();
		values[4] = value.getRicePriceIndex();
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForCoarseGrains(CerealsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getCoarseGrainsPriceIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForGrains(CerealsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getGrainsPriceIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForMeat(MeatIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		// tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getMeatPriceIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForBovine(BovineIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getBovineWeightedPrice();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForPig(PigIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getPigWeightedPrice();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForPoultry(PoultryIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getPoultryWeightedPrice();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForWheat(WheatIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getWheatIGCRebased();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForOvine(OvineIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getOvineWeightedPrice();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForDairy(DairyIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		// tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getDairyPriceIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForOils(OilsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		// tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getOilsIndex();
		values[2] = value.getSoftOilsIndex();
		values[3] = value.getEdibleOilsIndex();
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForEdibleOils(OilsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getEdibleOilsIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForSoftOils(OilsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getSoftOilsIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForSugar(SugarIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getReferencePeriod();
		//tableDate.setYear(value.getReferencePeriod().getYear() - 1900);
		tableDate.setYear(value.getReferencePeriod().getYear());
		values[0] = value.getReferencePeriod();
		values[1] = value.getIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}

	public static TableItem buildTableItemForFoodPrice(FoodPriceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		//tableDate.setYear(value.getDate().getYear() - 1900);
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getFoodPriceIndex();
		values[2] = value.getMeatIndex();
		values[3] = value.getDairyIndex();
		values[4] = value.getCerealsIndex();
		values[5] = value.getOilsIndex();
		values[6] = value.getSugarIndex();
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForRicePrice(RiceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getRiceIndex();
		values[2] = value.getLowQualityIndicaIndex();
		values[3] = value.getHighQualityIndicaIndex();
		values[4] = value.getJaponicaIndex();
		values[5] = value.getAromaticIndex();
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForAromaticPrice(RiceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getAromaticIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForJaponicaPrice(RiceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getJaponicaIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForWheatPrice(WheatIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getWheatIGCRebased();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForLowQualityIndica(RiceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getLowQualityIndicaIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForHighQualityIndica(RiceIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getHighQualityIndicaIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForMeals(MealsIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemForFeed(FeedIndexVo value) {
		Object[] values = new Object[7];
		Date tableDate = value.getDate();
		tableDate.setYear(value.getDate().getYear());
		values[0] = tableDate;
		values[1] = value.getIndex();
		values[2] = "";
		values[3] = "";
		values[4] = "";
		values[5] = "";
		values[6] = "";
		TableItem item = new TableItem(values);
		return item;
	}
	
}
