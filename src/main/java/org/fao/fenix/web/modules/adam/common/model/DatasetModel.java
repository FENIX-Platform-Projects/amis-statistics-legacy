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
package org.fao.fenix.web.modules.adam.common.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class DatasetModel extends BaseModel implements IsSerializable {

	private String name;
	
	private String code;
	
	public DatasetModel() {
		
	}
	
	public DatasetModel(String name, String code) {
		this.setName(name);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		set("code", this.code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		set("name", this.name);
	}
	
}
