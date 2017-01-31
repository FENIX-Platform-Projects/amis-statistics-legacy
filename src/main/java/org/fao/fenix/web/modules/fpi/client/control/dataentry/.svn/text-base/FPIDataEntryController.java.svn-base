package org.fao.fenix.web.modules.fpi.client.control.dataentry;

import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.fpi.client.view.GAULCodeSelector;
import org.fao.fenix.web.modules.fpi.client.view.HSCodeSelector;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
import org.fao.fenix.web.modules.fpi.common.vo.DataEntryVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FPIDataEntryController {

	public static Grid grid;

	public static ChangeListener buildDayChangeListener(final ListBox weekList, final ListBox monthList, final ListBox yearList, final VerticalPanel dailyPanel, final Grid weeklyGrid, final Grid monthlyGrid) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				buildUpdateDailyDataEntry(weekList, monthList, yearList, dailyPanel, weeklyGrid, monthlyGrid);
			}
		};
	}

	public static ChangeListener buildMonthChangeListener(final ListBox weekList, final ListBox monthList, final ListBox yearList, final VerticalPanel dailyPanel, final Grid weeklyGrid, final Grid monthlyGrid) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				buildUpdateDailyDataEntry(weekList, monthList, yearList, dailyPanel, weeklyGrid, monthlyGrid);
			}
		};
	}

	public static ChangeListener buildYearChangeListener(final ListBox weekList, final ListBox monthList, final ListBox yearList, final VerticalPanel dailyPanel, final VerticalPanel weeklyPanel, final VerticalPanel monthlyPanel) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				Grid weeklyGrid = weeklyPanel.getData("grid");
				Grid monthlyGrid = monthlyPanel.getData("grid");
				buildUpdateDailyDataEntry(weekList, monthList, yearList, dailyPanel, weeklyGrid, monthlyGrid);
				buildUpdateWeeklyDataEntry(weekList, monthList, yearList, weeklyPanel, monthlyPanel);
			}
		};
	}

	/** Implementation of the listener to change the daily data entry tab panel. */
	public static void buildUpdateDailyDataEntry(final ListBox weekList, final ListBox monthList, final ListBox yearList, final VerticalPanel dailyPanel, final Grid weeklyGrid, final Grid monthlyGrid) {
		Grid grid = dailyPanel.getData("grid");
		clearGrid(grid);
		int collectionDate = weekList.getSelectedIndex() + 1;
		int year = Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex()));
		int month = monthList.getSelectedIndex();
		Date date = calculateStartDate(collectionDate, new Date(year - 1900, month, 1));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				grid.setWidget(i, j, buildDay(date, i + 1, j, month, grid, weeklyGrid, monthlyGrid));
				date = new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
			}
		}
		for (int i = 0; i < 5; i++)
			grid.setWidget(i, 7, FPIDataEntryController.buildDailyAverage(0));
	}

	/** Implementation of the listener to change the monthly data entry tab panel. */
	public static void buildUpdateWeeklyDataEntry(final ListBox weekList, final ListBox monthList, final ListBox yearList, final VerticalPanel weeklyPanel, final VerticalPanel monthlyPanel) {
		Grid weeklyGrid = weeklyPanel.getData("grid");
		Grid monthlyGrid = monthlyPanel.getData("grid");
		clearWeeklyGrid(weeklyGrid);
		int collectionDate = weekList.getSelectedIndex() + 1;
		int year = Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex()));
		for (int i = 0; i < weeklyGrid.getRowCount(); i++) {
			int weeksInAMonth = FPIDataEntryController.calculateWeeksInAMonth(year, i, collectionDate);
			for (int j = 1; j <= weeksInAMonth; j++) {
				weeklyGrid.setWidget(i, j, FPIDataEntryController.buildWeek(i, j - 1, weeksInAMonth, weeklyGrid, monthlyGrid, 0));
			}
		}
	}

	public static ChangeListener buildDayly2WeeklyListener(final int weekNumber, final Grid weeklyGrid) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				FenixAlert.alert("DAILY 2 WEEKLY", weeklyGrid.toString());
			}
		};
	}

	public static ChangeListener buildDailyTextBoxListener(final int weekNumber, final int day, final Grid grid, final Grid weeklyGrid, final Grid monthlyGrid, final int month) {
		return new ChangeListener() {
			public void onChange(Widget widget) {

				if (validContent((TextBox) widget)) {
					FPIDataEntryConstants.week2ArrayMap.get(weekNumber)[day] = Double.valueOf(((TextBox) widget).getText());
					grid.clearCell(weekNumber - 1, 7);
					grid.setWidget(weekNumber - 1, 7, buildDailyAverage(calculateAverage(FPIDataEntryConstants.week2ArrayMap.get(weekNumber))));

					weeklyGrid.clearCell(month, weekNumber);
					weeklyGrid.setWidget(month, weekNumber, updateDailyAverage(calculateAverage(FPIDataEntryConstants.week2ArrayMap.get(weekNumber)), weekNumber, month, 4, weeklyGrid, monthlyGrid));
					FPIDataEntryConstants.month2ArrayMap.get(month)[weekNumber - 1] = calculateAverage(FPIDataEntryConstants.week2ArrayMap.get(weekNumber));
				} else {
					FenixAlert.alert(BabelFish.print().alert(), "Invalid content: " + ((TextBox) widget).getText());
					((TextBox) widget).setText("-1");
				}

			}
		};
	}

	public static boolean validContent(TextBox textbox) {
		try {
			Double.parseDouble(textbox.getText());
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static ChangeListener buildWeeklyTextBoxListener(final int month, final int week, final int weeks, final Grid grid, final Grid monthlyGrid) {
		return new ChangeListener() {
			public void onChange(Widget widget) {

				if (validContent((TextBox) widget)) {
					FPIDataEntryConstants.month2ArrayMap.get(month)[week] = Double.valueOf(((TextBox) widget).getText());
					updateMonthAverage(FPIDataEntryConstants.month2ArrayMap.get(month), month, weeks, grid);

					int row = calculateMonthRow(month);
					int col = calculateMonthColumn(month);

					monthlyGrid.setWidget(row, col, buildMonth(month, calculateMonthAverage(FPIDataEntryConstants.month2ArrayMap.get(month), weeks)));
				} else {
					FenixAlert.alert(BabelFish.print().alert(), "Invalid content: " + ((TextBox) widget).getText());
					((TextBox) widget).setText("-1");
				}

			}
		};
	}

	public static int calculateMonthColumn(int month) {
		if (month == 1 || month == 5 || month == 9)
			return 1;
		if (month == 2 || month == 6 || month == 10)
			return 2;
		if (month == 3 || month == 7 || month == 11)
			return 3;
		return 0;
	}

	public static int calculateMonthRow(int month) {
		if (month >= 4 && month <= 7)
			return 1;
		if (month >= 8 && month <= 11)
			return 2;
		return 0;
	}

	public static void updateMonthAverage(double[] monthArray, int month, int weeks, Grid grid) {
		grid.clearCell(month, 6);
		double monthAverage = calculateMonthAverage(monthArray, weeks);
		grid.setWidget(month, 6, buildWeeklyAverage(monthAverage));
		FPIDataEntryConstants.year[month] = monthAverage;
	}

	public static double calculateMonthAverage(double[] month, int weeks) {
		double total = 0;
		for (int i = 0; i < weeks; i++)
			if (month[i] >= 0)
				total += month[i];
		double average = total / weeks;
		// to cut decimals
		return Math.floor(average * 10000) / 10000;
	}

	public static double calculateAverage(double[] week) {
		double sum = 0;
		for (int i = 0; i < week.length; i++)
			if (week[i] >= 0)
				sum += week[i];
		double average = sum / week.length;
		// to cut decimals
		return Math.floor(average * 10000) / 10000;
	}

	public static Date calculateStartDate(int collectionDay, Date date) {
		if (date.getDay() != collectionDay)
			return calculateStartDate(collectionDay, new Date(date.getYear(), date.getMonth(), date.getDate() - 1));
		return date;
	}

	public static void clearGrid(Grid grid) {
		for (int i = grid.getColumnCount() - 1; i >= 0; i--)
			for (int j = grid.getRowCount() - 2; j >= 0; j--)
				grid.clearCell(j, i);
	}

	public static void clearWeeklyGrid(Grid grid) {
		for (int i = 0; i < 12; i++)
			for (int j = 1; j < 6; j++)
				grid.clearCell(i, j);
	}

	public static VerticalPanel buildDay(Date date, int week, int day, int month, Grid grid, Grid weeklyGrid, Grid monthlyGrid) {
		VerticalPanel dayPanel = new VerticalPanel();
		dayPanel.add(new HTML(FPIDataEntryConstants.dayMap.get(date.getDay()).substring(0, 3) + " " + date.getDate()));
		TextBox dayTextBox = new TextBox();
		dayTextBox.setText("-1");
		dayTextBox.addChangeListener(buildDailyTextBoxListener(week, day, grid, weeklyGrid, monthlyGrid, month));
		dayPanel.add(dayTextBox);
		dayPanel.setWidth("50px");
		dayPanel.setData("day", dayTextBox);
		dayPanel.setSpacing(5);
		return dayPanel;
	}

	public static VerticalPanel buildWeek(int month, int week, int weeks, Grid grid, Grid monthlyGrid, double value) {
		VerticalPanel weekPanel = new VerticalPanel();
		weekPanel.add(new HTML("#" + week));
		TextBox weekTextBox = new TextBox();
		weekTextBox.setWidth("50px");
		weekTextBox.setText(String.valueOf(value));
		weekTextBox.addChangeListener(buildWeeklyTextBoxListener(month, week - 1, weeks, grid, monthlyGrid));
		weekPanel.add(weekTextBox);
		weekPanel.setWidth("75px");
		return weekPanel;
	}

	public static ClickListener buildRefreshMonthListener(final int month, final int weeks, final Grid weekGrid, final Grid monthlyGrid) {
		return new ClickListener() {
			public void onClick(Widget sender) {
				updateMonthAverage(FPIDataEntryConstants.month2ArrayMap.get(month), month, weeks, weekGrid);

				int row = calculateMonthRow(month);
				int col = calculateMonthColumn(month);
				double monthAverage = calculateMonthAverage(FPIDataEntryConstants.month2ArrayMap.get(month), weeks);
				monthlyGrid.setWidget(row, col, buildMonth(month, monthAverage));
				FPIDataEntryConstants.year[month] = monthAverage;
			}
		};
	}

	public static VerticalPanel buildMonth(int month, double value) {
		VerticalPanel monthPanel = new VerticalPanel();
		monthPanel.add(new HTML("<b>" + FPIDataEntryConstants.monthMap.get(month).substring(0, 3) + ":</b> "));
		TextBox monthTextBox = new TextBox();
		monthTextBox.setWidth("50px");
		monthTextBox.setText(String.valueOf(value));
		monthTextBox.addChangeListener(buildMonthSelectionListener(month));
		monthPanel.add(monthTextBox);
		monthPanel.setWidth("100px");
		monthPanel.setData("textbox", monthTextBox);
		return monthPanel;
	}

	public static ChangeListener buildMonthSelectionListener(final int month) {
		return new ChangeListener() {
			public void onChange(Widget widget) {

				if (validContent((TextBox) widget)) {
					TextBox textbox = (TextBox) widget;
					FPIDataEntryConstants.year[month] = Double.parseDouble(textbox.getText());
				} else {
					FenixAlert.alert(BabelFish.print().alert(), "Invalid content: " + ((TextBox) widget).getText());
					((TextBox) widget).setText("-1");
				}

			}
		};
	}

	public static VerticalPanel buildDailyAverage(final double value) {
		VerticalPanel avg = new VerticalPanel();
		avg.add(new HTML(BabelFish.print().avg()));
		Text average = new Text();
		average.setText(String.valueOf(value));
		average.setWidth("50px");
		average.disableTextSelection(true);
		avg.setData("average", average);
		avg.add(average);
		avg.setWidth("50px");
		return avg;
	}

	public static VerticalPanel updateDailyAverage(final double value, int weekNumber, int month, int weeks, Grid weekGrid, Grid monthlyGrid) {
		VerticalPanel avg = new VerticalPanel();
		avg.add(new HTML("#" + weekNumber));
		TextBox average = new TextBox();
		average.setText(String.valueOf(value));
		average.setWidth("50px");
		average.addChangeListener(buildWeeklyTextBoxListener(month, weekNumber - 1, weeks, weekGrid, monthlyGrid));
		avg.setData("average", average);
		avg.add(average);
		avg.setWidth("50px");
		return avg;
	}

	public static VerticalPanel buildWeeklyAverage(double value) {
		VerticalPanel avg = new VerticalPanel();
		avg.add(new HTML(BabelFish.print().avg()));
		Text average = new Text();
		average.setWidth("50px");
		avg.add(average);
		avg.setData("average", average);
		average.setText(String.valueOf(value));
		average.disableTextSelection(true);
		avg.setWidth("50px");
		return avg;
	}

	public static int calculateWeeksInAMonth(int year, int month, int collectionDay) {
		int weeks = 0;
		int days = 31;
		if (month == 3 || month == 5 || month == 8 || month == 10)
			days = 30;
		if (month == 1)
			days = 28;
		if (month == 1 && year % 4 == 0)
			days = 29;
		for (int i = 1; i <= days; i++) {
			Date date = new Date(year, month, i);
			if (date.getDay() == collectionDay + 1)
				weeks++;
		}
		return weeks;
	}

	public static SelectionListener<ButtonEvent> buildSelectParameterListener(final String parameter, final HorizontalPanel parameterPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (parameter.equals(BabelFish.print().gaulCode()))
					selectGaulCode(parameterPanel);
				if (parameter.equals(BabelFish.print().commodityLabel()))
					selectCommodityCode(parameterPanel);
			}
		};
	}

	protected static void selectGaulCode(HorizontalPanel parameterPanel) {
		GAULCodeSelector selector = new GAULCodeSelector();
		selector.build(parameterPanel);
	}

	protected static void selectCommodityCode(HorizontalPanel parameterPanel) {
		HSCodeSelector selector = new HSCodeSelector();
		selector.build(parameterPanel);
	}

	public static SelectionListener<ButtonEvent> showExistingMonthlyData(final VerticalPanel monthlyPanel, final ListBox yearList, final HorizontalPanel hsPanel, final HorizontalPanel gaulPanel, final HorizontalPanel pricePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				int year = Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex()));
