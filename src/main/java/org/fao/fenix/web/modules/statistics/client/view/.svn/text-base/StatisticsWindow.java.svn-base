package org.fao.fenix.web.modules.statistics.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.statistics.client.control.StatisticsWindowController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class StatisticsWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "900px";

	private static final String WINDOW_HEIGHT = "600px";

	private final static int SPACING = 10;
	
	private StatisticsPanel statisticsPanel;

	public StatisticsPanel getStatisticsPanel() {
		return statisticsPanel;
	}

	public StatisticsWindow() {
		statisticsPanel = new StatisticsPanel();
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
		
		
		statisticsPanel.getCmdSyntaxComboBox().addListener(
				Events.SelectionChange,StatisticsWindowController.addSyntax(this));
		statisticsPanel.getExecuteButton().addSelectionListener(
				StatisticsWindowController.execute(this));
		statisticsPanel.getHistoryButton().addSelectionListener(
				StatisticsWindowController.history(this));
		StatisticsWindowController.fillCmdSyntaxStore(this);
		StatisticsWindowController.loadConsoleMapStore(this);
		
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenter().add(statisticsPanel.build());
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("FENIX Statistical Tool");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("esoko");
		getWindow().setCollapsible(false);
	}
}