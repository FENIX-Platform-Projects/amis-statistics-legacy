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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.action.AddLayer;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Slider;

/**
 *
 * @author etj
 */
public class LayerToolbarController {
	
	public static SelectionListener<IconButtonEvent> addLayer(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new AddLayer(mw).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> layerUp(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                LayerItem li = mw.getSelectedLayer();
                if(li != null) {
                    mw.moveLayerUp(li);
                	mw.getWindow().getLayout().layout();
                }
                else
                	FenixAlert.alert(BabelFish.print().moveLayerUp(), BabelFish.print().pleaseSelectLayerFirst());
			}
		};
	}

	public static SelectionListener<IconButtonEvent> layerDown(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                LayerItem li = mw.getSelectedLayer();
                if(li != null) {
                    mw.moveLayerDown(li);
                mw.getWindow().getLayout().layout();
                }
                else
                	FenixAlert.alert(BabelFish.print().moveLayerDown(), BabelFish.print().pleaseSelectLayerFirst());
			};
		};
	}

	public static SelectionListener<IconButtonEvent> layerToggle(final  ListView list, final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                LayerItem li = mw.getSelectedLayer();
                if(li != null) {
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
    				
                    mw.setLayerVisibility(li.getUniqueId(), li.isHidden());
                    
                    list.getStore().update(li);
       
				}
                else
                	FenixAlert.alert(BabelFish.print().showHideLayer(), BabelFish.print().pleaseSelectLayerFirst());
			};
		};
	}

	public static SelectionListener<ComponentEvent> todo(final MapWindow mw) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent e) {
				//Info.display("TODO", 
					//		"This feature has not been implemented yet (mv:{0})", 
						//	""+mw.getMapWindowId());
			}
		};
	}

	public static SelectionListener<IconButtonEvent> incLayerOpacity(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                LayerItem li = mw.getSelectedLayer();
                if(li != null) {
					if(li.getClientGeoView().incOpacity()) {
//						Info.display("Transparency", "Incrementing layer opacity");
						mw.setLayerOpacity(li.getUniqueId(), li.getClientGeoView().getOpacity());
					}
					//else
					//	FenixAlert.alert(BabelFish.print().incrementLayerOpacity(), BabelFish.print().maxOpacityReached());
				}
                else
                	FenixAlert.alert(BabelFish.print().incrementLayerOpacity(), BabelFish.print().pleaseSelectLayerFirst());
			}
		};
	}

	public static SelectionListener<IconButtonEvent> decLayerOpacity(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                LayerItem li = mw.getSelectedLayer();
                if(li != null) {
					if(li.getClientGeoView().decOpacity()) {
//						Info.display("Transparency", "Decrementing layer Opacity");
						mw.setLayerOpacity(li.getUniqueId(), li.getClientGeoView().getOpacity());
					}
					//else
					//	FenixAlert.alert(BabelFish.print().decrementLayerOpacity(), BabelFish.print().minOpacityReached());
				}
                else
                	FenixAlert.alert(BabelFish.print().decrementLayerOpacity(), BabelFish.print().pleaseSelectLayerFirst());
			}
		};
	}
	
	public static Listener<BaseEvent> changeLayerOpacity(final MapWindow mw, final Slider slider) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
			     LayerItem li = mw.getSelectedLayer();
		           
				 if(li != null) {				 
						double opacityValue = (double)slider.getValue();
						double newOpacity = (opacityValue - 10) / 100;
						if (newOpacity >= 0 && newOpacity <= 100) {
							mw.setLayerOpacity(li.getUniqueId(), (float)newOpacity);
							slider.setMessage("layer opacity set to "+slider.getValue()+"%");
							mw.llp.setLayerOpacity(li.getUniqueId(), (float)newOpacity);
						}
						//else
							//FenixAlert.alert(BabelFish.print().decrementLayerOpacity(), "Opacity limit has been reached");
					}
	                else
	                	FenixAlert.alert(BabelFish.print().decrementLayerOpacity(), BabelFish.print().pleaseSelectLayerFirst());
				 
			};
		};
	}
	

}
