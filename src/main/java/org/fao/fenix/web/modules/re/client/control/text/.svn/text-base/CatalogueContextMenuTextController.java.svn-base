package org.fao.fenix.web.modules.re.client.control.text;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.viewer.TextViewer;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuTextController extends CatalogueContextMenuController {

	 
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(MenuEvent evt) {
				
				//hide the resource explorer
				//REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);
				
				//final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				
				for (ResourceChildModel model: selectedItems){
					openTextViewer(Long.valueOf(model.getId()), model.hasWritePermission());		
				}
					
			    //add to Project Manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.TEXTVIEW);
				
				if (REModel.CLOSE){
					//hide the context menu
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
						
			}
		};
	}

	public static void openTextViewer(final Long id, final boolean isEditable) {
		TextServiceEntry.getInstance().getText(id, new AsyncCallback<List<String>>() {

			public void onSuccess(final List<String> result) {
				//new TextViewer(id, String.valueOf(Fenix.workspaceManager.counter), result).build();
				new TextViewer(id, result).build(isEditable);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.alert("Service call failed", "getText()");
			}
		});
	}

}