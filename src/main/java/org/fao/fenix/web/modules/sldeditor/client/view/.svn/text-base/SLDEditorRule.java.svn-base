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
package org.fao.fenix.web.modules.sldeditor.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorConstants;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.ListBox;


public class SLDEditorRule {

	private VerticalPanel wrapper;
	
	private VerticalPanel actionPanel;
	
	private TextField<String> ruleNameField;
	
	private TextField<String> filterPropertyNameField;
	
	private TextField<String> filterPropertyValueField;
	
	private TextField<String> labelPropertyNameField;
	
	private TextField<String> polygonPropertyNameIntervalField;	
	
	private ListBox isListBox;
	
	private ListBox comparisonListBox;
	
	private Button addRuleButton;
	
	private Button deleteRuleButton;
	
	private CheckBox addFilter;
	
	public final String LABEL_WIDTH = "100px";
	
	public final String FIELD_WIDTH = "150px";
	
	public final String LONG_FIELD_WIDTH = "250px";
	
	public final String MEDIUM_FIELD_WIDTH = "245px";
	
	public final String SHORT_FIELD_WIDTH = "135px";
	
	public final String BUTTON_WIDTH = "174px";
	
	public final int SPACING = 5;
	
	public List<HorizontalPanel> removablePanels;
	
	public List<HorizontalPanel> removableFilters;
	
	public List<ContentPanel> removableContentPanels;
	
	public List<HorizontalPanel> removableCheckbox;
	
	//Panels for Raster
	
	public List<HorizontalPanel> removableFormatRasterPanels;

	public List<HorizontalPanel> removableSliderOpacityPanels;
	
	public List<HorizontalPanel> removableColorMapPanels;
	
	public List<ContentPanel> removableColorMapContentPanels;

	public List<HorizontalPanel> removableChannelSelectionPanels;
	
	public List<HorizontalPanel> removableContrastEnhancementPanels;
	
	public List<ContentPanel> removableContrastEnhancementContentPanels;
	
	private int addFilterPanelIDX;
	
	private Button titleButton;

	private Html line;
	
	public SLDEditorRule() {
		wrapper = new VerticalPanel();
		actionPanel = new VerticalPanel();
		ruleNameField = new TextField<String>();
		ruleNameField.setAllowBlank(false);
		filterPropertyNameField = new TextField<String>();
		isListBox = new ListBox();
		comparisonListBox = new ListBox();
		filterPropertyValueField = new TextField<String>();
		addRuleButton = new Button("Add Another Rule");
		addRuleButton.setIconStyle("addIcon");
		deleteRuleButton = new Button("Remove This Rule");
		deleteRuleButton.setIconStyle("removeIcon");
		addFilter = new CheckBox();
		removablePanels = new ArrayList<HorizontalPanel>();
		removableFilters = new ArrayList<HorizontalPanel>();
		removableCheckbox = new ArrayList<HorizontalPanel>();
		labelPropertyNameField = new TextField<String>();
		polygonPropertyNameIntervalField = new TextField<String>();
		removableContentPanels = new ArrayList<ContentPanel>();
		
		removableSliderOpacityPanels = new ArrayList<HorizontalPanel>();
		removableColorMapPanels = new ArrayList<HorizontalPanel>();
		removableColorMapContentPanels = new ArrayList<ContentPanel>();
		removableChannelSelectionPanels = new ArrayList<HorizontalPanel>();
		removableContrastEnhancementPanels = new ArrayList<HorizontalPanel>();
		removableContrastEnhancementContentPanels = new ArrayList<ContentPanel>();
		removableFormatRasterPanels = new ArrayList<HorizontalPanel>();
	}

	public VerticalPanel build() {
//		wrapper.add(buildRuleNamePanel());
		wrapper.add(actionPanel);
//		wrapper.add(buildButtonsPanel());
		
		line = new Html("<hr>");
		line.setWidth("360px");
		wrapper.add(line);
		return wrapper;
	}
	
	public HorizontalPanel buildAddFilter() {
		return buildAddFilter(null);
	}
	
	public HorizontalPanel buildAddFilter(SLDEditorRuleVO data) {
		HorizontalPanel p = null;
		if(data == null)
		{
			p = new HorizontalPanel();
			p.setSpacing(SPACING);
			p.setVerticalAlign(VerticalAlignment.MIDDLE);
			addFilter.setBoxLabel("Add Filter");
			addFilter.addListener(Events.OnClick, SLDEditorController.addFilter(this));
			p.add(addFilter);
			removablePanels.add(p);
		}
		else
		{
			p = new HorizontalPanel();
			p.setSpacing(SPACING);
			p.setVerticalAlign(VerticalAlignment.MIDDLE);
			addFilter.setBoxLabel("Add Filter");
			addFilter.addListener(Events.OnClick, SLDEditorController.addFilter(this));
			addFilter.setValue(true);
			p.add(addFilter);
			removablePanels.add(p);
		}
		return p;
	}
	
