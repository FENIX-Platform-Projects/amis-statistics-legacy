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
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class ExportMapAsWmc implements Runnable {
    private MapWindow mw;

	/**
	 * Save the current map.<BR>
	 * If the map is not persisted yet, the metadataeditor will be presented.<BR>
	 * If the map is already on DB and the user can modify it, it will be updated.
	 * <BR><BR>
	 * Same as {@see SaveMap(MapWindow, boolean false)}
	 */
    public ExportMapAsWmc(MapWindow mw) {
        this.mw = mw;
    }


    public void run() {
    	Info.display("Exporting Map", "Sending info to server...");
		ClientMapView cmv = mw.buildClientMapView();
		MapServiceEntry.getInstance().exportAsWmc(cmv, new MapExported());
	}

	private class MapExported implements AsyncCallback<String> {
		public void onSuccess(String result) {
			Info.display("Export Map", "Map exported as WMC. Opening new window....");
			Window window = new Window();
			window.setLayout(new FitLayout());
//			window.setCloseAction(Window.CloseAction.CLOSE);
			window.setSize(600,500);
			window.setHeading("WMC: " + mw.getMapViewTitle());
			
			TextArea textArea = new TextArea();
			window.add(textArea);
			textArea.setValue(result);

			window.show();
		}

		public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error exporting map as WMC:<BR>" + caught.getLocalizedMessage());
		}
	}

}
