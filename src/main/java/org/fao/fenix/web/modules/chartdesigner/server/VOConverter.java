package org.fao.fenix.web.modules.chartdesigner.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.core.domain.chartdesigner.ChartDesignerFilter;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerFilterCodeLabel;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerHighlight;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerParameters;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerPlotDimension;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerSeriesColor;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerXCodeLabel;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerXContentDescriptor;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerYAxisParameters;
import org.fao.fenix.core.domain.chartdesigner.ChartDesignerYCodeLabel;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.CustomDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.resourceview.BooleanResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.DescriptorView;
import org.fao.fenix.core.domain.resourceview.NumericResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.resourceview.ResourceViewSetting;
import org.fao.fenix.core.domain.resourceview.StringResourceViewSetting;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerFilterVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerHighlightVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerYAxisParametersVO;
import org.fao.fenix.web.modules.designer.common.vo.DesignerConstants;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

public class VOConverter {
	
	public static ChartDesignerParameters vo2parameters(ChartDesignerParametersVO vo) {
		ChartDesignerParameters p = new ChartDesignerParameters();
		p.setAggregationFunction(vo.getAggregationFunction());
		p.setChartType(vo.getChartType());
		p.setLegendColor(vo.getLegendColor());
		p.setLegendFontFamily(vo.getLegendFontFamily());
		p.setLegendLabel(vo.getLegendLabel());
		p.setLegendSize(vo.getLegendSize());
		p.setPlotDimensionMap(vo2plotDimensionMap(vo));
		p.setTitleColor(vo.getTitleColor());
		p.setTitleFontFamily(vo.getTitleFontFamily());
		p.setTitleLabel(vo.getTitleLabel());
		p.setTitleSize(vo.getTitleSize());
		p.setxCodeLabelMap(vo2XCodeLabelMap(vo));
		p.setxColor(vo.getxColor());
		p.setxContentDescriptors(vo2XContentDescriptors(vo));
		p.setxFontFamily(vo.getxFontFamily());
		p.setxFromDate(vo.getxFromDate());
		p.setxGrid(vo.isxGrid());
		p.setxLabel(vo.getxLabel());
		p.setxLatestDays(vo.getxLatestDays());
		p.setxLatestMonths(vo.getxLatestMonths());
		p.setxLatestYears(vo.getxLatestYears());
		p.setxMonthlyAggregated(vo.isxMonthlyAggregated());
		p.setxOrientation(vo.getxOrientation());
		p.setxSize(vo.getxSize());
		p.setxToDate(vo.getxToDate());
		p.setxUseFromDateToDate(vo.isxUseFromDateToDate());
		p.setxUseMostRecentData(vo.isxUseMostRecentData());
		p.setxYearlyAggregated(vo.isxYearlyAggregated());
		p.setImageHeight(vo.getImageHeight());
		p.setImageWidth(vo.getImageWidth());
		p.setImagePath(vo.getImagePath());
		if (vo.getFilters() != null) {
			for (ChartDesignerFilterVO fvo : vo.getFilters())
				p.addFilter(vo2filter(fvo));
		}
		if (vo.getHighlights() != null) {
			for (ChartDesignerHighlightVO hvo : vo.getHighlights())
				p.addHighlight(vo2Highlight(hvo));
		}
		if (vo.getyAxes() != null) {
			for (ChartDesignerYAxisParametersVO yvo : vo.getyAxes())
				p.addYAxis(vo2axis(yvo));
		}
		p.setxGrid(vo.isxGrid());
		p.setxGridColor(vo.getxGridColor());
		p.setxGridLineStyle(vo.getxGridLineStyle());
		p.setLegendPosition(vo.getLegendPosition());
		p.setxAxisNumberOfIntervals(vo.getxAxisNumberOfIntervals());
		p.setxAxisColor(vo.getxAxisColor());
		p.setxAxisTicksColor(vo.getxAxisTicksColor());
		p.setSubTitle(vo.getSubTitle());
		p.setSubTitleColor(vo.getSubTitleColor());
		p.setSubTitleFontFamily(vo.getSubTitleFontFamily());
		p.setSubTitleLabel(vo.getSubTitleLabel());
		p.setSubTitleSize(vo.getSubTitleSize());
		p.setShowLegend(vo.isShowLegend());
		p.setShowBorder(vo.isShowBorder());
		p.setxLimFactor(vo.getxLimFactor());
		p.setxValuesColor(vo.getxValuesColor());
		p.setxValuesFontFamily(vo.getxValuesFontFamily());
		p.setxValuesSize(vo.getxValuesSize());
		p.setLabelsDistanceFromAxis(vo.getLabelsDistanceFromAxis());
		p.setLegendHorizontal(vo.isLegendHorizontal());
		p.setxAxisEquidistantDates(vo.isxAxisEquidistantDates());
		p.setDatesFormat(vo.getDatesFormat());
		return p;
	}
	
