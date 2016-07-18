package org.fao.fenix.web.modules.chartdesigner.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartTypeModelData;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelTabItem;
import org.fao.fenix.web.modules.chartdesigner.common.services.ChartDesignerServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerFilterVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerHighlightVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerYAxisParametersVO;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class ChartDesignerOpener extends ChartDesignerController {

	public static void loadParameters(ChartDesignerWindow w, List<ChartDesignerParametersVO> ps, boolean ola2chart) {
		for (ChartDesignerParametersVO p : ps)
			loadParameters(w, p, ola2chart);
	}
	
	private static void loadParameters(ChartDesignerWindow w, ChartDesignerParametersVO p, boolean ola2chart) {
		for (Long id : p.getDatasourceIDs()) {
			w.getChartDesignerPanel().getDatasourcePanel().getModels().clear();
			ResourceChildModel m = new ResourceChildModel();
			m.setId(String.valueOf(id));
			w.getChartDesignerPanel().getDatasourcePanel().getModels().add(m);
			loadGeneralSettings(w, p);
			loadR(w, p);
			loadDatasetAndSettingsAgent(w, new ChartDesignerDatasourceFieldSet(p.getChartType()), p, ola2chart);
		}
	}
	
	private static void loadR(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		ListBox l = w.getChartDesignerPanel().getrPanel().getAggregationFunctionListBox();
		for (int i = 0 ; i < l.getItemCount() ; i++)
			if (l.getValue(i).equalsIgnoreCase(p.getAggregationFunction()))
				l.setItemSelected(i, true);
	}
	
	private static void loadFilter(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		for (Long datasetID : p.getDatasourceIDs()) {
			for (ChartDesignerFilterVO f : p.getFilters()) {
				if (f.getDatasetID().longValue() == datasetID.longValue()) {
					for (ListBox l : w.getChartDesignerPanel().getPlotDimensionPanel().getContentDescriptorMap().keySet()) {
						if (w.getChartDesignerPanel().getPlotDimensionPanel().getContentDescriptorMap().get(l).equalsIgnoreCase(f.getContentDescriptor())) {
							for (int i = 0 ; i < l.getItemCount() ; i++) {
								if (f.getCodes().contains(l.getValue(i)))
									l.setItemSelected(i, true);
							}
						}
					}
				}
			}
		}
	}
	
	private static void loadAxesSettings(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		for (ChartDesignerYAxisParametersVO yap : p.getyAxes()) {
			LookAndFeelTabItem ti = w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(yap.getDatasetID());
			loadGeneralSettings(ti, yap);
		}
	}
	
	private static void loadGeneralSettings(LookAndFeelTabItem ti, ChartDesignerYAxisParametersVO p) {
		ti.getAxisColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getAxisColor() + "; color: " + p.getAxisColor() + "; font-weight: bold; font-style: italic;'>" + p.getAxisColor() + "</div>");
		ti.getAxisNumberOfIntervals().setValue(String.valueOf(p.getStep()));
		ti.getAxisTicksColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getTicksColor() + "; color: " + p.getTicksColor() + "; font-weight: bold; font-style: italic;'>" + p.getTicksColor() + "</div>");
		ti.getAxisTitle().setValue(p.getAxisTitle());
		ti.getValuesColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getValuesFontColor() + "; color: " + p.getValuesFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getValuesFontColor() + "</div>");
		for (int i = 0 ; i < ti.getValuesFontSize().getItemCount() ; i++)
			if (ti.getValuesFontSize().getValue(i).equalsIgnoreCase(p.getValuesFontSize()))
				ti.getValuesFontSize().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getValuesFontFamily().getItemCount() ; i++)
			if (ti.getValuesFontFamily().getValue(i).equalsIgnoreCase(p.getValuesFontStyle()))
				ti.getValuesFontFamily().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getLabelInclination().getItemCount() ; i++)
			if (ti.getLabelInclination().getValue(i).equalsIgnoreCase(String.valueOf(p.getLabelOrientation())))
				ti.getLabelInclination().setItemSelected(i, true);
		ti.getHasYGrid().setValue(p.isyGrid());
		ti.getyGridColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getyGridColor() + "; color: " + p.getyGridColor() + "; font-weight: bold; font-style: italic;'>" + p.getyGridColor() + "</div>");
		for (int i = 0 ; i < ti.getyGridLineType().getItemCount() ; i++)
			if (ti.getyGridLineType().getValue(i).equalsIgnoreCase(p.getyGridLineStyle()))
				ti.getyGridLineType().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getAxisLineType().getItemCount() ; i++)
			if (ti.getAxisLineType().getValue(i).equalsIgnoreCase(p.getLineType()))
				ti.getAxisLineType().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getAxisLineWidth().getItemCount() ; i++)
			if (ti.getAxisLineWidth().getValue(i).equalsIgnoreCase(p.getLineWidth()))
				ti.getAxisLineWidth().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getAxisPosition().getItemCount() ; i++)
			if (ti.getAxisPosition().getValue(i).equalsIgnoreCase(p.getAxisPosition()))
				ti.getAxisPosition().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getPointType().getItemCount() ; i++)
			if (ti.getPointType().getValue(i).equalsIgnoreCase(p.getPointType()))
				ti.getPointType().setItemSelected(i, true);
		ti.addSeriesColorAgent(p.getSeriesColorsMap());
		ti.getAxisFromValue().setValue(String.valueOf(p.getyMin()));
		ti.getAxisToValue().setValue(String.valueOf(p.getyMax()));
		for (int i = 0 ; i < ti.getAxisTitleFontFamily().getItemCount() ; i++)
			if (ti.getAxisTitleFontFamily().getValue(i).equalsIgnoreCase(p.getFontStyle()))
				ti.getAxisTitleFontFamily().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getAxisTitleFontSize().getItemCount() ; i++)
			if (ti.getAxisTitleFontSize().getValue(i).equalsIgnoreCase(p.getFontSize()))
				ti.getAxisTitleFontSize().setItemSelected(i, true);
		ti.getAxisTitleFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getFontColor() + "; color: " + p.getFontColor() + "; font-weight: bold; font-style: italic;'>" + p.getFontColor() + "</div>");
		ti.getyAxisEquidistantDates().setValue(p.isyAxisEquidistantDates());
		ti.getStacked().setValue(p.isStacked());
	}
	
	private static void loadGeneralSettings(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		LookAndFeelTabItem ti = w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings();
		ti.getxAxisColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getxAxisColor() + "; color: " + p.getxAxisColor() + "; font-weight: bold; font-style: italic;'>" + p.getxAxisColor() + "</div>");
		ti.getxAxisNumberOfIntervals().setValue(String.valueOf(p.getxAxisNumberOfIntervals()));
		ti.getxAxisTicksColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getxAxisTicksColor() + "; color: " + p.getxAxisTicksColor() + "; font-weight: bold; font-style: italic;'>" + p.getxAxisTicksColor() + "</div>");
		ti.getxAxisTitle().setValue(p.getxLabel());
		ti.getTitle().setValue(p.getTitleLabel());
