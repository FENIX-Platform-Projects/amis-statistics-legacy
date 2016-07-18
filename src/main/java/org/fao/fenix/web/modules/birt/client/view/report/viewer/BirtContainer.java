package org.fao.fenix.web.modules.birt.client.view.report.viewer;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.Element;

public class BirtContainer extends ContentPanel{
	
		
	public BirtContainer(){
		setBodyBorder(false);
		setHeaderVisible(false);
		setScrollMode(Scroll.AUTO);
		setAutoHeight(true);
		setAutoWidth(true);
		
	}
	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		//setSize(200, 325);
	}

}
