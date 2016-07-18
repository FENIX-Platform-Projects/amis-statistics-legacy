package org.fao.fenix.web.modules.chartdesigner.client.view;

import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ChartDesignerWindow extends FenixWindow {

	private ChartDesignerPanel chartDesignerPanel;
	
	private ChartDesignerToolbar chartDesignerToolbar;
	
	private ChartDesignerManualWindow chartDesignerManualWindow;
	
	private Long chartDesignID = null;
	
	private String caller = WhoCall.USER;
	
	private String tinyMCEPanelID = ""; 
	
	private String originalWidth = "";
	
	private String originalHeight = "";
	
	public ChartDesignerWindow() {
		chartDesignerPanel = new ChartDesignerPanel();
		chartDesignerToolbar = new ChartDesignerToolbar();
		chartDesignerManualWindow = new ChartDesignerManualWindow();
	}
	
	public ChartDesignerWindow(String caller, String tinyMCEPanelID) {
		chartDesignerPanel = new ChartDesignerPanel();
		chartDesignerToolbar = new ChartDesignerToolbar();
		chartDesignerManualWindow = new ChartDesignerManualWindow();
		this.setCaller(caller);
		this.setTinyMCEPanelID(tinyMCEPanelID);
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
		
		for (int i = 0 ; i < getWindow().getHeader().getToolCount() ; i++) {
			ToolButton tb = ((ToolButton)getWindow().getHeader().getTool(i));
			if (tb.getStyleName().contains("x-tool-close")) {
				tb.removeAllListeners();
				tb.addSelectionListener(ChartDesignerController.onClose(this));
			}
		}
		
	}
	
	public void build(List<ResourceChildModel> models) {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(chartDesignerPanel.build(this.getCaller()));
		getCenter().setTopComponent(chartDesignerToolbar.build());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
		enhanceButtons();
		chartDesignerPanel.getLayout().setActiveItem(chartDesignerPanel.getChartTypePanel().getLayoutContainer());
	}
	
	private void enhanceButtons() {
		
		// toolbar
		chartDesignerToolbar.getManualButton().addSelectionListener(ChartDesignerController.showManual(this));
		chartDesignerToolbar.getCloneItem().addSelectionListener(ChartDesignerController.cloneChart(this));
		chartDesignerToolbar.getSaveMenuItem().addSelectionListener(ChartDesignerController.saveFromMenu(this));
		chartDesignerToolbar.getSaveAsMenuItem().addSelectionListener(ChartDesignerController.saveAs(this));
		chartDesignerToolbar.getImageMenuItem().addSelectionListener(ChartDesignerController.exportAsImage(this));
		chartDesignerToolbar.getEmbedItem().addSelectionListener(ChartDesignerController.exportAsHTML(this));
		chartDesignerToolbar.getExcelMenuItem().addSelectionListener(ChartDesignerController.exportAsExcel(this));
		chartDesignerToolbar.getCsvMenuItem().addSelectionListener(ChartDesignerController.exportAsCSV(this));
		
		// save from buttons pad
		chartDesignerPanel.getSaveButton().addSelectionListener(ChartDesignerController.saveFromButtonsPad(this));
		
		// view charts
		chartDesignerPanel.getChartButton().addSelectionListener(ChartDesignerController.viewChart(this));
		
		// add dataset
//		chartDesignerPanel.getDatasourcePanel().getAddDatasetButton().addSelectionListener(ChartDesignerController.addDataset(this, "LINE"));
		chartDesignerPanel.getDatasourcePanel().getBarMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "BAR"));
		chartDesignerPanel.getDatasourcePanel().getLineMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "LINE"));
//		chartDesignerPanel.getDatasourcePanel().getPieMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "PIE"));
		chartDesignerPanel.getDatasourcePanel().getPointsMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "POINT"));
//		chartDesignerPanel.getDatasourcePanel().getBoxplotMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "BOXPLOT"));
		chartDesignerPanel.getDatasourcePanel().getTargetMI().addSelectionListener(ChartDesignerController.addDatasetMenuItem(this, "TARGET"));
		
		// chart type selectors
		chartDesignerPanel.getChartTypePanel().getBarButton().addSelectionListener(ChartDesignerController.addDataset(this, "BAR"));
		chartDesignerPanel.getChartTypePanel().getLineButton().addSelectionListener(ChartDesignerController.addDataset(this, "LINE"));
		chartDesignerPanel.getChartTypePanel().getBoxplotButton().addSelectionListener(ChartDesignerController.addDataset(this, "BOXPLOT"));
		chartDesignerPanel.getChartTypePanel().getPointButton().addSelectionListener(ChartDesignerController.addDataset(this, "POINT"));
		chartDesignerPanel.getChartTypePanel().getTargetButton().addSelectionListener(ChartDesignerController.addDataset(this, "TARGET"));
		chartDesignerPanel.getChartTypePanel().getBarLineButton().addSelectionListener(ChartDesignerController.addDataset(this, "BARLINE"));
		chartDesignerPanel.getAddToReportButton().addSelectionListener(ChartDesignerController.addToReport(this));
		
		// window's title
		chartDesignerPanel.getLookAndFeelTabPanel().getGeneralSettings().getTitle().addListener(Events.KeyUp, ChartDesignerController.chartTitleListener(this));
	}
	
	private void format() {
		setSize("850px", "700px");
		getWindow().setHeading("Chart Designer");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("workspaceChart");
		getWindow().setCollapsible(false);
		getWindow().setMaximizable(true);
		getWindow().addListener(Events.Resize, ChartDesignerController.resize(this));
		getWindow().addWindowListener(ChartDesignerController.close(this));
		getWindow().setMinimizable(false);
		getWindow().setClosable(true);
	}
	
	public void enableToolbar() {
		this.getChartDesignerToolbar().getImageMenuItem().setEnabled(true);
		this.getChartDesignerToolbar().getEmbedItem().setEnabled(true);
	}
	
	public ChartDesignerPanel getChartDesignerPanel() {
		return chartDesignerPanel;
	}

	public Long getChartDesignID() {
		return chartDesignID;
	}

	public void setChartDesignID(Long chartDesignID) {
		this.chartDesignID = chartDesignID;
	}

	public ChartDesignerToolbar getChartDesignerToolbar() {
		return chartDesignerToolbar;
	}

	public ChartDesignerManualWindow getChartDesignerManualWindow() {
		return chartDesignerManualWindow;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getTinyMCEPanelID() {
		return tinyMCEPanelID;
	}

	public void setTinyMCEPanelID(String tinyMCEPanelID) {
		this.tinyMCEPanelID = tinyMCEPanelID;
	}

	public String getOriginalWidth() {
		return originalWidth;
	}

	public void setOriginalWidth(String originalWidth) {
		this.originalWidth = originalWidth;
	}

	public String getOriginalHeight() {
		return originalHeight;
	}

	public void setOriginalHeight(String originalHeight) {
		this.originalHeight = originalHeight;
	}
	
}