//		if ((p.getTitleLabel() != null) && (!p.getTitleLabel().equals("null")))
//			w.getWindow().setHeading("Chart Designer - " + p.getTitleLabel());
		ti.getTitleFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getTitleColor() + "; color: " + p.getTitleColor() + "; font-weight: bold; font-style: italic;'>" + p.getTitleColor() + "</div>");
		for (int i = 0 ; i < ti.getTitleFontSize().getItemCount() ; i++)
			if (ti.getTitleFontSize().getValue(i).equalsIgnoreCase(p.getTitleSize()))
				ti.getTitleFontSize().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getTitleFontStyle().getItemCount() ; i++)
			if (ti.getTitleFontStyle().getValue(i).equalsIgnoreCase(p.getTitleFontFamily()))
				ti.getTitleFontStyle().setItemSelected(i, true);
		ti.getSubTitleFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getSubTitleColor() + "; color: " + p.getSubTitleColor() + "; font-weight: bold; font-style: italic;'>" + p.getSubTitleColor() + "</div>");
		for (int i = 0 ; i < ti.getSubTitleFontSize().getItemCount() ; i++)
			if (ti.getSubTitleFontSize().getValue(i).equalsIgnoreCase(p.getSubTitleSize()))
				ti.getSubTitleFontSize().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getSubTitleFontStyle().getItemCount() ; i++)
			if (ti.getSubTitleFontStyle().getValue(i).equalsIgnoreCase(p.getSubTitleFontFamily()))
				ti.getSubTitleFontStyle().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getxAxisTitleFontFamily().getItemCount() ; i++)
			if (ti.getxAxisTitleFontFamily().getValue(i).equalsIgnoreCase(p.getxFontFamily()))
				ti.getxAxisTitleFontFamily().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getxAxisTitleFontSize().getItemCount() ; i++)
			if (ti.getxAxisTitleFontSize().getValue(i).equalsIgnoreCase(p.getxSize()))
				ti.getxAxisTitleFontSize().setItemSelected(i, true);
		ti.getxAxisTitleFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getxColor() + "; color: " + p.getxColor() + "; font-weight: bold; font-style: italic;'>" + p.getxColor() + "</div>");
		ti.getxValuesColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getxValuesColor() + "; color: " + p.getxValuesColor() + "; font-weight: bold; font-style: italic;'>" + p.getxValuesColor() + "</div>");
		for (int i = 0 ; i < ti.getxValuesFontSize().getItemCount() ; i++)
			if (ti.getxValuesFontSize().getValue(i).equalsIgnoreCase(p.getxValuesSize()))
				ti.getxValuesFontSize().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getxValuesFontFamily().getItemCount() ; i++)
			if (ti.getxValuesFontFamily().getValue(i).equalsIgnoreCase(p.getxValuesFontFamily()))
				ti.getxValuesFontFamily().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getxLabelInclination().getItemCount() ; i++)
			if (ti.getxLabelInclination().getValue(i).equalsIgnoreCase(String.valueOf(p.getxOrientation())))
				ti.getxLabelInclination().setItemSelected(i, true);
		ti.getLabelsDistanceFromAxis().setValue(String.valueOf(p.getLabelsDistanceFromAxis()));
		ti.getShowLegend().setValue(p.isShowLegend());
		ti.getShowBorder().setValue(p.isShowBorder());
		ti.getLegendFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getLegendColor() + "; color: " + p.getLegendColor() + "; font-weight: bold; font-style: italic;'>" + p.getLegendColor() + "</div>");
		ti.getLegendHorizontal().setValue(p.isLegendHorizontal());
		ti.getLegendTitle().setValue(p.getLegendLabel());
		for (int i = 0 ; i < ti.getLegendFontFamily().getItemCount() ; i++)
			if (ti.getLegendFontFamily().getValue(i).equalsIgnoreCase(p.getLegendFontFamily()))
				ti.getLegendFontFamily().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getLegendFontSize().getItemCount() ; i++)
			if (ti.getLegendFontSize().getValue(i).equalsIgnoreCase(p.getLegendSize()))
				ti.getLegendFontSize().setItemSelected(i, true);
		for (int i = 0 ; i < ti.getLegendPosition().getItemCount() ; i++)
			if (ti.getLegendPosition().getValue(i).equalsIgnoreCase(p.getLegendPosition()))
				ti.getLegendPosition().setItemSelected(i, true);
		ti.getHasXGrid().setValue(p.isxGrid());
		ti.getxGridColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + p.getxGridColor() + "; color: " + p.getxGridColor() + "; font-weight: bold; font-style: italic;'>" + p.getxGridColor() + "</div>");
		for (int i = 0 ; i < ti.getxGridLineType().getItemCount() ; i++)
			if (ti.getxGridLineType().getValue(i).equalsIgnoreCase(p.getxGridLineStyle()))
				ti.getxGridLineType().setItemSelected(i, true);
		ti.getImageHeight().setValue(String.valueOf(p.getImageHeight()));
		ti.getImageWidth().setValue(String.valueOf(p.getImageWidth()));
		ti.getxAxisEquidistantDates().setValue(p.isxAxisEquidistantDates());
		for (int i = 0 ; i < ti.getDatesFormat().getItemCount() ; i++)
			if (ti.getDatesFormat().getValue(i).equalsIgnoreCase(p.getDatesFormat()))
				ti.getDatesFormat().setItemSelected(i, true);
	}
	
	private static void loadYAxesParameters(Long datasetID, ChartAxisPanel yp, ChartDesignerWindow w, ChartDesignerParametersVO p, List<ResourceChildModel> models) {
		for (ChartDesignerYAxisParametersVO yap : p.getyAxes()) {
			if (yap.getDatasetID().longValue() == datasetID.longValue()) {
				loadYAxisParameters(yp, w, yap, models);
			}
		}
	}
	
	private static void loadYAxisParameters(ChartAxisPanel yp, ChartDesignerWindow w, ChartDesignerYAxisParametersVO p, List<ResourceChildModel> models) {
		for (int i = 0 ; i < yp.getDimensionList().getItemCount() ; i++) {
			if (yp.getDimensionList().getValue(i).equalsIgnoreCase(p.getContentDescriptor())) {
				yp.getDimensionList().setItemSelected(i, true);
			}
		}
		for (String yCode : p.getyCodes().keySet()) {
			if (yp.getValuesList().getItemCount() > 0) {
				for (int i = 0 ; i < yp.getValuesList().getItemCount() ; i++) {
					if (yp.getValuesList().getValue(i).equalsIgnoreCase(yCode)) {
						yp.getValuesList().setItemSelected(i, true);
					} else {
						yp.getValuesList().setItemSelected(i, false);
					}
				}
			} else {
				yp.getValuesList().addItem(yCode, p.getyCodes().get(yCode));
			}
		}
		yp.getAggregateMonthly().setValue(p.isMonthlyAggregated());
		yp.getAggregateYearly().setValue(p.isYearlyAggregated());
		yp.getUseFromDateToDate().setValue(p.isFromDateToDate());
		yp.getUseMostRecentData().setValue(p.isMostRecentData());
		yp.getFromDate().setValue(p.getFromDate());
		yp.getToDate().setValue(p.getToDate());
		for (int i = 0 ; i < yp.getLatestDaysList().getItemCount() ; i++) {
			if (Integer.valueOf(yp.getLatestDaysList().getValue(i)).intValue() == p.getLatestDays()) {
				yp.getLatestDaysList().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < yp.getLatestMonthsList().getItemCount() ; i++) {
			if (Integer.valueOf(yp.getLatestMonthsList().getValue(i)).intValue() == p.getLatestMonths()) {
				yp.getLatestMonthsList().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < yp.getLatestYearsList().getItemCount() ; i++) {
			if (Integer.valueOf(yp.getLatestYearsList().getValue(i)).intValue() == p.getLatestYears()) {
				yp.getLatestYearsList().setItemSelected(i, true);
				break;
			}
		}
		List<String> yCodes = new ArrayList<String>();
		for (String yCode : p.getyCodes().keySet())
			yCodes.add(yCode);
		changeDimensionsAgent(yp, w, models, yp.getValuesList(), false, yCodes);
	}
	
	private static void loadXAxisParameters(ChartAxisPanel xp, ChartDesignerWindow w, ChartDesignerParametersVO p, List<ResourceChildModel> models) {
		for (String xContentDescriptor : p.getxContentDescriptors()) {
			for (int i = 0 ; i < xp.getDimensionList().getItemCount() ; i++) {
				if (xp.getDimensionList().getValue(i).equalsIgnoreCase(xContentDescriptor)) {
					xp.getDimensionList().setItemSelected(i, true);
				} else {
					xp.getDimensionList().setItemSelected(i, false);
				}
			}
		}
//		GWT.log("ChartDesignerOpener @ 255 - " + xp.getValuesList().getItemCount() + " values");
//		GWT.log("ChartDesignerOpener @ 255 - " + p.getxCodes().size() + " codes");
		for (String xCode : p.getxCodes()) {
			for (int i = 0 ; i < xp.getValuesList().getItemCount() ; i++) {
				if (xp.getValuesList().getValue(i).equalsIgnoreCase(xCode)) {
					xp.getValuesList().setItemSelected(i, true);
				} else {
					xp.getValuesList().setItemSelected(i, false);
				}
			}
		}
		xp.getAggregateMonthly().setValue(p.isxMonthlyAggregated());
		xp.getAggregateYearly().setValue(p.isxYearlyAggregated());
		xp.getUseFromDateToDate().setValue(p.isxUseFromDateToDate());
		xp.getUseMostRecentData().setValue(p.isxUseMostRecentData());
		xp.getFromDate().setValue(p.getxFromDate());
		xp.getToDate().setValue(p.getxToDate());
		for (int i = 0 ; i < xp.getLatestDaysList().getItemCount() ; i++) {
			if (Integer.valueOf(xp.getLatestDaysList().getValue(i)).intValue() == p.getxLatestDays()) {
				xp.getLatestDaysList().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < xp.getLatestMonthsList().getItemCount() ; i++) {
			if (Integer.valueOf(xp.getLatestMonthsList().getValue(i)).intValue() == p.getxLatestMonths()) {
				xp.getLatestMonthsList().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < xp.getLatestYearsList().getItemCount() ; i++) {
			if (Integer.valueOf(xp.getLatestYearsList().getValue(i)).intValue() == p.getxLatestYears()) {
				xp.getLatestYearsList().setItemSelected(i, true);
				break;
			}
		}
		if (p.isxUseFromDateToDate() || p.isxUseMostRecentData()) {
			xp.getValuesFieldSet().setEnabled(false);
			if (p.isxUseFromDateToDate()) {
				xp.getMostRecentDataFieldSet().setEnabled(false);
			} 
			if (p.isxUseMostRecentData()) {
				xp.getFromDateToDateFieldSet().setEnabled(false);
			}
		}
//		changeDimensionsAgent(xp, w, models, xp.getValuesList(), true, p.getxCodes());
		changeDimensionsAgent(xp, w, models, xp.getValuesList(), false, p.getxCodes());
	}
	
	private static void loadHighlights(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		for (ChartDesignerHighlightVO h : p.getHighlights()) {
			LookAndFeelTabItem ti = w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(h.getDatasetID());
			if (h.getHighlightType().equalsIgnoreCase("MAX")) {
				loadMax(ti, h);
			} else if (h.getHighlightType().equalsIgnoreCase("MIN")) {
				loadMin(ti, h);
			} else if (h.getHighlightType().equalsIgnoreCase("ALL")) {
				loadAllValues(ti, h);
			} else if (h.getHighlightType().equalsIgnoreCase("DATES")) {
				loadShowDates(ti, h);
			} else if (h.getHighlightType().equalsIgnoreCase("VALUES")) {
				loadShowValues(ti, h);
			}
		}
	}
	
	private static void loadShowValues(LookAndFeelTabItem ti, ChartDesignerHighlightVO h) {
		ti.getAxisShowValuesFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + h.getColor() + "; color: " + h.getColor() + "; font-weight: bold; font-style: italic;'>" + h.getColor() + "</div>");
		ti.getAxisShowValues().setValue(h.isShow());
		for (int i = 0 ; i < ti.getAxisShowValuesFontStyle().getItemCount() ; i++) {
			if (ti.getAxisShowValuesFontStyle().getValue(i) == h.getStyle()) {
				ti.getAxisShowValuesFontStyle().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < ti.getAxisShowValuesFontSize().getItemCount() ; i++) {
			if (ti.getAxisShowValuesFontSize().getValue(i) == h.getStyle()) {
				ti.getAxisShowValuesFontSize().setItemSelected(i, true);
				break;
			}
		}
		String[] symbols = splitSymbol(h.getSymbol());
		String[] aliases = splitSymbol(h.getAlias());
		for (int i = 0 ; i < symbols.length ; i++) {
			if (i < symbols.length && i < aliases.length)
				ti.buildAndAddValuePanel(true, symbols[i], aliases[i]);
		}
	}
	
	private static void loadShowDates(LookAndFeelTabItem ti, ChartDesignerHighlightVO h) {
		ti.getAxisShowDatesFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + h.getColor() + "; color: " + h.getColor() + "; font-weight: bold; font-style: italic;'>" + h.getColor() + "</div>");
		ti.getAxisShowDates().setValue(h.isShow());
		for (int i = 0 ; i < ti.getAxisShowDatesFontStyle().getItemCount() ; i++) {
			if (ti.getAxisShowDatesFontStyle().getValue(i) == h.getStyle()) {
				ti.getAxisShowDatesFontStyle().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < ti.getAxisShowDatesFontSize().getItemCount() ; i++) {
			if (ti.getAxisShowDatesFontSize().getValue(i) == h.getStyle()) {
				ti.getAxisShowDatesFontSize().setItemSelected(i, true);
				break;
			}
		}
		String[] symbols = splitSymbol(h.getSymbol());
		for (String s : symbols)
			ti.buildAndAddDatePanel(true, FieldParser.parseDate(s));
	}
	
	private static String[] splitSymbol(String symbol) {
		return symbol.split(":");
	}
	
	private static void loadAllValues(LookAndFeelTabItem ti, ChartDesignerHighlightVO h) {
		ti.getAllValuesFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + h.getColor() + "; color: " + h.getColor() + "; font-weight: bold; font-style: italic;'>" + h.getColor() + "</div>");
		ti.getAllValues().setValue(h.isShow());
		for (int i = 0 ; i < ti.getAllValuesFontFamily().getItemCount() ; i++) {
			if (ti.getAllValuesFontFamily().getValue(i) == h.getStyle()) {
				ti.getAllValuesFontFamily().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < ti.getAllValuesFontStize().getItemCount() ; i++) {
			if (ti.getAllValuesFontStize().getValue(i) == h.getStyle()) {
				ti.getAllValuesFontStize().setItemSelected(i, true);
				break;
			}
		}
	}
	
	private static void loadMin(LookAndFeelTabItem ti, ChartDesignerHighlightVO h) {
		ti.getMinValueFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + h.getColor() + "; color: " + h.getColor() + "; font-weight: bold; font-style: italic;'>" + h.getColor() + "</div>");
		ti.getMinValue().setValue(h.isShow());
		for (int i = 0 ; i < ti.getMinValueFontFamily().getItemCount() ; i++) {
			if (ti.getMinValueFontFamily().getValue(i) == h.getStyle()) {
				ti.getMinValueFontFamily().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < ti.getMinValueFontStize().getItemCount() ; i++) {
			if (ti.getMinValueFontStize().getValue(i) == h.getStyle()) {
				ti.getMinValueFontStize().setItemSelected(i, true);
				break;
			}
		}
	}
	
	private static void loadMax(LookAndFeelTabItem ti, ChartDesignerHighlightVO h) {
		ti.getMaxValueFontColor().setHTML("<div align='center' style='border: 4px black solid; background-color: " + h.getColor() + "; color: " + h.getColor() + "; font-weight: bold; font-style: italic;'>" + h.getColor() + "</div>");
		ti.getMaxValue().setValue(h.isShow());
		for (int i = 0 ; i < ti.getMaxValueFontFamily().getItemCount() ; i++) {
			if (ti.getMaxValueFontFamily().getValue(i) == h.getStyle()) {
				ti.getMaxValueFontFamily().setItemSelected(i, true);
				break;
			}
		}
		for (int i = 0 ; i < ti.getMaxValueFontStize().getItemCount() ; i++) {
			if (ti.getMaxValueFontStize().getValue(i) == h.getStyle()) {
				ti.getMaxValueFontStize().setItemSelected(i, true);
				break;
			}
		}
	}
	
	private static void loadDatasourceParameters(ChartDesignerWindow w, ChartDesignerParametersVO p) {
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
			ChartTypeModelData ctmd = new ChartTypeModelData(p.getChartType(), p.getChartType());
			fs.getChartTypeList().setValue(ctmd);
		}
	}
	
	public static void loadDatasetAndSettingsAgent(final ChartDesignerWindow w, final ChartDesignerDatasourceFieldSet fs, final ChartDesignerParametersVO p, final boolean olap2chart) {
		
		final List<ResourceChildModel> models = w.getChartDesignerPanel().getDatasourcePanel().getModels();
		final LoadingWindow l = new LoadingWindow(BabelFish.print().chartDesigner(), "Retrieving datasets, dimensions and values.", BabelFish.print().pleaseWait());
		
		try {
			
			ChartDesignerServiceEntry.getInstance().loadDataset(models, CheckLanguage.getLanguage(), new AsyncCallback<ResourceViewVO>() {
				
				public void onSuccess(ResourceViewVO rvvo) {
					
					l.destroyLoadingBox();
					addDatasetPanels(w, rvvo.getDatasets(), fs, false);
					createYAxisPanels(w.getChartDesignerPanel(), fs.getDatasets());
					fillXAxisDimensions(w.getChartDesignerPanel());
					fillYAxisDimensions(w.getChartDesignerPanel(), fs.getDatasets());
					addAxisFormatPanel(w.getChartDesignerPanel(), fs.getDatasets(), false);
					fillPlotDimensions(w, fs.getDatasets());
					w.getChartDesignerPanel().getxAxisPanel().getDimensionList().addChangeHandler(changeDimension(w, "X"));
					for (ChartAxisPanel cap : w.getChartDesignerPanel().getAxisTabPanel().getChartXAxisPanelsMap().values())
						cap.getDimensionList().addChangeHandler(changeDimension(w, cap));
					for (ChartAxisPanel cap : w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().values()) {
						cap.getDimensionList().addChangeHandler(changeDimension(w, cap));
						cap.getValuesList().addChangeHandler(valuesChangeHandler(cap, w.getChartDesignerPanel().getLookAndFeelTabPanel()));
					}
					chartTypeSelectionChangedAgent(w, false);
					w.getChartDesignerPanel().getDatasourceButton().setEnabled(true);
					w.getChartDesignerPanel().getxAxisButton().setEnabled(true);
					linkMinValuesToLabelsDistanceFromXAxis(w);
					
					// load existing settings
					loadDatasourceParameters(w, p);
					loadXAxisParameters(w.getChartDesignerPanel().getxAxisPanel(), w, p, models);
					for (Long datasetID : w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().keySet())
						loadYAxesParameters(datasetID, w.getChartDesignerPanel().getAxisTabPanel().getChartYAxisPanelsMap().get(datasetID), w, p, models);
					w.getChartDesignerPanel().getLookAndFeelTabPanel().getGeneralSettings().getSubTitle().setValue(p.getSubTitleLabel());
					loadAxesSettings(w, p);
					loadFilter(w, p);
					loadHighlights(w, p);
					
					// select the correct chart type
					String cbID = (String)fs.getUseThisDataset().getData("DATASET_ID");
					if (cbID != null && !cbID.equals("null")) {
						Long datasetID = Long.parseLong(cbID);
						for (Long id : p.getDatasourceIDs()) {
							if (id.longValue() == datasetID.longValue()) {
								for (ChartTypeModelData t : fs.getChartTypeList().getSelection()) {
									if (t.getChartTypeCode().equalsIgnoreCase(p.getChartType())) {
										fs.getChartTypeList().setValue(t);
									}
								}
								break;
							}
						}
					}
					
					// enable buttons
					w.getChartDesignerPanel().enableButtons();
					w.enableToolbar();
					
					// show the image
					if ((p.getImagePath() != null) && (p.getImagePath().length() > 0)) {
						w.getChartDesignerPanel().getChartPanel().setImage(p.getImagePath(), String.valueOf(p.getImageWidth()) + "px", String.valueOf(p.getImageHeight()) + "px");
						w.getChartDesignerPanel().getLayout().setActiveItem(w.getChartDesignerPanel().getChartPanel().getLayoutContainer());
					}
					
					restoreButtonsColor(w.getChartDesignerPanel());
					w.getChartDesignerPanel().getChartButton().setText("<b><font color='#CA1616'><u>" + w.getChartDesignerPanel().getChartButton().getText() + "</u><font></b>");
					restoreYMaxValues(w, p);
					
					if (olap2chart) {
						List<Long> datasetIDs = new ArrayList<Long>();
						datasetIDs.add(Long.parseLong(cbID));
						calculateYMaxValue(w, p, w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(Long.parseLong(cbID)).getAxisToValue(), datasetIDs);
					}
					//mackx
					else{
						try {
							List<Long> datasetIDs = new ArrayList<Long>();
							datasetIDs.add(Long.parseLong(cbID));
							calculateYMaxValue(w, p, w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(Long.parseLong(cbID)).getAxisToValue(), datasetIDs);
						} catch (Exception excpt) {
						}
					}
					
					l.destroyLoadingBox();
				};
				
				public void onFailure(Throwable e1) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					e1.printStackTrace();
					l.destroyLoadingBox();
				}
				
			});
			
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			l.destroyLoadingBox();
		}
	}
	
	public static void restoreYMaxValues(final ChartDesignerWindow w, final ChartDesignerParametersVO p) {
		for (Long datasetID : p.getDatasourceIDs()) {
			LookAndFeelTabItem ti = w.getChartDesignerPanel().getLookAndFeelTabPanel().getDatasetSettings().get(datasetID);
			for (ChartDesignerYAxisParametersVO yap : p.getyAxes()) {
				if (yap.getDatasetID().longValue() == datasetID.longValue()) {
					ti.getAxisToValue().setValue(String.valueOf(yap.getyMax()));
				}
			}
		}
	}
	
}