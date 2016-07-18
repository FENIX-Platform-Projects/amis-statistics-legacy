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


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.LayerListController;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;



/**
 * This panel contains the list of the layers associated to the current map
 * and the related controls to handle them.
 *
 * @author etj
 */
public class LayerListPanel {

	private final ContentPanel mainPanel = new ContentPanel();
	private final ContentPanel listPanel = new ContentPanel();
	private final DimensionPanel dimensionPanel;
	private final ToolBar toolbar;
	
	final private ListView<BeanModel> list;  
	final private Menu contextMenu;

//	private final ContentPanel dummyDimPanel = new ContentPanel();
	private final MapWindow parent;
	
	public LayerListPanel(MapWindow parent)
	{				        
		this.parent = parent;
		dimensionPanel = new DimensionPanel(parent);
		
		// LISTPANEL
		listPanel.setLayout(new RowLayout());
		listPanel.setBodyBorder(false);
		listPanel.setHeading(BabelFish.print().layerList());
		listPanel.setScrollMode(Scroll.AUTO);
		listPanel.setIconStyle("mapLayers");
	
		
		
		
	    ListStore<BeanModel> store = new ListStore<BeanModel>();   
		
		 
		list = new ListView<BeanModel>() {   
		      @Override  
		      protected BeanModel prepareData(BeanModel model) {   
		        String s = model.get("title");   
		        model.set("shortName", Format.ellipse(s, 40));  
		        return model;   
		      }   
		  
		};   
		
		list.setTemplate(getTemplate());   
	    list.setStore(store);   
	    list.setItemSelector("div.layer-details");   

		list.setBorders(false); 
		list.setWidth(250);    
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		list.getSelectionModel().addListener(Events.SelectionChange,  LayerListController.selection(this));
		contextMenu = LayerListControls.createContextMenu(parent, list);  
		
		// add context menu
		list.setContextMenu(contextMenu);
		
		listPanel.add(list);    
	
		
		toolbar = LayerListControls.buildToolbar(parent, list);
		listPanel.setTopComponent(toolbar);
		
		// MAIN PANEL
		mainPanel.setLayout(new AccordionLayout()); 
		mainPanel.setHeaderVisible(false);
		mainPanel.add(listPanel);
		mainPanel.add(dimensionPanel.getGUI());

//		dimensionPanel.getGUI().addListener(Events.AfterLayout, new Listener() {
//			public void handleEvent(BaseEvent be) {
//				Info.display("AfterLayout", "DIMENSIONAPANEL ");
//			}
//		});
//		dimensionPanel.getGUI().addListener(Events.Activate, new Listener() {
//			public void handleEvent(BaseEvent be) {
//				Info.display("Activate", "DIMENSIONAPANEL ");
//			}
//		});
//		dimensionPanel.getGUI().addListener(Events.Collapse, new Listener() {
//			public void handleEvent(BaseEvent be) {
//				Info.display("Collapse", "DIMENSIONAPANEL ");
//			}
//		});
//		dimensionPanel.getGUI().addListener(Events.Enable, new Listener() {
//			public void handleEvent(BaseEvent be) {
//				Info.display("Enable", "DIMENSIONAPANEL ");
//			}
//		});
//		dimensionPanel.getGUI().addListener(Events.Expand, new Listener() {
//			public void handleEvent(BaseEvent be) {
//				Info.display("Expand", "DIMENSIONAPANEL ");
//			}
//		});


//		listPanel.setAnimCollapse(true); // test
//		dimensionPanel.getGUI().setAnimCollapse(true); // test
		
//		Listener l = new Listener() {
//
//			public void handleEvent(BaseEvent be) {
//				System.out.println("BaseEvent "  + be + " ["+be.type+"]");
//				// be.doit = false;
//			}
//		};
//
//		dimensionPanel.getGUI().addListener(Events.BeforeExpand, l);
//		dimensionPanel.getGUI().addListener(Events.BeforeSelect, l);
		
	}
    
	public ContentPanel getGUI()
	{
		return mainPanel;
	}
    
    public LayerItem getSelectedLayer()
    {
        return (LayerItem)list.getSelectionModel().getSelectedItem();
    }
	
	public void addLayers(ClientMapView cmv)
	{
		for(ClientGeoView cgv : cmv.getLayerList())	{
			addLayer(cgv);
		}					
	}	
			
	/**
	 * Put into the ClientMapView the set of data extracted at build time,
	 * with their current values.
	 */
	void collectCMVData(ClientMapView cmv) {
		for(BeanModel dli: list.getStore().getModels()) {
		//for(BeanModel dli: list.getSelectionModel().getSelectedItems()) {
			LayerItem li = (LayerItem)dli;
			cmv.addLayer(li.getClientGeoView()); // TODO: check better all attribs are set properly
		}
	}

	public int getLayersCount() {
		return list.getStore().getCount();
	}

	public LayerItem getLayerByIndex(int index) {
		return (LayerItem)list.getStore().getAt(index);
	}

