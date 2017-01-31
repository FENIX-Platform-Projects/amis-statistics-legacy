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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class AxisPanel {
	
	private String FIELD_WIDTH = "225px";
	
	private String LABEL_WIDTH = "200px";
	
	private ListBox dataSource;
	
	private ListBox dimensions;
	
	private ListBox values;
	
	private ContentPanel axisPanel;
	
	private CardLayout layout;
	
	private CheckBox aggregateSubLevel;
	
	private CheckBox aggregateAsMonthly;
	
	private CheckBox aggregateAsYearly;
	
	private Button showUpperLevel;
	
	private Button showLowerLevel;
	
	private Button resetDimension;
	
	private Button standardViewButton;
	
	private Button fromDateToDateViewButton;
	
	private Button mostRecentDataViewButton;
	
	private DateField fromDateField;
	
	private DateField toDateField;
	
	private ListBox latestYearList;
	
	private ListBox latestMonthList;
	
	private ListBox latestDayList;
	
	private boolean useFromDateToDate = false;
	
	private boolean useMostRecentData = false;
	
	private String AXIS;
	
	private final int SPACING = 5;
	
	/** Map<columnHeader, Map<code, label>> */
	private Map<String, Map<String, String>> uniqueValuesMap;
	
	public AxisPanel(String axis) {
		this.setAXIS(axis);
		axisPanel = new ContentPanel();
		layout = new CardLayout();
		axisPanel.setButtonAlign(HorizontalAlignment.CENTER);
		axisPanel.setLayout(layout);
		dataSource = new ListBox();
		dimensions = new ListBox();
		values = new ListBox();
		values.addChangeHandler(OlapController.valuesListChangeHandler(values, this));
		uniqueValuesMap = new HashMap<String, Map<String,String>>();
		standardViewButton = new Button("Standard");
		standardViewButton.setMinWidth(20);
		standardViewButton.addSelectionListener(OlapController.buildStandardViewPanel(this));
		fromDateToDateViewButton = new Button("Date Interval");
		fromDateToDateViewButton.setMinWidth(20);
		fromDateToDateViewButton.setEnabled(false);
		mostRecentDataViewButton = new Button("Latest Data");
		mostRecentDataViewButton.setMinWidth(20);
		mostRecentDataViewButton.setEnabled(false);
		axisPanel.addButton(standardViewButton);
		axisPanel.addButton(fromDateToDateViewButton);
		axisPanel.addButton(mostRecentDataViewButton);
	}
	
	public ContentPanel build(String title, boolean hasDataSource) {
		axisPanel.setHeading(title);
		axisPanel.add(buildSelectorsPanel(hasDataSource));
		axisPanel.setData("dimensions", dimensions);
		axisPanel.setData("values", values);
		return axisPanel;
	}
	
	public VerticalPanel buildFromDateToDatePanel(Date fromDate, Date toDate) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		p.add(buildDimensionsPanel());
		p.add(new Html("<b><font color='#1D4589'>From Date</font></b>"));
		fromDateField = new DateField();
		fromDateField.setWidth(FIELD_WIDTH);
		fromDateField.setValue(fromDate);
		p.add(fromDateField);
		p.add(new Html("<b><font color='#1D4589'>To Date</font></b>"));
		toDateField = new DateField();
		toDateField.setWidth(FIELD_WIDTH);
		toDateField.setValue(toDate);
		p.add(toDateField);
		p.add(buildAggregationPanel(true));
		return p;
	}
	
	public VerticalPanel buildMostRecentDataPanel() {
		
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		
		p.add(buildDimensionsPanel());
		
		Html latestYearLabel = new Html("<b><font color='#1D4589'>Latest<font></b>");
		latestYearLabel.setWidth("50px");
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setSpacing(5);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.add(latestYearLabel);
		latestYearList = createLatestYearList(25);
		h1.add(latestYearList);
		Html year = new Html("<b><font color='#1D4589'>Year(s)</font></b>");
		year.setWidth("75px");
		h1.add(year);
		p.add(h1);
		
		Html latestMonthLabel = new Html("<b><font color='#1D4589'>Latest</font></b>");
		latestMonthLabel.setWidth("50px");
		HorizontalPanel h2 = new HorizontalPanel();
		h2.setSpacing(5);
		h2.setVerticalAlign(VerticalAlignment.MIDDLE);
		h2.add(latestMonthLabel);
		latestMonthList = createLatestMontList();
		h2.add(latestMonthList);
		Html month = new Html("<b><font color='#1D4589'>Month(s)</font></b>");
		month.setWidth("75px");
		h2.add(month);
		p.add(h2);
		
		Html latestDayLabel = new Html("<b><font color='#1D4589'>Latest</font></b>");
		latestDayLabel.setWidth("50px");
		HorizontalPanel h3 = new HorizontalPanel();
		h3.setSpacing(5);
		h3.setVerticalAlign(VerticalAlignment.MIDDLE);
		h3.add(latestDayLabel);
		latestDayList = createLatestDayList();
		h3.add(latestDayList);
		Html day = new Html("<b><font color='#1D4589'>Day(s)</font></b>");
		day.setWidth("75px");
		h3.add(day);
		p.add(h3);
		
		p.add(buildAggregationPanel(true));
		
		return p;
	}
	
	private ListBox createLatestYearList(int years) {
		latestYearList = new ListBox();
		latestYearList.setWidth("75px");
		for (int i = 0 ; i <= years ; i++)
			latestYearList.addItem(String.valueOf(i), String.valueOf(i));
		return latestYearList;
	}
	
	private ListBox createLatestMontList() {
		latestMonthList = new ListBox();
		latestMonthList.setWidth("75px");
		for (int i = 0 ; i <= 12 ; i++)
			latestMonthList.addItem(String.valueOf(i), String.valueOf(i));
		return latestMonthList;
	}
	
	private ListBox createLatestDayList() {
		latestDayList = new ListBox();
		latestDayList.setWidth("75px");
		for (int i = 0 ; i <= 31 ; i++)
			latestDayList.addItem(String.valueOf(i), String.valueOf(i));
		return latestDayList;
	}

	public ContentPanel build(String title, boolean hasDataSource, String iconStyle) {
		axisPanel.setHeading(title);
		axisPanel.add(buildSelectorsPanel(hasDataSource));
		axisPanel.setData("dimensions", dimensions);
		axisPanel.setData("values", values);
		axisPanel.setIconStyle(iconStyle);
		return axisPanel;
	}
	
	public VerticalPanel buildSelectorsPanel(boolean hasDataSource) {
		VerticalPanel selectorsPanel = new VerticalPanel();
		selectorsPanel.setSpacing(SPACING);
		if (hasDataSource)
			selectorsPanel.add(buildDataSourcePanel());
		selectorsPanel.add(buildDimensionsPanel());
		selectorsPanel.add(buildValuesPanel());
		selectorsPanel.add(buildResetPanel());
		selectorsPanel.add(buildAggregationPanel(false)); // TODO only if there are date values
		return selectorsPanel;
	}
	
	private VerticalPanel buildAggregationPanel(boolean enable) {
		VerticalPanel aggregationPanel = new VerticalPanel();
		aggregateAsMonthly = new CheckBox();
		aggregateAsMonthly.setBoxLabel("Aggregate as monthly data");
		aggregateAsMonthly.setEnabled(enable);
		aggregationPanel.add(aggregateAsMonthly);
		aggregateAsYearly = new CheckBox();
		aggregateAsYearly.setBoxLabel("Aggregate as yearly data");
		aggregateAsYearly.setEnabled(enable);
		aggregationPanel.add(aggregateAsYearly);
		return aggregationPanel;
	}
	
	private VerticalPanel buildDataSourcePanel() {
		VerticalPanel dataSourcePanel = new VerticalPanel();
//		dataSourcePanel.setSpacing(SPACING);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().dataSource()+"</font></b>");
		label.setWidth(LABEL_WIDTH);
		dataSource.setWidth(FIELD_WIDTH);
		dataSource.setMultipleSelect(true);
		dataSource.setVisibleItemCount(5);
		dataSourcePanel.add(label);
		dataSourcePanel.add(dataSource);
		OlapController.fillDataSourceList(dataSource);
		// dataSource.addChangeListener(OlapController.dataSourceChangeListener(dimensions));
		return dataSourcePanel;
	}
	
	private VerticalPanel buildDimensionsPanel() {
		VerticalPanel dimensionsPanel = new VerticalPanel();
//		dimensionsPanel.setSpacing(SPACING);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().dimensions()+"</font></b>");
		label.setWidth(LABEL_WIDTH);
		dimensions.setWidth(FIELD_WIDTH);
		dimensionsPanel.add(label);
		dimensionsPanel.add(dimensions);
		return dimensionsPanel;
	}
	
	@SuppressWarnings("deprecation")
	private VerticalPanel buildValuesPanel() {
		VerticalPanel valuesPanel = new VerticalPanel();
//		valuesPanel.setSpacing(SPACING);
		HTML label = new HTML("<b><font color='#1D4589'>"+BabelFish.print().values()+"</font></b>");
		label.setWidth(LABEL_WIDTH);
		values.setWidth(FIELD_WIDTH);
		values.setMultipleSelect(true);
		values.setVisibleItemCount(5);
		valuesPanel.add(label);
		valuesPanel.add(values);
		return valuesPanel;
	}
	
	private HorizontalPanel buildResetPanel() {
		HorizontalPanel resetPanel = new HorizontalPanel();
//		resetPanel.setSpacing(SPACING);
		resetPanel.setWidth(FIELD_WIDTH);
//		resetPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);
//		resetPanel.setBorders(true);
		resetDimension = new Button(BabelFish.print().reset());
		resetDimension.setWidth(FIELD_WIDTH);
		resetDimension.addSelectionListener(OlapController.reset(this));
		resetDimension.setIconStyle("undo");
		resetPanel.add(resetDimension);
		return resetPanel;
	}

	public ListBox getDimensions() {
		return dimensions;
	}

	public ListBox getValues() {
		return values;
	}

	public CheckBox getAggregateSubLevel() {
		return aggregateSubLevel;
	}

	public Button getShowUpperLevel() {
		return showUpperLevel;
	}

	public Button getShowLowerLevel() {
		return showLowerLevel;
	}

	public Map<String, Map<String, String>> getUniqueValuesMap() {
		return uniqueValuesMap;
	}

	public CheckBox getAggregateAsMonthly() {
		return aggregateAsMonthly;
	}

	public CheckBox getAggregateAsYearly() {
		return aggregateAsYearly;
	}

	public String uniqueValuesToString() {
		String s = "";
		for (String key : uniqueValuesMap.keySet()) {
			s += key + ":\n";
			for (String code : uniqueValuesMap.get(key).keySet())
				s += "\t" + code + ": " + uniqueValuesMap.get(key).get(code) + "\n";
		}
		return s;
	}

	public ContentPanel getAxisPanel() {
		return axisPanel;
	}

	public Button getStandardViewButton() {
		return standardViewButton;
	}

	public Button getFromDateToDateViewButton() {
		return fromDateToDateViewButton;
	}

	public Button getMostRecentDataViewButton() {
		return mostRecentDataViewButton;
	}

	public boolean isUseFromDateToDate() {
		return useFromDateToDate;
	}

	public void setUseFromDateToDate(boolean useFromDateToDate) {
		this.useFromDateToDate = useFromDateToDate;
	}

	public boolean isUseMostRecentData() {
		return useMostRecentData;
	}

	public void setUseMostRecentData(boolean useMostRecentData) {
		this.useMostRecentData = useMostRecentData;
	}

	public DateField getToDateField() {
		return toDateField;
	}

	public ListBox getLatestYearList() {
		return latestYearList;
	}

	public ListBox getLatestMonthList() {
		return latestMonthList;
	}

	public ListBox getLatestDayList() {
		return latestDayList;
	}

	public DateField getFromDateField() {
		return fromDateField;
	}

	public String getAXIS() {
		return AXIS;
	}

	public void setAXIS(String aXIS) {
		AXIS = aXIS;
	}
	
}
