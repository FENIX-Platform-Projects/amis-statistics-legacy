package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.DescriptorModelData;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.ListBox;

public class MTParametersCollector {

	public static String getTextFieldValue(TextField<String> textField) {
		String newLabel = null;
		try {
			newLabel = textField.getValue();
			
			if(newLabel.length()==0)
				newLabel = null;
		} catch (Exception e) {
		}
		return newLabel;
	}

	public static void setTextFieldValue(TextField<String> textField,
			String value) {
		if (value != null)
			textField.setValue(value);
		else
			textField.setValue("");
	}

	public static OLAPParametersVo retrieveParameters(final MTWindow w) {

		OLAPParametersVo vo = new OLAPParametersVo();

		vo.setDataSourceId(getDatasetID(w));
		vo.setDataSourceTitle(getDatasetTitle(w));
		vo.setDataSourceType("Dataset");
		vo.setFunction(getFunction(w));
		vo.setValue(getValue(w));
		vo.setValueLabel(getValueLabel(w));
		vo.setFilters(getFilters(w));
		vo.setShowSingleValues(true);
		
		//mackx == extended feature
		vo.setColFunction(w.getMtPanel()
				.getMtFunctionPanel()
				.getColFunctionListBox()
				.getValue(w.getMtPanel()
				.getMtFunctionPanel()
				.getColFunctionListBox()
				.getSelectedIndex()));
		vo.setRowFunction(w.getMtPanel()
				.getMtFunctionPanel()
				.getRowFunctionListBox()
				.getValue(w.getMtPanel()
				.getMtFunctionPanel()
				.getRowFunctionListBox()
				.getSelectedIndex()));
		vo.setSubColFunction(w.getMtPanel()
				.getMtFunctionPanel()
				.getSubColFunctionListBox()
				.getValue(w.getMtPanel()
				.getMtFunctionPanel()
				.getSubColFunctionListBox()
				.getSelectedIndex()));
		vo.setSubRowFunction(w.getMtPanel()
				.getMtFunctionPanel()
				.getSubRowFunctionListBox()
				.getValue(w.getMtPanel()
				.getMtFunctionPanel()
				.getSubRowFunctionListBox()
				.getSelectedIndex()));
		
		vo.setShading1Color(w.getMtPanel().getMtLookAndFeelPanel()
				.getShading1Color().getText());
		vo.setShading2Color(w.getMtPanel().getMtLookAndFeelPanel()
				.getShading2Color().getText());
		
		vo.setShowBorder(w.getMtPanel().getMtLookAndFeelPanel().getShowBorder().getValue());
		vo.setShowColSubject(w.getMtPanel().getMtLookAndFeelPanel().getShowColSubject().getValue());
		vo.setShowColSummary(w.getMtPanel().getMtLookAndFeelPanel().getShowColumnTotals().getValue());
		vo.setShowGridLine(w.getMtPanel().getMtLookAndFeelPanel().getShowGridLine().getValue());
		vo.setShowRowSubject(w.getMtPanel().getMtLookAndFeelPanel().getShowRowSubject().getValue());
		vo.setShowRowSummary(w.getMtPanel().getMtLookAndFeelPanel().getShowRowTotals().getValue());
		vo.setShowShading(w.getMtPanel().getMtLookAndFeelPanel().getShowShading().getValue());
		vo.setShowSubColSubject(w.getMtPanel().getMtLookAndFeelPanel().getShowSubColSubject().getValue());
		vo.setShowSubColSummary(w.getMtPanel().getMtLookAndFeelPanel().getShowSubColSummary().getValue());
		vo.setShowSubRowSubject(w.getMtPanel().getMtLookAndFeelPanel().getShowSubRowSubject().getValue());
		vo.setShowSubRowSummary(w.getMtPanel().getMtLookAndFeelPanel().getShowSubRowSummary().getValue());
		
		/*vo.setwLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getwLabel().getValue());
		vo.setxLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getxLabel().getValue());
		vo.setyLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getyLabel().getValue());
		vo.setzLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getzLabel().getValue());
		*/
		//mackx off
	
		vo.setShowTotals(vo.getShowRowSummary() && vo.getShowColSummary());

		vo.setShowRowVariation(w.getMtPanel().getMtLookAndFeelPanel()
				.getShowRowVariation().getValue());
		vo.setShowColVariation(w.getMtPanel().getMtLookAndFeelPanel()
				.getShowColumnVariation().getValue());

		vo.setDecimals(Integer.valueOf(w.getMtPanel().getMtLookAndFeelPanel()
				.getDecimals().getValue(
						w.getMtPanel().getMtLookAndFeelPanel().getDecimals()
								.getSelectedIndex())));
		vo.setDecimalsForTotals(Integer.valueOf(w.getMtPanel()
				.getMtLookAndFeelPanel().getDecimalsForTotals().getValue(
						w.getMtPanel().getMtLookAndFeelPanel()
								.getDecimalsForTotals().getSelectedIndex())));
		vo
				.setDecimalsForVariation(Integer.valueOf(w.getMtPanel()
						.getMtLookAndFeelPanel().getDecimalsForVariation()
						.getValue(
								w.getMtPanel().getMtLookAndFeelPanel()
										.getDecimalsForVariation()
										.getSelectedIndex())));

		vo.setReportOrientation("portrait");
		vo.setResourceViewID(w.getMtID());

		if (w.getMtPanel().getMtColumnsPanel().getDimensionList().getSelectedIndex() > -1) {
			vo.setX(w.getMtPanel().getMtColumnsPanel().getDimensionList()
					.getValue(
							w.getMtPanel().getMtColumnsPanel()
									.getDimensionList().getSelectedIndex()));

			String newXLabelShowTitle = getTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getxLabelShowTitle());
			//String pureXLabel = w.getMtPanel().getMtLookAndFeelPanel().getxLabel();
			
			//if (pureXLabel == null){
			String pureXLabel = w.getMtPanel().getMtColumnsPanel()
				.getDimensionList().getItemText(
						w.getMtPanel().getMtColumnsPanel()
								.getDimensionList().getSelectedIndex());
			//}
			vo.setXLabel(pureXLabel);

			if (newXLabelShowTitle == null){
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getxLabelShowTitle(), pureXLabel);
			}

