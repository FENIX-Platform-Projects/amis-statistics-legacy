package org.fao.fenix.web.modules.ipc.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.ipc.client.control.CreateIPCWorkflowController;
import org.fao.fenix.web.modules.ipc.client.control.IPCDataEntryController;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.IPCDataEntryControllerWindow;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ShowEditIPCWorkflow extends FenixWindow {

	
	private HTML country = new HTML();
	
	private HTML layer = new HTML();	
	
	private TextField<String> name = new TextField<String>();
	
	private TextField<String> code = new TextField<String>();
	
	private TextField<String> period = new TextField<String>();
	
	private TextArea description = new TextArea();
	
	private ListBox status = new ListBox();
	
//	private Date date;
	
	public Button save;
	
	public Button addActors; 
	
	public VerticalPanel panel;
	
	public ContentPanel contentPanel;
	
	public ListBox areasValues; 
	public ListBox selectedAreasValues;
	public Button addAll;
	public Button removeAll;
	public Button addSelected;
	public Button removeSelected;
	
	private String firstWidth = "200px";
	
	private int firstWidthInt = 200;
	
	private String secondWidth = "250px";
	
	private int secondWidthInt = 250;
	
	private Boolean addedActors = false;
	
	private List<UserVO> contributors;
	
	private List<UserVO> observers;
	
	private UserVO moderator;
	
	private WorkflowInfoVO wf;
	
	@SuppressWarnings("deprecation")
	public ShowEditIPCWorkflow() {
		panel = new VerticalPanel();
		contentPanel = new ContentPanel();
		save = new Button("<b>Save Changes </b>");
		addActors = new Button("Add Actors");
		areasValues = new ListBox();
		selectedAreasValues = new ListBox();
		addAll = new Button(">>");
		removeAll = new Button("<<");;
		addSelected = new Button(">");;
		removeSelected = new Button("<");
		areasValues.setSize("200px", "150px");
		selectedAreasValues.setSize("200px", "150px");
		areasValues.setMultipleSelect(true);
		selectedAreasValues.setMultipleSelect(true);
		addAll.setWidth("50px");
		addSelected.setWidth("50px");
		removeSelected.setWidth("50px");
		removeAll.setWidth("50px");	
		contributors = new ArrayList<UserVO>();
		observers = new ArrayList<UserVO>();	
		moderator = new UserVO();	
	}
	

	
	public void build(IPCDataEntryControllerWindow window, Boolean isModerator) {
		
		wf = window.getWorkFlowInfoVO();
		contributors = wf.getContributors();
		observers = wf.getObservers();

		contentPanel.setStyleAttribute("padding", "10px 10px 10px 10px");
		
		FormLayout layout = new FormLayout();   
	    layout.setLabelWidth(firstWidthInt);  
	    layout.setLabelAlign(LabelAlign.LEFT);  
	    layout.setLabelSeparator("");
	    layout.setLabelPad(10);
	   
	    
	    contentPanel.setLayout(layout); 
		contentPanel.setFrame(false);
		contentPanel.setHeaderVisible(false);
		contentPanel.setBodyBorder(false);
				    
		//Instructions label
	    LabelField label = new LabelField();
	    label.setFieldLabel("<b>Workflow Information: <b>");
	    contentPanel.add(label);

	    contentPanel.add(buildGeographicAreaPanel(wf.getGeographicArea().getLabel()));
		contentPanel.add(addSpace());
		contentPanel.add(buildLayerPanel(wf.getReferenceLayer().getLabel()));
		contentPanel.add(addSpace());
		if ( !(isModerator)){
			contentPanel.add(createDimensionValesPanel(wf.getProvinces()));
		}
		else {
			contentPanel.add(createFullDimensionValesPanel(wf.getProvinces()));
		}	
		contentPanel.add(addSpace());
		contentPanel.add(buildIPCName(wf.getWorkflowName()));
		contentPanel.add(addSpace());
		contentPanel.add(buildDescription(wf.getDescription()));
		contentPanel.add(addSpace());
		contentPanel.add(buildPeriod(wf.getPeriod()));
		contentPanel.add(addSpace());
		contentPanel.add(buildStatusListBox());
		contentPanel.add(addSpace());	
		contentPanel.add(buildSecurity());
		contentPanel.add(addSpace());	
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
		contentPanel.addButton(save);
		
		enhanceButtons(window);
		
		
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(contentPanel);
		if ( !(isModerator)){
			disableEditFields();
		}
		addCenterPartToWindow();	
		format();
		show();
	}
	
	private void disableEditFields(){
		name.setReadOnly(true);
		description.setReadOnly(true);
		status.setEnabled(false);
		addActors.setEnabled(false);
		save.setEnabled(false);
		
	}
	

	
	public HorizontalPanel addSpace() {
		HorizontalPanel p = new HorizontalPanel();
		p.add(new HTML("<br>"));
		return p;
	}
	
	

	
	
	

	
	public HorizontalPanel buildIPCName(String title) {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Title: <b>");
		label.setWidth(firstWidth);
		name.setWidth(secondWidth);
		name.setValue(title);
		p.add(label);
		p.add(name);
		return p;
	}
	
	public HorizontalPanel buildCode() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Code: <b>");
		label.setWidth(firstWidth);
		code.setWidth(secondWidth);
		p.add(label);
		p.add(code);
		return p;
	}
	
	public HorizontalPanel buildPeriod(String v) {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Time Period of Analysis: <b>");
		label.setWidth(firstWidth);
		period.setWidth(secondWidth);
		period.setValue(v);
		p.add(label);
		p.add(period);
		return p;
	}
	
	public HorizontalPanel buildDescription(String d) {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Description: <b>");
		label.setWidth(firstWidth);
		description.setSize(secondWidthInt, 100);
		p.add(label);
		p.add(description);
		description.setValue(d);
		return p;
	}
	
	public HorizontalPanel buildStatusListBox() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Status: <b>");
		label.setWidth(firstWidth);
		status.addItem("Opening", "0");
		status.addItem("Open", "1");
		status.addItem("Closing", "2");
		status.addItem("Closed", "3");	
		status.setWidth(secondWidth);
		p.add(label);
		p.add(status);
		return p;
	}
	
	public HorizontalPanel buildSecurity() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Actors: <b>");
		label.setWidth(firstWidth);
		p.add(label);
		p.add(addActors);
		return p;
	}
	
	private HorizontalPanel buildGeographicAreaPanel(String c){
		HorizontalPanel panel = new HorizontalPanel();
		HTML label = new HTML("<b>Country: <b>");
		label.setWidth(firstWidth);
		country.setHTML(c);
		panel.add(label);
		panel.add(country);
		return panel;
	}
	
	private HorizontalPanel buildLayerPanel(String l){
		HorizontalPanel panel = new HorizontalPanel();
		HTML label = new HTML("<b>Administrative Level of Analysis: <b>");
		label.setWidth(firstWidth);
		layer.setHTML(l);
		panel.add(label);
		panel.add(layer);
		return panel;
	}
	
	
