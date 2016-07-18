package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;


import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FAOSTATMapsVO implements IsSerializable {

	// <classificationType, custom> <jointype, shaded>
	private LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getValues() {
		return values;
	}

	public void setValues(LinkedHashMap<String, String> values) {
		this.values = values;
	}	

}
