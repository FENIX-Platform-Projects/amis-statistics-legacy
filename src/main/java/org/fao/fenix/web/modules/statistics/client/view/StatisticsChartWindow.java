
package org.fao.fenix.web.modules.statistics.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

public class StatisticsChartWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "900px";

	private static final String WINDOW_HEIGHT = "600px";

	private final static int SPACING = 10;
	
	private StatisticsChartPanel statisticsChartPanel;

	public StatisticsChartPanel getStatisticsChartPanel() {
		return statisticsChartPanel;
	}

	public StatisticsChartWindow() {
		super();
		statisticsChartPanel = new StatisticsChartPanel();
	}
	public StatisticsChartWindow(String imagePath) {
		super();
		statisticsChartPanel = new StatisticsChartPanel(imagePath);
	}

	public void build() {
		buildCenterPanel();
		format();
	
		//FenixAlert.error(BabelFish.print().error(),"142 pm manik:");
		
		show();
	
		
		/*statisticsPanel.getCmdSyntaxComboBox().addListener(Events.SelectionChange,StatisticsWindowController.addSyntax(this));
		statisticsPanel.getExecuteButton().addSelectionListener(StatisticsWindowController.execute(this));
		statisticsPanel.getHistoryButton().addSelectionListener(StatisticsWindowController.history(this));
		StatisticsWindowController.fillCmdSyntaxStore(this);
		StatisticsWindowController.loadConsoleMapStore(this);*/
		
	}
	
	private void buildCenterPanel() {
		
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(statisticsChartPanel.build());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
		
	/*	setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);*/
		//getCenter().add();
		//getCenterData().setSize(300);
		//addCenterPartToWindow();
	}
	
	public void setImage(Image  image){
		statisticsChartPanel.setImage(image);
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("FENIX Statistical Chart Tool");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("esoko");
		getWindow().setCollapsible(false);
	}
}