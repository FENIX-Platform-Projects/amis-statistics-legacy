package org.fao.fenix.web.modules.re.client.control.report;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuReportController extends CatalogueContextMenuController {

	 
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table){
		return new SelectionListener<MenuEvent>(){
			public void componentSelected(MenuEvent ce){
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				//hide the resource explorer
				//REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);
				
				for (ResourceChildModel model: selectedItems){
					new ReportViewer(Long.valueOf(model.getId()));
				}
				
				
				//add to project manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.REPORT);
				
				if (REModel.CLOSE){
					//hide the context menu
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
						
			}
			
		};
	}

}