package org.fao.fenix.web.modules.birt.client.view.chart.wizard;

import org.fao.fenix.web.modules.birt.client.utils.DateUtils;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DateLastUpdatePanel  {
	
	private VerticalPanel main;
	
	private ListBox years;
	
	private ListBox months;
	
	private ListBox days;
	
	private CheckBox checkBox;
	
	private CheckBox xValuesCheckBox;
	
	private ListBox xValues;
	
	private DateFromToPanel datePanel;
	
	private String width;
	
	private String periodType;
	
	public VerticalPanel build(ListBox x, CheckBox xCheckBox, DateFromToPanel dp, String w) {
		width = w;
		xValues = x;
		xValuesCheckBox = xCheckBox;
		datePanel = dp;
		main = new VerticalPanel();
		main.setSpacing(5);
		main.add(buildCheckBox());
		main.add(buildYearsPanel());
		main.add(buildMonthsPanel());	
		main.add(buildDaysPanel());	
		return main;
	}
	
	public HorizontalPanel buildCheckBox(){
		HorizontalPanel h = new HorizontalPanel();
		checkBox = new CheckBox();
		checkBox.setBoxLabel("Use Most Recent Data");
		h.add(checkBox);
		
		
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
	        public void handleEvent(ComponentEvent ce) {  
	        		setBox();
		        }
		    };  
		checkBox.addListener(Events.OnClick, t);
		return h;
	}
	
	public HorizontalPanel buildYearsPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		HTML label = new HTML("Years");
		label.setStyleName("simpleText");
		label.setWidth("83px");
		years = new ListBox();		
		years.setWidth(width);
		years.addItem("");
		for (int i=1; i < 100; i++) {
			years.addItem(Integer.toString(i));
		}		
		panel.add(label);
		panel.add(years);
		years.setEnabled(false);
		return panel;
	}
	
	public HorizontalPanel buildMonthsPanel() {
		HorizontalPanel datePanel = new HorizontalPanel();
		HTML label = new HTML("Months");
		label.setWidth("83px");
		label.setStyleName("simpleText");
		months = new ListBox();
		months.setWidth(width);
		months.addItem("");
		for (int i=1; i < 12; i++) {
			months.addItem(Integer.toString(i));
		}	
		datePanel.add(label);
		datePanel.add(months);
		months.setEnabled(false);
		return datePanel;
	}
	
	public HorizontalPanel buildDaysPanel() {
		HorizontalPanel datePanel = new HorizontalPanel();
		HTML label = new HTML("Days");
		label.setWidth("83px");
		label.setStyleName("simpleText");
		days = new ListBox();
		days.setWidth(width);
		days.addItem("");
		for (int i=1; i < 32; i++) {
			days.addItem(Integer.toString(i));
		}	
		datePanel.add(label);
		datePanel.add(days);
		days.setEnabled(false);
		return datePanel;
	}
	
	
	public void setBox() {
		if (checkBox.getValue()) {
//			years.setEnabled(true);
//			months.setEnabled(true);
//			days.setEnabled(true);			
			enableListBox();
			DateUtils.disableDateLastUpdateListBox(getPeriodType(), years, months, days);
//			datePanel.getFromDateField().setEnabled(false);
//			datePanel.getToDateField().setEnabled(false);
			datePanel.getCheckBox().setValue(false);
			xValues.setEnabled(false);
			xValuesCheckBox.setValue(false);
		}
		else {
			xValuesCheckBox.setValue(true);
			xValues.setEnabled(true);
			disableListBox();
		}
	}
	
	
	public void disableListBox() {
		checkBox.setValue(false);
		years.setEnabled(false);
		months.setEnabled(false);
		days.setEnabled(false);
	}
	
	public void enableListBox() {
		years.setEnabled(true);
		months.setEnabled(true);
		days.setEnabled(true);
	
	}


	public CheckBox getCheckBox() {
		return checkBox;
	}

	public ListBox getYears() {
		return years;
	}

	public ListBox getMonths() {
		return months;
	}

	public ListBox getDays() {
		return days;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	
	
	

}
