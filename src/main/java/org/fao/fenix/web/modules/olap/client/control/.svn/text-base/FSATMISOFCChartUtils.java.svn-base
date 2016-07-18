package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartFormatBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcSeriesVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;
import org.fao.fenix.web.modules.olap.client.view.OlapTool;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Legend;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.Legend.Position;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;

public class FSATMISOFCChartUtils extends OlapToolController {
	
	
	public static void createOFCChartFunction(final OlapTool tool, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {
		final OLAPParametersVo p = retrieveParameters(tool);
		if (!p.getYLabels().isEmpty() && !p.getWLabels().isEmpty()) {
			FenixAlert.info("Charts", "No charts available with 4 dimensions");
			tabPanel.getTabPanel().setSelection(tabPanel.getOlapTabItem());
		}
		else
			createOLAPOFCChartBeans(p, tool, tabPanel, chartTabItem);
	}
	
	private static void createOLAPOFCChartBeans(final OLAPParametersVo p, final OlapTool tool, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {

		OlapServiceEntry.getInstance().getChartData(p, tool.getHtml().toString(), new AsyncCallback<List<Map<String, Map<String, Double>>>>() {
			public void onSuccess(final List<Map<String, Map<String, Double>>> chartData) {
				OlapServiceEntry.getInstance().getChartsTitles(p, tool.getHtml().toString(), new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> chartTitles) {

						
						tabPanel.getChartPanel().removeAll();
			
						int i = 0;
						for (Map<String, Map<String, Double>> values : chartData) {	
							if (!chartTitles.isEmpty())
								createOfcChartBeanVO(values, p, tabPanel, chartTabItem, chartTitles.get(i));
							else
								createOfcChartBeanVO(values, p, tabPanel, chartTabItem, "");
							i++;
						}
						
						tabPanel.getTabPanel().setSelection(tabPanel.getOlapTabItem());
						tabPanel.getTabPanel().setSelection(tabPanel.getChartTabItem());
						tabPanel.getTabPanel().getLayout().layout();
						chartTabItem.addListener(Events.Select, FSATMISToolbarController.createChartFromTab(tool, tabPanel, chartTabItem));
					}
					public void onFailure(Throwable e) {
					
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					
					};
				});

				
			}
			public void onFailure(Throwable e) {
			
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			
			};
		});

	}
	
	
	@SuppressWarnings("unused")
	private static OfcChartBeanVO createOfcChartBeanVO(Map<String, Map<String, Double>> datas, OLAPParametersVo p, final FSATMISTabPanel tabPanel, final TabItem chartTabItem, String chartTitle) {
			OfcChartBeanVO chartBean = new OfcChartBeanVO();
			HashMap<String, String> xLabels = new HashMap<String, String>();
			List<String> xl = new ArrayList<String>();
			List<ValueVO> values = new ArrayList<ValueVO>();
			HashMap<String, List<ValueVO>> valuesNF = new HashMap<String, List<ValueVO>>();
		
			
				
			if (p.getYLabels().isEmpty() && p.getWLabels().isEmpty())
				create2DChart(datas, p, tabPanel, chartTabItem);
			else if (!p.getYLabels().isEmpty() && p.getWLabels().isEmpty())
				create3DColumnChart(chartTitle,datas, p, tabPanel, chartTabItem);
			else if (p.getYLabels().isEmpty() && !p.getWLabels().isEmpty())
				create3DRowChart(chartTitle,datas, p, tabPanel, chartTabItem);
//			else if (!p.getYLabels().isEmpty() && !p.getWLabels().isEmpty())
//				return create2DChartValues(p, html);
			
		
		return null;
	}
	
