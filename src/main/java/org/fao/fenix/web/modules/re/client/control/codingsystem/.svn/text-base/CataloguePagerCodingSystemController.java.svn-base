package org.fao.fenix.web.modules.re.client.control.codingsystem;

import java.util.List;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerCodingSystemController extends CataloguePagerController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				if (resourceExplorer.getCaller().equals(WhoCall.USER)){
					for (ResourceChildModel model: selectedItems){
						
						//open coding system label
						new CodingSearchResults().build(Long.valueOf(model.getId()));			
					}
					
					//add to project manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.CODINGSYSTEM);
					
					if (REModel.CLOSE) resourceExplorer.getWindow().close();
				}
			}
					
		};
	}
}