	public static ChartDesignerParametersVO parameters2VO(ChartDesignerParameters p) {
		ChartDesignerParametersVO vo = new ChartDesignerParametersVO();
		vo.setDatasourceIDs(datasets2LongList(p.getDatasourceIDs()));
		vo.setAggregationFunction(p.getAggregationFunction());
		vo.setChartType(p.getChartType());
		vo.setLegendColor(p.getLegendColor());
		vo.setLegendFontFamily(p.getLegendFontFamily());
		vo.setLegendLabel(p.getLegendLabel());
		vo.setLegendSize(p.getLegendSize());
		vo.setPlotDimensionMap(codes2LongStringMap(p.getPlotDimensionMap()));
		vo.setTitleColor(p.getTitleColor());
		vo.setTitleFontFamily(p.getTitleFontFamily());
		vo.setTitleLabel(p.getTitleLabel());
		vo.setTitleSize(p.getTitleSize());
		vo.setxCodes(codes2StringList(p.getxCodeLabelMap()));
		vo.setxColor(p.getxColor());
		vo.setxContentDescriptors(contentDescriptor2StringList(p.getxContentDescriptors()));
		vo.setxFontFamily(p.getxFontFamily());
		vo.setxFromDate(p.getxFromDate());
		vo.setxGrid(p.getxGrid());
		vo.setxLabel(p.getxLabel());
		vo.setxLatestDays(p.getxLatestDays());
		vo.setxLatestMonths(p.getxLatestMonths());
		vo.setxLatestYears(p.getxLatestYears());
		vo.setxMonthlyAggregated(p.getxMonthlyAggregated());
		vo.setxOrientation(p.getxOrientation());
		vo.setxSize(p.getxSize());
		vo.setxToDate(p.getxToDate());
		vo.setxUseFromDateToDate(p.getxUseFromDateToDate());
		vo.setxUseMostRecentData(p.getxUseMostRecentData());
		vo.setxYearlyAggregated(p.getxYearlyAggregated());
		vo.setImageHeight(p.getImageHeight());
		vo.setImageWidth(p.getImageWidth());
		vo.setImagePath(p.getImagePath());
		if (p.getFilters() != null) {
			for (ChartDesignerFilter f : p.getFilters())
				vo.addFilter(filter2VO(f));
		}
		if (p.getHighlights() != null) {
			for (ChartDesignerHighlight h : p.getHighlights())
				vo.addHighlight(highlight2VO(h));
		}
		if (p.getyAxes() != null) {
			for (ChartDesignerYAxisParameters yap : p.getyAxes())
				vo.addYAxis(yAxes2VO(yap));
		}
		vo.setxGrid(p.getxGrid());
		vo.setxGridColor(p.getxGridColor());
		vo.setxGridLineStyle(p.getxGridLineStyle());
		vo.setLegendPosition(p.getLegendPosition());
		vo.setxAxisNumberOfIntervals(p.getxAxisNumberOfIntervals());
		vo.setxAxisColor(p.getxAxisColor());
		vo.setxAxisTicksColor(p.getxAxisTicksColor());
		vo.setSubTitle(p.getSubTitle());
		vo.setSubTitleColor(p.getSubTitleColor());
		vo.setSubTitleFontFamily(p.getSubTitleFontFamily());
		vo.setSubTitleLabel(p.getSubTitleLabel());
		vo.setSubTitleSize(p.getSubTitleSize());
		vo.setShowLegend(p.getShowLegend());
		//mackx
		try {
			vo.setShowBorder(p.getShowBorder());
		} catch (Exception e) {
			vo.setShowBorder(false);
		}
		vo.setxLimFactor(p.getxLimFactor());
		vo.setxValuesColor(p.getxValuesColor());
		vo.setxValuesFontFamily(p.getxValuesFontFamily());
		vo.setxValuesSize(p.getxValuesSize());
		vo.setLabelsDistanceFromAxis(p.getLabelsDistanceFromAxis());
		vo.setLegendHorizontal(p.getLegendHorizontal());
		vo.setxAxisEquidistantDates(p.isxAxisEquidistantDates());
		vo.setDatesFormat(p.getDatesFormat());
		return vo;
	}
	
