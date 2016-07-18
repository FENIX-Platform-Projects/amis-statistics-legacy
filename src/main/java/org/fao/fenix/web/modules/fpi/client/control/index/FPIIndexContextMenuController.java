package org.fao.fenix.web.modules.fpi.client.control.index;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.table.Table;

public class FPIIndexContextMenuController {

	public static SelectionListener<MenuEvent> buildRemoveRowListener(final Table table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent event) {
				table.remove(table.getSelectedItem());
			}
		};
	}
}