	public void setLayerVisibility(long gvid, boolean visible)
	{
		LayerItem li = getLayerItem(gvid);
		if(li!=null) {
			li.setIsEnabled(Boolean.toString(visible));
			//list.getTemplate().overwrite(list.getElement(), Util.getJsObject(li));
			li.setHidden(!visible);
		}
	}

	public void setLayerOpacity(long uniqueId, float opacity) {
		Slider label = (Slider)toolbar.getItemByItemId("panelListToolbarOpacityLabel");
		double percent =  (double)opacity /1.0 * 100;
		if(label!=null)
			label.setMessage("layer opacity set to "+label.getValue()+"%");
	}
	
	public void setLayerOpacityO(long uniqueId, float opacity) {
		LabelToolItem label = (LabelToolItem)toolbar.getItemByItemId("panelListToolbarOpacityLabel");
		label.setLabel(""+opacity);
	}
	
	public LayerItem getLayerItem(long uniqid)
	{
		for(BeanModel dli: list.getStore().getModels()) {
			LayerItem li = (LayerItem)dli;
			if(li.getUniqueId() == uniqid) {
				return li;
			}
		}

   		//Info.display("Alert", "Layer not found '"+uniqid+"'");
		return null;
	}

	private int getLayerIndex(long uniqid)
	{
        for(int i=0; i<list.getItemCount(); i++)
            if( ((LayerItem)list.getStore().getAt(i)).getUniqueId() == uniqid)
                return i;

   		//Info.display("Alert", "Layer not found '"+uniqid+"'");
        return -1;
	}

	private int getLayerIndexByGV(long gvid)
	{
        for(int i=0; i<list.getItemCount(); i++)
            if( ((LayerItem)list.getStore().getAt(i)).getGeoViewId() == gvid)
                return i;

   		//Info.display("Alert", "Layer not found gv:'"+gvid+"'");
        return -1;
	}

	public void renameLayer(long unqId, String newTitle) {
		LayerItem li = getLayerItem(unqId);
		if(li != null) {
			li.setName(newTitle);
			li.getClientGeoView().setTitle(newTitle);
		}
	}

	public void addLayer(ClientGeoView cgv)
    {
        LayerItem li = buildLayer(cgv);
        // TODO: set position if unset?
//        li.addSelectionListener(LayerItemController.selected(list));
        
        list.getStore().add(li);
        
    }

	/**
	 * @return the layer's position
	 */
	public int addLayerAfter(ClientGeoView cgv, long uniqId)
    {
        LayerItem li = buildLayer(cgv);
		int idx = getLayerIndex(uniqId);
		
		list.getStore().insert(li, idx+1);
        
		return idx+1;
    }

	private static LayerItem buildLayer(ClientGeoView cgv)
	{
		LayerItem item = new LayerItem();
		boolean isJoined = false;
		boolean isJoinable = false;

		item.setName(cgv.getLayerName());   // name = layername  
		item.setTitle(cgv.getTitle());       
		item.setIsEnabled( Boolean.toString(! cgv.isHidden()));
		item.setClientGeoView(cgv);
	
		if(! cgv.isHidden()){
			item.setIsLayerVisibleIconStyle("images/accept.png");
			item.setIsLayerVisibleLabel("hide");
		} else {
			item.setIsLayerVisibleIconStyle("images/decline.png");
			item.setIsLayerVisibleLabel("show");
		}
		
		// ///////////////
		// Set the layer details
		// ///////////////

		if(cgv.isJoined()) {
			isJoined = true;
		}
		else if(cgv.isJoinable()) {
			isJoinable = true;
		}     

		if(cgv.getLayerType() == ClientGeoView.LayerType.EXTERNAL){
			item.setLayerStyle("map-images/map.png"); 
			item.setLayerDescription(BabelFish.print().externalLayerType());

			setIconStyle(item, isJoined, isJoinable);

		}	
		else if(cgv.getLayerType() == ClientGeoView.LayerType.UNDEF){
			item.setLayerStyle("map-images/map.png");
			item.setLayerDescription(BabelFish.print().undefinedLayerType());

			setIconStyle(item, isJoined, isJoinable);
		}	
		else if(cgv.getLayerType() == ClientGeoView.LayerType.RASTER){
			item.setLayerStyle("map-images/raster.png");
			item.setLayerDescription(BabelFish.print().rasterLayer());

			setIconStyle(item, isJoined, isJoinable);
		} 
		else if(cgv.getLayerType() == ClientGeoView.LayerType.VECTOR){
			item.setLayerStyle("map-images/vector.png");
			item.setLayerDescription(BabelFish.print().vectorLayer());

			if(cgv.getVectorType() == ClientGeoView.VectorType.POLY){
				//item.setLayerStyle("map-images/vector.png");
				//item.setLayerDescription(BabelFish.print().vectorPolygonLayer());

				setIconStyle(item, isJoined, isJoinable);
			}	
			else if(cgv.getVectorType() == ClientGeoView.VectorType.LINE){
				//item.setLayerStyle("map-images/line.png"); 
				//item.setLayerDescription(BabelFish.print().vectorLineLayer());

				setIconStyle(item, isJoined, isJoinable);
			}	
			else if(cgv.getVectorType() == ClientGeoView.VectorType.POINT) {
				//item.setLayerStyle("map-images/point.png");
				//item.setLayerDescription(BabelFish.print().vectorPointLayer());

				setIconStyle(item, isJoined, isJoinable);
			} 
		}
        
        return item;
        
//        Draggable d = new Draggable(item);
//        d.setConstrainHorizontal(true); 
//		  list.add(item);  
				
//		Text label1 = new Text(cgv.getTitle());  
//		label1.setStyleAttribute("margin", "4px");  
//		label1.addStyleName("layerTitle");  
//		label1.setBorders(true);  
//		vp.add(label1, new RowData(1, -1));	
	}

