package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataEntryController;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;

public class MonthlyDataEntry {

	public static VerticalPanel panel;

	public static Grid grid;
	
	public MonthlyDataEntry() {
		panel = new VerticalPanel();
		grid = new Grid(3, 4);
	}
	
	public VerticalPanel build() {
		Grid grid = new Grid(3, 4);
		int month = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) { 
				VerticalPanel monthPanel = FPIDataEntryController.buildMonth(month++, -1);
				grid.setWidget(i, j, monthPanel);
				String key = "month_" + (month - 1);
				panel.setData(key, monthPanel);
			}
		}
		panel.add(grid);
		panel.setData("grid", grid);
		panel.setSize("400px", "250px");
		return panel;
	}
	
}
