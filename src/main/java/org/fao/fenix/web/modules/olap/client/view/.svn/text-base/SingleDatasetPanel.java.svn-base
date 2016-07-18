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
package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
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
	
	private String WIDTH = "225px";
	
	private String SETTINGS_WIDTH = "215px";
	
	private Button addDataset;
	
	private CheckBox showSingleElements;
	
	private CheckBox showRowTotals;
	
	private CheckBox showColTotals;
	
	private CheckBox showTotals;
	
	private CheckBox showWidthAndHeight;
	
	private ListBox reportOrientation;
	
	private ListBox decimals;
	
	private ContentPanel aggregationFunctionPanel;
	
	private TextField<String> olapTitle;
	
	private HTML colHeaderColor;
	
	private HTML subColHeaderColor;
	
	private HTML rowHeaderColor;
	
	private HTML subRowHeaderColor;
	
	private HTML contentBackgroundColor;
	
	private HTML colFontColor;
	
	private HTML subColFontColor;
	
	private HTML rowFontColor;
	
	private HTML subRowFontColor;
	
	private HTML contentFontColor;
	
	private HTML summaryBackgroundColor;
	
	private HTML summaryFontColor;
	
	private HTML titleFontColor;
	
	private ListBox fontFamily;
	
	private ListBox fontSize;
	
	public SingleDatasetPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("490px");
		panel.setLayout(new AccordionLayout());
		xAxis = new AxisPanel("X");
		yAxis = new AxisPanel("Y");
		zAxis = new AxisPanel("Z");
		wAxis = new AxisPanel("W");
		addDataset = new Button(BabelFish.print().addDataset());
		initColors();
	}
	
	public ContentPanel build() {
		
		panel.add(buildDataSource());
		panel.add(buildAggregationFunction());
		
		panel.add(xAxis.build(BabelFish.print().column(), false, "column"));
		panel.add(zAxis.build(BabelFish.print().row(), false, "row"));
		panel.add(yAxis.build(BabelFish.print().subColumn(), false, "column"));
		panel.add(wAxis.build(BabelFish.print().subRow(), false, "row"));
		
		panel.add(buildValuePanel());
		panel.add(buildSettingsPanel());
		panel.add(buildFormatPanel());
		
		dataSource.addChangeListener(OlapController.dataSourceChangeListener(xAxis.getDimensions(), yAxis.getDimensions(), zAxis.getDimensions(), wAxis.getDimensions(), values));
		xAxis.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, xAxis));
		yAxis.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, yAxis));
		zAxis.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, zAxis));
		wAxis.getDimensions().addChangeListener(OlapController.dimensionsChangeListenerWithLabels(dataSource, wAxis));
		
		return panel;
	}
	
	private ContentPanel buildFormatPanel() {
		
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading("Format");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		wrap.setIconStyle("sld");
		
		panel.add(new HTML("<b><font color='#1D4589'>Title Color</font></b>"));
		panel.add(titleFontColor);
		
		fontFamily = new ListBox();
		fontFamily.setWidth(SETTINGS_WIDTH);
		fontFamily.addItem("Sans Serif", "sans-serif");
		fontFamily.addItem("Times New Roman", "Times New Roman");
		fontFamily.addItem("Arial", "arial");
		panel.add(new HTML("<b><font color='#1D4589'>Font Family</font></b>"));
		panel.add(fontFamily);
		
		fontSize = new ListBox();
		fontSize.setWidth(SETTINGS_WIDTH);
		for (int i = 20 ; i > 0 ; i--)
			fontSize.addItem(String.valueOf(i), String.valueOf(i));
		fontSize.setSelectedIndex(12);
		panel.add(new HTML("<b><font color='#1D4589'>Font Size</font></b>"));
		panel.add(fontSize);
		
		panel.add(new HTML("<b><font color='#1D4589'>Columns Header Color</font></b>"));
		panel.add(colHeaderColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Columns Font Color</font></b>"));
		panel.add(colFontColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Rows Header Color</font></b>"));
		panel.add(rowHeaderColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Rows Font Color</font></b>"));
		panel.add(rowFontColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Sub-Columns Header Color</font></b>"));
		panel.add(subColHeaderColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Sub-Columns Font Color</font></b>"));
		panel.add(subColFontColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Sub-Rows Header Color</font></b>"));
		panel.add(subRowHeaderColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Sub-Rows Font Color</font></b>"));
		panel.add(subRowFontColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Content Background Color</font></b>"));
		panel.add(contentBackgroundColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Content Font Color</font></b>"));
		panel.add(contentFontColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Summary Background Color</font></b>"));
		panel.add(summaryBackgroundColor);
		
		panel.add(new HTML("<b><font color='#1D4589'>Summary Font Color</font></b>"));
		panel.add(summaryFontColor);
		
		wrap.setScrollMode(Scroll.AUTO);
		wrap.add(panel);
		
		return wrap;
	}
	
	private ContentPanel buildSettingsPanel() {
		
		ContentPanel wrap = new ContentPanel();
		wrap.setHeading(BabelFish.print().settings());
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		wrap.setIconStyle("gear");
		
		olapTitle = new TextField<String>();
		olapTitle.setWidth(WIDTH);
		olapTitle.setEmptyText("Multidimensional Table Caption");
		panel.add(new HTML("<b><font color='#1D4589'>Multidimensional Table Title</font></b>"));
		panel.add(olapTitle);
		
		showSingleElements = new CheckBox();
		showSingleElements.setBoxLabel(BabelFish.print().showSingleElements());
		showSingleElements.setValue(true);
		showSingleElements.setEnabled(false);
		panel.add(showSingleElements);
		
		showTotals = new CheckBox();
		showTotals.setBoxLabel(BabelFish.print().showTotals());
		panel.add(showTotals);
		
		showRowTotals = new CheckBox();
		showRowTotals.setBoxLabel("Show Row Totals");
		panel.add(showRowTotals);
		
		showColTotals = new CheckBox();
		showColTotals.setBoxLabel("Show Column Totals");
		panel.add(showColTotals);
		
		showWidthAndHeight = new CheckBox();
		showWidthAndHeight.setBoxLabel("Show Widths and Heights");
//		showWidthAndHeight.setEnabled(false); // TODO remove when SAVE and LOAD are complete
		panel.add(showWidthAndHeight);
		
		decimals = new ListBox();
		decimals.setWidth(WIDTH);
		decimals.addItem("0", "0");
		decimals.addItem("1", "1");
		decimals.addItem("2", "2");
		decimals.addItem("3", "3");
		decimals.addItem("4", "4");
		decimals.setWidth(WIDTH);
		panel.add(new HTML("<b><font color='#1D4589'>Decimals</font></b>"));
		panel.add(decimals);
		
		reportOrientation = new ListBox();
		reportOrientation.addItem("Portrait", "portrait");
		reportOrientation.addItem("Landscape", "landscape");
		reportOrientation.setWidth(WIDTH);
		panel.add(new HTML("<b><font color='#1D4589'>Report Orientation</font></b>"));
		panel.add(reportOrientation);
		
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
		wrap.setIconStyle("dimensionToPlot");
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().value()+"</font></b>");
		label.setWidth("125px");
		values = new ListBox();
		values.setWidth(WIDTH);
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
//		dataSourcePanel.add(wrapper);
		dataSourcePanel.add(buildDataSourcePanel());
		dataSourcePanel.setIconStyle("datasource");
		return dataSourcePanel;
	}
	
	public HorizontalPanel buildButtonPanel() {
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		buttonPanel.add(addDataset);
		return buttonPanel;
	}
	
	public VerticalPanel buildDataSourcePanel() {
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSpacing(5);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().dataSource()+"</font></b>");
		label.setWidth("125px");
		dataSource = new ListBox();
		dataSource.setWidth(WIDTH);
		dataSource.setMultipleSelect(true);
		dataSource.setVisibleItemCount(5);
		wrapper.add(label);
		addDataset.setWidth(WIDTH);
		addDataset.setIconStyle("addDataset");
		wrapper.add(addDataset);
		wrapper.add(dataSource);
		Html suggestion = new Html("<font color='#1D4589'>Please select a dataset from the Resource Explorer by clicking on the 'Add Dataset' button. Then you will be able to select dimensions and values contained in this dataset to create your multidimensional table.</font>");
		wrapper.add(suggestion);
//		OlapController.fillDataSourceList(dataSource); // TODO restore me?
		return wrapper;
	}
	
	public ContentPanel buildAggregationFunction() {
		aggregationFunctionPanel = new ContentPanel();
		aggregationFunctionPanel.setHeading(BabelFish.print().aggregationFunction());
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSpacing(5);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().aggregationFunction()+"</font></b>");
		label.setWidth("125px");
		function = new ListBox();
		function.setVisibleItemCount(7);
		function.setWidth(WIDTH);
		function.addItem(BabelFish.print().frequency(), "COUNT");
		function.addItem(BabelFish.print().sum(), "SUM");
		function.addItem(BabelFish.print().average(), "AVG");
		function.addItem(BabelFish.print().minimum(), "MIN");
		function.addItem(BabelFish.print().maximum(), "MAX");
		function.setWidth(WIDTH);
		wrapper.add(label);
		wrapper.add(function);
		aggregationFunctionPanel.add(wrapper);
		aggregationFunctionPanel.setIconStyle("aggregation");
		Html suggestion = new Html("<font color='#1D4589'>Multidimensional Tables are used to create aggregations of raw data. Please select from the menu above one of the available aggregation functions to be applied to the datasource you have selected.</font>");
		wrapper.add(suggestion);
		return aggregationFunctionPanel;
	}
	
	private void initColors() {
		titleFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #1D4589; color: #1D4589; font-weight: bold; font-style: italic;'>#1D4589</div>");
		titleFontColor.setWidth(SETTINGS_WIDTH);
		titleFontColor.addClickHandler(SLDEditorController.colorPicker(titleFontColor));
		colHeaderColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #1D4589; color: #1D4589; font-weight: bold; font-style: italic;'>#1D4589</div>");
		colHeaderColor.setWidth(SETTINGS_WIDTH);
		colHeaderColor.addClickHandler(SLDEditorController.colorPicker(colHeaderColor));
		rowHeaderColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #1D4589; color: #1D4589; font-weight: bold; font-style: italic;'>#1D4589</div>");
		rowHeaderColor.setWidth(SETTINGS_WIDTH);
		rowHeaderColor.addClickHandler(SLDEditorController.colorPicker(rowHeaderColor));
		subColHeaderColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #D0DDED; color: #D0DDED; font-weight: bold; font-style: italic;'>#D0DDED</div>");
		subColHeaderColor.setWidth(SETTINGS_WIDTH);
		subColHeaderColor.addClickHandler(SLDEditorController.colorPicker(subColHeaderColor));
		subRowHeaderColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #D0DDED; color: #D0DDED; font-weight: bold; font-style: italic;'>#D0DDED</div>");
		subRowHeaderColor.setWidth(SETTINGS_WIDTH);
		subRowHeaderColor.addClickHandler(SLDEditorController.colorPicker(subRowHeaderColor));
		contentBackgroundColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		contentBackgroundColor.setWidth(SETTINGS_WIDTH);
		contentBackgroundColor.addClickHandler(SLDEditorController.colorPicker(contentBackgroundColor));
		colFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		colFontColor.setWidth(SETTINGS_WIDTH);
		colFontColor.addClickHandler(SLDEditorController.colorPicker(colFontColor));
		rowFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		rowFontColor.setWidth(SETTINGS_WIDTH);
		rowFontColor.addClickHandler(SLDEditorController.colorPicker(rowFontColor));
		subColFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subColFontColor.setWidth(SETTINGS_WIDTH);
		subColFontColor.addClickHandler(SLDEditorController.colorPicker(subColFontColor));
		subRowFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subRowFontColor.setWidth(SETTINGS_WIDTH);
		subRowFontColor.addClickHandler(SLDEditorController.colorPicker(subRowFontColor));
		contentFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		contentFontColor.setWidth(SETTINGS_WIDTH);
		contentFontColor.addClickHandler(SLDEditorController.colorPicker(contentFontColor));
		summaryBackgroundColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #B08C77; color: #B08C77; font-weight: bold; font-style: italic;'>#B08C77</div>");
		summaryBackgroundColor.setWidth(SETTINGS_WIDTH);
		summaryBackgroundColor.addClickHandler(SLDEditorController.colorPicker(summaryBackgroundColor));
		summaryFontColor = new HTML("<div align='center' style='border: 3px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		summaryFontColor.setWidth(SETTINGS_WIDTH);
		summaryFontColor.addClickHandler(SLDEditorController.colorPicker(summaryFontColor));
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

	public CheckBox getShowRowTotals() {
		return showRowTotals;
	}

	public CheckBox getShowColTotals() {
		return showColTotals;
	}

	public ListBox getReportOrientation() {
		return reportOrientation;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getAggregationFunctionPanel() {
		return aggregationFunctionPanel;
	}

	public ListBox getDecimals() {
		return decimals;
	}

	public TextField<String> getOlapTitle() {
		return olapTitle;
	}

	public CheckBox getShowTotals() {
		return showTotals;
	}

	public HTML getColHeaderColor() {
		return colHeaderColor;
	}

	public HTML getSubColHeaderColor() {
		return subColHeaderColor;
	}

	public HTML getRowHeaderColor() {
		return rowHeaderColor;
	}

	public HTML getSubRowHeaderColor() {
		return subRowHeaderColor;
	}

	public HTML getContentBackgroundColor() {
		return contentBackgroundColor;
	}

	public HTML getColFontColor() {
		return colFontColor;
	}

	public HTML getSubColFontColor() {
		return subColFontColor;
	}

	public HTML getRowFontColor() {
		return rowFontColor;
	}

	public HTML getSubRowFontColor() {
		return subRowFontColor;
	}

	public HTML getContentFontColor() {
		return contentFontColor;
	}

	public ListBox getFontFamily() {
		return fontFamily;
	}

	public ListBox getFontSize() {
		return fontSize;
	}

	public HTML getSummaryBackgroundColor() {
		return summaryBackgroundColor;
	}

	public HTML getSummaryFontColor() {
		return summaryFontColor;
	}

	public HTML getTitleFontColor() {
		return titleFontColor;
	}

	public CheckBox getShowWidthAndHeight() {
		return showWidthAndHeight;
	}
	
}