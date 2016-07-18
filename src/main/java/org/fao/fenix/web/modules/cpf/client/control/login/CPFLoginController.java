package org.fao.fenix.web.modules.cpf.client.control.login;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.cpf.client.lang.CPFLanguage;
import org.fao.fenix.web.modules.cpf.client.view.login.CPFLoginPanel;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class CPFLoginController {


	public static Listener<ComponentEvent> passwordListener(final TextField<String> password, final CPFLoginPanel loginPanel) {
		return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {

				TextField<String> newP  = new TextField<String>();
				newP.setWidth(100);
				newP.setPassword(true);

				loginPanel.getHolder().add(newP);
				loginPanel.getPanel().getLayout().layout();
			}
		};
	}

	public static Listener<ComponentEvent> loginListener(final CPFLoginPanel loginPanel) {
		return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {
		      loginAction(loginPanel);
			}
		};
	}

	public static KeyListener loginEnterKeyListener(final CPFLoginPanel loginPanel) {
		return new KeyListener() {
			public void componentKeyPress(ComponentEvent event) {
				 if(event.getKeyCode()==13)
		            {
					 loginAction(loginPanel);
		            }
		            super.componentKeyPress(event);
			}
		};
	}

	@SuppressWarnings("deprecation")
	public static void loginAction(final CPFLoginPanel loginPanel) {

		UserServiceEntry.getInstance().login(loginPanel.getUsername().getValue(), loginPanel.getPassword().getValue(), new AsyncCallback<LoginResponseVo>() {

			public void onSuccess(LoginResponseVo result) {

				LoginResponseVo vo = (LoginResponseVo) result;

				if (vo.isSucceeded()) {

					String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();

					String welcomeMessage = "  " + CPFLanguage.print().welcome() + " " + firstAndLastName;

					HorizontalPanel message = loginPanel.buildLoggedInPanel(welcomeMessage);

					if (RootPanel.get("loginMessage").getWidgetCount() > 0)
						RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));

					RootPanel.get("loginMessage").add(message);

					FenixUser.populateRoles(vo.getFenixUserVo());
					loginPanel.getCPFMainMenu().getCpf().getHistory().buildMainMenu(true);
				} else {
					String message = vo.getResponseMessage();

					if (vo.getResponseMessage().equals("UsernameNotFoundException"))
						message = CPFLanguage.print().usernameNotFoundException();
					if (vo.getResponseMessage().equals("BadCredentialsException"))
						message = CPFLanguage.print().userBadCredentialsException();
					if (vo.getResponseMessage().equals("AuthenticationException"))
						message = CPFLanguage.print().userAuthenticationException();
					if (vo.getResponseMessage().equals("DisabledException")) {
						//User not in a group
						message = CPFLanguage.print().userGroupDisabledException();
					}

					if (RootPanel.get("loginMessage").getWidgetCount() > 0)
						RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));

					HorizontalPanel errorMessage = loginPanel.buildErrorMessagePanel(message);
					RootPanel.get("loginMessage").add(errorMessage);
				}
			}

			public void onFailure(Throwable caught) {

				if (RootPanel.get("loginMessage").getWidgetCount() > 0)
					RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));

				HorizontalPanel errorMessage = loginPanel.buildErrorMessagePanel(caught.getLocalizedMessage());
				RootPanel.get("loginMessage").add(errorMessage);
			}

		});

	}

	public static Listener<ComponentEvent> logout(final CPFLoginPanel loginPanel) {
		return new Listener<ComponentEvent>() {

			public void handleEvent(ComponentEvent ce) {
				if (RootPanel.get("loginMessage").getWidgetCount() > 0)
					RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));

				doLogout(loginPanel);
			}
		};
	}

	private static void doLogout(final CPFLoginPanel loginPanel) {
		UserServiceEntry.getInstance().logout(new AsyncCallback<String>() {
			public void onSuccess(String result) {
				FenixUser.giveAnonymousRole();
				loginPanel.getCPFMainMenu().getCpf().getHistory().buildMainMenu(false);
			}

			public void onFailure(Throwable caught) {
				RootPanel.get("loginMessage").clear();
			}
		});
	}


}