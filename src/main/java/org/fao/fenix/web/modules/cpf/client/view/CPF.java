package org.fao.fenix.web.modules.cpf.client.view;


import org.fao.fenix.web.modules.cpf.client.control.menu.CPFMenuController;
import org.fao.fenix.web.modules.cpf.client.history.CPFHistory;
import org.fao.fenix.web.modules.cpf.client.view.home.CPFHome;
import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;
import org.fao.fenix.web.modules.cpf.client.view.utils.html.HTMLUtils;
import org.fao.fenix.web.modules.cpf.common.constants.CPFConstants;
import org.fao.fenix.web.modules.cpf.common.constants.CPFCurrentView;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.i18n.client.LocaleInfo;

public class CPF {

	CPFMainMenu mainMenu;

	CPFHome home;
	
	CPFHistory history;


	ContentPanel panel;
	
	public CPF() {
		mainMenu = new CPFMainMenu(this);
		home = new CPFHome(mainMenu);
		history = new CPFHistory(mainMenu, this);
	}

  
	public void build() {
		
		setLanguage();
		
		//Logo onclick action
		HTMLUtils.buildAndReplaceElement("CPF-LOGO", CPFMenuController.openView(CPFCurrentView.HOME, mainMenu));
		
		history.buildMainMenu(false);
		
		CPFHome.hideHomeVisibility();
		//buildHome();
		
		// initialize history listener
		history.initHistorySupport(); 

	}
	
	private void setLanguage() {
		System.out.println("current locale: " + LocaleInfo.getCurrentLocale().getLocaleName());
		String language = LocaleInfo.getCurrentLocale().getLocaleName();
		if ( !language.equalsIgnoreCase("default")) {
			CPFConstants.defaultLanguage = language;

			System.out.println("CPFConstants.defaultLanguage: " + CPFConstants.defaultLanguage);

		}
	}
	public CPFHistory getHistory() {
		return history;
	}

}

