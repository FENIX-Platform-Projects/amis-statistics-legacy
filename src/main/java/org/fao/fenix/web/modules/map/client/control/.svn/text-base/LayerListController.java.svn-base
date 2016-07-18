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

package org.fao.fenix.web.modules.map.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.control.action.RecallJoinedDimensions;
import org.fao.fenix.web.modules.map.client.control.action.UpdateJoinedDimensions;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;
import org.fao.fenix.web.modules.map.client.view.LayerListPanel;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.common.vo.ClientProjectedDataDimension;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

/**
 *
 * @author etj <e_tajariol AT users.sourceforge.net>
 */
public class LayerListController {
    
	public static Listener<SelectionChangedEvent<BeanModel>> selection(final LayerListPanel llp) {

		return new Listener<SelectionChangedEvent<BeanModel>>() {   

			public void handleEvent(SelectionChangedEvent<BeanModel> se) {   

				if(se.getSelection().size() > 0){
					//LayerItem li = (LayerItem)se.getSelectedItem();

					LayerItem li = (LayerItem)se.getSelection().get(0);
					
					if(li!=null){
						// set current transparency
						llp.setLayerOpacity(li.getUniqueId(), li.getClientGeoView().getOpacity()/100f);

						// set dimensions for joined layers
						if(li.isJoined())
							new RecallJoinedDimensions(llp, li.getGeoViewId(), li.getUniqueId()).run();
						else
						{
							llp.buildLayerDimensions(li.getUniqueId(), null);
						}
					}   
				}
			}
		};   
	}
	
	public static SelectionListener<ButtonEvent> updateDimension(final MapWindow mapWindow,
																	final long geoViewId,
																	final long clientLayerId)
	{
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Button btn = ce.getButton();
				
				List<ClientProjectedDataDimension> dims = new ArrayList<ClientProjectedDataDimension>();
				List<ComboBox> cbList = btn.getData("combos");
				for(ComboBox combo : cbList) {
					ClientProjectedDataDimension dim = new ClientProjectedDataDimension();
					dim.name = (String)combo.getData("dimensionName");

					List<PDDModel> pddml = combo.getSelection();
					if(pddml.isEmpty()) { // This is a workaround: empty keys are handled strangely by the combobox
						System.out.println("UpdatingDimensions: WARNING: setting empty dimension for " + combo.getData("dimensionName"));
						dim.currentId = "";
					} else {
						PDDModel pdd = pddml.get(0); // This is the expected behaviour: we should have one and only one item selected
						dim.currentId = pdd.getName();
						if(pdd.getName().equals(ClientProjectedDataDimension.UNCONSTRAINED_CLIENT_VALUE) ) {
							dim.isUnconstrained = true;
							System.out.println("UpdatingDimensions: INFO: " + combo.getData("dimensionName") + " is unconstrained" );
						}
					}
					dims.add(dim);
					// todo only add in map if value has changed
				}
				
				new UpdateJoinedDimensions(mapWindow, geoViewId, clientLayerId, dims).run();
				//Info.display("Click Event", "The '{0}' button was clicked.", btn.getText());  
			}
		};
	}
			
}
