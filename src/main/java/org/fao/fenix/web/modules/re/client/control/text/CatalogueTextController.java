package org.fao.fenix.web.modules.re.client.control.text;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.text.CatalogueContextMenuText;
import org.fao.fenix.web.modules.re.client.view.text.ResourceExplorerText;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueTextController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
		    }
		};
	}
	
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
		Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		

		 if (resourceExplorer.getCallback() != null) {
             resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
         }  				
		 
		 else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {
			 DesignerBox designerBox = ((ResourceExplorerText)resourceExplorer).getDesignerBox();
			 designerBox.setResourceID(Long.valueOf(model.getId()));
			 designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.TEXT);
			 designerBox.setResourceTitle(model.getName());
			 designerBox.getResourceName().setValue(model.getName());
			 resourceExplorer.getWindow().close();
		 }
		 
		 else if (resourceExplorer.getCaller().equals(WhoCall.USER)){
			ResourceOpener.openTextViewer(Long.valueOf(model.getId()), model.hasWritePermission());
			textList.add(model);
			//close resource explorer
			//resourceExplorer.getWindow().close();
			//add to project manager
			PMModel.addResourceToProjectManager(textList, ResourceType.TEXTVIEW);
			if (REModel.CLOSE) resourceExplorer.getWindow().close();
			
		} else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
			final ReportViewer reportViewer = ((ResourceExplorerText)resourceExplorer).getReportViewer();
			final ResourceChildModel modelBis = model;
			final LoadingWindow loading = new LoadingWindow();
			loading.create();
			BirtServiceEntry.getInstance().createReport(Long.valueOf(model.getId()), reportViewer.getObjectPosition(), reportViewer.getRptDesign(), "text", 400, 300, reportViewer.getKeyTemplate(), new AsyncCallback<String>(){
				
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
			
			//resourceExplorer.getWindow().close();
		} 
		//close resource explorer
		 if (REModel.CLOSE){
			  resourceExplorer.getWindow().close();
		}
	}

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuText(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueTextController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}
	
}
