package org.fao.fenix.web.modules.core.server.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.perspective.FenixDomainUser;
import org.fao.fenix.core.domain.security.FenixDoubleUser;
import org.fao.fenix.core.domain.security.FenixGroup;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixRole;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.modules.framework.FenixModuleConfiguration;
import org.fao.fenix.core.persistence.ResourceDao;
import org.fao.fenix.core.persistence.perspective.FenixDomainUserDao;
import org.fao.fenix.core.persistence.security.FenixDoubleUserManager;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.security.FenixSecurityUser;
import org.fao.fenix.core.persistence.security.ResourcePermission;
import org.fao.fenix.web.modules.core.common.services.UserService;
import org.fao.fenix.web.modules.core.common.vo.FenixModuleConfigurationVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.re.server.Domain2VoConverter;
import org.gwtwidgets.server.spring.ServletUtils;
import org.hibernate.annotations.Persister;
import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.GroupManager;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

	private FenixLogin fenixLogin;

	private FenixDomainUserDao fenixDomainUserDao;

	private FenixDoubleUserManager fenixDoubleUserManager;

	private Persister persister;

	private UserDetailsManager userDetailsManager;

	private FenixPermissionManager fenixPermissionManager;

	private ResourceDao resourceDao;

	private FenixModuleConfiguration fenixModuleConfiguration;

	private static Map<String, GrantedAuthority> grantedAuthorityMap = new HashMap<String, GrantedAuthority>();
	
	private static Map<FenixPermission, String> permissionMap = new HashMap<FenixPermission, String>();
	
	static {
		grantedAuthorityMap.put(FenixRole.USER, new GrantedAuthorityImpl(FenixRole.USER));
		grantedAuthorityMap.put(FenixRole.ADMIN, new GrantedAuthorityImpl(FenixRole.ADMIN));
		grantedAuthorityMap.put(FenixRole.FPI, new GrantedAuthorityImpl(FenixRole.FPI));
		grantedAuthorityMap.put(FenixRole.CCBS, new GrantedAuthorityImpl(FenixRole.CCBS));
		grantedAuthorityMap.put(FenixRole.IPC, new GrantedAuthorityImpl(FenixRole.IPC));
		permissionMap.put(FenixPermission.READ, org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.READ);
		permissionMap.put(FenixPermission.WRITE, org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.WRITE);
		permissionMap.put(FenixPermission.DELETE, org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.DELETE);
		permissionMap.put(FenixPermission.DOWNLOAD, org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.DOWNLOAD);
	}
	
	public void update(List<FenixUserVo> vos) {
		List<FenixDomainUser> users = new ArrayList<FenixDomainUser>();
		for (FenixUserVo vo : vos)
			users.add(vo2user(vo));
		for (FenixDomainUser u : users)
			fenixDomainUserDao.updateProperties(u);
	}

	public List<FenixUserVo> find(String searchString) {
		List<FenixUserVo> vos = new ArrayList<FenixUserVo>();
		List<FenixDomainUser> users = fenixDomainUserDao.find(searchString);
		for (FenixDomainUser u : users)
			vos.add(user2vo(u));
		return vos;
	}
	
	private FenixUserVo user2vo(FenixDomainUser u) {
		FenixUserVo vo = new FenixUserVo();
		vo.setEmail(u.getEmail());
		vo.setFirstName(u.getFirstName());
		vo.setLanguage(u.getLanguage());
		vo.setLastName(u.getLastName());
		vo.setLoginName(u.getName());
		vo.setPassword("");
		return vo;
	}
	
	private FenixDomainUser vo2user(FenixUserVo vo) {
		FenixDomainUser u = new FenixDomainUser();
		u.setEmail(vo.getEmail());
		u.setFirstName(vo.getFirstName());
		u.setLanguage(vo.getLanguage());
		u.setLastName(vo.getLastName());
		u.setName(vo.getLoginName());
		return u;
	}

	public LoginResponseVo login(String username, String password) {
		LoginResponseVo vo = new LoginResponseVo();
		try {
			vo.setSucceeded(fenixLogin.login(username, password));
			UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
			FenixDomainUser fenixDomainUser = fenixDomainUserDao.findByName(username);
			FenixDoubleUser du = new FenixDoubleUser();
			du.setSpringSecurityUser(userDetails);
			du.setFenixDomainUser(fenixDomainUser);
			vo.setFenixUserVo(convertDo2Vo(du));
		} catch (UsernameNotFoundException e) {
			vo.setSucceeded(false);
			vo.setResponseMessage(e.getClass().getSimpleName());
		} catch (BadCredentialsException e) {
			vo.setSucceeded(false);
			vo.setResponseMessage(e.getClass().getSimpleName());
		} catch (AuthenticationException failed) {
			vo.setSucceeded(false);
			vo.setResponseMessage(failed.getClass().getSimpleName());
		}
		return vo;
	}

	public void logout() {
		HttpServletRequest request = ServletUtils.getRequest();
		HttpServletResponse response = ServletUtils.getResponse();
		fenixLogin.logout(request, response);
	}

	public void addUser(FenixUserVo fenixUserVo) {
		// precondition
		// TODO add validators, all fields must be filled
		fenixUserVo.setEnabled(false);

		// logic
		fenixDoubleUserManager.save(convertVo2Do(fenixUserVo));
		GroupManager groupManager = (GroupManager) userDetailsManager;
		groupManager.addUserToGroup(fenixUserVo.getLoginName(), FenixSecurityConstant.ALL_USER_GROUP);
	}

	public void deleteUser(FenixUserVo fenixUserVo) {
		// first delete user from all groups
		GroupManager groupManager = (GroupManager) userDetailsManager;
		String[] groups = groupManager.findAllGroups();
		for (String group : groups) {
			String users[] = groupManager.findUsersInGroup(group);
			for (String user : users) {
				if (fenixUserVo.getLoginName().equals(user))
					groupManager.removeUserFromGroup(user, group);
			}
		}
		// then delete the user
		FenixDomainUser fenixDomainUser = fenixDomainUserDao.findByName(fenixUserVo.getLoginName());
		UserDetails userDetails = userDetailsManager.loadUserByUsername(fenixUserVo.getLoginName());
		FenixDoubleUser fenixDoubleUser = new FenixDoubleUser();
		fenixDoubleUser.setFenixDomainUser(fenixDomainUser);
		fenixDoubleUser.setSpringSecurityUser(userDetails);
		fenixDoubleUserManager.delete(fenixDoubleUser);
	}

	private FenixUserVo convertDo2Vo(FenixDoubleUser du) {
		FenixUserVo fenixUserVo = new FenixUserVo();
		fenixUserVo.setFirstName(du.getFenixDomainUser().getFirstName());
		fenixUserVo.setLastName(du.getFenixDomainUser().getLastName());
		fenixUserVo.setLoginName(du.getSpringSecurityUser().getUsername());
		fenixUserVo.setPassword("xxxxxxx");
		fenixUserVo.setEnabled(du.getSpringSecurityUser().isEnabled());
		GrantedAuthority[] grants = du.getSpringSecurityUser().getAuthorities();
		String[] grantArray = new String[grants.length];
		for (int i = 0; i < grants.length; i++) {
			GrantedAuthority grantedAuthority = grants[i];
			grantArray[i] = grantedAuthority.getAuthority();
		}
		fenixUserVo.setRoles(grantArray);
		return fenixUserVo;
	}

	public void updateUser(FenixUserVo fenixUserVo) {

	}

	private FenixDoubleUser convertVo2Do(FenixUserVo fenixUserVo) {

		String username = fenixUserVo.getLoginName();
		String password = fenixUserVo.getPassword();
		boolean enabled = fenixUserVo.isEnabled();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = false;

		String[] grants = fenixUserVo.getRoles();
		Collection<GrantedAuthority> listGrants = new ArrayList<GrantedAuthority>();
		for (String grant : grants) {
			if (FenixRole.isValidFormalRole(grant)) {
				listGrants.add(grantedAuthorityMap.get(grant));
			} else {
				throw new FenixException("Invalid assigned role : " + grant);
			}
		}
		if (listGrants.size() == 0) {
			throw new FenixException("User has 0 roles, should at least have 1 role");
		}
		GrantedAuthority[] authoritiesArray = new GrantedAuthority[listGrants.size()];
		GrantedAuthority[] authorities = (GrantedAuthority[]) listGrants.toArray(authoritiesArray);
		UserDetails user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		FenixDomainUser fenixDomainUser = new FenixDomainUser();
		fenixDomainUser.setName(fenixUserVo.getLoginName());
		fenixDomainUser.setFirstName(fenixUserVo.getFirstName());
		fenixDomainUser.setLastName(fenixUserVo.getLastName());

		FenixDoubleUser fenixDoubleUser = new FenixDoubleUser();
		fenixDoubleUser.setSpringSecurityUser(user);
		fenixDoubleUser.setFenixDomainUser(fenixDomainUser);
		return fenixDoubleUser;
	}

	public void databasePopulate(String method) {
		Method persisterMethod;
		try {
			persisterMethod = Persister.class.getDeclaredMethod(method);
			persisterMethod.invoke(persister);
		} catch (SecurityException e) {
			throw new FenixException(e);
		} catch (NoSuchMethodException e) {
			throw new FenixException(e);
		} catch (IllegalArgumentException e) {
			throw new FenixException(e);
		} catch (IllegalAccessException e) {
			throw new FenixException(e);
		} catch (InvocationTargetException e) {
			throw new FenixException(e);
		}
	}

	public String[] getGroupList() {
		GroupManager groupManager = (GroupManager) userDetailsManager;
		return groupManager.findAllGroups();
	}

	/*
	 * The users in this list do have only a username, name and lastName.
	 * FenixUserVo is also used here as the valueObject.
	 */

	public List<FenixUserVo> getUserList() {
		List<FenixDoubleUser> ddList = fenixDoubleUserManager.findAll();
		List<FenixUserVo> userList = new ArrayList<FenixUserVo>();
		for (FenixDoubleUser fenixDoubleUser : ddList) {
			FenixUserVo fenixUserVo = convertDo2Vo(fenixDoubleUser);
			userList.add(fenixUserVo);
		}
		return userList;
	}

	public List<FenixUserVo> getUsersInGroup(String groupName) {
		GroupManager groupManager = (GroupManager) userDetailsManager;
		String usersFound[] = groupManager.findUsersInGroup(groupName);
		List<FenixUserVo> userList = new ArrayList<FenixUserVo>();
		for (String username : usersFound) {
			FenixDoubleUser fenixDoubleUser = fenixDoubleUserManager.findByName(username);
			FenixUserVo fenixUserVo = convertDo2Vo(fenixDoubleUser);
			// fenixUserVo.setId(fenixDoubleUser.getFenixDomainUser().getId());
			userList.add(fenixUserVo);
		}
		return userList;
	}

	public void addGroup(String groupName) {
		FenixGroup group = new FenixGroup(groupName);
		GroupManager groupManager = (GroupManager) userDetailsManager;
		String[] groups = groupManager.findAllGroups();
		for (String groupFound : groups) {
			if (groupFound.equals(groupName)) {
				throw new FenixException("Group with the same name already exists! ");
			}
		}
		groupManager.createGroup(group.getGroupName(), group.getAuthorities());
	}

	public void addUser2Group(String userName, String groupName) {
		GroupManager groupManager = (GroupManager) userDetailsManager;
		groupManager.addUserToGroup(userName, groupName);
		UserDetails u = userDetailsManager.loadUserByUsername(userName);
		FenixSecurityUser fsuser = new FenixSecurityUser(u);
		fsuser.setEnabled(true);
		userDetailsManager.updateUser(fsuser);
		if (!userDetailsManager.loadUserByUsername(userName).isEnabled()) {
			throw new FenixException("Inconsistent behavior detected, user should be enabled by now");
		}
	}

	/**
	 * Disable the user when its last group has been deleted.
	 * 
	 * @return the list of users in a String, space separated, which have been
	 *         disabled.
	 */

	public String deleteGroup(String groupName) {
		GroupManager groupManager = (GroupManager) userDetailsManager;
		String users[] = groupManager.findUsersInGroup(groupName);
		StringBuffer disabledUsers = new StringBuffer();
		String[] groups = groupManager.findAllGroups();
		// check all users.
		for (String userName : users) {
			UserDetails u = userDetailsManager.loadUserByUsername(userName);
			// Admins will never be disabled.
			if (!userIsAdmin(u)) {
				boolean userHasGroups = false;
				// loop through all groups from that users and check whether he
				// shows up.
				for (String group : groups) {
					// groups like anonymous, all user group do not count, user
					// will be disabled
					// anyway.
					if (!group.equals(groupName) && !group.equals(FenixSecurityConstant.ALL_USER_GROUP) && !group.equals(FenixRole.ANONYMOUS)) {
						String[] usersInGroup = groupManager.findUsersInGroup(group);
						for (String userInGroup : usersInGroup) {
							if (userName.equals(userInGroup)) {
								// yes we found a valid group and therefore this
								// user will not be
								// disabled.
								userHasGroups = true;
							}
						}
					}
				}
				// if the user does not exist anymore in any group, he will be
				// disabled!
				if (!userHasGroups) {
					FenixSecurityUser fsuser = new FenixSecurityUser(u);
					fsuser.setEnabled(false);
					userDetailsManager.updateUser(fsuser);
					disabledUsers.append(", " + userName);
					if (userDetailsManager.loadUserByUsername(userName).isEnabled())
						throw new FenixException("Inconsistent behavior detected, user should be disabled by now");
				}
			}
			groupManager.removeUserFromGroup(userName, groupName);
		}
		groupManager.deleteGroup(groupName);
		return disabledUsers.toString();
	}

	public Boolean deleteUserFromGroup(String user, String groupName) {
		GroupManager groupManager = (GroupManager) userDetailsManager;
		groupManager.removeUserFromGroup(user, groupName);
		UserDetails u = userDetailsManager.loadUserByUsername(user);
		Boolean userEnabled = Boolean.FALSE;
		// An admin remains enabled
		if (!userIsAdmin(u)) {
			// check whether the user appears in a group
			String[] groups = groupManager.findAllGroups();
			for (String group : groups) {
				if (!group.equals(FenixSecurityConstant.ALL_USER_GROUP)) {
					String[] users = groupManager.findUsersInGroup(group);
					for (String userFound : users) {
						if (user.equals(userFound)) {
							userEnabled = Boolean.TRUE;
						}
					}
				}
			}
			if (!userEnabled) {
				// If the user does not appear in a group, disable the user
				FenixSecurityUser fsuser = new FenixSecurityUser(u);
				fsuser.setEnabled(false);
				userDetailsManager.updateUser(fsuser);
			}
		}
		return userEnabled;
	}

	public List<FenixResourceVo> findAllResources() {
		List<Resource> resourceList = resourceDao.findAll();
		Domain2VoConverter do2vo = new Domain2VoConverter();
		return do2vo.convertDomain2VoWihtoutPermissions(resourceList, "xxxxx", null);
	}

	public void updateGroupPermissionOnResource(String group, String permission, long resourceId, boolean permissionStatus) {
		FenixPermission p = (FenixPermission) FenixPermission.buildFromName(permission);
		fenixPermissionManager.updateGroupPermissionOnResourceAssociated(group, p, resourceId, permissionStatus);
	}

	public void updateGroupPermissionOnResources(String group, String permission, long[] resourceIds, boolean permissionStatus) {
		for (long l : resourceIds) {
			FenixPermission p = (FenixPermission) FenixPermission.buildFromName(permission);
			fenixPermissionManager.updateGroupPermissionOnResourceAssociated(group, p, l, permissionStatus);
		}
	}

	public Map<String, Boolean> getGroupPermission4Resource(String group, long resourceId) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.READ, false);
		map.put(org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.WRITE, false);
		map.put(org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.DELETE, false);
		map.put(org.fao.fenix.web.modules.core.client.security.permissionmanager.FenixPermission.DOWNLOAD, false);

		List<FenixPermission> permissionList = fenixPermissionManager.getGroupPermissions4Resource(group, resourceId);
		for (FenixPermission fenixPermission : permissionList) {
			map.put(permissionMap.get(fenixPermission), true);
		}
		return map;
	}

	boolean userIsAdmin(UserDetails userDetails) {
		GrantedAuthority gas[] = userDetails.getAuthorities();
		boolean adminFound = false;
		for (GrantedAuthority grantedAuthority : gas) {
			if (grantedAuthority.getAuthority().equals(FenixRole.ADMIN)) {
				adminFound = true;
			}
		}
		return adminFound;
	}

	public PermissionVo getPermissions(long resourceId) {
		ResourcePermission permission = fenixPermissionManager.getPermissions(resourceId);

		PermissionVo vo = new PermissionVo();
		vo.setCanRead(permission.isHasReadPermission());
		vo.setCanWrite(permission.isHasWritePermission());
		vo.setCanDelete(permission.isHasDeletePermission());
		vo.setCanDownload(permission.isHasDownloadPermission());

		return vo;
	}

	private static final long serialVersionUID = 8611986224540642653L;

	public void setFenixLogin(FenixLogin fenixLogin) {
		this.fenixLogin = fenixLogin;
	}

	public void setFenixDomainUserDao(FenixDomainUserDao fenixDomainUserDao) {
		this.fenixDomainUserDao = fenixDomainUserDao;
	}

	public void setFenixDoubleUserManager(FenixDoubleUserManager fenixDoubleUserManager) {
		this.fenixDoubleUserManager = fenixDoubleUserManager;
	}

	public void setPersister(Persister persister) {
		this.persister = persister;
	}

	public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public static void setGrantedAuthorityMap(Map<String, GrantedAuthority> grantedAuthorityMap) {
		UserServiceImpl.grantedAuthorityMap = grantedAuthorityMap;
	}

	public static void setPermissionMap(Map<FenixPermission, String> permissionMap) {
		UserServiceImpl.permissionMap = permissionMap;
	}

	public FenixModuleConfigurationVo retrieveFenixModuleConfiguration() {
		FenixModuleConfigurationVo fenixModuleConfigurationVo = new FenixModuleConfigurationVo();
		fenixModuleConfigurationVo.setCcbsIsRunning(fenixModuleConfiguration.isCcbsIsRunning());
		fenixModuleConfigurationVo.setFpiIsRunning(fenixModuleConfiguration.isFpiIsRunning());
		fenixModuleConfigurationVo.setIpcIsRunning(fenixModuleConfiguration.isIpcIsRunning());
		return fenixModuleConfigurationVo;
	}

	public void setFenixModuleConfiguration(FenixModuleConfiguration fenixModuleConfiguration) {
		this.fenixModuleConfiguration = fenixModuleConfiguration;
	}
}
