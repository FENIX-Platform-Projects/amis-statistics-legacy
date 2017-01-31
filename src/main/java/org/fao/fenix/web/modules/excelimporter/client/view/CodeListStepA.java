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
package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.HTML;


public class CodeListStepA extends ExcelImporterStepPanel {
	
	private FormPanel formPanel;
	
	private FileUploadField dataFile;
	
	private FileUploadField metadataFile;
	
	private SimpleComboBox<String> uploadPolicyCombo;
	
	private SimpleComboBox<String> delimiterCombo;
	
	private final static String FIELD_WIDTH = "425px";

	public CodeListStepA(String suggestion, String width) {
		super(suggestion, width);
		formPanel = new FormPanel();
		dataFile = new FileUploadField();
		metadataFile = new FileUploadField();
		uploadPolicyCombo = new SimpleComboBox<String>();
		uploadPolicyCombo.setTriggerAction(TriggerAction.ALL);	
		delimiterCombo = new SimpleComboBox<String>();
		delimiterCombo.setTriggerAction(TriggerAction.ALL);	
		this.getLayoutContainer().add(buildFormPanel());
	}
	
	private FormPanel buildFormPanel() {
		formPanel.add(buildDataPanel());
		formPanel.add(buildMetadataPanel());
		formPanel.add(addUploadPolicyCombo());
		formPanel.add(addDelimiterCombo());
		formPanel.setHeaderVisible(false);
		formPanel.setBodyBorder(false);
		return formPanel;
	}
	
	private HorizontalPanel buildDataPanel() {
		HorizontalPanel dataPanel = new HorizontalPanel();
		dataPanel.setSpacing(10);
		dataFile.setEmptyText("CSV file containing your data");
		dataFile.setLabelStyle("font-weight:bold;");
		dataFile.setName("DATASET");
		dataFile.setButtonOffset(10);
		dataFile.setWidth(FIELD_WIDTH);
		dataFile.setAllowBlank(false);
		dataFile.setAutoValidate(true);
//		dataFile.addListener(Events.OnChange, ExcelImporterController.datasetFileListener(this));
		dataPanel.add(createLabel(BabelFish.print().file()));
		dataPanel.add(dataFile);
		return dataPanel;
	}
	
	private HorizontalPanel buildMetadataPanel() {
		HorizontalPanel metadataPanel = new HorizontalPanel();
		metadataPanel.setSpacing(10);
		metadataFile.setEmptyText("Metadata XML file, if you have it.");
		metadataFile.setLabelStyle("font-weight:bold;");
		metadataFile.setButtonOffset(10);
		metadataFile.setWidth(FIELD_WIDTH);
		metadataFile.setName("METADATA");
		metadataPanel.add(createLabel(BabelFish.print().metadataFile()));
		metadataPanel.add(metadataFile);
		return metadataPanel;
	}
	
	private HorizontalPanel addUploadPolicyCombo() {
		HorizontalPanel comboPanel = new HorizontalPanel();
		comboPanel.setSpacing(10);
		HTML label = new HTML("<i>eventual conflict data already in the DB</i>");
		label.setWidth(FIELD_WIDTH);
		uploadPolicyCombo = new SimpleComboBox<String>();
		uploadPolicyCombo.setTriggerAction(TriggerAction.ALL);	
		uploadPolicyCombo.setFieldLabel(BabelFish.print().uploadPolicy());
		uploadPolicyCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		uploadPolicyCombo.setLabelStyle("font-weight:bold;");
		uploadPolicyCombo.add(BabelFish.print().ignore());
		uploadPolicyCombo.add(BabelFish.print().overwrite());
		uploadPolicyCombo.add(BabelFish.print().replace());
		uploadPolicyCombo.addListener(Events.SelectionChange, UploaderController.getUploadPolicyListener(uploadPolicyCombo));
		uploadPolicyCombo.setWidth(LABEL_WIDTH);
		uploadPolicyCombo.setAllowBlank(false);
		uploadPolicyCombo.setAutoValidate(true);
		uploadPolicyCombo.setEmptyText("Policy");
		uploadPolicyCombo.setSimpleValue(BabelFish.print().ignore());
		comboPanel.add(uploadPolicyCombo);
		comboPanel.add(label);
		return comboPanel;
	}
	
	public HorizontalPanel addDelimiterCombo() {
		HorizontalPanel comboPanel = new HorizontalPanel();
		comboPanel.setSpacing(10);
		HTML label = new HTML("<i>is the delimiter for this code list</i>");
		label.setWidth(FIELD_WIDTH);
		delimiterCombo = new SimpleComboBox<String>();
		delimiterCombo.setTriggerAction(TriggerAction.ALL);
		delimiterCombo.setFieldLabel(BabelFish.print().uploadPolicy());
		delimiterCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		delimiterCombo.setLabelStyle("font-weight:bold;");
		delimiterCombo.add(",");
		delimiterCombo.add("#");
		delimiterCombo.addListener(Events.SelectionChange, UploaderController.getDelimiterListener(delimiterCombo));
		delimiterCombo.setWidth(LABEL_WIDTH);
		delimiterCombo.setAllowBlank(false);
		delimiterCombo.setAutoValidate(true);
		delimiterCombo.setEmptyText("Delimiter");
		delimiterCombo.setSimpleValue(",");
		comboPanel.add(delimiterCombo);
		comboPanel.add(label);
		return comboPanel;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public FileUploadField getDataFile() {
		return dataFile;
	}

	public FileUploadField getMetadataFile() {
		return metadataFile;
	}

	public SimpleComboBox<String> getUploadPolicyCombo() {
		return uploadPolicyCombo;
	}

	public SimpleComboBox<String> getDelimiterCombo() {
		return delimiterCombo;
	}

	public static String getFieldWidth() {
		return FIELD_WIDTH;
	}
	
}
