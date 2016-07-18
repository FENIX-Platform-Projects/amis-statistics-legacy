package org.fao.fenix.web.modules.re.client.control.table;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.table.ResourceExplorerTable;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CataloguePagerTableController extends CataloguePagerController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				if (resourceExplorer.getCaller().equals(WhoCall.USER)){
					for (final ResourceChildModel model: selectedItems){
						
						TableServiceEntry.getInstance().getDatasetIdFromTableViewId(Long.valueOf(model.getId()), new AsyncCallback<Long>() {
							public void onSuccess(Long resourceId) {								
								new TableWindow().showAllData(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
								//new TableWindow().build(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
							}
							public void onFailure(Throwable caught) {
								FenixAlert.alert("RPC Failed", "getDatasetIdFromTableViewId @ openResources");
							}
						});
					}
					
					//add to project manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.TABLEVIEW);
					
					if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();
					
				}  else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
					ResourceChildModel model=null;
					for (ResourceChildModel model2: selectedItems){
						model = model2;
						break;
					}
					final ReportViewer reportViewer = ((ResourceExplorerTable) resourceExplorer).getReportViewer();
					final ResourceChildModel modelBis = model;
					final LoadingWindow loading = new LoadingWindow();
					loading.create();
					BirtServiceEntry.getInstance().createReport(Long.valueOf(model.getId()), reportViewer.getObjectPosition(), reportViewer.getRptDesign(), "table", 400, 300, reportViewer.getKeyTemplate() , new AsyncCallback<String>(){
						
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
		};
	}
	
}
