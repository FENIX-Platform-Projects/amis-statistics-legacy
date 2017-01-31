package org.fao.fenix.web.modules.birt.client.view.chart.wizard;

import org.fao.fenix.web.modules.birt.client.utils.DateUtils;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class DateFromToPanel  {
	
	private VerticalPanel main;
	
	private DateField fromDateField;
	
	private DateField toDateField;
	
	private DatePicker picker = new DatePicker();  
	
	private CheckBox checkBox;
	
	private ListBox xValues;
	
	private CheckBox xValuesCheckBox;
	
	private DateLastUpdatePanel dateLastUpdatePanel;
	
	private String width = new String();
	
	private String listWidth = "60px";
	
	private ListBox yearFrom;
	
	private ListBox monthFrom;
	
	private ListBox dayFrom;
	
	private ListBox yearTo;
	
	private ListBox monthTo;
	
	private ListBox dayTo;
	
	private String periodType;
	
	private HorizontalPanel xValuesPanel;
	
	private String fontSize;
	
	private String fontConlor;
	
	public VerticalPanel build(ListBox x, CheckBox xCheckBox, DateLastUpdatePanel dp, String w) {
		width = w;
		xValues = x;
		xValuesPanel = new HorizontalPanel();
		xValuesCheckBox = xCheckBox;
		dateLastUpdatePanel = dp;
		main = new VerticalPanel();
		checkBox = new CheckBox();
		main.setSpacing(5);
		main.add(buildCheckBox(null, null));
//		main.add(buildDateFieldFromDatePanel());
//		main.add(buildPicker());
//		main.add(buildDateFieldToDatePanel());	
		main.add(buildListBoxFromDatePanel(null, null));
		main.add(buildListBoxToDatePanel(null, null));
		disableListBox();
		return main;
	}
	
	public VerticalPanel build(HorizontalPanel x, CheckBox xCheckBox, DateLastUpdatePanel dp, String w,  String fontSize, String fontColor) {
		width = w;
		xValues = new ListBox();
		xValuesPanel = x;
		xValuesCheckBox = xCheckBox;
		dateLastUpdatePanel = dp;
		main = new VerticalPanel();
		checkBox = new CheckBox();
		main.setSpacing(3);
		main.add(buildCheckBox(fontSize, fontColor));
//		main.add(buildDateFieldFromDatePanel());
//		main.add(buildPicker());
//		main.add(buildDateFieldToDatePanel());	
		main.add(buildListBoxFromDatePanel(fontSize, fontColor));
		main.add(buildListBoxToDatePanel(fontSize, fontColor));
		disableListBox();
		return main;
	}
	
	
	
	public HorizontalPanel buildCheckBox(String fontSize, String fontColor){
		HorizontalPanel h = new HorizontalPanel();
		HTML label = new HTML("Use From Date - To Date (yyyy-mm-dd)");
		if ( fontSize != null && fontColor != null) 
			label = new HTML("<div style='color: "+ fontColor +"; font-weight: bold; font-size: "+ fontSize +";'>Use From Date - To Date (yyyy-mm-dd)</div>");
		h.add(checkBox);
		h.add(label);
		
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
	        public void handleEvent(ComponentEvent ce) {  
	        		System.out.println("select");
	        		setBox();
		        }
		    };  
		checkBox.addListener(Events.OnClick, t);
		return h;
	}
	
//	public HorizontalPanel buildPicker() {
//		HorizontalPanel datePanel = new HorizontalPanel();
//		HTML label = new HTML("From Date");
//		label.setStyleName("simpleText");
//		label.setWidth("83px");
//		picker = new DatePicker();
////		picker.set
////		fromDateField.setWidth(width);
//		datePanel.add(label);
//		datePanel.add(picker);
//		fromDateField.setEnabled(false);
//		return datePanel;
//	} 
	
