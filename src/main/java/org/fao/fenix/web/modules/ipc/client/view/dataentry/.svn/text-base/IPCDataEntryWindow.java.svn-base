package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import org.fao.fenix.web.modules.ipc.client.control.IPCDataEntryController;
import org.fao.fenix.web.modules.ipc.client.control.IPCXMLController;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class IPCDataEntryWindow extends FenixWindow {

	
	
	private TabPanel tabPanel;
	
	private TabItem moduleOneTabItem;
	
	private TabItem moduleTwoTabItem;
	
	private TabItem moduleThreeTabItem;
	
	private TabItem moduleFourTabItem;
	
	private ModuleOne moduleOne;
	
	private ModuleTwo moduleTwo;
	
	private ModuleThree moduleThree;
	
	private ModuleFour moduleFour;
	
	public Boolean windowIsOpen = false;
	
	private String provinceLabel;
	
	private WorkflowInfoVO workflowInfoVO;
	
	private ToolBar toolbar;
	
	public IPCDataEntryWindow(WorkflowInfoVO wf, String province) {
		tabPanel = new TabPanel();
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true); 
		workflowInfoVO = wf;
		getWindow().setHeading("IPC - " + wf.getGeographicArea().getLabel() + ", " + province + " - " + wf.getWorkflowName() + " - started on " + wf.getDate().toLocaleString() );
		moduleOneTabItem = new TabItem("Current / Imminent Phase Part 1");
		moduleTwoTabItem = new TabItem("Current / Imminent Phase Part 2");
		moduleThreeTabItem = new TabItem("Analysis of Immediate Hazards");
		moduleFourTabItem = new TabItem("Analysis of Underlying  Structures");
		
		moduleOne = new ModuleOne(province);
		moduleTwo = new ModuleTwo();
		moduleThree = new ModuleThree();
		moduleFour = new ModuleFour();
		provinceLabel = province;
	}
	
	public void build(String source, boolean isFromFile) {
		
		moduleOneTabItem.add(moduleOne.build());
		tabPanel.add(moduleOneTabItem);
	
		moduleTwoTabItem.add(moduleTwo.build());
		tabPanel.add(moduleTwoTabItem);
		
		moduleThreeTabItem.add(moduleThree.build());
		tabPanel.add(moduleThreeTabItem);
		
		moduleFourTabItem.add(moduleFour.build());
		tabPanel.add(moduleFourTabItem);
		
		buildCenterPanel();
		format();
		
		IPCXMLController.buildModules(moduleOne, moduleTwo , moduleThree, moduleFour, source, isFromFile);
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(tabPanel);
//		getCenter().setTopComponent(buildToolBar());
		
		addCenterPartToWindow();
	}
	
	public ToolBar buildToolBar(){
		toolbar = new ToolBar();
		Button save = new Button("Save");  
		save.setIconStyle("save");  
		toolbar.add(save);
		return toolbar;
		
	}
	
	private void format() {
		setSize("995px", "590px");
		this.getWindow().setResizable(false);
		this.getWindow().setPosition(260, 150);
		this.getWindow().addWindowListener(IPCDataEntryController.onCloseManager(this));
		
//		getWindow().setHeading("IPC - Integrated Phase Analisys");
		setCollapsible(true);
		getWindow().setResizable(true);
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public TabItem getModuleOneTabItem() {
		return moduleOneTabItem;
	}

	public TabItem getModuleTwoTabItem() {
		return moduleTwoTabItem;
	}

	public TabItem getModuleThreeTabItem() {
		return moduleThreeTabItem;
	}

	public TabItem getModuleFourTabItem() {
		return moduleFourTabItem;
	}

	public ModuleOne getModuleOne() {
		return moduleOne;
	}

	public ModuleTwo getModuleTwo() {
		return moduleTwo;
	}

	public ModuleThree getModuleThree() {
		return moduleThree;
	}

	public ModuleFour getModuleFour() {
		return moduleFour;
	}

	public String getProvinceLabel() {
		return provinceLabel;
	}

	public void setProvinceLabel(String provinceLabel) {
		this.provinceLabel = provinceLabel;
	}

	public WorkflowInfoVO getWorkflowInfoVO() {
		return workflowInfoVO;
	}

	
	
}