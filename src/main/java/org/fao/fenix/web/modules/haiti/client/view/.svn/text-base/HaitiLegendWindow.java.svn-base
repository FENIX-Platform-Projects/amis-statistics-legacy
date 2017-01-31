package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;

public class HaitiLegendWindow extends FenixWindow {

	private final String WIDTH = "250px";
	
	private final String HEIGHT = "350px";
	
	public void build(String title, String legendHTML) {
		buildCenterPanel(legendHTML);
		getWindow().setBorders(false);
		getWindow().setBodyBorder(false);
		getWindow().setSize(WIDTH, HEIGHT);
		getWindow().setHeading(title);
		show();
	}
	
	private void buildCenterPanel(String legendHTML) {
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.add(new HTML("<img src='" + legendHTML + "' height='90%' width='90%'/>"));
		setCenterProperties();
		getCenter().add(wrapper);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
}
