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
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TextService extends RemoteService {

	@Secured( { "ROLE_USER" })
	public Long saveText(String title, String content, String referenceDate);

	@Secured( { "ROLE_USER" })
	public Long updateText(Long textviewId, String content, String referenceDate);

	@Secured( { "ROLE_USER" })
	public Long saveTextGroup(String title, List<Long> textIds);
	
	@Secured( { "ROLE_USER" })
	public Long updateTextGroup(Long textGroupId, List<Long> textIds);
	
	@Secured( { "ROLE_USER" })
	public Long addTextToTextGroup(Long textGroupId, List<Long> textIds);
	
	@Secured( { "ROLE_USER" })
	public Long removeTextFromTextGroup(Long textGroupId, Long textId);
	
	
	
	@Secured( { "ROLE_USER" })
	public FenixResourceVo cloneTextGroup(TextGroupVO textGroupVO, List<TextVO> list, String referenceDate);

	
	/**
	 * Returns a list containing the attributes of a text e.g. ID, content,
	 * reference date.
	 * 
	 */
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getText(Long textviewId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public FenixResourceVo getNewTextResource(Long id);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public FenixResourceVo getNewTextGroupResource(Long id);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<TextVO> getTextsList(HashMap<Long, PermissionVo> textMap);
		
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public TextGroupVO getTextGroup(Long id);

}
