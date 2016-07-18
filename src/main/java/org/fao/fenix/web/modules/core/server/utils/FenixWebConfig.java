package org.fao.fenix.web.modules.core.server.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;

public class FenixWebConfig {

	private Resource resource;

	private static File fenixDir;

	public FenixWebConfig(Resource aResource) {
		resource = aResource;
		try {
			fenixDir = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * returns absolute path configured in application context usually pathTo
	 * org.fao.fenix.xebFenix dir
	 * 
	 * @return
	 */
	public static File getFenixDir() {
		return fenixDir;
	}

}