package org.fao.fenix.web.modules.core.client.banner;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class BannerChangeWindow extends FenixWindow{
	
	protected VerticalPanel panel;

	private FileUploadField leftBanner;
	private FileUploadField rightBanner;
	protected FormPanel formPanel;
	private Button cancel;
	private Button uploadButton;


	private LayoutContainer mainContainer;  
	private LayoutContainer leftColumnContainer;  
	private FormLayout formLayout;
	private FormData formData;
	private FieldSet remarksAreaFieldSet;
	
	
	public void build() {
		//set the window properties              
		setWindowProperties(BabelFish.print().changeBanner());

		//create form
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String servlet = ep.getChangeBannerServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());

		setFormPanel(buildBasicFormPanel(servlet));

		//set form listeners
//		getFormPanel().addListener(Events.BeforeSubmit, DatasetUploaderController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, getAfterSubmitFormListener());

		getCenter().add(getFormPanel());

		show();
	}

	public void setWindowProperties(String title) {
		setCenterProperties();
		addCenterPartToWindow();
		formatWindow(title);
	}

	protected FormPanel buildBasicFormPanel(String servlet) {
		formPanel = new FormPanel();
		formPanel.setStyleAttribute("padding", "10px 10px 10px 10px");
		formPanel.setAction(servlet);
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
		
		
		addBasicFileUploadFields();
		addButtons();
		addUploadButtonListener();
		
		
		
		formPanel.add(mainContainer);   

		
		return formPanel;
	}

	private void formatWindow(String header) {
		getCenter().setHeaderVisible(false);
		getWindow().setHeading(header);
		getWindow().setSize("450px", "250px");
	}

	protected void addBasicFileUploadFields() {
		addFileField();
		addMetadataField();
	}
	


	public void addFileField() {
		leftBanner = new FileUploadField(); 
		leftBanner.setButtonOffset(10);
		leftBanner.setAllowBlank(false);  
		leftBanner.setFieldLabel(BabelFish.print().leftImage());
		leftBanner.setLabelStyle("font-weight:bold;");
		leftBanner.setName("fileName");

		leftColumnContainer.add(leftBanner, formData);   
	}

	private void addMetadataField() {
		rightBanner = new FileUploadField();  
		rightBanner.setButtonOffset(10);
		rightBanner.setAllowBlank(true);  
		rightBanner.setFieldLabel(BabelFish.print().rightImage()); 
		rightBanner.setLabelStyle("font-weight:bold;");
		rightBanner.setName("metadataFileName");
		rightBanner.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
		
		leftColumnContainer.add(rightBanner, formData);   
	}
	

	public void addToFeedbackArea(Widget widget) {
		remarksAreaFieldSet.add(widget);
		remarksAreaFieldSet.getLayout().layout();

	}



	protected void addButtons() {
		cancel = new Button();
		uploadButton = new Button();
		//clear = new Button();
		cancel.setText(BabelFish.print().cancel());
//		cancel.addSelectionListener(UploaderController.getCancelListener(this));
		uploadButton.setText(BabelFish.print().upload());
		//clear.setText(I18N.print().clear());

		formPanel.setButtonAlign(HorizontalAlignment.CENTER);
		formPanel.addButton(uploadButton);
		formPanel.addButton(cancel);
	}

	private void addUploadButtonListener() {
		cancel.addSelectionListener(getCancelListener(this));
		getUploadButton().addSelectionListener(getUploadListener(this));
	}
	
	
	private static SelectionListener<ButtonEvent> getUploadListener(final BannerChangeWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
		
				// retrieve form parameters
				System.out.println("form");
	
				FormPanel form = window.getFormPanel();
			
				form.submit();
						

			}
		};
	}
	
	public static Listener<FormEvent> getAfterSubmitFormListener() {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// after submit event
			MessageBox message = new MessageBox();
			FenixAlert window = new FenixAlert();
			String results = be.getResultHtml();
			if (results == null || results.equals("") || results.equals("<pre></pre>")) {
				window.info("Upload image", BabelFish.print().uploadSuccessful());
						
			} else {
				window.alert("Upload image", BabelFish.print().updateUnsuccessful());
			}
			
		}
	 };
	}
	
	public static SelectionListener<ButtonEvent> getCancelListener(final BannerChangeWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			}
		};
	}
	
	
	public FileUploadField getLeftBanner() {
		return leftBanner;
	}
	public FileUploadField getRightBanner() {
		return rightBanner;
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
