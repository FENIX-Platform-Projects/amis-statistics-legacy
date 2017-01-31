package org.fao.fenix.web.modules.re.client.control.layer;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.designer.client.view.DesignerLayerSelectorWindow;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.layer.CatalogueContextMenuLayer;
import org.fao.fenix.web.modules.re.client.view.layer.ResourceExplorerLayer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueLayerController extends CatalogueController {

	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {

				onDoubleClickAction(resourceExplorer);

			}
		};
	}

	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer) {

		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

		if (resourceExplorer.getCallback() != null) {

			resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
			// resourceExplorer.getWindow().close();
		}

		else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {

			ResourceChildModel model = selectedItems.get(0);
			
			DesignerBox designerBox = ((ResourceExplorerLayer) resourceExplorer).getDesignerBox();
			designerBox.setResourceID(Long.valueOf(model.getId()));
			designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.LAYER);
			designerBox.setResourceTitle(model.getName());
			designerBox.getResourceName().setValue(model.getName());
			
			
			DesignerLayerSelectorWindow w = new DesignerLayerSelectorWindow(Long.valueOf(model.getId()), model.getName());
			w.build();
			resourceExplorer.getWindow().close();			

		} else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {

			List<Long> layerIds = new ArrayList<Long>();
			for (ResourceChildModel model : selectedItems) {
				layerIds.add(Long.valueOf(model.getId()));
			}

			MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {

				public void onSuccess(List<ClientGeoView> cgvlist) {
					ClientMapView mapView = new ClientMapView();
					mapView.setTitle("New map");
					ClientBBox bbox = null;

					for (ClientGeoView clientGeoView : cgvlist) {
						mapView.addLayer(clientGeoView);

						if (bbox == null)
							bbox = clientGeoView.getBBox();
						else
							bbox = bbox.union(clientGeoView.getBBox());
					}

					System.out.println("Zooming new map to " + bbox);
					mapView.setBbox(bbox);
					new MapWindow(mapView);
					Info.display("Map created", "Opening new map");

					// add to Project Manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.LAYER);

					if (REModel.CLOSE)
						resourceExplorer.getWindow().close();
				}

				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failed", "CatalogueLayerController @ onDoubleClick");
				}
			});
		}

	}

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();

				if (item != null) {
					grid.setContextMenu(new CatalogueContextMenuLayer(resourceExplorer, catalogue).buildMenu());
				} else {
					System.out.println("############# CatalogueLayerController GRID: setContextMenu selectedItem is NULL");
				}
			}
		};
	}

}
