package org.fao.fenix.web.modules.fpi.client.view.index;

import org.fao.fenix.web.modules.fpi.client.control.index.FPIIndexContextMenuController;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.table.Table;

public class FPIIndexContextMenu {

	public Menu menu;
	
	public FPIIndexContextMenu() {
		menu = new Menu();
	}
	
	public Menu build(final Table table) {
		MenuItem removeRow = new MenuItem();
		removeRow.setText("Remove Row");
		removeRow.setIconStyle("undo");
		removeRow.addSelectionListener(FPIIndexContextMenuController.buildRemoveRowListener(table));
		menu.add(removeRow);
		return menu;
	}
	
}