	public static ChartDesignerYAxisParameters vo2axis(ChartDesignerYAxisParametersVO vo) {
		ChartDesignerYAxisParameters y = new ChartDesignerYAxisParameters();
		y.setAxisColor(vo.getAxisColor());
		y.setAxisPosition(vo.getAxisPosition());
		y.setAxisTitle(vo.getAxisTitle());
		y.setContentDescriptor(vo.getContentDescriptor());
		y.setDatasetID(vo.getDatasetID());
		y.setFontColor(vo.getFontColor());
		y.setFontSize(vo.getFontSize());
		y.setFontStyle(vo.getFontStyle());
		y.setFromDate(vo.getFromDate());
		y.setFromDateToDate(vo.isFromDateToDate());
		y.setHeader(vo.getHeader());
		y.setLabelOrientation(vo.getLabelOrientation());
		y.setLatestDays(vo.getLatestDays());
		y.setLatestMonths(vo.getLatestMonths());
		y.setLatestYears(vo.getLatestYears());
		y.setyMin(vo.getyMin());
		y.setyMax(vo.getyMax());
		y.setMonthlyAggregated(vo.isMonthlyAggregated());
		y.setMostRecentData(vo.isMostRecentData());
		y.setSeriesColorMap(vo2SeriesColorMap(vo));
		y.setStep(vo.getStep());
		y.setTicksColor(vo.getTicksColor());
		y.setToDate(vo.getToDate());
		y.setyCodeLabelMap(vo2YCodeLabelMap(vo));
		y.setYearlyAggregated(vo.isYearlyAggregated());
		y.setScaleType(vo.getScaleType());
		y.setValuesFontColor(vo.getValuesFontColor());
		y.setValuesFontSize(vo.getValuesFontSize());
		y.setValuesFontStyle(vo.getValuesFontStyle());
		y.setLineType(vo.getLineType());
		y.setLineWidth(vo.getLineWidth());
		y.setyGrid(vo.isyGrid());
		y.setyGridColor(vo.getyGridColor());
		y.setyGridLineStyle(vo.getyGridLineStyle());
		y.setyAxisEquidistantDates(vo.isyAxisEquidistantDates());
		y.setStacked(vo.isStacked());
		y.setPointType(vo.getPointType());
		return y;
	}
	
