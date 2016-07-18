package org.fao.fenix.web.modules.core.client.security;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserPropertiesController {

	public static void find(final UserPropertiesWindow w) {
		String searchString = w.getUserPropertiesPanel().getSearchField().getValue();
		final LoadingWindow l = new LoadingWindow("INFO", "FENIX is retrieving all the users.", "Please Wait.");
		UserServiceEntry.getInstance().find(searchString, new AsyncCallback<List<FenixUserVo>>() {
			public void onSuccess(List<FenixUserVo> vos) {
				cleanUsersPanel(w.getUserPropertiesPanel().getUsersPanel());
				l.destroyLoadingBox();
				for (FenixUserVo vo : vos)
					w.getUserPropertiesPanel().addUserPanel(vo);
				l.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				l.destroyLoadingBox();
				FenixAlert.error("ERROR", e.getMessage());
				l.destroyLoadingBox();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> update(final UserPropertiesWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<FenixUserVo> users = collectUsers(w);
				final LoadingWindow l = new LoadingWindow("INFO", "FENIX is updating users properties.", "Please Wait.");
				UserServiceEntry.getInstance().update(users, new AsyncCallback() {
					public void onSuccess(Object o) {
						l.destroyLoadingBox();
						FenixAlert.info("INFO", "Users properties have been updated.");
						l.destroyLoadingBox();
					}
					public void onFailure(Throwable e) {
						l.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
						l.destroyLoadingBox();
					}
				});
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	private static List<FenixUserVo> collectUsers(UserPropertiesWindow w) {
		List<FenixUserVo> users = new ArrayList<FenixUserVo>();
		for (int i = 0 ; i < w.getUserPropertiesPanel().getUsersPanel().getItemCount() ; i++) {
			VerticalPanel p = (VerticalPanel)w.getUserPropertiesPanel().getUsersPanel().getWidget(i);
			HorizontalPanel firstLastNamePanel = (HorizontalPanel)p.getData("FIRST_LAST_NAME_PANEL");
			String firstName = ((TextField<String>)firstLastNamePanel.getData("FIRST_NAME")).getValue();
			String lastName = ((TextField<String>)firstLastNamePanel.getData("LAST_NAME")).getValue();
			HorizontalPanel usernameEmailPanel = (HorizontalPanel)p.getData("USERNAME_EMAIL_PANEL");
			String username = ((TextField<String>)usernameEmailPanel.getData("USERNAME")).getValue();
			String email = ((TextField<String>)usernameEmailPanel.getData("EMAIL")).getValue();
			HorizontalPanel langPanel = (HorizontalPanel)p.getData("LANG_PANEL");
			ComboBox<GaulModelData> langList = (ComboBox<GaulModelData>)langPanel.getData("LANG");
			String langCode = langList.getSelection().get(0).getGaulCode();
			FenixUserVo vo = new FenixUserVo();
			vo.setEmail(email);
			vo.setFirstName(firstName);
			vo.setLanguage(langCode);
			vo.setLastName(lastName);
			vo.setLoginName(username);
			users.add(vo);
		}
		return users;
	}
	
	public static SelectionListener<ButtonEvent> searchUser(final UserPropertiesWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				find(w);
			}
		};
	}
	
	public static KeyListener searchFieldListener(final UserPropertiesWindow w) {
		return new KeyListener() {
			public void componentKeyPress(ComponentEvent event) {
				if(event.getKeyCode()==13) 
					find(w);
				super.componentKeyPress(event); 
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> close(final UserPropertiesWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				w.getWindow().close();
			}
		};
	}
	
	private static void cleanUsersPanel(VerticalPanel p) {
		for (int i = p.getItemCount() - 1 ; i >= 0 ; i--)
			p.remove(p.getWidget(i));
	}
	
	public static void fillLangStore(ListStore<GaulModelData> langStore) {
		GaulModelData en = new GaulModelData(BabelFish.print().english(), "EN");
		langStore.add(en);
		GaulModelData fr = new GaulModelData(BabelFish.print().french(), "FR");
		langStore.add(fr);
		GaulModelData ar = new GaulModelData(BabelFish.print().arabic(), "AR");
		langStore.add(ar);
	}
	
	public static void selectLang(FenixUserVo vo, ComboBox<GaulModelData> langList) {
		if (vo.getLanguage() == null || vo.getLanguage().equals("") || vo.getLanguage().equalsIgnoreCase("EN"))
			langList.setValue(new GaulModelData(BabelFish.print().english(), "EN"));
		else if (vo.getLanguage().equalsIgnoreCase("FR"))
			langList.setValue(new GaulModelData(BabelFish.print().french(), "FR"));
		else if (vo.getLanguage().equalsIgnoreCase("AR"))
			langList.setValue(new GaulModelData(BabelFish.print().arabic(), "AR"));
	}
	
}