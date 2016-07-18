package org.fao.fenix.web.modules.re.client.control.project;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuProjectController extends CatalogueContextMenuController {

	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
					
				//hide the resource explorer
				//REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);
				
				if (REModel.CLOSE){
					//hide the context menu
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
				
				final List<ResourceChildModel> projectList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
			
				for (ResourceChildModel model : selectedItems) {
					
					if(model.getType().equals(ResourceType.PROJECT))
					   projectList.add(model);
					else {
						//projectList.add((ResourceChildModel)((item.getParentItem()).getParentItem()).getModel());
					}
				}
				
				//open project resources
				for (ResourceChildModel rsrcModel: selectedItems){
					String type = rsrcModel.getType();
					Long id = Long.valueOf(rsrcModel.getId());
						
					if(type.equals(ResourceType.MAPVIEW)) {
						MapWindow mw = new MapWindow(rsrcModel.getId(), rsrcModel.getName());
						mw.show();
					}
					else if(type.equals(ResourceType.CHARTVIEW)) {
						new ChartViewer(rsrcModel.getId());
					}
					else if(type.equals(ResourceType.TABLEVIEW)) {					
						new TableWindow().showAllData(id, rsrcModel.hasWritePermission(), rsrcModel.isFlexDatasetType(), rsrcModel.getName());
						//new TableWindow().build(id, rsrcModel.hasWritePermission(), rsrcModel.isFlexDatasetType(), rsrcModel.getName());
					}
					else if(type.equals(ResourceType.DATASET)) {						
						new TableWindow().showAllData(id, rsrcModel.hasWritePermission(), rsrcModel.isFlexDatasetType(), rsrcModel.getName());
						//new TableWindow().build(id, rsrcModel.hasWritePermission(), rsrcModel.isFlexDatasetType(), rsrcModel.getName());
					}
					else if(type.equals(ResourceType.REPORT)) {
						new ReportViewer(id);
					}
					else if(type.equals(ResourceType.TEXTVIEW)) {
						ResourceOpener.openTextViewer(id, rsrcModel.hasWritePermission());	
					}
					else if(type.equals(ResourceType.LAYER)) {
						
					}
					else if(type.equals(ResourceType.PATTERN)) {
						FenixAlert.info("Info", "The associated datasets for the data theme can be viewed from the project manager.");
					}
					
				}	
		
				if(projectList!=null){
				//add parent project to project manager
				PMModel.addProjectToProjectManager(projectList);
				} else {
					PMModel.addResourceToProjectManager(selectedItems, "addResource");
				}
				

			}
		};
	}

}
