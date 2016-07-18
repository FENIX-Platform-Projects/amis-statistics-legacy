package org.fao.fenix.web.modules.amis.client.view.utils.layout;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class FormattingUtils {

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