			if(pureXLabel!=null){
			vo.setAggregateXAsMonthly(w.getMtPanel().getMtColumnsPanel()
					.getAggregateMonthly().getValue());
			vo.setAggregateXAsYearly(w.getMtPanel().getMtColumnsPanel()
					.getAggregateYearly().getValue());
			vo.setXLabels(labels(w.getMtPanel().getMtColumnsPanel()));
			vo.setxUniqueValuesMap(uniqueValuesMap(w.getMtPanel()
					.getMtColumnsPanel()));
			}
		}

		if (w.getMtPanel().getMtRowsPanel().getDimensionList()
				.getSelectedIndex() > -1) {
			vo.setZ(w.getMtPanel().getMtRowsPanel().getDimensionList()
					.getValue(
							w.getMtPanel().getMtRowsPanel().getDimensionList()
									.getSelectedIndex()));
			///
			String newZLabelShowTitle = getTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getzLabelShowTitle());
			//String pureZLabel = w.getMtPanel().getMtLookAndFeelPanel().getzLabel();
			
			//if (pureZLabel == null){
			String pureZLabel = w.getMtPanel().getMtRowsPanel()
				.getDimensionList().getItemText(
						w.getMtPanel().getMtRowsPanel()
								.getDimensionList().getSelectedIndex());
			//}
			vo.setZLabel(pureZLabel);

			if (newZLabelShowTitle == null){
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getzLabelShowTitle(), pureZLabel);
			}
			///
/*
			String newLabel = getTextFieldValue(w.getMtPanel()
					.getMtLookAndFeelPanel().getzLabel());

			/*
			 * if(newLabel!=null) vo.setZLabel(newLabel); else
			 * vo.setZLabel(w.getMtPanel
			 * ().getMtRowsPanel().getDimensionList().getItemText
			 * (w.getMtPanel().
			 * getMtRowsPanel().getDimensionList().getSelectedIndex()));
			 */
