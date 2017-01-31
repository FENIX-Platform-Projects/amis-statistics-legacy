package org.fao.fenix.web.modules.re.client.view.dataset;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.communication.CatalogueContextMenuCommunicationController;
import org.fao.fenix.web.modules.re.client.control.dataset.CatalogueContextMenuDatasetController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CatalogueContextMenu;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.communication.SearchParametersCommunication;
import org.fao.fenix.web.modules.re.client.view.layer.SearchParametersLayer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

public class CatalogueContextMenuDataset extends CatalogueContextMenu {

	private ResourceChildModel selectedModel;
	
	public CatalogueContextMenuDataset(ResourceExplorer explorer, Catalogue catalogue) {
		super(explorer, catalogue);
	}

	public CatalogueContextMenuDataset(Catalogue catalogue) {
		super(catalogue);
	}

	public Menu buildMenu(final ResourceChildModel model) {	
		if (model != null) {
			selectedModel = model;
			contextMenu = buildMenu();
		} else System.out.println("######### CatalogueContextMenuDataset: selectedItem is NULL");

		return contextMenu;
	}
	
	public Menu buildMenu() {

		// View/Edit Dataset
		MenuItem view = new MenuItem(BabelFish.print().viewEdit());
		view.addSelectionListener(CatalogueContextMenuDatasetController.open(explorer, "table", catalogueGrid));
		contextMenu.add(view);
		contextMenu.add(new SeparatorMenuItem());
		
		// Chart Analysis
		MenuItem chartAnalysis = new MenuItem(BabelFish.print().chartAnalysis());
		chartAnalysis.addSelectionListener(CatalogueContextMenuDatasetController.open(explorer, "chart", catalogueGrid));
		contextMenu.add(chartAnalysis);
		
		// Table Analysis - currently disabled
		MenuItem tableAnalysis = new MenuItem(BabelFish.print().tableAnalysis());
		tableAnalysis.addSelectionListener(CatalogueContextMenuDatasetController.open(explorer, "tableAnalysis", catalogueGrid));
		tableAnalysis.setEnabled(false);
		contextMenu.add(tableAnalysis);
		contextMenu.add(new SeparatorMenuItem());
				
		// Export as csv 
		MenuItem csvExport = new MenuItem(BabelFish.print().exportAsCSV());
		csvExport.addSelectionListener(CatalogueContextMenuDatasetController.exportAsCSV(catalogueGrid));
		contextMenu.add(csvExport);
		contextMenu.add(new SeparatorMenuItem());

		// SET SCOPE
		if (explorer.getSearchParameters() instanceof SearchParametersDataset || explorer.getSearchParameters() instanceof SearchParametersLayer) {
			String scope = ((SearchParametersCommunication) explorer.getSearchParameters()).getLookIn();
			explorer.getSearchButtons().getFenixSearchParameters().setScope(scope);
		}

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

		
		if (FenixUser.hasUserRole() && selectedModel!=null) {
		    	contextMenu.add(delete);
		    	contextMenu.add(new SeparatorMenuItem());
//		    	contextMenu.add(rename);
//		    	contextMenu.add(new SeparatorMenuItem());
		}

		// View Metadata
		MenuItem metadata = new MenuItem(BabelFish.print().viewMetadata());
		metadata.addSelectionListener(CatalogueContextMenuController.viewMetadata(catalogueGrid));
		contextMenu.add(metadata);
		
		// set the permission with the permission manager
		addSetPermissions();

		return contextMenu;

	}
}
