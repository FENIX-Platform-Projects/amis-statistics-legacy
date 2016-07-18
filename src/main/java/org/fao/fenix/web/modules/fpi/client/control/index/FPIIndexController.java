package org.fao.fenix.web.modules.fpi.client.control.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.fpi.client.view.index.FPIIndexContextMenu;
import org.fao.fenix.web.modules.fpi.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.fpi.client.view.utils.TableItemBuilder;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
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
import org.fao.fenix.web.modules.fpi.common.vo.PeriodTypeCodeVo;
import org.fao.fenix.web.modules.fpi.common.vo.PigIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.PoultryIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.RiceIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.SugarIndexVo;
import org.fao.fenix.web.modules.fpi.common.vo.WheatIndexVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.table.CellRenderer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FPIIndexController {

	private static HorizontalPanel gaulPanel;

	private static HorizontalPanel commodityPanel;

	private static HorizontalPanel priceTypePanel;

	private static HorizontalPanel startDatePanel;

	private static HorizontalPanel endDatePanel;

	private static HorizontalPanel startSubsetPanel;

	private static HorizontalPanel endSubsetPanel;

	private static HorizontalPanel indexPanel;

	private static HorizontalPanel subIndexPanel;

	private static HorizontalPanel baseQuantityPanel;

	private static HorizontalPanel timePeriodPanel;

	private static Map<String, Integer> columnSizeMap = new HashMap<String, Integer>();

	private static Map<String, String> birtIndexTypeMap = new HashMap<String, String>();
	
	private static Map<String, String[]> subIndexTypeMap;
	
	private static String gaul;
	private static String priceType;
	private static int startYear;
	private static int endYear;

	private static int startSubsetYear;
	private static int endSubsetYear;
	private static String indexType;
	private static String subIndexType;
	private static String commodityCode;
	private static double baseQuantity;
	private static String timePeriod;
	private static PeriodTypeCodeVo timePeriodCode;

	static {
		columnSizeMap.put(BabelFish.print().foodPriceIndex(), 7);
		columnSizeMap.put(BabelFish.print().meatPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().dairyPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().cerealsPriceIndex(), 4);
		columnSizeMap.put(BabelFish.print().ricePriceIndex(), 6);
		columnSizeMap.put(BabelFish.print().oilsPriceIndex(), 4);
		columnSizeMap.put(BabelFish.print().sugarPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().bovinePriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().ovinePriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().pigPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().poultryPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().wheatPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().coarseGrainsPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().edibleOilsPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().grainsPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().softOilsPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().aromaticIndex(), 2);
		columnSizeMap.put(BabelFish.print().japonicaIndex(), 2);
		columnSizeMap.put(BabelFish.print().lowQualityIndicaIndex(), 2);
		columnSizeMap.put(BabelFish.print().highQualityIndicaIndex(), 2);
		columnSizeMap.put(BabelFish.print().mealsPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().feedPriceIndex(), 2);
		columnSizeMap.put(BabelFish.print().wheatIndex(), 2);
		birtIndexTypeMap.put(BabelFish.print().foodPriceIndex(), "FOOD");
		birtIndexTypeMap.put(BabelFish.print().meatPriceIndex(), "MEAT");
		birtIndexTypeMap.put(BabelFish.print().dairyPriceIndex(), "DAIRY");
		birtIndexTypeMap.put(BabelFish.print().cerealsPriceIndex(), "CEREALS");
		birtIndexTypeMap.put(BabelFish.print().ricePriceIndex(), "RICE");
		birtIndexTypeMap.put(BabelFish.print().oilsPriceIndex(), "OILS");
		birtIndexTypeMap.put(BabelFish.print().sugarPriceIndex(), "SUGAR");
		birtIndexTypeMap.put(BabelFish.print().bovinePriceIndex(), "BOVINE");
		birtIndexTypeMap.put(BabelFish.print().ovinePriceIndex(), "OVINE");
		birtIndexTypeMap.put(BabelFish.print().pigPriceIndex(), "PIG");
		birtIndexTypeMap.put(BabelFish.print().poultryPriceIndex(), "POULTRY");
		birtIndexTypeMap.put(BabelFish.print().coarseGrainsPriceIndex(), "COARSEGRAINS");
		birtIndexTypeMap.put(BabelFish.print().edibleOilsPriceIndex(), "EDIBLEOILS");
		birtIndexTypeMap.put(BabelFish.print().grainsPriceIndex(), "GRAINS");
		birtIndexTypeMap.put(BabelFish.print().softOilsPriceIndex(), "SOFTOILS");
		birtIndexTypeMap.put(BabelFish.print().aromaticIndex(), "AROMATIC");
		birtIndexTypeMap.put(BabelFish.print().japonicaIndex(), "JAPONICA");
		birtIndexTypeMap.put(BabelFish.print().lowQualityIndicaIndex(), "LOWINDICA");
		birtIndexTypeMap.put(BabelFish.print().highQualityIndicaIndex(), "HIGHINDICA");
		birtIndexTypeMap.put(BabelFish.print().mealsPriceIndex(), "MEALS");
		birtIndexTypeMap.put(BabelFish.print().feedPriceIndex(), "FEED");
		birtIndexTypeMap.put(BabelFish.print().wheatIndex(), "WHEAT");
		subIndexTypeMap = new HashMap<String, String[]>();
		subIndexTypeMap.put(BabelFish.print().foodPriceIndex(), new String[]{BabelFish.print().foodPriceIndex(), BabelFish.print().cerealsPriceIndex(), BabelFish.print().meatPriceIndex(), BabelFish.print().oilsPriceIndex(), BabelFish.print().dairyPriceIndex(), BabelFish.print().sugarPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().cerealsPriceIndex(), new String[]{BabelFish.print().cerealsPriceIndex(), BabelFish.print().coarseGrainsPriceIndex(), BabelFish.print().grainsPriceIndex(), BabelFish.print().wheatIndex()});
		subIndexTypeMap.put(BabelFish.print().oilsPriceIndex(), new String[]{BabelFish.print().oilsPriceIndex(), BabelFish.print().oilsPriceIndex(), BabelFish.print().edibleOilsPriceIndex(), BabelFish.print().softOilsPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().meatPriceIndex(), new String[]{BabelFish.print().meatPriceIndex(), BabelFish.print().bovinePriceIndex(), BabelFish.print().ovinePriceIndex(), BabelFish.print().pigPriceIndex(), BabelFish.print().poultryPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().dairyPriceIndex(), new String[]{BabelFish.print().dairyPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().sugarPriceIndex(), new String[]{BabelFish.print().sugarPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().ricePriceIndex(), new String[]{BabelFish.print().ricePriceIndex(), BabelFish.print().aromaticIndex(), BabelFish.print().japonicaIndex(), BabelFish.print().lowQualityIndicaIndex(), BabelFish.print().highQualityIndicaIndex()});
		subIndexTypeMap.put(BabelFish.print().mealsPriceIndex(), new String[]{BabelFish.print().mealsPriceIndex()});
		subIndexTypeMap.put(BabelFish.print().feedPriceIndex(), new String[]{BabelFish.print().feedPriceIndex()});
	}
	
	public static ChangeListener showSubIndices(final VerticalPanel parametersPanel) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				retrieveParameters(parametersPanel);
				ListBox indexList = (ListBox) indexPanel.getData("parameterValue");
				ListBox subIndexList = (ListBox) subIndexPanel.getData("parameterValue");	
				String key = indexList.getItemText(indexList.getSelectedIndex());
				String[] subIndices = subIndexTypeMap.get(key);
				for (int i = subIndexList.getItemCount() - 1 ; i >= 0 ; i--)
					subIndexList.removeItem(i);
				for (int i = 0 ; i < subIndices.length ; i++)
					subIndexList.addItem(subIndices[i]);
			}
		};
	}

	public static SelectionListener<ButtonEvent> buildCalculateIndexSelectionListener(final VerticalPanel parametersPanel, final Table table) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				retrieveParameters(parametersPanel);
				if (timePeriod.equals(BabelFish.print().annual()))
					timePeriodCode = PeriodTypeCodeVo.annual;
				else if (timePeriod.equals(BabelFish.print().monthly()))
					timePeriodCode = PeriodTypeCodeVo.monthly;
				if (subIndexType.equals(BabelFish.print().cerealsPriceIndex()))
					calculateCerealsIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().ricePriceIndex()))
					calculateRiceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().foodPriceIndex()))
					calculateFoodPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().meatPriceIndex()))
					calculateMeatPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().dairyPriceIndex()))
					calculateDairyPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().oilsPriceIndex()))
					calculateOilsPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().sugarPriceIndex()))
					calculateSugarPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().bovinePriceIndex()))
					calculateBovinePriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().ovinePriceIndex()))
					calculateOvinePriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().pigPriceIndex()))
					calculatePigPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().poultryPriceIndex()))
					calculatePoultryPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().wheatPriceIndex()))
					calculateWheatPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().coarseGrainsPriceIndex()))
					calculateCoarseGrainsPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().grainsPriceIndex()))
					calculateGrainsPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().edibleOilsPriceIndex()))
					calculateEdibleOilsPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().softOilsPriceIndex()))
					calculateSoftOilsPriceIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().aromaticIndex()))
					calculateAromaticIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().japonicaIndex()))
					calculateJaponicaIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().lowQualityIndicaIndex()))
					calculateLowQualityIndicaIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().highQualityIndicaIndex()))
					calculateHighQualityIndicaIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().mealsPriceIndex()))
					calculateMealsIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().feedPriceIndex()))
					calculateFeedIndex(parametersPanel, table);
				else if (subIndexType.equals(BabelFish.print().wheatIndex()))
					calculateWheatIndex(parametersPanel, table);
			}
		};
	}

	public Table build() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		TableColumnModel cm = new TableColumnModel(columns);
		Table table = new Table(cm);
		table.setSelectionMode(Style.SelectionMode.MULTI);
		table.setContextMenu(new FPIIndexContextMenu().build(table));
		return table;
	}

	public TableColumn buildTableColumn(String columnHeader, CellRenderer<TableItem> renderer) {
		TableColumn column = new TableColumn(columnHeader, 155);
		column.setSortable(true);
		column.setResizable(true);
		column.setMinWidth(200);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setRenderer(renderer);
		return column;
	}

	public static CerealsIndexParametersVo createCerealsIndexParametersVo(VerticalPanel updatePanel) {
		CerealsIndexParametersVo vo = new CerealsIndexParametersVo();
		vo.setBarleyDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getBarleyDenominator")).getText()));
		vo.setBarleyFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getBarleyFixedAggregate")).getText()));
		vo.setBarleyFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getBarleyFixedWeight")).getText()));
		vo.setBarleyPriceBaseYearAverage(Double.parseDouble(((TextBox) updatePanel.getData("getBarleyFixedWeight")).getText()));
		vo.setCoarseGrainFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainFixedAggregate")).getText()));
		vo.setCoarseGrainsBarleyDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainsBarleyDenominator")).getText()));
		vo.setCoarseGrainsBarleyFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainsBarleyFixedWeight")).getText()));
		vo.setCoarseGrainsDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainsDenominator")).getText()));
		vo.setCoarseGrainsMaizeDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainsMaizeDenominator")).getText()));
		vo.setCoarseGrainsMaizeFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getCoarseGrainsMaizeDenominator")).getText()));
		vo.setGrainFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getGrainFixedAggregate")).getText()));
		vo.setGrainsDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getGrainFixedAggregate")).getText()));
		vo.setMaizeDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getGrainFixedAggregate")).getText()));
		vo.setMaizeFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getMaizeFixedAggregate")).getText()));
		vo.setMaizePriceBaseYearAverage(Double.parseDouble(((TextBox) updatePanel.getData("getMaizePriceBaseYearAverage")).getText()));
		vo.setMaizeFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getMaizeFixedWeight")).getText()));
		vo.setRiceDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getRiceDenominator")).getText()));
		vo.setRiceFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getRiceFixedAggregate")).getText()));
		vo.setRiceFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getRiceFixedWeight")).getText()));
		vo.setRicePriceBaseYearAverage(Double.parseDouble(((TextBox) updatePanel.getData("getRiceFixedWeight")).getText()));
		vo.setWheatDenominator(Double.parseDouble(((TextBox) updatePanel.getData("getRiceFixedWeight")).getText()));
		vo.setWheatFixedAggregate(Double.parseDouble(((TextBox) updatePanel.getData("getRiceFixedWeight")).getText()));
		vo.setWheatFixedWeight(Double.parseDouble(((TextBox) updatePanel.getData("getWheatFixedWeight")).getText()));
		vo.setWheatPriceBaseYearAverage(Double.parseDouble(((TextBox) updatePanel.getData("getWheatPriceBaseYearAverage")).getText()));
		return vo;
	}

	public static void calculateCerealsIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateCerealsIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<CerealsIndexVo>>() {
			public void onSuccess(List<CerealsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForCerealsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForCereals(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateCoarseGrainsPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateCerealsIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<CerealsIndexVo>>() {
			public void onSuccess(List<CerealsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForCoarseGrainsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForCoarseGrains(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateGrainsPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateCerealsIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<CerealsIndexVo>>() {
			public void onSuccess(List<CerealsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForGrainsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForGrains(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateRiceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateRiceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<RiceIndexVo>>() {
			public void onSuccess(List<RiceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForRicePriceIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForRicePrice(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateAromaticIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateRiceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<RiceIndexVo>>() {
			public void onSuccess(List<RiceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForAromaticIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForAromaticPrice(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateJaponicaIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateRiceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<RiceIndexVo>>() {
			public void onSuccess(List<RiceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForJaponicaIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForJaponicaPrice(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateWheatIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		try {
			FpiServiceEntry.getInstance().calculateWheatIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<WheatIndexVo>>() {
				public void onSuccess(List<WheatIndexVo> result) {
					TableHeadersBuilder.renameTableHeadersForWheatIndex(table);
					table.removeAll();
					for (int i = 0; i < result.size(); i++)
						table.add(TableItemBuilder.buildTableItemForWheatPrice(result.get(i)));
					loadingWindow.destroy();
				}

				public void onFailure(Throwable caught) {
					loadingWindow.destroy();
					FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}
	
	public static void calculateLowQualityIndicaIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateRiceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<RiceIndexVo>>() {
			public void onSuccess(List<RiceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForLowQualityIndicaIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForLowQualityIndica(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateHighQualityIndicaIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateRiceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<RiceIndexVo>>() {
			public void onSuccess(List<RiceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForHighQualityIndicaIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForHighQualityIndica(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateFoodPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateFoodPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<FoodPriceIndexVo>>() {
			public void onSuccess(List<FoodPriceIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForFoodPriceIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForFoodPrice(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateBovinePriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateBovinePriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<BovineIndexVo>>() {
			public void onSuccess(List<BovineIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForBovineIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++) {
					TableItem item = TableItemBuilder.buildTableItemForBovine(result.get(i));
					table.add(item);
				}
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculatePigPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculatePigPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<PigIndexVo>>() {
			public void onSuccess(List<PigIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForPigIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++) {
					TableItem item = TableItemBuilder.buildTableItemForPig(result.get(i));
					table.add(item);
				}
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculatePoultryPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculatePoultryPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<PoultryIndexVo>>() {
			public void onSuccess(List<PoultryIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForPoultryIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++) {
					TableItem item = TableItemBuilder.buildTableItemForPoultry(result.get(i));
					table.add(item);
				}
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateWheatPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateWheatPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<WheatIndexVo>>() {
			public void onSuccess(List<WheatIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForWheatIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++) {
					TableItem item = TableItemBuilder.buildTableItemForWheat(result.get(i));
					table.add(item);
				}
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateOvinePriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateOvinePriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<OvineIndexVo>>() {
			public void onSuccess(List<OvineIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForOvineIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForOvine(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateMeatPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateMeatPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<MeatIndexVo>>() {
			public void onSuccess(List<MeatIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForMeatIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForMeat(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateDairyPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateDairyPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<DairyIndexVo>>() {
			public void onSuccess(List<DairyIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForDairyIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForDairy(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateOilsPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateOilsPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<OilsIndexVo>>() {
			public void onSuccess(List<OilsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForOilsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForOils(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateEdibleOilsPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateOilsPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<OilsIndexVo>>() {
			public void onSuccess(List<OilsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForEdibleOilsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForEdibleOils(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateSoftOilsPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateOilsPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<OilsIndexVo>>() {
			public void onSuccess(List<OilsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForSoftOilsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForSoftOils(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void calculateSugarPriceIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateSugarPriceIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<SugarIndexVo>>() {
			public void onSuccess(List<SugarIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForSugarIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForSugar(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateMealsIndex(final VerticalPanel parametersPanel, final Table table) {
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		FpiServiceEntry.getInstance().calculateMealsIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<MealsIndexVo>>() {
			public void onSuccess(List<MealsIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForMealsIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForMeals(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void calculateFeedIndex(final VerticalPanel parametersPanel, final Table table) {
		
		final LoadingWindow loadingWindow = new LoadingWindow();
		loadingWindow.create(BabelFish.print().calculatingIndex());
		
		FpiServiceEntry.getInstance().calculateFeedIndex(startYear, endYear, startSubsetYear, endSubsetYear, timePeriodCode, new AsyncCallback<List<FeedIndexVo>>() {
			public void onSuccess(List<FeedIndexVo> result) {
				TableHeadersBuilder.renameTableHeadersForFeedIndex(table);
				table.removeAll();
				for (int i = 0; i < result.size(); i++)
					table.add(TableItemBuilder.buildTableItemForFeed(result.get(i)));
				loadingWindow.destroy();
			}

			public void onFailure(Throwable caught) {
				loadingWindow.destroy();
				FenixAlert.alert(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static SelectionListener<ButtonEvent> buildClearTableSelectionListener(final Table table) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				table.removeAll();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final Table table, final VerticalPanel parametersPanel, final String format) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				retrieveParameters(parametersPanel);
				Integer columns = columnSizeMap.get(subIndexType);
				List<List<String>> tableList = new ArrayList<List<String>>();
				List<String> columnHeaders = new ArrayList<String>();
				for (int i = 0; i < columns; i++)
					columnHeaders.add(table.getColumn(i).getText());
				tableList.add(columnHeaders);
				List<TableItem> items = table.getItems();
				for (int i = 0; i < items.size(); i++) {
					Object[] values = items.get(i).getValues();
					List<String> row = new ArrayList<String>();
					for (int j = 0; j < columns; j++) {
						if (j == 0) {
							String date = String.valueOf((1 + ((Date) values[j]).getMonth()) + "/" + (((Date) values[j]).getYear() + 1900));
							row.add(date);
						} else {
							// row.add(reducePrecision(values[j].toString()));
							row.add(values[j].toString());
						}
					}
					tableList.add(row);
				}
				final LoadingWindow loadingWindow = new LoadingWindow();
				loadingWindow.create();
				if (format.equalsIgnoreCase("xls")) {
					FpiServiceEntry.getInstance().createExcel(subIndexType, tableList, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							Window.open("../olapExcel/" + result, "_blank", "status=no");
							loadingWindow.destroy();
						}
						public void onFailure(Throwable caught) {
							FenixAlert.alert("RPC Failed", "exportChartAndTable");
							loadingWindow.destroy();
						}
					});
				} else {
					BirtServiceEntry.getInstance().exportChartAndTable(subIndexType, tableList, BabelFish.print().date(), subIndexType, birtIndexTypeMap.get(subIndexType), format, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							Window.open(result, "_blank", "status=no");
							loadingWindow.destroy();
						}
	
						public void onFailure(Throwable caught) {
							FenixAlert.alert("RPC Failed", "exportChartAndTable");
							loadingWindow.destroy();
						}
					});
				}
			}
		};
	}

	public static String reducePrecision(String value) {
		int index = 0;
		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) == '.') {
				index = i;
				break;
			}
		if (value.length() >= (index + 3))
			return value.substring(0, index + 3);
		else
			return value;
	}

	private static void retrieveParameters(VerticalPanel parametersPanel) {
		gaulPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().gaulCode());
		commodityPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().commodityLabel());
		priceTypePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().priceTypeLabel());
		startDatePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().basePriceStartYear());
		endDatePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().basePriceEndYear());
		startSubsetPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().indexStartYear());
		endSubsetPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().indexEndYear());
		indexPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().indexType());
		subIndexPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().subIndexType());
		// baseQuantityPanel = (HorizontalPanel) parametersPanel.getData(I18N.print().baseQuantity());
		timePeriodPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().timePeriod());
		gaul = gaulPanel.getData("gaulCode").toString();
		priceType = ((ListBox) priceTypePanel.getData("parameterValue")).getItemText(((ListBox) priceTypePanel.getData("parameterValue")).getSelectedIndex());
		startYear = Integer.parseInt(((ListBox) startDatePanel.getData("parameterValue")).getItemText(((ListBox) startDatePanel.getData("parameterValue")).getSelectedIndex()));
		endYear = Integer.parseInt(((ListBox) endDatePanel.getData("parameterValue")).getItemText(((ListBox) endDatePanel.getData("parameterValue")).getSelectedIndex()));
		startSubsetYear = Integer.parseInt(((ListBox) startSubsetPanel.getData("parameterValue")).getItemText(((ListBox) startSubsetPanel.getData("parameterValue")).getSelectedIndex()));
		endSubsetYear = Integer.parseInt(((ListBox) endSubsetPanel.getData("parameterValue")).getItemText(((ListBox) endSubsetPanel.getData("parameterValue")).getSelectedIndex()));
		indexType = ((ListBox) indexPanel.getData("parameterValue")).getItemText(((ListBox) indexPanel.getData("parameterValue")).getSelectedIndex());
		subIndexType = ((ListBox) subIndexPanel.getData("parameterValue")).getItemText(((ListBox) subIndexPanel.getData("parameterValue")).getSelectedIndex());
		// commodityCode = commodityPanel.getData("hsCode").toString();
		// baseQuantity = Double.parseDouble(((TextBox) baseQuantityPanel.getData("parameterValue")).getText());
		timePeriod = ((ListBox) timePeriodPanel.getData("parameterValue")).getItemText(((ListBox) timePeriodPanel.getData("parameterValue")).getSelectedIndex());
		timePeriodCode = PeriodTypeCodeVo.valueOfIgnoreCase(timePeriod);
	}

}