package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;

public class CommunicationModuleWindow extends FenixWindow {

	private ContentPanel parametersPanel;
	
	private LoadingWindow loadingWindow;
	
	private CommunicationTabPanel tabPanel;
	
	private CommunicationModuleParametersPanel communicationModuleParametersPanel;
	
	private NetworkMonitor networkMonitor;
	
	public CommunicationModuleWindow() {
		loadingWindow = new LoadingWindow();
	}
	
	public void build() {
		buildCenterPanel();
		buildWestPanel();
		buildEastPanel();
		format();
		destroyThread();
		show();
		communicationModuleParametersPanel.updateButtons(tabPanel.getTabPanel());
		tabPanel.addListener(communicationModuleParametersPanel);
		communicationModuleParametersPanel.enhanceButtons(tabPanel.getCommunicationTablePanel().getCommunicationTable().getTable(), tabPanel.getLocalTablePanel().getLocalTable().getTable(), communicationModuleParametersPanel, this);
		communicationModuleParametersPanel.enhanceRemoteSearchButtons(tabPanel.getCommunicationTablePanel().getCommunicationTable(), communicationModuleParametersPanel.getRemoteSearchPanel());
		communicationModuleParametersPanel.enhanceLocalSearchButtons(tabPanel.getLocalTablePanel().getLocalTable(), communicationModuleParametersPanel.getLocalSearchPanel());
	}
	
	private void destroyThread() {
		getWindow().addWindowListener(new WindowListener() {
			@Override
			public void windowHide(WindowEvent we) {
//				super.windowClose(we); // 20090727 ETj fixme -- API changed
				networkMonitor.destroy();
			}
		});
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		tabPanel = new CommunicationTabPanel();
		getCenter().add(tabPanel.build());
		getCenter().setHeading(BabelFish.print().result());
		addCenterPartToWindow();
	}
	
	private void buildWestPanel() {
		communicationModuleParametersPanel = new CommunicationModuleParametersPanel();
		parametersPanel = communicationModuleParametersPanel.build();
		fillWestPart(parametersPanel);
		getWestData().setSize(250);
		getWest().setHeading(BabelFish.print().controller());
	}
	
	private void buildEastPanel() {
		networkMonitor = new NetworkMonitor();
		ContentPanel networkPanel = networkMonitor.build();
		fillEastPart(networkPanel);
		getEastData().setSize(350);
		getEast().setHeading(BabelFish.print().networkMonitor());
	}
	
	private void format() {
		setSize(1000, 450);
		getWindow().setHeading(BabelFish.print().communicationModule());
		setCollapsible(true);
		getWindow().setResizable(false);
	}

	public LoadingWindow getLoadingWindow() {
		return loadingWindow;
	}

	public NetworkMonitor getNetworkMonitor() {
		return networkMonitor;
	}
	
}
