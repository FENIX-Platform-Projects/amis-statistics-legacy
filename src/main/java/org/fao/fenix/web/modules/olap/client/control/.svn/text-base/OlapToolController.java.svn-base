package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISDatasetPanel;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTablePanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.view.AxisPanel;
import org.fao.fenix.web.modules.olap.client.view.OlapFilterPanel;
import org.fao.fenix.web.modules.olap.client.view.OlapTool;
import org.fao.fenix.web.modules.olap.client.view.OlapToolbar;
import org.fao.fenix.web.modules.olap.client.view.SingleElementsWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.LineChart;

public class OlapToolController {

	public static SelectionListener<ComponentEvent> showLowerLevel(final AxisPanel axisPanel, final ListBox dataSource) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				final ListBox dimensions = axisPanel.getDimensions();
				final ListBox values = axisPanel.getValues();
				final String code = values.getValue(values.getSelectedIndex());
				final String dimension = dimensions.getValue(dimensions.getSelectedIndex());
				final String header = dimensions.getItemText(dimensions.getSelectedIndex());
				final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				OlapServiceEntry.getInstance().getCodingHierarchyFamily(Long.valueOf(datasetId), dimension, header, code, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> result) {
						for (int i = values.getItemCount() - 1; i >= 0; i--)
							values.removeItem(i);
						for (CodeVo vo : result)
							values.addItem(vo.getLabel(), vo.getCode());
					};

