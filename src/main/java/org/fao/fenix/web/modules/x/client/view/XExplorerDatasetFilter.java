package org.fao.fenix.web.modules.x.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.x.client.control.XExplorerDatasetFilterController;
import org.fao.fenix.web.modules.x.common.vo.XDescriptorVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class XExplorerDatasetFilter {

	private ContentPanel datasetFilterPanel;
	
	private ListBox datasetDimensionsList;
	
	private ListBox unselectedValuesList;
	
	private ListBox selectedValuesList;
	
	private Button addButton;
	
	private Button addAllButton;
	
	private Button removeButton;
	
	private Button removeAllButton;
	
	private Button addToFilterButton;
	
	private Button clearFilterButton;
	
	private Button filterButton;
	
	private HTML filter;
	
	private TextField<String> valuesOracle;
	
	// Key = Header,Map<String, String> = Selected <Code, Label>
	private Map<Map<String, String>, Map<String, String>> filterMap;
	
	// Key = Header, Value = Remote Descriptor's ID
	private Map<String, XDescriptorVO> descriptorMap;
	
	private final String FIELD_WIDTH = "275px";
	
	private final String FILTER_WIDTH = "250px";
	
	private final String ORACLE_WIDTH = "150px";
	
	private final String PANEL_HEIGHT = "400px";
	
	private final String ORACLE_LABEL_WIDTH = "100px";
	
	public XExplorerDatasetFilter() {
		filterMap = new HashMap<Map<String,String>, Map<String,String>>();
		descriptorMap = new HashMap<String, XDescriptorVO>();
		filter = new HTML();
		datasetFilterPanel = new ContentPanel();
		datasetFilterPanel.setHeading("Dataset Filter");
//		datasetFilterPanel.setHeight(PANEL_HEIGHT);
//		datasetFilterPanel.setScrollMode(Scroll.AUTO);
		initiateLists();
		initiateButtons();	
	}
	
	@SuppressWarnings("deprecation")
	private void initiateLists() {
		datasetDimensionsList = new ListBox();
		datasetDimensionsList.setVisibleItemCount(4);
		datasetDimensionsList.setWidth(FIELD_WIDTH);
		unselectedValuesList = new ListBox();
		unselectedValuesList.setVisibleItemCount(4);
		unselectedValuesList.setWidth(FIELD_WIDTH);
		unselectedValuesList.setMultipleSelect(true);
		selectedValuesList = new ListBox();
		selectedValuesList.setVisibleItemCount(4);
		selectedValuesList.setWidth(FIELD_WIDTH);
		selectedValuesList.setMultipleSelect(true);
	}
	
	private void initiateButtons() {
		addButton = new Button("Add");
		addButton.addSelectionListener(XExplorerDatasetFilterController.add(unselectedValuesList, selectedValuesList));
		addAllButton = new Button("Add All");
		addAllButton.addSelectionListener(XExplorerDatasetFilterController.addAll(unselectedValuesList, selectedValuesList));
		removeButton = new Button("Remove");
		removeButton.addSelectionListener(XExplorerDatasetFilterController.remove(selectedValuesList));
		removeAllButton = new Button("Remove All");
		removeAllButton.addSelectionListener(XExplorerDatasetFilterController.removeAll(selectedValuesList));
		addToFilterButton = new Button("Add To Filter");
		addToFilterButton.addSelectionListener(XExplorerDatasetFilterController.addToFilter(this));
		clearFilterButton = new Button("Clear Filter");
		clearFilterButton.addSelectionListener(XExplorerDatasetFilterController.clearFilter(filter, filterMap));
		filterButton = new Button("Filter Dataset");
	}
	
	public ContentPanel build() {
		datasetFilterPanel.add(buildDatasetDimensionsPanel());
		datasetFilterPanel.add(buildValuesPanel());
		datasetFilterPanel.add(buildButtonsPanel());
		datasetFilterPanel.add(buildFilterLabelPanel());
		datasetFilterPanel.add(buildFilterPanel());
		return datasetFilterPanel;
	}
	
	private VerticalPanel buildDatasetDimensionsPanel() {
		VerticalPanel datasetDimensionsPanel = new VerticalPanel();
		datasetDimensionsPanel.setSpacing(5);
		HTML label = new HTML("<b>Dimensions</b>");
		datasetDimensionsPanel.add(label);
		datasetDimensionsPanel.add(datasetDimensionsList);
		return datasetDimensionsPanel;
	}
	
	private VerticalPanel buildValuesPanel() {
		VerticalPanel valuesSelectionPanel = new VerticalPanel();
		valuesSelectionPanel.setSpacing(5);
//		HTML label = new HTML("<b>Values</b>");
//		valuesSelectionPanel.add(label);
		valuesSelectionPanel.add(buildValuesOraclePanel());
		valuesSelectionPanel.add(unselectedValuesList);
		valuesSelectionPanel.add(buildValuesSelectionButtonsPanel());
		valuesSelectionPanel.add(selectedValuesList);
		return valuesSelectionPanel;
	}
	
	private HorizontalPanel buildValuesOraclePanel() {
		HorizontalPanel valuesOraclePanel = new HorizontalPanel();
		valuesOraclePanel.setSpacing(5);
		HTML label = new HTML("<b>Values: </b>");
		label.setWidth(ORACLE_LABEL_WIDTH);
		valuesOraclePanel.add(label);
		valuesOracle = new TextField<String>();
		valuesOracle.setWidth(ORACLE_WIDTH);
		valuesOraclePanel.add(valuesOracle);
		return valuesOraclePanel;
	}
	
	private HorizontalPanel buildValuesSelectionButtonsPanel() {
		HorizontalPanel valuesSelectionButtonsPanel = new HorizontalPanel();
		valuesSelectionButtonsPanel.setSpacing(5);
		valuesSelectionButtonsPanel.add(addButton);
		valuesSelectionButtonsPanel.add(addAllButton);
		valuesSelectionButtonsPanel.add(removeButton);
		valuesSelectionButtonsPanel.add(removeAllButton);
		return valuesSelectionButtonsPanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		buttonsPanel.add(addToFilterButton);
		buttonsPanel.add(clearFilterButton);
//		buttonsPanel.add(filterButton);
		return buttonsPanel;
	}
	
	private HorizontalPanel buildFilterLabelPanel() {
		HorizontalPanel filterLabelPanel = new HorizontalPanel();
//		filterLabelPanel.setSpacing(5);
		filterLabelPanel.add(new HTML("<b>Filter:</b>"));
		return filterLabelPanel;
	}
	
	private VerticalPanel buildFilterPanel() {
		VerticalPanel filterPanel = new VerticalPanel();
		filter.setWidth(FILTER_WIDTH);
		filter.setHeight("35px");
		filterPanel.setSpacing(5);
		filterPanel.add(filter);
		filterPanel.setScrollMode(Scroll.AUTO);
		return filterPanel;
	}

	public ContentPanel getDatasetFilterPanel() {
		return datasetFilterPanel;
	}

	public ListBox getDatasetDimensionsList() {
		return datasetDimensionsList;
	}

	public ListBox getUnselectedValuesList() {
		return unselectedValuesList;
	}

	public ListBox getSelectedValuesList() {
		return selectedValuesList;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getAddAllButton() {
		return addAllButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}

	public Button getRemoveAllButton() {
		return removeAllButton;
	}

	public Button getAddToFilterButton() {
		return addToFilterButton;
	}

	public Button getClearFilterButton() {
		return clearFilterButton;
	}

	public Button getFilterButton() {
		return filterButton;
	}

	public String getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public TextField<String> getValuesOracle() {
		return valuesOracle;
	}

	public Map<Map<String, String>, Map<String, String>> getFilterMap() {
		return filterMap;
	}

	public Map<String, XDescriptorVO> getDescriptorMap() {
		return descriptorMap;
	}

	public HTML getFilter() {
		return filter;
	}
	
}