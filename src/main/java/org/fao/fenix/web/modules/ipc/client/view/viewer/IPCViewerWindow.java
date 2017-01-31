package org.fao.fenix.web.modules.ipc.client.view.viewer;

import java.util.List;

import org.fao.fenix.web.modules.ipc.common.services.IPCXMLServiceEntry;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class IPCViewerWindow extends FenixWindow {

	private TabPanel tabPanel;
	
	private TabItem tabOne;
	
	private TabItem tabTwo;
	
	private TabItem tabThree;
	
	public IPCViewerWindow() {
		tabPanel = new TabPanel();
		tabOne = new TabItem("Part 1");
		tabOne.setScrollMode(Scroll.AUTO);
		tabTwo = new TabItem("Part 2");
		tabTwo.setScrollMode(Scroll.AUTO);
		tabThree = new TabItem("Part 3");
		tabThree.setScrollMode(Scroll.AUTO);
		tabPanel.add(tabOne);
		tabPanel.add(tabTwo);
		tabPanel.add(tabThree);
	}
	
	public void build(final String xml, final boolean isFromFile, final String windowHeader) {
		
		IPCXMLServiceEntry.getInstance().getAllModules(xml, isFromFile, new AsyncCallback<List<ModuleVO>>() {
			
			public void onSuccess(List<ModuleVO> modules) {
				
				ModuleVO moduleOne = modules.get(0);
				ModuleVO moduleTwo = modules.get(1);
				ModuleVO moduleThree = modules.get(2);
				ModuleVO moduleFour = modules.get(3);
				
				HTML partOneHtml = new HTML(IPCViewBuilder.buildTabOne(moduleOne, moduleTwo));
				HTML partTwoHtml = new HTML(IPCViewBuilder.buildTabTwo(moduleThree));
				HTML partThreeHtml = new HTML(IPCViewBuilder.buildTabThree(moduleFour));
				
				tabOne.add(partOneHtml);
				tabTwo.add(partTwoHtml);
				tabThree.add(partThreeHtml);
				
				buildCenterPanel();
				format(windowHeader);
				show();
			}
			
			public void onFailure(Throwable e) {
				FenixAlert.error("Error", e.getMessage());
			}
			
		});
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(tabPanel);
		addCenterPartToWindow();
	}
	
	private void format(String windowHeader) {
		setSize("995px", "590px");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setHeading(windowHeader);
	}
	
}