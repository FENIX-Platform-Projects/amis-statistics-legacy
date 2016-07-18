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

package org.fao.fenix.web.modules.map.client.control;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.map.client.control.action.AddLabelLayer;
import org.fao.fenix.web.modules.map.client.control.action.JoinDataset;
import org.fao.fenix.web.modules.map.client.control.action.LayerRename;
import org.fao.fenix.web.modules.map.client.control.io.Exporter;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.LayerListControls;
import org.fao.fenix.web.modules.map.client.view.Legend;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.client.view.sldeditor.LayerMetadata;
import org.fao.fenix.web.modules.map.client.view.sldeditor.Styler;

import com.extjs.gxt.ui.client.event.DataListEvent;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.Window;

/**
 *
 * @author etj
 */
public class LayerMenuController {
	
	public static Listener<ListViewEvent> contextMenuShow(final ListView list) {
		return new Listener<ListViewEvent>() {			
			public void handleEvent(ListViewEvent ev) {
				LayerItem layer = (LayerItem) list.getSelectionModel().getSelectedItem();
				
//				Info.display("GEOVIEWID", String.valueOf(layer.getGeoViewId()));
//				Info.display("Type", layer.getClientGeoView().getLayerName() + " | " + layer.getClientGeoView().getLayerType() + " | " + layer.getClientGeoView().getVectorType());
				Menu menu = list.getContextMenu();
				MenuItem mi;

				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_TOGGLE);
				mi.setText(layer.isHidden()? "Show" : "Hide");
				
				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_JOIN);
//				mi.setEnabled(layer.isJoinable()); // && layer.isInternal() && layer.getClientGeoView().isStored());
				mi.setEnabled(true);
				System.out.println("MENU_JOIN joinable:"+layer.isJoinable()+" internal:"+ layer.isInternal() +" stored:" + layer.getClientGeoView().isStored());

				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_SLD);
				mi.setEnabled(layer.getClientGeoView().isStored() && layer.isInternal());

				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_KML);
				mi.setEnabled(layer.isInternal());

				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_LABEL);
				mi.setEnabled(layer.isVect() && layer.isInternal());

//				mi = (MenuItem)menu.getItems().get(LayerListControls.MENU_TABLE);
//				mi.setEnabled(layer.isJoinable);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> toggleLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				if(li.isHidden()){
					li.setIsEnabled("true"); //Info.display("Enabling layer", "Layer '{0}' has been enabled", li.getName());
					li.setIsLayerVisibleIconStyle("images/accept.png");
					li.setIsLayerVisibleLabel("hide");
				}	
				else {
					li.setIsEnabled("false");//Info.display("Disabling layer", "Layer '{0}' has been disabled", li.getName());
					li.setIsLayerVisibleIconStyle("images/decline.png");
					li.setIsLayerVisibleLabel("show");
				}	
				list.getStore().update(li);
				mw.setLayerVisibility(li.getUniqueId(), li.isHidden());
			}
		};
	}

	public static SelectionListener<MenuEvent> removeLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				//Info.display("Removing layer", "Layer '{0}' is being removed...", li.getName());
                mw.removeLayer(li.getUniqueId());
			}
		};
	}

	public static SelectionListener<MenuEvent> renameLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				//Info.display("Renaming layer", "Renaming layer '{0}'...", li.getName());

                new LayerRename(mw, li.getUniqueId(), li.getTitle()).run();
			}
		};
	}

	public static SelectionListener<MenuEvent> zoomToLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				//Info.display("Zoom", "Zooming to layer '{0}'.", li.getName());				
				mw.zoomToLayer(li.getUniqueId());
			}
		};
	}

	public static SelectionListener<MenuEvent> redrawLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				//Info.display("Refresh", "Refreshing layer '{0}'.", li.getName());				
				mw.redrawLayer(li.getUniqueId());
			}
		};
	}
    
	public static SelectionListener<MenuEvent> showWMSMetadata(final  ListView list) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {				
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();			
				new LayerMetadata(li);	
			}
		};
	}

	public static SelectionListener<MenuEvent> showLayerMetadata(final  ListView list) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				ResourceOpener.openMetadata(li.getLayerId(), false);
			}
		};
	}

	public static SelectionListener<MenuEvent> todo(final  ListView list) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				
			//	Info.display("TODO", 
						//	"This feature has not been implemented yet (gv:{0})", 
						//	""+li.getName());
			}
		};
	}
	
	public static SelectionListener<MenuEvent> showStyleEditor(final  ListView list, final MapWindow mw){
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();
				System.out.println("Calling styler with layeritem id:" + li);
				new Styler(li, mw);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> showLegend(final  ListView list){
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {				
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();				
				new Legend(li);				
			}
		};
	}

	public static SelectionListener<MenuEvent> joinDataset(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();		
                new JoinDataset(mw, li.getGeoViewId(), li.getUniqueId()).run();
			}
		};
	}

	public static SelectionListener<MenuEvent> exportKML(final  ListView list) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();		

				String url = li.getWMSUrl();
				url = url.substring(0, url.indexOf("/wms"));
				url = url + "/wms/kml_reflect?layers=" + li.getLayerName();

                Window.open(url, "_blank", "");
			}
		};
	}
	
	public static SelectionListener<MenuEvent> export(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();		
				new Exporter(li, mw);
			}
		};
	}

	public static SelectionListener<MenuEvent> addLabelLayer(final  ListView list, final MapWindow mw) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent me) {
				LayerItem li = (LayerItem) list.getSelectionModel().getSelectedItem();		
			//	Info.display("Adding label layer", "Adding label to '{0}'...", li.getName());

                new AddLabelLayer(mw, li.getUniqueId(), li.getLayerId()).run();
			}
		};
	}
}
