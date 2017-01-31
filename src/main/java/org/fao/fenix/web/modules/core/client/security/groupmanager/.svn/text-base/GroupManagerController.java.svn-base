package org.fao.fenix.web.modules.core.client.security.groupmanager;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.security.FenixGoupListController;
import org.fao.fenix.web.modules.core.client.security.FenixRole;
import org.fao.fenix.web.modules.core.client.security.FenixSecurityConstant;
import org.fao.fenix.web.modules.core.client.security.FenixStringValidator;
import org.fao.fenix.web.modules.core.client.security.FenixUserListController;
import org.fao.fenix.web.modules.core.client.security.permissionmanager.PermissionManagerView;
import org.fao.fenix.web.modules.core.client.security.usermanager.UserManagerView;
import org.fao.fenix.web.modules.core.client.utils.FenixConstants;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GroupManagerController {

	GroupManagerView groupManagerView;
	FenixUserListController fenixUserListController;
	FenixUserListController fenixSelectedUserListController;
	FenixGoupListController fenixGoupListController;
	SelectionListener<ComponentEvent> addUserButtonSelected;
	SelectionListener<ComponentEvent> deleteUserButtonSelected;

	DataListItem selectedGroupDataListItem;
	String selectedGroup;

	private static GroupManagerController instance = null;

	protected GroupManagerController() {
		groupManagerView = GroupManagerView.getInstance();
	}

	public static GroupManagerController getInstance() {
		if (instance == null) {
			instance = new GroupManagerController();
			instance.build();
		}
		UserManagerView.getInstance().getWindow().close();
		PermissionManagerView.getInstance().getWindow().close();
		instance.loadGroupList();
		instance.groupManagerView.show();
		return instance;
	}

	private void loadGroupList() {
		groupManagerView.groupDataList.disableEvents(true);
		groupManagerView.userDataList.removeAll();
		groupManagerView.selectedUserDataList.removeAll();
		moveButtonsVisible(false);
		UserServiceEntry.getInstance().getGroupList(new AsyncCallback<String[]>() {
			public void onSuccess(String[] groupArray) {
				String[] groupArrayWithoutAllUserGroup = new String[groupArray.length - 1];
				int i = 0;
				for (String group : groupArray) {
					if (!group.equals(FenixSecurityConstant.ALL_USER_GROUP))
						groupArrayWithoutAllUserGroup[i++] = group;
				}
				fenixGoupListController.updateGroupList(groupArrayWithoutAllUserGroup);
				groupManagerView.groupDataList.enableEvents(true);
				setNumberOfGroupsFound();
				instance.selectGroup("");
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
			}
		});
	}

	public void build() {
		setEvents();
		fenixUserListController = new FenixUserListController(groupManagerView.userDataList);
		fenixSelectedUserListController = new FenixUserListController(groupManagerView.selectedUserDataList);
		fenixGoupListController = new FenixGoupListController(groupManagerView.groupDataList);

	}

	private void setEvents() {
		addGroupDataListListener();

		groupManagerView.addGroupButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				final String groupName = groupManagerView.groupNameTextField.getValue();
				if (FenixStringValidator.filled(groupName)) {
					List<DataListItem> groupList = groupManagerView.groupDataList.getItems();
					boolean alreadyExists = false;
					for (DataListItem dataListItem : groupList) {
						if (dataListItem.getData(FenixConstants.VO).equals(groupName))
							alreadyExists = true;
					}
					if (alreadyExists) {
						setGroupAlreadyExistsHTML("Group already exists");
					} else {
						groupManagerView.groupAlreadyExistsHTML.setHTML("");
						UserServiceEntry.getInstance().addGroup(groupName, new AsyncCallback<String>() {
							public void onSuccess(String result) {
								DataListItem item = new DataListItem();
								item.setText(groupName);
								item.setData(FenixConstants.VO, groupName);
								item.setIconStyle("user_group");
								groupManagerView.groupDataList.add(item);
								groupManagerView.addGroupButton.disable();
								setNumberOfGroupsFound();
								selectGroup(groupName);
							}

							public void onFailure(Throwable caught) {
								Info.display(BabelFish.print().generalServerProblem(), caught
										.getLocalizedMessage());
							}
						});
					}
				} else
					setGroupAlreadyExistsHTML("No emptyness here");
			}
		});

		groupManagerView.groupNameTextField.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				groupManagerView.addGroupButton.enable();
				groupManagerView.deleteGroupButton.disable();
				moveButtonsVisible(false);

				groupManagerView.userDataList.disableEvents(true);
				groupManagerView.userDataList.removeAll();
				groupManagerView.selectedUserDataList.disableEvents(true);
				groupManagerView.selectedUserDataList.removeAll();
			}
		});

		groupManagerView.deleteGroupButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				selectedGroupDataListItem = groupManagerView.groupDataList.getSelectedItem();
				final String groupName = selectedGroupDataListItem.getData(FenixConstants.VO);
				UserServiceEntry.getInstance().deleteGroup(groupName, new AsyncCallback<String>() {
					public void onSuccess(String disabledUsers) {
						groupManagerView.groupDataList.disableEvents(true);
						groupManagerView.groupDataList.remove(selectedGroupDataListItem);
						groupManagerView.deleteGroupButton.disable();
						groupManagerView.addGroupButton.enable();
						groupManagerView.groupDataList.enableEvents(true);
						groupManagerView.userDataList.disableEvents(true);
						groupManagerView.userDataList.removeAll();
						groupManagerView.selectedUserDataList.disableEvents(true);
						groupManagerView.selectedUserDataList.removeAll();
						moveButtonsVisible(false);
						setNumberOfGroupsFound();
						if (disabledUsers.length() > 0) {
							String message = "Attention! Users(s) have been disabled because they don't belong anymore to a group (group <b>"
									+ groupName
									+ "</b> was the only group they belonged to and it has been deleted). These user(s) have been disabled: <b> "
									+ disabledUsers.substring(1, disabledUsers.length())
									+ "</b>. It is advised to add those users to a group.";
							setStatusHTML(message);
						}
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught
								.getLocalizedMessage());
					}
				});
			}
		});

		groupManagerView.moveRightButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				DataListItem selectedGroup = groupManagerView.groupDataList.getSelectedItem();
				String selectedGroupName = selectedGroup.getData(FenixConstants.VO);
				final DataListItem selectedUser = groupManagerView.userDataList.getSelectedItem();
				moveRight(selectedGroupName, selectedUser);
				setNumberOfUsersSelected(groupManagerView.selectedUserDataList.getItems().size());
			}
		});

		groupManagerView.moveAllRightButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				DataListItem selectedGroup = groupManagerView.groupDataList.getSelectedItem();
				String selectedGroupName = selectedGroup.getData(FenixConstants.VO);
				for (final DataListItem selectedUser : groupManagerView.userDataList.getItems()) {
					moveRight(selectedGroupName, selectedUser);
				}
				setNumberOfUsersSelected(groupManagerView.selectedUserDataList.getItems().size());
			}
		});

		groupManagerView.moveLeftButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				DataListItem selectedGroup = groupManagerView.groupDataList.getSelectedItem();
				String selectedGroupName = selectedGroup.getData(FenixConstants.VO);
				final DataListItem selectedUser = groupManagerView.selectedUserDataList.getSelectedItem();
				moveLeft(selectedGroupName, selectedUser);
			}
		});

		groupManagerView.moveAllLeftButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				DataListItem selectedGroup = groupManagerView.groupDataList.getSelectedItem();
				String selectedGroupName = selectedGroup.getData(FenixConstants.VO);
				for (final DataListItem selectedUser : groupManagerView.selectedUserDataList.getItems()) {
					moveLeft(selectedGroupName, selectedUser);
				}
			}
		});

		groupManagerView.userDataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				groupManagerView.moveRightButton
						.setEnabled(groupManagerView.userDataList.getSelectedItems().size() == 1);
			}
		});
		groupManagerView.selectedUserDataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				groupManagerView.moveLeftButton.setEnabled(groupManagerView.selectedUserDataList.getSelectedItems()
						.size() == 1);
			}
		});

	}

	private void setGroupAlreadyExistsHTML(String message) {
		message = "<span style='color: rgb(255, 0, 0);'>" + message + "</span>";
		groupManagerView.groupAlreadyExistsHTML.setHTML(message);
	}

	void setMoveButtons() {
		groupManagerView.moveAllLeftButton.setEnabled(groupManagerView.selectedUserDataList.getItems().size() > 0);
		groupManagerView.moveAllRightButton.setEnabled(groupManagerView.userDataList.getItems().size() > 0);
	}

	void moveRight(String selectedGroupName, final DataListItem selectedUser) {
		final FenixUserVo fenixUserVo = (FenixUserVo) selectedUser.getData(FenixConstants.VO);
		UserServiceEntry.getInstance().addUser2Group(fenixUserVo.getLoginName(), selectedGroupName,
				new AsyncCallback<String>() {
					public void onSuccess(String result) {
						groupManagerView.userDataList.remove(selectedUser);
						fenixUserVo.setEnabled(true);
						fenixSelectedUserListController.addUser2DataList(fenixUserVo);
						setNumberOfUsersSelected(groupManagerView.selectedUserDataList.getItems().size());
						setMoveButtons();
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught
								.getLocalizedMessage());
					}
				});

	}

	void moveLeft(String selectedGroupName, final DataListItem selectedUser) {
		final FenixUserVo fenixUserVo = (FenixUserVo) selectedUser.getData(FenixConstants.VO);
		UserServiceEntry.getInstance().deleteUserFromGroup(fenixUserVo.getLoginName(), selectedGroupName,
				new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean userEnabled) {
						fenixUserVo.setEnabled(userEnabled);
						fenixUserListController.addUser2DataList(fenixUserVo);
						groupManagerView.selectedUserDataList.remove(selectedUser);
						setNumberOfUsersSelected(groupManagerView.selectedUserDataList.getItems().size());
						setMoveButtons();
					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught
								.getLocalizedMessage());
					}
				});
	}

	void addGroupDataListListener() {
		groupManagerView.groupDataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				moveButtonsVisible(true);
				groupManagerView.userDataList.enableEvents(true);
				groupManagerView.selectedUserDataList.enableEvents(true);
				groupManagerView.addGroupButton.disable();
				groupManagerView.groupAlreadyExistsHTML.setHTML("");
				DataList l = (DataList) ce.getComponent();
				selectedGroupDataListItem = l.getSelectedItem();
				selectedGroup = selectedGroupDataListItem.getData(FenixConstants.VO);

				if (FenixRole.roleSet.contains(selectedGroup))
					groupManagerView.deleteGroupButton.disable();
				else
					groupManagerView.deleteGroupButton.enable();

				UserServiceEntry.getInstance().getUserList(new AsyncCallback<List<FenixUserVo>>() {
					public void onSuccess(final List<FenixUserVo> fullUserList) {
						UserServiceEntry.getInstance().getUsersInGroup(selectedGroup,
								new AsyncCallback<List<FenixUserVo>>() {
									public void onSuccess(List<FenixUserVo> userInGroupList) {
										List<FenixUserVo> newList = new ArrayList<FenixUserVo>();
										for (FenixUserVo fenixUserVo : fullUserList) {
											boolean found = false;
											for (FenixUserVo fenixUserVo2 : userInGroupList) {
												if (fenixUserVo.getLoginName().equals(fenixUserVo2.getLoginName())) {
													found = true;
												}
											}
											if (!found)
												newList.add(fenixUserVo);
										}
										fenixUserListController.updateUserList(newList);
										fenixSelectedUserListController.updateUserList(userInGroupList);
										setNumberOfUsersSelected(groupManagerView.selectedUserDataList.getItems()
												.size());
										setMoveButtons();
									}

									public void onFailure(Throwable caught) {
										Info.display(BabelFish.print().generalServerProblem(), caught
												.getLocalizedMessage());
									}
								});

					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught
								.getLocalizedMessage());
					}
				});
			}
		});
	}

	void moveButtonsVisible(boolean visibleYes) {
		if (visibleYes) {
			groupManagerView.moveLeftButton.show();
			groupManagerView.moveRightButton.show();
			groupManagerView.moveAllLeftButton.show();
			groupManagerView.moveAllRightButton.show();
		} else {
			groupManagerView.moveRightButton.hide();
			groupManagerView.moveLeftButton.hide();
			groupManagerView.moveAllLeftButton.hide();
			groupManagerView.moveAllRightButton.hide();
		}
	}

	void setNumberOfGroupsFound() {
		int numberOfGroupsFound = groupManagerView.groupDataList.getItems().size();
		String message = "Found  <b> " + numberOfGroupsFound + "</b> group(s) ";
		setStatusHTML(message);
	}

	void setNumberOfUsersSelected(int numberOfUsersSelected) {
		String message = "The group <b>" + groupManagerView.groupDataList.getSelectedItem().getText() + "</b> has <b> "
				+ numberOfUsersSelected + "</b>  users selected";
		setStatusHTML(message);
	}

	private void setStatusHTML(String message) {
		// message = "<span style='color: rgb(255, 0, 0);'>" + message + "</span>";
		groupManagerView.statusHTML.setHTML(message);
	}

	private void selectGroup(String group) {
		if (groupManagerView.groupDataList.getItems().size() > 0 && group.equals(""))
			// only when there is something to select
			groupManagerView.groupDataList.setSelectedItem(groupManagerView.groupDataList.getItems().get(0));
		else {
			List<DataListItem> items = groupManagerView.groupDataList.getItems();
			for (DataListItem dataListItem : items) {
				if (dataListItem.getData(FenixConstants.VO).equals(group))
					groupManagerView.groupDataList.setSelectedItem(dataListItem);
			}
		}
	}

}
