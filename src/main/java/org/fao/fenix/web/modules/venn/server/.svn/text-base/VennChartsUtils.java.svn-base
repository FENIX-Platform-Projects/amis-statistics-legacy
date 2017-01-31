package org.fao.fenix.web.modules.venn.server;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.venn.VennDao;
import org.fao.fenix.web.modules.birt.common.vo.ChartBean;
import org.fao.fenix.web.modules.birt.common.vo.ChartBeanValues;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.GraphReportEngine;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;

public class VennChartsUtils {
	
	private static Logger LOGGER = Logger.getLogger(VennChartsUtils.class);

	public static String createReport(ChartWizardBean chartBean) {
		String nameReport = null;
		
		ReportDesignHandle designHandle = null;
		ElementFactory designFactory = null;
		
		SessionHandle session = BirtUtil.getDesignSessionHandle();

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();
			

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			simpleMasterPage.setOrientation("landscape");
			designHandle.getMasterPages().add(simpleMasterPage);

			GraphReportEngine gre0 = new GraphReportEngine(chartBean, designHandle);
			designHandle.getBody().add(gre0.getChart());

			nameReport = BirtUtil.randomNameFile();
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + nameReport);

			designHandle.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nameReport;
	}
	
	public static ChartWizardBean setInfoBarChart(String title, String yAxisTitle, Boolean stacked, String xLabelsRotate, String width, String height) {
		ChartWizardBean bean = new ChartWizardBean();
		bean.setTitle(title);
	
		bean.setYAxisTitle(yAxisTitle);
		bean.setChartType("Bar");
		if ( stacked )
			bean.setDisposition("Stacked");
		else
			bean.setDisposition("");
//		bean.setFlip(true);
//		bean.setChartType("Bar");
		bean.setXAxisTitle("");
		bean.setXAxisShowLabels(true);
		bean.setYAxisShowLabels(true);
		bean.setPosLegend("Below");
		bean.setShowLegend(false);
		bean.setDim2_3D("Two_Dimensional");
		bean.setRotateXLabels(xLabelsRotate);
		bean.setWidth(width);
		bean.setHeight(height);
		return bean;
	}
	
	public static FormatChartVo setInfoFormatLineChart(Boolean titleVisible, int fontsize) {
		FormatChartVo format = new FormatChartVo();
		format.setTitleVisible(titleVisible);
		/** title **/
		format.setSizeTitle(fontsize + 3);
		
		/** y **/
		format.setSizeYAxis(fontsize);
		format.setSizeYAxisLabel(fontsize);
		
		/** x **/
		format.setSizeXAxisLabel(fontsize);
		
		/** legend **/
		format.setSizeLabel(fontsize);
		
		format.setVisible(false);
		
//		format.setLegendHeight(new Double(5));
//		format.setLegendWidth(new Double(50));
		
		return format;
	}

	
	public static ChartBean createChartPerFrequencies(VennBeanVO bean, int fontsize) {
		ChartBean chartCB = new ChartBean();
		chartCB.setSetScripted("setScripted1");
		chartCB.setSrcScripted("srcScripted1");
		
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		YSerieBean total = new YSerieBean();
		total.setLabel("Frequencies");

		total.setValues(getChartValues(bean));
		total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
		series.add(total);
		List<String> xLabels = getChartLabels(bean);
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartCB.setChartValues(chartValues);

		chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));

		return chartCB;
	}
	
	
	public static ChartBean createChartPerDonor(VennBeanVO bean, int fontsize) {
		ChartBean chartCB = new ChartBean();
		
		HashMap<String, Double> donors = VennUtils.calculateDonorsTotal(bean);
		chartCB.setSetScripted("setScripted1");
		chartCB.setSrcScripted("srcScripted1");
		
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		YSerieBean total = new YSerieBean();
		total.setLabel("Budget (mn)");

		List<String> xLabels = getChartLabels(donors);
		total.setValues(getChartValues(donors, xLabels));
		total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
		series.add(total);
	
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartCB.setChartValues(chartValues);

		chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));

		return chartCB;
	}
	
	
	public static ChartBean createChartWithHashMap(HashMap<String, Double> chartHM, int fontsize, int chartiIdx, String color) {
		ChartBean chartCB = new ChartBean();
		
		chartCB.setSetScripted("setScripted1" + chartiIdx);
		chartCB.setSrcScripted("srcScripted1"+ chartiIdx);
		
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		YSerieBean total = new YSerieBean();
		total.setLabel("Budget (mn)");

		List<String> xLabels = getChartLabels(chartHM);
		total.setValues(getChartValues(chartHM, xLabels));
//		total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
		total.setColor(BirtUtil.convertHextToRGB(color));
		series.add(total);
	
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartCB.setChartValues(chartValues);

		chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));

		return chartCB;
	}
	
	public static ChartBean createChartWithHashMap(HashMap<String, Double> chartHM, int fontsize, int chartiIdx, String color, String codingSystem, String language, CodecDao codecDao) {
		ChartBean chartCB = new ChartBean();
		
		chartCB.setSetScripted("setScripted1" + chartiIdx);
		chartCB.setSrcScripted("srcScripted1"+ chartiIdx);
		
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		YSerieBean total = new YSerieBean();
		total.setLabel("Budget (mn)");

		List<String> xLabels = getChartValues(chartHM, codecDao, codingSystem, language);
		total.setValues(getChartValues(chartHM, xLabels));
//		total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
		total.setColor(BirtUtil.convertHextToRGB(color));
		series.add(total);
	
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartCB.setChartValues(chartValues);

		chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));

		return chartCB;
	}
	
	// this is a chart for each donor and by its modality
	public static List<ChartBean> createChartPerDonorAndfunding(HashMap<String,HashMap<String, Double>> donors, VennBeanVO bean, int fontsize) {
		List<ChartBean> charts = new ArrayList<ChartBean>();
		
//		HashMap<String,HashMap<String, Double>> donors = VennUtils.calculateDonorsAndFunding(bean);
		
		int i=0;
		// create a chart for each donor 
		for (String key : donors.keySet()) {
			ChartBean chartCB = new ChartBean();
			HashMap<String, Double> fundingModalities = donors.get(key);
			
			
			chartCB.setSetScripted("setScripted1" + i);
			chartCB.setSrcScripted("srcScripted1" + i);
			
			ChartBeanValues chartValues = new ChartBeanValues();
			List<YSerieBean> series = new ArrayList<YSerieBean>();
			YSerieBean total = new YSerieBean();
			total.setLabel("Budget (mn)");
	
			List<String> xLabels = getChartLabels(fundingModalities);
			total.setValues(getChartValues(fundingModalities, xLabels));
			total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
			series.add(total);
		
			chartValues.setxValues(xLabels);
			chartValues.setySeries(series);
			chartCB.setChartValues(chartValues);
	
			chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));
			
			charts.add(chartCB);
			i++;
		}

		return charts;
	}

	
	public static List<String> getChartLabels(VennBeanVO vennBean) {
		List<String> labels = new ArrayList<String>();
//		for(VennIntersectionsBean intersaction : vennBean.getAllIntersectionsChart())
//			labels.add(intersaction.getLabel());
		return labels;
	}
	
	public static List<Double> getChartValues(HashMap<String, Double> hashMap, List<String> labels){
		System.out.println("getChartValues(HashMap<String, Double> hashMap, List<String> labels)");
		List<Double> values = new ArrayList<Double>();
		for(String label : labels ) {
			System.out.println("label: " + label + " | " + hashMap.get(label));
			values.add(hashMap.get(label));
		}
		System.out.println("getChartValues(HashMap<String, Double> hashMap, List<String> labels) : " + values);
		return values;
	}
	
	private static List<String> getChartValues(HashMap<String, Double> hashMap, CodecDao codecDao, String codingSystem, String language) {
		List<String> labels = new ArrayList<String>();
		for (String key : hashMap.keySet()) {
			// pass lanuage
			String l = codecDao.getLabelFromCodeCodingSystem(key, codingSystem, "0", language);
			if ( l.length() > 15 )
				labels.add(l.substring(0, 14));
			else
				labels.add(l);
			
		}
		return labels;
	}
	
	public static List<String> getChartLabels(HashMap<String, Double> hashMap) {
		List<String> labels = new ArrayList<String>();
		for (String key : hashMap.keySet())
			labels.add(key);
		return labels;
	}
	
	private static List<Double> getChartValues(VennBeanVO bean){
		List<Double> values = new ArrayList<Double>();
//		if( bean.getAbc().getValue() != null) 
//			values.add(bean.getAbc().getValue());
//		else 
//			values.add(null);
//		
//		if( bean.getAb().getValue() != null) 
//			values.add(bean.getAb().getValue());
//		else 
//			values.add(null);
//		
//		if( bean.getBc().getValue() != null) 
//			values.add(bean.getBc().getValue());
//		else 
//			values.add(null);
//		
//		if( bean.getAc().getValue() != null) 
//			values.add(bean.getAc().getValue());
//		else 
//			values.add(null);
//
//		if( bean.getA().getValue() != null) 
//			values.add(bean.getA().getValue());
//		else 
//			values.add(null);
//		
//		if( bean.getB().getValue() != null) 
//			values.add(bean.getB().getValue());
//		else 
//			values.add(null);
//		
//		if( bean.getC().getValue() != null) 
//			values.add(bean.getC().getValue());
//		else 
//			values.add(null);

		return values;
	}
	
	

	public static List<LinkedHashMap<String, Double>> getBudgetProjectsForIntersection(VennCountryBeanVO vennCountryBean, VennGraphBeanVO vennGraphBean)  {
		List<LinkedHashMap<String, Double>> chartsHM = new ArrayList<LinkedHashMap<String,Double>>();
		LinkedHashMap<String, Double> sumHashMap = new LinkedHashMap<String, Double>();
		LinkedHashMap<String, Double> avgHashMap = new LinkedHashMap<String, Double>();
		
		List<VennProjectsBean> intersactions = vennCountryBean.getAllIntersections();
		for (VennProjectsBean intersaction : intersactions) {
			if (intersaction.getProjectsRows() != null) {
				if (!intersaction.getProjectsRows().isEmpty())
					calcultateTotalBudgetForIntersection(sumHashMap, avgHashMap, intersaction, vennGraphBean.getIntersection(intersaction.getIntersectionName()).getLabel());
			}
		}
		
		chartsHM.add(sumHashMap);
//		chartsHM.add(avgHashMap);
//		
		return chartsHM;
	}
	
	private static void calcultateTotalBudgetForIntersection(HashMap<String, Double> sumHashMap, HashMap<String, Double> avgHashMap, VennProjectsBean intersaction, String intersectionName) {
		
		Integer quantityIdx = 0;
		// headers
		for (int i = 0; i < intersaction.getHeaders().size(); i++) {		
			if (intersaction.getHeaders().get(i).getDataType().toString().equals("quantity")) {
				// the +1 if the first header column is the id of the row (or something else)
				//quantityIdx = i + 1;
				quantityIdx = i;
				break;
			}
		}

		List<Double> values = new ArrayList<Double>();
		Double sum = new Double(0);

//		System.out.println("quantityIdx->" + quantityIdx);
		
		
		// content
		for (List<String> projectRow : intersaction.getProjectsRows()) {
//			System.out.println("->" + projectRow);
			// +1 if there is the id
			for (int i = 0; i < projectRow.size(); i++) {
				if (i == quantityIdx) {
					try {
						Double value = Double.parseDouble(projectRow.get(i));
						values.add(value);
						sum = sum + value;
					} catch (NumberFormatException nfe) {
					}
				}
			}
		}
		
		
		sumHashMap.put(intersectionName, sum);
//		avgHashMap.put(intersaction.getIntersectionName(), sum / values.size());
	}
	
	public static VennIframeVO setIframes(String cp, String portlet, String servletType, String text) {
		VennIframeVO iframesVO = new VennIframeVO();
		String iframeCP = "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + cp + "&servletType=" + servletType + "' width='880px' height='470px' SCROLLING=NO  frameborder='0' />";
		String iframePortlet = "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + portlet + "&servletType=" + servletType + "' width='550px' height='260px' SCROLLING=NO  frameborder='0' />";

		iframesVO.setIframeCentralPanel(iframeCP);
		iframesVO.setIframePortlet(iframePortlet);
		iframesVO.setText(text);
		return iframesVO;
	}
	
	public static void setIframes(String cp, String portlet, String servletType, String text, VennChartBeanVO chartBeanVO) {
		chartBeanVO.setIframeCentralPanel("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + cp + "&servletType=" + servletType + "' width='880px' height='470px' SCROLLING=NO  frameborder='0' />");
		chartBeanVO.setIframePortlet("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + portlet + "&servletType=" + servletType + "' width='550px' height='260px' SCROLLING=NO  frameborder='0' />");
		chartBeanVO.setText(text);
	}
	
	
	public static LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getChartByIntersections(Long datasetId, VennBeanVO bean, String servletType, String chartType, String centralChartWidth, String centralChartHeigth, HashMap<String,  List<Date>> timePeriod, VennDao vennDao) {
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
		ChartWizardBean chart = null;
		LinkedHashMap<String, VennChartBeanVO> charts = new LinkedHashMap<String, VennChartBeanVO>();
	
		
		
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		
		/** TODO: get dac codes columnname **/
		String dacColumn = "field_20";
		
		/** TODO: get budget columnname **/
		String budgetColumn = "field_17";
		
		/** TODO: get budget columnname **/
		String groupBy = "field_17";
		
	
		

		/** adding countries **/
		List<String> featureCodes = (List<String>) bean.getCountries().keySet();
		filters.put("featureCode", featureCodes);
		
		
		List<String> dacTotalCodes = new ArrayList<String>();
		
		/** by intersections **/
		List<VennIntersectionsBean> intersections = bean.getVennGraphBeanVO().getAllIntersections();
		for (VennIntersectionsBean intersection : intersections) {
			if (intersection.getDacCodes() != null) {
				VennChartBeanVO vennChartBeanVO = new VennChartBeanVO();
				
				dacTotalCodes.addAll(intersection.getDacCodes());
				
				filters.put(dacColumn, intersection.getDacCodes());	
				
				List<Object[]> result = vennDao.sqlSumQuery(datasetId, budgetColumn, filters, timePeriod, groupBy);
				
				createHashMaps(intersection.getLabel(), vennChartBeanVO.getSum(), vennChartBeanVO.getCount(), vennChartBeanVO.getAvg(), result);
			
				chart = VennChartsUtils.setInfoBarChart("Total amount of Budget for common priorities", "Budget (mn)",  false, "45", centralChartWidth, centralChartHeigth);
			
				String chartString = createReport(chart);
				
				/** saving chart **/
				vennChartBeanVO.setChart(chart);
				vennChartBeanVO.setText(intersection.getLabel());
				setIframes(chartString, chartString, servletType, "", vennChartBeanVO);
				charts.put(intersection.getLabel(), vennChartBeanVO);
				
			}
		}
		
		/** total **/
		VennChartBeanVO vennChartBeanVO = new VennChartBeanVO();
		filters.put(dacColumn, dacTotalCodes);	
		List<Object[]> result = vennDao.sqlSumQuery(datasetId, budgetColumn, filters, timePeriod, groupBy);
		
		createHashMaps("Total", vennChartBeanVO.getSum(), vennChartBeanVO.getCount(), vennChartBeanVO.getAvg(), result);
	
		chart = VennChartsUtils.setInfoBarChart("Total amount of Budget for common priorities", "Budget (mn)",  false, "45", centralChartWidth, centralChartHeigth);
	
		String chartString = createReport(chart);
		
		/** saving chart **/
		vennChartBeanVO.setChart(chart);
		vennChartBeanVO.setText("Total");
		setIframes(chartString, chartString, servletType, "", vennChartBeanVO);
		charts.put("Total", vennChartBeanVO);

			
			
		return results;
	}
	
	
	public static LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getChartTotalByCategoriesLVL1(Long datasetId, VennBeanVO bean, String servletType, String chartType, String centralChartWidth, String centralChartHeight, String reportChartWidth, String reportChartHeight, HashMap<String,  List<Date>> timePeriod, VennDao vennDao, CodecDao codecDao) {
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
		ChartWizardBean chart = null;
		ChartWizardBean chartReport = null;
		LinkedHashMap<String, VennChartBeanVO> charts = new LinkedHashMap<String, VennChartBeanVO>();
	
		
		
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		
		/** TODO: get budget columnname **/
		String budgetColumn = "field_16";
		
		/** TODO: get groupby column **/
		String groupBy = "field_34";
		
	
		

		/** adding countries **/
		List<String> featureCodes = new ArrayList<String>();
		LOGGER.info(bean.getCountries());
		for(String key : bean.getCountries().keySet()) {
//			featureCodes.add(key);
			featureCodes.add("\"" + key + "\"");
		}
		if ( !featureCodes.isEmpty() )
			filters.put("featureCode", featureCodes);
		
		HashMap<String, String> dacTotalCodes = new HashMap<String, String>();
		/** filters on dac?? **/
		List<VennIntersectionsBean> intersections = bean.getVennGraphBeanVO().getAllIntersections();
		for (VennIntersectionsBean intersection : intersections) {
			if (!intersection.getDacCodes().isEmpty()) {
				for(String dacCode : intersection.getDacCodes()) {
					LOGGER.info(intersection.getLabel() + " | " + dacCode.subSequence(0, 2) + "000");
					dacTotalCodes.put(dacCode.subSequence(0, 2) + "000" , dacCode.subSequence(0, 2) + "000");
				}
//				dacTotalCodes.addAll(intersection.getDacCodes());
			}
		}
		
		if ( !dacTotalCodes.isEmpty()) {
			List<String> dacCodes = new ArrayList<String>();
			for(String key : dacTotalCodes.keySet())
				dacCodes.add(key);
			/** care about the lvl integer **/
			filters.put(groupBy, dacCodes);	
		}
		
		LOGGER.info(filters);
		
		/** total **/
		VennChartBeanVO vennChartBeanVO = new VennChartBeanVO();
		
		List<Object[]> result = vennDao.sqlSumQuery(datasetId, budgetColumn, filters, timePeriod, groupBy);
		
		createHashMaps("Total", vennChartBeanVO.getSum(), vennChartBeanVO.getCount(), vennChartBeanVO.getAvg(), result);
		
		vennChartBeanVO.setSum(VennUtils.setXaxisEntries(vennChartBeanVO.getSum(), 8));
		
		
		chart = VennChartsUtils.setInfoBarChart("Total amount of Budget per category", "Budget (mn)",  false, "45", centralChartWidth, centralChartHeight);
		chartReport = VennChartsUtils.setInfoBarChart("Total amount of Budget per category", "Budget (mn)",  false, "45", reportChartWidth, reportChartHeight);
	
		
		chart.setChartReportBean(VennChartsUtils.createChartWithHashMap(vennChartBeanVO.getSum(), 10, 0, "#003399", "DAC", "EN", codecDao));
		chartReport.setChartReportBean(VennChartsUtils.createChartWithHashMap(vennChartBeanVO.getSum(), 6, 0, "#003399", "DAC", "EN", codecDao));
	
		
		String chartString = createReport(chart);
		String chartReportString = createReport(chartReport);
		
		/** saving chart **/
		vennChartBeanVO.setChart(chartReport);
		vennChartBeanVO.setText("Total");
		setIframes(chartString, chartReportString, servletType, "", vennChartBeanVO);
		charts.put("Total", vennChartBeanVO);

			
		results.put("totalbycategories", charts);
		
			
		return results;
	}
	
	
	public static LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getChartGlobalByCategories(Long datasetId, VennBeanVO bean, String servletType, String chartType, String centralChartWidth, String centralChartHeight, String reportChartWidth, String reportChartHeight, HashMap<String, List<String>> filters, HashMap<String,  List<Date>> timePeriod, VennDao vennDao, CodecDao codecDao) {
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
		ChartWizardBean chart = null;
		ChartWizardBean chartReport = null;
		LinkedHashMap<String, VennChartBeanVO> charts = new LinkedHashMap<String, VennChartBeanVO>();
			
		/** TODO: get budget columnname **/
		String budgetColumn = "field_16";
		
		/** TODO: get groupby column **/
		String groupBy = "field_34";
		
			
		LOGGER.info(filters);
		
		/** total **/
		VennChartBeanVO vennChartBeanVO = new VennChartBeanVO();
		
		List<Object[]> result = vennDao.sqlSumQuery(datasetId, budgetColumn, filters, timePeriod, groupBy);
		
		createHashMaps("Total", vennChartBeanVO.getSum(), vennChartBeanVO.getCount(), vennChartBeanVO.getAvg(), result);
		
		vennChartBeanVO.setSum(VennUtils.setXaxisEntries(vennChartBeanVO.getSum(), 8));
		
		
		chart = VennChartsUtils.setInfoBarChart("Total amount of Budget per category", "Budget (mn)",  false, "45", centralChartWidth, centralChartHeight);
		chartReport = VennChartsUtils.setInfoBarChart("Total amount of Budget per category", "Budget (mn)",  false, "45", reportChartWidth, reportChartHeight);
	
		
		chart.setChartReportBean(VennChartsUtils.createChartWithHashMap(vennChartBeanVO.getSum(), 10, 0, "#003399", "DAC", "EN", codecDao));
		chartReport.setChartReportBean(VennChartsUtils.createChartWithHashMap(vennChartBeanVO.getSum(), 6, 0, "#003399", "DAC", "EN", codecDao));
	
		
		String chartString = createReport(chart);
		String chartReportString = createReport(chartReport);
		
		/** saving chart **/
		vennChartBeanVO.setChart(chartReport);
		vennChartBeanVO.setText("Total");
		setIframes(chartString, chartReportString, servletType, "", vennChartBeanVO);
		charts.put("Total", vennChartBeanVO);

			
		results.put("totalbycategories", charts);
		
			
		return results;
	}

	
	
	private static void createHashMaps(String value, LinkedHashMap<String , Double> sum, LinkedHashMap<String , Double> count, LinkedHashMap<String , Double> avg, List<Object[]> result) {
		try {
			for(int i=0; i < result.size(); i++) {
//				LOGGER.info(result.get(i)[0] + " | " + result.get(i)[1] + " | " + result.get(i)[2] + " | " + result.get(i)[3]);
//				LOGGER.info((result.get(i)[0]).toString());
//				LOGGER.info((Double) result.get(i)[1]);
//				LOGGER.info(result.get(i)[2]);
//				LOGGER.info((Double) result.get(i)[3]);

				sum.put((result.get(i)[0]).toString(), Double.valueOf(result.get(i)[1].toString()));

				String cValue = (result.get(i)[2]).toString();
				count.put((result.get(i)[0]).toString(), Double.valueOf(cValue));
				
				avg.put((result.get(i)[0]).toString(), Double.valueOf(result.get(i)[3].toString()));
			}
		}catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	
	
	
}
