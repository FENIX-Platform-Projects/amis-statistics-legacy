package org.fao.fenix.web.modules.ipc.client.view.old;

import org.fao.fenix.web.modules.core.client.utils.WindowFactory;
import org.fao.fenix.web.modules.ipc.client.control.CreateIPCReportController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateIPCReport {

	public Window window;
    public VerticalPanel panel;
    public static HorizontalPanel hPanel;

	public ComboBox<ListItem> countryComboBox = new ComboBox<ListItem>();
	public ComboBox<ListItem> workflowComboBox = new ComboBox<ListItem>();
	public Button createReport = new Button(BabelFish.print().createReport());
	public Button createWorkflow = new Button(BabelFish.print().createWorkflow());
	public LabelField label = new LabelField();
	
	public FormPanel formPanel;
	public Html message = new Html();
	
	
	public CreateIPCReport() {
		window = WindowFactory.createWindow(BabelFish.print().createIPCReport(), 550, 400);
		panel = new VerticalPanel();
	}

	public void build() {
		window.add(panel);
		
		formPanel = new FormPanel();
		formPanel.setStyleAttribute("padding", "10px 10px 10px 10px");
		
		FormLayout layout = new FormLayout();   
	    layout.setLabelWidth(180);  
	    layout.setLabelAlign(LabelAlign.LEFT);  
	    layout.setLabelSeparator("");
	    layout.setLabelPad(10);
	    
	    message.setStyleAttribute("fontSize", "11px");
	    
	    formPanel.setLayout(layout); 
	 	formPanel.setWidth(500);
		formPanel.setFrame(false);
		formPanel.setFieldWidth(200);
		formPanel.setHeaderVisible(false);
		formPanel.setBodyBorder(false);
		//formPanel.setButtonAlign(HorizontalAlignment.CENTER);
		    
		//Instructions label
	    label.setFieldLabel(BabelFish.print().createReport()+":");
	   
		//Format Combo boxes
		//Country List
		countryComboBox = new ComboBox<ListItem>();
		countryComboBox.setFieldLabel(BabelFish.print().selectGeographicArea());
		countryComboBox.setEditable(true);
		countryComboBox.setTypeAhead(true);
		countryComboBox.setDisplayField("name");
		countryComboBox.setEmptyText(BabelFish.print().pleaseSelect());

		countryComboBox.setAllowBlank(false);
		countryComboBox.setId("country");
		countryComboBox.addSelectionChangedListener(CreateIPCReportController.getWorkflows(countryComboBox, workflowComboBox, this));  
	 	
		//Workflow List
		workflowComboBox = new ComboBox<ListItem>();
		workflowComboBox.setFieldLabel(BabelFish.print().selectWorkflow());
		workflowComboBox.setEditable(true);
		workflowComboBox.setTypeAhead(true);
		workflowComboBox.setDisplayField("name");
		workflowComboBox.setEmptyText(BabelFish.print().pleaseSelect());

		workflowComboBox.setAllowBlank(false);
		workflowComboBox.setId("workflow");
				
		createReport.addSelectionListener(CreateIPCReportController.createIPCReport(this));
		createWorkflow.addSelectionListener(CreateIPCReportController.launchCreateIPCWorkflow(this));
		
		CreateIPCReportController.fillUserCountryList(countryComboBox, this);
		
		window.show();
	}
	
}
