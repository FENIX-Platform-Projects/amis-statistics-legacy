package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.google.gwt.user.client.Timer;

public class NetworkMonitor {

	private ContentPanel networkPanel;
	private NetworkTable networkTable;
	private Button showNodeInfo;
	private Button addNode;
	private Timer timer;
	
	public ContentPanel build() {
		networkPanel = new ContentPanel();
		networkPanel.setLayout(new FitLayout());
		networkTable = new NetworkTable();
		networkTable.build(networkTable.getTable());
		networkPanel.add(networkTable.getTable());
		networkPanel.setBottomComponent(buildButtonsPanel(networkTable.getTable()));
		format();
		updateTable();
		return networkPanel;
	}
	
	public void updateTable() {
		timer = new Timer() {
			public void run() {
				networkTable.getTable().removeAll();
				networkTable.build(networkTable.getTable());
			}
		};
		timer.scheduleRepeating(600000);
	}
	
	public void destroy() {
		if (timer != null)
			timer.cancel();
	}
	
	private HorizontalPanel buildButtonsPanel(Table table) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		showNodeInfo = new Button(BabelFish.print().showNodeInfo());
		showNodeInfo.addSelectionListener(CommunicationController.getShowNodeInfoListener(table));
		addNode = new Button(BabelFish.print().addNode());
		addNode.addSelectionListener(CommunicationController.getAddNodeListener());
		buttonsPanel.add(showNodeInfo);
		buttonsPanel.add(addNode);
		// buttonsPanel.add(removeNode);
		return buttonsPanel;
	}
	
	private void format() {
		networkPanel.setHeaderVisible(false);
	}
	
}
