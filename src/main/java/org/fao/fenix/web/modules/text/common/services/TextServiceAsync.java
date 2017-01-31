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

package org.fao.fenix.web.modules.text.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.PermissionVo;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TextServiceAsync {

	public void saveText(String title, String content, String referenceDate, AsyncCallback<Long> callback);
	
	public void saveTextGroup(String title, List<Long> textIds, AsyncCallback<Long> callback);
	
	public void updateText(Long textviewId, String content, String referenceDate, AsyncCallback<?> callback);
	
	public void updateTextGroup(Long textGroupId, List<Long> textIds, AsyncCallback<?> callback);
	
	public void addTextToTextGroup(Long textGroupId, List<Long> textIds, AsyncCallback<?> callback);
	
	public void removeTextFromTextGroup(Long textGroupId, Long textId, AsyncCallback<?> callback);

	public void getText(Long textviewId, AsyncCallback<List<String>> callback);

	public void getNewTextResource(Long id, AsyncCallback<FenixResourceVo> callback);
	
	public void getNewTextGroupResource(Long id, AsyncCallback<FenixResourceVo> callback);
	
	public void getTextsList(HashMap<Long, PermissionVo> textMap, AsyncCallback<List<TextVO>> callback);
			
	public void getTextGroup(Long id, AsyncCallback<TextGroupVO> callback);
	
	public void cloneTextGroup(TextGroupVO textGroupVO, List<TextVO> list, String referenceDate, AsyncCallback<FenixResourceVo> callback);
	
}
