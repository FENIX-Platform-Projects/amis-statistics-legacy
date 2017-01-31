package org.fao.fenix.web.modules.chartdesigner.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

public class ChartDesignerManualWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "500px";
	
	private static final String WINDOW_HEIGHT = "550px";
	
	private static final String WRAPPER_WIDTH = "450px";
	
	private static final String WRAPPER_HEIGHT = "525px";
	
	private Html html;
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new CardLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildWrapper());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTOY);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildWrapper() {
		VerticalPanel p = new VerticalPanel();
		p.setSize(WRAPPER_WIDTH, WRAPPER_HEIGHT);
		p.setSpacing(10);
		html = new Html(BabelFish.print().chartDesignerManual());
		html.setWidth(WRAPPER_WIDTH);
		p.add(html);
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("FENIX Chart Designer Manual");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("info");
	}
	
}