//	public HorizontalPanel buildDateFieldFromDatePanel(String fontSize, String fontColor) {
//		HorizontalPanel datePanel = new HorizontalPanel();
//		HTML label = new HTML("From Date");
//		label.setStyleName("simpleText");
//		label.setWidth("83px");
//		fromDateField = new DateField();
//		fromDateField.setWidth(width);
//		datePanel.add(label);
//		datePanel.add(fromDateField);
//		fromDateField.setEnabled(false);
//		datePanel.setVisible(false);
//		return datePanel;
//	}
	
	
//	public HorizontalPanel buildDateFieldToDatePanel(String fontSize, String fontColor) {
//		HorizontalPanel datePanel = new HorizontalPanel();
//		HTML label = new HTML("To Date");
//		label.setWidth("83px");
//		label.setStyleName("simpleText");
//		toDateField = new DateField();
//		toDateField.setWidth(width);
//		datePanel.add(label);
//		datePanel.add(toDateField);
//		toDateField.setEnabled(false);
//		datePanel.setVisible(false);
//		return datePanel;
//	}
	
	
	public HorizontalPanel buildListBoxFromDatePanel(String fontSize, String fontColor) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(3);
		HTML label = new HTML("From Date");
		if ( fontSize != null && fontColor != null) 
			label = new HTML("<div style='color: "+ fontColor +"; font-weight: bold; font-size: "+ fontSize +";'>From date:</div>");
		label.setStyleName("simpleText");
		label.setWidth("83px");
		yearFrom = new ListBox();		
		yearFrom.setWidth(listWidth);
		monthFrom = new ListBox();
		monthFrom.setWidth(listWidth);
		dayFrom = new ListBox();
		dayFrom.setWidth(listWidth);
		panel.add(label);
		panel.add(yearFrom);
		panel.add(monthFrom);
		panel.add(dayFrom);
		return panel;
	}
	
	public HorizontalPanel buildListBoxToDatePanel(String fontSize, String fontColor) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(3);
		HTML label = new HTML("To Date:");
		if ( fontSize != null && fontColor != null) 
			label = new HTML("<div style='color: "+ fontColor +"; font-weight: bold; font-size: "+ fontSize +";'>To date:</div>");
		label.setStyleName("simpleText");
		label.setWidth("83px");
		yearTo = new ListBox();	
		yearTo.setWidth(listWidth);
		monthTo = new ListBox();
		monthTo.setWidth(listWidth);
		dayTo = new ListBox();
		dayTo.setWidth(listWidth);
		panel.add(label);
		panel.add(yearTo);
		panel.add(monthTo);
		panel.add(dayTo);
		return panel;
	}
	

	
	public void setBox() {
		if (checkBox.getValue()) {
//			fromDateField.setEnabled(true);
//			toDateField.setEnabled(true);
			xValues.setEnabled(false);
			xValuesPanel.setEnabled(false);
			enableListBox();
			DateUtils.disableDateFromToListBox(getPeriodType(), yearFrom, monthFrom, dayFrom, yearTo, monthTo, dayTo);
//			dateLastUpdatePanel.getMonths().setEnabled(false);
//			dateLastUpdatePanel.getYears().setEnabled(false);
//			dateLastUpdatePanel.getCheckBox().setValue(false);
//			enableListBox();
			dateLastUpdatePanel.getCheckBox().setValue(false);
			xValuesCheckBox.setValue(false);
			
		}
		else {
			disableListBox();
//			fromDateField.setEnabled(false);
//			toDateField.setEnabled(false);
			xValues.setEnabled(true);
			xValuesPanel.setEnabled(true);
			xValuesCheckBox.setValue(true);
		}
		
	}
	
	
	public void disableListBox() {
		checkBox.setValue(false);
		yearFrom.setEnabled(false);
		monthFrom.setEnabled(false);
		dayFrom.setEnabled(false);
		yearTo.setEnabled(false);
		monthTo.setEnabled(false);
		dayTo.setEnabled(false);
	}
	
	public void enableListBox() {
		yearFrom.setEnabled(true);
		monthFrom.setEnabled(true);
		dayFrom.setEnabled(true);
		yearTo.setEnabled(true);
		monthTo.setEnabled(true);
		dayTo.setEnabled(true);
	}
	
	public DateField getFromDateField() {
		return fromDateField;
	}


	public DateField getToDateField() {
		return toDateField;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public VerticalPanel getMain() {
		return main;
	}

	public DatePicker getPicker() {
		return picker;
	}

	public ListBox getxValues() {
		return xValues;
	}

	public DateLastUpdatePanel getDateLastUpdatePanel() {
		return dateLastUpdatePanel;
	}

	public String getWidth() {
		return width;
	}

	public String getListWidth() {
		return listWidth;
	}

	public ListBox getYearFrom() {
		return yearFrom;
	}

	public ListBox getMonthFrom() {
		return monthFrom;
	}

	public ListBox getDayFrom() {
		return dayFrom;
	}

	public ListBox getYearTo() {
		return yearTo;
	}

	public ListBox getMonthTo() {
		return monthTo;
	}

	public ListBox getDayTo() {
		return dayTo;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	


	
	

}