/*
			if (newLabel == null){
				String value = w.getMtPanel().getMtRowsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtRowsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getzLabel(), value);
				vo.setZLabel(value);
			}
			/*if (newLabel != null)
				vo.setZLabel(newLabel);
			else {
				String value = w.getMtPanel().getMtRowsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtRowsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getzLabel(), value);
				vo.setZLabel(value);
			}*/

			if(pureZLabel!=null){
			vo.setAggregateZAsMonthly(w.getMtPanel().getMtRowsPanel()
					.getAggregateMonthly().getValue());
			vo.setAggregateZAsYearly(w.getMtPanel().getMtRowsPanel()
					.getAggregateYearly().getValue());
			vo.setZLabels(labels(w.getMtPanel().getMtRowsPanel()));
			vo.setzUniqueValuesMap(uniqueValuesMap(w.getMtPanel()
					.getMtRowsPanel()));
		}
		}

		if (w.getMtPanel().getMtSubRowsPanel().getDimensionList()
				.getSelectedIndex() > -1) {
			vo.setW(w.getMtPanel().getMtSubRowsPanel().getDimensionList()
					.getValue(
							w.getMtPanel().getMtSubRowsPanel()
									.getDimensionList().getSelectedIndex()));

			///
			String newWLabelShowTitle = getTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getwLabelShowTitle());
			//String pureWLabel = w.getMtPanel().getMtLookAndFeelPanel().getwLabel();
			
		//	if (pureWLabel == null){
			String pureWLabel = w.getMtPanel().getMtSubRowsPanel()
				.getDimensionList().getItemText(
						w.getMtPanel().getMtSubRowsPanel()
								.getDimensionList().getSelectedIndex());
		//	}
			vo.setWLabel(pureWLabel);

			if (newWLabelShowTitle == null){
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getwLabelShowTitle(), pureWLabel);
			}
			///
			/*String newLabel = getTextFieldValue(w.getMtPanel()
					.getMtLookAndFeelPanel().getwLabel());

			/*
			 * if(newLabel!=null) vo.setWLabel(newLabel); else
			 * vo.setWLabel(w.getMtPanel
			 * ().getMtSubRowsPanel().getDimensionList()
			 * .getItemText(w.getMtPanel
			 * ().getMtSubRowsPanel().getDimensionList().getSelectedIndex()));
			 */
/*
			if (newLabel == null){
				String value = w.getMtPanel().getMtSubRowsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtSubRowsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getwLabel(), value);
				vo.setWLabel(value);
			}
			/*if (newLabel != null)
				vo.setWLabel(newLabel);
			else {
				String value = w.getMtPanel().getMtSubRowsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtSubRowsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getwLabel(), value);
				vo.setWLabel(value);
			}*/
			if(pureWLabel!=null){
			vo.setAggregateWAsMonthly(w.getMtPanel().getMtSubRowsPanel()
					.getAggregateMonthly().getValue());
			vo.setAggregateWAsYearly(w.getMtPanel().getMtSubRowsPanel()
					.getAggregateYearly().getValue());
			vo.setWLabels(labels(w.getMtPanel().getMtSubRowsPanel()));
			vo.setwUniqueValuesMap(uniqueValuesMap(w.getMtPanel()
					.getMtSubRowsPanel()));
			}
		}

		if (w.getMtPanel().getMtSubColumnsPanel().getDimensionList()
				.getSelectedIndex() > -1) {
			vo.setY(w.getMtPanel().getMtSubColumnsPanel().getDimensionList()
					.getValue(
							w.getMtPanel().getMtSubColumnsPanel()
									.getDimensionList().getSelectedIndex()));
			///
			String newYLabelShowTitle = getTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getyLabelShowTitle());
			//String pureYLabel = w.getMtPanel().getMtLookAndFeelPanel().getyLabel();
			
			//if (pureYLabel == null){
			String pureYLabel = w.getMtPanel().getMtSubColumnsPanel()
				.getDimensionList().getItemText(
						w.getMtPanel().getMtSubColumnsPanel()
								.getDimensionList().getSelectedIndex());
		//	}
			vo.setYLabel(pureYLabel);

			if (newYLabelShowTitle == null){
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel().getyLabelShowTitle(), pureYLabel);
			}
			///
/*
			String newLabel = getTextFieldValue(w.getMtPanel()
					.getMtLookAndFeelPanel().getyLabel());

			/*
			 * if(newLabel!=null) vo.setYLabel(newLabel); else
			 * vo.setYLabel(w.getMtPanel
			 * ().getMtSubColumnsPanel().getDimensionList
			 * ().getItemText(w.getMtPanel
			 * ().getMtSubColumnsPanel().getDimensionList
			 * ().getSelectedIndex()));
			 */
