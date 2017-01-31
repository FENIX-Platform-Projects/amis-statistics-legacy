package org.fao.fenix.web.modules.re.client.control.tableview;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuTableViewController extends CatalogueContextMenuController {

	public static SelectionListener<MenuEvent> open(final ResourceExplorer resourceExplorer) {
		
		return new SelectionListener<MenuEvent>() {
			
			public void componentSelected(MenuEvent ce) {

				Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				REModel.setResourceExplorer(null);

				for (ResourceChildModel model : selectedItems) 
					new TableViewWindow().build(Long.valueOf(model.getId()), Long.valueOf(model.getLocalId()), false, false);

				// add to Project Manager
//				PMModel.addResourceToProjectManager(selectedItems, ResourceType.TABLEVIEW);

				if (REModel.CLOSE) {
					// hide the context menu
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					resourceExplorer.getWindow().close();
				}
				
			}
			
		};
		
	}
	
	@SuppressWarnings("deprecation")
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table) {
		
		return new SelectionListener<MenuEvent>() {
			
			public void componentSelected(MenuEvent evt) {
				
				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().close();
				REModel.setResourceExplorer(null);
				
				//final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model: selectedItems)
					new TableViewWindow().build(Long.valueOf(model.getId()), Long.valueOf(model.getLocalId()), false, false);
					
			    //add to Project Manager
//				PMModel.addResourceToProjectManager(selectedItems, ResourceType.TABLEVIEW);
				
				if (REModel.CLOSE){
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
						
			}
		};
	}

}