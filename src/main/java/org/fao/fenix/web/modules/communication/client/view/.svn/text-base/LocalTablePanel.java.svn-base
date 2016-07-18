package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.LocalController;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class LocalTablePanel {

	private LocalTable localTable;
	
	private LocalPager localPager;
	
	private VerticalPanel panel;
	
	public LocalTablePanel() {
		localPager = new LocalPager();
		panel = new VerticalPanel();
	}
	
	public VerticalPanel build() {
		localTable = new LocalTable();
		localTable.build(localTable.getTable(), 0, TableConstants.ITEMS_PER_PAGE);
		panel.add(localTable.getTable());
		panel.add(localPager.build(this));
		LocalController.updatePagerInfo(this, 1);
		return panel;
	}

	public LocalTable getLocalTable() {
		return localTable;
	}

	public LocalPager getLocalPager() {
		return localPager;
	}

	public VerticalPanel getPanel() {
		return panel;
	}
	
}
