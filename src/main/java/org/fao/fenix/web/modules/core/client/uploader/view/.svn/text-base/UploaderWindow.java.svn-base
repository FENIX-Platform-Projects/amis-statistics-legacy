package org.fao.fenix.web.modules.core.client.uploader.view;


import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class UploaderWindow extends FenixWindow {

	protected VerticalPanel panel;

	private FileUploadField metadata;
	private FileUploadField file;
	protected FormPanel formPanel;
	private Button cancel;
	private Button uploadButton;
	private Button clear;
	private ListBox resourceTypeList;
	private HTML message;


	private LayoutContainer mainContainer;  
	private LayoutContainer leftColumnContainer;  
	private LayoutContainer remarksArea;
	private FormLayout formLayout;
	private FormData formData;
	private FieldSet remarksAreaFieldSet;

	private HTML remarksHTML;  

	public void setWindowProperties(String title) {
		setCenterProperties();
		addCenterPartToWindow();
		formatWindow(title);
	}

	protected FormPanel buildBasicFormPanel(String urlServletUpload) {
		formPanel = new FormPanel();
		formPanel.setStyleAttribute("padding", "10px 10px 10px 10px");
		formPanel.setAction(urlServletUpload);
		formPanel.setEncoding(Encoding.MULTIPART);  
		formPanel.setMethod(Method.POST);  
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		//formPanel.setFieldWidth(300);
		formPanel.setHeaderVisible(false);


		//formPanel.setSize(300, -1);   
		formPanel.setButtonAlign(HorizontalAlignment.CENTER);   
		formPanel.setLayout(new FlowLayout());   
	
    	mainContainer = new LayoutContainer();   
		mainContainer.setLayout(new ColumnLayout());

		leftColumnContainer = new LayoutContainer();  

		formLayout = new FormLayout();   
		formLayout.setLabelAlign(LabelAlign.TOP);  
		//layout.setLabelWidth(300);
		formLayout.setLabelSeparator("");
		//layout.setPadding(20);

		leftColumnContainer.setLayout(formLayout);   

		formData = new FormData("95%");   
		formData.setHeight(35);
		//formData.setMargins(new Margins(20));

		mainContainer.add(leftColumnContainer, new ColumnData(0.9));   
		
		formPanel.add(mainContainer);   

		
		return formPanel;
	}

	private void formatWindow(String header) {
		getCenter().setHeaderVisible(false);
		getWindow().setHeading(header);
		getWindow().setSize("450px", "500px");
	}

	protected void addBasicFileUploadFields() {
		addFileField();
		addMetadataField();
	}
	
//	protected void addCodingMappingFileUploadFields() {
//		addFileField();
//		addSourceMetadataField();
//		addDestinationMetadataField();
//	}


	protected void addUploadPolicyCombo() {
		SimpleComboBox<String> combo = new SimpleComboBox<String>();
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setFieldLabel(BabelFish.print().uploadPolicy());
		combo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		combo.setLabelStyle("font-weight:bold;");

		combo.add(BabelFish.print().ignore());
		combo.add(BabelFish.print().overwrite());

		combo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(combo));

		combo.setValue(combo.getStore().getAt(0));

		if(combo.getValue()!=null)
			combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());  

		leftColumnContainer.add(combo, formData);   
	}
	
	protected void addCodingUploadPolicyCombo() {
		SimpleComboBox<String> combo = new SimpleComboBox<String>();
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setFieldLabel(BabelFish.print().uploadPolicy());
		combo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		combo.setLabelStyle("font-weight:bold;");

		combo.add(BabelFish.print().ignore());
		combo.add(BabelFish.print().overwrite());
		combo.add(BabelFish.print().replace());

		combo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(combo));

		combo.setValue(combo.getStore().getAt(0));

		if(combo.getValue()!=null)
			combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());  

		leftColumnContainer.add(combo, formData);   
	}


	protected void addDelimiterCombo() {

		SimpleComboBox<String> combo = new SimpleComboBox<String>();
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().delimiterExplanation()));   
		combo.setFieldLabel(BabelFish.print().fileDelimiter());
		combo.setLabelStyle("font-weight:bold;");
		combo.add(",");
		combo.add(";");
		combo.add("#");  

		combo.setValue(combo.getStore().getAt(0));

		if(combo.getValue()!=null)
			combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());  

		combo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(combo));

		leftColumnContainer.add(combo, formData);   
	}


	public void addFileField() {
		file = new FileUploadField(); 
		file.setButtonOffset(10);
		file.setAllowBlank(false);  
		file.setFieldLabel(BabelFish.print().file());
		file.setLabelStyle("font-weight:bold;");
		file.setName("fileName");

		leftColumnContainer.add(file, formData);   
	}

	private void addMetadataField() {
		metadata = new FileUploadField();  
		metadata.setButtonOffset(10);
		metadata.setAllowBlank(true);  
		metadata.setFieldLabel(BabelFish.print().associatedMetadataFile()); 
		metadata.setLabelStyle("font-weight:bold;");
		metadata.setName("metadataFileName");
		metadata.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
		
		leftColumnContainer.add(metadata, formData);   
	}
	
