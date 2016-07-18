package org.fao.fenix.web.modules.re.client.view.textgroup;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.communication.CatalogueContextMenuCommunicationController;
import org.fao.fenix.web.modules.re.client.control.textgroup.CatalogueContextMenuTextGroupController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CatalogueContextMenu;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;


public class CatalogueContextMenuTextGroup extends CatalogueContextMenu {

	public CatalogueContextMenuTextGroup(ResourceExplorer explorer, Catalogue catalogue){
		super(explorer, catalogue);
		System.out.println("-------------------------------- CatalogueContextMenuTextGroup -------------------------");
		
	}

	public Menu buildMenu() {	
		System.out.println("-------------------------------- CatalogueContextMenuText buildMenu -------------------------");
		// Open
		MenuItem open = new MenuItem(BabelFish.print().open());
		open.addSelectionListener(CatalogueContextMenuTextGroupController.open(catalogueGrid));
		contextMenu.add(open);
		contextMenu.add(new SeparatorMenuItem());
		
		// Share or Download
		if(FenixUser.hasAdminRole()) {
			MenuItem share = new MenuItem(BabelFish.print().share());
			share.addSelectionListener(CatalogueContextMenuCommunicationController.share(catalogueGrid));
			contextMenu.add(share);
			contextMenu.add(new SeparatorMenuItem());
		}

		// Delete
		MenuItem delete = new MenuItem(BabelFish.print().delete());
		delete.addSelectionListener(CatalogueContextMenuController.delete(catalogueGrid));
	
		// Rename
		MenuItem rename = new MenuItem(BabelFish.print().rename());
		rename.addSelectionListener(CatalogueContextMenuController.rename(catalogueGrid));
		
		if(FenixUser.hasUserRole()){
			contextMenu.add(delete);
			contextMenu.add(new SeparatorMenuItem());
//			contextMenu.add(rename);
//			contextMenu.add(new SeparatorMenuItem());
		}
		
		//View Metadata
		MenuItem metadata = new MenuItem(BabelFish.print().viewMetadata());
		metadata.addSelectionListener(CatalogueContextMenuController.viewMetadata(catalogueGrid));
		contextMenu.add(metadata);
		
		// set the permission with the permission manager
		addSetPermissions();
		
		return contextMenu;

	}
}
