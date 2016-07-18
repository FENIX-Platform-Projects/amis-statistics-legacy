package org.fao.fenix.web.modules.re.client.view.chart;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.chart.CatalogueContextMenuChartController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CatalogueContextMenu;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

public class CatalogueContextMenuChart extends CatalogueContextMenu {

	public CatalogueContextMenuChart(ResourceExplorer explorer, Catalogue catalogue){
		super(explorer, catalogue);
	}

	public Menu buildMenu() {	
		
		// Open
		MenuItem open = new MenuItem(BabelFish.print().open());
		open.addSelectionListener(CatalogueContextMenuChartController.open(explorer));
		contextMenu.add(open);
		contextMenu.add(new SeparatorMenuItem());

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
		
		//Change Dataset
		if(FenixUser.hasUserRole()){
			MenuItem changeDataset = new MenuItem(BabelFish.print().associateDataset());
			changeDataset.addSelectionListener(CatalogueContextMenuController.associateDatasetToChart(explorer));
			contextMenu.add(changeDataset);
		}
		
		// set the permission with the permission manager
		addSetPermissions();

		return contextMenu;

	}

}