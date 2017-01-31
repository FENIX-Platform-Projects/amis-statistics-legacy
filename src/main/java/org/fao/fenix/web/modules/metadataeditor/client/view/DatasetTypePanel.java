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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.DatasetTypeModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;

public class DatasetTypePanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private RadioButton radioScratch; 
	
	private RadioButton radioDB;
	
	private HorizontalPanel scratchPanel;
	
	private HorizontalPanel dbPanel;
	
	private TextField<String> scratchTextbox;
	
	private ComboBox<DatasetTypeModelData> dbListbox;
	
	private ListStore<DatasetTypeModelData> datasetTypeStore;
	
	private static final String RED = "#CA1616";

	public DatasetTypePanel() {
		
		panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setHeaderVisible(false);
		
		radioScratch = new RadioButton("datasetType", BabelFish.print().createNew()+ ": ");
		radioDB = new RadioButton("datasetType", BabelFish.print().getExistingTemplate() + ": ");
		
		wrapper = new VerticalPanel();
		scratchPanel = new HorizontalPanel();
		dbPanel = new HorizontalPanel();
		scratchTextbox = new TextField<String>();
		scratchTextbox.setAllowBlank(false);
		
		datasetTypeStore = new ListStore<DatasetTypeModelData>(); 
		dbListbox = new ComboBox<DatasetTypeModelData>();
		dbListbox.setTriggerAction(TriggerAction.ALL);
		dbListbox.setStore(datasetTypeStore);
		dbListbox.setDisplayField("datasetType");
		dbListbox.setTriggerAction(TriggerAction.ALL);
		
		format();
	}

	public ContentPanel build(boolean isEditable) {
		wrapper.add(buildStepOnePanel());
		wrapper.add(buildDbPanel(isEditable));
		wrapper.add(buildScratchPanel(isEditable));
		panel.add(wrapper);
		return panel;
	}
	
	private HorizontalPanel buildStepOnePanel() {
		HorizontalPanel stepOnePanel = new HorizontalPanel();
		stepOnePanel.setSpacing(10);
		stepOnePanel.setBorders(true);
		stepOnePanel.setWidth("750px");
		HTML stepOne = new HTML("<font color='#15428B'>" + BabelFish.print().stepOne() + "</font>");
		stepOnePanel.add(stepOne);
		return stepOnePanel;
	}
	
	public Boolean validate() {
		if (radioScratch.isChecked()) {
			if (!scratchTextbox.isValid())
				return false;
		} else if (radioDB.isChecked()) {
			if (dbListbox.getSelection().size() < 1)
				return false;
		}
		return true;
	}
	
	public void enhanceRadioButtons() {
		radioScratch.addClickListener(MEController.radioScratchListener(this));
		radioDB.addClickListener(MEController.radioDbListener(this));
	}

	private HorizontalPanel buildScratchPanel(boolean isEditable) {
		HTML label  = new HTML("<b><font color='" + RED + "'>" + BabelFish.print().templateName() + ":</font></b>");
		scratchPanel.add(radioScratch);
		scratchPanel.add(label);
		scratchPanel.add(scratchTextbox);
		scratchPanel.setData("label", label);
		label.setWidth("150px");
		label.setVisible(false);
		scratchTextbox.setVisible(false);
		scratchTextbox.setReadOnly(!isEditable);
		return scratchPanel;
	}
	
	private HorizontalPanel buildDbPanel(boolean isEditable) {
		HTML label  = new HTML("<b>" + BabelFish.print().existingTemplate() + ":</b>");
		dbPanel.add(radioDB);
		dbPanel.add(label);
		dbPanel.add(dbListbox);
		dbPanel.setData("label", label);
		label.setWidth("150px");
		dbListbox.setReadOnly(!isEditable);
		return dbPanel;
	}
	
	private void format() {
		radioDB.setChecked(true);
		radioScratch.setChecked(false);
		radioScratch.setWidth("200px");
		radioDB.setWidth("200px");
		panel.setHeight("600px");
		wrapper.setSpacing(10);
		scratchPanel.setSpacing(10);
		scratchPanel.setBorders(true);
		scratchPanel.setWidth("750px");
		dbPanel.setSpacing(10);
		dbPanel.setBorders(true);
		dbPanel.setWidth("750px");
		scratchTextbox.setWidth("200px");
		dbListbox.setWidth("200px");
	}

	public HorizontalPanel getScratchPanel() {
		return scratchPanel;
	}

	public HorizontalPanel getDbPanel() {
		return dbPanel;
	}

	public RadioButton getRadioScratch() {
		return radioScratch;
	}

	public RadioButton getRadioDB() {
		return radioDB;
	}

	public TextField<String> getScratchTextbox() {
		return scratchTextbox;
	}

	public ComboBox<DatasetTypeModelData> getDbListbox() {
		return dbListbox;
	}

	public ListStore<DatasetTypeModelData> getDatasetTypeStore() {
		return datasetTypeStore;
	}

	public ContentPanel getPanel() {
		return panel;
	}

}
