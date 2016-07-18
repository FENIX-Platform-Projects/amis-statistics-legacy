package org.fao.fenix.web.modules.olap.server.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.olap.OLAPFilter;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

public class OLAPUtils {

	public static OLAPParameters vo2p(OLAPParametersVo vo) {

		OLAPParameters p = new OLAPParameters();

		p.setChartType(vo.getChartType());

		// p.setDatasetId(vo.getDataSourceId());
		// p.setDatasetTitle(vo.getDataSourceTitle());
		
		//mackx
		p.setShowColSummary(vo.getShowColSummary());
		p.setShowRowSummary(vo.getShowRowSummary());
		p.setColFunction(vo.getColFunction());
		p.setRowFunction(vo.getRowFunction());
		p.setShading1Color(vo.getShading1Color());
		p.setShading2Color(vo.getShading2Color());
		p.setShowBorder(vo.getShowBorder());
		p.setShowColSubject(vo.getShowColSubject());
		p.setShowGridLine(vo.getShowGridLine());
		p.setShowRowSubject(vo.getShowRowSubject());
		p.setShowShading(vo.getShowShading());
		p.setShowSubColSubject(vo.getShowSubColSubject());
		p.setShowSubColSummary(vo.getShowSubColSummary());
		p.setShowSubRowSubject(vo.getShowSubRowSubject());
		p.setShowSubRowSummary(vo.getShowSubRowSummary());
		p.setSubColFunction(vo.getSubColFunction());
		p.setSubRowFunction(vo.getSubRowFunction());
		p.setxLabelShowTitle(vo.getxLabelShowTitle());
		p.setyLabelShowTitle(vo.getyLabelShowTitle());
		p.setwLabelShowTitle(vo.getwLabelShowTitle());
		p.setzLabelShowTitle(vo.getzLabelShowTitle());
		//mackx off

		p.setDataSourceId(vo.getDataSourceId());
		p.setDataSourceTitle(vo.getDataSourceTitle());
		p.setDataSourceType(vo.getDataSourceType());

		for (OLAPFilterVo fvo : vo.getFilters())
			p.addFilter(vo2filter(fvo));

		p.setFunction(vo.getFunction());
		p.setShowSingleValues(vo.isShowSingleValues());
		p.setShowTotals(vo.isShowTotals());

		p.setValue(vo.getValue());
		p.setValueLabel(vo.getValueLabel());

		p.setX(vo.getX());
		p.setXLabel(vo.getXLabel());
		p.setXLabels(vo.getXLabels());

		p.setZ(vo.getZ());
		p.setZLabel(vo.getZLabel());
		p.setZLabels(vo.getZLabels());
		Logger.getRootLogger().warn(p.getZLabels().size() + " Z Labels");

		if (!vo.getYLabels().isEmpty()) {
			p.setY(vo.getY());
			p.setYLabel(vo.getYLabel());
			p.setYLabels(vo.getYLabels());
		}
		if (!vo.getWLabels().isEmpty()) {
			p.setW(vo.getW());
			p.setWLabel(vo.getWLabel());
			p.setWLabels(vo.getWLabels());
		}
		return p;
	}

	public static OLAPFilter vo2filter(OLAPFilterVo vo) {
		OLAPFilter f = new OLAPFilter();
		f.setDimension(vo.getDimension());
		f.setDimensionLabel(vo.getDimensionLabel());
		f.setDimensionMap(vo.getDimensionMap());
		return f;
	}

	public static Color getUserColor(String user) {
		OLAPColor color = OLAPColor.valueOf(user);
		switch (color) {
		case WHITE:
			return Color.WHITE;
		case LIGHT_GRAY:
			return Color.LIGHT_GRAY;
		case GRAY:
			return Color.GRAY;
		case DARK_GRAY:
			return Color.DARK_GRAY;
		case BLACK:
			return Color.BLACK;
		case RED:
			return Color.RED;
		case PINK:
			return Color.PINK;
		case ORANGE:
			return Color.ORANGE;
		case YELLOW:
			return Color.YELLOW;
		case GREEN:
			return Color.GREEN;
		case MAGENTA:
			return Color.MAGENTA;
		case CYAN:
			return Color.CYAN;
		case BLUE:
			return Color.BLUE;
		}
		return null;
	}