/*
			if (newLabel == null){
				String value = w.getMtPanel().getMtSubColumnsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtSubColumnsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getyLabel(), value);
				vo.setYLabel(value);
			}
			/*
			if (newLabel != null)
				vo.setYLabel(newLabel);
			else {
				String value = w.getMtPanel().getMtSubColumnsPanel()
						.getDimensionList().getItemText(
								w.getMtPanel().getMtSubColumnsPanel()
										.getDimensionList().getSelectedIndex());
				setTextFieldValue(w.getMtPanel().getMtLookAndFeelPanel()
						.getyLabel(), value);
				vo.setYLabel(value);
			}*/

			if(pureYLabel!=null){
				vo.setAggregateYAsMonthly(w.getMtPanel().getMtSubColumnsPanel()
						.getAggregateMonthly().getValue());
				vo.setAggregateYAsYearly(w.getMtPanel().getMtSubColumnsPanel()
						.getAggregateYearly().getValue());
				vo.setYLabels(labels(w.getMtPanel().getMtSubColumnsPanel()));
				vo.setyUniqueValuesMap(uniqueValuesMap(w.getMtPanel()
						.getMtSubColumnsPanel()));
			}
		}
		//mackx
		vo.setwLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getwLabelShowTitle().getValue());
		vo.setxLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getxLabelShowTitle().getValue());
		vo.setyLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getyLabelShowTitle().getValue());
		vo.setzLabelShowTitle(w.getMtPanel().getMtLookAndFeelPanel().getzLabelShowTitle().getValue());
		//mackx off

		vo.setColHeaderColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getColumnBackgroundColor().getText());
		vo.setColFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getColumnFontColor().getText());
		vo.setRowHeaderColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getRowBackgroundColor().getText());
		vo.setRowFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getRowFontColor().getText());
		vo.setSubColHeaderColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSubColumnBackgroundColor().getText());
		vo.setSubColFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSubColumnFontColor().getText());
		vo.setSubRowHeaderColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSubRowBackgroundColor().getText());
		vo.setSubRowFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSubRowFontColor().getText());
		vo.setContentBackgroundColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getContentsBackgroundColor().getText());
		vo.setContentFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getContentsFontColor().getText());
		vo.setFontFamily(w.getMtPanel().getMtLookAndFeelPanel().getFontFamily()
				.getValue(
						w.getMtPanel().getMtLookAndFeelPanel().getFontFamily()
								.getSelectedIndex()));
		vo.setFontSize(w.getMtPanel().getMtLookAndFeelPanel().getFontSize()
				.getValue(
						w.getMtPanel().getMtLookAndFeelPanel().getFontSize()
								.getSelectedIndex()));
		vo.setSummaryBackgroundColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSummaryBackgroundColor().getText());
		vo.setSummaryFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getSummaryFontColor().getText());
		vo.setOlapTitleFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getTitleColor().getText());
		vo.setOlapTitle(w.getMtPanel().getMtLookAndFeelPanel().getTitleLabel()
				.getValue());
		vo.setNotesFontColor(w.getMtPanel().getMtLookAndFeelPanel()
				.getNotesColor().getText());
		vo.setNotesTitle(w.getMtPanel().getMtLookAndFeelPanel().getNotesLabel()
				.getValue());

		// collect widths
		for (String key : w.getMtPanel().getMtLookAndFeelPanel()
				.getLabelWidthMap().keySet())
			vo.getColumnLabelsWidths().put(
					key,
					w.getMtPanel().getMtLookAndFeelPanel().getLabelWidthMap()
							.get(key).getValue()
							+ "px");

		// collect function and label (Z or W) width
		vo.setFunctionWidth(w.getMtPanel().getMtLookAndFeelPanel()
				.getFunctionWidth().getValue()
				+ "px");
		vo.setFunctionHeight(w.getMtPanel().getMtLookAndFeelPanel()
				.getFunctionHeigth().getValue()
				+ "px");
		vo.setColumnLabelWidth(w.getMtPanel().getMtLookAndFeelPanel()
				.getRowLabelWidth().getValue()
				+ "px");
		vo.setSubRowLabelWidth(w.getMtPanel().getMtLookAndFeelPanel()
				.getSubRowLabelWidth().getValue()
				+ "px");

		// collect heights
		for (String key : w.getMtPanel().getMtLookAndFeelPanel()
				.getLabelHeightMap().keySet())
			vo.getRowLabelsHeights().put(
					key,
					w.getMtPanel().getMtLookAndFeelPanel().getLabelHeightMap()
							.get(key).getValue()
							+ "px");

		// period type code
		vo.setPeriodTypeCode("daily"); // TODO implementation missing

		// collect filters
		vo.setFilters(getOLAPFilters(w));

		// x-axis functions on dates
		vo.setxUseFromDateToDate(w.getMtPanel().getMtColumnsPanel()
				.getUseFromDateToDate().getValue());
		vo.setxUseMostRecentData(w.getMtPanel().getMtColumnsPanel()
				.getUseMostRecentData().getValue());
		vo.setxFromDate(w.getMtPanel().getMtColumnsPanel().getFromDate()
				.getValue());
		vo
				.setxToDate(w.getMtPanel().getMtColumnsPanel().getToDate()
						.getValue());
		vo.setxLatestYears(Integer.valueOf(w.getMtPanel().getMtColumnsPanel()
				.getLatestYearsList().getValue(
						w.getMtPanel().getMtColumnsPanel().getLatestYearsList()
								.getSelectedIndex())));
		vo.setxLatestMonths(Integer.valueOf(w.getMtPanel().getMtColumnsPanel()
				.getLatestMonthsList().getValue(
						w.getMtPanel().getMtColumnsPanel()
								.getLatestMonthsList().getSelectedIndex())));
		vo.setxLatestDays(Integer.valueOf(w.getMtPanel().getMtColumnsPanel()
				.getLatestDaysList().getValue(
						w.getMtPanel().getMtColumnsPanel().getLatestDaysList()
								.getSelectedIndex())));

		// z-axis functions on dates
		vo.setzUseFromDateToDate(w.getMtPanel().getMtRowsPanel()
				.getUseFromDateToDate().getValue());
		vo.setzUseMostRecentData(w.getMtPanel().getMtRowsPanel()
				.getUseMostRecentData().getValue());
		vo.setzFromDate(w.getMtPanel().getMtRowsPanel().getFromDate()
				.getValue());
		vo.setzToDate(w.getMtPanel().getMtRowsPanel().getToDate().getValue());
		vo.setzLatestYears(Integer.valueOf(w.getMtPanel().getMtRowsPanel()
				.getLatestYearsList().getValue(
						w.getMtPanel().getMtRowsPanel().getLatestYearsList()
								.getSelectedIndex())));
		vo.setzLatestMonths(Integer.valueOf(w.getMtPanel().getMtRowsPanel()
				.getLatestMonthsList().getValue(
						w.getMtPanel().getMtRowsPanel().getLatestMonthsList()
								.getSelectedIndex())));
		vo.setzLatestDays(Integer.valueOf(w.getMtPanel().getMtRowsPanel()
				.getLatestDaysList().getValue(
						w.getMtPanel().getMtRowsPanel().getLatestDaysList()
								.getSelectedIndex())));

		// w-axis functions on dates
		vo.setwUseFromDateToDate(w.getMtPanel().getMtSubRowsPanel()
				.getUseFromDateToDate().getValue());
		vo.setwUseMostRecentData(w.getMtPanel().getMtSubRowsPanel()
				.getUseMostRecentData().getValue());
		vo.setwFromDate(w.getMtPanel().getMtSubRowsPanel().getFromDate()
				.getValue());
		vo
				.setwToDate(w.getMtPanel().getMtSubRowsPanel().getToDate()
						.getValue());
		vo.setwLatestYears(Integer.valueOf(w.getMtPanel().getMtSubRowsPanel()
				.getLatestYearsList().getValue(
						w.getMtPanel().getMtSubRowsPanel().getLatestYearsList()
								.getSelectedIndex())));
		vo.setwLatestMonths(Integer.valueOf(w.getMtPanel().getMtSubRowsPanel()
				.getLatestMonthsList().getValue(
						w.getMtPanel().getMtSubRowsPanel()
								.getLatestMonthsList().getSelectedIndex())));
		vo.setwLatestDays(Integer.valueOf(w.getMtPanel().getMtSubRowsPanel()
				.getLatestDaysList().getValue(
						w.getMtPanel().getMtSubRowsPanel().getLatestDaysList()
								.getSelectedIndex())));

		// y-axis functions on dates
		vo.setyUseFromDateToDate(w.getMtPanel().getMtSubColumnsPanel()
				.getUseFromDateToDate().getValue());
		vo.setyUseMostRecentData(w.getMtPanel().getMtSubColumnsPanel()
				.getUseMostRecentData().getValue());
		vo.setyFromDate(w.getMtPanel().getMtSubColumnsPanel().getFromDate()
				.getValue());
		vo.setyToDate(w.getMtPanel().getMtSubColumnsPanel().getToDate()
				.getValue());
		vo.setyLatestYears(Integer.valueOf(w.getMtPanel()
				.getMtSubColumnsPanel().getLatestYearsList().getValue(
						w.getMtPanel().getMtSubColumnsPanel()
								.getLatestYearsList().getSelectedIndex())));
		vo.setyLatestMonths(Integer.valueOf(w.getMtPanel()
				.getMtSubColumnsPanel().getLatestMonthsList().getValue(
						w.getMtPanel().getMtSubColumnsPanel()
								.getLatestMonthsList().getSelectedIndex())));
		vo.setyLatestDays(Integer.valueOf(w.getMtPanel().getMtSubColumnsPanel()
				.getLatestDaysList().getValue(
						w.getMtPanel().getMtSubColumnsPanel()
								.getLatestDaysList().getSelectedIndex())));

		vo.setOlapHtml(w.getMtPanel().getMtTablePanel().getHtml().toString());

		vo.setDatesFormat(w.getMtPanel().getMtLookAndFeelPanel()
				.getDatesFormat().getValue(
						w.getMtPanel().getMtLookAndFeelPanel().getDatesFormat()
								.getSelectedIndex()));

		vo.setVariationThreshold(w.getMtPanel().getMtLookAndFeelPanel()
				.getVariationThreshold().getValue());
		vo.setVariationThresholdRed(w.getMtPanel().getMtLookAndFeelPanel()
				.getVariationThresholdRed().getValue());
		vo.setVariationThresholdYellow(w.getMtPanel().getMtLookAndFeelPanel()
				.getVariationThresholdYellow().getValue());
		vo.setShowVariationArrows(w.getMtPanel().getMtLookAndFeelPanel()
				.getShowVariationArrows().getValue());

		return vo;
	}

	private static List<OLAPFilterVo> getOLAPFilters(final MTWindow w) {
		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
		for (VerticalPanel p : w.getMtPanel().getMtFiltersPanel().getPanels()) {
			ListBox l = (ListBox) p.getData("LIST");
			if (isFilterSelected(l)) {
				OLAPFilterVo f = new OLAPFilterVo();
				Map<String, String> m = new HashMap<String, String>();
				String header = (String) p.getData("HEADER");
				String contentDescriptor = (String) p
						.getData("CONTENT_DESCRIPTOR");
				f.setDimensionLabel(header);
				f.setDimension(contentDescriptor);
				for (int i = 0; i < l.getItemCount(); i++)
					if (l.isItemSelected(i))
						m.put(l.getValue(i), l.getItemText(i));
				f.setDimensionMap(m);
				filters.add(f);
			}
		}
		return filters;
	}

	private static boolean isFilterSelected(ListBox l) {
		for (int i = 0; i < l.getItemCount(); i++) {
			if (l.isItemSelected(i)) {
				return true;
			}
		}
		return false;
	}

	private static Long getDatasetID(MTWindow w) {
		for (MTDatasourceFieldSet fs : w.getMtPanel().getMtDatasourcePanel()
				.getFieldSets()) {
			if (fs.getUseThisDataset().getValue())
				return Long.valueOf((String) fs.getUseThisDataset().getData(
						"DATASET_ID"));
		}
		return null;
	}

	private static String getDatasetTitle(MTWindow w) {
		for (MTDatasourceFieldSet fs : w.getMtPanel().getMtDatasourcePanel()
				.getFieldSets()) {
			if (fs.getUseThisDataset().getValue())
				return (String) fs.getUseThisDataset().getData("DATASET_NAME");
		}
		return null;
	}
	
	private static MTFunctionPanel getFunctionPane(MTWindow w) {
		return w.getMtPanel().getMtFunctionPanel();
				
	}

	private static String getFunction(MTWindow w) {
		return w.getMtPanel().getMtFunctionPanel()
				.getAggregationFunctionListBox().getValue(
						w.getMtPanel().getMtFunctionPanel()
								.getAggregationFunctionListBox()
								.getSelectedIndex());
	}

	private static String getValue(MTWindow w) {
		for (MTDatasourceFieldSet fs : w.getMtPanel().getMtDatasourcePanel()
				.getFieldSets()) {
			if (fs.getUseThisDataset().getValue()) {
				for (ComboBox<DescriptorModelData> combo : fs
						.getDescriptorComboBoxes()) {
					for (DescriptorModelData md : combo.getSelection()) {
						return md.getContentDescriptor();
					}
				}
			}
		}
		return null;
	}

	private static String getValueLabel(MTWindow w) {
		for (MTDatasourceFieldSet fs : w.getMtPanel().getMtDatasourcePanel()
				.getFieldSets()) {
			if (fs.getUseThisDataset().getValue()) {
				for (ComboBox<DescriptorModelData> combo : fs
						.getDescriptorComboBoxes()) {
					for (DescriptorModelData md : combo.getSelection()) {
						return md.getHeader();
					}
				}
			}
		}
		return null;
	}

	private static List<OLAPFilterVo> getFilters(MTWindow w) {
		List<OLAPFilterVo> vos = new ArrayList<OLAPFilterVo>();
		for (VerticalPanel panel : w.getMtPanel().getMtFiltersPanel()
				.getPanels()) {
			String header = (String) panel.getData("HEADER");
			String contentDescriptor = (String) panel
					.getData("CONTENT_DESCRIPTOR");
			ListBox l = (ListBox) panel.getData("LIST");
			OLAPFilterVo vo = new OLAPFilterVo();
			vo.setDimension(contentDescriptor);
			vo.setDimensionLabel(header);
			Map<String, String> m = new HashMap<String, String>();
			for (int i = 0; i < l.getItemCount(); i++)
				if (l.isItemSelected(i))
					m.put(l.getValue(i), l.getItemText(i));
			vos.add(vo);
		}
		return vos;
	}

	private static Map<String, Map<String, String>> uniqueValuesMap(
			MTDimensionPanel p) {
		Map<String, Map<String, String>> m = new HashMap<String, Map<String, String>>();
		String header = p.getDimensionList().getItemText(
				p.getDimensionList().getSelectedIndex());
		Map<String, String> codes = new HashMap<String, String>();
		if (p.getUseFromDateToDate().getValue()) {
			Date a = p.getFromDate().getValue();
			Date c = p.getToDate().getValue();
			for (int i = 0; i < p.getValuesList().getItemCount(); i++) {
				if (p.getValuesList().isItemSelected(i)) {
					Date b = FieldParser.parseDate(p.getValuesList()
							.getValue(i));
					if ((a.compareTo(b) <= 0) && (b.compareTo(c) <= 0)) {
						codes.put(p.getValuesList().getValue(i), p
								.getValuesList().getItemText(i));
					}
				}
			}
		} else if (p.getUseMostRecentData().getValue()) {
			Date a = new Date();
			a.setYear(a.getYear()
					- Integer.valueOf(p.getLatestYearsList().getValue(
							p.getLatestYearsList().getSelectedIndex())));
			a.setMonth(a.getMonth()
					- Integer.valueOf(p.getLatestMonthsList().getValue(
							p.getLatestMonthsList().getSelectedIndex())));
			a.setDate(a.getDate()
					- (1 + Integer.valueOf(p.getLatestDaysList().getValue(
							p.getLatestDaysList().getSelectedIndex()))));
			for (int i = 0; i < p.getValuesList().getItemCount(); i++) {
				if (p.getValuesList().isItemSelected(i)) {
					Date b = FieldParser.parseDate(p.getValuesList()
							.getValue(i));
					if (a.compareTo(b) < 0) {
						codes.put(p.getValuesList().getValue(i), p
								.getValuesList().getItemText(i));
					}
				}
			}
		} else {
			for (int i = 0; i < p.getValuesList().getItemCount(); i++) {
				if (p.getValuesList().isItemSelected(i)) {
					codes.put(p.getValuesList().getValue(i), p.getValuesList()
							.getItemText(i));
				}
			}
		}
		m.put(header, codes);
		return m;
	}

	private static Map<String, String> labels(MTDimensionPanel p) {
		Map<String, String> codes = new HashMap<String, String>();
		for (int i = 0; i < p.getValuesList().getItemCount(); i++)
			if (p.getValuesList().isItemSelected(i))
				codes.put(p.getValuesList().getValue(i), p.getValuesList()
						.getItemText(i));
		return codes;
	}

}