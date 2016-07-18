/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.x.server.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.x.XResourceType;
import org.springframework.core.io.Resource;

public class RSSWriter {
	
	private String path;
	
	private final static Logger LOGGER =  Logger.getLogger(RSSWriter.class);

	public RSSWriter() {
		
	}
	
	public RSSWriter(Resource resource) throws FenixException {
		try {
			this.setPath(resource.getFile().getPath());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	public void writeDatasetRSS(String rss, XResourceType resourceType) throws FenixException {
		long t0 = System.currentTimeMillis();
		try {
			File file = new File(path + File.separator + resourceType.name() + ".xml");
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(rss.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new FenixException(e.getMessage());
		}
		LOGGER.info("RSS created in " + (System.currentTimeMillis() - t0) + " ms @ " + (path + File.separator + "dataset.xml"));
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
}