	public static OLAPParametersVo parameters2vo(OLAPParameters p) {
		OLAPParametersVo vo = new OLAPParametersVo();
		vo.setAggregateWAsMonthly(p.isAggregateWAsMonthly());
		vo.setAggregateWAsYearly(p.isAggregateWAsYearly());
		vo.setAggregateYAsMonthly(p.isAggregateYAsMonthly());
		vo.setAggregateYAsYearly(p.isAggregateYAsYearly());
		vo.setAggregateZAsMonthly(p.isAggregateZAsMonthly());
		vo.setAggregateZAsYearly(p.isAggregateZAsYearly());
		vo.setAggregateXAsMonthly(p.isAggregateXAsMonthly());
		vo.setAggregateXAsYearly(p.isAggregateXAsYearly());
		vo.setColFontColor(p.getColFontColor());
		vo.setColHeaderColor(p.getColHeaderColor());
		vo.setColumnLabelsWidths(p.getColumnLabelsWidths());
		vo.setColumnLabelWidth(p.getColumnLabelWidth());
		vo.setContentBackgroundColor(p.getContentBackgroundColor());
		vo.setContentFontColor(p.getContentFontColor());
		vo.setDataSourceId(p.getDataSourceId());
		vo.setDataSourceTitle(p.getDataSourceTitle());
		vo.setDataSourceType(p.getDataSourceType());
		vo.setDatesFormat(p.getDatesFormat());
		vo.setDecimals(p.getDecimals());
		vo.setDecimalsForTotals(p.getDecimalsForTotals());
		vo.setDecimalsForVariation(p.getDecimalsForVariation());
		vo.setFilters(convert(p.getFilters()));
		vo.setFontFamily(p.getFontFamily());
		vo.setFontSize(p.getFontSize());
		vo.setFunction(p.getFunction());
		vo.setFunctionWidth(p.getFunctionWidth());
		vo.setOlapHtml(p.getOlapHtml());
		vo.setRowFontColor(p.getRowFontColor());
		vo.setRowHeaderColor(p.getRowHeaderColor());
		vo.setRowLabelsHeights(p.getRowLabelsHeights());
		vo.setShowColVariation(p.isShowColVariation());
		
		//mackx
		vo.setShowColSummary(p.getShowColSummary());
		vo.setShowRowSummary(p.getShowRowSummary());
		vo.setColFunction(p.getColFunction());
		vo.setRowFunction(p.getRowFunction());
		vo.setShading1Color(p.getShading1Color());
		vo.setShading2Color(p.getShading2Color());
		vo.setShowBorder(p.getShowBorder());
		vo.setShowColSubject(p.getShowColSubject());
		vo.setShowGridLine(p.getShowGridLine());
		vo.setShowRowSubject(p.getShowRowSubject());
		vo.setShowShading(p.getShowShading());
		vo.setShowSubColSubject(p.getShowSubColSubject());
		vo.setShowSubColSummary(p.getShowSubColSummary());
		vo.setShowSubRowSubject(p.getShowSubRowSubject());
		vo.setShowSubRowSummary(p.getShowSubRowSummary());
		vo.setSubColFunction(p.getSubColFunction());
		vo.setSubRowFunction(p.getSubRowFunction());
		vo.setxLabelShowTitle(p.getxLabelShowTitle());
		vo.setyLabelShowTitle(p.getyLabelShowTitle());
		vo.setwLabelShowTitle(p.getwLabelShowTitle());
		vo.setzLabelShowTitle(p.getzLabelShowTitle());
		//mackx off

		vo.setShowRowVariation(p.isShowRowVariation());
		vo.setShowSingleValues(p.isShowSingleValues());
		vo.setShowTotals(p.isShowTotals());
		vo.setOlapTitle(p.getOlapTitle());
		vo.setOlapTitleFontColor(p.getOlapTitleFontColor());
		vo.setNotesTitle(p.getNotesTitle());
		vo.setNotesFontColor(p.getNotesFontColor());
		vo.setX(p.getX());
		vo.setxFromDate(p.getxFromDate());
		vo.setXLabel(p.getXLabel());
		vo.setXLabels(p.getXLabels());
		vo.setxLatestDays(p.getxLatestDays());
		vo.setxLatestMonths(p.getxLatestMonths());
		vo.setxLatestYears(p.getxLatestYears());
		vo.setxToDate(p.getxToDate());
		vo.setxUseFromDateToDate(p.isxUseFromDateToDate());
		vo.setxUseMostRecentData(p.isxUseMostRecentData());
		vo.setZ(p.getZ());
		vo.setzFromDate(p.getzFromDate());
		vo.setZLabel(p.getZLabel());
		vo.setZLabels(p.getZLabels());
		vo.setzLatestDays(p.getzLatestDays());
		vo.setzLatestMonths(p.getzLatestMonths());
		vo.setzLatestYears(p.getzLatestYears());
		vo.setzToDate(p.getzToDate());
		vo.setzUseFromDateToDate(p.iszUseFromDateToDate());
		vo.setzUseMostRecentData(p.iszUseMostRecentData());
		vo.setW(p.getW());
		vo.setwFromDate(p.getwFromDate());
		vo.setWLabel(p.getWLabel());
		vo.setWLabels(p.getWLabels());
		vo.setwLatestDays(p.getwLatestDays());
		vo.setwLatestMonths(p.getwLatestMonths());
		vo.setwLatestYears(p.getwLatestYears());
		vo.setwToDate(p.getwToDate());
		vo.setwUseFromDateToDate(p.iswUseFromDateToDate());
		vo.setwUseMostRecentData(p.iswUseMostRecentData());
		vo.setY(p.getY());
		vo.setyFromDate(p.getyFromDate());
		vo.setYLabel(p.getYLabel());
		vo.setYLabels(p.getYLabels());
		vo.setyLatestDays(p.getyLatestDays());
		vo.setyLatestMonths(p.getyLatestMonths());
		vo.setyLatestYears(p.getyLatestYears());
		vo.setyToDate(p.getyToDate());
		vo.setyUseFromDateToDate(p.isyUseFromDateToDate());
		vo.setyUseMostRecentData(p.isyUseMostRecentData());
		vo.setResourceViewID(p.getResourceViewID());
		vo.setSummaryBackgroundColor(p.getSummaryBackgroundColor());
		vo.setSummaryFontColor(p.getSummaryFontColor());
		vo.setSubColFontColor(p.getSubColFontColor());
		vo.setSubColHeaderColor(p.getSubColHeaderColor());
		vo.setSubRowFontColor(p.getSubRowFontColor());
		vo.setSubRowHeaderColor(p.getSubRowHeaderColor());
		vo.setSubRowLabelWidth(p.getSubRowLabelWidth());
		vo.setColumnLabelsWidths(p.getColumnLabelsWidths());
		vo.setRowLabelsHeights(p.getRowLabelsHeights());
		vo.setVariationThreshold(p.getVariationThreshold());
		vo.setVariationThresholdRed(p.getVariationThresholdRed());
		vo.setVariationThresholdYellow(p.getVariationThresholdYellow());
		vo.setShowVariationArrows(p.getShowVariationArrows());

		return vo;
	}

	private static List<OLAPFilterVo> convert(List<OLAPFilter> filters) {
		List<OLAPFilterVo> l = new ArrayList<OLAPFilterVo>();
		for (OLAPFilter f : filters) {
			OLAPFilterVo vo = new OLAPFilterVo();
			vo.setDimension(f.getDimension());
			vo.setDimensionLabel(f.getDimensionLabel());
			vo.setDimensionMap(f.getDimensionMap());
			l.add(vo);
		}
		return l;
	}

	private enum OLAPColor {
		WHITE, LIGHT_GRAY, GRAY, DARK_GRAY, BLACK, RED, PINK, ORANGE, YELLOW, GREEN, MAGENTA, CYAN, BLUE;
	}

}
