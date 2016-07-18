package org.fao.fenix.web.modules.core.client.utils;

import org.fao.fenix.web.modules.core.client.view.FenixSettings;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class About {
	
	private Window about;
	private VerticalPanel mainCont;

	public About() {
		about = new Window();
		about.setHeading("About Workstation");
		about.setResizable(false);
		about.setSize(400, 300);
		about.setPosition(com.google.gwt.user.client.Window.getClientWidth() - 430, (int) (com.google.gwt.user.client.Window.getClientHeight() / 4));
		mainCont = new VerticalPanel();
		DOM.setStyleAttribute(about.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public void build() {

		HTML imm = new HTML();
		imm.setHTML("<table width='390px'><tr><td align='left'>"
				+ "&nbsp;<img src='images/faologo_blue.png'>"
				+ "</td><td align='right'><img src='images/ec_logo.gif'></td></tr></table>");

		mainCont.add(imm);

		HTML content = new HTML();
		content.setHTML("<table width='400'><tr><td align='center'><br>"
				+ "<span style='font-size: 18px; font-weight: bold;'>Workstation 3.3.1</span>"
				+ "<br><br>Funded by the European Commission<br>"
				+ "through the<br>EC-FAO Food Security Programme<br><a href='http://www.foodsec.org' target='_blank'>"
				+ "(http://www.foodsec.org)</a><br>"
				+ "<br>Food And Agriculture Organization of the United Nations</span></td></tr></table>");
		mainCont.add(content);

		about.add(mainCont);
		about.show();
	
	}



}
