package org.fao.fenix.web.modules.re.client.control.image;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.image.CatalogueContextMenuImage;
import org.fao.fenix.web.modules.re.client.view.image.ResourceExplorerImage;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;

public class CatalogueImageController extends CatalogueController {

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				if (item != null) {
					grid.setContextMenu(new CatalogueContextMenuImage(resourceExplorer, catalogue).buildMenu());
				} else {
					GWT.log("CatalogueOlapController GRID: setContextMenu selectedItem is NULL", new Throwable());
				}
			}
		};
	}

	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				onDoubleClickAction(resourceExplorer);
			}
		};
	}

	@SuppressWarnings("deprecation")
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer) {
		final List<ResourceChildModel> imageList = new ArrayList<ResourceChildModel>();
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		if (resourceExplorer.getCallback() != null) {
			resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
		} else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
			CatalogueContextMenuImageController.open(Long.valueOf(model.getId()));
			imageList.add(model);
//			PMModel.addResourceToProjectManager(olapList, ResourceType.OLAP);
			if (REModel.CLOSE)
				resourceExplorer.getWindow().close();
		} 
		
		else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {
//			 DesignerBox designerBox = ((ResourceExplorerImage)resourceExplorer).getDesignerBox();
//			 designerBox.setResourceID(Long.valueOf(model.getId()));
//			 designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.IMAGE);
//			 designerBox.setResourceTitle(model.getName());
//			 designerBox.getResourceName().setValue(model.getName());
//			 resourceExplorer.getWindow().close();
			 String tinyMCEPanelID = ((ResourceExplorerImage)resourceExplorer).getTinyMCEPanelID();
			 TinyMCEController.loadImage(tinyMCEPanelID, Long.valueOf(model.getId()));
			 resourceExplorer.getWindow().close();
		 }
		
		resourceExplorer.getWindow().close();
	}
	
}
