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
package org.fao.fenix.web.modules.ofcchart.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SingleDatasetPanel {

	private ContentPanel panel;
	
	private AxisPanel xAxis;
	
	private AxisPanel yAxis;
	
	private AxisPanel zAxis;
	
	private AxisPanel wAxis;
	
	private ListBox dataSource;
	
	private ListBox function;
	
	private ListBox values;
	
	private String width = "200px";
	
	private Button addDataset;
	
	private CheckBox showSingleElements;
	
	private CheckBox showTotals;
	
	private ListBox chartType;
	
	private Button createChart;
	
	private OfcFormatChartPanel ofcFormatChartPanel;
	
	public SingleDatasetPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("500px");
		panel.setLayout(new AccordionLayout());
		xAxis = new AxisPanel();
		yAxis = new AxisPanel();
		zAxis = new AxisPanel();
		wAxis = new AxisPanel();
		ofcFormatChartPanel = new OfcFormatChartPanel();
		createChart = new Button("Create Chart");
		addDataset = new Button(BabelFish.print().addDataset());
	}
	
	public ContentPanel build(OfcChartWindow window) {
		panel.add(buildDataSource());
//		panel.add(buildAggregationFunction());
		buildAggregationFunction();
		panel.add(xAxis.build("X-Axis", false));
//		panel.add(yAxis.build(I18N.print().subColumn(), false));
		panel.add(zAxis.build("Y-Axis", false));
//		panel.add(wAxis.build(I18N.print().subRow(), false));
//		panel.add(buildValuePanel());
		buildValuePanel();
		panel.add(buildSettingsPanel());
		panel.addButton(createChart);
		createChart.addSelectionListener(OfcChartController.createChart(window));
		dataSource.addChangeListener(OfcChartController.dataSourceChangeListener(xAxis.getDimensions(), yAxis.getDimensions(), zAxis.getDimensions(), wAxis.getDimensions(), values));
		xAxis.getDimensions().addChangeListener(OfcChartController.dimensionsChangeListenerWithLabels(dataSource, xAxis.getValues()));
		yAxis.getDimensions().addChangeListener(OfcChartController.dimensionsChangeListenerWithLabels(dataSource, yAxis.getValues()));
		zAxis.getDimensions().addChangeListener(OfcChartController.dimensionsChangeListenerWithLabels(dataSource, zAxis.getValues()));
		wAxis.getDimensions().addChangeListener(OfcChartController.dimensionsChangeListenerWithLabels(dataSource, wAxis.getValues()));
		
//		xAxis.getShowUpperLevel().addSelectionListener(OlapController.showUpperLevel(xAxis, dataSource));
//		yAxis.getShowUpperLevel().addSelectionListener(OlapController.showUpperLevel(yAxis, dataSource));
//		zAxis.getShowUpperLevel().addSelectionListener(OlapController.showUpperLevel(zAxis, dataSource));
//		wAxis.getShowUpperLevel().addSelectionListener(OlapController.showUpperLevel(wAxis, dataSource));
		
//		xAxis.getShowLowerLevel().addSelectionListener(OlapController.showLowerLevel(xAxis, dataSource));
//		yAxis.getShowLowerLevel().addSelectionListener(OlapController.showLowerLevel(yAxis, dataSource));
//		zAxis.getShowLowerLevel().addSelectionListener(OlapController.showLowerLevel(zAxis, dataSource));
//		wAxis.getShowLowerLevel().addSelectionListener(OlapController.showLowerLevel(wAxis, dataSource));
		
		return panel;
	}
	
	private ContentPanel buildSettingsPanel() {
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading(BabelFish.print().settings());
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		showSingleElements = new CheckBox();
		showSingleElements.setBoxLabel(BabelFish.print().showSingleElements());
		showSingleElements.setValue(true);
		showSingleElements.setEnabled(false);
//		panel.add(showSingleElements);
		showTotals = new CheckBox();
		showTotals.setBoxLabel(BabelFish.print().showTotals());
//		panel.add(showTotals);
		chartType = new ListBox();
		chartType.addItem(BabelFish.print().barChart(), "Bar");
		chartType.addItem(BabelFish.print().lineChart(), "Line");
		chartType.addItem(BabelFish.print().pieChart(), "Pie");
		chartType.setWidth("300px");	
		panel.add(chartType);
		panel.add(ofcFormatChartPanel.build());
		wrap.setScrollMode(Scroll.AUTO);
		wrap.add(panel);
		return wrap;
	}
	
	/**
	 * Used to make the user chose the dimension to plot.
	 */
	private ContentPanel buildValuePanel() {
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading(BabelFish.print().dimensionToPlot());
		HorizontalPanel panel = new HorizontalPanel();
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
		wrapper.add(buildButtonPanel());
		dataSourcePanel.add(wrapper);
		return dataSourcePanel;
	}
	
	public HorizontalPanel buildButtonPanel() {
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		buttonPanel.add(addDataset);
		return buttonPanel;
	}
	
	public HorizontalPanel buildDataSourcePanel() {
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().dataSource()+":</b>");
		label.setWidth("75px");
		dataSource = new ListBox();
		dataSource.setWidth(width);
		wrapper.add(label);
		wrapper.add(dataSource);
//		OlapController.fillDataSourceList(dataSource); // TODO restore me?
		return wrapper;
	}
	
	public ContentPanel buildAggregationFunction() {
		ContentPanel aggregationFunctionPanel = new ContentPanel();
		aggregationFunctionPanel.setHeading(BabelFish.print().aggregationFunction());
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().aggregationFunction()+":</b>");
		label.setWidth("75px");
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

	public Button getAddDataset() {
		return addDataset;
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

	public OfcFormatChartPanel getOfcFormatChartPanel() {
		return ofcFormatChartPanel;
	}

	public void setOfcFormatChartPanel(OfcFormatChartPanel ofcFormatChartPanel) {
		this.ofcFormatChartPanel = ofcFormatChartPanel;
	}
	
}