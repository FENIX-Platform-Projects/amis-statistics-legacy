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

package org.fao.fenix.web.modules.map.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.Counter;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * MapWindow is split on two main panels: <UL>
 * <LI> LayerListPanel: the panel on the left, holds the widgets to control the layers; </LI>
 * <LI> MapPanel: the center panel, contains the real map (OL+JS). </LI>
 * <br><br>
 * 
 * MapWindow forwards every command about layers to the two subpanel, 
 * so that each of them can update the info it handles.
 * 
 * @author etj
 */
public class MapWindow extends FenixWindow {

	public LayerListPanel llp; // TODO set me priv
	private MapPanel mapPanel; 
		
	private long mapViewId = -1;
	private String mapViewTitle;

	private static Counter mwCounter = new Counter(100);
	private final long mapWindowId;

	private static final MapWindowManager mapWindowManager = new MapWindowManager();
	public static MapWindow getMapWindow(long mapWindowId) {
		return mapWindowManager.getMapWindow(mapWindowId);
	}

	protected MapWindow(String title) {
		mapWindowId = mwCounter.getNext();
		mapWindowManager.addWindow(this);
		mapViewTitle = title;
		buildGUI(title);

		window.setWindowMovedListener(new Runnable() {
			public void run() {
				mapPanel.windowMoved();
			}
		});
	}

	public MapWindow(String mapid, String name){
		this(name);
		if(mapid != null)
			mapViewId = Long.parseLong(mapid);
		buildLayerList(mapid);
	}

	public MapWindow(ClientMapView cmv) {
		this(cmv.getTitle());
		if(cmv.getId() != null)
			mapViewId = Long.parseLong(cmv.getId());
		setMap(cmv);  
    }

    private void buildGUI(String title) {
    
		setTitle(title);
		
		setSize(750, 420);
		setCollapsible(true);
		setMaximizable(true);
							
		mapPanel = new MapPanel(this);		
		fillCenterPart(mapPanel.getGUI());
		getCenter().setHeaderVisible(false);
		llp = new LayerListPanel(this);
    
		fillWestPart(llp.getGUI());
		getWest().setHeading(BabelFish.print().layers());	
		
		getCenter().setTopComponent(mapPanel.getToolbar());
		
//		SocialBar sb = new SocialBar();
//		getCenter().setBottomComponent(sb.getSocialBar(ResourceType.MAP, String.valueOf(mapViewId)));
		
		show();    
    }
    
	private void buildLayerList(String mapid)
	{
		 MapServiceEntry.getInstance().getMap(Long.parseLong(mapid), new LayerListBuilder());
	}
	
	class LayerListBuilder implements AsyncCallback<ClientMapView>
	{
		//@Override
		public void onSuccess(ClientMapView cmv) {
            setMap(cmv);
		}
		
		//@Override
		public void onFailure(Throwable arg0) {
			throw new UnsupportedOperationException(BabelFish.print().notSupportedYet());
		}			
	}
    
    private void setMap(ClientMapView cmv)
    {
		llp.addLayers(cmv);
		mapPanel.createMap(cmv);    
    }
    
    public LayerItem getSelectedLayer()
    {
        return llp.getSelectedLayer();
    }

    public LayerItem getLayer(long uniqid)
    {
        return llp.getLayerItem(uniqid);
    }

	public void renameLayer(long unqId, String newTitle) {
		llp.renameLayer(unqId, newTitle);
	}
    
    public void addLayer(ClientGeoView cgv)
    {
		llp.addLayer(cgv);
		mapPanel.addLayer(cgv);
    }

	public void addLayerAfter(ClientGeoView cgv, long uniqId) {
		int idx = llp.addLayerAfter(cgv, uniqId);
		mapPanel.addLayer(cgv, idx);
	}

    public void removeLayer(long gvId)
    {
		llp.removeLayer(gvId);
		mapPanel.removeLayer(gvId);   
    }

    public void moveLayerUp(LayerItem li)
    {
        llp.moveLayerUp(li);
        mapPanel.moveLayerUp(li.getUniqueId());
    }

    public void moveLayerDown(LayerItem li)
    {
        llp.moveLayerDown(li);
        mapPanel.moveLayerDown(li.getUniqueId());
    }
    
	public void setLayerVisibility(long gvid, boolean visible)
	{
		llp.setLayerVisibility(gvid, visible);
		mapPanel.setLayerVisibility(gvid, visible);		
	}

	public void setLayerOpacity(long uniqueId, int opacity) {
		llp.setLayerOpacity(uniqueId, opacity/100f);
		mapPanel.setLayerOpacity(uniqueId, opacity/100f);
	}
	
	public void setLayerOpacity(long uniqueId, float opacity) {
		llp.setLayerOpacity(uniqueId, opacity);
		mapPanel.setLayerOpacity(uniqueId, opacity);
	}
	
	public void zoomToLayer(long gvid)
	{
		mapPanel.zoomToLayer(gvid);
	}

	public void zoomToBBox(ClientBBox bbox)
	{
		mapPanel.zoomToBBox(bbox);
	}

	public void redrawLayer(long gvid)
	{
		mapPanel.redrawLayer(gvid);
	}

	public long getMapWindowId() {
		return mapWindowId;
	}

	public long getMapViewId() {
		return mapViewId;
	}
	
