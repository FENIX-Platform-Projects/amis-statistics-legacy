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
package org.fao.fenix.web.modules.sldeditor.common.services;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class SLDEditorServiceEntry {

	private static SLDEditorServiceAsync sldEditorService = null;

	public static SLDEditorServiceAsync getInstance() {
		if (sldEditorService == null) {
			FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
			String urlSLDEditor = ep.getServiceEntryPointSLDEditor(GWT.getModuleBaseURL(), GWT.getModuleName());
			sldEditorService = (SLDEditorServiceAsync) GWT.create(SLDEditorService.class);
			ServiceDefTarget targetSLDEditorService = (ServiceDefTarget) sldEditorService;
			targetSLDEditorService.setServiceEntryPoint(urlSLDEditor);
		}
		return sldEditorService;
	}
	
}