    private static LayerItem buildLayer(LayerItem src)
	{
		LayerItem item = new LayerItem();  
       
		item.setName(src.getName());     // name = layername
		item.setTitle(src.getTitle());       
		item.setIsEnabled( src.isEnabled());
		item.setClientGeoView(src.getClientGeoView());
		item.setLayerStyle(src.getLayerStyle());
		item.setLayerDescription(src.getLayerDescription());
		
		if(!src.isHidden()){
			item.setIsLayerVisibleIconStyle("map-images/accept.png");
			item.setIsLayerVisibleLabel("hide");
		} else {
			item.setIsLayerVisibleIconStyle("map-images/decline.png");
			item.setIsLayerVisibleLabel("show");
		}
		
		if(src.getIconStyle()!=null)
		item.setIconStyle(src.getIconStyle());   
		
		if(src.getIconDescription()!=null)
			item.setIconDescription(src.getIconDescription());

        return item;
	}
    
    public void removeLayer(long uniqId) {
        LayerItem li = getLayerItem(uniqId);
        list.getStore().remove(li);        
    }

    public void moveLayerUp(LayerItem li) {        
        int position = getLayerIndex(li.getUniqueId());
        List liList = new ArrayList();
        
        if(position==-1) {
        	FenixAlert.alert(BabelFish.print().movingLayerUp(), BabelFish.print().layerNotFound() + " '"+li.getTitle()+"'");
    	   return;
        }
        if(position==0) {
        	FenixAlert.alert(BabelFish.print().movingLayerUp(), BabelFish.print().layerAtTopList());
            return;
        }
         
           
        LayerItem newli = buildLayer(li);
        liList.add(newli);
        list.getStore().remove(li);
        
        list.getStore().insert(newli, position-1);      
        list.getSelectionModel().setSelection(liList); 		

   }

    public void moveLayerDown(LayerItem li) {        
        int position = getLayerIndex(li.getUniqueId());
        List liList = new ArrayList();
        if(position==-1) {
        	FenixAlert.alert(BabelFish.print().movingLayerDown(), BabelFish.print().layerNotFound() +" '"+li.getTitle()+"'");
            return;
        }
        if(position==list.getItemCount()-1) {
        	FenixAlert.alert(BabelFish.print().movingLayerDown(), BabelFish.print().layerAtBottomList());
            return;
        }
                
        LayerItem newli = buildLayer(li);
        liList.add(newli);
        list.getStore().remove(li);
         
        list.getStore().insert(newli, position+1);
        list.getSelectionModel().setSelection(liList); 
        
    }

	public void buildLayerDimensions(long uniqId, ClientProjectedDataDimension[] dims) {
		if(dims != null) {
			LayerItem li = getLayerItem(uniqId);
			String title = li.getName();
			dimensionPanel.buildLayerDimensions(getLayerItem(uniqId).getGeoViewId(), uniqId, title, dims);
		}
		else {
			dimensionPanel.reset();
			// System.out.println("RESET");
		}
	}
	
	private static void setIconStyle(LayerItem item, boolean isJoined, boolean isJoinable) {
		
		if(isJoined) {
			item.setIconStyle("map-images/joint.png"); 
			item.setIconDescription(BabelFish.print().joinedWithDataset());
		}
		else if(isJoinable) {
			item.setIconStyle("map-images/join_dataset.png");
			item.setIconDescription(BabelFish.print().canBeJoinedWithDataset());
		}  
		else {
			item.setIconStyle("");
			item.setIconDescription("");
		}
	}
	
	
	 private native String getTemplate() /*-{  
     return ['<tpl for=".">',  
     '<div class="layer-details" id="{title}" >',
     '<span  title="{title}">',
	 '<tpl if="isLayerVisibleIconStyle !=\'\'"> <img src="{isLayerVisibleIconStyle}" title="Click on Show/Hide toolbar icon to {isVisibleLayerLabel} this layer"/> </tpl>',
     '<img src="{layerStyle}" title="{layerDesc}"/> ',
     '<tpl if="iconStyle !=\'\'"> <img src="{iconStyle}" title="{iconDesc}"/> </tpl>',
	 '{shortName}</tpl>',
     '</span></div>',
     '</tpl>',  
     '<div class="x-clear"></div>'].join("");  
       
     }-*/;   
	 

}

