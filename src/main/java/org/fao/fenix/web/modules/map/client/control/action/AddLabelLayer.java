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



import org.fao.fenix.web.modules.map.client.control.action.util.LayerFieldSelector;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class AddLabelLayer {
    
    private MapWindow mapWindow;
    private long uniqueId;
    private long layerId;
	private StyleDefinition styleDefinition;

    public AddLabelLayer(MapWindow mapWindow, long uniqueId, long layerId) {
        this.mapWindow = mapWindow;
        this.uniqueId  = uniqueId;
        this.layerId   = layerId;
    }
        
	public void run() 
	{
		Info.display("AddLabelLayer", "Retrieving layer fields list...");

		LayerFieldSelector selector = new LayerFieldSelector(layerId, new FieldSelected());
		selector.run();
	}


	class FieldSelected implements AsyncCallback<StyleDefinition> {

		public void onSuccess(StyleDefinition styleDefinition) {
			AddLabelLayer.this.styleDefinition = styleDefinition;
			System.out.println(styleDefinition.getFieldName());
			System.out.println(styleDefinition.getColor());
			
			MapServiceEntry.getInstance().createLabelStyle(layerId, styleDefinition, new StyleCreated());
		}

		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Unexpected error @ CountrySelected");
		}

	}

	class StyleCreated implements AsyncCallback<String> {

		public void onSuccess(String stylename) {
			Info.display("Adding labels", "Adding labels layer");

			LayerItem li = mapWindow.getLayer(uniqueId);
			ClientGeoView cgv = li.getClientGeoView().copy();
			cgv.setTitle("Labels: " + styleDefinition.getFieldName() + "@" + cgv.getTitle());
			cgv.setStyleName(stylename);
			
			System.out.println("adding label to layer: " + uniqueId + " | " + stylename + " | " + cgv.getLayerName());
			mapWindow.addLayerAfter(cgv, uniqueId);
		}

		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", 
					"Could not create label style: <BR>{0}", caught.getMessage());
		}
	}

}
