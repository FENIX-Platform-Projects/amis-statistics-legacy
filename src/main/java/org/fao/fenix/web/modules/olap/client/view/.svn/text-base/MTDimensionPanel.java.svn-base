package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.ListBox;

public class MTDimensionPanel extends MTStepPanel {
	
private VerticalPanel wrapper;
	
	private ListBox dimensionList;
	
	private ListBox valuesList;
	
	private ListBox latestYearsList;
	
	private ListBox latestMonthsList;
	
	private ListBox latestDaysList;
	
	private CheckBox aggregateMonthly;
	
	private CheckBox aggregateYearly;
	
	private CheckBox useFromDateToDate;
	
	private CheckBox useMostRecentData;
	
	private DateField fromDate;
	
	private DateField toDate;
	
	private final static int SPACING = 5;
	
	private final static int VISIBLE_ITEMS = 19;
	
	private final static String WRAPPER_HEIGHT = "530px";
	
	private final static String LARGE_FIELD_WIDTH = "375px";
	
	private final static String LARGE_FIELDSET_WIDTH = "400px";
	
	private final static String SMALL_FIELD_WIDTH = "225px";
	
	private final static String SMALL_FIELDSET_WIDTH = "250px";
	
	private final static String TINY_FIELDSET_WIDTH = "280px";
	
	private final static String SMALL_FIELDSET_HEIGHT = "150px";
	
	private final static String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private List<DescriptorViewVO> descriptorViewVOs;
	
	private FieldSet valuesFieldSet;
	
	private FieldSet dimensionsFieldSet;
	
	private FieldSet mostRecentDataFieldSet;
	
	private FieldSet fromDateToDateFieldSet;
	
	private FieldSet aggregateDateFieldSet;
	
	public MTDimensionPanel(String suggestion, String width) {
		super(suggestion, width);
		valuesFieldSet = new FieldSet();
		dimensionsFieldSet = new FieldSet();
		mostRecentDataFieldSet = new FieldSet();
		mostRecentDataFieldSet.setEnabled(false);
		fromDateToDateFieldSet = new FieldSet();
		fromDateToDateFieldSet.setEnabled(false);
		aggregateDateFieldSet = new FieldSet();
		aggregateDateFieldSet.setEnabled(false);
		latestYearsList = new ListBox();
		latestYearsList.setEnabled(false);
		MTController.fillLatestDataList(latestYearsList);
		latestMonthsList = new ListBox();
		latestMonthsList.setEnabled(false);
		MTController.fillLatestDataList(latestMonthsList);
		latestDaysList = new ListBox();
		latestDaysList.setEnabled(false);
		MTController.fillLatestDataList(latestDaysList);
		wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setSpacing(SPACING);
		wrapper.setHeight(WRAPPER_HEIGHT);
		dimensionList = new ListBox();
		valuesList = new ListBox();
		aggregateMonthly = new CheckBox();
		aggregateMonthly.setEnabled(false);
		aggregateYearly = new CheckBox();
		aggregateYearly.setEnabled(false);
		fromDate = new DateField();
		fromDate.setValue(new Date());
		toDate = new DateField();
		toDate.setValue(new Date());
		useFromDateToDate = new CheckBox();
		useFromDateToDate.setEnabled(true);
//		useFromDateToDate.addListener(Events.OnClick, MTController.enableValues(this));
		useMostRecentData = new CheckBox();
		useMostRecentData.setEnabled(true);
//		useMostRecentData.addListener(Events.OnClick, MTController.enableValues(this));
		descriptorViewVOs = new ArrayList<DescriptorViewVO>();
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	public VerticalPanel buildWrapperPanel() {
		wrapper.add(buildTopRow());
		wrapper.add(buildBottomRow());
		wrapper.setSpacing(SPACING);
		return wrapper;
	}
	
	private HorizontalPanel buildTopRow() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.add(buildDimensionsPanel());
		p.add(buildValuesPanel());
		return p;
	}
	
