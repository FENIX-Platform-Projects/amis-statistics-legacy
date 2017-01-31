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

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;

/**
 *
 * @author etj
 */
public class LayerRename {
    
    private MapWindow mapWindow;
    private long unqId;
	private String oldTitle;

    public LayerRename(MapWindow mapWindow, long unqId, String oldTitle) {
        this.mapWindow = mapWindow;
        this.unqId = unqId;
		this.oldTitle = oldTitle;
    }
        
	public void run() 
	{
		final MessageBox box = MessageBox.prompt("Rename Layer", "Please enter the layer new name:");
		box.getTextBox().setValue(oldTitle);
		box.addCallback(new ListenerImpl());
	}

	private class ListenerImpl implements Listener<MessageBoxEvent> {

		public void handleEvent(MessageBoxEvent be) {
			if(be.getButtonClicked().getItemId().equals(Dialog.OK)) {
				Info.display("Renaming layer", "New title '{0}'", be.getValue());
				mapWindow.renameLayer(unqId, be.getValue());
			} else {
				Info.display("Layer renaming aborted", "Layer '{0}'", oldTitle);
			}
		}
	}
		
}
