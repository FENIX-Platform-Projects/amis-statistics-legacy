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
package org.fao.fenix.web.modules.dataviewer.common.vo;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class DWCodesModelData extends BaseModel implements IsSerializable {

	private String code;
	
	private String label;
	
	private Boolean isList = false;  
	
	private String groupCode;
	
	private String groupLabel;
	
	private String domain;
	
	private String dateLastUpdate;
	
	public DWCodesModelData() {
		
	}
	
	public DWCodesModelData(String code, String label) {
		this.setLabel(label);
		this.setCode(code);
	}
		
	public DWCodesModelData(String code, String label, String groupCode, String groupLabel) {
		this.setLabel(label);
		this.setCode(code);
		this.setGroupCode(groupCode);
		this.setGroupLabel(groupLabel);
	}
	
	public DWCodesModelData(String code, String label, Boolean isList) {
		this.setLabel(label);
		this.setCode(code);
		this.setIsList(isList);
	}
	
	public DWCodesModelData(String code, String label, String domain, Boolean isList) {
		this.setLabel(label);
		this.setCode(code);
		this.setDomain(domain);
		this.setIsList(isList);
	}
	
	public DWCodesModelData(String code, String label, String domain) {
		this.setLabel(label);
		this.setCode(code);
		this.setDomain(domain);
	}

	public String getCode() {
		return code;
	}

	public Boolean getIsList() {
		return isList;
	}

	public String getDomain() {
		return domain;
	}

	public void setCode(String code) {
		this.code = code;
		set("code", this.code);
	}
	
	public void setIsList(Boolean isList) {
		this.isList = isList;
		set("isList", this.isList);
	}
	
	public Boolean isList() {
		return isList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		set("label", this.label);
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
		set("domain", this.domain);
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}

	public String getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(String dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}
	
}