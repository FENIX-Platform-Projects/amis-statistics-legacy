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
package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.olap.client.control.OlapOpener.ResourceViewSettings;
import org.fao.fenix.web.modules.olap.client.view.AxisPanel;
import org.fao.fenix.web.modules.olap.client.view.ExportToHtmlWindow;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapFilterPanel;
import org.fao.fenix.web.modules.olap.client.view.OlapToolbar;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.client.view.SingleElementsWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class OlapController {

	public static SelectionListener<IconButtonEvent> saveAs(final OlapWindow w) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				MESaveAs.prepopulateOlap(w.getResourceViewID(), true, false, w);
			}
		};
	}
	
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
				addFilterAgent(filterPanel, dataSource);
			}
		};
	}
	
	public static AxisPanel addFilterAgent(final OlapFilterPanel filterPanel, final ListBox dataSource) {
		AxisPanel filter = new AxisPanel("F");
		ContentPanel filterContentPanel = filter.build(BabelFish.print().filter(), false);
		filterPanel.getFilterPanel().add(filterContentPanel);
		filterPanel.getFilterPanel().getLayout().layout();
		filter.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, filter));
		String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
		fillDimension(datasetId, filter.getDimensions());
		return filter;
	}
	
	public static void addFilterAndSelectValuesAgent(OlapFilterPanel filterPanel, ListBox dataSource, String dimensionType, String dimensionLabel, List<UniqueValueVO> vos) {
		AxisPanel filter = new AxisPanel("F");
		ContentPanel filterContentPanel = filter.build(BabelFish.print().filter(), false);
		filterPanel.getFilterPanel().add(filterContentPanel);
		filterPanel.getFilterPanel().getLayout().layout();
		filter.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, filter));
		String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
		fillDimensionAndSelectValues(datasetId, dataSource, filter.getDimensions(), filter.getValues(), dimensionType, dimensionLabel, vos);
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

	public static SelectionListener<ButtonEvent> addDataset(final OlapWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new ResourceExplorerDataset(window);
			}
		};
	}

   public static SelectionListener<IconButtonEvent> showFlatData(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
				Long resourceId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
				String datasetTitle = dataSource.getItemText(dataSource.getSelectedIndex());
				new TableWindow().build(resourceId, false, false, datasetTitle);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> openInProjectManager(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
				String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				String hostPageURL = GWT.getHostPageBaseURL();
				int index = hostPageURL.indexOf("fenix-web") + "fenix-web".length();
				String url = hostPageURL.substring(0, index) + "/fenix/Fenix.html?openFSATMISDataset=" + datasetId;
				Window.open(url, "_blank", "status=no");
			};
		};
	}

	public static SelectionListener<IconButtonEvent> reset(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getWAxis().getValues());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getXAxis().getValues());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getYAxis().getValues());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions());
				clearListBox(window.getTabPanel().getSingleDatasetPanel().getZAxis().getValues());
				window.getTabPanel().getSingleDatasetPanel().getDataSource().setSelectedIndex(0);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> reset(final AxisPanel panel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				panel.getDimensions().setSelectedIndex(0);
				clearListBox(panel.getValues());
			}
		};
	}

	private static void clearListBox(ListBox list) {
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			list.removeItem(i);
	}

	public static SelectionListener<IconButtonEvent> exportPDF(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final LoadingWindow loading = new LoadingWindow("Exporting to PDF", "Loading", "Please Wait...");
				ListBox reportOrientationList = window.getTabPanel().getSingleDatasetPanel().getReportOrientation(); 
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
	
	public static SelectionListener<IconButtonEvent> exportExcel(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final OLAPParametersVo parameters = retrieveParameters(window);
				final LoadingWindow l = new LoadingWindow("Export to Excel", "Creating Excel...", "Please wait...");
				try {
					OlapServiceEntry.getInstance().createExcel(parameters, new AsyncCallback<String>() {
						public void onSuccess(String result) {
							l.destroyLoadingBox();
							Window.open("../olapExcel/" + result, "_blank", "status=no");
							l.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							l.destroyLoadingBox();
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					l.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					l.destroyLoadingBox();
				}
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> exportHTML(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				try {
					OlapServiceEntry.getInstance().getIFrame(window.getResourceViewID(), new AsyncCallback<String>() {
						public void onSuccess(String iframe) {
							ExportToHtmlWindow w = new ExportToHtmlWindow();
							String instructions = "Copy and paste the following string in your web page to include this multidimensional table in your website:\n\n";
							w.build(instructions + iframe);
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
	
	public static SelectionListener<IconButtonEvent> showManual(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				window.getHtml().setHTML(BabelFish.print().olapManual());
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addFilters(final OlapWindow window, final ToggleButton toggleButton) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (!toggleButton.isPressed())
					window.getEast().hide();
				else
					window.getEast().show();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> createTable(final OlapWindow window) {

		return new SelectionListener<ButtonEvent>() {

			public void componentSelected(ButtonEvent event) {

				if (window.getTabPanel().getSingleDatasetPanel().getDataSource().getItemCount() > 0) {
				
					final LoadingWindow loading = new LoadingWindow(BabelFish.print().info(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
					
					final OLAPParametersVo parameters = retrieveParameters(window);
	
					if (isValidUserSelection(parameters)) {
					
						window.getHtml().setHTML("");
		
						OlapServiceEntry.getInstance().createTable(parameters, new AsyncCallback<String>() {
		
							public void onSuccess(String result) {
		
								window.getHtml().setHTML(result);
		
								loading.destroyLoadingBox();
								
								addLinks(parameters, window);
								
								enableToolbar(window.getOlapToolbar());
								
								// show widths and heights
								boolean showWidthsAndHeights = window.getTabPanel().getSingleDatasetPanel().getShowWidthAndHeight().getValue();
								showWidthsAndHeightsAgent(window, showWidthsAndHeights);
								
								loading.destroyLoadingBox();
		
							}
		
							public void onFailure(Throwable e) {
								loading.destroyLoadingBox();
								FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
								loading.destroyLoadingBox();
							}
						});
					
					} else {
						loading.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
						loading.destroyLoadingBox();
					}
				
				} else {
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().pleaseSelectADataset());
				}

			}
		};
	}
	
	public static void enableToolbar(OlapToolbar t) {
//		t.getCreateChart().setEnabled(true);
//		t.getCreateMap().setEnabled(true);
		t.getExport().setEnabled(true);
		t.getExportExcel().setEnabled(true);
		t.getExportHTML().setEnabled(true);
	}
	
	public static boolean isValidUserSelection(OLAPParametersVo p) {
		boolean x = (p.getXLabels().size() > 0) || p.isxUseFromDateToDate() || p.isxUseMostRecentData();
		boolean z = (p.getZLabels().size() > 0) || p.iszUseFromDateToDate() || p.iszUseMostRecentData();
		if (x || z)
			return true;
		return false;
	}

	private static void addLinks(final OLAPParametersVo parameters, final OlapWindow window) {

		if (parameters.getYLabels().isEmpty() && parameters.getWLabels().isEmpty())
			addTwoDimensionLinks(parameters, window);
		else if (!parameters.getYLabels().isEmpty() && parameters.getWLabels().isEmpty())
			addThreeDimensionLinks(parameters, window);
		else if (!parameters.getYLabels().isEmpty() && !parameters.getWLabels().isEmpty())
			addFourDimensionLinks(parameters, window);

	}

	private static void addTwoDimensionLinks(final OLAPParametersVo p, final OlapWindow window) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
//				final String id = "x=" + xValue + ":z=" + zValue;
				final String id = "x=" + xValue + ";z=" + zValue;
				if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
					RootPanel.get(id).remove(0);
					Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
					test.addClickListener(new ClickListener() {
						public void onClick(Widget sender) {
							showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getValue(), p.getValueLabel());
						}
					});
					if (RootPanel.get(id) != null)
						RootPanel.get(id).add(test);
					window.getWrapper().getLayout().layout();
				} else {
					Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
					test.addClickListener(new ClickListener() {
						public void onClick(Widget sender) {
							showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getValue(), p.getValueLabel());
						}
					});
					if (RootPanel.get(id) != null)
						RootPanel.get(id).add(test);
					window.getWrapper().getLayout().layout();
				}
			}
		}
	}
	
	private static void addThreeDimensionLinks(final OLAPParametersVo p, final OlapWindow window) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
				for (final String yValue : p.getYLabels().keySet()) {
//					final String id = "x=" + xValue + ":z=" + zValue + ":y=" + yValue;
					final String id = "x=" + xValue + ";z=" + zValue + ";y=" + yValue;
//					System.out.println(id + " >>> " + RootPanel.get(id));
					if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
						RootPanel.get(id).remove(0);
						Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
						test.addClickListener(new ClickListener() {
							public void onClick(Widget sender) {
								showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getY(), p.getYLabel(), yValue, p.getValue(), p.getValueLabel());
							}
						});
						RootPanel.get(id).add(test);
						window.getWrapper().getLayout().layout();
					} else {
						Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
						test.addClickListener(new ClickListener() {
							public void onClick(Widget sender) {
								showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getY(), p.getYLabel(), yValue, p.getValue(), p.getValueLabel());
							}
						});
						if (RootPanel.get(id) != null)
							RootPanel.get(id).add(test);
						window.getWrapper().getLayout().layout();
					}
				}
			}
		}
	}
	
	private static void addFourDimensionLinks(final OLAPParametersVo p, final OlapWindow window) {
		for (final String xValue : p.getXLabels().keySet()) {
			for (final String zValue : p.getZLabels().keySet()) {
				for (final String yValue : p.getYLabels().keySet()) {
					for (final String wValue : p.getWLabels().keySet()) {
//						final String id = "x=" + xValue + ":z=" + zValue + ":y=" + yValue + ":w=" + wValue;
						final String id = "x=" + xValue + ";z=" + zValue + ";y=" + yValue + ";w=" + wValue;
						if ((RootPanel.get(id) != null) && RootPanel.get(id).getWidgetCount() > 0) {
							RootPanel.get(id).remove(0);
							Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
							test.addClickListener(new ClickListener() {
								public void onClick(Widget sender) {
									showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getY(), p.getYLabel(), yValue, p.getW(), p.getWLabel(), wValue, p.getValue(), p.getValueLabel());
								}
							});
							RootPanel.get(id).add(test);
							window.getWrapper().getLayout().layout();
						} else {
							Hyperlink test = new Hyperlink(BabelFish.print().elements(), id);
							test.addClickListener(new ClickListener() {
								public void onClick(Widget sender) {
									showElements(p.getDataSourceId(), p.getX(), p.getXLabel(), xValue, p.getZ(), p.getZLabel(), zValue, p.getY(), p.getYLabel(), yValue, p.getW(), p.getWLabel(), wValue, p.getValue(), p.getValueLabel());
								}
							});
							if (RootPanel.get(id) != null)
								RootPanel.get(id).add(test);
							window.getWrapper().getLayout().layout();
						}
					}
				}
			}
		}
	}

	private static void showElements(Long datasetId, String xDataType, String xHeader, String xValue, String zDataType, String zHeader, String zValue, String value, String valueLabel) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.setDataType(xDataType);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.setDataType(zDataType);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(valueLabel);
		vFilter.setDataType(value);
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

	private static void showElements(Long datasetId, String xDataType, String xHeader, String xValue, String zDataType, String zHeader, String zValue, String yDataType, String yHeader, String yValue, String value, String valueLabel) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.setDataType(xDataType);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.setDataType(zDataType);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO yFilter = new UDTableFilterVO();
		yFilter.setResourceId(datasetId);
		yFilter.setHeader(yHeader);
		yFilter.setDataType(yDataType);
		yFilter.addAllowedValues(yValue);
		filtersVO.add(yFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(valueLabel);
		vFilter.setDataType(value);
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

	private static void showElements(Long datasetId, String xDataType, String xHeader, String xValue, String zDataType, String zHeader, String zValue, String yDataType, String yHeader, String yValue, String wDataType, String wHeader, String wValue, String value, String valueLabel) {

		final List<UDTableFilterVO> filtersVO = new ArrayList<UDTableFilterVO>();

		UDTableFilterVO xFilter = new UDTableFilterVO();
		xFilter.setResourceId(datasetId);
		xFilter.setHeader(xHeader);
		xFilter.setDataType(xDataType);
		xFilter.addAllowedValues(xValue);
		filtersVO.add(xFilter);

		UDTableFilterVO zFilter = new UDTableFilterVO();
		zFilter.setResourceId(datasetId);
		zFilter.setHeader(zHeader);
		zFilter.setDataType(zDataType);
		zFilter.addAllowedValues(zValue);
		filtersVO.add(zFilter);

		UDTableFilterVO yFilter = new UDTableFilterVO();
		yFilter.setResourceId(datasetId);
		yFilter.setHeader(yHeader);
		yFilter.setDataType(yDataType);
		yFilter.addAllowedValues(yValue);
		filtersVO.add(yFilter);

		UDTableFilterVO wFilter = new UDTableFilterVO();
		wFilter.setResourceId(datasetId);
		wFilter.setHeader(wHeader);
		wFilter.setDataType(wDataType);
		wFilter.addAllowedValues(wValue);
		filtersVO.add(wFilter);

		UDTableFilterVO vFilter = new UDTableFilterVO();
		vFilter.setResourceId(datasetId);
		vFilter.setHeader(valueLabel);
		vFilter.setDataType(value);
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

	public static OLAPParametersVo retrieveParameters(final OlapWindow window) {

		OLAPParametersVo vo = new OLAPParametersVo();
		
		Date today = new Date();

		vo.setDataSourceId(getDatasetId(window));
		vo.setDataSourceTitle(getDatasetTitle(window));
		vo.setDataSourceType("Dataset");
		vo.setValue(getValue(window));
		vo.setValueLabel(getValueLabel(window));
		vo.setFilters(getFilters(window));
		vo.setShowSingleValues(window.getTabPanel().getSingleDatasetPanel().getShowSingleElements().getValue());
		vo.setFunction(getAggregationFunction(window));
		
		//mackx
		vo.setShowColSummary(window.getTabPanel().getSingleDatasetPanel().getShowColTotals().getValue());
		vo.setShowRowSummary(window.getTabPanel().getSingleDatasetPanel().getShowRowTotals().getValue());
		//mackx off
		
		vo.setShowTotals(window.getTabPanel().getSingleDatasetPanel().getShowTotals().getValue());
		vo.setDecimals(Integer.parseInt(window.getTabPanel().getSingleDatasetPanel().getDecimals().getValue(window.getTabPanel().getSingleDatasetPanel().getDecimals().getSelectedIndex())));
		vo.setReportOrientation(window.getTabPanel().getSingleDatasetPanel().getReportOrientation().getValue(window.getTabPanel().getSingleDatasetPanel().getReportOrientation().getSelectedIndex()));
		vo.setResourceViewID(window.getResourceViewID());
		
		vo.setX(getAxis(window, "x"));
		vo.setXLabel(getAxisLabel(window, "x"));
		vo.setAggregateXAsMonthly(window.getTabPanel().getSingleDatasetPanel().getXAxis().getAggregateAsMonthly().getValue());
		vo.setAggregateXAsYearly(window.getTabPanel().getSingleDatasetPanel().getXAxis().getAggregateAsYearly().getValue());
		
		vo.setZ(getAxis(window, "z"));
		vo.setZLabel(getAxisLabel(window, "z"));
		vo.setAggregateZAsMonthly(window.getTabPanel().getSingleDatasetPanel().getZAxis().getAggregateAsMonthly().getValue());
		vo.setAggregateZAsYearly(window.getTabPanel().getSingleDatasetPanel().getZAxis().getAggregateAsYearly().getValue());
		
		vo.setY(getAxis(window, "y"));
		vo.setYLabel(getAxisLabel(window, "y"));
		vo.setAggregateYAsMonthly(window.getTabPanel().getSingleDatasetPanel().getYAxis().getAggregateAsMonthly().getValue());
		vo.setAggregateYAsYearly(window.getTabPanel().getSingleDatasetPanel().getYAxis().getAggregateAsYearly().getValue());
		
		vo.setW(getAxis(window, "w"));
		vo.setWLabel(getAxisLabel(window, "w"));
		vo.setAggregateWAsMonthly(window.getTabPanel().getSingleDatasetPanel().getWAxis().getAggregateAsMonthly().getValue());
		vo.setAggregateWAsYearly(window.getTabPanel().getSingleDatasetPanel().getWAxis().getAggregateAsYearly().getValue());
		
		if ((window.getTabPanel().getSingleDatasetPanel().getXAxis().isUseFromDateToDate() == false) && (window.getTabPanel().getSingleDatasetPanel().getXAxis().isUseMostRecentData() == false)) {
			vo.setXLabels(getAxisLabels(window, "x"));
			valuesListChangeHandlerAgent(window.getTabPanel().getSingleDatasetPanel().getXAxis().getValues(), window.getTabPanel().getSingleDatasetPanel().getXAxis());
			vo.setxUniqueValuesMap(window.getTabPanel().getSingleDatasetPanel().getXAxis().getUniqueValuesMap());			
		}
		
		if ((window.getTabPanel().getSingleDatasetPanel().getZAxis().isUseFromDateToDate() == false) && (window.getTabPanel().getSingleDatasetPanel().getZAxis().isUseMostRecentData() == false)) {
			vo.setZLabels(getAxisLabels(window, "z"));
			valuesListChangeHandlerAgent(window.getTabPanel().getSingleDatasetPanel().getZAxis().getValues(), window.getTabPanel().getSingleDatasetPanel().getZAxis());
			vo.setzUniqueValuesMap(window.getTabPanel().getSingleDatasetPanel().getZAxis().getUniqueValuesMap());			
		}
		
		if ((window.getTabPanel().getSingleDatasetPanel().getYAxis().isUseFromDateToDate() == false) && (window.getTabPanel().getSingleDatasetPanel().getYAxis().isUseMostRecentData() == false)) {
			vo.setYLabels(getAxisLabels(window, "y"));
			valuesListChangeHandlerAgent(window.getTabPanel().getSingleDatasetPanel().getYAxis().getValues(), window.getTabPanel().getSingleDatasetPanel().getYAxis());
			vo.setyUniqueValuesMap(window.getTabPanel().getSingleDatasetPanel().getYAxis().getUniqueValuesMap());			
		}
		
		if ((window.getTabPanel().getSingleDatasetPanel().getWAxis().isUseFromDateToDate() == false) && (window.getTabPanel().getSingleDatasetPanel().getWAxis().isUseMostRecentData() == false)) {
			vo.setWLabels(getAxisLabels(window, "w"));
			valuesListChangeHandlerAgent(window.getTabPanel().getSingleDatasetPanel().getWAxis().getValues(), window.getTabPanel().getSingleDatasetPanel().getWAxis());
			vo.setwUniqueValuesMap(window.getTabPanel().getSingleDatasetPanel().getWAxis().getUniqueValuesMap());			
		}
		
		if (window.getTabPanel().getSingleDatasetPanel().getXAxis().isUseFromDateToDate()) {
			vo.setxUseFromDateToDate(true);
			vo.setxFromDate(window.getTabPanel().getSingleDatasetPanel().getXAxis().getFromDateField().getValue());
			vo.setxToDate(window.getTabPanel().getSingleDatasetPanel().getXAxis().getToDateField().getValue());
		} else if (window.getTabPanel().getSingleDatasetPanel().getXAxis().isUseMostRecentData()) {
			vo.setxUseMostRecentData(true);
			int years = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestYearList().getValue(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestYearList().getSelectedIndex()));
			int months = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestMonthList().getValue(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestMonthList().getSelectedIndex())) - 1;
			int days = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestDayList().getValue(window.getTabPanel().getSingleDatasetPanel().getXAxis().getLatestDayList().getSelectedIndex()));
			Date xMostRecentDataStartDate = new Date((today.getYear() - years), (today.getMonth() - months - 1), (today.getDate() - days));
			vo.setxMostRecentDataStartDate(xMostRecentDataStartDate);
			vo.setxLatestYears(years);
			vo.setxLatestMonths(months);
			vo.setxLatestDays(days);
		}
		
		if (window.getTabPanel().getSingleDatasetPanel().getZAxis().isUseFromDateToDate()) {
			vo.setzUseFromDateToDate(true);
			vo.setzFromDate(window.getTabPanel().getSingleDatasetPanel().getZAxis().getFromDateField().getValue());
			vo.setzToDate(window.getTabPanel().getSingleDatasetPanel().getZAxis().getToDateField().getValue());
		} else if (window.getTabPanel().getSingleDatasetPanel().getZAxis().isUseMostRecentData()) {
			vo.setzUseMostRecentData(true);
			int years = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestYearList().getValue(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestYearList().getSelectedIndex()));
			int months = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestMonthList().getValue(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestMonthList().getSelectedIndex())) - 1;
			int days = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestDayList().getValue(window.getTabPanel().getSingleDatasetPanel().getZAxis().getLatestDayList().getSelectedIndex()));
			Date zMostRecentDataStartDate = new Date((today.getYear() - years), (today.getMonth() - months - 1), (today.getDate() - days));
			vo.setzMostRecentDataStartDate(zMostRecentDataStartDate);
			vo.setzLatestYears(years);
			vo.setzLatestMonths(months);
			vo.setzLatestDays(days);
		}
		
		if (window.getTabPanel().getSingleDatasetPanel().getYAxis().isUseFromDateToDate()) {
			vo.setyUseFromDateToDate(true);
			vo.setyFromDate(window.getTabPanel().getSingleDatasetPanel().getYAxis().getFromDateField().getValue());
			vo.setyToDate(window.getTabPanel().getSingleDatasetPanel().getYAxis().getToDateField().getValue());
		} else if (window.getTabPanel().getSingleDatasetPanel().getYAxis().isUseMostRecentData()) {
			vo.setyUseMostRecentData(true);
			int years = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestYearList().getValue(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestYearList().getSelectedIndex()));
			int months = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestMonthList().getValue(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestMonthList().getSelectedIndex())) - 1;
			int days = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestDayList().getValue(window.getTabPanel().getSingleDatasetPanel().getYAxis().getLatestDayList().getSelectedIndex()));
			Date yMostRecentDataStartDate = new Date((today.getYear() - years), (today.getMonth() - months - 1), (today.getDate() - days));
			vo.setyMostRecentDataStartDate(yMostRecentDataStartDate);
			vo.setyLatestYears(years);
			vo.setyLatestMonths(months);
			vo.setyLatestDays(days);
		}
		
		if (window.getTabPanel().getSingleDatasetPanel().getWAxis().isUseFromDateToDate()) {
			vo.setwUseFromDateToDate(true);
			vo.setwFromDate(window.getTabPanel().getSingleDatasetPanel().getWAxis().getFromDateField().getValue());
			vo.setwToDate(window.getTabPanel().getSingleDatasetPanel().getWAxis().getToDateField().getValue());
		} else if (window.getTabPanel().getSingleDatasetPanel().getWAxis().isUseMostRecentData()) {
			vo.setwUseMostRecentData(true);
			int years = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestYearList().getValue(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestYearList().getSelectedIndex()));
			int months = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestMonthList().getValue(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestMonthList().getSelectedIndex())) - 1;
			int days = Integer.valueOf(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestDayList().getValue(window.getTabPanel().getSingleDatasetPanel().getWAxis().getLatestDayList().getSelectedIndex()));
			Date wMostRecentDataStartDate = new Date((today.getYear() - years), (today.getMonth() - months - 1), (today.getDate() - days));
			vo.setwMostRecentDataStartDate(wMostRecentDataStartDate);
			vo.setwLatestYears(years);
			vo.setwLatestMonths(months);
			vo.setwLatestDays(days);
		}
		
		vo.setColHeaderColor(window.getTabPanel().getSingleDatasetPanel().getColHeaderColor().getText());
		vo.setColFontColor(window.getTabPanel().getSingleDatasetPanel().getColFontColor().getText());
		vo.setRowHeaderColor(window.getTabPanel().getSingleDatasetPanel().getRowHeaderColor().getText());
		vo.setRowFontColor(window.getTabPanel().getSingleDatasetPanel().getRowFontColor().getText());
		vo.setSubColHeaderColor(window.getTabPanel().getSingleDatasetPanel().getSubColHeaderColor().getText());
		vo.setSubColFontColor(window.getTabPanel().getSingleDatasetPanel().getSubColFontColor().getText());
		vo.setSubRowHeaderColor(window.getTabPanel().getSingleDatasetPanel().getSubRowHeaderColor().getText());
		vo.setSubRowFontColor(window.getTabPanel().getSingleDatasetPanel().getSubRowFontColor().getText());
		vo.setContentBackgroundColor(window.getTabPanel().getSingleDatasetPanel().getContentBackgroundColor().getText());
		vo.setContentFontColor(window.getTabPanel().getSingleDatasetPanel().getContentFontColor().getText());
		vo.setFontFamily(window.getTabPanel().getSingleDatasetPanel().getFontFamily().getValue(window.getTabPanel().getSingleDatasetPanel().getFontFamily().getSelectedIndex()));
		vo.setFontSize(window.getTabPanel().getSingleDatasetPanel().getFontSize().getValue(window.getTabPanel().getSingleDatasetPanel().getFontSize().getSelectedIndex()));
		vo.setSummaryBackgroundColor(window.getTabPanel().getSingleDatasetPanel().getSummaryBackgroundColor().getText());
		vo.setSummaryFontColor(window.getTabPanel().getSingleDatasetPanel().getSummaryFontColor().getText());
		vo.setOlapTitleFontColor(window.getTabPanel().getSingleDatasetPanel().getTitleFontColor().getText());
		vo.setOlapTitle(window.getTabPanel().getSingleDatasetPanel().getOlapTitle().getValue());
		
		// collect widths
		for (String key: window.getOlapRuler().getColumnLabelWidths().keySet()) 
			vo.getColumnLabelsWidths().put(key, window.getOlapRuler().getColumnLabelWidths().get(key).getValue() + "px");
		// collect function and label (Z or W) width
		vo.setFunctionWidth(window.getOlapRuler().getFunctionWidthField().getValue() + "px");
		vo.setFunctionHeight(window.getOlapRuler().getFunctionHeightField().getValue() + "px");
		vo.setColumnLabelWidth(window.getOlapRuler().getColumnLabel().getValue() + "px");
		vo.setSubRowLabelWidth(window.getOlapRuler().getSubRowLabel().getValue() + "px");
		// collect heights
		for (String key: window.getOlapRuler().getRowLabelHeights().keySet()) 
			vo.getRowLabelsHeights().put(key, window.getOlapRuler().getRowLabelHeights().get(key).getValue() + "px");
		
		vo.setPeriodTypeCode(getPeriodTypeCode(window));
		
		return vo;
	}
	
	private static String getPeriodTypeCode(OlapWindow w) {
		String ptc = "daily";
		ListBox xDim = w.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		ListBox zDim = w.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		ListBox yDim = w.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		ListBox wDim = w.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		boolean xHasDate = xDim.getValue(xDim.getSelectedIndex()).contains("date") || xDim.getValue(xDim.getSelectedIndex()).contains("Date");
		boolean zHasDate = zDim.getValue(xDim.getSelectedIndex()).contains("date") || zDim.getValue(xDim.getSelectedIndex()).contains("Date");
		boolean yHasDate = yDim.getValue(xDim.getSelectedIndex()).contains("date") || yDim.getValue(xDim.getSelectedIndex()).contains("Date");
		boolean wHasDate = wDim.getValue(xDim.getSelectedIndex()).contains("date") || wDim.getValue(xDim.getSelectedIndex()).contains("Date");
		if (xHasDate) {
			for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues().getItemCount() ; i++) {
				if (w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues().isItemSelected(i)) {
					ptc = date2periodTypeCode(w.getTabPanel().getSingleDatasetPanel().getXAxis().getValues().getItemText(i));
					break;
				}
			}
		} else if (zHasDate) {
			for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getZAxis().getValues().getItemCount() ; i++) {
				if (w.getTabPanel().getSingleDatasetPanel().getZAxis().getValues().isItemSelected(i)) {
					ptc = date2periodTypeCode(w.getTabPanel().getSingleDatasetPanel().getZAxis().getValues().getItemText(i));
					break;
				}
			}
		} else if (yHasDate) {
			for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().getItemCount() ; i++) {
				if (w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().isItemSelected(i)) {
					ptc = date2periodTypeCode(w.getTabPanel().getSingleDatasetPanel().getYAxis().getValues().getItemText(i));
					break;
				}
			}
		} else if (wHasDate) {
			for (int i = 0 ; i < w.getTabPanel().getSingleDatasetPanel().getWAxis().getValues().getItemCount() ; i++) {
				if (w.getTabPanel().getSingleDatasetPanel().getWAxis().getValues().isItemSelected(i)) {
					ptc = date2periodTypeCode(w.getTabPanel().getSingleDatasetPanel().getWAxis().getValues().getItemText(i));
					break;
				}
			}
		}
		return ptc;
	}
	
	private static String date2periodTypeCode(String dateString) {
		String ptc = "daily";
		if ((dateString.indexOf("-") == 2) && (dateString.length() == 7))
			ptc = "monthly";
		else if (dateString.length() == 4)
			ptc = "yearly";
		return ptc;
	}

	private static List<OLAPFilterVo> getFilters(final OlapWindow window) {
		List<OLAPFilterVo> filters = new ArrayList<OLAPFilterVo>();
		for (int i = 0; i < window.getFilterPanel().getFilterPanel().getItemCount(); i++) {
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

	private static String getValue(final OlapWindow window) {
		ListBox value = window.getTabPanel().getSingleDatasetPanel().getValues();
		return value.getValue(value.getSelectedIndex());
	}

	private static String getValueLabel(final OlapWindow window) {
		ListBox value = window.getTabPanel().getSingleDatasetPanel().getValues();
		return value.getItemText(value.getSelectedIndex());
	}

	private static String getAggregationFunction(OlapWindow window) {
		ListBox function = window.getTabPanel().getSingleDatasetPanel().getFunction();
		return function.getValue(function.getSelectedIndex());
	}

	private static Map<String, String> getAxisLabels(OlapWindow window, String axis) {
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

	private static Long getDatasetId(OlapWindow window) {
		Long datasetId = null;
		ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
		datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		return datasetId;
	}

	private static String getDatasetTitle(OlapWindow window) {
//		ListBox dataSource = window.getTabPanel().getSingleDatasetPanel().getDataSource();
//		return dataSource.getItemText(dataSource.getSelectedIndex());
		return window.getTabPanel().getSingleDatasetPanel().getOlapTitle().getValue();
	}

	private static String getAxis(OlapWindow window, String axis) {
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

	private static String getAxisLabel(OlapWindow window, String axis) {
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
		OlapServiceEntry.getInstance().findAllCoreDataset(new AsyncCallback<Map<String, String>>() { 
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
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			}
		});
	}

	public static ChangeListener dataSourceChangeListener(final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox dataSource = (ListBox) sender;
				if (dataSource.getSelectedIndex() > 0) {
					String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
					updateDimensions(datasetId, xDimensions, yDimensions, zDimensions, wDimensions, values, null, null);
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
	
	public static void fillDimensionAndSelectValues(final String datasetId, final ListBox datasource, final ListBox dimensions, final ListBox values, final String dimensionType, final String dimensionLabel, final List<UniqueValueVO> vos) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasetId, new AsyncCallback<Map<String, String>>() {

			public void onSuccess(Map<String, String> results) {

				for (int i = dimensions.getItemCount() - 1; i >= 0; i--)
					dimensions.removeItem(i);
				dimensions.addItem(BabelFish.print().pleaseSelectADimension());
				
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						dimensions.addItem(dimension, results.get(dimension));
				
				for (int i = 0 ; i < dimensions.getItemCount() ; i++) 
					if ((dimensions.getItemText(i).equalsIgnoreCase(dimensionLabel)) && (dimensions.getValue(i).equalsIgnoreCase(dimensionType))) 
						dimensions.setItemSelected(i, true);
				
				dimensionsChangeWithLabelsAgent(datasource, dimensions, values, vos);
				
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			}

		});
	}

	public static void updateDimensions(final String datasetId, final ListBox xDimensions, final ListBox yDimensions, final ListBox zDimensions, final ListBox wDimensions, final ListBox values, final MTWindow w, final OLAPParametersVo p) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().findAllDatasetTypeDescriptors(datasetId, new AsyncCallback<Map<String, String>>() {

			public void onSuccess(Map<String, String> out) {
				
				TreeMap<String, String> results = sortByKeys(out);
				
				for (int i = xDimensions.getItemCount() - 1; i >= 0; i--)
					xDimensions.removeItem(i);
				if (values != null)
					xDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						xDimensions.addItem(dimension, results.get(dimension));

				for (int i = yDimensions.getItemCount() - 1; i >= 0; i--)
					yDimensions.removeItem(i);
				if (values != null)
					yDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						yDimensions.addItem(dimension, results.get(dimension));

				for (int i = zDimensions.getItemCount() - 1; i >= 0; i--)
					zDimensions.removeItem(i);
				if (values != null)
					zDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						zDimensions.addItem(dimension, results.get(dimension));

				for (int i = wDimensions.getItemCount() - 1; i >= 0; i--)
					wDimensions.removeItem(i);
				if (values != null)
					wDimensions.addItem(BabelFish.print().pleaseSelectADimension());
				for (String dimension : results.keySet())
					if (!results.get(dimension).equals("quantity"))
						wDimensions.addItem(dimension, results.get(dimension));

				if (values != null) {
					for (int i = values.getItemCount() - 1; i >= 0; i--)
						values.removeItem(i);
					for (String dimension : results.keySet())
						if (results.get(dimension).equals("quantity"))
							values.addItem(dimension, results.get(dimension));
				}
				
				if (w != null && p != null) {
					MTController.loadUserSettings(w, p);
				}
				
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
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
							FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
							loading.destroyLoadingBox();
						}
					});
				}
			}
		};
	}
	
	private static Listener<BaseEvent> aggregateAsMonthly(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel, final ListBox dates, final CheckBox aggregateMonthly, final CheckBox aggregateYearly) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				aggregateAsMonthlyAgent(dataSource, dimensions, axisPanel, dates, aggregateMonthly, aggregateYearly);
			}
		};
	}
	
	public static void aggregateAsMonthlyAgent(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel, final ListBox dates, final CheckBox aggregateMonthly, final CheckBox aggregateYearly) {
		if (aggregateMonthly.getValue() == true) {
			aggregateMonthly.setEnabled(true);
			aggregateYearly.setEnabled(false);
			List<String> dateStrings = new ArrayList<String>();
			for (int i = 0 ; i < dates.getItemCount() ; i++) 
				dateStrings.add(dates.getItemText(i));
			Collections.sort(dateStrings, new FieldParserComparator());
			List<String> months = new ArrayList<String>();
			for (String d : dateStrings) {
				String month = d.substring(5, 7);
				String year = d.substring(0, 4);
				String tmp = month + "-" + year;
				if (!months.contains(tmp))
					months.add(tmp);
			}
			for (int i = dates.getItemCount() - 1 ; i >= 0 ; i--)
				dates.removeItem(i);
			for (String month : months) 
				dates.addItem(month, month);
		} else {
			aggregateYearly.setEnabled(true);
//			dimensionsChangeWithLabelsAgent(dataSource, dimensions, axisPanel);
		}
	}
	
	private static Listener<BaseEvent> aggregateAsYearly(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel, final ListBox dates, final CheckBox aggregateYearly, final CheckBox aggregateMonthly) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				aggregateAsYearlyAgent(dataSource, dimensions, axisPanel, dates, aggregateYearly, aggregateMonthly);
			}
		};
	}
	
	private static void aggregateAsYearlyAgent(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel, final ListBox dates, final CheckBox aggregateYearly, final CheckBox aggregateMonthly) {
		if (aggregateYearly.getValue() == true) {
			aggregateMonthly.setEnabled(false);
			List<String> dateStrings = new ArrayList<String>();
			for (int i = 0 ; i < dates.getItemCount() ; i++) 
				dateStrings.add(dates.getItemText(i));
			Collections.sort(dateStrings, new FieldParserComparator());
			List<String> months = new ArrayList<String>();
			for (String d : dateStrings) {
				String year = d.substring(0, 4);
				String tmp = year;
				if (!months.contains(tmp))
					months.add(tmp);
			}
			for (int i = dates.getItemCount() - 1 ; i >= 0 ; i--)
				dates.removeItem(i);
			for (String month : months)
				dates.addItem(month, month);
		} else {
//			dimensionsChangeWithLabelsAgent(dataSource, dimensions, axisPanel);
			aggregateMonthly.setEnabled(true);
		}
	}
	
	public static SelectionListener<ButtonEvent> buildFromDateToDatePanel(final AxisPanel axisPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				for (int i = axisPanel.getAxisPanel().getItemCount() - 1 ; i >= 0 ; i--)
					axisPanel.getAxisPanel().remove(axisPanel.getAxisPanel().getWidget(i));
				Date toDate = FieldParser.parseStandardWorkstationFormat(axisPanel.getValues().getValue(0)); // 0 should be the most recent data
				Date fromDate = FieldParser.parseStandardWorkstationFormat(axisPanel.getValues().getValue(axisPanel.getValues().getItemCount() - 1)); // latest entry should be the oldest data
				axisPanel.getAxisPanel().add(axisPanel.buildFromDateToDatePanel(fromDate, toDate));
				axisPanel.getAxisPanel().getLayout().layout();
				axisPanel.setUseFromDateToDate(true);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> buildMostRecentDataPanel(final AxisPanel axisPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				for (int i = axisPanel.getAxisPanel().getItemCount() - 1 ; i >= 0 ; i--)
					axisPanel.getAxisPanel().remove(axisPanel.getAxisPanel().getWidget(i));
				axisPanel.getAxisPanel().add(axisPanel.buildMostRecentDataPanel());
				axisPanel.getAxisPanel().getLayout().layout();
				axisPanel.setUseMostRecentData(true);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> buildStandardViewPanel(final AxisPanel axisPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				buildStandardViewPanelAgent(axisPanel);
			}
		};
	}
	
	public static void buildStandardViewPanelAgent(final AxisPanel axisPanel) {
		for (int i = axisPanel.getAxisPanel().getItemCount() - 1 ; i >= 0 ; i--)
			axisPanel.getAxisPanel().remove(axisPanel.getAxisPanel().getWidget(i));
		axisPanel.getAxisPanel().add(axisPanel.buildSelectorsPanel(false));
		axisPanel.getAxisPanel().getLayout().layout();
		axisPanel.setUseMostRecentData(false);
		axisPanel.setUseFromDateToDate(false);
	}
	
	public static void dimensionsChangeWithLabelsAgent(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel) {
		
		final ListBox values = axisPanel.getValues();
		
		if (dimensions.getSelectedIndex() > 0) {
			
			final String header = dimensions.getItemText(dimensions.getSelectedIndex());
			final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
			final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
			final String lang = CheckLanguage.getLanguage();
			
			final String contentDescriptor = dimensions.getValue(dimensions.getSelectedIndex());
			if ((contentDescriptor.contains("date")) || (contentDescriptor.contains("Date"))) {
				axisPanel.getAggregateAsMonthly().setEnabled(true);
				axisPanel.getAggregateAsMonthly().addListener(Events.OnClick, aggregateAsMonthly(dataSource, dimensions, axisPanel, axisPanel.getValues(), axisPanel.getAggregateAsMonthly(), axisPanel.getAggregateAsYearly()));
				axisPanel.getAggregateAsYearly().setEnabled(true);
				axisPanel.getAggregateAsYearly().addListener(Events.OnClick, aggregateAsYearly(dataSource, dimensions, axisPanel, axisPanel.getValues(), axisPanel.getAggregateAsYearly(), axisPanel.getAggregateAsMonthly()));
				axisPanel.getFromDateToDateViewButton().setEnabled(true);
				axisPanel.getFromDateToDateViewButton().addSelectionListener(buildFromDateToDatePanel(axisPanel));
				axisPanel.getMostRecentDataViewButton().setEnabled(true);
				axisPanel.getMostRecentDataViewButton().addSelectionListener(buildMostRecentDataPanel(axisPanel));
			} else {
				axisPanel.getAggregateAsMonthly().setEnabled(false);
				axisPanel.getAggregateAsYearly().setEnabled(false);
				axisPanel.getFromDateToDateViewButton().setEnabled(false);
				axisPanel.getMostRecentDataViewButton().setEnabled(false);
				buildStandardViewPanelAgent(axisPanel);
			}
			
			OlapServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<List<UniqueValueVO>>() {
				
				public void onSuccess(List<UniqueValueVO> vos) {
					
					loading.destroyLoadingBox();
					
					if (containsDate(vos))
						Collections.sort(vos, new UniqueValueVODateComparator());
					else
						Collections.sort(vos, new UniqueValueVOLabelComparator());
					
					// remove old values
					for (int i = values.getItemCount() - 1; i >= 0; i--)
						values.removeItem(i);
					
					// fill with actual dimension values
					for (UniqueValueVO vo : vos) {
						if ((contentDescriptor.contains("date")) || (contentDescriptor.contains("Date"))) {
							String dateLabel = OlapDateFormatter.format(vo);
							values.addItem(dateLabel, vo.getCode());
						} else {
							values.addItem(vo.getLabel(), vo.getCode());
						}
					}
					
					// select all
					for (int i = 0 ; i < values.getItemCount() ; i++)
						values.setItemSelected(i, true);
															
					loading.destroyLoadingBox();
				}
				
				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					loading.destroyLoadingBox();
				}
			});
			
		}
	}
	
	public static void dimensionsChangeWithLabelsAgent(final ListBox dataSource, final ListBox dimensions, final AxisPanel axisPanel, final List<UniqueValueVO> uvos) {
		ListBox values = axisPanel.getValues();
		dimensionsChangeWithLabelsAgent(dataSource, dimensions, values, uvos);
	}
	
	public static void dimensionsChangeWithLabelsAgent(final ListBox dataSource, final ListBox dimensions, final ListBox values, final List<UniqueValueVO> uvos) {
		
		if (dimensions.getSelectedIndex() > 0) {
			
			final String header = dimensions.getItemText(dimensions.getSelectedIndex());
			final String dataType = dimensions.getValue(dimensions.getSelectedIndex());
			final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
			final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
			final String lang = CheckLanguage.getLanguage();
			
			OlapServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<List<UniqueValueVO>>() {
				
				public void onSuccess(List<UniqueValueVO> vos) {
					
					loading.destroyLoadingBox();
					
					if (containsDate(vos))
						Collections.sort(vos, new UniqueValueVODateComparator());
					else
						Collections.sort(vos, new UniqueValueVOLabelComparator());
					
					// remove old values
					for (int i = values.getItemCount() - 1; i >= 0; i--)
						values.removeItem(i);
					
					// fill with actual dimension values
					int i = 0;
					for (UniqueValueVO vo : vos)  {
						values.addItem(vo.getLabel(), vo.getCode());
						if (containsUniqueValue(uvos, vo.getLabel(), vo.getCode(), header, dataType)) 
							values.setItemSelected(i, true);
						i++;
					}
					
					loading.destroyLoadingBox();
				}
				
				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					loading.destroyLoadingBox();
				}
			});
			
		}
	}
	
	/**
	 * My apologies for this salvatore-style method. 
	 * Problem is I have to do everything inside the onSuccess method to restore the user's settings and I can do it only after the lists have been populated. 
	 * Anyway, most of the lines of code are switch and things like that, so it's very long but there is nothing too complicate, it's just a matter of... style! 
	 * Gui
	 */
	public static void dimensionsChangeWithLabelsAgent(final ListBox dataSource, final List<UniqueValueVO> uvos, final AxisPanel axisPanel, final ResourceViewVO rvo, final String axis) {
		
		if (axisPanel.getDimensions().getSelectedIndex() > 0) {
			
			final String header = axisPanel.getDimensions().getItemText(axisPanel.getDimensions().getSelectedIndex());
			final String dataType = axisPanel.getDimensions().getValue(axisPanel.getDimensions().getSelectedIndex());
			final String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
			final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
			final String lang = CheckLanguage.getLanguage();
			
			OlapServiceEntry.getInstance().getDimensionValues(Long.parseLong(datasetId), header, lang, new AsyncCallback<List<UniqueValueVO>>() {
				
				public void onSuccess(List<UniqueValueVO> vos) {
					
					loading.destroyLoadingBox();
					
					if (containsDate(vos))
						Collections.sort(vos, new UniqueValueVODateComparator());
					else
						Collections.sort(vos, new UniqueValueVOLabelComparator());
					
					// remove old values
					for (int i = axisPanel.getValues().getItemCount() - 1; i >= 0; i--)
						axisPanel.getValues().removeItem(i);
					
					// fill with actual dimension values
					int i = 0;
					for (UniqueValueVO vo : vos)  {
						if ((dataType.contains("date")) || (dataType.contains("Date"))) {
							String dateLabel = OlapDateFormatter.format(vo);
							axisPanel.getValues().addItem(dateLabel, vo.getCode());
						} else {
							axisPanel.getValues().addItem(vo.getLabel(), vo.getCode());
						}
						if (containsUniqueValue(uvos, vo.getLabel(), vo.getCode(), header, dataType)) {
							axisPanel.getValues().setItemSelected(i, true);
						}
						i++;
					}
					
					if (axisPanel.isUseFromDateToDate()) {
						axisPanel.getFromDateToDateViewButton().setEnabled(true);
						axisPanel.getMostRecentDataViewButton().setEnabled(true);
						for (int G = axisPanel.getAxisPanel().getItemCount() - 1 ; G >= 0 ; G--)
							axisPanel.getAxisPanel().remove(axisPanel.getAxisPanel().getWidget(G));
						axisPanel.getAxisPanel().add(axisPanel.buildFromDateToDatePanel(null, null));
						axisPanel.getAxisPanel().getLayout().layout();
					}
					if (axisPanel.isUseMostRecentData()) {
						axisPanel.getFromDateToDateViewButton().setEnabled(true);
						axisPanel.getMostRecentDataViewButton().setEnabled(true);
						for (int G = axisPanel.getAxisPanel().getItemCount() - 1 ; G >= 0 ; G--)
							axisPanel.getAxisPanel().remove(axisPanel.getAxisPanel().getWidget(G));
						axisPanel.getAxisPanel().add(axisPanel.buildMostRecentDataPanel());
						axisPanel.getAxisPanel().getLayout().layout();
					}
					
					aggregateAsMonthlyAgent(dataSource, axisPanel.getDimensions(), axisPanel, axisPanel.getValues(), axisPanel.getAggregateAsMonthly(), axisPanel.getAggregateAsYearly());
					aggregateAsYearlyAgent(dataSource, axisPanel.getDimensions(), axisPanel, axisPanel.getValues(), axisPanel.getAggregateAsYearly(), axisPanel.getAggregateAsMonthly());
					
					if (axisPanel.isUseFromDateToDate()) {
						if (axis.equalsIgnoreCase("X")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case X_FROM_DATE:
										axisPanel.getFromDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case X_TO_DATE:
										axisPanel.getToDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case AGGREGATE_X_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_X_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("Z")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case Z_FROM_DATE:
										axisPanel.getFromDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case Z_TO_DATE:
										axisPanel.getToDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case AGGREGATE_Z_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_Z_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("Y")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case Y_FROM_DATE:
										axisPanel.getFromDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case Y_TO_DATE:
										axisPanel.getToDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case AGGREGATE_Y_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_Y_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("W")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case W_FROM_DATE:
										axisPanel.getFromDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case W_TO_DATE:
										axisPanel.getToDateField().setValue(FieldParser.parseDate(svo.getValue()));
									break;
									case AGGREGATE_W_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_W_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
					}
					
					if (axisPanel.isUseMostRecentData()) {
						if (axis.equalsIgnoreCase("X")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case X_LATEST_YEARS:
										for (int C = 0 ; C < axisPanel.getLatestYearList().getItemCount() ; C++) {
											if (axisPanel.getLatestYearList().getValue(C).equalsIgnoreCase(svo.getValue())) { 
												axisPanel.getLatestYearList().setItemSelected(C, true);
												break;
											}
										}
									break;
									case X_LATEST_MONTHS:
										for (int C = 0 ; C < axisPanel.getLatestMonthList().getItemCount() ; C++) {
											if (axisPanel.getLatestMonthList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestMonthList().setItemSelected(C, true);
										}
									break;
									case X_LATEST_DAYS:
										for (int C = 0 ; C < axisPanel.getLatestDayList().getItemCount() ; C++) {
											if (axisPanel.getLatestDayList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestDayList().setItemSelected(C, true);
										}
									break;
									case AGGREGATE_X_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_X_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("Z")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case Z_LATEST_YEARS:
										for (int C = 0 ; C < axisPanel.getLatestYearList().getItemCount() ; C++) {
											if (axisPanel.getLatestYearList().getValue(C).equalsIgnoreCase(svo.getValue())) { 
												axisPanel.getLatestYearList().setItemSelected(C, true);
												break;
											}
										}
									break;
									case Z_LATEST_MONTHS:
										for (int C = 0 ; C < axisPanel.getLatestMonthList().getItemCount() ; C++) {
											if (axisPanel.getLatestMonthList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestMonthList().setItemSelected(C, true);
										}
									break;
									case Z_LATEST_DAYS:
										for (int C = 0 ; C < axisPanel.getLatestDayList().getItemCount() ; C++) {
											if (axisPanel.getLatestDayList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestDayList().setItemSelected(C, true);
										}
									break;
									case AGGREGATE_Z_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_Z_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("Y")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case Y_LATEST_YEARS:
										for (int C = 0 ; C < axisPanel.getLatestYearList().getItemCount() ; C++) {
											if (axisPanel.getLatestYearList().getValue(C).equalsIgnoreCase(svo.getValue())) { 
												axisPanel.getLatestYearList().setItemSelected(C, true);
												break;
											}
										}
									break;
									case Y_LATEST_MONTHS:
										for (int C = 0 ; C < axisPanel.getLatestMonthList().getItemCount() ; C++) {
											if (axisPanel.getLatestMonthList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestMonthList().setItemSelected(C, true);
										}
									break;
									case Y_LATEST_DAYS:
										for (int C = 0 ; C < axisPanel.getLatestDayList().getItemCount() ; C++) {
											if (axisPanel.getLatestDayList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestDayList().setItemSelected(C, true);
										}
									break;
									case AGGREGATE_Y_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_Y_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
						if (axis.equalsIgnoreCase("W")) {
							for (ResourceViewSettingVO svo : rvo.getSettings()) {
								ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
								switch (s) {
									case W_LATEST_YEARS:
										for (int C = 0 ; C < axisPanel.getLatestYearList().getItemCount() ; C++) {
											if (axisPanel.getLatestYearList().getValue(C).equalsIgnoreCase(svo.getValue())) { 
												axisPanel.getLatestYearList().setItemSelected(C, true);
												break;
											}
										}
									break;
									case W_LATEST_MONTHS:
										for (int C = 0 ; C < axisPanel.getLatestMonthList().getItemCount() ; C++) {
											if (axisPanel.getLatestMonthList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestMonthList().setItemSelected(C, true);
										}
									break;
									case W_LATEST_DAYS:
										for (int C = 0 ; C < axisPanel.getLatestDayList().getItemCount() ; C++) {
											if (axisPanel.getLatestDayList().getValue(C).equalsIgnoreCase(svo.getValue())) 
												axisPanel.getLatestDayList().setItemSelected(C, true);
										}
									break;
									case AGGREGATE_W_MONTHLY:
										axisPanel.getAggregateAsMonthly().setValue(Boolean.valueOf(svo.getValue()));
									break;
									case AGGREGATE_W_YEARLY:
										axisPanel.getAggregateAsYearly().setValue(Boolean.valueOf(svo.getValue()));
									break;
								}
							}
						}
					}
					
					loading.destroyLoadingBox();
				}
				
				public void onFailure(Throwable e) {
					loading.destroyLoadingBox();
					FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					loading.destroyLoadingBox();
				}
			});
			
		}
	}
	
	public static boolean containsDate(List<UniqueValueVO> vos) {
		if (!vos.isEmpty()) {
			UniqueValueVO vo = vos.get(0);
			if ((vo.getDatatype().contains("date")) || (vo.getDatatype().contains("Date")))
					return true;
		}
		return false;
	}
	
	private static boolean containsUniqueValue(List<UniqueValueVO> vos, String label, String code, String header, String dataType) {
		for (UniqueValueVO vo : vos) {
			boolean h = vo.getHeader().equalsIgnoreCase(header);
			boolean t = vo.getDatatype().equalsIgnoreCase(dataType);
			boolean c = vo.getCode().equalsIgnoreCase(code);
			boolean l = vo.getLabel().equalsIgnoreCase(label);
			if (h && t && c && l) 
				return true;
		}
		return false;
	}
	
	public static ChangeHandler valuesListChangeHandler(final ListBox valuesList, final AxisPanel axisPanel) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				valuesListChangeHandlerAgent(valuesList, axisPanel);
			}
		};
	}
	
	public static void valuesListChangeHandlerAgent(final ListBox valuesList, final AxisPanel axisPanel) {
		ListBox dimensionList = axisPanel.getDimensions();
		String columnHeader = dimensionList.getItemText(dimensionList.getSelectedIndex());
		Map<String, String> codeLabelMap = new HashMap<String, String>();
		for (int i = 0 ; i < valuesList.getItemCount() ; i++)
			if (valuesList.isItemSelected(i))
				codeLabelMap.put(valuesList.getValue(i), valuesList.getItemText(i));
		axisPanel.getUniqueValuesMap().put(columnHeader, codeLabelMap);
	}

	public static ChangeListener dimensionsChangeListenerWithLabels(final ListBox dataSource, final AxisPanel axisPanel) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				final ListBox dimensions = (ListBox) sender;
				dimensionsChangeWithLabelsAgent(dataSource, dimensions, axisPanel);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addToReport(final ReportViewer reportViewer, final OlapWindow olapWindow) {
		
		return new SelectionListener<ButtonEvent>() {
			
			public void componentSelected(ButtonEvent ce) {
				
				ListBox datasource = olapWindow.getTabPanel().getSingleDatasetPanel().getDataSource();
				final String datasetId = datasource.getValue(datasource.getSelectedIndex());
				final String datasetTitle = datasource.getItemText(datasource.getSelectedIndex());
				final String datasetCode = datasetId + String.valueOf((int)(Math.random() * 100));
				
				BirtServiceEntry.getInstance().addTableToReport(datasetCode, datasetId, reportViewer.getRptDesign(), reportViewer.getObjectPosition(), reportViewer.getKeyTemplate(), olapWindow.getHtml().toString(), new AsyncCallback<String>(){
					
					public void onSuccess(String s) {
				
						// TODO add table ID and Name to the side bar
						reportViewer.getSideBar().addElementToListResource(datasetCode, datasetTitle, "table", reportViewer.getObjectPosition());
						
						reportViewer.getReport().setHTML(s);
						olapWindow.getWindow().close();
					}
					
					public void onFailure(Throwable caught) {
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
					}
					
				});
				
			}
		};
	}
	
	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
	public static void showWidthsAndHeightsAgent(OlapWindow w, boolean showWidthsAndHeights) {
		w.getOlapRuler().showWidthsAndHeights(showWidthsAndHeights, w);
	}

}