//	private VerticalPanel createDimensionValesPane(List<IPCCodesVO> provinces){
//		HorizontalPanel panel = new HorizontalPanel();
//		VerticalPanel v = new VerticalPanel();
//		HTML label = new HTML("<b>Geographic  Area of Analysis: <b>");
//		label.setWidth(firstWidth);
//		panel.add(areasValues);
//		panel.add(createDimensionValuesButtonPanel());
//		panel.add(selectedAreasValues);
//		for(IPCCodesVO province : provinces)
//			selectedAreasValues.addItem(province.getLabel(), province.getCode());
//		v.add(label);
//		v.add(panel);
//		return v;
//	}
	private VerticalPanel createDimensionValesPanel(List<ProvinceVO> provinces){
		HorizontalPanel panel = new HorizontalPanel();
		VerticalPanel v = new VerticalPanel();
		HTML label = new HTML("<b>Geographic  Area of Analysis: <b>");
		label.setWidth(firstWidth);
		panel.add(areasValues);
		for(ProvinceVO province : provinces)
			areasValues.addItem(province.getProvinceLabel(), province.getProvinceCode());
		v.add(label);
		v.add(panel);
		return v;
	}

	
	private VerticalPanel createFullDimensionValesPanel(List<ProvinceVO> provinces){
		HorizontalPanel panel = new HorizontalPanel();
		VerticalPanel v = new VerticalPanel();
		HTML label = new HTML("<b>Geographic  Area of Analysis: <b>");
		label.setWidth(firstWidth);
		panel.add(areasValues);
		CreateIPCWorkflowController.fillAreasValues(this);
		panel.add(createDimensionValuesButtonPanel());
		panel.add(selectedAreasValues);
		v.add(label);
		v.add(panel);
		return v;
	}
	
	private VerticalPanel createDimensionValuesButtonPanel(){
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.add(addAll);
		panel.add(addSelected);
		panel.add(removeSelected);
		panel.add(removeAll);		
		return panel;
	}
	
	private void enhanceButtons(IPCDataEntryControllerWindow window){
		addAll.addSelectionListener(CreateIPCWorkflowController.addAll(areasValues, selectedAreasValues));
		addSelected.addSelectionListener(CreateIPCWorkflowController.addSelectedValues(areasValues, selectedAreasValues));
		removeSelected.addSelectionListener(CreateIPCWorkflowController.removeSelected(selectedAreasValues));
		removeAll.addSelectionListener(CreateIPCWorkflowController.removeAll(selectedAreasValues));
		addActors.addSelectionListener(CreateIPCWorkflowController.openAddActors(this));
		save.addSelectionListener(IPCDataEntryController.saveChangesWorkflowInfo(window, this));
	}
	
	
	
	private void format() {
		setSize("540px", "630px");
		getWindow().setHeading("IPC - Show/Edit Workflow");
		setCollapsible(true);
		getWindow().setResizable(false);
	}
	
	
	public ListBox getStatus() {
		return status;
	}
	public TextField<String> getName() {
		return name;
	}
	public TextArea getDescription() {
		return description;
	}
//	public Date getDate() {
//		return date;
//	}
	public TextField<String> getPeriod() {
		return period;
	}



	public List<UserVO> getContributors() {
		return contributors;
	}



	public void setContributors(List<UserVO> contributors) {
		this.contributors = contributors;
	}



	public List<UserVO> getObservers() {
		return observers;
	}



	public void setObservers(List<UserVO> observers) {
		this.observers = observers;
	}



	public UserVO getModerator() {
		return moderator;
	}



	public void setModerator(UserVO moderator) {
		this.moderator = moderator;
	}



	public WorkflowInfoVO getWorkflowInfoVO() {
		return wf;
	}



	public void setWf(WorkflowInfoVO wf) {
		this.wf = wf;
	}




	
	
	
}
