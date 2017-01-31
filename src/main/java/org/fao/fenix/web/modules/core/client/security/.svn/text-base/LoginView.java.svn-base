package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;

public class LoginView extends FenixWindow {

	int windowHeight = 180;
	TextField<String> usernameTextField;
	TextField<String> passwordTextField;
	Html text = null;
	Button loginButton;
	FormPanel formPanel;
	VerticalPanel verticalPanel;

	private static LoginView instance = null;

	public LoginView() {
	}

	public static LoginView getInstance() {
		if (instance == null) {
			instance = new LoginView();
			instance.build();
		}
		return instance;
	}

	public void build() {
		buildCenterPanel();
		fillCenterPart(verticalPanel);
		format();
		usernameTextField.setValue("");
		passwordTextField.setValue("");
		this.getWindow().setIconStyle("key");
	}

	protected void format() {
		// this is not working anymore since a verticalPanel is used...
		this.window.setFocusWidget(usernameTextField);
		this.window.setAutoWidth(false);
		setSize(380, windowHeight);
		center.setHeaderVisible(false);

		verticalPanel.setSpacing(10);
		verticalPanel.setVerticalAlign(Style.VerticalAlignment.MIDDLE);
		verticalPanel.setHorizontalAlign(Style.HorizontalAlignment.CENTER);

		formPanel.setFrame(false);
		formPanel.setFieldWidth(180);
		formPanel.setLabelWidth(100);
		formPanel.setLabelAlign(LabelAlign.RIGHT);
		formPanel.setHeaderVisible(false);
		formPanel.setBodyBorder(false);

		usernameTextField.setTabIndex(1);
		usernameTextField.setSelectOnFocus(true);

		passwordTextField.setPassword(true);
		passwordTextField.setTabIndex(2);
		passwordTextField.setSelectOnFocus(true);
		loginButton.setTabIndex(3);
	}

	protected void buildCenterPanel() {
//		setTitle(I18N.print().loginWindowHeading());
		setCollapsible(true);
		getWindow().setHeading(BabelFish.print().loginWindowHeading());

		formPanel = new FormPanel();

		usernameTextField = new TextField<String>();
		usernameTextField.setFieldLabel(BabelFish.print().username());
		usernameTextField.setEmptyText(BabelFish.print().enterUsername());
		formPanel.add(usernameTextField);

		passwordTextField = new TextField<String>();
		passwordTextField.setFieldLabel(BabelFish.print().password());		
		// Commented because for some reason this is not working well
		// passwordTextField.setEmptyText(I18N.print().enterPassword());
		formPanel.add(passwordTextField);

		loginButton = new Button(BabelFish.print().login());

		verticalPanel = new VerticalPanel();
		verticalPanel.add(formPanel);
		verticalPanel.add(loginButton);
	}

	public TextField<String> getPasswordTextField() {
		return passwordTextField;
	}
	
	public Button getLoginButton() {
		return loginButton;
	}

}