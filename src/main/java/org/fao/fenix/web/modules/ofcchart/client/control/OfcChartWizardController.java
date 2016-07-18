/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.ofcchart.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.bangladesh.client.view.BangladeshWindow;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.utils.OfcChartUtils;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcBarChart;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcLineChart;
import org.fao.fenix.web.modules.ofcchart.client.view.viewer.OfcChartViewer;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcAxisPanel;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartType;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartWizard;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcOtherDimensionsPanel;
import org.fao.fenix.web.modules.ofcchart.common.services.OfcChartServiceEntry;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartFormatBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcFilterVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcSeriesVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.LineChart;

public class OfcChartWizardController {
	
	public static ChartWidget createGeneralChart(OfcChartBeanVO chartBean, OfcChartFormatBeanVO chartFormatBean) {

		final String fileName = new String();
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData();
		cd.setBackgroundColour("#FFFFFF");
		XAxis xa = new XAxis();
		YAxis ya = new YAxis();
		
		final OfcSeriesVO series = new OfcSeriesVO();
		
		
		

		xa.setSteps(chartFormatBean.getXsteps());
//		xa.setSteps(12);
//		xa.setMax(20091005);
//		xa.setMin(20081005);
		xa.setGridColour("#FFFFFF");
		ya.setGridColour("#FFFFFF");
		
		if ( chartFormatBean.getChartType().equals("Pie")) {
		}
		else if ( chartFormatBean.getChartType().equals("Bar")) {
			createGeneralLineChart(cd, xa, ya, chartBean, series, chartFormatBean.getChartStyle());
			}
		else if ( chartFormatBean.getChartType().equals("Line")) {
			createLineChart(cd, xa, ya, chartBean, series, chartFormatBean.getChartStyle());
		}
	
//		cd.setTitle(new Text(chartBean.getDatasetName()));
		cd.setXAxis(xa);
		cd.setYAxis(ya);
		
	
		
		OfcChartUtils.setLegend(cd);
		

		chart.setChartData(cd);
		
		chart.setSize("355", "220");
		chart.setCacheFixEnabled(true);
		
		Window window = new Window();
		window.add(chart);
		window.show();

		return chart;
	}
	
	
	
	
	public static ChartWidget createChartFileBangladesh(BangladeshWindow window, List<OfcChartBeanVO> chartBeans, OfcChartFormatBeanVO chartFormatBean) {

		final String fileName = new String();
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData();
		cd.setBackgroundColour("#FFFFFF");
		XAxis xa = new XAxis();
		YAxis ya = new YAxis();
		YAxis yra = new YAxis();
		final OfcSeriesVO series = new OfcSeriesVO();
		
		
		
		for(OfcChartBeanVO bean : chartBeans) 
			createBangladeshLineChart(cd, xa, ya, bean, series, chartFormatBean.getChartStyle());
//			createLineChart(cd, xa, ya, bean, series, chartFormatBean.getChartStyle());

		xa.setSteps(chartFormatBean.getXsteps());
//		xa.setSteps(12);
//		xa.setMax(20091005);
//		xa.setMin(20081005);
		xa.setGridColour("#FFFFFF");
		ya.setGridColour("#FFFFFF");
	
		cd.setTitle(new Text(chartBeans.get(0).getDatasetName()));
		cd.setXAxis(xa);
		cd.setYAxis(ya);
		
	
		
		OfcChartUtils.setLegend(cd);
		

		chart.setChartData(cd);
		
		chart.setSize("355", "220");
		chart.setCacheFixEnabled(true);
		window.getBangladeshTabPanel().getBangladeshPreviewTab().getPanel().add(chart);
		window.getBangladeshTabPanel().getTabPanel().setSelection(window.getBangladeshTabPanel().getNewOutlookTab());
		window.getBangladeshTabPanel().getTabPanel().setSelection(window.getBangladeshTabPanel().getPreviewTab());
		


		return chart;
	}
	
	
	public static SelectionListener<ButtonEvent> createChart(final OfcChartWizard window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<OfcParametersVo> allParametersVo = new ArrayList<OfcParametersVo>();
				
				List<DatasetVO> datasets = window.getSelectXAxis().getDatasets();
				System.out.println("Dataset selected: " + datasets.size());
				/** preparing all the parameters for the query **/
				/** ChartFormatBeanVO **/
				OfcChartFormatBeanVO chartFormatBean = window.getChartFormatBean();
				/** set chart **/
				
				
				
				for(DatasetVO dataset : datasets) {
					OfcParametersVo parametersVo = new OfcParametersVo();
					parametersVo.setDataset(dataset);
					
					/** OfcParameterersVO **/
					/** X-AXIS **/
					HashMap<String, DimensionsBeanVO> selectedX = window.getSelectXAxis().getSelectedXDimensions();
					HashMap<String, String> selectedXValues = new HashMap<String, String>();
					ListBox values = window.getSelectXAxis().getxAxis().getValues();
					
					for (int i =0; i < values.getItemCount(); i++ ) 
						if (values.isItemSelected(i)) 
							selectedXValues.put(values.getValue(i), values.getItemText(i));
					parametersVo.setxLabels(selectedXValues);
					parametersVo.setxDimension(selectedX.get(dataset.getDsId()));
					
					
					/** Y-AXIS **/
					List<OfcAxisPanel> yPanels = window.getSelectYAxis().getyPanels();
					for(int i=0; i < yPanels.size(); i++) {
						if ( yPanels.get(i).getDataset().getDsId().equals(dataset.getDsId())) {
							ListBox yDim = yPanels.get(i).getDimensions();
							ListBox yVal = yPanels.get(i).getValues();
							HashMap<String, String> yValues = new HashMap<String, String>();
							for(int j=0; j < yVal.getItemCount(); j++) {
								if (yVal.isItemSelected(j)) 
									yValues.put(yVal.getValue(j), yVal.getItemText(j));
							}
							DimensionsBeanVO dimension = new DimensionsBeanVO();
							dimension.setDatatype(yDim.getValue(yDim.getSelectedIndex()));
							dimension.setHeader(yDim.getItemText(yDim.getSelectedIndex()));
							parametersVo.setyDimension(dimension);
							parametersVo.setyLabels(yValues);
							break;
						}				
					}
					
					/** filter **/
					OfcOtherDimensionsPanel otherPanel = window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId());
					if ( otherPanel != null) {
						List<OfcFilterVo> filters = otherPanel.getFilters();
						parametersVo.setFilters(filters);
					}
					else 
						parametersVo.setFilters(new ArrayList<OfcFilterVo>());
					
					/** Measurament Unit **/
					ListBox mu = window.getSelectOptions().getMu();
					for(int i=0; i < mu.getItemCount(); i++) {
						if (mu.isItemSelected(i)) 
							parametersVo.getMeasuramentUnit().add(mu.getItemText(i));				
					}
		
					/** aggregation **/
	//				ListBox aggregation = window.getSelectOptions().getAggregation();
	//				parametersVo.setAggregation(aggregation.getItemText(aggregation.getSelectedIndex()));
					allParametersVo.add(parametersVo);
				}
				
				
				
	
				for(OfcParametersVo parameters : allParametersVo) {
					System.out.println("SELECTED PARAMETERS: " + parameters.getDataset().getDatasetName());
					System.out.println("X: " + parameters.getxDimension().getHeader() + " | " + parameters.getxDimension().getDatatype());
					System.out.println("X size: " + parameters.getxLabels().size());
					System.out.println("Y: " + parameters.getyDimension().getHeader() + " | " + parameters.getyDimension().getDatatype());
					System.out.println("Y size: " + parameters.getyLabels().size());
					System.out.println("Filters size: " + parameters.getFilters().size());
					System.out.println("MU: " + parameters.getMeasuramentUnit());
				}
				
