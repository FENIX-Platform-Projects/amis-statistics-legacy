package org.fao.fenix.web.modules.chartdesigner.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartAxisTabPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ChartTypePanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.DataFrequencyPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.DatasourcePanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.LookAndFeelTabPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.ManualPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.PlotDimensionPanel;
import org.fao.fenix.web.modules.chartdesigner.client.view.steps.RPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class ChartDesignerPanel {

	private ContentPanel panel;

	private CardLayout layout;
	
	private ManualPanel manualPanel;
	
	private ChartTypePanel chartTypePanel;
	
	private DatasourcePanel datasourcePanel;
	
	private ChartAxisPanel xAxisPanel;
	
	private ChartAxisPanel yAxisPanel;
	
	private ChartAxisTabPanel axisTabPanel;
	
	private RPanel rPanel;
	
	private DataFrequencyPanel dataFrequencyPanel;
	
	private PlotDimensionPanel plotDimensionPanel;
	
	private LookAndFeelPanel lookAndFeelPanel;
	
	private LookAndFeelTabPanel lookAndFeelTabPanel;
	
	private ChartPanel chartPanel;
	
	private Button chartTypeButton;
	
	private Button datasourceButton;
	
	private Button xAxisButton;
	
	private Button yAxisButton;
	
	private Button rButton;
	
	private Button plotDimensionButton;
	
	private Button lookAndFeelButton;
	
	private Button chartButton;
	
	private Button addToReportButton;
	
	private Button dataFrequencyButton;
	
	private Button saveButton;
	
	private final static String STEP_WIDTH = "600px";
	
	private final static String BUTTON_WIDTH = "162px";
	
	private final static int SPACING = 3;
	
	private List<Long> datasourceDatasetIDs;
	
	private List<Long> xAxisDatasetIDs;
	
	private List<Long> yAxisDatasetIDs;
	
	private List<Long> plotDimensionDatasetIDs;

	public ChartDesignerPanel() {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		manualPanel = new ManualPanel();
		chartTypePanel = new ChartTypePanel("chart_designer_chart_type_suggestion", STEP_WIDTH);
		datasourcePanel = new DatasourcePanel("chart_designer_datasource_suggestion", STEP_WIDTH);
		xAxisPanel = new ChartAxisPanel("chart_designer_x_axis_suggestion", STEP_WIDTH);
		yAxisPanel = new ChartAxisPanel("chart_designer_y_axis_suggestion", STEP_WIDTH);
		axisTabPanel = new ChartAxisTabPanel("chart_designer_tab_axis_suggestion", STEP_WIDTH);
		rPanel = new RPanel("chart_designer_r_suggestion", STEP_WIDTH);
		dataFrequencyPanel = new DataFrequencyPanel("chart_designer_data_frequency_suggestion", STEP_WIDTH);
		plotDimensionPanel = new PlotDimensionPanel("chart_designer_plot_dimension_suggestion", STEP_WIDTH);
		lookAndFeelPanel = new LookAndFeelPanel("chart_designer_look_and_feel_suggestion", STEP_WIDTH);
		lookAndFeelTabPanel = new LookAndFeelTabPanel("chart_designer_look_and_feel_suggestion", STEP_WIDTH); 
		chartPanel = new ChartPanel("chart_designer_chart_panel_suggestion", STEP_WIDTH);
		chartTypeButton = new Button(BabelFish.print().chart_designer_chart_type());
		datasourceButton = new Button(BabelFish.print().chart_designer_datasource());
		datasourceButton.setEnabled(false);
		xAxisButton = new Button(BabelFish.print().chart_designer_x_axis());
		xAxisButton.setEnabled(false);
		yAxisButton = new Button(BabelFish.print().chart_designer_tab_axis());
		yAxisButton.setEnabled(false);
		rButton = new Button(BabelFish.print().chart_designer_r());
		rButton.setEnabled(false);
		plotDimensionButton = new Button(BabelFish.print().chart_designer_plot_dimension());
		plotDimensionButton.setEnabled(false);
		lookAndFeelButton = new Button(BabelFish.print().chart_designer_look_and_feel());
		lookAndFeelButton.setEnabled(false);
		chartButton = new Button(BabelFish.print().chart_designer_chart_panel());
		chartButton.setEnabled(false);
		addToReportButton = new Button("Add To Report");
		dataFrequencyButton = new Button(BabelFish.print().chart_designer_data_frequency());
		dataFrequencyButton.setEnabled(false);
		saveButton = new Button(BabelFish.print().save());
		saveButton.setEnabled(false);
		datasourceDatasetIDs = new ArrayList<Long>();
		xAxisDatasetIDs = new ArrayList<Long>();
		yAxisDatasetIDs = new ArrayList<Long>();
		plotDimensionDatasetIDs = new ArrayList<Long>();
	}
	
	public ContentPanel build(String caller) {
		
		// build steps
		panel.add(manualPanel.build());
		panel.add(chartTypePanel.build());
		panel.add(datasourcePanel.build());
		panel.add(xAxisPanel.build());
		panel.add(axisTabPanel.build());
		panel.add(dataFrequencyPanel.build());
		panel.add(rPanel.build());
		panel.add(plotDimensionPanel.build());
		panel.add(lookAndFeelTabPanel.build());
		panel.add(chartPanel.build());
		
		// add buttons
		panel.setBottomComponent(buildButtonsPanel(caller));
		
		// return panel
		return panel;
	}
	
	private HorizontalPanel buildButtonsPanel(String caller) {
		
		HorizontalPanel wrapper = new HorizontalPanel();
		
		VerticalPanel leftPanel = new VerticalPanel();
		
		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSpacing(SPACING);
		
		HorizontalPanel topRow = new HorizontalPanel();
		topRow.setSpacing(SPACING);
		
		HorizontalPanel bottomRow = new HorizontalPanel();
		bottomRow.setSpacing(SPACING);
		
//		chartTypeButton.setWidth(BUTTON_WIDTH);
//		chartTypeButton.setIconStyle("chartType");
//		chartTypeButton.addSelectionListener(ChartDesignerController.panelSelector(this, chartTypePanel.getLayoutContainer()));
//		topRow.add(chartTypeButton);
		
		datasourceButton.setWidth(BUTTON_WIDTH);
		datasourceButton.setIconStyle("datasource");
		datasourceButton.addSelectionListener(ChartDesignerController.panelSelector(this, datasourcePanel.getLayoutContainer()));
		topRow.add(datasourceButton);
		
		xAxisButton.setWidth(BUTTON_WIDTH);
		xAxisButton.setIconStyle("checklist");
		xAxisButton.addSelectionListener(ChartDesignerController.panelSelector(this, xAxisPanel.getLayoutContainer()));
		topRow.add(xAxisButton);
		
		yAxisButton.setWidth(BUTTON_WIDTH);
		yAxisButton.setIconStyle("checklist");
		yAxisButton.addSelectionListener(ChartDesignerController.panelSelector(this, axisTabPanel.getLayoutContainer()));
		topRow.add(yAxisButton);
		
		plotDimensionButton.setWidth(BUTTON_WIDTH);
		plotDimensionButton.setIconStyle("filter");
		plotDimensionButton.addSelectionListener(ChartDesignerController.panelSelector(this, plotDimensionPanel.getLayoutContainer()));
		topRow.add(plotDimensionButton);
		
		lookAndFeelButton.setWidth(BUTTON_WIDTH);
		lookAndFeelButton.setIconStyle("sld");
		lookAndFeelButton.addSelectionListener(ChartDesignerController.lookAndFeelPanelSelector(this, lookAndFeelTabPanel.getLayoutContainer()));
		bottomRow.add(lookAndFeelButton);
		
		dataFrequencyButton.setWidth(BUTTON_WIDTH);
		dataFrequencyButton.setIconStyle("dataFrequency");
		dataFrequencyButton.addSelectionListener(ChartDesignerController.panelSelector(this, dataFrequencyPanel.getLayoutContainer()));
		bottomRow.add(dataFrequencyButton);
		
		rButton.setWidth(BUTTON_WIDTH);
		rButton.setIconStyle("R");
		rButton.addSelectionListener(ChartDesignerController.panelSelector(this, rPanel.getLayoutContainer()));
		bottomRow.add(rButton);
		
		saveButton.setWidth(BUTTON_WIDTH);
		saveButton.setIconStyle("save");
		bottomRow.add(saveButton);
			
		if (caller.equals(WhoCall.TINYMCE_ADD)) {
			chartButton.setWidth(BUTTON_WIDTH);
			chartButton.setIconStyle("workspaceChart");
			rightPanel.add(chartButton);
			addToReportButton.setWidth(BUTTON_WIDTH);
			addToReportButton.setIconStyle("reportIcon");
			rightPanel.add(addToReportButton);
		} else {
			chartButton.setWidth(BUTTON_WIDTH);
			chartButton.setHeight("50px");
			chartButton.setIconStyle("workspaceChart");
			rightPanel.add(chartButton);
		}
		
		leftPanel.add(topRow);
		leftPanel.add(bottomRow);
		
		wrapper.add(leftPanel);
		wrapper.add(rightPanel);
		
		return wrapper;
	}
	
	public void enableButtons() {
		this.getChartButton().setEnabled(true);
		this.getDatasourceButton().setEnabled(true);
		this.getxAxisButton().setEnabled(true);
		this.getyAxisButton().setEnabled(true);
		this.getPlotDimensionButton().setEnabled(true);
		this.getLookAndFeelButton().setEnabled(true);
		this.getrButton().setEnabled(true);
		this.getChartTypeButton().setEnabled(true);
		this.getSaveButton().setEnabled(true);
	}	

	public CardLayout getLayout() {
		return layout;
	}

	public ChartTypePanel getChartTypePanel() {
		return chartTypePanel;
	}

	public DatasourcePanel getDatasourcePanel() {
		return datasourcePanel;
	}

	public ChartAxisPanel getxAxisPanel() {
		return xAxisPanel;
	}

	public ChartAxisPanel getyAxisPanel() {
		return yAxisPanel;
	}

	public RPanel getrPanel() {
		return rPanel;
	}

	public PlotDimensionPanel getPlotDimensionPanel() {
		return plotDimensionPanel;
	}

	public LookAndFeelTabPanel getLookAndFeelTabPanel() {
		return lookAndFeelTabPanel;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public ManualPanel getManualPanel() {
		return manualPanel;
	}

	public List<Long> getDatasourceDatasetIDs() {
		return datasourceDatasetIDs;
	}

	public List<Long> getxAxisDatasetIDs() {
		return xAxisDatasetIDs;
	}

	public List<Long> getyAxisDatasetIDs() {
		return yAxisDatasetIDs;
	}

	public List<Long> getPlotDimensionDatasetIDs() {
		return plotDimensionDatasetIDs;
	}

	public Button getChartButton() {
		return chartButton;
	}

	public ChartAxisTabPanel getAxisTabPanel() {
		return axisTabPanel;
	}

	public Button getChartTypeButton() {
		return chartTypeButton;
	}

	public Button getDatasourceButton() {
		return datasourceButton;
	}

	public Button getxAxisButton() {
		return xAxisButton;
	}

	public Button getyAxisButton() {
		return yAxisButton;
	}

	public Button getrButton() {
		return rButton;
	}

	public Button getPlotDimensionButton() {
		return plotDimensionButton;
	}

	public Button getLookAndFeelButton() {
		return lookAndFeelButton;
	}

	public Button getDataFrequencyButton() {
		return dataFrequencyButton;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getAddToReportButton() {
		return addToReportButton;
	}

}