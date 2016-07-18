package org.fao.fenix.web.modules.re.client.control.layer;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuLayerController extends CatalogueContextMenuController  {

	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table){
		return new SelectionListener<MenuEvent>(){
			public void componentSelected(MenuEvent ce){

				//hide the context menu
				REModel.getResourceExplorer().setCatalogueContextMenu(null);

				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().hide();
				REModel.setResourceExplorer(null);

				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();		

				List<Long> layerIds = new ArrayList<Long>();
				for(ResourceChildModel model: selectedItems){
					layerIds.add(Long.valueOf(model.getId()));
				}
				MapServiceEntry.getInstance().getLayers(layerIds, new AsyncCallback<List<ClientGeoView>>() {

					public void onSuccess(List<ClientGeoView> cgvlist) {
						ClientMapView mapView = new ClientMapView();
						mapView.setTitle("New map");
						ClientBBox bbox = null;

						for (ClientGeoView clientGeoView : cgvlist) {
							mapView.addLayer(clientGeoView);

							if(bbox==null)
								bbox = clientGeoView.getBBox();
							else
								bbox = bbox.union(clientGeoView.getBBox());
						}

						System.out.println("Zooming new map to " + bbox);
						mapView.setBbox(bbox);
						new MapWindow(mapView);
						Info.display("Map created", "Opening new map");
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "CatalogueContextMenuLayerController @ open");
					}
				});


				//add to project manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.LAYER);


				Info.display("Click Event", "The '{0}' button was clicked.", "Open");
			}
		};
	}

}
