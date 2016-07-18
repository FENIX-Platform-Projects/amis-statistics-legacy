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

package org.fao.fenix.web.modules.text.client.control.vo;

import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.widget.DataListItem;

/**
 * A data list item with some text related info associated.
 *
 */
public class TextItem extends DataListItem {
    private TextVO textVO;
    private long textId;

    public TextVO getTextVO() {
        return textVO;
    }

    public void setTextVO(TextVO textVO) {
        this.textVO = textVO;
    }

    public String getTextTitle() {
        return textVO.getTitle();
    }

    public void setTextID(TextVO textVO) {
        this.textVO = textVO;
    }

    
    public void setTextId(long textId) {
        this.textId = textId;
    }
    
    public long getTextId() {
        return textId;
    }
    
    
    
}
