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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.DataTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;

public class ColumnDefinitionPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private Map<String, VerticalPanel> columnDefinitionMap;
	
	private Map<String, HorizontalPanel> optionMap;
	
	private Map<String, VerticalPanel> optionColumnMap;
	
	private String columnKey;
	
	private String optionKey;
	
	private ListStore<GaulModelData> gaulListStore;
	
	private ListStore<CodingTypeModelData> codingTypeListStore;
	
	private ListStore<CodingNameModelData> codingNameListStore;
	
	private static final String RED = "#CA1616"; 
	
	public ColumnDefinitionPanel() {
		panel = new ContentPanel(new FitLayout());
		panel.setHeaderVisible(false);
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setHeight("475px");
		wrapper.setLayout(new FillLayout());
		columnDefinitionMap = new HashMap<String, VerticalPanel>();
		optionMap = new HashMap<String, HorizontalPanel>();
		optionColumnMap = new HashMap<String, VerticalPanel>();
		columnKey = "1";
		optionKey = "1";
		gaulListStore = new ListStore<GaulModelData>();
		codingTypeListStore = new ListStore<CodingTypeModelData>();
		codingNameListStore = new ListStore<CodingNameModelData>();
		MEController.fillGaulStore(gaulListStore);
		MEController.fillCodingTypeStore(codingTypeListStore);
		MEController.fillCodingNameStore(codingNameListStore);
	}
	
	public ContentPanel build(boolean isEditable) {
		System.out.println("isEditable? " + isEditable);
		wrapper.add(buildStepFourPanel());
		panel.add(wrapper);
		return panel;
	}
	
	private HorizontalPanel buildStepFourPanel() {
		HorizontalPanel stepFourPanel = new HorizontalPanel();
		stepFourPanel.setSpacing(10);
		stepFourPanel.setBorders(true);
		stepFourPanel.setWidth("750px");
		HTML stepFour = new HTML("<font color='#15428B'>" + BabelFish.print().stepFour() + "</font>");
		stepFourPanel.add(stepFour);
		return stepFourPanel;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean validate() {
		if (columnDefinitionMap.values().size() < 1) {
			FenixAlert.alert(BabelFish.print().info(), BabelFish.print().defineAtLeastOneColumn());
			return false;
		}
		for (VerticalPanel panel : columnDefinitionMap.values()) {
			HorizontalPanel descriptorPanel = (HorizontalPanel) panel.getData("descriptorPanel");
			TextField<String> header = (TextField<String>) descriptorPanel.getData("header");
			ComboBox<DataTypeModelData> typeList = (ComboBox<DataTypeModelData>) descriptorPanel.getData("dataType");
			if (!header.isValid())
				return false;
			if (typeList.getSelection().size() < 1)
				return false;
			List<HorizontalPanel> optionPanels = (List<HorizontalPanel>) panel.getData("optionPanels");
			for (HorizontalPanel optionPanel : optionPanels) {
				TextField<String> nameField = (TextField<String>) optionPanel.getData("optionName");
				TextField<String> valueField = (TextField<String>) optionPanel.getData("optionValue");
				
				ComboBox<GaulModelData> gaulComboBox = (ComboBox<GaulModelData>) optionPanel.getData("optionRegion");
				if (!nameField.isValid())
					return false;
				if (!valueField.isValid())
					return false;
			}
		}
		return true;
	}
	
	/**
	 * This panel contains one DescriptorPanel and one or more OptionPanel. 
	 */
	public VerticalPanel buildColumnDefinitionPanel(String headerValue, String dataTypeValue, boolean isKey, List<OptionVO> options, boolean isEditable) {
		VerticalPanel columnPanel = new VerticalPanel();
		columnPanel.setSpacing(10);
		columnPanel.setBorders(true);
		HorizontalPanel descriptorPanel = buildDescriptorPanel(headerValue, dataTypeValue, isKey, options, isEditable);
		columnPanel.add(descriptorPanel);
		columnPanel.setData("descriptorPanel", descriptorPanel);
		List<HorizontalPanel> optionPanels = new ArrayList<HorizontalPanel>();
		for (OptionVO option : options) {
			HorizontalPanel optionPanel = buildOptionPanel(option.getOptionName(), option.getOptionValue(),  isEditable);
			columnPanel.add(optionPanel);
			optionPanels.add(optionPanel);
			getOptionMap().put(getOptionKey(), optionPanel);
			getOptionColumnMap().put(getOptionKey(), columnPanel);
			String optionKey = String.valueOf(1 + Integer.parseInt(getOptionKey()));
			setOptionKey(optionKey);
		}
		columnPanel.setData("optionPanels", optionPanels);
		return columnPanel;
	}
	
	public HorizontalPanel buildDescriptorPanel(String headerValue, String dataTypeValue, boolean isKey, List<OptionVO> options, boolean isEditable) {
		
		HorizontalPanel descriptorPanel = new HorizontalPanel();
		descriptorPanel.setSpacing(10);
		descriptorPanel.setWidth("700px");
		
		CheckBox keyCheck = new CheckBox("Key");
		keyCheck.setChecked(isKey);
		keyCheck.setEnabled(isEditable);
		
		HTML headerLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().header() + ":</font></b>");
		TextField<String> header = new TextField<String>();
		header.setAllowBlank(false);
		header.setWidth("100px");
		header.setFieldLabel("<b>Header: </b>");
		header.setValue(headerValue);
		header.setReadOnly(!isEditable);
		
		HTML typeLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().dataType() + ":</font></b>");
		typeLabel.setWidth("75px");
		ComboBox<DataTypeModelData> typeList = new ComboBox<DataTypeModelData>();
		typeList.setTriggerAction(TriggerAction.ALL);
		ListStore<DataTypeModelData> typeStore = new ListStore<DataTypeModelData>();
		typeList.setStore(typeStore);
		typeList.setDisplayField("dataType");
		typeList.setReadOnly(!isEditable);
		
		IconButton addOptionButton = new IconButton("mapAddDataset");
		addOptionButton.setTitle(BabelFish.print().addOption());
		addOptionButton.addListener(Events.OnClick, MEController.addOption(this, columnKey, "", "", isEditable));
		addOptionButton.setEnabled(isEditable);
		
		Button removeColumnButton = new Button(BabelFish.print().removeColumn());
		removeColumnButton.addSelectionListener(MEController.removeColumn(this, columnKey));
		removeColumnButton.setEnabled(isEditable);
		
		descriptorPanel.add(keyCheck);
		descriptorPanel.add(headerLabel);
		descriptorPanel.add(header);
		descriptorPanel.add(typeLabel);
		descriptorPanel.add(typeList);
		descriptorPanel.add(addOptionButton);
		descriptorPanel.add(removeColumnButton);
		
		descriptorPanel.setData("dataTypeComboBox", typeList);
		descriptorPanel.setData("header", header);
		descriptorPanel.setData("dataType", typeList);
		descriptorPanel.setData("isKey", keyCheck);
		
		MEController.fillDataTypeComboBox(descriptorPanel, dataTypeValue);
		
		return descriptorPanel;
	}
	
	public HorizontalPanel buildOptionPanel(String optionName, String optionValue, boolean isEditable) {
		
		HorizontalPanel optionPanel = new HorizontalPanel();
		optionPanel.setSpacing(20);
		optionPanel.setWidth("700px");
		optionPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		
		HTML nameLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().codingType() + ":</font></b>");
		ComboBox<CodingTypeModelData> name = new ComboBox<CodingTypeModelData>();
		name.setTriggerAction(TriggerAction.ALL);
		name.setStore(codingTypeListStore);
		name.setDisplayField("codingType");
		name.setReadOnly(!isEditable);
		
		for (int i = name.getStore().getCount() - 1; i >= 0 ; i--) {
			CodingTypeModelData ct = name.getStore().getAt(i);
			if (ct.getCodingType().equals(optionName)) {
				name.setValue(ct);
				break;
			}
		}
		
		HTML valueLabel = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().codingName() + ":</font></b>");
		ComboBox<CodingNameModelData> value = new ComboBox<CodingNameModelData>();
		value.setTriggerAction(TriggerAction.ALL);
		value.setStore(codingNameListStore);
		value.setDisplayField("codingName");
		value.setReadOnly(!isEditable);
		
		for (int i = value.getStore().getCount() - 1; i >= 0 ; i--) {
			CodingNameModelData ct = value.getStore().getAt(i);
			if (ct.getCodingName().equals(optionValue)) {
				value.setValue(ct);
				break;
			}
		}
		
		IconButton removeOptionButton = new IconButton("mapRemoveDataset");
		removeOptionButton.setTitle(BabelFish.print().removeOption());
		removeOptionButton.addListener(Events.OnClick, MEController.removeOption(this, optionKey));
		removeOptionButton.setEnabled(isEditable);
		
		optionPanel.add(nameLabel);
		optionPanel.add(name);
		optionPanel.add(valueLabel);
		optionPanel.add(value);
		
		optionPanel.add(removeOptionButton);
		
		optionPanel.setData("optionKey", optionKey);
		optionPanel.setData("optionName", name);
		optionPanel.setData("optionValue", value);
		
		return optionPanel;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public Map<String, VerticalPanel> getColumnDefinitionMap() {
		return columnDefinitionMap;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public String getOptionKey() {
		return optionKey;
	}

	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}

	public Map<String, HorizontalPanel> getOptionMap() {
		return optionMap;
	}

	public Map<String, VerticalPanel> getOptionColumnMap() {
		return optionColumnMap;
	}
	
}
