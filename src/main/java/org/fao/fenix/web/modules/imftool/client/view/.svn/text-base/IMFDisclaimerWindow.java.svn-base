package org.fao.fenix.web.modules.imftool.client.view;

import org.fao.fenix.web.modules.imftool.client.control.IMFToolController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class IMFDisclaimerWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "170px";
	
	private static final String BUTTON_WIDTH = "145px";
	
	private static final String WINDOW_HEIGHT = "210px";

	public void build(String windowTitle, String message, boolean addCloseButton) {
		buildCenterPanel(message, addCloseButton);
		format(windowTitle);
		show();
		getWindow().setPagePosition(0, 0);
	}
	
	private void buildCenterPanel(String message, boolean addCloseButton) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildDisclaimerPanel(message, addCloseButton));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildDisclaimerPanel(String message, boolean addCloseButton) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		Html text = new Html("<div style='font-family: sans-serif; color: #1D317E; font-size: 7.5pt;'>" + message + "</div>");
		p.add(text);
		if (addCloseButton) {
			Button b = new Button(BabelFish.print().close());
			b.addSelectionListener(IMFToolController.close(this));
			b.setWidth(BUTTON_WIDTH);
			p.add(b);
		}
		return p;
	}
	
	private void format(String windowTitle) {
		getWindow().setMinWidth(0);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(windowTitle);
		getWindow().setResizable(false);
		getWindow().setIconStyle("info");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
		getWindow().setDraggable(false);
	}
	
}
