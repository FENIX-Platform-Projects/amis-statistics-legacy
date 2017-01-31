package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.core.client.Fenix;
import org.fao.fenix.web.modules.core.client.framework.FenixClientModuleConfiguration;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixModuleConfigurationVo;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginController {

	static LoginView loginView;
	
	public static MenuItem menuItem;

	private static LoginController instance = null;

	protected LoginController() {
		loginView = LoginView.getInstance();
	}

	public static LoginController getInstance() {
		if (instance == null) {
			instance = new LoginController();
//			instance.build();
		}
		instance.loginView.show();
		return instance;
	}

//	public void build() {
//		setEvents();
//	}

	@SuppressWarnings("deprecation")
	public static void loginAction(final LoginView loginView) {
		
		UserServiceEntry.getInstance().login(loginView.usernameTextField.getValue(), loginView.passwordTextField.getValue(), new AsyncCallback<LoginResponseVo>() {

			public void onSuccess(LoginResponseVo result) {
				
				LoginResponseVo vo = (LoginResponseVo) result;
				
				if (vo.isSucceeded()) {
					
					loadFenixConfiguration();
					String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
					Info.display(BabelFish.print().youreIn(), firstAndLastName + " " + BabelFish.print().isLoggedIn());
					if (loginView.text != null) {
						loginView.verticalPanel.remove(loginView.text);
						loginView.text = null;
					}
					loginView.getWindow().setHeight(loginView.windowHeight);
					loginView.getWindow().hide();
					String welcomeMessage = "  " + BabelFish.print().welcome() + " " + firstAndLastName;
					RootPanel.get("loginMessage").add(new HTML(welcomeMessage));
					
					loginView.getWindow().close();
					
					FenixUser.populateRoles(vo.getFenixUserVo());
					Fenix.buildMenu(true, false);
					
//					HorizontalPanel menuPanel = (HorizontalPanel)RootPanel.get("menu").getWidget(0);
//					ToolBar secondaryToolbar = (ToolBar)menuPanel.getData("secondaryToolbar");
//					Button loginButton = (Button)secondaryToolbar.getData("login");
//					loginButton.setText(I18N.print().logout());
//					loginButton.addSelectionListener(logoutListener());

				} else {
					String message = vo.getResponseMessage();
					Info.display(BabelFish.print().generalServerProblem(), vo.getResponseMessage());
					if (vo.getResponseMessage().equals("UsernameNotFoundException"))
						message = BabelFish.print().UsernameNotFoundException();
					if (vo.getResponseMessage().equals("BadCredentialsException"))
						message = BabelFish.print().BadCredentialsException();
					if (vo.getResponseMessage().equals("AuthenticationException"))
						message = BabelFish.print().AuthenticationException();
					if (vo.getResponseMessage().equals("DisabledException"))
						message = "Your user account is still disabled because you do not yet belong to any group. Ask your administrator to add you to a group.";
					setTextmessage(message, loginView);
					//Info.display(BabelFish.print().youreNotIn(), message);
				}
			}
			
			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
				setTextmessage(caught.getLocalizedMessage(), loginView);
			}

		});
		
	}
	
	public static SelectionListener<ButtonEvent> loginListener(final LoginView loginView) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				loginAction(loginView);
			}
		};
	}
	
	public static KeyListener loginEnterKeyListener(final LoginView loginView) {
		return new KeyListener() {
			public void componentKeyPress(ComponentEvent event) {
				 if(event.getKeyCode()==13) 
		            { 
					 loginAction(loginView);
		            } 
		            super.componentKeyPress(event); 
			}
		};
	}

	/**
	 * There is some elaborate logic for the text. The goal was not to give
	 * space to the text when there is no text to show.
	 */
	private static void setTextmessage(String message, LoginView loginView) {
		
		if (loginView.text == null)
			loginView.text = new Html();
		else
			loginView.verticalPanel.remove(loginView.text);
		
		message = "<span style='color: rgb(255, 0, 0);'>" + message + "</span>";
		loginView.text.setHtml(message);
		loginView.verticalPanel.remove(loginView.loginButton);
		loginView.verticalPanel.add(loginView.text);
		loginView.verticalPanel.add(loginView.loginButton);
		loginView.getWindow().setHeight(loginView.windowHeight + 20);

		// needed otherwise you will not see the loginView.text and the button
		// anymore
		loginView.getCenter().layout();
		//loginView.getWindow().hide();
		//loginView.getWindow().show();
	}
	
	private static SelectionListener<ButtonEvent> logoutListener() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				logoutAction();
			}
		};
	}

	private static Command logout() {
		return new Command() {
			public void execute() {
				UserServiceEntry.getInstance().logout(new AsyncCallback<String>() {
					public void onSuccess(String result) {
						logoutAction();
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
						RootPanel.get("loginMessage").clear();
					}
				});

			}
		};
	}
	
	private static void logoutAction() {
		FenixUser.giveAnonymousRole();
		RootPanel.get("loginMessage").clear();
	}

	private static void loadFenixConfiguration() {
		UserServiceEntry.getInstance().retrieveFenixModuleConfiguration(new AsyncCallback<FenixModuleConfigurationVo>() {
			public void onSuccess(FenixModuleConfigurationVo vo) {
				FenixClientModuleConfiguration.setCcbsIsRunning(vo.isCcbsIsRunning());
				FenixClientModuleConfiguration.setFpiIsRunning(vo.isFpiIsRunning());
				FenixClientModuleConfiguration.setIpcIsRunning(vo.isIpcIsRunning());
			}
			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
				setTextmessage(caught.getLocalizedMessage(), null);
			}
		});
	}

}