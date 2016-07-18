package org.fao.fenix.web.modules.cpf.client.view.menu;



import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.cpf.client.control.history.CPFHistoryController;
import org.fao.fenix.web.modules.cpf.client.lang.CPFLanguage;
import org.fao.fenix.web.modules.cpf.client.view.CPF;
import org.fao.fenix.web.modules.cpf.client.view.find.CPFFindPanel;
import org.fao.fenix.web.modules.cpf.client.view.login.CPFLoginPanel;
import org.fao.fenix.web.modules.cpf.common.constants.CPFCurrentView;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class CPFMainMenu {


	private ContentPanel panel;
	private HorizontalPanel menuPanel;
	private CPF cpf;
	
	private String spacerWidth = "550px";
	

	public CPFMainMenu(CPF cpf) {
		this.cpf = cpf;
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		menuPanel = new HorizontalPanel();
		menuPanel.setHeight(35);
		menuPanel.addStyleName("blue-background");
	}
	
	
	public ContentPanel build(boolean isLogin) {			
		buildMenu(isLogin);
		
		return panel;
	}


	private ContentPanel buildMenu(boolean isLogin) {
		menuPanel.add(buildPanel(CPFLanguage.print().HOME(), CPFCurrentView.HOME));
		
		if(isLogin)
			menuPanel.add(buildLoggedInMenu());
		else {
			menuPanel.add(spacerPanel());
			menuPanel.add(buildLoginPanel());
		}
		panel.add(menuPanel);
	    	
		return panel;
	}
	
	private ContentPanel buildLoggedInMenu() {
		menuPanel.add(buildPanel(CPFLanguage.print().CREATE_CPF_RESULTS_MATRIX(), CPFCurrentView.CREATE_CPF_RESULTS_MATRIX));
		menuPanel.add(buildPanel(CPFLanguage.print().FIND_CPF(), CPFCurrentView.FIND_CPF));
		menuPanel.add(buildFindPanel());
		
		panel.add(menuPanel);
	    return panel;
	}

	private HorizontalPanel buildPanel(String title, CPFCurrentView currentView) {
		HorizontalPanel p = new HorizontalPanel();
		p.setId(currentView.name());
		p.setData("base-style-name", "tab");
		
		//set default HOME as selected
		if(p.getId().equals(CPFCurrentView.HOME.name()))
			p.addStyleName("tab-selected");
		else
			p.addStyleName("tab");


		ClickHtml html = new ClickHtml();
		html.setId(currentView.name());
		html.setHtml("<div>" + title + "</div>");
		
		html.addListener(Events.OnClick, CPFHistoryController.setHistoryItem(currentView));


		p.add(html);

		return p;
	}
	

	private ContentPanel buildLoginPanel() {
		return new CPFLoginPanel(this).build();
	}
	
	private ContentPanel buildFindPanel() {
		return new CPFFindPanel(this).build();
	}
	
	public CPF getCpf() {
		return cpf;
	}


	public HorizontalPanel getMenuPanel() {
		return menuPanel;
	}

	
	public ContentPanel spacerPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		panel.setBodyBorder(false);
		panel.addStyleName("blue-background");
		panel.setWidth(spacerWidth);	
		
		return panel;
	}

}