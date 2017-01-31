package org.fao.fenix.web.modules.re.client.view.communication;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.communication.CatalogueContextMenuCommunicationController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CatalogueContextMenu;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

public class CatalogueContextMenuCommunication extends CatalogueContextMenu {

	public CatalogueContextMenuCommunication(Catalogue catalogue) {
		super(catalogue);
	}
	
	public CatalogueContextMenuCommunication(ResourceExplorer explorer, Catalogue catalogue) {
		super(explorer, catalogue);
	}
	
	@Override
	public Menu buildMenu() {
		
		// Download
		MenuItem download = new MenuItem(BabelFish.print().download());
		download.addSelectionListener(CatalogueContextMenuCommunicationController.download(catalogueGrid));
		contextMenu.add(download);
		contextMenu.add(new SeparatorMenuItem());
		
		// Share Resource
		MenuItem shareResource = new MenuItem(BabelFish.print().share());
		shareResource.addSelectionListener(CatalogueContextMenuCommunicationController.share(catalogueGrid));
		contextMenu.add(shareResource);
		contextMenu.add(new SeparatorMenuItem());

		// Delete
		MenuItem delete = new MenuItem(BabelFish.print().delete());
		delete.addSelectionListener(CatalogueContextMenuCommunicationController.deleteCommunicationResource(catalogueGrid));
	
		// Rename
		MenuItem rename = new MenuItem(BabelFish.print().rename());
		rename.addSelectionListener(CatalogueContextMenuController.rename(catalogueGrid));
		
		if(FenixUser.hasUserRole()){
			contextMenu.add(delete);
			contextMenu.add(new SeparatorMenuItem());
//			contextMenu.add(rename);
//			contextMenu.add(new SeparatorMenuItem());
		}
		
		// View Network Metadata
		MenuItem viewNetworkMetadata = new MenuItem(BabelFish.print().viewCommunicationResourceMetadata());
		viewNetworkMetadata.addSelectionListener(CatalogueContextMenuCommunicationController.viewCommunicationResourceMetadata(catalogueGrid));
		contextMenu.add(viewNetworkMetadata);
		contextMenu.add(new SeparatorMenuItem());
		
		return contextMenu;
		
	}

}
