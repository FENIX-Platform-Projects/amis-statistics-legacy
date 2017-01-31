package org.fao.fenix.web.modules.core.server.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.fao.fenix.map.wmc.WMCImporter;
import org.springframework.core.io.Resource;

/**
 * Just a wrapper class, used to prevent Spring dependence in WMCImporter.
 * 
 * @author etj
 */
public class MapPreloader extends WMCImporter {


	public MapPreloader(Resource resource) {

		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void run() {
        int maps = mapDao.findAll().size();
        if(maps>0) {
            Logger.getLogger(getClass()).info("There are already " + maps + " maps in the system. Maps preloading will be skipped.");
            return;
        }
		importMaps();
	}

}
