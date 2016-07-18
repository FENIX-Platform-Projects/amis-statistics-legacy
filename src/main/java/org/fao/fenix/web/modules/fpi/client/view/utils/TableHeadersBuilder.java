package org.fao.fenix.web.modules.fpi.client.view.utils;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.table.CellRenderer;
import com.extjs.gxt.ui.client.widget.table.DateTimeCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.i18n.client.NumberFormat;

public class TableHeadersBuilder {

	public static DateTimeCellRenderer<TableItem> buildDateTimeCellRenderer(String format) {
		return new DateTimeCellRenderer<TableItem>(format);
	}

	public static NumberCellRenderer<TableItem> buildNumberCellRenderer(NumberFormat currency) {
		return new NumberCellRenderer<TableItem>(currency);
	}
	
	public static CellRenderer<TableItem> buildCellRenderer(final NumberFormat number) {
		return new CellRenderer<TableItem>() {
			public String render(TableItem item, String property, Object value) {
				double val = (Double) value;
				String style = val < 100 ? "green" : "red";
				return "<span style='color:" + style + "'>" + number.format(val) + "</span>";
			}
		};
	}

	public static CellRenderer<TableItem> buildCellRenderer() {
		return new CellRenderer<TableItem>() {
			public String render(TableItem item, String property, Object value) {
				return "<b>" + ((Double) value) + "</b>";
			}
		};
	}
	
	public static void renameTableHeadersForFoodPriceIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().foodPriceIndex());
		table.getColumn(2).setText(BabelFish.print().meatPriceIndex());
		table.getColumn(3).setText(BabelFish.print().dairyPriceIndex());
		table.getColumn(4).setText(BabelFish.print().cerealsPriceIndex());
		table.getColumn(5).setText(BabelFish.print().oilsPriceIndex());
		table.getColumn(6).setText(BabelFish.print().sugarPriceIndex());
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(3).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(4).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(5).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(6).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
	}
	
	public static void renameTableHeadersForRicePriceIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().ricePriceIndex());
		table.getColumn(2).setText(BabelFish.print().lowQualityIndicaIndex());
		table.getColumn(3).setText(BabelFish.print().highQualityIndicaIndex());
		table.getColumn(4).setText(BabelFish.print().japonicaIndex());
		table.getColumn(5).setText(BabelFish.print().aromaticIndex());
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(3).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(4).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(5).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
	}
	
	public static void renameTableHeadersForAromaticIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().aromaticIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
	}
	
	public static void renameTableHeadersForJaponicaIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().japonicaIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
	}
	
	public static void renameTableHeadersForLowQualityIndicaIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().lowQualityIndicaIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
	}
	
	public static void renameTableHeadersForHighQualityIndicaIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().highQualityIndicaIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
	}

	public static void renameTableHeadersForCerealsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().cerealsPriceIndex());
		table.getColumn(2).setText(BabelFish.print().grainsPriceIndex());
		table.getColumn(3).setText(BabelFish.print().coarseGrainsPriceIndex());
		table.getColumn(4).setText(BabelFish.print().ricePriceIndex());
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(3).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(4).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForCoarseGrainsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().coarseGrainsPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForGrainsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().grainsPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}

	public static void renameTableHeadersForOilsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().oilsPriceIndex());
		table.getColumn(2).setText(BabelFish.print().softOilsPriceIndex());
		table.getColumn(3).setText(BabelFish.print().edibleOilsPriceIndex());
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(3).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForEdibleOilsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().edibleOilsPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForSoftOilsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().softOilsPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}

	public static void renameTableHeadersForMeatIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().meatPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForBovineIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().bovinePriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForPigIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().pigPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForPoultryIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().poultryPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForWheatIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().wheatIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForOvineIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().ovinePriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}

	public static void renameTableHeadersForDairyIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().dairyPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}

	public static void renameTableHeadersForSugarIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().sugarPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForMealsIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().mealsPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
	public static void renameTableHeadersForFeedIndex(Table table) {
		table.getColumn(0).setText(BabelFish.print().date());
		table.getColumn(1).setText(BabelFish.print().feedPriceIndex());
		table.getColumn(2).setText("");
		table.getColumn(3).setText("");
		table.getColumn(4).setText("");
		table.getColumn(5).setText("");
		table.getColumn(6).setText("");
		table.getColumn(0).setRenderer(buildDateTimeCellRenderer("MM-y"));
		table.getColumn(1).setRenderer(buildCellRenderer(NumberFormat.getFormat("0.0000")));
		table.getColumn(2).setRenderer(null);
		table.getColumn(3).setRenderer(null);
		table.getColumn(4).setRenderer(null);
		table.getColumn(5).setRenderer(null);
		table.getColumn(6).setRenderer(null);
	}
	
}
