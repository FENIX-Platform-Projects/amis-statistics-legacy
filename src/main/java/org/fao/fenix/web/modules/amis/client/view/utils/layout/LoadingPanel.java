package org.fao.fenix.web.modules.amis.client.view.utils.layout;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class LoadingPanel {


	public HorizontalPanel buildPanel(String title, String width, String height, Boolean showBox) {

		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSize(width, height);

		if ( showBox )
			p.addStyleName("content_box");




		Image i = new Image("amis-images/loading.gif");
		p.add(i);


		return p;
	}

}
