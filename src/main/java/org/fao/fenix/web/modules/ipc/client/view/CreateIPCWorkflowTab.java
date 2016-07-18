package org.fao.fenix.web.modules.ipc.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.ipc.client.control.CreateIPCWorkflowController;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateIPCWorkflowTab {

	
	public ComboBox<ListItem> countryComboBox = new ComboBox<ListItem>();
	
	public ComboBox<ListItem> layerComboBox = new ComboBox<ListItem>();
	
	private TextField<String> name = new TextField<String>();
	
	private TextField<String> code = new TextField<String>();
	
	private TextField<String> period = new TextField<String>();
	
	private TextArea description = new TextArea();
	
	private ListBox status = new ListBox();
	
//	private Date date;
	
	public Button openIPC;
	
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
	
	@SuppressWarnings("deprecation")
	public CreateIPCWorkflowTab() {
		panel = new VerticalPanel();
		contentPanel = new ContentPanel();
		openIPC = new Button("<b>" + BabelFish.print().startIPC() + "</b>");
		addActors = new Button("Add Actors");
//		date = new Date();
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
	

	
	public ContentPanel build(IPCWorkflowWindow window) {
		CreateIPCWorkflowController.getModerator(this);
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
	    label.setFieldLabel("<b>" +  BabelFish.print().createNewWorkflow() + "<b>");
	    contentPanel.add(label);
	    contentPanel.add(buildCountryComboBox());
	    contentPanel.add(buildLayerComboBox());
	   	
		//Fill Combo boxes and set Button
		CreateIPCWorkflowController.fillPageWidgets(this);


		contentPanel.add(createDimensionValesPanel());
		contentPanel.add(addSpace());
		contentPanel.add(buildIPCName());
		contentPanel.add(addSpace());
		contentPanel.add(buildDescription());
		contentPanel.add(addSpace());
		contentPanel.add(buildPeriod());
		contentPanel.add(addSpace());
		contentPanel.add(buildStatusListBox());
		contentPanel.add(addSpace());	
		contentPanel.add(buildSecurity());
		contentPanel.add(addSpace());	
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
		contentPanel.addButton(openIPC);
		enhanceButtons(window);
		return contentPanel;
	}
	

	
	public ComboBox<ListItem> buildCountryComboBox() {
		countryComboBox = new ComboBox<ListItem>();
		countryComboBox.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> store = new ListStore<ListItem>();
		countryComboBox.setStore(store);
		countryComboBox.setFieldLabel("<b>Country: <b>");
		countryComboBox.setEditable(true);
		countryComboBox.setTypeAhead(true);
		countryComboBox.setDisplayField("name");
		countryComboBox.setEmptyText(BabelFish.print().pleaseSelect());
		countryComboBox.setAllowBlank(false);
		countryComboBox.setId("country");
		countryComboBox.setMinListWidth(secondWidthInt);
		countryComboBox.addSelectionChangedListener(fillDescriptions(this));
		return countryComboBox;
	}
	
	public HorizontalPanel addSpace() {
		HorizontalPanel p = new HorizontalPanel();
//		p.setSpacing(10);
		p.add(new HTML("<br>"));
		return p;
	}
	
	
	@SuppressWarnings("unchecked")
	public ComboBox<ListItem> buildLayerComboBox() {
		layerComboBox = new ComboBox<ListItem>();
		layerComboBox.setTriggerAction(TriggerAction.ALL);
		ListStore<ListItem> store = new ListStore<ListItem>();
		layerComboBox.setStore(store);
		layerComboBox.setFieldLabel("<b>Administrative Level of Analysis: <b>");
		layerComboBox.setEditable(false);
		layerComboBox.setTypeAhead(true);
		layerComboBox.setDisplayField("name");
		layerComboBox.setEmptyText(BabelFish.print().pleaseSelect());
		layerComboBox.setAllowBlank(false);
		layerComboBox.setId("layer");
		layerComboBox.addSelectionChangedListener(fillDescriptions(this));
		return layerComboBox;
	}
	
	
	

	
	public HorizontalPanel buildIPCName() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Title: <b>");
		label.setWidth(firstWidth);
		name.setWidth(secondWidth);
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
	
	public HorizontalPanel buildPeriod() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Time Period of Analysis: <b>");
		label.setWidth(firstWidth);
		period.setWidth(secondWidth);
		p.add(label);
		p.add(period);
		return p;
	}
	
	public HorizontalPanel buildDescription() {
		HorizontalPanel p = new HorizontalPanel();
		HTML label = new HTML("<b>Description: <b>");
		label.setWidth(firstWidth);
		description.setSize(secondWidthInt, 100);
		p.add(label);
		p.add(description);
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

	
	@SuppressWarnings("unchecked")
	private SelectionChangedListener fillDescriptions(final CreateIPCWorkflowTab tab) {
		SelectionChangedListener t = new SelectionChangedListener() {  
			public void selectionChanged(SelectionChangedEvent se) {
				if ( countryComboBox.getValue() != null && layerComboBox.getValue() != null) {
					ListItem selectedcItem = countryComboBox.getValue();
					final String countryLabel = selectedcItem.getName();
					final String countryGaulCode = selectedcItem.getValue();
					ListItem selectedlItem = layerComboBox.getValue();
					final String layerLabel = selectedlItem.getName();
					final String layerCode= selectedlItem.getValue();
					System.out.println(" countryLabel = " + countryLabel
							+ " countryGaulCode " + countryGaulCode);
					
					System.out.println(" layerLabel = " + layerLabel
							+ " layerCode " + layerCode);
					// fill descriptions
					editIPCDescription();
					CreateIPCWorkflowController.fillAreasValues(tab);
				}
			}
		};  
		return t;
	}
	

	
	private void editIPCDescription() {
		ListItem selectedLayerItem = layerComboBox.getValue();
		ListItem selectedCountryItem = countryComboBox.getValue();
		final String layerLabel = selectedLayerItem.getName();
		final String countryLabel = selectedCountryItem.getName();
		System.out.println(" countryLabel = " + countryLabel
				+ " layerLabel " + layerLabel);
		name.setValue("IPC study on " + countryLabel + " " + layerLabel);
		description.setValue("IPC study on " + countryLabel + "\n" + layerLabel+ "\nstarted on " + new Date());
	}
	
	
	private VerticalPanel createDimensionValesPanel(){
		HorizontalPanel panel = new HorizontalPanel();
		VerticalPanel v = new VerticalPanel();
		HTML label = new HTML("<b>Geographic  Area of Analysis: <b>");
		label.setWidth(firstWidth);
		panel.add(areasValues);
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
	
	private void enhanceButtons(IPCWorkflowWindow window){
		addAll.addSelectionListener(CreateIPCWorkflowController.addAll(areasValues, selectedAreasValues));
		addSelected.addSelectionListener(CreateIPCWorkflowController.addSelectedValues(areasValues, selectedAreasValues));
		removeSelected.addSelectionListener(CreateIPCWorkflowController.removeSelected(selectedAreasValues));
		removeAll.addSelectionListener(CreateIPCWorkflowController.removeAll(selectedAreasValues));
		openIPC.addSelectionListener(CreateIPCWorkflowController.openIPCController(window, this));
		addActors.addSelectionListener(CreateIPCWorkflowController.openAddActors(this));
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
	
}
