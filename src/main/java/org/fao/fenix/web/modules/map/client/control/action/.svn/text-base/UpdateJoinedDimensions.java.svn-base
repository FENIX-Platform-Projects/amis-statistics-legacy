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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class UpdateJoinedDimensions {
    
    private MapWindow mapWindow;
    private long geoViewId;
	private long clientLayerId;
	private List<ClientProjectedDataDimension> dims;

	public UpdateJoinedDimensions(MapWindow mapWindow, long geoViewId, long clientLayerId, List<ClientProjectedDataDimension> dims) {
		this.mapWindow = mapWindow;
		this.geoViewId = geoViewId;
		this.clientLayerId = clientLayerId;
		this.dims = dims;
	}
	
	public void run() {
        MapServiceEntry.getInstance().setProjectedDimensions(geoViewId, dims, new DimensionsSet());
	}
    
    class DimensionsSet implements AsyncCallback<Void> {

        public void onSuccess(Void _) {
			Info.display("Info", "Dimensions set. Refreshing layer...");
			mapWindow.redrawLayer(clientLayerId);
        }
        
        public void onFailure(Throwable exc) {
			Info.display("Warning", exc.getMessage());

            FenixAlert.error(BabelFish.print().error(),
                                "Error setting projection dimensions:<BR>{0}<BR>The original dimensions set has been kept.", exc.getMessage());
        }    
    }    
}

