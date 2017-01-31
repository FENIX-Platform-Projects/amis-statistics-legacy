package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class FAOSTATLoadingPanel {
	
		
	public HorizontalPanel buildWaitingPanel(String title, String width, String height, Boolean showBox) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSize(width, height);
		
		if ( showBox )
			p.addStyleName("content_box");
//		p.setSpacing(5);
		
		

		
		Image i = new Image("dataviewer-images/loading.gif");
//		i.setSize("35px", "17px");
		
//		right.add(i);
		
//		p.add(left);
		p.add(i);
	
		
		return p;
	}

}
