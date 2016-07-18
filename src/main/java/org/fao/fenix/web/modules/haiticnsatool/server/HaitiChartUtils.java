package org.fao.fenix.web.modules.haiticnsatool.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.persistence.cnsa.CNSADao;
import org.fao.fenix.web.modules.bangladesh.server.birt.SettingGraphStyle;
import org.fao.fenix.web.modules.birt.common.vo.ChartBean;
import org.fao.fenix.web.modules.birt.common.vo.ChartBeanValues;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.haiti.client.control.IntegerComparator;

public class HaitiChartUtils {

	CNSADao cnsaDao;
	
	Logger LOGGER =  Logger.getLogger(HaitiChartUtils.class);
	
	String[] color = new String[] {"#FEFEFE", "#B91313", "#CE462A", "#DD7048", 
									"#EC9C6A", "#F6CA91","#E0E0E0", "#D2FEBD"
									,"#4BE500", "#37A700","#257200", "#255800"};
	
	public String getProvinceDateChart(String title, String xTitle, Date date, String language, String width, String height) {
		
		/** rice  chart**/
		
		
		LinkedHashMap<String, String> xValuesProvinces = getValues(date, language, "Department");
		LinkedHashMap<String, String> ySerieAnomalies = getValuesOrderByCode(date, language, "NDVI Anomaly");
		
		List<String> xValues = new ArrayList<String>();
		List<String> xLabels = new ArrayList<String>();
		for( String key : xValuesProvinces.keySet() ) {
			xValues.add(key);
			xLabels.add(xValuesProvinces.get(key));
		}
		
		List<String> ySeriesLabel = new ArrayList<String>();
		for( String key : ySerieAnomalies.keySet() ) {
			ySeriesLabel.add(key);
		}
		
		LOGGER.info("xLabels: " + xLabels);
		LOGGER.info("ySeriesLabel: " + ySeriesLabel);
		
		
		ChartWizardBean chartWizardBean = SettingGraphStyle.setInfoBarChartCNSA(title, xTitle, true, "45", width, height, "Right");
		ChartBean chartBean = getChart(xLabels, xValuesProvinces, ySerieAnomalies, date);
		chartBean.setSetScripted("setScripted3");
		chartBean.setSrcScripted("srcScripted3");
		chartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(true, 8, 8, 8));
		chartWizardBean.setChartReportBean(chartBean);
		
		
		CreateHaitiCNSAReport report = new CreateHaitiCNSAReport(chartWizardBean);
		return report.createChartReport(width, height);
	
	}
	
	
	public ChartWizardBean getChartchartWizardBean(String title, String xTitle, Date date, String language, String width, String height) {
		
		/** rice  chart**/
		
		
		LinkedHashMap<String, String> xValuesProvinces = getValues(date, language, "Department");
		LinkedHashMap<String, String> ySerieAnomalies = getValuesOrderByCode(date, language, "NDVI Anomaly");
		
		List<String> xValues = new ArrayList<String>();
		List<String> xLabels = new ArrayList<String>();
		for( String key : xValuesProvinces.keySet() ) {
			xValues.add(key);
			xLabels.add(xValuesProvinces.get(key));
		}
		
		List<String> ySeriesLabel = new ArrayList<String>();
		for( String key : ySerieAnomalies.keySet() ) {
			ySeriesLabel.add(key);
		}
		
//		LOGGER.info("xLabels: " + xLabels);
//		LOGGER.info("ySeriesLabel: " + ySeriesLabel);
		
		
		ChartWizardBean chartWizardBean = SettingGraphStyle.setInfoBarChartCNSA(title, xTitle, true, "45", width, height, "Right");
		ChartBean chartBean = getChart(xLabels, xValuesProvinces, ySerieAnomalies, date);
		chartBean.setSetScripted("setScripted10");
		chartBean.setSrcScripted("srcScripted10");
		chartBean.setFormatChartVo(SettingGraphStyle.setInfoFormatBarChart(false, 8, 8, 8));
		chartWizardBean.setChartReportBean(chartBean);
		
		
		return chartWizardBean;
	}
	
	

	
	private LinkedHashMap<String, String> getValues(Date date, String language, String indicator) {
		LinkedHashMap<String, String> indicatorMap = new LinkedHashMap<String, String>();
		indicatorMap = cnsaDao.getUniqueTextValues("EMODIS_108", indicator ,language);

		LOGGER.info(indicatorMap.size());
		
		for( String key : indicatorMap.keySet() ) {
			LOGGER.info(key + " | " + indicatorMap.get(key));
		}
		
		return indicatorMap;	
	}
	
	private LinkedHashMap<String, String> getValuesOrderByCode(Date date, String language, String indicator) {
		LinkedHashMap<String, String> indicatorMap = new LinkedHashMap<String, String>();
		indicatorMap = cnsaDao.getUniqueTextValuesOrderByCode("EMODIS_108", indicator ,language);

		/// TODO: MAKE A SORT BY INTEGER OF THE CODE!!!!!!!!!!!!!!!!
		
//		LOGGER.info(indicatorMap.size());
		
//		for( String key : indicatorMap.keySet() ) {
//			LOGGER.info(key + " | " + indicatorMap.get(key));
//		}
		
		return indicatorMap;	
	}


	


	
	private ChartBean getChart(List<String> xAxisLabels, LinkedHashMap<String, String> xValues, LinkedHashMap<String, String> ySeries, Date date) {
		
		ChartBean chartBean = new ChartBean();
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		
		TreeMap<Integer, String> values = new TreeMap<Integer, String>();
		
//		LOGGER.info("ySeries: " + ySeries);

		for (String anomalie : ySeries.keySet() ) {
			values.put(Integer.valueOf(anomalie), ySeries.get(anomalie));
		}
		
		values = sortByKeys(values);
		
//		LOGGER.info("values: " + values);
		
		int i=0;
		for (Integer anomalie : values.keySet() ) {
//			LOGGER.info("anomalie: " + anomalie + " | "+ values.get(anomalie));
			List<QuantitativeCoreContent> contents = cnsaDao.getDateValues(date, String.valueOf(anomalie), "EMODIS_108");
			// X-Axes(province) , value (for each serie...the anomaly serie)
//			LOGGER.info("contents: " + contents.size());
			LinkedHashMap<String, Double> serieValues = new LinkedHashMap<String, Double>();
			for(QuantitativeCoreContent q : contents) {	
				// adding for each x its value
				serieValues.put(q.getFeatureCode(), q.getQuantity());
			}
			
//			LOGGER.info("VALUES: " + serieValues);
			YSerieBean ySerie = fillChartBean(xValues, serieValues, values.get(anomalie) );
			ySerie.setColor(BirtUtil.convertHextToRGB(color[i]));
			series.add(ySerie);
			i++;
		}
		

		chartValues.setxValues(xAxisLabels);
		chartValues.setySeries(series);
		chartBean.setChartValues(chartValues);
		return chartBean;	
	}
	
	private YSerieBean fillChartBean(LinkedHashMap<String, String> xLabels, LinkedHashMap<String, Double> yValuesMap, String ySerieName) {
		YSerieBean ySerie = new YSerieBean();
		ySerie.setLabel(ySerieName);
		List<Double> values = new ArrayList<Double>();
//		 LOGGER.info("xAxisDates size ----> " +  xAxisDates.size() + " | " + xAxisDates);
		for(String xValue : xLabels.keySet() ){
			Double value = yValuesMap.get(xValue);
//			LOGGER.info("xValue ----> " +  xValue + " | " + value);
			if ( value != null) {
				values.add(value);
			}
			else 
				values.add(null);
			
		}
		
//		LOGGER.info("values sizes ----> " +  values.size());
		ySerie.setValues(values);	 
		return ySerie;
	}
	
	public void setCnsaDao(CNSADao cnsaDao) {
		this.cnsaDao = cnsaDao;
	}
	
	private static TreeMap<Integer, String> sortByKeys(Map<Integer, String> in) {
		TreeMap<Integer, String> out = new TreeMap<Integer, String>(new IntegerComparator());
		for (Integer key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
}
