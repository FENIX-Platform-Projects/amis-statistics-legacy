package org.fao.fenix.web.modules.core.client.security.permissionmanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.core.client.security.FenixGoupListController;
import org.fao.fenix.web.modules.core.client.security.FenixRole;
import org.fao.fenix.web.modules.core.client.security.FenixStringValidator;
import org.fao.fenix.web.modules.core.client.security.groupmanager.GroupManagerView;
import org.fao.fenix.web.modules.core.client.security.usermanager.UserManagerView;
import org.fao.fenix.web.modules.core.client.utils.FenixConstants;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PermissionManagerController {

	PermissionManagerView permissionManagerView;
	FenixGoupListController fenixGoupListController;
	PermissionAssociation permissionAssociation = new PermissionAssociation();
	boolean singleModeSelected = true;

	static Set<String> downloadCheckBox = new HashSet<String>();
	static Map<String, String> permissionMap = new HashMap<String, String>();
	static Map<String, Boolean> permissionMapBoolean = new HashMap<String, Boolean>();
	static {
		downloadCheckBox.add(ResourceType.DATASET);
		downloadCheckBox.add(ResourceType.TEXTVIEW);
		downloadCheckBox.add(ResourceType.EXTERNAL_LAYER);
		downloadCheckBox.add(ResourceType.INTERNAL_LAYER);
		downloadCheckBox.add(ResourceType.LAYER);
		downloadCheckBox.add(ResourceType.LAYERSUBTYPE);

		permissionMap.put(PermissionManagerView.giveRead, FenixPermission.READ);
		permissionMap.put(PermissionManagerView.giveWrite, FenixPermission.WRITE);
		permissionMap.put(PermissionManagerView.giveDelete, FenixPermission.DELETE);
		permissionMap.put(PermissionManagerView.giveDownload, FenixPermission.DOWNLOAD);
		permissionMap.put(PermissionManagerView.deleteRead, FenixPermission.READ);
		permissionMap.put(PermissionManagerView.deleteWrite, FenixPermission.WRITE);
		permissionMap.put(PermissionManagerView.deleteDelete, FenixPermission.DELETE);
		permissionMap.put(PermissionManagerView.deleteDownload, FenixPermission.DOWNLOAD);

		permissionMapBoolean.put(PermissionManagerView.giveRead, new Boolean(true));
		permissionMapBoolean.put(PermissionManagerView.giveWrite, new Boolean(true));
		permissionMapBoolean.put(PermissionManagerView.giveDelete, new Boolean(true));
		permissionMapBoolean.put(PermissionManagerView.giveDownload, new Boolean(true));
		permissionMapBoolean.put(PermissionManagerView.deleteRead, new Boolean(false));
		permissionMapBoolean.put(PermissionManagerView.deleteWrite, new Boolean(false));
		permissionMapBoolean.put(PermissionManagerView.deleteDelete, new Boolean(false));
		permissionMapBoolean.put(PermissionManagerView.deleteDownload, new Boolean(false));
	}

	String group;
	long resourceId;

	/**
	 * The instance can either be a PermissionManagerController or PermissionManagerControllerRe
	 */
	protected static PermissionManagerController instance = null;

	protected PermissionManagerController() {
		permissionManagerView = PermissionManagerView.getInstance();
	}

	/**
	 * instantiates an new instance when the instance itself is null or a subclass.
	 * 
	 * @return an instance of this class
	 */
	public static PermissionManagerController getInstance() {
		if (instance == null || (instance.getClass().getSuperclass().equals(PermissionManagerController.class))) {
			instance = new PermissionManagerController();
			instance.build();
		}
		initInstance();
		return instance;
	}

	protected static void initInstance() {
		UserManagerView.getInstance().getWindow().close();
		GroupManagerView.getInstance().getWindow().close();
		instance.loadGroupList();
		instance.unSelectCheckboxGroup();
		instance.permissionManagerView.show();
		UserManagerView.getInstance().getWindow().close();
		instance.loadResourceList();
	}

	protected void unSelectCheckboxGroup() {
		permissionManagerView.readPermissionCheckBox.setValue(false);
		permissionManagerView.writePermissionCheckBox.setValue(false);
		permissionManagerView.deletePermissionCheckBox.setValue(false);
		permissionManagerView.downloadPermissionCheckBox.setValue(false);
	}

	protected void loadGroupList() {
		UserServiceEntry.getInstance().getGroupList(new AsyncCallback<String[]>() {
			public void onSuccess(String[] groupList) {
				String[] groupArrayPlus = new String[groupList.length + 1];
				groupArrayPlus[0] = FenixRole.ANONYMOUS;
				int i = 1;
				for (String group : groupList) {
					groupArrayPlus[i++] = group;
				}
				fenixGoupListController.updateGroupList(groupArrayPlus);
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
			}
		});
	}

	protected void loadResourceList() {
		UserServiceEntry.getInstance().findAllResources(new AsyncCallback<List<FenixResourceVo>>() {
			public void onSuccess(List<FenixResourceVo> resourceList) {
				permissionManagerView.resourceDataList.removeAll();
				addFenixResourceVoList2ResourceDataList(resourceList);
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
			}
		});
	}

	public void build() {
		setEvents();
		fenixGoupListController = new FenixGoupListController(permissionManagerView.groupDataList);
	}

	private void setEvents() {
		permissionManagerView.resourceDataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {

				if (permissionManagerView.resourceDataList.getSelectedItems().size() > 1) {
					if (singleModeSelected)
						single2Multiple();
				} else {
					if (!singleModeSelected)
						multiple2Single();
					updateCheckBoxes();
				}

			}

		});
		permissionManagerView.groupDataList.addListener(Events.SelectionChange, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				permissionManagerView.permissionActionComboBox.enable();
				permissionManagerView.applyPermissionActionButton.enable();

				DataListItem groupDataListItem = permissionManagerView.groupDataList.getSelectedItem();
				if (groupDataListItem != null && groupDataListItem.getData(FenixConstants.VO) != null) {
					// only when there is are groups
					group = groupDataListItem.getData(FenixConstants.VO);
				} else {
					group = null;
				}

				if (singleModeSelected)
					updateCheckBoxes();
				else if (group != null && group.equals(FenixRole.ANONYMOUS)) {
					if (permissionManagerView.permissionActionComboBox.getStore().getCount() > 2) {
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.giveWriteModel);
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.giveDeleteModel);
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.giveDownloadModel);
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.deleteWriteModel);
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.deleteDeleteModel);
						permissionManagerView.permissionActionComboBox.getStore().remove(
								PermissionManagerView.deleteDownloadModel);
					}
				} else {
					if (permissionManagerView.permissionActionComboBox.getStore().getCount() == 2) {
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.giveWriteModel);
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.giveDeleteModel);
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.giveDownloadModel);
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.deleteWriteModel);
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.deleteDeleteModel);
						permissionManagerView.permissionActionComboBox.getStore().add(
								PermissionManagerView.deleteDownloadModel);
					}
				}

			}
		});

		permissionManagerView.readPermissionCheckBox.addListener(Events.OnClick, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent event) {
				// System.out.println("group = " + group);
				// System.out.println("FenixPermission.READ = " + FenixPermission.READ);
				// System.out.println("permissionManagerView.readPermissionCheckBox.getValue() = "
				// + permissionManagerView.readPermissionCheckBox.getValue());
				updateCheckBoxes(FenixPermission.READ, permissionManagerView.readPermissionCheckBox.getValue());
				UserServiceEntry.getInstance().updateGroupPermissionOnResource(group, FenixPermission.READ, resourceId,
						permissionManagerView.readPermissionCheckBox.getValue(), new AsyncCallback<String[]>() {
							public void onSuccess(String[] groupList) {
								Info.display("Permission", "Permission updated");
								updateCheckBoxes();
							}

							public void onFailure(Throwable caught) {
								permissionManagerView.readPermissionCheckBox
										.setValue(!permissionManagerView.readPermissionCheckBox.getValue());
								updateCheckBoxes();
							}
						});
			}
		});

		permissionManagerView.writePermissionCheckBox.addListener(Events.OnClick, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent event) {
				// System.out.println("group = " + group);
				// System.out.println("FenixPermission.WRITE = " + FenixPermission.WRITE);
				// System.out.println("permissionManagerView.writePermissionCheckBox.getValue() = "
				// + permissionManagerView.writePermissionCheckBox.getValue());
				updateCheckBoxes(FenixPermission.WRITE, permissionManagerView.writePermissionCheckBox.getValue());
				UserServiceEntry.getInstance().updateGroupPermissionOnResource(group, FenixPermission.WRITE,
						resourceId, permissionManagerView.writePermissionCheckBox.getValue(),
						new AsyncCallback<String[]>() {
							public void onSuccess(String[] groupList) {
								Info.display("Permission", "Permission updated");
								updateCheckBoxes();
							}

							public void onFailure(Throwable caught) {
								permissionManagerView.writePermissionCheckBox
										.setValue(!permissionManagerView.writePermissionCheckBox.getValue());
								updateCheckBoxes();
							}
						});
			}
		});
		permissionManagerView.deletePermissionCheckBox.addListener(Events.OnClick, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent event) {
				// System.out.println("group = " + group);
				// System.out.println("FenixPermission.DELETE = " + FenixPermission.DELETE);
				// System.out.println("permissionManagerView.deletePermissionCheckBox.getValue() = "
				// + permissionManagerView.deletePermissionCheckBox.getValue());
				updateCheckBoxes(FenixPermission.DELETE, permissionManagerView.deletePermissionCheckBox.getValue());
				UserServiceEntry.getInstance().updateGroupPermissionOnResource(group, FenixPermission.DELETE,
						resourceId, permissionManagerView.deletePermissionCheckBox.getValue(),
						new AsyncCallback<String[]>() {
							public void onSuccess(String[] groupList) {
								Info.display("Permission", "Permission updated");
								updateCheckBoxes();
							}

							public void onFailure(Throwable caught) {
								permissionManagerView.deletePermissionCheckBox
										.setValue(!permissionManagerView.deletePermissionCheckBox.getValue());
								updateCheckBoxes();
							}
						});
			}
		});
		permissionManagerView.downloadPermissionCheckBox.addListener(Events.OnClick, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent event) {
				// System.out.println("group = " + group);
				// System.out.println("FenixPermission.DOWNLOAD = " + FenixPermission.DOWNLOAD);
				// System.out.println("permissionManagerView.downloadPermissionCheckBox.getValue() = "
				// + permissionManagerView.downloadPermissionCheckBox.getValue());
				updateCheckBoxes(FenixPermission.DOWNLOAD, permissionManagerView.downloadPermissionCheckBox.getValue());
				UserServiceEntry.getInstance().updateGroupPermissionOnResource(group, FenixPermission.DOWNLOAD,
						resourceId, permissionManagerView.downloadPermissionCheckBox.getValue(),
						new AsyncCallback<String[]>() {
							public void onSuccess(String[] groupList) {
								Info.display("Permission", "Permission updated");
								updateCheckBoxes();
							}

							public void onFailure(Throwable caught) {
								permissionManagerView.downloadPermissionCheckBox
										.setValue(!permissionManagerView.downloadPermissionCheckBox.getValue());
								updateCheckBoxes();
							}
						});
			}
		});

		permissionManagerView.applyPermissionActionButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				List<DataListItem> slist = permissionManagerView.resourceDataList.getSelectedItems();
				long resourceIds[] = new long[slist.size()];
				int i = 0;
				for (DataListItem dataListItem : slist) {
					FenixResourceVo vo = dataListItem.getData(FenixConstants.VO);
					resourceIds[i++] = new Long(vo.getId()).longValue();
				}
				PDDModel p = permissionManagerView.permissionActionComboBox.getValue();
				String permissionAction = p.getLabel();
				final LoadingWindow updating = new LoadingWindow("", "Updating Permissions", "..in progress..");

				UserServiceEntry.getInstance().updateGroupPermissionOnResources(group,
						permissionMap.get(permissionAction), resourceIds,
						permissionMapBoolean.get(permissionAction).booleanValue(), new AsyncCallback<String[]>() {
							public void onSuccess(String[] groupList) {
								Info.display("Permissions", "Permissions updated");
								updating.destroyLoadingBox();
							}

							public void onFailure(Throwable caught) {
								Info.display("Permissions", "Permissions NOT updated");
								updating.destroyLoadingBox();
							}
						});

			}
		});
	}

	/**
	 * This method only exits to improve the user experience. It gives fast feedback to the user
	 * about the associated permissions.
	 */
	protected void updateCheckBoxes(String fenixPermission, Boolean value) {
		List<String> apList = permissionAssociation.getAssociatedPermission(fenixPermission, value.booleanValue());
		for (String associatedPermission : apList) {
			if (associatedPermission.equals(FenixPermission.READ))
				permissionManagerView.readPermissionCheckBox.setValue(value);
			if (associatedPermission.equals(FenixPermission.WRITE))
				permissionManagerView.writePermissionCheckBox.setValue(value);
			if (associatedPermission.equals(FenixPermission.DELETE))
				permissionManagerView.deletePermissionCheckBox.setValue(value);
			if (associatedPermission.equals(FenixPermission.DOWNLOAD))
				permissionManagerView.downloadPermissionCheckBox.setValue(value);
		}
	}

	void updateCheckBoxes() {

		permissionManagerView.htmlNote.setHTML("");

		DataListItem resourceDataListItem = permissionManagerView.resourceDataList.getSelectedItem();
		DataListItem groupDataListItem = permissionManagerView.groupDataList.getSelectedItem();
		if (resourceDataListItem != null && groupDataListItem != null) {
			FenixResourceVo vo = resourceDataListItem.getData(FenixConstants.VO);
			group = groupDataListItem.getData(FenixConstants.VO);
			if (FenixStringValidator.filled(vo.getId()) && FenixStringValidator.filled(group)) {
				permissionManagerView.permissionCheckBoxGroup.enable();
				permissionManagerView.readPermissionCheckBox.enable();
				permissionManagerView.deletePermissionCheckBox.setEnabled(!group.equals(FenixRole.ANONYMOUS));
				permissionManagerView.downloadPermissionCheckBox.setEnabled(!group.equals(FenixRole.ANONYMOUS)
						&& downloadCheckBox.contains(vo.getType()));
				permissionManagerView.writePermissionCheckBox.setEnabled(!group.equals(FenixRole.ANONYMOUS));
				resourceId = new Long(vo.getId()).longValue();
				UserServiceEntry.getInstance().getGroupPermission4Resource(group, resourceId,
						new AsyncCallback<Map<String, Boolean>>() {
							public void onSuccess(Map<String, Boolean> map) {
								permissionManagerView.readPermissionCheckBox.setValue(map.get(FenixPermission.READ));
								permissionManagerView.writePermissionCheckBox.setValue(map.get(FenixPermission.WRITE));
								permissionManagerView.deletePermissionCheckBox
										.setValue(map.get(FenixPermission.DELETE));
								permissionManagerView.downloadPermissionCheckBox.setValue(map
										.get(FenixPermission.DOWNLOAD));
							}

							public void onFailure(Throwable caught) {
								Info.display(BabelFish.print().generalServerProblem(), caught
										.getLocalizedMessage());
							}
						});

				String message = "Group <b>" + permissionManagerView.groupDataList.getSelectedItem().getText()
						+ "</b>  has for resource <b>" + vo.getTitle() + "</b> these permissions:";
				permissionManagerView.statusHTML.setHTML(message);

			} else {
				permissionManagerView.permissionCheckBoxGroup.disable();
				permissionManagerView.readPermissionCheckBox.disable();
				permissionManagerView.deletePermissionCheckBox.disable();
				permissionManagerView.downloadPermissionCheckBox.disable();
				permissionManagerView.writePermissionCheckBox.disable();
			}

		}
	}

	private void multiple2Single() {
		singleModeSelected = true;
		permissionManagerView.permissionCheckBoxGroup.setVisible(true);
		permissionManagerView.multiplePermissionVerticalPanel.setVisible(false);
	}

	private void single2Multiple() {
		singleModeSelected = false;
		permissionManagerView.statusHTML
				.setHTML("You can select more resources (only one group) and give/remove permission(s)");
		permissionManagerView.multiplePermissionVerticalPanel.setVisible(true);
		permissionManagerView.permissionCheckBoxGroup.setVisible(false);
	}

	protected void addFenixResourceVoList2ResourceDataList(List<FenixResourceVo> fenixResourceVoList) {
		for (FenixResourceVo fenixResourceVo : fenixResourceVoList) {
			if (!fenixResourceVo.getTitle().equals("") && !fenixResourceVo.getType().equals(ResourceType.COMMUNICATION)) {
				DataListItem item = new DataListItem();
				item.setText(fenixResourceVo.getTitle());
				item.setIconStyle(ResourceType.resourceIconMap.get(fenixResourceVo.getType()));

				if (item.getIconStyle() == null)
					item.setIconStyle(ResourceType.resourceIconMap.get(ResourceType.DEFAULT));
				item.setData(FenixConstants.VO, fenixResourceVo);

				permissionManagerView.resourceDataList.add(item);
			}
		}
	}

}
