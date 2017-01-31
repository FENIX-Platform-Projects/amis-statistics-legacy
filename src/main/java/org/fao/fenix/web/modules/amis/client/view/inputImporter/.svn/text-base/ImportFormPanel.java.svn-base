package org.fao.fenix.web.modules.amis.client.view.inputImporter;

import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.HTML;

public class ImportFormPanel {
	
	private LayoutContainer layoutContainer;
	private FormPanel formPanel;	
	private FileUploadField dataFile;	
	private FileUploadField metadataFile;
	private SimpleComboBox<String> delimiterCombo;
	private final static String FIELD_WIDTH = "425px";
	public final static String LABEL_WIDTH = "75px";
	
	
	public ImportFormPanel() {
		layoutContainer = new LayoutContainer();
		layoutContainer.setBorders(false);
	}
	
	public LayoutContainer buildForm() {
		
		formPanel = new FormPanel();
		dataFile = new FileUploadField();
		metadataFile = new FileUploadField();
		delimiterCombo = new SimpleComboBox<String>();
		delimiterCombo.setTriggerAction(TriggerAction.ALL);	
		layoutContainer.add(buildFormPanel());
		return layoutContainer;
	}
	
	private FormPanel buildFormPanel() {
		formPanel.add(buildDataPanel());
		formPanel.add(buildMetadataPanel());
		//formPanel.add(addDelimiterCombo());
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
	
	public Html createLabel(String text) {
		Html label = new Html("<b>" + text + ": </b>");
		label.setWidth(LABEL_WIDTH);
		return label;
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

	public SimpleComboBox<String> getDelimiterCombo() {
		return delimiterCombo;
	}

	public static String getFieldWidth() {
		return FIELD_WIDTH;
	}

	public static String getLabelWidth() {
		return LABEL_WIDTH;
	}

}
