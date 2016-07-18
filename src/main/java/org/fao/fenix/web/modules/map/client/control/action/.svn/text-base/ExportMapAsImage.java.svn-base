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

import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class ExportMapAsImage implements Runnable {
    private MapWindow mw;

	/**
	 * Save the current map.<BR>
	 * If the map is not persisted yet, the metadataeditor will be presented.<BR>
	 * If the map is already on DB and the user can modify it, it will be updated.
	 * <BR><BR>
	 * Same as {@see SaveMap(MapWindow, boolean false)}
	 */
    public ExportMapAsImage(MapWindow mw) {
        this.mw = mw;
    }


    public void run() {
    	Info.display("Exporting Map", "Sending info to server...");
		ClientMapView cmv = mw.buildClientMapView();
		MapServiceEntry.getInstance().exportAsImage(cmv, new MapExported());
	}

	private class MapExported implements AsyncCallback<String> {
		public void onSuccess(String result) {
			Info.display("Export Map", "Map exported as {0}", result);
			Window.open("/fenix-web/export/map/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
		}

		public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error exporting map as image:<BR>" + caught.getLocalizedMessage());
		}
	}

}
