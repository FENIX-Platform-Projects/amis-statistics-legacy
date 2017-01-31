package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class IPCDataEntryFormWindow extends FenixWindow {
	
	private ContentPanel contentPanel;

	private TabPanel tabPanel;
	
	private TabItem moduleOneTabItem;
	
	private TabItem moduleTwoTabItem;
	
	private TabItem moduleThreeTabItem;
	
	private TabItem moduleFourTabItem;
	
	private ModuleOne moduleOne;
	
	private ModuleTwo moduleTwo;
	
	private ModuleThree moduleThree;
	
	private ModuleFour moduleFour;
	
	private Boolean alreadyOpened = false;
	
	public IPCDataEntryFormWindow(String provinceName) {
		contentPanel = new ContentPanel();
		contentPanel.setTitle(provinceName);
		tabPanel = new TabPanel();
		moduleOneTabItem = new TabItem("Part 1A");
		moduleTwoTabItem = new TabItem("Part 1B");
		moduleThreeTabItem = new TabItem("Part 2");
		moduleFourTabItem = new TabItem("Part 3");
		moduleOne = new ModuleOne("");
		moduleTwo = new ModuleTwo();
		moduleThree = new ModuleThree();
		moduleFour = new ModuleFour();
	}
	
	public ContentPanel build(String filename) {
		
		moduleOneTabItem.add(moduleOne.build());
		tabPanel.add(moduleOneTabItem); 
		
		moduleTwoTabItem.add(moduleTwo.build());
		tabPanel.add(moduleTwoTabItem);
		
		moduleThreeTabItem.add(moduleThree.build());
		tabPanel.add(moduleThreeTabItem);
		
		moduleFourTabItem.add(moduleFour.build());
		tabPanel.add(moduleFourTabItem);
		
		buildCenterPanel();
		return contentPanel;
	}
	
	private void buildCenterPanel() {
		contentPanel.add(tabPanel);
	}

	public Boolean getAlreadyOpened() {
		return alreadyOpened;
	}

	public void setAlreadyOpened(Boolean alreadyOpened) {
		this.alreadyOpened = alreadyOpened;
	}

}