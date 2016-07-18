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

package org.fao.fenix.web.modules.map.client.control.vo;

import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;

import com.extjs.gxt.ui.client.data.BeanModel;

/**
 * A layer item model with some layer related info associated.
 *
 * @author etj
 */
public class LayerItem extends BeanModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientGeoView clientGeoView;
	
	public LayerItem() {
	}

	public LayerItem(String name) {
		setName(name);
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getName() {
		return get("name");
	}
	
	public void setTitle(String title) {
		set("title", title);
	}

	public String getTitle() {
		return get("title");
	}
	
	public void setShortName(String shortname) {
		set("shortname", shortname);
	}

	public String getShortName() {
		return get("shortname");
	}
	
	public void setIconStyle(String iconStyle) {
		set("iconStyle", iconStyle);
	}

	public String getIconStyle() {
		return get("iconStyle");
	}
	
	public void setIconDescription(String iconDesc) {
		set("iconDesc", iconDesc);
	}
	
	public void setIsLayerVisibleIconStyle(String isLayerVisibleIconStyle) {
		set("isLayerVisibleIconStyle", isLayerVisibleIconStyle);
	}
	
	public String getIsLayerVisibleIconStyle() {
		return get("isLayerVisibleIconStyle");
	}
	
	public void setIsLayerVisibleLabel(String isVisibleLayerLabel) {
		set("isVisibleLayerLabel", isVisibleLayerLabel);
	}
	
	public String getIsLayerVisibleLabel() {
		return get("isVisibleLayerLabel");
	}

	public String getIconDescription() {
		return get("iconDesc");
	}
	
	public void setLayerStyle(String layerStyle) {
		set("layerStyle", layerStyle);
	}

	public String getLayerStyle() {
		return get("layerStyle");
	}
	
	public void setLayerDescription(String layerDesc) {
		set("layerDesc", layerDesc);
	}

	public String getLayerDescription() {
		return get("layerDesc");
	}
	
	public String isEnabled() {
		return get("isEnabled");
	}

	public void setIsEnabled(String isEnabled) {
		set("isEnabled", isEnabled);
	}
	
	/**
     * Unique ID of this layer in this client.
     * The GeoViewId will no longer be an identifier for the client, because
     * it may change, for instance when a newly created LayerItem is saved in the map.
     */
	 
    public long getUniqueId() {
        return clientGeoView.getClientId();
    }

  
    public ClientGeoView getClientGeoView() {
        return clientGeoView;
    }

    public void setClientGeoView(ClientGeoView clientGeoView) {
        this.clientGeoView = clientGeoView;
    }

    //--- some wrappers --------------------------------------------------------

    public long getGeoViewId() {
        return clientGeoView.getGeoViewId();
    }

    public String getLayerName() {
        return clientGeoView.getLayerName();
    }

    public long getLayerId() {
        return clientGeoView.getLayerId();
    }
    
    public String getLayerTitle() {
        return clientGeoView.getTitle();
    }

    public String getLayerStyleName() {
        return clientGeoView.getStyleName();
    }

    public void setLayerStyleName(String style) {
        clientGeoView.setStyleName(style);
    }

    public boolean isHidden() {
        return clientGeoView.isHidden();
    }

    public void setHidden(boolean hidden) {
        clientGeoView.setHidden(hidden);
    }

    public String getWMSUrl() {
        return clientGeoView.getGetMapUrl();
    }

    public boolean isJoined() {
        return clientGeoView.isJoined();
    }

    public boolean isJoinable() {
        return clientGeoView.isJoinable();
    }

    public boolean isVect() {
        return clientGeoView.getLayerType() == ClientGeoView.LayerType.VECTOR;
    }
    
    public boolean isRaster() {
        return clientGeoView.getLayerType() == ClientGeoView.LayerType.RASTER;
    }
    
    public boolean isInternal() {
        return clientGeoView.getLayerType() != ClientGeoView.LayerType.EXTERNAL;
    }

	@Override
	public String toString() {
		return "LayerItem["
				+ "gvid:" + getGeoViewId()
				+ " lyid:" + getLayerId()
				+ " lynm:" + getLayerName()
				+ " styl:" + getLayerStyleName()
				+ (isHidden() ? " hidden" : " visible")
				+ (isJoined() ? " joined" : "")
				+ (isJoinable() ? " joinable" : "")
				+ (isVect()   ? " vect" : "")
				+ (isRaster() ? " rst" : "")
				+ (isInternal() ? " internal" : " ext")
				+ "]";
	}
}
