package org.fao.fenix.web.modules.core.server.utils.map;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.FenixFakeDataSource;
import org.fao.fenix.map.layer.LayerImporter;
import org.springframework.core.io.Resource;

/**
 * Just a wrapper class, used to prevent Spring dependence in GSLayerSynchronizer.
 * 
 * @author etj
 */
public class LayerImporterWrapper extends LayerImporter {
	private static final Logger LOGGER = Logger.getLogger(LayerImporterWrapper.class);

	private File baseDir;

	
	FenixFakeDataSource fenixFakeDataSource;
	
	public LayerImporterWrapper(Resource resource) {
		try {
			baseDir = resource.getFile();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void run() {        
		runThreaded();
	}
	
	public void setFenixFakeDataSource(FenixFakeDataSource fenixFakeDataSource) {
		setDbJdbcUrl(fenixFakeDataSource.getUrl());
		setDbUsername(fenixFakeDataSource.getUsername());
		setDbPassword(fenixFakeDataSource.getPassword());
	}

	@Override
	public void setDataDirs(List<String> datadirs) {
		List<String> rerootedDirs = new ArrayList<String>();
		for (String datadir : datadirs) {
			if(datadir.startsWith("/") ||     // UNIX 
				   datadir.charAt(1)==':') {  // WIN
				LOGGER.info("Adding source directory " + datadir);
				rerootedDirs.add(datadir);
			} else {
				File rerooted = new File(baseDir, datadir);
				String sdir = rerooted.getAbsolutePath();
				LOGGER.info("Adding source directory " + sdir + " (rerooted from "+datadir+" )");
				rerootedDirs.add(sdir);
			}
		}

		super.setDataDirs(rerootedDirs);
	}



}