	public void addFilter(SLDEditorRuleVO data)
	{
		if(data!= null)
		{
			SLDEditorController.addFilter(this,data);
	
		}
	}	
	public HorizontalPanel buildRuleNamePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html l = new Html("<b>Rule Name: </b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		ruleNameField.setEmptyText("Rule Name");
		ruleNameField.setWidth(LONG_FIELD_WIDTH);
		p.add(ruleNameField);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFilterPropertyNamePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html l = new Html("<b>Property Name: </b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		filterPropertyNameField.setEmptyText("Property Name");
		filterPropertyNameField.setWidth(LONG_FIELD_WIDTH);
		p.add(filterPropertyNameField);
		removableFilters.add(p);
		return p;
	}
	
	public HorizontalPanel buildLabelPropertyNamePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html l = new Html("<b>Property Name: </b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		labelPropertyNameField.setEmptyText("Property Name");
		labelPropertyNameField.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(labelPropertyNameField);
		removablePanels.add(p);
		return p;
	}
	
	//public HorizontalPanel buildLabelPropertyNameAreaPanel(boolean area) {
	public HorizontalPanel buildLabelPropertyNameAreaPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html l = new Html("<b>Property Name: </b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
//		if(area)
//		{
//			//polygonPropertyNameIntervalField.setEmptyText("AREA: Immutable!");
//			polygonPropertyNameIntervalField.setValue("AREA");
//			polygonPropertyNameIntervalField.setReadOnly(true);
//			polygonPropertyNameIntervalField.disableTextSelection(true);
//		}
//		else
//		{
			polygonPropertyNameIntervalField.setEmptyText("Property Name");
//		}
		
		polygonPropertyNameIntervalField.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(polygonPropertyNameIntervalField);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFilterConditionPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html l = new Html("<b>Property</b>");
		p.add(l);
		isListBox.clear();
		isListBox.addItem("is", "IS");
		isListBox.addItem("is not", "IS_NOT");
		p.add(isListBox);
		comparisonListBox.clear();
		comparisonListBox.addItem("equal to", SLDEditorConstants.PropertyIsEqualTo.name());
		comparisonListBox.addItem("less than", SLDEditorConstants.PropertyIsLessThan.name());
		comparisonListBox.addItem("greater than", SLDEditorConstants.PropertyIsGreaterThan.name());
		p.add(comparisonListBox);
		filterPropertyValueField.setEmptyText("Property Value");
		filterPropertyValueField.setWidth(SHORT_FIELD_WIDTH);
		p.add(filterPropertyValueField);
		removableFilters.add(p);
		return p;
	}
	
	public HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		addRuleButton.setWidth(BUTTON_WIDTH);
		p.add(addRuleButton);
		deleteRuleButton.setWidth(BUTTON_WIDTH);
		p.add(deleteRuleButton);
		removablePanels.add(p);
		removableFormatRasterPanels.add(p);
		return p;
	}

	public HorizontalPanel buildTitleButtonPanel(String title) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		this.titleButton = new Button(title);
		titleButton.setWidth(BUTTON_WIDTH);
		p.add(titleButton);
		removablePanels.add(p);
		return p;
	}
	
	public TextField<String> getRuleNameField() {
		return ruleNameField;
	}

	public ListBox getIsListBox() {
		return isListBox;
	}

	public ListBox getComparisonListBox() {
		return comparisonListBox;
	}

	public VerticalPanel getActionPanel() {
		return actionPanel;
	}
	
	public List<HorizontalPanel> getRemovablePanels() {
		return removablePanels;
	}
	
	public List<HorizontalPanel> getRemovableCheckbox() {
		return removableCheckbox;
	}

	public CheckBox getAddFilter() {
		return addFilter;
	}

	public List<HorizontalPanel> getRemovableFilters() {
		return removableFilters;
	}

	public List<ContentPanel> getRemovableContentPanels() {
		return removableContentPanels;
	}
	
	public Button getAddRuleButton() {
		return addRuleButton;
	}

	public Button getDeleteRuleButton() {
		return deleteRuleButton;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public int getAddFilterPanelIDX() {
		return addFilterPanelIDX;
	}

	public void setAddFilterPanelIDX(int addFilterPanelIDX) {
		this.addFilterPanelIDX = addFilterPanelIDX;
	}

	public TextField<String> getFilterPropertyNameField() {
		return filterPropertyNameField;
	}

	public TextField<String> getFilterPropertyValueField() {
		return filterPropertyValueField;
	}

	public TextField<String> getLabelPropertyNameField() {
		return labelPropertyNameField;
	}
	
	public TextField<String> getLabelPropertyNameIntervalField() {
		return polygonPropertyNameIntervalField;
	}
	
	public List<HorizontalPanel> getRemovableSliderOpacityPanels() {
		return removableSliderOpacityPanels;
	}
	
	public List<HorizontalPanel> getRemovableColorMapPanels() {
		return removableColorMapPanels;
	}
	
	public List<ContentPanel> getRemovableColorMapContentPanels() {
		return removableColorMapContentPanels;
	}
	
	public List<HorizontalPanel> getRemovableChannelSelectionPanels() {
		return removableChannelSelectionPanels;
	}
	
	public List<HorizontalPanel> getRemovableContrastEnhancementPanels() {
		return removableContrastEnhancementPanels;
	}

	public List<ContentPanel> getRemovableContrastEnhancementContentPanels() {
		return removableContrastEnhancementContentPanels;
	}
		
	public List<HorizontalPanel> getRemovableFormatRasterPanels() {
		return removableFormatRasterPanels;
	}

	public Html getLine() {
		return line;
	}
}