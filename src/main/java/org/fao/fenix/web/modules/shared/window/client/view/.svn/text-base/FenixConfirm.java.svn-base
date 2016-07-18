package org.fao.fenix.web.modules.shared.window.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;

public class FenixConfirm extends FenixWindow {

	public FenixConfirm(String confirmMessage, SelectionListener<ButtonEvent> okListener) {
		setCenterProperties();
		getCenter().add(buildCenterPanel(confirmMessage, okListener));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWindow().setHeading(BabelFish.print().confirmYourChoice());
		format();
		show();
	}
	
	private VerticalPanel buildCenterPanel(String confirmMessage, SelectionListener<ButtonEvent> okListener) {
		VerticalPanel centerPanel = new VerticalPanel();
		centerPanel.add(buildMessagePanel(confirmMessage));
		centerPanel.add(buildButtonsPanel(okListener));
		return centerPanel;
	}
	
	private HorizontalPanel buildMessagePanel(String confirmMessage) {
		HorizontalPanel messagePanel = new HorizontalPanel();
		messagePanel.setSpacing(10);
		HTML message = new HTML("<font color='red'>" + confirmMessage + "</font>");
		messagePanel.add(message);
		return messagePanel;
	}
	
	private HorizontalPanel buildButtonsPanel(SelectionListener<ButtonEvent> okListener) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		Button ok = new Button(BabelFish.print().ok(), okListener);
		Button cancel = new Button(BabelFish.print().close(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);
		return buttonsPanel;
	}
	
	private void format() {
		getWindow().setSize("200px", "150px");
		getWindow().setModal(true);
	}
	
}
