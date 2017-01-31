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


import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class CountryZoomer {
    private MapWindow mw;
	private CodeVo gaulcodevo;

    public CountryZoomer(MapWindow mw) {
        this.mw = mw;
    }

	public void run() 
	{
		CountrySelector countrySelector = new CountrySelector(new CountrySelected());
		countrySelector.run();
	}

	class CountrySelected implements AsyncCallback<CodeVo> {

//		@Override
		public void onSuccess(CodeVo gaulCodeVo) {
			CountryZoomer.this.gaulcodevo = gaulCodeVo;
			MapServiceEntry.getInstance().getCountryExtents(gaulCodeVo.getCode(), new BBoxGot());
		}

//		@Override
		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Unexpected error @ CountrySelected");
		}

	}

	class BBoxGot implements AsyncCallback<ClientBBox> {

//		@Override
		public void onSuccess(ClientBBox bbox) {
			Info.display("Zooming", "Zooming on {0}", gaulcodevo.getLabel());
			mw.zoomToBBox(bbox);
		}

//		@Override
		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Could not retrieve bounding box for {0}: <BR>{1}",
					gaulcodevo.getLabel(), caught.getMessage());
		}
	}
}
