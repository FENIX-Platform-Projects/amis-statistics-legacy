package org.fao.fenix.web.modules.cpf.client.view.login;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.cpf.client.control.login.CPFLoginController;
import org.fao.fenix.web.modules.cpf.client.lang.CPFLanguage;
import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;
import org.fao.fenix.web.modules.cpf.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class CPFLoginPanel {
	
	CPFMainMenu CPFMainMenu;
	
	HorizontalPanel holder;
	ContentPanel panel;
	
	private TextField<String> username;
	private TextField<String> password;
	
	ClickHtml login;
	
	// Dimensions
	
	String panelWidth = "300px";
	String widthText = "70px";	
	String widthTextField = "200px";
	
	
	public CPFLoginPanel(CPFMainMenu mainMenu) {
		panel = new ContentPanel();
		panel.addStyleName("blue-background");
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		panel.setAutoWidth(true);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		this.CPFMainMenu = mainMenu;
		
		holder = new HorizontalPanel();
		
		
		username = new TextField<String>();
		username.setTabIndex(1);
		username.setSelectOnFocus(true);
		username.setWidth(widthTextField);
		
		password = new TextField<String>();
		password.setPassword(true);
		password.setTabIndex(2);
		password.setSelectOnFocus(true);
		password.setWidth(widthTextField);
		password.addKeyListener(CPFLoginController.loginEnterKeyListener(this));
		
		
		String text = CPFLanguage.print().logIn();
		login = new ClickHtml();
		login.setHtml("<div class='login_button'>" + text + "</div>");
		login.addListener(Events.OnClick, CPFLoginController.loginListener(this));
		
	}
	
	public ContentPanel build() {
		panel.setWidth(panelWidth);	
		panel.add(buildForm());
		
		return panel;
	}
	
	private HorizontalPanel buildForm() {
		//holder.setStyleAttribute("padding", "6px");
		holder.setBorders(false);
		holder.addStyleName("login_panel");
		
		Html user = new Html();
		user.setHtml("<div class='login_label'>"+CPFLanguage.print().username()+"</div>");
		
		holder.add(user);
		holder.add(FormattingUtils.addHSpace(10));
		holder.add(username);
		
		holder.add(FormattingUtils.addHSpace(10));
		
		Html pword = new Html();
		pword.setHtml("<div class='login_label'>"+CPFLanguage.print().password()+"</div>");
		
		holder.add(pword);
		holder.add(FormattingUtils.addHSpace(5));
		holder.add(password);
		
		holder.add(FormattingUtils.addHSpace(10));
		holder.add(login);
		holder.add(FormattingUtils.addHSpace(5));
		
		return holder;
	}

	public CPFMainMenu getCPFMainMenu() {
		return CPFMainMenu;
	}
	
	
	public HorizontalPanel buildLoggedInPanel(String welcomeMessage) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("login-message");
		
		panel.add(FormattingUtils.addHSpace(10));
		panel.add(new Html("<div class='welcome-message'>"+ welcomeMessage + "</div>"));
		
		panel.add(FormattingUtils.addHSpace(5));
		
		String text = CPFLanguage.print().logOut();
		ClickHtml logout = new ClickHtml();
		logout.setHtml("<div class='logout-message'> | " + text + "</div>");
		logout.addListener(Events.OnClick, CPFLoginController.logout(this));
		panel.add(logout);
		
		panel.add(FormattingUtils.addHSpace(5));
		
		return panel;
	}
	
	public HorizontalPanel buildErrorMessagePanel(String errorMessage) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("login-message");
		
		ClickHtml html = new ClickHtml();
		html.setId("message");
		html.setHtml("<div class='error-message'>"+errorMessage + " <b>["+CPFLanguage.print().clickToClearMessage()+"]</b></font></div>");
		html.addListener(Events.OnClick, CPFLoginController.logout(this));
		
		panel.add(FormattingUtils.addHSpace(10));
		panel.add(html);
		
		panel.add(FormattingUtils.addHSpace(5));
		
		return panel;
	}
		
	public ContentPanel getPanel() {
		return panel;
	}

	public TextField<String> getPassword() {
		return password;
	}

	public HorizontalPanel getHolder() {
		return holder;
	}

	public TextField<String> getUsername() {
		return username;
	}
}
