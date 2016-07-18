package org.fao.fenix.web.modules.re.client.view;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.treetable.TreeTable;

public abstract class CatalogueContextMenu {

	protected Catalogue catalogue;
	protected TreeTable catalogueTable;
	protected Grid<ResourceChildModel> catalogueGrid;
	protected ResourceExplorer explorer;

	public Menu contextMenu;

	public abstract Menu buildMenu();

	public CatalogueContextMenu(ResourceExplorer explorer, Catalogue catalogue) {
		this.explorer = explorer;
		this.catalogue = catalogue;
		//this.catalogueTable = catalogue.getTable();
		this.catalogueGrid = catalogue.getGrid();
		this.contextMenu = new Menu();
	}

	public CatalogueContextMenu(Catalogue catalogue) {
		this.catalogue = catalogue;
		//this.catalogueTable = catalogue.getTable();
		this.catalogueGrid = catalogue.getGrid();
		this.contextMenu = new Menu();
	}

	protected void addSetPermissions() {
		// Open PermissionManager
		if (FenixUser.hasAdminRole()) {
			contextMenu.add(new SeparatorMenuItem());
			MenuItem setPermissions = new MenuItem("Set Permissions");
			setPermissions.addSelectionListener(CatalogueContextMenuController.setPermissions(catalogueGrid));
			contextMenu.add(setPermissions);
		}
	}

}
