package org.fao.fenix.web.modules.core.client.security.usermanager;

import java.util.List;

import org.fao.fenix.web.modules.core.client.framework.FenixClientModuleConfiguration;
import org.fao.fenix.web.modules.core.client.security.FenixRole;
import org.fao.fenix.web.modules.core.client.security.FenixStringValidator;
import org.fao.fenix.web.modules.core.client.security.FenixUserListController;
import org.fao.fenix.web.modules.core.client.security.groupmanager.GroupManagerView;
import org.fao.fenix.web.modules.core.client.security.permissionmanager.PermissionManagerView;
import org.fao.fenix.web.modules.core.client.utils.FenixConstants;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserManagerController {

	UserManagerView userManagerView;
	FenixUserListController fenixUserListController;

	private static UserManagerController instance = null;

	protected UserManagerController() {
		userManagerView = UserManagerView.getInstance();
	}

	public static UserManagerController getInstance() {
		if (instance == null) {
			instance = new UserManagerController();
			instance.build();
		}
		instance.loadUserList();
		instance.configureModuleCheckBoxes();
		PermissionManagerView.getInstance().getWindow().close();
		GroupManagerView.getInstance().getWindow().close();
		instance.userManagerView.show();
		return instance;
	}

	private void loadUserList() {
		activateRight();
		UserServiceEntry.getInstance().getUserList(new AsyncCallback<List<FenixUserVo>>() {
			public void onSuccess(List<FenixUserVo> result) {
				setTextmessage("");
				updateUserList(result);
				setListMessages(result.size());

			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
			}
		});
		setErrorMessage("");
	}

	protected void setListMessages(int size) {
		if (size == 0) {
			setTextmessage(BabelFish.print().noUsersFound());
			userManagerView.usersFoundHtml.setHTML("");
		} else
			userManagerView.usersFoundHtml.setHTML(size + " " + BabelFish.print().usersFound());

	}

	public void build() {
		setEvents();
		fenixUserListController = new FenixUserListController(userManagerView.dataList);
	}

	private void setEvents() {

		userManagerView.deleteUserButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				List<DataListItem> listItems = userManagerView.dataList.getItems();
				List<DataListItem> deletedItems = userManagerView.dataList.getSelectedItems();

				if (check4Admin(listItems, deletedItems)) {
					for (final DataListItem dataListItem : deletedItems) {
						final FenixUserVo fenixUserVo = dataListItem.getData(FenixConstants.VO);
						UserServiceEntry.getInstance().deleteUser(fenixUserVo, new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								setWestErrorMessage(BabelFish.print().userCantBeDeleted());
								Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
							}

							public void onSuccess(String result) {
								if (!userManagerView.dataList.remove(dataListItem))
									Info.display("Deleting User", "Something went terribly wrong, user might not have been deleted");
								setListMessages(userManagerView.dataList.getItemCount());
								userManagerView.deleteUserButton.disable();
								emptyRight();
							}
						});
					}
				} else
					setWestErrorMessage(BabelFish.print().dontDeleteAdmin());
			}

		});

		userManagerView.dataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				activateLeft();
				setWestErrorMessage("");
				DataList l = (DataList) ce.getComponent();
				if (l.getSelectedItems().size() > 0)
					userManagerView.deleteUserButton.enable();

				if (l.getSelectedItems().size() == 1) {
					DataListItem selectedItem = l.getSelectedItem();
					// When deleting more than one, the selectemItem is just
					// deleted for some reason. Therefore this check.
					if (selectedItem != null) {
						FenixUserVo fenixUserVo = selectedItem.getData(FenixConstants.VO);
						userManagerView.userNameTextField.setValue(fenixUserVo.getLoginName());
						userManagerView.passwordTextField.setValue(fenixUserVo.getPassword());
						userManagerView.firstNameTextField.setValue(fenixUserVo.getFirstName());
						userManagerView.lastNameTextField.setValue(fenixUserVo.getLastName());
						String[] roles = fenixUserVo.getRoles();
						userManagerView.roleAdmin.setValue(false);
						userManagerView.roleFpi.setValue(false);
						userManagerView.roleCcbs.setValue(false);
						userManagerView.roleIpc.setValue(false);
						for (int i = 0; i < roles.length; i++) {
							if (roles[i].equals(FenixRole.ADMIN))
								userManagerView.roleAdmin.setValue(true);
							if (roles[i].equals(FenixRole.FPI))
								userManagerView.roleFpi.setValue(true);
							if (roles[i].equals(FenixRole.CCBS))
								userManagerView.roleCcbs.setValue(true);
							if (roles[i].equals(FenixRole.IPC))
								userManagerView.roleIpc.setValue(true);
						}
					}
				}
			}
		});

		userManagerView.dataList.setSelectionMode(SelectionMode.MULTI);

		userManagerView.addUserButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				setWestErrorMessage("");
				activateRight();
				emptyRight();
			}
		});

		userManagerView.saveUserButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				final FenixUserVo fenixUserVo = new FenixUserVo();
				fenixUserVo.setLoginName(userManagerView.userNameTextField.getValue());
				fenixUserVo.setPassword(userManagerView.passwordTextField.getValue());
				fenixUserVo.setFirstName(userManagerView.firstNameTextField.getValue());
				fenixUserVo.setLastName(userManagerView.lastNameTextField.getValue());
				fenixUserVo.setEnabled(false);

				int i = 1;// the user has always the role user by default
				if (userManagerView.roleAdmin.getValue())
					i++;
				if (userManagerView.roleFpi.getValue())
					i++;
				if (userManagerView.roleCcbs.getValue())
					i++;
				if (userManagerView.roleIpc.getValue())
					i++;

				String[] roles = new String[i];
				i = 0;
				roles[i++] = FenixRole.USER;
				if (userManagerView.roleAdmin.getValue())
					roles[i++] = FenixRole.ADMIN;
				if (userManagerView.roleFpi.getValue())
					roles[i++] = FenixRole.FPI;
				if (userManagerView.roleCcbs.getValue())
					roles[i++] = FenixRole.CCBS;
				if (userManagerView.roleIpc.getValue())
					roles[i++] = FenixRole.IPC;
				fenixUserVo.setRoles(roles);

				if (validateUser(fenixUserVo))
					UserServiceEntry.getInstance().addUser(fenixUserVo, new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
							Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
							setErrorMessage(BabelFish.print().generalServerProblem() + caught.getLocalizedMessage());
						}

						public void onSuccess(String result) {
							setMessage("Note: User will be activated after it has been added to at least one group. Use the group manager to add the user to a group.");
							setTextmessage("");
							fenixUserListController.addUser2DataList(fenixUserVo);
							Info.display(BabelFish.print().userManagerWindowHeading(), BabelFish.print().userAdded());
							activateLeft();
							setListMessages(userManagerView.dataList.getItemCount());
						}
					});

			}

		});

		userManagerView.cancelButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				activateLeft();
				emptyRight();
			}
		});
	}

	// TODO isolate this validation and duplicate on the server side.
	private boolean validateUser(FenixUserVo fenixUserVo) {
		boolean valid = false;
		String message = "";
		if (FenixStringValidator.filled(fenixUserVo.getLoginName()) && FenixStringValidator.filled(fenixUserVo.getPassword()) && FenixStringValidator.filled(fenixUserVo.getFirstName()) && FenixStringValidator.filled(fenixUserVo.getLastName()))
			valid = true;
		else
			message = BabelFish.print().allFieldsMustBeFilled();

		if (valid) {
			List<DataListItem> list = userManagerView.dataList.getItems();
			for (DataListItem dataListItem : list) {
				FenixUserVo found = dataListItem.getData(FenixConstants.VO);
				if (found.getLoginName().equals(fenixUserVo.getLoginName()))
					valid = false;
			}
			message = BabelFish.print().usernameAlreadyExists();
		}

		if (valid && (fenixUserVo.getRoles().length == 0 || fenixUserVo.getRoles().length > 5)) {
			message = BabelFish.print().selectAtLeastOneRole();
			valid = false;
		}

		if (!valid)
			setErrorMessage(message);
		return valid;
	}

	private void updateUserList(List<FenixUserVo> result) {
		userManagerView.dataList.removeAll();
		for (int i = 0; i < result.size(); i++) {
			FenixUserVo fenixUserVo = (FenixUserVo) result.get(i);
			fenixUserListController.addUser2DataList(fenixUserVo);
		}
	}

	private void setTextmessage(String message) {
		userManagerView.text.setHTML(message);
	}

	private void activateRight() {
		// userManagerView.dataList.disable();
		userManagerView.addUserButton.disable();
		// userManagerView.deleteUserButton.disable();

		userManagerView.userNameTextField.enable();
		userManagerView.passwordTextField.enable();
		userManagerView.firstNameTextField.enable();
		userManagerView.lastNameTextField.enable();
		userManagerView.roleCheckBoxGroup.enable();
		userManagerView.saveUserButton.enable();
		userManagerView.cancelButton.enable();
	}

	private void activateLeft() {
		userManagerView.dataList.enable();
		userManagerView.addUserButton.enable();
		userManagerView.deleteUserButton.disable();

		userManagerView.userNameTextField.disable();
		userManagerView.passwordTextField.disable();
		userManagerView.firstNameTextField.disable();
		userManagerView.lastNameTextField.disable();
		userManagerView.roleCheckBoxGroup.disable();
		userManagerView.saveUserButton.disable();
		userManagerView.cancelButton.disable();
	}

	private void emptyRight() {
		userManagerView.userNameTextField.setValue("");
		userManagerView.passwordTextField.setValue("");
		userManagerView.firstNameTextField.setValue("");
		userManagerView.lastNameTextField.setValue("");
		userManagerView.roleAdmin.setValue(false);
		userManagerView.roleFpi.setValue(false);
		userManagerView.roleCcbs.setValue(false);
		userManagerView.roleIpc.setValue(false);

		setErrorMessage("");
	}

	private void setMessage(String message) {
		message = "<span style='color: rgb(0, 0, 0);'>" + message + "</span>";
		userManagerView.errorMessage.setHTML(message);
	}

	private void setErrorMessage(String message) {
		message = "<span style='color: rgb(255, 0, 0);'>" + message + "</span>";
		userManagerView.errorMessage.setHTML(message);
	}

	private void setWestErrorMessage(String message) {
		message = "<span style='color: rgb(255, 0, 0);'>" + message + "</span>";
		userManagerView.westErrorMessage.setHTML(message);
	}

	boolean check4Admin(List<DataListItem> listItems, List<DataListItem> deletedItems) {
		int foundNumberOfAdmins = calcuateNumberOfAdmins(listItems);
		int foundNumberOfAdmins2Delete = calcuateNumberOfAdmins(deletedItems);
		boolean weStillHaveALeader = false;
		if (foundNumberOfAdmins > foundNumberOfAdmins2Delete)
			weStillHaveALeader = true;
		return weStillHaveALeader;
	}

	int calcuateNumberOfAdmins(List<DataListItem> listItems) {
		int numberOfAdminsFound = 0;
		for (DataListItem listItem : listItems) {
			FenixUserVo fenixUserVo = listItem.getData(FenixConstants.VO);
			String roles[] = fenixUserVo.getRoles();
			boolean adminFound = false;
			for (int i = 0; i < roles.length; i++) {
				String role = roles[i];
				if (role.equals(FenixRole.ADMIN))
					adminFound = true;
			}
			if (adminFound)
				numberOfAdminsFound++;
		}
		return numberOfAdminsFound;
	}

	private void configureModuleCheckBoxes() {
		userManagerView.roleCcbs.setVisible(FenixClientModuleConfiguration.isCcbsIsRunning());
		userManagerView.roleFpi.setVisible(FenixClientModuleConfiguration.isFpiIsRunning());
		userManagerView.roleIpc.setVisible(FenixClientModuleConfiguration.isIpcIsRunning());
	}

	public void hide() {

	}

}
