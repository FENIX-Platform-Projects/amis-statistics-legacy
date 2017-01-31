package org.fao.fenix.web.modules.core.client.control.menu;

import java.util.List;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuBar;

public class FenixMenuBuilder {

	final MenuBar root = new MenuBar();

	public ToolBar buildToolBar(final String menubarModel, final ToolBar toolbar, final boolean isLogin) {

		REServiceEntry.getInstance().getMenubarModel(menubarModel, new AsyncCallback<List<FenixMenuItemVo>>() {

			public void onSuccess(List<FenixMenuItemVo> items) {
				
				toolbar.setBorders(false);

				for (FenixMenuItemVo firstLevelItem : items) {
					if (authorisationGranted(firstLevelItem)) {
						if (firstLevelItem.getLevel() == 1) {
							Menu menu = new Menu();
							boolean addMenu = false;
							for (FenixMenuItemVo secondLevelItem : items) {
								if (authorisationGranted(secondLevelItem)) {
									if (secondLevelItem.getLevel() == 2 && secondLevelItem.getParent().equals(firstLevelItem.getName())) {
										addMenu = true;
										MenuItem menuItem = new MenuItem(BabelFish.print().getString(secondLevelItem.getName()));
										menuItem.setIconStyle(secondLevelItem.getIconStyle());
										if (!secondLevelItem.hasChildren())
											menuItem.addSelectionListener(FenixSelectionPool.getSelectionListener(secondLevelItem.getCommand()));
										menu.add(menuItem);
										Menu subMenu = new Menu();
										boolean addSubMenu = false;
										for (FenixMenuItemVo thirdLevelItem : items) {
											if (authorisationGranted(thirdLevelItem)) {
												if (thirdLevelItem.getLevel() == 3 && thirdLevelItem.getParent().equals(secondLevelItem.getName())) {
													addSubMenu = true;
													MenuItem secondMenuItem = new MenuItem(BabelFish.print().getString(thirdLevelItem.getName()));
													secondMenuItem.setIconStyle(thirdLevelItem.getIconStyle());
													if (!thirdLevelItem.hasChildren())
														secondMenuItem.addSelectionListener(FenixSelectionPool.getSelectionListener(thirdLevelItem.getCommand()));
													subMenu.add(secondMenuItem);
													Menu subSubMenu = new Menu();
													boolean addSubSubMenu = false;
													for (FenixMenuItemVo fourthLevelItem : items) {
														if (authorisationGranted(fourthLevelItem)) {
															if (fourthLevelItem.getLevel() == 4 && fourthLevelItem.getParent().equals(thirdLevelItem.getName())) {
																addSubSubMenu = true;
																MenuItem thirdMenuItem = new MenuItem(BabelFish.print().getString(fourthLevelItem.getName()));
																thirdMenuItem.setIconStyle(fourthLevelItem.getIconStyle());
																if (!fourthLevelItem.hasChildren())
																	thirdMenuItem.addSelectionListener(FenixSelectionPool.getSelectionListener(fourthLevelItem.getCommand()));
																subSubMenu.add(thirdMenuItem);
															}
														}
													}
													if (addSubSubMenu)
														secondMenuItem.setSubMenu(subSubMenu);
												}
											}
										}
										if (addSubMenu)
											menuItem.setSubMenu(subMenu);
									}
								}
							}

							Button c = new Button(BabelFish.print().getString(firstLevelItem.getName()));
							c.setIconStyle(firstLevelItem.getIconStyle());
							
							if (!firstLevelItem.getName().equalsIgnoreCase("login") && !firstLevelItem.getName().equalsIgnoreCase("logout")) {
								toolbar.add(c);
							} else if (firstLevelItem.getName().equalsIgnoreCase("login") && isLogin) {
								toolbar.add(c);
								toolbar.add(new SeparatorToolItem());
								toolbar.setData("login", c);
							} else if (firstLevelItem.getName().equalsIgnoreCase("logout") && !isLogin) {
								toolbar.add(c);
							}
							toolbar.add(new SeparatorToolItem());

							if (addMenu) {
								c.setMenu(menu);
							} else {
								c.addSelectionListener(FenixSelectionPool.getButtonSelectionListener(firstLevelItem.getCommand()));
							}
							
						}
					}
				}
			}

			public void onFailure(Throwable e) {
				FenixAlert.error("INFO", e.getMessage());
			}

		});
		
		return null;
	}

	boolean authorisationGranted(FenixMenuItemVo item) {
		boolean granted = true;
		if (item.getName().equals("administration"))
			granted = FenixUser.hasAdminRole();
		if (item.getName().equals("dcmtCodesCreator"))
			granted = FenixUser.hasAdminRole();
		if (item.getName().equals("developersArea"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("project") && item.getParent().equals("newNew"))
			return  FenixUser.hasUserRole();
		if (item.getName().equals("report") && item.getParent().equals("newNew"))
			return  FenixUser.hasUserRole();
		if (item.getName().equals("openText") && item.getParent().equals("newNew"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("dataset") && item.getParent().equals("newNew"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("olap") && item.getParent().equals("newNew"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("image") && item.getParent().equals("newNew"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("dataManagement"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("integratedPhaseClassification"))
			return FenixUser.hasIpcRole();		
		if (item.getName().equals("giewsEarlyWarning"))
			return FenixUser.hasUserRole();		
		if (item.getName().equals("informationeXchange"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("esoko"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("fieldClimate"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("faostatImporter"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("login"))
			return FenixUser.hasAnonymousRole();
		if (item.getName().equals("logout"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("fewsnetImporter"))
			return FenixUser.hasUserRole();
		return granted;
	}

}