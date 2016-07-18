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
package org.fao.fenix.web.modules.metadataeditor.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class MetadataServiceEntry {

	private static MetadataServiceAsync metadataService = null;

	public static MetadataServiceAsync getInstance() {
		if (metadataService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlMetadata = ep.getServiceEntryPointMetadata(GWT.getModuleBaseURL(), GWT.getModuleName());
			metadataService = (MetadataServiceAsync) GWT.create(MetadataService.class);
			ServiceDefTarget targetMetadataService = (ServiceDefTarget) metadataService;
			targetMetadataService.setServiceEntryPoint(urlMetadata);
		}
		return metadataService;
	}
}
