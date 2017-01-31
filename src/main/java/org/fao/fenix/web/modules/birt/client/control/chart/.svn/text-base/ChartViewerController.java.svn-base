package org.fao.fenix.web.modules.birt.client.control.chart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.birt.client.utils.Counter;
import org.fao.fenix.web.modules.birt.client.utils.DateUtils;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.format.FormatChart;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.DateVo;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChartViewerController {

	public static SelectionListener<IconButtonEvent> showHideParameters(final ChartViewer chartViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				// datasets
				List<List<String>> datasets = new ArrayList<List<String>>();
				List<String> listTemp;
				for (int i = 0; i < chartViewer.getListCheckBoxDataset().size(); i++) {
					if (chartViewer.getListCheckBoxDataset().get(i).getValue()) {
						listTemp = new ArrayList<String>();
						listTemp.add(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(i)).get(0));
						listTemp.add(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(i)).get(1));
						datasets.add(listTemp);
					}
				}

				// Other Dimensions
				final Map<String, List<List<String>>> otherDimension = new HashMap<String, List<List<String>>>();
				VerticalPanel contOtherDim;
				List<List<String>> otherDim;
				List<String> element;
				for (TabItem tab : ((TabPanel) chartViewer.getSideBar().getVpOtherDim().getWidget(0)).getItems()) {
					contOtherDim = (VerticalPanel) tab.getWidget(0);
					otherDim = new ArrayList<List<String>>();
					for (int i = 1; i < contOtherDim.getWidgetCount(); i++) {
						if ( ((CheckBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(2)).getValue()){
							element = new ArrayList<String>();
							element.add(((HTML) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(0)).getHTML());
							element.add(((ListBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getValue(((ListBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getSelectedIndex()));
							otherDim.add(element);
						}
					}

					otherDimension.put(tab.getItemId(), otherDim);

				}

				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().showHideParametersToChart(chartViewer.getRptdesign(), otherDimension, datasets, new AsyncCallback<String>() {

					public void onSuccess(String result) {

						chartViewer.getChart().setHTML(result);
						loading.destroy();
					}

					public void onFailure(Throwable caught) {
						Info.display("showHideParametersToChart", caught.getLocalizedMessage());
						loading.destroy();
					}

				});

			}
		};
	}

	public static SelectionListener<IconButtonEvent> formatChart(final ChartViewer chartViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				new FormatChart(chartViewer);

			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> formatChart(final String rptDesign, final String chartType, final HTML content) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				new FormatChart(rptDesign, chartType, content);
			}
		};
	}
	
	private static String convertToHexToDec(String originalFormat) {

		String r = String.valueOf(Integer.parseInt(originalFormat.substring(1, 3), 16));
		String g = String.valueOf(Integer.parseInt(originalFormat.substring(3, 5), 16));
		String b = String.valueOf(Integer.parseInt(originalFormat.substring(5, 7), 16));

		return r + "_" + g + "_" + b;
	}

	private static void updateFormatChartVo(String chartType, FormatChart formatChart, ChartWizardBean chartWizardBean){
		
		FormatChartVo formatChartVo = formatChart.getFormatChartVo();

		// colors
		String colorRGB = DOM.getStyleAttribute(formatChart.getTabChartArea().getPlotAreaColor().getElement(), "backgroundColor");
		if (!colorRGB.equals("")) {
			formatChartVo.setPlotAreaColor(ColorPicker.getColorDec(colorRGB));
		}
		else {
			formatChartVo.setPlotAreaColor(convertToHexToDec(formatChartVo.getPlotAreaColor()));
		}

		colorRGB = DOM.getStyleAttribute(formatChart.getTabChartArea().getChartAreaColor().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setChartAreaColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setChartAreaColor(convertToHexToDec(formatChartVo.getChartAreaColor()));

		if (!chartType.equals("Line") && !chartType.equals("Scatter")) {
			formatChartVo.setDimension(formatChart.getTabChartArea().getDimension().getValue(formatChart.getTabChartArea().getDimension().getSelectedIndex()));
		}

		if (!chartType.equals("Line") && !chartType.equals("Area") && !chartType.equals("Pie") && !chartType.equals("Scatter")) {
			formatChartVo.setDisposition(formatChart.getTabLegSer().getDisposition().getValue(formatChart.getTabLegSer().getDisposition().getSelectedIndex()));
		}

		// title
		formatChartVo.setTitle(formatChart.getTabTitle().getTextTitle().getValue());
		formatChartVo.setSizeTitle(Integer.valueOf(formatChart.getTabTitle().getSizeTitle().getItemText(formatChart.getTabTitle().getSizeTitle().getSelectedIndex())).intValue());
		formatChartVo.setTitleVisible(formatChart.getTabTitle().getVisibleTitle().isChecked());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabTitle().getColorTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setColorTitle(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setColorTitle(convertToHexToDec(formatChartVo.getColorTitle()));
		
		// title style
		formatChartVo.setTitleIsBold(formatChart.getTabTitle().getStylePanel().getBold().getValue());
		formatChartVo.setTitleIsItalic(formatChart.getTabTitle().getStylePanel().getItalic().getValue());
		formatChartVo.setTitleIsUnderline(formatChart.getTabTitle().getStylePanel().getUnderline().getValue());
		System.out.println("TITLE FONT TYPE: "+ formatChart.getTabTitle().getStylePanel().getFontType().getValue(formatChart.getTabTitle().getStylePanel().getFontType().getSelectedIndex()));
		formatChartVo.setxTitleFont(formatChart.getTabTitle().getStylePanel().getFontType().getValue(formatChart.getTabTitle().getStylePanel().getFontType().getSelectedIndex()));
		
		

		// X Axis Format
		formatChartVo.setxAxixRotation(formatChart.getTabAxes().getRotateXLabels().getValue());
		
		formatChartVo.setxAxisLabelIsBold(formatChart.getTabAxes().getxAxisLabelStylePanel().getBold().getValue());
		formatChartVo.setxAxisLabelIsItalic(formatChart.getTabAxes().getxAxisLabelStylePanel().getItalic().getValue());
		formatChartVo.setxAxisLabelIsUnderline(formatChart.getTabAxes().getxAxisLabelStylePanel().getUnderline().getValue());
		formatChartVo.setxAxisTitleFont(formatChart.getTabAxes().getxAxisLabelStylePanel().getFontType().getValue(formatChart.getTabAxes().getxAxisLabelStylePanel().getFontType().getSelectedIndex()));

		
		// style
		formatChartVo.setxAxisIsBold(formatChart.getTabAxes().getxAxisStylePanel().getBold().getValue());
		formatChartVo.setxAxisIsItalic(formatChart.getTabAxes().getxAxisStylePanel().getItalic().getValue());
		formatChartVo.setxAxisIsUnderline(formatChart.getTabAxes().getxAxisStylePanel().getUnderline().getValue());
		formatChartVo.setxLabelFont(formatChart.getTabAxes().getxAxisStylePanel().getFontType().getValue(formatChart.getTabAxes().getxAxisStylePanel().getFontType().getSelectedIndex()));
		
		System.out.println("update SETTINGS getxAxisLabelIsBold(): " + formatChartVo.getxAxisLabelIsBold());
		System.out.println("update SETTINGS getxAxisLabelIsItalic(): " + formatChartVo.getxAxisLabelIsItalic());
		System.out.println("update SETTINGS getxAxisLabelIsUnderline(): " + formatChartVo.getxAxisLabelIsUnderline());
		System.out.println("update X Axis TITLE FONT TYPE: "+ formatChartVo.getxAxisTitleFont());

		
		System.out.println("update SETTINGS getxAxisIsBold(): " + formatChartVo.getxAxisIsBold());
		System.out.println("update SETTINGS getxAxisIsItalic(): " + formatChartVo.getxAxisIsItalic());
		System.out.println("update SETTINGS getxAxisIsUnderline(): " + formatChartVo.getxAxisIsUnderline());
		System.out.println("update X Axis FONT TYPE: "+ formatChartVo.getxLabelFont());

		formatChartVo.setXAxisTitle(formatChart.getTabAxes().getTextXAxisTitle().getValue());
		formatChartVo.setXAxisVisible(formatChart.getTabAxes().getVisibleXAxisTitle().isChecked());
		formatChartVo.setXGrid(formatChart.getTabAxes().getXGrid().isChecked());
		formatChartVo.setSizeXAxis(Integer.valueOf(formatChart.getTabAxes().getSizeXAxisTitle().getItemText(formatChart.getTabAxes().getSizeXAxisTitle().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorXAxisTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setXAxisTitleColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setXAxisTitleColor(convertToHexToDec(formatChartVo.getXAxisTitleColor()));

		formatChartVo.setXAxisLabelVisible(formatChart.getTabAxes().getVisibleXAxisLabel().isChecked());
		formatChartVo.setSizeXAxisLabel(Integer.valueOf(formatChart.getTabAxes().getSizeXAxisLabel().getItemText(formatChart.getTabAxes().getSizeXAxisLabel().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorXAxisLabel().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setXAxisLabelColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setXAxisLabelColor(convertToHexToDec(formatChartVo.getXAxisLabelColor()));

		// Y Axis
		
		// style title
		formatChartVo.setyTitleLabelIsBold(formatChart.getTabAxes().getyAxisTitleStylePanel().getBold().getValue());
		formatChartVo.setyTitleIsItalic(formatChart.getTabAxes().getyAxisTitleStylePanel().getItalic().getValue());
		formatChartVo.setyTitleIsUnderline(formatChart.getTabAxes().getyAxisTitleStylePanel().getUnderline().getValue());
		formatChartVo.setyTitleFont(formatChart.getTabAxes().getyAxisTitleStylePanel().getFontType().getValue(formatChart.getTabAxes().getyAxisTitleStylePanel().getFontType().getSelectedIndex()));

		
		System.out.println("update SETTINGS getyTitleLabelIsBold(): " + formatChartVo.getyTitleLabelIsBold());
		System.out.println("update SETTINGS getyTitleIsItalic(): " + formatChartVo.getyTitleIsItalic());
		System.out.println("update SETTINGS getyTitleIsUnderline(): " + formatChartVo.getyTitleIsUnderline());
		System.out.println("update Y TITLE FONT TYPE: "+ formatChartVo.getyTitleFont());

		
		formatChartVo.setYAxisTitle(formatChart.getTabAxes().getTextYAxisTitle().getValue());
		formatChartVo.setYAxisVisible(formatChart.getTabAxes().getVisibleYAxisTitle().isChecked());
		formatChartVo.setSizeYAxis(Integer.valueOf(formatChart.getTabAxes().getSizeYAxisTitle().getItemText(formatChart.getTabAxes().getSizeYAxisTitle().getSelectedIndex())).intValue());
		formatChartVo.setYGrid(formatChart.getTabAxes().getYGrid().isChecked());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorYAxisTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setYAxisTitleColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setYAxisTitleColor(convertToHexToDec(formatChartVo.getYAxisTitleColor()));

		
		// y axis title
		
		
		formatChartVo.setyAxisLabelIsBold(formatChart.getTabAxes().getyAxisStylePanel().getBold().getValue());
		formatChartVo.setyAxisLabelIsItalic(formatChart.getTabAxes().getyAxisStylePanel().getItalic().getValue());
		formatChartVo.setyAxisLabelIsUnderline(formatChart.getTabAxes().getyAxisStylePanel().getUnderline().getValue());
		formatChartVo.setyLabelFont(formatChart.getTabAxes().getyAxisStylePanel().getFontType().getValue(formatChart.getTabAxes().getyAxisStylePanel().getFontType().getSelectedIndex()));

		
		System.out.println("update SETTINGS getyAxisLabelIsBold(): " + formatChartVo.getyAxisLabelIsBold());
		System.out.println("update SETTINGS getyAxisLabelIsItalic(): " + formatChartVo.getyAxisLabelIsItalic());
		System.out.println("update SETTINGS getyAxisLabelIsUnderline(): " + formatChartVo.getyAxisLabelIsUnderline());
		System.out.println("update SETTINGS setyLabelFont(): " + formatChartVo.getyLabelFont());

		
		formatChartVo.setYAxisLabelVisible(formatChart.getTabAxes().getVisibleYAxisLabel().isChecked());
		formatChartVo.setSizeYAxisLabel(Integer.valueOf(formatChart.getTabAxes().getSizeYAxisLabel().getItemText(formatChart.getTabAxes().getSizeYAxisLabel().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorYAxisLabel().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setYAxisLabelColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setYAxisLabelColor(convertToHexToDec(formatChartVo.getYAxisLabelColor()));

		// Y Axis (Second Scale)
		if (formatChartVo.isSecondScaleIsThere()) {
			formatChartVo.setYAxisTitleScale2(formatChart.getTabAxes().getTextY2AxisTitle().getValue());
			formatChartVo.setYAxisVisibleScale2(formatChart.getTabAxes().getVisibleY2AxisTitle().isChecked());
			formatChartVo.setSizeYAxisScale2(Integer.valueOf(formatChart.getTabAxes().getSizeY2AxisTitle().getItemText(formatChart.getTabAxes().getSizeY2AxisTitle().getSelectedIndex())).intValue());
			colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorY2AxisTitle().getElement(), "backgroundColor");
			if (!colorRGB.equals(""))
				formatChartVo.setYAxisTitleColorScale2(ColorPicker.getColorDec(colorRGB));
			else
				formatChartVo.setYAxisTitleColorScale2(convertToHexToDec(formatChartVo.getYAxisTitleColorScale2()));

			formatChartVo.setYAxisLabelVisibleScale2(formatChart.getTabAxes().getVisibleY2AxisLabel().isChecked());
			formatChartVo.setSizeYAxisLabelScale2(Integer.valueOf(formatChart.getTabAxes().getSizeY2AxisLabel().getItemText(formatChart.getTabAxes().getSizeY2AxisLabel().getSelectedIndex())).intValue());
			colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorY2AxisLabel().getElement(), "backgroundColor");
			if (!colorRGB.equals(""))
				formatChartVo.setYAxisLabelColorScale2(ColorPicker.getColorDec(colorRGB));
			else
				formatChartVo.setYAxisLabelColorScale2(convertToHexToDec(formatChartVo.getYAxisLabelColorScale2()));
		}

		// First Scale
		formatChartVo.setAutoScale(formatChart.getTabAxes().getAutoStep().isChecked());
		formatChartVo.setStepNumber(formatChart.getTabAxes().getStepNumber().getValue());
		formatChartVo.setMin(formatChart.getTabAxes().getMinScale().getValue());
		formatChartVo.setMax(formatChart.getTabAxes().getMaxScale().getValue());
		formatChartVo.setFractionDigits(formatChart.getTabAxes().getFractionDigits().getValue());

		// Second Scale
		if (formatChartVo.isSecondScaleIsThere()) {
			formatChartVo.setAutoScaleScale2(formatChart.getTabAxes().getAutoStep2().isChecked());
			formatChartVo.setStepNumberScale2(formatChart.getTabAxes().getStepNumber2().getValue());
			formatChartVo.setMinScale2(formatChart.getTabAxes().getMinScale2().getValue());
			formatChartVo.setMaxScale2(formatChart.getTabAxes().getMaxScale2().getValue());
			formatChartVo.setFractionDigitsScale2(formatChart.getTabAxes().getFractionDigits2().getValue());
		}

		// Legend and Series
		formatChartVo.setVisible(formatChart.getTabLegSer().getChBoxLabel().isChecked());
		formatChartVo.setSizeLabel(Integer.valueOf(formatChart.getTabLegSer().getSizeLabel().getItemText(formatChart.getTabLegSer().getSizeLabel().getSelectedIndex())).intValue());
		formatChartVo.setPosition(formatChart.getTabLegSer().getPosition().getItemText(formatChart.getTabLegSer().getPosition().getSelectedIndex()));
		if (!chartType.equals("Line") && !chartType.equals("Area") && !chartType.equals("Pie") && !chartType.equals("Scatter")) {
			formatChartVo.setUnitSpacing(formatChart.getTabLegSer().getUnitSpace().getValue());
		}

		if (!chartType.equals("Pie")) {
			formatChartVo.setFlip(formatChart.getTabAxes().getFlip().isChecked());
			formatChartVo.setShowSeriesValues(formatChart.getTabAxes().getShowValue().isChecked());
		}

		HorizontalPanel hr;
		IconButton b;
		formatChartVo.getColorAndLabelFromClientToServer().clear();
		List<String> element;
		int numWidgets = formatChart.getTabLegSer().getColorCont().getWidgetCount();
		for (int i = 0; i < numWidgets; i++) {
			element = new ArrayList<String>();
			hr = (HorizontalPanel) formatChart.getTabLegSer().getColorCont().getWidget(i);
			element.add(((TextField<String>) hr.getWidget(0)).getValue());
			b = (IconButton) hr.getWidget(1);
			element.add(ColorPicker.getColorDec(DOM.getStyleAttribute(b.getElement(), "backgroundColor")));
			formatChartVo.addSetColorAndLabelFromClientToServer(element);
		}
		
		
		/// updatating the chartWizardBean (the style and the rotation)
		chartWizardBean.setTitleIsBold(formatChart.getTabTitle().getStylePanel().getBold().getValue());
		chartWizardBean.setTitleIsItalic(formatChart.getTabTitle().getStylePanel().getItalic().getValue());
		chartWizardBean.setTitleIsUnderline(formatChart.getTabTitle().getStylePanel().getUnderline().getValue());
		
		chartWizardBean.setRotateXLabels(formatChartVo.getxAxixRotation());
		
	}
	
	public static SelectionListener<ButtonEvent> setChartStyle(final String rptDesign, final String chartType, final HTML content, final FormatChart formatChart) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				updateFormatChartVo(chartType, formatChart, new ChartWizardBean());
				
				BirtServiceEntry.getInstance().setChartInfo(rptDesign, formatChart.getFormatChartVo(), new AsyncCallback<String>() {

					public void onSuccess(String result) {
						formatChart.getWindow().close();
						content.setHTML(result);
					}

					public void onFailure(Throwable caught) {
						Info.display("Service call failed!", "Service call to {0} failed", "setChartStyle");

					}
				});

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> setChartStyle(final ChartViewer chartViewer, final FormatChart formatChart) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				updateFormatChartVo(chartViewer.getChartWizardBean().getChartType(), formatChart, chartViewer.getChartWizardBean());
				
				BirtServiceEntry.getInstance().setChartInfo(chartViewer.getRptdesign(), formatChart.getFormatChartVo(), new AsyncCallback<String>() {

					public void onSuccess(String result) {
						formatChart.getWindow().close();
						chartViewer.getChart().setHTML(result);
					}

					public void onFailure(Throwable caught) {
						Info.display("Service call failed!", "Service call to {0} failed", "setChartStyle");

					}
				});

			}
		};
	}

	public static SelectionListener<IconButtonEvent> export(final ChartViewer chartViewer, final String extension) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {

				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().exportChart(chartViewer.getRptdesign(), extension, new AsyncCallback<String>() {

					public void onSuccess(String fileName) {
//						System.out.println("filename: " + fileName);
						Window.open(fileName, "_blank", "status=no");
						loading.destroy();
					}

					public void onFailure(Throwable caught) {
						Info.display("exportChart", caught.getLocalizedMessage());
						loading.destroy();
					}

				});

			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> viewLink(final ChartViewer chartViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
//				System.out.println(chartViewer.getChartWizardBean().getTitle());
//				System.out.println(chartViewer.getChartViewID());	
				if (chartViewer.getChartViewID()!= 0 )
					BirtServiceEntry.getInstance().linkChart(chartViewer.getChartViewID(),new AsyncCallback<String>() {
						public void onSuccess(String url) {
//							System.out.println(url);
							com.extjs.gxt.ui.client.widget.Window w = new com.extjs.gxt.ui.client.widget.Window();
							w.setHeading("Link to the chart");
							w.setScrollMode(Scroll.AUTOX);
							TextField<String> text = new TextField<String>();
							text.setWidth("740px");
							text.setHeight("60px");
							text.setReadOnly(true);
							text.setValue(url);
							w.add(text);
							w.show();
							w.setWidth("750px");
						}
						
						public void onFailure(Throwable caught) {					
						}
	
					});
				else 
					FenixAlert.alert("Creating Link", "The chart has to be saved before to be linked", "");
			}
		};
	}
	
	public static void createLink(final ChartViewer chartViewer) {
		if (chartViewer.getChartViewID()!= 0 )
			BirtServiceEntry.getInstance().linkChart(chartViewer.getChartViewID(),new AsyncCallback<String>() {
				public void onSuccess(String url) {
//					System.out.println("Chart link created: " + url);
				}					
				public void onFailure(Throwable caught) {					
				}
			});	
	}

	public static SelectionListener<MenuEvent> zoom(final ChartViewer chartViewer, final String inOut, final String stretch) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {

				BirtServiceEntry.getInstance().resizeChart(chartViewer.getRptdesign(), inOut, stretch, new AsyncCallback<String>() {

					public void onSuccess(String chart) {
						chartViewer.getChart().setHTML(chart);
					}

					public void onFailure(Throwable caught) {
						Info.display("resizeChart(" + inOut + ")", caught.getLocalizedMessage());
					}

				});

			}
		};
	}

	public static SelectionListener<IconButtonEvent> save(final ChartViewer chartViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
//				System.out.println("SAVE");
				if (chartViewer.getChartViewID() == 0L) {
					MetadataEditorWindow meWindow = new MetadataEditorWindow();
					meWindow.build(false, true, false, MESaver.getSaveChartListener(meWindow, chartViewer));
				} else {
					BirtServiceEntry.getInstance().saveChart(chartViewer.getRptdesign(), chartViewer.getChartViewID(), chartViewer.getChartWizardBean(), new AsyncCallback<Long>() {

						public void onSuccess(Long chartViewId) {
//							Info.display("saveChart", "Saved");
							createLink(chartViewer);
							FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
						}

						public void onFailure(Throwable caught) {
							 FenixAlert.error(BabelFish.print().error(), "The chart update failed because you do not have update permission or there is a technical problem");
						}

					});
				}
			}
		};
	}
	
	public static void saveUpdate(final ChartViewer chartViewer) {
//		System.out.println("SAVE");
		BirtServiceEntry.getInstance().saveChart(chartViewer.getRptdesign(), chartViewer.getChartViewID(), chartViewer.getChartWizardBean(), new AsyncCallback<Long>() {
			public void onSuccess(Long chartViewId) {
				Info.display("saveChart", "Saved");
				createLink(chartViewer);
			}
			public void onFailure(Throwable caught) {
				 FenixAlert.error(BabelFish.print().error(), "The chart update failed because you do not have update permission or there is a technical problem");
			}
		});		
	}

	public static SelectionListener<IconButtonEvent> saveAs(final ChartViewer chartViewer) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				MetadataEditorWindow meWindow = new MetadataEditorWindow();
				meWindow.build(false, true, false, null);
				MESaveAs.prepopulateChart(chartViewer.getChartViewID(), true, false, chartViewer, meWindow);
			}
		};
	}

	public static SelectionListener<ComponentEvent> background(final IconButton bkGround, final ChartViewer chartViewer) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {

				new ColorPicker(bkGround, bkGround.getAbsoluteLeft(), bkGround.getAbsoluteTop(), chartViewer).build(ColorPickerCaller.BIRT_BG);

			}
		};
	}

	public static void changeChartbackground(final ChartViewer chartViewer, final String color) {
		BirtServiceEntry.getInstance().changeChartBackground(chartViewer.getRptdesign(), color, new AsyncCallback<String>() {

			public void onSuccess(String chart) {
				chartViewer.getChart().setHTML(chart);
			}

			public void onFailure(Throwable caught) {
				Info.display("changeChartbackground", caught.getLocalizedMessage());
			}

		});
	}

	public static ChangeListener fillColumnValuesX(final ChartViewer chartViewer) {
		return new ChangeListener() {

			public void onChange(Widget widget) {				
				chartViewer.getDimensionXValues().clear();

				/*** Test if the column is a date **/
				/** TODO: QUICK FIX, should be checked on the column datatype and no column header **/ 
				BirtServiceEntry.getInstance().columnIsDate(Long.valueOf(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(0)).get(1)), chartViewer.getDimensionX().getValue(chartViewer.getDimensionX().getSelectedIndex()), new AsyncCallback<DescriptorVO>() {
					public void onSuccess(final DescriptorVO descriptor) {	
						final Counter counter = new Counter();
						List<Long> datasetsIds = new ArrayList<Long>();
						for (int i=0; i < chartViewer.getListCheckBoxDataset().size(); i++) {
							if (chartViewer.getListCheckBoxDataset().get(i).getValue()) {
								datasetsIds.add(Long.valueOf(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(i)).get(1)));
							}
						}
//						System.out.println("-> " + chartViewer.getDimensionX().getValue(chartViewer.getDimensionX().getSelectedIndex()));
						if ( descriptor != null ) {
							chartViewer.getDatePanel().setVisible(true);
							chartViewer.getDateLUPanel().setVisible(true);
						}
						else {
							chartViewer.resetDatePanel();
							chartViewer.getDatePanel().setVisible(false);
							chartViewer.getDateLUPanel().setVisible(false);
							chartViewer.getDatePanel().setVisible(false);
							chartViewer.getDateLUPanel().setVisible(false);
						}				
						TableServiceEntry.getInstance().getDimensionValues(datasetsIds, chartViewer.getDimensionX().getItemText(chartViewer.getDimensionX().getSelectedIndex()), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
							public void onSuccess(Map<String, String> values){
//								System.out.println("TableServiceEntry.getInstance().getDimensionValues(datasetsIds...");
								String startDate = new String();
								String endDate = new String();
								Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
								for (int i = 0; i < values.size(); i++) {
									Map.Entry<String, String> entry = iterator.next();
									chartViewer.getDimensionXValues().addItem(entry.getValue(),entry.getKey());
									
									if ( descriptor != null ) { 
										if  ( i==0 ) 
											startDate = entry.getKey();
										if ( i == values.size() - 1) 
											endDate = entry.getKey();										
									}
								}	
								if ( descriptor != null )  {
									DateFromToPanel p = chartViewer.getDateFromToPanel();
									DateLastUpdatePanel lu = chartViewer.getDateLastUpdatePanel();
									p.setPeriodType(descriptor.getContentDescriptor());
									lu.setPeriodType(descriptor.getContentDescriptor());
									DateUtils.setDateFromToDefault(startDate, endDate, descriptor.getContentDescriptor(), p.getYearFrom(), p.getMonthFrom(), p.getDayFrom(), p.getYearTo(), p.getMonthTo(), p.getDayTo());
									DateUtils.setDateLastUpdateDefault(lu.getYears(), lu.getMonths(), lu.getDays(), descriptor.getContentDescriptor(), p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()), p.getYearTo().getItemText(p.getYearTo().getSelectedIndex()));
								}
					
								if ((counter.getCount()+1)==chartViewer.getListCheckBoxDataset().size()){
									ChartWizardController.selectAllItemsToListBox(chartViewer.getDimensionXValues());
								} 
								else {
									counter.increaseCount();
								}
							}
									
							public void onFailure(Throwable caught){
									Info.display("getColumnValues", caught.getLocalizedMessage());
								}
										
						});
					}
					
					public void onFailure(Throwable caught){
						Info.display("columnIsDate", caught.getLocalizedMessage());
					}		
				}); 
				

				List<String> idDataset = new ArrayList<String>();

				for (int k = 0; k < ((TabPanel) chartViewer.getSideBar().getVpOtherDim().getWidget(0)).getItemCount(); k++) {
					idDataset.add(((TabPanel) chartViewer.getSideBar().getVpOtherDim().getWidget(0)).getItem(k).getItemId());
				}

				ChartViewerController.fillOtherDimensionXEvent(idDataset, chartViewer);

			}
		};
	}
	
	

	private static void fillOtherDimensionXEvent(final List<String> idDataset, final ChartViewer chartViewer) {

		for (int i = 0; i < idDataset.size(); i++) {
			final VerticalPanel otherDimension = (VerticalPanel) ((TabPanel) chartViewer.getSideBar().getVpOtherDim().getWidget(0)).getItem(i).getWidget(0);
			otherDimension.clear();
			ListBox dimensionY = (ListBox) ((VerticalPanel) ((TabPanel) chartViewer.getSideBar().getVpChartAxis().getWidget(4)).getItem(i).getWidget(0)).getWidget(1);
			List<String> otherDim = new ArrayList<String>();
			for (int j = 0; j < dimensionY.getItemCount(); j++) {
				if (!dimensionY.getItemText(j).equals(dimensionY.getItemText(dimensionY.getSelectedIndex())) && !dimensionY.getItemText(j).equals(chartViewer.getDimensionX().getItemText(chartViewer.getDimensionX().getSelectedIndex()))) {
					otherDim.add(dimensionY.getItemText(j));
				}
			}

			final List<String> otherDimWrapper = otherDim;
			TableServiceEntry.getInstance().getColumnsValues(Long.valueOf(idDataset.get(i)), otherDimWrapper, CheckLanguage.getLanguage() , new AsyncCallback<List<List<List<String>>>>(){
				public void onSuccess(List<List<List<String>>> values) {

					List<ListBox> listListBox = new ArrayList<ListBox>();
					for (int numDim = 0; numDim < otherDimWrapper.size(); numDim++) {
						listListBox.add(new ListBox());
					}

					int count = 0;
					for (List<List<String>> l1 : values) {
						if (l1.size() == 0)
							listListBox.get(count).addItem("null", "");
						for (int k = 0; k < l1.size(); k++) {
							//listOfDimension.add(l1.get(k).get(1));
							// I assume that the label are unique DANGER
							//mapOfDimension.put(l1.get(k).get(1), l1.get(k).get(0));
							listListBox.get(count).addItem(l1.get(k).get(1), l1.get(k).get(0));
						}
						count++;
					}

					for (int i = 0; i < listListBox.size(); i++) {
						HorizontalPanel hpTmp = new HorizontalPanel();
						hpTmp.setSpacing(5);
						hpTmp.insert(listListBox.get(i), 0);
						HTML t = new HTML(otherDimWrapper.get(i));
						t.setStyleName("simpleText");
						hpTmp.insert(t, 0);
						otherDimension.insert(hpTmp, otherDimension.getWidgetCount());

						if ((i + 1) == listListBox.size()) {
							HorizontalPanel hp = new HorizontalPanel();
							hp.setSpacing(5);
							HTML title = new HTML(BabelFish.print().otherDim() + ": ");
							title.setStyleName("simpleText");
							hp.add(title);
							otherDimension.insert(hp, 0);
						}
					}

				}

				public void onFailure(Throwable caught) {
					Info.display("getColumnsValues", caught.getLocalizedMessage());
				}
			});
		}

	}

	public static ChangeListener fillColumnValues(final ChartViewer chartViewer, final int tabNum, final String datasetID, final ListBox dimensionSel, final ListBox dimensionNoSel, final ListBox dimensionValue, final VerticalPanel otherDimensions) {
		return new ChangeListener() {

			public void onChange(Widget widget) {
//				System.out.println("ChangeListener fillColumnValues()");

//				final List<String> listOfDimension = new ArrayList<String>();
//				final Map<String, String> mapOfDimension = new HashMap<String, String>();
				final long dsId = Long.valueOf(datasetID).longValue();
				String columnName = dimensionSel.getValue(dimensionSel.getSelectedIndex());
				//BirtServiceEntry.getInstance().getColumnValues(dsId, columnName, new AsyncCallback<List<List<String>>>() {
				
				TableServiceEntry.getInstance().getDimensionValues(dsId, columnName, CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {	
					
					public void onSuccess(Map<String, String> values) {
						dimensionValue.clear();
						if (chartViewer.getChartWizardBean().getChartType().equals("BarLine")) {
							TabItem tab = (TabItem) ((TabPanel) chartViewer.getSideBar().getVpChartAxis().getWidget(4)).getWidget(tabNum);
							((ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(6)).clear();
							((ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(9)).clear();
						}
						
						

						//List<List<String>> revertdata = new ArrayList<List<String>>();
						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
						List<String> el;
						for (int i = 0; i < values.size(); i++) {
							//el = new ArrayList<String>();
							Map.Entry<String, String> entry = iterator.next();
							dimensionValue.addItem(entry.getValue(), entry.getKey());
//							el.add(entry.getKey());
//							el.add(entry.getValue());
							//revertdata.add(el);
						}
						
//						for (List<String> element : revertdata) {
//							listOfDimension.add(element.get(1));
//							// I assume that the label are unique DANGER
//							mapOfDimension.put(element.get(1), element.get(0));
//						}
//
//						Collections.sort(listOfDimension);
//						for (String element : listOfDimension) {
//							dimensionValue.addItem(element, mapOfDimension.get(element));
//						}

						// for(List<String> s: values){
						// dimensionValue.addItem(s.get(1) , s.get(0));
						// }

						ChartWizardController.selectAllItemsToListBox(dimensionValue);

						if (dimensionSel.getValue(0).equals(" "))
							dimensionSel.removeItem(0);

						List<String> otherDim = new ArrayList<String>();
						for (int j=0; j < dimensionSel.getItemCount(); j++){
							if (!dimensionSel.getItemText(j).toLowerCase().equals(dimensionSel.getItemText(dimensionSel.getSelectedIndex()).toLowerCase()) && !dimensionSel.getItemText(j).toLowerCase().equals(dimensionNoSel.getItemText(dimensionNoSel.getSelectedIndex()).toLowerCase())){
								otherDim.add(dimensionSel.getItemText(j));
							}
						}
						
						ChartWizardController.fillOtherDimensionYEvent(datasetID, dimensionSel, dimensionNoSel, otherDimensions, otherDim);

					}

					public void onFailure(Throwable caught) {
						Info.display("getColumnValues", caught.getLocalizedMessage());
					}

				});

			}

		};
	}

	public static SelectionListener<ButtonEvent> chartPreview(final ChartViewer chartViewer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				System.out.println("chartPreview: APPLY EXECUTED");

				chartViewer.getChartWizardBean().setAggregation(chartViewer.getAggregation().getItemText(chartViewer.getAggregation().getSelectedIndex()));  
				
				// datasets
				List<List<String>> datasets = new ArrayList<List<String>>();
				List<String> listTemp;
				for (int i = 0; i < chartViewer.getListCheckBoxDataset().size(); i++) {
					if (chartViewer.getListCheckBoxDataset().get(i).getValue()) {
						listTemp = new ArrayList<String>();
						listTemp.add(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(i)).get(0));
						listTemp.add(chartViewer.getMapDatasetList().get(chartViewer.getListCheckBoxDataset().get(i)).get(1));
						datasets.add(listTemp);
					}
				}
				chartViewer.getChartWizardBean().setDatasetId(datasets);

				
//				System.out.println("chartPreview: datasets: " + datasets);
				// Dimension X
				chartViewer.getChartWizardBean().setDimensionX(chartViewer.getDimensionX().getItemText(chartViewer.getDimensionX().getSelectedIndex()));

				List<List<String>> dimX = new ArrayList<List<String>>();
				List<String> element;
				
				/*** TODO: IF FROM DATE TO DATE IS ENABLED **/
				chartViewer.getChartWizardBean().setMostRecent(false);
				if ( chartViewer.getDateFromToPanel().getCheckBox().getValue()) {
//					Date fromDate = chartViewer.getDateFromToPanel().getFromDateField().getValue();
//					Date toDate = chartViewer.getDateFromToPanel().getToDateField().getValue();
				
					DateFromToPanel p = chartViewer.getDateFromToPanel();
					
					Date fromDate = DateUtils.setFromDate(p);
					Date toDate = DateUtils.setToDate(p);
					/** get all dates **/
			
					dimX = DateUtils.getDates(fromDate, toDate, chartViewer.getDimensionXValues());		
					
				}
				
				/*** TODO: IF MOST RECENT IS ENABLED **/
				else if ( chartViewer.getDateLastUpdatePanel().getCheckBox().getValue()){
//					System.out.println("chartWizard.getSelectData().getDateLastUpdatePanel().getCheckBox().getValue()");
					/** TODO: that one is used in ascending order **/
//					String latestDateString = chartViewer.getDimensionXValues().getValue(chartViewer.getDimensionXValues().getItemCount() -1 );
					/** TODO: this one is used in descending order **/
					String latestDateString = chartViewer.getDimensionXValues().getValue(0);
					Date latestDate = FieldParser.parseDate(latestDateString);
//					System.out.println("LATEST DATE:"  + latestDate);
					ListBox years = chartViewer.getDateLastUpdatePanel().getYears();
					ListBox months = chartViewer.getDateLastUpdatePanel().getMonths();
					ListBox days = chartViewer.getDateLastUpdatePanel().getDays();				
					Date fromDate = DateUtils.getLastModifiedDate(years, months, days, latestDate, chartViewer.getDateLastUpdatePanel().getPeriodType());
					dimX = DateUtils.getDates(fromDate, latestDate, chartViewer.getDimensionXValues());
					chartViewer.getChartWizardBean().setMostRecent(true);
					chartViewer.getChartWizardBean().setMostRecent(DateUtils.getLastModifiedDate(years, months, days));
//					System.out.println(chartViewer.getChartWizardBean().getMostRecent().getYears());
//					System.out.println("chartViewer.getChartWizardBean().ismostrecent(): " +chartViewer.getChartWizardBean().isMostRecent());
				}
				
				else {
				
//					System.out.println("chartPreview: chartViewer.getDimensionXValues().getItemCount(): " + chartViewer.getDimensionXValues().getItemCount());
//					for (int i = 0; i < chartViewer.getDimensionXValues().getItemCount(); i++) {
					for (int i = chartViewer.getDimensionXValues().getItemCount() -1; i >=0 ; i--) {
						if (chartViewer.getDimensionXValues().isItemSelected(i)) {
							element = new ArrayList<String>();
							element.add(chartViewer.getDimensionXValues().getValue(i));
							element.add(chartViewer.getDimensionXValues().getItemText(i));
							dimX.add(element);
						}
					}
				}
				chartViewer.getChartWizardBean().setDimensionValuesX(dimX);

				// Dimension Y
				ListBox listYValues;
				ListBox listYBarValues;
				ListBox listYLineValues;
				List<List<String>> dimY;
				List<List<String>> dimYBarLine;
				Map<String, String> dimensionY = new HashMap<String, String>();
				Map<String, List<List<String>>> dimensionValuesY = new HashMap<String, List<List<String>>>();
				Map<String, List<List<String>>> dimYBar = new HashMap<String, List<List<String>>>();
				Map<String, List<List<String>>> dimYLine = new HashMap<String, List<List<String>>>();
				for (TabItem tab : ((TabPanel) chartViewer.getSideBar().getVpChartAxis().getWidget(4)).getItems()) {
					// listY =
					// chartWizard.getSelectData().getDimensionY().get(tab
					// .getItemId());
					dimensionY.put(tab.getItemId(), ((ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(1)).getItemText(((ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(1)).getSelectedIndex()));

					if (chartViewer.getChartWizardBean().getChartType().equals("BarLine")) {
						listYBarValues = (ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(6);
						dimYBarLine = new ArrayList<List<String>>();
						for (int i = 0; i < listYBarValues.getItemCount(); i++) {
							element = new ArrayList<String>();
							element.add(listYBarValues.getValue(i));
							element.add(listYBarValues.getItemText(i));
							dimYBarLine.add(element);
						}
						dimYBar.put(tab.getItemId(), dimYBarLine);

						listYLineValues = (ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(9);
						dimYBarLine = new ArrayList<List<String>>();
						
//						System.out.println("chartPreview BARLINE: listYLineValues.getItemCount(): " + listYLineValues.getItemCount());
						for (int i = 0; i < listYLineValues.getItemCount(); i++) {
							element = new ArrayList<String>();
							element.add(listYLineValues.getValue(i));
							element.add(listYLineValues.getItemText(i));
							dimYBarLine.add(element);
						}
						dimYLine.put(tab.getItemId(), dimYBarLine);
					} else {
						listYValues = (ListBox) ((VerticalPanel) tab.getWidget(0)).getWidget(3);
						dimY = new ArrayList<List<String>>();
//						System.out.println("chartPreview NO-BARLINE: listYValues.getItemCount(): " + listYValues.getItemCount());
						for (int i = 0; i < listYValues.getItemCount(); i++) {
							if (listYValues.isItemSelected(i)) {
								element = new ArrayList<String>();
								element.add(listYValues.getValue(i));
								element.add(listYValues.getItemText(i));
								dimY.add(element);
							}
						}
						dimensionValuesY.put(tab.getItemId(), dimY);
					}

				}

				// Other Dimensions
				Map<String, List<List<String>>> otherDimension = new HashMap<String, List<List<String>>>();
				VerticalPanel contOtherDim;
				List<List<String>> otherDim;
				for (TabItem tab : ((TabPanel) chartViewer.getSideBar().getVpOtherDim().getWidget(0)).getItems()) {
					contOtherDim = (VerticalPanel) tab.getWidget(0);
					otherDim = new ArrayList<List<String>>();
					for (int i = 1; i < contOtherDim.getWidgetCount(); i++) {
//						System.out.println("chartPreview:  contOtherDim.getWidgetCount(): " +  contOtherDim.getWidgetCount());
						element = new ArrayList<String>();
						element.add(((HTML) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(0)).getHTML());
						element.add(((ListBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getValue(((ListBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getSelectedIndex()));
						element.add(((CheckBox) ((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(2)).getValue()?"true":"false");
						otherDim.add(element);
					}

					otherDimension.put(tab.getItemId(), otherDim);
				}

				chartViewer.getChartWizardBean().setDimensionY(dimensionY);
				chartViewer.getChartWizardBean().setDimensionValuesYBar(dimYBar);
				chartViewer.getChartWizardBean().setDimensionValuesYLine(dimYLine);
				chartViewer.getChartWizardBean().setDimensionValuesY(dimensionValuesY);
				chartViewer.getChartWizardBean().setOtherDimension(otherDimension);

				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().updatingChart(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();

				BirtServiceEntry.getInstance().updatePreview(chartViewer.getChartWizardBean(), "preview", chartViewer.getRptdesign(), new AsyncCallback<List<String>>() {

					public void onSuccess(List<String> result) {
						chartViewer.setRptdesign(result.get(0));
						chartViewer.getChart().setHTML(result.get(1));
						loadingWindow.destroyLoadingBox();
					}

					public void onFailure(Throwable caught) {
						Info.display("updatePreview", caught.getLocalizedMessage());
						loadingWindow.destroyLoadingBox();
					}

				});

			}
		};
	}
	
	
	public static void updateChart(final ChartViewer chartViewer, final Date latestDate, final Map<String, String> dimensionsX) {
//		System.out.println("chartPreview: APPLY EXECUTED");
//		System.out.println("chartViewer.getChartWizardBean().getDatasetId(): " + chartViewer.getChartWizardBean().getDatasetId());


		List<List<String>> dimX = new ArrayList<List<String>>();

//		System.out.println("chartWizard.getSelectData().getDateLastUpdatePanel().getCheckBox().getValue()");
//		System.out.println("LATEST DATE:"  + latestDate);
		DateVo dateVo = chartViewer.getChartWizardBean().getMostRecent();	
//		System.out.println("dateVo:"  + dateVo.getYears() + " | " + dateVo.getMonths() + " | "+ dateVo.getDays());
		
		Date fromDate = DateUtils.getLastModifiedDate(Integer.valueOf(dateVo.getYears()), Integer.valueOf(dateVo.getMonths()), Integer.valueOf(dateVo.getDays()), latestDate);
		/** TODO GET ALL DIMENSIONS VALUES **/ 	
		dimX = DateUtils.getDates(fromDate, latestDate, dimensionsX);
//		System.out.println(chartViewer.getChartWizardBean().getMostRecent().getYears());
//		System.out.println("chartViewer.getChartWizardBean().ismostrecent(): " +chartViewer.getChartWizardBean().isMostRecent());

		chartViewer.getChartWizardBean().setDimensionValuesX(dimX);


//		System.out.println("getOtherDimension(): " + chartViewer.getChartWizardBean().getOtherDimension());
//		System.out.println("getAggregation(): " + chartViewer.getChartWizardBean().getAggregation());

			BirtServiceEntry.getInstance().updatePreview(chartViewer.getChartWizardBean(), "preview", chartViewer.getRptdesign(), new AsyncCallback<List<String>>() {
	
				public void onSuccess(List<String> result) {
					chartViewer.setRptdesign(result.get(0));
					saveUpdate(chartViewer);
				}
	
				public void onFailure(Throwable caught) {
					Info.display("updatePreview", caught.getLocalizedMessage());
				}
	
			});
	
	
	}
	
	
}
