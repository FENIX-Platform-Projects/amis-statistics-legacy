package org.fao.fenix.web.modules.amis.client.view.login;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Label;

public class AMISRegistrationForm extends FenixWindow {

//	public void build()
//	{
//		getWindow().setCollapsible(true);
//		getWindow().setHeading(AMISLanguage.print().ccbsFlag());
//		ContentPanel cp = new ContentPanel();
//		System.out.println("build!!!!!");
//		FormPanel form = new FormPanel();  
//		form.setHeading("Simple Form");  
//		form.setFrame(true);  
//		form.setWidth(350);  
//		
//		HorizontalPanel hp = new HorizontalPanel();
//		
//		Label fromMonth = new Label("Marketing Season");
//		fromMonth.setStyleName("ccbs-Label");
//		fromMonth.setWidth("120px");
//		TextField<String> firstName = new TextField<String>();  
//		//firstName.setFieldLabel("Name");  
//		firstName.setAllowBlank(false);  
//	//	firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());  
//	//	form.add(firstName);  
//		hp.add(fromMonth);
//		hp.add(firstName);
//		cp.add(hp);
////		getWindow()..setCenterProperties();
////		getCenter().add(form);
////		getWindow().add(form);
//		//cp.add(form);
//		getWindow().add(cp);
//		show();
//	}
	private VerticalPanel vpRegisterForm;
	private ContentPanel cpRegisterForm;
	private Label nameLabel;
	private Label surnameLabel;
	private Label countryLabel;
	private Label institutionLabel;
	private Label emailLabel;
	private TextField<String> nameTextField;
	private TextField<String> surnameTextField;
	private TextField<String> countryTextField;
	private TextField<String> institutionTextField;
	private TextField<String> emailTextField;
	private HorizontalPanel hpNameSurname;
	private HorizontalPanel hpCountry;
	private HorizontalPanel hpInstitution;
	private HorizontalPanel hpEmail;
	private VerticalPanel vpForm;
	private int labelNameSurnameSpace;
	private int textFieldNameSurnameSpace;
	
	public AMISRegistrationForm()
	{
		System.out.println("AMISRegistrationForm!!!!");
		vpRegisterForm = new VerticalPanel();
		cpRegisterForm = new ContentPanel();
		nameLabel = new Label();
		surnameLabel = new Label();
		countryLabel = new Label();
		institutionLabel = new Label();
		emailLabel = new Label();
		nameTextField = new TextField<String>();
		surnameTextField = new TextField<String>();
		countryTextField = new TextField<String>();
		institutionTextField = new TextField<String>();
		emailTextField = new TextField<String>();
		hpNameSurname = new HorizontalPanel();
		hpCountry = new HorizontalPanel();
		hpInstitution = new HorizontalPanel();
		hpEmail = new HorizontalPanel();
		labelNameSurnameSpace = 40;
		textFieldNameSurnameSpace = 10;
		vpForm = new VerticalPanel();
	}
	
	public void build()
	{
		getWindow().setCollapsible(true);
		getWindow().setSize("430px", "440px");
		getWindow().setHeading(AMISLanguage.print().cbsRegistrationForm());
		cpRegisterForm.setHeaderVisible(false);
		cpRegisterForm.setSize("450px", "580px");
		
		vpForm.add(new Html("<div style='font-weight:bold;font-size:20pt;' class='ccbs-Registration-Form-Label'>AMIS Registration Form</div>"));
		vpForm.add(new Html("<div style='text-align:justify;' class='ccbs-Registration-Form-Label'>This registration gives permission to enter or upload data to<br/>the AMIS Statistics application. Please enter the information<br/> below and press 'Request Access'.<p><p></div>"));
		
		vpForm.setSpacing(5);
		HorizontalPanel hpNS = createLabelPanel("Name", "Surname");
		vpForm.add(hpNS);		
		
		hpNameSurname = createNameUsernameTextFieldPanel();
		vpForm.add(hpNameSurname);
		
		vpForm.add(FormattingUtils.addVSpace(5));
		hpNS = createLabelPanel("Country", "");
		vpForm.add(hpNS);	
		vpForm.add(FormattingUtils.addVSpace(2));
		hpCountry = createBigTextFieldPanel("Country");
		vpForm.add(hpCountry);
		vpForm.add(FormattingUtils.addVSpace(5));
		hpNS = createLabelPanel("Institution", "");
		vpForm.add(hpNS);
		vpForm.add(FormattingUtils.addVSpace(2));
		hpInstitution = createBigTextFieldPanel("Institution");
		vpForm.add(hpInstitution);
		vpForm.add(FormattingUtils.addVSpace(5));
		hpNS = createLabelPanel("Email Address: (the one to be used for AMIS communications)", "");
		vpForm.add(hpNS);
		vpForm.add(FormattingUtils.addVSpace(2));
		hpEmail = createBigTextFieldPanel("Email");
		vpForm.add(hpEmail);
//		
//		TextField<String> firstName = new TextField<String>();  
//		//firstName.setFieldLabel("Name");  
//		firstName.setAllowBlank(false);  
//	//	firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());  
//	//	form.add(firstName);  
//		hp.add(fromMonth);
//		hp.add(firstName);
//		cp.add(hp);
////		getWindow()..setCenterProperties();
////		getCenter().add(form);
////		getWindow().add(form);
//		//cp.add(form);,
//		getWindow().add(cp);
		HorizontalPanel hpButton = createButtonPanel();
		vpForm.add(hpButton);
		cpRegisterForm.add(vpForm);
		getWindow().add(cpRegisterForm);
		getWindow().show();
	}
	
