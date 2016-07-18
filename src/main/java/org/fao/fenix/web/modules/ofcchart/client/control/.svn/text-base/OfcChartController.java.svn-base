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
import java.util.Set;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISFormatCharts;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.utils.OfcChartUtils;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartFilterPanel;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartWindow;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcBarChart;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcLineChart;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcPieChart;
import org.fao.fenix.web.modules.ofcchart.common.services.OfcChartServiceEntry;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.view.AxisPanel;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Legend;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.Legend.Position;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;


public class OfcChartController {
	
	private static Timer timer;
	
	public static SelectionListener<IconButtonEvent> save(final OfcChartWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("chartdata" + window.getChartData().toString());
				ChartWidget chart = new ChartWidget();
				chart.setJsonData(window.getChartData().toString());
				chart.setSize("600", "500");
				String image = chart.getImageData();
				System.out.println("IMAGE: " + image);
				OfcChartServiceEntry.getInstance().saveChart(image, new AsyncCallback() {
					public void onSuccess(Object chartFile) {
//						Window.open(chartFile, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
					}
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					}
				});	
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> save(final ContentPanel chartPanel) {
		return new SelectionListener<IconButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(IconButtonEvent ce) {
				for(int i=0; i < chartPanel.getItemCount(); i++){
					System.out.println("-: " + chartPanel.getWidget(i).toString());
					ChartWidget chart = new ChartWidget();
					ContentPanel v = (ContentPanel) chartPanel.getWidget(i);
					chart = (ChartWidget) v.getWidget(0);
					String image = chart.getImageData();
					OfcChartServiceEntry.getInstance().saveChart(image, new AsyncCallback<String>() {
						public void onSuccess(String chartFile) {
							Window.open("../exportObject/"+ chartFile, "", "");
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
						}
					});	
				}

				
			}
		};
	}

	
	
	public static SelectionListener<IconButtonEvent> resizeChartsToSmall(final ContentPanel chartPanel) {
		return new SelectionListener<IconButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(IconButtonEvent ce) {
				for(int i=0; i < chartPanel.getItemCount(); i++){
					ChartWidget chart = new ChartWidget();
					ContentPanel v = (ContentPanel) chartPanel.getWidget(i);
					chart = (ChartWidget) v.getWidget(0);
					chart.setSize("730", "335");
					v.setSize("730", "335");
				}	
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> resizeChartsToNormal(final ContentPanel chartPanel) {
		return new SelectionListener<IconButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(IconButtonEvent ce) {
				for(int i=0; i < chartPanel.getItemCount(); i++){
					ChartWidget chart = new ChartWidget();
					ContentPanel v = (ContentPanel) chartPanel.getWidget(i);
					chart = (ChartWidget) v.getWidget(0);
					chart.setSize("730", "670");
					v.setSize("730", "670");
				}

				
			}
		};
	}

	public static SelectionListener<IconButtonEvent> formatCharts(final ContentPanel chartPanel) {
		return new SelectionListener<IconButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(IconButtonEvent ce) {
				ContentPanel v = (ContentPanel) chartPanel.getWidget(0);
				ChartWidget chart = (ChartWidget) v.getWidget(0);
				FSATMISFormatCharts window = new FSATMISFormatCharts();
				window.build(chartPanel, chart.getWidth(), chart.getHeight());			
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> resizeChartsToCustom(final FSATMISFormatCharts window, final ContentPanel chartPanel) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings({ "deprecation", "unchecked" })
			public void componentSelected(ButtonEvent ce) {
				for(int i=0; i < chartPanel.getItemCount(); i++){
					System.out.println("-" + i + " | " + window.getXsize().getValue() + " | " +  window.getYsize().getValue());
					ContentPanel v = (ContentPanel) chartPanel.getWidget(i);
					ChartWidget chart = (ChartWidget) v.getWidget(0);
//					
////				chart.setChartData(cd);
//					ChartData cd = new ChartData();
////					cd.set
//					cd.set
//					
//					Legend legend = new Legend(Position.TOP, true); 		
////				legend.setMargin(20);
//					legend.setBgColour("#ffffff");
//					legend.setBorder(true);
//					cd.setLegend(legend);
//					cd.setchart.getJsonData()
					chart.setSize(window.getXsize().getValue(),window.getYsize().getValue());	
					v.setSize(window.getXsize().getValue(),window.getYsize().getValue());	
				}
				window.getWindow().close();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> refreshChart(final OfcChartWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("changing title");
				ChartWidget chart = window.getChart();
				ChartData cd = window.getChartData();
				System.out.println(cd.toString());
				cd.setTitle(new Text(window.getTabPanel().getSingleDatasetPanel().getOfcFormatChartPanel().getChartTitleBox().getText().toString()));
				chart.setJsonData(cd.toString());
			}
		};
	}

	public static SelectionListener<ButtonEvent> addDataset(final OfcChartWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(window);
			}
		};
	}
	
	
	
	public static ChangeListener dataSourceChangeListener(final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dataSource = (ListBox) sender;
				if (dataSource.getSelectedIndex() > 0) {
					String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					updateDimensions(datasetId, xDimensions, yDimensions, zDimensions, wDimensions, values);
				}
			}
		};
	}
	
	public static void updateDimensions(final String datasetId, final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasetId, new AsyncCallback<Map<String, String>>() {

			public void onSuccess(Map<String, String> results) {

				for (int i = xDimensions.getItemCount() - 1; i >= 0; i--)
					xDimensions.removeItem(i);
				xDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						xDimensions.addItem(dimension, results.get(dimension));

				for (int i = yDimensions.getItemCount() - 1; i >= 0; i--)
					yDimensions.removeItem(i);
				yDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						yDimensions.addItem(dimension, results.get(dimension));

				for (int i = zDimensions.getItemCount() - 1; i >= 0; i--)
					zDimensions.removeItem(i);
				zDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						zDimensions.addItem(dimension, results.get(dimension));

				for (int i = wDimensions.getItemCount() - 1; i >= 0; i--)
					wDimensions.removeItem(i);
				wDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						wDimensions.addItem(dimension, results.get(dimension));

				for (int i = values.getItemCount() - 1; i >= 0; i--)
					values.removeItem(i);
				for (String dimension : results.keySet())
					if (results.get(dimension).equals("quantity"))
						values.addItem(dimension, results.get(dimension));

				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			}

		});
	}
	

	
	private static void createOLAPChartBeans(final OLAPParametersVo p, final OfcChartWindow w) {
		
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().chart(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
		
		
		System.out.println("DatasetId:" + p.getDataSourceId());
		System.out.println("DatasetTitle:" + p.getDataSourceTitle());
		System.out.println("getChartType:" + p.getChartType());
		System.out.println("getValueLabel:" + p.getValueLabel());
		System.out.println("getValue:" + p.getValue());
		System.out.println("getX:" + p.getX());
		System.out.println("getXLabel:" + p.getXLabel());
		System.out.println("getXLabels:" + p.getXLabels().size());
		System.out.println("getZ:" + p.getZ());
		System.out.println("getZLabel:" + p.getZLabel());
		System.out.println("getZLabels:" + p.getZLabels().size());
		System.out.println("getFilters:" + p.getFilters().size());
		
		
		
		OfcChartServiceEntry.getInstance().createChart(p, new AsyncCallback<OfcChartBeanVO>() {
			public void onSuccess(OfcChartBeanVO beans) {
				loading.destroyLoadingBox();
				System.out.println("CREATING CHART");
				ChartWidget chart = w.getChart();
				ChartData cd = w.getChartData();
				cd = new ChartData(p.getDataSourceTitle(),"font-size: 14px; font-family: Verdana; text-align: center;");
			

				cd.setBackgroundColour("#ffffff");
				XAxis xa = new XAxis();
				YAxis ya = new YAxis();

				
				
				Set<String> keySet = beans.getValues().keySet();
				int i=0;
				System.out.println(keySet.size());
				System.out.println(p.getChartType());
				/*** just for the PIE CHARTS **/
				
				if ( p.getChartType().equals("Pie Chart")) {
					List<PieChart> charts = OfcPieChart.createChart(beans);
					for(PieChart pie : charts) {
						cd.addElements(pie);
					}
				}
				else {
					/*** Adding labels**/
					List<String> xLabels = OfcChartUtils.sortXLabels(p.getXLabels());
					System.out.println("getXLabels:" + p.getXLabels().size());
					/*** add labels **/
					for(String xLabel : xLabels) { 
						xa.addLabels(xLabel);
					}
					xa.getLabels().setRotationAngle(45);
//							
//					for(String key : keySet) {
//						List<ValueVO> values =  beans.getValues().get(key);
//						/** set the range of the chart **/
//						OfcChartUtils.setRange(ya, values);
//						/*** evently a type of the chartType -> specified line **/
//						createChart(cd, p.getChartType(), xLabels, key, values);
//					}
					
					cd.getXAxis().setZDepth3D(1);
					cd.setXLegend(new Text(p.getXLabel(), "font-size: 14px; font-family: Verdana; text-align: center;"));					
				}
				
				cd.setXAxis(xa);
				cd.setYAxis(ya);
			
				Legend legend = new Legend(Position.RIGHT, true); 
				legend.setMargin(20);
				legend.setBgColour("#ffffff");
				legend.setBorder(true);
				
		
//				cd.setXLegend(new Text("CIAO"));
//				cd.setYLegend(new Text(p.getYLabel()));
				System.out.println("Legend: " + legend.toString());
				cd.setLegend(legend);
				
//				cd.setLegend(OfcLegend.createLegend());
				System.out.println(cd.toString());
				chart.setJsonData(cd.toString());
				chart.setSize("600", "500");
				chart.setCacheFixEnabled(true);
				System.out.println("CHART IMAGE: " + chart.getImageData());
				
//				OfcChartServiceEntry.getInstance().saveChart(chart.getImageData(), new AsyncCallback() {
//					public void onSuccess(Object result) {
//						// TODO Auto-generated method stub
//						
//					};
//					public void onFailure(Throwable e) {
//						loading.destroyLoadingBox();
//						FenixAlert.error(I18N.print().info(), e.getMessage());
//						loading.destroyLoadingBox();
//					}
//				});
				
				
				
				w.setChartData(cd);
				w.setChart(chart);
				ContentPanel cont = new ContentPanel();
				cont.setHeaderVisible(false);
				cont.setLayout(new FitLayout());
				cont.add(chart);		
				w.getWrapper().removeAll();
//				w.setChart(chart);
				w.getWrapper().add(chart);
				w.getWest().collapse();
				w.getEast().collapse();
//				w.getWestData().setSize(350);
//				w.getWest().expand();
				w.getCenter().expand();
				
				
				

				loading.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			};
		});
	}
	
	
	public static void createChart(ChartData cd, String chartType, List<String> xLabels, String yLabel, List<ValueVO> values, String... type) {
		if(chartType.equals("Bar Chart")) 
			cd.addElements(OfcBarChart.createChart(values, xLabels, yLabel));
		else if (chartType.equals("Line Chart")) {
//			 OfcLineChart.createChart(values, xLabels);
			List<LineChart> charts = OfcLineChart.createChartList(values, xLabels, yLabel);
			for(LineChart chart : charts)
				cd.addElements(chart);
		}
	}
	
	public static SelectionListener<ButtonEvent> createChart(final OfcChartWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final OLAPParametersVo p = retrieveParameters(window);
				createOLAPChartBeans(p, window);
			};
		};
	}
	
	public static OLAPParametersVo retrieveParameters(final OfcChartWindow window) {

		OLAPParametersVo vo = new OLAPParametersVo();

		vo.setDataSourceId(getDatasetId(window));
		vo.setDataSourceTitle(getDatasetTitle(window));
		vo.setX(getAxis(window, "x"));
		vo.setY(getAxis(window, "y"));
		vo.setZ(getAxis(window, "z"));
		vo.setW(getAxis(window, "w"));

		vo.setXLabel(getAxisLabel(window, "x"));
		vo.setYLabel(getAxisLabel(window, "y"));
		vo.setZLabel(getAxisLabel(window, "z"));
		vo.setWLabel(getAxisLabel(window, "w"));

		vo.setXLabels(getAxisLabels(window, "x"));
		vo.setYLabels(getAxisLabels(window, "y"));
		vo.setZLabels(getAxisLabels(window, "z"));
		vo.setWLabels(getAxisLabels(window, "w"));

		vo.setFunction(getAggregationFunction(window));
		vo.setValue(getValue(window));
		vo.setValueLabel(getValueLabel(window));

		vo.setFilters(getFilters(window));
		
		vo.setShowSingleValues(window.getTabPanel().getSingleDatasetPanel().getShowSingleElements().getValue());
		vo.setShowTotals(window.getTabPanel().getSingleDatasetPanel().getShowTotals().getValue());
		vo.setChartType(getChartType(window));

		return vo;
	}

