/*
 */

package org.fao.fenix.web.modules.map.client.control.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 *
 * @author ETj
 */
public class CGVModelData implements ModelData {

	private ClientGeoView cgv;

	private static List<String> propNames;

	static {
		propNames = new ArrayList<String>();
		propNames.add("title");
		propNames.add("gvid");
		propNames.add("cgv");
	}

	public CGVModelData(ClientGeoView cgv) {
		this.cgv = cgv;
	}

	public <X> X get(String property) {
		if("title".equals(property))
			return (X)cgv.getTitle();
		else if("gvid".equals(property))
			return (X) ((Long)cgv.getGeoViewId());
		else if("cgv".equals(property))
			return (X) (cgv);

		throw new IllegalArgumentException("Unknown property '"+property+"'");
	}
	
	public Map<String, Object> getProperties() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("title", cgv.getTitle());
		ret.put("gvid", cgv.getGeoViewId());
		ret.put("cgv", cgv);
		return ret;
	}
	
	public Collection<String> getPropertyNames() {
		return propNames;
	}
	
	public <X> X remove(String property) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public <X> X set(String property, X value) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