//	private void addSourceMetadataField() {
//		metadata = new FileUploadField();  
//		metadata.setButtonOffset(10);
//		metadata.setAllowBlank(true);  
//		metadata.setFieldLabel(I18N.print().associatedSourceMetadataFile()); 
//		metadata.setLabelStyle("font-weight:bold;");
//		metadata.setName("metadataFileName");
//		metadata.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
//		
//		leftColumnContainer.add(metadata, formData);   
//	}
//	
//	private void addDestinationMetadataField() {
//		metadata = new FileUploadField();  
//		metadata.setButtonOffset(10);
//		metadata.setAllowBlank(true);  
//		metadata.setFieldLabel(I18N.print().associatedDestinationMetadataFile()); 
//		metadata.setLabelStyle("font-weight:bold;");
//		metadata.setName("metadataFileName");
//		metadata.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
//		
//		leftColumnContainer.add(metadata, formData);   
//	}


	protected void addResourceTypeCombo() {

		SimpleComboBox<String> combo = new SimpleComboBox<String>();
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setFieldLabel(BabelFish.print().resourceType());
		combo.setLabelStyle("font-weight:bold;");
		combo.add(BabelFish.print().datasetFile());
		combo.add(BabelFish.print().codesFile());

		combo.setValue(combo.getStore().getAt(0));

		if(combo.getValue()!=null)
			combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());  

		combo.addListener(Events.SelectionChange, UploaderController.getResourceTypeListener(combo, formPanel));

		leftColumnContainer.add(combo, formData);   


	}

	private HorizontalPanel buildResourceTypePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		HTML label1 = new HTML("<b>" + BabelFish.print().resourceType() + ": </b>");
		label1.setWidth("100px");
		resourceTypeList = new ListBox();
		resourceTypeList.addItem(BabelFish.print().datasetFile());
		resourceTypeList.addItem(BabelFish.print().codingSystem());
		resourceTypeList.setName(resourceTypeList.getItemText(resourceTypeList.getSelectedIndex()));
		resourceTypeList.setWidth("150px");
		resourceTypeList.addChangeListener(UploaderController.getResourceTypeListener(resourceTypeList));
		panel.add(label1);
		panel.add(resourceTypeList);
		return panel;
	}



	protected void addRemarksArea() {

		remarksArea = new LayoutContainer();
		remarksArea.setScrollMode(Scroll.AUTO);

		formLayout = new FormLayout();  
		formLayout.setLabelAlign(LabelAlign.TOP);  
		remarksArea.setLayout(formLayout);  

		remarksAreaFieldSet = new FieldSet();
		remarksAreaFieldSet.setCollapsible(true);
		remarksAreaFieldSet.setWidth(350);

		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		remarksAreaFieldSet.setLayout(layout); 

		remarksHTML = new HTML("");
		remarksAreaFieldSet.add(remarksHTML); 

		remarksAreaFieldSet.hide();
		remarksArea.add(remarksAreaFieldSet, formData);  

		formPanel.add(remarksArea);  


	}

	public void fillRemarksArea(String remark) {
		remarksAreaFieldSet.show();
		remarksAreaFieldSet.setHeading(BabelFish.print().remarks()); 
		remarksHTML.setHTML(remark);  
	}

	public void addToFeedbackArea(Widget widget) {
		remarksAreaFieldSet.add(widget);
		remarksAreaFieldSet.getLayout().layout();

	}

	public void clearRemarksArea() {
		remarksHTML.setHTML(""); 
	}


	protected void addButtons() {

//		ButtonBar buttonBar = new ButtonBar();
//		buttonBar.setAlignment(HorizontalAlignment.CENTER);
//		buttonBar.setSpacing(10);

		cancel = new Button();
		uploadButton = new Button();
		//clear = new Button();
		cancel.setText(BabelFish.print().cancel());
		cancel.addSelectionListener(UploaderController.getCancelListener(this));
		uploadButton.setText(BabelFish.print().upload());
		//clear.setText(I18N.print().clear());

//		buttonBar.add(uploadButton);
//		buttonBar.add(cancel);
//
//		formPanel.setButtonBar(buttonBar);

		formPanel.setButtonAlign(HorizontalAlignment.CENTER);
		formPanel.addButton(uploadButton);
		formPanel.addButton(cancel);
	}

	protected HorizontalPanel buildMessagePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		message = new HTML();
		panel.add(message);
		return panel;
	}

	public void setMessage(String message) {
		this.message.setHTML(message);
	}

	public FileUploadField getMetadata() {
		return metadata;
	}
	public FileUploadField getFile() {
		return file;
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel form) {
		this.formPanel = form;
	}
	public Button getUploadButton() {
		return uploadButton;
	}

	public VerticalPanel getPanel() {
		return panel;
	}

	public void setPanel(VerticalPanel panel) {
		this.panel = panel;
	}

	public LayoutContainer getLeftColumnContainer() {
		return leftColumnContainer;
	}

	public FormData getFormData() {
		return formData;
	}

	
}