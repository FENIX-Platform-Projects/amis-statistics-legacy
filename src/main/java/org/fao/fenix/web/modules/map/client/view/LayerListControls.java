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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.LayerMenuController;
import org.fao.fenix.web.modules.map.client.control.LayerToolbarController;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 *
 * @author etj
 */
public class LayerListControls {
    
	public static ToolBar buildToolbar(MapWindow mw, ListView list)
	{
		ToolBar ret = new ToolBar();		
	
		IconButton iconButton;

		iconButton = new IconButton("mapAddLayer");
		iconButton.setToolTip(BabelFish.print().addLayer());
		iconButton.addSelectionListener(LayerToolbarController.addLayer(mw));		
		ret.add(iconButton);

        ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapLayerUp");
		iconButton.setToolTip(BabelFish.print().moveSelectedLayerUp());
		iconButton.addSelectionListener(LayerToolbarController.layerUp(mw));		
		ret.add(iconButton);

        iconButton = new IconButton("mapLayerDown");
		iconButton.setToolTip(BabelFish.print().moveSelectedLayerDown()); 
		iconButton.addSelectionListener(LayerToolbarController.layerDown(mw));		
		ret.add(iconButton);

        ret.add(new SeparatorToolItem());
        
		iconButton = new IconButton("mapLayerToggle");
		iconButton.setToolTip(BabelFish.print().showHideSelectedLayer()); 
		iconButton.addSelectionListener(LayerToolbarController.layerToggle(list, mw));
		ret.add(iconButton);

        ret.add(new SeparatorToolItem());
       // ret.add(new FillToolItem());

       LabelToolItem transp = new LabelToolItem("Control Opacity"); 
	   ret.add(transp);
		
        final Slider slider = new Slider(); 
    	slider.setId("panelListToolbarTransparencySlider");
    	slider.setWidth(75);  
		slider.setIncrement(10);  
		slider.setMaxValue(100);  
		slider.setClickToChange(true);
		slider.setValue(100);
		slider.addListener(Events.Change, LayerToolbarController.changeLayerOpacity(mw, slider));
//		slider.setTitle(BabelFish.print().layerOpacitySlider());
	
		ret.add(slider);
		
		/*iconButton = new IconButton("mapLayerOpacityDec");
		iconButton.setToolTip(BabelFish.print().decrementSelectedLayerOpacity());
		iconButton.addSelectionListener(LayerToolbarController.decLayerOpacity(mw));
		ret.add(iconButton)*/

		//transp = new LabelToolItem("1.0"); // we should store is somewhere
		//transp.setToolTip(BabelFish.print().currentLayerOpacity());
		//transp.setId("panelListToolbarOpacityLabel");
		//ret.add(transp);

		/*iconButton = new IconButton("mapLayerOpacityInc");
		iconButton.setToolTip(BabelFish.print().incrementSelectedLayerOpacity()); 
		iconButton.addSelectionListener(LayerToolbarController.incLayerOpacity(mw));
		ret.add(iconButton);*/

		return ret;
	}

	public final static int MENU_TOGGLE		= 0;
	public final static int MENU_REMOVE		= 1;
	public final static int MENU_RENAME		= 2;
	// sep
	public final static int MENU_JOIN		= 4;
	//public final static int MENU_TABLE		= 5;
	public final static int MENU_METADATA	= 5;
	public final static int MENU_LEGEND		= 6;
	public final static int MENU_PROP		= 7;
	// sep
	public final static int MENU_ZOOM		= 9;
	public final static int MENU_SLD		= 10;
	public final static int MENU_LABEL		= 11;
	// sep
	public final static int MENU_REFRESH	= 13;
	public final static int MENU_KML		= 14;
	public final static int MENU_EXPORT		= 15;

	public static Menu createContextMenu(MapWindow parent, ListView<BeanModel> list)
	{
        Menu contextMenu = new Menu();
        
		MenuItem menuItem;
		list.addListener(Events.ContextMenu, LayerMenuController.contextMenuShow(list));	
//		contextMenu.addListener("beforeshow", new BeforeMenuShow());

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().showHideLayer()); 
		menuItem.setIconStyle ("enable");
		menuItem.addSelectionListener(LayerMenuController.toggleLayer(list, parent));
		contextMenu.add(menuItem);  	

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().removeFromList());
		menuItem.setIconStyle("delete");
		//menuItem.setIconStyle ("icon-delete");
		menuItem.addSelectionListener(LayerMenuController.removeLayer(list, parent)); 
		contextMenu.add(menuItem);  	
		 
		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().rename()); 
		menuItem.setIconStyle ("rename");
		menuItem.addSelectionListener(LayerMenuController.renameLayer(list, parent));
		contextMenu.add(menuItem);  	
				
		contextMenu.add(new SeparatorMenuItem());
		
		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().joinWithDataset()); 
		menuItem.setIconStyle ("mapJoin");
		menuItem.addSelectionListener(LayerMenuController.joinDataset(list, parent)); 
		contextMenu.add(menuItem);  	
		
//		menuItem = new MenuItem();
//		menuItem.setText (".Open attribute table"); // fixme i18n
////		menuItem.setIconStyle ("icon-add");
//		menuItem.addSelectionListener(LayerMenuController.todo(list)); // TODO
//		contextMenu.add(menuItem);
		
		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().viewMetadata()); 
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.showLayerMetadata(list));
		contextMenu.add(menuItem);  	

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().viewLegend()); 
		menuItem.setIconStyle ("mapViewLegend");
		menuItem.addSelectionListener(LayerMenuController.showLegend(list));
		contextMenu.add(menuItem);  	

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().viewLayerProperties());
		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.showWMSMetadata(list));
		contextMenu.add(menuItem);

		contextMenu.add(new SeparatorMenuItem());

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().zoomToLayer()); 
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.zoomToLayer(list, parent));
		contextMenu.add(menuItem);  	
		
		menuItem = new MenuItem();
		menuItem.setText ("Change Style (Rendering)"); 
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.showStyleEditor(list, parent));
//		menuItem.addSelectionListener(LayerMenuController.todo(list)); // TODO
		contextMenu.add(menuItem);  	

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().addLabelsLayer());
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.addLabelLayer(list, parent));
//		menuItem.addSelectionListener(LayerMenuController.todo(list)); // TODO
		contextMenu.add(menuItem);

		contextMenu.add(new SeparatorMenuItem());

		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().refreshLayer());
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.redrawLayer(list, parent));
		contextMenu.add(menuItem);  	
        
		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().exportAsKMZArchive()); 
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.exportKML(list)); 
		contextMenu.add(menuItem);  
		
		menuItem = new MenuItem();
		menuItem.setText (BabelFish.print().exportAsGeotiff()); 
//		menuItem.setIconStyle ("icon-add");
		menuItem.addSelectionListener(LayerMenuController.export(list, parent)); 
		contextMenu.add(menuItem); 
        
        return contextMenu;
	}

}
