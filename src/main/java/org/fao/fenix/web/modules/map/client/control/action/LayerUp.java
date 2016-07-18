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

import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.MapWindow;


/**
 *
 * @author etj
 */
public class LayerUp {

    private MapWindow mapWindow;
    private LayerItem li;

    public LayerUp(MapWindow mapWindow, LayerItem li) {
        this.mapWindow = mapWindow;
        this.li = li;
    }
        
	public void run() 
	{
		//Info.display("Info", "Moving up layer '"+li.getText()+"'");
        mapWindow.moveLayerUp(li);
	}
}
