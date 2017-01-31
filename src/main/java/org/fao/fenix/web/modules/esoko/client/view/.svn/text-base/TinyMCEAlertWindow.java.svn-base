package org.fao.fenix.web.modules.esoko.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class TinyMCEAlertWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "400px";
	
	private static final String WINDOW_HEIGHT = "275px";
	
	public TinyMCEAlertWindow() {
		
	}
	
	public void build(String info, SelectionListener<ButtonEvent> listener) {
		buildCenterPanel(info, listener);
		format();
		show();
	}
	
	private void buildCenterPanel(String info, SelectionListener<ButtonEvent> listener) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildInfo(info));
		getCenter().setBottomComponent(buildButtonsPanel(listener));
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private HorizontalPanel buildButtonsPanel(SelectionListener<ButtonEvent> listener) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		Button ok = new Button("OK");
		ok.addSelectionListener(listener);
		Button ko = new Button("Cancel");
		ko.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getWindow().hide();
			}
		});
		p.add(ok);
		p.add(ko);
		return p;
	}
	
	private HorizontalPanel buildInfo(String info) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		Html html = new Html(info);
		p.add(html);
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(BabelFish.print().info());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("info");
		getWindow().setCollapsible(false);
	}
	
}