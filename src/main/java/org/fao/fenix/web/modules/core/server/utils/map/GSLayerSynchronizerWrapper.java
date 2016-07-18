package org.fao.fenix.web.modules.core.server.utils.map;

import java.io.IOException;

import org.fao.fenix.map.layer.GSLayerSynchronizer;
import org.springframework.core.io.Resource;

/**
 * Just a wrapper class, used to prevent Spring dependence in GSLayerSynchronizer.
 * 
 * @author etj
 */
public class GSLayerSynchronizerWrapper extends GSLayerSynchronizer {

	public GSLayerSynchronizerWrapper(Resource resource) {
		try {
			setDatadir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void run() {

		runThreaded();
	}

}
