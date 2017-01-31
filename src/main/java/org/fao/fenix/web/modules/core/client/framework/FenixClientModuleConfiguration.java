package org.fao.fenix.web.modules.core.client.framework;

public class FenixClientModuleConfiguration {

	private static boolean ipcIsRunning = false;
	private static boolean fpiIsRunning= false;
	private static boolean ccbsIsRunning= false;

	public static boolean isIpcIsRunning() {
		return ipcIsRunning;
	}

	public static void setIpcIsRunning(boolean ipcIsRunning) {
		FenixClientModuleConfiguration.ipcIsRunning = ipcIsRunning;
	}

	public static boolean isFpiIsRunning() {
		return fpiIsRunning;
	}

	public static void setFpiIsRunning(boolean fpiIsRunning) {
		FenixClientModuleConfiguration.fpiIsRunning = fpiIsRunning;
	}

	public static boolean isCcbsIsRunning() {
		return ccbsIsRunning;
	}

	public static void setCcbsIsRunning(boolean ccbsIsRunning) {
		FenixClientModuleConfiguration.ccbsIsRunning = ccbsIsRunning;
	}

}
