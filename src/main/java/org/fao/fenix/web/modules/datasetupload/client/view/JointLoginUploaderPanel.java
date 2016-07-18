package org.fao.fenix.web.modules.datasetupload.client.view;

import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.datasetupload.client.control.JointLoginUploaderController;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class JointLoginUploaderPanel {

	private FormPanel panel;
	
	private FileUploadField file;
	
	private FileUploadField metadata;
	
	private FormData formData;
	
	private Button cancel;
	
	private Button uploadButton;
	
	private TextField<String> usernameField;
	
	private TextField<String> passwordField;
	
	private SimpleComboBox<String> policyCombo;
	
	private SimpleComboBox<String> delimiterCombo;
	
	private LayoutContainer remarksArea;
	
	private FieldSet remarksAreaFieldSet;
	
	private HTML remarksHTML;  
	
	public JointLoginUploaderPanel() {
		panel = new FormPanel();
	}
	
	public FormPanel build(String urlServletLoginAndUpload, FSATMISTabPanel tabPanel, ListBox dataSource) {
		
		panel.setStyleAttribute("padding", "10px 10px 10px 10px");
		panel.setAction(urlServletLoginAndUpload);
		panel.setEncoding(Encoding.MULTIPART);  
		panel.setMethod(Method.POST);  
		panel.setBorders(false);
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
		panel.setFieldWidth(100);
		panel.setLabelWidth(75);
		panel.setAutoWidth(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.setLabelAlign(LabelAlign.LEFT);
		formLayout.setLabelSeparator("");
		panel.setLayout(formLayout);
		
		formData = new FormData();
		formData.setHeight(50);
		formData.setWidth(300);
		
		panel.add(buildUsernameField(), formData);
		panel.add(buildPasswordField(), formData);
		panel.add(buildFileField(), formData);
		panel.add(addMetadataField(), formData);
		panel.add(buildDelimiterCombo(), formData);
		panel.add(buildUploadPolicyCombo(), formData);
		panel.add(buildButtonBar());
//		panel.setButtonBar(buildButtonBar());
		panel.add(buildRemarksArea(), formData);
		
		panel.addListener(Events.BeforeSubmit, JointLoginUploaderController.getBeforeSubmitListener(this));
		panel.addListener(Events.Submit, JointLoginUploaderController.getAfterSubmitFormListener(this, tabPanel, dataSource));
		
		uploadButton.addSelectionListener(JointLoginUploaderController.getUploadListener(this));
		
		return panel;
	}
	
	private TextField<String> buildUsernameField() {
		usernameField = new TextField<String>();
		usernameField.setFieldLabel(BabelFish.print().username());
		usernameField.setLabelStyle("font-weight:bold;");
		usernameField.setAutoHeight(true);
		usernameField.setName("username");
//		if(usernameField.getValue()!=null)
//			usernameField.setName(usernameField.getValue());
		return usernameField;
	}
	
	private TextField<String> buildPasswordField() {
		passwordField = new TextField<String>();
		passwordField.setPassword(true);
		passwordField.setFieldLabel(BabelFish.print().password());
		passwordField.setLabelStyle("font-weight:bold;");
		passwordField.setAutoHeight(true);
		passwordField.setName("password");
//		if(passwordField.getValue()!=null)
//			passwordField.setName(passwordField.getValue());
		return passwordField;
	}
	
	private FileUploadField buildFileField() {
		file = new FileUploadField(); 
		file.setButtonOffset(10);
		file.setAllowBlank(false);  
		file.setFieldLabel(BabelFish.print().file());
		file.setLabelStyle("font-weight:bold;");
		file.setName("fileName");
		file.setWidth("75px");
		return file;
	}
	
	private FileUploadField addMetadataField() {
		metadata = new FileUploadField();  
		metadata.setButtonOffset(10);
		metadata.setAllowBlank(true);  
		metadata.setFieldLabel(BabelFish.print().associatedMetadataFile()); 
		metadata.setLabelStyle("font-weight:bold;");
		metadata.setName("metadataFileName");
		metadata.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
		metadata.setWidth("75px");
		return metadata;
	}
	
	protected SimpleComboBox<String> buildUploadPolicyCombo() {
		policyCombo = new SimpleComboBox<String>();
		policyCombo.setTriggerAction(TriggerAction.ALL);
		policyCombo.setFieldLabel(BabelFish.print().uploadPolicy());
		policyCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().uploadPolicyExplanation()));   
		policyCombo.setLabelStyle("font-weight:bold;");
		policyCombo.add(BabelFish.print().ignore());
		policyCombo.add(BabelFish.print().overwrite());
		policyCombo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(policyCombo));
		policyCombo.setValue(policyCombo.getStore().getAt(0));
		if(policyCombo.getValue()!=null)
			policyCombo.setName(policyCombo.getStore().getAt(policyCombo.getSelectedIndex()).getValue());  
		return policyCombo;
	}
	
	protected SimpleComboBox<String> buildDelimiterCombo() {
		delimiterCombo = new SimpleComboBox<String>();
		delimiterCombo.setTriggerAction(TriggerAction.ALL);
		delimiterCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().delimiterExplanation()));   
		delimiterCombo.setFieldLabel(BabelFish.print().fileDelimiter());
		delimiterCombo.setLabelStyle("font-weight:bold;");
		delimiterCombo.add(",");
		delimiterCombo.add(";");
		delimiterCombo.add("#");  
		delimiterCombo.setValue(delimiterCombo.getStore().getAt(0));
		if(delimiterCombo.getValue()!=null)
			delimiterCombo.setName(delimiterCombo.getStore().getAt(delimiterCombo.getSelectedIndex()).getValue());  
		delimiterCombo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(delimiterCombo));
		return delimiterCombo;
	}
	
	protected LayoutContainer buildRemarksArea() {
		remarksArea = new LayoutContainer();
		remarksArea.setScrollMode(Scroll.AUTO);
		FormLayout formLayout = new FormLayout();  
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
		return remarksArea;
	}
	
	public void clearRemarksArea() {
		remarksHTML.setHTML(""); 
	}
	
	public void fillRemarksArea(String remark) {
		remarksAreaFieldSet.show();
		remarksAreaFieldSet.setHeading(BabelFish.print().remarks()); 
		remarksHTML.setHTML(remark);  
	}
	
	protected ButtonBar buildButtonBar() {
		ButtonBar buttonBar = new ButtonBar();
//		buttonBar.setButtonAlign(HorizontalAlignment.CENTER);
//		buttonBar.setCellSpacing(10);
		buttonBar.setSpacing(10);
		cancel = new Button(BabelFish.print().cancel());
		uploadButton = new Button(BabelFish.print().upload());
//		cancel.addSelectionListener(UploaderController.getCancelListener(this)); // DO I NEED THIS?
		uploadButton.setText(BabelFish.print().upload());
		buttonBar.add(uploadButton);
		buttonBar.add(cancel);
		return buttonBar;
	}
	
	
	public FormPanel build(String urlServletLoginAndUpload) {
		
		//	panel.setStyleAttribute("padding", "5px 5px 5px 5px");
			panel.setAction(urlServletLoginAndUpload);
			panel.setEncoding(Encoding.MULTIPART);  
			panel.setMethod(Method.POST);  
			panel.setBorders(false);
			panel.setBodyBorder(false);
			panel.setHeaderVisible(false);
			panel.setButtonAlign(HorizontalAlignment.CENTER);
			panel.setFieldWidth(100);
			panel.setLabelWidth(75);
			panel.setAutoWidth(true);
		
			
			FormLayout formLayout = new FormLayout();
			formLayout.setLabelAlign(LabelAlign.LEFT);
			formLayout.setLabelSeparator("");
			panel.setLayout(formLayout);
			
			formData = new FormData();
			formData.setHeight(25);
			formData.setWidth(300);
			
			panel.add(buildUsernameField(), formData);
			panel.add(buildPasswordField(), formData);
			panel.add(buildFileField(), formData);
			panel.add(addMetadataField(), formData);
			panel.add(buildDelimiterCombo(), formData);
			panel.add(buildUploadPolicyCombo(), formData);
			panel.add(buildButtonBar());
//			panel.setButtonBar(buildButtonBar());
			panel.add(buildRemarksArea(), formData);
			
			panel.addListener(Events.BeforeSubmit, JointLoginUploaderController.getBeforeSubmitListener(this));
		//	panel.addListener(Events.Submit, JointLoginUploaderController.getAfterSubmitFormListener(this, tabPanel, dataSource));
			
			uploadButton.addSelectionListener(JointLoginUploaderController.getUploadListener(this));
			
			return panel;
		}
	

	public FormPanel getPanel() {
		return panel;
	}

	public FileUploadField getFile() {
		return file;
	}

	public FileUploadField getMetadata() {
		return metadata;
	}

	public FormData getFormData() {
		return formData;
	}

	public Button getCancel() {
		return cancel;
	}

	public Button getUploadButton() {
		return uploadButton;
	}

	public TextField<String> getUsernameField() {
		return usernameField;
	}

	public TextField<String> getPasswordField() {
		return passwordField;
	}

	public SimpleComboBox<String> getPolicyCombo() {
		return policyCombo;
	}

	public SimpleComboBox<String> getDelimiterCombo() {
		return delimiterCombo;
	}
	
}