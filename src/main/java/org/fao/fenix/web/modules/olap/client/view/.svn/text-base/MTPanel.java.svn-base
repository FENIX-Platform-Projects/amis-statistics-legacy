package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class MTPanel {

	private ContentPanel panel;

	private CardLayout layout;
	
	private Button datasourceButton;
	
	private Button functionButton;
	
	private Button lookAndFeelButton;
	
	private Button filtersButton;
	
	private Button columnsButton;
	
	private Button rowsButton;
	
	private Button subColumnsButton;
	
	private Button subRowsButton;
	
	private Button tableButton;
	
	private Button addToReportButton;
	
	private MTDatasourcePanel mtDatasourcePanel;
	
	private MTFunctionPanel mtFunctionPanel;
	
	private MTLookAndFeelPanel mtLookAndFeelPanel;
	
	private MTFiltersPanel mtFiltersPanel;
	
	private MTDimensionPanel mtColumnsPanel;
	
	private MTDimensionPanel mtRowsPanel;
	
	private MTDimensionPanel mtSubColumnsPanel;
	
	private MTDimensionPanel mtSubRowsPanel;
	
	private MTTablePanel mtTablePanel;
	
	private final static String BUTTON_WIDTH = "162px";
	
	private final static String STEP_WIDTH = "850px";
	
	private final static int SPACING = 3;
	
	public MTPanel() {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		datasourceButton = new Button("Datasource");
		functionButton = new Button("Function");
		lookAndFeelButton = new Button("Look&Feel");
		filtersButton = new Button("Filters");
		columnsButton = new Button("Columns");
		rowsButton = new Button("Rows");
		subColumnsButton = new Button("Sub-Columns");
		subRowsButton = new Button("Sub-Rows");
		tableButton = new Button("View Table");
		addToReportButton = new Button("Add To Report");
		mtDatasourcePanel = new MTDatasourcePanel("mt_datasource_suggestion", STEP_WIDTH);
		mtFunctionPanel = new MTFunctionPanel("mt_function_suggestion", STEP_WIDTH);
		mtLookAndFeelPanel = new MTLookAndFeelPanel("mt_lookandfeel_suggestion", STEP_WIDTH);
		mtFiltersPanel = new MTFiltersPanel("mt_filters_suggestion", STEP_WIDTH);
		mtColumnsPanel = new MTDimensionPanel("mt_columns_suggestion", STEP_WIDTH);
		mtRowsPanel = new MTDimensionPanel("mt_rows_suggestion", STEP_WIDTH);
		mtSubColumnsPanel = new MTDimensionPanel("mt_subcolumns_suggestion", STEP_WIDTH);
		mtSubRowsPanel = new MTDimensionPanel("mt_subrows_suggestion", STEP_WIDTH);
		mtTablePanel = new MTTablePanel("mt_table_suggestion", STEP_WIDTH);
	}
	
	public ContentPanel build(String caller) {
		
		// build steps
		panel.add(mtDatasourcePanel.build());
		panel.add(mtFunctionPanel.build());
		panel.add(mtLookAndFeelPanel.build());
		panel.add(mtFiltersPanel.build());
		panel.add(mtColumnsPanel.build());
		panel.add(mtRowsPanel.build());
		panel.add(mtSubColumnsPanel.build());
		panel.add(mtSubRowsPanel.build());
		panel.add(mtTablePanel.build());
		
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
		
		datasourceButton.setWidth(BUTTON_WIDTH);
		datasourceButton.setIconStyle("datasource");
		datasourceButton.addSelectionListener(MTController.panelSelector(this, mtDatasourcePanel.getLayoutContainer()));
		topRow.add(datasourceButton);
		
		functionButton.setWidth(BUTTON_WIDTH);
		functionButton.setIconStyle("aggregation");
		functionButton.addSelectionListener(MTController.panelSelector(this, mtFunctionPanel.getLayoutContainer()));
		topRow.add(functionButton);
		
		lookAndFeelButton.setWidth(BUTTON_WIDTH);
		lookAndFeelButton.setIconStyle("sld");
		lookAndFeelButton.addSelectionListener(MTController.panelSelector(this, mtLookAndFeelPanel.getLayoutContainer()));
		topRow.add(lookAndFeelButton);
		
		filtersButton.setWidth(BUTTON_WIDTH);
		filtersButton.setIconStyle("filter");
		filtersButton.addSelectionListener(MTController.panelSelector(this, mtFiltersPanel.getLayoutContainer()));
		topRow.add(filtersButton);
		
		rowsButton.setWidth(BUTTON_WIDTH);
		rowsButton.setIconStyle("row");
		rowsButton.addSelectionListener(MTController.panelSelector(this, mtRowsPanel.getLayoutContainer()));
		bottomRow.add(rowsButton);
		
		columnsButton.setWidth(BUTTON_WIDTH);
		columnsButton.setIconStyle("column");
		columnsButton.addSelectionListener(MTController.panelSelector(this, mtColumnsPanel.getLayoutContainer()));
		bottomRow.add(columnsButton);
		
		subRowsButton.setWidth(BUTTON_WIDTH);
		subRowsButton.setIconStyle("row");
		subRowsButton.addSelectionListener(MTController.panelSelector(this, mtSubRowsPanel.getLayoutContainer()));
		bottomRow.add(subRowsButton);
		
		subColumnsButton.setWidth(BUTTON_WIDTH);
		subColumnsButton.setIconStyle("column");
		subColumnsButton.addSelectionListener(MTController.panelSelector(this, mtSubColumnsPanel.getLayoutContainer()));
		bottomRow.add(subColumnsButton);
				
		if (caller.equals(WhoCall.TINYMCE_ADD)) {
			rightPanel.setSpacing(1 + SPACING);
			tableButton.setWidth(BUTTON_WIDTH);
			tableButton.setIconStyle("olap");
			rightPanel.add(tableButton);
			addToReportButton.setWidth(BUTTON_WIDTH);
			addToReportButton.setIconStyle("reportIcon");
			rightPanel.add(addToReportButton);
		} else {
			tableButton.setWidth(BUTTON_WIDTH);
			tableButton.setHeight("50px");
			tableButton.setIconStyle("olap");
			rightPanel.add(tableButton);
		}
		
		leftPanel.add(topRow);
		leftPanel.add(bottomRow);
		
		wrapper.add(leftPanel);
		wrapper.add(rightPanel);
		
		return wrapper;
	}

	public CardLayout getLayout() {
		return layout;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public Button getDatasourceButton() {
		return datasourceButton;
	}

	public Button getFunctionButton() {
		return functionButton;
	}

	public Button getLookAndFeelButton() {
		return lookAndFeelButton;
	}

	public Button getFiltersButton() {
		return filtersButton;
	}

	public Button getColumnsButton() {
		return columnsButton;
	}

	public Button getRowsButton() {
		return rowsButton;
	}

	public Button getSubColumnsButton() {
		return subColumnsButton;
	}

	public Button getSubRowsButton() {
		return subRowsButton;
	}

	public Button getTableButton() {
		return tableButton;
	}

	public Button getAddToReportButton() {
		return addToReportButton;
	}

	public MTDatasourcePanel getMtDatasourcePanel() {
		return mtDatasourcePanel;
	}

	public MTFunctionPanel getMtFunctionPanel() {
		return mtFunctionPanel;
	}

	public MTLookAndFeelPanel getMtLookAndFeelPanel() {
		return mtLookAndFeelPanel;
	}

	public MTFiltersPanel getMtFiltersPanel() {
		return mtFiltersPanel;
	}

	public MTDimensionPanel getMtColumnsPanel() {
		return mtColumnsPanel;
	}

	public MTDimensionPanel getMtRowsPanel() {
		return mtRowsPanel;
	}

	public MTDimensionPanel getMtSubColumnsPanel() {
		return mtSubColumnsPanel;
	}

	public MTDimensionPanel getMtSubRowsPanel() {
		return mtSubRowsPanel;
	}

	public MTTablePanel getMtTablePanel() {
		return mtTablePanel;
	}
	
}