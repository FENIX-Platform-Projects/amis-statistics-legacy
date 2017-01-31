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
package org.fao.fenix.web.modules.olapviewer.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class OlapViewerServiceEntry {

	private static OlapViewerServiceAsync olapService = null;

	public static OlapViewerServiceAsync getInstance() {
		if (olapService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlOlap = ep.getServiceEntryPointOlap(GWT.getModuleBaseURL(), GWT.getModuleName());
			olapService = (OlapViewerServiceAsync) GWT.create(OlapViewerService.class);
			ServiceDefTarget targetOlapService = (ServiceDefTarget) olapService;
			targetOlapService.setServiceEntryPoint(urlOlap);
		}
		return olapService;
	}
	
}
