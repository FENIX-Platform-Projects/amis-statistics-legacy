package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.ipc.client.control.IPCDataEntryController;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class IPCDataEntryControllerWindow extends FenixWindow{
	
	private ContentPanel mainPanel;
	
	private ContentPanel provincePanel;
		
	private ContentPanel usersPanel;
	
	private ContentPanel toolBarPanel;
	
	private Map<String, IPCDataEntryWindow>  ipcDataEntryWindows;
		
	private Button save = new Button("Save All");

	private Long workflowId;
	
	private WorkflowInfoVO workFlowInfoVO;
	
	private ToolBar toolbar;
	
	
	
	public IPCDataEntryControllerWindow() {
		ipcDataEntryWindows = new HashMap<String, IPCDataEntryWindow>();
		mainPanel = new ContentPanel();
		toolBarPanel = new ContentPanel();
		mainPanel.setLayout(new AccordionLayout());
		mainPanel.setHeight(470);
		provincePanel = new ContentPanel();	
		provincePanel.setExpanded(true);
		provincePanel.setHeading("Geographic Area of Analysis");
		provincePanel.setScrollMode(Scroll.AUTOY);
		provincePanel.setBorders(false);
		provincePanel.setBodyBorder(false);
		usersPanel = new ContentPanel();		
		usersPanel.setBorders(false);
		usersPanel.setBodyBorder(false);
		usersPanel.setScrollMode(Scroll.AUTOY);
		usersPanel.setHeight(450);
		workFlowInfoVO = new WorkflowInfoVO();
	}
	
	public void build(WorkflowInfoVO workflowInfo, Boolean newWorkflow, Boolean isEditable) {
		System.out.println("workflowInfo: " + workflowInfo.getWorkflowId() + "  " + workflowInfo.getModerator_id() );
		if( newWorkflow) 
			createNewFormWindows(workflowInfo);
		
		else
			createExistingProvincesWindows(workflowInfo);
		
		buildcenterPanel(workflowInfo);
		enhanceSave();
		format();
		provincePanel.setExpanded(true);
		show();
	}
	
	private void createNewFormWindows(WorkflowInfoVO workflowInfo) {
//		for(IPCCodesVO interestedArea : workflowInfo.getInterestedAreas()) {
//			IPCDataEntryWindow ipcDataEntryWindow = new IPCDataEntryWindow(workflowInfo, interestedArea.getLabel());
//			ipcDataEntryWindow.build("IPC.xml", true);
//			ipcDataEntryWindows.put(interestedArea.getCode(), ipcDataEntryWindow);			
//		}
		for(ProvinceVO province : workflowInfo.getProvinces()) {
			IPCDataEntryWindow ipcDataEntryWindow = new IPCDataEntryWindow(workflowInfo, province.getProvinceLabel());
			ipcDataEntryWindow.build("IPC.xml", true);
			ipcDataEntryWindows.put(province.getProvinceCode(), ipcDataEntryWindow);			
		}
		setWorkFlowInfoVO(workflowInfo);
	}
	
	private void createExistingProvincesWindows(WorkflowInfoVO workflowInfo) {
		for(ProvinceVO province : workflowInfo.getProvinces()) {
			IPCDataEntryWindow ipcDataEntryWindow = new IPCDataEntryWindow(workflowInfo, province.getProvinceLabel());
			ipcDataEntryWindow.build(province.getXml(), false);
			ipcDataEntryWindows.put(province.getProvinceCode(), ipcDataEntryWindow);
		}
		setWorkFlowInfoVO(workflowInfo);
	}
	
	private void buildcenterPanel(WorkflowInfoVO workflowInfo) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		toolBarPanel.add(buildToolBar());
		mainPanel.setHeaderVisible(false);
		toolBarPanel.setHeading(workflowInfo.getGeographicArea().getLabel() + " - " + workflowInfo.getReferenceLayer().getLabel());			
		mainPanel.add(buildWorkflowInfo(workflowInfo));
		mainPanel.add(buildActors(workflowInfo));
		toolBarPanel.add(mainPanel);
		getCenter().add(toolBarPanel);
		getCenter().setBottomComponent(save);
		addCenterPartToWindow();
	}
	
	public ToolBar buildToolBar(){
		toolbar = new ToolBar();
		Button showEditMode = new Button("Show/Edit WF");  
		showEditMode.setIconStyle("textEditBtn");  
		toolbar.add(showEditMode);
		showEditMode.addListener(Events.OnClick, IPCDataEntryController.openViewEdit(this));
		Button showMap = new Button("Show IPC Map");  
		showMap.setIconStyle("mapIcon");  
		toolbar.add(showMap);
		showMap.addListener(Events.OnClick, IPCDataEntryController.openIPCMap(this));
		return toolbar;
		
	}
	
	
	public ContentPanel buildWorkflowInfo(WorkflowInfoVO workflowInfo){
		createProvicesPanel(workflowInfo);
		return provincePanel;
	}
	
	private ContentPanel buildActors(WorkflowInfoVO workflowInfo){
		usersPanel.setHeading("Actors");
		usersPanel.setScrollMode(Scroll.AUTOY);
		IPCDataEntryController.getUsers(this);
		return usersPanel;
	}
	
	
	
	private void createProvicesPanel(WorkflowInfoVO workflowInfo){
//		for(IPCCodesVO interestedArea : workflowInfo.getInterestedAreas()) 
//			createProvicePanel(interestedArea);		
		for(ProvinceVO province : workflowInfo.getProvinces()) 
			createProvicePanel(province);		
	}
	
	private void createProvicePanel(ProvinceVO province) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSize("270px", "25px");
		p.setSpacing(10);
		p.setId(province.getProvinceCode());
		HTML label = new HTML("<b>"+ province.getProvinceLabel() + "<b>");
		label.setWidth("140px");
		Button show = new Button();	
		show.setIconStyle("arrow-up");
		show.setHeight(10);
		show.setWidth(10);
		show.setToolTip("Open data entry Window");
		Button hide = new Button();
		hide.setIconStyle("arrow-down");
		hide.setHeight(10);
		hide.setWidth(10);
		hide.setToolTip("Open data entry Window");
		p.add(label);
		p.add(show);
		p.add(hide);
		enhance(show, hide, province);
		provincePanel.add(p);
	}	
		
	
	private void enhance(Button show, Button hide, ProvinceVO province){
		show.addSelectionListener(IPCDataEntryController.show(ipcDataEntryWindows.get(province.getProvinceCode())));
		hide.addSelectionListener(IPCDataEntryController.hide(ipcDataEntryWindows.get(province.getProvinceCode())));		
	}
	private void enhanceSave() {
		save.addSelectionListener(IPCDataEntryController.saveWork(this));
	}
	
	
	public HorizontalPanel addSpace() {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight("40px");
		return p;
	}
	
	private void format() {
		setSize("250px", "590px");
		this.getWindow().setPosition(5, 150);	
		this.getWindow().setResizable(false);
		getWindow().setHeading("IPC - Integrated Phase Analysis Controller");
		setCollapsible(true);
		getWindow().setResizable(false);
	}

	public Map<String, IPCDataEntryWindow> getIpcDataEntryWindows() {
		return ipcDataEntryWindows;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public WorkflowInfoVO getWorkFlowInfoVO() {
		return workFlowInfoVO;
	}

	public void setWorkFlowInfoVO(WorkflowInfoVO workFlowInfoVO) {
		this.workFlowInfoVO = workFlowInfoVO;
	}

	public ContentPanel getUsersPanel() {
		return usersPanel;
	}
	
	
}