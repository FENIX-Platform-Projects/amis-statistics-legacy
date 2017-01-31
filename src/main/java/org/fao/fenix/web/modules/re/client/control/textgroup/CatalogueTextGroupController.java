package org.fao.fenix.web.modules.re.client.control.textgroup;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.textgroup.CatalogueContextMenuTextGroup;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueTextGroupController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
		    }
		};
	}
	
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		final List<ResourceChildModel> textGroupList = new ArrayList<ResourceChildModel>();
		Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		textGroupList.add(model);
		
		ResourceOpener.openTextGroup(Long.valueOf(model.getId()), model.hasWritePermission());
		
		//add to project manager
		PMModel.addResourceToProjectManager(textGroupList, ResourceType.TEXTGROUP);
		
		if (REModel.CLOSE){
			  resourceExplorer.getWindow().close();
		}
	}

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuTextGroup(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueTextGroupController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}
	
}
