package org.fao.fenix.web.modules.r.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

public class RPlotWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "500px";
	
	private static final String WINDOW_HEIGHT = "500px";

	public void build(String windowHeader, String imagePath) {
		Image image = new Image("../R/" + imagePath);
		buildCenterPanel(image);
		getWindow().setHeading(windowHeader);
		format(image);
		show();
	}
	
	private void buildCenterPanel(Image image) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(image);		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format(Image image) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("R");
		getWindow().setCollapsible(false);
	}
	
}