	public static ChartDesignerYAxisParametersVO yAxes2VO(ChartDesignerYAxisParameters yap) {
		ChartDesignerYAxisParametersVO vo = new ChartDesignerYAxisParametersVO();
		vo.setAxisColor(yap.getAxisColor());
		vo.setAxisPosition(yap.getAxisPosition());
		vo.setAxisTitle(yap.getAxisTitle());
		vo.setContentDescriptor(yap.getContentDescriptor());
		vo.setDatasetID(yap.getDatasetID());
		vo.setFontColor(yap.getFontColor());
		vo.setFontSize(yap.getFontSize());
		vo.setFontStyle(yap.getFontStyle());
		vo.setFromDate(yap.getFromDate());
		vo.setFromDateToDate(yap.getFromDateToDate());
		vo.setHeader(yap.getHeader());
		vo.setLabelOrientation(yap.getLabelOrientation());
		vo.setLatestDays(yap.getLatestDays());
		vo.setLatestMonths(yap.getLatestMonths());
		vo.setLatestYears(yap.getLatestYears());
		vo.setyMin(yap.getyMin());
		vo.setyMax(yap.getyMax());
		vo.setMonthlyAggregated(yap.getMonthlyAggregated());
		vo.setMostRecentData(yap.getMostRecentData());
		vo.setSeriesColorsMap(seriesColor2StringStringMap(yap.getSeriesColorMap()));
		vo.setHeaderYCodeMap(headerYCode2StringStringMap(yap.getSeriesColorMap()));
		vo.setStep(yap.getStep());
		vo.setTicksColor(yap.getTicksColor());
		vo.setToDate(yap.getToDate());
		vo.setyCodes(yCode2StringStringMap(yap.getyCodeLabelMap()));
		vo.setYearlyAggregated(yap.getYearlyAggregated());
		vo.setScaleType(yap.getScaleType());
		vo.setValuesFontColor(yap.getValuesFontColor());
		vo.setValuesFontSize(yap.getValuesFontSize());
		vo.setValuesFontStyle(yap.getValuesFontStyle());
		vo.setLineType(yap.getLineType());
		vo.setLineWidth(yap.getLineWidth());
		vo.setyGrid(yap.getyGrid());
		vo.setyGridColor(yap.getyGridColor());
		vo.setyGridLineStyle(yap.getyGridLineStyle());
		vo.setyAxisEquidistantDates(yap.isyAxisEquidistantDates());
		if (yap.getStacked() != null) {
			vo.setStacked(yap.getStacked());
		} else {
			vo.setStacked(false);
		}
		if (yap.getPointType() != null) {
			vo.setPointType(yap.getPointType());
		} else {
			vo.setPointType("1");
		}
		return vo;
	}
	
	public static ChartDesignerFilter vo2filter(ChartDesignerFilterVO vo) {
		ChartDesignerFilter f = new ChartDesignerFilter();
		f.setCodeLabelMap(vo2CodeLabelMap(vo));
		f.setContentDescriptor(vo.getContentDescriptor());
		f.setDatasetID(vo.getDatasetID());
		f.setHeader(vo.getHeader());
		return f;
	}
	
	public static ChartDesignerFilterVO filter2VO(ChartDesignerFilter f) {
		ChartDesignerFilterVO vo = new ChartDesignerFilterVO();
		vo.setCodes(filterCodes2StringList(f.getCodeLabelMap()));
		vo.setContentDescriptor(f.getContentDescriptor());
		vo.setDatasetID(f.getDatasetID());
		vo.setHeader(f.getHeader());
		return vo;
	}
	
	public static ChartDesignerHighlight vo2Highlight(ChartDesignerHighlightVO vo) {
		ChartDesignerHighlight h = new ChartDesignerHighlight();
		h.setColor(vo.getColor());
		h.setDatasetID(vo.getDatasetID());
		h.setHighlightType(vo.getHighlightType());
		h.setShow(vo.isShow());
		h.setSize(vo.getSize());
		h.setStyle(vo.getStyle());
		h.setSymbol(vo.getSymbol());
		h.setAlias(vo.getAlias());
		return h;
	}
	
