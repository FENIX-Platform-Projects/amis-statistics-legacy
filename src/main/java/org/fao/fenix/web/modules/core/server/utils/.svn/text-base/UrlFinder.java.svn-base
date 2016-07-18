package org.fao.fenix.web.modules.core.server.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

public class UrlFinder {
	
	private final static Logger LOGGER = Logger.getLogger(UrlFinder.class);

	public static String systemPathServices;

	public static String fenixServicesUrl = "";
	
	public static String xServicesUrl = "";
	
	public static String fenixWebUrl = "";
	
	public static String webRoot = "";
	
	public static String fenixBirtUrl = "";
	
	public static String geoserverUrl = "";
	
	public static String geoserverCatalogPath = "";
	
	public static String nameServicesWebAppl = "";
	
	private static File fenixDir;
	
	public static String port;
	
	public static String serverip;
	
	private Resource resource;
	
	public UrlFinder(Resource aResource) {
		resource = aResource;
		try {
			fenixDir = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFenixServicesUrl() {
		
		// find Geoserver path
		systemPathServices = getSystemPath();
		int index_s = systemPathServices.length() - 1;
		while ((systemPathServices.charAt(index_s) != '/') && (systemPathServices.charAt(index_s) != '\\'))
			index_s--;
		systemPathServices = systemPathServices.substring(0, index_s);
		
		// basic URL
		String baseURL = "http://" + serverip + ":" + port;
		
		fenixServicesUrl =  baseURL +  "/fenix-services/services/CommunicationModuleService";
		xServicesUrl =  baseURL +  "/fenix-services/services/XWebService";
		fenixWebUrl = baseURL + "/fenix-web/fenix/Fenix.html";
		webRoot = baseURL + "/fenix-web/";
		fenixBirtUrl = baseURL + "/fenix-birt";
		geoserverUrl = baseURL + "/geoserver/";
		geoserverCatalogPath = systemPathServices + "/geoserver/data/catalog.xml";
		
		LOGGER.info("___________________________________________________________");
		LOGGER.info("FENIX-WEB @ " + fenixWebUrl);
		LOGGER.info("WEB ROOT  @ " + webRoot);
		LOGGER.info("GEOSERVER @ " + geoserverUrl);
		LOGGER.info("___________________________________________________________");
		System.out.println("FENIX-WEB @ " + fenixWebUrl);
	}

	private String getSystemPath() {
		String systemPath = fenixDir.getAbsolutePath();
		systemPath = systemPath.replace('\\', '/');
		return systemPath;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	
}