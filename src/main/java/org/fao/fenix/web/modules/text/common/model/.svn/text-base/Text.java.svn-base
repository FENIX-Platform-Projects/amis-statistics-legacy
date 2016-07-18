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

package org.fao.fenix.web.modules.text.common.model;

import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Text extends BaseTreeModel implements IsSerializable {

	private static final long serialVersionUID = 2885802321308400582L;

	private TextVO textVO;
	
	public Text() {
	}

	public Text(TextVO vo) {
		setName(vo.getTitle());
		setTextVO(vo);
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getName() {
		return get("name");
	}

	public void setContent(String content) {
		getTextVO().setContent(content);
	}

	public String getContent() {
		return getTextVO().getContent();
	}

	public void setReferenceDate(String date) {
		getTextVO().setReferenceDate(date);
	}

	public String getReferenceDate() {
		return getTextVO().getReferenceDate();
	}

	public long getId() {
		return getTextVO().getResourceId();
	}

	public void setTextVO(TextVO vo) {
		textVO = vo;
	}

	public TextVO getTextVO() {
		return textVO;
	}


}
