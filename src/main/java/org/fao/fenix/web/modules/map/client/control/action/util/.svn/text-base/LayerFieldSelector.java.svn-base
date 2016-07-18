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

package org.fao.fenix.web.modules.map.client.control.action.util;

import java.util.List;

import org.fao.fenix.web.modules.map.client.view.form.StringSelectorForm;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.StyleDefinition;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class LayerFieldSelector {

	private final AsyncCallback<StyleDefinition> fieldNameReceptor;
	private final Long layerId;

	public LayerFieldSelector(Long layerid, AsyncCallback<StyleDefinition> fieldNameReceptor) {
		this.fieldNameReceptor = fieldNameReceptor;
		this.layerId = layerid;
	}

	public void run() 
	{
		Info.display("Info", "Retrieving GAUL0 entries...");
		MapServiceEntry.getInstance().getLayerFieldnames(layerId, new NamesListReceptor());
	}

    class NamesListReceptor implements AsyncCallback<List<String>> {

//		@Override
		public void onSuccess(List<String> result) {
			new StringSelectorForm(result, fieldNameReceptor).run();
		}

//		@Override
		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Could not retrieve fields names.");
		}
	}

}
