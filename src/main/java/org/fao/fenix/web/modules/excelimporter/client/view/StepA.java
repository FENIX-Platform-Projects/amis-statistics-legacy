package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.HTML;

public class StepA extends ExcelImporterStepPanel {

	private FormPanel formPanel;
	
	private FileUploadField dataFile;
	
	private FileUploadField metadataFile;
	
	private SimpleComboBox<String> uploadPolicyCombo;
	
	private SimpleComboBox<String> delimiterCombo;
	
	private SimpleComboBox<String> excelTemplate;
	
	private TextField<String> measurementUnitValue;
	
	private IconButton info;
	
	private HorizontalPanel muValuePanel;
	
	private final static String FIELD_WIDTH = "425px";
	
	public final static String LONG_LABEL_WIDTH = "100px";
	
	private final static String SHORT_FIELD_WIDTH = "375px";
	
	public StepA(String suggestion, String width) {
		super(suggestion, width);
		formPanel = new FormPanel();
		dataFile = new FileUploadField();
		metadataFile = new FileUploadField();
		uploadPolicyCombo = new SimpleComboBox<String>();
		uploadPolicyCombo.setTriggerAction(TriggerAction.ALL);	
		delimiterCombo = new SimpleComboBox<String>();
		delimiterCombo.setTriggerAction(TriggerAction.ALL);	
		excelTemplate = new SimpleComboBox<String>();
		excelTemplate.setTriggerAction(TriggerAction.ALL);	
		info = new IconButton("info");
		muValuePanel = new HorizontalPanel();
		this.getLayoutContainer().add(buildFormPanel());
	}
	
	private FormPanel buildFormPanel() {
		formPanel.add(buildDataPanel());
		formPanel.add(buildMetadataPanel());
//		formPanel.add(addDelimiterCombo());
		formPanel.add(addUploadPolicyCombo());
//		formPanel.add(addExcelTemplateCombo());
//		formPanel.setBottomComponent(addMeasurementUnitValue());
		formPanel.add(addMeasurementUnitValue());
		muValuePanel.setVisible(false);
		formPanel.setHeaderVisible(false);
		formPanel.setBodyBorder(false);
		return formPanel;
	}
	
	private HorizontalPanel buildDataPanel() {
		HorizontalPanel dataPanel = new HorizontalPanel();
		dataPanel.setSpacing(10);
		dataFile.setEmptyText("Excel or CSV file containing your data");
		dataFile.setLabelStyle("font-weight:bold;");
		dataFile.setName("DATASET");
		dataFile.setButtonOffset(10);
		dataFile.setWidth(FIELD_WIDTH);
		dataFile.setAllowBlank(false);
		dataFile.setAutoValidate(true);
		dataFile.addListener(Events.OnChange, ExcelImporterController.datasetFileListener(this));
		dataPanel.add(createLabel(BabelFish.print().datasetFile()));
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
		HTML label = new HTML("<i>conflict data that may already exist in the Workstation</i>");
		label.setWidth(FIELD_WIDTH);
		uploadPolicyCombo = new SimpleComboBox<String>();
		uploadPolicyCombo.setTriggerAction(TriggerAction.ALL);	
		uploadPolicyCombo.setFieldLabel(BabelFish.print().uploadPolicy());
		uploadPolicyCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		uploadPolicyCombo.setLabelStyle("font-weight:bold;");
		uploadPolicyCombo.add(BabelFish.print().ignore());
		uploadPolicyCombo.add(BabelFish.print().overwrite());
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
		HTML label = new HTML("<i>is the delimiter for this dataset</i>");
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
	
	public HorizontalPanel addExcelTemplateCombo() {
		HorizontalPanel comboPanel = new HorizontalPanel();
		comboPanel.setSpacing(10);
		HTML label = new HTML("<b><font size='1'>Excel Data Layout:</font></b>");
		label.setWidth(LONG_LABEL_WIDTH);
		excelTemplate = new SimpleComboBox<String>();
		excelTemplate.setTriggerAction(TriggerAction.ALL);
		excelTemplate.setFieldLabel(BabelFish.print().uploadPolicy());
		excelTemplate.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		excelTemplate.setLabelStyle("font-weight:bold;");
		excelTemplate.add("GIEWS Workstation");
		excelTemplate.add("World Bank");  // TODO restore
		excelTemplate.addListener(Events.SelectionChange, ExcelImporterController.excelDataLayoutListener(this));
		excelTemplate.setWidth(SHORT_FIELD_WIDTH);
		excelTemplate.setAllowBlank(false);
		excelTemplate.setAutoValidate(true);
		excelTemplate.setEmptyText("Please select an Excel template from this list.");
		excelTemplate.setSimpleValue("GIEWS Workstation");
		info.addSelectionListener(new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				new ExcelTemplateWindow().build();
			}
		});
		info.setToolTip("Data contained in you Excel file can be formatted in different ways. " +
						"The GIEWS Workstation can handle several Excel files structures, called <u>Layouts</u>. " +
						"Please click on this button to know more about each one of them.");
		comboPanel.add(label);
		comboPanel.add(excelTemplate);
		comboPanel.add(info);
//		comboPanel.setBorders(true);
		return comboPanel;
	}
	
	public HorizontalPanel addMeasurementUnitValue() {
		muValuePanel = new HorizontalPanel();
		muValuePanel.setSpacing(10);
//		muValuePanel.setBorders(true);
		muValuePanel.setVisible(false);
		HTML label = new HTML("<b><font size='1'>Measurement Unit:</font></b>");
		label.setWidth(LONG_LABEL_WIDTH);
		measurementUnitValue = new TextField<String>();
		measurementUnitValue.setWidth(SHORT_FIELD_WIDTH);
		measurementUnitValue.setName("TO BE DEFINED");
		measurementUnitValue.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				measurementUnitValue.setName("MU_VALUE_{" + measurementUnitValue.getValue() + "}");
				System.out.println("measurementUnitName is " + measurementUnitValue.getName());
			}
		});
		muValuePanel.add(label);
		muValuePanel.add(measurementUnitValue);
		return muValuePanel;
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

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public SimpleComboBox<String> getDelimiterCombo() {
		return delimiterCombo;
	}

	public SimpleComboBox<String> getExcelTemplate() {
		return excelTemplate;
	}

	public TextField<String> getMeasurementUnitValue() {
		return measurementUnitValue;
	}

	public IconButton getInfo() {
		return info;
	}

	public HorizontalPanel getMuValuePanel() {
		return muValuePanel;
	}
	
}