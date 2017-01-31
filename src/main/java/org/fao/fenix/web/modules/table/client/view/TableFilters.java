package org.fao.fenix.web.modules.table.client.view;

import java.util.Iterator;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.control.report.ReportWizardController;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.table.client.control.TableController;
import org.fao.fenix.web.modules.table.client.control.TableToolbarController;
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
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class TableFilters {

	public ContentPanel panel;

	private DataList dimensionDataList;

	private TableController tableController;

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

	private TableWindow tableWindow;

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
	

	public TableFilters(TableWindow window, Long resourceId) {

		this.tableController = new TableController(this, resourceId);
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
		addAll.setSize("20px", "20px");
		//addAll.setHeight("16px");
		removeAll = new Button("<<");
		removeAll.setSize("20px", "20px");
		//removeAll.setHeight("16px");
		addSelected = new Button(">");
		//addSelected.setHeight("16px");
		addSelected.setSize("20px", "20px");
		removeSelected = new Button("<");
		//removeSelected.setHeight("16px");
		removeSelected.setSize("20px", "20px");
		
		valuesPanel = new ContentPanel();
		xValuesPanel = new HorizontalPanel();
		xValuesCheckBox = new CheckBox();
		dateFromToPanel = new DateFromToPanel();
		dateLastUpdatePanel = new DateLastUpdatePanel();
		buildFromToDatePanel();
		buildLastUpdatePanel();

		format();
	}
	
	public TableFilters(TableWindow tableWindow, Long datasetID, Long tableViewID) {
		
	}

	public ContentPanel build(Long resourceId, boolean getAllData) {
		buildDatasetFilterPanel(resourceId, getAllData);
		enhanceButtons();
		return panel;
	}

	public ContentPanel buildPanel(Long resourceId, boolean getAllData, String pageCaller) {
		buildDatasetFilterPanel(resourceId, getAllData);
		enhanceButtons();
		return panel;
	}
	
	public ContentPanel buildForReport(Long resourceId, boolean getAllData, ReportViewer reportViewer) {
		buildDatasetFilterPanelForReport(resourceId, getAllData, reportViewer);
		enhanceButtons();
		return panel;
	}
	
	

	public void buildDatasetFilterPanelForReport(final Long resourceId, final boolean getAllData, final ReportViewer reportViewer) {
		buildDatasetFilterUniquePanel(resourceId, getAllData, true, reportViewer);
	}
	
	public void buildDatasetFilterPanel(final Long resourceId, final boolean getAllData) {
		buildDatasetFilterUniquePanel(resourceId, getAllData, false, null);
	}
	
	public void buildDatasetFilterUniquePanel(final Long resourceId, final boolean getAllData, final boolean report, final ReportViewer reportViewer) {

		String language = CheckLanguage.getLanguage();
		
		TableServiceEntry.getInstance().getDatasetDetails(resourceId, language, new AsyncCallback<TableVO>() {
			
			public void onSuccess(TableVO tableVO) {

				panel.setHeading(tableVO.getDatasetTitle());
				panel.setData(TableConstants.TABLE_VO, tableVO);
				// add toolbar
				panel.add(getToolbar(resourceId));
				
				tableVO.setDatasetID(resourceId);

				// add Form elements
				// FieldSet1: Set dimension list for the dataset:
				// the first dimension is pre-selected

				Iterator<Map.Entry<String, DimensionBeanVO>> iterator = tableVO.getDimensionDescriptorMap().entrySet().iterator();

				for (int i = 0; i < tableVO.getDimensionDescriptorMap().size(); i++) {
					Map.Entry<String, DimensionBeanVO> entry = iterator.next();
					String columnId = entry.getKey();
					DimensionBeanVO dimensionVO = entry.getValue();
					Map<String, String> values = dimensionVO.getDistinctDimensionValues();
	
				//	if(bean.showDimension()){
					if(values!=null){
						DataListItem item = new DataListItem();
						item.setText(dimensionVO.getHeader());
						item.setId(columnId);
						dimensionDataList.add(item);
					}
				//	}
				}

				dimensionDataList.setSelectedItem(dimensionDataList.getItem(0)); // set
				// the
				// first
				// item
				// selected
				dimensionDataList.setHeight("100px");
				dimensionDataList.setScrollMode(Scroll.AUTO);

				dimensionFieldSet.add(dimensionDataList);
				form.add(dimensionFieldSet);

				// FieldSet2: List the Dimension values, for the
				// selected dimension:
				// the from list is pre-filled with the first dimension
				// values
				// tableController.fillFromList(dimensionDataList,
				// resourceId, fromListField, fromListStore,
				// toListStore, tableVO.getDatasetType());
				tableController.fillFromList(dimensionValues, selectedValues, dimensionDataList, tableVO.getDimensionDescriptorMap(), datePanel, dateFromToPanel, dateLUPanel, dateLastUpdatePanel, tableVO.getPeriodTypeCode(), xValuesPanel);

				// populate the 'from' list with the appropriate
				// dimension values
				dimensionDataList.addListener(Events.SelectionChange, tableController.selectedDimension(dimensionValues, selectedValues, dimensionDataList, tableVO.getDimensionDescriptorMap(), datePanel, dateFromToPanel, dateLUPanel, dateLastUpdatePanel, tableVO.getPeriodTypeCode(), xValuesPanel));
				// dimensionValuesFieldSet.add(lists);
				dimensionValuesFieldSet.add(buildXValuesPanel());

				// Add the 'Add to Filter Button'
				addToFilterButton.addSelectionListener(tableController.addToFilter(dimensionValues, selectedValues, dimensionDataList, dateFromToPanel, dateLastUpdatePanel));
				// addToFilterButton.addSelectionListener(tableController.addToFilter(dimensionDataList,
				// toListField));

				addToFilterButtonBar.add(addToFilterButton);

//				dimensionValuesFieldSet.add(addToFilterButtonBar);
				form.add(dimensionValuesFieldSet);

				// FieldSet3: Selected Criteria
				form.add(queryFilterFieldSet);

				// Add the 'Load Table Button'
				loadTableButton.addSelectionListener(tableController.getData(tableVO, resourceId, tableWindow, "LOAD_BUTTON"));
				if ( report )
					addToReportButton.addSelectionListener(ReportWizardController.addToReport(reportViewer, tableWindow, tableVO.getDatasetTitle(), String.valueOf(resourceId)));

				
				if (tableController.getQueryCache().size() == 0) {
					clearFilterButton.disable();
					loadTableButton.disable();
					addToReportButton.disable();
				}
				// clearFilterButton.addSelectionListener(tableController.clearFilter(resourceId));
				clearFilterButton.addSelectionListener(tableController.clearFilter(resourceId));

				
				

				buttonBar.add(clearFilterButton);
				buttonBar.add(loadTableButton);
				if ( report )
					buttonBar.add(addToReportButton);

				form.add(buttonBar);
				
				panel.add(form);
			
				tableWindow.show();
				
				// refresh
				panel.getLayout().layout();
				

				if (getAllData) {
					tableController.getAllData(tableVO, resourceId, tableWindow, "LOAD_BUTTON");
					tableWindow.getWest().collapse();
//					controller.getLatestData(tableVO, resourceId, tableWindow, "LOAD_BUTTON");
				}
				
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

	private void format() {
		// set titles
		dimensionDataList.setTitle(BabelFish.print().pleaseSelect());

		dimensionFieldSet.setHeading(BabelFish.print().datasetDimensions());
		dimensionValuesFieldSet.setHeading(BabelFish.print().datasetValues());
		queryFilterFieldSet.setHeading(BabelFish.print().selectedCriteriaForFiltering());

		// fromListField.setTitle(I18N.print().pleaseSelect());
		// toListField.setTitle(I18N.print().selectedValues());

		// set sizes
		panel.setWidth(520);
		form.setWidth(520);

		dimensionFieldSet.setWidth(480);
		dimensionValuesFieldSet.setWidth(480);
		dimensionValuesFieldSet.setHeight(250);
//		dimensionValuesFieldSet.setScrollMode(Scroll.AUTO);
		queryFilterFieldSet.setWidth(480);
		queryFilterFieldSet.setHeight(80);
		queryFilterFieldSet.setStyleAttribute("fontSize", "6px");

		// formatting
//		panel.setScrollMode(Scroll.AUTOY);

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

		// addToFilterButtonBar.setCellSpacing(10);
		// addToFilterButtonBar.setButtonAlign(HorizontalAlignment.RIGHT);
		addToFilterButtonBar.setSpacing(10);
		addToFilterButtonBar.setAlignment(HorizontalAlignment.RIGHT);

		// buttonBar.setCellSpacing(10);
		// buttonBar.setButtonAlign(HorizontalAlignment.CENTER);
		buttonBar.setSpacing(10);
		buttonBar.setAlignment(HorizontalAlignment.CENTER);

		/*** LIST BOX ***/
		dimensionValues.setSize("185px", "80px");
		selectedValues.setSize("185px", "80px");
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
//		v.add(dateLUPanel());
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
	
	private VerticalPanel buildLastUpdatePanel(){
		dateLUPanel = new VerticalPanel();
		dateLUPanel.add(dateLastUpdatePanel.build(dimensionValues, xValuesCheckBox, dateFromToPanel, "200px"));
		dateLUPanel.setVisible(false);
		return dateLUPanel;
	}
	

	public TableController getTableController() {
		return tableController;
	}

	public void setTableController(TableController tableController) {
		this.tableController = tableController;
	}

	public ListBox getDimensionValues() {
		return dimensionValues;
	}

	public DataList getDimensionDataList() {
		return dimensionDataList;
	}
	
}
