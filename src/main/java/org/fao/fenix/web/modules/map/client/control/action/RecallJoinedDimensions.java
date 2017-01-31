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


import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.LayerListPanel;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class RecallJoinedDimensions {
    
    private LayerListPanel llp;
    private long geoViewId;
    private long clientLayerId;
	
    public RecallJoinedDimensions(LayerListPanel llp, long geoViewId, long clientLayerId) {
        this.llp = llp;
        this.geoViewId = geoViewId;
		this.clientLayerId = clientLayerId;
    }
        
	public void run() 
	{
        MapServiceEntry.getInstance().getProjectedDimensions(geoViewId, new DimensionsGot());
	}
    
    class DimensionsGot implements AsyncCallback<ClientProjectedDataDimension[]> {

        public void onSuccess(ClientProjectedDataDimension[] dims) {
			llp.buildLayerDimensions(clientLayerId, dims);

        }
        
        public void onFailure(Throwable arg0) {
			llp.buildLayerDimensions(clientLayerId, null);
            FenixAlert.error(BabelFish.print().error(),
                                "Error retrieving projection dimensions:<BR>" + arg0.getMessage());
        }    
    }    
}

