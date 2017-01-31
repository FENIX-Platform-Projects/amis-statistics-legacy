package org.fao.fenix.web.modules.ipc.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;

public class ConfirmWindow extends FenixWindow {
	
	private ContentPanel contentPanel;
	
	

	public ConfirmWindow(String title, String confirmMessage, SelectionListener<ButtonEvent> okListener) {
		contentPanel = new ContentPanel();
		contentPanel.setScrollMode(Scroll.AUTO);
		contentPanel.setHeaderVisible(false);
		setCenterProperties();
		getCenter().add(buildCenterPanel(confirmMessage, okListener));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWindow().setHeading("<b>" + title + "</b>");
		format();
		show();
	}
	
	private ContentPanel buildCenterPanel(String confirmMessage, SelectionListener<ButtonEvent> okListener) {
		contentPanel.add(buildMessagePanel(confirmMessage));
		buildButtonsPanel(okListener);
		return contentPanel;
	}
	
	
	
	
	private HorizontalPanel buildMessagePanel(String confirmMessage) {
		HorizontalPanel messagePanel = new HorizontalPanel();
		messagePanel.setSpacing(10);
//		HTML message = new HTML("<font color='red'>" + confirmMessage + "</font>");
		HTML message = new HTML(confirmMessage);
		messagePanel.add(message);
		return messagePanel;
	}
	
	private void buildButtonsPanel(SelectionListener<ButtonEvent> okListener) {
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
	
		Button ok = new Button(BabelFish.print().yes(), okListener);
		ok.setWidth(40);
		ok.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});

		Button cancel = new Button(BabelFish.print().no(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		cancel.setWidth(40);
		contentPanel.addButton(ok);
		contentPanel.addButton(cancel);
//		buttonsPanel.add(ok);
//		buttonsPanel.add(cancel);
	}
	
	private void format() {
		getWindow().setSize("400px", "150px");
		getWindow().setModal(true);
	}
	
	
}
