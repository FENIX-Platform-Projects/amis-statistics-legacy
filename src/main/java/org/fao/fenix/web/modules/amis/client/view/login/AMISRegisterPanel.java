package org.fao.fenix.web.modules.amis.client.view.login;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class AMISRegisterPanel {

		ContentPanel panel;
		
		TextField<String> name;
		
		TextField<String> address;
		
		TextField<String> company;
		
		TextField<String> email;
		
		// Dimensions
		
		String panelWidth = "300px";
		
		String widthText = "70px";
		
		String widthTextField = "150px";
		
		String titleWidth = "120px";
		
		Integer goWidth = 250;
		
		Integer leftSpacing = 15;
		
		public AMISRegisterPanel() {
			panel = new ContentPanel();
			
			panel.setHeaderVisible(false);
			panel.setBodyBorder(false);

			panel.setAutoHeight(true);
			panel.setScrollMode(Scroll.NONE);
			

			 name = new TextField<String>();
			
			address = new TextField<String>();
			
			company = new TextField<String>();
			
			email = new TextField<String>();
		}
		
		public ContentPanel build() {
			panel.setWidth(panelWidth);

			panel.add(buildTitle("REGISTER"));
			
			panel.add(buildForm());
			
			return panel;
		}
		

		
		private VerticalPanel buildForm() {
			VerticalPanel panel = new VerticalPanel();
			panel.addStyleName("login_register_panel");
			
			panel.add(FormattingUtils.addVSpace(10));
			panel.add(buildFormPanel("Name", name));
			
			panel.add(FormattingUtils.addVSpace(5));
			panel.add(buildFormPanel("Address", address));
			
			panel.add(FormattingUtils.addVSpace(5));
			panel.add(buildFormPanel("Company", company));
			
			panel.add(FormattingUtils.addVSpace(5));
			panel.add(buildFormPanel("E-Mail", email));

			
			panel.add(FormattingUtils.addVSpace(5));
			panel.add(buildGo());
			panel.add(FormattingUtils.addVSpace(5));
			
			return panel;
		}
		
		private HorizontalPanel buildTitle(String text) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setWidth(titleWidth);
			panel.addStyleName("login_register_panel");
			
			panel.add(FormattingUtils.addHSpace(leftSpacing));
			
			HTML html = new HTML();
			html.setHTML("<div class='login_register_panel_title'>" + text + "</div>");
			panel.add(html);
			
			return panel;
		} 
		
		
		private HorizontalPanel buildFormPanel(String text, TextField<String> textField) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);
			
			panel.addStyleName("login_register_panel");
			HTML html = new HTML();
			html.setHTML("<div class='login_register_panel_text'>" + text + "</div>");
			html.setWidth(widthText);
			
			
			panel.add(FormattingUtils.addHSpace(leftSpacing));
			panel.add(html);
			panel.add(FormattingUtils.addHSpace(5));
			panel.add(textField);
			
			return panel;
		}
		
				
		private HorizontalPanel buildGo() {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);
			
			panel.add(FormattingUtils.addHSpace(goWidth));
			
			IconButton icon = new IconButton("arrow_go");
			panel.add(icon);
			
			panel.add(FormattingUtils.addHSpace(2));
			
			HTML html = new HTML();
			String text = "GO";
			html.setHTML("<div class='login_register_go'>" + text + "</div>");
			
			panel.add(html);
			
			return panel;
		}
}
