package org.fao.fenix.web.modules.ec.client.control;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ec.client.view.ECWindow;
import org.fao.fenix.web.modules.ec.common.services.ECServiceEntry;
import org.fao.fenix.web.modules.ec.common.vo.ECBeanVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItem;
import org.fao.fenix.web.modules.ec.common.vo.ECItemConfigurationVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.axis.XAxis;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.BarChart;
import com.extjs.gxt.charts.client.model.charts.StackedBarChart;
import com.extjs.gxt.charts.client.model.charts.StackedBarChart.StackValue;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.elements.HorizontalStackedBarChart;


public class ECController {
	
	private static Timer timer;
	
	private static Timer newTimer;
	
	
	public static SelectionListener<ButtonEvent> createNewReport(final ECWindow window) {
		return new SelectionListener<ButtonEvent>() {		
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow("Exporting","Exporting report to PDF","Loading");
				loading.showLoadingBox();
				getReport(window, loading);
				
			}
		};
	}
	
	public static void getReport(final ECWindow window, final LoadingWindow loading) {
		newTimer = new Timer() {
			public void run() {
				loading.destroyLoadingBox();
				ECItem selectedcItem = window.countryComboBox.getValue();
				final String countryCode = selectedcItem.getValue();
				final String countryLabel = selectedcItem.getName();
				System.out.println("Country: " + countryCode + " | " + countryLabel);	
				com.google.gwt.user.client.Window.open("../ecReports/"+ countryCode +"_EC.pdf", "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
		};
		newTimer.schedule(15000);
	}
			
	public static void destroyNewTimer() {
		if (newTimer != null)
			newTimer.cancel();
	}	
	
	public static void fillECListBox(final ECWindow window) {
		ECServiceEntry.getInstance().getCountryList(new AsyncCallback<TreeMap<String, ECItemVO>>() {
			public void onSuccess(TreeMap<String, ECItemVO> result) {
				window.setCountries(result);
				List<ECItem> countryItems = new ArrayList<ECItem>();
				for (String key : result.keySet()) 
					countryItems.add(new ECItem(key, result.get(key).getCode(), false));
				
				ListStore<ECItem> store = new ListStore<ECItem>();
				store.add(countryItems);

				window.countryComboBox.getStore().add(countryItems);
				window.countryComboBox.setValue(window.countryComboBox.getStore().getAt(0));
			}
				
	
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	public static void fillHaitiEmergencyListBox(final ECWindow window) {
		ECServiceEntry.getInstance().getHaitiEmergencyList(new AsyncCallback<TreeMap<String, ECItemVO>>() {
			public void onSuccess(TreeMap<String, ECItemVO> result) {
				window.setCountries(result);
				List<ECItem> countryItems = new ArrayList<ECItem>();
				for (String key : result.keySet()) 
					countryItems.add(new ECItem(key, result.get(key).getCode(), false));
				
				ListStore<ECItem> store = new ListStore<ECItem>();
				store.add(countryItems);

				window.countryComboBox.getStore().add(countryItems);
				window.countryComboBox.setValue(window.countryComboBox.getStore().getAt(0));
			}
				
	
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	
	
	public static SelectionListener<ButtonEvent> createReport(final ECWindow window, final Boolean isHaitiEmergencyReport) {
		return new SelectionListener<ButtonEvent>() {		
			public void componentSelected(ButtonEvent ce) {		
				final LoadingWindow loading = new LoadingWindow("Creating Report","Retrieving informations from the Workstation","Loading");			
				final ECItem selectedcItem = window.countryComboBox.getValue();
				final ECItem dateItem = window.releaseDateField.getValue();
				loading.showLoadingBox();
				ECServiceEntry.getInstance().getECCountry(selectedcItem.getValue(),new AsyncCallback<CodeVo>() { 
					public void onSuccess(CodeVo ecCode) {
						ECBeanVO ecBeanVO = new ECBeanVO();
						ecBeanVO.setCountryCode(ecCode.getCode());
						ecBeanVO.setCountryLabel(ecCode.getLabel());
						ecBeanVO.setIsHaitiEmergency(isHaitiEmergencyReport);
						ecBeanVO.setUnhcrDate(dateItem.getUnhcrDate());
						ecBeanVO.setShowPriceLegend(dateItem.getShowPriceLegend());
						ecBeanVO.setShowFoodBalanceLegend(dateItem.getShowFoodBalanceLegend());
						ecBeanVO.setShowVegetationConditionLegend(dateItem.getShowVegetationLegend());
						ecBeanVO.setViewData(dateItem.getViewData());
						ecBeanVO.setReportType(dateItem.getReportType());
						
						if ( isHaitiEmergencyReport)
							ecBeanVO.setLanguage( window.language.getValue().getValue());			
						ecBeanVO.setReportDateString(dateItem.getName());
						ecBeanVO.setReportDateField(dateItem.getValue());
						ECServiceEntry.getInstance().createReport(ecBeanVO, new AsyncCallback<String>() {
							public void onSuccess(String result) {
								loading.destroyLoadingBox();										
								Window window = new Window();
								ContentPanel cont = new ContentPanel();
								cont.setHeaderVisible(false);
								cont.setLayout(new FitLayout());
								window.setLayout(new FitLayout());
								window.setSize(800, 600);
								HTML content = new HTML(result);
								cont.add(content);
								window.add(cont);
								window.show();					
							}
							public void onFailure(Throwable arg0) {	
									loading.destroyLoadingBox();		
								}
							});
						
					}
					
					public void onFailure(Throwable arg0) {	
						loading.destroyLoadingBox();		
						FenixAlert.error("RPC: Failed", "Error retriving EC label");
					}
				});
			
			}
		};
	}
	

	
	public static SelectionListener<ButtonEvent> exportAllPDF(final ECWindow window, final Boolean isHaitiEmergencyReport) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final LoadingWindow loading = new LoadingWindow("Exporting Report to PDF","Retrieving informations from the Workstation","Loading");			
				final ECItem selectedcItem = window.countryComboBox.getValue();
				final ECItem dateItem = window.releaseDateField.getValue();
				loading.showLoadingBox();
					for ( ECItem c : window.countryComboBox.getStore().getModels()) {
					ECServiceEntry.getInstance().getECCountry(c.getValue(),new AsyncCallback<CodeVo>() { 
						public void onSuccess(CodeVo ecCode) {
							ECBeanVO ecBeanVO = new ECBeanVO();
							ecBeanVO.setCountryCode(ecCode.getCode());
							ecBeanVO.setCountryLabel(ecCode.getLabel());
							ecBeanVO.setIsHaitiEmergency(isHaitiEmergencyReport);
							if ( isHaitiEmergencyReport)
								ecBeanVO.setLanguage( window.language.getValue().getValue());			
							ecBeanVO.setReportDateString(dateItem.getName());
							ecBeanVO.setReportDateField(dateItem.getValue());
							ecBeanVO.setExportPDF(true);
							ECServiceEntry.getInstance().createReport(ecBeanVO, new AsyncCallback<String>() {
								public void onSuccess(String result) {
	//								loading.destroyLoadingBox();
	//								com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "status=no");
//									FenixAlert.info("EndCreating reports", "Ok");
						
									loading.destroyLoadingBox();	
										
								}
								public void onFailure(Throwable arg0) {	
										loading.destroyLoadingBox();		
									}
								});
							
						}
						
						public void onFailure(Throwable arg0) {	
							loading.destroyLoadingBox();		
							FenixAlert.error("RPC: Failed", "Error retriving EC label");
						}
					});
				}				
			}
		};
	}
	
	
			
	
	
	
	
	public static Chart createBarStackChart(){
		Window window = new Window();
		window.setSize(600, 480);
		
		final Chart chart = new Chart("../gxt/chart/open-flash-chart.swf");
//		final Chart chart = new Chart("resources/chart/open-flash-chart.swf");
	
		
		ChartModel ch = new ChartModel();
		ch.setBackgroundColour("#FFFFFF");
		 Legend lg = new Legend(Position.RIGHT, true);  
		
		     lg.setPadding(10);  
		     ch.setLegend(lg);  
		
		
//		ChartWidget chart = new ChartWidget();
//		ChartData cd = new ChartData();
//		cd.setBackgroundColour("#FFFFFF");
		XAxis xa = new XAxis();
		YAxis ya = new YAxis();
		YAxis yra = new YAxis();
		xa.setGridColour("#FFFFFF");
		ch.setXAxis(xa);
		
		HorizontalStackedBarChart h = new HorizontalStackedBarChart();
		
		
		StackedBarChart c = new StackedBarChart();
//		c.setAnimateOnShow(animateOnShow)
		
		
//		c.setAllowNestedValues(false);
		
		BarChart b = new BarChart(com.extjs.gxt.charts.client.model.charts.BarChart.BarStyle.NORMAL);
//		BarChart b = new BarChart();
		b.addValues(5);
//		b.setAnimateOnShow(false);
//		b.set
		
//		chart.setWmode(WMode.WINDOW);
		
		
//		LineChart l = new LineChart();
		
		
//		List<Stack> stacks = new ArrayList<Stack>();
//		Stack stack = new Stack();
		List<StackValue> values = new ArrayList<StackValue>();
		StackValue v = new StackValue(2, "#5f5fcf");
		StackValue v1 = new StackValue(3, "#145444");
		StackValue v2 = new StackValue(3, "#346677");
		values.add(v);
		values.add(v1);
		values.add(v2);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
		c.addStack(values);
//		xa.setMax(1000);
		
//		ch.setXAxis(xa);
//		stack.
//		stack.addValues(10);
//		stack.addValues(20);
//		stack.addStackValues()
//		stack.addStackValues(values);
//		c.addStack(stack);
//		c.addStack(values);
//		c.add
//		c.setAnimateOnShow(false);
//		c.setAnimateOnShow(false);
//		c.setEnableEvents(false);
		
//		 PieChart pie = new PieChart();  
//		     pie.setAlpha(0.5f);  
//		     pie.setNoLabels(true);  
//		    pie.setTooltip("#label# $#val#M<br>#percent#");  
//		     pie.setColours("#ff0000", "#00aa00", "#0000ff", "#ff9900", "#ff00ff");  
//		     pie.addSlices(new PieChart.Slice(100, "AU","Australia"));  
//		     pie.addSlices(new PieChart.Slice(200, "US", "USA"));  
//		     pie.addSlices(new PieChart.Slice(150, "JP", "Japan"));  
//		     pie.addSlices(new PieChart.Slice(120, "DE", "Germany"));  
//		     pie.addSlices(new PieChart.Slice(60, "UK", "United Kingdom"));  
		    
		   
//		     ch.addChartConfig(pie);  
		
//		cd.addElements((Collection<Element>) c);
//		chart.setChartData(cd);
//		     chart.setExpressInstall("/home/fabiogrita/expressInstall.swf");
//		     chart.setExpressInstall("resources/chart/open-flash-chart.swf");
//		ch.addChartConfig(c);
//		     ch.addChartConfig(b);
		     ch.addChartConfig(c);
//		     chart.setIeFixEnabled(true);
//		     chart.setFlashVersion("10");
//		     chart.set
//		chart.setShadow(false);
//		chart.setWmode(wmode)
//		chart.setWmode(WMode.OPAQUE);
//		chart.setShim(false);
		ch.getChartConfigs().get(0).setAnimateOnShow(false);
//		pie.setAnimate(false);
//		c.setAnimateOnShow(false);
		ChartModel cm = new ChartModel();
		
		
//		cm.setSilent(true);
//		c.setModel()
		
//		chart.setWmode(wmode)
		 	chart.setSize("350", "150");
			chart.setTitle("oaijaosidj");
			ch.setTitle(new com.extjs.gxt.charts.client.model.Text("Chart Title"));
		     chart.setBorders(true);
				chart.setChartModel(ch);  
	
//		VerticalPanel p = new VerticalPanel();
//		p.add(chart);
	
//				com.rednels.ofcgwt.client.model.elements.StackedBarChart s = new com.rednels.ofcgwt.client.model.elements.StackedBarChart();
//				ChartData cd= new ChartData();
//				pie.seta
//		p.setHeight(600);
//		window.setScrollMode(Scroll.AUTO);
//		chart.setShadow(true);
//		chart.setShadowOffset(10);
		
//		chart.setShadow(false);
//		chart.setShim(true);
//		window.add(p);
//		window.add(chart);
//		window.show();
		
		ChartData cd = new ChartData();
		ChartWidget wi = new ChartWidget();
		
//		for( chart.get)
//		System.out.println(getImageData(chart));
		
//		saveChart(window);
		return chart;
	}
	
	
	
	public static SelectionChangedListener<ECItem> changeDates(final ECWindow window) {
		return new SelectionChangedListener<ECItem>() {
			public void selectionChanged(SelectionChangedEvent<ECItem> se) {				
				ECItem selectedcItem = window.countryComboBox.getValue();
				window.releaseDateField.getStore().removeAll();
	        	ECItemVO vo = window.getCountries().get(selectedcItem.getName());
	        	for(String key : vo.getDates().keySet()) {
	        		ECItemConfigurationVO item = vo.getDates().get(key);
	        		window.releaseDateField.getStore().add(new ECItem(item.getStringDate(), key, item.getShowPriceLegend(), item.getShowFoodBalanceLegend(), item.getShowVegetationConditionLegend(), item.getUnhcrDate(), item.getViewData(), item.getReportType()));		    		
	        	}
	        	window.releaseDateField.setValue(window.releaseDateField.getStore().getAt(0));
			}
		};
	}

	

	

	
	
}

