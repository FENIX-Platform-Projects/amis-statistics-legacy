package org.fao.fenix.web.modules.adam.client.view.utils;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class ADAMClientUtils {
	
	public static HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}
	
	public static HorizontalPanel addVSpace(Integer height) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(height);
		return p;
	}

}
