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
package org.fao.fenix.web.modules.metadataeditor.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class METabPanel {

	private TabPanel tabPanel;
	
	private TabItem datasetType;
	
	private TabItem keys;
	
	private TabItem generalInfo;
	
	private TabItem columnDefinition;
	
	private TabItem resourceSpecificFields;
	
	private TabItem metadataTabItem;
	
	private TabItem extraInfoTabItem;
	
	private GeneralInfoPanel generalInfoPanel;
	
	private DatasetTypePanel datasetTypePanel;
	
	private ColumnDefinitionPanel columnDefinitionPanel;
	
	private ResourceSpecificPanel resourceSpecificPanel;
	
	private KeysPanel keysPanel;
	
	private MetadataPanel metadataPanel;
	
	private ExtraInfoPanel extraInfoPanel;
	
	private Map<String, Integer> tabs;
	
	public METabPanel() {
		tabPanel = new TabPanel();
		datasetType = new TabItem(BabelFish.print().datasetType());
		keys = new TabItem(BabelFish.print().source());
		generalInfo = new TabItem(BabelFish.print().generalInformation());
		columnDefinition = new TabItem(BabelFish.print().columnDefinition());
		resourceSpecificFields = new TabItem("Resource Specific Fields");
		metadataTabItem = new TabItem(BabelFish.print().metadata());
		extraInfoTabItem = new TabItem("Extra Information");
		columnDefinition.setScrollMode(Scroll.AUTO);
		generalInfoPanel = new GeneralInfoPanel();
		datasetTypePanel = new DatasetTypePanel();
		columnDefinitionPanel = new ColumnDefinitionPanel();
		resourceSpecificPanel = new ResourceSpecificPanel();
		keysPanel = new KeysPanel();
//		metadataPanel = new MetadataPanel();
//		extraInfoPanel = new ExtraInfoPanel();
		tabs = new HashMap<String, Integer>();
	}
	
	public TabPanel build(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields) {
		
//		keys.add(keysPanel.build(isDataset, isEditable));
//		generalInfo.add(generalInfoPanel.build(isEditable));
		
//		if (isDataset) {
//			datasetType.add(datasetTypePanel.build(isEditable));
//			columnDefinition.add(columnDefinitionPanel.build(isEditable));
//			keys.setText("Keys");
//		}
		
		int tabCounter = 0;
		
		if (isDataset) {
//			tabPanel.add(datasetType);
			datasetType.add(datasetTypePanel.build(isEditable));
			tabPanel.add(datasetType);
			tabs.put("DATASET_TYPE", tabCounter++);
		}
		
//		tabPanel.add(keys);
//		tabPanel.add(generalInfo);
		
		metadataPanel = new MetadataPanel(isEditable);
		metadataTabItem.add(metadataPanel);
		tabPanel.add(metadataTabItem);
		
		extraInfoPanel = new ExtraInfoPanel(isEditable);
		extraInfoTabItem.add(extraInfoPanel);
		tabPanel.add(extraInfoTabItem);
		
//		keys.add(keysPanel.build(isDataset, isEditable));
//		tabPanel.add(keys);
//		tabs.put("KEYS", tabCounter++);
		
//		generalInfo.add(generalInfoPanel.build(isEditable));
//		tabPanel.add(generalInfo);
//		tabs.put("GENERAL_INFO", tabCounter++);
		
		if (isDataset) {
			columnDefinition.add(columnDefinitionPanel.build(isEditable));
			tabPanel.add(columnDefinition);
		}
		
		if (hasResourceSpecificFields) {
//			resourceSpecificFields.add(resourceSpecificPanel.build());
			resourceSpecificFields.add(resourceSpecificPanel.build());
			tabPanel.add(resourceSpecificFields);
			tabs.put("RESOURCE_SPECIFIC", tabCounter++);
		}
		
//		tabPanel.selectTab(2);
		return tabPanel;
	}
	
	public TabPanel build(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields, List<String> specificFields) {
		
		keys.add(keysPanel.build(isDataset, isEditable));
		generalInfo.add(generalInfoPanel.build(isEditable));
		
		if (isDataset) {
			datasetType.add(datasetTypePanel.build(isEditable));
			columnDefinition.add(columnDefinitionPanel.build(isEditable));
			keys.setText("Keys");
		}
		
		if (isDataset)
			tabPanel.add(datasetType);
		
		tabPanel.add(keys);
		tabPanel.add(generalInfo);
		
		if (isDataset)
			tabPanel.add(columnDefinition);
		
		if (hasResourceSpecificFields) {
			resourceSpecificFields.add(resourceSpecificPanel.build(specificFields));
			tabPanel.add(resourceSpecificFields);
		}
		
		return tabPanel;
	}

	public DatasetTypePanel getDatasetTypePanel() {
		return datasetTypePanel;
	}

//	public GeneralInfoPanel getGeneralInfoPanel() {
//		return generalInfoPanel;
//	}

	public ColumnDefinitionPanel getColumnDefinitionPanel() {
		return columnDefinitionPanel;
	}

//	public KeysPanel getKeysPanel() {
//		return keysPanel;
//	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public TabItem getColumnDefinition() {
		return columnDefinition;
	}

	public TabItem getDatasetType() {
		return datasetType;
	}

	public TabItem getKeys() {
		return keys;
	}

	public TabItem getGeneralInfo() {
		return generalInfo;
	}

	public ResourceSpecificPanel getResourceSpecificPanel() {
		return resourceSpecificPanel;
	}

	public TabItem getResourceSpecificFields() {
		return resourceSpecificFields;
	}

	public Map<String, Integer> getTabs() {
		return tabs;
	}

	public MetadataPanel getMetadataPanel() {
		return metadataPanel;
	}

	public TabItem getMetadataTabItem() {
		return metadataTabItem;
	}

	public ExtraInfoPanel getExtraInfoPanel() {
		return extraInfoPanel;
	}
	
}