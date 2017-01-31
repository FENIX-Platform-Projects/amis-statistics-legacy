package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataEntryController;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;

public class DailyDataEntry {

	public static VerticalPanel panel;

	public static Map<Integer, Integer> monthLenghtMap;

	public static Grid grid;

	static {
		monthLenghtMap = new HashMap<Integer, Integer>();
		monthLenghtMap.put(0, 31);
		monthLenghtMap.put(1, 28);
		monthLenghtMap.put(2, 31);
		monthLenghtMap.put(3, 30);
		monthLenghtMap.put(4, 30);
		monthLenghtMap.put(5, 30);
		monthLenghtMap.put(6, 31);
		monthLenghtMap.put(7, 31);
		monthLenghtMap.put(8, 30);
		monthLenghtMap.put(9, 31);
		monthLenghtMap.put(10, 30);
		monthLenghtMap.put(11, 31);
	}

	public DailyDataEntry() {
		panel = new VerticalPanel();
		grid = new Grid(5, 8);
	}

	public VerticalPanel build(int collectionDay, int year, int month, Grid weeklyGrid, Grid monthlyGrid) {
		Date date = FPIDataEntryController.calculateStartDate(collectionDay, new Date(year - 1900, month, 1));
		Grid grid = new Grid(5, 8);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 7; j++) {
				grid.setWidget(i, j, FPIDataEntryController.buildDay(date, i + 1, j, month, grid, weeklyGrid, monthlyGrid));
				date = new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
			}
		for (int i = 0; i < 5; i++)
			grid.setWidget(i, 7, FPIDataEntryController.buildDailyAverage(0));
		panel.add(grid);
		panel.setData("grid", grid);
		panel.setWidth("400px");
		return panel;
	}

}