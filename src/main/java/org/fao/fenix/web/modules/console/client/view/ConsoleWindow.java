package org.fao.fenix.web.modules.console.client.view;

import org.fao.fenix.web.modules.console.client.control.ConsoleController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class ConsoleWindow extends FenixWindow {

	private VerticalPanel consolePanel;
	
	private HTML textbox;
	
	private ListBox logsList;
	
	public void build() {
		consolePanel = new VerticalPanel();
		logsList = new ListBox();
		textbox = new HTML();
		consolePanel.add(createLogsListPanel());
		consolePanel.add(createLogsContentPanel());
		logsList.addChangeListener(ConsoleController.createLogListChangeListener(textbox));
		setCenterProperties();
		getCenter().add(consolePanel);
		addCenterPartToWindow();
		format();
		show();
	}
	
	public void format() {
		consolePanel.setScrollMode(Scroll.ALWAYS);
		setSize("700px", "360px");
		getCenter().setHeaderVisible(false);
		setTitle("Console");
		logsList.setWidth("650px");
		textbox.setWidth("650px");
	}
	
	public HorizontalPanel createLogsListPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		logsList = ConsoleController.createLogsList(logsList);
		panel.add(logsList);
		panel.setSpacing(10);
		return panel;
	}
	
	public HorizontalPanel createLogsContentPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		textbox = new HTML();
		textbox.setSize("200px", "200px");
		panel.add(textbox);
		panel.setSpacing(10);
		return panel;
	}
	
}
