package org.fao.fenix.web.modules.olap.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fao.fenix.map.quickmap.iface.LayerValuesProvider;

public class OlapGeoPairsProvider implements LayerValuesProvider {

	private String layerCode;
	
	private int size;
	
	private List<OlapGeoPair> olapGeoPairs;
	
	public OlapGeoPairsProvider(String layerCode) {
		this.setLayerCode(layerCode);
		this.olapGeoPairs = new ArrayList<OlapGeoPair>();
	}
	
	@Override
	public String getLayerCode() {
		return this.layerCode;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Iterator iterator() {
		return this.olapGeoPairs.iterator();
	}
	
	public void addOlapGeoPair(OlapGeoPair ogp) {
		if (this.olapGeoPairs == null)
			this.olapGeoPairs = new ArrayList<OlapGeoPair>();
		this.olapGeoPairs.add(ogp);
	}

	public void setLayerCode(String layerCode) {
		this.layerCode = layerCode;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setOlapGeoPairs(List<OlapGeoPair> olapGeoPairs) {
		this.olapGeoPairs = olapGeoPairs;
	}

	public List<OlapGeoPair> getOlapGeoPairs() {
		return olapGeoPairs;
	}	

}
