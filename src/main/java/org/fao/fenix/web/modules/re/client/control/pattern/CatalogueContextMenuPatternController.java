package org.fao.fenix.web.modules.re.client.control.pattern;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuPatternController extends CatalogueContextMenuController  {
	
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table){
		return new SelectionListener<MenuEvent>(){
			public void componentSelected(MenuEvent ce){
				
				
				//hide the context menu
				REModel.getResourceExplorer().setCatalogueContextMenu(null);
				
				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);
				
				List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				FenixAlert.info("Info", "The associated datasets for the data theme can be viewed from the project manager.");
				
				//Patterns are only added to project manager and the datasets viewed from there
				//add to Project Manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.PATTERN);
				
			}
		};
	}

}
