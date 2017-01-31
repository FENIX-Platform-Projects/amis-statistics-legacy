package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import java.util.Date;

import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataEntryController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class FPIDataEntryPanel {

	public static VerticalPanel panel;

	public static String[] months;

	public static String[] days;

	public static TabPanel tabPanel;

	public static ListBox weekList;

	public static ListBox monthList;

	public static ListBox yearList;

	public static DailyDataEntry dailyDataEntry;
	
	public static WeeklyDataEntry weeklyDataEntry;
	
	public static MonthlyDataEntry monthlyDataEntry;

	public static VerticalPanel dailyPanel;
	
	public static VerticalPanel weeklyPanel;
	
	public static VerticalPanel monthlyPanel;

	static {
		months = new String[] { BabelFish.print().january(), BabelFish.print().february(), BabelFish.print().march(), BabelFish.print().april(), BabelFish.print().may(), BabelFish.print().june(),
				BabelFish.print().july(), BabelFish.print().august(), BabelFish.print().september(), BabelFish.print().october(), BabelFish.print().november(), BabelFish.print().december() };
		days = new String[] { BabelFish.print().monday(), BabelFish.print().tuesday(), BabelFish.print().wednesday(), BabelFish.print().thursday(), BabelFish.print().friday(),
				BabelFish.print().saturday(), BabelFish.print().sunday() };
	}

	public FPIDataEntryPanel() {
		
		panel = new VerticalPanel();
		
		weekList = new ListBox();
		monthList = new ListBox();
		yearList = new ListBox();
		
		tabPanel = new TabPanel();
		
		dailyDataEntry = new DailyDataEntry();
		weeklyDataEntry = new WeeklyDataEntry();
		monthlyDataEntry = new MonthlyDataEntry();
		
		fillCollectionDay(weekList);
		fillMonthList(monthList);
		fillYearList(yearList);
		
		monthlyPanel = monthlyDataEntry.build();
		Grid monthlyGrid = monthlyPanel.getData("grid");
		
		weeklyPanel = weeklyDataEntry.build(weekList.getSelectedIndex() + 1, Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex())), monthlyGrid);
		Grid weeklyGrid = weeklyPanel.getData("grid");
		
		dailyPanel = dailyDataEntry.build(weekList.getSelectedIndex() + 1, Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex())), monthList.getSelectedIndex(), weeklyGrid, monthlyGrid);
		
		weekList.addChangeListener(FPIDataEntryController.buildDayChangeListener(weekList, monthList, yearList, dailyPanel, weeklyGrid, monthlyGrid));
		monthList.addChangeListener(FPIDataEntryController.buildMonthChangeListener(weekList, monthList, yearList, dailyPanel, weeklyGrid, monthlyGrid));
		yearList.addChangeListener(FPIDataEntryController.buildYearChangeListener(weekList, monthList, yearList, dailyPanel, weeklyPanel, monthlyPanel));
	}

	public VerticalPanel build() {
		panel.add(buildDateSelector(true));
		TabItem dailyTabPanel = new TabItem(BabelFish.print().dailyData());
		dailyTabPanel.add(dailyPanel);
		dailyTabPanel.setScrollMode(Scroll.AUTO);
		tabPanel.add(dailyTabPanel);
		TabItem weeklyTabPanel = new TabItem(BabelFish.print().weeklyData());
		weeklyTabPanel.add(weeklyPanel);
		weeklyTabPanel.setScrollMode(Scroll.AUTO);
		tabPanel.add(weeklyTabPanel);
		TabItem monthlyTabPanel = new TabItem(BabelFish.print().monthlyData());
		monthlyTabPanel.add(monthlyPanel);
		monthlyTabPanel.setScrollMode(Scroll.AUTO);
		tabPanel.add(monthlyTabPanel);
		tabPanel.setSelection(weeklyTabPanel);
		panel.add(tabPanel);
		panel.setData("tabPanel", tabPanel);
		panel.setData("monthlyPanel", monthlyPanel);
		return panel;
	}

	public HorizontalPanel buildDateSelector(boolean isDaily) {
		HorizontalPanel datePanel = new HorizontalPanel();
		datePanel.setSpacing(10);
		if (isDaily) {
			HTML weekLabel = new HTML(BabelFish.print().collectionDay());
			datePanel.add(weekLabel);
			datePanel.add(weekList);
			weekLabel.setWidth("75px");
			weekList.setWidth("100px");
			HTML monthLabel = new HTML(BabelFish.print().month());
			datePanel.add(monthLabel);
			datePanel.add(monthList);
			monthLabel.setWidth("50px");
			monthList.setWidth("100px");
		}
		HTML yearLabel = new HTML(BabelFish.print().year());
		datePanel.add(yearLabel);
		datePanel.add(yearList);
		yearLabel.setWidth("50px");
		yearList.setWidth("100px");
		return datePanel;
	}

	public void fillMonthList(ListBox list) {
		for (int i = 0; i < months.length; i++)
			list.addItem(months[i]);
	}

	public void fillYearList(ListBox list) {
		int current = new Date().getYear() + 1900;
		for (int i = current; i > current - 50; i--)
			list.addItem(String.valueOf(i));
	}
	
	public void fillCollectionDay(ListBox list) {
		for (int i = 0 ; i < days.length ; i++)
			list.addItem(days[i]);
	}

}
