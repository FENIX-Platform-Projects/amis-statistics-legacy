package org.fao.fenix.web.modules.re.client.control.codingsystem;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.codingsystem.CatalogueContextMenuCodingSystem;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueCodingSystemController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
			}
		};
	}
	
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		final List<ResourceChildModel> resources = new ArrayList<ResourceChildModel>();
		
		
		if (resourceExplorer.getCaller().equals(WhoCall.USER)){
			ResourceChildModel model = table.getSelectionModel().getSelectedItem();
			resources.add(model);
			//open coding system label
			new CodingSearchResults().build(Long.valueOf(model.getId()));
			
			//add to Project Manager
			PMModel.addResourceToProjectManager(resources, ResourceType.CODINGSYSTEM);
				
			if (REModel.CLOSE) resourceExplorer.getWindow().close();
		}
	}
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuCodingSystem(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueDatasetController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}

}
