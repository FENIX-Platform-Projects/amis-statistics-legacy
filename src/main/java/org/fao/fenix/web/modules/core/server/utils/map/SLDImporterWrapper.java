/*
 */

package org.fao.fenix.web.modules.core.server.utils.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.map.layer.SLDImporter;
import org.springframework.core.io.Resource;

/**
 *
 * @author ETj
 */
public class SLDImporterWrapper extends SLDImporter {
	private final static Logger LOGGER = Logger.getLogger(SLDImporterWrapper.class);

	private File baseDir;

	private int threadedStartDelay = 0;
	private boolean enabled = true;

	private boolean running = false;

	public SLDImporterWrapper(Resource resource) {
		try {
			baseDir = resource.getFile();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
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

	/**
	 * Run the process in a separate thread.
	 */
	public boolean run() {
		if(!enabled) {
			LOGGER.info(getClass().getName() + " is not enabled and will not run.");
			return false;
		}

		if (datadirs == null || defaultGeoserver == null )
			throw new IllegalStateException("Params are not set");

		synchronized(getClass()) {
			if(running) {
				LOGGER.warn(getClass().getSimpleName() + " is already running. Will not run until old run is over.");
				return false;
			} else
				running = true;
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (threadedStartDelay > 0) {
						LOGGER.info("Sleeping for " + threadedStartDelay + " seconds before running.");
						Thread.sleep(threadedStartDelay * 1000);
					}

					SLDImporterWrapper.super.run();

				} catch (InterruptedException ex) {
					LOGGER.error("Interrupted", ex);
				} finally {
					synchronized(getClass()) {
						running = false;
					}
				}
			}
		}, this.getClass().getSimpleName()+"_thread").start();
		return true;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setThreadedStartDelay(int threadedStartDelay) {
		this.threadedStartDelay = threadedStartDelay;
	}
}
