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

package org.fao.fenix.web.modules.text.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class TextGroupVO extends ResourceVO {

	private List<TextVO> textList = new ArrayList<TextVO>();

	
	public TextGroupVO() {
	}

	public void addText(TextVO r) {
		textList.add(r);
	}

	public void setTextList(List<TextVO> textList) {
		this.textList = textList;
	}

	public List<TextVO> getTextList() {
		return textList;
	}

	public String getType() {
		return ResourceType.TEXTGROUP;
	}


}
