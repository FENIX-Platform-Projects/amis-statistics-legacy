package org.fao.fenix.web.modules.ipc.client.view;

import java.util.List;

import org.fao.fenix.web.modules.ipc.client.control.CreateIPCWorkflowController;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IPCaddActorsWindow extends FenixWindow {
	
	public ListBox contValues; 
	public ListBox selectedContValues;
	public Button addAllCont;
	public Button removeAllCont;
	public Button addSelectedCont;
	public Button removeSelectedCont;
	
	public ListBox obsValues; 
	public ListBox selectedObsValues;
	public Button addAllObs;
	public Button removeAllObs;
	public Button addSelectedObs;
	public Button removeSelectedObs;
	
	private String firstWidth = "150px";
	
	private ContentPanel panel;
	
	private Button addActors;
	
	private List<UserVO> contributors;
	
	private List<UserVO> observers;
	
	public IPCaddActorsWindow() {
		panel = new ContentPanel();
		addActors = new Button("Add Actors");
		panel.setHeaderVisible(false);
		contValues = new ListBox();
		selectedContValues = new ListBox();
		addAllCont = new Button(">>");
		removeAllCont = new Button("<<");;
		addSelectedCont = new Button(">");;
		removeSelectedCont = new Button("<");
		contValues.setSize("200px", "150px");
		selectedContValues.setSize("200px", "150px");
		contValues.setMultipleSelect(true);
		selectedContValues.setMultipleSelect(true);
		addAllCont.setWidth("50px");
		addSelectedCont.setWidth("50px");
		removeSelectedCont.setWidth("50px");
		removeAllCont.setWidth("50px");	
		
		obsValues = new ListBox();
		selectedObsValues = new ListBox();
		addAllObs = new Button(">>");
		removeAllObs = new Button("<<");;
		addSelectedObs = new Button(">");;
		removeSelectedObs = new Button("<");
		obsValues.setSize("200px", "150px");
		selectedObsValues.setSize("200px", "150px");
		obsValues.setMultipleSelect(true);
		selectedObsValues.setMultipleSelect(true);
		addAllObs.setWidth("50px");
		addSelectedObs.setWidth("50px");
		removeSelectedObs.setWidth("50px");
		removeAllObs.setWidth("50px");
		
		
	}
	
	public void build(CreateIPCWorkflowTab createTab, Long moderator, List<UserVO> contributors_id, List<UserVO> observers_id) {
		CreateIPCWorkflowController.getUsersInIPCGroup(moderator, contValues, obsValues);
		contributors = contributors_id;
		observers = observers_id;
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildCenterPanel());
		getCenterData().setSize(600);
		addCenterPartToWindow();
		enhanceButtons();
		enhanceActors(createTab);
		format();
		show();	
	}
	
	public void build(ShowEditIPCWorkflow window, Long moderator, List<UserVO> contributors_id, List<UserVO> observers_id) {
		CreateIPCWorkflowController.getUsersInIPCGroup(moderator, contValues, obsValues);
		contributors = contributors_id;
		observers = observers_id;
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildCenterPanel());
		getCenterData().setSize(600);
		addCenterPartToWindow();
		enhanceButtons();
		enhanceActors(window);
		format();
		show();	
	}
	
	private ContentPanel buildCenterPanel() {
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(10);
		v.add(buildContributorPanel());
		v.add(buildObserversPanel());
		v.add(buildbuttonPanel());
		panel.add(v);
		return panel;
	}
	
	private VerticalPanel buildContributorPanel(){
		VerticalPanel v = new VerticalPanel();
		HTML label = new HTML("<b>Contributors: <br></b>");
		label.setWidth(firstWidth);
		v.add(label);
		v.add(createContValuesPanel());
		return v;
	}
	
	private VerticalPanel buildObserversPanel(){
		VerticalPanel v = new VerticalPanel();
		HTML label = new HTML("<b><br>Observers: <br> </b>");
		label.setWidth(firstWidth);
		v.add(label);
		v.add(createObsValuesPanel());
		return v;
	}
	
	private VerticalPanel buildInfoPanel(){
		VerticalPanel v = new VerticalPanel();
		return v;
	}
	
	private VerticalPanel createContValuesPanel(){
		HorizontalPanel p = new HorizontalPanel();
		VerticalPanel v = new VerticalPanel();
		p.add(contValues);
		p.add(createContValuesButtonPanel());
		p.add(selectedContValues);
		for(UserVO user : contributors)
			selectedContValues.addItem(user.getFirstAndLastName(), String.valueOf(user.getId()));
		v.add(p);
		return v;
	}
	
	private VerticalPanel createObsValuesPanel(){
		HorizontalPanel p = new HorizontalPanel();
		VerticalPanel v = new VerticalPanel();
		p.add(obsValues);
		p.add(createObsValuesButtonPanel());
		p.add(selectedObsValues);
		for(UserVO user : observers)
			selectedObsValues.addItem(user.getFirstAndLastName(), String.valueOf(user.getId()));
		v.add(p);
		return v;
	}
	
	private VerticalPanel createContValuesButtonPanel(){
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.add(addAllCont);
		panel.add(addSelectedCont);
		panel.add(removeSelectedCont);
		panel.add(removeAllCont);		
		return panel;
	}
	
	private VerticalPanel createObsValuesButtonPanel(){
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.add(addAllObs);
		panel.add(addSelectedObs);
		panel.add(removeSelectedObs);
		panel.add(removeAllObs);
		return panel;
	}
	
	private VerticalPanel buildbuttonPanel(){
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		p.add(addActors);
		return p;
	}
	
	private void enhanceButtons(){
		addAllCont.addSelectionListener(CreateIPCWorkflowController.addAll(contValues, selectedContValues));
		addSelectedCont.addSelectionListener(CreateIPCWorkflowController.addSelectedValues(contValues, selectedContValues));
		removeSelectedCont.addSelectionListener(CreateIPCWorkflowController.removeSelected(selectedContValues));
		removeAllCont.addSelectionListener(CreateIPCWorkflowController.removeAll(selectedContValues));
		
		addAllObs.addSelectionListener(CreateIPCWorkflowController.addAll(obsValues, selectedObsValues));
		addSelectedObs.addSelectionListener(CreateIPCWorkflowController.addSelectedValues(obsValues, selectedObsValues));
		removeSelectedObs.addSelectionListener(CreateIPCWorkflowController.removeSelected(selectedObsValues));
		removeAllObs.addSelectionListener(CreateIPCWorkflowController.removeAll(selectedObsValues));	
		
	}
	
	private void enhanceActors(CreateIPCWorkflowTab createTab){
		addActors.addSelectionListener(CreateIPCWorkflowController.getContributorsAndObservers(createTab, this));
	}
	
	private void enhanceActors(ShowEditIPCWorkflow window){
		addActors.addSelectionListener(CreateIPCWorkflowController.getContributorsAndObservers(window, this));
	}
	
	
	
	
	
	private void format() {
		setSize("500px", "450px");
		getWindow().setHeading("Actors Controller");
		setCollapsible(true);
		getWindow().setResizable(true);
	}

	public ListBox getSelectedContValues() {
		return selectedContValues;
	}

	public ListBox getSelectedObsValues() {
		return selectedObsValues;
	}
	
	
	
	
}