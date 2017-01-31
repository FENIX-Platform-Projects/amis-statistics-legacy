package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.communication.client.control.LocalController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class CommunicationTabPanel {

	private TabPanel tabPanel;
	private CommunicationTablePanel communicationTablePanel;
	private LocalTablePanel localTablePanel;
	private TabItem remote;
	private TabItem local;
	
	public CommunicationTabPanel() {
		tabPanel = new TabPanel();
	}
	
	public TabPanel build() {
		remote = new TabItem(BabelFish.print().remoteResources());
		local = new TabItem(BabelFish.print().localResources());
		ContentPanel remotePanel = buildRemotePanel();
		ContentPanel localPanel = buildLocalPanel();
		remote.add(remotePanel);
		local.add(localPanel);
		tabPanel.add(remote);
		tabPanel.add(local);
		return tabPanel;
	}
	
	public void addListener(CommunicationModuleParametersPanel parametersPanel) {
		tabPanel.addListener(Events.Select, CommunicationController.getTabListener(tabPanel, parametersPanel));
	}
	
	public ContentPanel buildRemotePanel() {
		ContentPanel remotePanel = new ContentPanel();
		remotePanel.setLayout(new FitLayout());
		communicationTablePanel = new CommunicationTablePanel();
		communicationTablePanel.build();
		remotePanel.add(communicationTablePanel.getCommunicationTable().getTable());
		remotePanel.setBottomComponent(communicationTablePanel.getCommunicationModulePager().getPanel());
		CommunicationController.updatePagerInfo(communicationTablePanel, 1);
		remotePanel.setHeight("350px");
		remotePanel.setHeaderVisible(false);
		return remotePanel;
	}
	
	public ContentPanel buildLocalPanel() {
		ContentPanel localPanel = new ContentPanel();
		localPanel.setLayout(new FitLayout());
		localTablePanel = new LocalTablePanel();
		localTablePanel.build();
		localPanel.add(localTablePanel.getLocalTable().getTable());
		localPanel.setBottomComponent(localTablePanel.getLocalPager().getPanel());
		LocalController.updatePagerInfo(localTablePanel, 1);
		localPanel.setHeight("350px");
		localPanel.setHeaderVisible(false);
		return localPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public CommunicationTablePanel getCommunicationTablePanel() {
		return communicationTablePanel;
	}

	public LocalTablePanel getLocalTablePanel() {
		return localTablePanel;
	}

	public TabItem getRemote() {
		return remote;
	}

	public TabItem getLocal() {
		return local;
	}
	
}
