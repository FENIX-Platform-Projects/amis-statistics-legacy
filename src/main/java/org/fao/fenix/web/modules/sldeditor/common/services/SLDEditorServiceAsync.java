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

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SLDEditorServiceAsync {

	public void createSLD(String sldName, String sldDescription, List<SLDEditorRuleVO> vos, AsyncCallback<String[]> callback) throws FenixGWTException;
	
	public void saveSLD(String pathFile, String nameFile, String boxValuePath, String boxValueName, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void uploadSLD(String nameFile, String pathFile, String nameStyle, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void createSLDRaster(String sldName, String sldDescription, List<SLDEditorRuleVO> vos, AsyncCallback<String[]> callback) throws FenixGWTException;

	public void createSLDeditor(String pathFile, String nameFile, AsyncCallback<List<SLDEditorRuleVO>> callback) throws FenixGWTException;
}