	public HorizontalPanel createButtonPanel()
	{
		HorizontalPanel hp = new HorizontalPanel();
		Button requestAccess = new Button("Request Access");
		requestAccess.setEnabled(false);
		requestAccess.setWidth(120);
		requestAccess.setBorders(true);
		requestAccess.addSelectionListener(AMISController.requestAccessPanelRegistrationForm(this));
		requestAccess.enable();
		hp.add(FormattingUtils.addHSpace(3));
		Button cancel = new Button("Cancel");
		cancel.setEnabled(false);
		cancel.setWidth(120);
		cancel.setBorders(true);
		cancel.addSelectionListener(AMISController.cancelPanelRegistrationForm(this));
		cancel.enable();
		hp.setSpacing(5);
		hp.add(requestAccess);
		hp.add(cancel);
		return hp;
	}
	
	public HorizontalPanel createLabelPanel(String labelOne, String labelTwo)
	{
		HorizontalPanel hp = new HorizontalPanel();
		if(labelOne.equals("Name"))
		{
			nameLabel.setText(labelOne);
			nameLabel.setStyleName("ccbs-Registration-Form-Label");
			nameLabel.setWidth("195px");
			hp.add(FormattingUtils.addHSpace(10));
			//hp.setSpacing(labelNameSurnameSpace);
			hp.add(nameLabel);
			surnameLabel.setText(labelTwo);
			surnameLabel.setStyleName("ccbs-Registration-Form-Label");
			surnameLabel.setWidth("195px");
			hp.add(surnameLabel);
		}
		else if(labelOne.equals("Country"))
		{
			hp.add(FormattingUtils.addHSpace(5));
			countryLabel.setText(labelOne);
			countryLabel.setStyleName("ccbs-Registration-Form-Label");
			countryLabel.setWidth("400px");
			hp.add(countryLabel);
		}
		else if(labelOne.equals("Institution"))
		{
			hp.add(FormattingUtils.addHSpace(5));
			institutionLabel.setText(labelOne);
			institutionLabel.setStyleName("ccbs-Registration-Form-Label");
			institutionLabel.setWidth("400px");
			hp.add(institutionLabel);
		}
		else if(labelOne.equals("Email Address: (the one to be used for AMIS communications)"))
		{
			hp.add(FormattingUtils.addHSpace(5));
			emailLabel.setText(labelOne);
			emailLabel.setStyleName("ccbs-Registration-Form-Label");
			emailLabel.setWidth("700px");
			hp.add(emailLabel);
		}
			
		return hp;
	}
	
	public HorizontalPanel createNameUsernameTextFieldPanel()
	{
		HorizontalPanel hp = new HorizontalPanel();
		nameTextField.setAllowBlank(false);  
		nameTextField.setWidth("195px");
		surnameTextField.setAllowBlank(false); 
		surnameTextField.setWidth("195px");
	//	hp.setSpacing(textFieldNameSurnameSpace);
		hp.add(nameTextField);
		hp.add(FormattingUtils.addHSpace(10));
		hp.add(surnameTextField);
		return hp;
	}
	
	public HorizontalPanel createBigTextFieldPanel(String panelName)
	{
		HorizontalPanel hp = new HorizontalPanel();
		if(panelName.equals("Country"))
		{
			countryTextField.setAllowBlank(false);  
			countryTextField.setWidth(400);
			hp.add(countryTextField);
		}
		else if(panelName.equals("Institution"))
		{
			institutionTextField.setAllowBlank(false);  
			institutionTextField.setWidth(400);
			hp.add(institutionTextField);
		}
		else if(panelName.equals("Email"))
		{
			emailTextField.setAllowBlank(false); 
			emailTextField.setWidth(400);
			hp.add(emailTextField);
		}
		return hp;
	}

	public TextField<String> getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(TextField<String> nameTextField) {
		this.nameTextField = nameTextField;
	}

	public TextField<String> getSurnameTextField() {
		return surnameTextField;
	}

	public void setSurnameTextField(TextField<String> surnameTextField) {
		this.surnameTextField = surnameTextField;
	}

	public TextField<String> getCountryTextField() {
		return countryTextField;
	}

	public void setCountryTextField(TextField<String> countryTextField) {
		this.countryTextField = countryTextField;
	}

	public TextField<String> getInstitutionTextField() {
		return institutionTextField;
	}

	public void setInstitutionTextField(TextField<String> institutionTextField) {
		this.institutionTextField = institutionTextField;
	}

