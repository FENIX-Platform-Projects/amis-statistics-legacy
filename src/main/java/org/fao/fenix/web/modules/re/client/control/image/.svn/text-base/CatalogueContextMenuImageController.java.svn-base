package org.fao.fenix.web.modules.re.client.control.image;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.ImageWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuImageController extends CatalogueContextMenuController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table) {
		
		return new SelectionListener<MenuEvent>() {
			
			public void componentSelected(MenuEvent evt) {
				
				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().close();
				REModel.setResourceExplorer(null);
				
				//final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model: selectedItems)
					open(Long.valueOf(model.getId()));
					
			    //add to Project Manager
//				PMModel.addResourceToProjectManager(selectedItems, ResourceType.OLAP);
				
				if (REModel.CLOSE){
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
						
			}
		};
	}
	
	public static void open(final Long imageID) {
		List<Long> imageIDs = new ArrayList<Long>();
		imageIDs.add(imageID);
		final LoadingWindow lw = new LoadingWindow("Open Image", "FENIX is retrieving your image from the DB.", "Please Wait...");
		try {
			REServiceEntry.getInstance().getImages(imageIDs, new AsyncCallback<List<String>>() {
				public void onSuccess(List<String> paths) {
					lw.destroyLoadingBox();
					for (String p : paths) {
						ImageWindow w = new ImageWindow(imageID, p);
						w.build();
					}
					lw.destroyLoadingBox();
				}
				public void onFailure(Throwable e) {
					lw.destroyLoadingBox();
					FenixAlert.error("Error", e.getMessage());
					lw.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			lw.destroyLoadingBox();
			FenixAlert.error("Error", e.getMessage());
			lw.destroyLoadingBox();
		}
	}
	
}