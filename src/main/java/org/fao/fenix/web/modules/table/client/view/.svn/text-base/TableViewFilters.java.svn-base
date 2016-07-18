package org.fao.fenix.web.modules.table.client.view;

import java.util.Iterator;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.control.TableViewController;
import org.fao.fenix.web.modules.table.client.control.TableViewGridController;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.DataListSelectionModel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class TableViewFilters {

	public ContentPanel panel;

	private DataList dimensionDataList;

	private TableViewController tableController;

	private ButtonBar addToFilterButtonBar = new ButtonBar();

	private Button addToFilterButton;

	private ButtonBar buttonBar = new ButtonBar();

	public Button loadTableButton;

	public Button clearFilterButton;

	public Button addToReportButton;

	public FieldSet queryFilterFieldSet;

	private FieldSet dimensionFieldSet;

	private FieldSet dimensionValuesFieldSet;

	public FormPanel form;

	private TableViewWindow tableWindow;

	private ListBox dimensionValues;

	private ListBox selectedValues;

	private Button addAll;

	private Button removeAll;

	private Button addSelected;

	private Button removeSelected;

	public String andSeparator = "<br/><b>" + BabelFish.print().and() + "</b><br/>";

	public String orSeparator = " <b>" + BabelFish.print().or() + "</b> ";

	/** Dates Panel **/

	private VerticalPanel datePanel;

	private VerticalPanel dateLUPanel;

	private DateFromToPanel dateFromToPanel;

	private DateLastUpdatePanel dateLastUpdatePanel;

	private CheckBox xValuesCheckBox;

	private HorizontalPanel xValuesPanel;

	private ContentPanel valuesPanel;

	public TableViewFilters(TableViewWindow window, Long datasetId, Long tableViewID) {

		this.tableController = new TableViewController(this, datasetId, tableViewID);
		this.tableWindow = window;

		panel = new ContentPanel(new FlowLayout());
		form = new FormPanel();
		dimensionDataList = new DataList();
		dimensionFieldSet = new FieldSet();
		dimensionValuesFieldSet = new FieldSet();
		queryFilterFieldSet = new FieldSet();

		addToFilterButton = new Button(BabelFish.print().addSelectedValuesToFilter());
		loadTableButton = new Button(BabelFish.print().loadTable());
		addToReportButton = new Button("Add to Report");
		clearFilterButton = new Button(BabelFish.print().clearFilter());

		dimensionValues = new ListBox();
		selectedValues = new ListBox();
		addAll = new Button(">>");
		addAll.setHeight("16px");
		removeAll = new Button("<<");
		removeAll.setHeight("16px");
		addSelected = new Button(">");
		addSelected.setHeight("16px");
		removeSelected = new Button("<");
		removeSelected.setHeight("16px");

		valuesPanel = new ContentPanel();
		xValuesPanel = new HorizontalPanel();
		xValuesCheckBox = new CheckBox();
		dateFromToPanel = new DateFromToPanel();
		dateLastUpdatePanel = new DateLastUpdatePanel();
		buildFromToDatePanel();
		buildLastUpdatePanel();

		format();
	}

	public ContentPanel build(Long resourceId, Long tableViewID, boolean getAllData) {
		buildDatasetFilterPanel(resourceId, tableViewID, getAllData);
		enhanceButtons();
		return panel;
	}

	public ContentPanel buildPanel(Long resourceId, Long tableViewID, boolean getAllData, String pageCaller) {
		buildDatasetFilterPanel(resourceId, tableViewID, getAllData);
		enhanceButtons();
		return panel;
	}

	public ContentPanel buildForReport(Long resourceId, Long tableViewID, boolean getAllData, ReportViewer reportViewer) {
		buildDatasetFilterPanelForReport(resourceId, tableViewID, getAllData, reportViewer);
		enhanceButtons();
		return panel;
	}

	public void buildDatasetFilterPanelForReport(final Long resourceId, final Long tableViewID, final boolean getAllData, final ReportViewer reportViewer) {
		buildDatasetFilterUniquePanel(resourceId, tableViewID, getAllData, true, reportViewer);
	}

	public void buildDatasetFilterPanel(final Long resourceId, final Long tableViewID, final boolean getAllData) {
		buildDatasetFilterUniquePanel(resourceId, tableViewID, getAllData, false, null);
	}

	public void buildDatasetFilterUniquePanel(final Long resourceId, final Long tableViewID, final boolean getAllData, final boolean report, final ReportViewer reportViewer) {
		String language = CheckLanguage.getLanguage();
		TableServiceEntry.getInstance().getDatasetDetails(resourceId, language, new AsyncCallback<TableVO>() {
			public void onSuccess(TableVO tableVO) {
				panel.setHeading(tableVO.getDatasetTitle());
				panel.setData(TableConstants.TABLE_VO, tableVO);
				panel.add(getToolbar(resourceId));
				tableVO.setDatasetID(resourceId);
				Iterator<Map.Entry<String, DimensionBeanVO>> iterator = tableVO.getDimensionDescriptorMap().entrySet().iterator();
				for (int i = 0; i < tableVO.getDimensionDescriptorMap().size(); i++) {
					Map.Entry<String, DimensionBeanVO> entry = iterator.next();
					String columnId = entry.getKey();
					DimensionBeanVO dimensionVO = entry.getValue();
					Map<String, String> values = dimensionVO.getDistinctDimensionValues();
					if (values != null) {
						DataListItem item = new DataListItem();
						item.setText(dimensionVO.getHeader());
						item.setId(columnId);
						dimensionDataList.add(item);
					}
				}
				dimensionDataList.setSelectedItem(dimensionDataList.getItem(0)); // set
				dimensionDataList.setHeight("100px");
				dimensionDataList.setScrollMode(Scroll.AUTO);
				dimensionFieldSet.add(dimensionDataList);
				form.add(dimensionFieldSet);
				tableController.fillFromList(dimensionValues, selectedValues, dimensionDataList, tableVO.getDimensionDescriptorMap(), datePanel, dateFromToPanel, dateLUPanel, dateLastUpdatePanel, tableVO.getPeriodTypeCode(), xValuesPanel);
				dimensionDataList.addListener(Events.SelectionChange, tableController.selectedDimension(dimensionValues, selectedValues, dimensionDataList, tableVO.getDimensionDescriptorMap(), datePanel, dateFromToPanel, dateLUPanel, dateLastUpdatePanel, tableVO.getPeriodTypeCode(), xValuesPanel));
				dimensionValuesFieldSet.add(buildXValuesPanel());
				addToFilterButton.addSelectionListener(tableController.addToFilter(dimensionValues, selectedValues, dimensionDataList, dateFromToPanel, dateLastUpdatePanel));
				addToFilterButtonBar.add(addToFilterButton);
				form.add(dimensionValuesFieldSet);
				form.add(queryFilterFieldSet);
				loadTableButton.addSelectionListener(tableController.getData(tableVO, resourceId, tableWindow, "LOAD_BUTTON"));
				// TODO Add to the Report Wizard...
				// if (report)
				//	addToReportButton.addSelectionListener(ReportWizardController.addToReport(reportViewer, tableWindow, tableVO.getDatasetTitle(), String.valueOf(resourceId)));
				if (tableController.getQueryCache().size() == 0) {
					clearFilterButton.disable();
					loadTableButton.disable();
					addToReportButton.disable();
				}
				clearFilterButton.addSelectionListener(tableController.clearFilter(resourceId));
				if (getAllData) {
					TableViewController controller = getTableController();
					controller.getLatestData(tableVO, resourceId, tableWindow, "LOAD_BUTTON");
				}
				buttonBar.add(clearFilterButton);
				buttonBar.add(loadTableButton);
				if (report)
					buttonBar.add(addToReportButton);
				form.add(buttonBar);
				panel.add(form);
				tableWindow.show();
				panel.getLayout().layout();
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "buildDatasetPanel @ TableFilters");
			}
		});
	}

	private VerticalPanel createDimensionValuesButtonPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(3);
		panel.add(addAll);
		panel.add(addSelected);
		panel.add(removeSelected);
		panel.add(removeAll);
		return panel;
	}

	public ToolBar getToolbar(Long resourceId) {
		ToolBar toolBar = new ToolBar();
		IconButton iconButton;
		iconButton = new IconButton("reMetadataIcon");
		iconButton.setTitle(BabelFish.print().viewMetadata());
		iconButton.addSelectionListener(tableController.viewMetadata(resourceId));
		toolBar.add(iconButton);
		return toolBar;
	}

	private void format() {
		dimensionDataList.setTitle(BabelFish.print().pleaseSelect());
		dimensionFieldSet.setHeading(BabelFish.print().datasetDimensions());
		dimensionValuesFieldSet.setHeading(BabelFish.print().datasetValues());
		queryFilterFieldSet.setHeading(BabelFish.print().selectedCriteriaForFiltering());
		panel.setWidth(520);
		form.setWidth(520);
		dimensionFieldSet.setWidth(480);
		dimensionValuesFieldSet.setWidth(480);
		dimensionValuesFieldSet.setHeight(250);
		queryFilterFieldSet.setWidth(480);
		queryFilterFieldSet.setHeight(80);
		queryFilterFieldSet.setStyleAttribute("fontSize", "6px");
		form.setFrame(true);
		form.setLayout(new FlowLayout());
		form.setHeaderVisible(false);
		dimensionDataList.setFlatStyle(true);
		dimensionDataList.setSelectionMode(SelectionMode.SINGLE);
		DataListSelectionModel sm = dimensionDataList.getSelectionModel();
		dimensionDataList.setSelectionModel(sm);
		dimensionDataList.setBorders(true);
		queryFilterFieldSet.setCollapsible(true);
		queryFilterFieldSet.setScrollMode(Scroll.AUTO);
		addToFilterButtonBar.setSpacing(10);
		addToFilterButtonBar.setAlignment(HorizontalAlignment.RIGHT);
		buttonBar.setSpacing(10);
		buttonBar.setAlignment(HorizontalAlignment.CENTER);
		dimensionValues.setSize("185px", "75px");
		selectedValues.setSize("185px", "75px");
		dimensionValues.setVisibleItemCount(5);
		selectedValues.setVisibleItemCount(5);
		dimensionValues.setMultipleSelect(true);
		selectedValues.setMultipleSelect(true);
		addAll.setWidth("40px");
		addSelected.setWidth("40px");
		removeSelected.setWidth("40px");
		removeAll.setWidth("40px");
		valuesPanel.setWidth(455);
		valuesPanel.setHeight(220);
		valuesPanel.setScrollMode(Scroll.AUTO);
		valuesPanel.setHeaderVisible(false);
		valuesPanel.setBodyBorder(false);
	}

	private void enhanceButtons() {
		addAll.addSelectionListener(tableController.addAll(dimensionValues, selectedValues));
		addSelected.addSelectionListener(tableController.addSelectedValues(dimensionValues, selectedValues));
		removeSelected.addSelectionListener(tableController.removeSelected(selectedValues));
		removeAll.addSelectionListener(tableController.removeAll(selectedValues));
	}

	private ContentPanel buildXValuesPanel() {
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(5);
		v.add(createDimensionValesPanel());
		v.add(datePanel);
		v.add(addToFilterButtonBar);
		valuesPanel.add(v);
		valuesPanel.setScrollMode(Scroll.AUTO);
		return valuesPanel;
	}

	private HorizontalPanel createDimensionValesPanel() {
		xValuesPanel.add(dimensionValues);
		/** HERE ADD THE OTHER STUFF ***/
		xValuesPanel.add(createDimensionValuesButtonPanel());
		xValuesPanel.add(selectedValues);
		return xValuesPanel;
	}

	private VerticalPanel buildFromToDatePanel() {
		datePanel = new VerticalPanel();
		datePanel.add(dateFromToPanel.build(xValuesPanel, xValuesCheckBox, dateLastUpdatePanel, "200px", "8pt", "#17448c"));
		datePanel.setVisible(false);
		return datePanel;
	}

	private VerticalPanel buildLastUpdatePanel() {
		dateLUPanel = new VerticalPanel();
		dateLUPanel.add(dateLastUpdatePanel.build(dimensionValues, xValuesCheckBox, dateFromToPanel, "200px"));
		dateLUPanel.setVisible(false);
		return dateLUPanel;
	}

	public TableViewController getTableController() {
		return tableController;
	}

	public void setTableController(TableViewController tableController) {
		this.tableController = tableController;
	}

	public ListBox getDimensionValues() {
		return dimensionValues;
	}

	public DataList getDimensionDataList() {
		return dimensionDataList;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}

}