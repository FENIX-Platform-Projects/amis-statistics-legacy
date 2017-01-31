package org.fao.fenix.web.modules.chartdesigner.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.chartdesigner.client.view.DescriptorModelData;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.DatasourcePanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelTabItem;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.PlotDimensionPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.RPanel;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerFilterVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerHighlightVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerYAxisParametersVO;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ChartDesignerParametersCollector {

	public static List<ChartDesignerParametersVO> collectParameters(
			ChartDesignerWindow w) {
		List<ChartDesignerParametersVO> l = new ArrayList<ChartDesignerParametersVO>();
		for (ChartDesignerDatasourceFieldSet fs : w.getChartDesignerPanel()
				.getDatasourcePanel().getFieldSets()) {
			if (fs.getUseThisDataset().getValue()) {
				ChartDesignerParametersVO p = collectParameters(w, fs);
				l.add(p);
			}
		}
		return l;
	}

	private static ChartDesignerParametersVO collectParameters(
			ChartDesignerWindow w, ChartDesignerDatasourceFieldSet fs) {

		ChartDesignerParametersVO p = new ChartDesignerParametersVO();

		p.setDatasourceIDs(getSelectedDatasetIDs(fs));
		p.setPlotDimensionMap(getPlotDimensionMap(w.getChartDesignerPanel()
				.getDatasourcePanel()));

		p.setChartType(getChartType(fs));
		p.setAggregationFunction(getAggregationFunction(w
				.getChartDesignerPanel().getrPanel()));

		p.setImageHeight(Integer.valueOf(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings().getImageHeight()
				.getValue()));
		p.setImageWidth(Integer.valueOf(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings().getImageWidth()
				.getValue()));

		p.setTitleLabel(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getTitle().getValue());
		p.setTitleColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getTitleFontColor().getText());
		p.setTitleFontFamily(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getTitleFontStyle().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getTitleFontStyle()
								.getSelectedIndex()));
		p.setTitleSize(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getTitleFontSize().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getTitleFontSize()
								.getSelectedIndex()));

		p.setSubTitle(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getSubTitle().getValue());
		p.setSubTitleColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getSubTitleFontColor().getText());
		p.setSubTitleFontFamily(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getSubTitleFontStyle().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getSubTitleFontStyle()
								.getSelectedIndex()));
		p.setSubTitleLabel(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getSubTitle().getValue());
		p.setSubTitleSize(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getSubTitleFontSize().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getSubTitleFontSize()
								.getSelectedIndex()));

		p.setLegendLabel(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getLegendTitle().getValue());
		p.setLegendColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getLegendFontColor().getText());
		p.setLegendFontFamily(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getLegendFontFamily().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getLegendFontFamily()
								.getSelectedIndex()));
		p.setLegendSize(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getLegendFontSize().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getLegendFontSize()
								.getSelectedIndex()));
		p.setLegendPosition(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getLegendPosition().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getLegendPosition()
								.getSelectedIndex()));
		p.setShowLegend(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getShowLegend().getValue());
		p.setShowBorder(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getShowBorder().getValue());
		p.setLegendHorizontal(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getLegendHorizontal().getValue());

		p.setxLabel(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisTitle().getValue());
		p.setxColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisTitleFontColor().getText());
		p.setxFontFamily(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisTitleFontFamily().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxAxisTitleFontFamily()
								.getSelectedIndex()));
		p.setxSize(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisTitleFontSize().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxAxisTitleFontSize()
								.getSelectedIndex()));
		p.setxOrientation(Integer.parseInt(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getxLabelInclination().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxLabelInclination()
								.getSelectedIndex())));
		p.setxContentDescriptors(collectXContentDescriptors(w));
		p.setxCodes(getXCodes(w.getChartDesignerPanel().getxAxisPanel()));
		p.setxLabels(getXLabels(w.getChartDesignerPanel().getxAxisPanel()));
		p.setxFromDate(w.getChartDesignerPanel().getxAxisPanel().getFromDate()
				.getValue());
		p.setxToDate(w.getChartDesignerPanel().getxAxisPanel().getToDate()
				.getValue());
		p.setxUseFromDateToDate(w.getChartDesignerPanel().getxAxisPanel()
				.getUseFromDateToDate().getValue());
		p.setxUseMostRecentData(w.getChartDesignerPanel().getxAxisPanel()
				.getUseMostRecentData().getValue());
		p.setxLatestDays(Integer.valueOf(w.getChartDesignerPanel()
				.getxAxisPanel().getLatestDaysList().getValue(
						w.getChartDesignerPanel().getxAxisPanel()
								.getLatestDaysList().getSelectedIndex())));
		p.setxLatestMonths(Integer.valueOf(w.getChartDesignerPanel()
				.getxAxisPanel().getLatestMonthsList().getValue(
						w.getChartDesignerPanel().getxAxisPanel()
								.getLatestMonthsList().getSelectedIndex())));
		p.setxLatestYears(Integer.valueOf(w.getChartDesignerPanel()
				.getxAxisPanel().getLatestYearsList().getValue(
						w.getChartDesignerPanel().getxAxisPanel()
								.getLatestYearsList().getSelectedIndex())));
		p.setxMonthlyAggregated(w.getChartDesignerPanel().getxAxisPanel()
				.getAggregateMonthly().getValue());
		p.setxYearlyAggregated(w.getChartDesignerPanel().getxAxisPanel()
				.getAggregateYearly().getValue());
		p.setxAxisNumberOfIntervals(Integer.valueOf(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getxAxisNumberOfIntervals().getValue()));
		if (p.getChartType().equalsIgnoreCase("TARGET")) {
			p.setxAxisNumberOfIntervals(0);
		}
		p.setxAxisColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisColor().getText());
		p.setxAxisTicksColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxAxisTicksColor().getText());
		p.setxValuesColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxValuesColor().getText());
		p.setxValuesFontFamily(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getxValuesFontFamily().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxValuesFontFamily()
								.getSelectedIndex()));
		p.setxValuesSize(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxValuesFontSize().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxValuesFontSize()
								.getSelectedIndex()));
		p.setLabelsDistanceFromAxis(Double.valueOf(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getLabelsDistanceFromAxis().getValue()));
		p.setxAxisEquidistantDates(w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getGeneralSettings()
				.getxAxisEquidistantDates().getValue());
		if (p.getChartType().equalsIgnoreCase("TARGET")) {
			p.setxAxisEquidistantDates(false);
		}

		p.setFilters(collectFilters(w.getChartDesignerPanel()
				.getPlotDimensionPanel()));

		p.setHighlights(collectHighlights(w, fs));

		p.setyAxes(collectYAxes(w, fs));

		p.setxGrid(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getHasXGrid().getValue());
		p.setxGridColor(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxGridColor().getText());
		p.setxGridLineStyle(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getxGridLineType().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getxGridLineType()
								.getSelectedIndex()));

		p.setDatesFormat(w.getChartDesignerPanel().getLookAndFeelTabPanel()
				.getGeneralSettings().getDatesFormat().getValue(
						w.getChartDesignerPanel().getLookAndFeelTabPanel()
								.getGeneralSettings().getDatesFormat()
								.getSelectedIndex()));

		return p;
	}

	private static List<String> collectXContentDescriptors(ChartDesignerWindow w) {
		List<String> des = new ArrayList<String>();
		ListBox l = w.getChartDesignerPanel().getxAxisPanel()
				.getDimensionList();
		for (int i = 0; i < l.getItemCount(); i++)
			if (l.isItemSelected(i))
				des.add(l.getValue(i));
		return des;
	}

	private static List<ChartDesignerYAxisParametersVO> collectYAxes(
			ChartDesignerWindow w, ChartDesignerDatasourceFieldSet fs) {
		List<ChartDesignerYAxisParametersVO> ys = new ArrayList<ChartDesignerYAxisParametersVO>();
		CheckBox cb = fs.getUseThisDataset();
		Long checkboxDatasetID = Long
				.valueOf((String) cb.getData("DATASET_ID"));
		for (Long datasetID : w.getChartDesignerPanel().getAxisTabPanel()
				.getChartYAxisPanelsMap().keySet()) {
			if (checkboxDatasetID.longValue() == datasetID) {
				ChartAxisPanel cap = w.getChartDesignerPanel()
						.getAxisTabPanel().getChartYAxisPanelsMap().get(
								datasetID);
				LookAndFeelTabItem ti = w.getChartDesignerPanel()
						.getLookAndFeelTabPanel().getDatasetSettings().get(
								datasetID);
				ChartDesignerYAxisParametersVO vo = new ChartDesignerYAxisParametersVO();
				vo.setAxisColor(ti.getAxisColor().getText());
				vo.setAxisPosition(ti.getAxisPosition().getValue(
						ti.getAxisPosition().getSelectedIndex()));
				vo.setAxisTitle(ti.getAxisTitle().getValue());
				vo.setContentDescriptor(cap.getDimensionList().getValue(
						cap.getDimensionList().getSelectedIndex()));
				vo.setDatasetID(ti.getDatasetID());
				vo.setFontColor(ti.getAxisTitleFontColor().getText());
				vo.setFontSize(ti.getAxisTitleFontSize().getValue(
						ti.getAxisTitleFontSize().getSelectedIndex()));
				vo.setFontStyle(ti.getAxisTitleFontFamily().getValue(
						ti.getAxisTitleFontFamily().getSelectedIndex()));
				if (cap.getUseFromDateToDate().getValue()) {
					vo.setFromDateToDate(cap.getUseFromDateToDate().getValue());
					vo.setFromDate(cap.getFromDate().getValue());
					vo.setToDate(cap.getToDate().getValue());
				}
				if (cap.getUseMostRecentData().getValue()) {
					vo.setMostRecentData(cap.getUseMostRecentData().getValue());
					vo
							.setLatestDays(Integer.valueOf(cap
									.getLatestDaysList().getValue(
											cap.getLatestDaysList()
													.getSelectedIndex())));
					vo.setLatestMonths(Integer.valueOf(cap
							.getLatestMonthsList().getValue(
									cap.getLatestMonthsList()
											.getSelectedIndex())));
					vo.setLatestYears(Integer
							.valueOf(cap.getLatestYearsList()
									.getValue(
											cap.getLatestYearsList()
													.getSelectedIndex())));
				}
				vo.setMonthlyAggregated(cap.getAggregateMonthly().getValue());
				vo.setYearlyAggregated(cap.getAggregateYearly().getValue());
				vo.setHeader(cap.getDimensionList().getItemText(
						cap.getDimensionList().getSelectedIndex()));
				vo.setLabelOrientation(ti.getLabelInclination().getValue(
						ti.getLabelInclination().getSelectedIndex()));
				try {
					vo
							.setyMin(Double.valueOf(ti.getAxisFromValue()
									.getValue()));
				} catch (Exception e) {
					vo.setyMin(Double.NaN);
				}
				try {
					vo.setyMax(Double.valueOf(ti.getAxisToValue().getValue()));
				} catch (Exception e) {
					vo.setyMax(Double.NaN);
				}
				vo.setStep(Double.valueOf(ti.getAxisNumberOfIntervals()
						.getValue()));
				vo.setTicksColor(ti.getAxisTicksColor().getText());
				vo.setSeriesColorsMap(collectSeriesColorsMap(w, datasetID));
				vo.setHeaderYCodeMap(collectHeaderYCodeMap(w, datasetID));
				vo.setyCodes(collectYCodes(w, datasetID));
				vo.setValuesFontColor(ti.getValuesColor().getText());
				vo.setValuesFontSize(ti.getValuesFontSize().getValue(
						ti.getValuesFontSize().getSelectedIndex()));
				vo.setValuesFontStyle(ti.getValuesFontFamily().getValue(
						ti.getValuesFontFamily().getSelectedIndex()));
				vo.setLineType(ti.getAxisLineType().getValue(
						ti.getAxisLineType().getSelectedIndex()));
				vo.setyGridLineStyle(ti.getyGridLineType().getValue(
						ti.getyGridLineType().getSelectedIndex()));
				vo.setLineWidth(ti.getAxisLineWidth().getValue(
						ti.getAxisLineWidth().getSelectedIndex()));
				vo.setyGridColor(ti.getyGridColor().getText());
				vo.setyGrid(ti.getHasYGrid().getValue());
				// System.out.println(vo.getDatasetID() + " has y grid " +
				// vo.isyGrid());
				vo.setyAxisEquidistantDates(ti.getyAxisEquidistantDates()
						.getValue());
				vo.setStacked(ti.getStacked().getValue());
				vo.setPointType(ti.getPointType().getValue(
						ti.getPointType().getSelectedIndex()));
				ys.add(vo);
			}
		}
		return ys;
	}

	private static Map<String, String> collectYCodes(ChartDesignerWindow w,
			Long datasetID) {
		Map<String, String> codeLabelMap = new HashMap<String, String>();
		ChartAxisPanel cap = w.getChartDesignerPanel().getAxisTabPanel()
				.getChartYAxisPanelsMap().get(datasetID);
		for (int i = 0; i < cap.getValuesList().getItemCount(); i++) {
			if (cap.getValuesList().isItemSelected(i)) {
				codeLabelMap.put(cap.getValuesList().getValue(i), cap
						.getValuesList().getItemText(i));
			}
		}
		return codeLabelMap;
	}

	private static Map<String, String> collectSeriesColorsMap(
			ChartDesignerWindow w, Long datasetID) {
		Map<String, String> headerColorMap = new HashMap<String, String>();
		LookAndFeelTabItem ti = w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getDatasetSettings().get(datasetID);
		for (TextField<String> key : ti.getSeriesColorMap().keySet())
			headerColorMap.put(key.getValue(), ti.getSeriesColorMap().get(key)
					.getText());
		return headerColorMap;
	}

	private static Map<String, String> collectHeaderYCodeMap(
			ChartDesignerWindow w, Long datasetID) {
		Map<String, String> headerColorMap = new HashMap<String, String>();
		LookAndFeelTabItem ti = w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getDatasetSettings().get(datasetID);
		for (TextField<String> key : ti.getHeaderYCodeMap().keySet())
			headerColorMap.put(key.getValue(), ti.getHeaderYCodeMap().get(key));
		return headerColorMap;
	}

	// private static List<Long> getSelectedDatasetIDs(ChartDesignerWindow w) {
	// List<Long> ids = new ArrayList<Long>();
	// for (ChartDesignerDatasourceFieldSet fs :
	// w.getChartDesignerPanel().getDatasourcePanel().getFieldSets()) {
	// CheckBox cb = fs.getUseThisDataset();
	// if (cb.getValue()) {
	// Long id = Long.valueOf((String)cb.getData("DATASET_ID"));
	// ids.add(id);
	// }
	// }
	// return ids;
	// }

	private static List<Long> getSelectedDatasetIDs(
			ChartDesignerDatasourceFieldSet fs) {
		List<Long> ids = new ArrayList<Long>();
		CheckBox cb = fs.getUseThisDataset();
		// System.out.println("cb is checked? " + cb.getValue());
		if (cb.getValue()) {
			Long id = Long.valueOf((String) cb.getData("DATASET_ID"));
			// System.out.println("ID: " + id);
			ids.add(id);
		}
		return ids;
	}

	private static List<ChartDesignerHighlightVO> collectHighlights(
			ChartDesignerWindow w, ChartDesignerDatasourceFieldSet fs) {
		CheckBox cb = fs.getUseThisDataset();
		Long datasetID = Long.valueOf((String) cb.getData("DATASET_ID"));
		List<ChartDesignerHighlightVO> hs = new ArrayList<ChartDesignerHighlightVO>();
		LookAndFeelTabItem ti = w.getChartDesignerPanel()
				.getLookAndFeelTabPanel().getDatasetSettings().get(datasetID);
		String T = getChartType(fs);
		GWT.log("Chart Type? " + T);
		if (ti.getAllValues().getValue() && !T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor(ti.getAllValuesFontColor().getText());
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("ALL");
			vo.setShow(ti.getAllValues().getValue());
			vo.setStyle(ti.getAllValuesFontFamily().getValue(
					ti.getAllValuesFontFamily().getSelectedIndex()));
			vo.setSize(ti.getAllValuesFontStize().getValue(
					ti.getAllValuesFontStize().getSelectedIndex()));
			hs.add(vo);
		}
		if (ti.getMaxValue().getValue() && !T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor(ti.getMaxValueFontColor().getText());
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("MAX");
			vo.setShow(ti.getMaxValue().getValue());
			vo.setStyle(ti.getMaxValueFontFamily().getValue(
					ti.getMaxValueFontFamily().getSelectedIndex()));
			vo.setSize(ti.getMaxValueFontStize().getValue(
					ti.getMaxValueFontStize().getSelectedIndex()));
			hs.add(vo);
		}
		if (ti.getMinValue().getValue() && !T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor(ti.getMinValueFontColor().getText());
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("MIN");
			vo.setShow(ti.getMinValue().getValue());
			vo.setStyle(ti.getMinValueFontFamily().getValue(
					ti.getMinValueFontFamily().getSelectedIndex()));
			vo.setSize(ti.getMinValueFontStize().getValue(
					ti.getMinValueFontStize().getSelectedIndex()));
			hs.add(vo);
		}
		if (ti.getAxisShowDates().getValue() && !T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor(ti.getAxisShowDatesFontColor().getText());
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("DATES");
			vo.setShow(ti.getAxisShowDates().getValue());
			vo.setStyle(ti.getAxisShowDatesFontStyle().getValue(
					ti.getAxisShowDatesFontStyle().getSelectedIndex()));
			vo.setSize(ti.getAxisShowDatesFontSize().getValue(
					ti.getAxisShowDatesFontSize().getSelectedIndex()));
			vo.setSymbol(createDatesSymbol(ti.getAxisShowDatePanels()));
			hs.add(vo);
		}
		if (ti.getAxisShowValues().getValue() && !T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor(ti.getAxisShowValuesFontColor().getText());
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("VALUES");
			vo.setShow(ti.getAxisShowValues().getValue());
			vo.setStyle(ti.getAxisShowValuesFontStyle().getValue(
					ti.getAxisShowValuesFontStyle().getSelectedIndex()));
			vo.setSize(ti.getAxisShowValuesFontSize().getValue(
					ti.getAxisShowValuesFontSize().getSelectedIndex()));
			vo.setSymbol(createValuesSymbol(ti.getAxisShowValuePanels()));
			vo.setAlias(createValuesAlias(ti.getAxisShowValuePanels()));
			hs.add(vo);
		}
		if (T.equalsIgnoreCase("TARGET")) {
			ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
			vo.setColor("#000000");
			vo.setDatasetID(ti.getDatasetID());
			vo.setHighlightType("TARGETS");
			vo.setShow(true);
			vo.setStyle("0");
			vo.setSize("0.7");
			hs.add(vo);
		}
		return hs;
	}

	@SuppressWarnings("unchecked")
	private static String createValuesAlias(
			List<HorizontalPanel> axisShowDatePanels) {
		String s = "";
		for (HorizontalPanel hp : axisShowDatePanels) {
			CheckBox cb = (CheckBox) hp.getData("CHECKBOX");
			if (cb.getValue()) {
				TextField<String> tf = (TextField<String>) hp
						.getData("ALIAS_TEXTFIELD");
				if (tf != null && tf.getValue() != null) {
					s += tf.getValue().trim();
				} else {
					s += " ";
				}
			}
			s += ":";
		}
		s = s.substring(0, s.length() - 1);
		// System.out.println("ChartDesignerParametersCollector | " + s);
		return s;
	}

	@SuppressWarnings("unchecked")
	private static String createValuesSymbol(
			List<HorizontalPanel> axisShowDatePanels) {
		String s = "";
		for (HorizontalPanel hp : axisShowDatePanels) {
			CheckBox cb = (CheckBox) hp.getData("CHECKBOX");
			if (cb.getValue()) {
				TextField<String> tf = (TextField<String>) hp
						.getData("TEXTFIELD");
				if (tf != null) {
					s += tf.getValue().trim();
				} else {
					s += "LAST";
				}
			}
			s += ":";
		}
		s = s.substring(0, s.length() - 1);
		// System.out.println("ChartDesignerParametersCollector | " + s);
		return s;
	}

	private static String createDatesSymbol(
			List<HorizontalPanel> axisShowDatePanels) {
		String s = "";
		for (HorizontalPanel hp : axisShowDatePanels) {
			CheckBox cb = (CheckBox) hp.getData("CHECKBOX");
			if (cb.getValue()) {
				DateField df = (DateField) hp.getData("DATEFIELD");
				s += date2string(df.getValue());
			}
			s += ":";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

	private static String date2string(Date d) {
		String s = "";
		s += String.valueOf(1900 + d.getYear());
		s += "-";
		if (d.getMonth() < 9)
			s += "0";
		s += String.valueOf(1 + d.getMonth());
		s += "-";
		if (d.getDate() < 10)
			s += "0";
		s += String.valueOf(d.getDate());
		return s;
	}

	private static List<ChartDesignerFilterVO> collectFilters(
			PlotDimensionPanel p) {
		List<ChartDesignerFilterVO> vos = new ArrayList<ChartDesignerFilterVO>();
		for (Long datasetID : p.getFiltersMap().keySet()) {
			for (ListBox l : p.getFiltersMap().get(datasetID)) {
				if (isFilterSelected(l)) {
					ChartDesignerFilterVO vo = new ChartDesignerFilterVO();
					vo.setContentDescriptor(p.getContentDescriptorMap().get(l));
					vo.setDatasetID(datasetID);
					List<String> codes = new ArrayList<String>();
					for (int i = 0; i < l.getItemCount(); i++)
						if (l.isItemSelected(i))
							codes.add(l.getValue(i));
					vo.setCodes(codes);
					vos.add(vo);
				}
			}
		}
		return vos;
	}

	private static boolean isFilterSelected(ListBox l) {
		for (int i = 0; i < l.getItemCount(); i++)
			if (l.isItemSelected(i))
				return true;
		return false;
	}

	private static Map<Long, String> getPlotDimensionMap(DatasourcePanel p) {
		Map<Long, String> m = new HashMap<Long, String>();
		for (ChartDesignerDatasourceFieldSet fs : p.getFieldSets()) {
			CheckBox cb = fs.getUseThisDataset();
			if (cb.getValue()) {
				for (ComboBox<DescriptorModelData> combo : fs
						.getDescriptorComboBoxes()) {
					for (DescriptorModelData md : combo.getSelection()) {
						m.put(Long.valueOf(md.getDatasetID()), md
								.getContentDescriptor());
					}
				}
			}
		}
		return m;
	}

	private static List<String> getXCodes(ChartAxisPanel p) {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < p.getValuesList().getItemCount(); i++)
			if (p.getValuesList().isItemSelected(i))
				l.add(p.getValuesList().getValue(i));
		return l;
	}

	private static List<String> getXLabels(ChartAxisPanel p) {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < p.getValuesList().getItemCount(); i++)
			if (p.getValuesList().isItemSelected(i))
				l.add(p.getValuesList().getItemText(i));
		return l;
	}

	private static Map<String, String> getSeriesMap(LookAndFeelPanel p) {
		Map<String, String> m = new HashMap<String, String>();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			HTML html = (HTML) p.getSeriesFieldSet().getData("COLOR_" + i);
			if (html == null) {
				break;
			} else {
				String label = (String) p.getSeriesFieldSet().getData(
						"LABEL_" + i);
				m.put(label, html.getText());
				p.getSeriesMap().put(label, html.getText());
			}
		}
		return m;
	}

	private static String getAggregationFunction(RPanel p) {
		return p.getAggregationFunctionListBox().getValue(
				p.getAggregationFunctionListBox().getSelectedIndex());
	}

	private static String getChartType(ChartDesignerDatasourceFieldSet fs) {
		return fs.getChartTypeList().getSelection().get(0).getChartTypeCode();
	}

}
