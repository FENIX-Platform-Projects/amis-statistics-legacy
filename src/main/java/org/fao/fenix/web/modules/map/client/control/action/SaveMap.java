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

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author etj
 */
public class SaveMap implements Runnable {
    private MapWindow mw;
    private boolean doClone;

	/**
	 * Save the current map.<BR>
	 * If the map is not persisted yet, the metadataeditor will be presented.<BR>
	 * If the map is already on DB and the user can modify it, it will be updated.
	 * <BR><BR>
	 * Same as {@see SaveMap(MapWindow, boolean false)}
	 */
    public SaveMap(MapWindow mw) {
        this(mw, false);
    }

	/**
	 * Save the current map.<BR>
	 * If clone is false, try to create a new map or update the existing one.<BR>
	 * If clone is true, copy the metadata from the existing map and create a new one.<BR>
	 * <BR>
	 * Also see {@see SaveMap(MapWindow)}.
	 */
    public SaveMap(MapWindow mw, boolean clone) {
        this.mw = mw;
        this.doClone = clone;
    }

    public void run() {
		if(! FenixUser.hasUserRole()) {
			FenixAlert.error("Error", "Please log in before saving.");
			return;
		}

		long mapviewid = mw.getMapViewId();
		
        if(mapviewid == -1 ) {
            createAndSave();
        } else {
			if(doClone) {
//				copyAndSave(mw, mapviewid);
				MESaveAs.prepopulateMap(mapviewid, true, false, mw);
			} else {
				checkUpdatability(mapviewid); // async call
			}
        }
    }

	/**
	 * Create an empty metadata, edit it and save all as a new map
	 */
    private void createAndSave() {
		
    	Info.display("Save Map", "Map is brand-new, creating metadata...");
		ClientMapView cmv = mw.buildClientMapView();
		
		MetadataEditorWindow meWindow = new MetadataEditorWindow();
		meWindow.build(false, true, false, MESaver.getSaveMapListener(meWindow, mw));
    }

	/**
	 * Create a metadata from the exisiting one, edit it and save all as a new map
	 */
	private void copyAndSave(MapWindow mapWindow, Long resourceId) {
		ClientMapView cmv = mw.buildClientMapView();

//		MetadataEditorMap mem = new MetadataEditorMap(cmv);
//		mem.setCallback(new MapSaved(mw));
//		mem.clone(mw.getMapViewId());
		// TODO implementation

		Info.display("Clone Map", "METADATA CLONING HAS BEEN REMOVED. Creating data from scratch.");
		MetadataEditorWindow meWindow = new MetadataEditorWindow();
		meWindow.build(false, true, true, MESaver.getSaveMapListener(meWindow, mw));
		
		MESaveAs.prepopulateMap(resourceId, true, true, mapWindow);
	}

	/**
	 * This callback is called by the metadataeditor after everything has been updated on db.
	 */
//	private static class MapSaved implements AsyncCallback<ClientMapView> {
//		private final MapWindow mw;
//
//		private MapSaved(MapWindow mw) {
//			this.mw = mw;
//		}
//
//		public void onSuccess(ClientMapView result) {
//	        Info.display("SaveMap", "SaveMap procedure completed successfully");
//			mw.refresh(result);
//	        Info.display("SaveMap", "Map refreshed");
//		}
//
//		public void onFailure(Throwable caught) {
//	        Info.display("SaveMap Failed", "SaveMap procedure failed");
//		}
//	}


	/**
	 * If the user can update the map, do it, otherwise clone the original map.
	 */
	private void checkUpdatability(long mapId) {
        Info.display("CheckUpdatability", "Checking if you can update this map...");
		UserServiceEntry.getInstance().getPermissions(mapId, new UpdatabilityChecked());
	}

	private class UpdatabilityChecked implements AsyncCallback<PermissionVo> {
		public void onSuccess(PermissionVo mapPermissions) {
			if(mapPermissions.canWrite()) {
				Info.display("Update Map", "Updating map...");
				ClientMapView cmv = mw.buildClientMapView();
				MapServiceEntry.getInstance().updateMap(cmv, new MapUpdated());
			} else
				FenixAlert.error("Error", "You can't update this map. <BR>Please use the 'Save as' button to save your changes.");
		}

		public void onFailure(Throwable caught) {
			FenixAlert.error("Error", "Error checking permissions on map.<BR>"+caught.getLocalizedMessage());
		}
	}

	private class MapUpdated implements AsyncCallback<ClientMapView> {
		public void onSuccess(ClientMapView result) {
			Info.display("UpdateMap", "UpdateMap procedure completed");
			mw.refresh(result);
		}

		public void onFailure(Throwable caught) {
			FenixAlert.error("UpdateMap Failed", "UpdateMap procedure failed:<BR>", caught.getLocalizedMessage());
		}
	}

}
