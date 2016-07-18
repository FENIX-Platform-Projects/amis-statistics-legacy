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
package org.fao.fenix.web.modules.fsatmis.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapToolController;
import org.fao.fenix.web.modules.olap.client.view.AxisPanel;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class FSATMISDatasetPanel {

	private ContentPanel panel;
	
	private AxisPanel xAxis;
	
	private AxisPanel yAxis;
	
	private AxisPanel zAxis;
	
	private AxisPanel wAxis;
	
	private ListBox dataSource;
	
	private ListBox function;
	
	private ListBox values;
	
	private String width = "200px";
	
	private CheckBox showSingleElements;
	
	private CheckBox useFlashCharts;
	
	private CheckBox showTotals;
	
	private CheckBox showBaseLayer;
	
	private ListBox chartType;
	
	private ListBox reportOrientation;
	
	private ListBox intervals;
	
	private ListBox minColor;
	
	private ListBox maxColor;
	
	public FSATMISDatasetPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("640px");
		panel.setLayout(new AccordionLayout());
		xAxis = new AxisPanel("X");
		yAxis = new AxisPanel("Y");
		zAxis = new AxisPanel("Z");
		wAxis = new AxisPanel("W");
		minColor = new ListBox();
		maxColor = new ListBox();
	}
	
	@SuppressWarnings("deprecation")
	public ContentPanel build() {
		
		panel.add(buildDataSource());
		panel.add(buildAggregationFunction());
		
		panel.add(xAxis.build(BabelFish.print().column(), false, "column"));
		panel.add(zAxis.build(BabelFish.print().row(), false, "row"));
		panel.add(yAxis.build(BabelFish.print().subColumn(), false, "column"));
		panel.add(wAxis.build(BabelFish.print().subRow(), false, "row"));
		
		panel.add(buildValuePanel());
		panel.add(buildSettingsPanel());
		
		dataSource.addChangeListener(OlapToolController.dataSourceChangeListener(xAxis.getDimensions(), yAxis.getDimensions(), zAxis.getDimensions(), wAxis.getDimensions(), values, this));
		xAxis.getDimensions().addChangeListener(OlapToolController.dimensionsChangeListenerWithLabels(dataSource, xAxis.getValues()));
		yAxis.getDimensions().addChangeListener(OlapToolController.dimensionsChangeListenerWithLabels(dataSource, yAxis.getValues()));
		zAxis.getDimensions().addChangeListener(OlapToolController.dimensionsChangeListenerWithLabels(dataSource, zAxis.getValues()));
		wAxis.getDimensions().addChangeListener(OlapToolController.dimensionsChangeListenerWithLabels(dataSource, wAxis.getValues()));
		
//		xAxis.getShowUpperLevel().addSelectionListener(OlapToolController.showUpperLevel(xAxis, dataSource));
//		yAxis.getShowUpperLevel().addSelectionListener(OlapToolController.showUpperLevel(yAxis, dataSource));
//		zAxis.getShowUpperLevel().addSelectionListener(OlapToolController.showUpperLevel(zAxis, dataSource));
//		wAxis.getShowUpperLevel().addSelectionListener(OlapToolController.showUpperLevel(wAxis, dataSource));
		
//		xAxis.getShowLowerLevel().addSelectionListener(OlapToolController.showLowerLevel(xAxis, dataSource));
//		yAxis.getShowLowerLevel().addSelectionListener(OlapToolController.showLowerLevel(yAxis, dataSource));
//		zAxis.getShowLowerLevel().addSelectionListener(OlapToolController.showLowerLevel(zAxis, dataSource));
//		wAxis.getShowLowerLevel().addSelectionListener(OlapToolController.showLowerLevel(wAxis, dataSource));
		
		return panel;
	}
	
	private ContentPanel buildSettingsPanel() {
		
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading(BabelFish.print().settings());
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		
		showSingleElements = new CheckBox();
		showSingleElements.setBoxLabel(BabelFish.print().showSingleElements());
		showSingleElements.setValue(true);
		showSingleElements.setEnabled(false);
		panel.add(showSingleElements);
		
		showTotals = new CheckBox();
		showTotals.setBoxLabel(BabelFish.print().showTotals());
		panel.add(showTotals);
		
		showBaseLayer = new CheckBox();
		showBaseLayer.setBoxLabel("Show Base Layer");
		showBaseLayer.setValue(true);
		panel.add(showBaseLayer);
		
		chartType = new ListBox();
		chartType.addItem(BabelFish.print().barChart(), "Bar");
//		chartType.addItem(I18N.print().lineChart(), "Line");
		chartType.setWidth("200px");
		panel.add(new HTML("<b>Chart Type</b>"));
		panel.add(chartType);
		
		useFlashCharts = new CheckBox();
		useFlashCharts.setBoxLabel(BabelFish.print().useFlashCharts());
		useFlashCharts.setValue(true);
		useFlashCharts.setEnabled(true);
		panel.add(useFlashCharts);
		
		reportOrientation = new ListBox();
		reportOrientation.addItem("Portrait", "portrait");
		reportOrientation.addItem("Landscape", "landscape");
		reportOrientation.setWidth("200px");
		panel.add(new HTML("<b>Report Orientation</b>"));
		panel.add(reportOrientation);
		
		intervals = new ListBox();
		intervals.addItem("10");
		intervals.addItem("9");
		intervals.addItem("8");
		intervals.addItem("7");
		intervals.addItem("6");
		intervals.addItem("5");
		intervals.addItem("4");
		intervals.addItem("3");
		intervals.addItem("2");
		intervals.setWidth("200px");
		panel.add(new HTML("<b>Number of Map Classes</b>")); // TODO add colors
		panel.add(intervals);
		
		minColor = createColorList();
		minColor.setSelectedIndex(6);
		panel.add(new HTML("<b>Maximum Color</b>")); // TODO add colors
		panel.add(minColor);
		
		maxColor = createColorList();
		maxColor.setSelectedIndex(9);
		panel.add(new HTML("<b>Minimum Color</b>")); // TODO add colors
		panel.add(maxColor);
		
		wrap.add(panel);
		return wrap;
	}
	
	private ListBox createColorList() {
		ListBox list = new ListBox();
		list.addItem("White", "WHITE");
		list.addItem("Light Gray", "LIGHT_GRAY");
		list.addItem("Gray", "GRAY");
		list.addItem("Dark Gray", "DARK_GRAY");
		list.addItem("Black", "BLACK");
		list.addItem("Red", "RED");
		list.addItem("Pink", "PINK");
		list.addItem("Orange", "ORANGE");
		list.addItem("Yellow", "YELLOW");
		list.addItem("Green", "GREEN");
		list.addItem("Magenta", "MAGENTA");
		list.addItem("Cyan", "CYAN");
		list.addItem("Blue", "BLUE");
		return list;
	}
	
	/**
	 * Used to make the user chose the dimension to plot.
	 */
	private ContentPanel buildValuePanel() {
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading(BabelFish.print().dimensionToPlot());
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().value()+": </b>");
		label.setWidth("75px");
		values = new ListBox();
		values.setWidth(width);
		panel.add(label);
		panel.add(values);
		wrap.add(panel);
		return wrap;
	}
	
	public ContentPanel buildDataSource() {
		ContentPanel dataSourcePanel = new ContentPanel();
		dataSourcePanel.setHeading(BabelFish.print().dataSource());
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.add(buildDataSourcePanel());
//		wrapper.add(buildButtonPanel());
		dataSourcePanel.add(wrapper);
		return dataSourcePanel;
	}
	
	public HorizontalPanel buildButtonPanel() {
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		return buttonPanel;
	}
	
	public VerticalPanel buildDataSourcePanel() {
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().dataSource()+":</b>");
		label.setWidth("100px");
		dataSource = new ListBox();
		dataSource.setWidth(width);
		wrapper.add(label);
		wrapper.add(dataSource);
		OlapToolController.fillDataSourceList(dataSource);
		return wrapper;
	}
	
	public ContentPanel buildAggregationFunction() {
		ContentPanel aggregationFunctionPanel = new ContentPanel();
		aggregationFunctionPanel.setHeading(BabelFish.print().aggregationFunction());
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().aggregationFunction()+":</b>");
		label.setWidth("175px");
		function = new ListBox();
		function.setWidth(width);
		function.addItem(BabelFish.print().frequency(), "COUNT");
		function.addItem(BabelFish.print().sum(), "SUM");
		function.addItem(BabelFish.print().average(), "AVG");
		function.addItem(BabelFish.print().minimum(), "MIN");
		function.addItem(BabelFish.print().maximum(), "MAX");
		wrapper.add(label);
		wrapper.add(function);
		aggregationFunctionPanel.add(wrapper);
		return aggregationFunctionPanel;
	}

	public ListBox getDataSource() {
		return dataSource;
	}

	public AxisPanel getXAxis() {
		return xAxis;
	}

	public AxisPanel getYAxis() {
		return yAxis;
	}

	public AxisPanel getZAxis() {
		return zAxis;
	}

	public AxisPanel getWAxis() {
		return wAxis;
	}

	public ListBox getFunction() {
		return function;
	}

	public ListBox getValues() {
		return values;
	}

	public CheckBox getShowSingleElements() {
		return showSingleElements;
	}

	public CheckBox getShowTotals() {
		return showTotals;
	}

	public ListBox getChartType() {
		return chartType;
	}

	public ListBox getReportOrientation() {
		return reportOrientation;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ListBox getIntervals() {
		return intervals;
	}

	public ListBox getMinColor() {
		return minColor;
	}

	public ListBox getMaxColor() {
		return maxColor;
	}

	public CheckBox getShowBaseLayer() {
		return showBaseLayer;
	}

	public CheckBox getUseFlashCharts() {
		return useFlashCharts;
	}
	
}