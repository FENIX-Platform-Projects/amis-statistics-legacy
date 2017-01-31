package org.fao.fenix.web.modules.ec.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECCropCalendarBean;
import org.fao.fenix.core.domain.ec.ECFoodSecurityIssuesBean;
import org.fao.fenix.core.domain.ec.ECFoodSecuritySituationBean;
import org.fao.fenix.core.domain.ec.ECGovernanceBean;
import org.fao.fenix.core.domain.ec.ECIPCBean;
import org.fao.fenix.core.domain.ec.ECKeyMessages;
import org.fao.fenix.core.domain.ec.ECMaps;
import org.fao.fenix.core.domain.ec.ECNaturalDisastersBean;
import org.fao.fenix.core.domain.ec.ECPriceBean;
import org.fao.fenix.core.domain.ec.ECSocialEconomicBean;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.ec.ECDao;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ec.common.services.ECService;
import org.fao.fenix.web.modules.ec.common.vo.ECBeanVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemConfigurationVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;
import org.fao.fenix.web.modules.ec.server.birt.CreateECReport;
import org.fao.fenix.web.modules.ec.server.birt.template2011.CreateECReport2011;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ECServiceImpl extends RemoteServiceServlet implements ECService {

	Logger LOGGER = Logger.getLogger(ECServiceImpl.class);
	
	private CreateECReport report;
	
	ECDao ecDao;
	
	CodecDao codecDao;
	
	private String dir;
	
	public ECServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public TreeMap<String, ECItemVO> getCountryList() {
		TreeMap<String, ECItemVO> countries = new TreeMap<String, ECItemVO>();
		try {
			countries = getCountries();
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
		return countries;
	}
	

	public TreeMap<String, ECItemVO> getHaitiEmergencyList() {
		TreeMap<String, ECItemVO> countries = new TreeMap<String, ECItemVO>();
		try {
			countries = getHaitiList();
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
		return countries;
	}
	
	
	public static Map<Integer, String> monthLabelMap;
	
	static {
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "January");
		monthLabelMap.put(1, "February");
		monthLabelMap.put(2, "March");
		monthLabelMap.put(3, "April");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "June");
		monthLabelMap.put(6, "July");
		monthLabelMap.put(7, "August");
		monthLabelMap.put(8, "September");
		monthLabelMap.put(9, "October");
		monthLabelMap.put(10, "November");
		monthLabelMap.put(11, "December");
	}
	
	public String createReport(ECBeanVO ecBeanVO) {
		if ( !ecBeanVO.getIsHaitiEmergency()) { 
//			return createECReports(ecBeanVO);
			if ( ecBeanVO.getReportType().equalsIgnoreCase("TEMPLATE_2011")) {
				return createECReportsTemplate2011(ecBeanVO);
			}
			else 
				return createTemporarelyECReports(ecBeanVO);
		}
		else
			return createHaitiECReport(ecBeanVO);
	}
	
	/*private String createECReports(ECBeanVO ecBeanVO) {
		LOGGER.info("Creating EC Report Bean");
		ECBean ecBean = new ECBean();

		
		ecBean.setCountry(ecBeanVO.getCountryLabel());
		ecBean.setCountryCode(ecBeanVO.getCountryCode());
		
//		ecBean.setDate(ecBeanVO.getReportDateString());
//		ecBean.setReferingDate(FieldParser.parseDate(ecBeanVO.getReportDateField()));
//		LOGGER.info("setReferingDate: " + ecBean.getReferingDate());
		
		ecBean.setDate("October 2009");
		
	
		ecBean.setSocialEconomicBean(getECSocialEconomicBean(ecBeanVO.getCountryCode()));
		
		ecBean.setKeyMessages(getECKeyMessages(ecBeanVO.getCountryCode()));
		ecBean.setBackgorundText(getBackground(ecBeanVO.getCountryCode()));
		ecBean.setPriceBean(getPriceBean(ecBeanVO.getCountryCode()));
		ecBean.setConflictsBean(getConflictsBean(ecBeanVO.getCountryCode()));
		

		ecBean.setNaturalBean(getNaturalDisastersBean(ecBeanVO.getCountryCode()));
		ecBean.setFoodSituationBean(getFoodSecuritySituation(ecBeanVO.getCountryCode()));	
		ecBean.setFoodIssuesBean(getFoodSecurityIssues(ecBeanVO.getCountryCode()));		
		ecBean.setGovernanceBean(getGovernanceBean(ecBeanVO.getCountryCode()));
		ecBean.setNews(getLatestIrinNews(ecBeanVO.getCountryCode(), 5));
	
		getGIEWSCharts(ecBean, ecBeanVO.getCountryCode());	
		
		ecBean.setIsHaitiEmergency(ecBeanVO.getIsHaitiEmergency());
		LOGGER.info("End - EC Report Bean");
		report = new CreateECReport(ecBean);
		return report.createECReport();
	}*/
	
	private String createECReportsTemplate2011(ECBeanVO ecBeanVO) {
		LOGGER.info("Creating EC Report Bean 2011");
		ECBean ecBean = new ECBean();
		/** SETTING EC REPORT BEAN **/

		
		ecBean.setCountry(ecBeanVO.getCountryLabel());
		ecBean.setCountryCode(ecBeanVO.getCountryCode());
		
		ecBean.setDate(ecBeanVO.getReportDateString());
		ecBean.setReferenceDate(FieldParser.parseDate(ecBeanVO.getReportDateField()));
		ecBean.setViewData(ecBeanVO.getViewData());
		ecBean.setReportType(ecBeanVO.getReportType());
		LOGGER.info("setReferingDate: " + ecBean.getReferenceDate());
		
		/** this is the same as country brief **/
		ecBean.setSocialEconomicBean(getECSocialEconomicBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		
		/** this is made for haiti **/
		ecBean.setKeyMessages(getECKeyMessages(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setBackgorundText(getBackground(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setPriceBean(getPriceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowPriceLegend()));
		ecBean.setPriceBeanSecondColumn(getPriceSecondColumnBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowPriceLegend()));
		
		ecBean.setConflictsBean(getConflictsBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getUnhcrDate()));
		
		
		
		/** Second Page **/
		ecBean.setNaturalBean(getNaturalDisastersBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowVegetationConditionLegend()));
		ecBean.setFoodSituationBean(getFoodSecuritySituation(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setFoodIssuesBean(getFoodSecurityIssues(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowFoodBalanceLegend()));
		ecBean.setGovernanceBean(getGovernanceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setNews(getLatestIrinNews(ecBeanVO.getCountryCode(), 5));
		ecBean.setCropCalendarBean(getCropCalendar(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setIpcBean(getIPcBean(ecBeanVO.getCountryCode()));
	
		getGIEWSCharts(ecBean, ecBeanVO.getCountryCode(), ecBean.getReferenceDate());	
		
		ecBean.setIsHaitiEmergency(ecBeanVO.getIsHaitiEmergency());
		LOGGER.info("End - EC Report Bean 2011");
		
		LOGGER.info(ecBean.getPriceBean().getShowLegend());
		LOGGER.info(ecBean.getFoodIssuesBean().getShowLegend());
		LOGGER.info(ecBean.getNaturalBean().getShowLegend());
		LOGGER.info(ecBean.getConflictsBean().getUnhcrDate());
		LOGGER.info(ecBean.getReportType());
		
		CreateECReport2011 report = new CreateECReport2011(ecBean);
		return report.createECReport(ecBeanVO.getExportPDF());
	}
	
	private String createTemporarelyECReports(ECBeanVO ecBeanVO) {
		LOGGER.info("Creating EC Report Bean");
		ECBean ecBean = new ECBean();
		/** SETTING EC REPORT BEAN **/
		/** First Page **/
		
		ecBean.setCountry(ecBeanVO.getCountryLabel());
		ecBean.setCountryCode(ecBeanVO.getCountryCode());
		
		ecBean.setDate(ecBeanVO.getReportDateString());
		ecBean.setReferenceDate(FieldParser.parseDate(ecBeanVO.getReportDateField()));
		ecBean.setViewData(ecBeanVO.getViewData());
		ecBean.setReportType(ecBeanVO.getReportType());
		LOGGER.info("setReferingDate: " + ecBean.getReferenceDate());
		
		/** this is the same as country brief **/
		ecBean.setSocialEconomicBean(getECSocialEconomicBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		
		/** this is made for haiti **/
		ecBean.setKeyMessages(getECKeyMessages(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setBackgorundText(getBackground(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setPriceBean(getPriceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowPriceLegend()));
		ecBean.setConflictsBean(getConflictsBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getUnhcrDate()));
		
		/** Second Page **/
		ecBean.setNaturalBean(getNaturalDisastersBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowVegetationConditionLegend()));
		ecBean.setFoodSituationBean(getFoodSecuritySituation(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setFoodIssuesBean(getFoodSecurityIssues(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowFoodBalanceLegend()));
		ecBean.setGovernanceBean(getGovernanceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setNews(getLatestIrinNews(ecBeanVO.getCountryCode(), 5));
	
		getGIEWSCharts(ecBean, ecBeanVO.getCountryCode(), ecBean.getReferenceDate());	
		
		ecBean.setIsHaitiEmergency(ecBeanVO.getIsHaitiEmergency());
		LOGGER.info("End - EC Report Bean");
		
		LOGGER.info(ecBean.getPriceBean().getShowLegend());
		LOGGER.info(ecBean.getFoodIssuesBean().getShowLegend());
		LOGGER.info(ecBean.getNaturalBean().getShowLegend());
		LOGGER.info(ecBean.getConflictsBean().getUnhcrDate());
		LOGGER.info(ecBean.getReportType());
		
		report = new CreateECReport(ecBean);
		return report.createECReport(ecBeanVO.getExportPDF());
	}
	
	
	
	private String createHaitiECReport(ECBeanVO ecBeanVO) {
		LOGGER.info("Creating HAITI Emergency Report Bean");
		ECBean ecBean = new ECBean();
		/** get Language labels **/
		try {
			ecBean.setLanguageMap(getLanguagesLabels(ecBeanVO.getLanguage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** SETTING EC REPORT BEAN **/
		
		/** First Page **/
		
		ecBean.setCountry(ecBeanVO.getCountryLabel());
		ecBean.setCountryCode(ecBeanVO.getCountryCode());
		ecBean.setLanguage(ecBeanVO.getLanguage());
		ecBean.setViewData(ecBeanVO.getViewData());
		ecBean.setReportType(ecBeanVO.getReportType());
		
		/** TODO: The DATE should be manage temporarily, based on which period the user is doing the report **/	
		ecBean.setDate(ecBeanVO.getReportDateString().replace(" ", "").toLowerCase());
//		ecBean.setDate(getDate());
//		ecBean.setDate("October 2009");
		ecBean.setReferenceDate(FieldParser.parseDate(ecBeanVO.getReportDateField()));
		
		/** tODO: remove**/
		ecBean.setNaturalBean(getNaturalDisastersBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBeanVO.getShowVegetationConditionLegend()));
		
		ecBean.setSocialEconomicBean(getECSocialEconomicBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate()));
		ecBean.setKeyMessages(getHaitiKeyMessages(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setBackgorundText(getHaitiBackground(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setPriceBean(getHaitiPriceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setConflictsBean(getHaitiConflictsBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		
		/** Second Page **/
		ecBean.setMaps(getHaitiECMaps(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setFoodIssuesBean(getHaitiFoodSecurityIssues(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setGovernanceBean(getHaitiGovernanceBean(ecBeanVO.getCountryCode(), ecBean.getReferenceDate(), ecBean.getLanguage()));
		ecBean.setNews(getLatestReliefWebNews(ecBeanVO.getCountryCode(), ecBeanVO.getCountryLabel(), 5));
	
//		getGIEWSCharts(ecBean, ecBeanVO.getCountryCode());	
		
		/** This is for haiti emergecy report **/
		ecBean.setIsHaitiEmergency(ecBeanVO.getIsHaitiEmergency());
		LOGGER.info("End - HAITI EC Report Bean");
		report = new CreateECReport(ecBean);
		return report.createHaitiEmergencyReport();
	}
	
//	private String getBackground(String countryCode){
//		return ecDao.getBackgroundText(countryCode);
//	}
	
	private String getBackground(String countryCode, Date referenceDate){
		return ecDao.getBackgroundText(countryCode, referenceDate);
	}
	
//	private ECGovernanceBean getGovernanceBean(String countryCode){
//		ECGovernanceBean bean = new ECGovernanceBean();
//		bean = ecDao.getGovernamentPolicies(countryCode);
//		bean.setGovernanceText(ecDao.getGovernanceText(countryCode));
//		return bean;
//	}
//	
	private ECGovernanceBean getGovernanceBean(String countryCode, Date referenceDate){
		ECGovernanceBean bean = new ECGovernanceBean();
		bean = ecDao.getGovernamentPolicies(countryCode, referenceDate);
		bean.setGovernanceText(ecDao.getGovernanceText(countryCode, referenceDate));
		return bean;
	}
	
//	private ECFoodSecuritySituationBean getFoodSecuritySituation(String countryCode){
//		ECFoodSecuritySituationBean bean = ecDao.getFoodSecuritySituation(countryCode);
//		bean.setFoodText(ecDao.getFoodSecuritySituationText(countryCode));
//		String iso2country = codecDao.getLabelFromCodeCodingSystem(countryCode, "GAUL-ISO2 Codes", "0");
//		bean.setFewsnetLink("http://v4.fews.net/pages/country.aspx?gb="+ iso2country.toLowerCase() + "&l=en");
//		ecDao.getFewsnetInfo(bean, countryCode);	
//		return bean;
//	}
	
	private ECFoodSecuritySituationBean getFoodSecuritySituation(String countryCode, Date referenceDate){
		ECFoodSecuritySituationBean bean = ecDao.getFoodSecuritySituation(countryCode, referenceDate);
		bean.setFoodText(ecDao.getFoodSecuritySituationText(countryCode, referenceDate));
		String iso2country = codecDao.getLabelFromCodeCodingSystem(countryCode, "GAUL-ISO2 Codes", "0", "EN");
		bean.setFewsnetLink("http://v4.fews.net/pages/country.aspx?gb="+ iso2country.toLowerCase() + "&l=en");
		ecDao.getFewsnetInfo(bean, countryCode, referenceDate);	
		return bean;
	}
	
//	private ECFoodSecurityIssuesBean getFoodSecurityIssues(String countryCode){
//		ECFoodSecurityIssuesBean bean = new ECFoodSecurityIssuesBean();
//		bean.setFoodText(ecDao.getFoodSecurityIssuesText(countryCode));
//		return bean;
//	}
	
	private ECFoodSecurityIssuesBean getFoodSecurityIssues(String countryCode, Date referenceDate, String showLegend){
		ECFoodSecurityIssuesBean bean = new ECFoodSecurityIssuesBean();
		bean.setFoodText(ecDao.getFoodSecurityIssuesText(countryCode, referenceDate));
		bean.setShowLegend(showLegend);
		LOGGER.info(showLegend);
		return bean;
	}
	
//	private ECNaturalDisastersBean getNaturalDisastersBean(String countryCode){
//		ECNaturalDisastersBean bean = new ECNaturalDisastersBean();
//		bean.setNaturalDisastersText(ecDao.getNaturalDisastersText(countryCode));
//		bean.setNaturalDisastersChart(countryCode + "_Chart.png");
//		bean.setNaturalDisastersMap(countryCode + "_Map.png");
//		return bean;
//	}
	
	private ECNaturalDisastersBean getNaturalDisastersBean(String countryCode, Date referenceDate, String showLegend){
		ECNaturalDisastersBean bean = new ECNaturalDisastersBean();
		bean.setNaturalDisastersText(ecDao.getNaturalDisastersText(countryCode, referenceDate));
		String date = FieldParser.date2ymd(referenceDate);
		String jrcChart = countryCode + "_chart_"+ date + ".png";
		bean.setNaturalDisastersChart("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + countryCode + File.separator + jrcChart + "\"");
		String jrcMap = countryCode + "_map_"+ date + ".png";
		bean.setNaturalDisastersMap("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + countryCode + File.separator + jrcMap + "\"");
		bean.setShowLegend(showLegend);
		return bean;
	}
	
//	private ECConflictsBean getConflictsBean(String countryCode){
//		String text = ecDao.getConflictsText(countryCode);
//		ECConflictsBean bean = ecDao.getRefugees(countryCode);
//		ECConflictsBean conflicts = ecDao.getConflicts(countryCode);
//		conflicts.getConflicts1().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts1().getValue(), "Conflict_status", "0"));
//		conflicts.getConflicts2().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts2().getValue(), "Conflict_status", "0"));
//		conflicts.getConflicts3().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts3().getValue(), "Conflict_status", "0"));
//		
//		bean.setConflicts1(conflicts.getConflicts1());
//		bean.setConflicts2(conflicts.getConflicts2());
//		bean.setConflicts3(conflicts.getConflicts3());
//		
//		bean.setEarthQuakes(ecDao.getEarthQuakes(countryCode));
//		bean.setFloods(ecDao.getFloods(countryCode));
//		
//		bean.setConflictsText(text);
//		return bean;
//	}
	
	private ECConflictsBean getConflictsBean(String countryCode, Date referenceDate, String unhcrDate){
		String text = ecDao.getConflictsText(countryCode, referenceDate);
		ECConflictsBean bean = ecDao.getRefugees(countryCode);
		ECConflictsBean conflicts = ecDao.getConflicts(countryCode);
		conflicts.getConflicts1().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts1().getValue(), "Conflict_status", "0", "EN"));
		conflicts.getConflicts2().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts2().getValue(), "Conflict_status", "0", "EN"));
		conflicts.getConflicts3().setValue(codecDao.getLabelFromCodeCodingSystem(conflicts.getConflicts3().getValue(), "Conflict_status", "0", "EN"));
		
		bean.setConflicts1(conflicts.getConflicts1());
		bean.setConflicts2(conflicts.getConflicts2());
		bean.setConflicts3(conflicts.getConflicts3());
		
		bean.setEarthQuakes(ecDao.getEarthQuakes(countryCode, referenceDate));
		bean.setFloods(ecDao.getFloods(countryCode, referenceDate));
		
		bean.setConflictsText(text);
		
		bean.setUnhcrDate(unhcrDate);
		
		return bean;
	}
	
//	private ECPriceBean getPriceBean(String countryCode){
//		ECPriceBean bean = new ECPriceBean();
//		bean.setPriceText(ecDao.getPriceText(countryCode));
//		return bean;
//	}
	
	private ECPriceBean getPriceBean(String countryCode, Date referenceDate, String showLegend){
		ECPriceBean bean = new ECPriceBean();
		bean.setPriceText(ecDao.getPriceText(countryCode, referenceDate));
		bean.setShowLegend(showLegend);
		return bean;
	}
	
	private ECPriceBean getPriceSecondColumnBean(String countryCode, Date referenceDate, String showLegend){
		ECPriceBean bean = new ECPriceBean();
		bean.setPriceText(ecDao.getPriceSecondColumnText(countryCode, referenceDate));
		return bean;
	}
	
	
	private String getDate(){
		Date date = new Date();
		String year = Integer.toString(1900 + date.getYear());
		String month = monthLabelMap.get(date.getMonth());
		return month + " " + year;
	}
	
//	private ECKeyMessages getECKeyMessages(String countryCode) {
//		return ecDao.getKeyMessages(countryCode);
//	}
	
	
	private ECKeyMessages getECKeyMessages(String countryCode, Date referenceDate) {
		return ecDao.getKeyMessages(countryCode, referenceDate);
	}
	
//	private ECSocialEconomicBean getECSocialEconomicBean(String countryCode) {
//		return ecDao.getSocialEconomicBean(countryCode);
//	}
	
	private ECSocialEconomicBean getECSocialEconomicBean(String countryCode, Date referingDate) {
		return ecDao.getSocialEconomicBean(countryCode, referingDate);
	}
	
	

	public List<String> saveCharts(List<String> images) {
		List<String> fileNames = new ArrayList<String>();
		for (String image: images) {
			String nameFile = BirtUtil.randomFileExport() + ".png";
			
			LOGGER.info(Setting.getFenixPath());
			LOGGER.info(Setting.getBirtApplName());
			LOGGER.info(nameFile);
			
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ec/" + nameFile);
			System.out.println("PATH: " + file.getAbsolutePath());
			byte[] imageBytes = Base64.decode(image);
			
//			System.out.println("imageBytes: " + imageBytes);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(imageBytes);
				fos.close();
				fileNames.add(nameFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileNames;
	}

	

	
	
//	public void getGIEWSCharts(ECBean bean, String gaulCode) {
//		ECGIEWSChartsParser p = new ECGIEWSChartsParser();
//		String isoCode = codecDao.getLabelFromCodeCodingSystem(gaulCode, "GAUL-ISO3 Codes", "0");
//		bean.getPriceBean().setChartFileName(p.getGIEWSPriceChart(isoCode));
//		bean.getFoodIssuesBean().setChartFileName(p.getGIEWSFoodSecurityIssueChart(isoCode));
//		
//	}
	
	public void getGIEWSCharts(ECBean bean, String gaulCode, Date referenceDate) {
		ECGIEWSChartsParser p = new ECGIEWSChartsParser();
		/** TODO: THIS SHould be based on stored images and not dinamically taken from giewss **/
//		String isoCode = codecDao.getLabelFromCodeCodingSystem(gaulCode, "GAUL-ISO3 Codes", "0");
		/** SELECT A DATE **/
		String date = FieldParser.date2ymd(referenceDate);
//		String date = "2010-03-01";
	
		String priceChart = gaulCode + "_pricechart_"+ date +".jpg";
		bean.getPriceBean().setChartFileName("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + gaulCode + File.separator + priceChart + "\"");
		String foodIssuesChart = gaulCode + "_foodissueschart_"+ date +".jpg";
		bean.getFoodIssuesBean().setChartFileName("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + gaulCode + File.separator + foodIssuesChart + "\"");
//		bean.getPriceBean().setChartFileName(gaulCode + "_pricechart_"+ date +".png");
//		bean.getFoodIssuesBean().setChartFileName(gaulCode + "foodissueschart_"+ date +".png");
//		bean.getPriceBean().setChartFileName(p.getGIEWSPriceChart(isoCode));
//		bean.getFoodIssuesBean().setChartFileName(p.getGIEWSFoodSecurityIssueChart(isoCode));
		
	}

	public List<String> getLatestIrinNews(String gaulCode, int entries) {
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( gaulCode.equals("13") || gaulCode.equals("19") || gaulCode.equals("92") || gaulCode.equals("165")) {
			
			List<String> links = new ArrayList<String>();

			// Policy Briefs
			links.add("<a href='http://www.fao.org/economic/es-policybriefs/briefs-detail/en/?no_cache=1&uid=48900'>FAO - Policy Briefs</a>");
			
			// Global food price monitor
			links.add("<a href='http://www.fao.org/giews/english/gfpm/GFPM_01_2011.pdf'>FAO - Global food price monitor</a>");
			
			// Crop Prospects and Food Situation
			links.add("<a href='http://www.fao.org/docrep/013/al972e/al972e00.pdf'>FAO - Crop Prospects and Food Situation</a>");

			// FAO Media center
			links.add("<a href='http://www.fao.org/news/story/en/item/49954/icode/'>FAO - Media Center</a>");
			
			return links;
		}
		else {
			ECRSSParser p = new ECRSSParser(); 
			String iso3Code = codecDao.getLabelFromCodeCodingSystem(gaulCode, "GAUL-ISO3 Codes", "0", "EN");
			return p.getLatestIrinNews(gaulCode, entries, iso3Code);
		}
	}
	
	public CodeVo getECCountry(String gaul0Code) {
		Code code = codecDao.getCodeFromCodeSystemRegion(gaul0Code, "EC_GAUL0", "0", "EN");
		return code2vo(code);
	}
	
	
	private CodeVo code2vo(Code c) {
		CodeVo vo = new CodeVo();
		vo.setCode(c.getCode());
		vo.setDescription(c.getDescription());
		vo.setLabel(c.getLabel());
		vo.setLangCode(c.getLangCode());
		return vo;
	}



	public void setEcDao(ECDao ecDao) {
		this.ecDao = ecDao;
	}





	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	
	public void harverstGiewsCharts() {
		ECGIEWSChartsParser p = new ECGIEWSChartsParser();
		Date date = new Date();
//		String dateString = FieldParser.date2ymd(date);
		/** SELECT A DATE **/
//		String date = FieldParser.date2ymd(referenceDate);
		String dateString = "2011-10-05";
		List<Code> gaulCodes = codecDao.getCodesOfaSystemLangCode("GAUL0", "0", "EN");
		for(Code gaulCode : gaulCodes) {
			LOGGER.info("retrieving: " + gaulCode.getLabel());
			String isoCode = codecDao.getLabelFromCodeCodingSystem(gaulCode.getCode(), "GAUL-ISO3 Codes", "0", "EN");
			p.harverstGiewsCharts(isoCode, gaulCode.getCode(), gaulCode.getLabel(), dateString);
		}
		
	}

	
	/*********************************************************
	 * 
	 *  HAITI EMERGENCY REPORT 
	 *  
	 *  *************************************************/

	
	private ECKeyMessages getHaitiKeyMessages(String countryCode, Date referenceDate, String language) {
		return ecDao.getHaitiKeyMessages(countryCode, referenceDate, language);
	}
	
	private String getHaitiBackground(String countryCode, Date referenceDate, String language){
		return ecDao.getHaitiBackgroundText(countryCode, referenceDate, language);
	}
	
	private ECPriceBean getHaitiPriceBean(String countryCode, Date referenceDate, String language){
		ECPriceBean bean = new ECPriceBean();
		bean.setPriceText(ecDao.getHaitiPriceText(countryCode, referenceDate));
		String date = FieldParser.date2ymd(referenceDate);
		bean.setChartValues(countryCode + "_price_values_"+ language + "_" + date +".png");
		bean.setChartVariations(countryCode + "_price_variations_"+ language + "_" +  date +".png");
		return bean;
	}
	
	private ECConflictsBean getHaitiConflictsBean(String countryCode, Date referenceDate, String language){
		ECConflictsBean bean= new ECConflictsBean();
		String text = ecDao.getHaitiConflictsText(countryCode, referenceDate);
		String date = FieldParser.date2ymd(referenceDate);
		bean.setChart(countryCode + "_refugees_"+ language + "_" +  date +".png");
		bean.setConflictsText(text);
		return bean;
	}
	
	
	private ECMaps getHaitiECMaps(String countryCode, Date referenceDate, String language){
		ECMaps bean = new ECMaps();
		bean.setMap1Text(ecDao.getHaitiTradeFlowText1(countryCode, referenceDate));
		bean.setMap2Text(ecDao.getHaitiTradeFlowText2(countryCode, referenceDate));
//		bean.setMap1(countryCode + "_Flow_Beans2.png");
		String date = FieldParser.date2ymd(referenceDate);
		bean.setMap1(countryCode + "_flow_maize_"+ language + "_" + date +".png");
		bean.setMap2(countryCode + "_move_people_"+ language + "_" + date +".png");
		bean.setLegend(countryCode + "_map_legend_"+ language + "_" + date +".png");

		return bean;
	}
	
	private ECFoodSecurityIssuesBean getHaitiFoodSecurityIssues(String countryCode, Date referenceDate, String language){
		ECFoodSecurityIssuesBean bean = new ECFoodSecurityIssuesBean();
		bean.setFoodText(ecDao.getHaitiFoodSecurityIssuesText(countryCode, referenceDate));
		String date = FieldParser.date2ymd(referenceDate);
		bean.setChartFileName(countryCode + "_cropcalendaremergency_"+ language + "_" +  date +".png");
		bean.setMap(countryCode + "_livelihood_"+ language + "_" +  date +".png");
		return bean;
	}
	
	private List<String> getLatestReliefWebNews(String gualCode, String gaulLabel, int entries) {
		ECRSSParser p = new ECRSSParser();	
		String iso3Code = codecDao.getLabelFromCodeCodingSystem(gualCode, "GAUL-ISO3 Codes", "0", "EN");
		return p.getLatestRefliefWebNews(gaulLabel, iso3Code.toLowerCase(), entries);
	}
	
	private ECGovernanceBean getHaitiGovernanceBean(String countryCode, Date referenceDate, String language){
		ECGovernanceBean bean = new ECGovernanceBean();
		bean.setGovernanceText(ecDao.getHaitiGovernanceText(countryCode, referenceDate, language));
		return bean;
	}
	
	
	
	/** This is used to load the countries **/
	
	public TreeMap<String, ECItemVO> getCountries() throws FenixGWTException {
		TreeMap<String, ECItemVO> countries = new TreeMap<String, ECItemVO>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + "ec_countries_list.xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList countriesNode = element.getElementsByTagName("country");
			for (int i = 0; i < countriesNode.getLength(); i++) {
				Element countryElement = (Element) countriesNode.item(i);
				String countryLabel = countryElement.getAttribute("title");
				String countryCode = countryElement.getAttribute("code");
				ECItemVO vo = new ECItemVO();			
				vo.setCode(countryCode);
				NodeList datesNode = countryElement.getElementsByTagName("dates");			
				for (int j = 0; j < datesNode.getLength(); j++) {
					LinkedHashMap<String, ECItemConfigurationVO> dates = new LinkedHashMap<String, ECItemConfigurationVO>();
					Element datesElement = (Element) datesNode.item(j);
					NodeList dateNode = datesElement.getElementsByTagName("date");					
					for (int k = 0; k < dateNode.getLength(); k++) {
						ECItemConfigurationVO ecItem = new ECItemConfigurationVO();
						Element dateElement = (Element) dateNode.item(k);
						String stringDate = dateElement.getAttribute("stringDate");
						String referenceDate = dateElement.getAttribute("referenceDate");
						ecItem.setStringDate(stringDate);
						
						String priceLegend = dateElement.getAttribute("priceLegend");
						if ( !priceLegend.equals("") ) {
							ecItem.setShowPriceLegend(priceLegend);
						}
						
						String foodBalanceLegend = dateElement.getAttribute("foodBalanceLegend");
						if ( !foodBalanceLegend.equals("") )
							ecItem.setShowFoodBalanceLegend(foodBalanceLegend);
						
						String vegetationConditionLegend = dateElement.getAttribute("vegetationConditionLegend");
						if ( !vegetationConditionLegend.equals(""))
							ecItem.setShowVegetationConditionLegend(vegetationConditionLegend);
						
						String unhcrDate = dateElement.getAttribute("unhcrDate");
						if ( !unhcrDate.equals("") )
							 ecItem.setUnhcrDate(unhcrDate);
						
						String viewData = dateElement.getAttribute("viewData");
						if (  !viewData.equals("") )
							ecItem.setViewData(viewData);
						
						String reportType = dateElement.getAttribute("reportType");
						if (  !reportType.equals("") )
							ecItem.setReportType(reportType);
						
						LOGGER.info("COUNTRY: + " + countryLabel);
						LOGGER.info("PRICE LEGEND: " + ecItem.getShowPriceLegend());
						LOGGER.info("BALANCE LEGEND " + ecItem.getShowFoodBalanceLegend());
						LOGGER.info("VEGETATION CONDITION LEGEND: " + ecItem.getShowVegetationConditionLegend());
						LOGGER.info("UNHCRDATE: " + ecItem.getUnhcrDate());
						
						dates.put(referenceDate, ecItem);
					}		
					vo.setDates(dates);
				}
				
				countries.put(countryLabel, vo);				
			}
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
//		countries = sortByKeys(countries);
//		print(countries);
		return countries;
	}
	
	public TreeMap<String, ECItemVO> getHaitiList() throws FenixGWTException {
		TreeMap<String, ECItemVO> countries = new TreeMap<String, ECItemVO>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + "ec_haiti_list.xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList countriesNode = element.getElementsByTagName("country");
			for (int i = 0; i < countriesNode.getLength(); i++) {
				Element countryElement = (Element) countriesNode.item(i);
				String countryLabel = countryElement.getAttribute("title");
				String countryCode = countryElement.getAttribute("code");
				ECItemVO vo = new ECItemVO();			
				vo.setCode(countryCode);
				NodeList datesNode = countryElement.getElementsByTagName("dates");			
				for (int j = 0; j < datesNode.getLength(); j++) {
					LinkedHashMap<String, ECItemConfigurationVO> dates = new LinkedHashMap<String, ECItemConfigurationVO>();
					Element datesElement = (Element) datesNode.item(j);
					NodeList dateNode = datesElement.getElementsByTagName("date");
					ECItemConfigurationVO ecItem = new ECItemConfigurationVO();
					for (int k = 0; k < dateNode.getLength(); k++) {
						Element dateElement = (Element) dateNode.item(k);
						String stringDate = dateElement.getAttribute("stringDate");
						String referenceDate = dateElement.getAttribute("referenceDate");
						ecItem.setStringDate(stringDate);
						dates.put(referenceDate, ecItem);
					}		
					vo.setDates(dates);
				}
				
				countries.put(countryLabel, vo);				
			}
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
//		countries = sortByKeys(countries);
//		print(countries);
		return countries;
	}

	private void print(TreeMap<String, ECItemVO> countries){
		for (String key : countries.keySet()) {
			 System.out.println("Key: " + key + " | " + countries.get(key).getCode() + " | " + countries.get(key).getDates());
		}
	}
	
	
	private static TreeMap<String, ECItemVO> sortByKeys(Map<String, ECItemVO> in) {
		TreeMap<String, ECItemVO> out = new TreeMap<String, ECItemVO>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	private Map<String, String> getLanguagesLabels(String lang) throws IOException {
		Properties propertiesFile = new Properties();
		propertiesFile.load(new FileInputStream(new File(dir + File.separator + "ECLang_" + lang +".properties")));
		Map<String, String> language = new HashMap<String, String>();
		
		String key;
		Enumeration e = propertiesFile.propertyNames();
		while (e.hasMoreElements()) {
			key = (String)e.nextElement();
			language.put(key, propertiesFile.getProperty(key));
		}
		return language;
	}
	
	private ECCropCalendarBean getCropCalendar(String countryCode, Date referenceDate){
		ECCropCalendarBean bean = new ECCropCalendarBean();
		bean = ecDao.getCropCalendarBean(countryCode, referenceDate);
		return bean;
	}
	
	private ECIPCBean getIPcBean(String countryCode){
		ECIPCBean bean = new ECIPCBean();
		bean = ecDao.getIPC(countryCode);
		return bean;
	}
	

}
