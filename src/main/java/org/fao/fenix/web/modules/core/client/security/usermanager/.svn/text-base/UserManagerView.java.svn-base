package org.fao.fenix.web.modules.core.client.security.usermanager;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.google.gwt.user.client.ui.HTML;

public class UserManagerView extends FenixWindow {

	Button deleteUserButton;
	HTML text = null;
	DataList dataList;
	HTML usersFoundHtml = null;
	HTML westErrorMessage;
	VerticalPanel westVerticalPanel;

	VerticalPanel centerVerticalPanel;
	HorizontalPanel centerHorizontalSaveCancelButtonPanel;
	ButtonBar centerButtonBar;
	Button addUserButton;
	FormPanel centerFormPanel;
	TextField<String> userNameTextField;
	TextField<String> passwordTextField;
	TextField<String> firstNameTextField;
	TextField<String> lastNameTextField;

	CheckBoxGroup roleCheckBoxGroup;
	CheckBox roleAdmin;
	CheckBox roleFpi;
	CheckBox roleCcbs;
	CheckBox roleIpc;
	HTML errorMessage;
	Button saveUserButton;
	Button cancelButton;

	private static UserManagerView instance = null;

	protected UserManagerView() {
	}

	public static UserManagerView getInstance() {
		if (instance == null) {
			instance = new UserManagerView();
			instance.build();
		}
		return instance;
	}

	public void build() {
		formatWindow();
		fillWindow();
		buildWestPanel();
		buildCenterPanel();
		fillCenterPanel();
		formatWestPanel();
		formatCenterPanel();
	}

	private void formatWindow() {
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setWidth(650);
		getWindow().setHeight(350);
	}

	private void fillWindow() {
		getWindow().setHeading("User Manager");
	}

	private void buildCenterPanel() {
		addUserButton = new Button(BabelFish.print().addUser());
		centerButtonBar = new ButtonBar();
		centerButtonBar.add(addUserButton);

		userNameTextField = new TextField<String>();
		passwordTextField = new TextField<String>();
		firstNameTextField = new TextField<String>();
		lastNameTextField = new TextField<String>();

		roleCheckBoxGroup = new CheckBoxGroup();
		roleAdmin = new CheckBox();
		roleFpi = new CheckBox();
		roleCcbs = new CheckBox();
		roleIpc = new CheckBox();
		roleCheckBoxGroup.add(roleAdmin);
		roleCheckBoxGroup.add(roleCcbs);
		roleCheckBoxGroup.add(roleFpi);
		roleCheckBoxGroup.add(roleIpc);

		centerFormPanel = new FormPanel();
		centerFormPanel.add(userNameTextField);
		centerFormPanel.add(passwordTextField);
		centerFormPanel.add(firstNameTextField);
		centerFormPanel.add(lastNameTextField);
		centerFormPanel.add(roleCheckBoxGroup);

		cancelButton = new Button();
		saveUserButton = new Button();
		centerHorizontalSaveCancelButtonPanel = new HorizontalPanel();
		centerHorizontalSaveCancelButtonPanel.add(saveUserButton);
		centerHorizontalSaveCancelButtonPanel.add(cancelButton);

		errorMessage = new HTML();

		centerVerticalPanel = new VerticalPanel();
		centerVerticalPanel.add(centerButtonBar);
		centerVerticalPanel.add(centerFormPanel);
		centerVerticalPanel.add(errorMessage);
		centerVerticalPanel.add(centerHorizontalSaveCancelButtonPanel);

		fillCenterPart(centerVerticalPanel);
	}

	private void fillCenterPanel() {
		userNameTextField.setFieldLabel(BabelFish.print().username());
		passwordTextField.setFieldLabel(BabelFish.print().password());
		firstNameTextField.setFieldLabel(BabelFish.print().firstName());
		lastNameTextField.setFieldLabel(BabelFish.print().lastName());
		roleCheckBoxGroup.setFieldLabel(BabelFish.print().role());
		roleAdmin.setBoxLabel(BabelFish.print().hasRoleAdmin());
		roleFpi.setBoxLabel(BabelFish.print().hasRoleFpi());
		roleCcbs.setBoxLabel(BabelFish.print().hasRoleCcbs());
		roleIpc.setBoxLabel(BabelFish.print().hasRoleIpc());
		saveUserButton.setText(BabelFish.print().save());
		cancelButton.setText(BabelFish.print().cancel());
	}

	private void buildWestPanel() {
		centerButtonBar = new ButtonBar();
		deleteUserButton = new Button(BabelFish.print().deleteUser());
		centerButtonBar.add(deleteUserButton);
		text = new HTML();
		usersFoundHtml = new HTML();
		westErrorMessage = new HTML();
		dataList = new DataList();
		westVerticalPanel = new VerticalPanel();
		westVerticalPanel.add(centerButtonBar);
		westVerticalPanel.add(text);
		westVerticalPanel.add(dataList);
		westVerticalPanel.add(usersFoundHtml);
		westVerticalPanel.add(westErrorMessage);
		westVerticalPanel.setScrollMode(Scroll.AUTO);
		fillWestPart(westVerticalPanel);
	}

	private void formatWestPanel() {
		west.setHeaderVisible(false);
		west.setBorders(false);
		west.setBodyBorder(false);
		west.setFrame(false);
		westVerticalPanel.setSpacing(10);
		dataList.setBorders(false);
		dataList.setWidth(190);
		dataList.setScrollMode(Scroll.NONE);
	}

	private void formatCenterPanel() {
		center.setHeaderVisible(false);
		center.setBorders(false);
		center.setBodyBorder(false);
		center.setFrame(false);

		centerFormPanel.setFrame(false);
		centerFormPanel.setFieldWidth(180);
		centerFormPanel.setLabelWidth(100);
		centerFormPanel.setLabelAlign(LabelAlign.LEFT);
		centerFormPanel.setHeaderVisible(false);
		centerFormPanel.setBodyBorder(false);
		centerFormPanel.setScrollMode(Scroll.NONE);

		userNameTextField.setTabIndex(1);
		userNameTextField.setSelectOnFocus(true);

		passwordTextField.setPassword(true);
		passwordTextField.setTabIndex(2);
		passwordTextField.setSelectOnFocus(true);

		firstNameTextField.setTabIndex(3);
		firstNameTextField.setSelectOnFocus(true);

		lastNameTextField.setTabIndex(4);
		lastNameTextField.setSelectOnFocus(true);

		roleCheckBoxGroup.setAutoWidth(true);
		roleCheckBoxGroup.setTabIndex(5);

		saveUserButton.setTabIndex(6);
		cancelButton.setTabIndex(7);

		centerHorizontalSaveCancelButtonPanel.setSpacing(10);

		centerVerticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		centerVerticalPanel.setSpacing(10);
	}

}