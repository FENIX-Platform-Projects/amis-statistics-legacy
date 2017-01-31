package org.fao.fenix.web.modules.re.client.control.text;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CataloguePagerTextController extends CataloguePagerController {
	
	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		
			if (resourceExplorer.getCaller().equals(WhoCall.USER)){
				for (ResourceChildModel model: selectedItems){
					ResourceOpener.openTextViewer(Long.valueOf(model.getId()), model.hasWritePermission());
				}
				
				//add to project manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.TEXTVIEW);
				
				//close resource explorer
				if (REModel.CLOSE) resourceExplorer.getWindow().close();
				
			} else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
				ResourceChildModel model=null;
				for (ResourceChildModel model2: selectedItems){
					model = model2;
					break;
				}
				final ReportViewer reportViewer = ((ResourceExplorerText)resourceExplorer).getReportViewer();
				final ResourceChildModel modelBis = model;
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().createReport(Long.valueOf(model.getId()), reportViewer.getObjectPosition(), reportViewer.getRptDesign(), "text", 400, 300, reportViewer.getKeyTemplate() , new AsyncCallback<String>(){
					
					public void onSuccess(String s) {
						if ((reportViewer.getKeyTemplate().equals("template1") || reportViewer.getKeyTemplate().equals("template2")) && !reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText().equals("( " + BabelFish.print().empty() + ")")){
							reportViewer.getSideBar().deleteResource("text", reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getItemId());
							//Window.alert(reportViewer.getSideBar().getListResource().getItem((reportViewer.getObjectPosition()-1)).getText());
						}
						reportViewer.getSideBar().addElementToListResource(modelBis.getId(), modelBis.getName(), "text", reportViewer.getObjectPosition());
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
