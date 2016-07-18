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

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.map.client.view.form.CountrySelectorForm;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class CountrySelector {

	private AsyncCallback<CodeVo> gaulCodeReceptor;
	private static List<CodeVo> cachedGaul0List = null;

	public CountrySelector(AsyncCallback<CodeVo> gaulCodeReceptor) {
		this.gaulCodeReceptor = gaulCodeReceptor;
	}

	public void run() 
	{
		if(cachedGaul0List == null) {
			Info.display("Info", "Retrieving GAUL0 entries...");
			RainfallServiceEntry.getInstance().findAllGaul0(new Gaul0ListReceptor());
		} else {
			Info.display("Info", "Using cached GAUL0 entries");
			new CountrySelectorForm(cachedGaul0List, gaulCodeReceptor).run();
		}
	}

    class Gaul0ListReceptor implements AsyncCallback<List<CodeVo>> {

//		@Override
		public void onSuccess(List<CodeVo> result) {
			cachedGaul0List = result;
			new CountrySelectorForm(result, gaulCodeReceptor).run();
		}

//		@Override
		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Could not retrieve GAUL0 country list");
		}
	}

}
