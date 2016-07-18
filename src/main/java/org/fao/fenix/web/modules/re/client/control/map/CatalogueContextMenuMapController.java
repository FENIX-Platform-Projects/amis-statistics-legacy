package org.fao.fenix.web.modules.re.client.control.map;

import java.util.List;

import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuMapController extends CatalogueContextMenuController  {

	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table){
		return new SelectionListener<MenuEvent>(){
			public void componentSelected(MenuEvent ce){
				
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				//hide the context menu
				REModel.getResourceExplorer().setCatalogueContextMenu(null);
				
				//hide the resource explorer
				//REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);
				
				if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();
								
				for (ResourceChildModel model: selectedItems){
					MapWindow mw = new MapWindow(model.getId(), model.getName());
					mw.show();
				}


				//add to Project Manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.MAPVIEW);

			}
		};
	}

}
