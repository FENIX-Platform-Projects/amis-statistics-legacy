/*
 */

package org.fao.fenix.web.modules.map.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author etj
 */
public class ClientBBox implements IsSerializable {
		
	private String srs;
	private double minlat;
	private double maxlat;
	private double minlon;
	private double maxlon;

	public ClientBBox() {
	}

	public ClientBBox(String srs, double minlat, double maxlat, double minlon, double maxlon) {
		this.srs = srs;
		this.minlat = minlat;
		this.maxlat = maxlat;
		this.minlon = minlon;
		this.maxlon = maxlon;
	}
	
	public double getMaxLat() {
		return maxlat;
	}

	public void setMaxLat(double maxlat) {
		this.maxlat = maxlat;
	}

	public double getMaxLon() {
		return maxlon;
	}

	public void setMaxLon(double maxlon) {
		this.maxlon = maxlon;
	}

	public double getMinLat() {
		return minlat;
	}

	public void setMinLat(double minlat) {
		this.minlat = minlat;
	}

	public double getMinLon() {
		return minlon;
	}

	public void setMinLon(double minlon) {
		this.minlon = minlon;
	}

	public String getSrs() {
		return srs;
	}

	public void setSrs(String srs) {
		this.srs = srs;
	}		
	
    public ClientBBox union(ClientBBox other) {
        ClientBBox ret = new ClientBBox();
        ret.setMinLat(Math.min(minlat, other.getMinLat()));
        ret.setMaxLat(Math.max(maxlat, other.getMaxLat()));
        ret.setMinLon(Math.min(minlon, other.getMinLon()));
        ret.setMaxLon(Math.max(maxlon, other.getMaxLon()));
        ret.setSrs(srs); // FIXME: what if SRS are different?
        return ret;
    }
    
	public String toString() {
		return "BBOX[" 
			+ "'"+srs+"', x0:"
			+ minlon + ", y0:"
			+ minlat + ", x1:"
			+ maxlon + ", y1:"
			+ maxlat 
			+ "]";
	}
}
