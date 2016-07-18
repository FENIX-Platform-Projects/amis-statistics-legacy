package org.fao.fenix.web.modules.core.server.utils;

import java.io.File;

public class Setting {

	public static String XML_FILES;

	public static String MAP_FILES;
	
	public static String GAUL_FILES;

	public static String systemPathBirt;
	
	public static String systemPathIPC;
	
	public static String systemPathServices;
	
	public static String nameBirtWebAppl = "";

	public static String nameServicesWebAppl = "";

	public static String nameCommunicationWebApp = "";
	
	public static String nameIPCWebAppl = "";

	public Setting() {
		String systemPath = getSystemPath();
		systemPathBirt = systemPath;
		systemPathServices = systemPath;
		systemPathIPC = systemPath;
		int index = systemPathBirt.length() - 1;
		int index_s = systemPathServices.length() - 1;
		int index_i = systemPathIPC.length() - 1;
		
		while ((systemPathBirt.charAt(index) != '/') && (systemPathBirt.charAt(index) != '\\')) {
			index--;
		}
		while ((systemPathServices.charAt(index_s) != '/') && (systemPathServices.charAt(index_s) != '\\')) {
			index_s--;
		}
		while ((systemPathIPC.charAt(index_i) != '/') && (systemPathIPC.charAt(index_i) != '\\')) {
			index_i--;
		}
		systemPathBirt = systemPathBirt.substring(0, index);
		systemPathServices = systemPathServices.substring(0, index_s);
		systemPathIPC = systemPathIPC.substring(0, index_i);
		//XML_FILES = systemPath + "/org.fao.fenix.web.modules.core.Fenix/xml/";
		XML_FILES = systemPath + "/fenix/xml/";
		MAP_FILES = systemPath + "/xmlMap/";
		GAUL_FILES = systemPath + "/gaulCodes/";
	}

	public static String getBirtApplName() {
		if (Setting.nameBirtWebAppl.equals("")) {
			String[] nameFile = null;
			File f = new File(systemPathBirt);
			nameFile = f.list();

			for (int i = 0; i < nameFile.length; i++) {

				if (new File(systemPathBirt + "/" + nameFile[i]).isDirectory()) {
					if (nameFile[i].contains("birt")) {
						Setting.nameBirtWebAppl = nameFile[i];
						return Setting.nameBirtWebAppl;
					}
				}

			}
		}
		return Setting.nameBirtWebAppl;
	}

	public static String getServicesApplName() {
		if (Setting.nameServicesWebAppl.equals("")) {
			String[] nameFile = null;
			File f = new File(systemPathServices);
			nameFile = f.list();
			for (int i = 0; i < nameFile.length; i++)
				if (new File(systemPathServices + "/" + nameFile[i]).isDirectory())
					if (nameFile[i].contains("services")) {
						Setting.nameServicesWebAppl = nameFile[i];
						return Setting.nameServicesWebAppl;
					}
		}
		return Setting.nameServicesWebAppl;
	}

	public static String getReportEngine() {
		return systemPathBirt + "/" + getBirtApplName() + "/WEB-INF/platform/";
	}

	public static String getSystemPath() {
		File fenixDir = FenixWebConfig.getFenixDir();
		String systemPath = fenixDir.getAbsolutePath();
		systemPath = systemPath.replace('\\', '/');
		return systemPath;
	}

	public static String getFenixPath() {
		String systemPath = getSystemPath();
		int index = systemPath.length() - 1;
		while (systemPath.charAt(index) != '/') {
			index--;
		}

		return systemPath.substring(0, index);
	}

	public static String getApplicationName() {
		String systemPath = getSystemPath();
		int index = systemPath.length() - 1;
		while (systemPath.charAt(index) != '/') {
			index--;
		}
		return systemPath.substring(index, systemPath.length());

	}
	
	public static String getIPCApplName() {
		if (Setting.nameIPCWebAppl.equals("")) {
			String[] nameFile = null;
			File f = new File(systemPathIPC);
			nameFile = f.list();

			for (int i = 0; i < nameFile.length; i++) {

				if (new File(systemPathIPC + "/" + nameFile[i]).isDirectory()) {
					if (nameFile[i].contains("ipc")) {
						Setting.nameIPCWebAppl = nameFile[i];
						return Setting.nameIPCWebAppl;
					}
				}

			}
		}
		return Setting.nameIPCWebAppl;
	}

	public static String getIPC() {
		return systemPathIPC + "/" + getIPCApplName() + "/org.fao.ipc.IPC/";
	}
}