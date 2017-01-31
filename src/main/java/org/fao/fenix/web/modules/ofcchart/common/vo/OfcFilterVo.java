package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OfcFilterVo implements IsSerializable {

	private HashMap<String, String> dimensionMap;
	
	private DimensionsBeanVO dimension;
	
	public OfcFilterVo(){
		this.dimension = new DimensionsBeanVO();
		this.dimensionMap = new HashMap<String, String>();
	} 

	public DimensionsBeanVO getDimension() {
		return dimension;
	}

	public void setDimension(DimensionsBeanVO dimension) {
		this.dimension = dimension;
	}

	public HashMap<String, String> getDimensionMap() {
		return dimensionMap;
	}

	public void setDimensionMap(HashMap<String, String> dimensionMap) {
		this.dimensionMap = dimensionMap;
	}

}