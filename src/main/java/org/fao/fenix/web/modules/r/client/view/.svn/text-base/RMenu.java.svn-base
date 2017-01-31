package org.fao.fenix.web.modules.r.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;

public class RMenu {

	private Button menuButton;
	
	private Menu menu;
	
	public RMenu() {
		menu = new Menu();
		menuButton = new Button(BabelFish.print().statisticalAnalysis());
		menuButton.setIconStyle("R");
		menuButton.setMenu(menu);
	}
	
	public Button build() {
		return menuButton;
	}

	public Button getMenuButton() {
		return menuButton;
	}

	public Menu getMenu() {
		return menu;
	}
	
}