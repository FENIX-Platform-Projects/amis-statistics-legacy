package org.fao.fenix.web.modules.core.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class UserServiceEntry {

	private static UserServiceAsync userService = null;

	public static UserServiceAsync getInstance() {
		if (userService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlUser = ep.getServiceEntryPointUserNew(GWT.getModuleBaseURL(), GWT.getModuleName());
			userService = (UserServiceAsync) GWT.create(UserService.class);
			ServiceDefTarget targetUserService = (ServiceDefTarget) userService;
			targetUserService.setServiceEntryPoint(urlUser);
		}
		return userService;
	}
}