	public TextField<String> getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(TextField<String> emailTextField) {
		this.emailTextField = emailTextField;
	}
	
//	# private void createForm1() {  
//		#     FormPanel simple = new FormPanel();  
//		#     simple.setHeading("Simple Form");  
//		#     simple.setFrame(true);  
//		#     simple.setWidth(350);  
//		#   
//		#     TextField<String> firstName = new TextField<String>();  
//		#     firstName.setFieldLabel("Name");  
//		#     firstName.setAllowBlank(false);  
//		#     firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId());  
//		#     simple.add(firstName, formData);  
//		#   
//		#     TextField<String> email = new TextField<String>();  
//		#     email.setFieldLabel("Email");  
//		#     email.setAllowBlank(false);  
//		#     simple.add(email, formData);  
//		#   
//		#     List<Stock> stocks = TestData.getStocks();  
//		#     Collections.sort(stocks, new Comparator<Stock>() {  
//		#       public int compare(Stock arg0, Stock arg1) {  
//		#         return arg0.getName().compareTo(arg1.getName());  
//		#       }  
//		#     });  
//		#   
//		#     ListStore<Stock> store = new ListStore<Stock>();  
//		#     store.add(stocks);  
//		#   
//		#     ComboBox<Stock> combo = new ComboBox<Stock>();  
//		#     combo.setFieldLabel("Company");  
//		#     combo.setDisplayField("name");  
//		#     combo.setTriggerAction(TriggerAction.ALL);  
//		#     combo.setStore(store);  
//		#     simple.add(combo, formData);  
//		#   
//		#     DateField date = new DateField();  
//		#     date.setFieldLabel("Birthday");  
//		#     simple.add(date, formData);  
//		#   
//		#     TimeField time = new TimeField();  
//		#     time.setFieldLabel("Time");  
//		#     simple.add(time, formData);  
//		#   
//		#     Slider slider = new Slider();  
//		#     slider.setMinValue(40);  
//		#     slider.setMaxValue(90);  
//		#     slider.setValue(60);  
//		#     slider.setIncrement(1);  
//		#     slider.setMessage("{0} inches tall");  
//		#   
//		#     final SliderField sf = new SliderField(slider);  
//		#     sf.setFieldLabel("Size");  
//		#     simple.add(sf, formData);  
//		#     CheckBox check1 = new CheckBox();  
//		#     check1.setBoxLabel("Classical");  
//		#   
//		#     CheckBox check2 = new CheckBox();  
//		#     check2.setBoxLabel("Rock");  
//		#     check2.setValue(true);  
//		#   
//		#     CheckBox check3 = new CheckBox();  
//		#     check3.setBoxLabel("Blue");  
//		#   
//		#     CheckBoxGroup checkGroup = new CheckBoxGroup();  
//		#     checkGroup.setFieldLabel("Music");  
//		#     checkGroup.add(check1);  
//		#     checkGroup.add(check2);  
//		#     checkGroup.add(check3);  
//		#     simple.add(checkGroup, formData);  
//		#   
//		#     Radio radio = new Radio();  
//		#     radio.setBoxLabel("Red");  
//		#     radio.setValue(true);  
//		#   
//		#     Radio radio2 = new Radio();  
//		#     radio2.setBoxLabel("Blue");  
//		#   
//		#     RadioGroup radioGroup = new RadioGroup();  
//		#     radioGroup.setFieldLabel("Favorite Color");  
//		#     radioGroup.add(radio);  
//		#     radioGroup.add(radio2);  
//		#     simple.add(radioGroup, formData);  
//		#   
//		#     Radio radio3 = new Radio();  
//		#     radio3.setBoxLabel("Apple");  
//		#     radio3.setValue(true);  
//		#   
//		#     Radio radio4 = new Radio();  
//		#     radio4.setBoxLabel("Banana");  
//		#   
//		#     RadioGroup radioGroup2 = new RadioGroup();  
//		#     radioGroup2.setFieldLabel("Favorite Fruit");  
//		#     radioGroup2.add(radio3);  
//		#     radioGroup2.add(radio4);  
//		#     simple.add(radioGroup2, formData);  
//		#   
//		#     TextArea description = new TextArea();  
//		#     description.setPreventScrollbars(true);  
//		#     description.setFieldLabel("Description");  
//		#     simple.add(description, formData);  
//		#   
//		#     Button b = new Button("Submit");  
//		#     simple.addButton(b);  
//		#     simple.addButton(new Button("Cancel"));  
//		#   
//		#     simple.setButtonAlign(HorizontalAlignment.CENTER);  
//		#   
//		#     FormButtonBinding binding = new FormButtonBinding(simple);  
//		#     binding.addButton(b);  
//		#   
//		#     SpinnerField spinnerField = new SpinnerField();  
//		#     spinnerField.setIncrement(.1d);  
//		#     spinnerField.getPropertyEditor().setType(Double.class);  
//		#     spinnerField.getPropertyEditor().setFormat(NumberFormat.getFormat("00.0"));  
//		#     spinnerField.setFieldLabel("Duration (s)");  
//		#     spinnerField.setMinValue(-10d);  
//		#     spinnerField.setMaxValue(10d);  
//		#     simple.add(spinnerField, formData);  
//		#   
//		#     vp.add(simple);  
//		#   }  
}