				createChart(window, allParametersVo, chartFormatBean);
			}
		};
		
	}
	
	private static void createChart(final OfcChartWizard window, final List<OfcParametersVo> allParametersVo, final OfcChartFormatBeanVO chartFormatBean) {
		
	//	final LoadingWindow loading = new LoadingWindow(I18N.print().chart(), I18N.print().pleaseWait() + "...", I18N.print().loading() + "...");
		
		System.out.println("getChartType:" + chartFormatBean.getChartType());
		
		for (OfcParametersVo p : allParametersVo) {
			System.out.println("DatasetId: " + p.getDataset().getDatasetName() + " | " + p.getDataset().getDsId());		
			System.out.println("X Header: " + p.getxDimension().getHeader());
			System.out.println("Y Header: " + p.getyDimension().getHeader());
			System.out.println("MU: " + p.getMeasuramentUnit());
		}
		final OfcSeriesVO series = new OfcSeriesVO();
		
		OfcChartServiceEntry.getInstance().getChart(allParametersVo, new AsyncCallback<List<OfcChartBeanVO>>() {
			public void onSuccess(List<OfcChartBeanVO> beans) {
				
//				window.getChartViewer().build(window);
//				OfcChartViewer chartViewer = window.getChartViewer();
				OfcChartViewer chartViewer = new OfcChartViewer(window);
				chartViewer.build(window);
				
	//			chartViewer.build(window);
	//			ChartWidget chart = chartViewer.getChartViewerShow().getChart();
	//			ChartData cd = chartViewer.getChartViewerShow().getChartData();
				ChartWidget chart = new ChartWidget();
				ChartData cd = new ChartData();
				cd.setBackgroundColour("#FFFFFF");
				XAxis xa = new XAxis();
				YAxis ya = new YAxis();
				YAxis yra = new YAxis();
				
	//			OfcParametersVo firstParameter = allParametersVo.get(0);
				
				for(OfcChartBeanVO bean : beans) {
					System.out.println("CREATING CHART: " + bean.getDatasetName());
					if ( chartFormatBean.getChartType().equals("Pie")) {						
					}
					else if ( chartFormatBean.getChartType().equals("Bar")) {
						createBarChart(cd, xa, ya, yra, bean, series, chartFormatBean.getChartStyle());
					}
					else if ( chartFormatBean.getChartType().equals("Line")) {
						createLineChart(cd, xa, ya, bean, series, chartFormatBean.getChartStyle());
					}
				}
		
	
					cd.setXAxis(xa);
					cd.setYAxis(ya);
	//				cd.setYAxisRight(yra);
	//			
					
					OfcChartUtils.setLegend(cd);
	//				/** setting X-Y legend with measurament unit and X header **/
					OfcChartUtils.setXYLegend(cd, allParametersVo.get(0));
					
			
					
					System.out.println(cd.toString());
					chart.setJsonData(cd.toString());
					chart.setSize("700", "500");
					chart.setCacheFixEnabled(true);
					System.out.println(cd.toString());
					
				
					
			
					chartViewer.getWrapper().removeAll();
					
				
					
					chartViewer.setChartData(cd);
					chartViewer.setChart(chart);
					chartViewer.getWrapper().add(chart);
					
	//				chartViewer.getWest().setExpanded(true);
	//				chartViewer.getWest().setExpanded(false);
//					chartViewer.getWest().expand();
//					chartViewer.getWest().collapse();
	//				chartViewer.getCenter().setExpanded(true);
	//				chartViewer.getCenter().collapse();
	//				chartViewer.getCenter().expand();
//					chartViewer.getWindow().setLayout(chartViewer.getWindow().getLayout());
					
	//				Window window = new Window();
	//				window.setSize(800, 600);
	//				
	//				window.add(chart);
	//				window.show();
					chartViewer.show();
				
			}
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				};
			});
		
		
		
		}

	

	public static ChartData createBarChart(ChartData cd, XAxis xa, YAxis ya, YAxis yra, OfcChartBeanVO bean, OfcSeriesVO series, String... type) {
		List<String> xlabels = new ArrayList<String>();
		
		/* set x-axis */
		if ( xa.getLabels() == null) {
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
			OfcChartUtils.setXlabelsToXAxis(xa, xlabels);
			xa.getLabels().setRotationAngle(45);
		}
		else 
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
		
		/* set y-axis */
		OfcChartUtils.createBarChart(cd, bean, xlabels, ya, yra, series, type);

		return cd;
	}
	
	
	public static ChartData createGeneralLineChart(ChartData cd, XAxis xa, YAxis ya, OfcChartBeanVO bean, OfcSeriesVO series, String... type) {
		List<String> xlabels = new ArrayList<String>();
		
		/* set x-axis */
		if ( xa.getLabels() == null) {
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
			OfcChartUtils.setXlabelsToXAxisGeneral(xa, bean.getxAxisLabels());
			xa.getLabels().setRotationAngle(45);
			
		}
		else 
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
		
		/* set y-axis */
		OfcChartUtils.createLineChart(cd, bean, xlabels, ya, series, type);

		return cd;
	}
	
	public static ChartData createGeneralBarChart(ChartData cd, XAxis xa, YAxis ya, YAxis yra, OfcChartBeanVO bean, OfcSeriesVO series, String... type) {
		List<String> xlabels = new ArrayList<String>();
		
		/* set x-axis */
		if ( xa.getLabels() == null) {
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
//			OfcChartUtils.setXlabelsToXAxisGeneral(xa, bean.getxAxisLabels());
			OfcChartUtils.setXlabelsToXAxis(xa, xlabels);
			xa.getLabels().setRotationAngle(45);
			
		}
		else 
//			xlabels = bean.getxAxisLabels();
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
		
		/* set y-axis */
		OfcChartUtils.createBarChart(cd, bean, xlabels, ya, yra, series, type);

		return cd;
	}
	
	public static ChartData createBangladeshLineChart(ChartData cd, XAxis xa, YAxis ya, OfcChartBeanVO bean, OfcSeriesVO series, String... type) {
		List<String> xlabels = new ArrayList<String>();
		
		/* set x-axis */
		if ( xa.getLabels() == null) {
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
			OfcChartUtils.setXlabelsToXAxisGeneral(xa, bean.getxAxisLabels());
			xa.getLabels().setRotationAngle(45);
			
		}
		else 
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
		
		/* set y-axis */
		OfcChartUtils.createLineChart(cd, bean, xlabels, ya, series, type);

		return cd;
	}
	
	private static ChartData createLineChart(ChartData cd, XAxis xa, YAxis ya, OfcChartBeanVO bean, OfcSeriesVO series, String... type) {
		List<String> xlabels = new ArrayList<String>();
		
		/* set x-axis */
		if ( xa.getLabels() == null) {
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
			OfcChartUtils.setXlabelsToXAxis(xa, xlabels);
			xa.getLabels().setRotationAngle(45);
			
		}
		else 
			xlabels = OfcChartUtils.sortXLabels(bean.getxLabels());
		
		/* set y-axis */
		OfcChartUtils.createLineChart(cd, bean, xlabels, ya, series, type);

		return cd;
	}
	
	
	
	public static void getChart(ChartData cd, String chartType, List<String> xLabels, String yLabel, List<ValueVO> values, String... type) {
		if(chartType.equals("Bar")) 
			cd.addElements(OfcBarChart.createChart(values, xLabels, yLabel));
		else if (chartType.equals("Line")) {
			List<LineChart> charts = OfcLineChart.createChartList(values, xLabels, yLabel);
			for(LineChart chart : charts)
				cd.addElements(chart);
		}
	}
	
	
	
	public static SelectionListener<ButtonEvent> addDataset(final OfcChartWizard window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(window);
			}
		};
	}

	public static SelectionListener<ButtonEvent> changeTab(final int numTab, final OfcChartWizard chartWizard){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				chartWizard.getPanel().getItem(numTab).setEnabled(true);
				chartWizard.getPanel().setSelection(chartWizard.getPanel().getItem(numTab));
			}
		};
	}
	
	public static void changeTabAxis(final int numTab, final OfcChartWizard chartWizard){
		chartWizard.getPanel().getItem(numTab).setEnabled(true);
		chartWizard.getPanel().setSelection(chartWizard.getPanel().getItem(numTab));
	}
	
	public static ClickListener activeDisactive(final HorizontalPanel cont,	final String nameChart, final OfcChartFormatBeanVO chartFormatBean, final OfcChartType ofcChartType) {
		return new ClickListener() {
			public void onClick(Widget wid) {
				// if the user doesn't click on the chart type already selected
				// I change the chart type
				if (cont != ofcChartType.getChartTypeActivated()) {
					DOM.setStyleAttribute(ofcChartType.getChartTypeActivated().getElement(), "backgroundColor", "#cbc4c4");
					DOM.setStyleAttribute(cont.getElement(), "backgroundColor", "#FF0000");
					ofcChartType.setChartTypeActivated(cont);
					chartFormatBean.setChartType(nameChart);
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> loadDatasets(final OfcChartWizard window) {
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				List<DatasetVO> datasets = new ArrayList<DatasetVO>();
				for (int i=0; i<window.getSelectData().getListCheckBoxDataset().size(); i++){
					if (window.getSelectData().getListCheckBoxDataset().get(i).isChecked())
						datasets.add(window.getSelectData().getMapDatasetList().get(window.getSelectData().getListCheckBoxDataset().get(i)));
				}
				System.out.println("Dataset Size: " + datasets.size());
				
				window.getSelectYAxis().createYDimensions(window, datasets);
				
				ListBox xDimensions = window.getSelectXAxis().getxAxis().getDimensions();
				window.getSelectXAxis().getDatasets().clear();
				window.getSelectXAxis().getDatasets().addAll(datasets);
								
				List<ListBox> yDimensions = new ArrayList<ListBox>();
				for(int i=0; i < window.getSelectYAxis().getyPanels().size(); i++) {
					yDimensions.add(window.getSelectYAxis().getyPanels().get(i).getDimensions());
				}
//				updateDimensions(datasets, xDimensions, yDimensions, values);
				updateXDimensions(window, datasets, xDimensions);
				changeTabAxis(2, window);
							
				/** clean the X dimension values **/
				window.getSelectXAxis().getxAxis().getValues().clear();
				
				window.getSelectOtherDimensions().clean();
//				createOtherDimensions(window, datasets);
				
				/** getMeasurementUnits **/
				getMeasuramentUnitValues(window, datasets);
				
			}
		};
	}
	
	
	public static void updateXDimensions(final OfcChartWizard window, final List<DatasetVO> datasets, final ListBox xDimensions) {
		List<String> datas = new ArrayList<String>();
		for(DatasetVO dataset: datasets)
			datas.add(dataset.getDsId());
		/** has no been selected any dataset **/
		if ( datasets.size() == 0) 
			FenixAlert.info("Select a Dataset", "Please Select a Dataset");
		xDimensions.clear();
		OfcChartServiceEntry.getInstance().getXCommonDimensions(datasets, new AsyncCallback<HashMap<String, List<DimensionsBeanVO>>>()   {
			public void onSuccess(HashMap<String, List<DimensionsBeanVO>> dimensions) {
				if ( !checkEqualSizes(dimensions) && datasets.size() > 1)
					FenixAlert.info("Dataset", "The datasets contains more columns using the same coding system");
				else {
					xDimensions.addItem(BabelFish.print().pleaseSelectADimension());
					for (String dimension : dimensions.keySet()) {
						/** TODO: POLICY -> TAKES JUST THE FIRST DATASET ***/
						System.out.println("DS_ID: " + dimension);
						List<DimensionsBeanVO> dims = dimensions.get(dimension);
						System.out.println("dimensions size: " + dims.size());
						for(DimensionsBeanVO d : dims) { 
							xDimensions.addItem(d.getHeader(), d.getDs_id());
						}
						break;
					}
				}
				window.getSelectXAxis().getCommonXDimensions().clear();
				window.getSelectXAxis().getCommonXDimensions().putAll(dimensions);
			}
			public void onFailure(Throwable caught) {
				
			
			}
		});
	}
	
	public static Boolean checkEqualSizes(HashMap<String, List<DimensionsBeanVO>> dimensions){
		int size = 0;
		int i=0;
		for (String dimension : dimensions.keySet()) {
			List<DimensionsBeanVO> dims = dimensions.get(dimension);
			if ( i == 0) 
				size = dims.size();
			else 
				if ( size != dims.size())
					return false;
			i++;
		}
		return true;
	}
	
	public static void updateYDimension(final OfcChartWizard window, final List<DatasetVO> datasets, final List<ListBox> yDimensions) {
		if ( datasets.size() == 0) 
			FenixAlert.info("Select a Dataset", "Please Select a Dataset");
		
		else {
				for (int j=0; j < datasets.size(); j++) {
				final int k = j;
				OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasets.get(j).getDsId(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> results) {
						System.out.println("updateYDimension");
						String selectedXValue = window.getSelectXAxis().getSelectedXDimensions().get(datasets.get(k).getDsId()).getHeader();
						System.out.println("SelectedXValue: " + selectedXValue);
					
						yDimensions.get(k).clear();
						yDimensions.get(k).addItem(BabelFish.print().pleaseSelectADimension());
						for (String dimension : results.keySet())
							/** TODO: it checks the column definition **/
							if (!results.get(dimension).equals("quantity") && !results.get(dimension).equals("measurementUnit") && !dimension.equals(selectedXValue)) {
								System.out.println("dimension: " + dimension);
								System.out.println("results): " + results.get(dimension));
								yDimensions.get(k).addItem(dimension, results.get(dimension));
							}
						/** TODO: update other dimensions **/
					}
		
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					}
		
				});
			}
		}
	}
	

	public static void updateOtherDimensions(final OfcChartWizard window, final DatasetVO dataset, final String selectedYValue) {
		/** considering what the uses selected:
		 * - take the already created panel
		 * - check if there are other dimensions to show
		 * 		- yes -> delete dimensions and values and show them
		 * 		- no  -> remove the panel
		 * **/
		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(dataset.getDsId(), new AsyncCallback<Map<String, String>>() {
			public void onSuccess(Map<String, String> results) {
				/** counting how many possible dimensions has the dataset **/
				System.out.println("updateOtherDimensions");
				/** it's -2 because the user has to select x axis and y axis **/	
				String selectedXValue = window.getSelectXAxis().getSelectedXDimensions().get(dataset.getDsId()).getHeader();
				System.out.println("Selected dimension X: " + selectedXValue + "\nSelected dimension Y: " + selectedYValue);

				if (window.getSelectOtherDimensions().getDatasetDimensions().containsKey(dataset.getDsId())) {
					window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId()).getDimensions().clear();
					window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId()).getValues().clear();
				}
				final String lang = CheckLanguage.getLanguage();
				List<String> headers = new ArrayList<String>();
				
				for (final String dimension : results.keySet()) {
					if (!results.get(dimension).equals("quantity") && !results.get(dimension).equals("measurementUnit") && !dimension.equals(selectedXValue) && !dimension.equals(selectedYValue)) { 
						System.out.println("Available dimension: " + dimension);
						headers.add(dimension);
					}
				}

				if ( headers.size() > 0) {
					/** has other dimensions?? **/
					TableServiceEntry.getInstance().hasValidDimensions(dataset.getDsId(),  headers, lang, new AsyncCallback<List<DimensionsBeanVO>>() {
						public void onSuccess(List<DimensionsBeanVO> results) {
							System.out.println(results);
							/** clear filters if exist **/
							if (window.getSelectOtherDimensions().getDatasetDimensions().containsKey(dataset.getDsId())) {
								window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId()).getFilters().clear();
							}
							
							if ( results.size() > 0 ) {
								for(DimensionsBeanVO dimension : results) {	 
									if (window.getSelectOtherDimensions().getDatasetDimensions().containsKey(dataset.getDsId())) {
										System.out.println(">: Other Dimension Panel Already Exist " + results);
										/** ADD DIMENSION **/
										final OfcOtherDimensionsPanel dimensions = window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId());
										dimensions.getDimensions().addItem(dimension.getHeader(),dimension.getDatatype());
									}
									else {
										System.out.println(">: Create New Other Dimension Panel" + dimension);
										/** CREATE THE PANEL **/
										window.getSelectOtherDimensions().createOtherDimension(window, dataset);
										final OfcOtherDimensionsPanel dimensions = window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId());
										dimensions.getDimensions().addItem(dimension.getHeader(),dimension.getDatatype());
									}	
									/** CREATING FILTER **/
									final OfcOtherDimensionsPanel d = window.getSelectOtherDimensions().getDatasetDimensions().get(dataset.getDsId());
									System.out.println("DATATYPE: " + dimension.getDatatype());
									System.out.println("HEADER: " + dimension.getHeader());									
									final OfcFilterVo filter = new OfcFilterVo();
									filter.setDimension(dimension);
									/** fill filter with first result **/
									OfcChartServiceEntry.getInstance().getFirstCode(dataset.getDsId(), dimension.getHeader(), lang, new AsyncCallback<HashMap<String, String>>() {
										public void onSuccess(HashMap<String, String> results) {
											filter.setDimensionMap(results);
											System.out.println("VALUE: " + filter.getDimension().getHeader() + " | " + results.size());
											for (String key : results.keySet()) {
												System.out.println("-> " + key + " | " + results.get(key));
											}
											d.getFilters().add(filter);
										}
										public void onFailure(Throwable e) {
											FenixAlert.error(BabelFish.print().info(), e.getMessage());
										}
										
									});

								}
							}
							else {
								if ( window.getSelectOtherDimensions().getDatasetPanel().containsKey(dataset.getDsId())) {
									System.out.println("REMOVING PANEL, there are not dimensions");
									window.getSelectOtherDimensions().getMainCont().getItemByItemId(dataset.getDsId()).removeFromParent();
								}
								window.getSelectOtherDimensions().getDatasetPanel().remove(dataset.getDsId());
								window.getSelectOtherDimensions().getDatasetDimensions().remove(dataset.getDsId());
								/** REMOVE FILTERS...think not..**/
							}
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
						}
					});
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
			}

		});
	}
	
	

	public static ChangeListener dimensionsXChangeListenerWithLabels(final OfcChartWizard window, final List<DatasetVO> datasets, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				HashMap<String , List<DimensionsBeanVO>> dimensions = window.getSelectXAxis().getCommonXDimensions();
				if ( !checkEqualSizes(dimensions) && datasets.size() > 1)
					FenixAlert.info("Dataset", "The datasets contains more columns using the same coding system");
				else {
					final ListBox listBox = (ListBox) sender;
					if ( listBox.getSelectedIndex() == 0) 
						values.clear();
					else {
						String datasetId = listBox.getValue(listBox.getSelectedIndex());
						String header = listBox.getItemText(listBox.getSelectedIndex());
						System.out.println("DatasetID: " + listBox.getValue(listBox.getSelectedIndex()));
						System.out.println("Selected Header: " + listBox.getItemText(listBox.getSelectedIndex()));
						final String lang = CheckLanguage.getLanguage();
						List<DimensionsBeanVO> queryDimensions = buildXQueryDimensions(dimensions, datasets, datasetId, header);
						
						/** ADDING TO SELECTED X VALUES **/
						HashMap<String, DimensionsBeanVO> selectedXValues = window.getSelectXAxis().getSelectedXDimensions();
						selectedXValues.clear();
						values.clear();
						for(DimensionsBeanVO selectedXValue : queryDimensions) {
							selectedXValues.put(selectedXValue.getDs_id(), selectedXValue);
							/** RETRIEVING DATAS ON THE SELECTED HEADER **/
							/** TODO: MAKE A STANDARD QUERY ALL TOGHETER **/
							TableServiceEntry.getInstance().getDimensionValues(Long.parseLong(selectedXValue.getDs_id()), selectedXValue.getHeader(), lang, new AsyncCallback<Map<String, String>>() {
								public void onSuccess(Map<String, String> results) {
							
									for (String key : results.keySet()) {
										/** checks double counting **/
										Boolean check = false;
										for(int i=0; i <  values.getItemCount(); i++) {
											if ( values.getItemText(i).equals(results.get(key))) {
												check = true;
												break;
											}
										}
										if ( !check) {
											values.addItem(results.get(key), key);
										}
									}
									
									/** updating Y dimensions **/
									List<ListBox> yDimensions = new ArrayList<ListBox>();
									for(int i=0; i < window.getSelectYAxis().getyPanels().size(); i++) {
										yDimensions.add(window.getSelectYAxis().getyPanels().get(i).getDimensions());
									}
									updateYDimension(window, datasets, yDimensions);
									
									/** updating other Dimensions **/
									/** TODO: clean the dimensions values (IF THEM EXISTS) **/
								}
								public void onFailure(Throwable e) {
									FenixAlert.error(BabelFish.print().info(), e.getMessage());
								}
							});
						}
						
					}
				
				}
				
			}
		};
	}
	
	private static List<DimensionsBeanVO> buildXQueryDimensions(HashMap<String, List<DimensionsBeanVO>> dimensions, List<DatasetVO> datasets, String ds_id, String header) {
		List<DimensionsBeanVO> dimension = dimensions.get(ds_id);
		List<DimensionsBeanVO> result = new ArrayList<DimensionsBeanVO>();
		System.out.println("dimensions size: " + dimension.size());
		for(DimensionsBeanVO d : dimension) { 
			if ( d.getHeader().equals(header)) {
				result.add(d);
				System.out.println("isDate: " + d.getIsDate());
				for(DatasetVO dataset : datasets){
					if ( !dataset.getDsId().equals(ds_id)) {
						List<DimensionsBeanVO> dims = dimensions.get(dataset.getDsId());
						if ( d.getIsDate() ) {
							for(DimensionsBeanVO dim : dims) {
								System.out.println(dim.getDatatype() + " | " + d.getDatatype());
								if ( dim.getDatatype().equals(d.getDatatype())) {
									result.add(dim);
									break;
								}
							}
						}
						else {
							for(DimensionsBeanVO dim : dims ){
								if ( dim.getCs_title().equals(d.getCs_title()) && dim.getCs_region().equals(d.getCs_region())) {
									result.add(dim);
									break;
								}
							}
						}
					}
				}
				break;
			}
		}
		
		System.out.println("result size: " + result.size());
		/** printing result **/
		for(DimensionsBeanVO d : result) 
			System.out.println(d.getDs_id() + " | " + d.getCs_title() + " | " + d.getDatatype() + " | " + d.getHeader() + " |" +d.getIsDate() );
		
		return result;
	}
	
	private static DimensionsBeanVO setSelectedDimension(HashMap<String, List<DimensionsBeanVO>> dimensions, String ds_id, String header) {
		List<DimensionsBeanVO> dims = dimensions.get(ds_id);
		System.out.println("dimensions size: " + dims.size());
		for(DimensionsBeanVO d : dims) { 
			if ( d.getHeader().equals(header))
					return d;
		}
		return null;
	}
	
	public static ChangeListener dimensionsYChangeListenerWithLabels(final OfcChartWizard window, final DatasetVO dataset, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dimensions = (ListBox) sender;
				if (dimensions.getSelectedIndex() > 0) {
					
					final String header = dimensions.getItemText(dimensions.getSelectedIndex());
					final String datasetId = dataset.getDsId();
					final String lang = CheckLanguage.getLanguage();
					
					TableServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<Map<String, String>>() {
						public void onSuccess(Map<String, String> results) {
							for (int i = values.getItemCount() - 1; i >= 0; i--)
								values.removeItem(i);
							/** TODO: SELECT ALL?? better not... **/
							for (String key : results.keySet()) 
								values.addItem(results.get(key), key);
							
							updateOtherDimensions(window, dataset, header);
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
						}
					});
					
				}
			}
		};
	}
	
	public static ChangeListener dimensionsOthersChangeListenerWithLabels(final OfcChartWizard window, final DatasetVO dataset, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dimensions = (ListBox) sender;
				final String header = dimensions.getItemText(dimensions.getSelectedIndex());
				final String datasetId = dataset.getDsId();
				final String lang = CheckLanguage.getLanguage();
					
				TableServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> results) {
						HashMap<String, String> selectedValues = new HashMap<String, String>();
						values.clear();
						List<OfcFilterVo> filters = window.getSelectOtherDimensions().getDatasetDimensions().get(datasetId).getFilters();
						for(OfcFilterVo filter : filters) {
							if( filter.getDimension().getHeader().equals(header)) {
								selectedValues = filter.getDimensionMap();
								System.out.println("SELECTED VALUES: " + selectedValues.size());
								break;
							}
						}
						/** REMOVE PRINT HASHMAP **/
						for (String key : selectedValues.keySet()) {
							System.out.println("selectedValueskey: " + key + " | " + selectedValues.get(key));
						}
							
											
						
						int k=0;
						for (String key : results.keySet()) {
							values.addItem(results.get(key), key);
							System.out.println("key: " + key + " | " + results.get(key));
							if ( selectedValues.get(key) != null ) {
								System.out.println("FOUND: " + k + " | " + key + " | " + results.get(key));
								values.setItemSelected(k, true);
							}
							k++;
						}
						
						

						
						
			
						
					}
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					}
				});	
			}
		};
	}
	
	public static ChangeListener dimensionsOthersValuesChangeListenerWithLabels(final OfcChartWizard window, final DatasetVO dataset, final ListBox dimensions) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox values = (ListBox) sender;
				final String header = dimensions.getItemText(dimensions.getSelectedIndex());
				final String datasetId = dataset.getDsId();
				final String lang = CheckLanguage.getLanguage();
				
				HashMap<String, String> selectedValues = new HashMap<String, String>();
						
				List<OfcFilterVo> filters = window.getSelectOtherDimensions().getDatasetDimensions().get(datasetId).getFilters();
				for(OfcFilterVo filter : filters) {
					if( filter.getDimension().getHeader().equals(header)) 
						filter.getDimensionMap().clear();				
				}
				
				for(int i=0; i < values.getItemCount(); i++) {
					if (values.isItemSelected(i)) {
						selectedValues.put(values.getValue(i), values.getItemText(i));
						System.out.println("ADDING: " + values.getValue(i) + " | " + values.getItemText(i));
					}
				}
				
				for(OfcFilterVo filter : filters) {
					if( filter.getDimension().getHeader().equals(header)) {
						filter.setDimensionMap(selectedValues);
						System.out.println("ADDING HM: " + filter.getDimensionMap().size());
						break;
					}
				}				
			}
		};
	}
	
	public static void getMeasuramentUnitValues(final OfcChartWizard window, final List<DatasetVO> datasets) {
		if ( datasets.size() != 0) {
			final ListBox values = window.getSelectOptions().getMu();
			values.clear();
			final String lang = CheckLanguage.getLanguage();
			for (int i=0; i < datasets.size(); i++) {
				final int k = i;
				OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasets.get(i).getDsId(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> results) {
						System.out.println("MU");
						for (String dimension : results.keySet()){
							if (results.get(dimension).equals("measurementUnit")) {
								System.out.println("MU2:" + dimension);
								TableServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasets.get(k).getDsId()), dimension, lang, new AsyncCallback<Map<String, String>>() {
									public void onSuccess(Map<String, String> results) {
										
//										values.clear();
										for (String key : results.keySet()) {
											System.out.println("Measurement Unit: " + results.get(key) + " | " + datasets.get(k).getDatasetName());
											Boolean check = false;
											for (int i = 0; i < values.getItemCount(); i++) {
												if ( values.getItemText(i).equals(results.get(key))) {
													check = true;
													break;
												}
											}
											if (!check)
												values.addItem(results.get(key), results.get(key));
										} 
										values.setSelectedIndex(0);
									}
									public void onFailure(Throwable e) {
										FenixAlert.error(BabelFish.print().info(), e.getMessage());
									}
								});
							}
						}
					}
		
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					}
		
				});
				
			}
		}
	}
	
	

	
}