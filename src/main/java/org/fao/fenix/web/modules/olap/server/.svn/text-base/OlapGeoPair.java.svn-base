package org.fao.fenix.web.modules.olap.server;

import org.fao.fenix.map.quickmap.iface.GeoPair;

public class OlapGeoPair implements GeoPair {

	private String FID;
	
	private double value;
	
	public OlapGeoPair(String FID, double value) {
		this.setFID(FID);
		this.setValue(value);
	}
	
	/**
	 * Returns the feature code, e.g. GAUL.
	 */
	@Override
	public String getFID() {
		return this.FID;
	}

	/**
	 * Returns the feature code associated value
	 */
	@Override
	public double getValue() {
		return this.value;
	}

	public void setFID(String fid) {
		FID = fid;
	}

	public void setValue(double value) {
		this.value = value;
	}

}