package org.fao.fenix.web.modules.amis.client.view.login;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.AMISLogInController;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.vo.AMISUserParameters;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class AMISLoginPanel {

	public ContentPanel panel;
	
	TextField<String> user;
	
	TextField<String> password;
	
	// Dimensions
	
	String panelWidth = "300px";
	
	String widthText = "70px";
	
	String widthTextField = "200px";
	
	String titleWidth = "120px";
	
	Integer goWidth = 170;
	
	Integer goWidthForInputPage = 235;
	
	Integer leftSpacing = 15;
	
	AMISInput amisInput;
	
	AMISMainMenu mainMenu;
	
	AMISLoginRegisterPanel loginRegisterPanel;
	
	private static AMISUserParameters amisUserParameters;

	
/*	public AMISLoginPanel(AMISInput amisInput) {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		this.amisInput = amisInput;
		this.mainMenu = null;
		this.loginRegisterPanel = null;
		
		user = new TextField<String>();
		user.setWidth(widthTextField);
		
		password = new TextField<String>();
		password.setPassword(true);
		password.setWidth(widthTextField);
		
		//amisUserParameters = new AMISUserParameters();
	}*/
	
	public AMISLoginPanel(AMISMainMenu mainMenu, AMISLoginRegisterPanel loginRegisterPanel) {
		panel = new ContentPanel();
	
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		this.amisInput = null;
		this.mainMenu = mainMenu;
		this.loginRegisterPanel = loginRegisterPanel;
		
		user = new TextField<String>();
		user.setWidth(widthTextField);
		
		password = new TextField<String>();
		password.setPassword(true);
		password.setWidth(widthTextField);
		password.addListener(Events.KeyPress, AMISLogInController.loginEnterKeyListener(user, password, mainMenu, loginRegisterPanel));
		
		
		amisUserParameters = new AMISUserParameters();
	}
	
	public ContentPanel build() {
		
	    panel.setWidth(panelWidth);

		panel.add(buildTitle("LOGIN"));
		
		panel.add(buildForm());
		
		return panel;
	}
	

	
	private VerticalPanel buildForm() {
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("login_register_panel");
		
		panel.add(FormattingUtils.addVSpace(20));
		panel.add(buildFormUser());
		
		panel.add(FormattingUtils.addVSpace(5));
		panel.add(buildFormPassword());
		
		panel.add(FormattingUtils.addVSpace(20));
		panel.add(buildGo());
		panel.add(FormattingUtils.addVSpace(15));
		
		return panel;
	}
	
	
	
	private HorizontalPanel buildTitle(String text) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setWidth(titleWidth);
		panel.addStyleName("login_top_panel");
		
		panel.add(FormattingUtils.addHSpace(leftSpacing));
		
		HTML html = new HTML();
		html.setHTML("<div class='login_register_panel_title'>" + text + "</div>");
		panel.add(html);
		
		return panel;
	} 
	
	
	private HorizontalPanel buildFormUser() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		panel.addStyleName("login_register_panel");
		HTML html = new HTML();
		String text = "User ID";
		html.setHTML("<div class='login_register_panel_text'>" + text + "</div>");
		html.setWidth(widthText);
		
		
		panel.add(FormattingUtils.addHSpace(leftSpacing));
		panel.add(html);
		panel.add(FormattingUtils.addHSpace(5));
		panel.add(user);
		panel.add(FormattingUtils.addHSpace(15));
		
		return panel;
	}
	
	private HorizontalPanel buildFormPassword() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		HTML html = new HTML();
		String text = "Password";
		html.setHTML("<div class='login_register_panel_text'>" + text + "</div>");
		html.setWidth(widthText);
		
		panel.add(FormattingUtils.addHSpace(leftSpacing));
		panel.add(html);
		panel.add(FormattingUtils.addHSpace(5));
		panel.add(password);
		panel.add(FormattingUtils.addHSpace(15));
		
		return panel;
	}
	
	private HorizontalPanel buildGo() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		panel.add(FormattingUtils.addHSpace(leftSpacing));
		
		String cancel = "[CANCEL]";
		ClickHtml cancelHtml = new ClickHtml();
		cancelHtml.setHtml("<div class='login_register_go'>" + cancel + "</div>");
		cancelHtml.addListener(Events.OnClick, AMISLogInController.cancel(mainMenu));
		
		// Cancel not required if opened from AMIS Input page
		if(!AMISController.currentSelectedView.equals(AMISCurrentView.INPUT)){
			panel.add(cancelHtml);
			panel.add(FormattingUtils.addHSpace(goWidth));
		}
		else
			panel.add(FormattingUtils.addHSpace(goWidthForInputPage));
		
		IconButton icon = new IconButton("arrow_go");
		panel.add(icon);
		panel.add(FormattingUtils.addHSpace(2));
		
		String text = "GO";
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='login_register_go'>" + text + "</div>");
		
		//html.addListener(Events.OnClick, AMISLogInController.loginListenerClick(user, password, amisInput.getDataSourceListBox(), amisInput));
		
		html.addListener(Events.OnClick, AMISLogInController.loginClickListener(user, password, mainMenu, loginRegisterPanel));
		
		panel.add(html);
		panel.add(FormattingUtils.addHSpace(10));
		
		return panel;
	}

	public static AMISUserParameters getAmisUserParameters() {
		return amisUserParameters;
	}

	public static void setAmisUserParameters(AMISUserParameters amisUserParameters) {
		AMISLoginPanel.amisUserParameters = amisUserParameters;
	}

	public AMISInput getAmisInput() {
		return amisInput;
	}

	public void setAmisInput(AMISInput amisInput) {
		this.amisInput = amisInput;
	}
	
	
	

	
	
}
