package org.fao.fenix.web.modules.bangladesh.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.bangladesh.BangladeshBean;
import org.fao.fenix.core.domain.bangladesh.BangladeshReportStyleBean;
import org.fao.fenix.core.domain.bangladesh.ImportsBean;
import org.fao.fenix.core.domain.bangladesh.ImportsChartValuesBean;
import org.fao.fenix.core.domain.bangladesh.ImportsLCBean;
import org.fao.fenix.core.domain.bangladesh.ImportsLCRowBean;
import org.fao.fenix.core.domain.bangladesh.InternationalMarketBean;
import org.fao.fenix.core.domain.bangladesh.InternationalMarketRowBean;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.persistence.bangladesh.BangladeshDao;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.bangladesh.PriceChangeOverThePastTwoWeeksBean;
import org.fao.fenix.web.modules.bangladesh.common.services.BangladeshService;
import org.fao.fenix.web.modules.bangladesh.common.vo.BangladeshChartsBean;
import org.fao.fenix.web.modules.bangladesh.server.birt.CreateBangladeshReport;
import org.fao.fenix.web.modules.bangladesh.server.birt.SettingGraphStyle;
import org.fao.fenix.web.modules.bangladesh.server.utils.BangladeshTableCreator;
import org.fao.fenix.web.modules.bangladesh.server.utils.BangladeshUtils;
import org.fao.fenix.web.modules.birt.common.vo.ChartBean;
import org.fao.fenix.web.modules.birt.common.vo.ChartBeanValues;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class BangladeshServiceImpl extends RemoteServiceServlet implements BangladeshService {

	Logger LOGGER =  Logger.getLogger(BangladeshServiceImpl.class);
	
	private CreateBangladeshReport report;
	
	private BangladeshDao bangladeshDao;
	
	private TextDao textDao;
	
//	private static final Date referenceDate = FieldParser.parseDate("2009-08-06");
	private static final Date referenceDate2010 = FieldParser.parseDate("2010-03-11");
	private static final Date referenceDate2009 = FieldParser.parseDate("2009-08-06");
	private static final Date referenceDate2011 = FieldParser.parseDate("2011-04-07");
	
	private static final String riceCode = "1006";
	
	private static final String wheatCode = "1001_00";
	
	private static final String riceCoarseHSBD = "3";
	
	private static final String attaHSBD = "7";
	
	private static final String retailDatasetType = "RetailDailyPrices";
	
	private static final String wholesaleDatasetType = "WholesaleDailyPrices";
		
//	private static final Double amanTarget2010 = new Double(300);	
	
//	private static final Double amanTarget2009 = new Double(200);	
	
	private static final String red = "#cc0003";
	
	private static final String blue = "#003366";
	
	private static final String green = "#33ff33";
	
	private static final String yellow = "#ffff00";
	
	private static final String lightBlue = "#00ccff";
	
	private static final String violet = "#652965"; 
	
	
	
	
	public static Map<Integer, String> monthLabelMap;
	
	static {
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "Jan");
		monthLabelMap.put(1, "Feb");
		monthLabelMap.put(2, "Mar");
		monthLabelMap.put(3, "Apr");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "Jun");
		monthLabelMap.put(6, "Jul");
		monthLabelMap.put(7, "Aug");
		monthLabelMap.put(8, "Sep");
		monthLabelMap.put(9, "Oct");
		monthLabelMap.put(10, "Nov");
		monthLabelMap.put(11, "Dec");
	}
	
	public static Map<Integer, String> monthFullLabelMap;
	
	static {
		monthFullLabelMap = new HashMap<Integer, String>();
		monthFullLabelMap.put(0, "January");
		monthFullLabelMap.put(1, "February");
		monthFullLabelMap.put(2, "March");
		monthFullLabelMap.put(3, "April");
		monthFullLabelMap.put(4, "May");
		monthFullLabelMap.put(5, "June");
		monthFullLabelMap.put(6, "July");
		monthFullLabelMap.put(7, "August");
		monthFullLabelMap.put(8, "September");
		monthFullLabelMap.put(9, "October");
		monthFullLabelMap.put(10, "November");
		monthFullLabelMap.put(11, "December");
	}
	

	public String createReport(List<String> chartFileNames, String issue, Date date) {
		Date endDate = new Date();
		// system.out.println("date year: " + date.getYear());
		if ( date.getYear() == 110 )
			endDate = BangladeshUtils.findReportDate(referenceDate2010, date);
		else if ( date.getYear() == 109 )
			endDate = BangladeshUtils.findReportDate(referenceDate2009, date);
		else if ( date.getYear() == 111 )
			endDate = BangladeshUtils.findReportDate(referenceDate2011, date);
			
		
		// LOGGER.info("date: " + date );
		// LOGGER.info("endDate: " + endDate );
		
		BangladeshBean bangladeshBean = createBean(issue, endDate);		
		String priceChangeTable = createPriceChangeOverThePastTwoWeeksTable(endDate);
//		BangladeshChartsBean charts = new BangladeshChartsBean();
		BangladeshChartsBean charts = getBirtCharts(bangladeshBean, endDate);

//		List<ChartBirtBean> chartBirtBeans = new ArrayList<ChartBirtBean>();
//		// system.out.println("ChartWizardBean");
//		for(ChartWizardBean chart : charts) { 
//			for(YSerieBean serie : chart.getChartReportBean().getChartValues().getySeries()) {
//				// system.out.println("sizes: " + serie.getValues().size());
//			}
//			// system.out.println("-----------");
//		}
		BangladeshReportStyleBean styleBean = new BangladeshReportStyleBean();
	
		report = new CreateBangladeshReport(bangladeshBean, priceChangeTable, charts, styleBean);		
		return report.createReport();
	}

	
	
	private BangladeshBean createBean(String issue, Date date) {
		// LOGGER.info("------------------> END DATE: " +date.toString());
		BangladeshBean bangladeshBean = new BangladeshBean();
		
		/** setting report texts **/
		bangladeshBean.setIssue(issue);
//		String stringDate = FieldParser.dateDDMMYYmd(date);
		String stringDate = date.getDate() + " " + monthFullLabelMap.get(date.getMonth()) + " " + (1900 + date.getYear());
		bangladeshBean.setDate(stringDate);
		bangladeshBean.setHighlightsText(textDao.getText("FN_HIGHLIGHTS", date));
		bangladeshBean.setDomesticPricesText(textDao.getText("FN_DOMESTIC_PRICES", date));
		bangladeshBean.setInternationalPricesText(textDao.getText("FN_INTERNATIONAL_PRICES", date));
		bangladeshBean.setProductionText(textDao.getText("FN_PRODUCTION", date));
		bangladeshBean.setWheatMarketText(textDao.getText("FN_WHEAT_MARKET", date));
		bangladeshBean.setRiceMarketText(textDao.getText("FN_RICE_MARKET", date));
		bangladeshBean.setImportsText(textDao.getText("FN_IMPORTS", date));
		bangladeshBean.setProcurementText(textDao.getText("FN_AMAN_PROCUREMENT", date));
		bangladeshBean.setPublicDistributionText(textDao.getText("FN_PUBLIC_DISTRIBUTION", date));
		
		return bangladeshBean;
	}
	

	
	
	private String createPriceChangeOverThePastTwoWeeksTable(Date reportDate) {

		PriceChangeOverThePastTwoWeeksBean bean = new PriceChangeOverThePastTwoWeeksBean();

		Date previousDate = BangladeshUtils.getPreviousFortnight(reportDate);
		Date lastYearDate = new Date(reportDate.getYear() -1, reportDate.getMonth(),  reportDate.getDate());
		Date lastMonthDate = new Date(reportDate.getYear(), reportDate.getMonth() -1,  reportDate.getDate());
		
		// LOGGER.info(reportDate);
		// LOGGER.info(previousDate);
		// LOGGER.info(lastYearDate);
		
		bean.setNewRetailRice(bangladeshDao.findPriceRetail(riceCoarseHSBD, reportDate));
		bean.setOldRetailRice(bangladeshDao.findPriceRetail(riceCoarseHSBD,  previousDate));
		bean.setLastYearRetailRice(bangladeshDao.findPriceRetail(riceCoarseHSBD,  lastYearDate));
		bean.setLastMonthRetailRice(bangladeshDao.findPriceRetail(riceCoarseHSBD,  lastMonthDate));
		
		bean.setNewRetailAtta(bangladeshDao.findPriceRetail(attaHSBD,  reportDate));
		bean.setOldRetailAtta(bangladeshDao.findPriceRetail(attaHSBD,  previousDate));
		bean.setLastYearRetailAtta(bangladeshDao.findPriceRetail(attaHSBD, lastYearDate));
		bean.setLastMonthRetailAtta(bangladeshDao.findPriceRetail(attaHSBD, lastMonthDate));
		
		bean.setNewWholesaleRice(bangladeshDao.findPriceWholesale(riceCoarseHSBD, reportDate) );
		bean.setOldWholesaleRice(bangladeshDao.findPriceWholesale(riceCoarseHSBD, previousDate));
		bean.setLastYearWholesaleRice(bangladeshDao.findPriceWholesale(riceCoarseHSBD, lastYearDate));
		bean.setLastMonthWholesaleRice(bangladeshDao.findPriceWholesale(riceCoarseHSBD, lastMonthDate));
		
		bean.setNewWholesaleAtta(bangladeshDao.findPriceWholesale(attaHSBD, reportDate));
		bean.setOldWholesaleAtta(bangladeshDao.findPriceWholesale(attaHSBD, previousDate));
		bean.setLastYearWholesaleAtta(bangladeshDao.findPriceWholesale(attaHSBD, lastYearDate));
		bean.setLastMonthWholesaleAtta(bangladeshDao.findPriceWholesale(attaHSBD, lastMonthDate));
		

		
		calculatePercentageTableValues(bean);
		
		return BangladeshTableCreator.createPriceChangeOverTwoWeeksTable(bean, reportDate, previousDate, lastYearDate);
	}
	
	private void calculatePercentageTableValues(PriceChangeOverThePastTwoWeeksBean bean) {
			
			// LOGGER.info("Retail getNewRetailRice: " + bean.getNewRetailRice());
			/** RETAIL RICE **/
			if ( bean.getNewRetailRice() != null && bean.getOldRetailRice() !=null ) {
				bean.setOldRetailRice(((bean.getNewRetailRice()-bean.getOldRetailRice())/bean.getOldRetailRice())*100);
				// LOGGER.info("Retail lastweekRice: " + bean.getOldRetailRice());
			}
			if ( bean.getNewRetailRice() != null && bean.getLastMonthRetailRice() !=null ) {
				bean.setLastMonthRetailRice(((bean.getNewRetailRice()-bean.getLastMonthRetailRice())/bean.getLastMonthRetailRice())*100);
				// LOGGER.info("Retail lastmonthRice: " + bean.getLastMonthRetailRice());
			}
			if ( bean.getNewRetailRice() != null && bean.getLastYearRetailRice() !=null ) {
				bean.setLastYearRetailRice(((bean.getNewRetailRice()-bean.getLastYearRetailRice())/bean.getLastYearRetailRice())*100);
				// LOGGER.info("Retail lastYearRice: " + bean.getLastYearRetailRice());
			}
		

			
			// LOGGER.info("Retail getNewRetailAtta: " + bean.getNewRetailAtta());
			// LOGGER.info("Retail getOldRetailAtta: " + bean.getOldRetailAtta());
			/** RETAIL ATTA **/
			if ( bean.getNewRetailAtta() != null && bean.getOldRetailAtta() !=null ) {
				bean.setOldRetailAtta(((bean.getNewRetailAtta()-bean.getOldRetailAtta())/bean.getOldRetailAtta())*100);
				// LOGGER.info("Retail lastweekAtta: " + bean.getOldRetailAtta());
			}
			if ( bean.getNewRetailAtta() != null && bean.getLastMonthRetailAtta() !=null ) {
				bean.setLastMonthRetailAtta(((bean.getNewRetailAtta()-bean.getLastMonthRetailAtta())/bean.getLastMonthRetailAtta())*100);
				// LOGGER.info("Retail lastmonthAtta: " + bean.getLastMonthRetailAtta());
			}
			if ( bean.getNewRetailAtta() != null && bean.getLastYearRetailAtta() !=null ) {
				bean.setLastYearRetailAtta(((bean.getNewRetailAtta()-bean.getLastYearRetailAtta())/bean.getLastYearRetailAtta())*100);
				// LOGGER.info("Retail lastYearAtta: " + bean.getLastYearRetailAtta());
			}
			
			
			// LOGGER.info("WHOLESALE getNewWholesaleRice: " + bean.getNewWholesaleRice());
			/** WHOLESALE RICE **/
			if ( bean.getNewWholesaleRice() != null && bean.getOldWholesaleRice() !=null ) {
				bean.setOldWholesaleRice(((bean.getNewWholesaleRice()-bean.getOldWholesaleRice())/bean.getOldWholesaleRice())*100);
				// LOGGER.info("WholesaleRice lastweekRice: " + bean.getOldWholesaleRice());
			}
			if ( bean.getNewWholesaleRice() != null && bean.getLastMonthWholesaleRice() !=null ) {
				bean.setLastMonthWholesaleRice(((bean.getNewWholesaleRice()-bean.getLastMonthWholesaleRice())/bean.getLastMonthWholesaleRice())*100);
				// LOGGER.info("WholesaleRice lastmonthRice: " + bean.getLastMonthWholesaleRice());
			}
			if ( bean.getNewWholesaleRice() != null && bean.getLastYearWholesaleRice() !=null ) {
				bean.setLastYearWholesaleRice(((bean.getNewWholesaleRice()-bean.getLastYearWholesaleRice())/bean.getLastYearWholesaleRice())*100);
				// LOGGER.info("WholesaleRice lastYearRice: " + bean.getLastYearWholesaleRice());
			}
			
			// LOGGER.info("WHOLESALE getNewWholesaleAtta: " + bean.getNewWholesaleAtta());
			// LOGGER.info("WHOLESALE getOldWholesaleAtta: " + bean.getOldWholesaleAtta());
			/** WHOLESALE ATTA **/
			if ( bean.getNewWholesaleAtta() != null && bean.getOldWholesaleAtta() !=null ) {
				bean.setOldWholesaleAtta(((bean.getNewWholesaleAtta()-bean.getOldWholesaleAtta())/bean.getOldWholesaleAtta())*100);
				// LOGGER.info("WholesaleAtta() lastweekAtta: " + bean.getOldWholesaleAtta());
			}
			if ( bean.getNewWholesaleAtta() != null && bean.getLastMonthWholesaleAtta() !=null ) {
				bean.setLastMonthWholesaleAtta(((bean.getNewWholesaleAtta()-bean.getLastMonthWholesaleAtta())/bean.getLastMonthWholesaleAtta())*100);
				// LOGGER.info("WholesaleAtta() lastmonthAtta: " + bean.getLastMonthWholesaleAtta());
			}
			if ( bean.getNewWholesaleAtta() != null && bean.getLastYearWholesaleAtta() !=null ) {
				bean.setLastYearWholesaleAtta(((bean.getNewWholesaleAtta()-bean.getLastYearWholesaleAtta())/bean.getLastYearWholesaleAtta())*100);
				// LOGGER.info("WholesaleAtta() lastYearAtta: " + bean.getLastYearWholesaleAtta());
			}
			
			
			
	}

	
	private List<Date> createXAxisDates(Date startDate, Date endDate, List<String> xAxisLabels) {
		List<Date> xAxisDates = new ArrayList<Date>();
		// LOGGER.info("startDate: " + startDate);
		// LOGGER.info("endDate: " + endDate);
//		// LOGGER.info("startDate.compareTo(endDate): " + startDate.compareTo(endDate));
		int i = 0;
		while(endDate.compareTo(startDate) > 0) {
//			// LOGGER.info("getDate: " + startDate);
//			if( (i % 7) == 0 ) {
				xAxisDates.add(startDate);
				xAxisLabels.add(FieldParser.parseDate(startDate));
//			}
			startDate = new Date(startDate.getYear(), startDate.getMonth(),  startDate.getDate() +1);
//			i++;
		}
		xAxisDates.add(endDate);
		xAxisLabels.add(FieldParser.parseDate(endDate));
		return xAxisDates;	
	}

	private List<Date> createXAxisWeeklyDates(Date startDate, Date endDate, List<String> xAxisLabels) {
		List<Date> xAxisDates = new ArrayList<Date>();
		// LOGGER.info("startDate: " + startDate);
		// LOGGER.info("endDate: " + endDate);
//		// LOGGER.info("startDate.compareTo(endDate): " + startDate.compareTo(endDate));

		while(endDate.compareTo(startDate) > 0) {
			// LOGGER.info("getDate: " + startDate);
			xAxisDates.add(startDate);
			xAxisLabels.add(FieldParser.parseDate(startDate));
			startDate = new Date(startDate.getYear(), startDate.getMonth(),  startDate.getDate() +7);

			if ( startDate.getDay() != 4) {
				Integer daysDifference = 4 - startDate.getDay();
				/** calculate the thursday **/
				startDate = new Date(startDate.getYear(), startDate.getMonth(),  startDate.getDate() + daysDifference);
			}
		}
		xAxisDates.add(endDate);
		xAxisLabels.add(FieldParser.parseDate(endDate));
		return xAxisDates;	
	}
	
	
	
	
	/*** BANGLADESH CHARTS **/


	public BangladeshChartsBean getBirtCharts(BangladeshBean bangladeshBean, Date endDate) {
		BangladeshChartsBean chartWizardBeans = new BangladeshChartsBean();
	
//		Date endDate = BangladeshUtils.findReportDate(referenceDate, date);
		Date startDate = new Date(endDate.getYear() -1 , endDate.getMonth(), endDate.getDate() +1 );
		
		// LOGGER.info("REPORT DATA DATE: " + endDate);
		List<String> xAxisLabels = new ArrayList<String>();
		List<String> xAxisWeeklyLabels = new ArrayList<String>();
		List<Date> xAxisDates = createXAxisDates(startDate, endDate, xAxisLabels);
		List<Date> xAxisWeeklyDates = createXAxisWeeklyDates(startDate, endDate, xAxisWeeklyLabels);
		
		/** getting second page charts **/
		getSecondPageCharts(chartWizardBeans, startDate, endDate, xAxisLabels, xAxisDates, xAxisWeeklyLabels, xAxisWeeklyDates);
		
		/** Internation production and market prospects **/
		createInternationalProductionMarket(bangladeshBean, endDate);
		
		/** Imports **/
		createImports(bangladeshBean, chartWizardBeans, endDate);
		
		/** Procurement charts **/
		getProcurementChart(bangladeshBean, chartWizardBeans, endDate);
		
		/** public distribution chart **/
		getPublicDistribution(bangladeshBean, chartWizardBeans, endDate);
		
		
		
		
		
		return chartWizardBeans;
	}
	
	private void getSecondPageCharts(BangladeshChartsBean chartWizardBeans, Date startDate, Date endDate, List<String> xAxisLabels, List<Date> xAxisDates, List<String> xAxisWeeklyLabels, List<Date> xAxisWeeklyDates) {
		
		/** rice  chart**/
		ChartWizardBean chartWizardBeanRice = SettingGraphStyle.setInfoLineChart("RICE", "taka per kg");
		ChartBean riceDChartBean = getDomesticPrices(riceCoarseHSBD, "Rice", startDate, endDate, xAxisLabels, xAxisDates);
		riceDChartBean.setSetScripted("setScripted0");
		riceDChartBean.setSrcScripted("srcScripted0");
		riceDChartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatLineChart());
		chartWizardBeanRice.setChartReportBean(riceDChartBean);
		chartWizardBeans.setRiceDomesticPrices(chartWizardBeanRice);
		
		/** atta chart **/
		ChartWizardBean chartWizardBeanAtta = SettingGraphStyle.setInfoLineChart("ATTA", "taka per kg");
		ChartBean attaDChartBean = getDomesticPrices(attaHSBD, "Atta", startDate, endDate, xAxisLabels, xAxisDates);
		attaDChartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatLineChart());
		attaDChartBean.setSetScripted("setScripted1");
		attaDChartBean.setSrcScripted("srcScripted1");
		chartWizardBeanAtta.setChartReportBean(attaDChartBean);
		chartWizardBeans.setAttaDomesticPrices(chartWizardBeanAtta);
		
		
		/** international prices Rice**/
		ChartWizardBean internationalPricesRice = SettingGraphStyle.setInfoLineChart("RICE", "USD/MT");
		ChartBean internationalRiceChartBean = getInternationalRicePrices(startDate, endDate, xAxisWeeklyLabels, xAxisWeeklyDates);
		internationalRiceChartBean.setSetScripted("setScripted2");
		internationalRiceChartBean.setSrcScripted("srcScripted2");
		internationalRiceChartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatLineChart());
		internationalPricesRice.setChartReportBean(internationalRiceChartBean);
		chartWizardBeans.setRiceInternationalPrices(internationalPricesRice);
	
		/** international prices Wheat**/
		ChartWizardBean internationalPricesWheat = SettingGraphStyle.setInfoLineChart("WHEAT", "USD/MT");
		ChartBean internationalWheatChartBean = getInternationalWheatPrice(startDate, endDate, xAxisWeeklyLabels, xAxisWeeklyDates);
		internationalWheatChartBean.setSetScripted("setScripted3");
		internationalWheatChartBean.setSrcScripted("srcScripted3");
		internationalWheatChartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatLineChart());
		internationalPricesWheat.setChartReportBean(internationalWheatChartBean);
		chartWizardBeans.setWheatInternationalPrices(internationalPricesWheat);
		
	}
	
	private ChartBean getDomesticPrices(String commodityCode, String ySerie, Date startDate, Date endDate, List<String> xAxisLabels, List<Date> xAxisDates) {
		
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();

		// LOGGER.info("startDate: " + startDate.toString());
		List<QuantitativeCoreContent> contents = bangladeshDao.getWholesalePrices(commodityCode, startDate, endDate);
		LinkedHashMap<Date, Double> valuesWholesale = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {	
			valuesWholesale.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("valuesWholesale:" + valuesWholesale);
		YSerieBean yWholesale = fillChartBean(xAxisDates, valuesWholesale, "Wholesale");
		yWholesale.setColor(BirtUtil.convertHextToRGB(blue));
		series.add(yWholesale);

		
		LinkedHashMap<Date, Double> valuesRetail = new LinkedHashMap<Date, Double>();
		contents = bangladeshDao.getRatailPrices(commodityCode, startDate, endDate);
		for(QuantitativeCoreContent q : contents) {
			valuesRetail.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("valuesRetail:" + valuesRetail);
		YSerieBean yRetail = fillChartBean(xAxisDates, valuesRetail, "Retail");
		yRetail.setColor(BirtUtil.convertHextToRGB(violet));
		series.add(yRetail);
	
		
		chartValues.setxValues(xAxisLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		return chartBean;	
	}
	
	
	private ChartBean getInternationalRicePrices(Date startDate, Date endDate, List<String> xAxisLabels, List<Date> xAxisDates) {
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		String commodityCode = riceCode;
		String serieIndicator = null;
		String label = null;
		
		
		/** EXCHANGE RATES INDIA **/
		List<QuantitativeCoreContent> contents = bangladeshDao.getExchangeRates(startDate, endDate, "115");
		LinkedHashMap<Date, Double> exchangeRatesIndia = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			exchangeRatesIndia.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("exchangeRatesIndia:" + exchangeRatesIndia);
		
		/** EXCHANGE RATES BANGLADESH **/
		contents = bangladeshDao.getExchangeRates(startDate, endDate, "23");
		LinkedHashMap<Date, Double> exchangeRatesBangladesh = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			exchangeRatesBangladesh.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("exchangeRatesBangladesh:" + exchangeRatesBangladesh);


		
		
		/** THAI 100 % **/
		serieIndicator = "t100";
		label = "Thai 100% P.R. **";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> thai100 = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			thai100.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("thai100:" + thai100);
		YSerieBean ythai100 = fillChartBean(xAxisDates, thai100, label);
		ythai100.setColor(BirtUtil.convertHextToRGB(blue));
		series.add(ythai100);
		
		
		
		/** THAI 5 % **/
		serieIndicator = "t5";
		label = "Thai 5% P.R. ***";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);		
		LinkedHashMap<Date, Double> thai5 = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			thai5.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("thai5:" + thai5);
		YSerieBean ythai5 = fillChartBean(xAxisDates, thai5, label);
		ythai5.setColor(BirtUtil.convertHextToRGB(green));
		series.add(ythai5);

		
		/** Kolkata **/
		serieIndicator = "k";
		label = "Kolkata ****";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> riceKolkata = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			riceKolkata.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("riceKolkata:" + riceKolkata);
		YSerieBean yriceKolkata = fillChartBeanExchangeRates(xAxisDates, riceKolkata, exchangeRatesIndia, label);
		yriceKolkata.setColor(BirtUtil.convertHextToRGB(lightBlue));
		series.add(yriceKolkata);
		
		/** Dhaka  **/
		serieIndicator = "d";
		label = "Dhaka city w.";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> riceDhaka = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			riceDhaka.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("riceDhaka:" + riceDhaka);
		YSerieBean yriceDhaka = fillChartBeanExchangeRates(xAxisDates, riceDhaka, exchangeRatesBangladesh, label);
		yriceDhaka.setColor(BirtUtil.convertHextToRGB(red));
		series.add(yriceDhaka);
		
		
		chartValues.setxValues(xAxisLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		return chartBean;
	}
	
	
	private ChartBean getInternationalWheatPrice(Date startDate, Date endDate, List<String> xAxisLabels, List<Date> xAxisDates) {
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		String commodityCode = wheatCode;
		String serieIndicator = null;

		
		/** EXCHANGE RATES BANGLADESH **/
		List<QuantitativeCoreContent> contents = bangladeshDao.getExchangeRates(startDate, endDate, "23");
		LinkedHashMap<Date, Double> exchangeRatesBangladesh = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			exchangeRatesBangladesh.put(q.getDate(), q.getQuantity());
		}
		// LOGGER.info("exchangeRatesBangladesh:" + exchangeRatesBangladesh);

		
		/** US Wheat (hard red) **/
		serieIndicator = "hrw";
		String label = "US No-2 HRW, US Gulf Port*";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> usWheatHard = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			usWheatHard.put(q.getDate(), q.getQuantity());
		}
		YSerieBean yusWheat = fillChartBean(xAxisDates, usWheatHard, label);
		yusWheat.setColor(BirtUtil.convertHextToRGB(blue));
		series.add(yusWheat);

		/**"US Wheat (soft red)" **/
		serieIndicator = "srw";
		label = "US No-2 SRW, US Gulf Port*";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> usWheatSoft = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			usWheatSoft.put(q.getDate(), q.getQuantity());
		}
		YSerieBean yusWheatSoft = fillChartBean(xAxisDates, usWheatSoft, label);
		yusWheatSoft.setColor(BirtUtil.convertHextToRGB(green));
		series.add(yusWheatSoft);
		
		/**"Dhaka wholesale **/
		serieIndicator = "d";
		label = "Dhaka city w.";
		contents = bangladeshDao.getInternationalPrices(commodityCode, serieIndicator, startDate, endDate);
		LinkedHashMap<Date, Double> usWheatDhaka = new LinkedHashMap<Date, Double>();
		for(QuantitativeCoreContent q : contents) {
			usWheatDhaka.put(q.getDate(), q.getQuantity());
		}
		YSerieBean yusWheatDhaka = fillChartBeanExchangeRates(xAxisDates, usWheatDhaka, exchangeRatesBangladesh, label);
		yusWheatDhaka.setColor(BirtUtil.convertHextToRGB(red));
		series.add(yusWheatDhaka);

		
		chartValues.setxValues(xAxisLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		return chartBean;
	}
	
	
	
	private YSerieBean fillChartBean(List<Date> xAxisDates, LinkedHashMap<Date, Double> yValuesMap, String ySerieName) {
		YSerieBean ySerie = new YSerieBean();
		ySerie.setLabel(ySerieName);
		List<Double> values = new ArrayList<Double>();
		// LOGGER.info("xAxisDates size ----> " +  xAxisDates.size() + " | " + xAxisDates);
		for(Date xDate : xAxisDates ){
			Double value = yValuesMap.get(xDate);
			// LOGGER.info("xDate ----> " +  xDate + " | " + value);
			if ( value != null) {
				if (ySerieName.equals("Wholesale"))
						values.add(value / 100);
				else
					values.add(value);
			}
			else 
				values.add(null);
			
		}
		// LOGGER.info("values sizes ----> " +  values.size());
		ySerie.setValues(values);	 
		return ySerie;
	}
	
	private YSerieBean fillChartBeanExchangeRates(List<Date> xAxisDates, LinkedHashMap<Date, Double> yValuesMap, LinkedHashMap<Date, Double> exchangeRates, String ySerieName) {
		YSerieBean ySerie = new YSerieBean();
		ySerie.setLabel(ySerieName);
		List<Double> values = new ArrayList<Double>();
		// LOGGER.info("xAxisDates size ----> " +  xAxisDates.size() + " | " + xAxisDates);
		for(Date xDate : xAxisDates ){
			Double value = yValuesMap.get(xDate);
			Double exchangeRatesValue = exchangeRates.get(xDate);
			// LOGGER.info("xDate ----> " +  xDate + " | " + value + " | "+ exchangeRatesValue);
			if ( value != null && exchangeRatesValue != null )
				values.add((value * 10 )/ exchangeRatesValue );		
			else 
				values.add(null);
			
		}
		// LOGGER.info("values sizes ----> " +  values.size());
		ySerie.setValues(values);	 
		return ySerie;
	}
	
	
	
	/** International  production and market prospects 
	 *  **/
	private void createInternationalProductionMarket(BangladeshBean bangladeshBean, Date endDate) {
		bangladeshBean.setWheatSRW(createWheatSRW(endDate));
		bangladeshBean.setWheatHRW(createWheatHRW(endDate));
	}
	
	private InternationalMarketBean createWheatSRW(Date endDate) {
		/** TODO: this should be just 1006 **/
		List<IndexCoreContent> contents = bangladeshDao.getInternationMarketFutures(wheatCode, endDate, "SRW");		
		return getMarketBean(contents);
	}
	
	private InternationalMarketBean createWheatHRW(Date endDate) {
		/** TODO: this should be just 1006 **/
		List<IndexCoreContent> contents = bangladeshDao.getInternationMarketFutures(wheatCode, endDate, "HRW");		
		return getMarketBean(contents);
	}
	
	private InternationalMarketBean getMarketBean(List<IndexCoreContent> contents) {
		InternationalMarketBean bean = new InternationalMarketBean();
		InternationalMarketRowBean current = new InternationalMarketRowBean();
		InternationalMarketRowBean previous = new InternationalMarketRowBean();
		TreeMap<Date, Double> currentValues = new TreeMap<Date, Double>();
		TreeMap<Date, Double> previousValues = new TreeMap<Date, Double>();
		
		if ( !contents.isEmpty()) {
			bean.setMeasuramentUnit(contents.get(0).getMeasurementUnit().toString());
			Date currentDate = contents.get(0).getDate();
			/** TODO: the BU is always constant between the two weeks ?? 
			 *  	  there are always the same number of months between two weeks??? 
			 * **/
			bean.setBuValue(contents.get(0).getSecondIndicator());
			// LOGGER.info(contents);
			for(int i=0; i <= 9 ; i++) {
				// LOGGER.info(currentValues.size() + " | " + contents.get(i).getBaseDateFrom() + " | " + contents.get(i).getDate());
				if(currentDate.compareTo(contents.get(i).getDate()) == 0) {
					current.setDate(currentDate);
					currentValues.put(contents.get(i).getBaseDateFrom(), (contents.get(i).getQuantity() * (Double.parseDouble(contents.get(i).getSecondIndicator()))));
				}
				else {
					previous.setDate(contents.get(i).getDate());
					previousValues.put(contents.get(i).getBaseDateFrom(), (contents.get(i).getQuantity() * (Double.parseDouble(contents.get(i).getSecondIndicator()))));
				}
			}
		}
			
		current.setValues(currentValues);
		previous.setValues(previousValues);
			
		bean.setCurrentDate(current);
		bean.setPreviousDate(previous);

		return bean;
	}
	
	
	
	/** Imports  **/
	
	private void createImports(BangladeshBean bangladeshBean, BangladeshChartsBean chartWizardBeans, Date endDate) {
		ImportsBean importsBean = new ImportsBean();
		importsBean.setLcBean(createLCTableBean(endDate));	
		createImportsCharts(importsBean, chartWizardBeans, endDate);
		bangladeshBean.setImportsBean(importsBean);
	}
	
	private void createImportsCharts(ImportsBean importsBean, BangladeshChartsBean chartWizardBeans, Date endDate) { 
		ChartWizardBean importsRiceCWB = SettingGraphStyle.setInfoBarChart("", "thousand MT", false, "0");
		ChartBean importsRiceCB = createImportsChart(importsBean, chartWizardBeans, riceCode, endDate);
		importsRiceCB.setSetScripted("setScripted10");
		importsRiceCB.setSrcScripted("srcScripted10");
		importsRiceCB.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(false));
		importsRiceCWB.setChartReportBean(importsRiceCB);
		chartWizardBeans.setRiceImports(importsRiceCWB);
		
		ChartWizardBean importsWheatCWB = SettingGraphStyle.setInfoBarChart("", "thousand MT", false, "0");
		ChartBean importsWheatCB = createImportsChart(importsBean, chartWizardBeans, wheatCode, endDate);
		importsWheatCB.setSetScripted("setScripted11");
		importsWheatCB.setSrcScripted("srcScripted11");
		importsWheatCB.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(false));
		importsWheatCWB.setChartReportBean(importsWheatCB);
		chartWizardBeans.setWheatImports(importsWheatCWB);
	}
	
	private ChartBean createImportsChart(ImportsBean importsBean, BangladeshChartsBean chartWizardBeans, String commodityCode, Date endDate) {
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartBeanValues = new ChartBeanValues();
		List<String> xLabels = new ArrayList<String>();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		List<Double> values = new ArrayList<Double>();
		ImportsChartValuesBean chartValues = new ImportsChartValuesBean();
		
		Date lastFN = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate() -14);
		Date lastYear = calculateWeek(endDate);
		
		
		List<QuantitativeCoreContent> currentValue = bangladeshDao.getImports(commodityCode, endDate);
		List<QuantitativeCoreContent> lastFNValue = bangladeshDao.getImports(commodityCode, lastFN);
		List<QuantitativeCoreContent> lastYearValue = bangladeshDao.getImports(commodityCode, lastYear);
		
		// LOGGER.info(currentValue);
		// LOGGER.info(lastFNValue);
		// LOGGER.info(lastYearValue);
		
		/** adding labels **/ 
		
		xLabels.add("as of " + lastFNValue.get(0).getDate().getDate() + " " + monthLabelMap.get(lastFNValue.get(0).getDate().getMonth()) + " " + ( 1900 + lastFNValue.get(0).getDate().getYear()));
		xLabels.add("as of " + currentValue.get(0).getDate().getDate() + " " + monthLabelMap.get(currentValue.get(0).getDate().getMonth()) + " " + ( 1900 + currentValue.get(0).getDate().getYear()));
		xLabels.add("as of " + lastYearValue.get(0).getDate().getDate() + " " + monthLabelMap.get(lastYearValue.get(0).getDate().getMonth()) + " " + ( 1900 + lastYearValue.get(0).getDate().getYear()));
	
		/** AID serie  **/
		YSerieBean serie = new YSerieBean();
		serie.setLabel("Aid");
		values = new ArrayList<Double>();
		values.add(lastFNValue.get(0).getQuantity() / 1000);
		values.add(currentValue.get(0).getQuantity() / 1000);
		values.add(lastYearValue.get(0).getQuantity() / 1000);
		serie.setValues(values);
		serie.setColor(BirtUtil.convertHextToRGB(yellow));
		series.add(serie);
		
		/** AID public commercial  **/
		serie = new YSerieBean();
		serie.setLabel("Public Commercial");
		values = new ArrayList<Double>();
		values.add(lastFNValue.get(2).getQuantity() / 1000);
		values.add(currentValue.get(2).getQuantity() / 1000);
		values.add(lastYearValue.get(2).getQuantity() / 1000);
		serie.setValues(values);
		serie.setColor(BirtUtil.convertHextToRGB(red));
		series.add(serie);
		
		/** Private private  **/
		serie = new YSerieBean();
		serie.setLabel("Private");
		values = new ArrayList<Double>();
		values.add(lastFNValue.get(1).getQuantity() / 1000);
		values.add(currentValue.get(1).getQuantity() / 1000);
		values.add(lastYearValue.get(1).getQuantity() / 1000);
		serie.setValues(values);
		serie.setColor(BirtUtil.convertHextToRGB(lightBlue));
		series.add(serie);
		
		/** add chart **/
		chartBeanValues.setxValues(xLabels);
		chartBeanValues.setySeries(series);
		chartBean.setChartValues(chartBeanValues);
		
		
		
		/** table values **/
		chartValues.setCurrentAid(currentValue.get(0).getQuantity() / 1000);
		chartValues.setLastFNAid(lastFNValue.get(0).getQuantity() / 1000);
		chartValues.setLastYearAid(lastYearValue.get(0).getQuantity() / 1000);
		
		chartValues.setCurrentPublicCommercial(currentValue.get(2).getQuantity() / 1000);
		chartValues.setLastFNPublicCommercial(lastFNValue.get(2).getQuantity() / 1000);
		chartValues.setLastYearPublicCommercial(lastYearValue.get(2).getQuantity() / 1000);
		
		chartValues.setCurrentPrivate(currentValue.get(1).getQuantity() / 1000);
		chartValues.setLastFNPrivate(lastFNValue.get(1).getQuantity() / 1000);
		chartValues.setLastYearPrivate(lastYearValue.get(1).getQuantity() / 1000);
		
		chartValues.setCurrentTotal(chartValues.getCurrentAid() + chartValues.getCurrentPublicCommercial() + chartValues.getCurrentPrivate());
		chartValues.setLastFNTotal(chartValues.getLastFNAid() + chartValues.getLastFNPublicCommercial() + chartValues.getLastFNPrivate());
		chartValues.setLastYearTotal(chartValues.getLastYearAid() + chartValues.getLastYearPublicCommercial() + chartValues.getLastYearPrivate());
		
		if ( commodityCode.equals(riceCode))
			importsBean.setChartValuesRice(chartValues);
		else
			importsBean.setChartValuesWheat(chartValues);
		
		

		return chartBean;
	}
	
	private Date calculateWeek(Date currentDate) {
		// LOGGER.info(currentDate);
		Calendar calendarCurrent = Calendar.getInstance();
		calendarCurrent.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDate());
		// system.out.println("WEEK_OF_YEAR c : " + calendarCurrent.get(Calendar.WEEK_OF_YEAR));
		
		Date lastYear = new Date(currentDate.getYear() -1, currentDate.getMonth(), currentDate.getDate());
		Calendar calendarLastYear = Calendar.getInstance();
		calendarLastYear.set(lastYear.getYear(), lastYear.getMonth(), lastYear.getDate());
		// system.out.println("WEEK_OF_YEAR y : " + calendarLastYear.get(Calendar.WEEK_OF_YEAR));
		
		Integer weekDifference =  calendarCurrent.get(Calendar.WEEK_OF_YEAR) - calendarLastYear.get(Calendar.WEEK_OF_YEAR);
		
		
		// system.out.println("weekDifference : " + weekDifference);
		if ( weekDifference == 0 ) 
			return lastYear;
		else if ( weekDifference >= 1) {
			lastYear = new Date(currentDate.getYear() -1, currentDate.getMonth(), currentDate.getDate() + (7 * weekDifference));
			calendarLastYear.set(lastYear.getYear(), lastYear.getMonth(), lastYear.getDate());
			// system.out.println("WEEK_OF_YEAR y >= 1 : " + calendarLastYear.get(Calendar.WEEK_OF_YEAR));
			return lastYear;
		}
		else if (weekDifference <= -1) {
			lastYear = new Date(currentDate.getYear() -1, currentDate.getMonth(), currentDate.getDate() - (7 * weekDifference));
			calendarLastYear.set(lastYear.getYear(), lastYear.getMonth(), lastYear.getDate());
			// system.out.println("WEEK_OF_YEAR: y <= -1 :" + calendarLastYear.get(Calendar.WEEK_OF_YEAR));
			return lastYear;
		}
			
		return null;
	}
	
	private ImportsLCBean createLCTableBean(Date endDate) { 
		ImportsLCBean bean = new ImportsLCBean();
		List<QuantitativeCoreContent> rice = bangladeshDao.getImportsLCTable(riceCode, endDate);		
		List<QuantitativeCoreContent> wheat = bangladeshDao.getImportsLCTable(wheatCode, endDate);
		if ( !rice.isEmpty() ) 
			bean.setRice(setLCValues(bean, rice));
		if ( !wheat.isEmpty())
			bean.setWheat(setLCValues(bean, wheat));
		
		/** set measuremnt unit **/
		if ( !rice.isEmpty() ) 
			bean.setMeasuramentUnit(rice.get(0).getMeasurementUnit().toString());
	
		return bean;
	}

	
	private ImportsLCRowBean setLCValues(ImportsLCBean bean, List<QuantitativeCoreContent> content) {
		Date currentDate = content.get(0).getDate();
		ImportsLCRowBean rows = new ImportsLCRowBean();
		
		int i=0;
		if ( !content.isEmpty() ) {

			while (currentDate.compareTo(content.get(i).getDate()) == 0 ) {
				Double value = content.get(i).getQuantity();
				/** parsing the period type **/
				if ( content.get(i).getSecondIndicator().contains("month")) {
					bean.setSecondRowPeriod(content.get(i).getSecondIndicator());
					/** second row **/
					if ( content.get(i).getFirstIndicator().equals("O"))
						rows.setSecondRowOpen(value);
					else
						rows.setSecondRowSettled(value);
				}
				else if ( content.get(i).getSecondIndicator().contains("year")) {
					bean.setThirdRowPeriod(content.get(i).getSecondIndicator());
					/** third row **/
					if ( content.get(i).getFirstIndicator().equals("O"))
						rows.setThirdRowOpen(value);
					else
						rows.setThirdRowSettled(value);
				}
				else {
					bean.setFirstRowPeriod(content.get(i).getSecondIndicator());
					/** first row **/
					if ( content.get(i).getFirstIndicator().equals("O"))
						rows.setFirstRowOpen(value);
					else
						rows.setFirstRowSettled(value);
				}
				
				i++;
				if ( i >= content.size()) 
					break;
			}
		}
		
		
		return rows;
	}
	
	/*********
	 * 
	 * PROCUREMENT 
	 * 
	 */
	
	private void getProcurementChart(BangladeshBean bean, BangladeshChartsBean chartWizardBeans, Date endDate) {
		ChartWizardBean totalAmanCWB = SettingGraphStyle.setInfoBarChart("", "thousand MT", true, "0");
		ChartBean totalAmanCB = getTotalProcurement(bean, endDate);
		totalAmanCB.setSetScripted("setScripted5");
		totalAmanCB.setSrcScripted("srcScripted5");
		totalAmanCB.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(false));
		totalAmanCWB.setChartReportBean(totalAmanCB);
		chartWizardBeans.setAmanProcurementTotal(totalAmanCWB);
		
		
		ChartWizardBean progressProcurementCWB = SettingGraphStyle.setInfoLineChart("", "thousand MT");
		ChartBean progressProcurementCB = getProcurementSeason(endDate);
		progressProcurementCB.setSetScripted("setScripted7");
		progressProcurementCB.setSrcScripted("srcScripted7");
		progressProcurementCB.setFormatChartVo(SettingGraphStyle.setInfoFormatLineChart());
		progressProcurementCWB.setChartReportBean(progressProcurementCB);
		chartWizardBeans.setAmanProcurementProgress(progressProcurementCWB);
	}
	
	private ChartBean getTotalProcurement(BangladeshBean bean, Date endDate) {
		/** total procurement **/
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();


		Date lastYearDate = new Date(endDate.getYear() -1 , endDate.getMonth(), endDate.getDate());
		
		IndexCoreContent contentCurrentReport = bangladeshDao.getTotalProcurement(riceCode, "A", endDate);
		IndexCoreContent contentLastYear = bangladeshDao.getTotalProcurement(riceCode, "A", lastYearDate);
		
	
	
		
		Double total2010 = new Double(0);
		Double total2009 = new Double(0);
		Double required2010 = new Double(0);
		Double required2009 = new Double(0);
		
		
		
		if ( contentCurrentReport != null ) {
			/** set title of the report **/
			bean.setProcurementHeaderTitle(contentCurrentReport.getSecondIndicator());
			
			total2010 = contentCurrentReport.getQuantity() / 1000;
			try {
				required2010 = Integer.valueOf(contentCurrentReport.getFeatureCode()) - total2010;
			} catch(Exception e) {
				// LOGGER.info(e.getMessage());
			}
		}
		
		if ( contentLastYear != null ) {
			total2009 = contentLastYear.getQuantity() / 1000;
			try {
				required2009 = Integer.valueOf(contentLastYear.getFeatureCode()) - total2009;
			} catch(Exception e) {
				// LOGGER.info(e.getMessage());
			}
		}
		
		// LOGGER.info(contentCurrentReport.getQuantity() + " | " + required2010 + " | " + total2010);
		// LOGGER.info(contentLastYear.getQuantity() + " | " + required2009 + " | " + required2009);
		
		YSerieBean total = new YSerieBean();
		total.setLabel("Total Rice procured so far");
		List<Double> values = new ArrayList<Double>();
		values.add(total2010);
		values.add(total2009);
		total.setValues(values);
		total.setColor(BirtUtil.convertHextToRGB(red));
		
		
		YSerieBean required = new YSerieBean();
		required.setLabel("Required to achieve the target");
		values = new ArrayList<Double>();
		values.add(required2010);
		values.add(required2009);
		required.setValues(values);
		required.setColor(BirtUtil.convertHextToRGB(lightBlue));
		
		series.add(total);
		series.add(required);

		List<String> xLabels = new ArrayList<String>();
		xLabels.add("as of " +  endDate.getDay() + " " + monthLabelMap.get(endDate.getMonth()) + " "+ (1900 + endDate.getYear()));
		xLabels.add("as of " +  lastYearDate.getDay() + " " + monthLabelMap.get(lastYearDate.getMonth()) + " "+ (1900 + lastYearDate.getYear()));
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		
		return chartBean;
	} 
	
	private ChartBean getProcurementSeason(Date endDate) {

		/** progress procurement **/
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		List<String> xAxisLabels = new ArrayList<String>();		
		List<IndexCoreContent> achievement = bangladeshDao.getProgressProcurement(riceCode, "A", endDate);
		List<IndexCoreContent> contracts = bangladeshDao.getProgressProcurement(riceCode, "C", endDate);
		
		/** getting xAxisLabels and Dates **/
		List<Date> xAxisDates = getProcurementLabels(achievement, xAxisLabels);
		
		LinkedHashMap<Date, Double> achievementHM = new LinkedHashMap<Date, Double>();
		for(IndexCoreContent q : achievement) {
			achievementHM.put(q.getDate(), q.getQuantity() /1000);
		}
		YSerieBean achievementSerie = fillChartBean(xAxisDates, achievementHM, "Achievement");
		achievementSerie.setColor(BirtUtil.convertHextToRGB(lightBlue));
		series.add(achievementSerie);
		
		LinkedHashMap<Date, Double> contractsHM = new LinkedHashMap<Date, Double>();
		for(IndexCoreContent q : contracts) {
			contractsHM.put(q.getDate(), q.getQuantity() /1000);
		}
		YSerieBean contractsSerie = fillChartBean(xAxisDates, contractsHM, "Contracts");
		contractsSerie.setColor(BirtUtil.convertHextToRGB(blue));
		series.add(contractsSerie);
		
		LinkedHashMap<Date, Double> targetHM = new LinkedHashMap<Date, Double>();
		for(IndexCoreContent q : achievement) {
			try {
				// system.out.println("q.getFeatureCode(): " + q.getFeatureCode() );
				targetHM.put(q.getDate(), Double.parseDouble(q.getFeatureCode()) /1000);
			} catch(Exception e) {
				// system.out.println(e);
			}
		}
		YSerieBean targetSerie = fillChartBean(xAxisDates, targetHM, "Target");
		targetSerie.setColor(BirtUtil.convertHextToRGB(red));
		series.add(targetSerie);

		chartValues.setxValues(xAxisLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		
		
		
		return chartBean;
	} 

	
	private List<Date> getProcurementLabels(List<IndexCoreContent>  contents, List<String> xAxisLabels) {
		List<Date> xAxisDates = new ArrayList<Date>();
		for(IndexCoreContent  content : contents) {
			xAxisDates.add(content.getDate());
			xAxisLabels.add(FieldParser.parseDate(content.getDate()));
		} 
		return xAxisDates;
	}
	 
	/*********
	 * 
	 * PUBLIC DISTRIBUTION 
	 * 
	 */
	
	private void getPublicDistribution(BangladeshBean bean, BangladeshChartsBean chartWizardBeans, Date endDate) {
		ChartWizardBean publicDistributionRice = SettingGraphStyle.setInfoBarChart("", "thousand MT", true, "90");
		ChartBean publicDistributionRiceCB = getPublicDistributionChart(bean, endDate);
		publicDistributionRiceCB.setSetScripted("setScripted8");
		publicDistributionRiceCB.setSrcScripted("srcScripted8");
		publicDistributionRiceCB.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(false));
		publicDistributionRice.setChartReportBean(publicDistributionRiceCB);
		chartWizardBeans.setPublicDistribution(publicDistributionRice);
	}
	
	private ChartBean getPublicDistributionChart(BangladeshBean bean, Date endDate) {
		/** Public Distribution chart **/
		ChartBean chartBean = new ChartBean();
		List<IndexCoreContent> contentsRice = bangladeshDao.getPublicDistribution(riceCode, endDate);
		List<IndexCoreContent> targetsRice = bangladeshDao.getPublicDistributionTarget(riceCode, endDate);
		
		List<IndexCoreContent> contentsWheat = bangladeshDao.getPublicDistribution(wheatCode, endDate);
		List<IndexCoreContent> targetsWheat = bangladeshDao.getPublicDistributionTarget(wheatCode, endDate);
		
		/** get the date for the report **/
		bean.setPublicDistributionDate(monthFullLabelMap.get(contentsRice.get(0).getBaseDateTo().getMonth()) + " " + contentsRice.get(0).getBaseDateTo().getDate());
		
		chartBean = getPublicDistributionRiceChart(contentsRice, targetsRice, contentsWheat, targetsWheat);
		

		
		
		return chartBean;
	} 
	
	private ChartBean getPublicDistributionRiceChart(List<IndexCoreContent> contentsRice, List<IndexCoreContent> targetsRice, List<IndexCoreContent> contentsWheat, List<IndexCoreContent> targetsWheat) {
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		List<String> xLabels = new ArrayList<String>();
		YSerieBean serieCumulative = new YSerieBean();
		YSerieBean serieYearTarget = new YSerieBean();
		List<Double> valuesCumulative = new ArrayList<Double>();
		List<Double> valuesYearTarget = new ArrayList<Double>();
		serieCumulative.setLabel("Cumulative this year");
		serieYearTarget.setLabel("Target this year");
		
		for(IndexCoreContent content : contentsRice) {
			for(IndexCoreContent target : targetsRice) {	
				// system.out.println(target.getFirstIndicator() + " | " + content.getFirstIndicator());
				if ( target.getFirstIndicator().equals(content.getFirstIndicator())) {
					// system.out.println("-------->" + target.getFirstIndicator() + " | " + content.getFirstIndicator());
					if ( content.getQuantity() != null) {
						valuesCumulative.add(content.getQuantity() / 1000);
						valuesYearTarget.add((target.getQuantity()) - content.getQuantity() / 1000);
					}
					else {					
						valuesCumulative.add(content.getQuantity());
						valuesYearTarget.add(target.getQuantity());
					}
					serieCumulative.setValues(valuesCumulative);
					serieYearTarget.setValues(valuesYearTarget);
					xLabels.add(content.getFirstIndicator() + " (R)");
				}
			}
		}
		
		serieCumulative.getValues().add(null);
		serieYearTarget.getValues().add(null);
		xLabels.add("");
		
		for(IndexCoreContent content : contentsWheat) {
			for(IndexCoreContent target : targetsWheat) {	
				// system.out.println(target.getFirstIndicator() + " | " + content.getFirstIndicator());
				if ( target.getFirstIndicator().equals(content.getFirstIndicator())) {
					// system.out.println("-------->" + target.getFirstIndicator() + " | " + content.getFirstIndicator());
					if ( content.getQuantity() != null) {
						valuesCumulative.add(content.getQuantity() / 1000);
						valuesYearTarget.add((target.getQuantity()) - content.getQuantity() / 1000);
					}
					else {					
						valuesCumulative.add(content.getQuantity());
						valuesYearTarget.add(target.getQuantity());
					}
					serieCumulative.setValues(valuesCumulative);
					serieYearTarget.setValues(valuesYearTarget);
					xLabels.add(content.getFirstIndicator()+ " (W)");
				}
			}
		}
		
		serieCumulative.setColor(BirtUtil.convertHextToRGB(lightBlue));
		serieYearTarget.setColor(BirtUtil.convertHextToRGB(red));
		
		series.add(serieCumulative);
		series.add(serieYearTarget);
		chartValues.setxValues(xLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		return chartBean;
	}
	
	

	private String getHighlights() {
		return bangladeshDao.getHighlights();
	}
	
	private String getDomesticPrices() {
		return bangladeshDao.getDomesticPrices();
	}
	
	private String getInternationalPrices() {
		return bangladeshDao.getInternationalPrices();
	}

	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}
	
	public void setBangladeshDao(BangladeshDao bangladeshDao) {
		this.bangladeshDao = bangladeshDao;
	}
	
	
	
}
