package org.fao.fenix.web.modules.re.client.control.report;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.report.CatalogueContextMenuReport;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;


public class CatalogueReportController extends CatalogueController {
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
			}
		};
	}
	
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		final List<ResourceChildModel> resourceList = new ArrayList<ResourceChildModel>();
		
		Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();

		resourceList.add(model);
		Long id = Long.valueOf(model.getId());
//		new ReportViewer(id);
		new TinyMCEReportWindow().build(id);
		
		//add to project manager
//		PMModel.addResourceToProjectManager(resourceList, ResourceType.REPORT);
		
		//REModel.getResourceExplorer().getWindow().close();
	
		if (REModel.CLOSE) resourceExplorer.getWindow().close();
	}
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuReport(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueReportController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}

}
