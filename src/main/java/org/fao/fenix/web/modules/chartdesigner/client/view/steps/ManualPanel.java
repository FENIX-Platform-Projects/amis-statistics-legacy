package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class ManualPanel {
	
	private LayoutContainer layoutContainer;
	
	private Html html;
	
	private VerticalPanel wrapper;
	
	private int SPACING = 10; 
	
	private String HEIGHT = "515px";
	
	public ManualPanel() {
		layoutContainer = new LayoutContainer();
		layoutContainer.setBorders(false);
		html = new Html(BabelFish.print().chartDesignerManual());
//		html = new Html("<iframe title='YouTube video player' width='625' height='375' src='http://www.youtube.com/embed/vdx5hFcZels' frameborder='0' allowfullscreen></iframe>");
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SPACING);
	}
	
	public LayoutContainer build() {
		wrapper.add(html);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setHeight(HEIGHT);
		layoutContainer.add(wrapper);
		return layoutContainer;
	}
	
	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}
	
}