	public static ChartDesignerHighlightVO highlight2VO(ChartDesignerHighlight h) {
		ChartDesignerHighlightVO vo = new ChartDesignerHighlightVO();
		vo.setColor(h.getColor());
		vo.setDatasetID(h.getDatasetID());
		vo.setHighlightType(h.getHighlightType());
		vo.setShow(h.isShow());
		vo.setSize(h.getSize());
		vo.setStyle(h.getStyle());
		vo.setSymbol(h.getSymbol());
		vo.setAlias(h.getAlias());
		return vo;
	}

	public static ResourceView vo2ResourceView(ResourceViewVO vo) {
		ResourceView r = new ResourceView();
		r.setTitle(vo.getTitle());
		r.setOlapHTML(vo.getOlapHTML());
		r.setOlapFunction(vo.getOlapFunction());
		r.setResourceId(vo.getResourceId());
		r.setType(vo.getType());
		r.setQuery(vo.getQuery());
		r.setOlapFunction(vo.getOlapFunction());
		r.setResourceId(vo.getResourceId());
		if ((vo.getDatasets() != null) && (!vo.getDatasets().isEmpty()))
			for (DatasetVO dvo : vo.getDatasets()) 
				r.addDataset(vo2coreDataset(dvo));
		if ((vo.getDescriptorViews() != null) && (!vo.getDescriptorViews().isEmpty()))
			for (DescriptorViewVO dvvo : vo.getDescriptorViews())
				r.addDescriptorView(vo2descriptor(dvvo));
		if ((vo.getSettings() != null) && (!vo.getSettings().isEmpty())) {
			for (ResourceViewSettingVO svo : vo.getSettings()) {
				DesignerConstants resourceViewSettingType = DesignerConstants.valueOf(svo.getResourceViewSettingType());
				switch (resourceViewSettingType) {
					case NUMERIC: r.addResourceViewSetting(vo2numericSetting(svo)); break;
					case STRING: r.addResourceViewSetting(vo2stringSetting(svo)); break;
					case BOOLEAN: r.addResourceViewSetting(vo2booleanSetting(svo)); break;
				}
			}
		}
		return r;
	}
	
	public static CoreDataset vo2coreDataset(DatasetVO vo) {
		CoreDataset d = new CoreDataset();
		d.setTitle(vo.getDatasetName());
		d.setResourceId(Long.valueOf(vo.getDsId()));
		return d;
	}
	
	public static CustomDataset vo2customDataset(DatasetVO vo) {
		CustomDataset d = new CustomDataset();
		d.setTitle(vo.getDatasetName());
		d.setResourceId(Long.valueOf(vo.getDsId()));
		return d;
	}
	
	public static DescriptorView vo2descriptor(DescriptorViewVO vo) {
		DescriptorView d = new DescriptorView();
		d.setContentDescriptor(vo.getContentDescriptor());
		d.setHeader(vo.getHeader());
		return d;
	}
	
	public static StringResourceViewSetting vo2stringSetting(ResourceViewSettingVO vo) {
		StringResourceViewSetting r = new StringResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setValue(vo.getValue());
		return r;
	}
	
	public static NumericResourceViewSetting vo2numericSetting(ResourceViewSettingVO vo) {
		NumericResourceViewSetting r = new NumericResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setQuantity(Double.valueOf(vo.getValue()));
		return r;
	}
	
	public static BooleanResourceViewSetting vo2booleanSetting(ResourceViewSettingVO vo) {
		BooleanResourceViewSetting r = new BooleanResourceViewSetting();
		r.setId(vo.getId());
		r.setSettingName(vo.getSettingName());
		r.setSelected(Boolean.valueOf(vo.getValue()));
		return r;
	}
	
