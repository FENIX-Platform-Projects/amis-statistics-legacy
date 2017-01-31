package org.fao.fenix.web.modules.core.client.security;

import org.fao.fenix.web.modules.ipc.common.services.IPCServiceEntry;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

/***
 * 
 * Temporary solution for the 'soft' integration of the IPC module
 *
 */
public class IPCSecurity {

	private static String ipcSecurityToken;

	public static String getIPCSecurityToken() {
		return ipcSecurityToken;
	}

	public static void setIPCSecurityToken(String ipcSecurityToken) {
		IPCSecurity.ipcSecurityToken = ipcSecurityToken;
	}

	
	public static void setSecurityTokenFromIPC(String username, String name) {
		IPCServiceEntry.getInstance().getSecurityTokenFromIPC(username, name, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				System.out.println();
				setIPCSecurityToken(result);
			}

			public void onFailure(Throwable caught) {
				Info.display("IPC: getSecurityTokenFromIPC FAILED !", caught.getLocalizedMessage());
			}

		});

	}

}