					public void onFailure(Throwable e) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					};
				});
			}
		};
	}

	public static SelectionListener<ComponentEvent> showUpperLevel(final AxisPanel axisPanel, final ListBox dataSource) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				final ListBox dimensions = axisPanel.getDimensions();
				final ListBox values = axisPanel.getValues();
				final String code = values.getValue(values.getSelectedIndex());
				final String dimension = dimensions.getValue(dimensions.getSelectedIndex());
				final String header = dimensions.getItemText(dimensions.getSelectedIndex());
				final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				OlapServiceEntry.getInstance().getCodingHierarchyUncles(Long.valueOf(datasetId), dimension, header, code, new AsyncCallback<List<CodeVo>>() {
					public void onSuccess(List<CodeVo> result) {
						for (int i = values.getItemCount() - 1; i >= 0; i--)
							values.removeItem(i);
						for (CodeVo vo : result)
							values.addItem(vo.getLabel(), vo.getCode());
					};

					public void onFailure(Throwable e) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					};
				});
			}
		};
	}

	public static SelectionListener<ButtonEvent> addFilter(final OlapFilterPanel filterPanel, final ListBox dataSource) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (dataSource.getSelectedIndex() > 0) {
					AxisPanel filter = new AxisPanel("F");
					ContentPanel filterContentPanel = filter.build(BabelFish.print().filter(), false);
					filterPanel.getFilterPanel().add(filterContentPanel);
					filterPanel.getFilterPanel().getLayout().layout();
					filter.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, filter));
					String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					fillDimension(datasetId, filter.getDimensions());
					
				} else {
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().pleaseSelectADataset());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> addFilter(final OlapTool olapTool, final OlapFilterPanel filterPanel, final ListBox dataSource) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				AxisPanel filter = new AxisPanel("F");
				ContentPanel filterContentPanel = filter.build(BabelFish.print().filter(), false);
				filterPanel.getFilterPanel().add(filterContentPanel);
				filterPanel.getFilterPanel().getLayout().layout();
				filter.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, filter));
				String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				fillDimension(datasetId, filter.getDimensions());
				olapTool.getToolBase().layout();
			}
		};
	}

	public static SelectionListener<ButtonEvent> removeFilter(final OlapFilterPanel filterPanel) {
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

	public static SelectionListener<IconButtonEvent> showFlatData(final OlapTool window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				ListBox dataSource = window.getTabPanel().getFsatmisDatasetPanel().getDataSource();
				Long resourceId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
//				new TableWindow().build(resourceId, false, false);
			}
		};
	}

	public static SelectionListener<IconButtonEvent> reset(final OlapTool window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				resetFunction(window.getTabPanel().getFsatmisDatasetPanel());			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> reset(final FSATMISDatasetPanel panel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				resetFunction(panel);
			}
		};
	}
	
	private static void resetFunction(FSATMISDatasetPanel panel) {
		clearListBox(panel.getWAxis().getDimensions());
		clearListBox(panel.getWAxis().getValues());
		clearListBox(panel.getXAxis().getDimensions());
		clearListBox(panel.getXAxis().getValues());
		clearListBox(panel.getYAxis().getDimensions());
		clearListBox(panel.getYAxis().getValues());
		clearListBox(panel.getZAxis().getDimensions());
		clearListBox(panel.getZAxis().getValues());
		panel.getDataSource().setSelectedIndex(0);
	}
	
	private static void resetDimensionsFunction(FSATMISDatasetPanel panel) {
		clearListBox(panel.getWAxis().getDimensions());
		clearListBox(panel.getWAxis().getValues());
		clearListBox(panel.getXAxis().getDimensions());
		clearListBox(panel.getXAxis().getValues());
		clearListBox(panel.getYAxis().getDimensions());
		clearListBox(panel.getYAxis().getValues());
		clearListBox(panel.getZAxis().getDimensions());
		clearListBox(panel.getZAxis().getValues());
	}

	private static void clearListBox(ListBox list) {
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			list.removeItem(i);
	}

	public static SelectionListener<IconButtonEvent> exportPDF(final OlapTool window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final LoadingWindow loading = new LoadingWindow("Exporting to PDF", "Loading", "Please Wait...");
				ListBox reportOrientationList = window.getTabPanel().getFsatmisDatasetPanel().getReportOrientation();
				String reportOrientation = reportOrientationList.getValue(reportOrientationList.getSelectedIndex());
				OlapServiceEntry.getInstance().createReport(window.getHtml().getHTML(), reportOrientation, new AsyncCallback<String>() {
					public void onSuccess(String report) {
						loading.destroyLoadingBox();
						Window.open("../exportObject/" + report, "_blank", "status=no");
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
						loading.destroyLoadingBox();
					}
				});
			}
		};
	}

	public static SelectionListener<IconButtonEvent> exportExcel(final OlapTool tool) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final OLAPParametersVo parameters = retrieveParameters(tool);
				try {
					OlapServiceEntry.getInstance().createExcel(parameters, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							Window.open("../olapExcel/" + result, "_blank", "status=no");
						}

						public void onFailure(Throwable e) {
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				}
			}
		};
	}
	
	

	public static SelectionListener<ButtonEvent> createTable(final OlapTool tool, final FSATMISTabPanel tabPanel) {

		return new SelectionListener<ButtonEvent>() {

			public void componentSelected(ButtonEvent event) {

				if (tool.getTabPanel().getFsatmisDatasetPanel().getDataSource().getSelectedIndex() > 0) {

					final OLAPParametersVo parameters = retrieveParameters(tool);
					for(OLAPFilterVo vo :parameters.getFilters()) {
//						System.out.println("filter: " + vo.getDimension() + " | "+ vo.getDimensionLabel());
//						System.out.println("map: " + vo.getDimensionMap());
					}

					if (isValidUserSelection(parameters)) {

						final LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
						
						tool.getHtml().setHTML("");
						
						OlapServiceEntry.getInstance().createTable(parameters, new AsyncCallback<String>() {

							public void onSuccess(String result) {

								tool.getHtml().setHTML(result);

								loading.destroyLoadingBox();

								addLinks(parameters, tool);

								enableToolbar(tool.getOlapToolbar());

								enableTabs(tabPanel);

								loading.destroyLoadingBox();

							}

							public void onFailure(Throwable e) {
								loading.destroyLoadingBox();
								FenixAlert.error(BabelFish.print().info(), e.getMessage());
								loading.destroyLoadingBox();
							}
						});

					} else {
						FenixAlert.info(BabelFish.print().info(), BabelFish.print().isValidUserSelection());
					}

				} else {
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().pleaseSelectADataset());
				}

			}
		};
	}

	public static void enableToolbar(OlapToolbar t) {
		t.getCreateChart().setEnabled(true);
		t.getCreateMap().setEnabled(true);
		t.getExport().setEnabled(true);
		t.getExportExcel().setEnabled(true);
	}

	public static void enableTabs(FSATMISTabPanel tab) { // TODO check a boolean for map panel enabling
		tab.getChartTabItem().setEnabled(true);
		tab.getMapTabItem().setEnabled(true);
	}

	public static boolean isValidUserSelection(OLAPParametersVo p) {
		boolean x = !(p.getXLabels().size() > 0);
		boolean z = !(p.getZLabels().size() > 0);
		boolean y = (p.getYLabels().size() > 0);
		boolean w = (p.getWLabels().size() > 0);
		if (x || z)
			return false;
		if ((x || z) && y)
			return false;
		if (((x || z) && !y) && w)
			return false;
		return true;
	}

	private static void addLinks(final OLAPParametersVo parameters, final OlapTool window) {

		if (parameters.getYLabels().isEmpty() && parameters.getWLabels().isEmpty())
			addTwoDimensionLinks(parameters, window);
		else if (!parameters.getYLabels().isEmpty() && parameters.getWLabels().isEmpty())
			addThreeDimensionLinks(parameters, window);
		else if (!parameters.getYLabels().isEmpty() && !parameters.getWLabels().isEmpty())
			addFourDimensionLinks(parameters, window);

	}

	private static void addTwoDimensionLinks(final OLAPParametersVo p, final OlapTool tool) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
				final String id = "x=" + xValue + ":z=" + zValue;
				if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
					RootPanel.get(id).remove(0);
					Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
					test.addClickListener(new ClickListener() {
						public void onClick(Widget sender) {
							// showElements(p.getDatasetId(), p.getX(),
							// p.getXLabel(), xValue, p.getZ(), p.getZLabel(),
							// zValue, p.getValue(), p.getValueLabel());
						}
					});
					if (RootPanel.get(id) != null)
						RootPanel.get(id).add(test);
					tool.getWrapper().getLayout().layout();
				} else {
					Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
					test.addClickListener(new ClickListener() {
						public void onClick(Widget sender) {
							// showElements(p.getDatasetId(), p.getX(),
							// p.getXLabel(), xValue, p.getZ(), p.getZLabel(),
							// zValue, p.getValue(), p.getValueLabel());
						}
					});
					if (RootPanel.get(id) != null)
						RootPanel.get(id).add(test);
					tool.getWrapper().getLayout().layout();
				}
			}
		}
	}

	private static void addThreeDimensionLinks(final OLAPParametersVo p, final OlapTool tool) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
				for (final String yValue : p.getYLabels().keySet()) {
					final String id = "x=" + xValue + ":z=" + zValue + ":y=" + yValue;
					if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
						RootPanel.get(id).remove(0);
						Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
						test.addClickListener(new ClickListener() {
							public void onClick(Widget sender) {
								// showElements(p.getDatasetId(), p.getX(),
								// p.getXLabel(), xValue, p.getZ(),
								// p.getZLabel(), zValue, p.getY(),
								// p.getYLabel(), yValue, p.getValue(),
								// p.getValueLabel());
							}
						});
						if (RootPanel.get(id) != null)
							RootPanel.get(id).add(test);
						tool.getWrapper().getLayout().layout();
					} else {
						Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
						test.addClickListener(new ClickListener() {
							public void onClick(Widget sender) {
								// showElements(p.getDatasetId(), p.getX(),
								// p.getXLabel(), xValue, p.getZ(),
								// p.getZLabel(), zValue, p.getY(),
								// p.getYLabel(), yValue, p.getValue(),
								// p.getValueLabel());
							}
						});
						if (RootPanel.get(id) != null)
							RootPanel.get(id).add(test);
						tool.getWrapper().getLayout().layout();
					}
				}
			}
		}
	}

	private static void addFourDimensionLinks(final OLAPParametersVo p, final OlapTool tool) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
				for (final String yValue : p.getYLabels().keySet()) {
					for (final String wValue : p.getWLabels().keySet()) {
						final String id = "x=" + xValue + ":z=" + zValue + ":y=" + yValue + ":w=" + wValue;
						if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
							RootPanel.get(id).remove(0);
							Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
							test.addClickListener(new ClickListener() {
								public void onClick(Widget sender) {
									// showElements(p.getDatasetId(), p.getX(),
									// p.getXLabel(), xValue, p.getZ(),
									// p.getZLabel(), zValue, p.getY(),
									// p.getYLabel(), yValue, p.getW(),
									// p.getWLabel(), wValue, p.getValue(),
									// p.getValueLabel());
								}
							});
							RootPanel.get(id).add(test);
							tool.getWrapper().getLayout().layout();
						} else {
							Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
							test.addClickListener(new ClickListener() {
								public void onClick(Widget sender) {
									// showElements(p.getDatasetId(), p.getX(),
									// p.getXLabel(), xValue, p.getZ(),
									// p.getZLabel(), zValue, p.getY(),
									// p.getYLabel(), yValue, p.getW(),
									// p.getWLabel(), wValue, p.getValue(),
									// p.getValueLabel());
								}
							});
							if (RootPanel.get(id) != null)
								RootPanel.get(id).add(test);
							tool.getWrapper().getLayout().layout();
						}
					}
				}
			}
		}
	}

	private static void showElements(Long datasetId, String xHeader, String xValue, String zHeader, String zValue) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(BabelFish.print().value());
		filtersVO.add(vFilter);

		UDTableServiceEntry.getInstance().getTable(filtersVO, "EN", false, new AsyncCallback<UDTableVO>() {
			public void onSuccess(UDTableVO result) {
				result.setFilters(filtersVO);
				SingleElementsWindow w = new SingleElementsWindow();
				w.build(result);
			};

			public void onFailure(Throwable e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			};
		});
	}

	private static void showElements(Long datasetId, String xHeader, String xValue, String zHeader, String zValue, String yHeader, String yValue) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO yFilter = new UDTableFilterVO();
		yFilter.setResourceId(datasetId);
		yFilter.setHeader(yHeader);
		yFilter.addAllowedValues(yValue);
		filtersVO.add(yFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(BabelFish.print().value());
		filtersVO.add(vFilter);

		UDTableServiceEntry.getInstance().getTable(filtersVO, "EN", false, new AsyncCallback<UDTableVO>() {
			public void onSuccess(UDTableVO result) {
				result.setFilters(filtersVO);
				SingleElementsWindow w = new SingleElementsWindow();
				w.build(result);
			};

			public void onFailure(Throwable e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			};
		});
	}

	private static void showElements(Long datasetId, String xHeader, String xValue, String zHeader, String zValue, String yHeader, String yValue, String wHeader, String wValue) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO yFilter = new UDTableFilterVO();
		yFilter.setResourceId(datasetId);
		yFilter.setHeader(yHeader);
		yFilter.addAllowedValues(yValue);
		filtersVO.add(yFilter);

		UDTableFilterVO wFilter = new UDTableFilterVO();
		wFilter.setResourceId(datasetId);
		wFilter.setHeader(wHeader);
		wFilter.addAllowedValues(wValue);
		filtersVO.add(wFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(BabelFish.print().value());
		filtersVO.add(vFilter);

		UDTableServiceEntry.getInstance().getTable(filtersVO, "EN", false, new AsyncCallback<UDTableVO>() {
			public void onSuccess(UDTableVO result) {
				result.setFilters(filtersVO);
				SingleElementsWindow w = new SingleElementsWindow();
				w.build(result);
			};

			public void onFailure(Throwable e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
			};
		});
	}

	public static OLAPParametersVo retrieveParameters(final OlapTool tool) {

		OLAPParametersVo vo = new OLAPParametersVo();

		vo.setDataSourceId(getDatasetId(tool));
		vo.setDataSourceTitle(getDatasetTitle(tool));
		vo.setX(getAxis(tool, "x"));
		vo.setY(getAxis(tool, "y"));
		vo.setZ(getAxis(tool, "z"));
		vo.setW(getAxis(tool, "w"));

		vo.setXLabel(getAxisLabel(tool, "x"));
		vo.setYLabel(getAxisLabel(tool, "y"));
		vo.setZLabel(getAxisLabel(tool, "z"));
		vo.setWLabel(getAxisLabel(tool, "w"));

		vo.setXLabels(getAxisLabels(tool, "x"));
		vo.setYLabels(getAxisLabels(tool, "y"));
		vo.setZLabels(getAxisLabels(tool, "z"));
		vo.setWLabels(getAxisLabels(tool, "w"));

		vo.setFunction(getAggregationFunction(tool));
		vo.setValue(getValue(tool));
		vo.setValueLabel(getValueLabel(tool));

		vo.setFilters(getFilters(tool));

		vo.setShowSingleValues(tool.getTabPanel().getFsatmisDatasetPanel().getShowSingleElements().getValue());
		vo.setShowTotals(tool.getTabPanel().getFsatmisDatasetPanel().getShowTotals().getValue());
		vo.setChartType(getChartType(tool));

		return vo;
	}

	private static String getChartType(OlapTool tool) {
		ListBox list = tool.getTabPanel().getFsatmisDatasetPanel().getChartType();
		return list.getValue(list.getSelectedIndex());
	}

	private static List<OLAPFilterVo> getFilters(final OlapTool tool) {
		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
		for (int i = 0; i < tool.getFilterPanel().getFilterPanel().getItemCount(); i++) {
			ContentPanel filterPanel = (ContentPanel) tool.getFilterPanel().getFilterPanel().getItem(i);
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

	private static List<OLAPFilterVo> getFSATMISFilters(FSATMISTablePanel tablePanel) {
		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
//		for (int i = 0; i < tablePanel.getFilterPanel().getFilterPanel().getItemCount(); i++) {
//			ContentPanel filterPanel = (ContentPanel) tablePanel.getFilterPanel().getFilterPanel().getItem(i);
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
		return filters;
	}

	public static SelectionListener<ButtonEvent> refreshTable(final OlapTool tool, final FSATMISTabPanel tabPanel, final FSATMISTablePanel tablePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<UDTableFilterVO> filters = createUDTableFilters(tool, tablePanel);
				FSATMISToolbarController.refreshRawData(tool, tabPanel, tablePanel, filters);
			}
		};
	}

	private static List<UDTableFilterVO> createUDTableFilters(final OlapTool tool, final FSATMISTablePanel tablePanel) {
		ListBox dataSource = tool.getTabPanel().getFsatmisDatasetPanel().getDataSource();
		Long datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		List<UDTableFilterVO> filters = new ArrayList<UDTableFilterVO>();
		List<OLAPFilterVo> vos = getFSATMISFilters(tablePanel);
		for (OLAPFilterVo vo : vos) {
			UDTableFilterVO f = new UDTableFilterVO();
			List<String> allowedValues = new ArrayList<String>();
			for (String key : vo.getDimensionMap().keySet()) {
				allowedValues.add(key);
				allowedValues.add(vo.getDimensionMap().get(key));
			}
			f.setAllowedValues(allowedValues);
			f.setHeader(vo.getDimensionLabel());
			f.setDataType(vo.getDimension());
			f.setResourceId(datasetId);
			filters.add(f);
		}
		return filters;
	}

	private static String getValue(final OlapTool window) {
		ListBox value = window.getTabPanel().getFsatmisDatasetPanel().getValues();
		return value.getValue(value.getSelectedIndex());
	}

	private static String getValueLabel(final OlapTool window) {
		ListBox value = window.getTabPanel().getFsatmisDatasetPanel().getValues();
		return value.getItemText(value.getSelectedIndex());
	}

	private static String getAggregationFunction(OlapTool window) {
		ListBox function = window.getTabPanel().getFsatmisDatasetPanel().getFunction();
		return function.getValue(function.getSelectedIndex());
	}
	

	private static Map<String, String> getAxisLabels(OlapTool window, String axis) {
		Map<String, String> axisLabels = new HashMap<String, String>();
		if (axis.equals("x")) {			
			ListBox values = window.getTabPanel().getFsatmisDatasetPanel().getXAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("y")) {
			ListBox values = window.getTabPanel().getFsatmisDatasetPanel().getYAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("z")) {
			ListBox values = window.getTabPanel().getFsatmisDatasetPanel().getZAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		} else if (axis.equals("w")) {
			ListBox values = window.getTabPanel().getFsatmisDatasetPanel().getWAxis().getValues();
			for (int i = 0; i < values.getItemCount(); i++)
				if (values.isItemSelected(i))
					axisLabels.put(values.getValue(i), values.getItemText(i));
		}
		return axisLabels;
	}

	private static boolean aggregateAxis(OlapTool window, String axis) {
		// if (axis.equals("x"))
		// return
		// window.getTabPanel().getFsatmisDatasetPanel().getXAxis().getAggregateSubLevel().getValue();
		// else if (axis.equals("y"))
		// return
		// window.getTabPanel().getFsatmisDatasetPanel().getYAxis().getAggregateSubLevel().getValue();
		// else if (axis.equals("z"))
		// return
		// window.getTabPanel().getFsatmisDatasetPanel().getZAxis().getAggregateSubLevel().getValue();
		// else if (axis.equals("w"))
		// return
		// window.getTabPanel().getFsatmisDatasetPanel().getWAxis().getAggregateSubLevel().getValue();
		return false;
	}

	private static Long getDatasetId(OlapTool window) {
		Long datasetId = null;
		ListBox dataSource = window.getTabPanel().getFsatmisDatasetPanel().getDataSource();
		datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		return datasetId;
	}

	private static String getDatasetTitle(OlapTool window) {
		ListBox dataSource = window.getTabPanel().getFsatmisDatasetPanel().getDataSource();
		return dataSource.getItemText(dataSource.getSelectedIndex());
	}

	private static String getAxis(OlapTool window, String axis) {
		String x = "";
		ListBox dimensions = window.getTabPanel().getFsatmisDatasetPanel().getXAxis().getDimensions();
		if (axis.equalsIgnoreCase("y"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getYAxis().getDimensions();
		else if (axis.equalsIgnoreCase("z"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getZAxis().getDimensions();
		else if (axis.equalsIgnoreCase("w"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getWAxis().getDimensions();
		if (dimensions.getSelectedIndex() > 0)
			x = dimensions.getValue(dimensions.getSelectedIndex());
		return x;
	}

	private static String getAxisLabel(OlapTool window, String axis) {
		String x = "";
		ListBox dimensions = window.getTabPanel().getFsatmisDatasetPanel().getXAxis().getDimensions();
		if (axis.equalsIgnoreCase("y"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getYAxis().getDimensions();
		else if (axis.equalsIgnoreCase("z"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getZAxis().getDimensions();
		else if (axis.equalsIgnoreCase("w"))
			dimensions = window.getTabPanel().getFsatmisDatasetPanel().getWAxis().getDimensions();
		if (dimensions.getSelectedIndex() > 0)
			x = dimensions.getItemText(dimensions.getSelectedIndex());
		return x;
	}

	public static void fillDataSourceList(final ListBox dataSourceList) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
		
		OlapServiceEntry.getInstance().findAllFSATMISDataset(new AsyncCallback<Map<String, String>>() { 
			
					public void onSuccess(Map<String, String> datasets) {
						dataSourceList.addItem(BabelFish.print().pleaseSelectADataset() + "...");
						for (int i = dataSourceList.getItemCount() - 1; i >= 0; i--)
							dataSourceList.removeItem(i);
						dataSourceList.addItem(BabelFish.print().pleaseSelectADataset());
						for (String key : datasets.keySet())
							dataSourceList.addItem(datasets.get(key), key);
						
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
						loading.destroyLoadingBox();
					}
				});
	}

	@SuppressWarnings("deprecation")
	public static ChangeListener dataSourceChangeListener(final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values, final FSATMISDatasetPanel fsatmisDatasetPanel) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dataSource = (ListBox) sender;
				if (dataSource.getSelectedIndex() > 0) {
					String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					updateDimensions(datasetId, xDimensions, yDimensions, zDimensions, wDimensions, values);
					resetDimensionsFunction(fsatmisDatasetPanel);
				}
			}
		};
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
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			}

		});
	}

	public static void updateDimensions(final String datasetId, final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasetId, new AsyncCallback<Map<String, String>>() {

			public void onSuccess(Map<String, String> out) {

				TreeMap<String, String> results = sortByKeys(out);
				
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

	@Deprecated
	/*
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
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
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
							
							LinkedHashMap<String, String> out = sortByValues(results);
//							for (String key : results.keySet())
							for (String key : out.keySet())
								values.addItem(results.get(key), key);
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable e) {
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							loading.destroyLoadingBox();
						}
					});

				}
			}
		};
	}
	
	private static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++) 
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	private static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
	public static SelectionListener<IconButtonEvent> chartTest(final OlapTool tool) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				ChartWidget chart = new ChartWidget();		
				ChartData cd = new ChartData("Relative Performance","font-size: 14px; font-family: Verdana; text-align: center;");
				cd.setBackgroundColour("#ffffff");
				

				LineChart lc2 = new LineChart();
				lc2.setColour("#00ff00");
				lc2.setText("Ave-Ridge Co LLC");
				for (int t=0;t<30;t++) {
					lc2.addValues(9);
				}
			
				YAxis ya = new YAxis();
				ya.setMax(2);
				ya.setMin(-1);
				cd.setYAxis(ya);
				

				cd.addElements(lc2);

				chart.setSize("600", "300");
				chart.setJsonData(cd.toString());
				com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
				window.add(chart);
				window.show();
			}
		};
	}

}