	public static ResourceViewVO resourceView2vo(ResourceView r) {
		
		ResourceViewVO vo = new ResourceViewVO();
		
		if (r.getTitle() != null)
			vo.setTitle(r.getTitle());
		if (r.getOlapHTML() != null)
		vo.setOlapHTML(r.getOlapHTML());
		if (r.getOlapFunction() != null)
		vo.setOlapFunction(r.getOlapFunction());
		if (r.getResourceId() != null)
		vo.setResourceId(r.getResourceId());
		if (r.getQuery() != null)
		vo.setQuery(r.getQuery());
		if (r.getType() != null)
		vo.setType(r.getType());
		
		List<ResourceViewSettingVO> settings = new ArrayList<ResourceViewSettingVO>();
		if (r.getSettings() != null) {
			for (ResourceViewSetting s : r.getSettings()) {
				settings.add(setting2vo(s));
			}
		}
		
		List<DescriptorViewVO> descriptors = new ArrayList<DescriptorViewVO>();
		if (r.getDescriptorViews() != null) {
			for (DescriptorView dv : r.getDescriptorViews()) {
				DescriptorViewVO dvvo = descriptor2vo(dv);
				descriptors.add(dvvo);
			}
		}
		
		List<DatasetVO> datasets = new ArrayList<DatasetVO>();
		if (r.getDatasets() != null) {
			for (Dataset d : r.getDatasets()) { 
				DatasetVO dvo = dataset2vo(d, false);
				for (DescriptorViewVO dvvo : descriptors)
					dvo.addDescriptorViewVO(dvvo);
				datasets.add(dvo);
			}
		}
		
		return vo;
	}
	
	public static ResourceViewVO resourceView2vo(ResourceView r, Map<Descriptor, List<UniqueTextValues>> textValues, Map<Descriptor, List<UniqueDateValues>> dateValues) {
		
		ResourceViewVO vo = new ResourceViewVO();
		
		if (r.getTitle() != null)
			vo.setTitle(r.getTitle());
		if (r.getResourceId() != null)
			vo.setResourceId(r.getResourceId());
		if (r.getType() != null)
			vo.setType(r.getType());
		
		List<ResourceViewSettingVO> settings = new ArrayList<ResourceViewSettingVO>();
		if (r.getSettings() != null) {
			for (ResourceViewSetting s : r.getSettings()) {
				settings.add(setting2vo(s));
			}
		}
		
		List<DescriptorViewVO> descriptors = new ArrayList<DescriptorViewVO>();
		if (!textValues.isEmpty()) {
			for (Descriptor d : textValues.keySet()) {
				DescriptorViewVO dvvo = descriptor2vo(d);
				for (UniqueTextValues t : textValues.get(d))
					dvvo.addUniqueValue(uniqueTextValue2vo(t));
				descriptors.add(dvvo);
			}
		}
		if (!dateValues.isEmpty()) {
			for (Descriptor d : dateValues.keySet()) {
				DescriptorViewVO dvvo = descriptor2vo(d);
				for (UniqueDateValues t : dateValues.get(d))
					dvvo.addUniqueValue(uniqueDateValue2vo(t));
				descriptors.add(dvvo);
			}
		}
		
		List<DatasetVO> datasets = new ArrayList<DatasetVO>();
		if (r.getDatasets() != null) {
			for (Dataset d : r.getDatasets()) { 
				DatasetVO dvo = dataset2vo(d, false);
				for (DescriptorViewVO dvvo : descriptors)
					dvo.addDescriptorViewVO(dvvo);
				datasets.add(dvo);
			}
		}
		
		return vo;
	}
	
	public static DatasetVO dataset2vo(Dataset d, boolean addDescriptors) {
		DatasetVO vo = new DatasetVO();
		vo.setDatasetName(d.getTitle());
		vo.setDsId(String.valueOf(d.getResourceId()));
		if (d.getSource() != null) {
			if (d.getSource().getTitle() != null)
				vo.setSourceName(d.getSource().getTitle());
			if (d.getSource().getCountry() != null)
				vo.setSourceRegion(d.getSource().getCountry());
			if (d.getSource().getContact() != null)
				vo.setSourceContact(d.getSource().getContact());
		}
		if (addDescriptors)
			for (Descriptor des : d.getDatasetType().getDescriptors())
				vo.addDescriptorViewVO(descriptor2vo(des));
		return vo;
	}
	