	private HorizontalPanel buildBottomRow() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(2 * SPACING);
		p.add(buildAggregationPanel());
		p.add(buildFromDateToDatePanel());
		p.add(buildMostRecentDataPanel());
		return p;
	}
	
	private FieldSet buildDimensionsPanel() {
		dimensionsFieldSet.setCheckboxToggle(false);
		dimensionsFieldSet.setHeading("Available Dimensions");
		dimensionsFieldSet.setWidth(LARGE_FIELDSET_WIDTH);
		dimensionsFieldSet.setBorders(true);
		dimensionList.setMultipleSelect(true);
		dimensionList.setVisibleItemCount(VISIBLE_ITEMS);
		dimensionList.setWidth(LARGE_FIELD_WIDTH);
		dimensionsFieldSet.add(dimensionList);
		return dimensionsFieldSet;
	}
	
	private FieldSet buildValuesPanel() {
		valuesFieldSet.setCheckboxToggle(false);
		valuesFieldSet.setHeading("Available Values");
		valuesFieldSet.setWidth(LARGE_FIELDSET_WIDTH);
		valuesFieldSet.setBorders(true);
		valuesList.setMultipleSelect(true);
		valuesList.setVisibleItemCount(VISIBLE_ITEMS);
		valuesList.setWidth(LARGE_FIELD_WIDTH);
		valuesFieldSet.add(valuesList);
		return valuesFieldSet;
	}
	
	private FieldSet buildAggregationPanel() {
		aggregateDateFieldSet.setCheckboxToggle(false);
		aggregateDateFieldSet.setCollapsible(false);
		aggregateDateFieldSet.setHeading("Date Aggregation");
		aggregateDateFieldSet.setWidth(SMALL_FIELDSET_WIDTH);
		aggregateDateFieldSet.setBorders(true);
		aggregateMonthly.setBoxLabel("Aggregate as Monthly Data");
		aggregateYearly.setBoxLabel("Aggregate as Yearly Data");
		aggregateDateFieldSet.add(aggregateMonthly);
		aggregateDateFieldSet.add(aggregateYearly);
		aggregateDateFieldSet.setHeight(SMALL_FIELDSET_HEIGHT);
//		aggregateDateFieldSet.collapse();
		return aggregateDateFieldSet;
	}
	
	private FieldSet buildFromDateToDatePanel() {
		
		fromDateToDateFieldSet.setCheckboxToggle(false);
		fromDateToDateFieldSet.setHeading("From Date To Date");
		fromDateToDateFieldSet.setWidth(SMALL_FIELDSET_WIDTH);
		fromDateToDateFieldSet.setBorders(true);
//		fromDateToDateFieldSet.collapse();
		fromDateToDateFieldSet.setHeight(SMALL_FIELDSET_HEIGHT);
		fromDateToDateFieldSet.setCollapsible(false);
		
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		
		useFromDateToDate.setBoxLabel("Use From Date To Date");
		Html fromDateLabel = new Html(TITLE + "From Date</div>");
		fromDateLabel.setWidth(SMALL_FIELD_WIDTH);
		fromDate.setWidth(SMALL_FIELD_WIDTH);
		Html toDateLabel = new Html(TITLE + "To Date</div>");
		toDateLabel.setWidth(SMALL_FIELD_WIDTH);
		toDate.setWidth(SMALL_FIELD_WIDTH);
		
		p.add(useFromDateToDate);
		p.add(fromDateLabel);
		p.add(fromDate);
		p.add(toDateLabel);
		p.add(toDate);
		
		fromDateToDateFieldSet.add(p);
		
		return fromDateToDateFieldSet;
	}
	
	private FieldSet buildMostRecentDataPanel() {
		
		mostRecentDataFieldSet.setCheckboxToggle(false);
		mostRecentDataFieldSet.setHeading("Most Recent Data");
//		mostRecentDataFieldSet.setWidth(SMALL_FIELDSET_WIDTH);
		mostRecentDataFieldSet.setWidth(TINY_FIELDSET_WIDTH);
		mostRecentDataFieldSet.setBorders(true);
		mostRecentDataFieldSet.setCollapsible(false);
		
		HorizontalPanel yp = new HorizontalPanel();
		yp.setSpacing(SPACING);
		yp.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html latestYearsLabel = new Html(TITLE + "Latest</div>");
		Html yearsLabel = new Html(TITLE + "Years</div>");
		yp.add(latestYearsLabel);
		yp.add(latestYearsList);
		yp.add(yearsLabel);
		
		HorizontalPanel mp = new HorizontalPanel();
		mp.setSpacing(SPACING);
		mp.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html latestMonthsLabel = new Html(TITLE + "Latest</div>");
		Html monthsLabel = new Html(TITLE + "Months</div>");
		mp.add(latestMonthsLabel);
		mp.add(latestMonthsList);
		mp.add(monthsLabel);
		
		HorizontalPanel dp = new HorizontalPanel();
		dp.setSpacing(SPACING);
		dp.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html latestDaysLabel = new Html(TITLE + "Latest</div>");
		Html daysLabel = new Html(TITLE + "Days</div>");
		dp.add(latestDaysLabel);
		dp.add(latestDaysList);
		dp.add(daysLabel);
		
		useMostRecentData.setBoxLabel("Use Most Recent Data");
		mostRecentDataFieldSet.add(useMostRecentData);
		mostRecentDataFieldSet.add(yp);
		mostRecentDataFieldSet.add(mp);
		mostRecentDataFieldSet.add(dp);
		
//		mostRecentDataFieldSet.collapse();
		mostRecentDataFieldSet.setHeight(SMALL_FIELDSET_HEIGHT);
		return mostRecentDataFieldSet;
	}
	
	public void enableDateSpecificFieldSets(boolean enabled) {
		System.out.println("enableDateSpecificFuntions? " + enabled);
		this.getAggregateDateFieldSet().setEnabled(enabled);
		this.getFromDateToDateFieldSet().setEnabled(enabled);
		this.getMostRecentDataFieldSet().setEnabled(enabled);
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ListBox getDimensionList() {
		return dimensionList;
	}

	public ListBox getValuesList() {
		return valuesList;
	}

	public ListBox getLatestYearsList() {
		return latestYearsList;
	}

	public ListBox getLatestMonthsList() {
		return latestMonthsList;
	}

	public ListBox getLatestDaysList() {
		return latestDaysList;
	}

	public CheckBox getAggregateMonthly() {
		return aggregateMonthly;
	}

	public CheckBox getAggregateYearly() {
		return aggregateYearly;
	}

	public CheckBox getUseFromDateToDate() {
		return useFromDateToDate;
	}

	public CheckBox getUseMostRecentData() {
		return useMostRecentData;
	}

	public DateField getFromDate() {
		return fromDate;
	}

	public DateField getToDate() {
		return toDate;
	}

	public List<DescriptorViewVO> getDescriptorViewVOs() {
		return descriptorViewVOs;
	}

	public FieldSet getValuesFieldSet() {
		return valuesFieldSet;
	}

	public FieldSet getDimensionsFieldSet() {
		return dimensionsFieldSet;
	}

	public FieldSet getMostRecentDataFieldSet() {
		return mostRecentDataFieldSet;
	}

	public FieldSet getFromDateToDateFieldSet() {
		return fromDateToDateFieldSet;
	}

	public FieldSet getAggregateDateFieldSet() {
		return aggregateDateFieldSet;
	}
	
}