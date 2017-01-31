package org.fao.fenix.web.modules.re.client.control.pattern;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePatternController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final Grid<ResourceChildModel> table) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				List<ResourceChildModel> resources = table.getSelectionModel().getSelectedItems();
								
		
				//Patterns are only added to project manager and the datasets viewed from there
				
				FenixAlert.info("Info", "The associated datasets for the data theme can be viewed from the project manager.");
				
				//add to Project Manager
				PMModel.addResourceToProjectManager(resources, ResourceType.PATTERN);
				
				REModel.getResourceExplorer().getWindow().hide();
				
			}
		};
	}

}