	@SuppressWarnings("deprecation")
	public static DescriptorViewVO descriptor2vo(Descriptor d) {
		DescriptorViewVO vo = new DescriptorViewVO();
		vo.setContentDescriptor(d.getContentDescriptor());
		vo.setHeader(d.getHeader());
		if (d.getOptions() != null) {
			for (Option o : d.getOptions())
				vo.addOption(option2vo(o));
		}
		return vo;
	}
	
	public static OptionVO option2vo(Option o) {
		OptionVO vo = new OptionVO();
		vo.setId(o.getId());
		vo.setOptionName(o.getOptionName());
		vo.setOptionRegion(o.getOptionRegion());
		vo.setOptionValue(o.getOptionValue());
		return vo;
	}
	
	public static UniqueValueVO uniqueTextValue2vo(UniqueTextValues t) {
		UniqueValueVO vo = new UniqueValueVO();
		vo.setCode(t.getValue());
		vo.setLabel(t.getLabel());
		vo.setLangCode(t.getLangCode());
		vo.setDs_id(t.getDs_id());
		vo.setDatatype(t.getDatatype());
		return vo;
	}
	
	public static UniqueValueVO uniqueDateValue2vo(UniqueDateValues t) {
		UniqueValueVO vo = new UniqueValueVO();
		vo.setCode(FieldParser.parseDate(t.getValue()));
		vo.setLabel(FieldParser.parseDate(t.getDate()));
		vo.setLangCode(t.getLangCode());
		vo.setDs_id(t.getDs_id());
		vo.setDatatype(t.getDatatype());
		vo.setPeriodType(t.getPeriodType());
		return vo;
	}
	
	public static ResourceViewSettingVO setting2vo(ResourceViewSetting r) {
		ResourceViewSettingVO vo = new ResourceViewSettingVO();
		vo.setId(r.getId());
		vo.setSettingName(r.getSettingName());
		if (r instanceof BooleanResourceViewSetting) {
			vo.setValue(String.valueOf(((BooleanResourceViewSetting)r).isSelected()));
		} else if (r instanceof NumericResourceViewSetting) {
			vo.setValue(String.valueOf(((NumericResourceViewSetting)r).getQuantity().intValue()));
		} else if (r instanceof StringResourceViewSetting) {
			vo.setValue(String.valueOf(((StringResourceViewSetting)r).getValue()));
		}
		return vo;
	}
	
	public static List<ChartDesignerXCodeLabel> vo2XCodeLabelMap(ChartDesignerParametersVO vo) {
		List<ChartDesignerXCodeLabel> l = new ArrayList<ChartDesignerXCodeLabel>();
		System.out.println("vo.getxCodes().size() " + vo.getxCodes().size());
		System.out.println("vo.getxLabels().size() " + vo.getxLabels().size());
		for (int i = 0 ; i < vo.getxCodes().size() ; i++) {
			try {
				ChartDesignerXCodeLabel c = new ChartDesignerXCodeLabel(vo.getxCodes().get(i), vo.getxLabels().get(i));
				l.add(c);
			} catch (Exception e) {
				ChartDesignerXCodeLabel c = new ChartDesignerXCodeLabel(vo.getxCodes().get(i), vo.getxCodes().get(i));
				l.add(c);
			}
		}
		return l;
	}
	
	public static List<ChartDesignerPlotDimension> vo2plotDimensionMap(ChartDesignerParametersVO vo) {
		List<ChartDesignerPlotDimension> l = new ArrayList<ChartDesignerPlotDimension>();
		for (Long id : vo.getPlotDimensionMap().keySet()) {
			ChartDesignerPlotDimension c = new ChartDesignerPlotDimension(id, vo.getPlotDimensionMap().get(id));
			l.add(c);
		}
		return l;
	}
	
	public static List<ChartDesignerXContentDescriptor> vo2XContentDescriptors(ChartDesignerParametersVO vo) {
		List<ChartDesignerXContentDescriptor> l = new ArrayList<ChartDesignerXContentDescriptor>();
		for (String des : vo.getxContentDescriptors()) {
			ChartDesignerXContentDescriptor c = new ChartDesignerXContentDescriptor(des, des);
			l.add(c);
		}
		return l;
	}
	
