package org.fao.fenix.web.modules.olap.common.vo;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OLAPFilterVo implements IsSerializable {

	private String dimension;

	private String dimensionLabel;

	private Map<String, String> dimensionMap;

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getDimensionLabel() {
		return dimensionLabel;
	}

	public void setDimensionLabel(String dimensionLabel) {
		this.dimensionLabel = dimensionLabel;
	}

	public Map<String, String> getDimensionMap() {
		return dimensionMap;
	}

	public void setDimensionMap(Map<String, String> dimensionMap) {
		this.dimensionMap = dimensionMap;
	}
	
	@Override
	public String toString() {
		String out = "\tDimension: " + this.getDimension() + "\n" +
					 "\tDimension Label: " + this.getDimensionLabel() + "\n";
		for (String c : this.getDimensionMap().keySet())
			out += "\t\tDimension Map: " + c + " > " + this.getDimensionMap().get(c) + "\n";
		return out;
	}

}