	private static void create2DChart(Map<String, Map<String, Double>> datas, OLAPParametersVo p, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {
		OfcChartBeanVO chartBean = new OfcChartBeanVO();

		HashMap<String, String> xLabels = new HashMap<String, String>();
		List<String> xl = new ArrayList<String>();
		List<ValueVO> values = new ArrayList<ValueVO>();
		HashMap<String, List<ValueVO>> valuesNF = new HashMap<String, List<ValueVO>>();
		Set<String> keySet = datas.keySet();
		
		for(String xLabel : keySet) {	
			Set<String> xSet = datas.get(xLabel).keySet();
			for(String ySerie : xSet) {
			
				values = new ArrayList<ValueVO>();
				for(String newX : keySet) {
					xLabels.put(newX, newX);
					values.add(new ValueVO(newX, datas.get(newX).get(ySerie)));
				}
				valuesNF.put(ySerie, values);
			}
		}
		
		for(String xLabel : keySet) {	
			xl.add(xLabel);
		}
		
		chartBean.setxLabels(xLabels);
		chartBean.setValuesNF(valuesNF);
		
		if ( !valuesNF.isEmpty()) {
			OfcChartFormatBeanVO chartFormat = createChartConfiguration(p);
				
			ContentPanel v = new ContentPanel();
			v.setBodyBorder(false);
			v.setBorders(false);
			v.setHeaderVisible(false);		
			v.add(createChart(chartBean, chartFormat));
//			v.setHeight(350);
			tabPanel.getChartPanel().add(v);
			chartTabItem.add(tabPanel.getChartPanel());
		}
	}
	
	
	private static void create3DColumnChart(String chartTitle, Map<String, Map<String, Double>> datas, OLAPParametersVo p, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {
		OfcChartBeanVO chartBean = new OfcChartBeanVO();
		HashMap<String, String> xLabels = new HashMap<String, String>();
		List<String> xl = new ArrayList<String>();
		List<ValueVO> values = new ArrayList<ValueVO>();
		HashMap<String, List<ValueVO>> valuesNF = new HashMap<String, List<ValueVO>>();
		Set<String> keySet = datas.keySet();
		
		for(String xLabel : keySet) {	
			Set<String> xSet = datas.get(xLabel).keySet();
			for(String ySerie : xSet) {
				values = new ArrayList<ValueVO>();
				for(String newX : keySet) {
					if (ySerie != null) {
						xLabels.put(newX, newX);
						xl.add(newX);
						System.out.println("ySerie:" + ySerie + " | xLABEL:" + newX +  " | VALUE: " + datas.get(newX).get(ySerie) ); 
						values.add(new ValueVO(newX, datas.get(newX).get(ySerie)));
					}
				}
				valuesNF.put(ySerie, values);
			}
		}
		
		
		chartBean.setxLabels(xLabels);
		chartBean.setValuesNF(valuesNF);
		
		if ( !valuesNF.isEmpty()) {
			OfcChartFormatBeanVO chartFormat = createChartConfiguration(p);
			chartFormat.setTitle(chartFormat.getTitle() + " - " + chartTitle);
			ContentPanel v = new ContentPanel();
			v.setBodyBorder(false);
			v.setBorders(false);
			v.setHeaderVisible(false);		
			v.add(createChart(chartBean, chartFormat));
//			v.setHeight(350);
			tabPanel.getChartPanel().add(v);
			chartTabItem.add(tabPanel.getChartPanel());
		}
	}
	
	private static void create3DRowChart(String chartTitle, Map<String, Map<String, Double>> datas, OLAPParametersVo p, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {
		OfcChartBeanVO chartBean = new OfcChartBeanVO();
		HashMap<String, String> xLabels = new HashMap<String, String>();
		List<String> xl = new ArrayList<String>();
		List<ValueVO> values = new ArrayList<ValueVO>();
		HashMap<String, List<ValueVO>> valuesNF = new HashMap<String, List<ValueVO>>();
		Set<String> keySet = datas.keySet();
		
		for(String xLabel : keySet) {	
			Set<String> xSet = datas.get(xLabel).keySet();
			for(String ySerie : xSet) {
				values = new ArrayList<ValueVO>();
				for(String newX : keySet) {
					if (ySerie != null) {
						xLabels.put(newX, newX);
						xl.add(newX);
						System.out.println("ySerie:" + ySerie + " | xLABEL:" + newX +  " | VALUE: " + datas.get(newX).get(ySerie) ); 
						values.add(new ValueVO(newX, datas.get(newX).get(ySerie)));
					}
				}
				valuesNF.put(ySerie, values);
			}
		}
			
		chartBean.setxLabels(xLabels);
		chartBean.setValuesNF(valuesNF);
		
		if ( !valuesNF.isEmpty()) {
			OfcChartFormatBeanVO chartFormat = createChartConfiguration(p);
			chartFormat.setTitle(chartFormat.getTitle() + " - " + chartTitle);
			
			ContentPanel v = new ContentPanel();
			v.setBodyBorder(false);
			v.setBorders(false);
			v.setHeaderVisible(false);
			v.add(createChart(chartBean, chartFormat));
			tabPanel.getChartPanel().add(v);
			chartTabItem.add(tabPanel.getChartPanel());
		}
	}
	
	
	private static ChartWidget createChart(OfcChartBeanVO chartBean, OfcChartFormatBeanVO chartFormat){
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData();
		cd.setBackgroundColour("#FFFFFF");
		XAxis xa = new XAxis();
		YAxis ya = new YAxis();
		YAxis yra = new YAxis();
		
		final OfcSeriesVO series = new OfcSeriesVO();
		
		
		xa.setGridColour("#FFFFFF");
		xa.setColour("#1d4589");
		ya.setColour("#1d4589");
		ya.setGridColour("#d0dded");
		
		//xa.setGridColour("#FFFFFF");
		//xa.setColour("#000000");
		//ya.setColour("#000000");
//		ya.setGridColour("#000000");
		
		chartBean.setHasFilters(false);
		
		
		if (chartFormat.getChartType().equals("Bar"))
			OfcChartWizardController.createGeneralBarChart(cd, xa, ya, yra, chartBean, series);
		else if ( chartFormat.getChartType().equals("Line"))		
			OfcChartWizardController.createGeneralLineChart(cd, xa, ya, chartBean, series);
		
		cd.setXAxis(xa);
		cd.setYAxis(ya);
		xa.setOffset(true);


		cd.setTitle(new Text(chartFormat.getTitle(), "font-size: 17px; font-family: Verdana; text-align: center;"));
		cd.setXLegend(new Text(chartFormat.getxLabel(), "font-size: 14px; font-family: Verdana; text-align: center;"));
	
		/** TYPE OF LEGEND **/
//		OfcChartUtils.setLegend(cd);
		Legend legend = new Legend(Position.RIGHT, true); 		
//		legend.setMargin(20);
		legend.setBgColour("#ffffff");
		legend.setBorder(true);
		cd.setLegend(legend);
		/** **/
		
		chart.setChartData(cd);	
//		chart.setSize("730", "310");
		chart.setSize("730", "670");
		chart.setCacheFixEnabled(true);
		return chart;
	}
	
	private static OfcChartFormatBeanVO createChartConfiguration(OLAPParametersVo p) {
		OfcChartFormatBeanVO c = new OfcChartFormatBeanVO();
		c.setChartType(p.getChartType());
		c.setTitle(p.getDataSourceTitle() + "(" + p.getFunction() + ")");
		c.setxLabel(p.getZLabel());
		c.setyLabel(p.getValueLabel());
		return c;
	}
	
}
