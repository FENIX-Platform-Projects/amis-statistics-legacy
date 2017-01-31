package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;

public class FenixUser {

	private static boolean hasAnonymousRole = true;
	private static boolean hasUserRole = false;
	private static boolean hasAdminRole = false;
	private static boolean hasFpiRole = false;
	private static boolean hasCcbsRole = false;
	private static boolean hasIpcRole = false;

	public static boolean hasAnonymousRole() {
		return hasAnonymousRole;
	}

	public static boolean hasUserRole() {
		return hasUserRole;
	}

	public static boolean hasAdminRole() {
		return hasAdminRole;
	}

	public static boolean hasFpiRole() {
		return hasFpiRole;
	}

	public static boolean hasCcbsRole() {
		return hasCcbsRole;
	}

	public static boolean hasIpcRole() {
		return hasIpcRole;
	}
	
	
	public static void giveAnonymousRole() {
		FenixUser.hasAnonymousRole = true;
		FenixUser.hasUserRole = false;
		FenixUser.hasAdminRole = false;
		FenixUser.hasFpiRole = false;
		FenixUser.hasCcbsRole = false;
		FenixUser.hasIpcRole = false;
	}

	private static void giveUserRole() {
		FenixUser.hasUserRole = true;
	}

	private static void giveAdminRole() {
		FenixUser.hasAdminRole = true;
	}

	private static void giveFpiRole() {
		FenixUser.hasFpiRole = true;
	}

	private static void giveCcbsRole() {
		FenixUser.hasCcbsRole = true;
	}

	private static void giveIpcRole() {
		FenixUser.hasIpcRole = true;
	}

	public static void populateRoles(FenixUserVo fenixUserVo) {
		String[] roles = fenixUserVo.getRoles();
		for (int i = 0; i < roles.length; i++) {
			String role = roles[i];
			if (role.equals(FenixRole.USER))
				giveUserRole();
			if (role.equals(FenixRole.ADMIN))
				giveAdminRole();
			if (role.equals(FenixRole.FPI))
				giveFpiRole();
			if (role.equals(FenixRole.CCBS))
				giveCcbsRole();
			if (role.equals(FenixRole.IPC))
				giveIpcRole();
		}
	}

}
