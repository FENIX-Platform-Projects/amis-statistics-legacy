package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataEntryController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;

public class WeeklyDataEntry {

	public static VerticalPanel panel;

	public static String[] months;

	public static Grid grid;

	static {
		months = new String[] { BabelFish.print().january(), BabelFish.print().february(), BabelFish.print().march(), BabelFish.print().april(), BabelFish.print().may(), BabelFish.print().june(),
				BabelFish.print().july(), BabelFish.print().august(), BabelFish.print().september(), BabelFish.print().october(), BabelFish.print().november(), BabelFish.print().december() };
	}

	public WeeklyDataEntry() {
		panel = new VerticalPanel();
		grid = new Grid(12, 8);
	}

	public VerticalPanel build(int collectionDay, int year, Grid monthlyGrid) {
		for (int i = 0; i < 12; i++) {
			HTML html = new HTML("<b>" + months[i].substring(0, 3) + ": </b>");
			html.setWidth("50px");
			grid.setWidget(i, 0, html);
		}
		for (int i = 0; i < grid.getRowCount(); i++) {
			int weeksInAMonth = FPIDataEntryController.calculateWeeksInAMonth(year, i, collectionDay);
			for (int j = 1; j <= weeksInAMonth; j++) {
				grid.setWidget(i, j, FPIDataEntryController.buildWeek(i, j, weeksInAMonth, grid, monthlyGrid, -1));
				grid.setWidget(i, 7, buildRefreshMonthButton(i, weeksInAMonth, grid, monthlyGrid));
			}
		}
		for (int i = 0; i < 12; i++) 
			grid.setWidget(i, 6, FPIDataEntryController.buildWeeklyAverage(0));
		panel.add(grid);
		panel.setData("grid", grid);
		panel.setSize("630px", "270px");
		panel.setScrollMode(Scroll.ALWAYS);
		return panel;
	}
	
	public Button buildRefreshMonthButton(final int month, final int weeks, final Grid weekGrid, final Grid monthlyGrid) {
		Button refresh = new Button("Refresh");
		refresh.addClickListener(FPIDataEntryController.buildRefreshMonthListener(month, weeks, weekGrid, monthlyGrid));
		return refresh;
	}

}
