/*
 *   Copyright (C) 2007-2008 Food and Agriculture Organization of the
 *   United Nations (FAO-UN)
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or (at
 *  your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package org.fao.fenix.web.modules.map.client.control.action;


import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.re.client.control.RECallback;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class JoinDataset {
    
    private MapWindow mapWindow;
    private long geoViewId;
	private long uniqueId;

    public JoinDataset(MapWindow mapWindow, long geoViewId, long uniqueId) {
        this.mapWindow = mapWindow;
        this.geoViewId = geoViewId;
		this.uniqueId = uniqueId;
    }
        
	public void run() 
	{
		Info.display("Join", "Joining dataset..");

		// let the user select the dataset s/he wants to project
		ResourceExplorerDataset re = new ResourceExplorerDataset();
        re.setCallback(new DatasetSelected());
	}

    class DatasetSelected implements RECallback
    {
        //@Override
        public void callback(ResourceExplorer re, List<ResourceChildModel> selectedResources) {
            re.getWindow().close();
            if(selectedResources.size() > 1)
                Info.display("Notice", "Too many datasets selected. Will only take the first one...");

            Info.display("Join dataset", "Please wait while joining dataset..");
            long datasetId = Long.parseLong(selectedResources.get(0).getId());
            MapServiceEntry.getInstance().addProjectedData(mapWindow.getMapViewId(),
															geoViewId,
															datasetId,
															new DataProjected());
        }
    }

    class DataProjected implements AsyncCallback<ClientGeoView> {

        public void onSuccess(ClientGeoView cgv) {
    		Info.display("Data projected", "New layer with projected data has been created");
            mapWindow.addLayerAfter(cgv, uniqueId);
        }

        public void onFailure(Throwable arg0) {
			if(arg0 instanceof FenixGWTException)
				FenixAlert.error(BabelFish.print().error(),
									arg0.getMessage());
			else
				FenixAlert.error(BabelFish.print().error(),
									"The data could not be projected: <BR>"+arg0.getLocalizedMessage());
        }
    }
		
}
