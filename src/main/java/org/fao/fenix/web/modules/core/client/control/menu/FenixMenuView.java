package org.fao.fenix.web.modules.core.client.control.menu;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FenixMenuView {

	HorizontalPanel menuHorizontalPanel;
	public MenuBar leftMenuBar;
	public MenuBar rightMenuBar;
	VerticalPanel leftVerticalPanel;
	VerticalPanel rightVerticalPanel;
	//public HTML loginResponseHtml;

	void build() {
		buildView();
		formatView();
		fillView();
//		RootPanel.get("main-menu").add(leftVerticalPanel);
//		RootPanel.get("secondary-menu").add(rightVerticalPanel);
	}

	private void buildView() {
		leftMenuBar = new MenuBar();
		rightMenuBar = new MenuBar();
		leftVerticalPanel = new VerticalPanel();
		leftVerticalPanel.add(leftMenuBar);
		rightVerticalPanel = new VerticalPanel();
		//loginResponseHtml = new HTML();
		rightVerticalPanel.add(rightMenuBar);
		//rightVerticalPanel.add(loginResponseHtml);
		menuHorizontalPanel = new HorizontalPanel();
		menuHorizontalPanel.add(leftVerticalPanel);
		menuHorizontalPanel.add(rightVerticalPanel);
	};

	private void formatView() {
		menuHorizontalPanel.setWidth("100%");
		rightVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		//loginResponseHtml.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

	}

	private void fillView() {
		// menuHorizontalPanel.show();
	}

}
