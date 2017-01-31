package org.fao.fenix.web.modules.re.client.control.table;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.table.CatalogueContextMenuTable;
import org.fao.fenix.web.modules.re.client.view.table.ResourceExplorerTable;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueTableController extends CatalogueController {

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

		if (resourceExplorer.getCaller().equals(WhoCall.USER)){
			resourceList.add(model);
			TableServiceEntry.getInstance().getDatasetIdFromTableViewId(Long.valueOf(model.getId()), new AsyncCallback<Long>() {
				public void onSuccess(Long resourceId) {					
					new TableWindow().showAllData(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
					//new TableWindow().build(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
				}
				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "getDatasetIdFromTableViewId @ openResources");
				}
			});
			
			// add to project manager
			PMModel.addResourceToProjectManager(resourceList, ResourceType.TABLEVIEW);
			if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();
			
		} else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
			final ReportViewer reportViewer = ((ResourceExplorerTable)resourceExplorer).getReportViewer();
			final ResourceChildModel modelBis = model;
			final LoadingWindow loading = new LoadingWindow();
			loading.create();
			BirtServiceEntry.getInstance().createReport(Long.valueOf(model.getId()), reportViewer.getObjectPosition(), reportViewer.getRptDesign(), "table", 400, 300, reportViewer.getKeyTemplate(), new AsyncCallback<String>(){
				
				public void onSuccess(String s) {
					if ((reportViewer.getKeyTemplate().equals("template1") || reportViewer.getKeyTemplate().equals("template2")) && !reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText().equals("( " + BabelFish.print().empty() + ")")){
						reportViewer.getSideBar().deleteResource("table", reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getItemId());
						//Window.alert(reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText());
					}
					reportViewer.getSideBar().addElementToListResource(modelBis.getId(), modelBis.getName(), "table", reportViewer.getObjectPosition());
					reportViewer.getReport().setHTML(s);
					loading.destroy();
				}
				
				public void onFailure(Throwable caught) {
					Info.display("Service call failed!", "Service call to {0} failed", "createReport()");
					loading.destroy();
				}
				
			});
			
			resourceExplorer.getWindow().close();
		}
	}
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuTable(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueTableController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}

}