//				System.out.println(">>>>>>>>>> " + yearList.getItemText(yearList.getSelectedIndex()));
				String gaulCode = (String) gaulPanel.getData("gaulCode");
				String commodityCode = (String) hsPanel.getData("hsCode");
				String datasetType = ((ListBox) pricePanel.getData("parameterValue")).getItemText(((ListBox) pricePanel.getData("parameterValue")).getSelectedIndex());
				FpiServiceEntry.getInstance().findAllPricesForDataEntry(datasetType, commodityCode, gaulCode, year, new AsyncCallback<List<DataEntryVo>>() {
					public void onSuccess(List<DataEntryVo> result) {
						for (int i = 0 ; i < 12 ; i++) {
							String key = "month_" + i;
							VerticalPanel monthPanel = (VerticalPanel) monthlyPanel.getData(key);
							TextBox monthBox = (TextBox) monthPanel.getData("textbox");
							monthBox.setText("-1.0");
						}
						for (int i = 0; i < result.size(); i++) {
							String key = "month_" + result.get(i).getDate().getMonth();
							VerticalPanel monthPanel = (VerticalPanel) monthlyPanel.getData(key);
							TextBox monthBox = (TextBox) monthPanel.getData("textbox");
							monthBox.setText(String.valueOf(result.get(i).getValue()));
						}
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "findAllPricesForDataEntry @ FPIDataEntryController");
					}
				});
			}
		};
	}

}