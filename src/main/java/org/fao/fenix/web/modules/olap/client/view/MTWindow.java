package org.fao.fenix.web.modules.olap.client.view;

import java.util.List;

import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class MTWindow extends FenixWindow {

	private MTPanel mtPanel;
	
	private MTToolbar mtToolbar;
	
	private MTManualWindow mtManualWindow;
	
	private Long mtID = null;
	
	private String caller = WhoCall.USER;
	
	private String tinyMCEPanelID = "";
	
	public MTWindow() {
		mtPanel = new MTPanel();
		mtToolbar = new MTToolbar();
		mtManualWindow = new MTManualWindow();
	}
	
	public MTWindow(String caller, String tinyMCEPanelID) {
		mtPanel = new MTPanel();
		mtToolbar = new MTToolbar();
		mtManualWindow = new MTManualWindow();
		this.setCaller(caller);
		this.setTinyMCEPanelID(tinyMCEPanelID);
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
		enableDateSpecificFuntions(false);
	}
	
	public void build(List<ResourceChildModel> models) {
		buildCenterPanel();
		format();
		show();
		enableDateSpecificFuntions(false);
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(mtPanel.build(this.getCaller()));
		getCenter().setTopComponent(mtToolbar.build());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
		mtPanel.getLayout().setActiveItem(mtPanel.getMtDatasourcePanel().getLayoutContainer());
		enhanceButtons();
	}
	
	private void enhanceButtons() {
		
		// add dataset
		mtPanel.getMtDatasourcePanel().getAddDatasetButton().addSelectionListener(MTController.addDataset(this));
		
		// view table
		mtPanel.getTableButton().addSelectionListener(MTController.viewTable(this));
		
		// size listeners
		mtPanel.getMtColumnsPanel().getValuesList().addChangeHandler(MTController.columnWidths(this));
		mtPanel.getMtSubColumnsPanel().getValuesList().addChangeHandler(MTController.columnWidths(this));
		mtPanel.getMtRowsPanel().getValuesList().addChangeHandler(MTController.rowsHeights(this));
		mtPanel.getMtSubRowsPanel().getValuesList().addChangeHandler(MTController.rowsHeights(this));
		mtPanel.getAddToReportButton().addSelectionListener(MTController.addToReport(this));
		
		// menu listeners
		mtToolbar.getSaveMenuItem().addSelectionListener(MTController.saveFromMenu(this));
		mtToolbar.getSaveAsMenuItem().addSelectionListener(MTController.saveAsFromMenu(this));
		mtToolbar.getEmbedItem().addSelectionListener(MTController.exportAsHTML(this));
		mtToolbar.getExcelMenuItem().addSelectionListener(MTController.exportAsExcel(this));
		mtToolbar.getOpenAsChartMenuItem().addSelectionListener(MTController.openAsChart(this));
		mtToolbar.getManualButton().addSelectionListener(MTController.showManual(this));
		
		// dates functions
		addListenersForDatesFunctions();
	}
	
	private void addListenersForDatesFunctions() {
		
		mtPanel.getMtColumnsPanel().getUseFromDateToDate().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getUseMostRecentData().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getFromDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getToDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getAggregateMonthly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getAggregateYearly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getLatestYearsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getLatestMonthsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtColumnsPanel()));
		mtPanel.getMtColumnsPanel().getLatestDaysList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtColumnsPanel()));
		
		mtPanel.getMtRowsPanel().getUseFromDateToDate().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getUseMostRecentData().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getFromDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getToDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getAggregateMonthly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getAggregateYearly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getLatestYearsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getLatestMonthsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtRowsPanel()));
		mtPanel.getMtRowsPanel().getLatestDaysList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtRowsPanel()));
		
		mtPanel.getMtSubColumnsPanel().getUseFromDateToDate().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getUseMostRecentData().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getFromDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getToDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getAggregateMonthly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getAggregateYearly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getLatestYearsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getLatestMonthsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubColumnsPanel()));
		mtPanel.getMtSubColumnsPanel().getLatestDaysList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubColumnsPanel()));
		
		mtPanel.getMtSubRowsPanel().getUseFromDateToDate().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getUseMostRecentData().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getFromDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getToDate().addListener(Events.Valid, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getAggregateMonthly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getAggregateYearly().addListener(Events.OnClick, MTController.enableValues(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getLatestYearsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getLatestMonthsList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubRowsPanel()));
		mtPanel.getMtSubRowsPanel().getLatestDaysList().addChangeHandler(MTController.enableValuesChangeHandler(this, mtPanel.getMtSubRowsPanel()));
	}
	
	public void enableDateSpecificFuntions(boolean enabled) {
		mtPanel.getMtRowsPanel().getFromDateToDateFieldSet().setEnabled(false);
		mtPanel.getMtRowsPanel().getMostRecentDataFieldSet().setEnabled(false);
		mtPanel.getMtRowsPanel().getAggregateDateFieldSet().setEnabled(false);
	}
	
	private void format() {
		setSize("850px", "700px");
		getWindow().setHeading("Multidimensional Table Designer");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("olap");
		getWindow().setCollapsible(false);
		getWindow().setMaximizable(true);
		getWindow().addListener(Events.Resize, MTController.resize(this));
		getWindow().addWindowListener(MTController.close(this));
	}

	public Long getMtID() {
		return mtID;
	}

	public void setMtID(Long mtID) {
		this.mtID = mtID;
	}

	public MTPanel getMtPanel() {
		return mtPanel;
	}

	public MTToolbar getMtToolbar() {
		return mtToolbar;
	}

	public MTManualWindow getMtManualWindow() {
		return mtManualWindow;
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
	
}