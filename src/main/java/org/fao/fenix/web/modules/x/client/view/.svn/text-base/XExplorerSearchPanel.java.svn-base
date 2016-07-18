/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.x.client.view;

import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.x.client.control.XExplorerController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class XExplorerSearchPanel {

	private ContentPanel networkSearchPanel;
	
	private ContentPanel datasetSearchPanel;
	
	private ContentPanel searchPanel;
	
	private ContentPanel settingsPanel;
	
	private VerticalPanel wrapper;
	
	private ListBox nodeList;
	
	private ListBox orderByList;
	
	private ListBox orderDirectionList;
	
	private ListBox dataTypeList;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<CategoryModelData> categoryStore;
	
	private ComboBox<CategoryModelData> categoryList;
	
	private Button searchButton;
	
	private Button synchButton;
	
	private Button resetButton;
	
	private TextField<String> titleTextField;
	
	private TextField<String> keywordsTextField;
	
	private TextField<String> valuesTextField;
	
	private TextField<String> maxItemsPerPage;
	
	private TextField<String> maxResultsPerSynchIteration;
	
	private ListBox ignoreOverwriteDataset;
	
	private ListBox ignoreOverwriteCodingSystem;
	
	private XExplorerDatasetFilter xExplorerDatasetFilter;
	
	private XExplorerMetadataPanel xExplorerMetadataPanel;
	
	private final String LABEL_WIDTH = "75px";
	
	private final String FIELD_WIDTH = "175px";
	
	public XExplorerSearchPanel() {
		searchPanel = new ContentPanel();
		searchPanel.setHeaderVisible(false);
		searchPanel.setLayout(new AccordionLayout());
		networkSearchPanel = new ContentPanel();
		datasetSearchPanel = new ContentPanel();
		settingsPanel = new ContentPanel();
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		titleTextField = new TextField<String>();
		titleTextField.setWidth(FIELD_WIDTH);
		keywordsTextField = new TextField<String>();
		keywordsTextField.setWidth(FIELD_WIDTH);
		valuesTextField = new TextField<String>();
		valuesTextField.setWidth(FIELD_WIDTH);
		maxItemsPerPage = new TextField<String>();
		maxItemsPerPage.setWidth(FIELD_WIDTH);
		xExplorerDatasetFilter = new XExplorerDatasetFilter();
		xExplorerMetadataPanel = new XExplorerMetadataPanel();
	}
	
	public ContentPanel build() {
		searchPanel.add(buildNetworkSearchPanel());
		searchPanel.add(xExplorerMetadataPanel.build());
		searchPanel.add(xExplorerDatasetFilter.build());
		searchPanel.add(buildSettingsPanel());
		searchPanel.setBottomComponent(buildButtonsPanel());
		return searchPanel;
	}
	
	private ContentPanel buildNetworkSearchPanel() {
		networkSearchPanel.add(buildDataTypePanel());
		networkSearchPanel.add(buildTextFieldPanel(TextFieldName.Title));
		networkSearchPanel.add(buildTextFieldPanel(TextFieldName.Keywords));
		networkSearchPanel.add(buildAreaPanel());
		networkSearchPanel.add(buildCategoryPanel());
		networkSearchPanel.add(buildTextFieldPanel(TextFieldName.Values));
		networkSearchPanel.add(buildNodePanel());
		networkSearchPanel.add(buildOrderByPanel());
		networkSearchPanel.add(buildTextFieldPanel(TextFieldName.Items));
		networkSearchPanel.setHeading("Network Search Parameters");
		return networkSearchPanel;
	} 
	
	private ContentPanel buildSettingsPanel() {
		settingsPanel.setHeading("Settings");
		settingsPanel.add(buildMaxResultsPerSynchIterationPanel());
		settingsPanel.add(buildIgnoreOverwriteDatasetPanel());
		settingsPanel.add(buildIgnoreOverwriteCodingSystemPanel());
		return settingsPanel;
	}
	
	private VerticalPanel buildMaxResultsPerSynchIterationPanel() {
		VerticalPanel maxResultsPerSynchIterationPanel = new VerticalPanel();
		maxResultsPerSynchIterationPanel.setSpacing(10);
		HTML label = new HTML("<b>Max Synch Results:</b>");
//		label.setWidth(LABEL_WIDTH);
		maxResultsPerSynchIteration = new TextField<String>();
		maxResultsPerSynchIteration.setValue("15");
		maxResultsPerSynchIteration.setWidth(FIELD_WIDTH);
		maxResultsPerSynchIterationPanel.add(label);
		maxResultsPerSynchIterationPanel.add(maxResultsPerSynchIteration);
		return maxResultsPerSynchIterationPanel;
	}
	
	private VerticalPanel buildIgnoreOverwriteCodingSystemPanel() {
		VerticalPanel ignoreOverwriteCodingSystemPanel = new VerticalPanel();
		ignoreOverwriteCodingSystemPanel.setSpacing(10);
		ignoreOverwriteCodingSystemPanel.setScrollMode(Scroll.AUTO);
		HTML label = new HTML("<b>Codes Policy:</b>");
//		label.setWidth(LABEL_WIDTH);
		ignoreOverwriteCodingSystem = new ListBox();
		ignoreOverwriteCodingSystem.addItem("Ignore", "IGNORE");
		ignoreOverwriteCodingSystem.addItem("Overwrite", "OVERWRITE");
		ignoreOverwriteCodingSystem.setWidth(FIELD_WIDTH);
		ignoreOverwriteCodingSystemPanel.add(label);
		ignoreOverwriteCodingSystemPanel.add(ignoreOverwriteCodingSystem);
		return ignoreOverwriteCodingSystemPanel;
	}
	
	private VerticalPanel buildIgnoreOverwriteDatasetPanel() {
		VerticalPanel ignoreOverwriteDatasetPanel = new VerticalPanel();
		ignoreOverwriteDatasetPanel.setSpacing(10);
		HTML label = new HTML("<b>Dataset Policy:</b>");
//		label.setWidth(LABEL_WIDTH);
		ignoreOverwriteDataset = new ListBox();
		ignoreOverwriteDataset.addItem("Ignore", "IGNORE");
		ignoreOverwriteDataset.addItem("Overwrite", "OVERWRITE");
		ignoreOverwriteDataset.setWidth(FIELD_WIDTH);
		ignoreOverwriteDatasetPanel.add(label);
		ignoreOverwriteDatasetPanel.add(ignoreOverwriteDataset);
		return ignoreOverwriteDatasetPanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		searchButton = new Button("Search");
		synchButton = new Button("Synchronize");
		resetButton = new Button("Reset");
		buttonsPanel.add(searchButton);
		buttonsPanel.add(synchButton);
		buttonsPanel.add(resetButton);
		return buttonsPanel;
	}
	
	private HorizontalPanel buildCategoryPanel() {
		HorizontalPanel categoryPanel = new HorizontalPanel();
		categoryPanel.setSpacing(10);
		HTML label = new HTML("<b>Category:</b>");
		label.setWidth(LABEL_WIDTH);
		categoryStore = new ListStore<CategoryModelData>(); 
		categoryList = new ComboBox<CategoryModelData>();
		categoryList.setTriggerAction(TriggerAction.ALL);
		categoryList.setStore(categoryStore);
		categoryList.setDisplayField("categoryName");
		categoryList.setWidth(FIELD_WIDTH);
		MEController.fillCategoryPanel(categoryStore);
		categoryPanel.add(label);
		categoryPanel.add(categoryList);
		return categoryPanel;
	}
	
	private HorizontalPanel buildAreaPanel() {
		HorizontalPanel areaPanel = new HorizontalPanel();
		areaPanel.setSpacing(10);
		HTML label = new HTML("<b>Area:</b>");
		label.setWidth(LABEL_WIDTH);
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(FIELD_WIDTH);
		MEController.fillGaulStore(gaulStore);
		areaPanel.add(label);
		areaPanel.add(gaulList);
		return areaPanel;
	}
	
	private HorizontalPanel buildNodePanel() {
		HorizontalPanel nodePanel = new HorizontalPanel();
		nodePanel.setSpacing(10);
		HTML label = new HTML("<b>Node:</b>");
		label.setWidth(LABEL_WIDTH);
		nodeList = new ListBox(true);
		nodeList.setWidth(FIELD_WIDTH);
		nodeList.setVisibleItemCount(5);
//		nodeList.setMultipleSelect(true); 
		nodeList.addItem("This Node");
		nodeList.addItem("All Node(s)");
		nodeList.setSelectedIndex(1);
		nodePanel.add(label);
		nodePanel.add(nodeList);
		XExplorerController.fillNodeList(nodeList);
		return nodePanel;
	}
	
	private HorizontalPanel buildOrderByPanel() {
		HorizontalPanel orderByPanel = new HorizontalPanel();
		orderByPanel.setSpacing(10);
		HTML label = new HTML("<b>Order By:</b>");
		label.setWidth(LABEL_WIDTH);
		orderByList = new ListBox();
		orderByList.addItem("Title", "title");
		orderByList.addItem("Source", "source");
		orderByList.addItem("Area", "region");
		orderByList.addItem("Date", "dateLastUpdate");
		orderDirectionList = new ListBox();
		orderDirectionList.addItem("Ascending", "ASC");
		orderDirectionList.addItem("Descending", "DESC");
		orderByPanel.add(label);
		orderByPanel.add(orderByList);
		orderByPanel.add(orderDirectionList);
		return orderByPanel;
	}
	
	private HorizontalPanel buildDataTypePanel() {
		HorizontalPanel dataTypePanel = new HorizontalPanel();
		dataTypePanel.setSpacing(10);
		HTML label = new HTML("<b>Data Type:</b>");
		label.setWidth(LABEL_WIDTH);
		dataTypeList = new ListBox();
		dataTypeList.addItem("All Resources", "all");
		dataTypeList.addItem("Dataset", "dataset");
//		dataTypeList.addItem("Layer (DB)", "dbfeaturelayer");
//		dataTypeList.addItem("Layer (Raster)", "rasterlayer");
//		dataTypeList.addItem("Layer (Shape)", "shplayer");
		dataTypeList.addItem("Text", "text");
		dataTypeList.addItem("Node", "node");
		dataTypeList.setWidth(FIELD_WIDTH);
		dataTypePanel.add(label);
		dataTypePanel.add(dataTypeList);
		return dataTypePanel;
	}
	
	private HorizontalPanel buildTextFieldPanel(TextFieldName textFieldName) {
		HorizontalPanel textFieldPanel = new HorizontalPanel();
		textFieldPanel.setSpacing(10);
		HTML label = new HTML("<b>" + textFieldName.name() + ":</b>");
		label.setWidth(LABEL_WIDTH);
		textFieldPanel.add(label);
		switch (textFieldName) {
			case Title : textFieldPanel.add(titleTextField); break;
			case Keywords : textFieldPanel.add(keywordsTextField); break;
			case Values : textFieldPanel.add(valuesTextField); break;
			case Items : 
				maxItemsPerPage.setValue(String.valueOf("25"));
				textFieldPanel.add(maxItemsPerPage); 
			break;
		}
		return textFieldPanel;
	}

	public ContentPanel getNetworkSearchPanel() {
		return networkSearchPanel;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ListBox getNodeList() {
		return nodeList;
	}
	
	public ListBox getOrderByList() {
		return orderByList;
	}

	public ListBox getOrderDirectionList() {
		return orderDirectionList;
	}

	public ListBox getDataTypeList() {
		return dataTypeList;
	}

	public ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public ListStore<CategoryModelData> getCategoryStore() {
		return categoryStore;
	}

	public ComboBox<CategoryModelData> getCategoryList() {
		return categoryList;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public Button getSynchButton() {
		return synchButton;
	}

	public Button getResetButton() {
		return resetButton;
	}

	public TextField<String> getTitleTextField() {
		return titleTextField;
	}

	public TextField<String> getKeywordsTextField() {
		return keywordsTextField;
	}

	public TextField<String> getValuesTextField() {
		return valuesTextField;
	}

	public String getLABEL_WIDTH() {
		return LABEL_WIDTH;
	}

	public String getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public TextField<String> getMaxItemsPerPage() {
		return maxItemsPerPage;
	}

	public ContentPanel getDatasetSearchPanel() {
		return datasetSearchPanel;
	}

	public ContentPanel getSearchPanel() {
		return searchPanel;
	}

	public XExplorerDatasetFilter getxExplorerDatasetFilter() {
		return xExplorerDatasetFilter;
	}

	public TextField<String> getMaxResultsPerSynchIteration() {
		return maxResultsPerSynchIteration;
	}

	public XExplorerMetadataPanel getxExplorerMetadataPanel() {
		return xExplorerMetadataPanel;
	}

	enum TextFieldName {
		Title, Keywords, Values, Items;
	}
	
}