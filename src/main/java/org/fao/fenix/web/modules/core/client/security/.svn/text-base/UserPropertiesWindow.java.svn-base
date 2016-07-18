package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class UserPropertiesWindow extends FenixWindow {

	private UserPropertiesPanel userPropertiesPanel;
	
	private static final String WINDOW_WIDTH = "525px";
	
	private static final String WINDOW_HEIGHT = "500px";
	
	public UserPropertiesWindow() {
		userPropertiesPanel = new UserPropertiesPanel();
	}
	
	public void build() {
		buildCenterPanel();
		userPropertiesPanel.getSearchButton().addSelectionListener(UserPropertiesController.searchUser(this));
		userPropertiesPanel.getCloseButton().addSelectionListener(UserPropertiesController.close(this));
		userPropertiesPanel.getUpdateButton().addSelectionListener(UserPropertiesController.update(this));
		userPropertiesPanel.getSearchField().addKeyListener(UserPropertiesController.searchFieldListener(this));
		format();
		UserPropertiesController.find(this);
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setTopComponent(userPropertiesPanel.buildHeaderPanel());
		getCenter().add(userPropertiesPanel.buildUsersPanel());
		getCenter().setBottomComponent(userPropertiesPanel.buildFooterPanel());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("User Properties Manager");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("user_group");
		getWindow().setCollapsible(false);
	}

	public UserPropertiesPanel getUserPropertiesPanel() {
		return userPropertiesPanel;
	}
	
}