/*
 */

package org.fao.fenix.web.modules.core.server.utils.map;

import org.apache.log4j.Logger;
import org.fao.fenix.map.fsharvesting.GeoTiffHarvester;

/**
 *
 * @author ETj
 */
public class GeoTiffHarvesterWrapper extends GeoTiffHarvester {
	private final static Logger LOGGER = Logger.getLogger(GeoTiffHarvesterWrapper.class);

	private int threadedStartDelay = 0;
	private boolean enabled = false;

	private boolean running = false;

	/**
	 * Run the process in a separate thread.
	 */
	public boolean run() {
		if(!enabled) {
			LOGGER.info(getClass().getName() + " is not enabled and will not run.");
			return false;
		}

		if (sBootDir == null || defaultGeoserver == null || geoserverDao == null || wmsMapProviderDao == null)
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

					GeoTiffHarvesterWrapper.super.run();

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