	public static List<ChartDesignerSeriesColor> vo2SeriesColorMap(ChartDesignerYAxisParametersVO vo) {
		List<ChartDesignerSeriesColor> l = new ArrayList<ChartDesignerSeriesColor>();
		TreeMap<String, String> sorted = sortByKeys(vo.getSeriesColorsMap());
		for (String des : sorted.descendingKeySet()) {
			ChartDesignerSeriesColor c = new ChartDesignerSeriesColor(des, sorted.get(des), vo.getHeaderYCodeMap().get(des));
			l.add(c);
		}
		return l;
	}
	
	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
	public static List<ChartDesignerYCodeLabel> vo2YCodeLabelMap(ChartDesignerYAxisParametersVO vo) {
		List<ChartDesignerYCodeLabel> l = new ArrayList<ChartDesignerYCodeLabel>();
		for (String code : vo.getyCodes().keySet()) {
			ChartDesignerYCodeLabel c = new ChartDesignerYCodeLabel(code, vo.getyCodes().get(code));
			l.add(c);
		}
		return l;
	}
	
	public static List<ChartDesignerFilterCodeLabel> vo2CodeLabelMap(ChartDesignerFilterVO vo) {
		List<ChartDesignerFilterCodeLabel> l = new ArrayList<ChartDesignerFilterCodeLabel>();
		for (String code : vo.getCodes()) {
			ChartDesignerFilterCodeLabel c = new ChartDesignerFilterCodeLabel(code, code);
			l.add(c);
		}
		return l;
	}
	
	public static Map<String, String> seriesColor2StringStringMap(List<ChartDesignerSeriesColor> codes) {
		Map<String, String> m = new HashMap<String, String>();
		for (ChartDesignerSeriesColor c : codes)
			m.put(c.getCode(), c.getLabel());
		return m;
	}
	
	public static Map<String, String> headerYCode2StringStringMap(List<ChartDesignerSeriesColor> codes) {
		Map<String, String> m = new HashMap<String, String>();
		for (ChartDesignerSeriesColor c : codes)
			m.put(c.getLabel(), c.getyCode());
		return m;
	}
	
	public static Map<String, String> yCode2StringStringMap(List<ChartDesignerYCodeLabel> codes) {
		Map<String, String> m = new HashMap<String, String>();
		for (ChartDesignerYCodeLabel c : codes)
			m.put(c.getCode(), c.getLabel());
		return m;
	}
	
	public static List<String> codes2StringList(List<ChartDesignerXCodeLabel> codes) {
		List<String> l = new ArrayList<String>();
		for (ChartDesignerXCodeLabel c : codes)
			l.add(c.getCode());
		return l;
	}
	
	public static List<String> filterCodes2StringList(List<ChartDesignerFilterCodeLabel> codes) {
		List<String> l = new ArrayList<String>();
		for (ChartDesignerFilterCodeLabel c : codes)
			l.add(c.getCode());
		return l;
	}
	
	public static List<String> contentDescriptor2StringList(List<ChartDesignerXContentDescriptor> codes) {
		List<String> l = new ArrayList<String>();
		for (ChartDesignerXContentDescriptor c : codes)
			l.add(c.getCode());
		return l;
	}
	
	public static List<Long> datasets2LongList(List<Dataset> datasets) {
		List<Long> l = new ArrayList<Long>();
		for (Dataset d : datasets)
			l.add(d.getResourceId());
		return l;
	}
	
	public static Map<Long, String> codes2LongStringMap(List<ChartDesignerPlotDimension> codes) {
		Map<Long, String> m = new HashMap<Long, String>();
		for (ChartDesignerPlotDimension c : codes)
			m.put(c.getCode(), c.getLabel());
		return m;
	}
	
}