//	private static String getChartType(OlapWindow window) {
//		ListBox list = window.getTabPanel().getSingleDatasetPanel().getChartType();
//		return list.getItemText(list.getSelectedIndex());
//	}

//	private static List<OLAPFilterVo> getFilters(final OfcChartWindow window) {
//		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
//		for (int i = 0; i < window.getFilterPanel().getFilterPanel().getItemCount(); i++) {
//			ContentPanel filterPanel = (ContentPanel) window.getFilterPanel().getFilterPanel().getItem(i);
//			ListBox dimensions = (ListBox) filterPanel.getData("dimensions");
//			ListBox values = (ListBox) filterPanel.getData("values");
//			OLAPFilterVo vo = new OLAPFilterVo();
//			vo.setDimension(dimensions.getValue(dimensions.getSelectedIndex()));
//			vo.setDimensionLabel(dimensions.getItemText(dimensions.getSelectedIndex()));
//			Map<String, String> dimensionMap = new HashMap<String, String>();
//			for (int j = 0; j < values.getItemCount(); j++) {
//				if (values.isItemSelected(j)) {
//					String valueCode = values.getValue(j);
//					String valueLabel = values.getItemText(j);
//					dimensionMap.put(valueCode, valueLabel);
//				}
//			}
//			vo.setDimensionMap(dimensionMap);
//			filters.add(vo);
//		}
//		return filters;
//	}

	private static String getValue(final OfcChartWindow window) {
		ListBox value = window.getTabPanel().getSingleDatasetPanel().getValues();
		return value.getValue(value.getSelectedIndex());
	}

	private static String getValueLabel(final OfcChartWindow window) {
		ListBox value = window.getTabPanel().getSingleDatasetPanel().getValues();
		return value.getItemText(value.getSelectedIndex());
	}

	private static String getAggregationFunction(OfcChartWindow window) {
		ListBox function = window.getTabPanel().getSingleDatasetPanel().getFunction();
		return function.getValue(function.getSelectedIndex());
	}

	private static Map<String, String> getAxisLabels(OfcChartWindow window, String axis) {
		Map<String, String> axisLabels = new HashMap<String, String>();
		if (axis.equals("x")) {
			ListBox values = window.getTabPanel().getSingleDatasetPanel().getXAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("y")) {
			ListBox values = window.getTabPanel().getSingleDatasetPanel().getYAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("z")) {
			ListBox values = window.getTabPanel().getSingleDatasetPanel().getZAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("w")) {
			ListBox values = window.getTabPanel().getSingleDatasetPanel().getWAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		}
		return axisLabels;
	}

	// TODO restore me!!!
	private static boolean aggregateAxis(OfcChartWindow window, String axis) {
//		if (axis.equals("x"))
//			return window.getTabPanel().getSingleDatasetPanel().getXAxis().getAggregateSubLevel().getValue();
//		else if (axis.equals("y"))
//			return window.getTabPanel().getSingleDatasetPanel().getYAxis().getAggregateSubLevel().getValue();
//		else if (axis.equals("z"))
//			return window.getTabPanel().getSingleDatasetPanel().getZAxis().getAggregateSubLevel().getValue();
//		else if (axis.equals("w"))
//			return window.getTabPanel().getSingleDatasetPanel().getWAxis().getAggregateSubLevel().getValue();
		return false;
	}

	private static Long getDatasetId(OfcChartWindow window) {
		Long datasetId = null;
		ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
		datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		return datasetId;
	}

	private static String getDatasetTitle(OfcChartWindow window) {
		ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
		return dataSource.getItemText(dataSource.getSelectedIndex());
	}

	private static String getAxis(OfcChartWindow window, String axis) {
		String x = "";
		ListBox dimensions = window.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		if (axis.equalsIgnoreCase("y"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		else if (axis.equalsIgnoreCase("z"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		else if (axis.equalsIgnoreCase("w"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		if (dimensions.getSelectedIndex() > 0)
			x = dimensions.getValue(dimensions.getSelectedIndex());
		return x;
	}

	private static String getAxisLabel(OfcChartWindow window, String axis) {
		String x = "";
		ListBox dimensions = window.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		if (axis.equalsIgnoreCase("y"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		else if (axis.equalsIgnoreCase("z"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		else if (axis.equalsIgnoreCase("w"))
			dimensions = window.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		if (dimensions.getSelectedIndex() > 0)
			x = dimensions.getItemText(dimensions.getSelectedIndex());
		return x;
	}

	public static void fillDataSourceList(final ListBox dataSourceList) {
		OlapServiceEntry.getInstance().findAllCoreDataset(new AsyncCallback<Map<String, String>>() { // TODO modify with 'Get FSATMIS Dataset'
			final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

			public void onSuccess(Map<String, String> datasets) {
				for (int i = dataSourceList.getItemCount() - 1; i >= 0; i--)
					dataSourceList.removeItem(i);
				dataSourceList.addItem(BabelFish.print().pleaseSelectADataset());
				for (String key : datasets.keySet())
					dataSourceList.addItem(key, datasets.get(key));
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			}
		});
	}


	public static void fillDimension(final String datasetId, final ListBox xDimensions) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasetId, new AsyncCallback<Map<String, String>>() {

			public void onSuccess(Map<String, String> results) {

				for (int i = xDimensions.getItemCount() - 1; i >= 0; i--)
					xDimensions.removeItem(i);
				xDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						xDimensions.addItem(dimension, results.get(dimension));
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
				loading.destroyLoadingBox();
			}

		});
	}

	

	@Deprecated
	/**
	 * Please refer to "dimensionsChangeListenerWithLabels" method
	 */
	public static ChangeListener dimensionsChangeListener(final ListBox dataSource, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dimensions = (ListBox) sender;
				if (dimensions.getSelectedIndex() > 0) {
					final String dimension = dimensions.getValue(dimensions.getSelectedIndex());
					final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
					OlapServiceEntry.getInstance().findDimensionValues(datasetId, dimension, new AsyncCallback<List<String>>() {
						public void onSuccess(List<String> results) {
							for (int i = values.getItemCount() - 1; i >= 0; i--)
								values.removeItem(i);
							for (String result : results)
								values.addItem(result);
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				}
			}
		};
	}

	public static ChangeListener dimensionsChangeListenerWithLabels(final ListBox dataSource, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dimensions = (ListBox) sender;
				if (dimensions.getSelectedIndex() > 0) {
					
					final String header = dimensions.getItemText(dimensions.getSelectedIndex());
					final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
					final String lang = CheckLanguage.getLanguage();
					
					TableServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<Map<String, String>>() {
						public void onSuccess(Map<String, String> results) {
							loading.destroyLoadingBox();
							for (int i = values.getItemCount() - 1; i >= 0; i--)
								values.removeItem(i);
							for (String key : results.keySet())
								values.addItem(results.get(key), key);
							loading.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
							loading.destroyLoadingBox();
						}
					});
					
				}
			}
		};
	}
	
	private static String getChartType(OfcChartWindow window) {
		ListBox list = window.getTabPanel().getSingleDatasetPanel().getChartType();
		return list.getItemText(list.getSelectedIndex());
	}
	
	public static SelectionListener<ButtonEvent> addFilter(final FenixWindow window, final OfcChartFilterPanel filterPanel, final ListBox dataSource) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				AxisPanel filter = new AxisPanel("F");
				ContentPanel filterContentPanel = filter.build(BabelFish.print().filter(), false);
				filterPanel.getFilterPanel().add(filterContentPanel);
				filterPanel.getFilterPanel().getLayout().layout();
				filter.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, filter));
				String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				fillDimension(datasetId, filter.getDimensions());
			}
		};
	}

	public static SelectionListener<ButtonEvent> removeFilter(final OfcChartFilterPanel filterPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (int i = 0; i < filterPanel.getFilterPanel().getItemCount(); i++) {
					ContentPanel tmp = (ContentPanel) filterPanel.getFilterPanel().getItem(i);
					if (tmp.isExpanded())
						filterPanel.getFilterPanel().remove(tmp);
				}
			}
		};
	}
	

	private static List<OLAPFilterVo> getFilters(final OfcChartWindow window) {
		System.out.println("Getting Filters");
		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
		for (int i = 0; i < window.getFilterPanel().getFilterPanel().getItemCount(); i++) {
			System.out.println(i);
			ContentPanel filterPanel = (ContentPanel) window.getFilterPanel().getFilterPanel().getItem(i);
			ListBox dimensions = (ListBox) filterPanel.getData("dimensions");
			ListBox values = (ListBox) filterPanel.getData("values");
			OLAPFilterVo vo = new OLAPFilterVo();
			vo.setDimension(dimensions.getValue(dimensions.getSelectedIndex()));
			vo.setDimensionLabel(dimensions.getItemText(dimensions.getSelectedIndex()));
			Map<String, String> dimensionMap = new HashMap<String, String>();
			for (int j = 0; j < values.getItemCount(); j++) {
				if (values.isItemSelected(j)) {
					String valueCode = values.getValue(j);
					String valueLabel = values.getItemText(j);
					dimensionMap.put(valueCode, valueLabel);
				}
			}
			vo.setDimensionMap(dimensionMap);
			filters.add(vo);
		}
		return filters;
	}
	
	
}