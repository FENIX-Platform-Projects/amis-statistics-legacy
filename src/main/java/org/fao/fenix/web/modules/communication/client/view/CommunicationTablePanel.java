package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class CommunicationTablePanel {

	private CommunicationTable communicationTable;
	
	private CommunicationModulePager communicationModulePager;
	
	private VerticalPanel panel;
	
	public CommunicationTablePanel() {
		communicationModulePager = new CommunicationModulePager();
		panel = new VerticalPanel();
	}
	
	public VerticalPanel build() {
		communicationTable = new CommunicationTable();
		communicationTable.build(communicationTable.getTable(), 0, TableConstants.ITEMS_PER_PAGE);
		panel.add(communicationTable.getTable());
		panel.add(communicationModulePager.build(this));
		CommunicationController.updatePagerInfo(this, 1);
		return panel;
	}

	public CommunicationTable getCommunicationTable() {
		return communicationTable;
	}

	public CommunicationModulePager getCommunicationModulePager() {
		return communicationModulePager;
	}

	public VerticalPanel getPanel() {
		return panel;
	}
	
}
