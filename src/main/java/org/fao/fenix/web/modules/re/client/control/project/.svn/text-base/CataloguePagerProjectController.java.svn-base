package org.fao.fenix.web.modules.re.client.control.project;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerProjectController extends CataloguePagerController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){

				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				
				for (ResourceChildModel model: selectedItems){
					if(model.getType().equals(ResourceType.PROJECT)){
					//add to project manager
					  PMModel.addProjectToProjectManager(selectedItems);
					  resourceExplorer.getWindow().close();
					}
				}
				
				//close resource explorer
				if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();
			}
		};
	}
	

}