	public String getMapViewTitle() {
		return mapViewTitle;
	}

	public ClientMapView buildClientMapView() {
		ClientMapView cmv = new ClientMapView();
		cmv.setTitle(this.mapViewTitle);
		if(getMapViewId() != -1)
			cmv.setId(""+getMapViewId());

		mapPanel.collectCMVData(cmv);
		llp.collectCMVData(cmv);
		return cmv;
	}

	/**
	 * This method is called after a save() has been performed.
	 * @param newcmv the ClientMapView as just read from the db
	 */
	public void refresh(ClientMapView cmv) {
		System.out.println("refresh...");
		
		setTitle(cmv.getTitle());

		boolean afterSaveAs = false;

		if(getMapViewId() == -1) // it's a new map, remember its code
			mapViewId = Long.parseLong(cmv.getId());
		else if (getMapViewId() != Long.parseLong(cmv.getId())) { // map refreshing after a "save as..."
			//Info.display("Save as...", "Replacing data in current mapview...");
			mapViewId = Long.parseLong(cmv.getId());
			afterSaveAs = true;
		}

		int lc = llp.getLayersCount();
		System.out.println("lc: " + lc + " | " + cmv.getLayerList().size());
		if(lc != cmv.getLayerList().size()) {
			FenixAlert.error(BabelFish.print().error(), "Layer list count mismatch<BR>(old: {0}"+lc+", new: {1}"+cmv.getLayerList().size()+")",
					""+lc, ""+cmv.getLayerList().size());
			// TODO do something!!!! dunno, load current layer list, or whatever
			return;
		}

		// we assume the layer lists are ordered the same way
		for (int i = 0; i < lc; i++) {
			ClientGeoView gvold = llp.getLayerByIndex(i).getClientGeoView();
			ClientGeoView gvnew = cmv.getLayerList().get(i);
			// sanity checks
			System.out.println("getLayerId (new) (old): " + gvnew.getLayerId() + " | " + gvold.getLayerId());
			System.out.println("check (new) (old): " + gvnew.getGeoViewId() + " | " + gvold.getGeoViewId());
			System.out.println("check (new) (old): " + gvnew.getTitle() + " | " + gvold.getTitle());
			System.out.println("getLayerType (new) (old): " + gvnew.getLayerType() + " | " + gvold.getLayerType());
			
			/** TODO: SKIP THAT TEST...DANGEROUS?! **/
//			if(gvold.getLayerId() != gvnew.getLayerId()) { 
//				 pls add some other checks
//					FenixAlert.error("Error", 
//							"Layer code "+i+" is not synch'ed <BR>" +
//							"(old='"+gvold.getLayerId()+"' new='"+gvnew.getLayerId()+"')");
//					continue;
//			}

//			if(gvold.getPosition().intValue() != gvnew.getPosition().intValue()) { // pls add some other checks
//					FenixAlert.error("Error",
//							"Layer position "+i+" is not synch'ed<BR>" +
//							"(old='"+gvold.getPosition()+"' new='"+gvnew.getPosition()+"')");
//					continue;
//			}

			// set the GV ids
			if(afterSaveAs) {
				System.out.println("afterSaveAs replacing with new geoview: " + gvnew.getGeoViewId() + " | " + gvold.getGeoViewId());
				System.out.println("afterSaveAs replacing with new geoview: " + gvnew.getTitle() + " | " + gvold.getTitle());
				// save as: GVs are completely replaced
				gvold.setGeoViewId(gvnew.getGeoViewId());
			}
			else {
				// if it is already stored, check the code, otherwise set the code
				if(gvold.isStored()) {
					if(gvold.getGeoViewId() != gvnew.getGeoViewId()) {
						FenixAlert.error(BabelFish.print().error(),
								"GV code "+i+" is not synch'ed<BR>" +
								"(old='"+gvold.getGeoViewId()+"' new='"+gvnew.getGeoViewId()+"')");
						continue;
					}
				}
				else {
					System.out.println("replacing with new geoview: " + gvnew.getGeoViewId() + " | " + gvold.getGeoViewId());
					System.out.println("replacing with new geoview: " + gvnew.getTitle() + " | " + gvold.getTitle());
					gvold.setGeoViewId(gvnew.getGeoViewId());
				}
			}
		}
		
		/// this should be here?? where is actually saved the map?
		FenixAlert.info("Save", "The Map has been saved");
	}

	//==========================================================================

	/**
	 * Needed for retrieving a MapWindow through its code.
	 */
	protected static class MapWindowManager {
		private final Map<Long, MapWindow> allMapWindows = new HashMap<Long, MapWindow>();
		protected void addWindow(MapWindow mw) {
			allMapWindows.put(mw.getMapWindowId(), mw);
			mw.getWindow().addWindowListener(new WListener(mw.getMapWindowId()));
		}
		protected void removeWindow(MapWindow mw) {
			allMapWindows.remove(mw.getMapWindowId());
		}
		public MapWindow getMapWindow(long mapWindowId) {
			return allMapWindows.get(mapWindowId);
		}

		class WListener extends WindowListener {
			private final long mwid;

			public WListener(long id) {
				mwid = id;
			}

			public void windowClose(WindowEvent we) {
				allMapWindows.remove(mwid);
				we.getWindow().removeWindowListener(this);
			}
		}
	}

}
