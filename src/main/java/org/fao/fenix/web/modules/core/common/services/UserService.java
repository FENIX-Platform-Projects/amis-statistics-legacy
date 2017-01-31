package org.fao.fenix.web.modules.core.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.FenixModuleConfigurationVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.springframework.security.annotation.Secured;
import org.springmodules.cache.annotations.CacheFlush;

import com.google.gwt.user.client.rpc.RemoteService;

public interface UserService extends RemoteService {
	
	public void update(List<FenixUserVo> vos);
	
	public List<FenixUserVo> find(String searchString);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	LoginResponseVo login(String username, String password);

	@Secured( { "ROLE_USER" })
	void logout();

	@Secured( { "ROLE_ADMIN" })
	List<FenixUserVo> getUserList();

	@Secured( { "ROLE_ADMIN" })
	void addUser(FenixUserVo fenixUserVo);

	@Secured( { "ROLE_ADMIN" })
	void deleteUser(FenixUserVo fenixUserVo);

	@Secured( { "ROLE_ADMIN" })
	void updateUser(FenixUserVo fenixUserVo);

	@Secured( { "ROLE_ADMIN" })
	public void databasePopulate(String method);

	@Secured( { "ROLE_ADMIN" })
	public String[] getGroupList();

	@Secured( { "ROLE_ADMIN" })
	public List<FenixUserVo> getUsersInGroup(String groupName);

	@Secured( { "ROLE_ADMIN" })
	public void addGroup(String groupName);

	@CacheFlush(modelId = "fenixCacheFlushing")
	@Secured( { "ROLE_ADMIN" })
	public String deleteGroup(String groupName);

	@CacheFlush(modelId = "fenixCacheFlushing")
	@Secured( { "ROLE_ADMIN" })
	public void addUser2Group(String userName, String groupName);

	@CacheFlush(modelId = "fenixCacheFlushing")
	@Secured( { "ROLE_ADMIN" })
	public Boolean deleteUserFromGroup(String user, String groupName);
	
	@Secured( { "ROLE_ADMIN" })
	public List<FenixResourceVo> findAllResources();  

	@Secured( { "ROLE_ADMIN" })
	public Map<String, Boolean> getGroupPermission4Resource(String group, long resourceId);

	@CacheFlush(modelId = "fenixCacheFlushing")
	@Secured( { "ROLE_ADMIN" })
	public void updateGroupPermissionOnResource(String group, String permission, long resourceId,
			boolean permissionStatus);

	@CacheFlush(modelId = "fenixCacheFlushing")
	@Secured( { "ROLE_ADMIN" })
	public void updateGroupPermissionOnResources(String group, String permission, long[] resourceIds,
			boolean permissionStatus);

	@Secured( { "ROLE_USER" })
	FenixModuleConfigurationVo retrieveFenixModuleConfiguration();

	public PermissionVo getPermissions(long resourceId);
	
}
