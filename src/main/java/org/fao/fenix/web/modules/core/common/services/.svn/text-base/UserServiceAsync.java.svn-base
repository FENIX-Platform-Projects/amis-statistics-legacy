package org.fao.fenix.web.modules.core.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.FenixModuleConfigurationVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	
	@SuppressWarnings("unchecked")
	public void update(List<FenixUserVo> vos, AsyncCallback callback);
	
	public void find(String searchString, AsyncCallback<List<FenixUserVo>> callback);

	void login(String username, String password, AsyncCallback<LoginResponseVo> callback);

	void logout(AsyncCallback<String> callback);

	void getUserList(AsyncCallback<List<FenixUserVo>> callback);

	void addUser(FenixUserVo fenixUserVo, AsyncCallback<String> callback);

	void deleteUser(FenixUserVo fenixUserVo, AsyncCallback<String> callback);

	void updateUser(FenixUserVo fenixUserVo, AsyncCallback<String> callback);

	void databasePopulate(String method, AsyncCallback<?> callback);

	void getGroupList(AsyncCallback<String[]> callback);

	void getUsersInGroup(String groupName, AsyncCallback<List<FenixUserVo>> callback);

	void addGroup(String groupName, AsyncCallback<?> callback);

	void deleteGroup(String groupName, AsyncCallback<String> callback);

	void addUser2Group(String userName, String groupName, AsyncCallback<?> callback);

	void deleteUserFromGroup(String user, String groupName, AsyncCallback<Boolean> callback);

	void findAllResources(AsyncCallback<List<FenixResourceVo>> callback);

	void getGroupPermission4Resource(String group, long resourceId, AsyncCallback<Map<String, Boolean>> callback);

	void updateGroupPermissionOnResource(String group, String permission, long resourceId, boolean permissionStatus,
			AsyncCallback<?> callback);

	void updateGroupPermissionOnResources(String group, String permission, long[] resourceIds,
			boolean permissionStatus, AsyncCallback<?> callback);

	void retrieveFenixModuleConfiguration(AsyncCallback<FenixModuleConfigurationVo> callback);

	void getPermissions(long resourceId, AsyncCallback<PermissionVo> callback);

}
