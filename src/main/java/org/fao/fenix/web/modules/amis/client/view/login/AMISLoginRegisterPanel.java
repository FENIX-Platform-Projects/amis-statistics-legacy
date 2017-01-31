package org.fao.fenix.web.modules.amis.client.view.login;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

public class AMISLoginRegisterPanel {

	ContentPanel panel;
	public HorizontalPanel errorMessagePanel;
	
	AMISLoginPanel loginPanel;
	
	AMISRegisterPanel registerPanel;
	
	AMISInput amisInput;
	
	AMISMainMenu mainMenu;
	
/*	public AMISLoginRegisterPanel(AMISInput input) {
		panel = new ContentPanel();
		errorMessagePanel = new HorizontalPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		loginPanel = new AMISLoginPanel(input);
		
		registerPanel = new AMISRegisterPanel();
		
		amisInput = input;
		
		mainMenu = null;
	}*/
	
	
	public AMISLoginRegisterPanel(AMISMainMenu mainMenu) {
		panel = new ContentPanel();
		errorMessagePanel = new HorizontalPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		this.mainMenu = mainMenu;
		
		loginPanel = new AMISLoginPanel(mainMenu, this);
		
		registerPanel = new AMISRegisterPanel();
		
		//amisInput = null;
			
	}
	
	public ContentPanel build() {
		panel.add(FormattingUtils.addHSpace(5));
		panel.add(FormattingUtils.addVSpace(15));
		//panel.add(buildWelcome());
		//panel.add(FormattingUtils.addVSpace(5));
		panel.add(buildText());
		panel.add(FormattingUtils.addVSpace(15));
		panel.add(buildLoginRegisterPanel());
		panel.add(FormattingUtils.addVSpace(20));
		panel.add(buildErrorMessagePanel());
		panel.add(FormattingUtils.addVSpace(40));
		//panel.layout();
		
		return panel;
	}
	
	private VerticalPanel buildWelcome() {
		VerticalPanel panel = new VerticalPanel();
		HTML html = new HTML();
		html.setHTML("<div class='input_title'>Welcome to the AMIS Input Data section</div>");
		panel.add(html);
		return panel;
	}
	
	private HorizontalPanel buildErrorMessagePanel() {
		errorMessagePanel.add(FormattingUtils.addHSpace(20));
		return errorMessagePanel;
	}
	
	private HTML setErrorMessage() {
		HTML html = new HTML();
		html.setHTML("");
		return html;
	}

	
//	private VerticalPanel buildText() {
//		VerticalPanel panel = new VerticalPanel();
//		HTML html = new HTML();
//
//		String text = "Members need to log in to input data. To request a login account please contact the <a href='mailto:AMIS-Secretariat@fao.org'>AMIS Secretariat</a>.";
//		html.setHTML("<div class='amis_login_register_text'>"+ text +"</div>");
//		panel.add(html);
//		return panel;
//	}
	
	private HorizontalPanel buildText() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(0);
		//final AMISRegistrationForm amisRegistrationForm = new AMISRegistrationForm();
		HTML html = new HTML();
		HTML html2 = new HTML();
		String text1 = "";
		if(AMISController.currentSelectedView.equals(AMISCurrentView.INPUT))
			//text1 = "&nbsp;&nbsp;Members need to log in to input data. To request a login account please fill the&nbsp;&nbsp;";
			text1 = "&nbsp;&nbsp;Members need to log in to input data. Members who have not yet requested a login account, please fill in the&nbsp;&nbsp;";
		else
			//text1 = "&nbsp;&nbsp;Members need to log in to access national data sources as well as to input data. To request a login account please fill the&nbsp;&nbsp;";
			text1 = "&nbsp;&nbsp;Members need to log in to access national data sources as well as to input data. Members who have not yet requested a login account, please fill in the&nbsp;&nbsp;";
		
		html.setHTML("<div class='amis_login_register_text' style='cursor:pointer;'>"+ text1 +"</div>");
		String text2 = "registration form.";
		html2.setHTML("<div class='amis_login_register_text link' style='cursor:pointer;'>"+ text2 +"</div>");
		html2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				AMISRegistrationForm amisRegistrationForm = new AMISRegistrationForm();
//				AMISController.openRegisterPanelForm(amisRegistrationForm);
				amisRegistrationForm.build();
			}
		});
		panel.add(html);
		panel.add(html2);
		Button registerForm = new Button("Register Form");
		registerForm.setEnabled(false);
		registerForm.setWidth(120);
		//registerForm.setIconStyle("save");
		//registerForm.setIconAlign(IconAlign.RIGHT);
		registerForm.setBorders(true);
		
		//registerForm.addSelectionListener(AMISController.openRegisterPanelForm(amisRegistrationForm));
		registerForm.enable();
		//panel.add(registerForm);
		return panel;
	}
	
	private HorizontalPanel buildLoginRegisterPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(FormattingUtils.addHSpace(75));
		panel.add(buildLoginPanel());
		panel.add(FormattingUtils.addHSpace(100));
		//panel.add(buildRegisterPanel());
		return panel;
	}
	
	private HorizontalPanel buildLoginPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(loginPanel.build());
		return panel;
	}
	
	private HorizontalPanel buildRegisterPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(registerPanel.build());
		return panel;
	}

	public AMISInput getAmisInput() {
		return amisInput;
	}

	public void setAmisInput(AMISInput amisInput) {
		this.amisInput = amisInput;
	}
	
	
	
}
