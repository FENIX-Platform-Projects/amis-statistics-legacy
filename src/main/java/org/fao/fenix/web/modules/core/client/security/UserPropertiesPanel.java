package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class UserPropertiesPanel {

	private VerticalPanel usersPanel;
	
	private HorizontalPanel headerPanel;
	
	private HorizontalPanel footerPanel;
	
	private Button closeButton;
	
	private Button updateButton;
	
	private Button searchButton;
	
	private TextField<String> searchField;
	
	private static final String USER_PANEL_WIDTH = "490px";
	
	private static final String USERS_PANEL_HEIGHT = "405px";
	
	private static final String USERS_PANEL_WIDTH = "460px";
	
	private static final String LABEL_WIDTH = "100px";
	
	private static final String FIELD_WIDTH = "125px";
	
	public UserPropertiesPanel() {
		closeButton = new Button("Close");
		updateButton = new Button("Apply Changes");
		updateButton.setWidth("450px");
		searchButton = new Button("Search");
		searchField = new TextField<String>();
		searchField.setEmptyText("e.g. John");
	}
	
	public VerticalPanel buildUsersPanel() {
		usersPanel = new VerticalPanel();
		usersPanel.setSize(USERS_PANEL_WIDTH, USERS_PANEL_HEIGHT);
		usersPanel.setScrollMode(Scroll.AUTO);
		return usersPanel;
	}
	
	public void addUserPanel(FenixUserVo vo) {
		usersPanel.add(buildUserPanel(vo));
		usersPanel.getLayout().layout();
	}
	
	private VerticalPanel buildUserPanel(FenixUserVo vo) {
		VerticalPanel userPanel = new VerticalPanel();
		userPanel.setWidth(USER_PANEL_WIDTH);
		HorizontalPanel firstLastNamePanel = buildFirstLastNamePanel(vo);
		HorizontalPanel usernameEmailPanel = buildUsernameEmailPanel(vo);
		HorizontalPanel langPanel = buildLangPanel(vo);
		userPanel.add(firstLastNamePanel);
		userPanel.add(usernameEmailPanel);
		userPanel.add(langPanel);
		userPanel.add(buildLinePanel());
		userPanel.setData("FIRST_LAST_NAME_PANEL", firstLastNamePanel);
		userPanel.setData("USERNAME_EMAIL_PANEL", usernameEmailPanel);
		userPanel.setData("LANG_PANEL", langPanel);
		return userPanel;
	}
	
	private HorizontalPanel buildFirstLastNamePanel(FenixUserVo vo) {
		HorizontalPanel firstLastNamePanel = new HorizontalPanel();
		firstLastNamePanel.setSpacing(5);
		Html firstNameLabel = new Html("<b>First Name</b>");
		firstNameLabel.setWidth(LABEL_WIDTH);
		firstLastNamePanel.add(firstNameLabel);
		TextField<String> firstNameField = new TextField<String>();
		firstNameField.setWidth(FIELD_WIDTH);
		firstNameField.setValue(vo.getFirstName());
		firstLastNamePanel.add(firstNameField);
		firstLastNamePanel.setData("FIRST_NAME", firstNameField);
		Html lastNameLabel = new Html("<b>Last Name</b>");
		lastNameLabel.setWidth(LABEL_WIDTH);
		firstLastNamePanel.add(lastNameLabel);
		TextField<String> lastNameField = new TextField<String>();
		lastNameField.setValue(vo.getLastName());
		lastNameField.setWidth(FIELD_WIDTH);
		firstLastNamePanel.add(lastNameField);
		firstLastNamePanel.setData("LAST_NAME", lastNameField);
		firstLastNamePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		return firstLastNamePanel;
	}
	
	private HorizontalPanel buildUsernameEmailPanel(FenixUserVo vo) {
		HorizontalPanel usernameEmailPanel = new HorizontalPanel();
		usernameEmailPanel.setSpacing(5);
		Html usernameLabel = new Html("<b><u>Username</u></b>");
		usernameLabel.setWidth(LABEL_WIDTH);
		usernameEmailPanel.add(usernameLabel);
		TextField<String> usernameField = new TextField<String>();
		usernameField.setValue(vo.getLoginName());
		usernameField.setWidth(FIELD_WIDTH);
		usernameField.setEnabled(false);
		usernameEmailPanel.add(usernameField);
		usernameEmailPanel.setData("USERNAME", usernameField);
		Html emailLabel = new Html("<b>E-Mail</b>");
		emailLabel.setWidth(LABEL_WIDTH);
		usernameEmailPanel.add(emailLabel);
		TextField<String> emailField = new TextField<String>();
		emailField.setValue(vo.getEmail());
		emailField.setWidth(FIELD_WIDTH);
		usernameEmailPanel.add(emailField);
		usernameEmailPanel.setData("EMAIL", emailField);
		usernameEmailPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		return usernameEmailPanel;
	}
	
	private HorizontalPanel buildLinePanel() {
		HorizontalPanel linePanel = new HorizontalPanel();
		linePanel.setSpacing(5);
		Html lineLabel = new Html("<hr/>");
		lineLabel.setWidth(USERS_PANEL_WIDTH);
		linePanel.add(lineLabel);
		return linePanel;
	}
	
	private HorizontalPanel buildLangPanel(FenixUserVo vo) {
		HorizontalPanel langPanel = new HorizontalPanel();
		langPanel.setSpacing(5);
		Html langLabel = new Html("<b>Language</b>");
		langLabel.setWidth(LABEL_WIDTH);
		langPanel.add(langLabel);
		ListStore<GaulModelData> langStore;
		ComboBox<GaulModelData> langList;
		langStore = new ListStore<GaulModelData>();
		langList = new ComboBox<GaulModelData>();
		langList.setTriggerAction(TriggerAction.ALL);
		langList.setStore(langStore);
		langList.setDisplayField("gaulLabel");
		UserPropertiesController.fillLangStore(langStore);
		UserPropertiesController.selectLang(vo, langList);
		langList.setWidth(FIELD_WIDTH);
		langPanel.add(langList);
		langPanel.setData("LANG", langList);
		langPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		return langPanel;
	}
	
	public HorizontalPanel buildHeaderPanel() {
		headerPanel = new HorizontalPanel();
		headerPanel.setSpacing(5);
		headerPanel.add(new Html("<b>Search: </b>"));
		searchField.setWidth("400px");
		headerPanel.add(searchField);
		headerPanel.add(searchButton);
		headerPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		return headerPanel;
	}
	
	public HorizontalPanel buildFooterPanel() {
		footerPanel = new HorizontalPanel();
		footerPanel.setSpacing(5);
		footerPanel.add(updateButton);
		footerPanel.add(closeButton);
		return footerPanel;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public TextField<String> getSearchField() {
		return searchField;
	}

	public VerticalPanel getUsersPanel() {
		return usersPanel;
	}
		
}