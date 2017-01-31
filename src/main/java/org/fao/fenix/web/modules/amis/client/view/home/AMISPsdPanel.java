package org.fao.fenix.web.modules.amis.client.view.home;

import org.fao.fenix.web.modules.amis.client.control.AMISPsdController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;


public class AMISPsdPanel {
	
	ContentPanel panel;
	
	public AMISPsdPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
	}
	
	public ContentPanel build(AMISPsd visualizePanel) {

		getVOs(visualizePanel);
		
		return panel;
	}
	
	
	public void getVOs(AMISPsd visualizePanel) {
		AMISPsdController.getVOs(visualizePanel, this, visualizePanel.getSettings());
	}
	
	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
	}
	

}
