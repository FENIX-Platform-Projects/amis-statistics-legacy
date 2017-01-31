/*
 *  Copyright (C) 2007-2008 Food and Agriculture Organization of the
 *  United Nations (FAO-UN)
 * 
 * 	This program is free software; you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation; either version 2 of the License, or (at
 * 	your option) any later version.
 * 
 * 	This program is distributed in the hope that it will be useful, but
 * 	WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * 	General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */
package org.fao.fenix.web.modules.map.client.control.action;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.re.client.control.RECallback;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.layer.ResourceExplorerLayer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class AddLayer {
    private MapWindow mw;

    public AddLayer(MapWindow mw) {
        this.mw = mw;
    }

	public void run() 
	{
		//Info.display("Info", "Adding layer to map {0}", "" + mw.getMapWindowId());

		ResourceExplorerLayer re = new ResourceExplorerLayer();
        re.setCallback(new LayersSelected());
	}

    class LayersSelected implements RECallback {

        public void callback(ResourceExplorer re, List<ResourceChildModel> selectedResourceId) {
            re.getWindow().close();

            if(selectedResourceId.isEmpty()) {
            	FenixAlert.alert(BabelFish.print().addLayer(), BabelFish.print().noLayersHaveBeenSelected());
               // Info.display("Notice", "No layers selected.");
                return;
            }

            List<Long> layerIds = new ArrayList<Long>();
            for(ResourceChildModel layer: selectedResourceId) {
                layerIds.add(new Long(layer.getId()));
            }

            MapServiceEntry.getInstance().getLayers(layerIds, new LayersLoaded());
        }
    }

    class LayersLoaded implements AsyncCallback<List<ClientGeoView>> {

        public void onSuccess(List<ClientGeoView> cgvlist) {
            for (ClientGeoView clientGeoView : cgvlist) {
                mw.addLayer(clientGeoView);
                System.out.println("ADDLAYER - LayersLoaded " + clientGeoView.getLayerType() + clientGeoView.getVectorType() + " | " + clientGeoView.getLayerName());
                //Info.display("Layer added","Added layer {0} to map {1}.",clientGeoView.getLayerType() +", " + clientGeoView.getGeoViewId() + " | " + clientGeoView.getTitle(),"" + mw.getMapWindowId());
            }

        }

        public void onFailure(Throwable arg0) {
            //Info.display("ERROR", "Adding layer {0} to map {1} failed", lid, "" + mw.getMapViewId());
            FenixAlert.error(BabelFish.print().error(),
                                arg0.getMessage());
        }
    }
}
