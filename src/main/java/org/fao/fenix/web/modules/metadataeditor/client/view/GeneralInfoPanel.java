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
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.SharingCodeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;

public class GeneralInfoPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private String[] leftTextFields = new String[]{BabelFish.print().title(),  
												   BabelFish.print().keywords(), 
												   BabelFish.print().metadataCode()};
	
	private List<String> mandatoryFields; 
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<SharingCodeModelData> sharingStore;
	
	private ComboBox<SharingCodeModelData> sharingList;
	
	private ListStore<CategoryModelData> categoryStore;
	
	private ComboBox<CategoryModelData> categoryList;
	
	private Map<String, VerticalPanel> fieldPanelMap;
	
	private Map<String, String> tooltipMap;
	
	private static final String RED = "#CA1616";
	
	@SuppressWarnings("unchecked")
	private List<Field> fields;
	
	@SuppressWarnings("unchecked")
	public GeneralInfoPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("700px");
		wrapper = new VerticalPanel();
		wrapper.setSpacing(5);
		fieldPanelMap = new HashMap<String, VerticalPanel>();
		fields = new ArrayList<Field>();
		fillTooltipMap();
		fillMandatoryFields();
	}
	
	public void fillTooltipMap() {
		tooltipMap = new HashMap<String, String>();
		tooltipMap.put(BabelFish.print().abstractAbstract(), BabelFish.print().tooltipabstractAbstract());
		tooltipMap.put(BabelFish.print().title(), BabelFish.print().tooltiptitle());
		tooltipMap.put(BabelFish.print().keywords(), BabelFish.print().tooltipkeywords());
		tooltipMap.put(BabelFish.print().startDate(), BabelFish.print().tooltipstartDate());
		tooltipMap.put(BabelFish.print().endDate(), BabelFish.print().tooltipendDate());
		tooltipMap.put(BabelFish.print().sharingCode(), BabelFish.print().tooltipsharingCode());
		tooltipMap.put(BabelFish.print().categories(), BabelFish.print().tooltipcategory());
		tooltipMap.put(BabelFish.print().region(), BabelFish.print().tooltipregion());
		tooltipMap.put(BabelFish.print().metadataCode(), BabelFish.print().tooltipcode());
	}
	
	public void fillMandatoryFields() {
		mandatoryFields = new ArrayList<String>();
		mandatoryFields.add(BabelFish.print().title()); 
		mandatoryFields.add(BabelFish.print().keywords()); 
		mandatoryFields.add(BabelFish.print().sharingCode()); 
		mandatoryFields.add(BabelFish.print().region()); 
		mandatoryFields.add(BabelFish.print().category());
	}
	
	public ContentPanel build(boolean isEditable) {
		wrapper.add(buildStepThreePanel());
		wrapper.add(buildGeneralInfoPanel(isEditable));
		wrapper.add(buildTextAreaPanel(BabelFish.print().abstractAbstract(), isEditable));
		wrapper.setSpacing(10);
		panel.add(wrapper);
		return panel;
	}
	
	private HorizontalPanel buildStepThreePanel() {
		HorizontalPanel stepThreePanel = new HorizontalPanel();
		stepThreePanel.setSpacing(10);
		stepThreePanel.setBorders(true);
		stepThreePanel.setWidth("750px");
		HTML stepThree = new HTML("<font color='#15428B'>" + BabelFish.print().stepThree() + "</font>");
		stepThreePanel.add(stepThree);
		return stepThreePanel;
	}
	
	public Boolean validate() {
		for (Field<String> field : fields) {
			if (!field.isValid()) 
				return false;
			if (gaulList.getSelection().size() < 1)
				return false;
			if (sharingList.getSelection().size() < 1)
				return false;
			if (categoryList.getSelection().size() < 1)
				return false;
		}
		return true;
	}
	
	private HorizontalPanel buildGeneralInfoPanel(boolean isEditable) {
		HorizontalPanel generalInfoPanel = new HorizontalPanel();
		generalInfoPanel.add(buildLeftPanel(isEditable));
		generalInfoPanel.add(buildRightPanel(isEditable));
		return generalInfoPanel;
	}
	
	private VerticalPanel buildLeftPanel(boolean isEditable) {
		VerticalPanel leftPanel = new VerticalPanel();
		leftPanel.setSpacing(5);
		for (String field : leftTextFields) {
			VerticalPanel textFieldPanel = buildTextFieldPanel(field, isEditable);
			leftPanel.add(textFieldPanel);
		}
		VerticalPanel listFieldPanel = buildSharingCodeListFieldPanel(BabelFish.print().sharingCode(), isEditable);
		leftPanel.add(listFieldPanel);
		return leftPanel;
	}
	
	private VerticalPanel buildRightPanel(boolean isEditable) {
		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSpacing(5);
		VerticalPanel startDateFieldPanel = buildDateFieldPanel(BabelFish.print().startDate(), isEditable);
		rightPanel.add(startDateFieldPanel);
		VerticalPanel endDateFieldPanel = buildDateFieldPanel(BabelFish.print().endDate(), isEditable);
		rightPanel.add(endDateFieldPanel);
		VerticalPanel regionPanel = buildListFieldPanel(BabelFish.print().region(), isEditable);
		rightPanel.add(regionPanel);
		VerticalPanel categoryPanel = buildCategoryListFieldPanel(BabelFish.print().category(), isEditable);
		rightPanel.add(categoryPanel);
		return rightPanel;
	}
	
	private VerticalPanel buildTextFieldPanel(String field, boolean isEditable) {
		VerticalPanel textFieldPanel = new VerticalPanel();
		textFieldPanel.setSpacing(5);
		textFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (mandatoryFields.contains(field))
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		else
			label = new HTML("<b>" + field + ": </b>");
		TextField<String> textbox = new TextField<String>();
		label.setWidth("200px");
		textbox.setWidth("350px");
		if(!field.equals(BabelFish.print().metadataCode()))
			textbox.setAllowBlank(!isEditable);
		textbox.setEmptyText(field);
		textFieldPanel.add(label);
		textFieldPanel.add(textbox);
		textFieldPanel.setData(field, textbox);
		textbox.setReadOnly(!isEditable);
		fieldPanelMap.put(field, textFieldPanel);
		fields.add(textbox);
		return textFieldPanel;
	}
	
	private VerticalPanel buildListFieldPanel(String field, boolean isEditable) {
		VerticalPanel textFieldPanel = new VerticalPanel();
		textFieldPanel.setSpacing(5);
		textFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (!mandatoryFields.contains(field))
			label = new HTML("<b>" + field + ": </b>");
		else
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		label.setWidth("200px");
		gaulList.setWidth("350px");
		gaulList.setAllowBlank(!isEditable);
		textFieldPanel.add(label);
		textFieldPanel.add(gaulList);
		textFieldPanel.setData(field, gaulList);
		gaulList.setReadOnly(!isEditable);
		fieldPanelMap.put(field, textFieldPanel);
		return textFieldPanel;
	}
	
	private VerticalPanel buildSharingCodeListFieldPanel(String field, boolean isEditable) {
		VerticalPanel sharingCodeFieldPanel = new VerticalPanel();
		sharingCodeFieldPanel.setSpacing(5);
		sharingCodeFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (!mandatoryFields.contains(field))
			label = new HTML("<b>" + field + ": </b>");
		else
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		sharingStore = new ListStore<SharingCodeModelData>(); 
		fillSharingStore();
		sharingList = new ComboBox<SharingCodeModelData>();
		sharingList.setTriggerAction(TriggerAction.ALL);
		sharingList.setStore(sharingStore);
		sharingList.setDisplayField("sharingCode");
		label.setWidth("200px");
		sharingList.setWidth("350px");
		sharingList.setAllowBlank(!isEditable);
		sharingCodeFieldPanel.add(label);
		sharingCodeFieldPanel.add(sharingList);
		sharingCodeFieldPanel.setData(field, sharingList);
		sharingList.setReadOnly(!isEditable);
		fieldPanelMap.put(field, sharingCodeFieldPanel);
		return sharingCodeFieldPanel;
	}
	
	private VerticalPanel buildCategoryListFieldPanel(String field, boolean isEditable) {
		VerticalPanel sharingCodeFieldPanel = new VerticalPanel();
		sharingCodeFieldPanel.setSpacing(5);
		sharingCodeFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (!mandatoryFields.contains(field))
			label = new HTML("<b>" + field + ": </b>");
		else
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		categoryStore = new ListStore<CategoryModelData>(); 
		categoryList = new ComboBox<CategoryModelData>();
		categoryList.setTriggerAction(TriggerAction.ALL);
		categoryList.setStore(categoryStore);
		categoryList.setDisplayField("categoryName");
		MEController.fillCategoryPanel(categoryStore);
		label.setWidth("200px");
		categoryList.setWidth("350px");
		categoryList.setAllowBlank(!isEditable);
		sharingCodeFieldPanel.add(label);
		sharingCodeFieldPanel.add(categoryList);
		sharingCodeFieldPanel.setData(field, categoryList);
		categoryList.setReadOnly(!isEditable);
		fieldPanelMap.put(field, sharingCodeFieldPanel);
		return sharingCodeFieldPanel;
	}
	
	private void fillSharingStore() {
		sharingStore.add(new SharingCodeModelData("Public"));
		sharingStore.add(new SharingCodeModelData("PublicAndDownload"));
//		sharingStore.add(new SharingCodeModelData("Shared"));
//		sharingStore.add(new SharingCodeModelData("SharedAndDownload"));
//		sharingStore.add(new SharingCodeModelData("Private"));
	}
	
	private VerticalPanel buildDateFieldPanel(String field, boolean isEditable) {
		VerticalPanel textFieldPanel = new VerticalPanel();
		textFieldPanel.setSpacing(5);
		textFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (!mandatoryFields.contains(field))
			label = new HTML("<b>" + field + ": </b>");
		else
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		DateField dateField = new DateField();
		label.setWidth("200px");
		dateField.setWidth("350px");
		textFieldPanel.add(label);
		textFieldPanel.add(dateField);
		textFieldPanel.setData(field, dateField);
		dateField.setReadOnly(!isEditable);
		fieldPanelMap.put(field, textFieldPanel);
		fields.add(dateField);
		return textFieldPanel;
	}
	
	private VerticalPanel buildTextAreaPanel(String field, boolean isEditable) {
		VerticalPanel textFieldPanel = new VerticalPanel();
		textFieldPanel.setSpacing(10);
		textFieldPanel.setToolTip(tooltipMap.get(field));
		HTML label = null;
//		System.out.println(field + "? " + (mandatoryFields.contains(field)));
		if (!mandatoryFields.contains(field))
			label = new HTML("<b>" + field + ": </b>");
		else
			label = new HTML("<b><font color='" + RED + "'>" + field + ": </font></b>");
		TextArea textarea = new TextArea();
		label.setWidth("200px");
		textarea.setSize("730px", "100px");
		textFieldPanel.add(label);
		textFieldPanel.add(textarea);
		textFieldPanel.setData(field, textarea);
		textarea.setReadOnly(!isEditable);
		fieldPanelMap.put(field, textFieldPanel);
		fields.add(textarea);
		return textFieldPanel;
	}

	public ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public Map<String, VerticalPanel> getFieldPanelMap() {
		return fieldPanelMap;
	}

	public ListStore<SharingCodeModelData> getSharingStore() {
		return sharingStore;
	}

	public ComboBox<SharingCodeModelData> getSharingList() {
		return sharingList;
	}

	public ListStore<CategoryModelData> getCategoryStore() {
		return categoryStore;
	}

	public ComboBox<CategoryModelData> getCategoryList() {
		return categoryList;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
}
